<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
/*
"sStdId"             //교직원번호
"sUserid"           //포털아이디
"sName"            //이름
"dept_Nm",         //부서명
"dept_Cd",         //부서코드
*/
String strMsg = request.getParameter("msg") ;
strMsg = "aubx9KafaHR+G27aXq2JW4GW+Sp7Mj+3bkprOZBLDkbhRrUKUhjlN1lY6+SxhKMo1p4CP338GDoyMavBtVPtactH58OEjKZIwKNfbgJOdyIkc8u7iFhCEA==a.HDFHeggciJeciFie" ;

%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<script type="text/javascript" src="/html/script/js/lib/jquery-1.11.0.min.js"></script>

</head>
<body>
<form name="form1" action="/user/login/ssoLoginAction.do" >
<input type="hidden" name="ssoMsg" id="ssoMsg" value="<%= strMsg %>" />

</form>
<script type="text/javascript">
$(document).ready(function()
{
    var f = document.form1 ;
    f.action = "/user/login/ssoLoginAction.do"
    f.target = "_self" ;
    f.submit();
});
</script>  
</body>
</html>

