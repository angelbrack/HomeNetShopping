package homenet.shop.login.service;

import java.io.Serializable;
import lombok.Data;

public class UserLoginVO implements Serializable {
	
	static final long serialVersionUID = -8734444638459129053L;
	
	private String mbrDc;
	private String loginId;						//LOGIN ID
	private String loginPwd;					//사용자비밀번호
	private String userNo;						//사용자번호
	private String pwdChgYn; //패스워드 변경여부 , Y 변경
	private String optrExpireYn; //권한만료 Y:권한만료
	private String optrAuthCd ;
	private String userNm ;
	private String pwdFailTms ;
	private String remoteAddr ;
	private String loginTime ;
	private String cnntInfo ;
	private String osCd ;
	private String mblYn ;
	private String rpsStno ;
	private String tmpPwdYn ;
	private String tmpPwdLimitYn ;
	private String pwdChngLimitYn ;
	private String etrNm ;
	private String etrNo ;
	private String bizJangNo ;
	private String nwrk200tlDelYn ;
	
	
	
	
	private String loginDiv ;
	private String loginIdK ;
	private String loginPwdK ;
	private String loginIdC ;
	private String loginPwdC ;
	private String loginIdG ;
	private String loginPwdG ;
	
	private String ssoMsg ;

	
	public String getMbrDc() {
		return mbrDc;
	}
	public void setMbrDc(String mbrDc) {
		this.mbrDc = mbrDc;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getPwdChgYn() {
		return pwdChgYn;
	}
	public void setPwdChgYn(String pwdChgYn) {
		this.pwdChgYn = pwdChgYn;
	}
	public String getOptrExpireYn() {
		return optrExpireYn;
	}
	public void setOptrExpireYn(String optrExpireYn) {
		this.optrExpireYn = optrExpireYn;
	}
	public String getOptrAuthCd() {
		return optrAuthCd;
	}
	public void setOptrAuthCd(String optrAuthCd) {
		this.optrAuthCd = optrAuthCd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPwdFailTms() {
		return pwdFailTms;
	}
	public void setPwdFailTms(String pwdFailTms) {
		this.pwdFailTms = pwdFailTms;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getCnntInfo() {
		return cnntInfo;
	}
	public void setCnntInfo(String cnntInfo) {
		this.cnntInfo = cnntInfo;
	}
	public String getOsCd() {
		return osCd;
	}
	public void setOsCd(String osCd) {
		this.osCd = osCd;
	}
	public String getMblYn() {
		return mblYn;
	}
	public void setMblYn(String mblYn) {
		this.mblYn = mblYn;
	}
	public String getLoginDiv() {
		return loginDiv;
	}
	public void setLoginDiv(String loginDiv) {
		this.loginDiv = loginDiv;
	}
	public String getLoginIdK() {
		return loginIdK;
	}
	public void setLoginIdK(String loginIdK) {
		this.loginIdK = loginIdK;
	}
	public String getLoginPwdK() {
		return loginPwdK;
	}
	public void setLoginPwdK(String loginPwdK) {
		this.loginPwdK = loginPwdK;
	}
	public String getLoginIdC() {
		return loginIdC;
	}
	public void setLoginIdC(String loginIdC) {
		this.loginIdC = loginIdC;
	}
	public String getLoginPwdC() {
		return loginPwdC;
	}
	public void setLoginPwdC(String loginPwdC) {
		this.loginPwdC = loginPwdC;
	}
	public String getLoginIdG() {
		return loginIdG;
	}
	public void setLoginIdG(String loginIdG) {
		this.loginIdG = loginIdG;
	}
	public String getLoginPwdG() {
		return loginPwdG;
	}
	public void setLoginPwdG(String loginPwdG) {
		this.loginPwdG = loginPwdG;
	}
	public String getRpsStno() {
		return rpsStno;
	}
	public void setRpsStno(String rpsStno) {
		this.rpsStno = rpsStno;
	}
	public String getTmpPwdYn() {
		return tmpPwdYn;
	}
	public void setTmpPwdYn(String tmpPwdYn) {
		this.tmpPwdYn = tmpPwdYn;
	}
	public String getTmpPwdLimitYn() {
		return tmpPwdLimitYn;
	}
	public void setTmpPwdLimitYn(String tmpPwdLimitYn) {
		this.tmpPwdLimitYn = tmpPwdLimitYn;
	}
	public String getPwdChngLimitYn() {
		return pwdChngLimitYn;
	}
	public void setPwdChngLimitYn(String pwdChngLimitYn) {
		this.pwdChngLimitYn = pwdChngLimitYn;
	}
	public String getEtrNm() {
		return etrNm;
	}
	public void setEtrNm(String etrNm) {
		this.etrNm = etrNm;
	}
	public String getEtrNo() {
		return etrNo;
	}
	public void setEtrNo(String etrNo) {
		this.etrNo = etrNo;
	}
	public String getBizJangNo() {
		return bizJangNo;
	}
	public void setBizJangNo(String bizJangNo) {
		this.bizJangNo = bizJangNo;
	}
	public String getNwrk200tlDelYn() {
		return nwrk200tlDelYn;
	}
	public void setNwrk200tlDelYn(String nwrk200tlDelYn) {
		this.nwrk200tlDelYn = nwrk200tlDelYn;
	}
	public String getSsoMsg() {
		return ssoMsg;
	}
	public void setSsoMsg(String ssoMsg) {
		this.ssoMsg = ssoMsg;
	}
	
	/*
	private String loginDiv;					//접속구분(mgnt:1, support:2, portal:3)
	
	
	
	private String userNm;					//사용자이름
	private String mbrDc;					//회원구분코드
	private String optrAuthCd;					//사용자권한코드
	private String pwdFailTms;				//로그인실패횟수
	private String exprYn;						//계정 만기 여부
	private String loginTime;					//접속시간
	private String remoteAddr;					//접속IP
	private String email;						//이메일
	private String mblYn;						//모바일여부
	private String cnntInfo;					//접속정보
	private String osCd;						//os정보
	private String sexDc;						//성별
	private String bird;						//생년월일
	// 에러나서 추가
	private String blngCd;
	private String blngNm;
	
	
	*/

	
}
