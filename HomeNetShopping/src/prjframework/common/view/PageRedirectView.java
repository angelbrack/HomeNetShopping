package prjframework.common.view;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import prjframework.common.util.Casting;

public class PageRedirectView extends AbstractView{

	protected Log logger = LogFactory.getLog(this.getClass());

	public static final String MOVE_TYPE = "fw.moveType"; //페이지 이동 속성 key
	public static final String ALERT_MESSAGE_TEXT = "fw.alertMessageText";//출력 메시지 key
	public static final String PARAM_OBJECT = "fw.paramObject";//전송 파라미터
	public static final String REDIRECT_URL = "fw.redirectUrl";//리턴 url
	public static final String METHDO_TYPE = "fw.methodType";//전송 타입
	public static final String TARGET_FUNCTION = "fw.targetFunction";//전송 타입

	// MOVE_TYPE Operation
	public static final String ALERT_AND_REDIRECT = "alertAndRedirect";
	public static final String ALERT_AND_REDIRECT_NO_POST = "alertAndRedirectNoPort";
	public static final String ALERT_AND_BACK = "alertAndBack";
	public static final String ALERT_AND_FORWARD = "alertAndForward";
	public static final String ALERT_AND_CLOSE_POPUP = "alertAndClosePopup";
	public static final String ALERT_AND_CLOSE_OPENER_RELOAD = "alertAndCloseOpenerReload";
	public static final String ALERT_AND_CLOSE_OPENER_REDIRECT = "alertAndCloseOpenerRedirect";
	public static final String ALERT_AND_CLOSE_PARENT_RELOAD = "alertAndCloseParentReload";
	public static final String ALERT_AND_CLOSE_PARENT_REDIRECT = "alertAndCloseParentRedirect";
	public static final String ALERT_AND_CLOSE_OPENER_RETURN = "alertAndCloseOpenerReturn";

	public static final String CONFIRM_AND_REDIRECT = "confirmAndRedirect";

	private static final String HTML_DTD = "<!DOCTYPE html>\n";
	private static final String HTML_HEAD1 = "<html lang=\"ko\">\n<head>\n<title></title>\n<script type=\"text/javascript\">\nfunction pageAction(){\n";
	private static final String HTML_HEAD2 = "}\n</script>\n</head>\n<body onload=\"pageAction();\">\n";
	private static final String HTML_FOOTER = "</body>\n</html>\n";


	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String resultString = "";
		String moveType = (String)model.get(this.MOVE_TYPE);

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();

		if(moveType.equals(this.ALERT_AND_BACK)) resultString = this.alertAndBack(model).toString();
		else if(moveType.equals(this.ALERT_AND_REDIRECT)) resultString = this.alertAndRedirect(model).toString();
		else if(moveType.equals(this.ALERT_AND_REDIRECT_NO_POST)) resultString = this.alertAndRedirectNoPort(model).toString();
		else if(moveType.equals(this.ALERT_AND_CLOSE_POPUP) || moveType.equals(this.ALERT_AND_CLOSE_OPENER_RELOAD) || moveType.equals(this.ALERT_AND_CLOSE_OPENER_REDIRECT) || 
				moveType.equals(this.ALERT_AND_CLOSE_PARENT_RELOAD) || moveType.equals(this.ALERT_AND_CLOSE_PARENT_REDIRECT) || moveType.equals(this.ALERT_AND_CLOSE_OPENER_RETURN)) resultString = this.alertAndObjectAction(model).toString();


