package homenet.shop.common.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class Expression {
	public static boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			return StringUtils.isEmpty((String) obj);
		} else if (obj instanceof List) {
			return CollectionUtils.isEmpty((List) obj);
		} else if (obj instanceof Map) {
			return MapUtils.isEmpty((Map) obj);
		} else if (obj instanceof Object[]) {
			return ArrayUtils.isEmpty((Object[]) obj);
		} else {
			return obj == null;
		}
	}

	public static boolean isNotEmpty(Object obj) {
		if (obj instanceof String) {
			return StringUtils.isNotEmpty((String) obj);
		} else if (obj instanceof List) {
			return CollectionUtils.isNotEmpty((List) obj);
		} else if (obj instanceof Map) {
			return MapUtils.isNotEmpty((Map) obj);
		} else if (obj instanceof Object[]) {
			return ArrayUtils.isNotEmpty((Object[]) obj);
		} else {
			return obj != null;
		}
	}
}
