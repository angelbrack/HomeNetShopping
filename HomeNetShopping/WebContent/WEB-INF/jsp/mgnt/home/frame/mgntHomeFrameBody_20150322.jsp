<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<script type="text/javascript">
<!--
	var _PATH_LEFT = "<c:url value='/mgnt/home/menuLeftAction.do' />";
	
	function onMovePage(urlGbn, param){
	 	var url = "";
	 	var currentMenuNo = "";
	 	
	 	if(urlGbn == '1'){
	 		url = "/mgnt/member/prtcnListAction.do";
	 		currentMenuNo = 3;
	 	}else if(urlGbn == '2'){
	 		url = "/mgnt/member/mngeSysUserListAction.do?searchEduOptrApprStatCd="+param;
	 		currentMenuNo = 3;
	 	}else if(urlGbn == '3'){
	 		url = "/mgnt/report/applicationListAction.do";
	 		currentMenuNo = 4;
	 	}else if(urlGbn == '4'){
	 		url = "/mgnt/report/completionListAction.do";
	 		currentMenuNo = 4;
	 	}else if(urlGbn == '5'){
	 		url = "/mgnt/board/QnaListAction.do?searchAnswerYn=N";
	 		currentMenuNo = 8;
	 	}else if(urlGbn == '6'){
	 		url = "/mgnt/board/QnaViewAction.do?buSeqNo="+param;
	 		currentMenuNo = 8;
	 	}else if(urlGbn == '7'){
	 		url = "/mgnt/board/NoticeListAction.do";
	 		currentMenuNo = 8;
	 	}else if(urlGbn == '8'){
	 		url = "/mgnt/board/NoticeViewAction.do?buSeqNo="+param;
	 		currentMenuNo = 8;
	 	}
		parent.document.getElementById("mainFrame").cols = "217,*" ;
	    parent.document.getElementById("leftFrame").src = _PATH_LEFT+"?menuSelYn=Y&currentMenuNo="+currentMenuNo;
	    parent.document.getElementById("bodyFrame").src = url;
	}
//-->
</script>
<body>

