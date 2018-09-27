package homenet.shop.display.service;

import java.util.List;

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
	
	private String  searchShopTpCd			; // 검색 매장유형코드
	private String  searchUseYn             ; // 검색 사용여부
	private String  searchDispYn            ; // 검색 전시여부
	
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
	
	private List<DispImgInfoVO> dispImgInfoList;	// 전시  이미지정보 리스트
	
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
	public List<DispImgInfoVO> getDispImgInfoList() {
		return dispImgInfoList;
	}
	public void setDispImgInfoList(List<DispImgInfoVO> dispImgInfoList) {
		this.dispImgInfoList = dispImgInfoList;
	}
	
	
}