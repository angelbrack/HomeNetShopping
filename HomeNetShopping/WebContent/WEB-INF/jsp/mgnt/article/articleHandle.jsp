<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="form1" id="form1" method="post" action="">
				<input type="hidden" id="cmd"				name="cmd"					value="I"								/> <!-- 등록/수정 구분 		-->
				<input type="hidden" id="treeId"			name="treeId"				value=""								/> <!-- tree Id	 		-->
			<!--contents-->
			<div class="con_top">
				<div class="tit">
					<c:out value="${requestScope.USER_PGM_AUTH.optrPgmNm}" />
				</div>
				<div class="navi">
					<img src="<ctag:conf key="THEME.PATH" />mgnt/images/ic_01.jpg" alt="" />
					<span>&nbsp;>&nbsp;</span>
					품목군 관리
					<span>&nbsp;>&nbsp;</span>
					품목군 관리
				</div>	
			</div>
			
			<div class="con_btn mt10 mb10">
				<a href="#none" onclick="articleTreeList.fnAdd(); return false;" class="btn_write1" >신규</a>
				<a href="#none" onclick="articleTreeList.fnSave(); return false;" class="btn_apply1" >저장</a>
			</div>
			
			<div>
				<table>
					<caption>
						품목군정보에 대한 관리
					</caption>
					<colgroup>
						<col width="20%" />
						<col width="5%" />
						<col width="*" />						
					</colgroup>					
					<tbody>		
						<tr>
							<td>
								<div id="treeArea" style="width: 250px; height: 650px; background-color: #f5f5f5; border: 1px solid Silver;" >
									<div id="treeboxbox_tree" class="tree clear" setImagePath="/html/script/css/images/dhtmlxtree/" xclass="dhtmlxTree" style="display:none;width: 250px; height: 650px; overflow-x:auto;overflow-y:auto;">
										<ul>
											<li id="treeRoot">품목
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
												<th scope="row"><label for="artcCd">품목코드</label><span class="fwb2 fcop4">*</span></th>
					                            <td>
					                                <input type="text" id="artcCd" name="artcCd" value="" style="width:90%;height:18px;" class="txtbox1" />
					                            </td>
					                            <th scope="row"><label for="artcNm">품목명</label><span class="fwb2 fcop4">*</span></th>
					                            <td>
					                                <input type="text" id="artcNm" name="artcNm" value="" style="width:90%;height:18px;" class="txtbox1" />
					                            </td>
											</tr>
											<tr>
												<th scope="row"><label for="artcFullNm">품목FULL명</label> <span class="fwb2 fcop4">*</span></th>
												<td colspan="3">
					                                <input type="text" id="artcFullNm" name="artcFullNm" value="" style="width:96%;height:18px;" class="txtbox1" />
					                            </td>
											</tr>
											<tr>
												<th scope="row"><label for="uprArtcNm">상위 품목</label></th>
					                            <td>
					                                <input type="text" id="uprArtcNm" name="ecArtcNm" value="" style="width:90%;height:18px;" class="txtbox1" disabled/>
					                                <input type="hidden" id="uprArtcCd" name="uprArtcCd" value="" />
					                            </td>
					                            <th scope="row"><label for="ecArtcNm">전자상거래품목</label><span class="fwb2 fcop4">*</span></th>
					                            <td>
					                                <input type="text" id="ecArtcNm" name=""ecArtcNm"" value="" style="width:50%;height:18px;" class="txtbox1" />
					                                <input type="hidden" id="ecArtcCd" name="ecArtcCd" value="" />
					                                <a href="#none" onclick="articleTreeList.fnSearchEcArtc();" class="btn_search3 ml2 mr2" id="btnSearchEcArtc">검색</a>
													<a href="#none" onclick="articleTreeList.fnInitEcArtc();" class="btn_search3 ml2 mr2" id="btnInitEcArtc">초기화</a>
					                            </td>
											</tr>
											<tr>
												<th scope="row"><label for="artcDpthNo">품목깊이번호</label><span class="fwb2 fcop4">*</span></th>
					                            <td>
					                                <input type="text" id="artcDpthNo" name="artcDpthNo" value="1" style="width:90%;height:18px;" class="txtbox1" disabled/>
					                            </td>
					                            <th scope="row"><label for="onlBrchInvRt">재고율</label></th>
					                            <td>
					                                <input type="text" id="onlBrchInvRt" name="onlBrchInvRt" value="" style="width:90%;height:18px;" class="txtbox1"/>
					                            </td>
											</tr>
											<tr>
												<th scope="row"><label for="maxLmtQty">최대구매수량</label></th>
					                            <td>
					                                <input type="text" id="maxLmtQty" name="maxLmtQty" value="" style="width:90%;height:18px;" class="txtbox1"/>
					                            </td>
					                            <th scope="row"><label for="goodsMrgnRt">상품마진율</label></th>
					                            <td>
					                                <input type="text" id="goodsMrgnRt" name="goodsMrgnRt" value="" style="width:90%;height:18px;" class="txtbox1"/>
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