package homenet.shop.display.service;

import java.util.List;

import homenet.shop.article.service.GoodsArtcCdVO;
import prjframework.common.util.PagerVO;

/**
 * <p>전시  기본정보 VO</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 21.</li>
 * </ul>
 */
public class DispShopBaseVO  extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private String  searchDpmlNo            ; // 검색 전시몰번호
	private Integer searchDispNo			; // 검색 전시번호
	private String  searchShopTpCd			; // 검색 매장유형코드
	private String  searchUseYn             ; // 검색 사용여부
	private String  searchDispYn            ; // 검색 전시여부
	private String  searchMenuUseYn         ; // 검색 메뉴사용여부
	private String  searchTlwtLfYn          ; // 검색 최하위리프여부
	
	private Integer dispNo					; // 전시번호
	private Integer uprDispNo             	; // 상위전시번호
	private String  dpmlNo                 	; // 전시몰번호
	private String  dispNm                 	; // 전시명
	private String  dispGnbNm             	; // 전시 GNB명
	private String  gnbExpMethCd         	; // 전시 GNB노출방식
	private String  dispTitNm             	; // 전시 제목명
	private String  dispTitExpMethCd    	; // 전시제목노출방식
	private String  shopTpCd              	; // 매장유형코드
	private String  shopDescCont          	; // 매장설명내용
	private Integer dpthNo                 	; // 깊이번호
	private Integer dispPrioRnk           	; // 전시우선순위
	private String  useYn                  	; // 사용여부
	private String  dispYn                 	; // 전시여부
	private String  tlwtLfYn              	; // 최하위리프여부
	private String  prtTpCd               	; // 출력유형코드
	private String  lstSortCd             	; // 리스트정렬코드
	private String  movFrmeCd             	; // 이동프레임코드
	private String  lnkUrlAddr            	; // 연결 url주소
	private Integer lnkSpdpHhNo          	; // 연결 기획전 전시번호 
	private Integer dispLrgNo             	; // 전시대번호
	private Integer dispMidNo             	; // 전시중번호
	private Integer dispSmlNo             	; // 전시소번호
	private Integer dispThnNo             	; // 전시세번호
	private String  menuUseYn             	; // 메뉴사용여부
	private Integer tmplNo                 	; // 템플릿번호
	private Integer pppSn                  	; // 팝업일련번호
	
	private String  shopTpNm              	; // 매장유형코드_명
	private String  dpmlNm                 	; // 전시몰_명
	
	private Integer dispLrgNm             	; // 전시대_명
	private Integer dispMidNm             	; // 전시중_명
	private Integer dispSmlNm             	; // 전시소_명
	private Integer dispThnNm             	; // 전시세_명
	
	private Integer childCount				; // 하위 전시 갯수
	
	private String[] addTitleImgList		; // 매장 타이틀 이미지
	private String[] addGnbImgList			; // 매장 메뉴 이미지
	private String[] addHeaderImgList		; // 매장 부가 이미지
	
	private List<DispImgInfoVO> dispImgInfoList		; // 전시  이미지정보 리스트
	private List<GoodsArtcCdVO> articleList			; // 품목군 리스트
	public String getSearchDpmlNo() {
		return searchDpmlNo;
	}
	public void setSearchDpmlNo(String searchDpmlNo) {
		this.searchDpmlNo = searchDpmlNo;
	}
	public Integer getSearchDispNo() {
		return searchDispNo;
	}
	public void setSearchDispNo(Integer searchDispNo) {
		this.searchDispNo = searchDispNo;
	}
	public String getSearchShopTpCd() {
		return searchShopTpCd;
	}
	public void setSearchShopTpCd(String searchShopTpCd) {
		this.searchShopTpCd = searchShopTpCd;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public String getSearchDispYn() {
		return searchDispYn;
	}
	public void setSearchDispYn(String searchDispYn) {
		this.searchDispYn = searchDispYn;
	}
	public String getSearchMenuUseYn() {
		return searchMenuUseYn;
	}
	public void setSearchMenuUseYn(String searchMenuUseYn) {
		this.searchMenuUseYn = searchMenuUseYn;
	}
	public String getSearchTlwtLfYn() {
		return searchTlwtLfYn;
	}
	public void setSearchTlwtLfYn(String searchTlwtLfYn) {
		this.searchTlwtLfYn = searchTlwtLfYn;
	}
	public Integer getDispNo() {
		return dispNo;
	}
	public void setDispNo(Integer dispNo) {
		this.dispNo = dispNo;
	}
	public Integer getUprDispNo() {
		return uprDispNo;
	}
	public void setUprDispNo(Integer uprDispNo) {
		this.uprDispNo = uprDispNo;
	}
	public String getDpmlNo() {
		return dpmlNo;
	}
	public void setDpmlNo(String dpmlNo) {
		this.dpmlNo = dpmlNo;
	}
	public String getDispNm() {
		return dispNm;
	}
	public void setDispNm(String dispNm) {
		this.dispNm = dispNm;
	}
	public String getDispGnbNm() {
		return dispGnbNm;
	}
	public void setDispGnbNm(String dispGnbNm) {
		this.dispGnbNm = dispGnbNm;
	}
	public String getGnbExpMethCd() {
		return gnbExpMethCd;
	}
	public void setGnbExpMethCd(String gnbExpMethCd) {
		this.gnbExpMethCd = gnbExpMethCd;
	}
	public String getDispTitNm() {
		return dispTitNm;
	}
	public void setDispTitNm(String dispTitNm) {
		this.dispTitNm = dispTitNm;
	}
	public String getDispTitExpMethCd() {
		return dispTitExpMethCd;
	}
	public void setDispTitExpMethCd(String dispTitExpMethCd) {
		this.dispTitExpMethCd = dispTitExpMethCd;
	}
	public String getShopTpCd() {
		return shopTpCd;
	}
	public void setShopTpCd(String shopTpCd) {
		this.shopTpCd = shopTpCd;
	}
	public String getShopDescCont() {
		return shopDescCont;
	}
	public void setShopDescCont(String shopDescCont) {
		this.shopDescCont = shopDescCont;
	}
	public Integer getDpthNo() {
		return dpthNo;
	}
	public void setDpthNo(Integer dpthNo) {
		this.dpthNo = dpthNo;
	}
	public Integer getDispPrioRnk() {
		return dispPrioRnk;
	}
	public void setDispPrioRnk(Integer dispPrioRnk) {
		this.dispPrioRnk = dispPrioRnk;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDispYn() {
		return dispYn;
	}
	public void setDispYn(String dispYn) {
		this.dispYn = dispYn;
	}
	public String getTlwtLfYn() {
		return tlwtLfYn;
	}
	public void setTlwtLfYn(String tlwtLfYn) {
		this.tlwtLfYn = tlwtLfYn;
	}
	public String getPrtTpCd() {
		return prtTpCd;
	}
	public void setPrtTpCd(String prtTpCd) {
		this.prtTpCd = prtTpCd;
	}
	public String getLstSortCd() {
		return lstSortCd;
	}
	public void setLstSortCd(String lstSortCd) {
		this.lstSortCd = lstSortCd;
	}
	public String getMovFrmeCd() {
		return movFrmeCd;
	}
	public void setMovFrmeCd(String movFrmeCd) {
		this.movFrmeCd = movFrmeCd;
	}
	public String getLnkUrlAddr() {
		return lnkUrlAddr;
	}
	public void setLnkUrlAddr(String lnkUrlAddr) {
		this.lnkUrlAddr = lnkUrlAddr;
	}
	public Integer getLnkSpdpHhNo() {
		return lnkSpdpHhNo;
	}
	public void setLnkSpdpHhNo(Integer lnkSpdpHhNo) {
		this.lnkSpdpHhNo = lnkSpdpHhNo;
	}
	public Integer getDispLrgNo() {
		return dispLrgNo;
	}
	public void setDispLrgNo(Integer dispLrgNo) {
		this.dispLrgNo = dispLrgNo;
	}
	public Integer getDispMidNo() {
		return dispMidNo;
	}
	public void setDispMidNo(Integer dispMidNo) {
		this.dispMidNo = dispMidNo;
	}
	public Integer getDispSmlNo() {
		return dispSmlNo;
	}
	public void setDispSmlNo(Integer dispSmlNo) {
		this.dispSmlNo = dispSmlNo;
	}
	public Integer getDispThnNo() {
		return dispThnNo;
	}
	public void setDispThnNo(Integer dispThnNo) {
		this.dispThnNo = dispThnNo;
	}
	public String getMenuUseYn() {
		return menuUseYn;
	}
	public void setMenuUseYn(String menuUseYn) {
		this.menuUseYn = menuUseYn;
	}
	public Integer getTmplNo() {
		return tmplNo;
	}
	public void setTmplNo(Integer tmplNo) {
		this.tmplNo = tmplNo;
	}
	public Integer getPppSn() {
		return pppSn;
	}
	public void setPppSn(Integer pppSn) {
		this.pppSn = pppSn;
	}
	public String getShopTpNm() {
		return shopTpNm;
	}
	public void setShopTpNm(String shopTpNm) {
		this.shopTpNm = shopTpNm;
	}
	public String getDpmlNm() {
		return dpmlNm;
	}
	public void setDpmlNm(String dpmlNm) {
		this.dpmlNm = dpmlNm;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	public String[] getAddTitleImgList() {
		return addTitleImgList;
	}
	public void setAddTitleImgList(String[] addTitleImgList) {
		this.addTitleImgList = addTitleImgList;
	}
	public String[] getAddGnbImgList() {
		return addGnbImgList;
	}
	public void setAddGnbImgList(String[] addGnbImgList) {
		this.addGnbImgList = addGnbImgList;
	}
	public String[] getAddHeaderImgList() {
		return addHeaderImgList;
	}
	public void setAddHeaderImgList(String[] addHeaderImgList) {
		this.addHeaderImgList = addHeaderImgList;
	}
	public List<DispImgInfoVO> getDispImgInfoList() {
		return dispImgInfoList;
	}
	public void setDispImgInfoList(List<DispImgInfoVO> dispImgInfoList) {
		this.dispImgInfoList = dispImgInfoList;
	}
	public List<GoodsArtcCdVO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<GoodsArtcCdVO> articleList) {
		this.articleList = articleList;
	}

	
}
