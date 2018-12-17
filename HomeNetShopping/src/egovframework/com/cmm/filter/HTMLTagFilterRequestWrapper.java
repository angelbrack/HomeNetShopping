
package egovframework.com.cmm.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import prjframework.common.util.XssHtmlEscape;


public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {
	private final Log log = LogFactory.getLog(this.getClass());
	private String path = null;
	
	
	@Resource(name = "configProperties")
	protected Properties configProperties;
	
	
	public HTMLTagFilterRequestWrapper(HttpServletRequest request) throws IOException {
		super((HttpServletRequest)request);
		this.path = ((HttpServletRequest)request).getRequestURI();
		log.debug("HTMLTagFilterRequestWrapper path ["+path+"]");
		//	log.debug("aa 20180427"+configProperties.getProperty("HTML.FILTER.EXCLUDE.FIELD"));
		/*
		String characterEncoding = request.getCharacterEncoding();
		if(StringUtils.isEmpty(characterEncoding)) {
			//characterEncoding = StandardCharsets.UTF_8.name();
		}
		this.encoding = Charset.forName(characterEncoding);

		// Convert InputStream data to byte array and store it to this wrapper
		// instance.
		try {
			InputStream inputStream = request.getInputStream();
			this.bodyData = IOUtils.toByteArray(inputStream);
		} catch(IOException e) {
			throw e;
		}
		*/
	}

	@Override
	public String getParameter(String paramName) {
		String value = super.getParameter(paramName);
		return doFilter(paramName, value);
	}

	@Override
	public String[] getParameterValues(String paramName) {
		String values[] = super.getParameterValues(paramName);
		if (values == null) {
			return values;
		}
		for (int index = 0; index < values.length; index++) {
			values[index] = doFilter(paramName, values[index]);
		}
		return values;
	}
	/* java1.6 */
	/*@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getParameterMap() {
		Map<String, Object> paramMap = super.getParameterMap();
		Map<String, Object> newFilteredParamMap = new HashMap<String, Object>();

		Set<Map.Entry<String, Object>> entries = paramMap.entrySet();
		for (Map.Entry<String, Object> entry : entries) {
			String paramName = entry.getKey();
			Object[] valueObj = (Object[])entry.getValue();
			String[] filteredValue = new String[valueObj.length];
			for (int index = 0; index < valueObj.length; index++) {
				filteredValue[index] = doFilter(paramName, String.valueOf(valueObj[index]));
			}
			
			newFilteredParamMap.put(entry.getKey(), filteredValue);
		}

		return newFilteredParamMap;
	}*/
	/* java1.7 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> paramMap = super.getParameterMap();
		Map<String, String[]> newFilteredParamMap = new HashMap<String, String[]>();

		Set<Map.Entry<String, String[]>> entries = paramMap.entrySet();
		for (Map.Entry<String, String[]> entry : entries) {
			String paramName = entry.getKey();
			Object[] valueObj = (Object[])entry.getValue();
			String[] filteredValue = new String[valueObj.length];
			for (int index = 0; index < valueObj.length; index++) {
				filteredValue[index] = doFilter(paramName, String.valueOf(valueObj[index]));
			}
			
			newFilteredParamMap.put(entry.getKey(), filteredValue);
		}

		return newFilteredParamMap;
	}
	/**
	 * @param paramName String
	 * @param value String
	 * @return String
	 */
	private String doFilter(String paramName, String value) {
		log.debug("param ["+paramName+"] value ["+value+"]");
		/*if(value == null) {
			return value;
		}
		
		String[] excludeParam = null;
		String[] webEditParam = null;
		DataConfiguration conf = null;
		
		try {
			conf = new DataConfiguration(new PropertiesConfiguration("/prjctfrmwrk/common/properties/config.properties"));
			excludeParam = conf.getStringArray("HTML.FILTER.EXCLUDE.FIELD");
			webEditParam = conf.getStringArray("HTML.FILTER.WEBEDIT.FIELD");
		} catch(ConfigurationException e) {
			log.error("HTMLTagFilter conf error");
		}

		if(excludeParam != null && excludeParam.length > 0) {
			for(int k = 0; k < excludeParam.length; k++) {
				if(StringUtils.contains(paramName, excludeParam[k])) {
					log.debug("HTMLTagFilter excludeParam :" + paramName);
					return value;
				}
			}
		}
		
		if(webEditParam != null && webEditParam.length > 0) {
			for(int k = 0; k < webEditParam.length; k++) {
				if(StringUtils.contains(paramName, webEditParam[k])) {
					log.debug("HTMLTagFilter webEditParam param :" + paramName);
					log.debug("HTMLTagFilter webEditParam value :" + value);
					return Jsoup.clean(value, Whitelist.basic());
				}
			}
		}
		
		
		
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("'", "&#39;").replaceAll("\"", "&#40;") ;
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		return value;*/
		XssHtmlEscape xssHtmlEscape = XssHtmlEscape.getInstance();
		value = xssHtmlEscape.escapeHTML(paramName, value);
		return value;
	}
	
	
}
