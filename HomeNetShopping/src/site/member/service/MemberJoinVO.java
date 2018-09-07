package site.member.service;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import prjframework.common.util.PagerVO;

@Data
@SuppressWarnings("serial")
public class MemberJoinVO extends PagerVO implements Serializable {
	
	@Size(min=4, max=8, message="{errors.required} {errors.minmaxlength}") private String loginId;
	@Size(min=1, max=100, message="{errors.required} {errors.minmaxlength}") private String userNm;
	@Size(max=100, message="{errors.maxlength}") private String engUserNm;
	@Size(min=8, max=15, message="{errors.required} {errors.minmaxlength}") private String loginPwd;
	@Size(min=8, max=8, message="{errors.required} {errors.minlength}") private String birthday;
	@Size(min=1, message="{errors.required}") private String sexCd;
	@Size(min=1, message="{errors.required}") private String nattyCd;
	@Size(max=100, message="{errors.maxlength}") private String psprNo;
	@Size(min=1, max=20, message="{errors.required} {errors.minlength}") private String zipCd;
	@Size(min=1, max=500, message="{errors.required} {errors.minlength}") private String pnoAddr;
	@Size(max=500, message="{errors.maxlength}") private String bpnoAddr;
	@Size(max=11, message="{errors.maxlength}") private String telNo;
	@Size(min=10, max=11, message="{errors.required} {errors.minlength}") private String hpNo;
	@Size(min=1, max=50, message="{errors.required} {errors.minlength}") private String email;
	@Size(min=1, message="{errors.required}") private String smsRcvYn;
	@Size(min=1, message="{errors.required}") private String emailRcvYn;
	@Size(min=1, max=10, message="{errors.required}") private String blngCd;
	@Size(max=100, message="{errors.maxlength}") private String deptNm;
	@Size(max=100, message="{errors.maxlength}") private String psclNm;
	
	private String telNo1;
	private String telNo2;
	private String telNo3;
	private String hpNo1;
	private String hpNo2;
	private String hpNo3;
	private String userNo;
	private String mbrWtdrDtm;
	private String mbrWtdrRsnCn;
	private String mbrDivDd;
	private String mbrRegTpCd;
	private String mbrJoinDtm;
	private String indvAgmtDtm;
	private String wrtPerNo;
	private String wrtDttm;
	private String updtPnNo;
	private String updtDttm;
	private String frnYn;
	private String pwdFailTms;
	private String optrAuthCd;
	private String userAuthExpyDt;
	private String optrApprStatCd;
	private String mbrYn;
	private String userRegDtm;
	private String optrExpyDttm;
	private String optrApprPerNo;
	private String mbrLastLoginDtm;
	private String optrLastLoginDtm;
	private String exfireYn;
	private String optrApprYn;
	private String userAddrDivCd;
	private String cntryCd;
	private String userTelDivCd;
	private String cntryNo;
	private String atchFileNo;
	private String atchFileDivCd;
	private String atchFileNm;
	private String atchFileSaveNm;
	private String atchFileSz;
	private String blngNm;
	private String blngDivCd;
	private String blngDivNm;
	private String cntryNoT;
	private String cntryNoH;
	private String searchKey;
	private String searchWord;
	private String chkId;
	private String chkEmail;
	private String usedCnt;
	private String resultId;
	private String loginPwd1;
	private String loginPwdS;
	private String loginPwdA;
	private String chkLoginId;
	private String chkStrEmail;
	private String certCd;
	private String certNm;
	private String certCds[];
	
    private String PORT_T_NO;
    private String PORT_L_NO;
    
