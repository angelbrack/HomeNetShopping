/**
 * 상세페이지 지원자 체크박스 전체선택
 * @returns
 */
var allScgdArbtAplcNos = true;
function onCheckAll() {
	$('input:checkbox[name="scgdArbtAplcNos"]').prop("checked", allScgdArbtAplcNos);
	if(allScgdArbtAplcNos) {
		allScgdArbtAplcNos = false;
	} else {
		allScgdArbtAplcNos = true;
	}
}

/**
 * 목록 검색
 * @param pagePerLine
 * @returns
 */
function onListPage(pageNo) {
	var frm = document.form1;
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/scgd/scgdList.do";
	frm.submit();	
}

/**
 * 정렬검색
 * @param orderCol
 * @param orderSort
 * @returns
 */
function fnOrderSearch(orderCol, orderSort) {
	$("#form1 #orderCol").val(orderCol);
	$("#form1 #orderSort").val(orderSort);
	
	onListPage(1);
}

/**
 * 목록 검색
 * @param pagePerLine
 * @returns
 */
function onListPerLine(pagePerLine) {
	var frm = document.form1;
	
	frm.currentPage.value = "1";
	frm.recordCountPerPage.value = pagePerLine;
	frm.target = "_self";
	frm.action = url2;
	frm.submit();
}

/**
 * 목록 검색
 * @param pagePerLine
 * @returns
 */
function onSearch() {
	var frm = document.form1;

	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/scgd/scgdList.do";
	frm.submit();
}

/**
 * 상세페이지 지원자 목록 검색
 * @param pagePerLine
 * @returns
 */
function onListPage2(pageNo) {
	var frm = document.form1;
	
	frm.currentSubPage.value = pageNo;
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/scgd/scgdView.do";
	frm.submit();	
}

/**
 * 상세페이지 지원자 목록 검색
 * @param pagePerLine
 * @returns
 */
function onListPerLine2(pagePerLine) {
	var frm = document.form1;
	
	frm.currentSubPage.value = "1";
	frm.subRecordCountPerPage.value = pagePerLine;
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/scgd/scgdView.do";
	frm.submit();
}

/**
 * 상세페이지 지원자 목록 검색
 * @returns
 */
function onSearch2() {
	var frm = document.form1;
	
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/scgd/scgdView.do";
	frm.submit();
}

/**
 * 등록/수정 페이지 이동
 * @param arbtInfoNo
 * @returns
 */
function onEditPage(arbtInfoNo) {
	var frm = document.form1;
	$("#arbtInfoNo").val(arbtInfoNo);
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/scgd/scgdHandle.do";
	frm.submit();
}

/**
 * 상세페이지 이동
 * @param arbtInfoNo
 * @returns
 */
function onViewPage(arbtInfoNo) {
	var frm = document.form1;
	$("#arbtInfoNo").val(arbtInfoNo);
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/scgd/scgdView.do";
	frm.submit();
}

/**
 * 아르바이트 정보 저장
 * @returns
 */
