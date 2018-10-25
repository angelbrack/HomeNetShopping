package homenet.shop.display.service;

import prjframework.common.util.PagerVO;

/**
 * <p>전시몰정보 VO</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 10. 25.</li>
 * </ul>
 */
public class DpmlBaseVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private String   dpmlNo              ; // 전시몰번호
	private String   dpmlNm              ; // 전시몰명
	private String   useYn               ; // 사용여부
	
	public String getDpmlNo() {
		return dpmlNo;
	}
	public void setDpmlNo(String dpmlNo) {
		this.dpmlNo = dpmlNo;
	}
	public String getDpmlNm() {
		return dpmlNm;
	}
	public void setDpmlNm(String dpmlNm) {
		this.dpmlNm = dpmlNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	

}
