<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="form1" id="form1" method="post" action="">
				<input type="hidden" id="cmd"					name="cmd"					value="I"					/> <!-- 등록/수정 구분 		-->
				<input type="hidden" id="treeId" 				name="treeId" 				value="" 					/> <!-- tree id	 		-->
				<input type="hidden" id="treeDepth" 			name="treeDepth" 			value="" 					/> <!-- tree depth	 	-->
				
				<input type="hidden" id="dpmlNo" 				name="dpmlNo" 				value="" 					/> <!-- 전시몰번호	 		-->
				<input type="hidden" id="shopTpCd" 				name="shopTpCd" 			value="" 					/> <!-- 매장유형	 		-->
				<input type="hidden" id="dispNo" 				name="dispNo" 				value="" 					/> <!-- 전시번호	 		-->
				<input type="hidden" id="uprDispNo" 			name="uprDispNo" 			value="" 					/> <!-- 상위전시번호	 		-->
				
				<input type="hidden" id="dispLrgNo" 			name="dispLrgNo" 			value="" 					/> <!-- 전시대번호	 		-->
				<input type="hidden" id="dispMidNo" 			name="dispMidNo" 			value="" 					/> <!-- 전시중번호	 		-->
				<input type="hidden" id="dispSmlNo" 			name="dispSmlNo" 			value="" 					/> <!-- 전시소번호	 		-->
				<input type="hidden" id="dispThnNo" 			name="dispThnNo" 			value="" 					/> <!-- 전시세번호	 		-->
			<!--contents-->
			<div class="con_top">
				<div class="tit">
					<c:out value="${requestScope.USER_PGM_AUTH.optrPgmNm}" />
				</div>
				<div class="navi">
					<img src="<ctag:conf key="THEME.PATH" />mgnt/images/ic_01.jpg" alt="" />
					<span>&nbsp;>&nbsp;</span>
					전시매장 관리
					<span>&nbsp;>&nbsp;</span>
					전시매장 관리
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
                            <td class="tit"><label for="searchDpmlNo">전시몰</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="searchDpmlNo" id="searchDpmlNo">
                                    <c:forEach var="mall" items="${mallList}">
										<option value="${mall.dpmlNo}">${mall.dpmlNm}</option>
									</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="searchShopTpCd">매장유형</label></td>
                            <td colspan="2">
                                <ctag:code name="searchShopTpCd" type="S" key="SHOP_TP_CD" selected=""  css="form" />
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="searchDispYn">전시여부</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="searchDispYn" id="searchDispYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">전시</option>
                                	<option value="N">전시안함</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
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
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="searchUseYn" id="searchUseYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">사용</option>
                                	<option value="N">사용안함</option>
                                </select>
                            </td>
                        </tr>
						<tr> 
	       					<td colspan="3" class="blank"></td>
    					</tr>
					</tbody>
				</table>
			</div>
			
			<div class="con_btn mt10 mb10">
				<a href="#none" onclick="displayTreeList.fnAdd(); return false;" class="btn_write1" >신규</a>
				<a href="#none" onclick="displayTreeList.fnSave(); return false;" class="btn_apply1" >저장</a>
			</div>
			
			<div class="con_list">
				<table>
					<caption>
						전시매장정보에 대한 관리
					</caption>
					<colgroup>
						<col width="20%" />
						<col width="5%" />
						<col width="*" />						
					</colgroup>					
					<tbody>		
						<tr>
							<td>
								<div id="treeArea" style="width: 250px; height: 1000px; background-color: #f5f5f5; border: 1px solid Silver;" >
									<div id="treeboxbox_tree" class="tree clear" setImagePath="/html/script/css/images/dhtmlxtree/" xclass="dhtmlxTree" style="display:none;width: 250px; height: 650px; overflow-x:auto;overflow-y:auto;">
										<ul>
											<li id="treeRoot"><span id="spDpmlNm"></span>
												<ul>
													<li></li>
												</ul>
											</li>
										</ul>	
									</div>
								</div>
							</td>
							<td>&nbsp;</td>
							<td id="detailArea">
								<div class="con_write" style="height: 1000px;">
									<table>
										<caption>전시</caption>
										<colgroup>
											<col width="20%" />
											<col width="30%" />
											<col width="20%" />
											<col width="*" />
										</colgroup>
										<tbody>
											<tr>
												<th scope="row"><label for="dispNm">전시카테고리명</label> <span class="fwb2 fcop4">*</span></th>
												<td colspan="3">
													<input type="text" name="dispNm" id="dispNm" value="<c:out value="${info.dispNm }" />" maxlength="100" class="txtbox1"  style="width:90%;height:18px;" />
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="dispTitExpMethCd">매장 타이틀 노출유형</label></th>
												<td colspan="3">
													<c:set var="dispTitExpMethCd" value="" />
													<c:if test="${empty info }">
														<c:set var="dispTitExpMethCd" value="01" />
													</c:if>
													<ctag:code name="dispTitExpMethCd" type="R" key="DISP_TIT_EXP_METH_CD" selected="${dispTitExpMethCd }"  css="form" />
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="gnbExpMethCd">매장 메뉴 노출유형 </label></th>
												<td colspan="3">
													<c:set var="gnbExpMethCd" value="" />
													<c:if test="${empty info }">
														<c:set var="gnbExpMethCd" value="01" />
													</c:if>
													<ctag:code name="gnbExpMethCd" type="R" key="GNB_EXP_METH_CD" selected="${gnbExpMethCd }"  css="form" />
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="apndFile0">매장 타이틀 이미지</label> </th>
												<td  class="pt10 pb10" colspan="3">
													<div style="width:700px;padding:0px;" id="apndFile0">							 	
													</div>
													<div class="img_list" id="apndImgDiv">
														<ul id="apndImgUl1">
														</ul>
													</div>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="apndFile1">매장 메뉴 이미지</label> </th>
												<td  class="pt10 pb10" colspan="3">
													<div style="width:700px;padding:0px;" id="apndFile1">							 	
													</div>
													<div class="img_list" id="apndImgDiv">
														<ul id="apndImgUl2">
														</ul>
													</div>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="shopDescCont">매장 설명 내용</label> </th>
												<td colspan="3" class="pt10 pb10">
													<textarea id="shopDescCont" name="shopDescCont" class="txtbox1" style="width:98%; height:100px;"></textarea>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="dpthNo">카테고리 깊이 </label> </th>
												<td>
													<input type="text" name="dpthNo" id="dpthNo" value="" class="txtbox1"  style="width:20%;height:18px;" disabled/>
												</td>
												<th scope="row"><label for="tlwtLfYn_Y">Leaf 카테고리 여부 </label></th>
												<td>
													<input type="radio" id="tlwtLfYn_Y" name="tlwtLfYn" value="Y" <c:if test='${info.tlwtLfYn eq "Y" }'>checked="checked"</c:if> class="form" title="" style=""><label for="tlwtLfYn_Y" class="">&nbsp;예</label>
													<input type="radio" id="tlwtLfYn_N" name="tlwtLfYn" value="N" <c:if test='${empty info or info.tlwtLfYn eq "N" }'>checked="checked"</c:if> class="form" title="" style=""><label for="tlwtLfYn_N" class="">&nbsp;아니오</label>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="dispPrioRnk">전시우선순위</label> <span class="fwb2 fcop4">*</span></th>
												<td>
													<input type="text" name="dispPrioRnk" id="dispPrioRnk" value="<c:out value="${info.dispPrioRnk }" />" maxlength="3" class="txtbox1"  style="width:100px;height:18px;" />
													<span>1 ~ 999 범위 내 입력</span>
												</td>
												<th scope="row"><label for="dispYn_Y">전시여부 </label></th>
												<td>
													<input type="radio" id="dispYn_Y" name="dispYn" value="Y" <c:if test='${info.dispYn eq "Y" }'>checked="checked"</c:if> class="form" title="" style=""><label for="dispYn_Y" class="">&nbsp;전시</label>
													<input type="radio" id="dispYn_N" name="dispYn" value="N" <c:if test='${empty info or info.dispYn eq "N" }'>checked="checked"</c:if> class="form" title="" style=""><label for="dispYn_N" class="">&nbsp;전시안함</label>
												</td>
											</tr>
											<tr>
												
												<th scope="row"><label for="useYn_Y">사용여부 </label></th>
												<td>
													<input type="radio" id="useYn_Y" name="useYn" value="Y" <c:if test='${info.useYn eq "Y" }'>checked="checked"</c:if> class="form" title="" style=""><label for="useYn_Y" class="">&nbsp;사용</label>
													<input type="radio" id="useYn_N" name="useYn" value="N" <c:if test='${empty info or info.useYn eq "N" }'>checked="checked"</c:if> class="form" title="" style=""><label for="useYn_N" class="">&nbsp;사용안함</label>
												</td>
												<th scope="row"><label for="menuUseYn_Y">메뉴사용 여부 </label></th>
												<td>
													<input type="radio" id="menuUseYn_Y" name="menuUseYn" value="Y" <c:if test='${info.menuUseYn eq "Y" }'>checked="checked"</c:if> class="form" title="" style=""><label for="menuUseYn_Y" class="">&nbsp;사용</label>
													<input type="radio" id="menuUseYn_N" name="menuUseYn" value="N" <c:if test='${empty info or info.menuUseYn eq "N" }'>checked="checked"</c:if> class="form" title="" style=""><label for="menuUseYn_N" class="">&nbsp;사용안함</label>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="prtTpCd">출력유형</label> </th>
												<td>
													<ctag:code name="prtTpCd" type="S" key="PRT_TP_CD" selected="${info.prtTpCd }"  css="form" />
												</td>
												<th scope="row"><label for="prtTpCd">전시코너수</label> </th>
												<td>
													<a href="javascript:goCornerList('shop');">1개</a>
												</td>
											</tr>
											<tr id="printType06" style="display:none" name="printType"></tr>
											<tr id="printType01" style="display:none" name="printType">
												<th scope="row"><label for="tmplNm">전시 템플릿</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="hidden" id="tmplNo" name="tmplNo" value="<c:out value="${info.tmplNo }" />" />
													<input type="text" name="tmplNm" id="tmplNm" value="" class="txtbox1"  style="width:200px;height:18px;" disabled />
													<a href="#none" onclick="searchDisplayTemplate('displayShopForm');return false;" class="btn_search3 ml5 mr20" id="btnSearchDisplayTemplate">검색</a>
												</td>
											</tr>
											<tr id="printType02" style="display:none" name="printType"> 
												<th scope="row"><label for="lnkUrlAddr">대상 URL</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="text" name="lnkUrlAddr" id="lnkUrlAddr" value="<c:out value="${info.lnkUrlAddr }" />" class="txtbox1"  style="width:500px;height:18px;" />
												</td>
											</tr>
											<tr id="printType04" style="display:none" name="printType">
												<th scope="row"><label for="lnkSpdpHhNo40">카테고리매장번호</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="text" id="lnkSpdpHhNo40" value="" class="txtbox1"  style="width:200px;height:18px;" readOnly />
													<a href="#none" onclick="searchDisplayShop('displayShopForm');return false;" class="btn_search3 ml5 mr20" id="btnSearchDisplayShop">검색</a>
												</td>
											</tr>
											<tr id="printType05" style="display:none" name="printType">
												<th scope="row"><label for="lnkSpdpHhNo50">기획전매장번호</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="text" id="lnkSpdpHhNo50" value="" class="txtbox1"  style="width:200px;height:18px;" readOnly />
													<a href="#none" onclick="searchSpecialDisplay('displayShopForm');return false;" class="btn_search3 ml5 mr20" id="btnSearchDisplayShop">검색</a>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="lstSortCd">리스트소팅값</label> </th>
												<td colspan="3">
													<ctag:code name="lstSortCd" type="S" key="LST_SORT_CD" selected="${info.lstSortCd }"  css="form" />
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="apndFile2">매장 부가 이미지</th>
												<td  class="pt10 pb10" colspan="3">
													<div style="width:700px;padding:0px;" id="apndFile2">							 	
													</div>
													<div class="img_list" id="apndImgDiv">
														<ul id="apndImgUl3">
														</ul>
													</div>
												</td>
											</tr>
											<!--  품목군 매핑 영역 추가 -->
											<tr id="searchArticle">
												<th scope="row"><label for="lstSortCd">품목군</label> </th>
												<td colspan="3">
													<!-- <div id="target1">						
														<input type="text" id="articleCode1" name="articleCode1" value="" class="txtbox1"  style="width:80px;height:18px;" readOnly />
														<input type="text" id="articleName1" name="articleName1" value="" class="txtbox1"  style="width:250px;height:18px;" readOnly />
														<span class="hahahoho">
															<a href="javascript:searchDisplayArticle('displayShopForm',1);" name="popupBtnArticle1" id="popupBtnArticle1" class="btn_search4">검색</a>
									 						<a href="javascript:addNoArticleType(1);" name="addTargetBtn" class="btn_plus2">추가</a>
															<a href="javascript:removeNoArticleType(1);" name="removeTargetBtn" style="display: none;" class="btn_minus2">삭제</a>
														</span>
													 </div> -->
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</td>						
						</tr>
					
					</tbody>
				</table>
			</div>
			</form>
		</div>
	</div>