		writer.write(resultString);
	}


	/**
	 * @Description alertAndBack HTML Source Generator
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	private StringBuffer alertAndBack(Map model)
	throws Exception
	{
		StringBuffer resultBuffer = new StringBuffer();

		resultBuffer.append(this.HTML_DTD);
		resultBuffer.append(this.HTML_HEAD1);

		if(model.get(this.ALERT_MESSAGE_TEXT) != null) resultBuffer.append("alert(\"" + model.get(this.ALERT_MESSAGE_TEXT) + "\");\n");

		resultBuffer.append("history.back();\n");
		resultBuffer.append(this.HTML_HEAD2);
		resultBuffer.append(this.HTML_FOOTER);

		return resultBuffer;
	}

	/**
	 * @Description  alertAndRedirect HTML Source Generator
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	private StringBuffer alertAndRedirect(Map model)
	throws Exception
	{
		StringBuffer resultBuffer = new StringBuffer();

		resultBuffer.append(this.HTML_DTD);
		resultBuffer.append(this.HTML_HEAD1);

		if(model.get(this.ALERT_MESSAGE_TEXT) != null) resultBuffer.append("alert(\"" + model.get(this.ALERT_MESSAGE_TEXT) + "\");\n");
		resultBuffer.append("document.paramForm.action=\""+ model.get(this.REDIRECT_URL) + "\";\n");
		resultBuffer.append("document.paramForm.submit();\n");
		resultBuffer.append(this.HTML_HEAD2);
		resultBuffer.append("<form name=\"paramForm\" method=\"" + model.get(this.METHDO_TYPE) + "\">\n");

		ArrayList<Map> paramList = this.getParameterList(model);

		for(Map param : paramList)
		{
			logger.debug("## Input Name, Value : " + param.get("name") + ", " + param.get("value"));
			resultBuffer.append("<input type=\"hidden\" name=\"" + param.get("name") +  "\" value=\"" + (String) param.get("value") + "\" />\n");
		}

		resultBuffer.append("</form>");
		resultBuffer.append(this.HTML_FOOTER);

		return resultBuffer;
	}

	/**
	 * @Description  alertAndRedirectNoPort HTML Source Generator
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	private StringBuffer alertAndRedirectNoPort(Map model)
	throws Exception
	{
		StringBuffer resultBuffer = new StringBuffer();

		resultBuffer.append(this.HTML_DTD);
		resultBuffer.append(this.HTML_HEAD1);

		if(model.get(this.ALERT_MESSAGE_TEXT) != null) resultBuffer.append("alert(\"" + model.get(this.ALERT_MESSAGE_TEXT) + "\");\n");
		resultBuffer.append("document.paramForm.action=\""+ model.get(this.REDIRECT_URL) + "\";\n");
		resultBuffer.append("document.paramForm.submit();\n");
		resultBuffer.append(this.HTML_HEAD2);
		resultBuffer.append("<form name=\"paramForm\" method=\"" + model.get(this.METHDO_TYPE) + "\">\n");
		resultBuffer.append("</form>");
		resultBuffer.append(this.HTML_FOOTER);

		return resultBuffer;
	}
	
	/**
	 * @Description  alertAndClosePopup  HTML Source Generator
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	private StringBuffer alertAndObjectAction(Map model)
	throws Exception
	{
		StringBuffer resultBuffer = new StringBuffer();

		String parameter = "";
		String moveType = (String)model.get(this.MOVE_TYPE);

		resultBuffer.append(this.HTML_DTD);
		resultBuffer.append(this.HTML_HEAD1);

		if(model.get(this.ALERT_MESSAGE_TEXT) != null) resultBuffer.append("alert(\"" + model.get(this.ALERT_MESSAGE_TEXT) + "\");\n");

		//REDIRECT일때 파라미터 정보를 GET방식으로 전송한다.
		if(moveType.equals(this.ALERT_AND_CLOSE_OPENER_REDIRECT) || moveType.equals(this.ALERT_AND_CLOSE_PARENT_REDIRECT))
		{
			parameter = this.getParameter(model);
			parameter = parameter.length() > 0 ? "?" + parameter : "";
		}

		if(moveType.equals(this.ALERT_AND_CLOSE_OPENER_RELOAD) || moveType.equals(this.ALERT_AND_CLOSE_PARENT_RELOAD)) resultBuffer.append(model.get(this.TARGET_FUNCTION) + ".location.reload();\n");
		else if(moveType.equals(this.ALERT_AND_CLOSE_OPENER_REDIRECT) || moveType.equals(this.ALERT_AND_CLOSE_PARENT_REDIRECT)) resultBuffer.append(model.get(this.TARGET_FUNCTION) + ".location.href='" + model.get(this.REDIRECT_URL) + parameter + "';\n");
		else if(moveType.equals(this.ALERT_AND_CLOSE_OPENER_RETURN)) resultBuffer.append(model.get(this.TARGET_FUNCTION) + ".onReturnPage();\n");
			
		if(moveType.equals(this.ALERT_AND_CLOSE_POPUP) || moveType.equals(this.ALERT_AND_CLOSE_OPENER_RELOAD) || moveType.equals(this.ALERT_AND_CLOSE_OPENER_REDIRECT) || moveType.equals(this.ALERT_AND_CLOSE_OPENER_RETURN)) resultBuffer.append("window.close();");
		
		resultBuffer.append(this.HTML_HEAD2);
		resultBuffer.append(this.HTML_FOOTER);
		
		return resultBuffer;
	}


	/**
	 * @Description  Object파람을 ArrayList 형태로 변환
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	private ArrayList<Map> getParameterList(Map model)
	throws Exception
	{
		ArrayList<Map> resultList = new ArrayList();
		Object paramObject = model.get(this.PARAM_OBJECT);

		if(paramObject != null)
		{
			if(paramObject instanceof Map) resultList = Casting.stringToParameterList(paramObject.toString().replaceAll("[\\{\\}\\s]", "").replaceAll(",", "&")); //Map
			else if(paramObject instanceof String) resultList = Casting.stringToParameterList(paramObject.toString()); //일반 문자열
			else resultList = Casting.voToParameterList(paramObject);
		}

		return resultList;
	}


	/**
	 * @Description   Object파일을 String형태로 변환
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	private String getParameter(Map model)
	throws Exception
	{
		String result = "";
		Object paramObject = model.get(this.PARAM_OBJECT);

		if( paramObject != null)
		{
			if(paramObject instanceof Map) result = paramObject.toString().replaceAll("[\\{\\}\\s]", "").replaceAll(",", "&");
			else if(paramObject instanceof String) result = paramObject.toString(); //일반 문자열
			else result = Casting.voToParameter(paramObject);
		}

		return result;
	}

}
