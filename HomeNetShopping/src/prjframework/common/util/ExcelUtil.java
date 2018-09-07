package prjframework.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 엑셀파일을 읽어 배열로 반환한다.
 * 
 *  Rule 1. 수식은 값으로 변환해야한다
 *  Rule 2. Sheet1의 데이터를 포함한다.
 *  Rule 3. 소숫점데이터는 모두 정수로 변환된다
 *  Rule 4. POI 3.2 버전 이상을 사용해야 정상 작동됩니다.(이전버전 사용시 XLS 사용자 정의 날짜형식 Format 인식 못함.)
 *  
 *   
 * @author 나건웅
 * @created 2015-01-14
 */
public class ExcelUtil {
	
	public Log log = LogFactory.getLog(getClass());
	private static final String CLASS = "ExcelUtil : ";
	protected static final String ROWMERGE = "$MERGE_ROW$";
	private String[][] result = null;
	private String source = null;
	private String excelFileName = null;
	private String METHOD = null;
	
	private int startRow = 0;//시작행
	
	// XLS
	private FileInputStream fin;
	private HSSFWorkbook wbXLS;
	private HSSFSheet sheetXLS;
	
	// XLSX
	private XSSFWorkbook wbXLSX;
	private XSSFSheet sheetXLSX;
	
	public ExcelUtil(String source) throws FileNotFoundException, IOException {
		this.source = source;
		excelFileName = getExcelFileName(source);
		
		File file = new File(source);
		
		/**
		 * Workbook 생성
		 * xlsx와 xls 파일 별도 생성
		 */
		if (excelFileName.indexOf(".xlsx") > 0) {
			wbXLSX = new XSSFWorkbook(new FileInputStream(file));
		}
		else if (excelFileName.indexOf(".xls") > 0) {
			try {
				FileInputStream fin = new FileInputStream(source); //읽을 엑셀 경로
				POIFSFileSystem fs = new POIFSFileSystem(fin);
				wbXLS = new HSSFWorkbook(fs); // 읽어들인 Excel파일을 다루기 위한 workbook instance를 생성시킨다.
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			} finally {
			    if (fin != null){
					fin.close();
			    }	
			}
		}
		
	}
	
	/**
	 * Excel 파일명 리턴
	 * @param excel
	 * @return String
	 * @throws IOException
	 */
	public String getExcelFileName(String excel) throws IOException {
		//check file
		File file = new File(excel);
		if (!file.exists() || !file.isFile() || !file.canRead()) {
			throw new IOException(excel);
		}
		return excel;
	}

