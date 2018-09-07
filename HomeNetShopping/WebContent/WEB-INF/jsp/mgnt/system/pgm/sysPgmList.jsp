<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/pgm/sysPgmInclude.jsp" %>
<body>

<div id="wrap">
<form name="form1" method="post" action="/mgnt/system/PgmListAction.do" onsubmit="return false;">
	<input type="hidden" name="currentPage" value="<c:out value="${searchVO.currentPage}"/>" />
	<input type="hidden" name="optrPgmNo" id="optrPgmNo" />
	<input type="hidden" name="searchValue" id="searchValue" />

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

			<div class="con_search mt24">
				<table>
				<colgroup>
				<col width="18%" />
				<col width="32%" />
				<col width="18%" />
				<col width="32%" />
				</colgroup>
				<tbody>
					<tr>
						<td colspan="4" class="blank"></td>
					</tr>
					<tr>
						<td class="tit"><label for="searchOptrPgmGrpNo"><spring:message code="LBL.OPTR.PGM.GRP.NO" /></label></td>
						<td colspan="3">
							<input type="text" name="searchOptrPgmGrpNo" id="searchOptrPgmGrpNo" value="<c:out value="${ searchVO.searchOptrPgmGrpNo }" />" maxlength="6" style="ime-mode:disabled;width:100px;height:18px;" onkeypress="return onPress(event,'numbers');" class="txtbox1" title="<spring:message code='LBL.OPTR.PGM.GRP.NO' />" onkeypress="if(event.keyCode==13) {onSearch();}" />
						</td>
					</tr>
					<tr>
						<td class="tit"><spring:message code="LBL.SEARCH.LANG" /></td>
						<td colspan="2">
							<label for="searchKey" class="hidden"><spring:message code="LBL.OPTR.PGM.NM" /></label>
							<select name="searchKey" id="searchKey" style="height:22px;" >
			                    <option value="1" <c:if test="${searchVO.searchKey eq '1'}">selected</c:if>><spring:message code="LBL.OPTR.PGM.NM" /></option>
			                    <option value="2" <c:if test="${searchVO.searchKey eq '2'}">selected</c:if>><spring:message code="LBL.OPTR.PGM.ID" /></option>
			                    <option value="3" <c:if test="${searchVO.searchKey eq '3'}">selected</c:if>><spring:message code="LBL.OPTR.PGM.URL" /></option>
			                </select>
							<label for="searchWord" class="hidden"><spring:message code="LBL.SEARCH" /></label>
							<input type="text" name="searchWord" id="searchWord" value="<c:out value="${searchVO.searchWord}" />" style="width:400px;height:18px;" class="txtbox1" title="<spring:message code='LBL.SEARCH' />" onkeypress="if(event.keyCode==13) {onSearch();}" />
						</td>
						<td class="ar">
							<a href="#none" onclick="onSearch(); return false;"  class="btn_search3 ml5 mr20"><spring:message code="BTN.SEARCH" /></a>
						</td>
					</tr>

					<tr>
						<td colspan="4" class="blank"></td>
					</tr>
				</tbody>
				</table>
			</div>

			<div class="con_list_option mt33">
				<ul>
					<li><spring:message code="LBL.SEARCH.TOTAL.CNT" /> : <c:out value="${paginationInfo.totalRecordCount}"/><spring:message code="LBL.SEARCH.TOTAL.NUM" /></li>
					<li class="op1">
					</li>
				</ul>
			</div>

			<div class="con_list">
				<table summary="<spring:message code='SUMMARY.SYSTEM.PGM.LIST.TABLE' />">
				<caption>
				<spring:message code="TITLE.SYSTEM.PGM" />
				</caption>
				<colgroup>
                    <col width="10%" />
                    <col width="10%" />
                    <col width="20%" />
                    <col width="15%" />
                    <col width="*" />
                    <col width="11%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="LBL.CHOICE" /></th>
						<th scope="col"><spring:message code="LBL.OPTR.PGM.GRP.NO" /></th>
						<th scope="col"><spring:message code="LBL.OPTR.PGM.NM" /></th>
						<th scope="col"><spring:message code="LBL.OPTR.PGM.ID" /></th>
						<th scope="col"><spring:message code="LBL.OPTR.PGM.URL" /></th>
						<th scope="col"><spring:message code="LBL.MENU.USE.YN" /></th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(list) == 0}">
                        <tr>
                            <td colspan="6">
                                <spring:message code="COMMSG.NO.SEARCH.DATA" />
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${list}" var="programInfo">
                        <tr>
                            <td>
                                <label for="progNo" class="hidden"><spring:message code="LBL.CHOICE" /></label><input type="checkbox" name="progNo" id="progNo" value="<c:out value="${programInfo.optrPgmNo}" />"/>
                            </td>
                            <td><c:out value="${programInfo.optrPgmGrpNo}"/></td>
                            <td class="al pl20"><a href="#edit" onclick="onEdit('<c:out value="${programInfo.optrPgmNo}" />');return false;">
                                <c:out value="${programInfo.optrPgmNm}" /></a>
                            </td>
                            <td class="al pl20"><c:out value="${programInfo.optrPgmId}" /></td>
                            <td class="al pl20"><c:out value="${programInfo.optrPgmUrlV}" /></td>
                            <td><c:out value="${programInfo.menuUseYn}" /></td>
                        </tr>
                    </c:forEach>
				</tbody>
				</table>
			</div>

			<div class="con_paging mt30">
				<ctag:paging paginationInfo="${paginationInfo}" type="mgnt" jsFunction="onListPage" rowControl="true" jsRowFunction="onListPerLine" />
		   	</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="onEdit();return false;" class="btn_write1"><spring:message code="BTN.NEW" /></a>
				<a href="#none" onclick="onDelete();return false;" class="btn_delete1"><spring:message code="BTN.CHOICE.DELETE" /></a>
			</div>
			<!--내용-->

		</div>
	</div>

</form>

</div>

</body>
</html>
