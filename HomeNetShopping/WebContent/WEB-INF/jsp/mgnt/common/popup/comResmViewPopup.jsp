<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/user/common/include/html_start.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	
	if("<c:out value='${fn:length(fileList2)}'/>" != 0 ) {
		<c:forEach var='fileList2' items='${fileList2}' varStatus='status'>
			var fileName = "<c:out value='${fileList2.atchmnflStreNm}'/>";
			var realFileName= "<c:out value='${fileList2.atchmnflNm}'/>";
			
			var filePathUrl = "<c:out value="${_CTX_PATH}" />/user_uploading?pathkey=STUD.RESUME&getthumb="+fileName+"&realFileName="+encodeURI(encodeURIComponent(realFileName));
			$("#thumnailImg").attr("src", filePathUrl);
			$("#thumnailImg").attr("alt", realFileName);
		</c:forEach>
	}else {
		$("#thumnailImg").attr("src", "<ctag:conf key="THEME.PATH" />/user/images/sub/photo_thum.png");
		$("#thumnailImg").attr("alt", "사진");
	}
 	
});

</script>

<form name="form1" id="form1" method="post" action="" onsubmit="return false;">
	<input type="hidden" name="resmNo" id="resmNo" value="" />
	
    <!-- sub_container -->
    <div id="sub_container">
        <!-- sub_content -->
        <div class="sub_content" style="margin-top:-180px;">
            <!-- content -->
            <div class="content container">
                <div class="resume_tit mb50"><c:out value="${resumeInfo.resmNm}" /></div>

                <h4 class="title_content">개인정보</h4>
                <div class="board_wrap">
                    <table summary="개인정보의 성명, 학적사항, 학번, 성별, 학과, 이메일, 휴대폰번호, 주소로 구성된 표" class="board_view">
                        <caption>이력서 기본정보</caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:15%;">
                            <col style="width:27%;">
                            <col style="width:15%;">
                            <col style="width:28%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td rowspan="5">
                                    <img src="" id="thumnailImg" onerror="<ctag:conf key="THEME.PATH" />user/images/sub/photo_thum.png" alt="" width="130" height="180" title="사진" />
                                </td>
                                <th scope="row">성명</th>
                                <td><c:out value="${studentInfo.userNm}" /></td>
                                <th scope="row">학적사항</th>
                                <td><c:out value="${studentInfo.recStsMinNm}" />(<c:out value="${studentInfo.schYear}" />학년)</td>
                            </tr>
                            <tr>
                                <th scope="row">학번</th>
                                <td><c:out value="${resumeInfo.stno}" /></td>
                                <th scope="row">성별</th>
                                <td><c:out value="${resumeInfo.sexDc}" /></td>
                            </tr>
                            <tr>
                                <th scope="row">학과</th>
                                <td colspan="3"><c:out value="${resumeInfo.majorNm}" /></td>
                            </tr>
                            <tr>
                                <th scope="row">E-mail</th>
                                <td><c:out value="${resumeInfo.userCtplc}" /></td>
                                <th scope="row">휴대폰번호</th>
                                <td><c:out value="${resumeInfo.userTlno}" /></td>
                            </tr>
                            <tr>
                                <th scope="row">주소</th>
                                <td colspan="3"><c:out value="${resumeInfo.userAddr}" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <h4 class="title_content">기본정보</h4>
                <div class="board_wrap">
                    <table summary="기본정보의 희망직종, 희망취업형태, 경력사항, 희망근무지로 구성된 표" class="board_view">
                        <caption>기본정보</caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:35%;">
                            <col style="width:15%;">
                            <col style="width:35%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row">희망직종 *</th>
                                <td colspan="3"><c:out value="${resumeInfo.hopeKoocNm}" /></td>
                            </tr>
                            <tr>
                                <th scope="row">희망취업형태 *</th>
                                <td><c:out value="${resumeInfo.hopeGjobFomNm}" /></td>
                                <th scope="row">경력사항 *</th>
                                <td><c:out value="${resumeInfo.carrMtrNm}" /></td>
                            </tr>
                            <tr>
                                <th scope="row">희망근무지 *</th>
                                <td colspan="3"><c:out value="${resumeInfo.hopeWrkPlcNm}" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <h4 class="title_content">신상정보 및 병역사항</h4>
                <div class="board_wrap">
                    <table summary="신상정보 및 병역사항의 보훈대상여부, 병역사항으로 구성된 표" class="board_view">
                        <caption>신상정보 및 병역사항</caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:35%;">
                            <col style="width:15%;">
                            <col style="width:35%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row">보훈대상여부</th>
                                <td><c:out value="${resumeInfo.mrtTgtNm}" /></td>
                                <th scope="row">병역사항</th>
                                <td><c:out value="${resumeInfo.mlsvCd}" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <h4 class="title_content">어학능력</h4>
                <div class="board_wrap">
                    <table summary="어학능력의 어학시험명, 점수(등급), 취득일자로 구성된 표" class="board_view">
                        <caption>어학능력</caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:35%;">
                            <col style="width:15%;">
                            <col style="width:35%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" colspan="4">어학능력</th>
                            </tr>
                            <c:if test="${not empty resumeInfo.lstcsExamList and fn:length(resumeInfo.lstcsExamList) > 0}">
                       			<c:forEach var="lstcsExamList" items="${resumeInfo.lstcsExamList}" varStatus="lstcsExamStatus">
		                            <tr>
		                                <th scope="row">어학시험명</th>
		                                <td colspan="3"><c:out value="${lstcsExamList.lstcsExamNm}" escapeXml="false" /></td>
		                            </tr>
		                            <tr>
		                                <th scope="row">점수(등급)</th>
		                                <td><c:out value="${lstcsExamList.lstcsScGrdNm}"  escapeXml="false" /></td>
		                                <th scope="row">취득일자</th>
		                                <td>
		                                	<fmt:parseDate var="gainDt" value="${fn:substring(lstcsExamList.gainDt, 0, 8)}" pattern="yyyyMMdd"/>
											<fmt:formatDate value="${gainDt}" pattern="yyyy.MM.dd"/>
		                                </td>
		                            </tr>
                            	</c:forEach>
                       		</c:if>
                        </tbody>
                    </table>
                </div>

                <h4 class="title_content">자격면허</h4>
                <div class="board_wrap">
                    <table summary="자격면허의 자격증명, 주관기관, 발급일자로 구성된 표" class="board_view">
                        <caption>자격면허</caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:35%;">
                            <col style="width:15%;">
                            <col style="width:35%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" colspan="4">자격면허</th>
                            </tr>
                       		<c:if test="${not empty resumeInfo.certList and fn:length(resumeInfo.certList) > 0}">
                       			<c:forEach var="certList" items="${resumeInfo.certList}" varStatus="certStatus">
		                            <tr>
		                                <th scope="row">자격증명</th>
		                                <td colspan="3"><c:out value="${certList.certNm}" escapeXml="false" /></td>
		                            </tr>
		                            <tr>
		                                <th scope="row">주관기관</th>
		                                <td><c:out value="${certList.certBurNm}" escapeXml="false" /></td>
		                                <th scope="row">발급일자</th>
		                                <td>
		                                	<fmt:parseDate var="isueDt" value="${fn:substring(certList.isueDt, 0, 8)}" pattern="yyyyMMdd"/>
											<fmt:formatDate value="${isueDt}" pattern="yyyy.MM.dd"/>
		                                </td>
		                            </tr>
                       			</c:forEach>
                       		</c:if>                            
                        </tbody>
                    </table>
                </div>

                <h4 class="title_content">봉사활동</h4>
                <div class="board_wrap">
                    <table summary="봉사활동의 제목, 봉사기관, 봉사기간, 봉사내용으로 구성된 표" class="board_view">
                        <caption>봉사활동</caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:35%;">
                            <col style="width:15%;">
                            <col style="width:35%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" colspan="4">봉사활동</th>
                            </tr>
                       		<c:if test="${not empty resumeInfo.rdsvActiList and fn:length(resumeInfo.rdsvActiList) > 0}">
                       			<c:forEach var="rdsvActiList" items="${resumeInfo.rdsvActiList}" varStatus="rdsvActiStatus">                            
                            <tr>
                                <th scope="row">제목</th>
                                <td colspan="3"><c:out value="${rdsvActiList.rdsvActiNm}" escapeXml="false"/></td>
                            </tr>
                            <tr>
                                <th scope="row">봉사기관</th>
                                <td><c:out value="${rdsvActiList.rdsvBurNm}" escapeXml="false" /></td>
                                <th scope="row">봉사기간</th>
                                <td>
                                	<fmt:parseDate var="rdsvTeSdt" value="${fn:substring(rdsvActiList.rdsvTeSdt, 0, 8)}" pattern="yyyyMMdd"/>
                                	<fmt:parseDate var="rdsvTeEdt" value="${fn:substring(rdsvActiList.rdsvTeEdt, 0, 8)}" pattern="yyyyMMdd"/>
									<fmt:formatDate value="${rdsvTeSdt}" pattern="yyyy.MM.dd"/> ~ <fmt:formatDate value="${rdsvTeEdt}" pattern="yyyy.MM.dd"/>                                	
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">봉사내용</th>
                                <td colspan="3"><c:out value="${rdsvActiList.rdsvCn}" escapeXml="false" /></td>
                            </tr>
                       			</c:forEach>
                       		</c:if>                            
                        </tbody>
                    </table>
                </div>

                <h4 class="title_content">수상내역</h4>
                <div class="board_wrap">
                    <table summary="수상내역의 수상명, 수상기관, 수상일시, 수상내용으로 구성된 표" class="board_view">
                        <caption>수상내역</caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:35%;">
                            <col style="width:15%;">
                            <col style="width:35%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" colspan="4">수상내역</th>
                            </tr>
                       		<c:if test="${not empty resumeInfo.awdItmzList and fn:length(resumeInfo.awdItmzList) > 0}">
                       			<c:forEach var="awdItmzList" items="${resumeInfo.awdItmzList}" varStatus="awdItmzStatus">                            
		                            <tr>
		                                <th scope="row">수상명</th>
		                                <td colspan="3"><c:out value="${awdItmzList.awdNm}" escapeXml="false"/></td>
		                            </tr>
		                            <tr>
		                                <th scope="row">수상기관</th>
		                                <td><c:out value="${awdItmzList.awdBurNm}" escapeXml="false"/></td>
		                                <th scope="row">수상일시</th>
		                                <td>
		                                	<fmt:parseDate var="awdDt" value="${fn:substring(awdItmzList.awdDt, 0, 8)}" pattern="yyyyMMdd"/>
											<fmt:formatDate value="${awdDt}" pattern="yyyy.MM.dd"/>		                                	
		                                </td>
		                            </tr>
		                            <tr>
		                                <th scope="row">수상내용</th>
		                                <td colspan="3"><c:out value="${awdItmzList.awdCn}" escapeXml="false"/></td>
		                            </tr>
                       			</c:forEach>
                       		</c:if>     		                            
                        </tbody>
                    </table>
                </div>

                <h4 class="title_content">자기소개서</h4>
                <div class="resume_box">
                    <dl>
                        <dt>자기소개서의 상세내용입니다.</dt>
                        <dd><c:out value="${resumeInfo.selfIndcCn}" escapeXml="false" /></dd>
                    </dl>
                </div>

                <div class="button_area_lg">
                    <a href="javascript:void(0);" class="btn_lg" onclick="self.close();">닫기</a>
                </div>
            </div>
            <!-- /content -->
        </div>
        <!-- /sub_content -->
    </div>
    <!-- /sub_container -->

</form>
