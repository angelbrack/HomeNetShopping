var url1 = "";	// list
var url2 = "";	// handle
var url3 = "";	// excel list
var url4 = "";	// save
var url5 = "";	// search
var url6 = "";	// address_search
var url7 = "";	// pwd change
var url8 = "";	// blngsearch
var url11 = "";	// blngsearch

var message1 = ""; //\uc218\uc815\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message2 = ""; //\uc218\uc815\uc774 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4.
var message3 = ""; //\uc815\uc0c1\uc801\uc73c\ub85c \uc218\uc815\ub418\uc5c8\uc2b5\ub2c8\ub2e4.
var message4 = ""; //\ube44\ubc00\ubc88\ud638\ub97c \ucd08\uae30\ud654 \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message5 = ""; //\uac1c\uc778\uc815\ubcf4\uc870\ud68c \uc0ac\uc720\uc785\ub825
var message6 = ""; //\uac1c\uc778\uc815\ubcf4\uc870\ud68c \uc0ac\uc720\ub97c \uc785\ub825\ud558\uc138\uc694.
         
var message38;
var message39;
var message40;
var message41;
 
var frm;

function onInit() {
	if(!frm) {
		frm = document.form1;
	}
}
function onSetting() {
	if($("input[name=frnYn]:checked").val()=="Y"){
		$("#zipCd").prop('readonly',false);
		$("#pnoAddr").prop('readonly',false);
	} else {
		$("#zipCd").prop('readonly',true);
		$("#pnoAddr").prop('readonly',true);
	}
	$("input[name=frnYn]").change(function(){
		if($("input[name=frnYn]:checked").val()=="Y"){
			$("#zipCd").prop('readonly',false);
			$("#pnoAddr").prop('readonly',false);
		} else {
			$("#zipCd").prop('readonly',true);
			$("#pnoAddr").prop('readonly',true);
		}
	});
}


