<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/jquery/plugin/jquery.form.js"></script>
<%@ include file="/WEB-INF/jsp/mgnt/system/userRole/sysUserRoleInclude.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		<c:if test="${info['useYn'] == 'Y'}" >
			$("#useYnY").prop("checked",true);
		</c:if>
		
		<c:if test="${info['useYn'] == 'N'}" >
			$("#useYnN").prop("checked",true);
		</c:if>	
		
	    var tree = new UtilTree("treeDiv");
	    tree.init(); 
	    var objTree = document.getElementById(_TREE_ID);
	    
	    _CHECK_BOX = "checkbox";
	    _CHECKED = "<c:out value="${info.optrMenuNos}"/>";
	    $(objTree).bind("loaded.jstree", function (e, data) { 
	        var result= _CHECKED.split(",");
	        if(result.length > 0){
	            for(i=0; i < result.length; i++){
	                data.inst.check_node("#treeItem" + result[i], true);
	            }
	        }
	    });  
	});
</script>
<body>

<div id="wrap">

<form:form name="form1" method="post" action="/mgnt/system/UserRoleListAction.do" commandName="searchVO" onsubmit="return false;" >
   <form:hidden path="optrAuthNo" name="optrAuthNo" id="optrAuthNo" value="${info['optrAuthNo']}" />
   <form:hidden path="cmd" name="cmd" value="${param.cmd}" />
   <form:hidden path="optrPgmNo" name="optrPgmNo" id="optrPgmNo" value="" />
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
					<spring:message code="TITLE.SYSTEM.USERROLE" />
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
						                            <li id="treeItem0" rel="root" class="jstree-open">
						                                <a href="#menuSelect">
						                                <spring:message code="BTN.ROOT" /> 
						                                </a>
						                                <c:set var="prevLevel" value="0" />
						                                    
						                                <c:forEach items="${menuList}" var="item" varStatus="status">
						                                    <c:if test="${ status.count == 0 }">
						                                        <c:set var="prevLevel" value="${ item.level }" />
						                                    </c:if>
						                                    
						                                    <c:if test="${ item.level > prevLevel }">
						                                        <ul id="treeOver${ status.count }">
						                                        <li value="<c:choose><c:when test="${empty item.optrPgmNo}">0</c:when><c:otherwise><c:out value="${item.optrPgmNo}" /></c:otherwise></c:choose>" id="treeItem${ item.menuNo }" class="jstree-open">
						                                            <a href="#menuSelect" onclick="doSelectMenu('<c:out value="${item.menuNo}" />');"><c:out value="${ item.menuNm }"/></a>
						                                    </c:if>
						                                    
						                                    <c:if test="${ item.level == prevLevel }">
						                                        </li>
						                                        <li value="<c:choose><c:when test="${empty item.optrPgmNo}">0</c:when><c:otherwise><c:out value="${item.optrPgmNo}" /></c:otherwise></c:choose>" id="treeItem${ item.menuNo }" class="jstree-open"><a href="#menuSelect" onclick="doSelectMenu('<c:out value="${item.menuNo}" />'); return false;"><c:out value="${ item.menuNm }"/></a>
						                                    </c:if>
						                                    
						                                    <c:if test="${ item.level < prevLevel }">
						                                        <c:forEach begin="${item.level}" end="${prevLevel}">
						                                            </li>
						                                            </ul>
						                                        </c:forEach>
						                                        
						                                        <ul id="treeOver${ status.count }">
						                                        <li value="<c:choose><c:when test="${empty item.optrPgmNo}">0</c:when><c:otherwise><c:out value="${item.optrPgmNo}" /></c:otherwise></c:choose>" id="treeItem${ item.menuNo }" class="jstree-open"><a href="#menuSelect" onclick="doSelectMenu('<c:out value="${item.menuNo}" />'); return false;"><c:out value="${ item.menuNm }"/></a>
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
								<table summary="<spring:message code='SUMMARY.SYSTEM.USERROLE.HANDLE.TABLE' />">
								<caption>
									<spring:message code="TITLE.SYSTEM.USERROLE" />
								</caption>
								<colgroup>
									<col width="25%" />
			                		<col width="*" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label for="optrAuthNm"><spring:message code="LBL.AUTH.ROLE.NM" /></label> <span class="fwb2 fcop4">*</span></th>
										<td>
											<spring:message var="optrAuthNm" code="LBL.AUTH.ROLE.NM" />
											<form:input path="optrAuthNm" name="optrAuthNm" id="optrAuthNm" value="${info['optrAuthNm']}" title="${optrAuthNm}" style="width:200px;height:18px;" class="txtbox1" />
											<form:errors path="optrAuthNm" cssClass="errorMsg" />
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="optrAuthCd"><spring:message code="LBL.USER.AUTH.CD" /></label> <span class="fwb2 fcop4">*</span></th>
										<td>
						                    <ctag:code name="optrAuthCd" type="S" key="USER_AUTH_CD" selected="${info['optrAuthCd']}" optdef="- 선택 -" css="form" />
						                    <form:errors path="optrAuthCd" cssClass="errorMsg" />
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="useYnY"><spring:message code="LBL.USE.YN" /></label> <span class="fwb2 fcop4">*</span></th>
										<td>
											<spring:message var="useYn" code="LBL.USE.YN" />
											<form:radiobutton path="useYn" name="useYn" id="useYnY" value="Y" title="${useYn}" checked="checked" /> <label for="useYnY"><spring:message code="LBL.USE.Y" /></label>
											<form:radiobutton path="useYn" name="useYn" id="useYnN" value="N" title="${useYn}"  /> <label for="useYnN"><spring:message code="LBL.USE.N" /></label>
											<form:errors path="useYn" cssClass="errorMsg" />
										</td>
									</tr>
								</tbody>
								</table>
							</div>

							<div class="con_btn mt30">
								<a href="#none" onclick="onSave();" class="btn_write1"><spring:message code="BTN.SAVE" /></a>
								<a href="#none" onclick="onList();" class="btn_list1"><spring:message code="BTN.LIST" /></a>
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

</body>
</html>
