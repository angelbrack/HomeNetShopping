/**
 * 첨부파일 팝업 창을 띄움.
 * 첨부가능한 파일 수 만큼 첨부파일 가능
 * filePathKey : 업로드 경로 설정 키
 * callBackFun : 파일업로드 후 호출되는 return function
 * selObj : fileUpload object
 * maxUpCnt : 맥스 업로드 가능 파일 수
 * USE : onFileUpload('TEST.COMMON.BOARD', 'onFileReturn', document.getElementById("fileInfo"), 3, '01');
 */
function onFileUpload(filePathKey, callBackFun, selObj, maxUpCnt) {
	if (typeof(selObj) != 'object') {
		alert("파일업로드 object가 존재하지 않습니다.");
		return;
	} else if (selObj.tagName == 'SELECT') {
		if (selObj.length > maxUpCnt) {
			alert("더이상 파일을 업로드할 수 없습니다.");
			return;
		}		
	} else if (selObj.tagName == 'INPUT') {
		if (selObj.value != '') {
			alert("더이상 파일을 업로드할 수 없습니다.");
			return;
		}		
	}
	
	var url = "/common/fileuploadForm.do";
	url += "?filePathKey="+filePathKey;
	
	if (callBackFun != '' && typeof (callBackFun) != 'undefined') {
		url += "&callback="+callBackFun;
	}
	
	var opt = "width=380, height=253";
	window.open(url, "UPLOAD_POP", opt);  
}

/**
 * 첨부 파일 다운로드
 * @param filePathKey : 첨부파일경로 KEY
 * @param selObj	  : 첨부파일 대상 OBJECT
 */
function onFileDownload(filePathKey, selObj) {
	if (typeof(selObj) != 'object') {return ;}
	
	if (selObj.tagName == 'SELECT') {
		_selectFileDownload(filePathKey, selObj);
	} else {
		_inputFileDownload(filePathKey, selObj);
	}
}

/**
 * SELECT BOX 형 첨부파일다운로드
 * @param filePathKey
 * @param selObj
 * @returns {Boolean}
 */
function _selectFileDownload(filePathKey, selObj) {
	var fileName = "";
	var fileInfo = "";
	var selectCnt = 0;
	
	for(var i=1; i < selObj.length; i++){
		if (selObj.options[i].selected == true) {
			selectCnt++;
		}
	}
	
	if(selObj.length == 1){
		alert("다운로드 할 파일이 없습니다.");
		return false;
	}else if(selObj.options[0].selected == true || selectCnt == 0) {
		alert("다운로드 할 파일을 선택하세요.");
		return false;
	}
	
	for(var i=1; i < selObj.length; i++) {
		if(selObj.options[i].selected == true) {
			fileInfo = selObj.options[i].value;
			
			location.href = "/common/fileDownload.do?filePathKey="+filePathKey+"&fileInfo="+fileInfo;
		}
	}
}

/**
 * INPUT BOX 형 첨부파일 다운로드
 * @param filePathKey
 * @param selObj
 */
function _inputFileDownload(filePathKey, selObj) {
	var fileName = "";
	var fileInfo = "";
	var selectCnt = 0;
	
	if(selObj.value == ""){
		alert("다운로드 할 파일이 없습니다.");
		return false;
	}
	
	fileInfo = selObj.value;
	
	location.href = "/common/fileDownload.do?filePathKey="+filePathKey+"&fileInfo="+fileInfo;	
}

/**
 * 이미 업로드된 파일 조회 다운로드
 * atchFileNm : 원래파일명
 * atchFileSaveNm : 저장된파일명
 * atchFileSz : 첨부파일 사이즈
 */
function onViewFileDownload(filePathKey, atchFileNm, atchFileSaveNm, atchFileSz) {	
	var fileInfo = "02|";
	fileInfo += encodeURIComponent(atchFileNm);
	fileInfo += "|";
	fileInfo += atchFileSaveNm;
	fileInfo += "|";
	fileInfo += "|";
	fileInfo += atchFileSz;
	fileInfo += "|";
	
	location.href = "/common/fileDownload.do?filePathKey="+filePathKey+"&fileInfo="+fileInfo;
}

/**
 * 첨부된 파일 삭제
 * selObj : 파일업로드된 목록을 보여주는 obj (document.getElementById)
 * filePathKey : 파일업로드 경로
 */
function onFileDelete(filePathKey, selObj, callBackFn) {
	if (typeof(selObj) != 'object') {return ;}
	
	if (selObj.tagName == 'SELECT') {
		_selectFileDelete(filePathKey, selObj, callBackFn);
	} else if (selObj.tagName == 'INPUT'){
		_inputFileDelete(filePathKey, selObj, callBackFn);
	} else {
		return;
	}
}

function _inputFileDelete(filePathKey, selObj, callBackFn) {
	var fileInfo = "";
	
	if(selObj.value == ""){
		alert("삭제 할 파일이 없습니다.");
		return false;
	}

	var result = confirm("삭제하시겠습니까?");
	
	if(!result) {
		return;
	}	
	
	fileInfo = selObj.value;

	jQuery.ajax({
		type: 'post'
		, url: "/common/fileDelete.do?filePathKey="+filePathKey+"&fileInfo="+encodeURIComponent(fileInfo)
		, contentType: 'application/x-wwwform-urlencoded; charset=UTF-8'
		, dataType: 'json'
		, success: function(data) {
			if (data != null) {
				eval(callBackFn + '(data)');
			} else {
				alert('NULL');
				return false;
			}
		}
		, error: function(data, textStatus, errorThrown) {
			alert('ERROR');
		}
	});
	
	selObj.value = "";
}

