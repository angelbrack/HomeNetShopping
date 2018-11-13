<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>

<body>

<div id="wrap_pop">
<form name="form1" id="form1" method="post" action="" commandName="searchVO">
	<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/>
	<div class="pop_con_top">
		<div class="pop_con_top_tit">
			전시카테고리 정보
		</div>
		<div class="pop_con_top_close">
			<a href="#none" onclick="self.close();" class="btn_close_01"></a>
		</div>
	</div>

	<div id="pop_con">
		<div class="pop_con_c">
		
			<div class="con_search">
				<table style="border:10px">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<td colspan="4" class="blank"></td>
						</tr>
						<tr>
                            <td class="tit"><label for="searchDpmlNo">전시몰</label></td>
                            <td>
                                <select style="width:120px;height:22px;" name="searchDpmlNo" id="searchDpmlNo">
                                    <c:forEach var="mall" items="${mallList}">
										<option value="${mall.dpmlNo}">${mall.dpmlNm}</option>
									</c:forEach>
                                </select>
                            </td>
                            <td class="tit"><label for="searchShopTpCd">매장유형</label></td>
                            <td colspan="2">
                                <ctag:code name="searchShopTpCd" type="S" key="SHOP_TP_CD" selected=""  css="form" />
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="searchDispYn">전시여부</label></td>
                            <td>
                                <select style="width:120px;height:22px;" name="searchDispYn" id="searchDispYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">전시</option>
                                	<option value="N">전시안함</option>
                                </select>
                            </td>
                            <td class="tit"><label for="searchMenuUseYn">메뉴사용여부</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="searchMenuUseYn" id="searchMenuUseYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">메뉴사용</option>
                                	<option value="N">메뉴사용안함</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="searchUseYn">사용여부</label></td>
                            <td colspan="3">
                                <select style="width:120px;height:22px;" name="searchUseYn" id="searchUseYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">사용</option>
                                	<option value="N">사용안함</option>
                                </select>
                            </td>
                        </tr>
						<tr>
							<td class="tit">전시카테고리 검색</td>
							<td colspan="3">
								<label for="searchKey" class="hidden">검색구분</label>
								<select name="searchKey" id="searchKey" style="height:22px;" >
				                    <option value="" 	<c:if test="${empty searchVO.searchKey}">selected</c:if>>-전체-</option>
				                    <option value="000" <c:if test="${searchVO.searchKey eq '001'}">selected</c:if>>품목코드</option>
				                    <option value="001" <c:if test="${searchVO.searchKey eq '002'}">selected</c:if>>품목명</option>
				                </select>
								<label for="searchWord" class="hidden">검색어</label>
								<input type="text" name="searchWord" id="searchWord" value="<c:out value="${searchVO.searchWord}" />" style="width:250px;height:18px;" class="txtbox1" title="검색어" onkeypress="if(event.keyCode==13) {fnSearch();}" />
							</td>
							<td class="ar">
								<a href="#none" onclick="fnSearch(); return false;"  class="btn_search3 ml5 mr20">검색</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div class="con_list">
				<table>
					<caption>
						전시카테고리 정보에 대한 목록
					</caption>
					<colgroup>
						<col width="4%" />
						<col width="4%" />
						<col width="8%" />
						<col width="6%" />
						<col width="*" />
						<col width="10%" />
						<col width="6%" />
						<col width="6%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">선택</th>
							<th scope="col">No</th>
							<th scope="col">몰명</th>
							<th scope="col">전시번호</th>
							<th scope="col">전시명</th>
							<th scope="col">매장유형</th>
							<th scope="col">깊이</th>
							<th scope="col">순위</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${fn:length(resultList) > 0}">
						<c:forEach items="${resultList}" var="item" varStatus="status">
						<tr>
							<td>
								<input type="radio"  id="selRdo_<c:out value="${status.count }"/>" 		name="selRdo" title="선택" /> 
								<input type="hidden" id="dispNo_<c:out value="${status.count }"/>" 		name="selDispNo" 		value="<c:out value="${item.dispNo 		}"						/>" 	/>
								<input type="hidden" id="dispNm_<c:out value="${status.count }"/>" 		name="selDispNm" 		value="<c:out value="${item.dispNm 		}"	escapeXml="false" 	/>" 	/>
								<input type="hidden" id="dispLrgNm_<c:out value="${status.count }"/>" 	name="selDispLrgNm" 	value="<c:out value="${item.dispLrgNm 	}"	escapeXml="false" 	/>" 	/>
								<input type="hidden" id="dispMidNm_<c:out value="${status.count }"/>" 	name="selDispMidNm" 	value="<c:out value="${item.dispMidNm 	}"	escapeXml="false" 	/>" 	/>
								<input type="hidden" id="dispSmlNm_<c:out value="${status.count }"/>" 	name="selDispSmlNm" 	value="<c:out value="${item.dispSmlNm 	}"	escapeXml="false" 	/>" 	/>
								<input type="hidden" id="dispThnNm_<c:out value="${status.count }"/>" 	name="selDispThnNm" 	value="<c:out value="${item.dispThnNm 	}"	escapeXml="false" 	/>" 	/>
							</td>
							<td><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.currentPage-1) * searchVO.recordCountPerPage + status.count)}"/></td>
							<td><c:out value="${item.dpmlNm }" escapeXml="false" /></td>
							<td><c:out value="${item.dispNo }" escapeXml="false" /></td>
							<td class="al pl20 ellip">
								<c:if test="${not empty item.dispLrgNm }">
									<c:out value="${item.dispLrgNm }" escapeXml="false" />
								</c:if>
								<c:if test="${not empty item.dispMidNm }">
									> <c:out value="${item.dispMidNm }" escapeXml="false" />
								</c:if>
								<c:if test="${not empty item.dispSmlNm }">
									> <c:out value="${item.dispSmlNm }" escapeXml="false" />
								</c:if>
								<c:if test="${not empty item.dispThnNm }">
									> <c:out value="${item.dispThnNm }" escapeXml="false" />
								</c:if>
							
							</td>
							<td><c:out value="${item.shopTpNm }" escapeXml="false" /></td>
							<td><c:out value="${item.dpthNo }" escapeXml="false" /></td>
							<td><c:out value="${item.dispPrioRnk }" escapeXml="false" /></td>
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<td colspan="8">
	                    	검색된 데이터가 없습니다.
						</td>
					</c:otherwise>
				</c:choose>	
					</tbody>
				</table>
			</div>
			
			<div class="con_paging mt30">
				<ctag:paging paginationInfo="${paginationInfo}" type="portal" jsFunction="fnListPage" rowControl="true" jsRowFunction="fnListPerLine" />
		   	</div>
			
			<div class="con_btn mt30">
				<a href="#none" onclick="fnSelDisplay(); return false;" class="btn_write1">선택</a>
				<a href="#none" onclick="self.close();" class="btn_list1">닫기</a>
			</div>
			
		</div>
	</div>
	
	
