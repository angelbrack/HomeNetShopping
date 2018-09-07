package prjframework.common.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import egovframework.rte.fdl.property.EgovPropertyService;

/**-----------------------------------------------------------------------
 * lmsframework
 *------------------------------------------------------------------------
 * @Class CodeTag.java
 * @Description properties 파일읽어오는 태크
 *
 * @author king
 * @since 2014. 12. 02
 * @version 1.0
 *
 * @Copyright (c) 2014 (주) 넥스젠어쏘시에이트
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일               수정자       수정내용
 * -----------   ---------  -----------------------------------------------
 * 2014. 12. 02  king       최초생성
 */
public class ConfigTag extends TagSupport {

	
	private static WebApplicationContext wac = null;

	/**
	 * CodeTag에서 사용할 CodeService 이름 프로젝트에 맞게 구현
	 */
	private static final String configPropService = "configPropService";
	
	static final long serialVersionUID = 8369681626511660735L;

	protected Logger logger = Logger.getLogger(this.getClass());
	private String key = "";
	private String langType = "";

	public ConfigTag() {
		super();
	}

	@Override
	public void release() {
		super.release();
		this.key = "";
		this.langType = "";
	}


	@Override
	public int doEndTag()
			throws JspException
	{
		try
		{
			EgovPropertyService configPropService = this.getConfigPropService();
			this.printTagString(configPropService.getString(key));
		}
		catch (Exception e)
		{
			logger.error("ConfigTag Tag error : " + e.getMessage());
		}

		return EVAL_PAGE;
	}

	private EgovPropertyService getConfigPropService()
	{
		this.initWebApplicationContext();
		return (EgovPropertyService)this.wac.getBean(this.configPropService);
	}


	private WebApplicationContext initWebApplicationContext()
	{
		if(this.wac == null) this.wac = WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX + "action");
		return this.wac;
	}

	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

	public void printTagString(String printStr)
			throws JspException
			{
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(printStr);
		} catch (IOException e) {
			throw new JspException("IO Error.", e);
		}
			}

}
