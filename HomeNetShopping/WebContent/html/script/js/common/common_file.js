$(function(){
    // form
    $(document).on('click', '.form-file button', function(e){
        e.preventDefault();
        $(this).siblings('input[type="file"]').trigger('click');
    });

    $(document).on('change', '.form-file input[type="file"]', function(e){
        e.preventDefault();
        if ( this.files[0] ) {
            var filename = this.files[0].name;
            // $(this).siblings('input[type="text"]').val(filename);
        }
    });

});


$(function() {	
	//파일업로드
	$.fn.fnFileUpload = function(option){
		var dafaultOption = {
				path : "SAMPLE.NOTICE",		//기본경로
				idx : "",					//fileupload index
				fileCo : 3,					//file upload count
				addSavePath : "",			//추가 저장 경로
				callbackFn : null
			};
		$.extend(dafaultOption, option);
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload";
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		var url = uploadingUrl + "?pathkey=" + dafaultOption.path + "&addSavePath=" + dafaultOption.addSavePath;
		var idx = dafaultOption.idx;
		var fileCo = dafaultOption.fileCo;
		var upCnt = 1;
		
		return this.each(function(i, obj) {
			if(idx === 0){
				idx = "";
			}
			var divAreaHtml = "";
		
	        divAreaHtml += " <input type=\"file\" class=\"form\" name=\"files"+idx+"\" id=\"fileupload"+idx+"\" data-url=\""+url+"\" multiple  width=\"100%\" /> ";
	        //divAreaHtml += " <button type=\"button\" class=\"btn btn-outline btn-bordered btn-style-c btn-sm\">찾아보기</button> ";
	               
	        
	        $(this).addClass("form-file");
	        $(this).addClass("m-b-10");
	        $(this).append(divAreaHtml);
	        
	        var divAreaAttchHtml = "";
	        divAreaAttchHtml += " <div id=\"progress"+idx+"\" class=\"progress\"> ";
	        divAreaAttchHtml += " 	<div class=\"bar\" style=\"width: 0%;\"><span class=\"barTxt\">0%</span></div> ";
	        divAreaAttchHtml += " </div> ";	
	        
	        divAreaAttchHtml += " <ul id=\"uploaded-files"+idx+"\" class=\"dot-list\"> ";
	        divAreaAttchHtml += " </ul> ";
	        
	        $(this).after(divAreaAttchHtml);
			
			$('#fileupload'+idx).fileupload({
		        dataType: 'json',                   
		        done: function (e, data) {
		            $.each(data.result, function (index, file) {
		            	if(index === 0){
		            		index = "";
						}
		                if(file.errorMsg) {
		                	alert(decodeURI(decodeURIComponent(file.errorMsg)));
		                } else {
		                	
		    		    	if($('input[name="addFileList'+ idx +'"]').length+upCnt > fileCo) {
		    		    		alert(fileCo + "개 까지 업로드 할 수 있습니다.");
		    		    		return false;
		    		    	}
		                	
		    		    	if(file.realFileName.length > 255) {
		    		    		alert("실제 파일명이 너무 길어 업로드 할 수 없습니다. ");
		    		    		return false;
		    		    	}
		    		    	
		                    var fileHtml = "";
		                    fileHtml += " <li name=\"fileGubun"+idx+"\"> ";
		                    fileHtml += "   <span class=\"m-r-10 font-dark-grey underline\"><a href='"+url+"&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" ></span>";
		                    fileHtml += "   <a href=\"#none\" class=\"btn-delete\" onclick=\"onFileDelede(this)\">&times;</a> ";
		                    fileHtml += " </li> ";
		                    $("#uploaded-files"+idx).append(fileHtml);
		                    
		                    upCnt+1;
		                    if(dafaultOption.callbackFn != undefined && dafaultOption.callbackFn != ""){
		                    	//dafaultOption.callbackFn(file);
		                    	
		                    	eval("var callbackfun = " + dafaultOption.callbackFn);
		            			callbackfun(file);
		                    	
			                }
		                }
		                $(".filename").text("");
		                $("#fileupload" + idx).val(""); 
		                $("#fileupload" + idx).attr("readonly", false);
		                $(".data-loading").hide();
		                $("#progress" + idx).hide();
		            });
		        },
		        progressall: function (e, data) {   
		            var progress = parseInt(data.loaded / data.total * 100, 10);            
		             
		            $('#progress'+ idx +' .bar').css('width', progress + '%');
		            $('#progress'+ idx +' .bar .barTxt').text(progress + '%');
		            if(progress >= 100) {
		                $("#progress" + idx).hide();
		                $(".filename").text("");
		                $("#fileupload" + idx).val(""); 
		                $("#fileupload" + idx).attr("readonly", false);
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
		    	if($('input[name="addFileList'+ idx +'"]').length == fileCo) {
		    		alert(fileCo + "개 까지 업로드 할 수 있습니다.");
		    		return false;
		    	}
		      
		        if($.browser.msie && $.browser.version <= 9){
		            $("#fileupload" + idx).attr("readonly", true);
		            $(".data-loading").show();
		        } 
		        $('#progress'+ idx + ' .bar').css('width',0 + '%');
		        $('#progress' + idx + ' .bar .barTxt').text(0 + '%');
		        $("#progress" + idx).show();
		        
		    }).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
		    ;
		}); 
	};
	
	//파일업로드
	$.fn.fnUserFileUpload = function(option){
		var dafaultOption = {
				path : "SAMPLE.NOTICE",		//기본경로
				idx : "",					//fileupload index
				fileCo : 3,					//file upload count
				addSavePath : "",			//추가 저장 경로
				callbackFn : null
			};
		$.extend(dafaultOption, option);
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload";
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		var url = uploadingUrl + "?pathkey=" + dafaultOption.path + "&addSavePath=" + dafaultOption.addSavePath;
		var idx = dafaultOption.idx;
		var fileCo = dafaultOption.fileCo;
		var upCnt = 1;
		
		return this.each(function(i, obj) {
			if(idx === 0){
				idx = "";
			}
			var divAreaHtml = "";
		
	        //divAreaHtml += " <input type=\"file\" name=\"files"+idx+"\" id=\"fileupload"+idx+"\" data-url=\""+url+"\" multiple  width=\"100%\" /> ";
	        //divAreaHtml += " <button type=\"button\" class=\"btn btn-outline btn-bordered btn-style-c btn-sm\">찾아보기</button> ";
	               
	        
	        //$(this).addClass("form-file");
	        //$(this).addClass("m-b-10");
	        //$(this).append(divAreaHtml);
	        
	        var divAreaAttchHtml = "";
	        divAreaAttchHtml += " <div id=\"progress"+idx+"\" class=\"progress\"> ";
	        divAreaAttchHtml += " 	<div class=\"bar\" style=\"width: 0%;\"><span class=\"barTxt\">0%</span></div> ";
	        divAreaAttchHtml += " </div> ";	
	        
	        divAreaAttchHtml += " <ul id=\"uploaded-files"+idx+"\" class=\"file_list\"> ";
	        divAreaAttchHtml += " </ul> ";
	        
	        //$(this).after(divAreaAttchHtml);
	        $(this).append(divAreaAttchHtml);
	        
	        
	        var fileAddHtml = "";
	        fileAddHtml += " <li> ";
	        fileAddHtml += " <input type=\"file\" name=\"files"+idx+"\" id=\"fileupload"+idx+"\" data-url=\""+url+"\" multiple  width=\"100%\" /> ";
	        //fileAddHtml += " <a href=\""+url+"\" name=\"files"+idx+"\" id=\"fileupload"+idx+"\" class=\"btn_sm_blue\" data-url=\""+url+"\">추가</a> ";
	        //fileAddHtml += " <button type=\"button\" class=\"btn_sm_blue\" data-url=\""+url+"\" multiple>추가</button> ";
	        fileAddHtml += " </li> ";
	     
	        $("#uploaded-files"+idx).append(fileAddHtml);
			
			$('#fileupload'+idx).fileupload({
		        dataType: 'json',                   
		        done: function (e, data) {
		            $.each(data.result, function (index, file) {
		            	if(index === 0){
		            		index = "";
						}
		                if(file.errorMsg) {
		                	alert(decodeURI(decodeURIComponent(file.errorMsg)));
		                } else {
		                	
		    		    	if($('input[name="addFileList'+ idx +'"]').length+upCnt > fileCo) {
		    		    		alert(fileCo + "개 까지 업로드 할 수 있습니다.");
		    		    		return false;
		    		    	}
		                	
		    		    	if(file.realFileName.length > 255) {
		    		    		alert("실제 파일명이 너무 길어 업로드 할 수 없습니다. ");
		    		    		return false;
		    		    	}
		    		    	
		                    var fileHtml = "";
		                    fileHtml += " <li name=\"fileGubun"+idx+"\"> ";
		                    fileHtml += "   <span class=\"m-r-10 font-dark-grey underline\"><a href='"+url+"&getfile="+file.fileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(file.realFileName))))+"' target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(file.realFileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(file.fileInfo)) +"\" ></span>";
		                    fileHtml += "   <a href=\"#none\" class=\"btn_sm_red\" onclick=\"onFileDelede(this)\">삭제</a> ";
		                    fileHtml += " </li> ";
		                    $("#uploaded-files"+idx).append(fileHtml);
		                    
		                    upCnt+1;
		                    if(dafaultOption.callbackFn != undefined && dafaultOption.callbackFn != ""){
		                    	dafaultOption.callbackFn(file);
			                }
		                }
		                $(".filename").text("");
		                $("#fileupload" + idx).val(""); 
		                $("#fileupload" + idx).attr("readonly", false);
		                $(".data-loading").hide();
		                $("#progress" + idx).hide();
		            });
		        },
		        progressall: function (e, data) {   
		            var progress = parseInt(data.loaded / data.total * 100, 10);            
		             
		            $('#progress'+ idx +' .bar').css('width', progress + '%');
		            $('#progress'+ idx +' .bar .barTxt').text(progress + '%');
		            if(progress >= 100) {
		                $("#progress" + idx).hide();
		                $(".filename").text("");
		                $("#fileupload" + idx).val(""); 
		                $("#fileupload" + idx).attr("readonly", false);
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
		    	if($('input[name="addFileList'+ idx +'"]').length == fileCo) {
		    		alert(fileCo + "개 까지 업로드 할 수 있습니다.");
		    		return false;
		    	}
		      
		        if($.browser.msie && $.browser.version <= 9){
		            $("#fileupload" + idx).attr("readonly", true);
		            $(".data-loading").show();
		        } 
		        $('#progress'+ idx + ' .bar').css('width',0 + '%');
		        $('#progress' + idx + ' .bar .barTxt').text(0 + '%');
		        $("#progress" + idx).show();
		        
		    }).error(function (jqXHR, textStatus, errorThrown) {alert(jqXHR + "   textStatus"+ textStatus +"    errorThrown" + errorThrown);})
		    ;
		}); 
	};
	
});


//파일다운로드 - 관리자
function fnFileDown(path, idx, obj, addSavePath) {
	if(idx === 0){
		idx = "";
	}
	if(typeof obj === "string" && obj.length > 0) {
		var fileHtml = "";
        $(this).addClass("attachments");
        
        fileHtml += " <h4 class=\"bold m-b-10\">첨부파일 다운로드</h4> ";
        fileHtml += " <ul class=\"dot-list\"> ";
        
		var fileObj = obj.replaceAll("&#034;", '"').toString();
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload"; 
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		var url = uploadingUrl + "?pathkey=" + path;
		
		if(addSavePath != undefined && addSavePath != "") {
			url += "&addSavePath=" + addSavePath;
		}
		var jsonFileArray = JSON.parse(fileObj);
		
		var fileYn = "N";
		$.each(jsonFileArray, function(i, jsonObj) {
			if(jsonObj.atchmnflNm != undefined && jsonObj.atchmnflStreNm != undefined && jsonObj.atchmnflMg != undefined) {
				var fileName = jsonObj.atchmnflNm;
				var realFileName= jsonObj.atchmnflStreNm;
				var fileSize= jsonObj.atchmnflMg;
				
				var fileInfo = fileName+"|"+realFileName+"| |"+ fileSize;
						
				fileHtml += "<li><a href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(fileName))))+"\" target=\"fileHiddenFrame\" class=\"font-dark-grey underline\">"+decodeURI(decodeURIComponent(fileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" ></li>";
				
				fileYn = "Y";
			}
		});
		
        fileHtml += " </ul> ";
		
		if(fileYn == "Y") {
			$("#uploaded-files"+idx).html(fileHtml);
		}
	}
	
}

//파일다운로드 - PC
function fnUserFileDown(path, idx, obj, addSavePath) {
	if(idx === 0){
		idx = "";
	}
	if(typeof obj === "string" && obj.length > 0) {
		var fileHtml = "";
        //$(this).addClass("attachments");
        
        fileHtml += " <ul> ";
        
		var fileObj = obj.replaceAll("&#034;", '"').toString();
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload"; 
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		var url = uploadingUrl + "?pathkey=" + path;
		
		if(addSavePath != undefined && addSavePath != "") {
			url += "&addSavePath=" + addSavePath;
		}
		var jsonFileArray = JSON.parse(fileObj);
		
		var fileYn = "N";
		$.each(jsonFileArray, function(i, jsonObj) {
			if(jsonObj.atchmnflNm != undefined && jsonObj.atchmnflStreNm != undefined && jsonObj.atchmnflMg != undefined) {
				var fileName = jsonObj.atchmnflNm;
				var realFileName= jsonObj.atchmnflStreNm;
				var fileSize= jsonObj.atchmnflMg;
				
				var fileInfo = fileName+"|"+realFileName+"| |"+ fileSize;
						
				fileHtml += "<li><a href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(fileName))))+"\" target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(fileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" ></li>";
				
				fileYn = "Y";
			}
		});
		
        fileHtml += " </ul> ";
		
		if(fileYn == "Y") {
			$("#uploaded-files"+idx).html(fileHtml);
		}
	}
	
}

//파일다운로드 - 모바일
function fnMobileFileDown(path, idx, obj, addSavePath) {
	if(idx === 0){
		idx = "";
	}
	if(typeof obj === "string" && obj.length > 0) {
		var fileHtml = "";
        
		var fileObj = obj.replaceAll("&#034;", '"').toString();
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload"; 
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		var url = uploadingUrl + "?pathkey=" + path;
		
		if(addSavePath != undefined && addSavePath != "") {
			url += "&addSavePath=" + addSavePath;
		}
		var jsonFileArray = JSON.parse(fileObj);
		
		var fileYn = "N";
		$.each(jsonFileArray, function(i, jsonObj) {
			if(jsonObj.atchmnflNm != undefined && jsonObj.atchmnflStreNm != undefined && jsonObj.atchmnflMg != undefined) {
				var fileName = jsonObj.atchmnflNm;
				var realFileName= jsonObj.atchmnflStreNm;
				var fileSize= jsonObj.atchmnflMg;
				
				var fileInfo = fileName+"|"+realFileName+"| |"+ fileSize;
						
				//fileHtml += "<li><a href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(fileName))))+"\" target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(fileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" ></li>";
				fileHtml += "<a data-ajax=\"false\" href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(decodeURI(decodeURIComponent(fileName))))+"\" target=\"fileHiddenFrame\" class=\"ui-btn ui-mini ui-corner-all ui-btn-c ui-btn-inline\">"+decodeURI(decodeURIComponent(fileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" >";
				
				fileYn = "Y";
			}
		});
		
		if(fileYn == "Y") {
			$("#uploaded-files"+idx).html(fileHtml);
		}
	}
	
}

//파일수정
function fnFileEdit(path, idx, obj, addSavePath){
	if(idx === 0){
		idx = "";
	}
	if(typeof obj === "string" && obj.length > 0) {
		var fileObj = obj.replaceAll("&#034;", '"').toString();
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload";
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		var url = uploadingUrl + "?pathkey=" + path;
		if(addSavePath != undefined && addSavePath != "") {
			url += "&addSavePath=" + addSavePath;
		}
		var jsonFileArray = JSON.parse(fileObj);
		
		
		$.each(jsonFileArray, function(i, jsonObj) {
			if(jsonObj.atchmnflNm != undefined && jsonObj.atchmnflStreNm != undefined && jsonObj.atchmnflMg != undefined) {
				var fileHtml = "";	
				var fileName = jsonObj.atchmnflNm;
				var realFileName= jsonObj.atchmnflStreNm;
				var fileSize= jsonObj.atchmnflMg;
				
				var fileInfo = fileName+"|"+realFileName+"| |"+ fileSize;
				
				if(fileHtml != "") {
					fileHtml += "	<br />";
				}
				
				fileHtml += " <li name=\"fileGubun"+idx+"\"> ";
                fileHtml += "   <span class=\"m-r-10 font-dark-grey underline\"><a href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(fileName))+"\" target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(fileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" ></span>";
                fileHtml += "   <a href=\"#none\" class=\"btn-delete\" onclick=\"onFileDelede(this)\">&times;</a> ";
                fileHtml += " </li> ";
				
				$("#uploaded-files"+idx).append(fileHtml);
				
				fileYn = "Y";
			}
		});
		
	}
}



//파일수정
function fnUserFileEdit(path, idx, obj, addSavePath){
	if(idx === 0){
		idx = "";
	}
	if(typeof obj === "string" && obj.length > 0) {
		var fileObj = obj.replaceAll("&#034;", '"').toString();
		
		var curUrl = document.location.href;
		var uploadingUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			uploadingUrl = CTX_PATH + "/upload";
		} else if(curUrl.indexOf("/app/") > -1) {
			uploadingUrl = CTX_PATH + "/user_uploading";
		} else {
			uploadingUrl = CTX_PATH + "/user_uploading";
		}
		
		var url = uploadingUrl + "?pathkey=" + path;
		if(addSavePath != undefined && addSavePath != "") {
			url += "&addSavePath=" + addSavePath;
		}
		var jsonFileArray = JSON.parse(fileObj);
		
		
		$.each(jsonFileArray, function(i, jsonObj) {
			if(jsonObj.atchmnflNm != undefined && jsonObj.atchmnflStreNm != undefined && jsonObj.atchmnflMg != undefined) {
				var fileHtml = "";	
				var fileName = jsonObj.atchmnflNm;
				var realFileName= jsonObj.atchmnflStreNm;
				var fileSize= jsonObj.atchmnflMg;
				
				var fileInfo = fileName+"|"+realFileName+"| |"+ fileSize;
				
				if(fileHtml != "") {
					fileHtml += "	<br />";
				}
				
				fileHtml += " <li name=\"fileGubun"+idx+"\"> ";
                fileHtml += "   <span class=\"m-r-10 font-dark-grey underline\"><a href=\""+url+"&getfile="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(fileName))+"\" target=\"fileHiddenFrame\">"+decodeURI(decodeURIComponent(fileName))+"</a><input type=\"hidden\" name=\"addFileList"+idx+"\" value=\""+ decodeURI(decodeURIComponent(fileInfo)) +"\" ></span>";
                fileHtml += "   <a href=\"#none\" class=\"btn-delete\" onclick=\"onFileDelede(this)\">&times;</a> ";
                fileHtml += " </li> ";
				
				$("#uploaded-files"+idx).append(fileHtml);
				
				fileYn = "Y";
			}
		});
		
	}
}

//삭제
function onFileDelede(obj) {
	$(obj).parent().remove();
}