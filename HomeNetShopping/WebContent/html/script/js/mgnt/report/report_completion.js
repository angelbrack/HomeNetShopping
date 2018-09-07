var frm;

var message1 = ""; //승인
var message2 = ""; //기각
var message3 = ""; //필수입력
var message10 = "";
var message11 = "";

var url1 = "";
var url2 = "";
var url3 = "";

var imgCalPath="";

function onInit() {
	if(!frm) {
		frm = document.form1;
	}
}

function onCalSetting() {
	$("#searchRptDtSt").datepicker({dateFormat:'yymmdd',showOn:'button',buttonImageOnly:true,buttonImage:imgCalPath,prevText:'이전 달',nextText:'다음 달',monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],dayNames:['일','월','화','수','목','금','토'],dayNamesShort:['일','월','화','수','목','금','토'],dayNamesMin:['일','월','화','수','목','금','토'],showMonthAfterYear:true,yearSuffix:'년',onSelect:function(date){$("#searchRptDtEnd").datepicker("option", "minDate", date);}});
	$("#searchRptDtEnd").datepicker({dateFormat:'yymmdd',showOn:'button',buttonImageOnly:true,buttonImage:imgCalPath,prevText:'이전 달',nextText:'다음 달',monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],dayNames:['일','월','화','수','목','금','토'],dayNamesShort:['일','월','화','수','목','금','토'],dayNamesMin:['일','월','화','수','목','금','토'],showMonthAfterYear:true,yearSuffix:'년',minDate:$('#searchRptDtSt').val()});
}

function onListPage(pageNo) {  
	frm.currentPage.value = pageNo;
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onSearch() {
	
	if ($('#searchRptDtSt').val() == "" && $('#searchRptDtEnd').val() != "") {
		alert(message4);
		$('#searchRptDtSt').focus();
		return false;
	}
	
	if ($('#searchRptDtSt').val() != "" && $('#searchRptDtEnd').val() == "") {
		alert(message5);
		$('#searchRptDtEnd').focus();
		return false;
	}
	frm.currentPage.value = 1;
	frm = document.form1;
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onExcelPage(parm) {
	frm = document.form1;
	if (parm == "1") {
		frm.action = url4;
	} else if (parm == "2") {
		frm.action = url5;
	}
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

function onViewPage(crsSessId, rptNo) {
	frm.crsSessId.value = crsSessId;
	frm.rptNo.value = rptNo;
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}

function onRejectReasonInputPopup () {
	try {
		var url = url6;
		var opt = "width=700, height=350, status=yes,scrollbars=yes,resizable=yes";
		window.open(url, "REJECT_INPUT", opt);
	} catch(e) {
		alert(e);
	}
}

function onSaveProcRsn() {
	if ($("#rptProcRsn").val() == "") {
		$("#rptProcRsn").focus();
		return false;
	}
	if (opener) {
		opener.onSaveData("C",$("#rptProcRsn").val());
		self.close();
	} else {
		self.close();
	}
}

function onSaveData(cmd, rptProcRsn){
	
	var confrm = "";
	$("#cmd").val(cmd);
	if (cmd == "A") {
		confrm = confirm(message1);
	} else if (cmd == "C") {
		$("#rptProcRsn").val(rptProcRsn);
		confrm = confirm(message2);
	}
	
	if (confrm) {
		frm.action = url3;
		frm.target = "_self";
		frm.submit();	
	}
}

function onDelete() {
	if (!$("input[name=rowIds]").is(":checked")) {
		alert(message13);
		return;
	}
	var frm = document.form1;
	if (confirm(message12)) {
		frm.target = "_self";
		frm.action = url7;
		frm.submit();
	}
}

$(function() {	
	$('#fileupload').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.COMPLETION.REPORT&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" /> ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">\uc0ad\uc81c\u0097</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload").val(""); 
				$("#fileupload").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress").hide();

			});
		},
		progressall: function (e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress .bar').css('width', progress + '%');
			$('#progress .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress").hide();
				$(".filename").text("");
				$("#fileupload").val(""); 
				$("#fileupload").attr("readonly", false);
				$(".data-loading").hide();
			}
		},
		error: function(request,status,error){
			if($.browser.msie && $.browser.version <= 9){
			} else {
				alert(request.responseText);
			}
			return false;
		}
	}).bind('fileuploadsubmit', function (e, data) {
		/* \ud30c\uc77c \ud55c\uac1c\ub9cc \uc62c\ub9b4\ub54c */
		if($("[name=fileGubun]").size()>2) {
			alert(message4);
			return false;
		} 
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload").attr("readonly", true);
 			$(".data-loading").show();
 		}
		$('#progress .bar').css('width',0 + '%');
		$('#progress .bar .barTxt').text(0 + '%');
		$("#progress").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
});

function onFileDelede(obj) {
	$(obj).parent().remove();
	document.form1.atchFileNm.value="";
	document.form1.atchFileSaveNm.value="";
	document.form1.atchFileSz.value="";
}

function onUserDetailPopup(callBackFun, userNo) {
	try {
		var url = "/mgnt/report/UserDetailAction.do"+"?callBackFun="+callBackFun+"&userNo="+userNo;
		var opt = "width=700, height=400, status=yes,scrollbars=yes,resizable=yes";
		var win = window.open(url, "MEMBER_DETAIL_VIEW", opt);
		win.focus();
	} catch(e) {
		alert(e);
	}
}