function onListPage(pageNo) {  
	frm.currentPage.value = pageNo;
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onApplyListPage(pageNo) {  
	frm.applyCurrentPage.value = pageNo;
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}

function onApplyListPerLine(pagePerLine) {
	frm.applyCurrentPage.value = 1;
	frm.applyRecordCountPerPage.value = pagePerLine;
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}


function onSearch() {  
	frm.currentPage.value = 1;
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

function onEditPage(userNo) {
	frm.userNo.value = userNo;
	
	jPrompt('','', message5, function(text) {
		if (text == null || text.length < 1) {
			jAlert(message6, '');
			return false;
		} else {
			$("#indvInfoInqRsnCn").val(text); //\uac1c\uc778\uc815\ubcf4\uc0ac\uc720 
			frm.action = url2;
			frm.target = "_self";
			frm.submit();
		}
	});
	
}

function onExcelDownload() {
	frm.action = url3;
	frm.target = "_self";
	frm.submit();
}

function onZipPopup(callBackFun) {
	try {
		var url = url6+"?callBackFun="+callBackFun;
		var opt = "width=700, height=600, status=yes, scrollbars=yes, resizable=yes";
		var win = window.open(url, "_popup", opt);
		win.focus();
	} catch(e) {
		alert(e);
	}
}

function onZipRtn(zipCd, pnoAddr) {
	$("#zipCd").val(zipCd);
	$("#pnoAddr").val(pnoAddr);
}

/* \uc18c\uc18d\uae30\uad00 \ud31d\uc5c5\ud638\ucd9c */
function onBlngSearchPopup(callBackFun) {
	try {
		var url = url8;
		url += "?callBackFun="+callBackFun;
		var opt = "width=700, height=700, status=yes,scrollbars=yes,resizable=yes";
		var win = window.open(url, "MEMBER_BLNG_VIEW", opt);
		win.focus();
	} catch(e) {
		alert(e);
	}
}

/* \uc18c\uc18d\uae30\uad00 \ub9ac\ud134\ud568\uc218 */
function onSetBlng(blngNm, blngCd) {
	$("#blngNm").val(blngNm);
	$("#blngCd").val(blngCd);
}

/* search \uc18c\uc18d\uae30\uad00 \ub9ac\ud134\ud568\uc218 */
function onSearchSetBlng(blngNm, blngCd) {
	$("#searchBlngNm").val(blngNm);
	$("#searchBlngCd").val(blngCd);
}

function onSetMemJoin(blngNm, blngCd) {
	if (opener) {
		if($("#callBackFun").val() != "") {
			eval("var callbackfun = opener."+$("#callBackFun").val());
			callbackfun(blngNm, blngCd);
		} else {
			opener.onSettingMemJoin(blngNm, blngCd);
		}
		self.close();
	} else {
		self.close();
	}
}

function onPwdChange() {
	if(confirm(message4)) {
		frm.userNo.value = $("#userNo").val();
		frm.action = url7;
		frm.target = "_self";
		frm.submit();
	}
}

function onMemJoinListPage(pageNo) {
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = url5;
	frm.submit();	
}

function onMemJoinSearch() {
	frm.target = "_self";
	frm.action = url5;
	frm.submit();
}

function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
}

function onTelPress(event, type) {
	if(type == "numbers") {
		if((event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 45) return false;
	}
}

function onSaveData(){
	
	$("#form1").validate({
		rules: {
			userNm:{ required:true, maxlength:50 },
			engUserNm:{ maxlength:100 },
			birthday:{ required:true, number:true, date:true, minlength:8, maxlength:8 },
			nattyCd:{ required:true, maxlength:10 },
			psprNo:{ number:true, maxlength:50 },
			zipCd:{ required:true, maxlength:20 },
			pnoAddr:{ required:true, maxlength:500 },
			bpnoAddr:{ required:true, maxlength:500 },
			telNo1:{ rangelength:[2,4], number:true },
			telNo2:{ rangelength:[3,4], number:true },
			telNo3:{ rangelength:[3,4], number:true },
			hpNo1:{ required:true, rangelength:[3,4], number:true },
			hpNo2:{ required:true, rangelength:[3,4], number:true },
			hpNo3:{ required:true, rangelength:[3,4], number:true },
			email:{ required:true, email: true, maxlength:40 },
			blngNm:{ maxlength:100 },
			deptNm:{ maxlength:100 },
			psclNm:{ maxlength:100 }
		},
		submitHandler: function(form) {
			if (confirm(message1)) {
				form.submit();
			}
		}
	});
	
	$("#form1").attr("action", url4);
	$("#form1").submit();
}


/*
	required: "\ud544\uc218 \ud56d\ubaa9\uc785\ub2c8\ub2e4.",
	remote: "\ud56d\ubaa9\uc744 \uc218\uc815\ud558\uc138\uc694.",
	email: "\uc720\ud6a8\ud558\uc9c0 \uc54a\uc740 E-Mail\uc8fc\uc18c\uc785\ub2c8\ub2e4.",
	url: "\uc720\ud6a8\ud558\uc9c0 \uc54a\uc740 URL\uc785\ub2c8\ub2e4.",
	date: "\uc62c\ubc14\ub978 \ub0a0\uc9dc\ub97c \uc785\ub825\ud558\uc138\uc694.",
	dateISO: "\uc62c\ubc14\ub978 \ub0a0\uc9dc(ISO)\ub97c \uc785\ub825\ud558\uc138\uc694.",
	number: "\uc720\ud6a8\ud55c \uc22b\uc790\uac00 \uc544\ub2d9\ub2c8\ub2e4.",
	digits: "\uc22b\uc790\ub9cc \uc785\ub825 \uac00\ub2a5\ud569\ub2c8\ub2e4.",
	creditcard: "\uc2e0\uc6a9\uce74\ub4dc \ubc88\ud638\uac00 \ubc14\ub974\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.",
	equalTo: "\uac19\uc740 \uac12\uc744 \ub2e4\uc2dc \uc785\ub825\ud558\uc138\uc694.",
	extension: "\uc62c\ubc14\ub978 \ud655\uc7a5\uc790\uac00 \uc544\ub2d9\ub2c8\ub2e4.",
	maxlength: $.validator.format("{0}\uc790\ub97c \ub118\uc744 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. "),
	minlength: $.validator.format("{0}\uc790 \uc774\uc0c1 \uc785\ub825\ud558\uc138\uc694."),
	rangelength: $.validator.format("\ubb38\uc790 \uae38\uc774\uac00 {0} \uc5d0\uc11c {1} \uc0ac\uc774\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694."),
	range: $.validator.format("{0} \uc5d0\uc11c {1} \uc0ac\uc774\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694."),
	max: $.validator.format("{0} \uc774\ud558\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694."),
	min: $.validator.format("{0} \uc774\uc0c1\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694.")
*/


function onSearchMemCert() {
	try {
		url = url11;
		var opt = "width=700, height=700, status=yes,scrollbars=yes,resizable=yes";
		window.open(url, "MEMBER_CERT_LIST", opt);
	} catch(e) {
		alert(e);
	}
}

function onAddCert() {
	if($("input[type='checkbox']:checked").length == 0) {
		alert(message40);
		return false;
	}
	
	var rowNo = opener.$("#tbCertInfo tbody tr").length;
	if(rowNo == 1 && opener.$("#tbCertInfo tbody tr:eq(0)").attr("id") == "delrow") {
		opener.$("#tbCertInfo tbody tr:eq(0)").remove();
	}
	rowNo = opener.$("#tbCertInfo tbody tr").length;
	var rowTrHtml = "";
	$("input[type='checkbox']:checked").each(function() {
		
		rowTrHtml += "<tr>";
		rowTrHtml += "	<td class=\"al pl20\">";
		rowTrHtml += "		<input type=\"hidden\" name=\"certCds\" id=\"certCds"+(rowNo+1)+"\" value=\""+$("#cdInsV"+this.value).val()+"\" />";
		rowTrHtml += $("#cdInsNm"+this.value).val();
		rowTrHtml += "	</td>";
		rowTrHtml += "	<td><a href=\"#none\" onclick=\"onDelRow(this)\" class=\"btn_class_pass1\">"+message38+"</a></td>";
		rowTrHtml += "</tr>";
		
		rowNo++;
	});
	opener.$("#tbCertInfo").append(rowTrHtml);
	self.close();
}

function onDelRow(obj){
	$(obj).parent().parent().remove();
	var rowNo = $("#tbCertInfo tbody tr").length;
	if(rowNo == 0) {
		$("#tbCertInfo").append("<tr id=\"delrow\"><td colspan=\"2\">"+message39+"</td></tr>");
	}
}

function onAddChk(obj){
	if($(obj).prop("checked") == true) {
		opener.$("input[name=certCds]").each(function() {
			if(this.value == $("#cdInsV"+obj.value).val()) {
				alert(message41);
				$(obj).prop("checked", false);
			}
		});
	}
}


