package site.auth.service;

import java.io.Serializable;

import prjframework.common.util.PagerVO;
import lombok.Data;

@Data

public class AuthSearchVO extends PagerVO implements Serializable {
	private static final long serialVersionUID = 8628951365417291543L;

	private String searchOptrApprStatCd = null;
	private String searchOptrAuthNo = null;
	private String searchUserNo = null;
	private String searchCdId = null;
	private String searchMbrDc = null;
	
	private String searchKey = "";
	private String searchWord = "";
	private String searchValue = "";
	private String userNo = "";
	private String optrExpyDttm = "";
	
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
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getOptrExpyDttm() {
		return optrExpyDttm;
	}
	public void setOptrExpyDttm(String optrExpyDttm) {
		this.optrExpyDttm = optrExpyDttm;
	}
	public String getSearchMbrDc() {
		return searchMbrDc;
	}
	public void setSearchMbrDc(String searchMbrDc) {
		this.searchMbrDc = searchMbrDc;
	}
	
}
