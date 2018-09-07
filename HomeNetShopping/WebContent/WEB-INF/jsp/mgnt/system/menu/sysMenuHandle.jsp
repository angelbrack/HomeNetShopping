<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/menu/sysMenuInclude.jsp" %>
<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
	pageContext.setAttribute("lf", "\n");
	pageContext.setAttribute("cr", "\r");
</jsp:scriptlet>
<body>


<div id="wrap">
<form:form name="form" id="form" method="post" action="" commandName="menuVO" >
<ctag:token name="saveToken" /><!-- sub 토큰 사용시 사용 -->
	<div id="con_right" class="mt29">
		<div class="con_right_c">
							
			<!--내용-->
			<div class="con_top">
				<div class="tit">
					<c:out value="${requestScope.USER_PGM_AUTH.optrPgmNm}" /><%@ include file="/WEB-INF/jsp/common/incManual.jspf" %>
				</div>
				<div class="navi">
					<img src="<ctag:conf key="THEME.PATH" />mgnt/images/ic_01.jpg" alt="<spring:message code='LBL.FIRST' />" />
					<span>&nbsp;>&nbsp;</span>
						<spring:message code="TITLE.SYSTEM" />
					<span>&nbsp;>&nbsp;</span>
					<spring:message code="TITLE.SYSTEM.MEUN" />
				</div>
			</div>

			<div class="con_layout mt24">
				<table>
				<colgroup>
				<col width="35%" />
				<col width="2%" />
				<col width="63%" />
				</colgroup>
				<tbody>
					<tr>
						<td class="vat">
							<!--왼쪽-->
							<div class="con_view2">
								<table>
								<colgroup>
									<col width="*" />
								</colgroup>
								<tbody>
									<tr>
										<td>
											<div id="treeDiv">
												<ul id="treeRoot0">
													<li id="treeItem0" rel="root"  class="jstree-open">
														<a href="#menuSelect" onclick="doSelectMenu('0', '0', 'ROOT', '', '', '', '','0'); return false;">
														<spring:message code="BTN.ROOT" />
														</a>
												
														<c:set var="prevLevel" value="0" />
															
														<c:forEach items="${list}" var="item" varStatus="status">
															<c:if test="${ status.count == 0 }">
																<c:set var="prevLevel" value="${ item.level }" />
															</c:if>
															
															<c:if test="${ item.level > prevLevel }">
																<ul id="treeOver${ status.count }">
																<li id="treeItem${ item.menuNo }" class="jstree-open">
																	<a href="#menuSelect" onclick="doSelectMenu('<c:out value='${ item.level }'/>', '<c:out value='${ item.menuNo }'/>', '<c:out value='${ item.menuNm }'/>', '<c:out value='${ item.useYn }'/>', '<c:out value='${ item.sortOr }'/>', '<c:out value='${ item.optrPgmNo }'/>', '<c:out value='${ item.optrPgmNm }'/>'); return false;"><c:out value="${ item.menuNm }"/></a>
															</c:if>
															
															<c:if test="${ item.level == prevLevel }">
																</li>
																<li id="treeItem${ item.menuNo }" class="jstree-open"><a href="#menuSelect" onclick="doSelectMenu('<c:out value='${ item.level }'/>', '<c:out value='${ item.menuNo }'/>', '<c:out value='${ item.menuNm }'/>', '<c:out value='${ item.useYn }'/>', '<c:out value='${ item.sortOr }'/>', '<c:out value='${ item.optrPgmNo }'/>', '<c:out value='${ item.optrPgmNm }'/>'); return false;"><c:out value="${ item.menuNm }"/></a>
															</c:if>
															
															<c:if test="${ item.level < prevLevel }">
																<c:forEach begin="${item.level}" end="${prevLevel}">
																	</li>
																	</ul>
																</c:forEach>
																
																<ul id="treeOver${ status.count }">
																<li id="treeItem${ item.menuNo }" class="jstree-open"><a href="#menuSelect" onclick="doSelectMenu('<c:out value='${ item.level }'/>', '<c:out value='${ item.menuNo }'/>', '<c:out value='${ item.menuNm }'/>', '<c:out value='${ item.useYn }'/>', '<c:out value='${ item.sortOr }'/>', '<c:out value='${ item.optrPgmNo }'/>', '<c:out value='${ item.optrPgmNm }'/>'); return false;"><c:out value="${ item.menuNm }"/></a>
															</c:if>
																
															<c:set var="prevLevel" value="${ item.level }" />
																
														</c:forEach>
															
														<c:forEach begin="0" end="${ prevLevel }">
															</li></ul>
														</c:forEach>
													</li>
												</ul>
												
											</div>
										</td>
									</tr>
									</tbody>
								</table>
							</div>
						</td>
						<td class="vat ac pt140">
						</td>
						<td class="vat">
							<div class="con_write">
								<table summary="<spring:message code='SUMMARY.SYSTEM.MENU.LIST.TABLE' />">
								<caption>
									<spring:message code="TITLE.SYSTEM.MEUN" />
								</caption>
								<colgroup>
								<col width="20%" />
								<col width="*" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label for="menuNm"><spring:message code="LBL.MENU.NM" /></label> <span class="fwb2 fcop4">*</span></th>
										<td>
											<input type="text" name="menuNm" id="menuNm" value="" maxlength="100" title="<spring:message code='LBL.MENU.NM' />" disabled="disabled" style="width:200px;height:18px;" class="txtbox1" />
										</td>
									</tr>
									<tr id="optrPgmTr" style="display:none;">
										<th scope="row"><label for="optrPgmNm"><spring:message code="LBL.OPTR.PGM.NM" /></label> <span class="fwb2 fcop4">*</span></th>
										<td>
											<input type="text" id="optrPgmNm" name="optrPgmNm" value="" title="<spring:message code='LBL.OPTR.PGM.NM' />" disabled="disabled" readonly="readonly" style="width:200px;height:18px;" class="txtbox1" />
											<input type="hidden" id="optrPgmNo" name="optrPgmNo" value=""/>
											<a href="#none" onclick="fnSearchProgram(); return false;" class="btn_view1" ><span><spring:message code="BTN.SEARCH"/></span></a>
											<a href="#none" onclick="fnSettingProgram('','');return false;" class="btn_view1" ><span><spring:message code="BTN.DELETE"/></span></a>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="useYnY"><spring:message code="LBL.USE.YN" /></label> <span class="fwb2 fcop4">*</span></th>
										<td>
											<input type="radio" id="useYnY" name="useYn" value="Y" disabled="disabled" title="<spring:message code='LBL.USE.YN' />" /> <label for="useYnY"><spring:message code="LBL.USE.Y" /></label>
											<input type="radio" id="useYnN" name="useYn" value="N" disabled="disabled" title="<spring:message code='LBL.USE.YN' />" /> <label for="useYn"><spring:message code="LBL.USE.N" /></label>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="sortOr"><spring:message code="LBL.SORT.OR" /></label> <span class="fwb2 fcop4">*</span></th>
										<td>
											<input type="text" id="sortOr" name="sortOr" value="" maxlength="5" disabled="disabled" onkeypress="return onPress(event,'numbers');" title="<spring:message code='LBL.SORT.OR' />" style="ime-mode:disabled;width:200px;height:18px;" class="txtbox1" />
											<input type="hidden" id="menuNo" name="menuNo" value=""/>
										</td>
									</tr>
								</tbody>
								</table>
							</div>

							<div class="con_btn mt30">
								<a href="#none" onclick="doUpdate();" class="btn_write1"><spring:message code="BTN.SAVE" /></a>
								<a href="#none" onclick="doCancel(); return false;" class="btn_list1"><spring:message code="BTN.CANCEL" /></a>
							</div>
							<!--오른쪽-->
						</td>
					</tr>
				</tbody>
				</table>
			</div>
			<!--내용-->

		</div>
	</div>

</form:form>
</div>

<%-- Message Print Layer --%>
<div id="ajax-dialog-modal" style="display:none">
	<span id="ajax-dialog-modal-text"></span>
</div>

</body>
</html>
