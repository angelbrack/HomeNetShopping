package homenet.shop.article.service;

import prjframework.common.util.PagerVO;

public class GoodsArtcCdVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private String  artcCd			; // 품목코드
	private String  ecArtcCd 		; // 전자상거래품목코드
	private String  uprArtcCd 	    ; // 상위 품목코드
	private Integer artcDpthNo 	    ; // 품목깊이번호
	private String  artcNm    		; // 품목명
	private String  artcFullNm    	; // 품목FULL명
	private Integer onlBrchInvRt	; // 재고율
	private Integer maxLmtQty		; // 최대구매수량
	private Integer goodsMrgnRt		; // 상품마진율
	private String  sizeLktbFileNm	; // 사이즈조견표 파일명
	private String  sizeLktbPathNm	; // 사이즈조견표 파일패스
	
	private String  ecArtcNm 		; // 전자상거래품목명
	private String  uprArtcNm		; // 상위 품목명
	private Integer childCount		; // 허위 품목 Count 
	
	public String getArtcCd() {
		return artcCd;
	}
	public void setArtcCd(String artcCd) {
		this.artcCd = artcCd;
	}
	public String getEcArtcCd() {
		return ecArtcCd;
	}
	public void setEcArtcCd(String ecArtcCd) {
		this.ecArtcCd = ecArtcCd;
	}
	public String getUprArtcCd() {
		return uprArtcCd;
	}
	public void setUprArtcCd(String uprArtcCd) {
		this.uprArtcCd = uprArtcCd;
	}
	public Integer getArtcDpthNo() {
		return artcDpthNo;
	}
	public void setArtcDpthNo(Integer artcDpthNo) {
		this.artcDpthNo = artcDpthNo;
	}
	public String getArtcNm() {
		return artcNm;
	}
	public void setArtcNm(String artcNm) {
		this.artcNm = artcNm;
	}
	public String getArtcFullNm() {
		return artcFullNm;
	}
	public void setArtcFullNm(String artcFullNm) {
		this.artcFullNm = artcFullNm;
	}
	public Integer getOnlBrchInvRt() {
		return onlBrchInvRt;
	}
	public void setOnlBrchInvRt(Integer onlBrchInvRt) {
		this.onlBrchInvRt = onlBrchInvRt;
	}
	public Integer getMaxLmtQty() {
		return maxLmtQty;
	}
	public void setMaxLmtQty(Integer maxLmtQty) {
		this.maxLmtQty = maxLmtQty;
	}
	public Integer getGoodsMrgnRt() {
		return goodsMrgnRt;
	}
	public void setGoodsMrgnRt(Integer goodsMrgnRt) {
		this.goodsMrgnRt = goodsMrgnRt;
	}
	public String getSizeLktbFileNm() {
		return sizeLktbFileNm;
	}
	public void setSizeLktbFileNm(String sizeLktbFileNm) {
		this.sizeLktbFileNm = sizeLktbFileNm;
	}
	public String getSizeLktbPathNm() {
		return sizeLktbPathNm;
	}
	public void setSizeLktbPathNm(String sizeLktbPathNm) {
		this.sizeLktbPathNm = sizeLktbPathNm;
	}
	public String getEcArtcNm() {
		return ecArtcNm;
	}
	public void setEcArtcNm(String ecArtcNm) {
		this.ecArtcNm = ecArtcNm;
	}
	public String getUprArtcNm() {
		return uprArtcNm;
	}
	public void setUprArtcNm(String uprArtcNm) {
		this.uprArtcNm = uprArtcNm;
	}
	public Integer getchildCount() {
		return childCount;
	}
	public void setchildCount(Integer childCount) {
		this.childCount = childCount;
	}
	
}
