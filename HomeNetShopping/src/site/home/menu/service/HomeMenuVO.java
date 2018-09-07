package site.home.menu.service;

import java.io.Serializable;

import lombok.Data;

public class HomeMenuVO implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 3106716000301977202L;
	private String lvl; 
	private String sortOr;
	private String menuNo;
	private String hgrkMenuNo;
	private String optrPgmNo;
	private String menuNm;
	private String optrPgmNm;
	private String optrPgmUrlV;
	private String lastYn;
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getSortOr() {
		return sortOr;
	}
	public void setSortOr(String sortOr) {
		this.sortOr = sortOr;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getHgrkMenuNo() {
		return hgrkMenuNo;
	}
	public void setHgrkMenuNo(String hgrkMenuNo) {
		this.hgrkMenuNo = hgrkMenuNo;
	}
	public String getOptrPgmNo() {
		return optrPgmNo;
	}
	public void setOptrPgmNo(String optrPgmNo) {
		this.optrPgmNo = optrPgmNo;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getOptrPgmNm() {
		return optrPgmNm;
	}
	public void setOptrPgmNm(String optrPgmNm) {
		this.optrPgmNm = optrPgmNm;
	}
	public String getOptrPgmUrlV() {
		return optrPgmUrlV;
	}
	public void setOptrPgmUrlV(String optrPgmUrlV) {
		this.optrPgmUrlV = optrPgmUrlV;
	}
	public String getLastYn() {
		return lastYn;
	}
	public void setLastYn(String lastYn) {
		this.lastYn = lastYn;
	}
	
		
}