</div>
<link rel="stylesheet" type="text/css" href="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxtree.css">
<script src="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxcommon.js"></script>
<script src="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxtree.js"></script>
<script src="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxtree_start.js"></script>
<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/mgnt/display/display.js?20181113000001"></script>
<script type="text/javascript">
<!--

var tree = dhtmlXTreeFromHTML("treeboxbox_tree"); // for script conversion
tree.setOnClickHandler(displayTreeList.fnClickHandler);
tree.setOnOpenHandler(displayTreeList.fnOpenHandler);
$('#treeboxbox_tree').show();

$(document).ready(function() {
	// 화면 초기화
	displayTreeList.init();
	
	$("#apndFile0").fnFileUpload({
		  path 		: "DISPLAY.IMG"		
		, idx 		: "1"				
		, fileCo 	: 1
		, imgYn		: "Y"
	});
	
	$("#apndFile1").fnFileUpload({
		  path 		: "DISPLAY.IMG"		
		, idx 		: "2"				
		, fileCo 	: 1
		, imgYn		: "Y"
	});
	
	$("#apndFile2").fnFileUpload({
		  path 		: "DISPLAY.IMG"		
		, idx 		: "3"				
		, fileCo 	: 1
		, imgYn		: "Y"
	});
	
});

/*
 * 첨부파일 수정시
 */