<div id="wrap">

    <div id="con_right" class="mt29">
        <div class="con_right_c">

            <!--내용-->
            <div class="con_top">
                <div class="tit">메인리스트</div>
                <div class="navi">
                    <img src="<ctag:conf key="THEME.PATH" />support/images/ic_01.jpg" alt="<spring:message code="LBL.FIRST" />" />
                    <span>&nbsp;&gt;&nbsp;</span><spring:message code="TITLE.MEMBER" />
                    <span>&nbsp;&gt;&nbsp;</span>메인리스트
                </div>
            </div>

            <div class="con_layout mt24">
                <table>
	                <colgroup>
		                <col width="49%" />
		                <col width="2%" />
		                <col width="49%" />
	                </colgroup>
	                <tbody>
	                    <tr>
	                        <td class="vat">
	                            <!--왼쪽-->
	                            <div class="con_layout2">
		                            <div class="con_title2"><spring:message code="TITLE.MEMBER" /></div>
		
		                            <div class="con_list2">
		                                <table summary="<spring:message code='SUMMARY.APPLY.USER.LIST.TABLE' />">
			                                <caption><spring:message code="TITLE.MEMBER" /></caption>
			                                <colgroup>
				                                <col width="40%" />
				                                <col width="*" />
			                                </colgroup>
			                                <thead>
			                                    <tr>
			                                        <th scope="col"><spring:message code="LBL.DIVISION" /></th>
			                                        <th scope="col"><spring:message code="LBL.CNT" /></th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                    <c:forEach var="list" items="${user}">
				                                    <tr>
				                                        <td><c:out value="${list.gbn}" /></td>
				                                        <td>
				                                        	<c:if test="${list.gbn == '총인원'}">
				                                        		<fmt:formatNumber value="${list.cnt}" />
				                                        	</c:if>
				                                        	<c:if test="${list.gbn == '종사자'}">
				                                        		<a href="#" onclick="onMovePage('1', '')"><fmt:formatNumber value="${list.cnt}" /></a>
				                                        	</c:if>
				                                        	<c:if test="${list.gbn == '교육기간 담당자'}">
				                                        		<a href="#" onclick="onMovePage('2', '')"><fmt:formatNumber value="${list.cnt}" /></a>
				                                        	</c:if>
				                                        </td>
				                                    </tr>
			                                    </c:forEach>
			                                </tbody>
		                                </table>
		                            </div>
		                        </div>
	                            <!--왼쪽-->
	                        </td>
	                        <td>
	                            <!--가운데-->
	                            <!--가운데-->
	                        </td>
	                        <td class="vat">
	                            <!--오른쪽-->
	                            <div class="con_layout2">
		                            <div class="con_title2"><spring:message code="LBL.CHARGE.PERSON.APPROVE.REQUISITION" /></div>
		
		                            <div class="con_list2">
		                                <table summary="<spring:message code='SUMMARY.MEMBER.MEMBER.POPUP.LIST' />">
			                                <caption><spring:message code="LBL.CHARGE.PERSON.APPROVE.REQUISITION" /></caption>
			                                <colgroup>
				                                <col width="40%" />
				                                <col width="*" />
			                                </colgroup>
			                                <thead>
			                                    <tr>
			                                        <th scope="col"><spring:message code="LBL.DIVISION" /></th>
			                                        <th scope="col"><spring:message code="LBL.YEAR.CHARGE.PERSON.CNT" /></th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<c:forEach var="list" items="${eduList}">
				                                    <tr>
				                                        <td><c:out value="${list.eduOptrApprStatNm}" /></td>
				                                        <td>
				                                        	<a href="#" onclick="onMovePage('2', '<c:out value="${list.eduOptrApprStatCd}" />')"><fmt:formatNumber value="${list.cnt}" /></a>
				                                        </td>
				                                    </tr>
			                                    </c:forEach>
			                                </tbody>
		                                </table>
		                            </div>
		                        </div>
	                            <!--오른쪽-->
	                        </td>
	                    </tr>
	                </tbody>
                </table>
            </div>

            <div class="con_layout mt36">
                <table>
	                <colgroup>
		                <col width="49%" />
		                <col width="2%" />
		                <col width="49%" />
	                </colgroup>
	                <tbody>
	                    <tr>
	                        <td class="vat">
	                            <!--왼쪽-->
	                            <div class="con_layout2">
		                            <div class="con_title2">
		                                <spring:message code="TITLE.REPORT.ADMISSION" />
		                                <div class="con_title2_btn">
		                                    <a href="#" onclick="onMovePage('3', '');" class="btn_class_add3">▶ more</a>
		                                </div>
		                            </div>
		
		                            <div class="con_list2">
		                            	<table summary="<spring:message code='SUMMARY.COURSE.ADMISSION.REPORT.LIST.TABLE' />">
			                                <caption><spring:message code="TITLE.REPORT.ADMISSION" /></caption>
		                                <colgroup>
		                                <col width="40%" />
		                                <col width="*" />
		                                </colgroup>
		                                <thead>
		                                    <tr>
		                                        <th scope="col"><spring:message code="LBL.DIVISION" /></th>
		                                        <th scope="col"><spring:message code="LBL.YEAR.CHARGE.PERSON.CNT" /></th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                    <tr>
		                                        <td><spring:message code="LBL.APPROVE" /></td>
		                                        <td><a href="#" onclick="onMovePage('3', '');"><c:out value="${admissionData.approved}" /></a></td>
		                                    </tr>
		                                    <tr>
		                                        <td><spring:message code="LBL.NOT.APPROVE" /></td>
		                                        <td><a href="#" onclick="onMovePage('3', '');"><c:out value="${admissionData.notApproved}" /></a></td>
		                                    </tr>
		                                </tbody>
	                                </table>
	                            </div>
	                        </div>
                            <!--왼쪽-->
                        </td>
                        <td>
                            <!--가운데-->
                            <!--가운데-->
                        </td>
                        <td class="vat">
                            <!--오른쪽-->
                            <div class="con_layout2">
	                            <div class="con_title2">
	                                <spring:message code="TITLE.REPORT.COMPLETION" />
	                                <div class="con_title2_btn">
	                                    <a href="#" onclick="onMovePage('4', '');" class="btn_class_add3">▶ more</a>
	                                </div>
	                            </div>
	
	                            <div class="con_list2">
	                                <table summary="<spring:message code='SUMMARY.COURSE.COMPLETION.REPORT.LIST.TABLE' />">
			                                <caption><spring:message code="TITLE.REPORT.COMPLETION" /></caption>
		                                <colgroup>
		                                <col width="40%" />
		                                <col width="*" />
		                                </colgroup>
		                                <thead>
		                                    <tr>
		                                        <th scope="col"><spring:message code="LBL.DIVISION" /></th>
		                                        <th scope="col"><spring:message code="LBL.YEAR.CHARGE.PERSON.CNT" /></th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                    <tr>
		                                        <td><spring:message code="LBL.APPROVE" /></td>
		                                        <td><a href="#"onclick="onMovePage('4', '');"><c:out value="${completionData.approved}" /></a></td>
		                                    </tr>
		                                    <tr>
		                                        <td><spring:message code="LBL.NOT.APPROVE" /></td>
		                                        <td><a href="#" onclick="onMovePage('4', '');"><c:out value="${completionData.notApproved}" /></a></td>
		                                    </tr>
		                                </tbody>
		                                </table>
		                            </div>
		                        </div>
	                            <!--오른쪽-->
	                        </td>
	                    </tr>
	                </tbody>
                </table>
            </div>

            <div class="con_layout mt36">
                <table>
	                <colgroup>
		                <col width="49%" />
		                <col width="2%" />
		                <col width="49%" />
	                </colgroup>
	                <tbody>
	                    <tr>
	                        <td class="vat">
	                            <!--왼쪽-->
	                            <div class="con_layout2">
		                            <div class="con_title2">
		                                <spring:message code="LBL.NOREPLY.QUESTION.AND.ANSWER" />
		                                <div class="con_title2_btn">
		                                    <a href="#" onclick="onMovePage('5', '');" class="btn_class_add3">▶ more</a>
		                                </div>
		                            </div>
		
		                            <div class="con_list2">
		                                <table summary="<spring:message code='SUMMARY.HELPDESK.NOREPLY.QNA.LIST.TABLE' />">
			                                <caption><spring:message code="LBL.NOREPLY.QUESTION.AND.ANSWER" /></caption>
			                                <colgroup>
				                                <col width="*" />
				                                <col width="20%" />
			                                </colgroup>
			                                <thead>
			                                    <tr>
			                                        <th scope="col"><spring:message code="LBL.TITLE" /></th>
			                                        <th scope="col"><spring:message code="LBL.REGDATE" /></th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                    <c:forEach var="list" items="${qnaList}">
				                                    <tr>
				                                        <td class="al pl20"><a href="#" onclick="onMovePage('6', '<c:out value="${list.buSeqNo}" />')"><c:out value="${list.titNm}" /></a></td>
				                                        <td><c:out value="${list.wrtDttm}" /></td>
				                                    </tr>
			                                    </c:forEach>
			                                </tbody>
		                                </table>
		                            </div>
		                        </div>
	                            <!--왼쪽-->
	                        </td>
	                        <td>
	                            <!--가운데-->
	                            <!--가운데-->
	                        </td>
	                        <td class="vat">
	                            <!--오른쪽-->
	                            <div class="con_layout2">
		                            <div class="con_title2">
		                               	<spring:message code="TITLE.HELPDESK.NOTICE" />
		                                <div class="con_title2_btn">
		                                    <a href="#" onclick="onMovePage('7', '');" class="btn_class_add3" target="_self">▶ more</a>
		                                </div>
		                            </div>
		
		                            <div class="con_list2">
		                                <table summary="<spring:message code='SUMMARY.HELPDESK.NOTICE.LIST.TABLE' />">
			                                <caption><spring:message code="TITLE.HELPDESK.NOTICE" /></caption>
			                                <colgroup>
				                                <col width="*" />
				                                <col width="20%" />
			                                </colgroup>
			                                <thead>
			                                    <tr>
			                                        <th scope="col"><spring:message code="LBL.TITLE" /></th>
			                                        <th scope="col"><spring:message code="LBL.REGDATE" /></th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<c:forEach var="list" items="${noticeList}">
				                                    <tr>
				                                        <td class="al pl20"><a href="#" onclick="onMovePage('8', '<c:out value="${list.buSeqNo}" />')"><c:out value="${list.titNm}" /></a></td>
				                                        <td><c:out value="${list.wrtDttm}" /></td>
				                                    </tr>
			                                    </c:forEach>
			                                </tbody>
		                                </table>
		                            </div>
		                        </div>
	                            <!--오른쪽-->
	                        </td>
	                    </tr>
	                </tbody>
                </table>
            </div>
            <!--내용-->

            </div>
        </div>

    </div>

</body>
</html>
