function fnListPage(currentPage) {
	var frm = document.form1;
	
	frm.currentPage.value = currentPage;
	frm.target = "_self";
	frm.action = CTX_PATH + "/company/myp/arbeit/compArbeitWatList.do";
	frm.submit();	
}

function fnListPage2(currentPage) {
	var frm = document.form1;
	
	frm.currentPage.value = currentPage;
	frm.target = "_self";
	frm.action = CTX_PATH + "/company/myp/arbeit/compArbeitIngList.do";
	frm.submit();	
}

function fnListPage3(currentPage) {
	var frm = document.form1;
	
	frm.currentPage.value = currentPage;
	frm.target = "_self";
	frm.action = CTX_PATH + "/company/myp/arbeit/compArbeitClseList.do";
	frm.submit();	
}

function fnViewPage(genArbtNo) {
	$("#genArbtNo").val(genArbtNo);
	document.form1.target = "_self";
	document.form1.action = CTX_PATH + "/company/myp/arbeit/compArbeitView.do";
	document.form1.submit();
}

/**
 * 아르바이트 정보 복사
 * @param genArbtNo
 * @returns
 */
function fnCopyData(genArbtNo) {
	if(confirm("복사하시겠습니까?")) {
		$("#genArbtNo").val(genArbtNo);
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/company/myp/arbeit/copyData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("복사되었습니다.");
				fnListPage(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 등록/수정 페이지 이동
 * @param genArbtNo
 * @returns
 */
function fnEditPage(genArbtNo) {
	var frm = document.form1;
	$("#genArbtNo").val(genArbtNo);
	frm.target = "_self";
	frm.action = CTX_PATH + "/company/myp/arbeit/compArbeitHandle.do";
	frm.submit();
}


/**
 * 아르바이트 정보 삭제
 * @returns
 */
function fnDeleteData(genArbtNo) {
	$("#genArbtNo").val(genArbtNo);
	if(confirm("삭제하시겠습니까?")) {
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/company/myp/arbeit/deleteData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("삭제되었습니다.");
				fnListPage(1);
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}

/**
 * 이메일 도메인 셋팅
 * @param emailDomain
 * @returns
 */
function fnSetEmailDomain(emailDomain) {
	$("#chpnScndEmailCtplc").val(emailDomain);
}


//모집직종 팝업
function fnArbtRcrtKoocPop() {
	var ARBTRCRTKOOC_POP = window.open(CTX_PATH+"/company/myp/arbeit/otshRcrtKoocPopup.do", 'ARBTRCRTKOOC_POP' , 'width=700, height=750, resizable=yes, scrollbars=yes, left=200, top=0');
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
 * 아르바이트 정보 저장
 * @returns
 */
function fnSaveData() {
	
	$("#form1").validInit({onsubmit : false, onfocusout : false});
	var option = {};
	
	option = {
		"validList" : [
				  {"objId" : "titNm", 					"label" : "공고제목", 			"rule" : {"required":true,  "maxlength":200  }}
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
				, {"objId" : "chpnNm", 					"label" : "담당자명", 			"rule" : {"required":true,  "maxlength":100 }}
				, {"objId" : "chpnFstEmailCtplc", 		"label" : "이메일", 			"rule" : {"required":true,  "maxlength":100  }}
				, {"objId" : "chpnScndEmailCtplc", 		"label" : "이메일", 			"rule" : {"required":true,  "maxlength":100  }}
				, {"objId" : "chpnFstTlno", 			"label" : "문의처", 			"rule" : {"required":true, "number":true, "range":[0, 9999]} }
				, {"objId" : "chpnScndTlno", 			"label" : "문의처", 			"rule" : {"required":true, "number":true, "range":[0, 9999]} }
				, {"objId" : "chpnThrdTlno", 			"label" : "문의처", 			"rule" : {"required":true, "number":true, "range":[0, 9999]} }
			
		]
	};
	
	
	$("#form1").validAddRules(option);		
	if($("#form1").validate().form() == false) {
    	return false;
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
			url: CTX_PATH + "/company/myp/arbeit/saveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("저장되었습니다.");
				if($("#genArbtNo").val() == "") {
					fnListPage(1);
				} else {
					fnListPage($("#currentPage").val());
				}
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
}