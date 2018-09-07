package prjframework.common.dataaccess.util;

public class DataUtil {
	
	public static String convertCamelCase(String strValue) {
		if ((strValue.indexOf(95) < 0)
				&& (Character.isLowerCase(strValue.charAt(0)))) {
			return strValue;
		}
		StringBuilder sb = new StringBuilder();
		boolean bChk = false;
		int len = strValue.length();

		for (int i = 0; i < len; ++i) {
			char ch = strValue.charAt(i);
			if (ch == '_') {
				bChk = true;
			} else if (bChk) {
				sb.append(Character.toUpperCase(ch));
				bChk = false;
			} else {
				sb.append(Character.toLowerCase(ch));
			}
		}
		return sb.toString();
	}

}
