var validMsgMap  = {};

validMsgMap["required"] = " 필수 입력값입니다.";
validMsgMap["remote"] = " 항목을 수정하세요.";
validMsgMap["email"] = " 유효하지 않은 이메일 주소입니다.";
validMsgMap["url"] = " 유효하지 않은 URL입니다.";
validMsgMap["date"] = " 올바른 날짜를 입력하세요.";
validMsgMap["dateISO"] = " 올바른 날짜(YYYY.MM.DD)를 입력하세요.";
validMsgMap["number"] = " 유효한 숫자가 아닙니다.";
validMsgMap["digits"] = " 숫자만 입력 가능합니다.";
validMsgMap["creditcard"] = " 신용카드 번호가 바르지 않습니다.";
validMsgMap["equalTo"] = " 같은 값을 다시 입력하세요.";
validMsgMap["minDate"] = " {2}보다 작을 수 없습니다.";
validMsgMap["maxDate"] =" {2}보다 클 수 없습니다.";
validMsgMap["minlength"] = " {1}자 이상 입력해야 합니다.";
validMsgMap["maxlength"] = " {1}자 이상 입력할수 없습니다.";
validMsgMap["rangelength"] = " 문자 길이가 {1} 에서 {2} 사이의 값을 입력하세요.";
validMsgMap["range"] = " {1}이상 {2}이하로 입력해주세요.";
validMsgMap["min"] = " {1}이상으로 입력해주세요.";
validMsgMap["max"] = " {1}이하로 입력해주세요.";
validMsgMap["selectRequired"] = " 필수 값입니다.";
validMsgMap["password"] = "비밀번호는 9~15자 영문+숫자+특수문자조합";

$(function() {
	$.fn.extend({
				validInit : function(option) {
					var dafaultOption = {
						onsubmit : false,
						onfocusout : false,
						onkeyup: false

					};
					var target = this;
					$(target).validate({
						onsubmit : isEmpty(option.onsubmit) ? dafaultOption.onsubmit : option.onsubmit,
						onfocusout : isEmpty(option.onfocusout) ? dafaultOption.onfocusout : option.onfocusout,
						onkeyup : isEmpty(option.onkeyup) ? dafaultOption.onkeyup : option.onkeyup,
						submitHandler : function(target) {
							return true;
						},
						showErrors : function(errorMap, errorList) {
							if (this.numberOfInvalids()) {
								alert(errorList[0].message);
								$(errorList[0].element).focus();
							}
						}
					});
				},
				validAddRules : function(option) {
					
					var formTarget = this;
					var validList = option.validList;
					$.each(validList, function(i, validTarget) {
						
						var target = validTarget.objId;
						var rules = validTarget.rule;
						var isArr = false;
						
						if(!isEmpty(validTarget.isArr)) {
							isArr = validTarget.isArr;
						}
						
						//var objType = $('[name="'+ target +'"]').prop('tagName').toLowerCase();
						//console.log(target);
						var label = "";
						if (!isEmpty($('[name="'+ target +'"]').attr('label'))) {
							label = $('[name="'+ target +'"]').attr('label');
						} else {
							label = validTarget.label;
						}
									
						for(var obj in rules) {
													
							var ruleId = obj;
							var ruleValue = rules[ruleId];
							var ruleObj = {}; 
						
							//룰 셋팅
							ruleObj[ruleId] = ruleValue;
							
							//룰 메세지
							var ruleObjMsg = {};
							var ruleMsg = validMsgMap[ruleId];
							if(!isEmpty(ruleMsg)) {
								
								
								if(new RegExp("\\{[0-9]\\}").test(ruleMsg)) {
									if((typeof ruleValue).toLowerCase() === "number") {
										ruleMsg = ruleMsg.replace(new RegExp("\\{" + 1 + "\\}", "g"), ruleValue);
									} else if((typeof ruleValue).toLowerCase() === "object") {
										$.each(ruleValue, function(i, n) {
											ruleMsg = ruleMsg.replace(new RegExp("\\{" + (i+1) + "\\}", "g"), n);
										});
									}
								} 
								ruleObjMsg[ruleId] = label + ruleMsg;
								
								//console.log("label > "+label);
								/*if(typeof ruleValue === "boolean") {
									ruleObjMsg[ruleId] = fnEkpMsg(ruleMsg, label);
								} else if(typeof ruleValue === "NUMBER" || typeof ruleValue === "number") {
									ruleObjMsg[ruleId] = fnEkpMsg(ruleMsg, label, ruleValue);
								} else {
									if(ruleValue.length == 2) {
										ruleObjMsg[ruleId] = fnEkpMsg(ruleMsg, label, ruleValue[0], ruleValue[1]);
									} else if(ruleValue.length == 3) {
										ruleObjMsg[ruleId] = fnEkpMsg(ruleMsg, label, ruleValue[0], ruleValue[1], ruleValue[2]);
									} else if(ruleValue.length == 4) {
										ruleObjMsg[ruleId] = fnEkpMsg(ruleMsg, label, ruleValue[0], ruleValue[1], ruleValue[2], ruleValue[3]);
									}
								}*/
								
								ruleObj["messages"] = ruleObjMsg;
							}
							/*if(objType == "input") {
								//룰추가
								$('input[name="'+ target +'"]').rules("add", ruleObj);	
							} else if(objType == "select") {
								//룰추가
								$('select[name="'+ target +'"]').rules("add", ruleObj);	
							}
							*/
							
							 if(isArr){
								 $('[name="'+ target +'"]').each(function(i){
							      	$('[name="'+ target +'"]:eq('+i+')').rules("add", ruleObj);	
							     });
						    } else {
						    	$('[name="'+ target +'"]').rules("add", ruleObj);	
						    }
							
	
						}
					});
				},
			});

});