function onSaveData() {
	$("#form1").validInit({onsubmit : false, onfocusout : false});
	var option = {};
	
	option = {
		"validList" : [
		      {"objId" : "scgdDpnm", 		"label" : "부서명", 		"rule" : {"required":true,  "maxlength":200 }}
			, {"objId" : "scgdChpnNm", 		"label" : "담당자명", 		"rule" : {"required":true,  "maxlength":100 }}
			, {"objId" : "scgdEtnsNo", 		"label" : "내선번호", 		"rule" : {"required":true,  "maxlength":30  }}
			, {"objId" : "titNm", 			"label" : "공고제목", 		"rule" : {"required":true,  "maxlength":200  }}
			, {"objId" : "scgdRcrtKoocCd", 	"label" : "모집직종", 		"rule" : {"required":true}}
			, {"objId" : "scgdWrkPlcNm", 	"label" : "근무지(건물명,호수)", 		"rule" : {"required":true,  "maxlength":200  }}
			, {"objId" : "wrkTeCd", 		"label" : "근무기간", 		"rule" : {"required":true}}
			, {"objId" : "wrkDowCd", 		"label" : "근무요일", 		"rule" : {"required":true}}			
			, {"objId" : "wrkEtcNm", 		"label" : "근무기타명", 	"rule" : {"maxlength":200}}
			, {"objId" : "wrkHrCd", 		"label" : "근무시간", 		"rule" : {"required":true}}
			, {"objId" : "wrkSrtTmHh", 		"label" : "근무시간시작(시)", 		"rule" : {"required":true}}
			, {"objId" : "wrkSrtTmMnt", 	"label" : "근무시간시작(분)", 		"rule" : {"required":true}}
			, {"objId" : "wrkEndTmHh", 		"label" : "근무시간종료(시)", 		"rule" : {"required":true}}
			, {"objId" : "wrkEndTmMnt", 	"label" : "근무시간종료(분)", 		"rule" : {"required":true}}
			, {"objId" : "salrDvCd", 		"label" : "급여구분", 		"rule" : {"required":true}}
			, {"objId" : "salrAm", 			"label" : "급여금액", 		"rule" : {"required":true, "number":true, "range":[0, 99999999]}}
			, {"objId" : "scgdRcrtPers", 	"label" : "모집인원", 		"rule" : {"required":true, "number":true, "range":[0, 99999999]}}
			, {"objId" : "arbtSexDc", 		"label" : "성별", 		"rule" : {"required":true}}
			, {"objId" : "scgdArbtSeltClseDt", 		"label" : "선발마감일자", 		"rule" : {"required":true, "dateISO":true}}
			, {"objId" : "arbtRcvClseCd", 	"label" : "접수마감", 		"rule" : {"required":true}}
			, {"objId" : "rcvClseDt", 		"label" : "접수마감일자", 		"rule" : {"dateISO":true}}
			, {"objId" : "scgdArbtRcvMethCds", 		"label" : "접수방법", 		"rule" : {"required":true}}
			
		]
	};
	
	
	$("#form1").validAddRules(option);		
	if($("#form1").validate().form() == false) {
    	return false;
	}
	
	if($("#wrkDowCd").val() == "99") {
		if($("#wrkEtcNm").val() == "") {
			alert("근무기타명은 필수 입력입니다.");
			$("#wrkEtcNm").focus();
			return false;
		}
	}
	
	// 상세내용
	oEditors.getById["arbtCn"].exec("UPDATE_CONTENTS_FIELD", []);
	if(document.getElementById("arbtCn").value.trim() == "<br>" || document.getElementById("arbtCn").value.trim() == "<p><br></p>") {
		alert("모집내용은 필수 입력입니다.");
	    oEditors.getById["arbtCn"].exec("FOCUS");
	    return false;
    }
	$("#arbtCn").val(document.getElementById("arbtCn").value.trim());
	
	if($("input:radio[name=arbtRcvClseCd]:checked").val() == "01") {
		if($("#rcvClseDt").val() == "") {
			alert("접수마감일자는 필수 입력입니다.");
			$("#rcvClseDt").focus();
			return false;
		}
	}
	
	if(confirm("저장하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/saveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");

				if($("#arbtInfoNo").val() == "") {
					onListPage(1);
				} else {
					onListPage($("#currentPage").val());
				}
				
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 아르바이트 정보 복사
 * @param arbtInfoNo
 * @returns
 */
function onCopyData(arbtInfoNo) {
	if(confirm("복사하시겠습니까?")) {
		$("#arbtInfoNo").val(arbtInfoNo);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/copyData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("복사되었습니다.");
				onListPage(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 지원자 합격정보 저장
 * @param scgdArbtAplcStcd
 * @returns
 */
function onPassSaveData(scgdArbtAplcStcd) {
	if($('input:checkbox[name="scgdArbtAplcNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("저장하시겠습니까?")) {
		$("#scgdArbtAplcStcd").val(scgdArbtAplcStcd);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/passSaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
				onListPage(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 지원자 정보 삭제
 * @returns
 */
function onApplyDeleteData() {
	if($('input:checkbox[name="scgdArbtAplcNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("저장하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/deleteAplcData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("삭제되었습니다.");
				onSearch2(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

//	블랙리스트
function fnSpclManagePop() {
	
	var idx			= 0;
	var userNo		= "";
	
	if($('input:checkbox[name="scgdArbtAplcNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	// 블랙리스트 폼 생성
	var $form = fnAddForm('formSpcl');
	$form.empty();
	
	$("input:checkbox[name=scgdArbtAplcNos]:checked").each(function(){
		userNo 		= $(this).attr("userNo");
		
		fnAddInput($form, "userNoList_"+idx, "userNoList", userNo);
		
		idx++;
	});
	
	/*
	 * 페널티구분코드
	 */
	fnAddInput($form, "pntDvCd", "pntDvCd", "01");
	
	// 블랙리스트 팝업
	fnSendSpclManagePop($form);
}


/**
 * 아르바이트 정보 삭제
 * @returns
 */
function onDeleteData() {
	if(confirm("삭제하시겠습니까? 삭제시 지원자정보까지 삭제됩니다.")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/deleteData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("삭제되었습니다.");
				onListPage(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 아르바이트 목록 정보 삭제
 * @returns
 */
function onDeleteDatas() {
	
	if($('input:checkbox[name="arbtInfoNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("삭제하시겠습니까? 삭제시 지원자정보까지 삭제됩니다.")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/deleteDatas.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("삭제되었습니다.");
				onListPage(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 승인프로세스 저장
 * @param arbtStcd
 * @returns
 */
function onApplySaveData(arbtStcd) {
	if(confirm("저장하시겠습니까?")) {
		$("#arbtStcd").val(arbtStcd);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/applySaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
				onListPage2(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 선발마감 저장
 * @returns
 */
function onFinishSeltData() {
	
	if(confirm("선발마감 하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/finishSeltSaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
				onListPage2(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 마감저장
 * @returns
 */
function onFinishData() {
	if($('input:checkbox[name="arbtInfoNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("마감 하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/scgd/finishSaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
				onListPage2(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 엑셀다운로드
 * @returns
 */
function onExcelDown() {
	
	$.ajax({
		async : false,
		type: 'POST',
		url: CTX_PATH + "/mgnt/arbt/scgd/scgdAplcExcelList.ajax",
		data: $("#form1").serialize(),
		dataType:"json",
		success : function (data) {
			var downUrl = "/upload?pathkey=COMMON.EXCEL.DOWN&getfile="+data.fileName+"&realFileName="+data.fileName;
			
			$("#fileHiddenFrame").attr("src", downUrl);
		}, 
		error: function(data, textStatus, errorThrown) {
			fnAjaxError("에러가 발생하였습니다.");
		}
	});
}
