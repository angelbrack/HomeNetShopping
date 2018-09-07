package prjframework.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 0. Project		: lmsproject
 *
 * 1. FileName		: RequestWrapper.java
 * 2. Package		: prjframework.common.util
 * 3. Commnet		: RequestParameter 값이 controller로 전달되기 전에 적절히 XSS(Cross Site Scripting) 방지
 * 4. Author		: king
 * 5. Creation Date	: 2014. 5. 13. 오후 2:31:04
 * 6. History		: 
 *                    Name		: Date          : Reason	: Type
 *                   ------------------------------------------------------
 *                    king	: 2014. 5. 13.		:			: CREATE
 */
public class RequestWrapper extends HttpServletRequestWrapper {

	public RequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String parameter) {

		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		//value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		//value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		//value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("<script", "&gt;script");
		value = value.replaceAll("<iframe", "&gt;iframe");
		value = value.replaceAll("<form", "&gt;form");
		value = value.replaceAll("<embed", "&gt;embed");
		value = value.replaceAll("<object", "&gt;object");
		value = value.replaceAll("<applet", "&gt;applet");
		return value;
	}
}
