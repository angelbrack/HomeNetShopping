package site.userRole.service;

import java.io.Serializable;

import javax.validation.constraints.Size;

import prjframework.common.util.PagerVO;

@SuppressWarnings("serial")
public class UserRoleVO extends PagerVO implements Serializable  {
	
	private String optrAuthNm;
	private String useYn;
	private String optrAuthCd;
	
	private String[] optrPgmNos;
	private String[] wrAuthYns;
	private String[] delAuthYns;
	private String[] readAuthYns;
	private String optrAuthNo;
	private String optrAuthKrNm;
	private String optrAuthEnNm;
	private String optrAuthZhNm;
	private String optrMenuNos;
	private String wrUserNo;
	private String optrPgmNo;
	private String wrAuthYn;
	private String delAuthYn;
	private String readAuthYn;
	private String loginUserNo;
	private String glbTpCd;
	private String remoteAddr;
	public String[] getOptrPgmNos() {
		return optrPgmNos;
	}
	public void setOptrPgmNos(String[] optrPgmNos) {
		this.optrPgmNos = optrPgmNos;
	}
	public String[] getWrAuthYns() {
		return wrAuthYns;
	}
	public void setWrAuthYns(String[] wrAuthYns) {
		this.wrAuthYns = wrAuthYns;
	}
	public String[] getDelAuthYns() {
		return delAuthYns;
	}
	public void setDelAuthYns(String[] delAuthYns) {
		this.delAuthYns = delAuthYns;
	}
	public String[] getReadAuthYns() {
		return readAuthYns;
	}
	public void setReadAuthYns(String[] readAuthYns) {
		this.readAuthYns = readAuthYns;
	}
	public String getOptrAuthNo() {
		return optrAuthNo;
	}
	public void setOptrAuthNo(String optrAuthNo) {
		this.optrAuthNo = optrAuthNo;
	}
	public String getOptrAuthKrNm() {
		return optrAuthKrNm;
	}
	public void setOptrAuthKrNm(String optrAuthKrNm) {
		this.optrAuthKrNm = optrAuthKrNm;
	}
	public String getOptrAuthZhNm() {
		return optrAuthZhNm;
	}
	public void setOptrAuthZhNm(String optrAuthZhNm) {
		this.optrAuthZhNm = optrAuthZhNm;
	}
	public String getOptrAuthEnNm() {
		return optrAuthEnNm;
	}
	public void setOptrAuthEnNm(String optrAuthEnNm) {
		this.optrAuthEnNm = optrAuthEnNm;
	}
	public String getWrUserNo() {
		return wrUserNo;
	}
	public void setWrUserNo(String wrUserNo) {
		this.wrUserNo = wrUserNo;
	}
	public String getWrAuthYn() {
		return wrAuthYn;
	}
	public void setWrAuthYn(String wrAuthYn) {
		this.wrAuthYn = wrAuthYn;
	}
	public String getOptrPgmNo() {
		return optrPgmNo;
	}
	public void setOptrPgmNo(String optrPgmNo) {
		this.optrPgmNo = optrPgmNo;
	}
	public String getDelAuthYn() {
		return delAuthYn;
	}
	public void setDelAuthYn(String delAuthYn) {
		this.delAuthYn = delAuthYn;
	}
	public String getReadAuthYn() {
		return readAuthYn;
	}
	public void setReadAuthYn(String readAuthYn) {
		this.readAuthYn = readAuthYn;
	}
	public String getLoginUserNo() {
		return loginUserNo;
	}
	public void setLoginUserNo(String loginUserNo) {
		this.loginUserNo = loginUserNo;
	}
	public String getGlbTpCd() {
		return glbTpCd;
	}
	public void setGlbTpCd(String glbTpCd) {
		this.glbTpCd = glbTpCd;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getOptrMenuNos() {
		return optrMenuNos;
	}
	public void setOptrMenuNos(String optrMenuNos) {
		this.optrMenuNos = optrMenuNos;
	}
	public String getOptrAuthNm() {
		return optrAuthNm;
	}
	public void setOptrAuthNm(String optrAuthNm) {
		this.optrAuthNm = optrAuthNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getOptrAuthCd() {
		return optrAuthCd;
	}
	public void setOptrAuthCd(String optrAuthCd) {
		this.optrAuthCd = optrAuthCd;
	}
	
	
}
