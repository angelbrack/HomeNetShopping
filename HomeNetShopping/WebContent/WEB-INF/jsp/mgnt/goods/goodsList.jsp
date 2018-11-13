<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="formInit" id="formInit" method="post" action="">
				<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/>
				
				<input type="hidden" id="goodsNo" 				name="goodsNo" 				value="" 														/>
			</form>
			<form name="form1" id="form1" method="post" action="">
				<input type="hidden" id="cmd"					name="cmd"					value=""														/> <!-- 등록/수정 구분 		-->
				<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/> <!-- 페이지 번호 			-->
				<input type="hidden" id="goodsNo" 				name="goodsNo" 				value="" 														/> <!-- 브랜드번호	 		-->
			<!--contents-->
			<div class="con_top">
				<div class="tit">
					<c:out value="${requestScope.USER_PGM_AUTH.optrPgmNm}" />
				</div>
				<div class="navi">
					<img src="<ctag:conf key="THEME.PATH" />mgnt/images/ic_01.jpg" alt="" />
					<span>&nbsp;>&nbsp;</span>
					상품 관리
					<span>&nbsp;>&nbsp;</span>
					상품 관리
				</div>	
			</div>
			
			<div class="con_search mt24">
				<table>
					<colgroup>
					<col width="15%" />
					<col width="*" />
					<col width="30%" />
					</colgroup>
					<tbody>
						<tr>
							<td colspan="3" class="blank"></td>
						</tr>
						<tr>
                            <td class="tit"><label for="searchTermOption">기간</label></td>
                            <td colspan="2">
                            	<select style="width:120px;height:22px;" name="searchTermOption" id="searchTermOption">
                            		<option value="searchSaleStrtDtime" <c:if test="${empty searchVO.searchTermOption or searchVO.searchTermOption eq 'searchSaleStrtDtime' }">selected="selected"</c:if>>판매시작일</option>
									<option value="searchSaleEndDtime" <c:if test="${searchVO.searchTermOption eq 'searchSaleEndDtime' }">selected="selected"</c:if>>판매종료일</option>
									<option value="searchWrtDttm" <c:if test="${searchVO.searchTermOption eq 'searchWrtDttm' }">selected="selected"</c:if>>상품등록일</option>
									<option value="searchMdlLnchDt"> <c:if test="${searchVO.searchTermOption eq 'searchMdlLnchDt' }">selected="selected"</c:if>모델출시일</option
                            	</select>
                            	<input type="text" name="searchStartDate" id="searchStartDate" title='시작일자' maxlength="20" style="width:100px;height:18px;" class="txtbox1" value="${searchVO.searchStartDate }" />
								~
								<input type="text" name="searchEndDate" id="searchEndDate" title='종료일자' maxlength="20" style="width:100px;height:18px;" class="txtbox1" value="${searchVO.searchEndDate }" />
                            	<select style="width:120px;height:22px;" name="searchCheckOptDate" id="searchCheckOptDate">
                            		<option value='' <c:if test="${empty searchVO.searchCheckOptDate }">selected="selected"</c:if>>기간선택안함</option>
									<option value='0day' <c:if test="${searchVO.searchCheckOptDate eq '0day' }">selected="selected"</c:if>>당일</option>
									<option value='7day' <c:if test="${searchVO.searchCheckOptDate eq '7day' }">selected="selected"</c:if>>최근 일주일</option>
									<option value='15day' <c:if test="${searchVO.searchCheckOptDate eq '15day' }">selected="selected"</c:if>>15일</option>
									<option value="1month" <c:if test="${searchVO.searchCheckOptDate eq '1month' }">selected="selected"</c:if>>1개월</option>
									<option value="3month" <c:if test="${searchVO.searchCheckOptDate eq '3month' }">selected="selected"</c:if>>3개월</option>
                            	</select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="searchGoodsNm">상품명</label></td>
                            <td colspan="2">
                            	<input type="text" name="searchGoodsNm" id="searchGoodsNm" title='상품명' maxlength="20" style="width:90%;height:18px;" class="txtbox1" value="<c:out value="${searchVO.searchGoodsNm }" />" />
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="searchDispNo">전시카테고리</label></td>
                            <td colspan="2">
                            	<input type="text" name="searchDispNo" id="searchDispNo" title='전시번호' maxlength="20" style="width:100px;height:18px;" class="txtbox1" value="<c:out value="${searchVO.searchDispNo }" />" readOnly />
                            	<input type="text" name="searchDispNm" id="searchDispNm" title='전시명' maxlength="20" style="width:100px;height:18px;" class="txtbox1" value="<c:out value="${searchVO.searchDispNm }" />" readOnly />
                            	<a href="#none" onclick="openDisplaySearchPopup();" class="btn_search3 ml2 mr2" id="btnSearchDisp">검색</a>
                            </td>
                        </tr>
                        
						<tr>
                            <td class="tit"><label for="searchWord">검색어</label></td>
                            <td>
                                <select style="width:120px;height:22px;" name="searchKey" id="searchKey">
                                    <option value="000" <c:if test="${empty searchVO.searchKey or searchVO.searchKey eq '000'}">selected="selected"</c:if>>브랜드명</option>
                                    <option value="001" <c:if test="${searchVO.searchKey eq '001'}">selected="selected"</c:if>>브랜드상세설명</option>
                                </select>
                                <input type="text" name="searchWord" id="searchWord" maxlength="100" style="width:400px;height:18px;" class="txtbox1" title="" value="${searchVO.searchWord }" class="txtbox1" onkeypress="if(event.keyCode==13) {brandList.fnSearch();}" escapeXml="false" />
                            </td>
                            <td class="ar">
                                <a href="#none" onclick="brandList.fnSearch();" class="btn_search3 ml5 mr20">검색</a>
                            </td>
                        </tr>
						<tr> 
	       					<td colspan="3" class="blank"></td>
    					</tr>
					</tbody>
				</table>
			</div>
			
			<div class="con_list_option mt33">
				<ul>
					<li>검색건수 : <fmt:formatNumber value="${paginationInfo.totalRecordCount }" pattern="#,###" />건</li>
					<li class="op1"></li>
				</ul>
			</div>
			
			<div class="con_list">
				<table>
					<caption>
						브랜드정보에 대한 목록
					</caption>
					<colgroup>
						<col width="4%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="*%" />
						<col width="10%" />
						<col width="10%" />
						
					</colgroup>
					<thead>
						<tr>
							<th scope="col">No</th>
							<th scope="col">브랜드명</th>
							<th scope="col">브랜드한글명</th>
							<th scope="col">브랜드영문명</th>
							<th scope="col">브랜드상세설명</th>
							<th scope="col">등록일</th>
							<th scope="col">수정일</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			
			<div class="con_paging mt30">
				
		   	</div>
			
			<div class="con_btn mt30">
				<a href="#none" onclick="brandList.fnEdit('I'); return false;" class="btn_write1" >신규</a>
			</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/mgnt/goods/goods.js?20181107000001"></script>
