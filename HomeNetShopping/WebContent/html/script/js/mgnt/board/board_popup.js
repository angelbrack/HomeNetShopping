var url1 = "";	// list
var url2 = "";	// handle
var url3 = "";	// save
var url4 = "";	// delete
var imgCalPath="";
var fileMessage="";
var oEditors = [];
var oEditorInit = "";

var message1 = ""; //\uc800\uc7a5 \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message2 = ""; //\uc218\uc815 \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message3 = ""; //\uc0ad\uc81c \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message4 = ""; //\ucca8\ubd80\ud30c\uc77c\uc740 \ud55c\uac1c\ub9cc \uac00\ub2a5\ud569\ub2c8\ub2e4.
var message5 = ""; //\uc2dc\uc791\uc77c\uc774 \uc885\ub8cc\uc77c\ubcf4\ub2e4 \ud074 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.

var frm;


function onInit() {
	if(!frm) {
		frm = document.form1;
	}
}

function onSetting() {
	
	$(".numbers").focusout(function(){
		if($(this).val()==""){
			$(this).val(0);
		}
	});
	
	$("#fileupload").on('click', function(){
		if($("#uploaded-files").children().size() > 0){
			alert(message4);
			return false;
		}
	});
	
	if($("input[name=popupDivCd]:checked").val()=="01"){
		$(".popupTr").hide();
		$(".bannerTr").show();
	} else {
		$(".popupTr").show();
		$(".bannerTr").hide();
		fnCreateEditor(oEditors, 'bdltCn');
		oEditorInit = "Y";
	}
	
	$("input[name=popupDivCd]").change(function(){
		if($("input[name=popupDivCd]:checked").val()=="01"){
			$(".popupTr").hide();
			$(".bannerTr").show();
		} else {
			$(".popupTr").show();
			$(".bannerTr").hide();
			if(oEditorInit != "Y"){
				fnCreateEditor(oEditors, 'bdltCn');
				oEditorInit = "Y";
			}
		}
	});
	
	$("#ntcStDtm").datepicker({
		dateFormat:'yymmdd',
		showOn:'button',
		buttonImageOnly:true,
		buttonImage:imgCalPath,
		prevText:'\uc774\uc804 \ub2ec',
		nextText:'\ub2e4\uc74c \ub2ec',
		monthNames:['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
		monthNamesShort:['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
		dayNames:['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesShort:['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesMin:['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		showMonthAfterYear:true,
		yearSuffix:'\ub144',
		onSelect:function(date){
			$("#ntcEndDtm").datepicker("option", "minDate", date);
		}
	});
	
	$("#ntcEndDtm").datepicker({
		dateFormat:'yymmdd',
		showOn:'button',
		buttonImageOnly:true,
		buttonImage:imgCalPath,
		prevText:'\uc774\uc804 \ub2ec',
		nextText:'\ub2e4\uc74c \ub2ec',
		monthNames:['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
		monthNamesShort:['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
		dayNames:['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesShort:['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesMin:['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		showMonthAfterYear:true,
		yearSuffix:'\ub144',
		minDate:$('#ntcStDtm').val()
	});
}

function onListPage(pageNo) {  
	frm.currentPage.value = pageNo;
	frm.action = url1;
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

function onViewPage(popupNo) {
	frm.popupNo.value = popupNo;
	frm.action = url5;
	frm.target = "_self";
	frm.submit();
}

function onEditPage(cmd) {
	frm.cmd.value = cmd;
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}

function onDeleteData() {
	if (confirm(message3)) {
		frm.action = url4;
		frm.target = "_self";
		frm.submit();
	}
}

function onSaveData(){
	if(oEditorInit == "Y"){
		oEditors.getById["bdltCn"].exec("UPDATE_CONTENTS_FIELD", []);
	}
	if($.trim($("bdltCn").val()) == "<br>" || $.trim($("bdltCn").val()) == "<p><br></p>"){
	    oEditors.getById["buCn"].exec("FOCUS");
	    return false;
    }
	
	var mindate = $("#ntcStDtm").val();
	var maxdate = $("#ntcEndDtm").val();
	
	if(mindate != "" && maxdate != "" && mindate > maxdate) {
		alert(message5);
		$("#ntcEndDtm").focus();
		return false;
	}
	var confirmMsg = "";
	if ("I" == $("#cmd").val() ) {
		confirmMsg = message1;
	} else {
		confirmMsg = message2;
	}
	
	$("#form1").validate({
		rules: {
			titNm: {required:true, maxlength:60},
			popupDivCd: {required:true},
			bnLinkUrl: {maxlength:100},
			popupFileWd: {min:1, maxlength:5},
			popupFileHgt: {min:1, maxlength:5},
			popuLocTop: {maxlength:5},
			popuLocTop: {maxlength:5},
			ntcStDtm: {required:true, number:true, date:true, minlength:8, maxlength:8},
			ntcEndDtm: {required:true, number:true, date:true, minlength:8, maxlength:8},
			ntcYn: {required:true}
		},
		submitHandler: function(form) {
			if (confirm(confirmMsg)) {
				form.submit();
			}
		}
	});
	
	$("#form1").attr("action", url3);
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

function onCheckDate(thisval) {
	var yyyymmdd = thisval.value;
	if(yyyymmdd != "") {
		var year  = yyyymmdd.substring(0,4);
		var month = yyyymmdd.substring(4,6);
		var day = yyyymmdd.substring(6,8);
		
		var result = true; // \uc5d0\ub7ec \ubcc0\uc218
		var lastDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		
		if (year%1000 != 0 && year%4 == 0) {
			lastDay[1] = 29;
		}
		
		if (day > lastDay[month-1] || day < 1) {
			result = false;        // \ub0a0\uc9dc \uccb4\ud06c
		}
		
		if (month < 1 || month > 12) {
			result = false;
		}
		if (month%1 != 0 || year%1 != 0 || day%1 != 0) {
			result = false;
		}
		
		if (!result) {
			alert("\ub0a0\uc9dc\ud615\uc2dd\uc774 \uc544\ub2d8");
			thisval.value="";
			return false;
		}
	}
}

/** start \ud30c\uc77c\ucca8\ubd80 \uad00\ub828 \ud568\uc218 **/
$(function() {	
	$('#fileupload').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
	
					fileHtml += ' <div class="alert alert-success" name="fileGubun"> ';
					fileHtml += ' 	<a href="/upload?pathkey=PORTAL.BANNER&getfile='+file.fileName+'&realFileName='+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+'" target="fileHiddenFrame">'+decodeURI(decodeURIComponent(file.realFileName))+'</a><input type="hidden"  name="addFileList" value="'+ decodeURI(decodeURIComponent(file.fileInfo)) +'" > ';
					fileHtml += ' 	<button type="button" class="close" data-dismiss="alert" onclick="onFileDelede(this)">\uc0ad\uc81c\u0097</button> ';
					fileHtml += ' </div> ';
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
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload").attr("readonly", true);
 			$(".data-loading").show();
 		}
 		
		$('#progress .bar').css('width',0 + '%');
		$('#progress .bar .barTxt').text(0 + '%');
		$("#progress").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);});
});

function onFileDelede(obj) {
	$(obj).parent().remove();
}

function onFileReturn(fileName, saveName, fileExt, fileSize, fileInfo) {		
	$("#fileInfo").append("<OPTION value="+fileInfo+">"+fileName+"</OPTION>");
}

function callBackDeleteFile(data) {
	if (data.RSLT_CD == '00') {
		changeFileInfoToRichUploadInfo(data.FILE_INFO);
	}
}
/** end \ud30c\uc77c\ucca8\ubd80 \uad00\ub828 \ud568\uc218 **/
