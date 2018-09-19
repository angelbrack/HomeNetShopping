<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="formInit" id="formInit" method="post" action="">
			</form>
			<form name="form1" id="form1" method="post" action="" onSubmit="return false;">
				<!-- 검색 영역 Start -->
				<input type="hidden" id="searchKey"					name="searchKey"				value="<c:out value="${searchVO.searchKey 		}" />"		/>	<!-- 검색 검색구분 		-->
				<input type="hidden" id="searchWord"				name="searchWord"				value="<c:out value="${searchVO.searchWord 		}" />"		/>	<!-- 검색 검색어 			-->
				
				<input type="hidden" id="cmd"						name="cmd"						value="<c:out value="${searchVO.cmd 			}" />"		/>  <!-- 등록/수정 구분 		-->
				<input type="hidden" id="currentPage" 				name="currentPage" 				value="<c:out value="${searchVO.currentPage 	}" />" 		/>	<!-- 검색 페이지번호 		-->	
				<input type="hidden" id="brndNo"					name="brndNo"					value="<c:out value="${info.brndNo 				}" />"		/>  <!-- 브랜드번호 			-->
				
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
					브래드정보관리
				</div>	
			</div>
			
			<div class="con_write mt24"> 
				<table>
					<caption>브랜드 정보</caption>
					<colgroup>
						<col width="20%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><label for="brndNm">브랜드명</label> <span class="fwb2 fcop4">*</span></th>
							<td colspan="3">
								<input type="text" name="brndNm" id="brndNm" title='브랜드명' maxlength="60" style="width:400px;height:18px;" class="txtbox1" value="<c:out value="${info.brndNm}"  escapeXml="false" />" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="brndKorNm">브랜드한글명 </label> <span class="fwb2 fcop4">*</span></th>
							<td colspan="3">
								<input type="text" name="brndKorNm" id="brndKorNm" title='브랜드한글명 ' maxlength="60" style="width:400px;height:18px;" class="txtbox1" value="<c:out value="${info.brndKorNm}"  escapeXml="false" />" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="brndEngNm">브랜드영문명 </label> <span class="fwb2 fcop4">*</span></th>
							<td colspan="3">
								<input type="text" name="brndEngNm" id="brndEngNm" title='브랜드영문명' maxlength="60" style="width:400px;height:18px;" class="txtbox1" value="<c:out value="${info.brndEngNm}"  escapeXml="false" />" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="apndFile">첨부파일</label> </th>
							<td  class="pt10 pb10" colspan="3">
								<div style="width:700px;padding:0px;" id="apndFile">							 	
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="recuDtlCn">브랜드상세설명</label> </th>
							<td colspan="3" class="pt10 pb10">
								<textarea id="brndDescCont" name="brndDescCont" class="textarea richedit" style="width:98%; height:100px;"><c:out value="${info.brndDescCont }" escapeXml="false" /></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="con_btn mt30">
				<a href="#none" onclick="brandHandle.fnSave();" class="btn_write1">저장</a>
			<c:if test="${not empty info.brndNo}" >
				<a href="#none" onclick="brandHandle.fnDelete();" class="btn_delete1">삭제</a>
			</c:if>
				<a href="#none" onclick="brandHandle.fnList('${searchVO.currentPage}');return false;" class="btn_list1">목록</a>
			</div>	
			
			<!--contents 끝 -->
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/mgnt/brand/brand.js?20180919100000"></script>
<script type="text/javascript">
<!--
$(document).ready(function() {
	// 화면 초기화
	brandHandle.init();
	
	$("#apndFile").fnFileUpload({
		  path 		: "BRAND.IMG"		
		, idx 		: ""				
		, fileCo 	: 10					
	});
 	//fnFileEdit("RECU.REGISTER", "", "<c:out value='${(fileList)}'/>");
});
//-->
</script>