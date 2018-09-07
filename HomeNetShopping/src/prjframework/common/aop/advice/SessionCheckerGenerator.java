package prjframework.common.aop.advice;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import prjframework.common.annotation.SessionChecker;
import prjframework.common.exception.SessionCheckerException;
import prjframework.common.util.SessionUtil;
import egovframework.com.cmm.EgovMessageSource;

/**-----------------------------------------------------------------------
 * Egovframework 2.6
 *------------------------------------------------------------------------
 * @Class SessionCheckerGenerator.java
 * @Description Annotation으로 설정된 Controller Method가 SESSION 값과 비교하여 사용가능한지를 확인한다
 *
 * @author king
 * @since 2015. 01. 07.
 * @version 1.0
 *
 * @Copyright (c) 2014 (주) 넥스젠어쏘시에이트
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일               수정자       수정내용
 * -----------   ---------  -----------------------------------------------
 * 2015. 01. 07. king      최초생성
 */

public class SessionCheckerGenerator {

	/** log4j	*/
	protected Log logger = LogFactory.getLog(this.getClass());

	/** MessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	/**
	 * 메소드 수행전에 Annotation이 존재할시 권한 체크
	 *
	 * @param thisJoinPoint
	 * @throws Throwable
	 */
	public void afterTargetMethod(JoinPoint thisJoinPoint) throws Throwable {

		//메소드 수행전 Annotation 가져오기
		MethodSignature signature = (MethodSignature)thisJoinPoint.getSignature();
		SessionChecker sessionCheckerAnnotation = signature.getMethod().getAnnotation(SessionChecker.class);

		//Annotation 정보가 있을때만 매핑
		if(sessionCheckerAnnotation != null) {
			String sessionChkValue = sessionCheckerAnnotation.value();
			logger.debug("## SessionCheckerGenerator -> start ");
			logger.debug("## sessionChkValue : " + sessionChkValue);
			logger.debug("## SessionChecker 로그인여부 "+SessionUtil.isLogin());
			//로그인여부
			if(!SessionUtil.isLogin()) {
				logger.debug("## SessionCheckerGenerator : " + egovMessageSource.getMessage("login.notLogin"));
				throw new SessionCheckerException(egovMessageSource.getMessage("login.notLogin"));
			} else {
				/*
				logger.debug("## sessionChkValue.indexOf(SessionUtil.getOptrAuthCd() : " + sessionChkValue.indexOf(SessionUtil.getOptrAuthCd()));
				logger.debug("## sessionChkValue : " + sessionChkValue);
				logger.debug("## SessionUtil.getOptrAuthCd() : " + SessionUtil.getOptrAuthCd());
				*/

				//권한체크
//				if(sessionChkValue != null && sessionChkValue.indexOf(SessionUtil.getOptrAuthCd()) < 0) {
//					logger.debug("## SessionCheckerGenerator : " + egovMessageSource.getMessage("login.notAuth"));
//					throw new SessionCheckerException(egovMessageSource.getMessage("login.notAuth"));
//				}
			}


			//logger.debug("## userSession : " + (String)requestAttributes.getAttribute("UserLoginVO",RequestAttributes.SCOPE_REQUEST));
		}
	}
}