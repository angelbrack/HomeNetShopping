package prjframework.common.aop.advice;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import prjframework.common.annotation.ValidSaveToken;
import prjframework.common.exception.SavedTokenInvalidException;
import prjframework.common.taglib.SaveTokenTag;

/**-----------------------------------------------------------------------
 * Egovframework 2.6
 *------------------------------------------------------------------------
 * @Class ValidToken.java
 * @Description Request요청에 대한 CSRF을 방지하기 위한 대상을 지정하는 Annotation
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
public class SaveTokenValidator {

	/**	log4j	*/
	protected Log logger = LogFactory.getLog(this.getClass());

    /**
     * @Description 메소드 수행전에 Annotation이 존재할시 Token 비교를 수행한다.
     *
     * @param thisJoinPoint
     * @throws Throwable
     *
     */
    public void beforeTargetMethod(JoinPoint thisJoinPoint) throws Throwable {
		logger.debug("## AOP start>>>>> " );
		//PageTitle Annotation 가져오기
		MethodSignature signature = (MethodSignature)thisJoinPoint.getSignature();
		ValidSaveToken validSaveToken = signature.getMethod().getAnnotation(ValidSaveToken.class);

		//Method VaildSaveToken Annotation이 존재할시 Token정보를 확인한다.
		if(validSaveToken != null)
		{
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

			String pPrefix = (String)requestAttributes.getAttribute(SaveTokenTag.SAVE_TOKEN_PREFIX_FIELD, RequestAttributes.SCOPE_REQUEST);
			logger.debug("## AOP pPrefix : " + pPrefix);
			String sToken = (String)requestAttributes.getAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefix, RequestAttributes.SCOPE_SESSION);//Session Token
			String pToken = (String)requestAttributes.getAttribute(SaveTokenTag.SAVE_TOKEN_FIELD, RequestAttributes.SCOPE_REQUEST);//Parameter Token
			logger.debug("## AOP sToken : " + sToken);
			logger.debug("## AOP pToken : " + pToken);
			if(sToken == null) throw new SavedTokenInvalidException("Session 내에 유효한 토큰이 없습니다. 정상적인 요청이 아닙니다.");//Session Token Error
			if(pToken == null) throw new SavedTokenInvalidException("파라미터 Token정보가 존재하지 않습니다.");//Parameter Token Error

			//파라미터 토큰과 세션에 저장한 토큰 비교
			SaveTokenTag saveTokenTag = new SaveTokenTag();
			if(!saveTokenTag.isValid(sToken, pToken)) throw new SavedTokenInvalidException("파라미터 Token정보가 존재하지 않습니다.");
			//정상적인 요청시 기존 Session에 등록된 토큰정보를 삭제한다
			else requestAttributes.removeAttribute(SaveTokenTag.SAVE_TOKEN_KEY + pPrefix, RequestAttributes.SCOPE_SESSION);
		}
	}
}