    private String di;
    private String ci;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getEngUserNm() {
		return engUserNm;
	}
	public void setEngUserNm(String engUserNm) {
		this.engUserNm = engUserNm;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getNattyCd() {
		return nattyCd;
	}
	public void setNattyCd(String nattyCd) {
		this.nattyCd = nattyCd;
	}
	public String getPsprNo() {
		return psprNo;
	}
	public void setPsprNo(String psprNo) {
		this.psprNo = psprNo;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getPnoAddr() {
		return pnoAddr;
	}
	public void setPnoAddr(String pnoAddr) {
		this.pnoAddr = pnoAddr;
	}
	public String getBpnoAddr() {
		return bpnoAddr;
	}
	public void setBpnoAddr(String bpnoAddr) {
		this.bpnoAddr = bpnoAddr;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSmsRcvYn() {
		return smsRcvYn;
	}
	public void setSmsRcvYn(String smsRcvYn) {
		this.smsRcvYn = smsRcvYn;
	}
	public String getEmailRcvYn() {
		return emailRcvYn;
	}
	public void setEmailRcvYn(String emailRcvYn) {
		this.emailRcvYn = emailRcvYn;
	}
	public String getBlngCd() {
		return blngCd;
	}
	public void setBlngCd(String blngCd) {
		this.blngCd = blngCd;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getPsclNm() {
		return psclNm;
	}
	public void setPsclNm(String psclNm) {
		this.psclNm = psclNm;
	}
	public String getTelNo1() {
		return telNo1;
	}
	public void setTelNo1(String telNo1) {
		this.telNo1 = telNo1;
	}
	public String getTelNo2() {
		return telNo2;
	}
	public void setTelNo2(String telNo2) {
		this.telNo2 = telNo2;
	}
	public String getTelNo3() {
		return telNo3;
	}
	public void setTelNo3(String telNo3) {
		this.telNo3 = telNo3;
	}
	public String getHpNo1() {
		return hpNo1;
	}
	public void setHpNo1(String hpNo1) {
		this.hpNo1 = hpNo1;
	}
	public String getHpNo2() {
		return hpNo2;
	}
	public void setHpNo2(String hpNo2) {
		this.hpNo2 = hpNo2;
	}
	public String getHpNo3() {
		return hpNo3;
	}
	public void setHpNo3(String hpNo3) {
		this.hpNo3 = hpNo3;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getMbrWtdrDtm() {
		return mbrWtdrDtm;
	}
	public void setMbrWtdrDtm(String mbrWtdrDtm) {
		this.mbrWtdrDtm = mbrWtdrDtm;
	}
	public String getMbrWtdrRsnCn() {
		return mbrWtdrRsnCn;
	}
	public void setMbrWtdrRsnCn(String mbrWtdrRsnCn) {
		this.mbrWtdrRsnCn = mbrWtdrRsnCn;
	}
	public String getMbrDivDd() {
		return mbrDivDd;
	}
	public void setMbrDivDd(String mbrDivDd) {
		this.mbrDivDd = mbrDivDd;
	}
	public String getMbrRegTpCd() {
		return mbrRegTpCd;
	}
	public void setMbrRegTpCd(String mbrRegTpCd) {
		this.mbrRegTpCd = mbrRegTpCd;
	}
	public String getMbrJoinDtm() {
		return mbrJoinDtm;
	}
	public void setMbrJoinDtm(String mbrJoinDtm) {
		this.mbrJoinDtm = mbrJoinDtm;
	}
	public String getIndvAgmtDtm() {
		return indvAgmtDtm;
	}
	public void setIndvAgmtDtm(String indvAgmtDtm) {
		this.indvAgmtDtm = indvAgmtDtm;
	}
	public String getWrtPerNo() {
		return wrtPerNo;
	}
	public void setWrtPerNo(String wrtPerNo) {
		this.wrtPerNo = wrtPerNo;
	}
	public String getWrtDttm() {
		return wrtDttm;
	}
	public void setWrtDttm(String wrtDttm) {
		this.wrtDttm = wrtDttm;
	}
	public String getUpdtPnNo() {
		return updtPnNo;
	}
	public void setUpdtPnNo(String updtPnNo) {
		this.updtPnNo = updtPnNo;
	}
	public String getUpdtDttm() {
		return updtDttm;
	}
	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}
	public String getFrnYn() {
		return frnYn;
	}
	public void setFrnYn(String frnYn) {
		this.frnYn = frnYn;
	}
	public String getPwdFailTms() {
		return pwdFailTms;
	}
	public void setPwdFailTms(String pwdFailTms) {
		this.pwdFailTms = pwdFailTms;
	}
	public String getOptrAuthCd() {
		return optrAuthCd;
	}
	public void setOptrAuthCd(String optrAuthCd) {
		this.optrAuthCd = optrAuthCd;
	}
	public String getUserAuthExpyDt() {
		return userAuthExpyDt;
	}
	public void setUserAuthExpyDt(String userAuthExpyDt) {
		this.userAuthExpyDt = userAuthExpyDt;
	}
	public String getOptrApprStatCd() {
		return optrApprStatCd;
	}
	public void setOptrApprStatCd(String optrApprStatCd) {
		this.optrApprStatCd = optrApprStatCd;
	}
	public String getMbrYn() {
		return mbrYn;
	}
	public void setMbrYn(String mbrYn) {
		this.mbrYn = mbrYn;
	}
	public String getUserRegDtm() {
		return userRegDtm;
	}
	public void setUserRegDtm(String userRegDtm) {
		this.userRegDtm = userRegDtm;
	}
	public String getOptrExpyDttm() {
		return optrExpyDttm;
	}
	public void setOptrExpyDttm(String optrExpyDttm) {
		this.optrExpyDttm = optrExpyDttm;
	}
	public String getOptrApprPerNo() {
		return optrApprPerNo;
	}
	public void setOptrApprPerNo(String optrApprPerNo) {
		this.optrApprPerNo = optrApprPerNo;
	}
	public String getMbrLastLoginDtm() {
		return mbrLastLoginDtm;
	}
	public void setMbrLastLoginDtm(String mbrLastLoginDtm) {
		this.mbrLastLoginDtm = mbrLastLoginDtm;
	}
	public String getOptrLastLoginDtm() {
		return optrLastLoginDtm;
	}
	public void setOptrLastLoginDtm(String optrLastLoginDtm) {
		this.optrLastLoginDtm = optrLastLoginDtm;
	}
	public String getExfireYn() {
		return exfireYn;
	}
	public void setExfireYn(String exfireYn) {
		this.exfireYn = exfireYn;
	}
	public String getOptrApprYn() {
		return optrApprYn;
	}
	public void setOptrApprYn(String optrApprYn) {
		this.optrApprYn = optrApprYn;
	}
	public String getUserAddrDivCd() {
		return userAddrDivCd;
	}
	public void setUserAddrDivCd(String userAddrDivCd) {
		this.userAddrDivCd = userAddrDivCd;
	}
	public String getCntryCd() {
		return cntryCd;
	}
	public void setCntryCd(String cntryCd) {
		this.cntryCd = cntryCd;
	}
	public String getUserTelDivCd() {
		return userTelDivCd;
	}
	public void setUserTelDivCd(String userTelDivCd) {
		this.userTelDivCd = userTelDivCd;
	}
	public String getCntryNo() {
		return cntryNo;
	}
	public void setCntryNo(String cntryNo) {
		this.cntryNo = cntryNo;
	}
	public String getAtchFileNo() {
		return atchFileNo;
	}
	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}
	public String getAtchFileDivCd() {
		return atchFileDivCd;
	}
	public void setAtchFileDivCd(String atchFileDivCd) {
		this.atchFileDivCd = atchFileDivCd;
	}
	public String getAtchFileNm() {
		return atchFileNm;
	}
	public void setAtchFileNm(String atchFileNm) {
		this.atchFileNm = atchFileNm;
	}
	public String getAtchFileSaveNm() {
		return atchFileSaveNm;
	}
	public void setAtchFileSaveNm(String atchFileSaveNm) {
		this.atchFileSaveNm = atchFileSaveNm;
	}
	public String getAtchFileSz() {
		return atchFileSz;
	}
	public void setAtchFileSz(String atchFileSz) {
		this.atchFileSz = atchFileSz;
	}
	public String getBlngNm() {
		return blngNm;
	}
	public void setBlngNm(String blngNm) {
		this.blngNm = blngNm;
	}
	public String getBlngDivCd() {
		return blngDivCd;
	}
	public void setBlngDivCd(String blngDivCd) {
		this.blngDivCd = blngDivCd;
	}
	public String getBlngDivNm() {
		return blngDivNm;
	}
	public void setBlngDivNm(String blngDivNm) {
		this.blngDivNm = blngDivNm;
	}
	public String getCntryNoT() {
		return cntryNoT;
	}
	public void setCntryNoT(String cntryNoT) {
		this.cntryNoT = cntryNoT;
	}
	public String getCntryNoH() {
		return cntryNoH;
	}
	public void setCntryNoH(String cntryNoH) {
		this.cntryNoH = cntryNoH;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getChkId() {
		return chkId;
	}
	public void setChkId(String chkId) {
		this.chkId = chkId;
	}
	public String getChkEmail() {
		return chkEmail;
	}
	public void setChkEmail(String chkEmail) {
		this.chkEmail = chkEmail;
	}
	public String getUsedCnt() {
		return usedCnt;
	}
	public void setUsedCnt(String usedCnt) {
		this.usedCnt = usedCnt;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getLoginPwd1() {
		return loginPwd1;
	}
	public void setLoginPwd1(String loginPwd1) {
		this.loginPwd1 = loginPwd1;
	}
	public String getLoginPwdS() {
		return loginPwdS;
	}
	public void setLoginPwdS(String loginPwdS) {
		this.loginPwdS = loginPwdS;
	}
	public String getLoginPwdA() {
		return loginPwdA;
	}
	public void setLoginPwdA(String loginPwdA) {
		this.loginPwdA = loginPwdA;
	}
	public String getChkLoginId() {
		return chkLoginId;
	}
	public void setChkLoginId(String chkLoginId) {
		this.chkLoginId = chkLoginId;
	}
	public String getChkStrEmail() {
		return chkStrEmail;
	}
	public void setChkStrEmail(String chkStrEmail) {
		this.chkStrEmail = chkStrEmail;
	}
	public String getCertCd() {
		return certCd;
	}
	public void setCertCd(String certCd) {
		this.certCd = certCd;
	}
	public String getCertNm() {
		return certNm;
	}
	public void setCertNm(String certNm) {
		this.certNm = certNm;
	}
	public String[] getCertCds() {
		return certCds;
	}
	public void setCertCds(String[] certCds) {
		this.certCds = certCds;
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
	public String getDi() {
		return di;
	}
	public void setDi(String di) {
		this.di = di;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
    
    
	
}