function _selectFileDelete(filePathKey, selObj, callBackFn) {
	var fileInfo = "";
	var selectCnt = 0;
	
	for(var i=1; i < selObj.length; i++){
		if (selObj.options[i].selected == true) {
			selectCnt++;
		}
	}
	
	if(selObj.length == 1){
		alert("삭제 할 파일이 없습니다.");
		return false;
	}else if(selObj.options[0].selected == true || selectCnt == 0) {
		alert("삭제 할 파일을 선택하세요.");
		return false;
	}

	var result = confirm("삭제하시겠습니까?");
	if(!result) {
		return;
	}	
	
	var len = selObj.length;
	var idx;
	for(var i=len-1; i >= 1; i--) {
		if(selObj.options[i].selected == true) {
			fileInfo = selObj.options[i].value;

			jQuery.ajax({
				type: 'post'
				, url: "/common/fileDelete.do?filePathKey="+filePathKey+"&fileInfo="+encodeURIComponent(fileInfo)
				, contentType: 'application/x-wwwform-urlencoded; charset=UTF-8'
				, dataType: 'json'
				, success: function(data) {
					if (data != null) {
						eval(callBackFn + '(data)');
					} else {
						alert('NULL');
						return false;
					}
				}
				, error: function(data, textStatus, errorThrown) {
					alert('ERROR');
				}
			});
			
			selObj.options[i] = null;
		}
	}	
}

/**
 * 공통 파일 업로드 -> RichUpload 유형으로 변환
 * 첨부된 파일을 다운로드 하거나 삭제 하기위해서는 파일 업로드 정보 형태로 데이터를 전송해야 함.
 * 수정구분|원본파일명|서버에저장된파일명|확장자|파일사이지|첨부파일번호
 */
function changeFileInfoToRichUploadInfo(fileInfo) {
	var richUploadInfo;
	var infos = fileInfo.split("|");
	
	try {
		if (infos == null || infos.length != 6) {
			alert("잘못된 파일정보 입니다.");
			return "";
		} else {
			richUploadInfo = infos[1]+"|"+infos[2]+"|"+infos[3]+"|"+infos[4];		
		}		
	} catch(e) {
		return "";
	}
	
	return richUploadInfo;
}

/**
 * 현재 사용하는 곳 없음 
 * RichUpload 유형 -> 공통 파일 업로드 유형으로 변환
 * 첨부된 파일을 다운로드 하거나 삭제 하기위해서는 파일 업로드 정보 형태로 데이터를 전송해야 함.
 */
function changeRichUploadInfoToFileInfo(richUploadInfo, fileExistCd, fileSeqNo) {
	var fileInfo;
	var infos = richUploadInfo.split("|");
	
	try {
		if (infos == null || infos.length != 4) {
			alert("잘못된 파일정보 입니다.");
			return "";
		} else {
			fileInfo = fileExistCd +"|"+infos[0]+"|"+infos[1]+"|"+infos[2]+"|"+infos[3]+"|"+fileSeqNo;	
		}		
	} catch(e) {
		return "";
	}
	
	return fileInfo;
}

/**
 * TODO : 개발자 구현
 * RETURN data 정의서
 * data.FILE_INFO     : 삭제 요청한 파일 정보(수정구분|원본파일명|서버에저장된파일명|확장자|파일사이지|첨부파일번호)
 * data.FILE_UPLOAD_CD : 파일 업로드 코드 (01 : 초기 업로드(DB저장안됨), 02 : DB등록완료)
 * data.ORI_FILE_NAME : 실제 파일 명
 * data.CHG_FILE_NAME : 서버에 저장된 파일 명
 * data.FILE_SIZE     : 첨부파일 사이즈
 * data.FILE_EXT      : 파일 확장자
 * data.RSLT_CD       : 삭제 결과(00 : 정상삭제, 99 : 파라미터 오류, 90 : 기본경로가 존재하지 않음, 70 : 파일이 존재하지 않음. 60 : 첨부파일 삭제중 오류 발생, 50 : 잘못된 파일명을 삭제요청하여 오류 발생)
 * data.RSLT_MSG      : 파일삭제 결과 메시지 (data.RSLT_CD 로 따로 메시지를 띄우는걸 추천 함)
 */
function _sample_callBackDeleteFile(data) {
	var richFileInfo;
	if (data.RSLT_CD == '00') {
		richFileInfo = changeFileInfoToRichUploadInfo(data.FILE_INFO);
		
		if(data.FILE_UPLOAD_CD == "02") {
			// 이미 데이터가 등록된 첨부파일 삭제시 삭제한 파일정보 저장 (업부 저장시 사용할 경우 아래 주석을 풀어 파라미터를 받아 처리)
			delArr.put(richFileInfo, richFileInfo);
		} 
		
		addArr.remove(richFileInfo);
		
		$("#delFileList").val(delArr.keys());  // 현재 파일삭제시 디비 작업을 바로 하고 있음으로 필요없음.
		$("#addFileList").val(addArr.keys());
	}
}