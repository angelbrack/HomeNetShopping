package homenet.shop.brand.service;

import prjframework.common.util.PagerVO;

/**
 * <p>브랜드 이미지 정보 VO</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
public class BrndImgInfoVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;

	private Integer imgNo			; // 이미지번호
	private Integer brndNo			; // 브랜드번호
	private String  imgNm 		    ; // 이미지명
	private String  imgPathNm 	    ; // 이미지경로명
	private String  imgFileNm 	    ; // 파일명
	
	public Integer getImgNo() {
		return imgNo;
	}
	public void setImgNo(Integer imgNo) {
		this.imgNo = imgNo;
	}
	public Integer getBrndNo() {
		return brndNo;
	}
	public void setBrndNo(Integer brndNo) {
		this.brndNo = brndNo;
	}
	public String getImgNm() {
		return imgNm;
	}
	public void setImgNm(String imgNm) {
		this.imgNm = imgNm;
	}
	public String getImgPathNm() {
		return imgPathNm;
	}
	public void setImgPathNm(String imgPathNm) {
		this.imgPathNm = imgPathNm;
	}
	public String getImgFileNm() {
		return imgFileNm;
	}
	public void setImgFileNm(String imgFileNm) {
		this.imgFileNm = imgFileNm;
	}
}
