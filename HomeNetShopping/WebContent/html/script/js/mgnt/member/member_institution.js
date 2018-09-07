var url1 = "";	// list
var url2 = "";	// handle
var url3 = "";	// popup
var url4 = "";	// save
var url5 = "";	// delete
var url6 = "";	// search address
var url7 = "";	// check duple code
var url8 = "";  // excel list

var message1 = ""; //\uc800\uc7a5\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message2 = ""; //\uc218\uc815\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message3 = ""; //\uc0ad\uc81c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?
var message4 = ""; //\ud544\uc218 \uc785\ub825\uac12\uc785\ub2c8\ub2e4.
var message5 = ""; //\uc0ac\uc6a9 \uac00\ub2a5\ud55c \ucf54\ub4dc\uc785\ub2c8\ub2e4.
var message6 = ""; //\uc0ac\uc6a9\uc911\uc778 \ucf54\ub4dc\uc785\ub2c8\ub2e4.
var message7 = ""; //\ud604\uc7ac \uc18c\uc18d\uae30\uad00\ucf54\ub4dc\ub294 \uc911\ubcf5\ub41c \uc18c\uc18d\uae30\uad00\ucf54\ub4dc \uc785\ub2c8\ub2e4.
var message8 = ""; //\uc18c\uc18d\uae30\uad00\ucf54\ub4dc \uc911\ubcf5\uac80\uc0ac\ub97c \uc2e4\uc2dc\ud574\uc8fc\uc138\uc694.
var message9 = "";
var message10 = "";
var message11 = "";
var message12 = "";
var message13 = "";

var titChgRprtDt = "";
var titChgItmzCn = "";
var frm;

