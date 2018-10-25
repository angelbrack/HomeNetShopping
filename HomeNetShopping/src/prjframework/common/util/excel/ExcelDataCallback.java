package prjframework.common.util.excel;

import java.lang.reflect.InvocationTargetException;

/**
 * ExcelUtil에서 엑셀 파일을 읽어 각 행(row) 별로 callback 하기 위한 인터페이스
 * 
 * ExcelCallback 보다 더 자세한 정보가 필요할때 사용한다.
 * 
 * @author 안지성
 * @version 1.0
 * 
 */
/** 
 * <p>ExcelUtil에서 엑셀 파일을 읽어 각 행(row) 별로 callback 하기 위한 인터페이스</p>
 * <p>ExcelCallback 보다 더 자세한 정보가 필요할때 사용한다.</p>
 * 
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 10. 16.</li>
 * </ul>
 */
public interface ExcelDataCallback {
	public void rowAt(ExcelHeader header, int rowNo, Object[] cellDatas) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
}
