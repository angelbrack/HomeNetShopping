package prjframework.common.taglib;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;


/**-----------------------------------------------------------------------
 * Egovframework 2.6
 *------------------------------------------------------------------------
 * @Class SavedTokenTag.java
 * @Description SaveToken Tag
 *
 * @author king
 * @since 2014. 12. 03.
 * @version 1.0
 *
 * @Copyright (c) 2014 (주) 넥스젠어쏘시에이트
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일               수정자       수정내용
 * -----------   ---------  -----------------------------------------------
 * 2014. 12. 03. king      최초생성
 */
@SuppressWarnings("unchecked")
public class SaveTokenTag extends BodyTagSupport{

	static final long serialVersionUID = 1109742514901126564L;

	protected Logger logger = Logger.getLogger(this.getClass());

	//Session에 저장될 Token Key 속성들
	public static final String SAVE_TOKEN_KEY = "lms.common.save.token.key"; //생성될 Token을 저장할 SessionKey

	//파라미터로 Hidden 으로 전송될 파라미터 명
	public static final String SAVE_TOKEN_FIELD = "lms.common.save.token.field";//폼으로 전송할 parameter Name명
	public static final String SAVE_TOKEN_PREFIX_FIELD = "lms.common.save.token.prefix";//폼으로 전송할 parameter Name명

	//초기 디폴트로 설정될 Token 속성값
	public static final String DEFAULT_PREFIX = "NONE"; //페이지내의 Token태그가 여러개 존재할시 구분짓는 속성값

	private final Random RANDOM = new Random();

	private String tokenMessage = "";

	private String name = ""; //이름
	private String prefix = ""; //페이지내의 테그가 여러개 존재시 구분값


	/**
	 * LSavedTokenTag 생성자
	 */
	public SaveTokenTag() {
		super();
	}


	/**
	 * @Description  Token 문자열을  생성한다.
	 *
	 * @param req
	 * @return
	 *
	 */
	private String createToken(HttpServletRequest req) {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}


	/**
	 * @Description  Token정보를 세션에 저장한다.  페이지내의 Token Tag가 여러개 존재시 Prefix를 통하여 Session을 구분한다.
	 *
	 * @param request
	 * @param auth
	 * @param prefix
	 * @return
	 *
	 */
	private String setToken(HttpServletRequest request, String prefix ){

		HttpSession session = request.getSession();
		String token = this.createToken(request);

		session.setAttribute(this.SAVE_TOKEN_KEY + prefix, token);

		return token;
	}


	/**
	 * @Description  Token 유효성 검사를 수행한다.
	 *
	 * @param request
	 * @param session
	 * @return
	 *
	 */
	public boolean isValid(String sToken, String pToken) {
		return pToken.equals(sToken) ? true : false;
	}

	/**
	 * @Description  Token Tag정보를 전송할 hidden 파라미터형태로 변경한다.
	 *
	 * @param requset
	 * @param name
	 * @param auth
	 * @param prefix
	 * @return
	 *
	 */
	public String generateHiddenParam( HttpServletRequest requset, String name, String prefix)
	{
		StringBuffer hiddenParam = new StringBuffer("");
		if(prefix == null || prefix.equals("")) prefix = this.DEFAULT_PREFIX;

		String tokenId = setToken(requset, prefix);

		hiddenParam.append("\n<input type=\"hidden\" name=\"" + this.SAVE_TOKEN_PREFIX_FIELD + "\" value=\"" + prefix + "\" />");
		hiddenParam.append("\n<input type=\"hidden\" name=\"" + this.SAVE_TOKEN_FIELD + "\" value=\"" + tokenId + "\" />");

		return hiddenParam.toString();
	}


	@Override
	public int doEndTag()
			throws JspException
	{
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();

		//출력할 Hidden input을 생성한다.
		tokenMessage = this.generateHiddenParam(request, getName(), getPrefix());
		this.printTagString(tokenMessage);
		this.release();

		return EVAL_PAGE;
	}


    /**
     * print blank string
     *
     * @throws LTagException
     */
    public void printBlankString() throws Exception{
        printTagString("");
    }

    /**
     * Tag 의 위치에 내용을 출력한다.
     *
     * @param printStr 출력하려는 내용
     * @throws LTagException JspWriter를 이용하던중 IOException이 발생하는 경우.
     */
    public void printTagString(String printStr) throws JspException{
        JspWriter out = this.pageContext.getOut();

        try {
            out.print(printStr);
        } catch (IOException e) {
            throw new JspException("IO Error.", e);
        }
    }

	/**
	 * release
	 */
	@Override
	public void release() {
		super.release();
		this.name = "";
		this.tokenMessage = "";
	}


	/**
	 * name setter
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * name getter
	 */
	public String getName(){
		return this.name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


}
