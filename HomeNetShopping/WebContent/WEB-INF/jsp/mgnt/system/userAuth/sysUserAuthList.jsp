<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%@ include file="/WEB-INF/jsp/mgnt/system/userAuth/sysUserAuthInclude.jsp" %>
<body>

<div id="wrap">
<form name="form" method="post" action="<c:url value="/mgnt/system/UserAuthListAction.do" />">
	<input type="hidden" name="currentPage" value="<c:out value="${authSearchVO.currentPage}"/>" />
	<input type="hidden" name="searchValue" value="" />
	<input type="hidden" name="searchUserNo" id="searchUserNo" value="" />
	<input type="hidden" name="indvInfoInqRsnCn" id="indvInfoInqRsnCn" value="" />
	
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
						<td class="tit"><label for="searchOptrAuthNo"><spring:message code="LBL.SYSTEM.ROLE" /></label></td>
						<td>
							<ctag:code name="searchOptrAuthNo" type="S" key="OPTR_AUTH_CD" selected="${authSearchVO.searchOptrAuthNo}" optdef="- 전체 -" css="form" />
						</td>
						<td class="tit"><label for="searchMbrDc">회원구분</label></td>
						<td>
							<input type="radio" name="searchMbrDc" id="searchMbrDc0" value="" <c:if test="${empty authSearchVO.searchMbrDc}">checked="checked"</c:if> /><label for="searchMbrDc0">전체</label>
                            <ctag:code name="searchMbrDc" type="R" key="MBR_DC" selected="${authSearchVO.searchMbrDc}"  optdef="- 선택 -" css="form" />
						</td>
					</tr>
					<tr>
						<td class="tit"><spring:message code="LBL.SEARCH.DIV" /></td>
						<td colspan="2">
							<label for="searchKey" class="hidden"><spring:message code="LBL.USER.NM" /></label>
							<select style="height:22px;" name="searchKey" id="searchKey">
								<option value="userNm" <c:if test="${ authSearchVO.searchKey eq 'userNm' }"> selected="selected"</c:if> ><spring:message code="LBL.USER.NM" /></option>
								<option value="userId" <c:if test="${ authSearchVO.searchKey eq 'userId' }"> selected="selected"</c:if> ><spring:message code="LBL.USER.ID" /></option>
							</select>
							<label for="searchWord" class="hidden"><spring:message code="LBL.SEARCH" /></label>
							<input type="text" name="searchWord" id="searchWord" value="<c:out value="${authSearchVO.searchWord}" />" maxlength="100" style="width:400px;height:18px;" class="txtbox1" title="<spring:message code='LBL.SEARCH' />" />
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
				<table summary="<spring:message code='SUMMARY.SYSTEM.USERAUTH.LIST.TABLE' />">
					<caption>
						<spring:message code="TITLE.SYSTEM.AUTH" />
					</caption>
					<colgroup>
						<col width="8%" />
						<col width="15%" />
						<col width="15%" />
						<col />
						<col width="8%" />
						<col width="8%" />
						<col width="12%" />
						<col width="8%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">회원구분</th>
							<th scope="col"><spring:message code="LBL.USER.ID" /></th>
							<th scope="col"><spring:message code="LBL.USER.NM" /></th>
							<th scope="col"><spring:message code="LBL.SYSTEM.ROLE" /></th>
							<th scope="col">권한만료일자</th>
							<th scope="col">비밀번호실패횟수</th>
							<th scope="col">관리자마지막로그인일시</th>
							<th scope="col">비밀번호변경일자</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(list) == 0}">
							<tr>
							  <td colspan="8">
							      <spring:message code="COMMSG.NO.SEARCH.DATA" />
							  </td>
							</tr>
						</c:if>
			            <c:forEach items="${list}" var="userInfo" varStatus="status">   
			                <tr>
			                    <td><c:out value="${userInfo.mbrDcNm}" /></td>
			                    <td class="lnk">
			                        <a href="#none" onclick="onEdit('<c:out value="${userInfo.userNo}" />'); return false;"><c:out value="${userInfo.loginId}" /></a>
			                    </td>
			                    <td><c:out value="${userInfo.userNm}" /></td>
			                    <td><c:out value="${userInfo.userAuthNm}" /></td>
			                    <td>
									<fmt:parseDate var="optrExpyDttm" value="${fn:substring(userInfo.optrExpyDttm, 0, 8)}" pattern="yyyyMMdd"/>
									<fmt:formatDate value="${optrExpyDttm}" pattern="yyyy.MM.dd"/>
			                    </td>
			                    <td><c:out value="${userInfo.pwdFailTms}" /></td>
			                    <td>
			                    	<fmt:parseDate var="lastLoginDttm" value="${userInfo.lastLoginDttm}" pattern="yyyyMMddHHmmss"/>
									<fmt:formatDate value="${lastLoginDttm}" pattern="yyyy.MM.dd HH:mm:ss"/>
			                    </td>
			                    <td>
			                    	<fmt:parseDate var="pwdChngDttm" value="${fn:substring(userInfo.pwdChngDttm, 0, 8)}" pattern="yyyyMMdd"/>
									<fmt:formatDate value="${pwdChngDttm}" pattern="yyyy.MM.dd"/>
			                    </td>
			                </tr>
			            </c:forEach>					
					</tbody>
				</table>
			</div>

			<div class="con_paging mt30">
				<ctag:paging paginationInfo="${paginationInfo}" type="mgnt" jsFunction="onListPage" rowControl="true" jsRowFunction="onListPerLine" />
		   	</div>

		</div>
	</div>

</form>

</div>

</body>
</html>
