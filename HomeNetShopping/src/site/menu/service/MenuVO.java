package site.menu.service;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class MenuVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3771983099656990129L;
	@Size(min = 1, max = 100, message="메뉴명은 필수 입력항목입니다. 1~100자리 글자를 입력하세요.") private String menuNm = null;
	@Size(min = 1, message="메뉴구분코드는 필수 입력항목입니다. 1~3자리 글자를 입력하세요.") private String useYn = null;
	@Size(min = 1, max = 5, message="정렬순서는 필수 입력항목입니다. 1~5자리 글자를 입력하세요.") private String sortOr = null;
	private String menuDivCd = null;

	private String[] menuNms = null;
	private String[] cdInsVs = null;
	private String menuNo= null;
	private String optrPgmNo = null;
	private String level = null;
	private String hgrkMenuNo = null;
	private String optrPgmNm = null;
	private String cdInsV = null;
	private String optrPgmUrlV = null;
	private String seqMenuNo = null;
	private String glbTpCd = null;
	private String loginUserNo = null;
	private String remoteAddr = null;
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSortOr() {
		return sortOr;
	}
	public void setSortOr(String sortOr) {
		this.sortOr = sortOr;
	}
	public String getMenuDivCd() {
		return menuDivCd;
	}
	public void setMenuDivCd(String menuDivCd) {
		this.menuDivCd = menuDivCd;
	}
	public String[] getMenuNms() {
		return menuNms;
	}
	public void setMenuNms(String[] menuNms) {
		this.menuNms = menuNms;
	}
	public String[] getCdInsVs() {
		return cdInsVs;
	}
	public void setCdInsVs(String[] cdInsVs) {
		this.cdInsVs = cdInsVs;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getOptrPgmNo() {
		return optrPgmNo;
	}
	public void setOptrPgmNo(String optrPgmNo) {
		this.optrPgmNo = optrPgmNo;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getHgrkMenuNo() {
		return hgrkMenuNo;
	}
	public void setHgrkMenuNo(String hgrkMenuNo) {
		this.hgrkMenuNo = hgrkMenuNo;
	}
	public String getOptrPgmNm() {
		return optrPgmNm;
	}
	public void setOptrPgmNm(String optrPgmNm) {
		this.optrPgmNm = optrPgmNm;
	}
	public String getCdInsV() {
		return cdInsV;
	}
	public void setCdInsV(String cdInsV) {
		this.cdInsV = cdInsV;
	}
	public String getOptrPgmUrlV() {
		return optrPgmUrlV;
	}
	public void setOptrPgmUrlV(String optrPgmUrlV) {
		this.optrPgmUrlV = optrPgmUrlV;
	}
	public String getSeqMenuNo() {
		return seqMenuNo;
	}
	public void setSeqMenuNo(String seqMenuNo) {
		this.seqMenuNo = seqMenuNo;
	}
	public String getGlbTpCd() {
		return glbTpCd;
	}
	public void setGlbTpCd(String glbTpCd) {
		this.glbTpCd = glbTpCd;
	}
	public String getLoginUserNo() {
		return loginUserNo;
	}
	public void setLoginUserNo(String loginUserNo) {
		this.loginUserNo = loginUserNo;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	
	
}
