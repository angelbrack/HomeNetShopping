<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/pgm/sysPgmInclude.jsp" %>
<body>

<div id="wrap">
<form name="form1" id="form1" method="post" action="" onsubmit="return false;">
	<input type="hidden" name="currentPage" value="<c:out value="${searchVO.currentPage}"/>" />
	
	<div class="pop_con_top">
		<div class="pop_con_top_tit">
			<c:out value="${requestScope.USER_PGM_AUTH.optrPgmNm}" /><%@ include file="/WEB-INF/jsp/common/incManual.jspf" %>
		</div>
		<div class="pop_con_top_close">
			<a href="#none" onclick="self.close();" class="btn_close_01"></a>
		</div>
	</div>

	<div id="pop_con">
		<div class="pop_con_c">

			<!--내용-->
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
						<td class="tit"><spring:message code="LBL.OPTR.PGM.GRP.NO" /></td>
						<td colspan="3">
							<label for="searchOptrPgmGrpNo" class="hidden"><spring:message code="LBL.OPTR.PGM.GRP.NO" /></label>
							<input type="text" name="searchOptrPgmGrpNo" id="searchOptrPgmGrpNo" value="<c:out value="${ searchVO.searchOptrPgmGrpNo }" />" maxlength="100" style="ime-mode:disabled;width:100px;height:18px;" class="txtbox1" title="<spring:message code='LBL.OPTR.PGM.GRP.NO' />" onkeypress="return onPress(event,'numbers');" />
						</td>
					</tr>
					<tr>
						<td class="tit"><spring:message code="LBL.SEARCH.DIV" /></td>
						<td colspan="3">
							<label for="searchKey" class="hidden"><spring:message code="LBL.SEARCH.DIV" /></label>
							<select name="searchKey" id="searchKey" style="width:200px;height:22px;">
								<option value="1" <c:if test="${searchVo.searchKey eq '1'}">selected</c:if>><spring:message code="LBL.OPTR.PGM.NM" /></option>
			                    <option value="2" <c:if test="${searchVo.searchKey eq '2'}">selected</c:if>><spring:message code="LBL.OPTR.PGM.ID" /></option>
			                    <option value="3" <c:if test="${searchVo.searchKey eq '3'}">selected</c:if>><spring:message code="LBL.OPTR.PGM.URL" /></option>
							</select>
							<label for="searchWord" class="hidden"><spring:message code="LBL.SEARCH" /></label>
							<input type="text" name="searchWord" id="searchWord" value="<c:out value="${searchVO.searchWord}" />" style="width:400px;height:18px;" class="txtbox1" title="<spring:message code='LBL.SEARCH' />" onkeypress="if(event.keyCode==13) {onSearch2();}" />
							<a href="#none" onclick="onSearch2();return false;"  class="btn_search1"><spring:message code="BTN.SEARCH" /></a>
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
					<li><spring:message code="LBL.SEARCH.TOTAL.CNT" /></b> : <spring:message code="LBL.SEARCH.TOTAL" /> <c:out value="${paginationInfo.totalRecordCount}" /><spring:message code="LBL.SEARCH.TOTAL.NUM" /></li>
					<li class="op1">
					</li>
				</ul>
			</div>

			<div class="con_list">
				<table summary="<spring:message code='SUMMARY.SYSTEM.PGM.SEARCH.TABLE' />">
				<caption>
					<spring:message code="TITLE.SYSTEM.PGM" />
				</caption>
				<colgroup>
					<col width="12%" />
					<col width="20%" />
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th><spring:message code="LBL.OPTR.PGM.GRP.NO" /></th>
						<th><spring:message code="LBL.OPTR.PGM.NM" /></th>
						<th><spring:message code="LBL.OPTR.PGM.ID" /></th>
						<th><spring:message code="LBL.OPTR.PGM.URL" /></th>
					</tr>
				</thead>
				<tbody>
					 <c:if test="${fn:length(list) == 0}">
					  <tr>
					  	<td colspan="4">
					  		<spring:message code="COMMSG.NO.SEARCH.DATA" />
					  	</td>
					  </tr>
					 </c:if>
					<c:forEach items="${list}" var="programInfo">	
						<tr>
							<td><c:out value="${programInfo.optrPgmGrpNo}" /></td>
							<td class="op2"><a href="#edit" onclick="onSetProgram('<c:out value="${programInfo.optrPgmNo}" />','<c:out value="${programInfo.optrPgmNm}" />');return false;">
								<c:out value="${programInfo.optrPgmNm}" /></a>
							</td>
							<td class="op2"><c:out value="${programInfo.optrPgmId}" /></td>
							<td class="op2"><c:out value="${programInfo.optrPgmUrlV}" /></td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>

			<div class="con_paging mt30">
				<ctag:paging paginationInfo="${paginationInfo}" type="mgnt" jsFunction="onListPage2" rowControl="true" jsRowFunction="onListPerLine2" />
		   	</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="self.close();" class="btn_list1">닫기</a>
			</div>
			<!--내용-->

		</div>
	</div>
</form>	
</div>

</body>
</html>
