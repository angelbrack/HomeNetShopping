package prjframework.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * -----------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 소속 : 미래지식포털
 * 수정일 : 2017.04.01
 * 수정자 : 김진섭
 * 수정내용 : 최초생성
 * ------------------------------------------------------------------------
 */
public abstract class ExcelDownUtil {
	private final static Log log = LogFactory.getLog(ExcelDownUtil.class);

	@SuppressWarnings({"resource", "rawtypes"})
	public static String selectExcelList(List<EgovMap> excelList, HashMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 해더 선언
		response.setHeader("Content-Type", "text/html;charset=utf-8");

		// 실제 파일명 생성
		String excelNm = StringUtil.valueOf(map.get("excelNm"));
		String fileName = excelNm + System.currentTimeMillis() + ".xlsx";

		// 파일 경로 설정
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String uploadFilePath = StringUtil.valueOf(map.get("downloadFilePath"));
		uploadFilePath = uploadFilePath.substring(1, uploadFilePath.length());
		String basePath = realPath + uploadFilePath;
		String autoCellWidth = "";
		String excelLeft = "";
		String excelRight = "";
		String typeNo = "";

		if(!StringUtil.valueOf(map.get("autoCellWidth")).equals("")) {
			autoCellWidth = StringUtil.valueOf(map.get("autoCellWidth"));
		}

		if(!StringUtil.valueOf(map.get("excelLeft")).equals("")) {
			excelLeft = StringUtil.valueOf(map.get("excelLeft"));
		}

		if(!StringUtil.valueOf(map.get("excelRight")).equals("")) {
			excelRight = StringUtil.valueOf(map.get("excelRight"));
		}

		// poi 엑셀 입력 start
		SXSSFWorkbook xlsxWb = new SXSSFWorkbook();
		Sheet sheet1 = xlsxWb.createSheet("firstSheet");

		Row row = null;
		Cell cell = null;

		// 타이틀 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle Hstyle = xlsxWb.createCellStyle();
		Hstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		Hstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		Hstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Hstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 내용 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle Cstyle = xlsxWb.createCellStyle();
		Cstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Cstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Cstyle.setWrapText(false);

		// 내용 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle CstyleLeft = xlsxWb.createCellStyle();
		CstyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		CstyleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		CstyleLeft.setWrapText(false);

		// 내용 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle CstyleRight = xlsxWb.createCellStyle();
		CstyleRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		CstyleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		CstyleRight.setWrapText(false);

		// 첫번째 타이틀 라인
		row = sheet1.createRow(0);

		// 타이틀 정보를 배열로 생성
		String[] arrExcelTit = null;

		if(!StringUtil.valueOf(map.get("excelTit")).equals("")) {
			arrExcelTit = StringUtil.valueOf(map.get("excelTit")).split("\\|");
		}

		// 엑셀 사이즈를 수동으로 조절시
		String[] arrExcelWidth = null;

		if(!StringUtil.valueOf(map.get("excelWidth")).equals("")) {
			arrExcelWidth = StringUtil.valueOf(map.get("excelWidth")).split("\\|");
		}

		// 타이틀 정보 cell 입력
		if(arrExcelTit != null) {
			for(int j = 0; j < arrExcelTit.length; j++) {
				cell = row.createCell(j);
				cell.setCellValue(arrExcelTit[j]);	// 타이틀 내용
				cell.setCellStyle(Hstyle);	// 타이틀 스타일 적용
			}
		}

		// 내용 정보를 배열로 cell 입력
		if(excelList != null) {

			int size = excelList.size();
			int rowNum = 1;

			for(int i = 0; i < size; i++) {

				row = sheet1.createRow(rowNum++);

				int forNo = 1;

				for(int k = 0; k < excelList.get(i).size(); k++) {

					if(forNo < 10) {
						typeNo = "0" + Integer.toString(forNo);
					} else {
						typeNo = Integer.toString(forNo);
					}

					cell = row.createCell(k);

					if(excelLeft != null && !excelLeft.equals("")) {
						if(excelLeft.indexOf(typeNo) > -1) {
							cell.setCellStyle(CstyleLeft);	// 내용 스타일 적용(왼쪽 정렬)
						} else {
							cell.setCellStyle(Cstyle);	// 내용 스타일 적용(중앙 정렬)
						}
					} else if(excelRight != null && !excelRight.equals("")) {
						if(excelRight.indexOf(typeNo) > -1) {
							cell.setCellStyle(CstyleRight);	// 내용 스타일 적용(오른쪽 정렬)
						} else {
							cell.setCellStyle(Cstyle);	// 내용 스타일 적용(중앙 정렬)
						}
					} else {
						cell.setCellStyle(Cstyle);	// 내용 스타일 적용(중앙 정렬)
					}

					cell.setCellValue(String.valueOf(excelList.get(i).getValue(k)));	// 데이터베이스에서 가져온 순서대로 입력

					forNo++;

				}

				if(i % 100 == 0) {
					((SXSSFSheet)sheet1).flushRows(100);
				}
			}

			if(autoCellWidth.equals("S")) {
				// cell 크기 자동 맞춤
				if(arrExcelWidth != null){
					for(int x = 0; x < arrExcelWidth.length; x++) {
						sheet1.autoSizeColumn(x);
						// 자동맞춤여부
						sheet1.setColumnWidth(x, Integer.parseInt(arrExcelWidth[x]) * 100);  // cell 크기 자동으로 생성시 너무 붙어서 간격 조정
					}
				}
			} else {
				// cell 크기 자동 맞춤
				if(arrExcelTit != null){
					for(int x = 0; x < arrExcelTit.length; x++) {
						sheet1.autoSizeColumn(x);
						// 자동맞춤여부
						if(!"N".equals(autoCellWidth)) {
							sheet1.setColumnWidth(x, ((sheet1.getColumnWidth(x)) * 2) + 516);  // cell 크기 자동으로 생성시 너무 붙어서 간격 조정
						}
					}
				}
			}
		}
		// poi 엑셀 입력 end

		try {
			String filesPath = basePath + fileName;

			if(filesPath.indexOf("../") >= 0) {
				return "";
			} else {
				// 엑셀 파일 정보 생성 start
				File xlsFile = new File(filesPath);
				File baseFile = new File(basePath);

				// 폴더가 없는 경우에 경로에 폴더를 생성
				if(!baseFile.exists()) {
					if(basePath.indexOf("../") >= 0) {
						return "";
					} else {
						if(!baseFile.mkdirs()) {
							return "";
						}
					}
				}
				
				FileOutputStream fileOut = null;

				try {
					fileOut = new FileOutputStream(xlsFile);
					xlsxWb.write(fileOut);
				} catch(FileNotFoundException e) {
					if(log.isErrorEnabled()) {
						//log.error(e.getMessage(), e);
						log.error("에러가 발생하였습니다.");
					}
				} finally {
					if(fileOut != null) {
						try {
							fileOut.close();
						} catch(IOException e) {
							if(log.isErrorEnabled()) {
								//log.error(e.getMessage(), e);
								log.error("에러가 발생하였습니다.");
							}
						}
					}
				}

				// 엑셀 파일 정보 생성 end
			}
		} catch(FileNotFoundException e) {
			if(log.isErrorEnabled()) {
				log.error("selectExcelList error");
			}
		} catch(IOException e) {
			if(log.isErrorEnabled()) {
				log.error("selectExcelList error");
			}
		} finally {
		}

		return fileName;

	}
	
