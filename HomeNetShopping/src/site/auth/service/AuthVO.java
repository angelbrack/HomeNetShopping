package site.auth.service;

import java.io.Serializable;

import javax.validation.constraints.Size;

import prjframework.common.util.PagerVO;

import lombok.Data;

@SuppressWarnings("serial")
public class AuthVO extends PagerVO implements Serializable {

	@Size(min = 1, message="{errors.required} ")private String optrAuthCd = null;
	@Size(min = 1, message="{errors.required} ")private String optrApprStatCd = null;
	@Size(min = 1, message="{errors.required} ")private String optrExpyDttm = null;

	private String[] optrAuthNo = null;
	private String userNo = null;
	private String optrAuthNoEach = null;
	private String wrtPerNo = null;
	private String univCd = null;
	private String blngNm = null;
	private String searchKey = "";
	private String searchWord = "";
	private String searchOptrApprStatCd = "";
	private String searchOptrAuthNo = "";
	private String searchValue = "";
	private String searchUserNo = null;
	private String searchCdId = null;
	private String remoteAddr = null;
	public String getOptrAuthCd() {
		return optrAuthCd;
	}
	public void setOptrAuthCd(String optrAuthCd) {
		this.optrAuthCd = optrAuthCd;
	}
	public String getOptrApprStatCd() {
		return optrApprStatCd;
	}
	public void setOptrApprStatCd(String optrApprStatCd) {
		this.optrApprStatCd = optrApprStatCd;
	}
	public String[] getOptrAuthNo() {
		return optrAuthNo;
	}
	public void setOptrAuthNo(String[] optrAuthNo) {
		this.optrAuthNo = optrAuthNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getOptrAuthNoEach() {
		return optrAuthNoEach;
	}
	public void setOptrAuthNoEach(String optrAuthNoEach) {
		this.optrAuthNoEach = optrAuthNoEach;
	}
	public String getWrtPerNo() {
		return wrtPerNo;
	}
	public void setWrtPerNo(String wrtPerNo) {
		this.wrtPerNo = wrtPerNo;
	}
	public String getUnivCd() {
		return univCd;
	}
	public void setUnivCd(String univCd) {
		this.univCd = univCd;
	}
	public String getBlngNm() {
		return blngNm;
	}
	public void setBlngNm(String blngNm) {
		this.blngNm = blngNm;
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
	public String getSearchOptrApprStatCd() {
		return searchOptrApprStatCd;
	}
	public void setSearchOptrApprStatCd(String searchOptrApprStatCd) {
		this.searchOptrApprStatCd = searchOptrApprStatCd;
	}
	public String getSearchOptrAuthNo() {
		return searchOptrAuthNo;
	}
	public void setSearchOptrAuthNo(String searchOptrAuthNo) {
		this.searchOptrAuthNo = searchOptrAuthNo;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getSearchUserNo() {
		return searchUserNo;
	}
	public void setSearchUserNo(String searchUserNo) {
		this.searchUserNo = searchUserNo;
	}
	public String getSearchCdId() {
		return searchCdId;
	}
	public void setSearchCdId(String searchCdId) {
		this.searchCdId = searchCdId;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getOptrExpyDttm() {
		return optrExpyDttm;
	}
	public void setOptrExpyDttm(String optrExpyDttm) {
		this.optrExpyDttm = optrExpyDttm;
	}
	
}
