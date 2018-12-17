<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="formInit" id="formInit" method="post" action="">
			</form>
			<form name="form1" id="form1" method="post" action="" onSubmit="return false;">
				<!-- 검색 영역 Start -->
				<input type="hidden" id="searchTermOption"			name="searchTermOption"			value="<c:out value="${searchVO.searchTermOption 		}" />"		/>	<!-- 검색 기간구분 			-->
				<input type="hidden" id="searchStartDate"			name="searchStartDate"			value="<c:out value="${searchVO.searchStartDate 		}" />"		/>	<!-- 검색 기간 시작일 		-->
				<input type="hidden" id="searchEndDate"				name="searchEndDate"			value="<c:out value="${searchVO.searchEndDate 		    }" />"		/>	<!-- 검색 기간 종료일 		-->
				<input type="hidden" id="searchGoodsNm"				name="searchGoodsNm"			value="<c:out value="${searchVO.searchGoodsNm 		    }" />"		/>	<!-- 검색 상품명 			-->
				<input type="hidden" id="searchDispNo"				name="searchDispNo"				value="<c:out value="${searchVO.searchDispNo 		    }" />"		/>	<!-- 검색 전시번호 			-->
				<input type="hidden" id="searchDispNm"				name="searchDispNm"				value="<c:out value="${searchVO.searchDispNm 		    }" />"		/>	<!-- 검색 전시명 			-->
				<input type="hidden" id="searchArtcCd"				name="searchArtcCd"				value="<c:out value="${searchVO.artcCd 		    		}" />"		/>	<!-- 검색 품목코드 			-->
				<input type="hidden" id="searchArtcNm"				name="searchArtcNm"				value="<c:out value="${searchVO.artcNm 		    		}" />"		/>	<!-- 검색 품목명 			-->                                                                                                                                      
				<input type="hidden" id="searchKey"					name="searchKey"				value="<c:out value="${searchVO.searchKey 		        }" />"		/>	<!-- 검색 검색구분 			-->
				<input type="hidden" id="searchWord"				name="searchWord"				value="<c:out value="${searchVO.searchWord 		        }" />"		/>	<!-- 검색 검색어 			-->
				                                                                                                                                            
				<input type="hidden" id="cmd"						name="cmd"						value="<c:out value="${searchVO.cmd 			        }" />"		/>  <!-- 등록/수정 구분 		-->
				<input type="hidden" id="currentPage" 				name="currentPage" 				value="<c:out value="${searchVO.currentPage 	        }" />" 		/>	<!-- 검색 페이지번호 		-->	
				<input type="hidden" id="goodsNo"					name="goodsNo"					value="<c:out value="${info.goodsNo 			        }" />"		/>  <!-- 브랜드번호 			-->
				
			<!--contents 시작 -->
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
			
			<div class="con_write mt24"> 
				<table>
					<caption>상품기본정보</caption>
					<colgroup>
						<col width="20%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<!-- 상품기본정보 -->
						<tr>
							<th scope="row"><label for="goodsNm">상품유형구분</label> <span class="fwb2 fcop4">*</span></th>
							<td colspan="3">
								<ctag:code name="goodsTpCd" type="S" key="GOODS_TP_CD" selected="${info.goodsTpCd 		}" optdef="- 선택 -" css="form" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="mdlNm">모델명 </label> <span class="fwb2 fcop4">*</span></th>
							<td colspan="3">
								<input type="text" name="mdlNm" id="mdlNm" title='모델명 ' style="width:400px;height:18px;" class="txtbox1" value="<c:out value="${info.mdlNm}"  escapeXml="false" />" readOnly/>
								<a href="#none" onclick="openModelSearchPopup();" class="btn_search3 ml2 mr2" id="btnSearchModel">검색</a>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="artcCd">품목코드 </label> <span class="fwb2 fcop4">*</span></th>
							<td colspan="3">
								<input type="text" name="artcCd" id="artcCd" title='품목코드' maxlength="20" style="width:100px;height:18px;" class="txtbox1" value="<c:out value="${info.artcCd }" />" readOnly />
                            	<input type="text" name="artcNm" id="artcNm" title='품목명' maxlength="20" style="width:100px;height:18px;" class="txtbox1" value="<c:out value="${info.artcNm }" />" readOnly />
                            	<a href="#none" onclick="openGoodsArticleSearchPopup();" class="btn_search3 ml2 mr2" id="btnSearchGoodsArticle">검색</a>
								<a href="#none" onclick="initGoodsArticle();" class="btn_search3 ml2 mr2" id="btnInitGoodsArticle">초기화</a>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="tdfSctCd">과/면세 구분</label> </th>
							<td colspan="3" class="pt10 pb10">
								<ctag:code name="tdfSctCd" type="S" key="TDF_SCT_CD" selected="${info.tdfSctCd 		}" optdef="- 선택 -" css="form" />
							</td>
						</tr>
						<!-- // 상품기본정보 -->
						<tr>
							<th scope="row"><label for="recuDtlCn">전시카테고리</label> </th>
							<td colspan="3" class="pt10 pb10">
								<input type="text" name="dispNo" id="dispNo" title='전시번호' maxlength="20" style="width:100px;height:18px;" class="txtbox1" value="<c:out value="${info.dispNo }" />" readOnly />
                            	<input type="text" name="dispNm" id="dispNm" title='전시명' maxlength="20" style="width:300px;height:18px;" class="txtbox1" value="<c:out value="${info.dispNm }" />" readOnly />
                            	<a href="#none" onclick="openDisplaySearchPopup();" class="btn_search3 ml2 mr2" id="btnSearchDisp">검색</a>
                            	<a href="#none" onclick="initGoodsDisplay();" class="btn_search3 ml2 mr2" id="btnInitGoodsDisplay">초기화</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="con_btn mt30">
				<a href="#none" onclick="goodsHandle.fnSave();" class="btn_write1">저장</a>
			<c:if test="${not empty info.goodsNo}" >
				<a href="#none" onclick="goodsHandle.fnDelete();" class="btn_delete1">삭제</a>
			</c:if>
				<a href="#none" onclick="goodsHandle.fnList('${searchVO.currentPage}');return false;" class="btn_list1">목록</a>
			</div>	
			
			<!--contents 끝 -->
			</form>
		</div>
	</div>
