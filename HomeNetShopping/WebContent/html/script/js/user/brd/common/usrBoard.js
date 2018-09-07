function fnSearchCls1Cd(searchCls1Cd) {
		
	$("#searchCls1Cd").val(searchCls1Cd);
	
	document.form1.target = "_self";
	document.form1.action = $("#form1 #listUrl").val();
	document.form1.submit();
}

function fnView(bdotSeq) {
	var frm = document.form1;
	
	$("#form1 #bdotSeq").val(bdotSeq);
	
	frm.target = "_self";
	frm.action = $("#form1 #viewUrl").val();
	frm.submit();
}

function fnSearch() {
	var frm = document.form1;
	frm.pageIndex.value="1";
	
	var str = document.getElementById("searchWord");	
	var blank_patten = /[\s]/g;
	if(blank_patten.test(str.value) == true) {
		alert("공백문자는 허용되지 않습니다.");
		document.getElementById("searchWord").value="";
		return true;		
	}
	var special_patten = /[~!@#$%^&*()|\\\'\";:\/?]/gi;
	if(special_patten.test(str.value) == true) {
		alert("특수문자는 허용되지 않습니다.");
		document.getElementById("searchWord").value="";
		return true;		
	}
	
	frm.target = "_self";
	frm.action = $("#form1 #listUrl").val();
	frm.submit();
}

function fnListPage(pageNo) {
	var frm = document.form1;
	frm.pageIndex.value = pageNo;
	frm.target = "_self";
	frm.action = $("#form1 #listUrl").val();
	frm.submit();
}

function fnListPerLine(pagePerLine) {
	var frm = document.form1;
	
	frm.pageIndex.value = 1;
	frm.recordCountPerPage.value = pagePerLine;
	frm.action = $("#form1 #listUrl").val();
	frm.target = "_self";
	frm.submit();
}

function fnList() {
	var frm = document.form1;
	
	frm.target = "_self";
	frm.action = $("#form1 #listUrl").val();
	frm.submit();
}

function fnQndaAdd() {
	var frm = document.form1;
	
	frm.target = "_self";
	frm.action = CTX_PATH + "/user/brd/qna/qnaHandle.do";
	frm.submit();
}