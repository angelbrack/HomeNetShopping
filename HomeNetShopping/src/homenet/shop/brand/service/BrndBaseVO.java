package homenet.shop.brand.service;

import java.util.List;

import prjframework.common.util.PagerVO;

/**
 * <p>브랜드 정보 VO</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
public class BrndBaseVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private Integer brndNo			; // 브랜드번호
	private String  brndNm 		    ; // 브랜드명
	private String  brndKorNm 	    ; // 브랜드한글명
	private String  brndEngNm 	    ; // 브랜드영문명
	private String  brndDescCont    ; // 브랜드상세설명
	
	private List<BrndImgInfoVO> BrndImgInfoList;	// 브랜드 이미지 정보 List
	
	public Integer getBrndNo() {
		return brndNo;
	}
	public void setBrndNo(Integer brndNo) {
		this.brndNo = brndNo;
	}
	public String getBrndNm() {
		return brndNm;
	}
	public void setBrndNm(String brndNm) {
		this.brndNm = brndNm;
	}
	public String getBrndKorNm() {
		return brndKorNm;
	}
	public void setBrndKorNm(String brndKorNm) {
		this.brndKorNm = brndKorNm;
	}
	public String getBrndEngNm() {
		return brndEngNm;
	}
	public void setBrndEngNm(String brndEngNm) {
		this.brndEngNm = brndEngNm;
	}
	public String getBrndDescCont() {
		return brndDescCont;
	}
	public void setBrndDescCont(String brndDescCont) {
		this.brndDescCont = brndDescCont;
	}
	public List<BrndImgInfoVO> getBrndImgInfoList() {
		return BrndImgInfoList;
	}
	public void setBrndImgInfoList(List<BrndImgInfoVO> brndImgInfoList) {
		BrndImgInfoList = brndImgInfoList;
	}
	
}
