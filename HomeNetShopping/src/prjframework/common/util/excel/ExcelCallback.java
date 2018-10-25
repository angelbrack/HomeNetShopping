package prjframework.common.util.excel;

/** 
 * <p>ExcelUtil에서 엑셀 파일을 읽어 각 행(row) 별로 callback 하기 위한 인터페이스</p>
 * 
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 12.</li>
 * </ul>
 */
public interface ExcelCallback {
	public void rowAt(int rowNo, Object[] cellDatas);
}
