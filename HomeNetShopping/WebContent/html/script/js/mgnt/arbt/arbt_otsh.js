

/**
 * 아르바이트 정보 저장
 * @returns
 */
function onSaveData() {
	
	$("#form1").validInit({onsubmit : false, onfocusout : false});
	var option = {};
	
	option = {
		"validList" : [
			  	  {"objId" : "etrNm", 					"label" : "기업명", 			"rule" : {"required":true,  "maxlength":100 }}
				, {"objId" : "chpnNm", 					"label" : "담당자명", 			"rule" : {"required":true,  "maxlength":100 }}
				, {"objId" : "chpnFstEmailCtplc", 		"label" : "이메일", 			"rule" : {"required":true,  "maxlength":100  }}
				, {"objId" : "chpnScndEmailCtplc", 		"label" : "이메일", 			"rule" : {"required":true,  "maxlength":100  }}
				, {"objId" : "chpnFstTlno", 			"label" : "문의처", 			"rule" : {"required":true, "number":true, "range":[0, 9999]} }
		        , {"objId" : "chpnScndTlno", 			"label" : "문의처", 			"rule" : {"required":true, "number":true, "range":[0, 9999]} }
		        , {"objId" : "chpnThrdTlno", 			"label" : "문의처", 			"rule" : {"required":true, "number":true, "range":[0, 9999]} }
		        , {"objId" : "zpno", 					"label" : "지번", 			"rule" : {"required":true, "maxlength":5} }
		        , {"objId" : "roadNmAddr", 				"label" : "주소", 			"rule" : {"required":true, "maxlength":255} }
		        , {"objId" : "roadNmDtlAddr", 			"label" : "상세주소", 			"rule" : {"required":true, "maxlength":255} }
		        , {"objId" : "hmpgUrl", 				"label" : "홈페이지", 			"rule" : {"required":true, "maxlength":255} }
				, {"objId" : "titNm", 					"label" : "공고제목", 			"rule" : {"required":true,  "maxlength":200  }}
				, {"objId" : "arbtRcrtKoocCd", 			"label" : "모집직종", 			"rule" : {"required":true}}
				, {"objId" : "wrkDtlZonCd", 			"label" : "근무상세지역", 		"rule" : {"required":true}}
				, {"objId" : "eclwDvCd", 				"label" : "전철구분", 			"rule" : {"required":true}}
				, {"objId" : "eclwStatnCd", 			"label" : "전철역", 			"rule" : {"required":true}}
				, {"objId" : "extLocNm", 				"label" : "출구", 			"rule" : {"maxlength":100}}
				, {"objId" : "wrkTeCd", 				"label" : "근무기간", 			"rule" : {"required":true}}
				, {"objId" : "wrkDowCd", 				"label" : "근무요일", 			"rule" : {"required":true}}
				, {"objId" : "wrkEtcNm", 				"label" : "근무기타명", 		"rule" : {"maxlength":200}}
				, {"objId" : "wrkHrCd", 				"label" : "근무시간", 			"rule" : {"required":true}}
				, {"objId" : "wrkSrtTmHh", 				"label" : "근무시간시작(시)", 	"rule" : {"required":true}}
				, {"objId" : "wrkSrtTmMnt", 			"label" : "근무시간시작(분)", 	"rule" : {"required":true}}
				, {"objId" : "wrkEndTmHh", 				"label" : "근무시간종료(시)", 	"rule" : {"required":true}}
				, {"objId" : "wrkEndTmMnt", 			"label" : "근무시간종료(분)", 	"rule" : {"required":true}}
				, {"objId" : "salrDvCd", 				"label" : "급여구분", 			"rule" : {"required":true}}
				, {"objId" : "salrAm", 					"label" : "급여금액", 			"rule" : {"required":true, "number":true, "range":[0, 99999999]}}
				, {"objId" : "scgdRcrtPers", 			"label" : "모집인원", 			"rule" : {"required":true, "number":true, "range":[0, 99999999]}}
				, {"objId" : "arbtSexDc", 				"label" : "성별", 			"rule" : {"required":true}}
				, {"objId" : "arbtRcvClseCd", 			"label" : "접수마감", 			"rule" : {"required":true}}
				, {"objId" : "rcvClseDt", 				"label" : "접수마감일자", 		"rule" : {"dateISO":true}}
				, {"objId" : "genArbtRcvMethCds", 		"label" : "접수방법", 			"rule" : {"required":true}}
			
		]
	};
	
	
	$("#form1").validAddRules(option);		
	if($("#form1").validate().form() == false) {
    	return false;
	}
	
	//직접입력시 체크
	var directYn = $("input:checkbox[name=directYn]").is(":checked");
	if(directYn) {
		if($("#bizJangNo").val() == "") {
			alert("사업자번호는 필수 입력입니다.");
			$("#bizJangNo").focus();
			return false;
		}
		if($("#etrFomCd").val() == "") {
			alert("기업형태는 필수 입력입니다.");
			$("#etrFomCd").focus();
			return false;
		}
		if($("#zonCd").val() == "") {
			alert("지역은 필수 입력입니다.");
			$("#zonCd").focus();
			return false;
		}
	}
	
	if($("#wrkZonCd").val() == "" && !$("input:checkbox[name=hmtrcWrkYn]").is(":checked")) {
			alert("근무지역은 필수 입력입니다.");
			$("#wrkZonCd").focus();
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
			url: CTX_PATH + "/mgnt/arbt/otsh/saveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
				if($("#genArbtNo").val() == "") {
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
 * 상세페이지 지원자 체크박스 전체선택
 * @returns
 */
var allCnfmYns = true;
function onCheckAll() {
	$('input:checkbox[name="otshArbtAplcNos"]').prop("checked", allCnfmYns);
	if(allCnfmYns) {
		allCnfmYns = false;
	} else {
		allCnfmYns = true;
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
	frm.action = CTX_PATH + "/mgnt/arbt/otsh/otshList.do";
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

	var chktext = /[ \{\}\[\]\?,;:|\)*~`!^\-_+\u253c<>@\#$%&\'\"\\\(\=]/gi;
	
	if(chktext.test($("#searchWord").val())) {
		alert("특수문자는 허용되지 않습니다.");
		return;
	}
	
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/otsh/otshList.do";
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
	frm.action = CTX_PATH + "/mgnt/arbt/otsh/otshView.do";
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
	frm.action = CTX_PATH + "/mgnt/arbt/otsh/otshView.do";
	frm.submit();
}

/**
 * 상세페이지 지원자 목록 검색
 * @returns
 */
function onSearch2() {
	var frm = document.form1;
	
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/otsh/otshView.do";
	frm.submit();
}

/**
 * 등록/수정 페이지 이동
 * @param genArbtNo
 * @returns
 */
function onEditPage(genArbtNo) {
	var frm = document.form1;
	$("#genArbtNo").val(genArbtNo);
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/otsh/otshHandle.do";
	frm.submit();
}

/**
 * 상세페이지 이동
 * @param genArbtNo
 * @returns
 */
function onViewPage(genArbtNo) {
	var frm = document.form1;
	$("#genArbtNo").val(genArbtNo);
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/arbt/otsh/otshView.do";
	frm.submit();
}

//기업정보 검색
function fnSearchCompany() {
	// 기업정보 폼 생성
	var $form = fnAddForm('formComp');
	$form.empty();
	
	//fnAddInput($form, "pntDvCd", "pntDvCd", "01");
	
	fnCompanyPop($form);
}

function selCompanyCallback(companyInfo) {
	var etrNo 			= companyInfo.etrNo 		;		
	var etrNm 	        = companyInfo.etrNm 		;
	var bizJangNo	    = companyInfo.bizJangNo	    ;
	var etrFomCd 	    = companyInfo.etrFomCd 	    ;
	var etrFomCdNm      = companyInfo.etrFomCdNm	;
	var zonCd 	        = companyInfo.zonCd 		;
	var zonCdNm 	    = companyInfo.zonCdNm 	    ;
	var hmpgUrl         = companyInfo.hmpgUrl		;
	
	$("#etrNo").val(etrNo);
	$("#etrNm").val(etrNm);
	$("#bizJangNo").val(bizJangNo);
	
	$("input:radio[name=etrFomCd]").each(function() {
		if (this.value == etrFomCd) {
			this.checked = true;
			return;
		}
	});
	
	$("input:radio[name=zonCd]").each(function() {
		if (this.value == zonCd) {
			this.checked = true;
			return;
		}
	});
	
	$("#hmpgUrl").val(hmpgUrl);
}

// 기업정보 직접입력 여부 셋팅
function fnDirectInput() {
	var directYn = $("input:checkbox[name=directYn]").is(":checked");
	
	$("#etrNm").val("");	
	$("#etrNo").val("");
	$("#bizJangNo").val("");
	
	$("input[name=etrFomCd]").eq(0).attr("checked", true);
	$("input[name=zonCd]").eq(0).attr("checked", true);
	
	if ( directYn == true ) {
		$(".etrDirect").show();
		$("#etrNm").prop("readonly", false).removeClass("disabled");
		$("#btnSearchCompany").hide();
		$("#bizJangNo").prop("readonly", false).removeClass("disabled");
		$("input:radio[name=etrFomCd]").prop("readonly", false).removeClass("disabled");
		$("input:radio[name=zonCd]").prop("readonly", false).removeClass("disabled");
		
	} else {
		$(".etrDirect").hide();
		$("#etrNm").prop("readonly", true).addClass("disabled");
		$("#btnSearchCompany").show();
		$("#bizJangNo").prop("readonly", true).addClass("disabled");
		$("input:radio[name=etrFomCd]").prop("readonly", true).addClass("disabled");
		$("input:radio[name=zonCd]").prop("readonly", true).addClass("disabled");
	}
}

//모집직종 팝업
function fnArbtRcrtKoocPop() {
	var ARBTRCRTKOOC_POP = window.open(CTX_PATH+"/mgnt/arbt/otsh/otshRcrtKoocPopup.do", 'ARBTRCRTKOOC_POP' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
	ARBTRCRTKOOC_POP.focus();   
}

//모집직종 팝업 셋
function fnSelArbtRcrtKoocPop(resultList) {
	var arbtRcrtKoocList = JSON.parse(resultList);
	var arbtRcrtKoocCd = "";
	var arbtRcrtKoocNm = "";
	$.each(arbtRcrtKoocList, function(key, obj) {
		if(arbtRcrtKoocCd != "") {
			arbtRcrtKoocCd += ",";
		}
		arbtRcrtKoocCd += obj.cdId;
		
		if(arbtRcrtKoocNm != "") {
			arbtRcrtKoocNm += ",";
		}
		arbtRcrtKoocNm += obj.cdNm;
	});
	
	$("#arbtRcrtKoocCd").val(arbtRcrtKoocCd);
	$("#arbtRcrtKoocNm").val(arbtRcrtKoocNm);
}


/**
 * 이메일 도메인 셋팅
 * @param emailDomain
 * @returns
 */
function fnSetEmailDomain(emailDomain) {
	$("#chpnScndEmailCtplc").val(emailDomain);
}


/**
 * 아르바이트 정보 복사
 * @param genArbtNo
 * @returns
 */
function onCopyData(genArbtNo) {
	if(confirm("복사하시겠습니까?")) {
		$("#genArbtNo").val(genArbtNo);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/otsh/copyData.ajax",
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
 * @param otshArbtAplcStcd
 * @returns
 */
function onPassSaveData(otshArbtAplcStcd) {
	if($('input:checkbox[name="otshArbtAplcNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("저장하시겠습니까?")) {
		$("#otshArbtAplcStcd").val(otshArbtAplcStcd);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/otsh/passSaveData.ajax",
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
	if($('input:checkbox[name="otshArbtAplcNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("저장하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/otsh/deleteAplcData.ajax",
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

/**
 * 아르바이트 정보 삭제
 * @returns
 */
function onDeleteData() {
	if(confirm("삭제하시겠습니까? 삭제시 지원자정보까지 삭제됩니다.")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/otsh/deleteData.ajax",
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
	
	if($('input:checkbox[name="genArbtNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("삭제하시겠습니까? 삭제시 지원자정보까지 삭제됩니다.")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/otsh/deleteDatas.ajax",
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
 * @param genArbtStcd
 * @returns
 */
function onApplySaveData(genArbtStcd) {
	if(confirm("저장하시겠습니까?")) {
		$("#genArbtStcd").val(genArbtStcd);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/otsh/applySaveData.ajax",
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
			url: CTX_PATH + "/mgnt/arbt/otsh/finishSeltSaveData.ajax",
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
	if($('input:checkbox[name="genArbtNos"]:checked').length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	
	if(confirm("마감 하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/arbt/otsh/finishSaveData.ajax",
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
		url: CTX_PATH + "/mgnt/arbt/otsh/otshAplcExcelList.ajax",
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
