package site.common.service.impl;

import java.io.Serializable;

import lombok.Data;

@Data
public class IndvInfoVO implements Serializable {
	static final long serialVersionUID = -70343566498577864L;
	/*
	 @AssertFalse : 해당 속성의 값이 false인지 체크한다.
	 @AssertTrue : 해당 속성의 값이 true인지 체크한다.
	 @DecimalMax : 해당 속성이 가질 수 있는 최대값을 체크한다.
	 @DecimalMin : 해당 속성이 가질 수 있는 최소값을 체크한다.
	 @Digits : 해당 속성이 가질 수 있는 정수부의 자리수와 소수부의 자리수를 체크한다.
	 @Future : 해당 속성의 값이 현재일 이후인지 체크한다.
	 @Max : 해당 속성이 가질 수 있는 최대 Length를 체크한다
	 @Min : 해당 속성이 가질 수 있는 최소 Length를 체크한다.
	 @NotNull : 해당 속성의 값이 Null이 아닌지 체크한다.
	 @Null : 해당 속성의 값이 Null인지 체크한다.
	 @Past : 해당 속성의 값이 현재일 이전인지 체크한다.
	 @Pattern : 해당 속성의 값이 정의된 Regular Expression에 부합하는지 체크한다. Regular Expression은 Java Regular Expression Convention (see java.util.regex.Pattern)에 맞게 정의해야 한다.
	 @Size : 해당 속성이 가질 수 있는 최대, 최소 Length를 체크한다
	 @Valid : 해당 객체에 대해 Validation Check가 이루어진다.
	 */
	/* 	HIST_SEQ_NO, MENU_NO, MENU_NM, INDV_INFO_INQ_DIV_V, INDV_INFO_INQ_RSN_CN,
QUERY_EXE_CN, INQ_T, CONN_USER_NO, CONN_USER_NM, CONN_DTM,
CONN_IP, DCARD_YN, DCARD_DTM, DCARD_IP, DCARD_USER_NO,
INDV_INFO_INQ_ITM,
 */	
	private String histSeqNo; 
	private String menuNo; 
	private String menuNm; 
	private String indvInfoInqRsnCn;
	private String queryExeCn; 
	private String inqT; 
	private String connUserNo; 
	private String connUserNm; 
	private String connDtm;
	private String connIp; 
	private String dcardYn; 
	private String dcardDtm; 
	private String dcardIp; 
	private String dcardUserNo;
	private String optrPgmNo;
	private String optrPgmNm; 
	private String optrPgmUrlV;
	public String getHistSeqNo() {
		return histSeqNo;
	}
	public void setHistSeqNo(String histSeqNo) {
		this.histSeqNo = histSeqNo;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getIndvInfoInqRsnCn() {
		return indvInfoInqRsnCn;
	}
	public void setIndvInfoInqRsnCn(String indvInfoInqRsnCn) {
		this.indvInfoInqRsnCn = indvInfoInqRsnCn;
	}
	public String getQueryExeCn() {
		return queryExeCn;
	}
	public void setQueryExeCn(String queryExeCn) {
		this.queryExeCn = queryExeCn;
	}
	public String getInqT() {
		return inqT;
	}
	public void setInqT(String inqT) {
		this.inqT = inqT;
	}
	public String getConnUserNo() {
		return connUserNo;
	}
	public void setConnUserNo(String connUserNo) {
		this.connUserNo = connUserNo;
	}
	public String getConnUserNm() {
		return connUserNm;
	}
	public void setConnUserNm(String connUserNm) {
		this.connUserNm = connUserNm;
	}
	public String getConnDtm() {
		return connDtm;
	}
	public void setConnDtm(String connDtm) {
		this.connDtm = connDtm;
	}
	public String getConnIp() {
		return connIp;
	}
	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}
	public String getDcardYn() {
		return dcardYn;
	}
	public void setDcardYn(String dcardYn) {
		this.dcardYn = dcardYn;
	}
	public String getDcardDtm() {
		return dcardDtm;
	}
	public void setDcardDtm(String dcardDtm) {
		this.dcardDtm = dcardDtm;
	}
	public String getDcardIp() {
		return dcardIp;
	}
	public void setDcardIp(String dcardIp) {
		this.dcardIp = dcardIp;
	}
	public String getDcardUserNo() {
		return dcardUserNo;
	}
	public void setDcardUserNo(String dcardUserNo) {
		this.dcardUserNo = dcardUserNo;
	}
	public String getOptrPgmNo() {
		return optrPgmNo;
	}
	public void setOptrPgmNo(String optrPgmNo) {
		this.optrPgmNo = optrPgmNo;
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

	
}
