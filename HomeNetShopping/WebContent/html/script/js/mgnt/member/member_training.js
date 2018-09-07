var message1 = ""; //\uc0ad\uc81c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message2 = ""; //\uc800\uc7a5\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message3 = ""; //\ud544\uc218 \uc785\ub825\uac12\uc785\ub2c8\ub2e4.
var message4 = ""; //300\uc790 \uc774\uc0c1 \uc785\ub825\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.
var message5 = ""; //100\uc790 \uc774\uc0c1 \uc785\ub825\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.
var message6 = ""; //5\uc790 \uc774\uc0c1 \uc785\ub825\ud574\uc57c \ud569\ub2c8\ub2e4.
var message7 = ""; //\ucca8\ubd80\ud30c\uc77c\uc740 \ud55c\uac1c\ub9cc \uac00\ub2a5\ud569\ub2c8\ub2e4.
var message8 = ""; //\uc81c\ubaa9\uc744 \uc785\ub825\ud558\uc138\uc694.

var url1 = "";
var url2 = "";
var url3 = "";
var url4 = "";
var url5 = "";

var msg = "";


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
					fileHtml += " 	<a href='/upload?pathkey=COMMON.BOARD&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
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
}

function onViewPage(buSeqNo) {
	
	$("#buSeqNo").val(buSeqNo);
	
	document.form1.target = "_self";
	document.form1.action = url6;
	document.form1.submit();
}

function onListPage(pageNo) {
	
	document.form1.currentPage.value=pageNo;
	
	document.form1.target = "_self";
	document.form1.action = url2;
	document.form1.submit();
}

function onListPerLine(pagePerLine) {
	document.form1.currentPage.value = 1;
	document.form1.recordCountPerPage.value = pagePerLine;
	document.form1.action = url2;
	document.form1.target = "_self";
	document.form1.submit();
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

function onFileReturn(fileName, saveName, fileExt, fileSize, fileInfo) {		
	$("#fileInfo").append("<OPTION value="+fileInfo+">"+fileName+"</OPTION>");
}

function callBackDeleteFile(data) {
	if (data.RSLT_CD == '00') {
		changeFileInfoToRichUploadInfo(data.FILE_INFO);
	}
}

function onPreViewPopup() {
	var opt = "width=1050, height=750, status=yes, scrollbars=yes, resizable=yes";
	win = window.open("", "_popup", opt);
	win.focus();
	
	document.form1.target = "_popup";
	document.form1.action = url3;
	document.form1.submit();
}

function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
}