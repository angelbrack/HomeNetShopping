package prjframework.common.util.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;

public class SingleSheetExcelFactory {

	public SingleSheetExcelFactory() {
    }

	public void toExcelFile(List<?> dataList, String[][] mapping, String title, File targetFile) {
		toExcelFile(dataList, mapping, title, null, targetFile);
	}

    public void toExcelFile(List<?> dataList, String[][] mapping, String title, Integer[] freezePane, File targetFile) {

        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Sheet1");
            
            if ( freezePane != null ) {
            	int colSplit 		= 0;
            	int rowSplit		= 0;
            	int leftmostColumn	= 0;
            	int topRow			= 0;
            	
            	for (int i=0; i<freezePane.length; i++) {
            		if ( i == 0 ) colSplit 			= freezePane[i];
            		if ( i == 1 ) rowSplit 			= freezePane[i];
            		if ( i == 2 ) leftmostColumn 	= freezePane[i];
            		if ( i == 3 ) topRow 			= freezePane[i];
            	}
            	
            	
            
	            //sheet.createFreezePane(0, 1, 0, 5);
	            //sheet.createFreezePane(1, 0, 5, 0);
	            //sheet.createFreezePane(1, 1, 2, 2);
	            //sheet.createFreezePane(1, 0, 1, 0);
            	
            	sheet.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
            }
            
            CreationHelper createHelper = wb.getCreationHelper();
            makeTitleHeader(title, sheet);
            makeColumnNames(mapping, sheet);
            Object[] last = new Object[mapping.length];
            for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
                HSSFRow row = sheet.createRow(rowIndex + 2); // title, column 제외
                Object data = dataList.get(rowIndex);
                filterPrivacyInfo(data);
                for (int colIndex = 0; colIndex < mapping.length; colIndex++) {
                    String propertyName = mapping[colIndex][0];
                    String[] options = null;
                    if (propertyName.contains(",")) {
                        options = propertyName.substring(propertyName.indexOf(",") + 1).trim().split(",");
                        propertyName = propertyName.substring(0, propertyName.indexOf(",")).trim();
                    }

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }
                    if (value instanceof Date) {
                        value = DateUtil.getExcelDate((Date) value);
                        double dValue = ((Number) value).doubleValue();
                        cell.setCellValue(dValue);
                        String dateFormat = options != null && options.length >= 1 ? options[0] : "yyyy-MM-dd";
                        CellStyle cellStyle = wb.createCellStyle();
                        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFormat));
                        cell.setCellStyle(cellStyle);

                    }

                    last[colIndex] = value;
                    if (value == null) {
                        last[colIndex] = null;
                        continue;
                    }

                    if (value instanceof Number) {
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        double dValue = ((Number) value).doubleValue();
                        cell.setCellValue(dValue);
                    }

                    else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(value.toString());
                    }

                }
            }

            FileOutputStream fileout = new FileOutputStream(targetFile);
            wb.write(fileout);
            fileout.close();
            IOUtils.closeQuietly(wb);
        } catch (Exception ex) {
            // ex.printStackTrace();
            throw new IllegalArgumentException(ex);
        }
    }


    public void toGoodAttrExcelFile(List<?> dataList, String[] mapping, List<?> dataList2, String[] mapping2, String title, File targetFile) {

        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("상품부가속성");

            HSSFCellStyle cellStyle0 = wb.createCellStyle();
            cellStyle0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle0.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle0.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle0.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle0.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle0.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);

            sheet.setColumnWidth(0, 5600);
            sheet.setColumnWidth(1, 5600);
            sheet.setColumnWidth(2, 5600);
            sheet.setColumnWidth(3, 5600);
            sheet.setColumnWidth(4, 5600);
            sheet.setColumnWidth(5, 5600);
            sheet.setColumnWidth(6, 5600);
            sheet.setColumnWidth(7, 5600);
            sheet.setColumnWidth(8, 5600);
            sheet.setColumnWidth(9, 5600);
            sheet.setColumnWidth(10, 5600);
            sheet.setColumnWidth(11, 5600);
            sheet.setColumnWidth(12, 5600);
            sheet.setColumnWidth(13, 5600);
            sheet.setColumnWidth(14, 5600);
            sheet.setColumnWidth(15, 5600);
            sheet.setColumnWidth(16, 5600);
            sheet.setColumnWidth(17, 5600);
            sheet.setColumnWidth(18, 5600);
            sheet.setColumnWidth(19, 5600);
            sheet.setColumnWidth(20, 5600);


            Object[] last = new Object[mapping.length];


            int endCol = 0;
            Object tmpColsCheck = dataList.get(1);
            filterPrivacyInfo(tmpColsCheck);
            for (int colIndex = 0; colIndex < mapping.length; colIndex++) {
                String check_propertyName = mapping[colIndex];
                Object check_value = null;

                if (check_propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                    try {
                        check_value = PropertyUtils.getNestedProperty(tmpColsCheck, check_propertyName);
                    } catch (NestedNullException ex) {
                        // 프로퍼티가 Null 일때는 null 로 남겨둠.
                    }
                } else {
                    check_value = PropertyUtils.getSimpleProperty(tmpColsCheck, check_propertyName);
                }

                if (check_value != null) {
                    endCol = colIndex + 1;
                }

            }

            for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
                HSSFRow row = sheet.createRow(rowIndex);
                Object data = dataList.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < endCol; colIndex++) {
                    String propertyName = mapping[colIndex];

                    Object value = null;

                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());



                }
            }

            HSSFSheet sheet2 = wb.createSheet("속성코드List");

            Object[] last2 = new Object[mapping2.length];

            for (int rowIndex = 0; rowIndex < dataList2.size(); rowIndex++) {
                HSSFRow row = sheet2.createRow(rowIndex);
                Object data = dataList2.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping2.length; colIndex++) {
                    String propertyName = mapping2[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last2[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }

            FileOutputStream fileout = new FileOutputStream(targetFile);
            wb.write(fileout);
            fileout.close();
            IOUtils.closeQuietly(wb);
        } catch (Exception ex) {
            // ex.printStackTrace();
            throw new IllegalArgumentException(ex);
        }
    }


    public void toGoodMasterExcelFile(List<?> dataList, String[] mapping, List<?> dataList2, String[] mapping2, List<?> dataList3,
            String[] mapping3, List<?> dataList4, String[] mapping4, List<?> dataList5, String[] mapping5, List<?> dataList6,
            String[] mapping6, List<?> dataList7, String[] mapping7, List<?> dataList8, String[] mapping8, List<?> dataList9,
            String[] mapping9, List<?> dataList10, String[] mapping10, List<?> dataList11, String[] mapping11, List<?> dataList12,
            String[] mapping12, List<?> dataList13, String[] mapping13, List<?> dataList14, String[] mapping14, List<?> dataList15,
            String[] mapping15, List<?> dataList16, String[] mapping16, List<?> dataList17, String[] mapping17, List<?> dataList18,
            String[] mapping18, String title, File targetFile) {

        try {

            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFCellStyle cellStyle0 = wb.createCellStyle();
            cellStyle0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle0.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle0.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle0.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle0.setBorderBottom(HSSFCellStyle.BORDER_THIN);


            HSSFCellStyle cellStyle1 = wb.createCellStyle();
            cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            /***********************************
             * sheet 1 : 03-가방
             ***********************************/

            HSSFSheet sheet = wb.createSheet("03-가방");

            sheet.setColumnWidth(0, 5600);
            sheet.setColumnWidth(1, 5600);
            sheet.setColumnWidth(2, 5600);
            sheet.setColumnWidth(3, 5600);
            sheet.setColumnWidth(4, 5600);
            sheet.setColumnWidth(5, 5600);
            sheet.setColumnWidth(6, 5600);
            sheet.setColumnWidth(7, 5600);
            sheet.setColumnWidth(8, 9600);
            sheet.setColumnWidth(9, 5600);
            sheet.setColumnWidth(10, 5600);
            sheet.setColumnWidth(11, 5600);
            sheet.setColumnWidth(12, 5600);
            sheet.setColumnWidth(13, 5600);
            sheet.setColumnWidth(14, 5600);
            sheet.setColumnWidth(15, 5600);
            sheet.setColumnWidth(16, 5600);
            sheet.setColumnWidth(17, 5600);
            sheet.setColumnWidth(18, 5600);
            sheet.setColumnWidth(19, 5600);
            sheet.setColumnWidth(20, 5600);
            sheet.setColumnWidth(21, 5600);
            sheet.setColumnWidth(22, 5600);
            sheet.setColumnWidth(23, 5600);
            sheet.setColumnWidth(24, 5600);
            sheet.setColumnWidth(25, 5600);
            sheet.setColumnWidth(26, 5600);
            sheet.setColumnWidth(27, 5600);
            sheet.setColumnWidth(28, 5600);
            sheet.setColumnWidth(29, 5600);
            sheet.setColumnWidth(30, 5600);
            sheet.setColumnWidth(31, 5600);
            sheet.setColumnWidth(32, 5600);
            sheet.setColumnWidth(33, 5600);
            sheet.setColumnWidth(34, 5600);
            sheet.setColumnWidth(35, 5600);
            sheet.setColumnWidth(36, 5600);
            sheet.setColumnWidth(37, 5600);
            sheet.setColumnWidth(38, 5600);
            sheet.setColumnWidth(39, 5600);
            sheet.setColumnWidth(40, 5600);
            sheet.setColumnWidth(41, 5600);
            sheet.setColumnWidth(42, 5600);
            sheet.setColumnWidth(43, 5600);
            sheet.setColumnWidth(44, 5600);
            sheet.setColumnWidth(45, 5600);
            sheet.setColumnWidth(46, 5600);
            sheet.setColumnWidth(47, 5600);
            sheet.setColumnWidth(48, 5600);
            sheet.setColumnWidth(48, 5600);
            sheet.setColumnWidth(50, 5600);
            sheet.setColumnWidth(51, 5600);
            sheet.setColumnWidth(52, 5600);
            sheet.setColumnWidth(53, 5600);
            sheet.setColumnWidth(54, 5600);
            sheet.setColumnWidth(55, 5600);
            sheet.setColumnWidth(56, 5600);
            sheet.setColumnWidth(57, 5600);
            sheet.setColumnWidth(58, 5600);
            sheet.setColumnWidth(59, 5600);
            sheet.setColumnWidth(60, 5600);
            sheet.setColumnWidth(61, 5600);
            sheet.setColumnWidth(62, 5600);
            sheet.setColumnWidth(63, 5600);
            sheet.setColumnWidth(64, 5600);
            sheet.setColumnWidth(65, 5600);
            sheet.setColumnWidth(66, 5600);
            sheet.setColumnWidth(67, 5600);
            sheet.setColumnWidth(68, 5600);
            sheet.setColumnWidth(69, 5600);
            sheet.setColumnWidth(70, 5600);
            sheet.setColumnWidth(71, 5600);
            sheet.setColumnWidth(72, 5600);
            sheet.setColumnWidth(73, 5600);
            sheet.setColumnWidth(74, 5600);
            sheet.setColumnWidth(75, 5600);
            sheet.setColumnWidth(76, 5600);
            sheet.setColumnWidth(77, 5600);
            sheet.setColumnWidth(78, 5600);
            sheet.setColumnWidth(79, 5600);
            sheet.setColumnWidth(80, 5600);
            sheet.setColumnWidth(81, 5600);
            sheet.setColumnWidth(82, 5600);
            sheet.setColumnWidth(83, 5600);
            sheet.setColumnWidth(84, 5600);
            sheet.setColumnWidth(85, 5600);
            sheet.setColumnWidth(86, 5600);
            sheet.setColumnWidth(87, 5600);

            Object[] last = new Object[mapping.length];

            for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
                HSSFRow row = sheet.createRow(rowIndex);
                Object data = dataList.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping.length; colIndex++) {
                    String propertyName = mapping[colIndex];



                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);

                    if (colIndex == 8) {
                        cell.setCellStyle(cellStyle1);
                    } else {
                        cell.setCellStyle(cellStyle0);
                    }


                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            value = "";
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }



                    if (value == null) {
                        value = "";
                        last[colIndex] = null;
                        // continue;
                    }



                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }

            /***********************************
             * sheet 2 : 07-영상가전(TV류 모니터)
             ***********************************/

            HSSFSheet sheet2 = wb.createSheet("07-영상가전(TV류 모니터)");

            sheet2.setColumnWidth(0, 5600);
            sheet2.setColumnWidth(1, 5600);
            sheet2.setColumnWidth(2, 5600);
            sheet2.setColumnWidth(3, 5600);
            sheet2.setColumnWidth(4, 5600);
            sheet2.setColumnWidth(5, 5600);
            sheet2.setColumnWidth(6, 5600);
            sheet2.setColumnWidth(7, 5600);
            sheet2.setColumnWidth(8, 9600);
            sheet2.setColumnWidth(9, 5600);
            sheet2.setColumnWidth(10, 5600);
            sheet2.setColumnWidth(11, 5600);
            sheet2.setColumnWidth(12, 5600);
            sheet2.setColumnWidth(13, 5600);
            sheet2.setColumnWidth(14, 5600);
            sheet2.setColumnWidth(15, 5600);
            sheet2.setColumnWidth(16, 5600);
            sheet2.setColumnWidth(17, 5600);
            sheet2.setColumnWidth(18, 5600);
            sheet2.setColumnWidth(19, 5600);
            sheet2.setColumnWidth(20, 5600);
            sheet2.setColumnWidth(21, 5600);
            sheet2.setColumnWidth(22, 5600);
            sheet2.setColumnWidth(23, 5600);
            sheet2.setColumnWidth(24, 5600);
            sheet2.setColumnWidth(25, 5600);
            sheet2.setColumnWidth(26, 5600);
            sheet2.setColumnWidth(27, 5600);
            sheet2.setColumnWidth(28, 5600);
            sheet2.setColumnWidth(29, 5600);
            sheet2.setColumnWidth(30, 5600);
            sheet2.setColumnWidth(31, 5600);
            sheet2.setColumnWidth(32, 5600);
            sheet2.setColumnWidth(33, 5600);
            sheet2.setColumnWidth(34, 5600);
            sheet2.setColumnWidth(35, 5600);
            sheet2.setColumnWidth(36, 5600);
            sheet2.setColumnWidth(37, 5600);
            sheet2.setColumnWidth(38, 5600);
            sheet2.setColumnWidth(39, 5600);
            sheet2.setColumnWidth(40, 5600);
            sheet2.setColumnWidth(41, 5600);
            sheet2.setColumnWidth(42, 5600);
            sheet2.setColumnWidth(43, 5600);
            sheet2.setColumnWidth(44, 5600);
            sheet2.setColumnWidth(45, 5600);
            sheet2.setColumnWidth(46, 5600);
            sheet2.setColumnWidth(47, 5600);
            sheet2.setColumnWidth(48, 5600);
            sheet2.setColumnWidth(48, 5600);
            sheet2.setColumnWidth(50, 5600);
            sheet2.setColumnWidth(51, 5600);
            sheet2.setColumnWidth(52, 5600);
            sheet2.setColumnWidth(53, 5600);
            sheet2.setColumnWidth(54, 5600);
            sheet2.setColumnWidth(55, 5600);
            sheet2.setColumnWidth(56, 5600);
            sheet2.setColumnWidth(57, 5600);
            sheet2.setColumnWidth(58, 5600);
            sheet2.setColumnWidth(59, 5600);
            sheet2.setColumnWidth(60, 5600);
            sheet2.setColumnWidth(61, 5600);
            sheet2.setColumnWidth(62, 5600);
            sheet2.setColumnWidth(63, 5600);
            sheet2.setColumnWidth(64, 5600);
            sheet2.setColumnWidth(65, 5600);
            sheet2.setColumnWidth(66, 5600);
            sheet2.setColumnWidth(67, 5600);
            sheet2.setColumnWidth(68, 5600);
            sheet2.setColumnWidth(69, 5600);
            sheet2.setColumnWidth(70, 5600);
            sheet2.setColumnWidth(71, 5600);
            sheet2.setColumnWidth(72, 5600);
            sheet2.setColumnWidth(73, 5600);
            sheet2.setColumnWidth(74, 5600);
            sheet2.setColumnWidth(75, 5600);
            sheet2.setColumnWidth(76, 5600);
            sheet2.setColumnWidth(77, 5600);
            sheet2.setColumnWidth(78, 5600);
            sheet2.setColumnWidth(79, 5600);
            sheet2.setColumnWidth(80, 5600);
            sheet2.setColumnWidth(81, 5600);
            sheet2.setColumnWidth(82, 5600);
            sheet2.setColumnWidth(83, 5600);
            sheet2.setColumnWidth(84, 5600);
            sheet2.setColumnWidth(85, 5600);
            sheet2.setColumnWidth(86, 5600);
            sheet2.setColumnWidth(87, 5600);

            Object[] last2 = new Object[mapping2.length];

            for (int rowIndex = 0; rowIndex < dataList2.size(); rowIndex++) {
                HSSFRow row = sheet2.createRow(rowIndex);
                Object data = dataList2.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping2.length; colIndex++) {
                    String propertyName = mapping2[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last2[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }

            /***********************************
             * sheet 3 : 08-가정용 전기(냉장고,세탁기,식기세척기,전자레인지)
             ***********************************/

            HSSFSheet sheet3 = wb.createSheet("08-가정용 전기(냉장고,세탁기,식기세척기,전자레인지)");

            sheet3.setColumnWidth(0, 5600);
            sheet3.setColumnWidth(1, 5600);
            sheet3.setColumnWidth(2, 5600);
            sheet3.setColumnWidth(3, 5600);
            sheet3.setColumnWidth(4, 5600);
            sheet3.setColumnWidth(5, 5600);
            sheet3.setColumnWidth(6, 5600);
            sheet3.setColumnWidth(7, 5600);
            sheet3.setColumnWidth(8, 9600);
            sheet3.setColumnWidth(9, 5600);
            sheet3.setColumnWidth(10, 5600);
            sheet3.setColumnWidth(11, 5600);
            sheet3.setColumnWidth(12, 5600);
            sheet3.setColumnWidth(13, 5600);
            sheet3.setColumnWidth(14, 5600);
            sheet3.setColumnWidth(15, 5600);
            sheet3.setColumnWidth(16, 5600);
            sheet3.setColumnWidth(17, 5600);
            sheet3.setColumnWidth(18, 5600);
            sheet3.setColumnWidth(19, 5600);
            sheet3.setColumnWidth(20, 5600);
            sheet3.setColumnWidth(21, 5600);
            sheet3.setColumnWidth(22, 5600);
            sheet3.setColumnWidth(23, 5600);
            sheet3.setColumnWidth(24, 5600);
            sheet3.setColumnWidth(25, 5600);
            sheet3.setColumnWidth(26, 5600);
            sheet3.setColumnWidth(27, 5600);
            sheet3.setColumnWidth(28, 5600);
            sheet3.setColumnWidth(29, 5600);
            sheet3.setColumnWidth(30, 5600);
            sheet3.setColumnWidth(31, 5600);
            sheet3.setColumnWidth(32, 5600);
            sheet3.setColumnWidth(33, 5600);
            sheet3.setColumnWidth(34, 5600);
            sheet3.setColumnWidth(35, 5600);
            sheet3.setColumnWidth(36, 5600);
            sheet3.setColumnWidth(37, 5600);
            sheet3.setColumnWidth(38, 5600);
            sheet3.setColumnWidth(39, 5600);
            sheet3.setColumnWidth(40, 5600);
            sheet3.setColumnWidth(41, 5600);
            sheet3.setColumnWidth(42, 5600);
            sheet3.setColumnWidth(43, 5600);
            sheet3.setColumnWidth(44, 5600);
            sheet3.setColumnWidth(45, 5600);
            sheet3.setColumnWidth(46, 5600);
            sheet3.setColumnWidth(47, 5600);
            sheet3.setColumnWidth(48, 5600);
            sheet3.setColumnWidth(48, 5600);
            sheet3.setColumnWidth(50, 5600);
            sheet3.setColumnWidth(51, 5600);
            sheet3.setColumnWidth(52, 5600);
            sheet3.setColumnWidth(53, 5600);
            sheet3.setColumnWidth(54, 5600);
            sheet3.setColumnWidth(55, 5600);
            sheet3.setColumnWidth(56, 5600);
            sheet3.setColumnWidth(57, 5600);
            sheet3.setColumnWidth(58, 5600);
            sheet3.setColumnWidth(59, 5600);
            sheet3.setColumnWidth(60, 5600);
            sheet3.setColumnWidth(61, 5600);
            sheet3.setColumnWidth(62, 5600);
            sheet3.setColumnWidth(63, 5600);
            sheet3.setColumnWidth(64, 5600);
            sheet3.setColumnWidth(65, 5600);
            sheet3.setColumnWidth(66, 5600);
            sheet3.setColumnWidth(67, 5600);
            sheet3.setColumnWidth(68, 5600);
            sheet3.setColumnWidth(69, 5600);
            sheet3.setColumnWidth(70, 5600);
            sheet3.setColumnWidth(71, 5600);
            sheet3.setColumnWidth(72, 5600);
            sheet3.setColumnWidth(73, 5600);
            sheet3.setColumnWidth(74, 5600);
            sheet3.setColumnWidth(75, 5600);
            sheet3.setColumnWidth(76, 5600);
            sheet3.setColumnWidth(77, 5600);
            sheet3.setColumnWidth(78, 5600);
            sheet3.setColumnWidth(79, 5600);
            sheet3.setColumnWidth(80, 5600);
            sheet3.setColumnWidth(81, 5600);
            sheet3.setColumnWidth(82, 5600);
            sheet3.setColumnWidth(83, 5600);
            sheet3.setColumnWidth(84, 5600);
            sheet3.setColumnWidth(85, 5600);
            sheet3.setColumnWidth(86, 5600);
            sheet3.setColumnWidth(87, 5600);

            Object[] last3 = new Object[mapping3.length];

            for (int rowIndex = 0; rowIndex < dataList3.size(); rowIndex++) {
                HSSFRow row = sheet3.createRow(rowIndex);
                Object data = dataList3.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping3.length; colIndex++) {
                    String propertyName = mapping3[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last3[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }


            /***********************************
             * sheet 4 : 09-계절가전(에어컨,온풍기)
             ***********************************/

            HSSFSheet sheet4 = wb.createSheet("09-계절가전(에어컨,온풍기)");

            sheet4.setColumnWidth(0, 5600);
            sheet4.setColumnWidth(1, 5600);
            sheet4.setColumnWidth(2, 5600);
            sheet4.setColumnWidth(3, 5600);
            sheet4.setColumnWidth(4, 5600);
            sheet4.setColumnWidth(5, 5600);
            sheet4.setColumnWidth(6, 5600);
            sheet4.setColumnWidth(7, 5600);
            sheet4.setColumnWidth(8, 9600);
            sheet4.setColumnWidth(9, 5600);
            sheet4.setColumnWidth(10, 5600);
            sheet4.setColumnWidth(11, 5600);
            sheet4.setColumnWidth(12, 5600);
            sheet4.setColumnWidth(13, 5600);
            sheet4.setColumnWidth(14, 5600);
            sheet4.setColumnWidth(15, 5600);
            sheet4.setColumnWidth(16, 5600);
            sheet4.setColumnWidth(17, 5600);
            sheet4.setColumnWidth(18, 5600);
            sheet4.setColumnWidth(19, 5600);
            sheet4.setColumnWidth(20, 5600);
            sheet4.setColumnWidth(21, 5600);
            sheet4.setColumnWidth(22, 5600);
            sheet4.setColumnWidth(23, 5600);
            sheet4.setColumnWidth(24, 5600);
            sheet4.setColumnWidth(25, 5600);
            sheet4.setColumnWidth(26, 5600);
            sheet4.setColumnWidth(27, 5600);
            sheet4.setColumnWidth(28, 5600);
            sheet4.setColumnWidth(29, 5600);
            sheet4.setColumnWidth(30, 5600);
            sheet4.setColumnWidth(31, 5600);
            sheet4.setColumnWidth(32, 5600);
            sheet4.setColumnWidth(33, 5600);
            sheet4.setColumnWidth(34, 5600);
            sheet4.setColumnWidth(35, 5600);
            sheet4.setColumnWidth(36, 5600);
            sheet4.setColumnWidth(37, 5600);
            sheet4.setColumnWidth(38, 5600);
            sheet4.setColumnWidth(39, 5600);
            sheet4.setColumnWidth(40, 5600);
            sheet4.setColumnWidth(41, 5600);
            sheet4.setColumnWidth(42, 5600);
            sheet4.setColumnWidth(43, 5600);
            sheet4.setColumnWidth(44, 5600);
            sheet4.setColumnWidth(45, 5600);
            sheet4.setColumnWidth(46, 5600);
            sheet4.setColumnWidth(47, 5600);
            sheet4.setColumnWidth(48, 5600);
            sheet4.setColumnWidth(48, 5600);
            sheet4.setColumnWidth(50, 5600);
            sheet4.setColumnWidth(51, 5600);
            sheet4.setColumnWidth(52, 5600);
            sheet4.setColumnWidth(53, 5600);
            sheet4.setColumnWidth(54, 5600);
            sheet4.setColumnWidth(55, 5600);
            sheet4.setColumnWidth(56, 5600);
            sheet4.setColumnWidth(57, 5600);
            sheet4.setColumnWidth(58, 5600);
            sheet4.setColumnWidth(59, 5600);
            sheet4.setColumnWidth(60, 5600);
            sheet4.setColumnWidth(61, 5600);
            sheet4.setColumnWidth(62, 5600);
            sheet4.setColumnWidth(63, 5600);
            sheet4.setColumnWidth(64, 5600);
            sheet4.setColumnWidth(65, 5600);
            sheet4.setColumnWidth(66, 5600);
            sheet4.setColumnWidth(67, 5600);
            sheet4.setColumnWidth(68, 5600);
            sheet4.setColumnWidth(69, 5600);
            sheet4.setColumnWidth(70, 5600);
            sheet4.setColumnWidth(71, 5600);
            sheet4.setColumnWidth(72, 5600);
            sheet4.setColumnWidth(73, 5600);
            sheet4.setColumnWidth(74, 5600);
            sheet4.setColumnWidth(75, 5600);
            sheet4.setColumnWidth(76, 5600);
            sheet4.setColumnWidth(77, 5600);
            sheet4.setColumnWidth(78, 5600);
            sheet4.setColumnWidth(79, 5600);
            sheet4.setColumnWidth(80, 5600);
            sheet4.setColumnWidth(81, 5600);
            sheet4.setColumnWidth(82, 5600);
            sheet4.setColumnWidth(83, 5600);
            sheet4.setColumnWidth(84, 5600);
            sheet4.setColumnWidth(85, 5600);
            sheet4.setColumnWidth(86, 5600);
            sheet4.setColumnWidth(87, 5600);

            Object[] last4 = new Object[mapping4.length];

            for (int rowIndex = 0; rowIndex < dataList4.size(); rowIndex++) {
                HSSFRow row = sheet4.createRow(rowIndex);
                Object data = dataList4.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping4.length; colIndex++) {
                    String propertyName = mapping4[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last4[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }

            /***********************************
             * sheet 5 : 10-사무용기기(컴퓨터,노트북,프린터)
             ***********************************/

            HSSFSheet sheet5 = wb.createSheet("10-사무용기기(컴퓨터,노트북,프린터)");

            sheet5.setColumnWidth(0, 5600);
            sheet5.setColumnWidth(1, 5600);
            sheet5.setColumnWidth(2, 5600);
            sheet5.setColumnWidth(3, 5600);
            sheet5.setColumnWidth(4, 5600);
            sheet5.setColumnWidth(5, 5600);
            sheet5.setColumnWidth(6, 5600);
            sheet5.setColumnWidth(7, 5600);
            sheet5.setColumnWidth(8, 5600);
            sheet5.setColumnWidth(9, 5600);
            sheet5.setColumnWidth(10, 5600);
            sheet5.setColumnWidth(11, 5600);
            sheet5.setColumnWidth(12, 5600);
            sheet5.setColumnWidth(13, 5600);
            sheet5.setColumnWidth(14, 5600);
            sheet5.setColumnWidth(15, 5600);
            sheet5.setColumnWidth(16, 5600);
            sheet5.setColumnWidth(17, 5600);
            sheet5.setColumnWidth(18, 5600);
            sheet5.setColumnWidth(19, 5600);
            sheet5.setColumnWidth(20, 5600);
            sheet5.setColumnWidth(21, 5600);
            sheet5.setColumnWidth(22, 5600);
            sheet5.setColumnWidth(23, 5600);
            sheet5.setColumnWidth(24, 5600);
            sheet5.setColumnWidth(25, 5600);
            sheet5.setColumnWidth(26, 5600);
            sheet5.setColumnWidth(27, 5600);
            sheet5.setColumnWidth(28, 5600);
            sheet5.setColumnWidth(29, 5600);
            sheet5.setColumnWidth(30, 5600);
            sheet5.setColumnWidth(31, 5600);
            sheet5.setColumnWidth(32, 5600);
            sheet5.setColumnWidth(33, 5600);
            sheet5.setColumnWidth(34, 5600);
            sheet5.setColumnWidth(35, 5600);
            sheet5.setColumnWidth(36, 5600);
            sheet5.setColumnWidth(37, 5600);
            sheet5.setColumnWidth(38, 5600);
            sheet5.setColumnWidth(39, 5600);
            sheet5.setColumnWidth(40, 5600);
            sheet5.setColumnWidth(41, 5600);
            sheet5.setColumnWidth(42, 5600);
            sheet5.setColumnWidth(43, 5600);
            sheet5.setColumnWidth(44, 5600);
            sheet5.setColumnWidth(45, 5600);
            sheet5.setColumnWidth(46, 5600);
            sheet5.setColumnWidth(47, 5600);
            sheet5.setColumnWidth(48, 5600);
            sheet5.setColumnWidth(48, 5600);
            sheet5.setColumnWidth(50, 5600);
            sheet5.setColumnWidth(51, 5600);
            sheet5.setColumnWidth(52, 5600);
            sheet5.setColumnWidth(53, 5600);
            sheet5.setColumnWidth(54, 5600);
            sheet5.setColumnWidth(55, 5600);
            sheet5.setColumnWidth(56, 5600);
            sheet5.setColumnWidth(57, 5600);
            sheet5.setColumnWidth(58, 5600);
            sheet5.setColumnWidth(59, 5600);
            sheet5.setColumnWidth(60, 5600);
            sheet5.setColumnWidth(61, 5600);
            sheet5.setColumnWidth(62, 5600);
            sheet5.setColumnWidth(63, 5600);
            sheet5.setColumnWidth(64, 5600);
            sheet5.setColumnWidth(65, 5600);
            sheet5.setColumnWidth(66, 5600);
            sheet5.setColumnWidth(67, 5600);
            sheet5.setColumnWidth(68, 5600);
            sheet5.setColumnWidth(69, 5600);
            sheet5.setColumnWidth(70, 5600);
            sheet5.setColumnWidth(71, 5600);
            sheet5.setColumnWidth(72, 5600);
            sheet5.setColumnWidth(73, 5600);
            sheet5.setColumnWidth(74, 5600);
            sheet5.setColumnWidth(75, 5600);
            sheet5.setColumnWidth(76, 5600);
            sheet5.setColumnWidth(77, 5600);
            sheet5.setColumnWidth(78, 5600);
            sheet5.setColumnWidth(79, 5600);
            sheet5.setColumnWidth(80, 5600);
            sheet5.setColumnWidth(81, 5600);
            sheet5.setColumnWidth(82, 5600);
            sheet5.setColumnWidth(83, 5600);
            sheet5.setColumnWidth(84, 5600);
            sheet5.setColumnWidth(85, 5600);
            sheet5.setColumnWidth(86, 5600);
            sheet5.setColumnWidth(87, 5600);

            Object[] last5 = new Object[mapping5.length];

            for (int rowIndex = 0; rowIndex < dataList5.size(); rowIndex++) {
                HSSFRow row = sheet5.createRow(rowIndex);
                Object data = dataList5.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping5.length; colIndex++) {
                    String propertyName = mapping5[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last5[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 6 : 11-광학기기(디지털카메라,캠코더)
             ***********************************/

            HSSFSheet sheet6 = wb.createSheet("11-광학기기(디지털카메라,캠코더)");

            sheet6.setColumnWidth(0, 5600);
            sheet6.setColumnWidth(1, 5600);
            sheet6.setColumnWidth(2, 5600);
            sheet6.setColumnWidth(3, 5600);
            sheet6.setColumnWidth(4, 5600);
            sheet6.setColumnWidth(5, 5600);
            sheet6.setColumnWidth(6, 5600);
            sheet6.setColumnWidth(7, 5600);
            sheet6.setColumnWidth(8, 5600);
            sheet6.setColumnWidth(9, 5600);
            sheet6.setColumnWidth(10, 5600);
            sheet6.setColumnWidth(11, 5600);
            sheet6.setColumnWidth(12, 5600);
            sheet6.setColumnWidth(13, 5600);
            sheet6.setColumnWidth(14, 5600);
            sheet6.setColumnWidth(15, 5600);
            sheet6.setColumnWidth(16, 5600);
            sheet6.setColumnWidth(17, 5600);
            sheet6.setColumnWidth(18, 5600);
            sheet6.setColumnWidth(19, 5600);
            sheet6.setColumnWidth(20, 5600);
            sheet6.setColumnWidth(21, 5600);
            sheet6.setColumnWidth(22, 5600);
            sheet6.setColumnWidth(23, 5600);
            sheet6.setColumnWidth(24, 5600);
            sheet6.setColumnWidth(25, 5600);
            sheet6.setColumnWidth(26, 5600);
            sheet6.setColumnWidth(27, 5600);
            sheet6.setColumnWidth(28, 5600);
            sheet6.setColumnWidth(29, 5600);
            sheet6.setColumnWidth(30, 5600);
            sheet6.setColumnWidth(31, 5600);
            sheet6.setColumnWidth(32, 5600);
            sheet6.setColumnWidth(33, 5600);
            sheet6.setColumnWidth(34, 5600);
            sheet6.setColumnWidth(35, 5600);
            sheet6.setColumnWidth(36, 5600);
            sheet6.setColumnWidth(37, 5600);
            sheet6.setColumnWidth(38, 5600);
            sheet6.setColumnWidth(39, 5600);
            sheet6.setColumnWidth(40, 5600);
            sheet6.setColumnWidth(41, 5600);
            sheet6.setColumnWidth(42, 5600);
            sheet6.setColumnWidth(43, 5600);
            sheet6.setColumnWidth(44, 5600);
            sheet6.setColumnWidth(45, 5600);
            sheet6.setColumnWidth(46, 5600);
            sheet6.setColumnWidth(47, 5600);
            sheet6.setColumnWidth(48, 5600);
            sheet6.setColumnWidth(48, 5600);
            sheet6.setColumnWidth(50, 5600);
            sheet6.setColumnWidth(51, 5600);
            sheet6.setColumnWidth(52, 5600);
            sheet6.setColumnWidth(53, 5600);
            sheet6.setColumnWidth(54, 5600);
            sheet6.setColumnWidth(55, 5600);
            sheet6.setColumnWidth(56, 5600);
            sheet6.setColumnWidth(57, 5600);
            sheet6.setColumnWidth(58, 5600);
            sheet6.setColumnWidth(59, 5600);
            sheet6.setColumnWidth(60, 5600);
            sheet6.setColumnWidth(61, 5600);
            sheet6.setColumnWidth(62, 5600);
            sheet6.setColumnWidth(63, 5600);
            sheet6.setColumnWidth(64, 5600);
            sheet6.setColumnWidth(65, 5600);
            sheet6.setColumnWidth(66, 5600);
            sheet6.setColumnWidth(67, 5600);
            sheet6.setColumnWidth(68, 5600);
            sheet6.setColumnWidth(69, 5600);
            sheet6.setColumnWidth(70, 5600);
            sheet6.setColumnWidth(71, 5600);
            sheet6.setColumnWidth(72, 5600);
            sheet6.setColumnWidth(73, 5600);
            sheet6.setColumnWidth(74, 5600);
            sheet6.setColumnWidth(75, 5600);
            sheet6.setColumnWidth(76, 5600);
            sheet6.setColumnWidth(77, 5600);
            sheet6.setColumnWidth(78, 5600);
            sheet6.setColumnWidth(79, 5600);
            sheet6.setColumnWidth(80, 5600);
            sheet6.setColumnWidth(81, 5600);
            sheet6.setColumnWidth(82, 5600);
            sheet6.setColumnWidth(83, 5600);
            sheet6.setColumnWidth(84, 5600);
            sheet6.setColumnWidth(85, 5600);
            sheet6.setColumnWidth(86, 5600);
            sheet6.setColumnWidth(87, 5600);

            Object[] last6 = new Object[mapping6.length];

            for (int rowIndex = 0; rowIndex < dataList6.size(); rowIndex++) {
                HSSFRow row = sheet6.createRow(rowIndex);
                Object data = dataList6.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping6.length; colIndex++) {
                    String propertyName = mapping6[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last6[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }

            /***********************************
             * sheet 7 : 12-소형전자(MP3,전자사전 등)
             ***********************************/

            HSSFSheet sheet7 = wb.createSheet("12-소형전자(MP3,전자사전 등)");

            sheet7.setColumnWidth(0, 5600);
            sheet7.setColumnWidth(1, 5600);
            sheet7.setColumnWidth(2, 5600);
            sheet7.setColumnWidth(3, 5600);
            sheet7.setColumnWidth(4, 5600);
            sheet7.setColumnWidth(5, 5600);
            sheet7.setColumnWidth(6, 5600);
            sheet7.setColumnWidth(7, 5600);
            sheet7.setColumnWidth(8, 5600);
            sheet7.setColumnWidth(9, 5600);
            sheet7.setColumnWidth(10, 5600);
            sheet7.setColumnWidth(11, 5600);
            sheet7.setColumnWidth(12, 5600);
            sheet7.setColumnWidth(13, 5600);
            sheet7.setColumnWidth(14, 5600);
            sheet7.setColumnWidth(15, 5600);
            sheet7.setColumnWidth(16, 5600);
            sheet7.setColumnWidth(17, 5600);
            sheet7.setColumnWidth(18, 5600);
            sheet7.setColumnWidth(19, 5600);
            sheet7.setColumnWidth(20, 5600);
            sheet7.setColumnWidth(21, 5600);
            sheet7.setColumnWidth(22, 5600);
            sheet7.setColumnWidth(23, 5600);
            sheet7.setColumnWidth(24, 5600);
            sheet7.setColumnWidth(25, 5600);
            sheet7.setColumnWidth(26, 5600);
            sheet7.setColumnWidth(27, 5600);
            sheet7.setColumnWidth(28, 5600);
            sheet7.setColumnWidth(29, 5600);
            sheet7.setColumnWidth(30, 5600);
            sheet7.setColumnWidth(31, 5600);
            sheet7.setColumnWidth(32, 5600);
            sheet7.setColumnWidth(33, 5600);
            sheet7.setColumnWidth(34, 5600);
            sheet7.setColumnWidth(35, 5600);
            sheet7.setColumnWidth(36, 5600);
            sheet7.setColumnWidth(37, 5600);
            sheet7.setColumnWidth(38, 5600);
            sheet7.setColumnWidth(39, 5600);
            sheet7.setColumnWidth(40, 5600);
            sheet7.setColumnWidth(41, 5600);
            sheet7.setColumnWidth(42, 5600);
            sheet7.setColumnWidth(43, 5600);
            sheet7.setColumnWidth(44, 5600);
            sheet7.setColumnWidth(45, 5600);
            sheet7.setColumnWidth(46, 5600);
            sheet7.setColumnWidth(47, 5600);
            sheet7.setColumnWidth(48, 5600);
            sheet7.setColumnWidth(48, 5600);
            sheet7.setColumnWidth(50, 5600);
            sheet7.setColumnWidth(51, 5600);
            sheet7.setColumnWidth(52, 5600);
            sheet7.setColumnWidth(53, 5600);
            sheet7.setColumnWidth(54, 5600);
            sheet7.setColumnWidth(55, 5600);
            sheet7.setColumnWidth(56, 5600);
            sheet7.setColumnWidth(57, 5600);
            sheet7.setColumnWidth(58, 5600);
            sheet7.setColumnWidth(59, 5600);
            sheet7.setColumnWidth(60, 5600);
            sheet7.setColumnWidth(61, 5600);
            sheet7.setColumnWidth(62, 5600);
            sheet7.setColumnWidth(63, 5600);
            sheet7.setColumnWidth(64, 5600);
            sheet7.setColumnWidth(65, 5600);
            sheet7.setColumnWidth(66, 5600);
            sheet7.setColumnWidth(67, 5600);
            sheet7.setColumnWidth(68, 5600);
            sheet7.setColumnWidth(69, 5600);
            sheet7.setColumnWidth(70, 5600);
            sheet7.setColumnWidth(71, 5600);
            sheet7.setColumnWidth(72, 5600);
            sheet7.setColumnWidth(73, 5600);
            sheet7.setColumnWidth(74, 5600);
            sheet7.setColumnWidth(75, 5600);
            sheet7.setColumnWidth(76, 5600);
            sheet7.setColumnWidth(77, 5600);
            sheet7.setColumnWidth(78, 5600);
            sheet7.setColumnWidth(79, 5600);
            sheet7.setColumnWidth(80, 5600);
            sheet7.setColumnWidth(81, 5600);
            sheet7.setColumnWidth(82, 5600);
            sheet7.setColumnWidth(83, 5600);
            sheet7.setColumnWidth(84, 5600);
            sheet7.setColumnWidth(85, 5600);
            sheet7.setColumnWidth(86, 5600);
            sheet7.setColumnWidth(87, 5600);

            Object[] last7 = new Object[mapping7.length];

            for (int rowIndex = 0; rowIndex < dataList7.size(); rowIndex++) {
                HSSFRow row = sheet7.createRow(rowIndex);
                Object data = dataList7.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping7.length; colIndex++) {
                    String propertyName = mapping7[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last7[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }

            /***********************************
             * sheet 8 : 14-네비게이션
             ***********************************/

            HSSFSheet sheet8 = wb.createSheet("14-네비게이션");

            sheet8.setColumnWidth(0, 5600);
            sheet8.setColumnWidth(1, 5600);
            sheet8.setColumnWidth(2, 5600);
            sheet8.setColumnWidth(3, 5600);
            sheet8.setColumnWidth(4, 5600);
            sheet8.setColumnWidth(5, 5600);
            sheet8.setColumnWidth(6, 5600);
            sheet8.setColumnWidth(7, 5600);
            sheet8.setColumnWidth(8, 5600);
            sheet8.setColumnWidth(9, 5600);
            sheet8.setColumnWidth(10, 5600);
            sheet8.setColumnWidth(11, 5600);
            sheet8.setColumnWidth(12, 5600);
            sheet8.setColumnWidth(13, 5600);
            sheet8.setColumnWidth(14, 5600);
            sheet8.setColumnWidth(15, 5600);
            sheet8.setColumnWidth(16, 5600);
            sheet8.setColumnWidth(17, 5600);
            sheet8.setColumnWidth(18, 5600);
            sheet8.setColumnWidth(19, 5600);
            sheet8.setColumnWidth(20, 5600);
            sheet8.setColumnWidth(21, 5600);
            sheet8.setColumnWidth(22, 5600);
            sheet8.setColumnWidth(23, 5600);
            sheet8.setColumnWidth(24, 5600);
            sheet8.setColumnWidth(25, 5600);
            sheet8.setColumnWidth(26, 5600);
            sheet8.setColumnWidth(27, 5600);
            sheet8.setColumnWidth(28, 5600);
            sheet8.setColumnWidth(29, 5600);
            sheet8.setColumnWidth(30, 5600);
            sheet8.setColumnWidth(31, 5600);
            sheet8.setColumnWidth(32, 5600);
            sheet8.setColumnWidth(33, 5600);
            sheet8.setColumnWidth(34, 5600);
            sheet8.setColumnWidth(35, 5600);
            sheet8.setColumnWidth(36, 5600);
            sheet8.setColumnWidth(37, 5600);
            sheet8.setColumnWidth(38, 5600);
            sheet8.setColumnWidth(39, 5600);
            sheet8.setColumnWidth(40, 5600);
            sheet8.setColumnWidth(41, 5600);
            sheet8.setColumnWidth(42, 5600);
            sheet8.setColumnWidth(43, 5600);
            sheet8.setColumnWidth(44, 5600);
            sheet8.setColumnWidth(45, 5600);
            sheet8.setColumnWidth(46, 5600);
            sheet8.setColumnWidth(47, 5600);
            sheet8.setColumnWidth(48, 5600);
            sheet8.setColumnWidth(48, 5600);
            sheet8.setColumnWidth(50, 5600);
            sheet8.setColumnWidth(51, 5600);
            sheet8.setColumnWidth(52, 5600);
            sheet8.setColumnWidth(53, 5600);
            sheet8.setColumnWidth(54, 5600);
            sheet8.setColumnWidth(55, 5600);
            sheet8.setColumnWidth(56, 5600);
            sheet8.setColumnWidth(57, 5600);
            sheet8.setColumnWidth(58, 5600);
            sheet8.setColumnWidth(59, 5600);
            sheet8.setColumnWidth(60, 5600);
            sheet8.setColumnWidth(61, 5600);
            sheet8.setColumnWidth(62, 5600);
            sheet8.setColumnWidth(63, 5600);
            sheet8.setColumnWidth(64, 5600);
            sheet8.setColumnWidth(65, 5600);
            sheet8.setColumnWidth(66, 5600);
            sheet8.setColumnWidth(67, 5600);
            sheet8.setColumnWidth(68, 5600);
            sheet8.setColumnWidth(69, 5600);
            sheet8.setColumnWidth(70, 5600);
            sheet8.setColumnWidth(71, 5600);
            sheet8.setColumnWidth(72, 5600);
            sheet8.setColumnWidth(73, 5600);
            sheet8.setColumnWidth(74, 5600);
            sheet8.setColumnWidth(75, 5600);
            sheet8.setColumnWidth(76, 5600);
            sheet8.setColumnWidth(77, 5600);
            sheet8.setColumnWidth(78, 5600);
            sheet8.setColumnWidth(79, 5600);
            sheet8.setColumnWidth(80, 5600);
            sheet8.setColumnWidth(81, 5600);
            sheet8.setColumnWidth(82, 5600);
            sheet8.setColumnWidth(83, 5600);
            sheet8.setColumnWidth(84, 5600);
            sheet8.setColumnWidth(85, 5600);
            sheet8.setColumnWidth(86, 5600);
            sheet8.setColumnWidth(87, 5600);

            Object[] last8 = new Object[mapping8.length];

            for (int rowIndex = 0; rowIndex < dataList8.size(); rowIndex++) {
                HSSFRow row = sheet8.createRow(rowIndex);
                Object data = dataList8.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping8.length; colIndex++) {
                    String propertyName = mapping8[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last8[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 9 : 15-자동차용품(자동차부품,기타 자동차용품)
             ***********************************/

            HSSFSheet sheet9 = wb.createSheet("15-자동차용품(자동차부품,기타 자동차용품)");

            sheet9.setColumnWidth(0, 5600);
            sheet9.setColumnWidth(1, 5600);
            sheet9.setColumnWidth(2, 5600);
            sheet9.setColumnWidth(3, 5600);
            sheet9.setColumnWidth(4, 5600);
            sheet9.setColumnWidth(5, 5600);
            sheet9.setColumnWidth(6, 5600);
            sheet9.setColumnWidth(7, 5600);
            sheet9.setColumnWidth(8, 5600);
            sheet9.setColumnWidth(9, 5600);
            sheet9.setColumnWidth(10, 5600);
            sheet9.setColumnWidth(11, 5600);
            sheet9.setColumnWidth(12, 5600);
            sheet9.setColumnWidth(13, 5600);
            sheet9.setColumnWidth(14, 5600);
            sheet9.setColumnWidth(15, 5600);
            sheet9.setColumnWidth(16, 5600);
            sheet9.setColumnWidth(17, 5600);
            sheet9.setColumnWidth(18, 5600);
            sheet9.setColumnWidth(19, 5600);
            sheet9.setColumnWidth(20, 5600);
            sheet9.setColumnWidth(21, 5600);
            sheet9.setColumnWidth(22, 5600);
            sheet9.setColumnWidth(23, 5600);
            sheet9.setColumnWidth(24, 5600);
            sheet9.setColumnWidth(25, 5600);
            sheet9.setColumnWidth(26, 5600);
            sheet9.setColumnWidth(27, 5600);
            sheet9.setColumnWidth(28, 5600);
            sheet9.setColumnWidth(29, 5600);
            sheet9.setColumnWidth(30, 5600);
            sheet9.setColumnWidth(31, 5600);
            sheet9.setColumnWidth(32, 5600);
            sheet9.setColumnWidth(33, 5600);
            sheet9.setColumnWidth(34, 5600);
            sheet9.setColumnWidth(35, 5600);
            sheet9.setColumnWidth(36, 5600);
            sheet9.setColumnWidth(37, 5600);
            sheet9.setColumnWidth(38, 5600);
            sheet9.setColumnWidth(39, 5600);
            sheet9.setColumnWidth(40, 5600);
            sheet9.setColumnWidth(41, 5600);
            sheet9.setColumnWidth(42, 5600);
            sheet9.setColumnWidth(43, 5600);
            sheet9.setColumnWidth(44, 5600);
            sheet9.setColumnWidth(45, 5600);
            sheet9.setColumnWidth(46, 5600);
            sheet9.setColumnWidth(47, 5600);
            sheet9.setColumnWidth(48, 5600);
            sheet9.setColumnWidth(48, 5600);
            sheet9.setColumnWidth(50, 5600);
            sheet9.setColumnWidth(51, 5600);
            sheet9.setColumnWidth(52, 5600);
            sheet9.setColumnWidth(53, 5600);
            sheet9.setColumnWidth(54, 5600);
            sheet9.setColumnWidth(55, 5600);
            sheet9.setColumnWidth(56, 5600);
            sheet9.setColumnWidth(57, 5600);
            sheet9.setColumnWidth(58, 5600);
            sheet9.setColumnWidth(59, 5600);
            sheet9.setColumnWidth(60, 5600);
            sheet9.setColumnWidth(61, 5600);
            sheet9.setColumnWidth(62, 5600);
            sheet9.setColumnWidth(63, 5600);
            sheet9.setColumnWidth(64, 5600);
            sheet9.setColumnWidth(65, 5600);
            sheet9.setColumnWidth(66, 5600);
            sheet9.setColumnWidth(67, 5600);
            sheet9.setColumnWidth(68, 5600);
            sheet9.setColumnWidth(69, 5600);
            sheet9.setColumnWidth(70, 5600);
            sheet9.setColumnWidth(71, 5600);
            sheet9.setColumnWidth(72, 5600);
            sheet9.setColumnWidth(73, 5600);
            sheet9.setColumnWidth(74, 5600);
            sheet9.setColumnWidth(75, 5600);
            sheet9.setColumnWidth(76, 5600);
            sheet9.setColumnWidth(77, 5600);
            sheet9.setColumnWidth(78, 5600);
            sheet9.setColumnWidth(79, 5600);
            sheet9.setColumnWidth(80, 5600);
            sheet9.setColumnWidth(81, 5600);
            sheet9.setColumnWidth(82, 5600);
            sheet9.setColumnWidth(83, 5600);
            sheet9.setColumnWidth(84, 5600);
            sheet9.setColumnWidth(85, 5600);
            sheet9.setColumnWidth(86, 5600);
            sheet9.setColumnWidth(87, 5600);

            Object[] last9 = new Object[mapping9.length];

            for (int rowIndex = 0; rowIndex < dataList9.size(); rowIndex++) {
                HSSFRow row = sheet9.createRow(rowIndex);
                Object data = dataList9.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping9.length; colIndex++) {
                    String propertyName = mapping9[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last9[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 10 : 16-의료기기
             ***********************************/

            HSSFSheet sheet10 = wb.createSheet("16-의료기기");

            sheet10.setColumnWidth(0, 5600);
            sheet10.setColumnWidth(1, 5600);
            sheet10.setColumnWidth(2, 5600);
            sheet10.setColumnWidth(3, 5600);
            sheet10.setColumnWidth(4, 5600);
            sheet10.setColumnWidth(5, 5600);
            sheet10.setColumnWidth(6, 5600);
            sheet10.setColumnWidth(7, 5600);
            sheet10.setColumnWidth(8, 5600);
            sheet10.setColumnWidth(9, 5600);
            sheet10.setColumnWidth(10, 5600);
            sheet10.setColumnWidth(11, 5600);
            sheet10.setColumnWidth(12, 5600);
            sheet10.setColumnWidth(13, 5600);
            sheet10.setColumnWidth(14, 5600);
            sheet10.setColumnWidth(15, 5600);
            sheet10.setColumnWidth(16, 5600);
            sheet10.setColumnWidth(17, 5600);
            sheet10.setColumnWidth(18, 5600);
            sheet10.setColumnWidth(19, 5600);
            sheet10.setColumnWidth(20, 5600);
            sheet10.setColumnWidth(21, 5600);
            sheet10.setColumnWidth(22, 5600);
            sheet10.setColumnWidth(23, 5600);
            sheet10.setColumnWidth(24, 5600);
            sheet10.setColumnWidth(25, 5600);
            sheet10.setColumnWidth(26, 5600);
            sheet10.setColumnWidth(27, 5600);
            sheet10.setColumnWidth(28, 5600);
            sheet10.setColumnWidth(29, 5600);
            sheet10.setColumnWidth(30, 5600);
            sheet10.setColumnWidth(31, 5600);
            sheet10.setColumnWidth(32, 5600);
            sheet10.setColumnWidth(33, 5600);
            sheet10.setColumnWidth(34, 5600);
            sheet10.setColumnWidth(35, 5600);
            sheet10.setColumnWidth(36, 5600);
            sheet10.setColumnWidth(37, 5600);
            sheet10.setColumnWidth(38, 5600);
            sheet10.setColumnWidth(39, 5600);
            sheet10.setColumnWidth(40, 5600);
            sheet10.setColumnWidth(41, 5600);
            sheet10.setColumnWidth(42, 5600);
            sheet10.setColumnWidth(43, 5600);
            sheet10.setColumnWidth(44, 5600);
            sheet10.setColumnWidth(45, 5600);
            sheet10.setColumnWidth(46, 5600);
            sheet10.setColumnWidth(47, 5600);
            sheet10.setColumnWidth(48, 5600);
            sheet10.setColumnWidth(48, 5600);
            sheet10.setColumnWidth(50, 5600);
            sheet10.setColumnWidth(51, 5600);
            sheet10.setColumnWidth(52, 5600);
            sheet10.setColumnWidth(53, 5600);
            sheet10.setColumnWidth(54, 5600);
            sheet10.setColumnWidth(55, 5600);
            sheet10.setColumnWidth(56, 5600);
            sheet10.setColumnWidth(57, 5600);
            sheet10.setColumnWidth(58, 5600);
            sheet10.setColumnWidth(59, 5600);
            sheet10.setColumnWidth(60, 5600);
            sheet10.setColumnWidth(61, 5600);
            sheet10.setColumnWidth(62, 5600);
            sheet10.setColumnWidth(63, 5600);
            sheet10.setColumnWidth(64, 5600);
            sheet10.setColumnWidth(65, 5600);
            sheet10.setColumnWidth(66, 5600);
            sheet10.setColumnWidth(67, 5600);
            sheet10.setColumnWidth(68, 5600);
            sheet10.setColumnWidth(69, 5600);
            sheet10.setColumnWidth(70, 5600);
            sheet10.setColumnWidth(71, 5600);
            sheet10.setColumnWidth(72, 5600);
            sheet10.setColumnWidth(73, 5600);
            sheet10.setColumnWidth(74, 5600);
            sheet10.setColumnWidth(75, 5600);
            sheet10.setColumnWidth(76, 5600);
            sheet10.setColumnWidth(77, 5600);
            sheet10.setColumnWidth(78, 5600);
            sheet10.setColumnWidth(79, 5600);
            sheet10.setColumnWidth(80, 5600);
            sheet10.setColumnWidth(81, 5600);
            sheet10.setColumnWidth(82, 5600);
            sheet10.setColumnWidth(83, 5600);
            sheet10.setColumnWidth(84, 5600);
            sheet10.setColumnWidth(85, 5600);
            sheet10.setColumnWidth(86, 5600);
            sheet10.setColumnWidth(87, 5600);

            Object[] last10 = new Object[mapping10.length];

            for (int rowIndex = 0; rowIndex < dataList10.size(); rowIndex++) {
                HSSFRow row = sheet10.createRow(rowIndex);
                Object data = dataList10.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping10.length; colIndex++) {
                    String propertyName = mapping10[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last10[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 11 : 17-주방용품
             ***********************************/

            HSSFSheet sheet11 = wb.createSheet("17-주방용품");

            sheet11.setColumnWidth(0, 5600);
            sheet11.setColumnWidth(1, 5600);
            sheet11.setColumnWidth(2, 5600);
            sheet11.setColumnWidth(3, 5600);
            sheet11.setColumnWidth(4, 5600);
            sheet11.setColumnWidth(5, 5600);
            sheet11.setColumnWidth(6, 5600);
            sheet11.setColumnWidth(7, 5600);
            sheet11.setColumnWidth(8, 5600);
            sheet11.setColumnWidth(9, 5600);
            sheet11.setColumnWidth(10, 5600);
            sheet11.setColumnWidth(11, 5600);
            sheet11.setColumnWidth(12, 5600);
            sheet11.setColumnWidth(13, 5600);
            sheet11.setColumnWidth(14, 5600);
            sheet11.setColumnWidth(15, 5600);
            sheet11.setColumnWidth(16, 5600);
            sheet11.setColumnWidth(17, 5600);
            sheet11.setColumnWidth(18, 5600);
            sheet11.setColumnWidth(19, 5600);
            sheet11.setColumnWidth(20, 5600);
            sheet11.setColumnWidth(21, 5600);
            sheet11.setColumnWidth(22, 5600);
            sheet11.setColumnWidth(23, 5600);
            sheet11.setColumnWidth(24, 5600);
            sheet11.setColumnWidth(25, 5600);
            sheet11.setColumnWidth(26, 5600);
            sheet11.setColumnWidth(27, 5600);
            sheet11.setColumnWidth(28, 5600);
            sheet11.setColumnWidth(29, 5600);
            sheet11.setColumnWidth(30, 5600);
            sheet11.setColumnWidth(31, 5600);
            sheet11.setColumnWidth(32, 5600);
            sheet11.setColumnWidth(33, 5600);
            sheet11.setColumnWidth(34, 5600);
            sheet11.setColumnWidth(35, 5600);
            sheet11.setColumnWidth(36, 5600);
            sheet11.setColumnWidth(37, 5600);
            sheet11.setColumnWidth(38, 5600);
            sheet11.setColumnWidth(39, 5600);
            sheet11.setColumnWidth(40, 5600);
            sheet11.setColumnWidth(41, 5600);
            sheet11.setColumnWidth(42, 5600);
            sheet11.setColumnWidth(43, 5600);
            sheet11.setColumnWidth(44, 5600);
            sheet11.setColumnWidth(45, 5600);
            sheet11.setColumnWidth(46, 5600);
            sheet11.setColumnWidth(47, 5600);
            sheet11.setColumnWidth(48, 5600);
            sheet11.setColumnWidth(48, 5600);
            sheet11.setColumnWidth(50, 5600);
            sheet11.setColumnWidth(51, 5600);
            sheet11.setColumnWidth(52, 5600);
            sheet11.setColumnWidth(53, 5600);
            sheet11.setColumnWidth(54, 5600);
            sheet11.setColumnWidth(55, 5600);
            sheet11.setColumnWidth(56, 5600);
            sheet11.setColumnWidth(57, 5600);
            sheet11.setColumnWidth(58, 5600);
            sheet11.setColumnWidth(59, 5600);
            sheet11.setColumnWidth(60, 5600);
            sheet11.setColumnWidth(61, 5600);
            sheet11.setColumnWidth(62, 5600);
            sheet11.setColumnWidth(63, 5600);
            sheet11.setColumnWidth(64, 5600);
            sheet11.setColumnWidth(65, 5600);
            sheet11.setColumnWidth(66, 5600);
            sheet11.setColumnWidth(67, 5600);
            sheet11.setColumnWidth(68, 5600);
            sheet11.setColumnWidth(69, 5600);
            sheet11.setColumnWidth(70, 5600);
            sheet11.setColumnWidth(71, 5600);
            sheet11.setColumnWidth(72, 5600);
            sheet11.setColumnWidth(73, 5600);
            sheet11.setColumnWidth(74, 5600);
            sheet11.setColumnWidth(75, 5600);
            sheet11.setColumnWidth(76, 5600);
            sheet11.setColumnWidth(77, 5600);
            sheet11.setColumnWidth(78, 5600);
            sheet11.setColumnWidth(79, 5600);
            sheet11.setColumnWidth(80, 5600);
            sheet11.setColumnWidth(81, 5600);
            sheet11.setColumnWidth(82, 5600);
            sheet11.setColumnWidth(83, 5600);
            sheet11.setColumnWidth(84, 5600);
            sheet11.setColumnWidth(85, 5600);
            sheet11.setColumnWidth(86, 5600);
            sheet11.setColumnWidth(87, 5600);

            Object[] last11 = new Object[mapping11.length];

            for (int rowIndex = 0; rowIndex < dataList11.size(); rowIndex++) {
                HSSFRow row = sheet11.createRow(rowIndex);
                Object data = dataList11.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping11.length; colIndex++) {
                    String propertyName = mapping11[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last11[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 12 : 24-악기
             ***********************************/

            HSSFSheet sheet12 = wb.createSheet("24-악기");

            sheet12.setColumnWidth(0, 5600);
            sheet12.setColumnWidth(1, 5600);
            sheet12.setColumnWidth(2, 5600);
            sheet12.setColumnWidth(3, 5600);
            sheet12.setColumnWidth(4, 5600);
            sheet12.setColumnWidth(5, 5600);
            sheet12.setColumnWidth(6, 5600);
            sheet12.setColumnWidth(7, 5600);
            sheet12.setColumnWidth(8, 5600);
            sheet12.setColumnWidth(9, 5600);
            sheet12.setColumnWidth(10, 5600);
            sheet12.setColumnWidth(11, 5600);
            sheet12.setColumnWidth(12, 5600);
            sheet12.setColumnWidth(13, 5600);
            sheet12.setColumnWidth(14, 5600);
            sheet12.setColumnWidth(15, 5600);
            sheet12.setColumnWidth(16, 5600);
            sheet12.setColumnWidth(17, 5600);
            sheet12.setColumnWidth(18, 5600);
            sheet12.setColumnWidth(19, 5600);
            sheet12.setColumnWidth(20, 5600);
            sheet12.setColumnWidth(21, 5600);
            sheet12.setColumnWidth(22, 5600);
            sheet12.setColumnWidth(23, 5600);
            sheet12.setColumnWidth(24, 5600);
            sheet12.setColumnWidth(25, 5600);
            sheet12.setColumnWidth(26, 5600);
            sheet12.setColumnWidth(27, 5600);
            sheet12.setColumnWidth(28, 5600);
            sheet12.setColumnWidth(29, 5600);
            sheet12.setColumnWidth(30, 5600);
            sheet12.setColumnWidth(31, 5600);
            sheet12.setColumnWidth(32, 5600);
            sheet12.setColumnWidth(33, 5600);
            sheet12.setColumnWidth(34, 5600);
            sheet12.setColumnWidth(35, 5600);
            sheet12.setColumnWidth(36, 5600);
            sheet12.setColumnWidth(37, 5600);
            sheet12.setColumnWidth(38, 5600);
            sheet12.setColumnWidth(39, 5600);
            sheet12.setColumnWidth(40, 5600);
            sheet12.setColumnWidth(41, 5600);
            sheet12.setColumnWidth(42, 5600);
            sheet12.setColumnWidth(43, 5600);
            sheet12.setColumnWidth(44, 5600);
            sheet12.setColumnWidth(45, 5600);
            sheet12.setColumnWidth(46, 5600);
            sheet12.setColumnWidth(47, 5600);
            sheet12.setColumnWidth(48, 5600);
            sheet12.setColumnWidth(48, 5600);
            sheet12.setColumnWidth(50, 5600);
            sheet12.setColumnWidth(51, 5600);
            sheet12.setColumnWidth(52, 5600);
            sheet12.setColumnWidth(53, 5600);
            sheet12.setColumnWidth(54, 5600);
            sheet12.setColumnWidth(55, 5600);
            sheet12.setColumnWidth(56, 5600);
            sheet12.setColumnWidth(57, 5600);
            sheet12.setColumnWidth(58, 5600);
            sheet12.setColumnWidth(59, 5600);
            sheet12.setColumnWidth(60, 5600);
            sheet12.setColumnWidth(61, 5600);
            sheet12.setColumnWidth(62, 5600);
            sheet12.setColumnWidth(63, 5600);
            sheet12.setColumnWidth(64, 5600);
            sheet12.setColumnWidth(65, 5600);
            sheet12.setColumnWidth(66, 5600);
            sheet12.setColumnWidth(67, 5600);
            sheet12.setColumnWidth(68, 5600);
            sheet12.setColumnWidth(69, 5600);
            sheet12.setColumnWidth(70, 5600);
            sheet12.setColumnWidth(71, 5600);
            sheet12.setColumnWidth(72, 5600);
            sheet12.setColumnWidth(73, 5600);
            sheet12.setColumnWidth(74, 5600);
            sheet12.setColumnWidth(75, 5600);
            sheet12.setColumnWidth(76, 5600);
            sheet12.setColumnWidth(77, 5600);
            sheet12.setColumnWidth(78, 5600);
            sheet12.setColumnWidth(79, 5600);
            sheet12.setColumnWidth(80, 5600);
            sheet12.setColumnWidth(81, 5600);
            sheet12.setColumnWidth(82, 5600);
            sheet12.setColumnWidth(83, 5600);
            sheet12.setColumnWidth(84, 5600);
            sheet12.setColumnWidth(85, 5600);
            sheet12.setColumnWidth(86, 5600);
            sheet12.setColumnWidth(87, 5600);

            Object[] last12 = new Object[mapping12.length];

            for (int rowIndex = 0; rowIndex < dataList12.size(); rowIndex++) {
                HSSFRow row = sheet12.createRow(rowIndex);
                Object data = dataList12.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping12.length; colIndex++) {
                    String propertyName = mapping12[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last12[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 13 : 35-기타
             ***********************************/

            HSSFSheet sheet13 = wb.createSheet("35-기타");

            sheet13.setColumnWidth(0, 5600);
            sheet13.setColumnWidth(1, 5600);
            sheet13.setColumnWidth(2, 5600);
            sheet13.setColumnWidth(3, 5600);
            sheet13.setColumnWidth(4, 5600);
            sheet13.setColumnWidth(5, 5600);
            sheet13.setColumnWidth(6, 5600);
            sheet13.setColumnWidth(7, 5600);
            sheet13.setColumnWidth(8, 5600);
            sheet13.setColumnWidth(9, 5600);
            sheet13.setColumnWidth(10, 5600);
            sheet13.setColumnWidth(11, 5600);
            sheet13.setColumnWidth(12, 5600);
            sheet13.setColumnWidth(13, 5600);
            sheet13.setColumnWidth(14, 5600);
            sheet13.setColumnWidth(15, 5600);
            sheet13.setColumnWidth(16, 5600);
            sheet13.setColumnWidth(17, 5600);
            sheet13.setColumnWidth(18, 5600);
            sheet13.setColumnWidth(19, 5600);
            sheet13.setColumnWidth(20, 5600);
            sheet13.setColumnWidth(21, 5600);
            sheet13.setColumnWidth(22, 5600);
            sheet13.setColumnWidth(23, 5600);
            sheet13.setColumnWidth(24, 5600);
            sheet13.setColumnWidth(25, 5600);
            sheet13.setColumnWidth(26, 5600);
            sheet13.setColumnWidth(27, 5600);
            sheet13.setColumnWidth(28, 5600);
            sheet13.setColumnWidth(29, 5600);
            sheet13.setColumnWidth(30, 5600);
            sheet13.setColumnWidth(31, 5600);
            sheet13.setColumnWidth(32, 5600);
            sheet13.setColumnWidth(33, 5600);
            sheet13.setColumnWidth(34, 5600);
            sheet13.setColumnWidth(35, 5600);
            sheet13.setColumnWidth(36, 5600);
            sheet13.setColumnWidth(37, 5600);
            sheet13.setColumnWidth(38, 5600);
            sheet13.setColumnWidth(39, 5600);
            sheet13.setColumnWidth(40, 5600);
            sheet13.setColumnWidth(41, 5600);
            sheet13.setColumnWidth(42, 5600);
            sheet13.setColumnWidth(43, 5600);
            sheet13.setColumnWidth(44, 5600);
            sheet13.setColumnWidth(45, 5600);
            sheet13.setColumnWidth(46, 5600);
            sheet13.setColumnWidth(47, 5600);
            sheet13.setColumnWidth(48, 5600);
            sheet13.setColumnWidth(48, 5600);
            sheet13.setColumnWidth(50, 5600);
            sheet13.setColumnWidth(51, 5600);
            sheet13.setColumnWidth(52, 5600);
            sheet13.setColumnWidth(53, 5600);
            sheet13.setColumnWidth(54, 5600);
            sheet13.setColumnWidth(55, 5600);
            sheet13.setColumnWidth(56, 5600);
            sheet13.setColumnWidth(57, 5600);
            sheet13.setColumnWidth(58, 5600);
            sheet13.setColumnWidth(59, 5600);
            sheet13.setColumnWidth(60, 5600);
            sheet13.setColumnWidth(61, 5600);
            sheet13.setColumnWidth(62, 5600);
            sheet13.setColumnWidth(63, 5600);
            sheet13.setColumnWidth(64, 5600);
            sheet13.setColumnWidth(65, 5600);
            sheet13.setColumnWidth(66, 5600);
            sheet13.setColumnWidth(67, 5600);
            sheet13.setColumnWidth(68, 5600);
            sheet13.setColumnWidth(69, 5600);
            sheet13.setColumnWidth(70, 5600);
            sheet13.setColumnWidth(71, 5600);
            sheet13.setColumnWidth(72, 5600);
            sheet13.setColumnWidth(73, 5600);
            sheet13.setColumnWidth(74, 5600);
            sheet13.setColumnWidth(75, 5600);
            sheet13.setColumnWidth(76, 5600);
            sheet13.setColumnWidth(77, 5600);
            sheet13.setColumnWidth(78, 5600);
            sheet13.setColumnWidth(79, 5600);
            sheet13.setColumnWidth(80, 5600);
            sheet13.setColumnWidth(81, 5600);
            sheet13.setColumnWidth(82, 5600);
            sheet13.setColumnWidth(83, 5600);
            sheet13.setColumnWidth(84, 5600);
            sheet13.setColumnWidth(85, 5600);
            sheet13.setColumnWidth(86, 5600);
            sheet13.setColumnWidth(87, 5600);

            Object[] last13 = new Object[mapping13.length];

            for (int rowIndex = 0; rowIndex < dataList13.size(); rowIndex++) {
                HSSFRow row = sheet13.createRow(rowIndex);
                Object data = dataList13.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping13.length; colIndex++) {
                    String propertyName = mapping13[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last13[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 14 : 13-휴대폰(모바일개통)
             ***********************************/

            HSSFSheet sheet14 = wb.createSheet("13-휴대폰(모바일개통)");

            sheet14.setColumnWidth(0, 5600);
            sheet14.setColumnWidth(1, 5600);
            sheet14.setColumnWidth(2, 5600);
            sheet14.setColumnWidth(3, 5600);
            sheet14.setColumnWidth(4, 5600);
            sheet14.setColumnWidth(5, 5600);
            sheet14.setColumnWidth(6, 5600);
            sheet14.setColumnWidth(7, 5600);
            sheet14.setColumnWidth(8, 5600);
            sheet14.setColumnWidth(9, 5600);
            sheet14.setColumnWidth(10, 5600);
            sheet14.setColumnWidth(11, 5600);
            sheet14.setColumnWidth(12, 5600);
            sheet14.setColumnWidth(13, 5600);
            sheet14.setColumnWidth(14, 5600);
            sheet14.setColumnWidth(15, 5600);
            sheet14.setColumnWidth(16, 5600);
            sheet14.setColumnWidth(17, 5600);
            sheet14.setColumnWidth(18, 5600);
            sheet14.setColumnWidth(19, 5600);
            sheet14.setColumnWidth(20, 5600);
            sheet14.setColumnWidth(21, 5600);
            sheet14.setColumnWidth(22, 5600);
            sheet14.setColumnWidth(23, 5600);
            sheet14.setColumnWidth(24, 5600);
            sheet14.setColumnWidth(25, 5600);
            sheet14.setColumnWidth(26, 5600);
            sheet14.setColumnWidth(27, 5600);
            sheet14.setColumnWidth(28, 5600);
            sheet14.setColumnWidth(29, 5600);
            sheet14.setColumnWidth(30, 5600);
            sheet14.setColumnWidth(31, 5600);
            sheet14.setColumnWidth(32, 5600);
            sheet14.setColumnWidth(33, 5600);
            sheet14.setColumnWidth(34, 5600);
            sheet14.setColumnWidth(35, 5600);
            sheet14.setColumnWidth(36, 5600);
            sheet14.setColumnWidth(37, 5600);
            sheet14.setColumnWidth(38, 5600);
            sheet14.setColumnWidth(39, 5600);
            sheet14.setColumnWidth(40, 5600);
            sheet14.setColumnWidth(41, 5600);
            sheet14.setColumnWidth(42, 5600);
            sheet14.setColumnWidth(43, 5600);
            sheet14.setColumnWidth(44, 5600);
            sheet14.setColumnWidth(45, 5600);
            sheet14.setColumnWidth(46, 5600);
            sheet14.setColumnWidth(47, 5600);
            sheet14.setColumnWidth(48, 5600);
            sheet14.setColumnWidth(48, 5600);
            sheet14.setColumnWidth(50, 5600);
            sheet14.setColumnWidth(51, 5600);
            sheet14.setColumnWidth(52, 5600);
            sheet14.setColumnWidth(53, 5600);
            sheet14.setColumnWidth(54, 5600);
            sheet14.setColumnWidth(55, 5600);
            sheet14.setColumnWidth(56, 5600);
            sheet14.setColumnWidth(57, 5600);
            sheet14.setColumnWidth(58, 5600);
            sheet14.setColumnWidth(59, 5600);
            sheet14.setColumnWidth(60, 5600);
            sheet14.setColumnWidth(61, 5600);
            sheet14.setColumnWidth(62, 5600);
            sheet14.setColumnWidth(63, 5600);
            sheet14.setColumnWidth(64, 5600);
            sheet14.setColumnWidth(65, 5600);
            sheet14.setColumnWidth(66, 5600);
            sheet14.setColumnWidth(67, 5600);
            sheet14.setColumnWidth(68, 5600);
            sheet14.setColumnWidth(69, 5600);
            sheet14.setColumnWidth(70, 5600);
            sheet14.setColumnWidth(71, 5600);
            sheet14.setColumnWidth(72, 5600);
            sheet14.setColumnWidth(73, 5600);
            sheet14.setColumnWidth(74, 5600);
            sheet14.setColumnWidth(75, 5600);
            sheet14.setColumnWidth(76, 5600);
            sheet14.setColumnWidth(77, 5600);
            sheet14.setColumnWidth(78, 5600);
            sheet14.setColumnWidth(79, 5600);
            sheet14.setColumnWidth(80, 5600);
            sheet14.setColumnWidth(81, 5600);
            sheet14.setColumnWidth(82, 5600);
            sheet14.setColumnWidth(83, 5600);
            sheet14.setColumnWidth(84, 5600);
            sheet14.setColumnWidth(85, 5600);
            sheet14.setColumnWidth(86, 5600);
            sheet14.setColumnWidth(87, 5600);

            Object[] last14 = new Object[mapping14.length];

            for (int rowIndex = 0; rowIndex < dataList14.size(); rowIndex++) {
                HSSFRow row = sheet14.createRow(rowIndex);
                Object data = dataList14.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping14.length; colIndex++) {
                    String propertyName = mapping14[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last14[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 15 : 31-렌탈물품대여 서비스(정수기, 비데, 공기청정기 등)
             ***********************************/

            HSSFSheet sheet15 = wb.createSheet("31-렌탈물품대여 서비스(정수기, 비데, 공기청정기 등)");

            sheet15.setColumnWidth(0, 5600);
            sheet15.setColumnWidth(1, 5600);
            sheet15.setColumnWidth(2, 5600);
            sheet15.setColumnWidth(3, 5600);
            sheet15.setColumnWidth(4, 5600);
            sheet15.setColumnWidth(5, 5600);
            sheet15.setColumnWidth(6, 5600);
            sheet15.setColumnWidth(7, 5600);
            sheet15.setColumnWidth(8, 5600);
            sheet15.setColumnWidth(9, 5600);
            sheet15.setColumnWidth(10, 5600);
            sheet15.setColumnWidth(11, 5600);
            sheet15.setColumnWidth(12, 5600);
            sheet15.setColumnWidth(13, 5600);
            sheet15.setColumnWidth(14, 5600);
            sheet15.setColumnWidth(15, 5600);
            sheet15.setColumnWidth(16, 5600);
            sheet15.setColumnWidth(17, 5600);
            sheet15.setColumnWidth(18, 5600);
            sheet15.setColumnWidth(19, 5600);
            sheet15.setColumnWidth(20, 5600);
            sheet15.setColumnWidth(21, 5600);
            sheet15.setColumnWidth(22, 5600);
            sheet15.setColumnWidth(23, 5600);
            sheet15.setColumnWidth(24, 5600);
            sheet15.setColumnWidth(25, 5600);
            sheet15.setColumnWidth(26, 5600);
            sheet15.setColumnWidth(27, 5600);
            sheet15.setColumnWidth(28, 5600);
            sheet15.setColumnWidth(29, 5600);
            sheet15.setColumnWidth(30, 5600);
            sheet15.setColumnWidth(31, 5600);
            sheet15.setColumnWidth(32, 5600);
            sheet15.setColumnWidth(33, 5600);
            sheet15.setColumnWidth(34, 5600);
            sheet15.setColumnWidth(35, 5600);
            sheet15.setColumnWidth(36, 5600);
            sheet15.setColumnWidth(37, 5600);
            sheet15.setColumnWidth(38, 5600);
            sheet15.setColumnWidth(39, 5600);
            sheet15.setColumnWidth(40, 5600);
            sheet15.setColumnWidth(41, 5600);
            sheet15.setColumnWidth(42, 5600);
            sheet15.setColumnWidth(43, 5600);
            sheet15.setColumnWidth(44, 5600);
            sheet15.setColumnWidth(45, 5600);
            sheet15.setColumnWidth(46, 5600);
            sheet15.setColumnWidth(47, 5600);
            sheet15.setColumnWidth(48, 5600);
            sheet15.setColumnWidth(48, 5600);
            sheet15.setColumnWidth(50, 5600);
            sheet15.setColumnWidth(51, 5600);
            sheet15.setColumnWidth(52, 5600);
            sheet15.setColumnWidth(53, 5600);
            sheet15.setColumnWidth(54, 5600);
            sheet15.setColumnWidth(55, 5600);
            sheet15.setColumnWidth(56, 5600);
            sheet15.setColumnWidth(57, 5600);
            sheet15.setColumnWidth(58, 5600);
            sheet15.setColumnWidth(59, 5600);
            sheet15.setColumnWidth(60, 5600);
            sheet15.setColumnWidth(61, 5600);
            sheet15.setColumnWidth(62, 5600);
            sheet15.setColumnWidth(63, 5600);
            sheet15.setColumnWidth(64, 5600);
            sheet15.setColumnWidth(65, 5600);
            sheet15.setColumnWidth(66, 5600);
            sheet15.setColumnWidth(67, 5600);
            sheet15.setColumnWidth(68, 5600);
            sheet15.setColumnWidth(69, 5600);
            sheet15.setColumnWidth(70, 5600);
            sheet15.setColumnWidth(71, 5600);
            sheet15.setColumnWidth(72, 5600);
            sheet15.setColumnWidth(73, 5600);
            sheet15.setColumnWidth(74, 5600);
            sheet15.setColumnWidth(75, 5600);
            sheet15.setColumnWidth(76, 5600);
            sheet15.setColumnWidth(77, 5600);
            sheet15.setColumnWidth(78, 5600);
            sheet15.setColumnWidth(79, 5600);
            sheet15.setColumnWidth(80, 5600);
            sheet15.setColumnWidth(81, 5600);
            sheet15.setColumnWidth(82, 5600);
            sheet15.setColumnWidth(83, 5600);
            sheet15.setColumnWidth(84, 5600);
            sheet15.setColumnWidth(85, 5600);
            sheet15.setColumnWidth(86, 5600);
            sheet15.setColumnWidth(87, 5600);

            Object[] last15 = new Object[mapping15.length];

            for (int rowIndex = 0; rowIndex < dataList15.size(); rowIndex++) {
                HSSFRow row = sheet15.createRow(rowIndex);
                Object data = dataList15.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping15.length; colIndex++) {
                    String propertyName = mapping15[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last15[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 16 : 사은품|경품
             ***********************************/

            HSSFSheet sheet16 = wb.createSheet("사은품|경품");

            sheet16.setColumnWidth(0, 5600);
            sheet16.setColumnWidth(1, 5600);
            sheet16.setColumnWidth(2, 5600);
            sheet16.setColumnWidth(3, 5600);
            sheet16.setColumnWidth(4, 5600);
            sheet16.setColumnWidth(5, 5600);
            sheet16.setColumnWidth(6, 5600);
            sheet16.setColumnWidth(7, 5600);
            sheet16.setColumnWidth(8, 5600);
            sheet16.setColumnWidth(9, 5600);
            sheet16.setColumnWidth(10, 5600);
            sheet16.setColumnWidth(11, 5600);
            sheet16.setColumnWidth(12, 5600);
            sheet16.setColumnWidth(13, 5600);
            sheet16.setColumnWidth(14, 5600);
            sheet16.setColumnWidth(15, 5600);



            Object[] last16 = new Object[mapping16.length];

            for (int rowIndex = 0; rowIndex < dataList16.size(); rowIndex++) {
                HSSFRow row = sheet16.createRow(rowIndex);
                Object data = dataList16.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping16.length; colIndex++) {
                    String propertyName = mapping16[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last16[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            /***********************************
             * sheet 17 : 부속품
             ***********************************/

            HSSFSheet sheet17 = wb.createSheet("부속품");

            sheet17.setColumnWidth(0, 5600);
            sheet17.setColumnWidth(1, 5600);
            sheet17.setColumnWidth(2, 5600);
            sheet17.setColumnWidth(3, 5600);
            sheet17.setColumnWidth(4, 5600);
            sheet17.setColumnWidth(5, 5600);
            sheet17.setColumnWidth(6, 5600);
            sheet17.setColumnWidth(7, 5600);
            sheet17.setColumnWidth(8, 5600);
            sheet17.setColumnWidth(9, 5600);
            sheet17.setColumnWidth(10, 5600);
            sheet17.setColumnWidth(11, 5600);
            sheet17.setColumnWidth(12, 5600);
            sheet17.setColumnWidth(13, 5600);
            sheet17.setColumnWidth(14, 5600);
            sheet17.setColumnWidth(15, 5600);
            sheet17.setColumnWidth(16, 5600);
            sheet17.setColumnWidth(17, 5600);
            sheet17.setColumnWidth(18, 5600);

            Object[] last17 = new Object[mapping17.length];

            for (int rowIndex = 0; rowIndex < dataList17.size(); rowIndex++) {
                HSSFRow row = sheet17.createRow(rowIndex);
                Object data = dataList17.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping17.length; colIndex++) {
                    String propertyName = mapping17[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last17[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }

            /***********************************
             * sheet 18 : 공정위 항목 미등록 상품
             ***********************************/

            HSSFSheet sheet18 = wb.createSheet("공정위 항목 미등록 상품");

            sheet15.setColumnWidth(0, 5600);
            sheet15.setColumnWidth(1, 5600);
            sheet15.setColumnWidth(2, 5600);
            sheet15.setColumnWidth(3, 5600);
            sheet15.setColumnWidth(4, 5600);
            sheet15.setColumnWidth(5, 5600);
            sheet15.setColumnWidth(6, 5600);
            sheet15.setColumnWidth(7, 5600);
            sheet15.setColumnWidth(8, 5600);
            sheet15.setColumnWidth(9, 5600);
            sheet15.setColumnWidth(10, 5600);
            sheet15.setColumnWidth(11, 5600);
            sheet15.setColumnWidth(12, 5600);
            sheet15.setColumnWidth(13, 5600);
            sheet15.setColumnWidth(14, 5600);
            sheet15.setColumnWidth(15, 5600);
            sheet15.setColumnWidth(16, 5600);
            sheet15.setColumnWidth(17, 5600);
            sheet15.setColumnWidth(18, 5600);
            sheet15.setColumnWidth(19, 5600);
            sheet15.setColumnWidth(20, 5600);
            sheet15.setColumnWidth(21, 5600);
            sheet15.setColumnWidth(22, 5600);
            sheet15.setColumnWidth(23, 5600);
            sheet15.setColumnWidth(24, 5600);
            sheet15.setColumnWidth(25, 5600);
            sheet15.setColumnWidth(26, 5600);
            sheet15.setColumnWidth(27, 5600);
            sheet15.setColumnWidth(28, 5600);
            sheet15.setColumnWidth(29, 5600);
            sheet15.setColumnWidth(30, 5600);
            sheet15.setColumnWidth(31, 5600);
            sheet15.setColumnWidth(32, 5600);
            sheet15.setColumnWidth(33, 5600);
            sheet15.setColumnWidth(34, 5600);
            sheet15.setColumnWidth(35, 5600);
            sheet15.setColumnWidth(36, 5600);
            sheet15.setColumnWidth(37, 5600);
            sheet15.setColumnWidth(38, 5600);
            sheet15.setColumnWidth(39, 5600);
            sheet15.setColumnWidth(40, 5600);
            sheet15.setColumnWidth(41, 5600);
            sheet15.setColumnWidth(42, 5600);
            sheet15.setColumnWidth(43, 5600);
            sheet15.setColumnWidth(44, 5600);
            sheet15.setColumnWidth(45, 5600);
            sheet15.setColumnWidth(46, 5600);
            sheet15.setColumnWidth(47, 5600);
            sheet15.setColumnWidth(48, 5600);
            sheet15.setColumnWidth(48, 5600);
            sheet15.setColumnWidth(50, 5600);
            sheet15.setColumnWidth(51, 5600);
            sheet15.setColumnWidth(52, 5600);
            sheet15.setColumnWidth(53, 5600);
            sheet15.setColumnWidth(54, 5600);
            sheet15.setColumnWidth(55, 5600);
            sheet15.setColumnWidth(56, 5600);
            sheet15.setColumnWidth(57, 5600);
            sheet15.setColumnWidth(58, 5600);
            sheet15.setColumnWidth(59, 5600);
            sheet15.setColumnWidth(60, 5600);
            sheet15.setColumnWidth(61, 5600);
            sheet15.setColumnWidth(62, 5600);
            sheet15.setColumnWidth(63, 5600);
            sheet15.setColumnWidth(64, 5600);
            sheet15.setColumnWidth(65, 5600);
            sheet15.setColumnWidth(66, 5600);


            Object[] last18 = new Object[mapping18.length];

            for (int rowIndex = 0; rowIndex < dataList18.size(); rowIndex++) {
                HSSFRow row = sheet18.createRow(rowIndex);
                Object data = dataList18.get(rowIndex);
                filterPrivacyInfo(data);

                for (int colIndex = 0; colIndex < mapping18.length; colIndex++) {
                    String propertyName = mapping18[colIndex];

                    Object value = null;
                    HSSFCell cell = row.createCell(colIndex);
                    cell.setCellStyle(cellStyle0);

                    if (propertyName.matches("[a-zA-Z][a-zA-Z0-9_]*\\.[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*")) {
                        try {
                            value = PropertyUtils.getNestedProperty(data, propertyName);
                        } catch (NestedNullException ex) {
                            // 프로퍼티가 Null 일때는 null 로 남겨둠.
                        }
                    } else {
                        value = PropertyUtils.getSimpleProperty(data, propertyName);
                    }

                    if (value == null) {
                        last18[colIndex] = null;
                        continue;
                    }

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(value.toString());

                }
            }



            FileOutputStream fileout = new FileOutputStream(targetFile);
            wb.write(fileout);
            fileout.close();
            IOUtils.closeQuietly(wb);
        } catch (Exception ex) {
            // ex.printStackTrace();
            throw new IllegalArgumentException(ex);
        }
    }


    private void filterPrivacyInfo(Object data) {

        /*if (privacyInfoFilter == null || privacyInfoPermissionHolder == null) {
            return;
        }

        privacyInfoFilter.filter(data, privacyInfoPermissionHolder.getPermissions());*/
    }


    private static void makeTitleHeader(String title, HSSFSheet sheet) {

        HSSFRow row = sheet.createRow(0);

        Cell cell = row.createCell(0);

        cell.setCellValue(title);
    }


    private static void makeColumnNames(String[][] mapping, HSSFSheet sheet) {

        HSSFRow row = sheet.createRow(1);
        for (int i = 0; i < mapping.length; i++) {
            row.createCell(i).setCellValue(mapping[i][1]);
        }
    }


}
