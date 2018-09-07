package site.program.service;

import java.io.Serializable;

import prjframework.common.util.PagerVO;

public class ProgramVO extends PagerVO implements Serializable  {
	
	private static final long serialVersionUID = -412907473531301223L;
	private String optrPgmNo;
	private String optrPgmId;
	private String optrPgmUrlV;
	private String optrPgmNm;
	private String optrPgmGrpNo;
	private String menuUseYn;

	private String[] progNo; 			// 프로그램삭제시 선택된 항목 저장
	private String progGlbTpCd;		
	private String searchOptrAuthNo;  	//검색-역할번호
	private String searchMenuUseYn;  	//검색-메뉴사용여부
	private String searchOptrPgmGrpNo;  //검색-프로그램그룹번호
	private String searchKey;
	private String searchWord;
	private String loginUserNo;  //사용자번호
	private String remoteAddr;  //ip
	
	public String getOptrPgmNo() {
		return optrPgmNo;
	}
	public void setOptrPgmNo(String optrPgmNo) {
		this.optrPgmNo = optrPgmNo;
	}
	public String getOptrPgmId() {
		return optrPgmId;
	}
	public void setOptrPgmId(String optrPgmId) {
		this.optrPgmId = optrPgmId;
	}
	public String getOptrPgmUrlV() {
		return optrPgmUrlV;
	}
	public void setOptrPgmUrlV(String optrPgmUrlV) {
		this.optrPgmUrlV = optrPgmUrlV;
	}
	public String getOptrPgmNm() {
		return optrPgmNm;
	}
	public void setOptrPgmNm(String optrPgmNm) {
		this.optrPgmNm = optrPgmNm;
	}
	public String getOptrPgmGrpNo() {
		return optrPgmGrpNo;
	}
	public void setOptrPgmGrpNo(String optrPgmGrpNo) {
		this.optrPgmGrpNo = optrPgmGrpNo;
	}
	public String getMenuUseYn() {
		return menuUseYn;
	}
	public void setMenuUseYn(String menuUseYn) {
		this.menuUseYn = menuUseYn;
	}
	public String[] getProgNo() {
		return progNo;
	}
	public void setProgNo(String[] progNo) {
		this.progNo = progNo;
	}
	public String getProgGlbTpCd() {
		return progGlbTpCd;
	}
	public void setProgGlbTpCd(String progGlbTpCd) {
		this.progGlbTpCd = progGlbTpCd;
	}
	public String getSearchOptrAuthNo() {
		return searchOptrAuthNo;
	}
	public void setSearchOptrAuthNo(String searchOptrAuthNo) {
		this.searchOptrAuthNo = searchOptrAuthNo;
	}
	public String getSearchMenuUseYn() {
		return searchMenuUseYn;
	}
	public void setSearchMenuUseYn(String searchMenuUseYn) {
		this.searchMenuUseYn = searchMenuUseYn;
	}
	public String getSearchOptrPgmGrpNo() {
		return searchOptrPgmGrpNo;
	}
	public void setSearchOptrPgmGrpNo(String searchOptrPgmGrpNo) {
		this.searchOptrPgmGrpNo = searchOptrPgmGrpNo;
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