function fnFileEdit(idx, obj, addSavePath, imgYn, sortYn, orienteYn){
	
	if(typeof obj === "string" && obj.length > 0) {
		
		var fileObj = obj.replaceAll("&#034;", '"').toString();
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload";
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		/*var url = uploadingUrl + "?pathkey=" + path;
		if(addSavePath != undefined && addSavePath != "") {
			url += "&addSavePath=" + addSavePath;
		}*/
		var url = "";
		var jsonFileArray = JSON.parse(fileObj);
		
		$.each(jsonFileArray, function(i, jsonObj) {
			if(jsonObj.bnrImgPathNm != undefined && jsonObj.bnrImgFileNm != undefined) {
				var fileHtml 		= "";	
				var fileName 		= jsonObj.bnrImgFileNm;
				var realFileName	= jsonObj.bnrImgFileNm;
				var fileSize		= 0;
				var filePath		= jsonObj.bnrImgPathNm;
				
				var fileInfo = fileName+"|"+realFileName+"| |"+ fileSize+"|"+filePath;
				
				if(fileHtml != "") {
					fileHtml += "	<br />";
				}
				
				url	= uploadingUrl + "?filePath="+filePath;
				
				if ( imgYn != "Y" ) {
					fileHtml += " <li name=\"fileGubun"+idx+"\"> ";
	                fileHtml += "   <span class=\"m-r-10 font-dark-grey underline\"><a href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(fileName))+"\" target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(fileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" ></span>";
	                fileHtml += "   <a href=\"#none\" class=\"btn-delete\" onclick=\"onFileDelede(this)\">&times;</a> ";
	                fileHtml += " </li> ";
					
					$("#uploaded-files"+idx).append(fileHtml);
				}
				
				var fileImgHtml = "";
                if ( imgYn == "Y" ) {	 
                	
                	fileImgHtml += "<li id='Img_"+realFileName.split('.')[0]+"'>";
                	fileImgHtml += "	<div class=\"img\">";
                	if ( orienteYn == "Y" ) {
                		fileImgHtml += "		<img src=\""+decodeURI(decodeURIComponent(filePath))+"/"+realFileName+"\" exif=\"true\" name=\"exifImg\" onload=\"fnUploadImg(this);\" />";
                	} else {
                		fileImgHtml += "		<img src=\""+decodeURI(decodeURIComponent(filePath))+"/"+realFileName+"\" exif=\"true\" name=\"exifImg\" />";
                	}
                	fileImgHtml += "	</div>";
                	fileImgHtml += "	<div class=\"img_del\"><a href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(fileName))+"\" target=\"fileHiddenFrame\">다운로드</a><a href=\"#\" onClick=\"onImgFileDelete(this)\">삭제</a></div>";
                	fileImgHtml += "	<div class=\"layer_img\">";
                	fileImgHtml += "		<img src=\""+decodeURI(decodeURIComponent(filePath))+"/"+realFileName+"\">";
                	fileImgHtml += "	</div>";
                	fileImgHtml += "<input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" /></li>";
	                
	                $("#apndImgUl"+idx).append(fileImgHtml);
				}
				
				fileYn = "Y";
			}
		});
		
		if ( imgYn == "Y" ) {
			$('.img_list li').on({
				'mouseover':function(){
					if($(this).find('.layer_img').css('display') == 'none'){
						$(this).find('.layer_img').show();
					}
				},
				'mouseleave':function(){
					$('.img_list li').find('.layer_img').hide();
				}
			});
		}
		
		if ( sortYn == "Y" ) {
			$("#apndImgUl"+idx).sortable()
		}
		
	}
}

