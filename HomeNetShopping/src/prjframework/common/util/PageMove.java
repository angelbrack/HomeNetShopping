package prjframework.common.util;


import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import prjframework.common.view.PageRedirectView;


public class PageMove {


	/**
	 * @Description  alert 메세지 출력후 history back 메소드
	 *
	 * @param model
	 * @param msg null일경우 alert은 수행되지 않습니다.
	 * @return
	 *
	 */
	public static ModelAndView alertAndBack(ModelMap model, String msg)
			throws Exception{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_BACK);
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * @Description  alert 메시지 출력후 특정디렉토리 이동
	 *
	 * @param model
	 * @param url 이동할 url정보
	 * @param msg alert에 출력할 메시지 null일경우 alert은 수행되지 않습니다.
	 * @param param 파라미터 Object (Map or String or VO)
	 * @return
	 * @throws Exception
	 *
	 */
	public static ModelAndView alertAndRedirect(ModelMap model, String url, String msg, Object param)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_REDIRECT);
		model.addAttribute(PageRedirectView.REDIRECT_URL, url);
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.PARAM_OBJECT, param);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * @Description  alert 메시지 출력후 특정디렉토리 이동(POST전송)
	 *
	 * @param model
	 * @param url 이동할 url정보
	 * @param msg alert에 출력할 메시지 null일경우 alert은 수행되지 않습니다.
	 * @param param 파라미터 Object (Map or String or VO)
	 * @return
	 * @throws Exception
	 *
	 */
	public static ModelAndView alertAndRedirectPost(ModelMap model, String url, String msg, Object param)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_REDIRECT);
		model.addAttribute(PageRedirectView.REDIRECT_URL, url);
		if(msg != null && !"".equals(msg)) {
			model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		}
		
		model.addAttribute(PageRedirectView.PARAM_OBJECT, param);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "POST");

		return new ModelAndView("pageRedirectView");
	}

	/**
	 * @Description  alert 메시지 출력후 특정디렉토리 이동(POST전송)
	 *
	 * @param model
	 * @param url 이동할 url정보
	 * @param msg alert에 출력할 메시지 null일경우 alert은 수행되지 않습니다.
	 * @param param 파라미터 Object (Map or String or VO)
	 * @return
	 * @throws Exception
	 *
	 */
	public static ModelAndView alertAndRedirectNoPost(ModelMap model, String url, String msg, Object param)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_REDIRECT_NO_POST);
		model.addAttribute(PageRedirectView.REDIRECT_URL, url);
		if(msg != null && !"".equals(msg)) {
			model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		}
		
		model.addAttribute(PageRedirectView.METHDO_TYPE, "POST");

		return new ModelAndView("pageRedirectView");
	}
	
	/**
	 * @Description alert 메시지 출력후 현재 팝업 닫음
	 *
	 * @param model
	 * @param msg alert에 출력할 메시지 null일경우 alert은 수행되지 않습니다.
	 * @return
	 * @throws Exception
	 *
	 */
	public static ModelAndView alertAndClosePopup(ModelMap model, String msg)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_POPUP);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "opener");
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * @Description alert 메시지 출력후 오프너 reload
	 *
	 * @param model
	 * @param msg alert에 출력할 메시지 null일경우 alert은 수행되지 않습니다.
	 * @return
	 * @throws Exception
	 *
	 */
	public static ModelAndView alertAndCloseOpenerReload(ModelMap model, String msg)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_OPENER_RELOAD);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "opener");
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * @Description alert 메시지 출력후 오프너 return onReturnPage() 함수 호출
	 *
	 * @param model
	 * @param msg alert에 출력할 메시지 null일경우 alert은 수행되지 않습니다.
	 * @return
	 * @throws Exception
	 *
	 */
	public static ModelAndView alertAndCloseOpenerReturn(ModelMap model, String msg)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_OPENER_RETURN);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "opener");
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}
	
	/**
	 * @Description
	 *
	 * @param model
	 * @param url 이동할 url정보
	 * @param msg alert에 출력할 메시지 null일경우 alert은 수행되지 않습니다.
	 * @param param 파라미터 Object (Map or String or VO)
	 * @return
	 * @throws Exception
	 *
	 */
	public static ModelAndView alertAndCloseOpenerRedirect(ModelMap model, String url, String msg, Object param)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_OPENER_REDIRECT);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "opener");
		model.addAttribute(PageRedirectView.REDIRECT_URL, url);
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.PARAM_OBJECT, param);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * <PRE>
	 * 1. MethodName	: alertAndCloseTopReload
	 * 2. ClassName		: PageMove
	 * 3. Author		: MONG
	 * 4. Creation Date	: 2013. 1. 17. 오후 2:05:09
	 * 5. Comment		: Layer창에서 Top오브 젝트의 페이지를 리로드 한다.
	 * </PRE>
	 * 		@return ModelAndView
	 * 		@param model
	 * 		@param msg
	 * 		@return
	 * 		@throws Exception
	 */
	public static ModelAndView alertAndCloseTopReload(ModelMap model, String msg)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_PARENT_RELOAD);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "top");
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * <PRE>
	 * 1. MethodName	: alertAndCloseTopRedirect
	 * 2. ClassName		: PageMove
	 * 3. Author		: MONG
	 * 4. Creation Date	: 2013. 1. 17. 오후 2:05:40
	 * 5. Comment		: Layer창에서 Top오브 젝트의 페이지를 이동한다.
	 * </PRE>
	 * 		@return ModelAndView
	 * 		@param model
	 * 		@param url
	 * 		@param msg
	 * 		@param param
	 * 		@return
	 * 		@throws Exception
	 */
	public static ModelAndView alertAndCloseTopRedirect(ModelMap model, String url, String msg, Object param)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_PARENT_REDIRECT);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "top");
		model.addAttribute(PageRedirectView.REDIRECT_URL, url);
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.PARAM_OBJECT, param);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * <PRE>
	 * 1. MethodName	: alertAndCloseParentReload
	 * 2. ClassName		: PageMove
	 * 3. Author		: MONG
	 * 4. Creation Date	: 2013. 1. 17. 오후 2:05:52
	 * 5. Comment		: Layer창에서 parent오브 젝트의 페이지를 리로드 한다.
	 * </PRE>
	 * 		@return ModelAndView
	 * 		@param model
	 * 		@param msg
	 * 		@return
	 * 		@throws Exception
	 */
	public static ModelAndView alertAndCloseParentReload(ModelMap model, String msg)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_PARENT_RELOAD);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "parent");
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}


	/**
	 * <PRE>
	 * 1. MethodName	: alertAndCloseParentRedirect
	 * 2. ClassName		: PageMove
	 * 3. Author		: MONG
	 * 4. Creation Date	: 2013. 1. 17. 오후 2:06:07
	 * 5. Comment		: Layer창에서 parent오브 젝트의 페이지를 이동 한다.
	 * </PRE>
	 * 		@return ModelAndView
	 * 		@param model
	 * 		@param url
	 * 		@param msg
	 * 		@param param
	 * 		@return
	 * 		@throws Exception
	 */
	public static ModelAndView alertAndCloseParentRedirect(ModelMap model, String url, String msg, Object param)
	throws Exception
	{
		model.addAttribute(PageRedirectView.MOVE_TYPE, PageRedirectView.ALERT_AND_CLOSE_PARENT_REDIRECT);
		model.addAttribute(PageRedirectView.TARGET_FUNCTION, "parent");
		model.addAttribute(PageRedirectView.REDIRECT_URL, url);
		model.addAttribute(PageRedirectView.ALERT_MESSAGE_TEXT, msg);
		model.addAttribute(PageRedirectView.PARAM_OBJECT, param);
		model.addAttribute(PageRedirectView.METHDO_TYPE, "GET");

		return new ModelAndView("pageRedirectView");
	}

}
