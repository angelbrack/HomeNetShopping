package prjframework.common.util.excel;

import java.util.List;

public class ListDataHolder implements DataHolder {

	private List<?> dataList;
	private int index = 0;

	public ListDataHolder(List<?> dataList) {
		this.dataList = dataList;

	}

	//@Override
	public boolean hasNext() {
		return dataList.size() > index;
	}

	//@Override
	public Object next() {
		return dataList.get(index++);
	}
}