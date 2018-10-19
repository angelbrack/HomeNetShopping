<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="formInit" id="formInit" method="post" action="">
				<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/>
				
				<input type="hidden" id="artcCd" 				name="artcCd" 				value="" 														/>
			</form>
			<form name="form1" id="form1" method="post" action="">
				<input type="hidden" id="cmd"					name="cmd"					value=""														/> <!-- 등록/수정 구분 		-->
				<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/> <!-- 페이지 번호 			-->
				<input type="hidden" id="artcCd" 				name="artcCd" 				value="" 														/> <!-- 품목군번호	 		-->
			<!--contents-->
			<div class="con_top">
				<div class="tit">
					<c:out value="${requestScope.USER_PGM_AUTH.optrPgmNm}" />
				</div>
				<div class="navi">
					<img src="<ctag:conf key="THEME.PATH" />mgnt/images/ic_01.jpg" alt="" />
					<span>&nbsp;>&nbsp;</span>
					기준정보관리
					<span>&nbsp;>&nbsp;</span>
					품목군 관리
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
                            <td class="tit"><label for="searchWord">검색어</label></td>
                            <td>
                                <select style="width:120px;height:22px;" name="searchKey" id="searchKey">
                                    <option value="000" <c:if test="${empty searchVO.searchKey or searchVO.searchKey eq '000'}">selected="selected"</c:if>>품목군명</option>
                                    <option value="001" <c:if test="${searchVO.searchKey eq '001'}">selected="selected"</c:if>>품목군상세설명</option>
                                </select>
                                <input type="text" name="searchWord" id="searchWord" maxlength="100" style="width:400px;height:18px;" class="txtbox1" title="" value="${searchVO.searchWord }" class="txtbox1" onkeypress="if(event.keyCode==13) {articleList.fnSearch();}" escapeXml="false" />
                            </td>
                            <td class="ar">
                                <a href="#none" onclick="articleList.fnSearch();" class="btn_search3 ml5 mr20">검색</a>
                            </td>
                        </tr>
						<tr> 
	       					<td colspan="3" class="blank"></td>
    					</tr>
					</tbody>
				</table>
			</div>
			
			<div class="con_btn mt30">
				<a href="#none" onclick="articleList.fnEdit('I'); return false;" class="btn_write1" >신규</a>
			</div>
			
			<div class="con_list">
				<table>
					<caption>
						품목군정보에 대한 관리
					</caption>
					<colgroup>
						<col width="5%" />
						<col />
						<col width="90%" />						
					</colgroup>					
					<tbody>		
						<tr>
							<td>
								<div id="treeArea" style="width: 250px; height: 650px; background-color: #f5f5f5; border: 1px solid Silver;" >
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
<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/mgnt/article/article.js?20180918000000"></script>
<script type="text/javascript">
<!--

var tree = dhtmlXTreeFromHTML("treeboxbox_tree"); // for script conversion
tree.setOnClickHandler(articleTreeList.fnClickHandler);
tree.setOnOpenHandler(articleTreeList.fnOpenHandler);
$('#treeboxbox_tree').show();

$(document).ready(function() {
	// 화면 초기화
	articleTreeList.init();
});
//-->
</script>