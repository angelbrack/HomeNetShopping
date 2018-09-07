<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/pgm/sysPgmInclude.jsp" %>
<script type="text/javascript">
<!--
	$(document).ready(function () {
		
		<c:if test="${info.menuUseYn == 'Y'}" >
			$("#menuUseY").prop("checked",true);
		</c:if>
		
		<c:if test="${info.menuUseYn == 'N'}" >
			$("#menuUseN").prop("checked",true);
		</c:if>	
	
	});

//-->
</script>
<body>

<div id="wrap">
<form:form name="form1" method="post" action="" commandName="searchVO" >
	<form:hidden path="currentPage" name="currentPage" value="${searchVO.currentPage}" />
	<form:hidden path="optrPgmNo" id="optrPgmNo" name="optrPgmNo" value="${info.optrPgmNo}" />
	<form:hidden path="searchOptrPgmGrpNo" id="searchOptrPgmGrpNo" name="searchOptrPgmGrpNo" value="${searchVO.searchOptrPgmGrpNo}" />
	<form:hidden path="searchKey" id="searchKey" name="searchKey" value="${searchVO.searchKey}"/>
	<form:hidden path="searchWord" id="searchWord" name="searchWord" value="${searchVO.searchWord}"/>
	<form:hidden path="recordCountPerPage" id="recordCountPerPage" name="recordCountPerPage" value="${searchVO.recordCountPerPage}"/>
	<form:hidden path="pageIndex" name="pageIndex" id="pageIndex" value="${ searchVO.pageIndex }" />
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
						<spring:message code="TITLE.SYSTEM.PGM" />
				</div>
			</div>

			<div class="con_write mt24">
				<table summary="<spring:message code='SUMMARY.SYSTEM.PGM.HANDLE.TABLE' />">
				<caption>
					<spring:message code="TITLE.SYSTEM.PGM.EDIT" />
				</caption>
				<colgroup>
				<col width="20%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="optrPgmNm"><spring:message code="LBL.OPTR.PGM.NM" /></label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
							<spring:message var="optrPgmNm" code="LBL.OPTR.PGM.NM" />
							<form:input path="optrPgmNm" name="optrPgmNm" id="optrPgmNm" value="${info.optrPgmNm}" maxlength="100" title="${optrPgmNm}" style="width:400px;height:18px;" class="txtbox1" />
							<form:errors path="optrPgmNm" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="optrPgmId"><spring:message code="LBL.OPTR.PGM.ID" /></label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
							<spring:message var="optrPgmId" code="LBL.OPTR.PGM.ID" />
							<form:input path="optrPgmId" name="optrPgmId" id="optrPgmId" value="${info.optrPgmId}" maxlength="100" title="${optrPgmId}" style="ime-mode:disabled;width:400px;height:18px;" class="txtbox1" />
							<form:errors path="optrPgmId" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="optrPgmUrlV"><spring:message code="LBL.OPTR.PGM.URL" /></label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
							<spring:message var="optrPgmUrlV" code="LBL.OPTR.PGM.URL" />
							<form:input path="optrPgmUrlV" name="optrPgmUrlV" id="optrPgmUrlV" value="${info.optrPgmUrlV}" maxlength="200" title="${optrPgmUrlV}" style="ime-mode:disabled;width:400px;height:18px;" class="txtbox1" />
							<form:errors path="optrPgmUrlV" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="optrPgmGrpNo"><spring:message code="LBL.OPTR.PGM.GRP.NO" /></label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
							<spring:message var="optrPgmGrpNo" code="LBL.OPTR.PGM.GRP.NO" />
							<form:input path="optrPgmGrpNo" name="optrPgmGrpNo" id="optrPgmGrpNo" value="${info.optrPgmGrpNo}" maxlength="6" title="${optrPgmGrpNo}" style="ime-mode:disabled;width:100px;height:18px;" onkeypress="return onPress(event,'numbers');" class="txtbox1" />
							<form:errors path="optrPgmGrpNo" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="menuUseYn"><spring:message code="LBL.MENU.USE.YN" /></label> <span class="fwb2 fcop4">*</span></th>
						<td colspan="3">
							<spring:message var="menuUseYn" code="LBL.MENU.USE.YN" />
							<form:radiobutton path="menuUseYn" name="menuUseYn" id="menuUseY" value="Y" title="${menuUseYn}" checked="checked" /> <label for="menuUseY">Y</label>
							<form:radiobutton path="menuUseYn" name="menuUseYn" id="menuUseN" value="N" title="${menuUseYn}" /> <label for="menuUseN">N</label>
							<form:errors path="menuUseYn" cssClass="errorMsg" />
						</td>
					</tr>
				</tbody>
				</table>
			</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="onSave();" class="btn_write1"><spring:message code="BTN.SAVE" /></a>
			<c:if test="${info.optrPgmNo > 0}">	
				<a href="#none" onclick="onDeleteEdit();" class="btn_delete1"><spring:message code="BTN.DELETE" /></a>
			</c:if>	
				<a href="#none" onclick="onSearch();" class="btn_list1"><spring:message code="BTN.LIST" /></a>
			</div>
			<!--내용-->

		</div>
	</div>
</form:form>
</div>

</body>
</html>

<%@ include file="/WEB-INF/jsp/common/incFile.jspf" %>
