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
	 	alert(url);
		parent.document.getElementById("mainFrame").cols = "217,*" ;
	    parent.document.getElementById("leftFrame").src = _PATH_LEFT+"?menuSelYn=Y&currentMenuNo="+currentMenuNo;
	    parent.document.getElementById("bodyFrame").src = url;
	}
//-->
</script>
<body>

<div id="wrap">
메인 서머리 위치
</div>

</body>
</html>
