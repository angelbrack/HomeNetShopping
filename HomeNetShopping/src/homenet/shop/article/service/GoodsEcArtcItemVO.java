package homenet.shop.article.service;

import prjframework.common.util.PagerVO;

public class GoodsEcArtcItemVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private Integer goodsNo						; // 상품번호
	private String  ecArtcCd                    ; // 전자상거래품목코드
	private String  artcNm                      ; // 전자상거래품목품목명
	private String  mdlNm                       ; // 모델명
	private String  ratVltgVal                  ; // 정격전압값
	private String  cnsmPowerVal                ; // 소모전력값
	private String  enrgCnsmEfcncGradeVal       ; // 에너지소포효율등급코드
	private String  smnsMdlLnchYm               ; // 동일모델출시년월
	private String  qualGrteStdCont             ; // 품질보증기준내용
	private String  szWdthVal                   ; // 크기가로값
	private String  szLnthVal                   ; // 크기세로값
	private String  szHgtVal                    ; // 크기높이값
	private String  wgtVal                      ; // 중량값
	private String  clorCd                      ; // 색상코드
	private String  asMgrNm                     ; // AS책임자
	private String  asMgrPhonVal                ; // AS책임자전화번호
	private String  item1Val                    ; // 항목1값
	private String  item2Val                    ; // 항목2값
	private String  item3Val                    ; // 항목3값
	private String  item4Val                    ; // 항목4값
	private String  item5Val                    ; // 항목5값
	private String  item6Val                    ; // 항목6값
	private String  item7Val                    ; // 항목7값
	private String  item8Val                    ; // 항목8값
	private String  item9Val                    ; // 항목9값
	private String  item10Val                   ; // 항목10값
	private String  item11Val                   ; // 항목11값
	
	private GoodsEcArtcItemNmVO goodsEcArtcItemNmVO;	// 전자상거래 품목 Item 명
	
	public Integer getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getEcArtcCd() {
		return ecArtcCd;
	}
	public void setEcArtcCd(String ecArtcCd) {
		this.ecArtcCd = ecArtcCd;
	}
	public String getArtcNm() {
		return artcNm;
	}
	public void setArtcNm(String artcNm) {
		this.artcNm = artcNm;
	}
	public String getMdlNm() {
		return mdlNm;
	}
	public void setMdlNm(String mdlNm) {
		this.mdlNm = mdlNm;
	}
	public String getRatVltgVal() {
		return ratVltgVal;
	}
	public void setRatVltgVal(String ratVltgVal) {
		this.ratVltgVal = ratVltgVal;
	}
	public String getCnsmPowerVal() {
		return cnsmPowerVal;
	}
	public void setCnsmPowerVal(String cnsmPowerVal) {
		this.cnsmPowerVal = cnsmPowerVal;
	}
	public String getEnrgCnsmEfcncGradeVal() {
		return enrgCnsmEfcncGradeVal;
	}
	public void setEnrgCnsmEfcncGradeVal(String enrgCnsmEfcncGradeVal) {
		this.enrgCnsmEfcncGradeVal = enrgCnsmEfcncGradeVal;
	}
	public String getSmnsMdlLnchYm() {
		return smnsMdlLnchYm;
	}
	public void setSmnsMdlLnchYm(String smnsMdlLnchYm) {
		this.smnsMdlLnchYm = smnsMdlLnchYm;
	}
	public String getQualGrteStdCont() {
		return qualGrteStdCont;
	}
	public void setQualGrteStdCont(String qualGrteStdCont) {
		this.qualGrteStdCont = qualGrteStdCont;
	}
	public String getSzWdthVal() {
		return szWdthVal;
	}
	public void setSzWdthVal(String szWdthVal) {
		this.szWdthVal = szWdthVal;
	}
	public String getSzLnthVal() {
		return szLnthVal;
	}
	public void setSzLnthVal(String szLnthVal) {
		this.szLnthVal = szLnthVal;
	}
	public String getSzHgtVal() {
		return szHgtVal;
	}
	public void setSzHgtVal(String szHgtVal) {
		this.szHgtVal = szHgtVal;
	}
	public String getWgtVal() {
		return wgtVal;
	}
	public void setWgtVal(String wgtVal) {
		this.wgtVal = wgtVal;
	}
	public String getClorCd() {
		return clorCd;
	}
	public void setClorCd(String clorCd) {
		this.clorCd = clorCd;
	}
	public String getAsMgrNm() {
		return asMgrNm;
	}
	public void setAsMgrNm(String asMgrNm) {
		this.asMgrNm = asMgrNm;
	}
	public String getAsMgrPhonVal() {
		return asMgrPhonVal;
	}
	public void setAsMgrPhonVal(String asMgrPhonVal) {
		this.asMgrPhonVal = asMgrPhonVal;
	}
	public String getItem1Val() {
		return item1Val;
	}
	public void setItem1Val(String item1Val) {
		this.item1Val = item1Val;
	}
	public String getItem2Val() {
		return item2Val;
	}
	public void setItem2Val(String item2Val) {
		this.item2Val = item2Val;
	}
	public String getItem3Val() {
		return item3Val;
	}
	public void setItem3Val(String item3Val) {
		this.item3Val = item3Val;
	}
	public String getItem4Val() {
		return item4Val;
	}
	public void setItem4Val(String item4Val) {
		this.item4Val = item4Val;
	}
	public String getItem5Val() {
		return item5Val;
	}
	public void setItem5Val(String item5Val) {
		this.item5Val = item5Val;
	}
	public String getItem6Val() {
		return item6Val;
	}
	public void setItem6Val(String item6Val) {
		this.item6Val = item6Val;
	}
	public String getItem7Val() {
		return item7Val;
	}
	public void setItem7Val(String item7Val) {
		this.item7Val = item7Val;
	}
	public String getItem8Val() {
		return item8Val;
	}
	public void setItem8Val(String item8Val) {
		this.item8Val = item8Val;
	}
	public String getItem9Val() {
		return item9Val;
	}
	public void setItem9Val(String item9Val) {
		this.item9Val = item9Val;
	}
	public String getItem10Val() {
		return item10Val;
	}
	public void setItem10Val(String item10Val) {
		this.item10Val = item10Val;
	}
	public String getItem11Val() {
		return item11Val;
	}
	public void setItem11Val(String item11Val) {
		this.item11Val = item11Val;
	}
	public GoodsEcArtcItemNmVO getGoodsEcArtcItemNmVO() {
		return goodsEcArtcItemNmVO;
	}
	public void setGoodsEcArtcItemNmVO(GoodsEcArtcItemNmVO goodsEcArtcItemNmVO) {
		this.goodsEcArtcItemNmVO = goodsEcArtcItemNmVO;
	}
	
	
}
    