function addNoArticleType(idx) {
	
	$("#searchArticle").find("div:last").after(getAticletHtml());
	addArticleNoMultiType(idx);

	var divCnt = $("#searchArticle").find("div").length;
	var cnt = divCnt-1;
	for ( var i=divCnt; i>0; i-- ) {
		if ( i == divCnt ) {
			$("[name=addTargetBtn]").eq(cnt).show();
		} else {
			$("[name=addTargetBtn]").eq(cnt).hide();
		}
		
		
		if ( i == divCnt ) {
			$("[name=removeTargetBtn]").eq(cnt).hide();
		} else {
			$("[name=removeTargetBtn]").eq(cnt).show();
		}
		
		cnt--;
	}
	
	/* if($("#searchArticle").find("div").length > 2)
		$("#searchArticle").find("[name=removeTargetBtn]").show();
	$("#searchArticle").find("div").eq(divCnt-2).find("[name=addTargetBtn]").hide(); */
}

function addArticleNoMultiType(idx) {
	
	var nIdx = idx+1;
	var obj = $("#searchArticle").find("div:last");
	var divCnt = $("#searchArticle").find("div").length;
	obj.attr("id", "target"+nIdx);
	obj.find("[name=articleCode1]").attr("id", "articleCode"+nIdx);
 	obj.find("[name=articleName1]").attr("id", "articleName"+nIdx);
 	obj.find("[name=articleCode1]").val(null);
 	obj.find("[name=articleName1]").val(null);
	
	//obj.find("[name=popupBtnArticle1]").attr("onClick" , "searchDisplayArticle('displayShopForm',"+nIdx+");"); 
	obj.find("[name=popupBtnArticle1]").attr("href" , "javascript:searchDisplayArticle('displayShopForm',"+nIdx+");"); 
	obj.find("[name=addTargetBtn]").attr("href", "javascript:addNoArticleType("+nIdx+");");
	obj.find("[name=removeTargetBtn]").attr("href", "javascript:removeNoArticleType("+nIdx+");");
}

