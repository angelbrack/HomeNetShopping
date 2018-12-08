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
						<col style="width: 13%;">
						<col style="width: 15%;">
						<col style="width: 10%;">
						<col style="width: 10%;">
						<col style="width: *;">	
					</colgroup>
					<tbody>
						<tr>
							<td colspan="5" class="blank"></td>
						</tr>
						<tr>
							<td class="tit">품목군 </td>
							<td>
								<select id="searchArtcUpCd" name="searchArtcUpCd">
									<option value="">- 선택 -</option>
									<c:forEach var="item" items="${searchArtcUpCdList}">
										<option value="${item.artcCd}">${item.artcNm}</option>
									</c:forEach>
								</select>
							</td>
							<td class="tit">품목 </td>
							<td>
								<select id="searchArtcCd" name="searchArtcCd">
									<option value="">- 선택 -</option>
								</select>
							</td>
							<td>
								<select id="searchArtcCdOption" name="searchArtcCdOption">
									<option value="">- 선택 -</option>
									<option value="artcCd">품목코드</option>
								</select>
								<textarea id="searchArtcCdList"  name="searchArtcCdList" style="width:80%;height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="tit">공정위 품목군</td>
							<td colspan="4">
								<ctag:code name="searchEcArtcCd" type="S" key="EC_ARTC_CD" selected="" optdef="- 선택 -" css="form" />
							</td>
						</tr>
						<tr>
							<td class="tit">구분</td>
							<td colspan="3">
								<label for="searchKey" class="hidden">검색구분</label>
								<select name="searchKey" id="searchKey" style="height:22px;" >
				                    <option value="" 	<c:if test="${empty searchVO.searchKey}">selected</c:if>>- 선택 -</option>
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
					<tbody id="itemsList">
					</tbody>
				</table>
			</div>
			
			<div class="con_paging mt30">
				<div class="pagination" id="pager">
                </div>
		   	</div>
			
			<div class="con_btn mt30">
				<a href="#none" onclick="fnSelArticle(); return false;" class="btn_write1">선택</a>
				<a href="#none" onclick="self.close();" class="btn_list1">닫기</a>
			</div>
			
		</div>
	</div>
	
	
</form>
</div>
<!-- Templates -->
<p style="display:none"><label for="listTmp"></label><textarea id="listTmp" rows="0" cols="0"><!--
	{#foreach $T.list as list}
	<tr>
		<td>
			<input type="radio"  id="selRdo_{$T.list$index}" 		name="selRdo" 			title="선택" /> 
			<input type="hidden" id="artcCd_{$T.list$index}" 		name="selArtcCd" 		value="{$T.list.artcCd}" 	/>
			<input type="hidden" id="artcNm_{$T.list$index}" 		name="selArtcNm" 		value="{$T.list.artcNm}" 	/>
		</td>
		<td>{$T.paginationInfo.totalRecordCount - (($T.paginationInfo.currentPageNo-1) * $T.paginationInfo.pageSize + $T.list$index)}</td>
		<td>{$T.list.artcCd}</td>
		<td>{$T.list.artcNm}</td>
		<td>{$T.list.uprArtcNm}</td>
		<td>{$T.list.ecArtcNm}</td>
	</tr>
	{#/for}
--></textarea></p>
<script type="text/javascript">
<!--

var RunCallbackFunction = function() { };

$(document).ready(function() {
	// 품목군 change
	$("#searchArtcUpCd").change(function() {
		var data = {};
		var searchArtcUpCd = $("select[name=searchArtcUpCd] option:selected").val();
		data.searchArtcUpCd = searchArtcUpCd;
		data.searchArtcDpthNo = 5;
		
		var param	= JSON.stringify(data);
		
		var obj = $("#searchArtcCd");
		var options = obj.prop('options');
		
		if ( searchArtcUpCd == '' ) {
			// selectbox option 초기화
			initSelectBoxOption(obj, options)
		}
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/article/selectArticleCodeList.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var artcCdList		= data.artcCdList;
				
				// selectbox option 초기화
				initSelectBoxOption(obj, options)
				
				$(artcCdList).each(function(){
					lastIndex = options.length;
					options[lastIndex] = new Option(this.artcNm, this.artcCd);  
				});
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});	
	});
	
	// 
	$("#searchArtcCdOption").change(function(){
		$("#searchArtcCdList").text("");
	});
	
	fnSearch();
});

// 검색
function fnSearch() {
	fnListPage(1);
}

//페이징에서 호출
function fnListPage(pageNo) {
	$("#form1 #currentPage").val(pageNo);
	
	var data	= {};

	var searchArtcUpCd 			= $("select[name=searchArtcUpCd] option:selected").val();		// 검색 품목군
	var searchArtcCd			= $("select[name=searchArtcCd] option:selected").val();			// 검색 품목
	var searchArtcCdOption		= $("select[name=searchArtcCdOption] option:selected").val();
	var searchArtcCdList		= $("#searchArtcCdList").val();
	var searchEcArtcCd			= $("select[name=searchEcArtcCd] option:selected").val();		// 검색 공정위품목군
	var searchKey				= $("select[name=searchKey] option:selected").val();			// 검색구분
	var searchWord				= $("#searchWord").val();										// 검색어
	
	
	data.currentPage 			= $("#form1 #currentPage").val();								// 현재 페이지번호
	data.recordCountPerPage		= $("#form1 #recordCountPerPage").val();						// 페이지당 레코드 갯수
	data.searchArtcUpCd			= searchArtcUpCd;												// 검색 품목군  
	data.searchArtcCd			= searchArtcCd;                                                 // 검색 품목   
	data.searchArtcCdOption		= searchArtcCdOption;
	data.searchEcArtcCd			= searchEcArtcCd;                                               // 검색 공정위품목군   
	data.searchKey				= searchKey;                                                    // 검색구분        
	data.searchWord				= searchWord;                                                   // 검색어         
	
	var param	= JSON.stringify(data);
	
	$.ajax({
		async : false,
		type: 'POST',
		url: CTX_PATH + "/mgnt/article/selectArticleList.json",
		data: param,
		contentType: 'application/json',
		dataType:"json",
		success : function (data) {
			//list목록
			$("#itemsList").setTemplateElement("listTmp", [], {filter_data: false}).processTemplate(data);
			//페이징
			PageUtil("pager", data.paginationInfo, "fnListPage");
		}, 
		error: function(data, textStatus, errorThrown) {
			fnAjaxError(data);
		}
	});
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
	console.log("articleInfo : ", JSON.stringify(articleInfo));
	RunCallbackFunction(articleInfo);
}
//-->
</script>
</body>
</html>