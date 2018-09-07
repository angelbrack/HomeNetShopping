package site.member.service;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import prjframework.common.util.PagerVO;

@Data
@SuppressWarnings("serial")
public class MemberIdsearchVO extends PagerVO implements Serializable {

	private String loginId;
	private String loginIdP;
	private String userNmI;
	private String userNmP;
	private String emailI;
	private String emailP;
	private String password;
	private String loginPwdA;
	private String loginPwdS;
	
	private String searchType;
	
    private String PORT_T_NO;
    private String PORT_L_NO;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginIdP() {
		return loginIdP;
	}
	public void setLoginIdP(String loginIdP) {
		this.loginIdP = loginIdP;
	}
	public String getUserNmI() {
		return userNmI;
	}
	public void setUserNmI(String userNmI) {
		this.userNmI = userNmI;
	}
	public String getUserNmP() {
		return userNmP;
	}
	public void setUserNmP(String userNmP) {
		this.userNmP = userNmP;
	}
	public String getEmailI() {
		return emailI;
	}
	public void setEmailI(String emailI) {
		this.emailI = emailI;
	}
	public String getEmailP() {
		return emailP;
	}
	public void setEmailP(String emailP) {
		this.emailP = emailP;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginPwdA() {
		return loginPwdA;
	}
	public void setLoginPwdA(String loginPwdA) {
		this.loginPwdA = loginPwdA;
	}
	public String getLoginPwdS() {
		return loginPwdS;
	}
	public void setLoginPwdS(String loginPwdS) {
		this.loginPwdS = loginPwdS;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getPORT_T_NO() {
		return PORT_T_NO;
	}
	public void setPORT_T_NO(String pORT_T_NO) {
		PORT_T_NO = pORT_T_NO;
	}
	public String getPORT_L_NO() {
		return PORT_L_NO;
	}
	public void setPORT_L_NO(String pORT_L_NO) {
		PORT_L_NO = pORT_L_NO;
	}
	
    
}