</form>
</div>
<script type="text/javascript">
<!--

var RunCallbackFunction = function() { };

// 검색
function fnSearch() {
	fnListPage(1);
}

//페이징에서 호출
function fnListPage(pageNo) {
	var frm	= document.form1;
	
	$("#form1 #currentPage").val(pageNo);
	
	frm.method = "post";
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/display/displayListPop.do";
	
	frm.submit();
}

// 페이지당 레코드 갯수로 호출
function fnListPerLine(recordCountPerPage) {
	var frm	= document.form1
	
	frm.method = "post";
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/display/displayListPop.do";
	
	frm.submit();
}

// 품목군 선택
function fnSelDisplay() {
	var selRdoLen	= $("input:radio[name=selRdo]").length;
	var checkedLen	= $("input:radio[name=selRdo]:checked").length;
	var checkedIdx	= $("input:radio[name=selRdo]").index($("input:radio[name=selRdo]:checked"));
	
	if ( selRdoLen == 0 ) {
		alert("검색된 전시 카테고리 정보가 없습니다.");
		return;
	}
	
	if ( checkedLen == 0 ) {
		alert("전시 카테고리을 선택하여 주시기 바랍니다.");
		return;
	}
	
	var displayInfo	= new Object();
	
	var dispNo 			= $("input[name=selDispNo]").eq(checkedIdx).val();
	var dispNm 			= $("input[name=selDispNm]").eq(checkedIdx).val();
	var dispLrgNm 		= $("input[name=selDispLrgNm]").eq(checkedIdx).val();
	var dispMidNm 		= $("input[name=selDispMidNm]").eq(checkedIdx).val();
	var dispSmlNm 		= $("input[name=selDispSmlNm]").eq(checkedIdx).val();
	var dispThnNm 		= $("input[name=selDispThnNm]").eq(checkedIdx).val();
	
	displayInfo.dispNo 			= dispNo 		;
	displayInfo.dispNm 			= dispNm 		;
	displayInfo.dispLrgNm 		= dispLrgNm 	;
	displayInfo.dispMidNm 		= dispMidNm 	;
	displayInfo.dispSmlNm 		= dispSmlNm 	;
	displayInfo.dispThnNm 		= dispThnNm 	;
	
	RunCallbackFunction(displayInfo);
}
//-->
</script>
</body>
</html>