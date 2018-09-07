<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/userAuth/sysUserAuthInclude.jsp" %>

<script type="text/javascript">
<!--
var oEditors = [];
$(document).ready(function() {
	$("#optrExpyDttm").datepicker();
 	$("#optrExpyDttm").inputmask("9999.99.99", {"placeholder": ""}); 
});

//-->	
</script>

<body>

<div id="wrap">

<form:form name="form1" method="post" action="" onsubmit="return false;" commandName="authVO" >
	<form:hidden path="pageIndex" name="pageIndex" value="${authSearchVO.pageIndex}"/>
	<form:hidden path="searchValue" name="searchValue" value="${authSearchVO.searchValue}" />
	<form:hidden path="searchKey" name="searchKey" value="${authSearchVO.searchKey}" />
	<form:hidden path="searchWord" name="searchWord" value="${authSearchVO.searchWord}" />
	<form:hidden path="searchOptrApprStatCd" name="searchOptrApprStatCd" value="${authSearchVO.searchOptrApprStatCd}" />
	<form:hidden path="searchOptrAuthNo" name="searchOptrAuthNo" value="${authSearchVO.searchOptrAuthNo}" />
	<form:hidden path="searchUserNo" name="searchUserNo" value="${ authSearchVO.searchUserNo }" />
	<form:hidden path="userNo" name="userNo" value="${authSearchVO.searchUserNo }" />
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
						<spring:message code="TITLE.SYSTEM.AUTH" />
				</div>
			</div>

			<div class="con_write mt24">
				<table summary="<spring:message code='SUMMARY.SYSTEM.USERAUTH.HANDLE.TABLE' />">
				<caption>
					<spring:message code="TITLE.SYSTEM.AUTH.EDIT" />
				</caption>
				<colgroup>
				<col width="20%" />
				<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><spring:message code="LBL.USER.ID" /></th>
						<td><c:out value="${userInfo.loginId}"/></td>
					</tr>
                    <tr>
                        <th scope="row">회원구분</th>
                        <td><c:out value="${userInfo.mbrNm}"/></td>
                    </tr>
					<tr>
						<th scope="row"><spring:message code="LBL.USER.NM" /></th>
						<td><c:out value="${userInfo.userNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><label for="optrAuthNo"><spring:message code="LBL.OPTR.AUTH" /></label></th>
						<td>
						<c:forEach  items="${ userAuthInfo }" var="item" varStatus="status">
				            <input type="checkbox" name="optrAuthNo" id="optrAuthNo${ status.count }" value="<c:out value="${ item.optrAuthNo }" />"<c:if test="${ not empty item.userNo }"> checked="checked"</c:if> /><label for="optrAuthNo${ status.count }"><c:out value="${ item.optrAuthNm }" /></label>
						    <input type="hidden" id="optrAuthCd${ item.optrAuthNo }" value="<c:out value="${ item.optrAuthCd }" />" />
		                </c:forEach>					
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="optrAuthCd"><spring:message code="LBL.SYSTEM.ROLE" /></label> <span class="fwb2 fcop4">*</span></th>
						<td>
							<ctag:code name="optrAuthCd" type="S" key="OPTR_AUTH_CD" selected="${userInfo.optrAuthCd}" optdef="- 선택 -" css="form" />
							<form:errors path="optrAuthCd" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="optrExpyDttm">운영자만료일자</label> <span class="fwb2 fcop4">*</span></th>
						<td>
							<input type="text" style="width:100px;height:18px;" class="txtbox1" name="optrExpyDttm" id="optrExpyDttm" value="<c:out value="${userInfo.optrExpyDttm}" />" />
							<form:errors path="optrExpyDttm" cssClass="errorMsg" />
						</td>
					</tr>
				</tbody>
				</table>
			</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="onSaveData(); return false;" class="btn_write1"><spring:message code="BTN.SAVE" /></a>
				<a href="#none" onclick="onList(); return false;" class="btn_list1"><spring:message code="BTN.LIST" /></a>
			</div>

		</div>
	</div>
</form:form>
</div>

</body>
</html>
