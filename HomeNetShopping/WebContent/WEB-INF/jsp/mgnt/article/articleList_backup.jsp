<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<div id="wrap">
	<div id="con_right" class="mt29">
		<div class="con_right_c">
			<form name="formInit" id="formInit" method="post" action="">
				<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/>
				
				<input type="hidden" id="artcCd" 				name="artcCd" 				value="" 														/>
			</form>
			<form name="form1" id="form1" method="post" action="">
				<input type="hidden" id="cmd"					name="cmd"					value=""														/> <!-- 등록/수정 구분 		-->
				<input type="hidden" id="currentPage" 			name="currentPage" 			value="<c:out value="${searchVO.currentPage 		}" />" 		/> <!-- 페이지 번호 			-->
				<input type="hidden" id="artcCd" 				name="artcCd" 				value="" 														/> <!-- 품목군번호	 		-->
			<!--contents-->
			<div class="con_top">
				<div class="tit">
					<c:out value="${requestScope.USER_PGM_AUTH.optrPgmNm}" />
				</div>
				<div class="navi">
					<img src="<ctag:conf key="THEME.PATH" />mgnt/images/ic_01.jpg" alt="" />
					<span>&nbsp;>&nbsp;</span>
					품목군 관리
					<span>&nbsp;>&nbsp;</span>
					품목군 관리
				</div>	
			</div>
			
			<div class="con_search mt24">
				<table>
					<colgroup>
					<col width="15%" />
					<col width="*" />
					<col width="30%" />
					</colgroup>
					<tbody>
						<tr>
							<td colspan="3" class="blank"></td>
						</tr>
						<tr>
                            <td class="tit"><label for="searchWord">검색어</label></td>
                            <td>
                                <select style="width:120px;height:22px;" name="searchKey" id="searchKey">
                                    <option value="000" <c:if test="${empty searchVO.searchKey or searchVO.searchKey eq '000'}">selected="selected"</c:if>>품목군명</option>
                                    <option value="001" <c:if test="${searchVO.searchKey eq '001'}">selected="selected"</c:if>>품목군상세설명</option>
                                </select>
                                <input type="text" name="searchWord" id="searchWord" maxlength="100" style="width:400px;height:18px;" class="txtbox1" title="" value="${searchVO.searchWord }" class="txtbox1" onkeypress="if(event.keyCode==13) {articleList.fnSearch();}" escapeXml="false" />
                            </td>
                            <td class="ar">
                                <a href="#none" onclick="articleList.fnSearch();" class="btn_search3 ml5 mr20">검색</a>
                            </td>
                        </tr>
						<tr> 
	       					<td colspan="3" class="blank"></td>
    					</tr>
					</tbody>
				</table>
			</div>
			
			<div class="con_list_option mt33">
				<ul>
					<li>검색건수 : <fmt:formatNumber value="${paginationInfo.totalRecordCount }" pattern="#,###" />건</li>
					<li class="op1"></li>
				</ul>
			</div>
			
			<div class="con_list">
				<table>
					<caption>
						품목군정보에 대한 목록
					</caption>
					<colgroup>
						<col width="4%" />
						<col width="6%" />
						<col width="8%" />
						<col width="6%" />
						<col width="6%" />
						<col width="8%" />
						<col width="6%" />
						<col width="6%" />
						<col width="6%" />
						<col width="10%" />
						<col width="10%" />
						
					</colgroup>
					<thead>
						<tr>
							<th scope="col">No</th>
							<th scope="col">품목코드</th>
							<th scope="col">품목군명</th>
							<th scope="col">전자상거래품목코드</th>
							<th scope="col">상위품목코드</th>
							<th scope="col">상위품목군명</th>
							<th scope="col">재고율</th>
							<th scope="col">최대구매수량</th>
							<th scope="col">상품마진율</th>
							<th scope="col">등록일</th>
							<th scope="col">수정일</th>
						</tr>
					</thead>
					<tbody>
			<c:choose>
				<c:when test="${fn:length(list) > 0 }">
					<c:forEach items="${list}" var="item" varStatus="status">
						<tr>
							<td><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.currentPage-1) * searchVO.recordCountPerPage + status.count)}"/></td>
							<td>
								<a href="#none" onClick="articleList.fnEdit('U', '<c:out value="${item.artcCd }"/>');">
								<c:out value="${item.artcCd }"/>
								</a>	
							</td>
							<td><c:out value="${item.artcNm }"/></td>
							<td><c:out value="${item.ecArtcCd }"/></td>
							<td><c:out value="${item.uprArtcCd }"/></td>
							<td><c:out value="${item.uprArtcNm }"/></td>
							<td><c:out value="${item.onlBrchInvRt }"/></td>
							<td><c:out value="${item.maxLmtQty }"/></td>
							<td><c:out value="${item.goodsMrgnRt }"/></td>
							<td>
                                <fmt:parseDate value="${item.wrtDttm}" var="wrtDttm" pattern="yyyyMMddHHmmss"/>
                                <fmt:formatDate value="${wrtDttm}" pattern="yyyy.MM.dd"/>
                            </td>
                            <td>
                                <fmt:parseDate value="${item.updtDttm}" var="updtDttm" pattern="yyyyMMddHHmmss"/>
                                <fmt:formatDate value="${updtDttm}" pattern="yyyy.MM.dd"/>
                            </td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
						<tr>
							<td colspan="7">검색된 데이터가 없습니다.</td>
						</tr>
				</c:otherwise>
			</c:choose>
					</tbody>
				</table>
			</div>
			
			<div class="con_paging mt30">
				<ctag:paging paginationInfo="${paginationInfo}" type="portal" jsFunction="articleList.fnListPage" rowControl="true" jsRowFunction="articleList.fnListPerLine" />
		   	</div>
			
			<div class="con_btn mt30">
				<a href="#none" onclick="articleList.fnEdit('I'); return false;" class="btn_write1" >신규</a>
			</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="<ctag:conf key="JS.PATH" />/mgnt/article/article.js?20180918000000"></script>
<script type="text/javascript">
<!--
$(document).ready(function() {
	// 화면 초기화
	articleList.init();
});
//-->
</script>