function onInit() {
	if(!frm) {
		frm = document.form1;
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
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
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
		if($("[name=fileGubun]").size()>0) {
			alert(message11);
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
	
	$('#fileupload2').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun2\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList2\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files2").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload2").val(""); 
				$("#fileupload2").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress2").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress2 .bar').css('width', progress + '%');
			$('#progress2 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress2").hide();
				$(".filename").text("");
				$("#fileupload2").val(""); 
				$("#fileupload2").attr("readonly", false);
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
		if($("[name=fileGubun2]").size()>0) {
			alert(message11);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload2").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress2 .bar').css('width',0 + '%');
		$('#progress2 .bar .barTxt').text(0 + '%');
		$("#progress2").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload3').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun3\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList3\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files3").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload3").val(""); 
				$("#fileupload3").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress3").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress3 .bar').css('width', progress + '%');
			$('#progress3 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress3").hide();
				$(".filename").text("");
				$("#fileupload3").val(""); 
				$("#fileupload3").attr("readonly", false);
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
		if($("[name=fileGubun3]").size()>0) {
			alert(message11);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload3").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress3 .bar').css('width',0 + '%');
		$('#progress3 .bar .barTxt').text(0 + '%');
		$("#progress3").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload4').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun4\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList4\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files4").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload4").val(""); 
				$("#fileupload4").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress4").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress4 .bar').css('width', progress + '%');
			$('#progress4 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress4").hide();
				$(".filename").text("");
				$("#fileupload4").val(""); 
				$("#fileupload4").attr("readonly", false);
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
		if($("[name=fileGubun4]").size()>0) {
			alert(message11);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload4").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress4 .bar').css('width',0 + '%');
		$('#progress4 .bar .barTxt').text(0 + '%');
		$("#progress4").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload5').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun5\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList5\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files5").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload5").val(""); 
				$("#fileupload5").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress5").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress5 .bar').css('width', progress + '%');
			$('#progress5 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress5").hide();
				$(".filename").text("");
				$("#fileupload5").val(""); 
				$("#fileupload5").attr("readonly", false);
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
		if($("[name=fileGubun5]").size()>0) {
			alert(message11);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload5").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress5 .bar').css('width',0 + '%');
		$('#progress5 .bar .barTxt').text(0 + '%');
		$("#progress5").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload6').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun6\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList6\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files6").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload6").val(""); 
				$("#fileupload6").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress6").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress6 .bar').css('width', progress + '%');
			$('#progress6 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress6").hide();
				$(".filename").text("");
				$("#fileupload6").val(""); 
				$("#fileupload6").attr("readonly", false);
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
		if($("[name=fileGubun6]").size()>0) {
			alert(message11);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload6").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress6 .bar').css('width',0 + '%');
		$('#progress6 .bar .barTxt').text(0 + '%');
		$("#progress6").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload7').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun7\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList7\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files7").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload7").val(""); 
				$("#fileupload7").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress7").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress7 .bar').css('width', progress + '%');
			$('#progress7 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress7").hide();
				$(".filename").text("");
				$("#fileupload7").val(""); 
				$("#fileupload7").attr("readonly", false);
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
		if($("[name=fileGubun7]").size()>5) {
			alert(message12);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload7").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress7 .bar').css('width',0 + '%');
		$('#progress7 .bar .barTxt').text(0 + '%');
		$("#progress7").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload8').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun8\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList8\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files8").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload8").val(""); 
				$("#fileupload8").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress8").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress8 .bar').css('width', progress + '%');
			$('#progress8 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress8").hide();
				$(".filename").text("");
				$("#fileupload8").val(""); 
				$("#fileupload8").attr("readonly", false);
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
		if($("[name=fileGubun8]").size()>9) {
			alert(message13);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload8").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress8 .bar').css('width',0 + '%');
		$('#progress8 .bar .barTxt').text(0 + '%');
		$("#progress8").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload9').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun9\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList9\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files9").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload9").val(""); 
				$("#fileupload9").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress9").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress9 .bar').css('width', progress + '%');
			$('#progress9 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress9").hide();
				$(".filename").text("");
				$("#fileupload9").val(""); 
				$("#fileupload9").attr("readonly", false);
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
		if($("[name=fileGubun9]").size()>0) {
			alert(message11);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload9").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress9 .bar').css('width',0 + '%');
		$('#progress9 .bar .barTxt').text(0 + '%');
		$("#progress9").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload10').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun10\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList10\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files10").append(fileHtml);
				}
				$(".filename").text("");
				$("#fileupload10").val(""); 
				$("#fileupload10").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress10").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress10 .bar').css('width', progress + '%');
			$('#progress10 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress10").hide();
				$(".filename").text("");
				$("#fileupload10").val(""); 
				$("#fileupload10").attr("readonly", false);
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
		if($("[name=fileGubun10]").size()>0) {
			alert(message11);
			return false;
		}
		
 		if($.browser.msie && $.browser.version <= 9){
 			$("#fileupload10").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress10 .bar').css('width',0 + '%');
		$('#progress10 .bar .barTxt').text(0 + '%');
		$("#progress10").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload11').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun11\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList11\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files11").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload11").val(""); 
				$("#fileupload11").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress11").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress11 .bar').css('width', progress + '%');
			$('#progress11 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress11").hide();
				$(".filename").text("");
				$("#fileupload11").val(""); 
				$("#fileupload11").attr("readonly", false);
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
 			$("#fileupload11").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress11 .bar').css('width',0 + '%');
		$('#progress11 .bar .barTxt').text(0 + '%');
		$("#progress11").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload12').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
			
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun12\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList12\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files12").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload12").val(""); 
				$("#fileupload12").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress12").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			 
			$('#progress12 .bar').css('width', progress + '%');
			$('#progress12 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress12").hide();
				$(".filename").text("");
				$("#fileupload12").val(""); 
				$("#fileupload12").attr("readonly", false);
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
 			$("#fileupload12").attr("readonly", true);
 			$(".data-loading").show();
 		} 
		$('#progress12 .bar').css('width',0 + '%');
		$('#progress12 .bar .barTxt').text(0 + '%');
		$("#progress12").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload13').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun13\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList13\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files13").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload13").val(""); 
				$("#fileupload13").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress13").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			
			$('#progress13 .bar').css('width', progress + '%');
			$('#progress13 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress13").hide();
				$(".filename").text("");
				$("#fileupload13").val(""); 
				$("#fileupload13").attr("readonly", false);
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
			$("#fileupload13").attr("readonly", true);
			$(".data-loading").show();
		} 
		$('#progress13 .bar').css('width',0 + '%');
		$('#progress13 .bar .barTxt').text(0 + '%');
		$("#progress13").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload14').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun14\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList14\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files14").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload14").val(""); 
				$("#fileupload14").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress14").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			
			$('#progress14 .bar').css('width', progress + '%');
			$('#progress14 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress14").hide();
				$(".filename").text("");
				$("#fileupload14").val(""); 
				$("#fileupload14").attr("readonly", false);
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
			$("#fileupload14").attr("readonly", true);
			$(".data-loading").show();
		} 
		$('#progress14 .bar').css('width',0 + '%');
		$('#progress14 .bar .barTxt').text(0 + '%');
		$("#progress14").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload14').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun14\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList14\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files14").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload14").val(""); 
				$("#fileupload14").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress14").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			
			$('#progress14 .bar').css('width', progress + '%');
			$('#progress14 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress14").hide();
				$(".filename").text("");
				$("#fileupload14").val(""); 
				$("#fileupload14").attr("readonly", false);
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
			$("#fileupload14").attr("readonly", true);
			$(".data-loading").show();
		} 
		$('#progress14 .bar').css('width',0 + '%');
		$('#progress14 .bar .barTxt').text(0 + '%');
		$("#progress14").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	
	$('#fileupload15').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun15\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList15\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files15").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload15").val(""); 
				$("#fileupload15").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress15").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			
			$('#progress15 .bar').css('width', progress + '%');
			$('#progress15 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress15").hide();
				$(".filename").text("");
				$("#fileupload15").val(""); 
				$("#fileupload15").attr("readonly", false);
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
			$("#fileupload15").attr("readonly", true);
			$(".data-loading").show();
		} 
		$('#progress15 .bar').css('width',0 + '%');
		$('#progress15 .bar .barTxt').text(0 + '%');
		$("#progress15").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload16').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun16\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList16\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files16").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload16").val(""); 
				$("#fileupload16").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress16").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			
			$('#progress16 .bar').css('width', progress + '%');
			$('#progress16 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress16").hide();
				$(".filename").text("");
				$("#fileupload16").val(""); 
				$("#fileupload16").attr("readonly", false);
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
			$("#fileupload16").attr("readonly", true);
			$(".data-loading").show();
		} 
		$('#progress16 .bar').css('width',0 + '%');
		$('#progress16 .bar .barTxt').text(0 + '%');
		$("#progress16").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload17').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun17\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList17\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files17").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload17").val(""); 
				$("#fileupload17").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress17").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			
			$('#progress17 .bar').css('width', progress + '%');
			$('#progress17 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress17").hide();
				$(".filename").text("");
				$("#fileupload17").val(""); 
				$("#fileupload17").attr("readonly", false);
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
			$("#fileupload17").attr("readonly", true);
			$(".data-loading").show();
		} 
		$('#progress17 .bar').css('width',0 + '%');
		$('#progress17 .bar .barTxt').text(0 + '%');
		$("#progress17").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
	$('#fileupload18').fileupload({
		dataType: 'json',					
		done: function (e, data) {
			$.each(data.result, function (index, file) {
				if(file.errorMsg) {
					alert(file.errorMsg);
				} else {
					var fileHtml = "";
					
					fileHtml += " <div class=\"alert alert-success\" name=\"fileGubun18\"> ";
					fileHtml += " 	<a href='/upload?pathkey=SUPPORT.EDUINSTITUTION2&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\"  name=\"addFileList18\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" > ";
					fileHtml += " 	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" onclick=\"onFileDelede(this)\">"+message14+"</button> ";
					fileHtml += " </div> ";
					$("#uploaded-files18").append(fileHtml);
				} 
				$(".filename").text("");
				$("#fileupload18").val(""); 
				$("#fileupload18").attr("readonly", false);
				$(".data-loading").hide();
				$("#progress18").hide();
			});
		},
		progressall: function (e, data) {	
			var progress = parseInt(data.loaded / data.total * 100, 10);            
			
			$('#progress18 .bar').css('width', progress + '%');
			$('#progress18 .bar .barTxt').text(progress + '%');
			if(progress >= 100) {
				$("#progress18").hide();
				$(".filename").text("");
				$("#fileupload18").val(""); 
				$("#fileupload18").attr("readonly", false);
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
			$("#fileupload18").attr("readonly", true);
			$(".data-loading").show();
		} 
		$('#progress18 .bar').css('width',0 + '%');
		$('#progress18 .bar .barTxt').text(0 + '%');
		$("#progress18").show();
		
	}).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
	;
	
});

function onFileDelede(obj) {
	$(obj).parent().remove();
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

function onEditPage(blngCd) {
	frm.cmd.value = 'I';
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}


function onViewPage(blngCd) {
	frm.cmd.value = 'U';
	frm.blngCd.value = blngCd;
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}

function onPreviewPage2() {
	var opt = "width=1050, height=750, status=yes, scrollbars=yes, resizable=yes";
	var win = window.open(url3, "_popup", opt);
	win.focus();
}

function onPreviewPage() {
	var opt = "width=1050, height=750, status=yes, scrollbars=yes, resizable=yes";
	win = window.open("", "_popup", opt);
	win.focus();
	
	document.form1.target = "_popup";
	document.form1.action = url3;
	document.form1.submit();
}

function onDeleteData() {
	if (confirm(message3)) {
		frm.action = url5;
		frm.target = "_self";
		frm.submit();
	}
}

function onCodeCheck(){
	var blngCd = $("#blngCd").val() ;

	if(blngCd == "") {
		alert(message4) ;
		return ;
	}
	
	jQuery.ajax({
		type : "GET",
		url : url7+"?blngCd="+ blngCd,
		dataType : "json",
		cache : false,
		contentType : "application/json",
		async: false,
		success : function(data){
			jQuery.each(data, function(i, node) {
				if(node["usedCnt"] == "0") {
					alert(node["checkCode"]+" "+message5) ;
					$("#chkCode").val("1");
					$("#chkBlngCd").val(blngCd);
				}else{
					alert(node["checkCode"]+" "+message6) ;
					$("#chkCode").val("2");
					$("#chkBlngCd").val(blngCd);
				}
			});
		}
	});
	
}

function onEngNum(event) {
	if( (event.keyCode < 48 || event.keyCode > 57) 
			&& (event.keyCode < 65 || event.keyCode > 90) 
			&& (event.keyCode < 97 || event.keyCode > 122) ) {
		return false;
	}
} 

function onTelPress(event, type) {
	if(type == "numbers") {
		if((event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 45) return false;
	}
} 

function onSaveData(cmd){
	$("#form1").attr("target", "_self");
	var msg = "";
	if('U'==cmd) {
		msg = message2;
	} else {
		msg = message1;
		
		if($("#chkCode").val() == "0") {
	        alert(message8);
	        $("#chkCode").focus();
	        return ;
	    }
		
		if($("#chkCode").val() == "2") {
			alert(message7);
			$("#chkCode").focus();
			return ;
		}
		
		if($("#blngCd").val() != $("#chkBlngCd").val()) {
			alert(message8);
			$("#chkCode").focus();
			return ;
		}
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
   	
   	var addFileList2 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList2 == ""){
   				addFileList2 = $(this).val();
   			}else{
   				addFileList2 += ","+$(this).val();
   			}
   		}
   	});
	
   	var addFileList3 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList3 == ""){
   				addFileList3 = $(this).val();
   			}else{
   				addFileList3 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList4 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList4 == ""){
   				addFileList4 = $(this).val();
   			}else{
   				addFileList4 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList5 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList5 == ""){
   				addFileList5 = $(this).val();
   			}else{
   				addFileList5 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList6 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList6 == ""){
   				addFileList6 = $(this).val();
   			}else{
   				addFileList6 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList7 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList7 == ""){
   				addFileList7 = $(this).val();
   			}else{
   				addFileList7 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList8 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList8 == ""){
   				addFileList8 = $(this).val();
   			}else{
   				addFileList8 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList9 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList9 == ""){
   				addFileList9 = $(this).val();
   			}else{
   				addFileList9 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList10 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList10 == ""){
   				addFileList10 = $(this).val();
   			}else{
   				addFileList10 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList11 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList11 == ""){
   				addFileList11 = $(this).val();
   			}else{
   				addFileList11 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList12 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList12 == ""){
   				addFileList12 = $(this).val();
   			}else{
   				addFileList12 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList13 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList13 == ""){
   				addFileList13 = $(this).val();
   			}else{
   				addFileList13 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList14 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList14 == ""){
   				addFileList14 = $(this).val();
   			}else{
   				addFileList14 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList15 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList15 == ""){
   				addFileList15 = $(this).val();
   			}else{
   				addFileList15 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList16 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList16 == ""){
   				addFileList16 = $(this).val();
   			}else{
   				addFileList16 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList17 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList17 == ""){
   				addFileList17 = $(this).val();
   			}else{
   				addFileList17 += ","+$(this).val();
   			}
   		}
   	});
   	
   	var addFileList18 = "";
   	$("#fileInfo > option").each(function(index){
   		if($(this).val() != ""){
   			if(addFileList18 == ""){
   				addFileList18 = $(this).val();
   			}else{
   				addFileList18 += ","+$(this).val();
   			}
   		}
   	});
   	
   	$("#addFileList").val(addFileList);
   	$("#addFileList2").val(addFileList2);
   	$("#addFileList3").val(addFileList3);
   	$("#addFileList4").val(addFileList4);
   	$("#addFileList5").val(addFileList5);
   	$("#addFileList6").val(addFileList6);
   	$("#addFileList7").val(addFileList7);
   	$("#addFileList8").val(addFileList8);
   	$("#addFileList9").val(addFileList9);
   	$("#addFileList10").val(addFileList10);
   	$("#addFileList11").val(addFileList11);
   	$("#addFileList12").val(addFileList12);
   	$("#addFileList13").val(addFileList13);
   	$("#addFileList14").val(addFileList14);
   	$("#addFileList15").val(addFileList15);
   	$("#addFileList16").val(addFileList16);
   	$("#addFileList17").val(addFileList17);
   	$("#addFileList18").val(addFileList18);
	
	$("#form1").validate({
		rules: {
			blngCd:{ required:true, maxlength:10 },
			coNm:{ required:true, maxlength:100 },
			blngDivCd:{ required:true, maxlength:1 },
			pspYn:{ required:true },
			afmQuota:{ number:true, maxlength:6 },
			ceoNm:{ maxlength:50 },
			telNo:{ numberhyphen: true, maxlength:20 },
			bizNo:{ maxlength:20 },
			cusAdv: { maxlength:50 },
			hmpgUrl:{ maxlength:500 },
			ins:{ maxlength:50 },
			adm:{ maxlength:50 },
			chradm:{ maxlength:50 },
			chradm2:{ maxlength:50 },
			chm:{ maxlength:50 },
			drl:{ maxlength:50},
			prceva:{ maxlength:50 },
			prceva2:{ maxlength:50 },
			prceva3:{ maxlength:50 },
			prceva4:{ maxlength:50 },
			email:{ email: true, maxlength:50 },
			admEmail:{ email: true, maxlength:50 },
			chradmEmail:{ email: true, maxlength:50 },
			chradm2Email:{ email: true, maxlength:50 },			
			zipCd:{ numberhyphen: true, maxlength:10 },
			pnaddV:{ maxlength:200 },
			bpnoAddV:{ maxlength:200 },
			useYn:{ required:true },
			sortOr:{ required:true, number:true, maxlength:5 }
		},
		
		submitHandler: function(form) {
			if (confirm(msg)) {
				form.submit();
			}
		}
	});
	
	$("#form1").attr("action", url4);
	$("#form1").submit();
}

function onZipPopup() {
	var opt = "width=700, height=600, status=yes, scrollbars=yes, resizable=yes";
	win = window.open(url6, "_popup", opt);
	win.focus();
}

function onExcelDownload() {
	frm.action = url8;
	frm.target = "_self";
	frm.submit();
}

function onPress(event, type) {
	if(event.keyCode < 48 || event.keyCode > 57) return false;
}
function subjOrder() {
	/** start \uacfc\ubaa9\uc815\ubcf4 \ubc88\ud638 **/
	$(".subjOrder").each(function(index) {
		$(this).text(index+1);
	});
}

function onAddChgRow(){
	$("#emptySubj").remove();
	$("#subjBody img").remove();
	
	var subjBodyHtml = "";
	subjBodyHtml += '<tr><td><input type="checkbox" class="subj" /></td>';
	subjBodyHtml += '<td class="subjOrder"></td>';
	
	subjBodyHtml += '<td><input type="text" name="chgRprtDts" onkeypress="return onPress(event,\'numbers\');" maxlength="8" class="txtbox1 dateinput" title="'+titChgRprtDt+'" />';
	subjBodyHtml += '<td><input type="text" name="chgItmzCns" style="width:98%;height:18px;" class="txtbox1" title="'+titChgItmzCn+'" /></td>';
	subjBodyHtml += '</td></tr>';
	
	$("#subjBody").append(subjBodyHtml);
	settingDatepicker();
	//onTimeFormat();
	subjOrder();
}

function onDelChgRow(){
	if($(".subj:checked").size() <= 0) {
		alert(message9); 
	} else {
		$(".subj").each(function(index) { 
			if( $(this).attr("checked")=="checked" ) { 
				$(this).parent().parent().remove(); 
			} 
		});
		if($(".subj").size() == 0) {
			$("#subjBody").append('<tr id="emptySubj"><td colspan="4">'+message10+'</td></tr>');
		} else {
			subjOrder();
		}
		
	} 
}
/** end \ucc28\uc218 \ub4f1\ub85d\uc218\uc815 \ucee8\ud2b8\ub864 \ud568\uc218 **/
function settingDatepicker() {	
	$(document).find("input[name=chgRprtDts]").each(function(index) {
		$(this).attr("id","chgRprtDts"+index);
		$(this).removeClass('hasDatepicker').datepicker({
			dateFormat: 'yymmdd',
			showOn : "button",
			buttonImageOnly: true,
			buttonImage : "/theme/00001/ko/support/images/btn_cal_01.jpg",
			prevText: '\uc774\uc804 \ub2ec',
			nextText: '\ub2e4\uc74c \ub2ec',
			monthNames: ['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
			monthNamesShort: ['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
			dayNames: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
			dayNamesShort: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
			dayNamesMin: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
			showMonthAfterYear: true,
			yearSuffix: '\ub144'
		});
	});
}	

