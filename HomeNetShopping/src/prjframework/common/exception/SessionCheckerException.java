package prjframework.common.exception;

import egovframework.rte.fdl.cmmn.exception.BaseException;

/**-----------------------------------------------------------------------
 * 	Egovframework 2.0
 *------------------------------------------------------------------------
 * @Class SavedTokenInvalidException.java
 * @Description SaveToken이 유효하지 않을시 발생하는 Exception
 *
 * @author king
 * @since 2014. 12. 03.
 * @version 1.0
 *
 * @Copyright (c) 2014 (주) 넥스젠어쏘시에이트
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일              수정자       수정내용
 * -----------   ---------  -----------------------------------------------
 * 2014. 12. 03. king      최초생성
 */
public class SessionCheckerException extends BaseException{

	static final long serialVersionUID = -8829784836983153641L;


	public SessionCheckerException()
	{
		super("SavedTokenInvalidException without message", null, null);
	}


	public SessionCheckerException(String defaultMessage)
	{
		super(defaultMessage, null, null);
	}

}
