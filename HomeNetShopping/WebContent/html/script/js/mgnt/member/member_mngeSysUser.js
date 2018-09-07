var url1 = "";
var url2 = "";
var url3 = "";

var frm;

function onListPage(pageNo) {
	frm = document.forms[0];
	if($("#searchWord").val().length > 200) {
		alert(message8);
		return false;
	};
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();	
}

function onEnter(e) {	
	frm = document.forms[0];
	if(e.keyCode == 13) {			
		onListPage(1);
	}
}

function onExcelPage() {
	frm = document.forms[0];
	frm.target = "_self";
	frm.action = url7;
	frm.submit();	
}
 
function onListPerLine(recordCountPerPage) {
	frm = document.forms[0];
	
	frm.recordCountPerPage.value = recordCountPerPage;
	frm.currentPage.value = 1;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();	
}

function onEditPage(userNo) {
	frm = document.forms[0];
	
    $("#userNo").val(userNo);
    
	 $("#indvInfoInqRsnCn").val(text); //개인정보사유 
	 frm.target = "_self";
	 frm.action = url2;
	 frm.submit();
}

function onViewPage(userNo) {
	frm = document.forms[0];
	
	$("#userNo").val(userNo);
	
	jPrompt('','', message3, function(text) {
		if (text == null || text.length < 1) {
			jAlert(message4, '');
			return false;
		} else {
			$("#indvInfoInqRsnCn").val(text); //개인정보사유 
			frm.target = "_self";
			frm.action = url4;
			frm.submit();
		}
	});
	
}

function onStatSaveData(eduOptrApprStatCd) {
	frm = document.forms[0];
	$("#eduOptrApprStatCd").val();
	var confirmMsg = message1;
	if(eduOptrApprStatCd == "3") {
		confirmMsg = message2;
	}
	if(confirm(confirmMsg)) {
		frm.target = "_self";
		frm.action = url3;
		frm.submit();
	}
}

function onSaveData() {
	frm = document.forms[0];
	
	if(confirm(message7)) {
		frm.target = "_self";
		frm.action = url3;
		frm.submit();
	}
}

function onSearchBlng(callBackFun) {
	try {
		var url = url5+"?callBackFun="+callBackFun;
		var opt = "width=700, height=700, status=yes,scrollbars=yes,resizable=yes";
		window.open(url, "MEMBER_BLNG_VIEW", opt);
	} catch(e) {
		alert(e);
	}
}

function onSearchBlngRtn(blngNm, blngCd) {
	$("#searchBlngNm").val(blngNm);
	$("#serachBlngCd").val(blngCd);
}

function onBlngRtn(blngNm, blngCd) {
	$("#blngNm").val(blngNm);
	$("#blngCd").val(blngCd);
}

function onPwdChange() {
	if(confirm(message9)) {
		$.ajax({
			async : false,
			type: 'POST',
			url: url6,
			data : {"userNo" : $("#userNo").val(), "email" : $("#email").val(), "userNm" : $("#userNm").val()}, 
			dataType:"json",
			success : function (r) {
				if(!r.status) {
					if(r.resultCd >= 1){
						alert(message6);
						returnVal = false;
					} else {
						alert(message5);
					}
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				alert(message5);
				returnVal = false;
			}
		});
	}
}

