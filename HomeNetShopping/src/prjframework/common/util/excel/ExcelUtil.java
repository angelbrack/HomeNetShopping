package prjframework.common.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import prjframework.common.beans.NestedBeanUtils;

/** 
 * <p>엑셀 파일을 분석하고 생성할수 있는 유틸리티 클래스</p>
 * 
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 12.</li>
 * </ul>
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ExcelUtil {
	
	/**
     * 엑셀 파일을 파싱하여 데이터 형태로 반환한다.
     * 
     * 
     * @param file
     *        파싱할 엑셀파일
     * @param clazz
     *        각 행에 매핑될 빈즈 클래스
     * @param mappings
     *        엑셀컬럼과 빈즈 프로퍼티 매핑 테이블
     * @param headerLines
     *        헤더로 인식할 라인수
     * @return
     * @throws IOException
     */
	public static List parse(File file, final Class clazz, final String[] mapping, int headerSkipLines) throws IOException {

        final ExcelUtil instance = new ExcelUtil();
        final List res = new ArrayList(100);
        instance.execute(file, headerSkipLines, new ExcelCallback() {

            //@Override
            public void rowAt(int rowNo, Object[] cellDatas) {

                try {
                    res.add(ExcelUtil.convertCellToObject(clazz, mapping, cellDatas));
                } catch (Exception ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
        });
        return res;
    }
	
	/**
     * 엑셀 파일을 파싱하여 데이터 형태로 반환한다.
     * 
     * 
     * @param file
     *        파싱할 엑셀파일
     * @param clazz
     *        각 행에 매핑될 빈즈 클래스
     * @param mappings
     *        엑셀컬럼과 빈즈 프로퍼티 매핑 테이블
     * @param headerLines
     *        헤더로 인식할 라인수
     * @param columnRowIndex
     *        컬럼으로 인식할 라인의 인덱스
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static ExcelData parse(File file, final Class clazz, final String[][] mappings, int headerLines, final int columnRowIndex)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        final ExcelUtil instance = new ExcelUtil();
        final List res = new ArrayList(100);
        ExcelHeader header = instance.execute(file, headerLines, columnRowIndex, new ExcelDataCallback() {

            //@Override
            public void rowAt(ExcelHeader header, int rowNo, Object[] cellDatas) throws InstantiationException, IllegalAccessException,
                    InvocationTargetException, NoSuchMethodException {

                res.add(instance.convertCellToObjectByColumn(header, columnRowIndex, clazz, mappings, cellDatas));
            }
        });

        return new ExcelData(header, res);
    }
    
    /**
     * 엑셀파일을 읽어 각 줄을 excelCallback 에게 callback 한다.
     * 
     * @param fileName
     * @param headerLinesToSkip
     * @param excelCallback
     * @throws IOException
     */
    public void execute(String fileName, int headerLinesToSkip, ExcelCallback excelCallback) throws IOException {

        execute(new File(fileName), headerLinesToSkip, excelCallback);
    }
    
    /**
     * 엑셀파일을 읽어 각 줄을 excelCallback 에게 callback 한다.
     * 
     * @param file
     * @param headerLinesToSkip
     * @param excelCallback
     * @throws IOException
     */
    public void execute(File file, int headerLinesToSkip, ExcelCallback excelCallback) throws IOException {

        execute(loadExcelFile(file).getSheetAt(0), headerLinesToSkip, excelCallback);
    }
    
    /**
     * 엑셀파일을 읽어 각 줄을 excelCallback 에게 callback 한다.
     * 
     * @param sheet
     * @param headerLinesToSkip
     * @param excelCallback
     * @throws IOException
     */
    void execute(Sheet sheet, int headerLinesToSkip, ExcelCallback excelCallback) throws IOException {

        int rowCount = sheet.getPhysicalNumberOfRows();
        int maxCellCount = countMaxCellCount(sheet, rowCount);

        for (int i = headerLinesToSkip; i <= rowCount + 100; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            Object[] values = getRowCellsData(row, maxCellCount);
            excelCallback.rowAt(i, values);
        }
    }
    
    /**
     * 엑셀을 Template Method 형태로 분석하며 Callback 받아 처리할수 있는 메소드
     * 
     * @param file
     *        파싱할 엑셀파일
     * @param headerLines
     *        헤더로 인식할 라인수
     * @param columnRowIndex
     *        헤더로 인식할 라인수
     * @param excelDataCallback
     *        각 행별로 콜백을 받을 콜백 구현
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public ExcelHeader execute(File file, int headerLines, int columnRowIndex, ExcelDataCallback excelDataCallback)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Sheet sheet = loadExcelFile(file).getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int maxCellCount = countMaxCellCount(sheet, rowCount);
        String[][] headers = new String[rowCount][maxCellCount];
        for (int i = 0; i < headerLines; i++) {
            Row row = sheet.getRow(i);
            Object[] values = getRowCellsData(row, maxCellCount);
            for (int k = 0; k < maxCellCount; k++) {
                if (values[k] != null) {
                    headers[i][k] = values[k].toString();
                }
            }
        }
        ExcelHeader header = new ExcelHeader(headers);
        // Column Name Row 는 넘기고 데이터 계산 시작
        for (int i = columnRowIndex + 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            Object[] values = getRowCellsData(row, maxCellCount);
            excelDataCallback.rowAt(header, i, values);
        }
        return header;

    }
    
    /**
     * 엑셀파일을 읽어 Workbook 객체를 생성한다.
     */
    Workbook loadExcelFile(String fileName) {

        return loadExcelFile(new File(fileName));
    }


    /**
     * 엑셀파일을 읽어 Workbook 객체를 생성한다.
     */
    Workbook loadExcelFile(File file) {

        try {
            Workbook workBook = WorkbookFactory.create(new FileInputStream(file));
            return workBook;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    private int countMaxCellCount(Sheet sheet, int rowCount) {

        int max = 0;
        for (int i = 0; i < rowCount; i++) {
            if (sheet.getRow(i) == null) {
                continue;
            }
            max = Math.max(max, sheet.getRow(i).getPhysicalNumberOfCells());
        }
        return max;
    }
    
    private Object[] getRowCellsData(Row row, int maxCellCount) {

        Object[] values = new Object[maxCellCount];

        for (int i = 0; i < maxCellCount; i++) {
            if (row == null) {
                continue;
            }
            Cell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }
            values[i] = decideValues(cell);
        }
        return values;
    }


    private Object decideValues(Cell cell) {

        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();

            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            default:
                // 다른 형식은 처리하지 않음.
        }
        return null;
    }
    
    protected Object convertCellToObjectByColumn(ExcelHeader header, int columnRowIndex, Class clazz, String[][] mappings,
            Object[] cellDatas) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Object bizz = clazz.newInstance();

        Method[] methods = bizz.getClass().getMethods();

        for (String[] mapping : mappings) {
            String columnName = mapping[0];
            String propertyName = mapping[1];
            int colIndex = header.getColumnIndex(columnRowIndex, columnName);
            if (colIndex == -1 || cellDatas[colIndex] == null) {
                continue;
            }
            Object cellData = cellDatas[colIndex];
            // PropertyUtils 은 정확히 같은 타입만을 set 해주기 때문에 각 메소드의 타입을 파악해야 함.
            if (cellData instanceof Double) {
                Double value = (Double) cellData;
                String setter = toSetter(propertyName);
                Method setterMethod = null;
                for (Method method : methods) {
                    if (method.getName().equals(setter)) {
                        setterMethod = method;
                    }
                }
                if (setterMethod == null) {
                    continue;
                }
                Class<?>[] parameterTypes = setterMethod.getParameterTypes();
                Class<?> paramClass = parameterTypes[0];

                if (paramClass.equals(String.class)) {
                    if (value.toString().endsWith(".0")) {
                        int valueTmp = value.intValue();
                        // PropertyUtils.setProperty(bizz, propertyName,
                        // Integer.toString(valueTmp));
                        NestedBeanUtils.setProperty(bizz, propertyName, Integer.toString(valueTmp));
                    } else {
                        // PropertyUtils.setProperty(bizz, propertyName,
                        // value.toString());
                        NestedBeanUtils.setProperty(bizz, propertyName, value.toString());
                    }
                } else if (paramClass.equals(Long.class)) {
                    // PropertyUtils.setProperty(bizz, propertyName,
                    // value.longValue());
                    NestedBeanUtils.setProperty(bizz, propertyName, value.longValue());
                } else if (paramClass.equals(Double.class)) {
                    // PropertyUtils.setProperty(bizz, propertyName,
                    // value.doubleValue());
                    NestedBeanUtils.setProperty(bizz, propertyName, value.doubleValue());
                } else if (paramClass.equals(Integer.class)) {
                    // PropertyUtils.setProperty(bizz, propertyName,
                    // value.intValue());
                    NestedBeanUtils.setProperty(bizz, propertyName, value.intValue());
                }

                else if (paramClass.equals(Float.class)) {
                    // PropertyUtils.setProperty(bizz, propertyName,
                    // value.floatValue());
                    NestedBeanUtils.setProperty(bizz, propertyName, value.floatValue());
                }
            } else if (cellDatas[colIndex] instanceof Float) {
                // PropertyUtils.setProperty(bizz, mapping[i], ((Float)
                // cellDatas[i]).longValue());
                NestedBeanUtils.setProperty(bizz, propertyName, ((Float) cellData).longValue());
            } else {
                // PropertyUtils.setProperty(bizz, mapping[i], cellDatas[i]);
                NestedBeanUtils.setProperty(bizz, propertyName, cellData);
            }
        }
        return bizz;
    }
    
    private static String toSetter(String temp) {

        return "set" + temp.substring(0, 1).toUpperCase() + temp.substring(1);
    }
	
	/*
     * 데이터 목록을 엑셀파일로 생성한다.
     * 
     * @param dataList 엑셀의 각 행이 될 데이터 목록
     * 
     * @param mapping 엑셀 컬럼과 빈즈 프로퍼티 매핑 테이블
     * 
     * @param title 엑셀 첫재줄에 들어갈 제목
     * 
     * @param targetFile 엑셀 파일이 저장될 위치
     */
    public static void toExcelFile(List<?> dataList, String[][] mapping, String title, File targetFile) {

        new SingleSheetExcelFactory().toExcelFile(dataList, mapping, title, targetFile);
    }
    
    protected static Object convertCellToObject(Class clazz, String[] mapping, Object[] cellDatas) throws ClassNotFoundException,
    		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		Object bizz = clazz.newInstance();
		
		Method[] methods = bizz.getClass().getMethods();
		
		/*
		 * 실제 Mapping이 Excel CellData의 크기보다 더 큰경우에 대한 예외처리 mapping을 여유있게 넉넉히 잡은 경우, Excel Cell Data에 해당정보가 들어오지 않아도, 해당 값을 입력만 안 할 뿐 오류가 나지
		 * 않도록 조치
		 * 
		 */
		
		int mappingLength = mapping.length;
		if (cellDatas.length < mappingLength) {
		    mappingLength = cellDatas.length;
		}
		// for (int i = 0; i < mapping.length; i++) {
		for (int i = 0; i < mappingLength; i++) {
		    if (cellDatas[i] == null) {
		        continue;
		    }
		    String[] options = null;
		    String propertyName = mapping[i];
		    if (propertyName.contains(",")) {
		        options = propertyName.substring(propertyName.indexOf(",") + 1).trim().split(",");
		        propertyName = propertyName.substring(0, propertyName.indexOf(",")).trim();
		
		    }
		    Object rawData = cellDatas[i];
		    Class paramClass = parseParameterClass(methods, options, propertyName);
		    if (paramClass == null) {
		        continue;
		    }
		
		
		    // PropertyUtils 은 정확히 같은 타입만을 set 해주기 때문에 각 메소드의 타입을 파악해야 함.
		    if (rawData instanceof Double) {
		        Double value = (Double) cellDatas[i];
		
		        if (paramClass.equals(Long.class)) {
		            NestedBeanUtils.setProperty(bizz, propertyName, value.longValue());
		        } else if (paramClass.equals(Double.class)) {
		            NestedBeanUtils.setProperty(bizz, propertyName, value.doubleValue());
		        } else if (paramClass.equals(Integer.class)) {
		            NestedBeanUtils.setProperty(bizz, propertyName, value.intValue());
		        } else if (paramClass.equals(Float.class)) {
		            NestedBeanUtils.setProperty(bizz, propertyName, value.floatValue());
		        } else if (paramClass.equals(String.class)) {
		            // if (value.toString().endsWith(".0")) {
		            // int valueTmp = value.intValue();
		            // NestedBeanUtils.setProperty(bizz, propertyName, Integer.toString(valueTmp));
		            // } else {
		            // NestedBeanUtils.setProperty(bizz, propertyName, value.toString());
		            // }
		
		            String strValue = String.valueOf(((Double) cellDatas[i]).longValue());
		            NestedBeanUtils.setProperty(bizz, propertyName, strValue);
		
		
		        } else if (paramClass.equals(Date.class)) {
		            Date dateValue = DateUtil.getJavaDate(value);
		            NestedBeanUtils.setProperty(bizz, propertyName, dateValue);
		        }
		    } else if (rawData instanceof Float) {
		        NestedBeanUtils.setProperty(bizz, propertyName, ((Float) rawData).longValue());
		    } else {
		        // 현재까지 Option을 지원하는 것은 Date 뿐임.
		        if (options != null && paramClass.equals(Date.class)) {
		            SimpleDateFormat formatter = new SimpleDateFormat(options[2]);
		            try {
		                Date date = formatter.parse(rawData.toString());
		                NestedBeanUtils.setProperty(bizz, propertyName, date);
		            } catch (ParseException e) {
		                // e.printStackTrace();
		            }
		        } else {
		            NestedBeanUtils.setProperty(bizz, propertyName, rawData);
		        }
		    }
		}
		return bizz;
	}
    
    private static Class<?> parseParameterClass(Method[] methods, String[] options, String propertyName) {

        Class<?> paramClass = null;
        if (options == null) {

            String setter = toSetter(propertyName);
            Method setterMethod = null;
            for (Method method : methods) {
                if (method.getName().equals(setter)) {
                    setterMethod = method;
                }
            }
            if (setterMethod == null) {
                return null;
            }

            Class<?>[] parameterTypes = setterMethod.getParameterTypes();
            paramClass = parameterTypes[0];
        } else {
            class SupportType {

                String type;
                Class clazz;


                SupportType(String type, Class clazz) {

                    this.type = type;
                    this.clazz = clazz;
                }
            }

            SupportType[] supportTypes = new SupportType[] {//
                    new SupportType("string", String.class),//
                            new SupportType("long", Long.class),//
                            new SupportType("double", Double.class),//
                            new SupportType("float", float.class),//
                            new SupportType("date", Date.class),//
                            new SupportType("date", Date.class),//
                            new SupportType("date", Date.class),//

                    };
            for (SupportType type : supportTypes) {
                if (type.type.equals(options[0])) {
                    paramClass = type.clazz;
                }
            }
        }
        return paramClass;
    }
    
    /*
     * 데이터 목록을 엑셀파일로 생성한다.
     * 
     * @param dataList 엑셀의 각 행이 될 데이터 목록
     * 
     * @param mapping 엑셀 컬럼과 빈즈 프로퍼티 매핑 테이블
     * 
     * @param title 엑셀 첫재줄에 들어갈 제목
     * 
     * @param targetFile 엑셀 파일이 저장될 위치
     */
    public static void toExcelFile(List<?> dataList, String[][] mapping, String title, Integer[] freezePane, File targetFile) {

        new SingleSheetExcelFactory().toExcelFile(dataList, mapping, title, freezePane, targetFile);
    }
    
    /*
     * 데이터 목록을 엑셀파일로 생성한다.
     * 
     * @param dataList 엑셀의 각 행이 될 데이터 목록
     * @param mapping 엑셀 컬럼과 빈즈 프로퍼티 매핑 테이블
     * @param title 엑셀 첫재줄에 들어갈 제목 
     * @param targetFile 엑셀 파일이 저장될 위치
     */
    public static void toExcelFile(List<?> dataList, String[] mapping, List<?> dataList2, String[] mapping2, String title, File targetFile) {

        new SingleSheetExcelFactory().toGoodAttrExcelFile(dataList, mapping, dataList2,
                mapping2, title, targetFile);
    }
    
    /*
     * 데이터 목록을 엑셀파일로 생성한다.
     * 
     * @param dataList 엑셀의 각 행이 될 데이터 목록
     * @param mapping 엑셀 컬럼과 빈즈 프로퍼티 매핑 테이블
     * @param title 엑셀 첫재줄에 들어갈 제목
     * @param targetFile 엑셀 파일이 저장될 위치
     */
    public static void toExcelFile(List<?> dataList, String[] mapping, List<?> dataList2, String[] mapping2, List<?> dataList3,
            String[] mapping3, List<?> dataList4, String[] mapping4, List<?> dataList5, String[] mapping5, List<?> dataList6,
            String[] mapping6, List<?> dataList7, String[] mapping7, List<?> dataList8, String[] mapping8, List<?> dataList9,
            String[] mapping9, List<?> dataList10, String[] mapping10, List<?> dataList11, String[] mapping11, List<?> dataList12,
            String[] mapping12, List<?> dataList13, String[] mapping13, List<?> dataList14, String[] mapping14, List<?> dataList15,
            String[] mapping15, List<?> dataList16, String[] mapping16, List<?> dataList17, String[] mapping17, List<?> dataList18,
            String[] mapping18, String title, File targetFile) {



        new SingleSheetExcelFactory().toGoodMasterExcelFile(dataList, mapping, dataList2,
                mapping2, dataList3, mapping3, dataList4, mapping4, dataList5, mapping5, dataList6, mapping6, dataList7, mapping7,
                dataList8, mapping8, dataList9, mapping9, dataList10, mapping10, dataList11, mapping11, dataList12, mapping12, dataList13,
                mapping13, dataList14, mapping14, dataList15, mapping15, dataList16, mapping16, dataList17, mapping17, dataList18,
                mapping18, title, targetFile);
    }
    
    /**
     * 데이터 목록을 엑셀파일로 생성한다.
     * 
     * @param dataList 엑셀의 각 행이 될 데이터 목록
     * @param mapping 엑셀 컬럼과 빈즈 프로퍼티 매핑 테이블
     * @param title 엑셀 첫재줄에 들어갈 제목
     * @param targetFile 엑셀 파일이 저장될 위치
     */
    public static void toMultipleSheetsExcelFile(List<?> dataList, String[][] mapping, String title, File targetFile) {

        new MultiSheetExcelFactory().toMultipleSheetsExcelFile(dataList, mapping, title,
                targetFile);
    }

}
