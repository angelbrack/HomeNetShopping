var frm;

function onListPage(pageNo) {
	frm = document.forms[0];
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/mbr/student/studStudentList.do";
	frm.submit();	
}

function onListPerLine(pagePerLine) {
	frm = document.forms[0];
	
	frm.currentPage.value = "1";
	frm.recordCountPerPage.value = pagePerLine;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();
}

function onSearch() {
	frm = document.forms[0];
	
	if($("#searchWord").val().length > 100) {
		alert("검색어는 100자를 초과할 수 없습니다.");
		return;
	}
	frm.currentPage.value = 1;
    frm.target = "_self";
    frm.action = CTX_PATH + "/mgnt/mbr/student/studStudentList.do";
    frm.submit();
    return;
}

function onViewPage(userNo, stdId) {
	frm = document.forms[0];
	
    if (userNo != '') {
        $("#userNo").val(userNo);
    }
    $("#stdId").val(stdId);
    	
    frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/mbr/student/studStudentView.do";
	frm.submit();

}

function fnResumeViewPage(userNo, resmNo) {
	var RESUMEVIEW_POP = window.open(CTX_PATH+"/mgnt/common/popup/resmView.do?userNo="+userNo+"&resmNo="+resmNo, 'RESUMEVIEW_POP' , 'width=1360, height=600, resizable=yes, scrollbars=yes, left=0, top=0');
	RESUMEVIEW_POP.focus();   	
}