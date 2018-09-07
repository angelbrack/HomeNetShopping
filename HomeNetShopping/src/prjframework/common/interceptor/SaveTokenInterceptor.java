package prjframework.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import prjframework.common.taglib.SaveTokenTag;
import prjframework.common.view.PageRedirectView;


/**-----------------------------------------------------------------------
 * Egovframework 2.6
 *------------------------------------------------------------------------
 * @Class SavedTokenInterceptor.java
 * @Description 1. CSRF방지를 위하여  Token 을 비교하는 AOP(Validator)에서 참조해야할 객체를 바인딩 하는 Interceoptor
 *  			2. Controller에서 AlertAndBack을 수행시에 AOP에서 삭제한 Token정보를 복원하는 Interceoptor
 *
 * @author king
 * @since 2014. 12. 03.
 * @version 1.0
 *
 * @Copyright (c) 2014 (주) 하늘연소프트
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일               수정자       수정내용
 * -----------   ---------  -----------------------------------------------
 * 2014. 12. 03. king      최초생성
 */
public class SaveTokenInterceptor extends HandlerInterceptorAdapter {

	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * @Description  CSRF를 사용하기 위하여 AOP에서 request.getParameter객체를 참조 불가능하기 때문에 이를 Interceptor에서 AOP에서 사용할수 있는 객체로 저장한다.
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 *
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
	throws Exception
	{
		logger.debug("## SaveTokenInterceptor preHandle start ");
		//Session정보 획득
		HttpSession session = request.getSession();

		// Spring MVC 의 Request Context 에서 request attribute 를 얻는다.
		RequestAttributes requestAttribute = RequestContextHolder.currentRequestAttributes();
		//Token 정보 조회
		String pPrefixFiled = request.getParameter(SaveTokenTag.SAVE_TOKEN_PREFIX_FIELD); //파라미터로 전송된 세션 Prefix 명
		String pToken = request.getParameter(SaveTokenTag.SAVE_TOKEN_FIELD); //파라미터로 전송퇴 Token
		String sToken = (String)requestAttribute.getAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefixFiled, RequestAttributes.SCOPE_SESSION); //세션에 저장된 Token
		String sTokenTemp = (String)requestAttribute.getAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefixFiled + ".temp", RequestAttributes.SCOPE_SESSION); //세션에 저장된 Token Backup

		if(pToken != null && pPrefixFiled != null && sToken != null)
		{
			logger.debug("## INTER BEFORE pPrefixFiled : " + pPrefixFiled);
			logger.debug("## INTER BEFORE pToken : " + pToken);
			logger.debug("## INTER BEFORE sToken : " + sToken);
			logger.debug("## INTER BEFORE sTokenTemp : " + sTokenTemp);

			//AOP에서 파라미터로 넘어온 Token, Prefix 객체를 참조하기 위하여 request객체에 저장한다.
			request.setAttribute(SaveTokenTag.SAVE_TOKEN_FIELD, pToken);
			request.setAttribute(SaveTokenTag.SAVE_TOKEN_PREFIX_FIELD, pPrefixFiled);

			//Alert and Back 처리를 위해 토큰 백업
			session.setAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefixFiled + ".temp", sToken);
		}

		return true;
	}


	/**
	 * @Description CSRF사용시 alertAndBack처리가 되어 hisotry.back()을 실행하였을 경우 기존 세션에 등록되어있는 Token값이 삭제된다  이때 history.back()(alertAndBack)을 수행했을 경우에는 기존에 백업한 Token정보를 통해서 다시 세션 Token을 생성한다.
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 *
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView)
			throws Exception {
		logger.debug("## SaveTokenInterceptor postHandle start ");
		//사용객체 획득
		HttpSession session = request.getSession(); //세션 정보 획득
		ModelMap model = null;
		String viewName = "";
		if(modelAndView != null) {
			model = modelAndView.getModelMap();
			viewName = modelAndView.getViewName();//View 정보획득
		}
		
		//Token정보 조회
		String pPrefixFiled = request.getParameter(SaveTokenTag.SAVE_TOKEN_PREFIX_FIELD); //파라미터로 전송된 세션 Prefix 명
		String sTokenTemp = (String)session.getAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefixFiled + ".temp"); //세션에 저장된 Token Backup
		String sToken = (String)session.getAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefixFiled); //세션에 저장된 Token Backup

		//View Name alertAndBack 이며 세션에 sTokenTemp이  있을경우 세션정보를 원복한다.
		if( viewName != null && sTokenTemp != null)
		{
			logger.debug("## INTER AFTER sToken : " + sToken);
			logger.debug("## INTER AFTER sTokenTemp : " + sTokenTemp);
			logger.debug("## INTER AFTER viewName : " + viewName);

			String moveType = (String)model.get(PageRedirectView.MOVE_TYPE);
			if(PageRedirectView.ALERT_AND_BACK.equals(moveType)) session.setAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefixFiled, sTokenTemp);
		}
		//Token Backup값이 존재할시 삭제한다.
		if(sTokenTemp != null) session.removeAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefixFiled + ".temp");
	}
}



