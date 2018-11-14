/**
 * Checks if value is empty. Deep-checks arrays and objects Note: isEmpty([]) ==
 * true, isEmpty({}) == true, isEmpty([{0:false},"",0]) == true, isEmpty({0:1}) ==
 * false
 * 
 * @param value
 * @returns {boolean}
 */
function isEmpty(value) {
	var isEmptyObject = function(a) {
		if(typeof a.length === 'undefined') { // it's an Object, not an Array
			var hasNonempty = Object.keys(a).some(function nonEmpty(element) {
				return !isEmpty(a[element]);
			});
			return hasNonempty ? false : isEmptyObject(Object.keys(a));
		}
		return !a.some(function nonEmpty(element) { // check if array is really
			// not empty as JS thinks
			return !isEmpty(element); // at least one element should be
			// non-empty
		});
	};
	return(value == false || typeof value === 'undefined' || value == null || (typeof value === 'object' && isEmptyObject(value)));
}
/**
 * null 이나 빈값을 기본값으로 변경
 * 
 * @param str
 *            입력값
 * @param defaultVal
 *            기본값(옵션)
 * @returns {String} 체크 결과값
 */
function nvl(str, defaultVal) {
	var defaultValue = "";
	if(typeof defaultVal != 'undefined') {
		defaultValue = defaultVal;
	}
	if(typeof str == "undefined" || str == null || str == '' || str == "undefined") {
		return defaultValue;
	}
	return str;
}
/**
 * 길이체크
 * 
 * @param str
 * @returns {Number}
 */
function checkLength(str) {
	var stringLength = str.length;
	var stringByteLength = 0;
	for(var i = 0; i < stringLength; i++) {
		if(escape(str.charAt(i)).length >= 4) {
			stringByteLength += 3;
		} else if(escape(str.charAt(i)) == "%A7") {
			stringByteLength += 3;
		} else {
			if(escape(str.charAt(i)) != "%0D") {
				stringByteLength++;
			}
		}
	}
	return stringByteLength;
}
/**
 * jquery messageProperties Load /message/messageResources_{lang}.properties
 * 
 * @param lang
 */
function fnLoadBundles(lang) {
	if(lang == "ko") {
		lang = "ko_KR";
	} else if(lang == "en") {
		lang = "en_US";
	} else if(lang == "ja") {
		lang = "ja_JP";
	}
	jQuery.i18n.properties(
		{
			name : 'messageResources',
			path : '/message/',
			mode : 'both',
			language : lang,
			callback : function(data) {
			}
		});
}
/**
 * jquery message get ex) fnEkpMsg("errors.required", "제목")
 * 
 * @param key
 * @returns
 */
/*function fnEkpMsg(key) {
	return jQuery.i18n.prop(key, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5]);
}*/
/**
 * 리턴형
 * 공통코드 ajax로 가져오기
 * @param paramData = {grpCdId : "", hgrkGrpCdId : "", hgrkCdId : "", refeV1 : "", refeV2 : "", refeV3 : "", refeV4 : "", refeV5 : ""}
 * @returns
 */
function fnCommCodeList(paramData) {
	var returnObj = null;
	$.ajax(
			{
				async : false,
				type : 'POST',
				url : CTX_PATH + "/mgnt/system/CommCdListAction.ajax",
				data : paramData,
				contentType: 'application/json',
				dataType:"json",
				success : function(data) {
					returnObj = data;
				},
				error : function(data, textStatus, errorThrown) {
					fnAjaxError();
				}
			});
	
	return returnObj;
}

/**
 * 콜백형
 * 공통코드 ajax로 가져오기
 * @param paramData = {grpCdId : "", hgrkGrpCdId : "", hgrkCdId : "", refeV1 : "", refeV2 : "", refeV3 : "", refeV4 : "", refeV5 : ""}
 * @param callBack
 * @returns
 */
function fnCommCodeList2(paramData, callBack) {
	$.ajax(
		{
			async : false,
			type : 'POST',
			url : CTX_PATH + "/mgnt/system/CommCdListAction.ajax",
			data : paramData,
			contentType: 'application/json',
			dataType:"json",
			success : function(data) {
				if(callBack != null && callBack != "") {
					window[callBack](data);
				}
			},
			error : function(data, textStatus, errorThrown) {
				fnAjaxError();
			}
		});
}

/**
 * 뎁스별 공통코드 가져오기
 * @param grpCdId
 * @param hgrkGrpCdId
 * @param hgrkCdId
 * @param targetId
 * @returns
 */
function fnCommCodeList3(grpCdId, hgrkGrpCdId, hgrkCdId, targetId) {
	var paramData = {"grpCdId" : grpCdId, 
					 "hgrkGrpCdId" : hgrkGrpCdId, 
					 "hgrkCdId" : hgrkCdId, 
					 "refeV1" : "", 
					 "refeV2" : "", 
					 "refeV3" : "", 
					 "refeV4" : "", 
					 "refeV5" : ""};
	
	$.ajax({async : false,
			type : 'POST',
			url : CTX_PATH + "/mgnt/system/CommCdListAction.ajax",
			data : JSON.stringify(paramData),
			contentType: 'application/json',
			dataType:"json",
			success : function(data) {
				//console.log("data:"+JSON.stringify(data));
				
				$("#"+targetId).find("option").remove();
				$("#"+targetId).append("<option value=''>- 선택 -</option>");
				
				$.each(data.codeList, function(key, value) {
					$("#"+targetId).append("<option value='"+value.cdId+"'>"+value.cdNm+"</option>");
				});
			},
			error : function(data, textStatus, errorThrown) {
				fnAjaxError();
			}
		});
}

function fnMobileCommCodeList(grpCdId, hgrkGrpCdId, hgrkCdId, targetId) {
	var paramData = {"grpCdId" : grpCdId, 
			"hgrkGrpCdId" : hgrkGrpCdId, 
			"hgrkCdId" : hgrkCdId, 
			"refeV1" : "", 
			"refeV2" : "", 
			"refeV3" : "", 
			"refeV4" : "", 
			"refeV5" : ""};
	
	$.ajax({
		type : 'POST',
		url : CTX_PATH + "/mgnt/system/CommCdListAction.ajax",
		data : JSON.stringify(paramData),
		//contentType: 'application/json',
		//contentType: "Content-Type: application/javascript",
		//dataType:"json",		
		jsonpCallback: 'jsonCallback',
        contentType: "application/json",
        dataType: 'jsonp',
		
		success : function(data) {
			//console.log("data:"+JSON.stringify(data));
			
			$("#"+targetId).find("option").remove();
			$("#"+targetId).append("<option value=''>- 선택 -</option>");
			
			$.each(data.codeList, function(key, value) {
				$("#"+targetId).append("<option value='"+value.cdId+"'>"+value.cdNm+"</option>");
			});
		},
		error : function(data, textStatus, errorThrown) {
			fnAjaxError();
		}
	});
}

