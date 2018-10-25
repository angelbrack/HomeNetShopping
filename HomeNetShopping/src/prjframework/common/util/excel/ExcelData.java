package prjframework.common.util.excel;

import java.util.List;

/** 
 * <p>ExcelUtil에서 엑셀 파일을 읽어낸 정보를 모아서 제공하기ㅣ 위한 holder 객체</p>
 * 
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 10. 16.</li>
 * </ul>
 */
@SuppressWarnings("unchecked")
public class ExcelData {
	ExcelHeader excelHeader;

	List data;

	public ExcelData(ExcelHeader header, List res) {
		this.excelHeader = header;
		this.data = res;
	}

	public ExcelHeader getExcelHeader() {
		return excelHeader;
	}

	public void setExcelHeader(ExcelHeader excelHeader) {
		this.excelHeader = excelHeader;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

}
