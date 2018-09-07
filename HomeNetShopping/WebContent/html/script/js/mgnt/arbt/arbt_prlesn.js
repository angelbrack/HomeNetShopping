/**
 * 이메일 도메인 셋팅
 * @param emailDomain
 * @returns
 */
function fnSetEmailDomain(emailDomain) {
	$("#scndEmailCtplc").val(emailDomain);
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
	frm.action = CTX_PATH + "/mgnt/arbt/prlesn/prlesnList.do";
	frm.submit();	
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

	var chktext = /[ \{\}\[\]\?,;:|\)*~`!^\-_+\u253c<>@\#$%&\'\"\\\(\=]/gi;
	
	if(chktext.test($("#searchWord").val())) {
		alert("특수문자는 허용되지 않습니다.");
		return;
	}
	
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/prlesn/prlesnList.do";
	frm.submit();
}

/**
 * 등록/수정 페이지 이동
 * @param prlesnNo
 * @returns
 */
function onEditPage(prlesnNo) {
	var frm = document.form1;
	$("#prlesnNo").val(prlesnNo);
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/prlesn/prlesnHandle.do";
	frm.submit();
}

/**
 * 상세페이지 이동
 * @param prlesnNo
 * @returns
 */
function onViewPage(prlesnNo) {
	var frm = document.form1;
	$("#prlesnNo").val(prlesnNo);
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/prlesn/prlesnView.do";
	frm.submit();
}

/**
 * 과외 아르바이트 정보 저장
 * @returns
 */
function onSaveData() {
	
	$("#emailTmp").val($("#fstEmailCtplc").val()+"@"+$("#scndEmailCtplc").val());
	
	$("#form1").validInit({onsubmit : false, onfocusout : false});
	var option = {};
	
	option = {
		"validList" : [
		      {"objId" : "prlesnAplcPnNm", 			"label" : "성명", 		"rule" : {"required":true, "maxlength":200 }}
	        , {"objId" : "fstTlno", 				"label" : "전화번호", 		"rule" : {"required":true, "number":true, "range":[0, 9999]} }
	        , {"objId" : "scndTlno", 				"label" : "전화번호", 		"rule" : {"required":true, "number":true, "range":[0, 9999]} }
	        , {"objId" : "thrdTlno", 				"label" : "전화번호", 		"rule" : {"required":true, "number":true, "range":[0, 9999]} }
	        , {"objId" : "fstMblTlno", 				"label" : "핸드폰", 		"rule" : {"required":true, "number":true, "range":[0, 9999]} }
	        , {"objId" : "scndMblTlno", 			"label" : "핸드폰", 		"rule" : {"required":true, "number":true, "range":[0, 9999]} }
	        , {"objId" : "thrdMblTlno", 			"label" : "핸드폰", 		"rule" : {"required":true, "number":true, "range":[0, 9999]} }
	        , {"objId" : "fstEmailCtplc", 			"label" : "이메일", 		"rule" : {"required":true, "maxlength":255} }
	        , {"objId" : "scndEmailCtplc", 			"label" : "이메일", 		"rule" : {"required":true, "maxlength":255} }
	        , {"objId" : "emailTmp", 				"label" : "이메일", 		"rule" : {"required":true, "email":true} }
	        , {"objId" : "zpno", 					"label" : "지번", 		"rule" : {"required":true, "maxlength":5} }
	        , {"objId" : "bpnoAdd", 				"label" : "주소", 		"rule" : {"required":true, "maxlength":255} }
	        , {"objId" : "bpsnoDtlAddr", 			"label" : "상세주소", 		"rule" : {"required":true, "maxlength":255} }
	        , {"objId" : "studPnn", 				"label" : "학생성명", 		"rule" : {"required":true, "maxlength":100} }
	        , {"objId" : "prlesnTgtCds", 			"label" : "과외대상", 		"rule" : {"required":true} }
	        , {"objId" : "studSexCd", 				"label" : "학생성별", 		"rule" : {"required":true} }
	        , {"objId" : "prlesnZonCd", 			"label" : "과외지역", 		"rule" : {"required":true} }
	        , {"objId" : "prlesnZonDtc", 			"label" : "과외상세지역", 	"rule" : {"required":true} }
	        , {"objId" : "tgtSbjtCds", 				"label" : "희망과목", 		"rule" : {"required":true} }
	        , {"objId" : "prlesnXpCd", 				"label" : "급여", 		"rule" : {"required":true} }
	        , {"objId" : "prlesnHrCd", 				"label" : "과외시간", 		"rule" : {"required":true} }
	        , {"objId" : "lctrClcd", 				"label" : "희망선생님", 	"rule" : {"required":true} }
	        , {"objId" : "lctrSexCd", 				"label" : "희망선생님성별", "rule" : {"required":true} }
	        , {"objId" : "lctrShgrCd", 				"label" : "희망선생님학년", "rule" : {"required":true} }
	        , {"objId" : "etcCn", 					"label" : "기타 희망사항", 	"rule" : {"required":true, "maxlength":4000} }
			
		]
	};
	
	
	$("#form1").validAddRules(option);		
	if($("#form1").validate().form() == false) {
    	return false;
	}
	
	if(confirm("저장하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/saveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
				if($("#prlesnNo").val() == "") {
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
 * 과외 아르바이트 정보 복사
 * @param prlesnNo
 * @returns
 */
function onCopyData(prlesnNo) {
	if(confirm("복사하시겠습니까?")) {
		$("#prlesnNo").val(prlesnNo);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/copyData.ajax",
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
 * 과외 아르바이트 정보 삭제
 * @returns
 */
function onDeleteData() {
	if(confirm("삭제하시겠습니까? 삭제시 지원자정보까지 삭제됩니다.")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/deleteData.ajax",
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
 * 과외 아르바이트 목록 정보 삭제
 * @returns
 */
function onDeleteDatas() {
	
	if($('input:checkbox[name="prlesnNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("삭제하시겠습니까? 삭제시 지원자정보까지 삭제됩니다.")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/deleteDatas.ajax",
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
function onApplySaveData(prlesnStcd) {
	if(confirm("저장하시겠습니까?")) {
		$("#prlesnStcd").val(prlesnStcd);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/applySaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
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
	if($('input:checkbox[name="prlesnNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("마감 하시겠습니까?")) {
		$("#prlesnStcd").val("");
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/finishSaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
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
		url: CTX_PATH + "/mgnt/arbt/prlesn/prlesnAplcExcelList.ajax",
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

/**
 * 메모저장
 * @returns
 */
function onMemoCnSaveData() {
	$("#form1").validInit({onsubmit : false, onfocusout : false});
	var option = {};
	
	option = {
		"validList" : [
	        {"objId" : "moCn",	"label" : "MEMO", 	"rule" : {"required":true, "maxlength":4000} }
		]
	};
	
	$("#form1").validAddRules(option);		
	if($("#form1").validate().form() == false) {
    	return false;
	}
	
	if(confirm("저장하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/memoCnSaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 메모삭제
 * @returns
 */
function onMemoCnDeleteData() {
	if(confirm("삭제 하시겠습니까?")) {
		$("#moCn").val("");
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/prlesn/memoCnSaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("삭제되었습니다.");
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}
