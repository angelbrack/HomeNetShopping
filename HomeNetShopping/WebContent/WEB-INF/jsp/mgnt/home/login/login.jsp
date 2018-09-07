<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>

<link rel="stylesheet" href="<c:out value="/html/script/bootstrap/css/bootstrap.min.css" />" /><!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="<c:out value="/html/script/bootstrap/css/font-awesome.min.css" />"><!-- Font Awesome -->
<link rel="stylesheet" href="<c:out value="/html/script/bootstrap/css/ionicons.min.css" />"><!-- Ionicons -->
<link rel="stylesheet" href="<c:out value="/html/script/bootstrap/css/dist/AdminLTE.css" />"><!-- Theme style -->
<link rel="stylesheet" href="<c:out value="/html/script/bootstrap/css/plugins/blue.css" />"><!-- iCheck -->
<script>
<!--
	var CONTEXT_PATH = "<%= request.getContextPath() %>";
	var domainUrl = document.domain;
	
	//현재페이지 단독페이지 여부판단
	try {
		if(top != self) {
			top.document.location = self.document.location;
		} else if(opener) { //popup창에서 호출된경우
			opener.top.document.location = self.document.location;
			self.close();
		}
	} catch(ex) {}
	
	function doLogin() {
	    var frm1 = document.form1;
	    
	    if(field_check()) {
	        frm1.submit();
	    }
	}

	function field_check() {
	    var frm1 = document.form1;

	    var id = frm1.loginId.value;
	    var pw = frm1.loginPwd.value;

	    if(id == null || id == "") {
	        alert("아이디를 입력하세요.");
	        frm1.loginId.focus();
	        return false;
	    } 
	    
	    if(id.length > 30) {
	        alert("아이디는 30자이상 입력할수 없습니다.");
	        frm1.loginId.focus();
	        return false;
	    } 

	    if(pw == null || pw == "") {
	        alert("비밀번호를 입력하세요.");
	        frm1.loginPwd.focus();
	        return false;
	    } 
	    
	    if(pw.length > 30) {
	        alert("비밀번호는 30자이상 입력할수 없습니다.");
	        frm1.loginPwd.focus();
	        return false;
	    } 
	    
	    return true;
	}

	function checkEnter(e) {        
	    if(e.keyCode == 13) {           
	        doLogin();
	    }
	}       

	function onEnter(e) {   
	    var frm1 = document.form1;

	    if(e.keyCode == 13) {           
	        frm1.loginPwd.focus();
	    }
	}       
//-->
</script>

<body>

    <div class="login-box">
        <form name="form1" method="post" action="/mgnt/login/loginAction.do" target="_self">
		    <input type="hidden" name="loginDiv" id="loginDiv" value="1" />   
		    <input type="hidden" name="ajax" id="ajax" value="" />    
		    <input type="hidden" name="resultNo" id="resultNo" value="1" />
        <div class="login-logo">
            <b>고려대 경력개발시스템 관리자</b>
        </div>
        <div class="login-box-body">
            <p class="login-box-msg">Sign in to start your session</p>
            <div class="radio">
                <label>
                    <input type="radio" name="mbrDc" id="mbrDcK" value="K">고대인
                </label>
                <label>
                    <input type="radio" name="mbrDc" id="mbrDcG" value="G" checked="checked">일반관리자
                </label>
            
                
            </div>

            <div class="form-group has-feedback">
                
                <input type="text" class="form-control" placeholder="아이디" name="loginId" id="loginId" value="system" maxlength="30" autocomplete="off" >
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="Password" name="loginPwd" id="loginPwd" value="s12345" maxlength="30" autocomplete="off"> 
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">

                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" onclick="actionLogin();return false;">Sign In</button>
                </div>
                <!-- /.col -->
            </div>
            Copyrightⓒ2018 Korea University. All Rights Reserved.
        </div>
        </form>
    </div>
</body>
</html>

