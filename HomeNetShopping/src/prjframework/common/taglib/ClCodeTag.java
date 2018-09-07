package prjframework.common.taglib;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

/**-----------------------------------------------------------------------
 * lmsframework
 *------------------------------------------------------------------------
 * @Class CodeTag.java
 * @Description CodeTag 클래스 참조하는 CodeService는 업무에 맞게 변경해야 함
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
public class ClCodeTag extends TagSupport {

	static final long serialVersionUID = -8559042938610781697L;

	protected Logger logger = Logger.getLogger(this.getClass());

	private static WebApplicationContext wac = null;

	/**
	 * CodeTag에서 사용할 CodeService 이름 프로젝트에 맞게 구현
	 */
	private static final String commonClCodeService = "commonClCodeService";

	private String elementMessage = "";

	/**
	 * CodeTag 생성자
	 */
	public ClCodeTag() {
		super();
	}

	@Override
	public void release() {
		super.release();
		this.id = "";
	}




	/**
	 * <PRE>
	 * 1. MethodName	: createElementBody
	 * 2. ClassName		: CodeTag
	 * 3. Author		: LMS
	 * 4. Creation Date	: 2014. 12. 02. 오전 10:02:26
	 * 5. Comment		: Html Element Body를 생성합니다.
	 * </PRE>
	 * 		@return String
	 * 		@param clCodeList
	 * 		@return
	 */
	private String createElementBody(List<Map<String, Object>> clCodeList)
	{
		StringBuffer strBuff = new StringBuffer();
		StringBuffer hgrkBuff = new StringBuffer();
		StringBuffer mlclstrBuff = new StringBuffer();
		StringBuffer clBuff = new StringBuffer();
		
		String crsClLv ="";
		String crsClNm ="";
		String crsClNo = "";
		String hgrkCrsClNo ="";
		String crsTypeCd = "";
		
		for(Map<String, Object> courseVo : clCodeList) {
			crsClLv = courseVo.get("crsClLv").toString();
			crsClNm = (String) courseVo.get("crsClNm");
			crsClNo = courseVo.get("crsClNo").toString();
			hgrkCrsClNo = courseVo.get("hgrkCrsClNo").toString();
			crsTypeCd = courseVo.get("crsTypeCd").toString();
			
			if("1".equals(crsClLv)) {
				hgrkBuff.append("<option value=\""+crsClNo+"\" class=\"type"+crsTypeCd+"\">"+crsClNm+"</option>");
			} else if("2".equals(crsClLv)) {
				mlclstrBuff.append("<option value=\""+crsClNo+"\" class=\"mlcl"+hgrkCrsClNo+"\">"+crsClNm+"</option>");
			} else if("3".equals(crsClLv)) {
				clBuff.append("<option value=\""+crsClNo+"\" class=\"cl"+hgrkCrsClNo+"\">"+crsClNm+"</option>");
			} else {
				
			}
		}
		
		strBuff.append("<label for=\"searchCrsHlclNo\" class=\"hidden\">과정분류1</label>");
		strBuff.append("<select style=\"width:150px;height:22px;\" name=\"searchCrsHlclNo\" id=\"searchCrsHlclNo\"><option value=\"\" selected=\"selected\">- 전체 -</option>");
		strBuff.append(hgrkBuff.toString()+"</select>");
		
		strBuff.append(" <label for=\"searchCrsMlclNo\" class=\"hidden\">과정분류2</label>");
		strBuff.append("<select style=\"width:150px;height:22px;\" name=\"searchCrsMlclNo\" id=\"searchCrsMlclNo\"><option value=\"\" selected=\"selected\">- 전체 -</option>");
		strBuff.append(mlclstrBuff.toString()+"</select>");
		
		strBuff.append(" <label for=\"searchCrsClNo\" class=\"hidden\">과정분류3</label>");
		strBuff.append("<select style=\"width:150px;height:22px;\" name=\"searchCrsClNo\" id=\"searchCrsClNo\"><option value=\"\">- 전체 -</option>");
		strBuff.append(clBuff.toString()+"</select>");

		return strBuff.toString();
	}
	



	/**
	 * <PRE>
	 * 1. MethodName	: initWebApplicationContext
	 * 2. ClassName		: CodeTag
	 * 3. Author		: LMS
	 * 4. Creation Date	: 2014. 12. 02. 오후 5:53:54
	 * 5. Comment		: WabApplication를 가져온다. 최초 생성후 기존 생성객체를 재사용
	 * </PRE>
	 * 		@return WebApplicationContext
	 * 		@return
	 */
	private WebApplicationContext initWebApplicationContext() {
		if(this.wac == null) this.wac = WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX + "action");
		return this.wac;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