<script type="text/javascript">
<!--
$(document).ready(function() {
	// 화면 초기화
	goodsList.init();
});

//전시카테고리 팝업 callback 함수
function setDisplayShopNo(data) {
	var cataName;
	
	$(data).each(function() {
		console.log("this.dispLrgNm="+this.dispLrgNm);
		console.log("this.dispMidNm="+this.dispMidNm);
		console.log("this.dispSmlNm="+this.dispSmlNm);
		console.log("this.dispThnNm="+this.dispThnNm);
		if(this.dispLrgNm != null && this.dispLrgNm != "") {
			cataName = this.dispLrgNm;
		} 
		if(this.dispMidNm != null && this.dispMidNm != "") {
			cataName = cataName + " > " +this.dispMidNm;
		}
		if(this.dispSmlNm != null && this.dispSmlNm != "") {
			cataName = cataName + " > " +this.dispSmlNm;
		}
		if(this.dispThnNm != null && this.dispThnNm != "") {
			cataName = cataName + " > " +this.dispThnNm;
		}
		
		console.log("cataName="+cataName);
		
		if(cataName == null || cataName == "") {
			cataName = this.dispNm;
		}
		
		$('#searchDispNo').val(this.dispNo);
		$('#searchDispNm').val(cataName);
	
	});

}
//-->
</script>