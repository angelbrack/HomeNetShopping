<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>

<body>

<div id="wrap_pop">
<form name="form1" id="form1" method="post" action="" commandName="searchVO">
	<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/>
	<div class="pop_con_top">
		<div class="pop_con_top_tit">
			품목군 정보
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
						<col style="width: 4%;">
						<col style="width: 21%;">
						<col style="width: 6%;">
						<col style="width: 25%;">
						<col style="width: 13%;">	
					</colgroup>
					<tbody>
						<tr>
							<td colspan="5" class="blank"></td>
						</tr>
						<tr>
							<td class="tit">품목군 </td>
							<td>
								<select id="searchArtcUpCd" name="searchArtcUpCd">
									<option value="">전체</option>
									<c:forEach var="item" items="${searchArtcUpCdList}">
									<option value="${item.artcCd}">${item.artcNm}</option>
									</c:forEach>
								</select>
							</td>
							<td class="tit">품목 </td>
							<td>
								<select id="searchArtcCd" name="searchArtcCd">
									<option value="">-전체-</option>
								</select>
							</td>
							<td>
								<select id="searchArtcCdOption" name="searchArtcCdOption">
									<option value="">선택</option>
									<option value="artcCd">품목코드</option>
								</select>
								<textarea id="searchArtcCdList"  name="searchArtcCdList" style="width:80%;height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="tit">공정위 품목군 </td>
							<td>
								<ctag:code name="searchEcArtcCd" type="S" key="EC_ARTC_CD" selected=""  css="form" />
							</td>
						</tr>
						<tr>
							<td class="tit">구분</td>
							<td colspan="4">
								<label for="searchKey" class="hidden">검색구분</label>
								<select name="searchKey" id="searchKey" style="height:22px;" >
				                    <option value="" 	<c:if test="${empty searchVO.searchKey}">selected</c:if>>-전체-</option>
				                    <option value="001" <c:if test="${searchVO.searchKey eq '001'}">selected</c:if>>품목군명</option>
				                    <option value="002" <c:if test="${searchVO.searchKey eq '002'}">selected</c:if>>품목군 코드(2자리)</option>
				                    <option value="003" <c:if test="${searchVO.searchKey eq '003'}">selected</c:if>>품목명</option>
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
						품목군 정보에 대한 목록
					</caption>
					<colgroup>
						<col width="4%" />
						<col width="4%" />
						<col width="10%" />
						<col width="*" />
						<col width="25%" />
						<col width="25%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">선택</th>
							<th scope="col">No</th>
							<th scope="col">품목코드</th>
							<th scope="col">품목명</th>
							<th scope="col">상위 품목명</th>
							<th scope="col">전자상거래품목명</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${fn:length(resultList) > 0}">
						<c:forEach items="${resultList}" var="item" varStatus="status">
						<tr>
							<td>
								<input type="radio"  id="selRdo_<c:out value="${status.count }"/>" name="selRdo" title="선택" /> 
								<input type="hidden" id="artcCd_<c:out value="${status.count }"/>" 		name="selArtcCd" 		value="<c:out value="${item.artcCd 		}"						/>" 	/>
								<input type="hidden" id="artcNm_<c:out value="${status.count }"/>" 		name="selArtcNm" 		value="<c:out value="${item.artcNm 		}"	escapeXml="false" 	/>" 	/>
							</td>
							<td><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.currentPage-1) * searchVO.recordCountPerPage + status.count)}"/></td>
							<td><c:out value="${item.artcCd }" escapeXml="false" /></td>
							<td><c:out value="${item.artcNm }" escapeXml="false" /></td>
							<td><c:out value="${item.uprArtcNm }" escapeXml="false" /></td>
							<td><c:out value="${item.ecArtcNm }" escapeXml="false" /></td>
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
				<a href="#none" onclick="fnSelArticle(); return false;" class="btn_write1">선택</a>
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
	frm.action = CTX_PATH + "/mgnt/article/articleListPop.do";
	
	frm.submit();
}

// 페이지당 레코드 갯수로 호출
function fnListPerLine(recordCountPerPage) {
	var frm	= document.form1
	
	frm.method = "post";
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/article/articleListPop.do";
	
	frm.submit();
}

// 품목군 선택
function fnSelArticle() {
	var selRdoLen	= $("input:radio[name=selRdo]").length;
	var checkedLen	= $("input:radio[name=selRdo]:checked").length;
	var checkedIdx	= $("input:radio[name=selRdo]").index($("input:radio[name=selRdo]:checked"));
	
	if ( selRdoLen == 0 ) {
		alert("검색된 품목군 정보가 없습니다.");
		return;
	}
	
	if ( checkedLen == 0 ) {
		alert("품목군을 선택하여 주시기 바랍니다.");
		return;
	}
	
	var articleInfo	= new Object();
	
	var artcCd 			= $("input[name=selArtcCd]").eq(checkedIdx).val();
	var artcNm 			= $("input[name=selArtcNm]").eq(checkedIdx).val();
	
	articleInfo.artcCd 		= artcCd 		;
	articleInfo.artcNm 		= artcNm 		;
	
	RunCallbackFunction(displayInfo);
}
//-->
</script>
</body>
</html>