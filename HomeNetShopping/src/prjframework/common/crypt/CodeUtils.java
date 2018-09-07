package prjframework.common.crypt;

import java.io.ByteArrayOutputStream;

import sun.misc.BASE64Encoder;

public class CodeUtils {	
	public static String fromHexBase64(String str, String charset) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		for (int i = 0, iend = str.length(); i < iend; i += 2)
			os.write(Integer.parseInt(str.substring(i, i + 2), 16));
		return new BASE64Encoder().encode(os.toByteArray());
	}
}
