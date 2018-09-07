/*
 * Copyright 2008-2009 MOPAS(Ministry of Public Administration and Security).
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class HTMLTagFilter implements Filter {
	
	@SuppressWarnings("unused")
	private FilterConfig config;
	
	private final Log log = LogFactory.getLog(this.getClass());

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(excludeUrl(request)) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest)request), response);
		}
	}

	private boolean excludeUrl(ServletRequest request) {

		String uri = ((HttpServletRequest)request).getRequestURI();
		String[] excludeUrl = null;
		DataConfiguration conf = null;
		
		try {
			conf = new DataConfiguration(new PropertiesConfiguration("/prjframe/common/properties/config.properties"));
			excludeUrl = conf.getStringArray("HTML.TAG.EXCLUDE.URL");
		} catch(ConfigurationException e) {
			log.error("HTMLTagFilter conf error");
		}

		if(excludeUrl != null && excludeUrl.length > 0) {
			for(int k = 0; k < excludeUrl.length; k++) {
				if(StringUtils.contains(uri, excludeUrl[k])) {
					log.debug("HTMLTagFilter excludeUrl :" + uri);
					return true;
				}
			}
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {

	}
}
