package prjframework.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 0. Project		: prjproject
 *
 * 1. FileName		: CrossScriptingFilter.java
 * 2. Package		: prjframework.common.util
 * 3. Commnet		: XSS(Cross Site Scripting) 방지 필터
 * 4. Author		: king
 * 5. Creation Date	: 2014. 5. 13. 오후 2:30:12
 * 6. History		: 
 *                    Name		: Date          : Reason	: Type
 *                   ------------------------------------------------------
 *                    king	: 2014. 5. 13.		:			: CREATE
 */
public class CrossScriptingFilter implements Filter {
 
    private FilterConfig filterConfig;
     
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
//    	request.getRemoteAddr()
        /*
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        if (req.getScheme().equals("https")) {
            String url = "http://" + req.getServerName()
                    + req.getContextPath() + req.getServletPath();
            if (req.getPathInfo() != null) {
                url += req.getPathInfo();
            }
            resp.sendRedirect(url);
        } else {
        	
            chain.doFilter(request, response);
        }
        */
    }
 
    public void destroy() {
        this.filterConfig = null;
    }
}