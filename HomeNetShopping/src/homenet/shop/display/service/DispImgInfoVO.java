package homenet.shop.display.service;

import prjframework.common.util.PagerVO;

/**
 * <p>전시  이미지정보 VO</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 21.</li>
 * </ul>
 */
public class DispImgInfoVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private Integer dispNo             	; // 전시번호
	private String  dispImgTpCd      	; // 전시이미지유형코드(01 기획전배너, 02 관련전시매장, 03 전시매장 GNB, 04 전시매장타이틀, 05 전시매장대표)
	private Integer imgSn              	; // 이미지일련번호
	private String  dispShopSctCd		; // 전시매장구분코드(01 전시매장카테고리, 02 기획전)
	private String  bnrImgPathNm     	; // 배너이미지경로명
	private String  bnrImgFileNm     	; // 배너이미지파일명
	private String  lnkUrlAddr        	; // 연결 URL주소
	
	public Integer getDispNo() {
		return dispNo;
	}
	public void setDispNo(Integer dispNo) {
		this.dispNo = dispNo;
	}
	public Integer getImgSn() {
		return imgSn;
	}
	public void setImgSn(Integer imgSn) {
		this.imgSn = imgSn;
	}
	public String getDispImgTpCd() {
		return dispImgTpCd;
	}
	public void setDispImgTpCd(String dispImgTpCd) {
		this.dispImgTpCd = dispImgTpCd;
	}
	public String getDispShopSctCd() {
		return dispShopSctCd;
	}
	public void setDispShopSctCd(String dispShopSctCd) {
		this.dispShopSctCd = dispShopSctCd;
	}
	public String getBnrImgPathNm() {
		return bnrImgPathNm;
	}
	public void setBnrImgPathNm(String bnrImgPathNm) {
		this.bnrImgPathNm = bnrImgPathNm;
	}
	public String getBnrImgFileNm() {
		return bnrImgFileNm;
	}
	public void setBnrImgFileNm(String bnrImgFileNm) {
		this.bnrImgFileNm = bnrImgFileNm;
	}
	public String getLnkUrlAddr() {
		return lnkUrlAddr;
	}
	public void setLnkUrlAddr(String lnkUrlAddr) {
		this.lnkUrlAddr = lnkUrlAddr;
	}
	
	
}