	/**
	 * Excel 파일 Read
	 * @return String[][]
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String[][] read() throws FileNotFoundException, IOException {
		if (excelFileName.indexOf(".xlsx") > 0) {
			try {
				result = readXLSX();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (excelFileName.indexOf(".xls") > 0) {
			try {
				result = readXLS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * XLSX 파일 Read
	 * @return String[][]
	 * @throws IOException
	 */
	public String[][] readXLSX() throws Exception {
		
		METHOD = "readXLSX()";
		
		//Sheet 한개 기준
		int firstOfSheet = 1; //wbXLSX.getNumberOfSheets();
		for (int i = 0; i < firstOfSheet; i++) {
			try {
				sheetXLSX = wbXLSX.getSheetAt(i);
				
				startRow = getStartRow();
				
				//Get Excel Row Size
				int rowSize = sheetXLSX.getPhysicalNumberOfRows();
				
				// n줄을 제외한 데이터의 row. startRow
				if (rowSize >= startRow) {
					rowSize = rowSize - startRow;
				} else if (rowSize < startRow) {
					// 출력행 없음
					continue;
				}
				
				result = new String[rowSize][];
				int colsize = sheetXLSX.getRow(i).getLastCellNum();
				int resultRowNum = 0;
				for (Row row : sheetXLSX) {
					try {
						
						// n줄을 제외한 데이터의 row.
						if (row.getRowNum() < startRow || row == null) {
							continue;
						}
		
						result[resultRowNum] = new String[colsize];
						
						for (Cell cell : row) {
		
							String cellString = "";
							
							switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING: // 1
									
									cellString = cell.getRichStringCellValue().getString().trim();
									break;
			
								case Cell.CELL_TYPE_NUMERIC: // 0
									/*
									if (DateUtil.isCellDateFormatted(cell)) {
										cellString = String.valueOf(cell.getDateCellValue());
									} else {
										cellString = String.valueOf((long)cell.getNumericCellValue());
									}*/
									cellString = String.valueOf((long)cell.getNumericCellValue());
									break;
			
								case Cell.CELL_TYPE_BOOLEAN: // 4
									
									cellString = String.valueOf(cell.getBooleanCellValue());
									break;
			
								case Cell.CELL_TYPE_FORMULA: // 2
									
									cellString = String.valueOf(cell.getCellFormula());
									break;
									
								case Cell.CELL_TYPE_BLANK: // 3
									
									cellString = "";
									break;
									
								case Cell.CELL_TYPE_ERROR: // 5
								
									cellString = String.valueOf(cell.getErrorCellValue());
									break;
									
								default:
									
									cellString = "";
									
								}
							result[resultRowNum][cell.getColumnIndex()] = cellString;
						}
						resultRowNum++;
					} catch (NegativeArraySizeException e) {
					// 셀의 필요없는 로우를 읽으려고 할때 발생하는 예외이다.
					// 적절하게 배열을 초기화 해서 넘김으로 에러를 방지한다.
					result[resultRowNum] = new String[0];
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new Exception(CLASS + METHOD + "에서 에러가 발생했습니다. \n '" + source + "' 파일을 읽는데 에러가 발생했습니다.");
		
			}
		}
			
		return result;
	}
	
	/**
	 * XLS 파일 Read
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readXLS() throws Exception {

		String METHOD = "readXLS()";
		
		// Sheet 한개 기준 
		int firstOfSheet = 1; //wbXLS.getNumberOfSheets();
		for (int i = 0; i < firstOfSheet; i++) {
			try {
				sheetXLS = wbXLS.getSheetAt(i);
				
				startRow = getStartRow();
				
				//Get Excel Row Size
				int rowSize = sheetXLS.getPhysicalNumberOfRows();

				// n줄을 제외한 데이터의 row. startRow
				if (rowSize >= startRow){
					rowSize = rowSize - startRow;
				} else if (rowSize < startRow) {
					// 출력행 없음
					continue;
				}
				
				result = new String[rowSize][];
				int colSize = sheetXLS.getRow(1).getLastCellNum(); // 행의 셀 값
				HSSFRow row;
				for (int y = 0; y < rowSize; y++) {	
				    try {

				    	row = sheetXLS.getRow(y); //시트에 대한 행의 값
				    	
				    	// n줄을 제외한 데이터의 row.
						if (row.getRowNum() < startRow || row == null) {
							rowSize++;
							continue;
						}
				    	
						result[y - startRow] = new String[colSize];
						HSSFCell cell;
						
					    for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					       
					    	cell = row.getCell((short) j);

					        String cellString = "";
					        
					        if(cell != null) {
								// cell 타입에 따른 cell의 값을 출력시킨다
								switch (cell.getCellType()) {
									
									case HSSFCell.CELL_TYPE_NUMERIC : // 0
										
										/**
										 * POI 3.2 버전 이상을 사용해야 날짜형식 검색이 제대로 됨
										 * 사용자 정의 서식 날짜는 Format을 체크 하지 못함
										 * 
										 * @author 박정기
										 * @since 2014-03-14
										 */
										/*if (HSSFDateUtil.isCellDateFormatted(cell)) {
											cellString = String.valueOf(cell.getDateCellValue());
										} else {
											if (HSSFDateUtil.isValidExcelDate(cell.getNumericCellValue())){
												cellString = String.valueOf(cell.getDateCellValue());
											} else {
												cellString = String.valueOf((long)cell.getNumericCellValue());
											}
										}*/
										
										/**
										 * 날짜형식 안맞아서 오류난다. 그래서 데이터 그대로 반환하게 변경한다. 
										 * @author 이민섭
										 * @since 2015-04-20
										 */
										cellString = String.valueOf((long)cell.getNumericCellValue());
										break;
									
									case HSSFCell.CELL_TYPE_STRING : // 1
									   
									    cellString = cell.getStringCellValue().trim();
									    break;
									
									case HSSFCell.CELL_TYPE_FORMULA : // 2
										
										cellString = String.valueOf(cell.getCellFormula());
									    break;
									
									case HSSFCell.CELL_TYPE_BLANK : // 3
	
										cellString = "";	
										break;
									
									case HSSFCell.CELL_TYPE_BOOLEAN : // 4
										
									    cellString = String.valueOf(cell.getBooleanCellValue());
									    break;
									
									case HSSFCell.CELL_TYPE_ERROR : // 5
										
									    cellString = String.valueOf(cell.getErrorCellValue());
									    break;
									
									default :
										cellString = "";
								}
					        } else {
					        	/**
								 * null 에러 떨어져서 추가한다 ㅡㅡ 아놔
								 * @author 이민섭
								 * @since 2015-04-30
								 */
					        	cellString = "";
					        }
							
							result[y - startRow][j] = cellString;
					    }
					} catch (NegativeArraySizeException e) {
						// 셀의 필요없는 로우를 읽으려고 할때 발생하는 예외이다.
						result[y - startRow] = new String[0];
					}
				}
			} catch (Exception e) {
			
				e.printStackTrace();
				throw new Exception(CLASS + METHOD + "에서 에러가 발생했습니다. \n '" + source + "' 파일을 읽는데 에러가 발생했습니다.");
		
			}
		}
        
		return result;
		
	}
	
	/**
	 * Excel 파일 Read 결과 테스트용
	 * @param result
	 */
	public void view(String[][] result) {
		if (result != null) { 
			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result[i].length; j++) {
					System.out.print(result[i][j] + "|");
				}
				//System.out.println("");
			}
		}
	}
	
	/**
	 * 시작열 지정
	 * @param startRow
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * 시작열 리턴
	 * @return int
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * Sheet 이름 반환
	 * @return String
	 */
	public String getSheetName() throws FileNotFoundException, IOException {
		// Sheet 한개 기준
		String sheetName = null;
		
		if (excelFileName.indexOf(".xlsx") > 0) {
			sheetName = wbXLSX.getSheetName(0);
		} else if (excelFileName.indexOf(".xls") > 0) {
			sheetName = wbXLS.getSheetName(0);
		}
		
		return sheetName;
	}

}