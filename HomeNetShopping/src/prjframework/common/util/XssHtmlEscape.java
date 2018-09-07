package prjframework.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class XssHtmlEscape {
	private final Log log = LogFactory.getLog(this.getClass());
	
//	private static XssHtmlEscape xssHtmlEscape = null;
	
	private String[] excludeParam = null;
	private String[] webEditParam = null;
	private DataConfiguration conf = null;
	
	private static class LazyHolder {
		static final XssHtmlEscape xssHtmlEscape = new XssHtmlEscape(); 
	}
	
	public static XssHtmlEscape getInstance() {
		return LazyHolder.xssHtmlEscape ;
	}
	
	public XssHtmlEscape() {
		try {
			conf = new DataConfiguration(new PropertiesConfiguration("/prjframe/common/properties/config.properties"));
			excludeParam = conf.getStringArray("HTML.FILTER.EXCLUDE.FIELD");
			webEditParam = conf.getStringArray("HTML.FILTER.WEBEDIT.FIELD");
		} catch(ConfigurationException e) {
			log.error("XssHtmlEscape conf error");
		}
	}
	
	/**
	 * @param paramName String
	 * @param value String
	 * @return String
	 */
	public String escapeHTML(String paramName, String value) {
		log.debug("param ["+paramName+"] value ["+value+"]");
		if(value == null) {
			return value;
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
		return value;
	}
}
