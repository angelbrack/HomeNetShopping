package site.user.service;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import prjframework.common.util.PagerVO;

@Data
@SuppressWarnings("serial")
public class UserVO extends PagerVO implements Serializable  {
	
	/** 항공조사자관리 **/
	private String searchMbrYn = "";
	private String searchBlngCd = "";	//소속코드검색
	private String searchBlngNm = "";	//소속코드명검색
	private String mbrJoinDtm = "";
	
	private String mbrDc = "";		// 회원구분코드
	private String mbrDivNm = "";		// 회원구분명
	private String frnYn = "N";			// 외국인여부
	private String loginId = "";		// 로그인ID
	@Size(min=1, max=15, message="{errors.required} {errors.minmaxlength}") private String userNm = "";			// 성명
	@Size(max=30, message="{errors.maxlength}") private String engUserNm = "";		// 영문성명
	private String loginPwd = "";		// 로그인비밀번호
	@Size(min=8, max=8, message="{errors.required} {errors.minlength}") private String birthday = "";		// 생년월일
	@Size(min=1, message="{errors.required}") private String sexCd = "M";			// 성별코드	code564
	@Size(min=1, message="{errors.required}") private String nattyCd = "";		// 국적코드
	@Size(max=100, message="{errors.maxlength}") private String psprNo = "";			// 여권번호
	@Size(min=1, max=20, message="{errors.required} {errors.minlength}") private String zipCd = "";			// 우편번호
	@Size(min=1, max=500, message="{errors.required} {errors.minlength}") private String pnoAddr = "";		// 우편번호주소
	@Size(min=1, max=500, message="{errors.required} {errors.minlength}") private String bpnoAddr = "";		// 우편번호외주소
	private String cntryNoT = "";		// 유선번호 국가번호
	private String cntryNoH = "";		// 휴대폰번호 국가번호
	private String cntryCd = "";		// 주소 국가코드
	@Size(max=4, message="{errors.maxlength}") private String telNo1 = "";			// 전화번호1
	@Size(max=4, message="{errors.maxlength}") private String telNo2 = "";			// 전화번호2
	@Size(max=4, message="{errors.maxlength}") private String telNo3 = "";			// 전화번호3
	@Size(min=3, max=4, message="{errors.required} {errors.minlength}") private String hpNo1 = "";			// 휴대폰번호1
	@Size(min=3, max=4, message="{errors.required} {errors.minlength}") private String hpNo2 = "";			// 휴대폰번호2
	@Size(min=3, max=4, message="{errors.required} {errors.minlength}") private String hpNo3 = "";			// 휴대폰번호3
	@Size(min=1, max=50, message="{errors.required} {errors.minlength}") private String email = "";			// 이메일
	@Size(min=1, message="{errors.required}") private String smsRcvYn = "N";		// SMS수신여부
	@Size(min=1, message="{errors.required}") private String emailRcvYn = "N";		// 이메일수신여부
	@Size(min=1, max=10, message="{errors.required}") private String blngCd = "";			// 소속기관코드
	private String blngNm = "";			// 소속기관명
	@Size(max=100, message="{errors.maxlength}") private String deptNm = "";			// 부서명
	@Size(max=100, message="{errors.maxlength}") private String psclNm = "";			// 직급명
	private String mbrYn = "";			// 회원여부
	private String mbrWtdrDtm = "";		// 회원탈퇴일시
	@Size(min=1, message="{errors.required}") private String userNo = "";			// 사용자번호
	private String updtPnNo = "";		// 수정자 사용자번호
	private String excelflag = "";		// 엑셀여부
	
	private String callbackFunc;
	private String optrAuthCd;	//권한
	private String indvInfoInqRsnCn; //개인정보사유
	private String userTelDivCd; //전화번호 구분코드
	private String userAddrDivCd; //주소 구분코드
	
	private String[] arrCheck;
	private String[] arrComplHistNo;
	private String complHistNo;
	private String searchBirthday;
	private String searchCrsNm;
	private String searchUserNm;
	private String searchRegStatCd;
	private String certCds[];
	private String certCd;
	private String certNm;

