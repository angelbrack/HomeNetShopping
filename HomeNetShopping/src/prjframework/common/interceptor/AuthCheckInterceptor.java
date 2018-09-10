package prjframework.common.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.org.apache.bcel.internal.generic.LLOAD;

import egovframework.com.cmm.EgovMessageSource;
import homenet.shop.login.service.UserLoginVO;
import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.exception.UserAuthNotExistsException;
import prjframework.common.interceptor.service.UserAuthCheckService;
import prjframework.common.util.SessionUtil;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	/** 사용자 권한 체크 서비스 **/
	private UserAuthCheckService checkService;
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	private Set<String> permittedURL;
	
	private Set<String> allowURL;
	
	public void setPermittedURL(Set<String> permittedURL) {
		this.permittedURL = permittedURL;
	}
	
	public void setAllowURL(Set<String> allowURL) {
		this.allowURL = allowURL;
	}
	
	public void setCheckService(UserAuthCheckService checkService) {
		this.checkService = checkService;
	}
	
	/**
	 * 로그인 정보가 존재하지 않으면 로그인 페이지로 되돌린다.
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {	

		HttpSession session = request.getSession(true);
		String optrAuthNo = (String) session.getAttribute("optrAuthNo");
		
		UserLoginVO userLoginVO = (UserLoginVO) session.getAttribute("UserLoginVO");
		
		String requestURI = request.getRequestURI();
		boolean isPermittedURL = false; 
		boolean isAllowURL = false; 
		boolean isPass = true; 
		
		if (this.permittedURL != null) {
			for(Iterator<String> it = this.permittedURL.iterator(); it.hasNext();){
				String urlPattern = request.getContextPath() + (String) it.next();
				//log.debug("urlPattern : " + urlPattern);
				if(Pattern.matches(urlPattern, requestURI)){
					// 정규표현식을 이용해서 요청 URI가 허용된 URL에 맞는지 점검함.
					isPermittedURL = true;
				}
			}
		}
		// 허용 URL
		if (this.allowURL != null) {
			for(Iterator<String> it = this.allowURL.iterator(); it.hasNext();){
				String urlPattern = request.getContextPath() + (String) it.next();
				//log.debug("urlPattern : " + urlPattern);
				if(Pattern.matches(urlPattern, requestURI)){
					// 정규표현식을 이용해서 요청 URI가 허용된 URL에 맞는지 점검함.
					isAllowURL = true;
				}
			}
		}
		
		log.debug("permittedURL : " + permittedURL);
		log.debug("RequestURI : " + requestURI);
		log.debug("isPermittedURL : " + isPermittedURL);
		log.debug("allowURL : " + allowURL);
		log.debug("isAllowURL : " + isAllowURL);
		//log.debug("SessionUtil.getUserInfo().getExprYn() : " + SessionUtil.getUserInfo().getExprYn());
		
		if(requestURI.indexOf("/mgnt/") > -1) {
			if( optrAuthNo != null || userLoginVO != null){
				isPass = true;
				// 접근 제한 체크를 하지 않는 Uri에 대해서는 none check
				if (!isPermittedURL) {
					/**  
					 * URL 체크 허용 mgmt url -> support에서 사용할수있게
					 * 단, 기관담당자 권한이 승인상태일때만
					 */
					/* master 나중에 꼭 수정해야함
					if(!isAllowURL) {
						if(requestURI.indexOf("/mgnt/") > -1 && !"Y".equals(SessionUtil.getUserInfo().getExprYn())) {
							session.setAttribute("redirectAuthPath", "/mgnt/home/logoutAction.do");
							throw new UserAuthNotExistsException(egovMessageSource.getMessage("login.notAuth"));
						} else if(requestURI.indexOf("/support/") > -1 && !"2".equals(SessionUtil.getUserInfo().getExprYn())) {
							session.setAttribute("redirectAuthPath", "/support/home/logoutAction.do");
							throw new UserAuthNotExistsException(egovMessageSource.getMessage("login.notAuth"));
						}
					}else{
						if(!("2".equals(SessionUtil.getUserInfo().getExprYn()) || "2".equals(SessionUtil.getUserInfo().getExprYn()))) {
							session.setAttribute("redirectAuthPath", "/support/home/logoutAction.do");
							throw new UserAuthNotExistsException(egovMessageSource.getMessage("login.notAuth"));
						}
					}
					*/
					
					try {
						
						DataMap param = new DataMap();
						param.put("requestUri", requestURI);
						param.put("optrAuthNo", optrAuthNo);
						
						Map<String, String> userAuth = (Map<String, String>)checkService.authCheck(param);
						request.setAttribute("USER_PGM_AUTH", userAuth);
						log.debug("LOGIN_CHECK_INTERCEPTOR : " + userAuth);
					} catch (UserAuthNotExistsException e) {
						
						if(requestURI.indexOf("/mgnt/") > -1) {
							session.setAttribute("redirectAuthPath", "/mgnt/home/logoutAction.do");
						}
						throw new UserAuthNotExistsException(egovMessageSource.getMessage("login.notAuth"));
					}
					
				}
			} else {
				if(!isPermittedURL){
					session.setAttribute("redirectAuthPath", "/mgnt/home/logoutAction.do");
					throw new UserAuthNotExistsException(egovMessageSource.getMessage("login.notLogin"));
				}else{
					isPass = true;
				}
			}
		}
		
		if(requestURI.indexOf("/user/") > -1) {
			///user/index/home/homeMain.do
		}
		
		if(requestURI.indexOf("/company/") > -1) {
			
		}
		
		if(requestURI.indexOf("/general/") > -1) {
			
		}
		
		
		
		
		
		return isPass;
	}
	
	
}
