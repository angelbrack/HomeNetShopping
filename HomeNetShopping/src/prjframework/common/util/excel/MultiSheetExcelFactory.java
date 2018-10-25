package prjframework.common.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import homenet.shop.sample.web.SampleController;

/** 
 * <p>참고: http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/ xssf/usermodel/examples/BigGridDemo.java</p>
 * <p>OOM 방지를 위해 sheet단위로 생성하여 외부에서 사용할 수 있도록 public 메소드 일부 추가</p>
 * 
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 12.</li>
 * </ul>
 */
@Slf4j
public class MultiSheetExcelFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(MultiSheetExcelFactory.class);
	
	public MultiSheetExcelFactory() {
	}

	/**
	 * 데이터 목록을 엑셀파일로 생성한다. 2012-07-20 / 김덕선 수정 / POI Library도 Worksheet에 제한 rows가 65535이므로, 이를 처리하는 루틴을 추가 처리방안 : Sheet 에 번호를 부여하여 추가로
	 * WorkSheet를 추가하는 기능으로 전환
	 * 
	 * @param dataList
	 *            엑셀의 각 행이 될 데이터 목록
	 * @param mapping
	 *            엑셀 컬럼과 빈즈 프로퍼티 매핑 테이블
	 * @param title
	 *            엑셀 첫재줄에 들어갈 제목
	 * @param targetFile
	 *            엑셀 파일이 저장될 위치
	 */

	public void toMultipleSheetsExcelFile(List<?> dataList, String[][] mapping, String title, File targetFile) {
		toMultipleSheetsExcelFile(new ListDataHolder(dataList), mapping, title, targetFile);
	}

	/**
	 * 많은 데이터가 있을때 이를 한번에 하나씩 불러올수 있는 DataHolder 콜백을 사용한다.
	 * 
	 * @param dataHolder
	 * @param mapping
	 * @param title
	 * @param targetFile
	 */
	public void toMultipleSheetsExcelFile(DataHolder dataHolder, String[][] mapping, String title, File targetFile) {
		List<Sheet> sheets = createSheets(dataHolder, mapping);
		createExcel(targetFile, sheets);

	}

	/**
	 * List<Sheet>를 넘겨 받아 엑셀파일로 생성한다
	 *
	 * @param sheets
	 * @param targetFile
	 */
	public void toMultipleSheetsExcelFile(List<Sheet> sheets, File targetFile) {
		createExcel(targetFile, sheets);
	}

	/**
	 * 단일 시트를 생성하여 리턴
	 * 
	 * @param dataHolder
	 * @param mapping
	 * @return
	 */
	public Sheet createSheet(List<?> dataList, String[][] mapping) {
		Sheet sheet = new Sheet();
		DataHolder dataHolder = new ListDataHolder(dataList);

		makeColumnNames(mapping, sheet, 0);

		while (dataHolder.hasNext()) {
			Object data = dataHolder.next();
			createRow(mapping, sheet, data);
		}
		sheet.endSheet();

		return sheet;
	}

	private void createExcel(File targetFile, List<Sheet> sheets) {
		XSSFWorkbook wb = new XSSFWorkbook();
		for (int i = 0; i < sheets.size(); i++) {
			XSSFSheet sheet = wb.createSheet("Sheet" + (i + 1));
			String sheetRef = sheet.getPackagePart().getPartName().getName();
			sheets.get(i).setSheetRef(sheetRef);
		}
		try {
			// save the template
			File templateFile = File.createTempFile("template", ".xlsx");
			FileOutputStream os = new FileOutputStream(templateFile);
			wb.write(os);
			os.close();

			// Step 3. Substitute the template entry with the generated data
			FileOutputStream out = new FileOutputStream(targetFile);
			substitute(templateFile, sheets, out);
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IOUtils.closeQuietly(wb);

	}

	private List<Sheet> createSheets(DataHolder dataHolder, String[][] mapping) {
		Sheet sheet = null;
		List<Sheet> sheets = new ArrayList<Sheet>();
		while (dataHolder.hasNext()) {
			if (isNeedNewSheet(sheet)) {
				if (sheet != null) {
					sheet.endSheet();
				}
				sheet = new Sheet();
				sheets.add(sheet);
				makeColumnNames(mapping, sheet, 0);
			}
			Object data = dataHolder.next();
			createRow(mapping, sheet, data);
		}
		sheet.endSheet();
		return sheets;
	}

	private void createRow(String[][] mapping, Sheet sheet, Object data) {
		filterPrivacyInfo(data);
		sheet.insertRow();
		for (int colIndex = 0; colIndex < mapping.length; colIndex++) {
			String propertyName = mapping[colIndex][0];
			String[] options = null;
			if (propertyName.contains(",")) {
				options = propertyName.substring(propertyName.indexOf(",") + 1).trim().split(",");
				propertyName = propertyName.substring(0, propertyName.indexOf(",")).trim();
			}

			Object value = null;
			if (isNestedProperty(propertyName)) {
				try {
					value = PropertyUtils.getNestedProperty(data, propertyName);
				} catch (NestedNullException ex) {
					// 프로퍼티가 Null 일때는 null 로 남겨둠.
				} catch (IllegalAccessException e) {
					throw new IllegalArgumentException(e);
				} catch (InvocationTargetException e) {
					throw new IllegalArgumentException(e);
				} catch (NoSuchMethodException e) {
					throw new IllegalArgumentException(e);
				}
			} else {
				// CombinedCall에도 적응할 수 있도록 SimpleProperty가 아닌 일반 Property를
				// 사용하자.
				try {
					value = PropertyUtils.getProperty(data, propertyName);
				} catch (IllegalAccessException e) {
					throw new IllegalArgumentException(e);
				} catch (InvocationTargetException e) {
					throw new IllegalArgumentException(e);
				} catch (NoSuchMethodException e) {
					throw new IllegalArgumentException(e);
				}
			}

			if (value == null) {
				// null일때는 해당 셀을 만들지 않는다.
			} else if (value instanceof Number) {
				double dValue = ((Number) value).doubleValue();
				sheet.createCell(colIndex, dValue);

			} else if (value instanceof Date) {
				/*
				 * Ceres-2121 : BO> 단품관리 메뉴의 전체 다운로드 기능 개선(엑셀파일 시트분리) 스타일이 너무 많으면 "셀 서식이 너무 많습니다" 에러 발생 (5만건 이상) 따라서 이를 대체하기 위하여 문자형태로 그냥 출력
				 * 데이트 타입을 따로 뺐으면 빼서 처리할 수 있도록 처리하는 부분이 필요 (그래서 else에서 처리하도록 기능수정)
				 */
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = sdf.format((Date) value);
				sheet.createCell(colIndex, dateString);

			} else {
				sheet.createCell(colIndex, value.toString());
			}
		}
		sheet.endRow();
	}

	private boolean isNestedProperty(String propertyName) {
		return propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*");
	}

	private boolean isNeedNewSheet(Sheet sheet) {
		return sheet == null || sheet.isFull();
	}

	private void makeColumnNames(String[][] mapping, Sheet sheet, int rowIndex) {
		sheet.insertRow(rowIndex);
		for (int i = 0; i < mapping.length; i++) {
			sheet.createCell(i, mapping[i][1]);
		}
		sheet.endRow();
	}

	public void substitute(List<Sheet> sheets, OutputStream out) throws IOException {
		substitute(File.createTempFile("template", ".xlsx"), sheets, out);
	}

	/**
	 * @param zipfile
	 *            the template file
	 * @param tmpfile
	 *            the XML file with the sheet data
	 * @param entry
	 *            the name of the sheet entry to substitute, e.g. xl/worksheets/sheet1.xml
	 * @param out
	 *            the stream to write the result to
	 */
	private void substitute(File templateFile, List<Sheet> sheets, OutputStream out) throws IOException {
		ZipFile zip = new ZipFile(templateFile);

		ZipOutputStream zos = new ZipOutputStream(out);

		@SuppressWarnings("unchecked")
		Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
		while (en.hasMoreElements()) {
			ZipEntry ze = en.nextElement();
			if (isSheetEntry(ze.getName(), sheets) == false) {
				zos.putNextEntry(new ZipEntry(ze.getName()));
				InputStream is = zip.getInputStream(ze);
				copyStream(is, zos);
				is.close();
			}
		}

		for (Sheet sheet : sheets) {
			zos.putNextEntry(new ZipEntry(sheet.sheetRef));
			InputStream is = new FileInputStream(sheet.tmpFile);
			copyStream(is, zos);
			is.close();
		}

		zos.close();
		IOUtils.closeQuietly(zip); 
	}

	private boolean isSheetEntry(String zipEntryName, List<Sheet> sheets) {
		for (Sheet sheet : sheets) {
			if (zipEntryName.equals(sheet.sheetRef)) {
				return true;
			}
		}
		return false;
	}

	//
	private void copyStream(InputStream in, OutputStream out) throws IOException {
		byte[] chunk = new byte[1024];
		int count;
		while ((count = in.read(chunk)) >= 0) {
			out.write(chunk, 0, count);
		}
	}

	private void filterPrivacyInfo(Object data) {
		/*if (privacyInfoFilter == null || privacyInfoPermissionHolder == null) {
			return;
		}

		privacyInfoFilter.filter(data, privacyInfoPermissionHolder.getPermissions());*/
	}

}