    /** popup firstIndex */
    private int applyFirstIndex = 1;
    /** popup lastIndex */
    private int applyLastIndex = 1;
    /** popup recordCountPerPage */
    private int applyRecordCountPerPage = 10;
    /** popup currentPage **/
    private int applyCurrentPage = 1;
    /** 페이지사이즈 */
	private int applyPageSize = 10;
	public String getSearchMbrYn() {
		return searchMbrYn;
	}
	public void setSearchMbrYn(String searchMbrYn) {
		this.searchMbrYn = searchMbrYn;
	}
	public String getSearchBlngCd() {
		return searchBlngCd;
	}
	public void setSearchBlngCd(String searchBlngCd) {
		this.searchBlngCd = searchBlngCd;
	}
	public String getSearchBlngNm() {
		return searchBlngNm;
	}
	public void setSearchBlngNm(String searchBlngNm) {
		this.searchBlngNm = searchBlngNm;
	}
	public String getMbrJoinDtm() {
		return mbrJoinDtm;
	}
	public void setMbrJoinDtm(String mbrJoinDtm) {
		this.mbrJoinDtm = mbrJoinDtm;
	}
	public String getMbrDc() {
		return mbrDc;
	}
	public void setMbrDc(String mbrDc) {
		this.mbrDc = mbrDc;
	}
	public String getMbrDivNm() {
		return mbrDivNm;
	}
	public void setMbrDivNm(String mbrDivNm) {
		this.mbrDivNm = mbrDivNm;
	}
	public String getFrnYn() {
		return frnYn;
	}
	public void setFrnYn(String frnYn) {
		this.frnYn = frnYn;
	}
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
	public String getCntryCd() {
		return cntryCd;
	}
	public void setCntryCd(String cntryCd) {
		this.cntryCd = cntryCd;
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
	public String getBlngNm() {
		return blngNm;
	}
	public void setBlngNm(String blngNm) {
		this.blngNm = blngNm;
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
	public String getMbrYn() {
		return mbrYn;
	}
	public void setMbrYn(String mbrYn) {
		this.mbrYn = mbrYn;
	}
	public String getMbrWtdrDtm() {
		return mbrWtdrDtm;
	}
	public void setMbrWtdrDtm(String mbrWtdrDtm) {
		this.mbrWtdrDtm = mbrWtdrDtm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUpdtPnNo() {
		return updtPnNo;
	}
	public void setUpdtPnNo(String updtPnNo) {
		this.updtPnNo = updtPnNo;
	}
	public String getExcelflag() {
		return excelflag;
	}
	public void setExcelflag(String excelflag) {
		this.excelflag = excelflag;
	}
	public String getCallbackFunc() {
		return callbackFunc;
	}
	public void setCallbackFunc(String callbackFunc) {
		this.callbackFunc = callbackFunc;
	}
	public String getOptrAuthCd() {
		return optrAuthCd;
	}
	public void setOptrAuthCd(String optrAuthCd) {
		this.optrAuthCd = optrAuthCd;
	}
	public String getIndvInfoInqRsnCn() {
		return indvInfoInqRsnCn;
	}
	public void setIndvInfoInqRsnCn(String indvInfoInqRsnCn) {
		this.indvInfoInqRsnCn = indvInfoInqRsnCn;
	}
	public String getUserTelDivCd() {
		return userTelDivCd;
	}
	public void setUserTelDivCd(String userTelDivCd) {
		this.userTelDivCd = userTelDivCd;
	}
	public String getUserAddrDivCd() {
		return userAddrDivCd;
	}
	public void setUserAddrDivCd(String userAddrDivCd) {
		this.userAddrDivCd = userAddrDivCd;
	}
	public String[] getArrCheck() {
		return arrCheck;
	}
	public void setArrCheck(String[] arrCheck) {
		this.arrCheck = arrCheck;
	}
	public String[] getArrComplHistNo() {
		return arrComplHistNo;
	}
	public void setArrComplHistNo(String[] arrComplHistNo) {
		this.arrComplHistNo = arrComplHistNo;
	}
	public String getComplHistNo() {
		return complHistNo;
	}
	public void setComplHistNo(String complHistNo) {
		this.complHistNo = complHistNo;
	}
	public String getSearchBirthday() {
		return searchBirthday;
	}
	public void setSearchBirthday(String searchBirthday) {
		this.searchBirthday = searchBirthday;
	}
	public String getSearchCrsNm() {
		return searchCrsNm;
	}
	public void setSearchCrsNm(String searchCrsNm) {
		this.searchCrsNm = searchCrsNm;
	}
	public String getSearchUserNm() {
		return searchUserNm;
	}
	public void setSearchUserNm(String searchUserNm) {
		this.searchUserNm = searchUserNm;
	}
	public String getSearchRegStatCd() {
		return searchRegStatCd;
	}
	public void setSearchRegStatCd(String searchRegStatCd) {
		this.searchRegStatCd = searchRegStatCd;
	}
	public String[] getCertCds() {
		return certCds;
	}
	public void setCertCds(String[] certCds) {
		this.certCds = certCds;
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
	public int getApplyFirstIndex() {
		return applyFirstIndex;
	}
	public void setApplyFirstIndex(int applyFirstIndex) {
		this.applyFirstIndex = applyFirstIndex;
	}
	public int getApplyLastIndex() {
		return applyLastIndex;
	}
	public void setApplyLastIndex(int applyLastIndex) {
		this.applyLastIndex = applyLastIndex;
	}
	public int getApplyRecordCountPerPage() {
		return applyRecordCountPerPage;
	}
	public void setApplyRecordCountPerPage(int applyRecordCountPerPage) {
		this.applyRecordCountPerPage = applyRecordCountPerPage;
	}
	public int getApplyCurrentPage() {
		return applyCurrentPage;
	}
	public void setApplyCurrentPage(int applyCurrentPage) {
		this.applyCurrentPage = applyCurrentPage;
	}
	public int getApplyPageSize() {
		return applyPageSize;
	}
	public void setApplyPageSize(int applyPageSize) {
		this.applyPageSize = applyPageSize;
	}
    
	
}
