<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctag" uri="/WEB-INF/tld/ctag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>고려대학교 경력개발시스템</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<ctag:conf key="THEME.PATH" />support/css/ts.css" />
<script type="text/javascript">
	function onErrCofirm() {
		if(top.location.href.indexOf('mgnt') > -1) {
			top.location.href = "/mgnt/home/menuMainAction.do";
		} else if(top.location.href.indexOf('support') > -1) {
			top.location.href = "/support/home/menuMainAction.do";
		} else {
			top.location.href = "/";
		}
	}
</script>
</head>

<body class="bg2">

<div class="con_error">
	<div class="con_error_c">

		<p class="op1">
		불편을 드려 죄송합니다.<br />
		요청하신 페이지오류가 발생하였습니다.
		</p>

		<p class="op2">
		요청하신 페이지오류가 발생하였습니다.<br />
		입력하신 페이지 주소가 정확한지 다시 한번 확인해보시기 바랍니다.<br /><br />

		동일한 문제가 지속적으로 발생할 경우에 해당 <span>서비스 관리자</span>에게 문의해 주십시요.
		</p>
<%-- 	request: ${requestScope['javax.servlet.forward.servlet_path']} --%>
<%-- 	referer: ${header.referer} --%>
	</div>
	
	<div class="con_error_btn">
		<a href="javascript:onErrCofirm();" class="btn_prev_01">확인</a>
	</div>

</div>

</body>
</html>

