<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/commCd/sysCommCdInclude.jsp" %>
<script type="text/javascript">
<!--
	$(document).ready(function () {
		
	<c:if test="${info.useYn == 'Y'}" >
		$("#useYn1").prop("checked",true);
	</c:if>
	
	<c:if test="${info.useYn == 'N'}" >
		$("#useYn2").prop("checked",true);
	</c:if>	
	
	});

//-->
</script>
<body>
<div id="wrap">
<form:form name="form1" method="post" action="" commandName="searchVO" >
	<form:hidden path="currentPage" name="currentPage" value="${searchVO.currentPage}" />
	<form:hidden path="searchGrpCdId" name="searchGrpCdId" value="${searchVO.searchGrpCdId}" />
	<form:hidden path="searchWord" name="searchWord" value="${searchVO.searchWord}" />	
	<form:hidden path="recordCountPerPage" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />	
	<form:hidden path="cmd" name="cmd" value="${info.cmd}" />
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
						<spring:message code="TITLE.SYSTEM.COMMCD" />
				</div>
			</div>

			<div class="con_write mt24">
				<table summary="<spring:message code='SUMMARY.SYSTEM.COMMCD.HANDLE.TABLE' />">
				<caption>
					<spring:message code="TITLE.SYSTEM.COMMCD.EDIT" />
				</caption>
				<colgroup>
				<col width="20%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="grpCdId"><spring:message code="LBL.CODE.DIV" /> </label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
						<c:if test="${ info.cmd == null || info.cmd == 'I' }">	
							<spring:message var="grpCdId" code="LBL.CODE.DIV" />
							<form:input path="grpCdId" id="grpCdId" name="grpCdId" maxlength="50" value="${info.grpCdId}" title="${grpCdId}" style="ime-mode:disabled;width:200px;height:18px;" class="txtbox1" /><form:errors path="grpCdId" cssClass="errorMsg" />
						</c:if>
						<c:if test="${ info.cmd == 'U' }">	
							<c:out value="${info.grpCdId}"/>
							<form:hidden path="grpCdId" name="grpCdId" value="${info.grpCdId}" />
						</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="cdId"><spring:message code="LBL.CODE" /> </label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
						<c:if test="${ info.cmd == null || info.cmd == 'I' }">	
							<spring:message var="cdId" code="LBL.CODE" />
							<form:input path="cdId" id="cdId" name="cdId" maxlength="3" value="${info.cdId}" title="${cdId}" style="ime-mode:disabled;width:100px;height:18px;" class="txtbox1" /><form:errors path="cdId" cssClass="errorMsg" />
						</c:if>
						<c:if test="${ info.cmd == 'U' }">	
							<c:out value="${info.cdId}"/>	
							<form:hidden path="cdId" name="cdId" value="${info.cdId}" />
						</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="cdNm"><spring:message code="LBL.CODE.NAME" /> </label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
							<spring:message var="cdId" code="LBL.CODE.NAME" />
							<form:input path="cdNm" id="cdNm" name="cdNm" title="${cdId}" maxlength="100" value="${info.cdNm}" style="width:200px;height:18px;" class="txtbox1" />
							<form:errors path="cdNm" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="cdCn"><spring:message code="LBL.CODE.DESCRIPTION" /></label></th>
						<td colspan="3">
							<spring:message var="cdCn" code="LBL.CODE.DESCRIPTION" />
							<form:input path="cdCn" id="cdCn" name="cdCn" title="${cdCn}" maxlength="1000" value="${info.cdCn}" style="width:600px;height:18px;" class="txtbox1" />
							<form:errors path="cdCn" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="useYn1"><spring:message code="LBL.USE.YN" /></label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
							<spring:message var="useYn" code="LBL.USE.YN" />
							<form:radiobutton path="useYn" id="useYn1" name="useYn" title="${useYn}" value="Y" checked="checked" /> <label for="useYn1">Y</label>
							<form:radiobutton path="useYn" id="useYn2" name="useYn" title="${useYn}" value="N" /> <label for="useYn2">N</label>
							<form:errors path="useYn" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="sortOr"><spring:message code="LBL.SORT.ORDER" /></label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
						<spring:message var="sortOr" code="LBL.SORT.ORDER" />
						<form:input path="sortOr" id="sortOr" name="sortOr" maxlength="5" value="${info.sortOr}" title="${sortOr}" style="ime-mode:disabled;width:50px;height:18px;" onkeypress="return onPress(event,'numbers');" class="txtbox1" />
						<form:errors path="sortOr" cssClass="errorMsg" />
						</td>
					</tr>
					
					<tr>
						<th scope="row"><label for="refeV1"><spring:message code="LBL.REFERENCE.2" /> </label></th>
						<td colspan="3">
							<spring:message var="refeV1" code="LBL.REFERENCE.2" />
							<form:input path="refeV1" id="refeV1" name="refeV1" title="${refeV1}" maxlength="20" value="${info.refeV1}" style="width:200px;height:18px;" class="txtbox1" />
							<form:errors path="refeV1" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="refeV2"><spring:message code="LBL.REFERENCE.1" /> </label></th>
						<td colspan="3">
							<spring:message var="refeV2" code="LBL.REFERENCE.1" />
							<form:input path="refeV2" id="refeV2" name="refeV2" title="${refeV2}" maxlength="20" value="${info.refeV2}" style="width:200px;height:18px;" class="txtbox1" />
							<form:errors path="refeV2" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="refeV3"><spring:message code="LBL.REFERENCE.3" /> </label></th>
						<td colspan="3">
							<spring:message var="refeV3" code="LBL.REFERENCE.3" />
							<form:input path="refeV3" id="refeV3" name="refeV3" title="${refeV3}" maxlength="20" value="${info.refeV3}" style="width:200px;height:18px;" class="txtbox1" />
							<form:errors path="refeV3" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="refeV4"><spring:message code="LBL.REFERENCE.4" /> </label></th>
						<td colspan="3">
							<spring:message var="refeV4" code="LBL.REFERENCE.4" />
							<form:input path="refeV4" id="refeV4" name="refeV4" title="${refeV4}" maxlength="20" value="${info.refeV4}" style="width:200px;height:18px;" class="txtbox1" />
							<form:errors path="refeV4" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="refeV5"><spring:message code="LBL.REFERENCE.5" /> </label></th>
						<td colspan="3">
							<spring:message var="refeV5" code="LBL.REFERENCE.5" />
							<form:input path="refeV5" id="refeV5" name="refeV5" title="${refeV5}" maxlength="20" value="${info.refeV5}" style="width:200px;height:18px;" class="txtbox1" />
							<form:errors path="refeV5" cssClass="errorMsg" />
						</td>
					</tr>
				</tbody>
				</table>
			</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="javascript:onSave();" class="btn_write1"><spring:message code="BTN.SAVE" /></a>
			<c:if test="${info.cmd == 'U'}">
				<a href="#none" onclick="javascript:onDelete();" class="btn_delete1"><spring:message code="BTN.DELETE" /></a>
			</c:if>	
				<a href="#none" onclick="javascript:onListPage2();" class="btn_list1"><spring:message code="BTN.LIST" /></a>
			</div>

		</div>
	</div>

</form:form>
</div>
</body>
</html>
