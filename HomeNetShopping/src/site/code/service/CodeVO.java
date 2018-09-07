package site.code.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import prjframework.common.util.PagerVO;

@Data
@SuppressWarnings("serial")
public class CodeVO extends PagerVO implements Serializable {
	/*
	 @AssertFalse : 해당 속성의 값이 false인지 체크한다.
	 @AssertTrue : 해당 속성의 값이 true인지 체크한다.
	 @DecimalMax : 해당 속성이 가질 수 있는 최대값을 체크한다.
	 @DecimalMin : 해당 속성이 가질 수 있는 최소값을 체크한다.
	 @Digits : 해당 속성이 가질 수 있는 정수부의 자리수와 소수부의 자리수를 체크한다.
	 @Future : 해당 속성의 값이 현재일 이후인지 체크한다.
	 @Max : 해당 속성이 가질 수 있는 최대 Length를 체크한다
	 @Min : 해당 속성이 가질 수 있는 최소 Length를 체크한다.
	 @NotNull : 해당 속성의 값이 Null이 아닌지 체크한다.
	 @Null : 해당 속성의 값이 Null인지 체크한다.
	 @Past : 해당 속성의 값이 현재일 이전인지 체크한다.
	 @Pattern : 해당 속성의 값이 정의된 Regular Expression에 부합하는지 체크한다. Regular Expression은 Java Regular Expression Convention (see java.util.regex.Pattern)에 맞게 정의해야 한다.
	 @Size : 해당 속성이 가질 수 있는 최대, 최소 Length를 체크한다
	 @Valid : 해당 객체에 대해 Validation Check가 이루어진다.
	 */
	
	/*
	 * 검색 조건
	 */
	private String searchGrpCdId	 = "";
	private String searchGrpCdIdBox = "" ;
	
	/*
	 * Data Field
	 */
	private String hgrkGrpCdId 			= "";
	private String hgrkCdId 			= "";
	private String grpCdId 			= "";
	private String cdId 			= "";
	private String cdNm 			= "";
	private String cdCn 			= "";
	private String sortOr 			= "";
	private String useYn 			= "";
	private String cnnctInsCd 		= "";
	private String cnnctInsCdNm 	= "";
	private String refeV1 			= "";
	private String refeV2 			= "";
	private String refeV3 			= "";
	private String refeV4 			= "";
	private String refeV5 			= "";

	private List<CodeVO> subList = new ArrayList<CodeVO>();
	
	
	public List<CodeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CodeVO> subList) {
		this.subList = subList;
	}
	public String getSearchGrpCdId() {
		return searchGrpCdId;
	}
	public void setSearchGrpCdId(String searchGrpCdId) {
		this.searchGrpCdId = searchGrpCdId;
	}
	public String getGrpCdId() {
		return grpCdId;
	}
	public void setGrpCdId(String grpCdId) {
		this.grpCdId = grpCdId;
	}
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getCdCn() {
		return cdCn;
	}
	public void setCdCn(String cdCn) {
		this.cdCn = cdCn;
	}
	public String getSortOr() {
		return sortOr;
	}
	public void setSortOr(String sortOr) {
		this.sortOr = sortOr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getCnnctInsCd() {
		return cnnctInsCd;
	}
	public void setCnnctInsCd(String cnnctInsCd) {
		this.cnnctInsCd = cnnctInsCd;
	}
	public String getCnnctInsCdNm() {
		return cnnctInsCdNm;
	}
	public void setCnnctInsCdNm(String cnnctInsCdNm) {
		this.cnnctInsCdNm = cnnctInsCdNm;
	}
	public String getRefeV1() {
		return refeV1;
	}
	public void setRefeV1(String refeV1) {
		this.refeV1 = refeV1;
	}
	public String getRefeV2() {
		return refeV2;
	}
	public void setRefeV2(String refeV2) {
		this.refeV2 = refeV2;
	}
	public String getRefeV3() {
		return refeV3;
	}
	public void setRefeV3(String refeV3) {
		this.refeV3 = refeV3;
	}
	public String getRefeV4() {
		return refeV4;
	}
	public void setRefeV4(String refeV4) {
		this.refeV4 = refeV4;
	}
	public String getRefeV5() {
		return refeV5;
	}
	public void setRefeV5(String refeV5) {
		this.refeV5 = refeV5;
	}
	public String getSearchGrpCdIdBox() {
		return searchGrpCdIdBox;
	}
	public void setSearchGrpCdIdBox(String searchGrpCdIdBox) {
		this.searchGrpCdIdBox = searchGrpCdIdBox;
	}
	public String getHgrkCdId() {
		return hgrkCdId;
	}
	public void setHgrkCdId(String hgrkCdId) {
		this.hgrkCdId = hgrkCdId;
	}
	public String getHgrkGrpCdId() {
		return hgrkGrpCdId;
	}
	public void setHgrkGrpCdId(String hgrkGrpCdId) {
		this.hgrkGrpCdId = hgrkGrpCdId;
	}
		
	
	
}
