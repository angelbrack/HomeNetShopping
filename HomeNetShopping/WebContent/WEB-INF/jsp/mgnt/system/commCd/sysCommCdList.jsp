<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/commCd/sysCommCdInclude.jsp" %>
<body>
<div id="wrap">
<form name="form1" method="post" action="<c:url value='mgnt/system/commCd/ListAction.do'/>" onsubmit="return false;">
	<input type="hidden" name="currentPage" value="<c:out value="${searchVO.currentPage}"/>" />
	<input type="hidden" name="grpCdId" value="" />
	<input type="hidden" name="cdId" value="" />
	<input type="hidden" name="cmd" value="I" />

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
						<td class="tit"><label for="searchGrpCdId"><spring:message code="LBL.CODE.DIV" /></label></td>
						<td colspan="3">
                            <form:select path="searchVO.searchGrpCdId" cssStyle="width:340px;height:22px;">
                            	<form:option value=""><spring:message code="LBL.ALL" /></form:option>
                            	<form:options items="${idList}" itemValue="grpCdId" itemLabel="cdNm" />
                            </form:select>
                            <input type="text" maxlength="100" name="searchGrpCdIdBox" id="searchGrpCdIdBox" value="<c:out value="${searchVO.searchGrpCdIdBox}" />" style="width:205px;height:18px;" class="txtbox1" onkeypress="if(event.keyCode==13){form.searchGrpCdId.value = this.value; onListPage(1);}" />
                            <p class="fc_red mt5 fs11">※ 찾으려고 하는 코드구분 값과 엔터를 입력 하면 콤보박스에서 찾습니다.</p>
						</td>
					</tr>
					<tr>
						<td class="tit"><label for="searchWord"><spring:message code="LBL.CODE.NAME" /></label></td>
						<td colspan="2">
							<input type="text" name="searchWord" id="searchWord" title="<spring:message code='LBL.CODE.NAME' />" maxlength="100" value="<c:out value="${searchVO.searchWord}" escapeXml="false"/>" style="width:280px;height:18px;" class="txtbox1" onkeypress="if(event.keyCode==13) {onListPage(1);}"/>
						</td>
						<td class="ar">
							<a href="#none" onclick="onSearch(1);"  class="btn_search3 ml5 mr20"><spring:message code="BTN.SEARCH" /></a>
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
					<li><spring:message code="LBL.SEARCH.TOTAL.CNT" /> : <c:out value="${resultCount}"/> <spring:message code="LBL.SEARCH.TOTAL.NUM" /></li>
					<li class="op1">
					</li>
				</ul>
			</div>

			<div class="con_list">
				<table summary="<spring:message code='SUMMARY.SYSTEM.COMMCD.LIST.TABLE' />">
				<caption>
				<spring:message code="TITLE.SYSTEM.COMMCD" />
				</caption>
				<colgroup>
	            <col width="8%" />
	            <col width="25%" />
	            <col width="15%" />
	            <col width="*" />
	            <col width="10%" />
	            <col width="10%" />
				</colgroup>
				<thead>
					<tr>
	                    <th scope="col"><spring:message code="LBL.NUM" /></th>
	                    <th scope="col"><spring:message code="LBL.CODE.DIV" /></th>
	                    <th scope="col"><spring:message code="LBL.CODE" /></th>
	                    <th scope="col"><spring:message code="LBL.CODE.NAME" /></th>
	                    <th scope="col"><spring:message code="LBL.SORT.ORDER" /></th>
	                    <th scope="col"><spring:message code="LBL.USE.YN" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(resultList) == 0}">
	                    <tr>
	                        <td colspan="6">
	                            <spring:message code="COMMSG.NO.SEARCH.DATA" />
	                        </td>
	                    </tr>
	                </c:if>
	                <c:forEach items="${resultList}" var="result" varStatus="status">  
	                    <tr>
	                        <td><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.currentPage-1) * searchVO.recordCountPerPage + status.count)}"/></td>
	                        <td><c:out value="${result.grpCdId}"/></td>
	                        <td><c:out value="${result.cdId}"/></td>
	                        <td class="al pl20">
	                            <a href="javascript:onEditPage('<c:out value="${result.grpCdId}"/>', '<c:out value="${result.cdId}"/>')"><c:out value="${result.cdNm}" escapeXml="false"/></a>
	                        </td>
	                        <td><c:out value="${result.sortOr}"/></td>
	                        <td><c:out value="${result.useYn}"/></td>
	                    </tr>
	                </c:forEach>
				</tbody>
				</table>
			</div>

			<div class="con_paging mt30">
				<ctag:paging paginationInfo="${paginationInfo}" type="mgnt" jsFunction="onListPage" rowControl="true" jsRowFunction="onListPerLine" />
		   	</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="javascript:onNewPage();" class="btn_write1"><spring:message code="BTN.NEW" /></a>
			</div>

		</div>
	</div>

</form>
</div>
</body>
</html>
