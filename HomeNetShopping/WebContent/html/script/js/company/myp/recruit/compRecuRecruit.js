var option = {};
option = {
		"validList" : [
		      {"objId" : "etrNm", 			"label" : "기업명", 			"rule" : { "required":true  }						}
			, {"objId" : "bizJangNo", 		"label" : "사업자번호", 		"rule" : { "required":true,  "maxlength":200}		}
			, {"objId" : "etrFomCd", 		"label" : "기업형태", 			"rule" : { "required":true 	}						}
			, {"objId" : "zonCd", 			"label" : "지역", 			"rule" : { "required":true  }						}
			, {"objId" : "tgtDc", 			"label" : "대상구분", 			"rule" : { "required":true  }						}
			, {"objId" : "recuInfoTitNm", 	"label" : "내용(제목)", 		"rule" : { "required":true,  "maxlength":200}		}
			, {"objId" : "submMethCd", 		"label" : "제출방법", 			"rule" : { "required":true	}						}
			, {"objId" : "rcvClseDt", 		"label" : "접수마감일자", 		"rule" : { "required":true	}						}
			, {"objId" : "rcvClseCd", 		"label" : "접수마감", 			"rule" : { "required":true	}						}
			, {"objId" : "carrCd", 			"label" : "경력", 			"rule" : { "required":false	}						}
			
		]
	};


