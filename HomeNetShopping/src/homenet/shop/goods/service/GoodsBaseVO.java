package homenet.shop.goods.service;

import java.util.Date;
import java.util.List;

import prjframework.common.util.PagerVO;

/**
 * <p>삼품 정보 VO</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 11. 05.</li>
 * </ul>
 */
public class GoodsBaseVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private String  searchTermOption				; // 검색 기간 옵션
	private String  searchSaleStrtDtime             ; // 검색 판매시작일자
	private String  searchSaleEndDtime              ; // 검색 판매종료일자
	private String  searchWrtDttm			        ; // 검색 상품등록일
	private String  searchMdlLnchDt                 ; // 검색 출시일자
	
	private List<String>  searchMdlNmList           ; // 검색 모델명 리스트
	private String  searchGoodsNm                   ; // 검색 상품명
	private Integer searchDispNo					; // 검색 전시번호
	private Integer searchDispNm					; // 검색 전시명
	private String  searchArtcCd                    ; // 검색 품목코드
	private String  searchArtcNm                    ; // 검색 품목명
	private Integer searchSupCorpNo                 ; // 검색 공급사코드
	private List<Integer> searchGoodsNoList	        ; // 검색 상품번호 리스트
	private List<Integer> searchBrndNoList	        ; // 검색 브랜드번호 리스트
	private String  searchGoodsCmpsCd               ; // 검색 상품구성코드
	private String  searchSaleStatCd                ; // 검색 판매상태코드
	private String  searchMgmtStatCd        		; // 검색 단종관리상태코드
	private String  searchIndivGoodsSalePsbYn     	; // 검색 이벤트쿠폰사용가능여부
	
	private Integer goodsNo					        ; // 상품번호
	private String  goodsId                         ; // 상품아이디
	private String  mdlNm                           ; // 모델명
	private String  artcCd                          ; // 품목코드
	private Integer brndNo                          ; // 브랜드번호
	private String  brndCrttSctCd                   ; // 브랜드특성코드
	private String  goodsTpCd                       ; // 상품유형코드
	private String  goodsCmpsCd                     ; // 상품구성코드
	private String  goodsNm                         ; // 상품명
	private String  orplNm                          ; // 원산지명
	private String  mfcpNm                          ; // 제조사명
	private String  qtyLmtGoodsYn                   ; // 한정판매수량상품여부
	private String  saleStatCd                      ; // 판매상태코드
	private Date    saleStrtDtime                   ; // 판매시작일자
	private Date    saleEndDtime                    ; // 판매종료일자
	private String  ngsMrkEndDt                     ; // 신상품표시종료일자
	private String  goodsPrCont                     ; // 상품홍보문구
	private String  assocSchKwdNm                   ; // 연관검색키워드명
	private String  mdlLnchDt                       ; // 출시일자
	private String  mgmtStatCd                      ; // 단종관리상태코드
	private String  mgmtStatDt                      ; // 단종관리일자
	private String  tdfSctCd                        ; // 과면세구분코드
	private String  clmDscntPsbYn                   ; // 청구할인가능여부
	private String  staffDscntPsbYn                 ; // 임직원할인가능여부
	private Integer staffDscntPrc                   ; // 임직원할인금액
	private String  maxPurQtyLmtYn                  ; // 구매수량제한여부
	private Integer minLmtQty                       ; // 최소구매제한수량
	private Integer maxLmtQty                       ; // 최대구매제한수량
	private String  shopInvOwnYn                    ; // 개별상품판매가능여부
	private String  indivGoodsSalePsbYn             ; // 이벤트쿠폰사용가능여부
	private String  payMeanLmtYn                    ; // 결제수단제한여부
	private String  dlvTpCd                         ; // 배송처리유형코드
	private String  dlvMeanCd                       ; // 배송수단코드
	private String  exchRtgsLmtTxtCd                ; // 교환반품안내문구 관리자
	private String  exchRtgsGdTxtCd                 ; // 교환반품안내문구 FRONT
	private String  exchRtgsPsbYn                   ; // 교환반품가능여부
	private String  attchdGoodsExitYn               ; // 부속상품존재여부
	private String  attchdGoodsTpCd                 ; // 부속상품유형
	private Integer rgfAmt                          ; // 임대상품 등록비 
	private Integer instCstAmt                      ; // 임대상품 임대비
	private Integer rntlTermMmCnt                   ; // 임대상품 임대기간개월수
	private Integer webMastDispNo                   ; // 웹대표전시번호
	private Integer mblMastDispNo                   ; // 모바일대표전시번호
	private Integer supCorpNo                       ; // 공급사코드
	private String  dlvStrtFcstDt                   ; // 상품 배송시작일 
	private String  genGoodsDlvYn                   ; // 일반상품배송여부
	private Integer adjPurPrc                       ; // 정산매입가(사입가)
	public String getSearchTermOption() {
		return searchTermOption;
	}
	public void setSearchTermOption(String searchTermOption) {
		this.searchTermOption = searchTermOption;
	}
	public String getSearchSaleStrtDtime() {
		return searchSaleStrtDtime;
	}
	public void setSearchSaleStrtDtime(String searchSaleStrtDtime) {
		this.searchSaleStrtDtime = searchSaleStrtDtime;
	}
	public String getSearchSaleEndDtime() {
		return searchSaleEndDtime;
	}
	public void setSearchSaleEndDtime(String searchSaleEndDtime) {
		this.searchSaleEndDtime = searchSaleEndDtime;
	}
	public String getSearchWrtDttm() {
		return searchWrtDttm;
	}
	public void setSearchWrtDttm(String searchWrtDttm) {
		this.searchWrtDttm = searchWrtDttm;
	}
	public String getSearchMdlLnchDt() {
		return searchMdlLnchDt;
	}
	public void setSearchMdlLnchDt(String searchMdlLnchDt) {
		this.searchMdlLnchDt = searchMdlLnchDt;
	}
	public List<String> getSearchMdlNmList() {
		return searchMdlNmList;
	}
	public void setSearchMdlNmList(List<String> searchMdlNmList) {
		this.searchMdlNmList = searchMdlNmList;
	}
	public String getSearchGoodsNm() {
		return searchGoodsNm;
	}
	public void setSearchGoodsNm(String searchGoodsNm) {
		this.searchGoodsNm = searchGoodsNm;
	}
	public Integer getSearchDispNo() {
		return searchDispNo;
	}
	public void setSearchDispNo(Integer searchDispNo) {
		this.searchDispNo = searchDispNo;
	}
	public Integer getSearchDispNm() {
		return searchDispNm;
	}
	public void setSearchDispNm(Integer searchDispNm) {
		this.searchDispNm = searchDispNm;
	}
	public String getSearchArtcCd() {
		return searchArtcCd;
	}
	public void setSearchArtcCd(String searchArtcCd) {
		this.searchArtcCd = searchArtcCd;
	}
	public String getSearchArtcNm() {
		return searchArtcNm;
	}
	public void setSearchArtcNm(String searchArtcNm) {
		this.searchArtcNm = searchArtcNm;
	}
	public Integer getSearchSupCorpNo() {
		return searchSupCorpNo;
	}
	public void setSearchSupCorpNo(Integer searchSupCorpNo) {
		this.searchSupCorpNo = searchSupCorpNo;
	}
	public List<Integer> getSearchGoodsNoList() {
		return searchGoodsNoList;
	}
	public void setSearchGoodsNoList(List<Integer> searchGoodsNoList) {
		this.searchGoodsNoList = searchGoodsNoList;
	}
	public List<Integer> getSearchBrndNoList() {
		return searchBrndNoList;
	}
	public void setSearchBrndNoList(List<Integer> searchBrndNoList) {
		this.searchBrndNoList = searchBrndNoList;
	}
	public String getSearchGoodsCmpsCd() {
		return searchGoodsCmpsCd;
	}
	public void setSearchGoodsCmpsCd(String searchGoodsCmpsCd) {
		this.searchGoodsCmpsCd = searchGoodsCmpsCd;
	}
	public String getSearchSaleStatCd() {
		return searchSaleStatCd;
	}
	public void setSearchSaleStatCd(String searchSaleStatCd) {
		this.searchSaleStatCd = searchSaleStatCd;
	}
	public String getSearchMgmtStatCd() {
		return searchMgmtStatCd;
	}
	public void setSearchMgmtStatCd(String searchMgmtStatCd) {
		this.searchMgmtStatCd = searchMgmtStatCd;
	}
	public String getSearchIndivGoodsSalePsbYn() {
		return searchIndivGoodsSalePsbYn;
	}
	public void setSearchIndivGoodsSalePsbYn(String searchIndivGoodsSalePsbYn) {
		this.searchIndivGoodsSalePsbYn = searchIndivGoodsSalePsbYn;
	}
	public Integer getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getMdlNm() {
		return mdlNm;
	}
	public void setMdlNm(String mdlNm) {
		this.mdlNm = mdlNm;
	}
	public String getArtcCd() {
		return artcCd;
	}
	public void setArtcCd(String artcCd) {
		this.artcCd = artcCd;
	}
	public Integer getBrndNo() {
		return brndNo;
	}
	public void setBrndNo(Integer brndNo) {
		this.brndNo = brndNo;
	}
	public String getBrndCrttSctCd() {
		return brndCrttSctCd;
	}
	public void setBrndCrttSctCd(String brndCrttSctCd) {
		this.brndCrttSctCd = brndCrttSctCd;
	}
	public String getGoodsTpCd() {
		return goodsTpCd;
	}
	public void setGoodsTpCd(String goodsTpCd) {
		this.goodsTpCd = goodsTpCd;
	}
	public String getGoodsCmpsCd() {
		return goodsCmpsCd;
	}
	public void setGoodsCmpsCd(String goodsCmpsCd) {
		this.goodsCmpsCd = goodsCmpsCd;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public String getOrplNm() {
		return orplNm;
	}
	public void setOrplNm(String orplNm) {
		this.orplNm = orplNm;
	}
	public String getMfcpNm() {
		return mfcpNm;
	}
	public void setMfcpNm(String mfcpNm) {
		this.mfcpNm = mfcpNm;
	}
	public String getQtyLmtGoodsYn() {
		return qtyLmtGoodsYn;
	}
	public void setQtyLmtGoodsYn(String qtyLmtGoodsYn) {
		this.qtyLmtGoodsYn = qtyLmtGoodsYn;
	}
	public String getSaleStatCd() {
		return saleStatCd;
	}
	public void setSaleStatCd(String saleStatCd) {
		this.saleStatCd = saleStatCd;
	}
	public Date getSaleStrtDtime() {
		return saleStrtDtime;
	}
	public void setSaleStrtDtime(Date saleStrtDtime) {
		this.saleStrtDtime = saleStrtDtime;
	}
	public Date getSaleEndDtime() {
		return saleEndDtime;
	}
	public void setSaleEndDtime(Date saleEndDtime) {
		this.saleEndDtime = saleEndDtime;
	}
	public String getNgsMrkEndDt() {
		return ngsMrkEndDt;
	}
	public void setNgsMrkEndDt(String ngsMrkEndDt) {
		this.ngsMrkEndDt = ngsMrkEndDt;
	}
	public String getGoodsPrCont() {
		return goodsPrCont;
	}
	public void setGoodsPrCont(String goodsPrCont) {
		this.goodsPrCont = goodsPrCont;
	}
	public String getAssocSchKwdNm() {
		return assocSchKwdNm;
	}
	public void setAssocSchKwdNm(String assocSchKwdNm) {
		this.assocSchKwdNm = assocSchKwdNm;
	}
	public String getMdlLnchDt() {
		return mdlLnchDt;
	}
	public void setMdlLnchDt(String mdlLnchDt) {
		this.mdlLnchDt = mdlLnchDt;
	}
	public String getMgmtStatCd() {
		return mgmtStatCd;
	}
	public void setMgmtStatCd(String mgmtStatCd) {
		this.mgmtStatCd = mgmtStatCd;
	}
	public String getMgmtStatDt() {
		return mgmtStatDt;
	}
	public void setMgmtStatDt(String mgmtStatDt) {
		this.mgmtStatDt = mgmtStatDt;
	}
	public String getTdfSctCd() {
		return tdfSctCd;
	}
	public void setTdfSctCd(String tdfSctCd) {
		this.tdfSctCd = tdfSctCd;
	}
	public String getClmDscntPsbYn() {
		return clmDscntPsbYn;
	}
	public void setClmDscntPsbYn(String clmDscntPsbYn) {
		this.clmDscntPsbYn = clmDscntPsbYn;
	}
	public String getStaffDscntPsbYn() {
		return staffDscntPsbYn;
	}
	public void setStaffDscntPsbYn(String staffDscntPsbYn) {
		this.staffDscntPsbYn = staffDscntPsbYn;
	}
	public Integer getStaffDscntPrc() {
		return staffDscntPrc;
	}
	public void setStaffDscntPrc(Integer staffDscntPrc) {
		this.staffDscntPrc = staffDscntPrc;
	}
	public String getMaxPurQtyLmtYn() {
		return maxPurQtyLmtYn;
	}
	public void setMaxPurQtyLmtYn(String maxPurQtyLmtYn) {
		this.maxPurQtyLmtYn = maxPurQtyLmtYn;
	}
	public Integer getMinLmtQty() {
		return minLmtQty;
	}
	public void setMinLmtQty(Integer minLmtQty) {
		this.minLmtQty = minLmtQty;
	}
	public Integer getMaxLmtQty() {
		return maxLmtQty;
	}
	public void setMaxLmtQty(Integer maxLmtQty) {
		this.maxLmtQty = maxLmtQty;
	}
	public String getShopInvOwnYn() {
		return shopInvOwnYn;
	}
	public void setShopInvOwnYn(String shopInvOwnYn) {
		this.shopInvOwnYn = shopInvOwnYn;
	}
	public String getIndivGoodsSalePsbYn() {
		return indivGoodsSalePsbYn;
	}
	public void setIndivGoodsSalePsbYn(String indivGoodsSalePsbYn) {
		this.indivGoodsSalePsbYn = indivGoodsSalePsbYn;
	}
	public String getPayMeanLmtYn() {
		return payMeanLmtYn;
	}
	public void setPayMeanLmtYn(String payMeanLmtYn) {
		this.payMeanLmtYn = payMeanLmtYn;
	}
	public String getDlvTpCd() {
		return dlvTpCd;
	}
	public void setDlvTpCd(String dlvTpCd) {
		this.dlvTpCd = dlvTpCd;
	}
	public String getDlvMeanCd() {
		return dlvMeanCd;
	}
	public void setDlvMeanCd(String dlvMeanCd) {
		this.dlvMeanCd = dlvMeanCd;
	}
	public String getExchRtgsLmtTxtCd() {
		return exchRtgsLmtTxtCd;
	}
	public void setExchRtgsLmtTxtCd(String exchRtgsLmtTxtCd) {
		this.exchRtgsLmtTxtCd = exchRtgsLmtTxtCd;
	}
	public String getExchRtgsGdTxtCd() {
		return exchRtgsGdTxtCd;
	}
	public void setExchRtgsGdTxtCd(String exchRtgsGdTxtCd) {
		this.exchRtgsGdTxtCd = exchRtgsGdTxtCd;
	}
	public String getExchRtgsPsbYn() {
		return exchRtgsPsbYn;
	}
	public void setExchRtgsPsbYn(String exchRtgsPsbYn) {
		this.exchRtgsPsbYn = exchRtgsPsbYn;
	}
	public String getAttchdGoodsExitYn() {
		return attchdGoodsExitYn;
	}
	public void setAttchdGoodsExitYn(String attchdGoodsExitYn) {
		this.attchdGoodsExitYn = attchdGoodsExitYn;
	}
	public String getAttchdGoodsTpCd() {
		return attchdGoodsTpCd;
	}
	public void setAttchdGoodsTpCd(String attchdGoodsTpCd) {
		this.attchdGoodsTpCd = attchdGoodsTpCd;
	}
	public Integer getRgfAmt() {
		return rgfAmt;
	}
	public void setRgfAmt(Integer rgfAmt) {
		this.rgfAmt = rgfAmt;
	}
	public Integer getInstCstAmt() {
		return instCstAmt;
	}
	public void setInstCstAmt(Integer instCstAmt) {
		this.instCstAmt = instCstAmt;
	}
	public Integer getRntlTermMmCnt() {
		return rntlTermMmCnt;
	}
	public void setRntlTermMmCnt(Integer rntlTermMmCnt) {
		this.rntlTermMmCnt = rntlTermMmCnt;
	}
	public Integer getWebMastDispNo() {
		return webMastDispNo;
	}
	public void setWebMastDispNo(Integer webMastDispNo) {
		this.webMastDispNo = webMastDispNo;
	}
	public Integer getMblMastDispNo() {
		return mblMastDispNo;
	}
	public void setMblMastDispNo(Integer mblMastDispNo) {
		this.mblMastDispNo = mblMastDispNo;
	}
	public Integer getSupCorpNo() {
		return supCorpNo;
	}
	public void setSupCorpNo(Integer supCorpNo) {
		this.supCorpNo = supCorpNo;
	}
	public String getDlvStrtFcstDt() {
		return dlvStrtFcstDt;
	}
	public void setDlvStrtFcstDt(String dlvStrtFcstDt) {
		this.dlvStrtFcstDt = dlvStrtFcstDt;
	}
	public String getGenGoodsDlvYn() {
		return genGoodsDlvYn;
	}
	public void setGenGoodsDlvYn(String genGoodsDlvYn) {
		this.genGoodsDlvYn = genGoodsDlvYn;
	}
	public Integer getAdjPurPrc() {
		return adjPurPrc;
	}
	public void setAdjPurPrc(Integer adjPurPrc) {
		this.adjPurPrc = adjPurPrc;
	}
	
	

}