function getAticletHtml(idx) {
    var html = "<div id='target"+idx+"'>"+$("#searchArticle td:eq(0)").find("div:last").html()+"</div>";
    return html;    
}

function removeNoArticleType(idx) {

	$("#target"+idx).remove();
	$("#articleCode"+idx).remove();
	$("#articleName"+idx).remove();

	
	var divCnt = $("#searchArticle").find("div").length;
	var cnt = divCnt-1;
	for ( var i=divCnt; i>0; i-- ) {
		if ( i == divCnt ) {
			$("[name=addTargetBtn]").eq(cnt).show();
		} else {
			$("[name=addTargetBtn]").eq(cnt).hide();
		}
		
		
		if ( i == divCnt ) {
			$("[name=removeTargetBtn]").eq(cnt).hide();
		} else {
			$("[name=removeTargetBtn]").eq(cnt).show();
		}
		
		cnt--;
	}
	
	/* if($("#searchArticle").find("div").length > 2)
		$("#searchArticle").find("div:last").find("[name=removeTargetBtn]").show();
	else if($("#searchArticle").find("div").length  == 2)
		$("#searchArticle").find("div:last").find("[name=removeTargetBtn]").hide();
	$("#searchArticle").find("div:last").find("[name=addTargetBtn]").show(); */
}

/**
 * 품목군 조회 팝업 호출
 */
function searchDisplayArticle(form, num) {
	
	/* var param = null;
	var displaySearchPopup = new displayPopup();
	displaySearchPopup.open("displayArticleSearchPopup",
							"/display/display.article.search.popup.lecs",
							param, 
							{
								isMultiSelect:true,
								width:1000,
								height:550, 
								callbackF:function(data) {	
									
									document.getElementById('articleCode'+num).value = data[0].articleCode;
									document.getElementById('articleName'+num).value = data[0].articleName;
							} 
	});
	 */
	
	// 품목군 조회 팝업 폼 생성
	var $form = fnAddForm('formArticle');
	$form.empty();
	
	fnAddInput($form, "num", "num", num);
	
	fnArticlePop($form);
}

/**
 * 품목군 조회 팝업에서 선택된 값 callback
 */
function selArticleCallback(articleInfo) {
	var num = $("#formArticle #num").val();
	
	var artcCd = articleInfo.artcCd;
	var artcNm = articleInfo.artcNm;
	
	$("#articleCode"+num).val(artcCd);
	$("#articleName"+num).val(artcNm);
}
//-->
</script>