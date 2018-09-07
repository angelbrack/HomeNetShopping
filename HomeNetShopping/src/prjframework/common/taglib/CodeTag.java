package prjframework.common.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import site.code.service.CodeService;

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
public class CodeTag extends TagSupport {

	static final long serialVersionUID = -8559042938610781697L;

	protected Logger logger = Logger.getLogger(this.getClass());

	private static WebApplicationContext wac = null;

	/**
	 * CodeTag에서 사용할 CodeService 이름 프로젝트에 맞게 구현
	 */
	private static final String commonCodeService = "codeService";

	private String elementMessage = "";

	/** Html Element Type(S:selectBox, R:RadioBox, C:checkBax) */
	private String type = "";
	/** Html Element Name */
	private String name = "";
	/** Html Element id */
	private String id = "";
	/** 공통코드 조회 문자 Key */
	private String key = "";
	/** 기본 DefaultSelect Key  */
	private String selected = "";
	/** Html Element에 적용할 javascript Function */
	private String function = "";
	/** checke box의 DefaultSelect Key */
	private String[] checked;
	/** selectbox default("선택" or "전체") */
	private String optdef;
	/** 데이터 베이스 조회 컬럼*/
	private String actvYn = "";
	/** 아이디 추가 명칭 값*/
	private String prefix = "";
	/** 검색조건 추가*/
	private String srchKey = "";
	/** CSS 1*/
	private String css = "";
	/** CSS 2*/
	private String css2 = "";
	/** title*/
	private String title = "";
	/** style */
	private String style = "";
	/** standard */
	private String standardType = "";
	/** langType */
	private String langType = "";
	/** OrderBy key*/
	private String orderBy;
	/** dataRole*/
	private String dataRole;
	/**
	 * CodeTag 생성자
	 */
	public CodeTag() {
		super();
	}

	@Override
	public void release() {
		super.release();
		this.type = "";
		this.name = "";
		this.id = "";
		this.key = "";
		this.selected = "";
		this.function = "";
		this.elementMessage = "";
		this.checked = null;
		this.optdef = "";
		this.actvYn = "";
		this.prefix = "";
		this.srchKey = "";
		this.css = "";
		this.css2 = "";
		this.title = "";
		this.style = "";
		this.standardType = "";
		this.setLangType("");
		this.orderBy="";
		this.orderBy="";

	}


	@Override
	public int doEndTag()
			throws JspException
	{
		//Code Tag 정보를 통하여 Html Element를 생성한다. 생성도중 오류시  Excepton처리 한다.
		try
		{
			CodeService commonCodeService = this.getCodeService();

			List<Map<String, Object>> codeList = null;

			codeList = commonCodeService.getCommonCodeList(this.key);
			
			this.elementMessage = this.createHtmlElement(codeList);

			this.printTagString(this.elementMessage);
		}
		catch (Exception e)
		{
			logger.error("code Tag error : " + e.getMessage());
		}

		return EVAL_PAGE;
	}


