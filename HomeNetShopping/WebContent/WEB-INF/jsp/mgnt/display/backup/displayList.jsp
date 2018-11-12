<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="formInit" id="formInit" method="post" action="">
				<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/>
				
				<input type="hidden" id="dispNo" 				name="dispNo" 				value="" 														/>
			</form>
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
                                <select style="width:120px;height:22px;" name="dpmlNo" id="dpmlNo">
                                    <c:forEach var="mall" items="${mallList}">
										<option value="${mall.dpmlNo}">${mall.dpmlNm}</option>
									</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="shopTpCd">매장유형</label></td>
                            <td colspan="2">
                                <ctag:code name="shopTpCd" type="S" key="SHOP_TP_CD" selected=""  css="form" />
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="dispYn">전시여부</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="dispYn" id="dispYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">전시</option>
                                	<option value="N">전시안함</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="menuUseYn">메뉴사용여부</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="menuUseYn" id="menuUseYn">
                                	<option value="">- 선택 -</option>
                                	<option value="Y">메뉴사용</option>
                                	<option value="N">메뉴사용안함</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit"><label for="useYn">사용여부</label></td>
                            <td colspan="2">
                                <select style="width:120px;height:22px;" name="useYn" id="useYn">
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
			
			<div class="con_btn mt30">
				<a href="#none" onclick="articleList.fnEdit('I'); return false;" class="btn_write1" >신규</a>
			</div>
			
			<div class="con_list">
				<table>
					<caption>
						전시매장정보에 대한 관리
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
});
//-->
</script>