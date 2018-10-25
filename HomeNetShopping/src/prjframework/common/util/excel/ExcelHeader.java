package prjframework.common.util.excel;

import java.util.Arrays;

/** 
 * <p>Excel에서 header로 지정된 라인들에 대한 정보를 가지는 객체</p>
 * 
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 10. 16.</li>
 * </ul>
 */
public class ExcelHeader {
	String[][] headers;

	/**
	 * 간단한 생성자 설명 (short description about constructor) 자세한 생성자 설명 (full description about the constructor)
	 * 
	 * @param headers2
	 */
	public ExcelHeader(String[][] headers) {
		this.headers = headers;
	}

	/**
	 * 헤더명을 기준으로 column 위치를 파악하여 반환한다.
	 */
	public int getColumnIndex(int columnRowIndex, String columnName) {
		for (int i = 0; i < headers[columnRowIndex].length; i++) {
			if (columnName.equals(headers[columnRowIndex][i])) {
				return i;
			}
		}
		return -1;
	}

	public String getHeader(int row, int column) {
		return headers[row][column];
	}

	public String[] getHeaderRow(int row) {
		return headers[row];
	}

	@Override
	public String toString() {
		return "ExcelHeader [headers=" + Arrays.toString(headers) + "]";
	}

	/**
	 * 간단한 메서드 설명 (short description about method) 자세한 메서드 설명 (short description about method)
	 * 
	 * @param cellDatas
	 * @param string
	 * @return
	 */
	public Object getColumnMatchingData(Object[] cellDatas, int columnRowIndex, String columnName) {
		int colIndex = getColumnIndex(columnRowIndex, columnName);
		return cellDatas[colIndex];
	}

}
