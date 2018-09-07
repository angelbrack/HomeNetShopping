var url1;
var url2;
var url3;
var url4;
var url5;
var url6;

var message1;
var message2;
var message3;
var message4;
var message5;
var message6;
var message7;


function onList() {
	var frm = document.form1;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();
}

function onEdit(cmd, optrAuthNo) {
	var frm = document.form1;

	if (typeof optrAuthNo != 'undefined') {
		$("#optrAuthNo"+optrAuthNo).attr("checked", "checked");
	}
	
	frm.target = "_self";
	frm.action = url2+"?cmd="+cmd;
	frm.submit();	
}
	
function onSave() {
	var frm = document.form1;
	var optrAuthNm;
	var chkFlag = true;
	
	var optrPgmNo = [];
	$("#treeDiv").jstree("get_checked", null, true).each(function(){
		if (this.value != '0') {
			optrPgmNo.push(this.value);
		}
	});
		
	$("#optrPgmNo").val(optrPgmNo);
	
	$("input[name=optrAuthNm]").each(function () {
		optrAuthNm = $(this).val();
		
		if (optrAuthNm == '') {
			alert(message1);
			$(this).focus();
			chkFlag = false;
			return false;
		}
	});
	
	if (!chkFlag) {
		return;
	}

	if (!$("input[name=useYn]").is(":checked")) {
		alert(message2);
		return;
	}

	if (confirm(message7)) {
		frm.target = "_self";
		frm.action = url3;
		frm.submit();	
	}
}

function onCopy() {
	var frm = document.form1;
	
	var optrAuthNo = $(":radio[name=optrAuthNo]:checked").val();

	if (typeof(optrAuthNo) == "undefined") {
		alert(message3);
		return;
	}
	
	if (optrAuthNo != '') {
		if (confirm(message4)) {
			frm.target = "_self";
			frm.action = url5;
			frm.submit();	
		}
	}
}

function onDelete() {
	var frm = document.form1;

	var optrAuthNo = $(":radio[name=optrAuthNo]:checked").val();

	if (typeof(optrAuthNo) == "undefined") {
		alert(message5);
		return;
	}
	
	if (optrAuthNo != '') {
		if (confirm(message6)) {
			frm.target = "_self";
			frm.action = url4;
			frm.submit();	
		}
	}
}

function doSelectMenu(menuNo) {
}

function onSaveButtonAuth() {
	var frm = document.form1;
	
	var chkReadAuthYn = "";
	var chkWrdAuthYn = "";
	var chkDelAuthYn = "";
	
	
	var checkIdx = document.getElementsByName("checkIdx");
    for(var i = 0; i < checkIdx.length; i++) {
		
		if (i > 0) {
			if (chkReadAuthYn.length > 0) { chkReadAuthYn = chkReadAuthYn + ","; }
			if (chkWrdAuthYn.length > 0) { chkWrdAuthYn = chkWrdAuthYn + ","; }
			if (chkDelAuthYn.length > 0) { chkDelAuthYn = chkDelAuthYn + ","; }
		}
		
		if($("input:checkbox[name=chkReadAuthYn]").eq(i).is(":checked") == true) {
			chkReadAuthYn = chkReadAuthYn + "Y";
		} else {
			chkReadAuthYn = chkReadAuthYn + "N";
		}
		
		if($("input:checkbox[name=chkWrdAuthYn]").eq(i).is(":checked") == true) {
			chkWrdAuthYn = chkWrdAuthYn + "Y";
		} else {
			chkWrdAuthYn = chkWrdAuthYn + "N";
		}
		
		if($("input:checkbox[name=chkDelAuthYn]").eq(i).is(":checked") == true) {
			chkDelAuthYn = chkDelAuthYn + "Y";
		} else {
			chkDelAuthYn = chkDelAuthYn + "N";
		}
		
	}
    
	frm.readAuthYn.value = chkReadAuthYn;
	frm.wrAuthYn.value = chkWrdAuthYn;
	frm.delAuthYn.value = chkDelAuthYn;
	
	frm.target = "_self";
	frm.action = url6;
	frm.submit();
}