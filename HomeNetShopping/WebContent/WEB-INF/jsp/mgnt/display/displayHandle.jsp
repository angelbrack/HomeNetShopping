<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="form1" id="form1" method="post" action="">
				<input type="hidden" id="cmd"					name="cmd"					value=""														/> <!-- 등록/수정 구분 		-->
				<input type="hidden" id="dispNo" 				name="dispNo" 				value="" 														/> <!-- 전시번호	 		-->
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
                            <td class="tit"><label for="dpmlNo">전시몰</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="searchDpmlNo" id="searchDpmlNo">
                                    <c:forEach var="mall" items="${mallList}">
										<option value="${mall.dpmlNo}">${mall.dpmlNm}</option>
									</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="shopTpCd">매장유형</label></td>
                            <td colspan="2">
                                <ctag:code name="searchShopTpCd" type="S" key="SHOP_TP_CD" selected=""  css="form" />
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="dispYn">전시여부</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="searchDispYn" id="searchDispYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">전시</option>
                                	<option value="N">전시안함</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="menuUseYn">메뉴사용여부</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="searchMenuUseYn" id="searchMenuUseYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">메뉴사용</option>
                                	<option value="N">메뉴사용안함</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="useYn">사용여부</label></td>
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
								<div id="treeArea" style="width: 250px; height: 850px; background-color: #f5f5f5; border: 1px solid Silver;" >
									<div id="treeboxbox_tree" class="tree clear" setImagePath="/html/script/css/images/dhtmlxtree/" xclass="dhtmlxTree" style="display:none;width: 250px; height: 650px; overflow-x:auto;overflow-y:auto;">
										<ul>
											<li id="treeRoot">Root
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
								<div class="con_write" style="height: 650px;">
									<table>
										<caption>품목군</caption>
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
												<th scope="row"><label for="dispTitExpMethCd">매장 타이틀 노출유형</label> <span class="fwb2 fcop4">*</span></th>
												<td colspan="3">
													<c:set var="dispTitExpMethCd" value="" />
													<c:if test="${empty info }">
														<c:set var="dispTitExpMethCd" value="01" />
													</c:if>
													<ctag:code name="dispTitExpMethCd" type="R" key="DISP_TIT_EXP_METH_CD" selected="${dispTitExpMethCd }"  css="form" />
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="gnbExpMethCd">매장 메뉴 노출유형 </label> <span class="fwb2 fcop4">*</span></th>
												<td colspan="3">
													<c:set var="gnbExpMethCd" value="" />
													<c:if test="${empty info }">
														<c:set var="gnbExpMethCd" value="01" />
													</c:if>
													<ctag:code name="gnbExpMethCd" type="R" key="GNB_EXP_METH_CD" selected="${gnbExpMethCd }"  css="form" />
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="apndFile0">매장 타이틀 이미지(titleImage)</label> </th>
												<td  class="pt10 pb10" colspan="3">
													<div style="width:700px;padding:0px;" id="apndFile0">							 	
													</div>
													<div class="imgs_wrap" id="apndImg0">
													</div>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="apndFile1">매장 메뉴 이미지(gnbImage)</label> </th>
												<td  class="pt10 pb10" colspan="3">
													<div style="width:700px;padding:0px;" id="apndFile1">							 	
													</div>
													<div class="imgs_wrap" id="apndImg1">
													</div>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="recuDtlCn">매장 설명 내용</label> </th>
												<td colspan="3" class="pt10 pb10">
													<textarea id="shopDescCont" name="shopDescCont" class="txtbox1" style="width:98%; height:100px;"><c:out value="${info.shopDescCont }" escapeXml="false" /></textarea>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="dpthNo">카테고리 깊이 </label> </th>
												<td>
													<c:out value="${info.dpthNo }" />
												</td>
												<th scope="row"><label for="tlwtLfYn_Y">Leaf 카테고리 여부 </label> <span class="fwb2 fcop4">*</span></th>
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
												<th scope="row"><label for="dispYn_Y">전시여부 </label> <span class="fwb2 fcop4">*</span></th>
												<td>
													<input type="radio" id="dispYn_Y" name="dispYn" value="Y" <c:if test='${info.dispYn eq "Y" }'>checked="checked"</c:if> class="form" title="" style=""><label for="dispYn_Y" class="">&nbsp;전시</label>
													<input type="radio" id="dispYn_N" name="dispYn" value="N" <c:if test='${empty info or info.dispYn eq "N" }'>checked="checked"</c:if> class="form" title="" style=""><label for="dispYn_N" class="">&nbsp;전시안함</label>
												</td>
											</tr>
											<tr>
												
												<th scope="row"><label for="useYn_Y">사용여부 </label> <span class="fwb2 fcop4">*</span></th>
												<td>
													<input type="radio" id="useYn_Y" name="useYn" value="Y" <c:if test='${info.useYn eq "Y" }'>checked="checked"</c:if> class="form" title="" style=""><label for="useYn_Y" class="">&nbsp;사용</label>
													<input type="radio" id="useYn_N" name="useYn" value="N" <c:if test='${empty info or info.useYn eq "N" }'>checked="checked"</c:if> class="form" title="" style=""><label for="useYn_N" class="">&nbsp;사용안함</label>
												</td>
												<th scope="row"><label for="menuUseYn_Y">메뉴사용 여부 </label> <span class="fwb2 fcop4">*</span></th>
												<td>
													<input type="radio" id="menuUseYn_Y" name="menuUseYn" value="Y" <c:if test='${info.menuUseYn eq "Y" }'>checked="checked"</c:if> class="form" title="" style=""><label for="menuUseYn_Y" class="">&nbsp;사용</label>
													<input type="radio" id="menuUseYn_N" name="menuUseYn" value="N" <c:if test='${empty info or info.menuUseYn eq "N" }'>checked="checked"</c:if> class="form" title="" style=""><label for="menuUseYn_N" class="">&nbsp;사용안함</label>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="prtTpCd">출력유형</label> </th>
												<td>
													<ctag:code name="prtTpCd" type="S" key="PRT_TP_CD" selected="${info.prtTpCd }"  optdef="- 선택 -" css="form" />
												</td>
												<th scope="row"><label for="prtTpCd">전시코너수</label> </th>
												<td>
													<a href="javascript:goCornerList('shop');">1개</a>
												</td>
											</tr>
											<c:choose>
												<c:when test="${info.prtTpCd == '06'}">
													<c:set var="style10" value="display: "/>
													<c:set var="style20" value="display: none;"/>
													<c:set var="style30" value="display: none;"/>
													<c:set var="style40" value="display: none;"/>
													<c:set var="style50" value="display: none;"/>	
												</c:when>
												<c:when test="${info.prtTpCd == '01'}">			
													<c:set var="style10" value="display: none;"/>
													<c:set var="style20" value="display: "/>
													<c:set var="style30" value="display: none;"/>
													<c:set var="style40" value="display: none;"/>
													<c:set var="style50" value="display: none;"/>			
												</c:when>
												<c:when test="${info.prtTpCd == '02'}">			
													<c:set var="style10" value="display: none;"/>
													<c:set var="style20" value="display: none;"/>
													<c:set var="style30" value="display: "/>
													<c:set var="style40" value="display: none;"/>
													<c:set var="style50" value="display: none;"/>
												</c:when>
												<c:when test="${info.prtTpCd == '04'}">			
													<c:set var="style10" value="display: none;"/>
													<c:set var="style20" value="display: none;"/>
													<c:set var="style30" value="display: none;"/>
													<c:set var="style40" value="display: "/>
													<c:set var="style50" value="display: none;"/>
												</c:when>	
												<c:otherwise>
													<c:set var="style10" value="display: none;"/>
													<c:set var="style20" value="display: none;"/>
													<c:set var="style30" value="display: none;"/>
													<c:set var="style40" value="display: none;"/>
													<c:set var="style50" value="display: "/>
												</c:otherwise>
											</c:choose>
											<tr id="printType06" style="${style10}" name="printType"></tr>
											<tr id="printType01" style="${style20}" name="printType">
												<th scope="row"><label for="tmplNm">전시 템플릿</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="hidden" id="tmplNo" name="tmplNo" value="<c:out value="${info.tmplNo }" />" />
													<input type="text" name="tmplNm" id="tmplNm" value="" class="txtbox1"  style="width:200px;height:18px;" readOnly />
													<a href="#none" onclick="searchDisplayTemplate('displayShopForm');return false;" class="btn_search3 ml5 mr20" id="btnSearchDisplayTemplate">검색</a>
												</td>
											</tr>
											<tr id="printType02" style="${style30}" name="printType"> 
												<th scope="row"><label for="lnkUrlAddr">대상 URL</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="text" name="lnkUrlAddr" id="lnkUrlAddr" value="<c:out value="${info.lnkUrlAddr }" />" class="txtbox1"  style="width:500px;height:18px;" />
												</td>
											</tr>
											<tr id="printType04" style="${style40}" name="printType">
												<th scope="row"><label for="lnkSpdpHhNo40">카테고리매장번호</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="text" id="lnkSpdpHhNo40" value="" class="txtbox1"  style="width:200px;height:18px;" readOnly />
													<a href="#none" onclick="searchDisplayShop('displayShopForm');return false;" class="btn_search3 ml5 mr20" id="btnSearchDisplayShop">검색</a>
												</td>
											</tr>
											<tr id="printType05" style="${style50}" name="printType">
												<th scope="row"><label for="lnkSpdpHhNo50">기획전매장번호</label> <span class="fwb2 fcop4">*</span> </th>
												<td colspan="3">
													<input type="text" id="lnkSpdpHhNo50" value="" class="txtbox1"  style="width:200px;height:18px;" readOnly />
													<a href="#none" onclick="searchSpecialDisplay('displayShopForm');return false;" class="btn_search3 ml5 mr20" id="btnSearchDisplayShop">검색</a>
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="lstSortCd">리스트소팅값</label> </th>
												<td colspan="3">
													<ctag:code name="lstSortCd" type="S" key="LST_SORT_CD" selected="${info.lstSortCd }"  optdef="- 선택 -" css="form" />
												</td>
											</tr>
											<tr>
												<th scope="row"><label for="apndFile2">매장 부가 이미지(headerImage)</label> </th>
												<td  class="pt10 pb10" colspan="3">
													<div style="width:700px;padding:0px;" id="apndFile2">							 	
													</div>
													<div class="imgs_wrap" id="apndImg2">
													</div>
												</td>
											</tr>
											<!--  품목군 매핑 영역 추가 -->
											<tr id="searchArticle">
												<th scope="row"><label for="lstSortCd">품목군</label> </th>
												<td colspan="3">
											<c:choose>
												<c:when test="${not empty info.tlwtLfYn && info.tlwtLfYn == 'Y' && not empty articleList}">
												 	<c:forEach var="article" items="${articleList}" varStatus="idx">
												 	
												 	</c:forEach>
												</c:when>
												<c:otherwise>
													<div id="target1">						
														<input type="text" id="articleCode1" name="articleCode1" value="" class="txtbox1"  style="width:80px;height:18px;" readOnly />
														<input type="text" id="articleName1" name="articleName1" value="" class="txtbox1"  style="width:250px;height:18px;" readOnly />
														<span class="hahahoho">
															<button type="button" class="lt_cnt2" name="popupBtnArticle1" id="popupBtnArticle1"  onClick="searchDisplayArticle('displayShopForm',1);return false;">검색</button>
									 						<a href="javascript:addNoArticleType(1);" name="addTargetBtn" class="btn_plus1">추가</a>
															<a href="javascript:removeNoArticleType(1);" name="removeTargetBtn" style="display: none;" class="btn_minus1">삭제</a>
														</span>
													 </div>
												</c:otherwise>
											</c:choose> 
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
<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/mgnt/display/display.js?20180918000000"></script>
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
		, idx 		: "0"				
		, fileCo 	: 1
		, imgYn		: "Y"
	});
	fnFileEdit("0", "<c:out value='${(fileList)}'/>", "", "Y");
	
	$("#apndFile1").fnFileUpload({
		  path 		: "DISPLAY.IMG"		
		, idx 		: "1"				
		, fileCo 	: 1
		, imgYn		: "Y"
	});
	fnFileEdit("1", "<c:out value='${(fileList)}'/>", "", "Y");
	
	$("#apndFile2").fnFileUpload({
		  path 		: "DISPLAY.IMG"		
		, idx 		: "2"				
		, fileCo 	: 1
		, imgYn		: "Y"
	});
	fnFileEdit("2", "<c:out value='${(fileList)}'/>", "", "Y");
});
//-->
</script>