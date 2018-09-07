var message1 = ""; //삭제하시겠습니까?
var message2 = ""; //저장하시겠습니까?
var message3 = ""; //필수 입력값입니다.
var message4 = ""; //300자 이상 입력할 수 없습니다.
var message5 = ""; //100자 이상 입력할 수 없습니다.
var message6 = ""; //5자 이상 입력해야 합니다.
var message7 = ""; //6자 이상 입력할 수 없습니다.
var message8 = ""; //숫자만 입력가능합니다.
var message9 = ""; //선택된 항목이 없습니다.

var url1 = "";
var url2 = "";
var url3 = "";
var url4 = "";
var url5 = "";

var msg = "";

function onListPage(pageNo) {
	var frm = document.form1;
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = url2;
	frm.submit();	
}

function onListPerLine(pagePerLine) {
	var frm = document.form1;
	
	frm.currentPage.value = 1;
	frm.recordCountPerPage.value = pagePerLine;
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}

function onViewPage(buSeqNo) {
	
	$("#buSeqNo").val(buSeqNo);
	
	document.form1.target = "_self";
	document.form1.action = url6;
	document.form1.submit();
}


function onHandlePage(cmd) {
	
	$("#cmd").val(cmd);
	
	document.form1.target = "_self";
	document.form1.action = url3;
	document.form1.submit();
}

function onSearch() {
	var frm = document.form1;

	frm.target = "_self";
	frm.action = url2;
	frm.submit();
}

function onEdit(optrPgmNo) {
	var frm = document.form1;

	if (optrPgmNo != '') {
		$("#optrPgmNo").val(optrPgmNo);
	}

	frm.target = "_self";
	frm.action = url3;
	frm.submit();
}
//
function onDelete() {
	var frm = document.form1;
	if (confirm(message1)) {
		frm.target = "_self";
		frm.action = url4;
		frm.submit();
	}
}

function onSaveData() {
	
	var frm = document.form1;
	
	if($("#cmd").val() == "I") {
		document.getElementById("currentPage").value = "1";
	}
	
	if($("#titNm").val().trim() == "") {
		alert(message7);
		frm.titNm.focus();
		return false;
	}
	
	if($("#buGrpId").val() == "") {
		alert(message9);
		frm.buGrpId.focus();
		return false;
	}
	
	oEditors.getById["buCn"].exec("UPDATE_CONTENTS_FIELD", []);
	if(document.getElementById("buCn").value.trim() == "<br>" || document.getElementById("buCn").value.trim() == "<p><br></p>"){
		alert(message8);
	    oEditors.getById["buCn"].exec("FOCUS");
	    return false;
    }
	
    if(confirm(msg)) {
        document.form1.target = "_self";
        document.form1.action = url5;
        document.form1.submit();                    
    }
}


function onDeleteEdit() {
	var frm = document.form1;
	if (confirm(message1)) {
		frm.target = "_self";
		frm.action = url4;
		frm.submit();
	}
}

function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
} 

function onSearchPage() {
	
	document.form1.currentPage.value="1";
	
	var str = document.getElementById("searchWord");	
	var blank_patten = /[\s]/g;
	if(blank_patten.test(str.value) == true) {
		alert(message5);
		document.getElementById("searchWord").value="";
		return true;
	}
	var special_patten = /[~!@#$%^&*()|\\\'\";:\/?]/gi;
	if(special_patten.test(str.value) == true) {
		alert(message6);
		document.getElementById("searchWord").value="";
		return true;
	}
	
	document.form1.target = "_self";
	document.form1.action = url2;
	document.form1.submit();
}

function onDeleteData(cmd) {
	$("#cmd").val("D");
	if (confirm(message1)) {
		document.form1.target = "_self";
		document.form1.action = url4;
		document.form1.submit();
	}	
}

