package prjframework.common.dataaccess.util;

import org.apache.commons.collections.map.ListOrderedMap;

public class DataMap extends ListOrderedMap {
	private static final long serialVersionUID = 1L;
	public Object put(Object key, Object value) {
		return super.put(DataUtil.convertCamelCase((String)key), value);
	}

}