	/**
	 * <PRE>
	 * 1. MethodName	: createHtmlElement
	 * 2. ClassName		: CodeTag
	 * 3. Author		: LMS
	 * 4. Creation Date	: 2014. 12. 02. 오전 10:02:16
	 * 5. Comment		: Html Element 를 생성합니다.
	 * </PRE>
	 * 		@return String
	 * 		@param codeList
	 * 		@return
	 */
	private String createHtmlElement(List<Map<String, Object>> codeList)
	{
		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer.append(this.createElementHead());
		resultBuffer.append(this.createElementBody(codeList));
		resultBuffer.append(this.createElementBottom());

		return resultBuffer.toString();
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
	 * 		@param codeList
	 * 		@return
	 */
	private String createElementBody(List<Map<String, Object>> codeList)
	{
		String result = "";
		String cdInsNm	="";
		String cdInsV	="";
		String dataRole = this.dataRole == null || this.dataRole.equals("") ? "" : "data-role=\""+this.dataRole+"\"";
		int i = 1;
		for(Map<String, Object> codeVO : codeList)
		{
			cdInsNm = (String) codeVO.get("cdNm");
			cdInsV = (String) codeVO.get("cdId");
			 
			if(this.type.equals("S"))
			{
				String selectedCd = cdInsV.equals(this.selected) ? " selected=\"selected\"" : "";
				if(i == 1 && this.optdef != null) result += "<option value=\"\">" + this.optdef + "</option>";
				result += "<option value=\"" + cdInsV + "\"" + selectedCd + "  >" + cdInsNm + "</option>";
			}
			else if(this.type.equals("C"))
			{
				String selectedCd = "";

				if(this.checked != null)
				{
					for(String value : this.checked)
					{
						if(value.equals(cdInsV))
						{
							selectedCd = "checked=\"checked\"";
							break;
						}
					}
				}

				String id = this.id.equals("") ? this.name + i : this.id + i;
				if("".equals(this.standardType)){
					result += "<input type=\"checkbox\" id=\"" +  id + "\" name=\"" + this.name + "\" value=\"" + cdInsV + "\"" + selectedCd + " class=\""+css +"\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " " + dataRole + " /><label for=\"" + id + "\" class=\"" + css2 + "\">&nbsp;" + cdInsNm + "</label>\n";
				} else if("B".equals(this.standardType)){
					result += "<label><input type=\"checkbox\" id=\"" +  id + "\" name=\"" + this.name + "\" value=\"" + cdInsV + "\"" + selectedCd + " class=\""+css +"\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " " + dataRole + "  />&nbsp;" + cdInsNm + "</label>\n";
				} else if("L".equals(this.standardType)){
					result += "<li><input type=\"checkbox\" id=\"" +  id + "\" name=\"" + this.name + "\" value=\"" + cdInsV + "\"" + selectedCd + " class=\""+css +"\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " " + dataRole + " /><label for=\"" + id + "\" class=\"" + css2 + "\">&nbsp;" + cdInsNm + "</label></li>\n";
				}
			}
			else if(this.type.equals("R"))
			{
				String selectedCd = cdInsV.equals(this.selected) ? " checked=\"checked\"" : "";
				String id = this.id.equals("") ? this.name + i : this.id + i;

				if("".equals(this.standardType)){
					result += "<input type=\"radio\" id=\"" +  id + "\" name=\"" + this.name + "\" value=\"" + cdInsV + "\"" + selectedCd + " class=\""+css +"\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " " + dataRole + " /><label for=\"" + id + "\" class=\"" + css2 + "\" >&nbsp;" + cdInsNm + "</label>\n";
				} else if("B".equals(this.standardType)){
					result += "<label><input type=\"radio\" id=\"" +  id + "\" name=\"" + this.name + "\" value=\"" + cdInsV + "\"" + selectedCd + " class=\""+css +"\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " " + dataRole + " />&nbsp;" + cdInsNm + "</label>\n";
				} else if("L".equals(this.standardType)){
					result += "<li><input type=\"radio\" id=\"" +  id + "\" name=\"" + this.name + "\" value=\"" + cdInsV + "\"" + selectedCd + " class=\""+css +"\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " " + dataRole + " /><label for=\"" + id + "\" class=\"" + css2 + "\">&nbsp;" + cdInsNm + "</label></li>\n";
				}
			}
			i++;
		}

		return result;
	}


	/**
	 * <PRE>
	 * 1. MethodName	: createElementHead
	 * 2. ClassName		: CodeTag
	 * 3. Author		: LMS
	 * 4. Creation Date	: 2014. 12. 02. 오전 10:02:53
	 * 5. Comment		: Html Element Head정보를 생성합니다.
	 * </PRE>
	 * 		@return String
	 * 		@return
	 */
	private String createElementHead()
	{
		String result = "";

		if(this.type.equals("S"))
		{
			String id = this.id.equals("") ? this.name : this.id;
			String prefix = this.prefix == null || this.prefix.equals("") ? "" : this.prefix;
			String dataRole = this.dataRole == null || this.dataRole.equals("") ? "" : "data-role=\""+this.dataRole+"\"";

			if("B".equals(this.standardType)){
				result = "<label><select id=\"" +  id + prefix + "\" name=\"" + this.name + "\" class=\"" + this.css + "\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " "+ dataRole + ">";
			}else{
				result = "<select id=\"" +  id + prefix + "\" name=\"" + this.name + "\" class=\"" + this.css + "\" title=\"" + this.title + "\" style=\"" + this.style + "\" " + this.function + " "+ dataRole + ">";
			}
		}
		return result;
	}


	/**
	 * <PRE>
	 * 1. MethodName	: createElementBottom
	 * 2. ClassName		: CodeTag
	 * 3. Author		: LMS
	 * 4. Creation Date	: 2014. 12. 02. 오전 10:03:09
	 * 5. Comment		: Html Elemetn Bottom정보를 생성합니다.
	 * </PRE>
	 * 		@return String
	 * 		@return
	 */
	private String createElementBottom()
	{
		String result = "";

		if("".equals(this.standardType)){
			if(this.type.equals("S")) result = "</select>";
		}  else if("B".equals(this.standardType)){
			if(this.type.equals("S")) result = "</select></label>";
		}
		return result;
	}


	/**
	 * <PRE>
	 * 1. MethodName	: getCodeService
	 * 2. ClassName		: CodeTag
	 * 3. Author		: LMS
	 * 4. Creation Date	: 2014. 12. 02. 오후 5:55:11
	 * 5. Comment		: WebApplicationContext에서 commonCodeService 객체를 반환한다.
	 * </PRE>
	 * 		@return CodeService
	 * 		@return
	 */
	private CodeService getCodeService()
	{
		this.initWebApplicationContext();
		return (CodeService)this.wac.getBean(this.commonCodeService);
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
	private WebApplicationContext initWebApplicationContext()
	{
		if(this.wac == null) this.wac = WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX + "action");
		return this.wac;
	}


    /**
     * <PRE>
     * 1. MethodName	: printTagString
     * 2. ClassName		: CodeTag
     * 3. Author		: LMS
     * 4. Creation Date	: 2014. 12. 02. 오후 6:00:42
     * 5. Comment		: Tag 의 위치에 내용을 출력한다.
     * </PRE>
     * 		@return void
     * 		@param printStr printStr 출력하려는 내용
     * 		@throws JspException JspWriter를 이용하던중 IOException이 발생하는 경우.
     */
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String[] getChecked() {
		return checked;
	}

	public void setChecked(String[] checked) {
		this.checked = checked;
	}

	public String getOptdef() {
		return optdef;
	}

	public void setOptdef(String optdef) {
		this.optdef = optdef;
	}

	public String getActvYn() {
		return actvYn;
	}

	public void setActvYn(String actvYn) {
		this.actvYn = actvYn;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSrchKey()
	{
		return srchKey;
	}

	public void setSrchKey(String srchKey)
	{
		this.srchKey = srchKey;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getCss2() {
		return css2;
	}

	public void setCss2(String css2) {
		this.css2 = css2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStandardType() {
		return standardType;
	}

	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public String getDataRole() {
		return dataRole;
	}
	
	public void setDataRole(String dataRole) {
		this.dataRole = dataRole;
	}


}
