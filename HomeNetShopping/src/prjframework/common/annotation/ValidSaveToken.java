package prjframework.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidSaveToken {
	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any
	 */
	String value() default "";
}
