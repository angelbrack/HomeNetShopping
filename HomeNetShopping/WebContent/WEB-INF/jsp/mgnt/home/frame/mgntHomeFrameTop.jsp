<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>

<script type="text/javascript" src="<ctag:conf key="JS.PATH" />site/frame/frame_menu_top.js"></script>

<script type="text/javascript">
function goMgntMain() {
    parent.document.getElementById("mainFrame").cols = "20,*" ;
    parent.document.getElementById("bodyFrame").src="/mgnt/home/menuBodyAction.do";
}
function goMgntTopMenu(url) {
    parent.document.getElementById("mainFrame").cols = "217,*" ;
    parent.document.getElementById("bodyFrame").src = url;
}
</script>

<script type="text/javascript">
    var _CONTEXT_ROOT = "";
	_CURRENT_MENU_NO = "<c:out value='${ currentMenuNo }' />";
	_PATH_TOP = "<c:url value='/mgnt/home/menuTopAction.do'/>";
	_PATH_LEFT = "<c:url value='/mgnt/home/menuLeftAction.do'/>";
	_PATH_LOGOUT = "<c:url value='/mgnt/home/logoutAction.do'/>";
	
    function onChgRole(roleId){
    	try {
	        if(roleId){
	            top.document.location = "/mgnt/home/menuChangeRoleAction.do?roleId="+roleId;
	        }
    	} catch(e) {
    		alert(e);
    	}
    }	
    function fnLeftHidden(){
    	if(parent.bottomFrame.cols=="0,*"){
    		parent.bottomFrame.cols="190,*";
    	}else{
    		parent.bottomFrame.cols="0,*";
    	}
    }
</script>

<body>
  
<form name="form" method="post" action="<c:url value='/mgnt/home/menuTopAction.do'/>">
    <input type="hidden" id="currentMenuNo" name="currentMenuNo" value="<c:out value='${ currentMenuNo }' />" />
    
<div id="wrap">


	<div id="skip">
		<a href="#header">메인메뉴로 바로가기</a>
		<a href="#con">본문으로 바로가기</a>
		<a href="#footer">하단으로 바로가기</a>
	</div>

	<div id="header">
		<div class="header_c">
			<div class="logo">
				<ul>
					<li><a href="#none" onclick="goMgntMain();"><img src="/theme/00001/ko/user/images/common/logo.png" alt="고려대로고"/></a></li>
				</ul>
			</div>
			<div class="gnb">
				<ul>
					<li class="op1 mr15"><span class="fwb"><c:out value="${sessionScope.UserLoginVO.userNm}" /></span>님 환영합니다.</li>
					<fmt:parseDate var="loginTime" value="${sessionScope.UserLoginVO.loginTime}" pattern="yyyyMMddHHmmss"/>
					<li class="op2 mr15">접속시간 : <fmt:formatDate value="${loginTime}" pattern="yyyy.MM.dd HH:mm:ss"/></li>
					<li class="mr10"><a href="#none" class="black3" onclick="logout()">Logout</a></li>
					<li class="last">
						<label for="roleList" class="hidden">사용자권한</label>
						<select name="roleList" id="roleList" style="width:130px;height:22px;" onchange="onChgRole(this.value);">
							<c:forEach items="${sessionScope.roleList}" var="roleList" varStatus="count">
							<option value="<c:out value="${roleList['optrAuthNo']}" />" <c:if test="${sessionScope.optrAuthNo == roleList['optrAuthNo']}">selected=\"selected\"</c:if>><c:out value="${roleList['optrAuthNm']}" /></option>
							</c:forEach>
						</select>						
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div id="lnb">
		<div class="lnb_c">
			<ul>
				<c:forEach items="${ list }" var="item" varStatus="status">
                    <li id="menuLi${ item.menuNo }">
                        <a href="#topMenu" <c:if test="${ currentMenuNo eq item.menuNo }">class="on"</c:if> onclick="doChangeMenu('<c:out value="${ item.menuNo }"/>', '<c:out value="${ status.count }" />'); return false;"><c:out value="${ item.menuNm }"/></a>
                    </li>
                </c:forEach>
			</ul>
		</div>
	</div>

</div>

</form>

</body>
</html>