	@SuppressWarnings({"resource", "rawtypes"})
	public static String selectExcelVoList(List<?> excelList, HashMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 해더 선언
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		
		// 실제 파일명 생성
		String excelNm = StringUtil.valueOf(map.get("excelNm"));
		String fileName = excelNm + System.currentTimeMillis() + ".xlsx";
		
		// 파일 경로 설정
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String uploadFilePath = StringUtil.valueOf(map.get("downloadFilePath"));
		uploadFilePath = uploadFilePath.substring(1, uploadFilePath.length());
		String basePath = realPath + uploadFilePath;
		String autoCellWidth = "";
		String excelLeft = "";
		String excelRight = "";
		String typeNo = "";
		
		if(!StringUtil.valueOf(map.get("autoCellWidth")).equals("")) {
			autoCellWidth = StringUtil.valueOf(map.get("autoCellWidth"));
		}
		
		if(!StringUtil.valueOf(map.get("excelLeft")).equals("")) {
			excelLeft = StringUtil.valueOf(map.get("excelLeft"));
		}
		
		if(!StringUtil.valueOf(map.get("excelRight")).equals("")) {
			excelRight = StringUtil.valueOf(map.get("excelRight"));
		}
		
		// poi 엑셀 입력 start
		SXSSFWorkbook xlsxWb = new SXSSFWorkbook();
		Sheet sheet1 = xlsxWb.createSheet("firstSheet");
		
		Row row = null;
		Cell cell = null;
		
		// 타이틀 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle Hstyle = xlsxWb.createCellStyle();
		Hstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		Hstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		Hstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		Hstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Hstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Hstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		// 내용 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle Cstyle = xlsxWb.createCellStyle();
		Cstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		Cstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		Cstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Cstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Cstyle.setWrapText(false);
		
		// 내용 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle CstyleLeft = xlsxWb.createCellStyle();
		CstyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
		CstyleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		CstyleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		CstyleLeft.setWrapText(false);
		
		// 내용 스타일 적용(다른 스타일이 필요한 경우 옵션을 이용하여 디자인)
		CellStyle CstyleRight = xlsxWb.createCellStyle();
		CstyleRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
		CstyleRight.setBottomBorderColor(HSSFColor.BLACK.index);
		CstyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		CstyleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		CstyleRight.setWrapText(false);
		
		// 첫번째 타이틀 라인
		row = sheet1.createRow(0);
		
		// 타이틀 정보를 배열로 생성
		String[] arrExcelTit = null;
		
		if(!StringUtil.valueOf(map.get("excelTit")).equals("")) {
			arrExcelTit = StringUtil.valueOf(map.get("excelTit")).split("\\|");
		}
		
		// 타이틀 정보를 배열로 생성
		String[] arrExcelIndex = null;
		
		if(!StringUtil.valueOf(map.get("excelIndex")).equals("")) {
			arrExcelIndex = StringUtil.valueOf(map.get("excelIndex")).split("\\|");
		}
		
		// 엑셀 사이즈를 수동으로 조절시
		String[] arrExcelWidth = null;
		
		if(!StringUtil.valueOf(map.get("excelWidth")).equals("")) {
			arrExcelWidth = StringUtil.valueOf(map.get("excelWidth")).split("\\|");
		}
		
		// 타이틀 정보 cell 입력
		if(arrExcelTit != null) {
			for(int j = 0; j < arrExcelTit.length; j++) {
				cell = row.createCell(j);
				cell.setCellValue(arrExcelTit[j]);	// 타이틀 내용
				cell.setCellStyle(Hstyle);	// 타이틀 스타일 적용
			}
		}
		
		// 내용 정보를 배열로 cell 입력
		if(excelList != null) {
			
			int rowNum = 1;
			
			for(int i = 0; i < excelList.size(); i++) {
				
				row = sheet1.createRow(rowNum++);
				
				int forNo = 1;
				if(arrExcelIndex != null) {
					for(int k = 0; k < arrExcelIndex.length; k++) {
						
						if(forNo < 10) {
							typeNo = "0" + Integer.toString(forNo);
						} else {
							typeNo = Integer.toString(forNo);
						}
						
						cell = row.createCell(k);
						
						if(excelLeft != null && !excelLeft.equals("")) {
							if(excelLeft.indexOf(typeNo) > -1) {
								cell.setCellStyle(CstyleLeft);	// 내용 스타일 적용(왼쪽 정렬)
							} else {
								cell.setCellStyle(Cstyle);	// 내용 스타일 적용(중앙 정렬)
							}
						} else if(excelRight != null && !excelRight.equals("")) {
							if(excelRight.indexOf(typeNo) > -1) {
								cell.setCellStyle(CstyleRight);	// 내용 스타일 적용(오른쪽 정렬)
							} else {
								cell.setCellStyle(Cstyle);	// 내용 스타일 적용(중앙 정렬)
							}
						} else {
							cell.setCellStyle(Cstyle);	// 내용 스타일 적용(중앙 정렬)
						}
						
						cell.setCellValue(getValue(excelList.get(i), arrExcelIndex[k]));	// 데이터베이스에서 가져온 순서대로 입력
						
						forNo++;
						
					}
				}
				
				if(i % 100 == 0) {
					((SXSSFSheet)sheet1).flushRows(100);
				}
			}
			
			if(autoCellWidth.equals("S")) {
				// cell 크기 자동 맞춤
				if(arrExcelWidth != null){
					for(int x = 0; x < arrExcelWidth.length; x++) {
						sheet1.autoSizeColumn(x);
						// 자동맞춤여부
						sheet1.setColumnWidth(x, Integer.parseInt(arrExcelWidth[x]) * 100);  // cell 크기 자동으로 생성시 너무 붙어서 간격 조정
					}
				}
			} else {
				// cell 크기 자동 맞춤
				if(arrExcelTit != null){
					for(int x = 0; x < arrExcelTit.length; x++) {
						sheet1.autoSizeColumn(x);
						// 자동맞춤여부
						if(!"N".equals(autoCellWidth)) {
							sheet1.setColumnWidth(x, ((sheet1.getColumnWidth(x)) * 2) + 516); // cell 크기 자동으로 생성시 너무 붙어서 간격 조정
						}
					}
				}
			}
		}
		// poi 엑셀 입력 end
		
		try {
			String filesPath = basePath + fileName;
			
			if(filesPath.indexOf("../") >= 0) {
				return "";
			} else {
				// 엑셀 파일 정보 생성 start
				File xlsFile = new File(filesPath);
				File baseFile = new File(basePath);

				// 폴더가 없는 경우에 경로에 폴더를 생성
				if(!baseFile.exists()) {
					if(basePath.indexOf("../") >= 0) {
						return "";
					} else {
						if(!baseFile.mkdirs()) {
							return "";
						}
					}
				}
				
				FileOutputStream fileOut = null;
				
				try {
					fileOut = new FileOutputStream(xlsFile);
					xlsxWb.write(fileOut);
				} catch(FileNotFoundException e) {
					if(log.isErrorEnabled()) {
						//log.error(e.getMessage(), e);
						log.error("에러가 발생하였습니다.");
					}
				} finally {
					if(fileOut != null) {
						try {
							fileOut.close();
						} catch(IOException e) {
							if(log.isErrorEnabled()) {
								//log.error(e.getMessage(), e);
								log.error("에러가 발생하였습니다.");
							}
						}
					}
				}
				
				// 엑셀 파일 정보 생성 end
			}
		} catch(FileNotFoundException e) {
			if(log.isErrorEnabled()) {
				log.error("selectExcelVoList error");
			}
		} catch(IOException e) {
			if(log.isErrorEnabled()) {
				log.error("selectExcelVoList error");
			}
		} finally {
		}
		
		return fileName;
		
	}

	private static String getValue(Object obj, String property) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
			Method[] methods = obj.getClass().getMethods();
			for(Method method : methods) {
				if(method.getName().equals(methodName)) {
					return StringUtil.valueOf(method.invoke(obj));
				}
			}
		} catch(SecurityException e) {
			if(log.isErrorEnabled()) {
				log.error("getValue error");
			}
		}
		return null;
	}
	
}
