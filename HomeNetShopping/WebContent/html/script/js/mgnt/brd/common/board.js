var listUrl 	= "";
var handleUrl 	= "";

var msg = "";

function fnFileDelete(obj) {
	$(obj).parent().remove();
}

function fnListPage(pageNo) {
	var frm = document.form1;
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = listUrl;
	frm.submit();	
}

function fnListPerLine(pagePerLine) {
	var frm = document.form1;
	
	frm.currentPage.value = 1;
	frm.recordCountPerPage.value = pagePerLine;
	frm.action = listUrl;
	frm.target = "_self";
	frm.submit();
}

function fnViewPage(bdotSeq) {
	
	$("#bdotSeq").val(bdotSeq);
	$("#cmd").val("U");
	
	document.form1.target = "_self";
	document.form1.action = handleUrl;
	document.form1.submit();
}


function fnHandlePage(cmd, bdotSeq, ansYn) {
	var newAnsYn = "";
	
	if(ansYn == "답변완료") {
		newAnsYn = "Y";
	} else if(ansYn == "처리중") {
		newAnsYn = "S";
	} else if(ansYn == "접수완료") {
		newAnsYn = "N";
	}
	
	$("#cmd").val(cmd);
	$("#bdotSeq").val(bdotSeq);
	$("#ansYn").val(newAnsYn);
	
	document.form1.target = "_self";
	document.form1.action = handleUrl;
	document.form1.submit();
}

function fnSearch() {
	var frm = document.form1;

	frm.target = "_self";
	frm.action = listUrl;
	frm.submit();
}

function fnEdit(optrPgmNo) {
	var frm = document.form1;

	if (optrPgmNo != '') {
		$("#optrPgmNo").val(optrPgmNo);
	}

	frm.target = "_self";
	frm.action = handleUrl;
	frm.submit();
}

// 저장
function fnSaveData() {
	
	if($("#cmd").val() == "I") {
		document.getElementById("currentPage").value = "1";
	}
	
	var selectedIndex	= $("#cls1Cd option").index($("#cls1Cd option:selected"));
	if ( selectedIndex == 0 ) {
		alert("분류를 선택해주세요.");
		//$("#cls1Cd option:eq(1)").attr("selected", "selected");
		$("#cls1Cd").focus();
		return;
	}
	
	if($("#titNm").val() == "") {
		alert("제목를 입력하세요.");
		document.form1.titNm.focus();
		return false;
	}
	
	if ( $("#blbdDvCd").val() == "001" ) {
		
		var notcSdt	= $.trim($("#notcSdt").val());
		var notcEdt	= $.trim($("#notcEdt").val());
		
		if ( notcSdt == "" ) {
			alert("시작일자를 입력해주세요.");
			$("#notcSdt").focus();
			return false;
		}
		
		if ( notcEdt == "" ) {
			alert("종료일자를 입력해주세요.");
			$("#notcEdt").focus();
			return false;
		}
		
	}
	
	oEditors.getById["bdotCn"].exec("UPDATE_CONTENTS_FIELD", []);
	if(document.getElementById("bdotCn").value.trim() == "<br>" || document.getElementById("bdotCn").value.trim() == "<p><br></p>"){
		alert("내용를 입력하세요.");
	    oEditors.getById["bdotCn"].exec("FOCUS");
	    return false;
    }

    /*var addFileList = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList == ""){
   				addFileList = $(this).val();
   			}else{
   				addFileList += ","+$(this).val();
   			}
   		}
   	});
   	
   	$("#addFileList").val(addFileList);*/
     
    if(confirm(msg)) {
        document.form1.target = "_self";
        document.form1.action = CTX_PATH + "/mgnt/brd/common/saveBoard.do";
        document.form1.submit();
    }
}

// 답변저장
function fnAnsSaveData() {
	
	if($("#cmd").val() == "I") {
		document.getElementById("currentPage").value = "1";
	}
	
	oEditors.getById["bdotCn"].exec("UPDATE_CONTENTS_FIELD", []);
	if(document.getElementById("bdotCn").value.trim() == "<br>" || document.getElementById("bdotCn").value.trim() == "<p><br></p>"){
		alert("답변내용을 입력하세요.");
	    oEditors.getById["bdotCn"].exec("FOCUS");
	    return false;
    }

    /*var addFileList = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList == ""){
   				addFileList = $(this).val();
   			}else{
   				addFileList += ","+$(this).val();
   			}
   		}
   	});
   	
   	$("#addFileList2").val(addFileList);*/

     
    if(confirm(msg)) {
        document.form1.target = "_self";
        document.form1.action = CTX_PATH + "/mgnt/brd/common/saveBoard.do";
        document.form1.submit();
    }
}


function fnDeleteEdit() {
	var frm = document.form1;
	if (confirm("삭제하시겠습니까?")) {
		frm.target = "_self";
		frm.action = url4;
		frm.submit();
	}
}

function fnPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
} 

function fnSearchPage() {
	
	document.form1.currentPage.value="1";
	
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
	
	document.form1.target = "_self";
	document.form1.action = listUrl;
	document.form1.submit();
}

function fnDeleteData() {
	if (confirm("삭제하시겠습니까?")) {
		document.form1.target = "_self";
		document.form1.action = CTX_PATH + "/mgnt/brd/common/deleteBoard.do";
		document.form1.submit();
	}	
}

