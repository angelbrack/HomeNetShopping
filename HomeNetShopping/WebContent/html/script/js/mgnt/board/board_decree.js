var message1 = ""; //\uc0ad\uc81c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message2 = ""; //\uc800\uc7a5\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message3 = ""; //\ud544\uc218 \uc785\ub825\uac12\uc785\ub2c8\ub2e4.
var message4 = ""; //300\uc790 \uc774\uc0c1 \uc785\ub825\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.
var message5 = ""; //100\uc790 \uc774\uc0c1 \uc785\ub825\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.
var message6 = ""; //5\uc790 \uc774\uc0c1 \uc785\ub825\ud574\uc57c \ud569\ub2c8\ub2e4.
var message7 = ""; //6\uc790 \uc774\uc0c1 \uc785\ub825\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.
var message8 = ""; //\uc22b\uc790\ub9cc \uc785\ub825\uac00\ub2a5\ud569\ub2c8\ub2e4.
var message9 = ""; //\uc120\ud0dd\ub41c \ud56d\ubaa9\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.
var message10 = "";

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
					fileHtml += " 	<a href='/upload?pathkey=COMMON.BOARD&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" /> ";
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
		if($("[name=fileGubun]").size()>14) {
			alert(message10);
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
}

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
	if (!$("input[name=progNo]").is(":checked")) {
		alert(message9);
		return;
	}
	var frm = document.form1;
	if (confirm(message1)) {
		frm.target = "_self";
		frm.action = url4;
		frm.submit();
	}
}

function onSaveData() {
	
	if($("#cmd").val() == "I") {
		document.getElementById("currentPage").value = "1";
	}
	
	if($("#titNm").val() == "") {
		alert(message7);
		document.form1.titNm.focus();
		return false;
	}
	
	oEditors.getById["buCn"].exec("UPDATE_CONTENTS_FIELD", []);
	if(document.getElementById("buCn").value.trim() == "<br>" || document.getElementById("buCn").value.trim() == "<p><br></p>"){
		alert(message8);
	    oEditors.getById["buCn"].exec("FOCUS");
	    return false;
    }

    var addFileList = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList == ""){
   				addFileList = $(this).val();
   			}else{
   				addFileList += ","+$(this).val();
   			}
   		}
   	});
   	
   	$("#addFileList").val(addFileList);
     
    if(confirm(msg)) {
        document.form1.target = "_self";
        document.form1.action = url5;
        document.form1.submit();                    
    }
}




function onCheckValid() {

	if($("#optrPgmNm").val().trim() == "") { 
		alert(message3); 
		$("#optrPgmNm").focus(); 
		return false;
	}
	
	if($("#optrPgmNm").val().trim().length > 300) { 
		alert(message4); 
		$("#optrPgmNm").focus(); 
		return false;
	}
	
	if($("#optrPgmId").val().trim() == "") { 
		alert(message3); 
		$("#optrPgmId").focus(); 
		return false;
	}
	
	if($("#optrPgmId").val().trim().length > 100) { 
		alert(message5); 
		$("#optrPgmId").focus(); 
		return false;
	}
	
	if($("#optrPgmUrlV").val().trim() == "") { 
		alert(message3); 
		$("#optrPgmUrlV").focus(); 
		return false;
	}
	
	if($("#optrPgmUrlV").val().trim().length > 200) { 
		alert(message4); 
		$("#optrPgmUrlV").focus(); 
		return false;
	}
	
	if($("#optrPgmGrpNo").val().trim() == "") { 
		alert(message3); 
		$("#optrPgmGrpNo").focus(); 
		return false;
	}
	
	if($("#optrPgmGrpNo").val().trim().length < 5) { 
		alert(message6); 
		$("#optrPgmGrpNo").focus(); 
		return false;
	}
	
	if($("#optrPgmGrpNo").val().trim().length > 6) { 
		alert(message7); 
		$("#optrPgmGrpNo").focus(); 
		return false;
	}
	
	if(isNaN($("#optrPgmGrpNo").val().trim()) == true) {
		alert(message8); 
		$("#optrPgmGrpNo").focus(); 
		return false;
	}

	return true;
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

function onDeleteData() {
	$("#cmd").val("D");
	if (confirm(message1)) {
		document.form1.target = "_self";
		document.form1.action = url4;
		document.form1.submit();
	}	
}