/**
 * selectbox 필수값 체크 확장
 */
$.validator.addMethod("selectRequired", function(value, element, params) {
	//console.log(this.errorList[0]);
	if(isEmpty(value)) {
	   return false;
	} else {
		return true;
	}
});

/**
 * value 값이 param값보다 작으면 false 크면 true 
 * ex) {"objId" : "banrEndD", "label" : "종료일자", "rule" : { "required" : true, "date" : true, "maxlength":8, "minDate": [$("#banrSrtD").val(), "시작일자"]}},
 */

$.validator.addMethod("minDate", function(value, element, params) {
	if(toNumber(value) < toNumber(params[0])) {
		return false;
	} else {
		return true;
	}
});

/**
 * value 값이 param값보다 크면 false 작으면 true
 * ex) {"objId" : "banrSrtD", "label" : "시작일자", "rule" : { "required" : true, "date" : true, "maxlength":8, "maxDate": [$("#banrEndD").val(), "종료일자"]}},
 */
$.validator.addMethod("maxDate", function(value, element, params) {
	if(toNumber(value) > toNumber(params[0])) {
		return false;
	} else {
		return true;
	}
});

$.validator.addMethod("password", function(value, element, params) {
	return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // consists of only these
		    && /[a-z]/.test(value) // has a lowercase letter
		    && /\d/.test(value) // has a digit
});


/**
 * server validator를 위한 공통 함수
 * url : 보내야 되는 url 
 * formName : form name 
 * callback 콜백 함수 없어도 된다. String임
 */
function fnValAjax(url, formName, callBack) {
	
	$.ajax({
		async : false,
		type: 'POST',
		url: url,
		data : $("#"+formName).serialize(),
		dataType:"json",
		success : function (data) {
			if(data.vaildErrorMsg != undefined && !isEmpty(data.vaildErrorMsg)) {
				if(!isEmpty(data.vaildErrorFileId)) {
                    $("#"+data.vaildErrorFileId).focus();
                }
				
				alert(data.vaildErrorMsg);
				
				if(data.vaildErrorFileId == "ERR001") {
					fnBackLogin();
				}
				
				return false ;
			}
			
			if(callBack != null && callBack != "") {
				window[callBack](data) ;
				//var fn = new Function(callBack+"();");
				//fn();
			} 
			
		}, 
		error: function(xhr, textStatus, errorThrown) {
			if(data.status == "401") {
				var curUrl = document.location.href;
				var loginUrl = "";
				if(curUrl.indexOf("/mgnt/") > -1) {
					loginUrl = _CTX_PATH + "/mgnt/";
				} else if(curUrl.indexOf("/app/") > -1) {
					loginUrl = _CTX_PATH + "/app/";
				} else {
					loginUrl = _CTX_PATH + "/";
				}
				
				alert("해당 화면에 접근권한이 없습니다.");
				
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
			} else if(data.status == "403") {
				var curUrl = document.location.href;
				var loginUrl = "";
				if(curUrl.indexOf("/mgnt/") > -1) {
					loginUrl = _CTX_PATH + "/ekp/mgnt/login/initMLOLogin.do";
				} else if(curUrl.indexOf("/app/") > -1) {
					loginUrl = _CTX_PATH + "/ekp/app/login/retrieveULOLogin.do";
				} else {
					loginUrl = _CTX_PATH + "/ekp/user/login/retrieveULOLogin.do";
				}
				
				alert("로그인 정보가 없습니다. 로그인 후 사용하여주시기 바랍니다.");
				
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
		}
	});
}

function fnBackLogin() {
	document.location.href = "/mgnt/main/auth/noRefererURIProgrmAction.do";
}