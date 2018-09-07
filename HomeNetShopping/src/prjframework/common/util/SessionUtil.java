package prjframework.common.util;

import java.util.List;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import korea.wrk.login.service.RoleVO;
import korea.wrk.login.service.UserLoginVO;

/**
 * session Util
 * - Spring에서 제공하는 RequestContextHolder 를 이용하여
 * request 객체를 service까지 전달하지 않고 사용할 수 있게 해줌
 * 
 */
public class SessionUtil {
 
 /**
  * attribute 값을 가져 오기 위한 method
  * 
  * @param String  attribute key name 
  * @return Object attribute obj
 */
 public static Object getAttribute(String name){
  return (Object)RequestContextHolder.currentRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
 }
 
 /**
  * attribute 설정 method
  * 
  * @param String  attribute key name 
  * @param Object  attribute obj
  * @return void
 */
 public static void setAttribute(String name, Object object) {
  RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
 }
 
 /**
  * 설정한 attribute 삭제 
  * 
  * @param String  attribute key name 
  * @return void
 */
 public static void removeAttribute(String name) throws Exception {
  RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
 }
 
 /**
  * session id 
  * 
  * @param void
  * @return String SessionId 값
 */
 public static String getSessionId() throws Exception  {
  return RequestContextHolder.getRequestAttributes().getSessionId();
 }
 
 public static Object aa() throws Exception {
	 return null;
 }
 
 
 /** 로그인 유무를 확인한다.*/
 public static boolean isLogin(){
 	if(RequestContextHolder.currentRequestAttributes().getAttribute("UserLoginVO", RequestAttributes.SCOPE_SESSION) != null) {
 		return true;
 	}
 	return false;
 }
 
 /**
  * HttpSession에 로그인한 사용자 정보를 담은 VO를 가져 온다.
  * 
  * @return Map - 로그인한 사용자 정보를 담은 VO
  */
 public static UserLoginVO getUserInfo() {
	 
	 if(SessionUtil.isLogin()) {
		 return (UserLoginVO)RequestContextHolder.currentRequestAttributes().getAttribute("UserLoginVO", RequestAttributes.SCOPE_SESSION);
	 } else {
		 return new UserLoginVO();
	 }
 }   
 public static List<RoleVO>  getAuthInfo() {
	 
	 if(SessionUtil.isLogin()) {
		 return (List<RoleVO>) RequestContextHolder.currentRequestAttributes().getAttribute("roleList", RequestAttributes.SCOPE_SESSION);
	 } else {
		 List<RoleVO> list = null; 
		 return list;
	 }
 }
 
 public static String getOptrAuthNo() {
	 
	 if(SessionUtil.isLogin()) {
		 return (String)RequestContextHolder.currentRequestAttributes().getAttribute("optrAuthNo", RequestAttributes.SCOPE_SESSION);
	 } else {
		 return null;
	 }
 }
 
 /**
  * HttpSession에 로그인한 ID 를 가져 온다.
  * 
  * @return String - 로그인ID
  */
 public static String getLoginId() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getLoginId() : null;
 }
 
 /**
  * HttpSession에 로그인한 사용자번호를 가져 온다.
  * 
  * @return String - 사용자번호
  */
 public static String getUserNo() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getUserNo() : null;
 }
 
 /**
  * HttpSession에 로그인한 사용자이름을 가져 온다.
  * 
  * @return String - 사용자이름
  */
 public static String getUserNm() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getUserNm() : null;
 }
 
 /**
  * HttpSession에 로그인한 사용자학번을 가져 온다.
  * 
  * @return String - 사용자학번
  */
 public static String getRpsStno() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getRpsStno() : null;
 }
 
 /**
  * HttpSession에 로그인한 사용자권한코드을 가져 온다.
  * 
  * @return String - 사용자권한코드
  */
 public static String getOptrAuthCd() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getOptrAuthCd() : null;
 }
 
 /**
  * HttpSession에 로그인한 사용자IP을 가져 온다.
  * 
  * @return String - 사용자IP
  */
 
 public static String getLoginIp() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getRemoteAddr() : null;
 }
 
 /**
  * HttpSession에 로그인한 운영자승인상태을 가져 온다.
  * 
  * @return String - 운영자승인상태
  */
 public static String getExprYn() {
 	return  SessionUtil.isLogin() ? String.valueOf(SessionUtil.getUserInfo().getOptrExpireYn()) : null;
 }
 
 /**
  * HttpSession에 로그인한 사용자로그인일시을 가져 온다.
  * 
  * @return String - 사용자로그인일시
  */
 public static String getOptrLastLoginDtm() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getLoginTime() :  null;
 }
 
 /**
  * HttpSession에 로그인한 회원구분코드을 가져 온다.
  * 
  * @return String - 회원구분코드
  */
 public static String getMbrDc() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getMbrDc() : null;
 }

 /**
  * HttpSession에 로그인한 로그인ID만기여부을 가져 온다.
  * 
  * @return String - 로그인ID만기여부
  */
 public static String getLoginIdExprYn() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getOptrExpireYn() : null;
 }
 
 /**
  * HttpSession에 로그인한 로그인실패횟수을 가져 온다.
  * 
  * @return String - 로그인실패횟수
  */
 public static String getPwdFailTms() {
 	return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getPwdFailTms() : null;
 }
 
 /**
  * HttpSession에 로그인한 회원의 기업번호를 가져 온다.
  * 
  * @return String - 기업번호
  */
public static String getEtrNo() {
	 return  SessionUtil.isLogin() ? SessionUtil.getUserInfo().getEtrNo() : null;
 }
 
}