</div>

<div id="thover"></div>
<div id="tpopup" style="display:none">
	<img id="imgShowPreview" src="" style="width : 0px; height : 0px;"> 
    <div id="tclose" onclick="fnImgPreviewClose()">X</div>    
</div>

<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/mgnt/goods/goods.js?20180919100000"></script>
<script type="text/javascript">
<!--
$(document).ready(function() {
	// 화면 초기화
	goodsHandle.init();
	
	/* $("#apndFile").fnFileUpload({
		  path 		: "BRAND.IMG"		
		, idx 		: ""				
		, fileCo 	: 10
		, imgYn		: "Y"
		, sortYn	: "Y"
	});
 	
	fnFileEdit("", "<c:out value='${(fileList)}'/>", "", "Y", "Y"); */
});

//전시카테고리 팝업 callback 함수
function setDisplayShop(data) {
	var cataName;
	
	$(data).each(function() {
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
		
		if(cataName == null || cataName == "") {
			cataName = this.dispNm;
		}
		
		$('#dispNo').val(this.dispNo);
		$('#dispNm').val(cataName);
	
	});
}

// 전시카테고리 초기화
function initGoodsDisplay() {
	$('#dispNo').val('');
	$('#dispNm').val('');
}

// 품목군 팝업 callback 함수
function setGoodsArticle(data) {
	$(data).each(function() {
		$('#artcCd').val(this.artcCd);
		$('#artcNm').val(this.artcNm);
	});
}

// 품목군 초기화
function initGoodsArticle() {
	$('#artcCd').val('');
	$('#artcNm').val('');
}
//-->
</script>