// 날짜를 포맷합니다.
function formatYmd(cellvalue, options, rowObject) {
	// alert(cellvalue + "" + options + "" + rowObject);
	var re = /\D/;
	var re2 = /^\({1}\d{4}\)\d{2}\d{2}/;
	var num = cellvalue;
	if(num == null) {
		num = "";
	}
	var newNum = num;
	// yyyymmdd 날짜형식을 포맷합니다.
	// alert(num+"#"+)
	if(num != "" && re2.test(num) != true && num.length >= 8) {
		if(num != "") {
			while(re.test(num)) {
				num = num.replace(re, "");
			}
		}
		if(num.length >= 8) {
			// for format yyyy.mm.dd
			newNum = num.substring(0, 4) + '.' + num.substring(4, 6) + '.' + num.substring(6, 8);
		}
	}
	return newNum;
}
// 날짜를 포맷합니다.
function formatYmdHms(cellvalue, options, rowObject) {
	// alert(cellvalue + "" + options + "" + rowObject);
	var re = /\D/;
	var re2 = /^\({1}\d{4}\)\d{2}\d{2}\d{2}\d{2}\d{2}/;
	var re3 = /^\({1}\d{4}\)\d{2}\d{2}/;
	var num = cellvalue;
	if(num == null) {
		num = "";
	}
	var newNum = num;
	// yyyymmddhhmiss 날짜형식을 포맷합니다.
	if(num != "" && re2.test(num) != true && num.length > 8) {
		if(num != "") {
			while(re.test(num)) {
				num = num.replace(re, "");
			}
		}
		if(num.length > 8) {
			// for format yyyy-mm-dd
			newNum = num.substring(0, 4) + '.' + num.substring(4, 6) + '.' + num.substring(6, 8) + ' ' + num.substring(8, 10) + ':' + num.substring(10, 12) + ':' + num.substring(12, 14);
		} else if(num.length == 8) {
			// for format yyyy-mm-dd
			newNum = num.substring(0, 4) + '.' + num.substring(4, 6) + '.' + num.substring(6, 8);
		}
	} else {// yyyymmdd 날짜형식을 포맷합니다.
		if(num != "" && re3.test(num) != true && num.length >= 8) {
			if(num != "") {
				while(re.test(num)) {
					num = num.replace(re, "");
				}
			}
			if(num.length >= 8) {
				// for format yyyy-mm-dd
				newNum = num.substring(0, 4) + '.' + num.substring(4, 6) + '.' + num.substring(6, 8);
			}
		}
	}
	return newNum;
}
/**
 * 입력 키 체크
 * 
 * @param event
 * @param type
 * @returns {Boolean}
 */
function onPress(event, type) {
	event = event || window.event;
	var keyId = (event.which) ? event.which : event.keyCode;
	if(type == "numbers") {
		// if( ( keyId >=48 && keyId <= 57 ) || ( keyId >=96 && keyId <= 105 ) )
		// {
		if((keyId >=48 && keyId <= 57 ) || ( keyId >=96 && keyId <= 105 ) || keyId == 8 || keyId == 46 || keyId == 37 || keyId == 39 ) {
			return true;
		} else {
			return false;
		}
	}
}
/**
 * 숫자만 입력하도록 함
 */
function fnNumberKey(event) {

	event = event || window.event;
	var keyId = (event.which) ? event.which : event.keyCode;
	
	if((keyId >=48 && keyId <= 57 ) || ( keyId >=96 && keyId <= 105 ) || keyId == 8 || keyId == 46 || keyId == 37 || keyId == 39 ) {
		return true;
	} else {
		return false;
	}
}

/**
 * 날짜 수동 입력 시 자동으로 포맷을 맞춰줌
 */
function fnDateMask(event, formd, textid) {

	event = event || window.event;
	var keyId = (event.which) ? event.which : event.keyCode;
		
	if((keyId >=48 && keyId <= 57 ) || ( keyId >=96 && keyId <= 105 ) || keyId == 8 || keyId == 46 || keyId == 37 || keyId == 39 || keyId == 190 ) {
		var form = eval("document."+formd);
		var text = eval("form."+textid);

		var textlength = text.value.length;

		if (textlength == 4) {
			text.value = text.value + ".";
		} else if (textlength == 7) {
			text.value = text.value + ".";
		} else if (textlength > 9) {
			var chk_date = checkdate(text);
			if (chk_date == false) {
				return;
			}
		}
		
		return true;
	} else {
		return false;
	}
	
}

function fnCheckNumber(event) {
	event = event || window.event;
	var keyId = (event.which) ? event.which : event.keyCode;
	if((keyId >= 48 && keyId <= 57)) {
		return true;
	} else {
		return false;
	}
}
/*
 * 숫자만 입력 허용 @param obj
 * 
 * @returns {String}
 */
