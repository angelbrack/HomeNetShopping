package homenet.shop.article.service;

import java.util.List;

import prjframework.common.util.PagerVO;

public class GoodsArtcCdVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private Integer searchArtcDpthNo 	    	; // 검색 품목깊이번호
	private String  searchConnectByDeniedYn		; // 검색 Connect By 거부 여부
	private String  searchArtcUpCd 	            ; // 검색 품목군코드(품목깊이번호 2인 품목코드)
	private String  searchArtcCd 	            ; // 검색 품목코드
	private String  searchArtcCdOption 	        ; // 검색 품목코드 옵션(artcCd : 품목코드)
	private List<?> searchArtcCdList 	        ; // 검색 품목코드 리스트
	private String  searchEcArtcCd 		        ; // 검색 전자상거래품목코드
	
	private String  artcCd			            ; // 품목코드
	private String  artcUpCd			        ; // 품목군코드(품목코드 앞 2자리)
	private String  artcUpNm			        ; // 품목군명(품목코드 앞 2자리)
	private String  ecArtcCd 		            ; // 전자상거래품목코드
	private String  uprArtcCd 	                ; // 상위 품목코드
	private Integer artcDpthNo 	                ; // 품목깊이번호
	private String  artcNm    		            ; // 품목명
	private String  artcFullNm    	            ; // 품목FULL명
	private Integer onlBrchInvRt	            ; // 재고율
	private Integer maxLmtQty		            ; // 최대구매수량
	private Integer goodsMrgnRt		            ; // 상품마진율
	private String  sizeLktbFileNm	            ; // 사이즈조견표 파일명
	private String  sizeLktbPathNm	            ; // 사이즈조견표 파일패스
	                                            
	private String  ecArtcNm 		            ; // 전자상거래품목명
	private String  uprArtcNm		            ; // 상위 품목명
	private Integer childCount		            ; // 허위 품목 Count
	
	private Integer dispNo						; // 전시번호
	
	public Integer getSearchArtcDpthNo() {
		return searchArtcDpthNo;
	}
	public void setSearchArtcDpthNo(Integer searchArtcDpthNo) {
		this.searchArtcDpthNo = searchArtcDpthNo;
	}
	public String getSearchConnectByDeniedYn() {
		return searchConnectByDeniedYn;
	}
	public void setSearchConnectByDeniedYn(String searchConnectByDeniedYn) {
		this.searchConnectByDeniedYn = searchConnectByDeniedYn;
	}
	public String getSearchArtcUpCd() {
		return searchArtcUpCd;
	}
	public void setSearchArtcUpCd(String searchArtcUpCd) {
		this.searchArtcUpCd = searchArtcUpCd;
	}
	public String getSearchArtcCd() {
		return searchArtcCd;
	}
	public void setSearchArtcCd(String searchArtcCd) {
		this.searchArtcCd = searchArtcCd;
	}
	public String getSearchArtcCdOption() {
		return searchArtcCdOption;
	}
	public void setSearchArtcCdOption(String searchArtcCdOption) {
		this.searchArtcCdOption = searchArtcCdOption;
	}
	public List<?> getSearchArtcCdList() {
		return searchArtcCdList;
	}
	public void setSearchArtcCdList(List<?> searchArtcCdList) {
		this.searchArtcCdList = searchArtcCdList;
	}
	public String getSearchEcArtcCd() {
		return searchEcArtcCd;
	}
	public void setSearchEcArtcCd(String searchEcArtcCd) {
		this.searchEcArtcCd = searchEcArtcCd;
	}
	public String getArtcCd() {
		return artcCd;
	}
	public void setArtcCd(String artcCd) {
		this.artcCd = artcCd;
	}
	public String getArtcUpCd() {
		return artcUpCd;
	}
	public void setArtcUpCd(String artcUpCd) {
		this.artcUpCd = artcUpCd;
	}
	public String getArtcUpNm() {
		return artcUpNm;
	}
	public void setArtcUpNm(String artcUpNm) {
		this.artcUpNm = artcUpNm;
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
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	public Integer getDispNo() {
		return dispNo;
	}
	public void setDispNo(Integer dispNo) {
		this.dispNo = dispNo;
	}
	
}