// 기업회원 채용정보 등록/수정
var compRecuRecruitHandle = {
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		compRecuRecruitHandle.addEvent();
		
		
		// 접수마감
		var rcvClseCd = $("input:radio[name=rcvClseCd]:checked").val();
		compRecuRecruitHandle.fnChkRcvClseDt(rcvClseCd);
		
		// 경력사항
		var recuCarrTgtCd = $("input:radio[name=recuCarrTgtCd]:checked").val();
		compRecuRecruitHandle.fnSetCareer(recuCarrTgtCd);
		
		var cmd = $("#form1 #cmd").val();
		if ( cmd == 'I' ) {
			compRecuRecruitHandle.fnDefaultForm();
		}
	},
	// 이벤트 추가
	addEvent : function() {
		// 접수마감일자
		$("#rcvClseDt").datepicker();
		
		$("#rcvClseDt").inputmask("9999.99.99", {"placeholder": ""}); 
		
		$("input:radio[name=rcvClseCd]").change(function(){
			// 접수마감 
			compRecuRecruitHandle.fnChkRcvClseDt(this.value);
		});
		
		$("input:radio[name=recuCarrTgtCd]").change(function(){
			// 경력사항 
			compRecuRecruitHandle.fnSetCareer(this.value);
		});
	},
	// 저장
	fnSave : function() { 
		
		var data = {};
		if ( !compRecuRecruitHandle.validate(data) ) {
			return false;
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("저장하시겠습니까?") ) return false;
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/company/myp/recruit/recruitSave.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				// Server Valid Error 
				//if( !fnValidError(data) ) return;

				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					var cmd = $("#form1 #cmd").val();
					var pageNo = $("#form1 #currentPage").val()
					if ( cmd == "U" ) {
						/*
						 * 채용 리스트 페이지 이동
						 */
						compRecuRecruitHandle.fnList(pageNo);
					} else {
						/*
						 * 검색조건 초기화 리스트 페이지 이동
						 */
						compRecuRecruitHandle.fnInitList(1);
						}
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
		
	},
	// 채용정보 저장시 입력값 체크
	validate : function(data) {
		
		$("#form1").validInit({onsubmit : false, onfocusout : false});
		
		$("#form1").validAddRules(option);		
		if($("#form1").validate().form() == false) {
	    	return false;
		}
		
		var nWrk110VO;
		var nWrk301VOList;
		var nWrk301VOInfo;
		var nWrk302VOList;
		var nWrk302VOInfo;
		var nWrk303VOList;
		var nWrk303VOInfo;
		
		var addFileList;
		var addFileInfo;
		
		var etrNo;
		
		data.cmd			= $("#cmd").val();
		
		/*
		 * 기업정보
		 */
		nWrk110VO			= new Object();
		nWrk110VO.etrNm		= $("#etrNm").val();
		
		etrNo				= $("#etrNo").val();
		nWrk110VO.etrNo		= etrNo;
		
		nWrk110VO.bizJangNo	= $("#bizJangNo").val();
		nWrk110VO.bizJangNo	= nWrk110VO.bizJangNo.replaceAll("-", "");
		
		
		nWrk110VO.etrFomCd	= $("input:radio[name=etrFomCd]:checked").val();
		nWrk110VO.zonCd		= $("input:radio[name=zonCd]:checked").val();
		nWrk110VO.hmpgUrl	= $("#hmpgUrl").val();
		if ( data.cmd == "I" ) nWrk110VO.delYn		= "N";
		
		data.nWrk110VO		= nWrk110VO;
		
		/*
		 * 채용정보
		 */
		if ( data.cmd == "U" ) {
			data.recuInfoNo	= $("#recuInfoNo").val();
			data.etrNo		= $("#etrNo").val();
		}
		data.tgtDc			= $('input:radio[name="tgtDc"]:checked').val();			// 대상구분(ALL:전체, KR:한국어, EN:영어)
		data.recuInfoTitNm	= $("#recuInfoTitNm").val();
		if ( data.cmd == "I" ) data.recuAprvDc	= "001";							// 채용승인구분코드(001:신청, 002:승인, 003:마감, 004:미승인)
		
		if ( $('input:checkbox[id="toppAnncYn"]').is(":checked") ) {
			data.toppAnncYn = "Y";
		} else {
			data.toppAnncYn = "N";
		}
		data.submMethCd		= $("input:radio[name=submMethCd]:checked").val();		// 제출방법
		
		// 접수마감일자
		data.rcvClseDt		= $("#rcvClseDt").val();
		data.rcvClseDt		= data.rcvClseDt.replaceAll(".", "");
		data.rcvClseCd		= $("input:radio[name=rcvClseCd]:checked").val();		// 접수마감코드
		
		// 채용추천
		if ( $('input:radio[name="recuRcmdCd"]:checked').length > 0 ) {
			
			nWrk301VOList	= new Array();
			$('input:radio[name="recuRcmdCd"]:checked').each(function() {
				nWrk301VOInfo = new Object();
				nWrk301VOInfo.recuRcmdCd = this.value;
				nWrk301VOList.push(nWrk301VOInfo);
			});
			
			data.nWrk301VOList	= nWrk301VOList;
		}
		
		// 채용대상
		if ( $('input:checkbox[name="recuTgtCd"]:checked').length > 0 ) {
			
			nWrk302VOList	= new Array();
			$('input:checkbox[name="recuTgtCd"]:checked').each(function() {
				nWrk302VOInfo = new Object();
				nWrk302VOInfo.recuTgtCd = this.value;
				nWrk302VOList.push(nWrk302VOInfo);
			});
			
			data.nWrk302VOList	= nWrk302VOList;
		}
		
		// 채용유형
		if ( $('input:radio[name="recuTpCd"]:checked').length > 0 ) {
			
			nWrk303VOList	= new Array();
			$('input:radio[name="recuTpCd"]:checked').each(function() {
				nWrk303VOInfo = new Object();
				nWrk303VOInfo.recuTpCd = this.value;
				nWrk303VOList.push(nWrk303VOInfo);
			});
			
			data.nWrk303VOList	= nWrk303VOList;
		}
		
		data.recuCarrTgtCd		= $("input:radio[name=recuCarrTgtCd]:checked").val();		// 경력사항
		data.carrCd				= $("#carrCd option:selected").val();						// 경력
		data.anslAmCd			= $("#anslAmCd option:selected").val();						// 연봉
		
		// 첨부파일
		data.apndFileNo			= $("#apndFileNo").val();									// 수정시 첨부파일 번호
		if ( $('input[name="addFileList"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList"]').each(function() {
				addFileList.push(this.value);
			});
			data.addFileList	= addFileList;
		}
		
		/*
		if ( data.recuCarrTgtCd == "003" && data.carrCd == "" ) {
			alert("경력은 필수 입력입니다.");
			$("#carrCd").focus();
			return false;
		}
		*/
		// 상세내용
		oEditors.getById["recuDtlCn"].exec("UPDATE_CONTENTS_FIELD", []);
		if(document.getElementById("recuDtlCn").value.trim() == "<br>" || document.getElementById("recuDtlCn").value.trim() == "<p><br></p>"){
			alert("상세내용은 필수 입력입니다.");
		    oEditors.getById["recuDtlCn"].exec("FOCUS");
		    return false;
	    }
		
		data.recuDtlCn	= document.getElementById("recuDtlCn").value.trim();
		data.inqT		= 0;
		
		return true;
	},
	// 채용정보 조회 페이지로 이동
	fnList : function(pageNo) {
		var sUrl			= "";
		var compRecruitTab	= $("#form1 #compRecruitTab").val();
		
		if ( compRecruitTab == "1" ) {
			sUrl = CTX_PATH + "/company/myp/recruit/compRecruitWatList.do";
		} else if ( compRecruitTab == "2" ) {
			sUrl = CTX_PATH + "/company/myp/recruit/compRecruitProgList.do";
		} else if ( compRecruitTab == "3" ) {
			sUrl = CTX_PATH + "/company/myp/recruit/compRecruitClseList.do";
		} else {
			sUrl = CTX_PATH + "/company/myp/recruit/compRecruitWatList.do";
		}
		
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = sUrl;
		
		frm.submit();
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function(pageNo) {
		var frm	= document.formInit;
		
		$("#formInit #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/company/myp/recruit/compRecruitWatList.do";
		
		frm.submit();
	},
	// 접수마감일자 셋팅
	fnChkRcvClseDt : function(val) {
		
		if(val != "001") {
			$("#rcvClseDt").val("");
			$("#rcvClseDt").prop('disabled', true);
			$('#rcvClseDt').datepicker('disable');
		    //$('#rcvClseDt').datepicker('setDate', new Date()).datepicker('disable').blur();
		    //$('#rcvClseDt').disableSelection = true;
		    
			//option.validList[5].rule.required = false;
			option.validList[7].rule.required = false;	// 접수마감일자
		} else {
			$("#rcvClseDt").prop('disabled', false);
			$("#rcvClseDt").datepicker('enable');
			
			//option.validList[5].rule.required = true;
			option.validList[7].rule.required = true;	// 접수마감일자
		}
	}, 
	//
	fnSetCareer : function(recuCarrTgtCd) {
		if ( recuCarrTgtCd == "003" ) {
			// 경력
			option.validList[9].rule.required = true;
			
			$("#carrCd").prop("disabled", false);
		} else {
			option.validList[9].rule.required = false;
			
			$("#carrCd").prop("disabled", true);
			$('#carrCd option').eq(0).prop('selected',true);
		}
	},
	// 폼 초기화
	formInit : function() {
		var frm = document.form1;
		
		for (var i=0; i<frm.elements.length; i++) {
			var inputType = frm.elements[i].getAttribute("type");
			var inputId = frm.elements[i].getAttribute("id");
			
			if ( inputType == 'hidden' ) {
				frm.elements[i].setAttribute("value", "");
			}
		}
	},
	fnDefaultForm : function() { 
		$("input:radio[name=tgtDc]").eq(0).prop("checked", true);				// 대상구분
		$("input:radio[name=submMethCd]").eq(0).prop("checked", true);			// 제출방법
		$("input:radio[name=rcvClseCd]").eq(0).prop("checked", true);			// 접수마감
		$("input:radio[name=recuRcmdCd]").eq(0).prop("checked", true);			// 추천
		$("input:radio[name=recuTpCd]").eq(0).prop("checked", true);			// 채용유형
		$("input:radio[name=recuCarrTgtCd]").eq(0).prop("checked", true);		// 경력사항 
	}
	
	
}