function fnNumberOnly(obj) {
	var regexp = /[^[0-9]/gi;
	var newVal = "";
	var oldVal = $(obj).val();
	/*
	 * for (var i=0; i<oldVal.length; i++) { newVal +=
	 * toNumber(oldVal.charAt(i)); }
	 */
	newVal = toNumber(oldVal)
	$(obj).val(newVal);
}
/**
 * strString문자를 strChar로 변환
 * 
 * @param strString
 * @param strChar
 * @returns {String}
 */
function replaceAll(strString, strChar) {
	var resultValue = "";
	for(i = 0; i < strString.length; i++) {
		if(strString.charAt(i) != strChar) {
			resultValue = resultValue + strString.charAt(i);
		}
	}
	return resultValue;
}
/**
 * html tag 제거
 * 
 * @param htmlStr
 * @returns
 */
function removeTag(htmlStr) {
	return htmlStr.replace(/(<([^>]+)>)/gi, "");
}
/**
 * 숫자만 반환.
 * 
 * @param obj
 * @returns
 */
function toNumber(obj) {
	return obj.replace(/[^0-9]/g, "");
}
/**
 * 사용여부코드를 사용여부코드명으로 반환
 * 
 * @param cellValue
 * @param options
 * @param rowObject
 * @returns
 */
function formatUseYnNm(cellValue, options, rowObject) {
	var chgCellValue;
	if(cellValue == "Y")
		chgCellValue = "사용";
	else
		chgCellValue = "미사용";
	return chgCellValue;
}
/**
 * Html 의 tag 를 삭제
 * 
 * @param cellValue
 * @param options
 * @param rowObject
 * @returns
 */
function formatHtmlContent(cellValue, options, rowObject) {
	var chgCellValue = "";
	chgCellValue = removeTag(cellValue)
	return chgCellValue;
}
/**
 * 한글 자르기
 * 
 * @param value : stringData
 * @param valueSize : maxsize
 * @returns {string}
 */
function hanCut(hanValue, valueSize) {
	var tmpStr;
	var temp = 0;
	var onechar;
	var tcount;
	var returnStr = "";
	tcount = 0;
	tmpStr = new String(hanValue);
	temp = tmpStr.length;
	for(var k = 0; k < temp; k++) {
		onechar = tmpStr.charAt(k);
		if(escape(onechar).length > 3)
			tcount += 2; // 한글/한문/특수문자
		else if(onechar != ' ')
			tcount++; // 공백
		else if(onechar != '\r')
			tcount++; // 탭
		else if(onechar != '\n')
			tcount += 2; // 엔터
		if(tcount > valueSize) {
			return returnStr;
			break;
		}
		returnStr += onechar;
	}
	return returnStr;
}
/**
 * 행 추가
 * 
 * @param page
 * @returns obj 행
 */
function fnAppendRow(page) {
	var find_row = "";
	var loop_tag_id = page.LOOP_TAG_ID;
	if(nvl(loop_tag_id) != "") {
		find_row = nvl(loop_tag_id);
	} else {
		if(nvl(page.TWOROW_YN) == "Y") {
			find_row = "tbody";
		} else {
			find_row = "tbody tr";
		}
	}
	var obj = $("#" + page.TABLE_ID).find(find_row).filter(".original").clone();
	//obj.attr("style", "display:visible");
	obj.attr("style", "display:");
	obj.removeClass("original");
	obj.addClass("generated");
	$("#" + page.TABLE_ID).find(find_row).filter(":last").after(obj);
	return obj;
}
/**
 * checkbox 전체 선택/해제
 * 
 * @param obj
 * @param checkboxName
 * @returns
 */
function fnAllCheck(obj, checkboxName) {                                   
	if($(obj).prop("checked")) {                                             
		//$("input:checkbox[name=" + checkboxName + "]").prop("checked", true);
		$("input:checkbox[name=" + checkboxName + "]").each(function(){        
			//alert($(this).prop("disabled"))                                    
			if ( !$(this).prop("disabled") ) {                                   
				$(this).prop("checked", true);                                     
			}                                                                    
		});                                                                    
	} else {                                                                 
		$("input:checkbox[name=" + checkboxName + "]").prop("checked", false); 
	}                                                                        
} 
/**
 * 시분 체크
 * 
 * @param obj
 * @param checkboxName
 * @returns
 */
function chkTime(obj) {
	var input = $.trim(obj.value.replace(/:/g, ""));
	var inputHours = input.substr(0, 2);
	var inputMinutes = input.substr(2, 2);
	var inputSeconds = 0;
	if(input.length < 1)
		return "";
	if(inputHours.length < 1) {
		inputHours = "00";
	} else if(inputHours.length < 2) {
		inputHours = "0" + inputHours;
	}
	if(inputMinutes.length < 1) {
		inputMinutes = "00";
	} else if(inputMinutes.length < 2) {
		inputMinutes = "0" + inputMinutes;
	}
	var resultTime = new Date(0, 0, 0, inputHours, inputMinutes, inputSeconds);
	if(resultTime.getHours() != inputHours || resultTime.getMinutes() != inputMinutes) {
		obj.value = "";
	} else {
		obj.value = inputHours + ":" + inputMinutes;
	}
}
// 금액를 포맷합니다.
function formatAmt(cellvalue, options, rowObject) {
	if(cellvalue == null)
		return "";
	return comma(cellvalue) + " 원";
}
function comma(num) {
	var len, point, str;
	if(num == null)
		return "";
	num = num + "";
	point = num.length % 3;
	len = num.length;
	str = num.substring(0, point);
	while(point < len) {
		if(str != "")
			str += ",";
		str += num.substring(point, point + 3);
		point += 3;
	}
	return str;
}
function numberWithCommas(x) {
	if(x == null)
		return "";
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
function getGridRowNumber(cellvalue, options, rowObject) {
	var currentNum;
	var _GRIDPARAM = $("#table_list").jqGrid('getGridParam');
	var records = nvl(_GRIDPARAM.records, 0);
	var page = nvl(_GRIDPARAM.page, 0);
	var rows = nvl(_GRIDPARAM.rowNum, 0);
	var currentNo = options.rowId;
	// var page = nvl($("#page").val(), 0);
	// var rows = nvl($("#rows").val(), 0);
	currentNum = (records - (page - 1) * rows) - (currentNo - 1);
	return currentNum;
}
// Server Valid Error
function fnValidError(data) {
	if(data.vaildErrorMsg != undefined && !isEmpty(data.vaildErrorMsg)) {
		if(!isEmpty(data.vaildErrorFileId)) {
            $("#"+data.vaildErrorFileId).focus();
        }
		
		alert(data.vaildErrorMsg);
		
		if(data.vaildErrorFileId == "ERR001") {
			fnBackLogin();
		}
		
		return false;
	}
	
	return true;
}
function fnValidErrorBackup(data) {
	if(!isEmpty(data.vaildErrorMsg)) {
		if(!isEmpty(data.vaildErrorFileId)) {
			$("#" + data.vaildErrorFileId).focus();
		}
		alert(data.vaildErrorMsg);
		if(data.vaildErrorFileId == "ERR001") {
			var curUrl = document.location.href;
			var loginUrl = "";
			if(curUrl.indexOf("/mgnt/") > -1) {
				loginUrl = CTX_PATH + "/ekp/mgnt/login/initMLOLogin.do";
			} else if(curUrl.indexOf("/app/") > -1) {
				loginUrl = CTX_PATH + "/ekp/app/login/retrieveULOLogin.do";
			} else {
				loginUrl = CTX_PATH + "/ekp/user/login/retrieveULOLogin.do";
			}
			
			if(window.opener){ 
				//opener가 이미 closed됐는지 확인. closed됐다면 해당 페이지를 이동
				if(window.opener.closed){
					top.location.href = loginUrl;
				} else { // opener가 존재하면 opener의 top을 해당 페이지로 이동
					window.opener.top.location.href = loginUrl;
					window.open('about:blank','_self').close();
				}
			} else { // popup이 아니라면 해당 페이지를 이동
				top.location.href = loginUrl;
			} 
		}
		return false;
	}
	return true;
}

//Server Ajax error
function fnAjaxError(data) {
	//console.log("data=["+data+"]");
	//console.log("data=["+JSON.stringify(data)+"]");
	
	var responseJSON = $.parseJSON(data.responseText);
	console.log("responseJSON=["+responseJSON+"]");
	console.log("responseJSON=["+JSON.stringify(responseJSON)+"]");
	
	if(data.status == "401") {
		var curUrl = document.location.href;
		var loginUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			loginUrl = CTX_PATH + "/mgnt/";
		} else if(curUrl.indexOf("/app/") > -1) {
			loginUrl = CTX_PATH + "/app/";
		} else {
			loginUrl = CTX_PATH + "/";
		}
		if ( responseJSON.exception != undefined ) {
			alert(responseJSON.exception.message);
		} else {
			alert("해당 화면에 접근권한이 없습니다.");
		}
		
		/*if(window.opener){ 
			//opener가 이미 closed됐는지 확인. closed됐다면 해당 페이지를 이동
			if(window.opener.closed){
				top.location.href = loginUrl;
			} else { // opener가 존재하면 opener의 top을 해당 페이지로 이동
				window.opener.top.location.href = loginUrl;
				window.open('about:blank','_self').close();
			}
		} else { // popup이 아니라면 해당 페이지를 이동
			top.location.href = loginUrl;
		} */
	} else if(data.status == "403") {
		var curUrl = document.location.href;
		var loginUrl = "";
		if(curUrl.indexOf("/mgnt/") > -1) {
			loginUrl	= CTX_PATH + "/";
		} else if(curUrl.indexOf("/app/") > -1) {
			loginUrl = CTX_PATH + "/";
		} else {
			loginUrl = CTX_PATH + "/";
		}
		
		if ( responseJSON.exception != undefined ) {
			alert(responseJSON.exception.message);
		} else {
			alert("로그인 정보가 없습니다. 로그인 후 사용하여주시기 바랍니다.");
		}
		
		if(window.opener){ 
			//opener가 이미 closed됐는지 확인. closed됐다면 해당 페이지를 이동
			if(window.opener.closed){
				top.location.href = loginUrl;
			} else { // opener가 존재하면 opener의 top을 해당 페이지로 이동
				window.opener.top.location.href = loginUrl;
				window.open('about:blank','_self').close();
			}
		} else { // popup이 아니라면 해당 페이지를 이동
			top.location.href = loginUrl;
		} 		
	} else {
		alert("서버에 통신 중에 에러가 발생하였습니다.");
		return false;
	}
	return false;
}

function compareNumbers(a, b) {
	return a - b;
}

/**
 * 페이징관련함수
 * 
 */
var PageUtil = function(pageDivId, paginationInfo, jsFunction, pageDivCd) {	 // 페이지처리함수
	//console.log(paginationInfo.firstPageNo);
	var pageDivHtml = "";
	var firstPageNo = paginationInfo.firstPageNo;							// 첫페이지번호
	var firstPageNoOnPageList = paginationInfo.firstPageNoOnPageList;		// 이전페이지
	var totalPageCount = paginationInfo.totalPageCount;						// 총페이지갯수
	var pageSize = paginationInfo.pageSize;									// 총건수
	var lastPageNoOnPageList = paginationInfo.lastPageNoOnPageList;			// 다음페이지
	var currentPageNo = paginationInfo.currentPageNo;						// 현재페이지
	var lastPageNo = paginationInfo.lastPageNo;								// 마지막페이지번호
	var recordCountPerPage = paginationInfo.recordCountPerPage;				// 한번에출력될게시물수
	var numberSpliter = "";
	
	if(pageDivCd == "M") {
		if(totalPageCount > currentPageNo) {
			pageDivHtml += "<a href=\"#\" onclick=\""+ jsFunction +"("+ (currentPageNo + 1) +"); return false;\" class=\"btn btn-outline btn-bordered btn-md btn-style-d btn-loadmore\">더보기</a>";
		}
	} else {
		if(totalPageCount > pageSize) {
			if (firstPageNoOnPageList > pageSize) {
				//pageDivHtml += "<a href=\"#\" onclick=\""+ jsFunction +"("+ firstPageNo +"); return false;\">[처음]</a>&#160;";
				pageDivHtml += "<a href=\"#\" class=\"btn btn-outline btn-bordered btn-style-d btn-sm btn-prev\" onclick=\""+ jsFunction +"("+ (Number(firstPageNoOnPageList) - 1) +"); return false;\"><span>이전</span></a>";
			} else {
				//pageDivHtml += "<a href=\"#\" onclick=\""+ jsFunction +"("+ firstPageNo +"); return false;\">[처음]</a>&#160;";
				pageDivHtml += "<a href=\"#\" class=\"btn btn-outline btn-bordered btn-style-d btn-sm btn-prev\" onclick=\""+ jsFunction +"("+ firstPageNo +"); return false;\"><span>이전</span></a>";
			}
		}
	
		for (var i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if (i == currentPageNo){
				pageDivHtml += "<a href=\"javascript:void(0); return false;\" class=\"current\">"+ i+"</a>";
				if (lastPageNoOnPageList != i) {
					pageDivHtml += numberSpliter;
				}
			} else {
				pageDivHtml += "<a href=\"#\" onclick=\""+ jsFunction +"("+ i +"); return false;\">"+ i+"</a>";
				if (lastPageNoOnPageList != i) {
					pageDivHtml += numberSpliter;
				}
			}
		}
	
		if(totalPageCount > pageSize) {
			if(lastPageNoOnPageList < totalPageCount) {
				pageDivHtml += "<a href=\"#\" class=\"btn btn-outline btn-bordered btn-style-d btn-sm btn-next\" onclick=\""+ jsFunction +"("+ (Number(firstPageNoOnPageList) + Number(pageSize)) +"); return false;\"><span>다음</span></a>";
				//pageDivHtml += "<a href=\"#\" onclick=\""+ jsFunction +"("+ lastPageNo +"); return false;\">[마지막]</a>&#160;";
			} else {
				pageDivHtml += "<a href=\"#\" class=\"btn btn-outline btn-bordered btn-style-d btn-sm btn-next\" onclick=\""+ jsFunction +"("+ lastPageNo +"); return false;\"><span>다음</span></a>";
				//pageDivHtml += "<a href=\"#\" onclick=\""+ jsFunction +"("+ lastPageNo +"); return false;\">[마지막]</a>&#160;";
			}
		}
	}
	
	$("#"+pageDivId).html(pageDivHtml);
	
}

/**
 * 지정한 기간에서 term기간(month) 만큼 날자를 계산
 * 20060308, 2 ==> return 20060507
 * 20060308, -1 ==> return 20060209
 */
function addMonth(dt, term) {
    var date = toTimeObject(dt);

    var years  = date.getFullYear();
    var months = date.getMonth() + eval(term);
    var days   = 0;
    if(eval(term)>0)  {
        days   = date.getDate() - 1;
    } else {
        days   = date.getDate() + 1;
    }

    timeObj = new Date(years,months,days);
    return toTimeString(timeObj).substring(0,dt.length);
}

/**
 * 지정한 기간에서 term기간(week) 만큼 날자를 계산
 * addDay를 이용하며 term*7의 일자를 계산한다.
 */
function addWeek(dt, term) {
    return addDay(dt, term*7);
}

/**
 * 지정한 기간에서 term기간(day) 만큼 날자를 계산
 * 20060308, 2 ==> return 20060310
 * 20060308, -1 ==> return 20060307
 */
function addDay(dt, term) {
    var date = toTimeObject(dt);

    var years  = date.getFullYear();
    var months = date.getMonth();
    var days   = date.getDate() + term;

    timeObj = new Date(years,months,days);
    return toTimeString(timeObj).substring(0,dt.length);
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toTimeObject(time) { //parseTime(time)
    var year  = time.substring( 0,  4);
    var month = time.substring( 4,  6) - 1; // 1월=0,12월=11
    var day   = time.substring( 6,  8);
    var hour  = time.substring( 8, 10);
    var min   = time.substring(10, 12);

    return new Date(year,month,day,hour,min);
}

/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeString(date) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();
    var hour  = date.getHours();
    var min   = date.getMinutes();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
    if (("" + hour).length  == 1) { hour  = "0" + hour;  }
    if (("" + min).length   == 1) { min   = "0" + min;   }

    return ("" + year + month + day + hour + min);
}

/**
 * 유효하는(존재하는) Date 인지 체크
 * @param strDate : 검증할 string형식의 날짜(날짜형식"20090101") yyyymmdd
 * @returns : true, false
 * @example : if(!isValidDate(strDate)) alert("올바른 날짜가 아닙니다.");
 */
function isValidDate(strDate) {
    var year  = "";
    var month = "";
    var day   = "";

    if(strDate.length == 8) {
        year  = strDate.substring(0,4);
        month = strDate.substring(4,6);
        day   = strDate.substring(6,8);
        if(Number(year,10) >= 1900  && isValidMonth(month) && isValidDay(year,month,day)) {
            return true;
        }
    } else if(strDate.length == 6) {
        year  = strDate.substring(0,4);
        month = strDate.substring(4,6);

        if(Number(year,10) >= 1900 && isValidMonth(month)) {
            return true;
        }
    }

    return false;
}

/**
 * 유효한(존재하는) 월(月)인지 체크
 * @param mm : 검증할 월(형식"01" ~ "12")
 * @returns : true, false
 * @example : isValidMonth(mm)
 */
function isValidMonth(mm) {
    var m = Number(mm,10);
    return (m >= 1 && m <= 12);
}
/**
 * 유효한(존재하는) 일(日)인지 체크
 * @param yyyy : 검증할 년(형식"2009")
 * @param mm : 검증할 월(형식"01" ~ "12")
 * @param dd : 검증할 일(형식"01" ~ "31")
 * @returns : true, false
 * @example : isValidDay(yyyy, mm, dd)
 */
function isValidDay(yyyy, mm, dd) {
    var m   = Number(mm,10) - 1;
    var d   = Number(dd,10);
    var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

    if((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
        end[1] = 29;
    }

    return (d >= 1 && d <= end[m]);
}

/**
 * 날짜시간포멧 리턴 함수
 * @param format:    yyyymmdd or hh24miss or hh12miss 를 이용
 * @param datetime   임시 용도
 * @param isRealTime true/false 서버시간을 사용할지의 여부. 사용하면 true 아니면 false
 * @returns : 포멧된 날짜 시간
 * @example : getDateTime("yyyy.mm.dd",,true); or getDateTime("hh24:mi:ss","20120302111658",false)
 */
function getDateTime(format, datetime) {
    var today   = getToday();
    var yyyy    = "";
    var mm      = "";
    var dd      = "";

    //var time    = getTime();
    var hh24    = "";
    var mi      = "";
    var ss      = "";


    if(datetime == undefined || trim(datetime) == "") {
        yyyy    = today.substring(0,4);
        mm      = lpad(today.substring(4,6),2,"0");
        dd      = lpad(today.substring(6,8),2,"0");

        hh24    = time.substring(0, 2);
        mi      = time.substring(2, 4);
        ss      = time.substring(4);
    } else {
        if(datetime.length==6) {
            hh24    = lpad(datetime.substring(0,2),2,"0");
            mi      = lpad(datetime.substring(2,4),2,"0");
            ss      = lpad(datetime.substring(4,6),2,"0");
        } else if(datetime.length==8) {
            yyyy    = datetime.substring(0,4);
            mm      = lpad(datetime.substring(4,6),2,"0");
            dd      = lpad(datetime.substring(6,8),2,"0");
        } else if(datetime.length==14) {
            yyyy    = datetime.substring(0,4);
            mm      = lpad(datetime.substring(4,6),2,"0");
            dd      = lpad(datetime.substring(6,8),2,"0");
            hh24    = lpad(datetime.substring(8,10),2,"0");
            mi      = lpad(datetime.substring(10,12),2,"0");
            ss      = lpad(datetime.substring(12,14),2,"0");
        }
    }
    if(format.indexOf("yyyy") > -1) {
        format = format.replaceAll("yyyy", yyyy);
    }
    if(format.indexOf("mm") > -1) {
        format = format.replaceAll("mm",mm);
    }
    if(format.indexOf("dd") > -1) {
        format = format.replaceAll("dd",dd);
    }
    if(format.indexOf("hh24") > -1) {
        format = format.replaceAll("hh24",hh24);
    }
    if(format.indexOf("hh12") > -1) {
        if(hh24 > 12) {
            hh24 = hh24 -12;
            hh24 = lpad(hh24,2,"0");
        }
        format = format.replaceAll("hh12",hh24);
    }
    if(format.indexOf("mi") > -1) {
        format = format.replaceAll("mi", mi);
    }
    if(format.indexOf("ss") > -1) {
        format = format.replaceAll("ss", ss);
    }

    return format;
}

/**
 * Left 빈자리 만큼 str 을 붙인다.
 * @param src : Right에 붙을 원본 데이터
 * @param len : str붙힐 데이터 길이
 * @param str : 대상 데이터
 * @returns : str과 src가 붙은 데이터
 * @example : lpad("123123", 10, " ");
 */
function lpad(src, len, str) {
    var retStr = "";
    var padCnt = Number(len) - String(src).length;

    for(var i=0;i<padCnt;i++) {
        retStr += String(str);
    }

    return retStr+src;
}
/**
 * Right 빈자리 만큼 str 을 붙인다.
 * @param src : Left에 붙을 원본 데이터
 * @param len : str붙힐 데이터 길이
 * @param str : 대상 데이터
 * @returns : str과 src가 붙은 데이터
 * @example : rpad("123123", 10, " ");
 */
function rpad(src, len, str) {
    var retStr = "";
    var padCnt = Number(len) - String(src).length;

    for(var i=0;i<padCnt;i++) {
        retStr += String(str);
    }

    return src+retStr;
}

/**
 * 오늘날짜 반환
 * @param 없음
 * @returns : yyyymmdd : 오늘날짜
 * @example : getToday();
 */
function getToday() {
    //return _TODAY_;
	return "";
}
/**
 * 현재시각 반환
 * @param 없음
 * @returns : hh24miss : 현재시각
 * @example : getTime();
 */
function getTime() {
	return "";
}

/**
 * 두 Time이 몇 개월 차이나는지 구함
 * time1이 time2보다 크면(미래면) minus(-)
 */
function getMonthInterval(time1,time2) {
    var date1 = toTimeObject(time1.replaceAll('.','').replaceAll('-',''));
    var date2 = toTimeObject(time2.replaceAll('.','').replaceAll('-',''));

    var years  = date2.getFullYear() - date1.getFullYear();
    var months = date2.getMonth() - date1.getMonth();
    var days   = date2.getDate() - date1.getDate();

    return (years * 12 + months + (days >= 0 ? 0 : -1) );
}
/**
 * 두 Time이 며칠 차이나는지 구함
 * time1이 time2보다 크면(미래면) minus(-)
 */
function getDayInterval(time1, time2) {
    var date1 = toTimeObject(time1.replaceAll('-','').replaceAll('.',''));
    var date2 = toTimeObject(time2.replaceAll('-','').replaceAll('.',''));
    var day   = 1000 * 3600 * 24; //24시간
    //console.log("1="+(date2 - date1));
    //console.log("1="+((date2 - date1) / day));
    return parseInt((date2 - date1) / day, 10);
}
/**
 * 두 Time이 몇 시간 차이나는지 구함
 * time1이 time2보다 크면(미래면) minus(-)
 */
function getHourInterval(time1,time2) {
    var date1 = toTimeObject(time1.replaceAll('-','').replaceAll('.',''));
    var date2 = toTimeObject(time2.replaceAll('-','').replaceAll('.',''));
    var hour  = 1000 * 3600; //1시간

    return parseInt((date2 - date1) / hour, 10);
}
/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeString(date) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();
    var hour  = date.getHours();
    var min   = date.getMinutes();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
    if (("" + hour).length  == 1) { hour  = "0" + hour;  }
    if (("" + min).length   == 1) { min   = "0" + min;   }

    return ("" + year + month + day + hour + min);
}

/**
 * 변수가 undefined 일 경우 대체
 * @param v : 원본값
 * @param r : 대체값
 * @example : null2void($("#aaa").val(), "");
 */
function null2void(v, r) {
    if(v == undefined || v == "undefined") {
        if(r == undefined || r == "undefined") {
            v = "";
        } else {
            v = r;
        }
    }   

    return v;
}

/**
 * 문자열 좌우 공백제거
 * @param str : 대상 데이터
 * @returns : 공백 제거된 데이터
 * @example : trim(str)
 */
function trim(str) {
    if(typeof str == "boolean") {
        return str;
    } else {
        if(null2void(str) == "") {
            return str;
        } else {
            return str.replace(/(^\s*)|(\s*$)/g, "");        
        }       
    }
}

/**
 * replaceAll 처리
 */
String.prototype.replaceAll = function(strValue1, strValue2) {
    return this.split(strValue1).join(strValue2);
};

/**
 * get 쿠키
 * @param cookieName
 * @returns
 * ex) 
 * if(fnGetCookie("cookeName") != "done") {
 */
function fnGetCookie(cookieName) {
	var nameOfCookie = cookieName + "=";
	var x = 0;
	while (x <= document.cookie.length){
		var y = (x + nameOfCookie.length);
		if (document.cookie.substring(x, y) == nameOfCookie){
			if ((endOfCookie = document.cookie.indexOf(";", y)) == -1)
			endOfCookie = document.cookie.length;
			return unescape (document.cookie.substring(y, endOfCookie));
		}
		x = document.cookie.indexOf (" ", x) + 1;
		if (x == 0) break;
	}
	return "";
}

/**
 *  set 쿠키
 * @param cookieName
 * @param value
 * @param expiredays
 * ex) 
 * fnSetCookie("cookeName", "done", 1);
 */
function fnSetCookie(cookieName, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate (todayDate.getDate() + expiredays);
	document.cookie = cookieName + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}

function fnDeleteCookie(cookieName) {
	var expireDate = new Date();
 
	//어제 날짜를 쿠키 소멸 날짜로 설정한다.
	expireDate.setDate( expireDate.getDate() - 1 );
	document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString() + "; path=/";
}

/**
 *  비밀번호 체크 로직(id)
 *  @param obj -- id를 입력
 */
function fnCheckPass(txtId){
	var obj = $("#" + txtId);
    var alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    var number = "1234567890";
// var sChar = "-_=+\|()*&^%$#@!~`?></;,.:'";
    var sChar = " !@$%^&*#";
    var sChar_Count = 0;
    var alphaCheck = false;
    var numberCheck = false;
    var $msg = obj.closest("tr").find("p.msg");
    var returnTxt = "";
    var pw = obj.val();
    if(9 <= pw.length && pw.length <= 15){
        for(var i=0; i<pw.length; i++){
            if(sChar.indexOf(pw.charAt(i)) != -1){
                sChar_Count++;
            }
            if(alpha.indexOf(pw.charAt(i)) != -1){
                alphaCheck = true;
            }
            if(number.indexOf(pw.charAt(i)) != -1){
                numberCheck = true;
            }
        }
        if(sChar_Count < 1 || alphaCheck != true || numberCheck != true){
        	returnTxt = "비밀번호는 9~15자 영문,숫자,특수문자 1자 이상으로 조합해주세요.";
            return returnTxt;
        }
    }else{
    	returnTxt = "비밀번호는 9~15자 영문,숫자,특수문자 1자 이상으로 조합해주세요.";
        return returnTxt;
    }
    return returnTxt;
}

var fnByteCheck = {
	getByteLength : function(s) {
		if (s == null || s.length == 0) {
			return 0;
		}
		var size = 0;
		for ( var i = 0; i < s.length; i++) {
			size += this.charByteSize(s.charAt(i));
		}
		return size;
	},
		
	cutByteLength : function(s, len) {
		if (s == null || s.length == 0) {
			return 0;
		}
		var size = 0;
		var rIndex = s.length;

		for ( var i = 0; i < s.length; i++) {
			size += this.charByteSize(s.charAt(i));
			if( size == len ) {
				rIndex = i + 1;
				break;
			} else if( size > len ) {
				rIndex = i;
				break;
			}
		}

		return s.substring(0, rIndex);
	},

	charByteSize : function(ch) {

		if (ch == null || ch.length == 0) {
			return 0;
		}

		var charCode = ch.charCodeAt(0);

		if (charCode <= 0x00007F) {
			return 1;
		} else if (charCode <= 0x0007FF) {
			return 2;
		} else if (charCode <= 0x00FFFF) {
			return 3;
		} else {
			return 4;
		}
	}
};

function tz(orgVal) {
	var changeVal	= "";
	
	if ( orgVal == null ) return "";
	
	changeVal = orgVal.replaceAll("&quot;", 	"#!@$#");
	changeVal = changeVal.replaceAll("&apos;", 	"~!@#$%^&*()_+");
//	changeVal = changeVal.replaceAll("\r\n", 	"<br/>");
	
	return changeVal;
}

/*
 * Form 생성
 */
function fnAddForm(formId) {
	
	$("#"+formId).remove();
	
	var $form = $('<form id="'+formId+'" method="" action=""></form>');
    $form.appendTo("BODY");
    
    return $form;
}

/*
 * input hidden 필드 생성
 */
function fnAddInput($form, inputId, inputName, inputVal) {
	
	var $input = $('<input>', {
	    type: 'hidden',
	    id: inputId,
	    name: inputName,
	    value: inputVal
	});
	
	$form.append($input);
}

/*
 * 메일 발송 팝업 
 */
function fnSendMailPop($form) {
	var VIEW_MAIL = window.open('', 'VIEW_MAIL' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
	VIEW_MAIL.focus();   
	
	$form.attr("action", CTX_PATH+"/ekp/mgnt/mail/handleCSMMail.do");
    $form.attr("method", "POST");
    $form.attr("target", "VIEW_MAIL");
    $form.submit();
}

/*
 * SMS 발송 팝업 
 */
function fnSendSmsPop($form) {
	var VIEW_SMS = window.open('', 'VIEW_SMS' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
	VIEW_SMS.focus();   
	
	$form.attr("action", CTX_PATH+"/ekp/mgnt/sms/handleCSSSms.do");
    $form.attr("method", "POST");
    $form.attr("target", "VIEW_SMS");
    $form.submit();
}

/*
 * 블랙리스트 팝업 
 */
function fnSendSpclManagePop($form) {
	var VIEW_SPCL = window.open('', 'VIEW_SPCL' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
	VIEW_SPCL.focus();   
	
	$form.attr("action", CTX_PATH+"/mgnt/mbr/special/spclManageHandlePop.do");
    $form.attr("method", "POST");
    $form.attr("target", "VIEW_SPCL");
    $form.submit();
}

/*
 * 품목군 리스트 팝업 
 */
function fnArticlePop($form) {
	var VIEW_ARTICLE = window.open('', 'VIEW_ARTICLE' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
	VIEW_ARTICLE.focus();   
	
	$form.attr("action", CTX_PATH+"/mgnt/article/articleListPop.do");
    $form.attr("method", "POST");
    $form.attr("target", "VIEW_ARTICLE");
    $form.submit();
}

/*
 * 문자열 자르기 
 */
function substr(str, start, end) {
	if ( str == undefined ) return str;
	var changStr = 	str.substring(start, end);
	return changStr;
}

/*
 * 강의 홈 페이지로 이동
 */
function fnSbjtViewMovePage(sbjtId, cntsId, mobileYn) {
	
	var sUrl	= '';
	if ( mobileYn != undefined && mobileYn == 'Y' ) {
		sUrl	= CTX_PATH+"/ekp/app/course/initUCRCourse.sdo";
	} else {
		sUrl	= CTX_PATH+"/ekp/user/course/initUCRCourse.sdo";
	}
	
	var $form	= fnAddForm('sbjtForm');
	fnAddInput($form, 'sbjtId', 'sbjtId', sbjtId);
	fnAddInput($form, 'cntsId', 'cntsId', cntsId);
	
	$form.attr("action", sUrl);
    $form.attr("method", "GET");
    $form.attr("target", "_self");
    $form.submit();
}

/*
 * 스크랩 저장
 * 
 * @param : data 저장할 파라메터 객체
 *  - scrpDvCd 		: 스크랩구분(001:채용, 002:프로그램, 003:리크루팅, 004:외부행사)
 *  - scrpCnnctNo 	: 스크랩연계번호(연계할 레코드의 PK 값)
 * @param : _callbackFn 콜백함수
 */
function fnScrap(data, _callbackFn) {
	var param	= JSON.stringify(data); 
	//console.log(param);
		
	$.ajax({
		async : false,
		type: 'POST',
		url: CTX_PATH + "/user/mbr/scrp/scrapSave.json",
		data: param,
		contentType: 'application/json',
		dataType:"json",
		success : function (data) {
			var resultMsg		= data.resultMsg;
			var completeYn		= data.completeYn;
			
			alert(resultMsg);
			if ( completeYn == "Y" ) {
				if ( _callbackFn != undefined ) {
					eval(_callbackFn)(completeYn, resultMsg);
				}
			}
			
		}, 
		error: function(data, textStatus, errorThrown) {
			fnAjaxError(data);
		}
	});
}

/*
 * 스크랩 저장
 * 
 * @param : data 삭제할 파라메터 객체
 *  - scrpNo 		: 스크랩번호
 *  - scrpDvCd 		: 스크랩구분코드(001:채용, 002:프로그램, 003:리크루팅, 004:외부행사)
 *  - scrpCnnctNo 	: 스크랩연계번호  
 * @param : _callbackFn 콜백함수
 */
function fnDeleteScrap(data, _callbackFn) {
	var param	= JSON.stringify(data); 
	//console.log(param);
		
	$.ajax({
		async : false,
		type: 'POST',
		url: CTX_PATH + "/user/mbr/scrp/scrapDelete.json",
		data: param,
		contentType: 'application/json',
		dataType:"json",
		success : function (data) {
			var resultMsg		= data.resultMsg;
			var completeYn		= data.completeYn;
			
			alert(resultMsg);
			if ( completeYn == "Y" ) {
				if ( _callbackFn != undefined ) {
					eval(_callbackFn)(completeYn, resultMsg);
				}
			}
			
		}, 
		error: function(data, textStatus, errorThrown) {
			fnAjaxError(data);
		}
	});
}

/*
 * HTML 태크 원복
 */
function fnRecoveHtml(sourceVal) {
	
	var changeVal	= '';
	if ( sourceVal == '' ) return changeVal;
	
	changeVal = sourceVal.replace(/&amp;/g, '&');
	changeVal = changeVal.replace(/&lt;/g, '<');
	changeVal = changeVal.replace(/&gt;/g, '>');
	//changeVal = changeVal.replace(/&apos;/g, '\'');
	//changeVal = changeVal.replace(/&quot;/g, '\"');
	
	return changeVal
}

/**
 * 절상, 절하, 반올림 처리
 * @param strMode  - 수식
 * @param nCalcVal - 처리할 값(소수점 이하 데이터 포함)
 * @param nDigit   - 연산 기준 자릿수(오라클의 ROUND함수 자릿수 기준)
 *                   -2:십단위, -1:원단위, 0:소수점 1자리
 *                   1:소수점 2자리, 2:소수점 3자리, 3:소수점 4자리, 4:소수점 5자리 처리
 * @return String nCalcVal
 */
function fnCalcMath(strMode, nCalcVal, nDigit) {
    if(strMode == "CEIL") {  //절상
        if(nDigit < 0) {
            nDigit = -(nDigit);
            nCalcVal = Math.ceil(nCalcVal / Math.pow(10, nDigit)) * Math.pow(10, nDigit);
        } else {
            nCalcVal = Math.ceil(nCalcVal * Math.pow(10, nDigit)) / Math.pow(10, nDigit);
        }
    } else if(strMode == "FLOOR") { //절하
        if(nDigit < 0) {
            nDigit = -(nDigit);
            nCalcVal = Math.floor(nCalcVal / Math.pow(10, nDigit)) * Math.pow(10, nDigit);
        } else {
            nCalcVal = Math.floor(nCalcVal * Math.pow(10, nDigit)) / Math.pow(10, nDigit);
        }
    } else {        //반올림
        if(nDigit < 0) {
            nDigit = -(nDigit);
            nCalcVal = Math.round(nCalcVal / Math.pow(10, nDigit)) * Math.pow(10, nDigit);
        } else {
            nCalcVal = Math.round(nCalcVal * Math.pow(10, nDigit)) / Math.pow(10, nDigit);
        }
    }
    return nCalcVal;
}


//우편번호 검색
function fnOpenPost(pstcd, address1) {
	/**
	zonecode	13494	국가기초구역번호. 2015년 8월 1일부터 시행될 새 우편번호.
	(ex. 서울 종로구 계동 1-1)
	address	경기 성남시 분당구 판교역로 235	기본 주소
	(검색 결과에서 첫줄에 나오는 주소)
	addressEnglish	235 Pangyoyeok-ro, Bundang-gu, Seongnam-si, Gyeonggi-do, korea	기본 영문 주소
	addressType	R/J	검색된 기본 주소 타입: R(도로명), J(지번)
	userSelectedType	R/J	검색 결과에서 사용자가 선택한 주소의 타입
	userLanguageType	K/E	검색 결과에서 사용자가 선택한 주소의 언어 타입: K(한글주소), E(영문주소)
	roadAddress	경기 성남시 분당구 판교역로 235	도로명 주소
	(모든 주소에 도로명 주소가 부여되어 있지는 않습니다. ex. 서울 종로구 계동 1-1)
	roadAddressEnglish	235, Pangyoyeok-ro, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea	영문 도로명 주소
	jibunAddress	경기 성남시 분당구 삼평동 681	지번 주소
	jibunAddressEnglish	681, Sampyeong-dong, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea	영문 지번 주소
	autoRoadAddress	경기 성남시 분당구 판교역로 235	매핑된 도로명 주소가 여러개인 경우, 사용자가 '선택안함'을 클릭했을 때 임의로 첫번째 매핑 주소를 넣어서 반환합니다.
	(autoMapping을 false로 설정한 경우에는 값이 채워지지 않습니다.)
	autoRoadAddressEnglish	235, Pangyoyeok-ro, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea	autoRoadAddress의 영문 도로명 주소
	autoJibunAddress	경기 성남시 분당구 삼평동 681	매핑된 지번 주소가 여러개인 경우, 사용자가 '선택안함'을 클릭했을 때 임의로 첫번째 매핑 주소를 넣어서 반환합니다.
	(autoMapping을 false로 설정한 경우에는 값이 채워지지 않습니다.)
	autoJibunAddressEnglish	681, Sampyeong-dong, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea	autoJibunAddress의 영문 지번 주소
	buildingCode	4113510900106810000000001	건물관리번호
	buildingName	에이치스퀘어 엔동	건물명
	apartment	N	공동주택 여부(Y/N)
	(아파트,연립주택,다세대주택 등)
	sido	경기	도/시 이름
	sigungu	성남시 분당구	시/군/구 이름
	bcode	4113510900	법정동/법정리 코드
	bname	삼평동	법정동/법정리 이름
	query	판교역로 235	사용자가 입력한 검색어
	postcode	463-400	구 우편번호 (2015년 8월 1일 이후에는 업데이트가 되지 않습니다.)
	postcode1	463	구 우편번호 앞 3자리 (2015년 8월 1일 이후에는 업데이트가 되지 않습니다.)
	postcode2	400	구 우편번호 뒤 3자리 (2015년 8월 1일 이후에는 업데이트가 되지 않습니다.)
	postcodeSeq	001	구 우편번호 일련번호 (2015년 8월 1일 이후에는 업데이트가 되지 않습니다.)
	*/
  new daum.Postcode({
      oncomplete: function(data) {
          $('#'+pstcd).val(data.zonecode);
          $('#'+address1).val(data.roadAddress);
//        //console.log(data);
      }
  }).open();
}

function validateEmail(sEmail) {
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	if ( !filter.test(sEmail) ) {
		return false;
	}
	
	return true;
}

function validateMultipleEmails(multiEmail) {
    var result = multiEmail.split(",");
    for(var i = 0;i < result.length;i++) {
    	if(!validateEmail(result[i])) {
    		return false; 
    	}
    }
    return true;
}

function getCheckedValue(name) {
	var value = $("input:radio[name='"+name+"']:checked").val();
	return value;
}

function getSelectedValue(name) {
	//var value = $("select[name='"+name+"']:selected").val();
	var value = $("select[name='"+name+"']").val();
	return value;
}

function escapeXml(unsafe) {
    return unsafe.replace(/[<>&'"]/g, function (c) {
        switch (c) {
            case '<': return '&lt;';
            case '>': return '&gt;';
            case '&': return '&amp;';
            case '\'': return '&apos;';
            case '"': return '&quot;';
        }
    });
}

/*
 * HTML 태크 원복
 */
function fnRecoveHtml(sourceVal) {
	var changeVal	= '';
	if ( sourceVal == '' ) return changeVal;
	
	changeVal = sourceVal.replace(/&lt;/g, '<');
	changeVal = changeVal.replace(/&gt;/g, '>');
	changeVal = changeVal.replace(/&amp;/g, '&');
	
	changeVal = changeVal.replace(/&quot;/g, '\"');
	changeVal = changeVal.replace(/&apos;/g, '\'');
	
	changeVal = changeVal.replace(/&#39;/g, '\'');
	changeVal = changeVal.replace(/&#40;/g, '\"');
	changeVal = changeVal.replace(/&#40;/g, '\\(');
	changeVal = changeVal.replace(/&#41;/g, '\\)');
	
	return changeVal
}

// 전시카테고리 팝업
function openDisplaySearchPopup(obj){
	
	clickObj = obj;
	
	//var param = "&selectMallYn=Y&searchDpmlNo=EC1&searchShopTpCd=01&searchTlwtLfYn=Y";
	var param = "&selectMallYn=Y&searchDpmlNo=EC1&searchShopTpCd=01";
	var displaySearchPopup = new openPopup();
	displaySearchPopup.open("displaySearchPopup",
			"/mgnt/display/displayListPop.do",
			param, 
			{	isMultiSelect:true,
				width:1000,
				height:700, 
				callbackF:function(data) {	
					setDisplayShop(data);
			} 
		});
}

// 품목군 팝업
function openGoodsArticleSearchPopup(obj){
	
	clickObj = obj;
	
	//var param = "&selectMallYn=Y&searchDpmlNo=EC1&searchShopTpCd=01&searchTlwtLfYn=Y";
	var param = "&selectMallYn=Y&searchDpmlNo=EC1&searchShopTpCd=01";
	var goodsArticleSearchPopup = new openPopup();
	goodsArticleSearchPopup.open("goodsArticleSearchPopup",
			"/mgnt/article/articleListPop.do",
			param, 
			{	isMultiSelect:true,
				width:1000,
				height:700, 
				callbackF:function(data) {	
					setGoodsArticle(data);
			} 
		});
}

function openPopup(){
	
	var popObj = this;
	
	var top		= (screen.height)/2 - 250;
	var left	= (screen.width)/2 - 500;
	
	this.defaultOption = {
		menubar : "no",
		scrollbars : "yes",
		resizable : "no",
		status : "yes",
		width : 1000,
		height : 600,
		top : top,
		left : left,
		isMultiSelect : false,
		callbackF : null
	},
	
	this.popupWindow = null,
	
	this.data = null,
	
	this.open = function (id, url, param, o) {
		if (o != undefined && o != null) {	// 옵션 셋팅
			if (o.isMultiSelect != undefined && o.isMultiSelect != null) {
				this.defaultOption.isMultiSelect = o.isMultiSelect;
			}
			if (o.width != undefined && o.width != null) {
				this.defaultOption.width = o.width;
			}
			if (o.height != undefined && o.height != null) {
				this.defaultOption.height = o.height;
			}
			if (o.scrollbars != undefined && o.scrollbars != null) {
				this.defaultOption.scrollbars = o.scrollbars;
			}
			if (o.callbackF != undefined && o.callbackF != null) {
				this.defaultOption.callbackF = o.callbackF;
			}
		}
		
		this.popupWindow = window.open(
				url + "?isMultiSelect=" + this.defaultOption.isMultiSelect + param,
				id,
				"menubar=" + this.defaultOption.menubar + ",scrollbars=" + this.defaultOption.scrollbars 
				+ ",resizable=" + this.defaultOption.resizable + ",status=" + this.defaultOption.status 
				+ ",width=" + this.defaultOption.width + ",height=" + this.defaultOption.height 
				+ ",top=" + this.defaultOption.top + ",left=" + this.defaultOption.left
		);
		
		if (this.popupWindow != undefined && this.popupWindow!= null) {
			this.popupWindow.focus();
		}
    	this.popupWindow.onload = new function(){
    		setTimeout(function() { 
    			if(popObj.popupWindow.closed == false) {
    				popObj.popupWindow.RunCallbackFunction = popObj.selected;
    			}
    		}, 
    		1000);
			
    		//화면이 늦게 로딩되는걸 대비해서 5초 후도 세팅
    		setTimeout(function() {
    			if(popObj.popupWindow.closed == false) {
    				popObj.popupWindow.RunCallbackFunction = popObj.selected;
    			} 
    		}, 
    		5000);
    		setTimeout(function() {
    			if(popObj.popupWindow.closed == false) {
    				popObj.popupWindow.RunCallbackFunction = popObj.selected;
    			} 
    		}, 
    		10000);
		};
    
	},
	
	/**
	 * 선택 후 실행되어야 할 function 실행
	 */
	this.selected = function(data) {
		if (popObj.defaultOption.callbackF != undefined && popObj.defaultOption.callbackF != null) {
			popObj.defaultOption.callbackF(data);
		}
		if (popObj.popupWindow != undefined && popObj.popupWindow != null) {
			popObj.popupWindow.close();
		}
	};
}
