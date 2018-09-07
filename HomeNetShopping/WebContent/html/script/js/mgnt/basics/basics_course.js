var frm;

var message1 = ""; //필수 입력값입니다.
var message2 = ""; //3자 이상 입력할 수 없습니다.
var message3 = ""; //숫자만 입력가능합니다.
var message4 = ""; //10자 이상 입력할 수 없습니다.
var message5 = ""; //200자 이상 입력할 수 없습니다.
var message6 = ""; //500자 이상 입력할 수 없습니다.
var message7 = "";
var message8 = "";
var message9 = "";
var message10 = "";
var message11 = "";
var message12 = "";
var message13 = "";
var message14 = "";
var message15 = "";
var message16 = "";
var message17 = "";
var message18 = "";
var message19 = "";
var message20 = "";

var url1 = "";
var url2 = "";
var url3 = "";
var url4 = "";

function onInit() {
	if(!frm) {
		frm = document.form1;
	}
}

function onListPage(pageNo) {  
	frm.currentPage.value = pageNo;
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onListPerLine(pagePerLine) {
	frm.currentPage.value = 1;
	frm.recordCountPerPage.value = pagePerLine;
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onViewPage(perCrsId) {
	frm.perCrsId.value = perCrsId;
	frm.cmd.value = "U";
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}

function onEditPage(){
	frm.cmd.value = "I";
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}

function onPerCrsIdChg() {
	$("#checkDup").val("false");
}

function onSaveData(cmd){
	/*
	if (cmd == 'I') {
		if ($("#checkDup").val() == "false") {
			alert(message12);
			$("#perCrsId").focus();
			return false;
		}
	}
	*/
	var result = onCheckValid();
	
	var confrm = "";
	
	if(result) {
		if (cmd == 'D') {
			confrm = confirm(message14);
			frm.cmd.value = cmd;
		} else {
			confrm = confirm(message1);
		}
		if (confrm) {
			frm.action = url3;
			frm.target = "_self";
			frm.submit();	
		}
	}
}


function onCheckValid() {
//	if($("#cmd").val().trim() == "U") {
//		if($("#orgnSearchCrsMlclNo").val().trim() == $("#searchCrsMlclNo").val().trim() ||
//		$("#orgnSearchCrsHlclNo").val().trim() == $("#searchCrsHlclNo").val().trim() ||
//				$("#orgnSearchCrsMlclNo").val().trim() == $("#searchCrsMlclNo").val().trim() ||
//				$("#orgnSearchCrsClNo").val().trim() == $("#searchCrsClNo").val().trim() ) {
//			$("#changeCrsClYn").attr("value","true");
//		}
//	}
	
	/*
	if($("#perCrsId").val().trim() == "") {
		alert(message3); 
		$("#perCrsId").focus(); 
		return false;
	}
	
	if ($("#perCrsId").val().length > 5) {
		alert(message4); 
		$("#perCrsId").focus(); 
		return false;
	}
	*/
	
	if($("#perCrsNm").val().trim() == "") {
		alert(message3); 
		$("#perCrsNm").focus(); 
		return false;
	}
	
	if ($("#perCrsNm").val().length > 100) {
		alert(message5); 
		$("#perCrsNm").focus(); 
		return false;
	}
	
	if( $("#crsTypeCd").val() == "") {
		alert(message3); 
		$("#crsTypeCd").focus(); 
		return false;
	}
	
	if( $("#crsHlclNo").val() == "") {
		alert(message3); 
		$("#crsHlclNo").focus(); 
		return false;
	}
	
	if($("#perCrsCn").val().trim() == "") {
		alert(message3); 
		$("#perCrsCn").focus(); 
		return false;
	}
	
	if ($("#perCrsCn").val().length > 4000) {
		alert(message6); 
		$("#perCrsNm").focus(); 
		return false;
	}
	
	var rtnYn = "Y";
	$("#tbBlngInfo tbody tr").each(function(i) {
		if($(this).attr("id") != "delrow") {
			if($(this).find(".tmpBlngCd").val().trim() == "") {
				alert((i+1) + message16);
				$(this).find(".tmpBlngCd").focus();
				rtnYn = "N";
				return false;
			}
			
			if($(this).find(".tmpPerQuotaT").val().trim() == "") {
				alert((i+1) + message17);
				$(this).find(".tmpPerQuotaT").focus();
				rtnYn = "N";
				return false;
			}
			if($(this).find(".tmpPerQuotaT").val().length > 5) {
				alert((i+1) + message18);
				$(this).find(".tmpPerQuotaT").focus();
				rtnYn = "N";
				return false;
			}
			if(isNaN($(this).find(".tmpPerQuotaT").val()) == true) {
				alert((i+1) + message19);
				$(this).find(".tmpPerQuotaT").focus();
				rtnYn = "N";
				return false;
			}
			if($(this).find(".tmpFstApprDt").val().trim() != "" && !isValidDate($(this).find(".tmpFstApprDt").val())) {
				alert((i+1) + message20);
				$(this).find(".tmpFstApprDt").focus();
				rtnYn = "N";
				return false;
			}
		}

	});
	
	if(rtnYn == "N") {
		return false;
	}
	
	return true;
}

function onDupChk(selObj) {
	$("#tbBlngInfo tbody tr").each(function(i) {
		if($(this).find(".tmpBlngCd").attr("id") != $(selObj).attr("id")) {
			if($(this).find(".tmpBlngCd").val() != "" && selObj.value != "" && $(this).find(".tmpBlngCd").val() == selObj.value) {
				alert(message15);
				$(selObj).val("");
				return flase;
			}
		}
	});
}

function isValidDate(param) {
	var date = param.replace(/\./g,''); ;
	// 자리수가 맞지않을때
	if( isNaN(date) || date.length!=8 ) {
	    return false;
	}
	 
	var year = Number(date.substring(0, 4));
	var month = Number(date.substring(4, 6));
	var day = Number(date.substring(6, 8));
	
	if( month<1 || month>12 ) {
	    return false;
	}
	 
	var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	var maxDay = maxDaysInMonth[month-1];
	 
	// 윤년 체크
	if( month==2 && ( year%4==0 && year%100!=0 || year%400==0 ) ) {
	    maxDay = 29;
	}
	 
	if( day<=0 || day>maxDay ) {
	    return false;
	}
	return true;
}


function checkInputChar(obj) {
	var pattern = /[^(a-zA-Z0-9)]/; //영문,숫자만 허용
	if (pattern.test(document.getElementById('perCrsId').value)) {
		alert(message7);
		obj.value = '';
		obj.focus();
		return false;
	}
}

function checkDuplication() {
	if ($("#perCrsId").val()=="") {
		alert(message3);
		$("#perCrsId").focus();
		return false;
	}
	
	$.ajax({
		async : false,
		type: 'POST',
		url: url4,
		data : {"perCrsId" : $("#perCrsId").val()}, 
		dataType:"json",
		success : function (r) {
			if(!r.status) {
				if(r.result != ""){
					alert($("#perCrsId").val() + message9);
					$("#checkDup").val("false");
					$("#perCrsId").val("");
				} else {
					alert($("#perCrsId").val()+message8);
					$("#checkDup").val("true");
				}
			}
		}, 
		error: function(data, textStatus, errorThrown) {
			alert(message10);
		}
	});
}

function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
}
