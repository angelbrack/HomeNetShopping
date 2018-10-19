<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="prjframework.common.util.WebUtil"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<%--
	String fileName = "고려대관리자 메뉴구조도 - 복사본.xlsx";
	out.println("fileName_1=["+WebUtil.encodeURIComponent(fileName)+"]");
--%>

<%-- <jsp:forward page="/portal/home/mainIntroAction.do" />--%>

<a href="/mgnt/" target="_self">관리자</a> <br/> <br/>


<a href="/user/index/home/homeMain.do" target="_self">사용자메인</a> <br/><br/>

<a href="/mobile/index/home/homeMain.do" target="_self">모바일메인</a> <br/><br/>


<a href="/user/login/login/initLogin.do" target="_self">사용자로그인</a><br/><br/>


<a href="/design/index.jsp" target="_self">사용자디자인</a> <br/><br/>

<a href="/design/mobile/www/apps/main/default.jsp" target="_self">모바일디자인</a> <br/><br/>


SSO연계 <br/><br/>

 <br/>

<a href="#" onclick="fnArtc();">품목조회</a> <br/><br/>

<form name="form1" id="form1" method="post" action="">
</form>

<script>
function fnArtc() {
	/* var frm = document.form1;
	
	frm.method = "post";
	frm.target = "_self";
	frm.action = "https://msecure.e-himart.co.kr/app/order/get/list/goods/article/ajax";
	
	frm.submit(); */
	
	var data = {
			artcDpthNo : '1'
	}
		
	
	var param	= JSON.stringify(data); 
	console.log(param);
	
	$.ajax({
		async : false,
		type: 'POST',
		url: CTX_PATH + "/mgnt/article/initArticleSaveList.json",
		data: null,
		contentType: 'application/json',
		dataType:"json",
		success : function (data) {
			console.log("data=["+data+"]");
			
			var resultMsg		= data.resultMsg;
			var completeYn		= data.completeYn;
			
			alert(resultMsg);
			if ( completeYn == "Y" ) {
				
			}
			
		}, 
		error: function(data, textStatus, errorThrown) {
			fnAjaxError(data);
		}
	});
}
</script>