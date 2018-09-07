<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/userRole/sysUserRoleInclude.jsp" %>

<body>

<div id="wrap">
<form name="form1" method="post" action="<c:url value="/back/authority/role/ListAction.do" />" onsubmit="return false;" >
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

			<div class="con_list mt24">
				<table summary="<spring:message code='SUMMARY.SYSTEM.USERROLE.LIST.TABLE' />">
				<caption>
					<spring:message code="TITLE.SYSTEM.USERROLE" />
				</caption>
				<colgroup>
					<col width="10%" />
			        <col width="15%" />
			        <col width="*" />
			        <col width="25%" />
			        <col width="15%" />
				</colgroup>
				<thead>
					<tr>
						<th><spring:message code="LBL.CHOICE" /></th>
		                <th><spring:message code="LBL.OPTR.AUTH.NO" /></th>
		                <th><spring:message code="LBL.OPTR.AUTH.NM" /></th>
		                <th><spring:message code="LBL.USER.AUTH.CD" /></th>
		                <th><spring:message code="LBL.USE.YN" /></th>
					</tr>
				</thead>
				<tbody>
				 <c:if test="${fn:length(list) == 0}">
	              <tr>
	                <td colspan="5">
	                    <spring:message code="COMMSG.NO.SEARCH.DATA" />
	                </td>
	              </tr>
	             </c:if>
	            <c:forEach items="${list}" var="info" varStatus="status">
	                <tr>
	                    <td><label for="optrAuthNo" class="hidden"><spring:message code="LBL.CHOICE" /></label><input type="radio" name="optrAuthNo" id="optrAuthNo<c:out value="${info.optrAuthNo}" />" value="<c:out value="${info.optrAuthNo}"/>"/></td>
	                    <td><c:out value="${info.optrAuthNo}" /></td>
	                    <td><a href="#none" onclick="onEdit('U','<c:out value="${info.optrAuthNo}" />')"><c:out value="${info.optrAuthNm}" /></a></td>
	                    <td><c:out value="${info['userAuthNm']}" /></td>
	                    <td><c:out value="${info['useYn']}" /></td>
	                </tr>
	            </c:forEach>
				</tbody>
				</table>
			</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="onEdit('I');" class="btn_write1"><spring:message code="BTN.NEW" /></a>
				<a href="#none" onclick="onCopy();" class="btn_write1"><spring:message code="BTN.COPY" /></a>
				<a href="#none" onclick="onDelete();" class="btn_delete1"><spring:message code="BTN.DELETE" /></a>
			</div>
			<!--내용-->

		</div>
	</div>
</form>
</div>

</body>
</html>
