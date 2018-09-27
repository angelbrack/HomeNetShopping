package homenet.shop.display.service;

import prjframework.common.util.PagerVO;

/**
 * <p>전시  상품정보 VO</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 21.</li>
 * </ul>
 */
public class DispGoodsInfoVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private Integer goodsNo				; // 상품번호
	private Integer dispNo             	; // 전시번호
	private Integer dispPrioRnk       	; // 전시우선순위
	private String   useYn              ; // 사용여부
	
	public Integer getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
	}
	public Integer getDispNo() {
		return dispNo;
	}
	public void setDispNo(Integer dispNo) {
		this.dispNo = dispNo;
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
}
