var option = {};
option = {
		"validList" : [
		      {"objId" : "titNm", 			"label" : "제목", 			"rule" : { "required":true,  "maxlength":200}		}
			, {"objId" : "otshEvntDvCd", 	"label" : "구분", 			"rule" : { "required":true	}						}
		]
	};

//교외행사 리스트
var recuOtshList = {
	// 전역변수
	GV : {
		
	},
	// 초기화
	init : function () {
		
		// 접수시작일자
		$("#searchRcvSdt").datepicker();
		$('#searchRcvSdt').datepicker("option", "onClose", function ( selectedDate ) {
		    $("#searchRcvClseDt").datepicker( "option", "minDate", selectedDate );
		});
		// 접수종료일자
		$("#searchRcvClseDt").datepicker();
		$('#searchRcvClseDt').datepicker("option", "minDate", $("#searchRcvSdt").val());
	    $('#searchRcvClseDt').datepicker("option", "onClose", function ( selectedDate ) {
	        $("#searchRcvSdt").datepicker( "option", "maxDate", selectedDate );
	    });
	    
	    $("#searchRcvSdt").inputmask('9999.99.99');									// 접수시작일자
	    $("#searchRcvClseDt").inputmask('9999.99.99');								// 접수종료일자
		
		// 이벤트 추가
		recuOtshList.addEvent();
	},
	// 이벤트 추가
	addEvent : function() {
		// 접수시작일자 
		$("#searchRcvSdt").on("focusout", function(){
	    	var searchRcvSdt	= this.value;
	    	searchRcvSdt		= searchRcvSdt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	var searchRcvClseDt	= $("#searchRcvClseDt").val();
	    	searchRcvClseDt		= searchRcvClseDt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	if ( $.trim(searchRcvSdt) == "" ) return;
	    	
	    	if ( !isValidDate(searchRcvSdt) ) {
	    		alert("유효하지 않는 날짜 입니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    	
	    	if ( $.trim(searchRcvSdt) == "" || $.trim(searchRcvClseDt) == "" ) return;
	    	if( getDayInterval(searchRcvSdt, searchRcvClseDt) < 0 ) {
	    		alert("시작일자가 종료일자 보다 큽니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    	
	    });
	    
		// 접수종료일자 
	    $("#rcvClseDt").on("focusout", function(){
	    	var rcvSdt	= $("#rcvSdt").val();
	    	rcvSdt		= rcvSdt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	var rcvClseDt	= this.value;
	    	rcvClseDt		= rcvClseDt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	if ( $.trim(rcvClseDt) == "" ) return;
	    	
	    	if ( !isValidDate(rcvClseDt) ) {
	    		alert("유효하지 않는 날짜 입니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    	
	    	if ( $.trim(rcvSdt) == "" || $.trim(rcvClseDt) == "" ) return;
	    	if( getDayInterval(rcvSdt, rcvClseDt) < 0 ) {
	    		alert("시작일자가 종료일자 보다 큽니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    });
	},
	// 검색
	fnSearch : function() {
		recuOtshList.fnListPage(1);
	},
	// 페이징에서 호출
	fnListPage : function(pageNo) {
		var frm	= document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshList.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshList.do";
		
		frm.submit();
	},
	// 교외행사 상세 페이지로 이동
	fnView : function(otshEvntNo) {
		var frm = document.form1;
		
		$("#form1 #otshEvntNo").val(otshEvntNo);
		
		frm.method	= "post";
		frm.target	= "_self";
		frm.action	= CTX_PATH + "/mgnt/recu/otsh/otshView.do";
		frm.submit();
	},
	// 신규/수정 페이지로 이동
	fnEdit : function(cmd, otshEvntNo) {
		
		var frm = document.form1
		
		$("#form1 #cmd").val(cmd);
		$("#form1 #otshEvntNo").val(otshEvntNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshHandle.do";
		
		frm.submit();
		
	},
	// 삭제
	fnDelete : function(otshEvntNo) {
		var data = {};
		
		data.otshEvntNo	= otshEvntNo;
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/otsh/otshDelete.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					recuOtshList.fnInitList();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshList.do";
		
		frm.submit();
	}
}

//교외행사 등록/수정
var recuOtshHandle = {
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		
		// 접수시작일자
		$("#rcvSdt").datepicker();
		$('#rcvSdt').datepicker("option", "onClose", function ( selectedDate ) {
		    $("#rcvClseDt").datepicker( "option", "minDate", selectedDate );
		});
		// 접수종료일자
		$("#rcvClseDt").datepicker();
		$('#rcvClseDt').datepicker("option", "minDate", $("#rcvSdt").val());
	    $('#rcvClseDt').datepicker("option", "onClose", function ( selectedDate ) {
	        $("#rcvSdt").datepicker( "option", "maxDate", selectedDate );
	    });
	    
	    $("#rcvSdt").inputmask('9999.99.99');									// 접수시작일자
	    $("#rcvClseDt").inputmask('9999.99.99');								// 접수종료일자
	    
	    // 이벤트 추가
	    recuOtshHandle.addEvent();
	    
	    $("#rcvSdt").val(rcvSdt);
	    $("#rcvClseDt").val(rcvClseDt);
	},
	// 이벤트 추가
	addEvent : function() {
		// 접수시작일자 
		$("#rcvSdt").on("focusout", function(){
	    	var rcvSdt	= this.value;
	    	rcvSdt		= rcvSdt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	var rcvSdt	= $("#rcvSdt").val();
	    	rcvSdt		= rcvSdt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	if ( $.trim(rcvSdt) == "" ) return;
	    	
	    	if ( !isValidDate(rcvSdt) ) {
	    		alert("유효하지 않는 날짜 입니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    	
	    	if ( $.trim(rcvSdt) == "" || $.trim(rcvSdt) == "" ) return;
	    	if( getDayInterval(rcvSdt, rcvSdt) < 0 ) {
	    		alert("시작일자가 종료일자 보다 큽니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    	
	    });
	    
		// 접수종료일자 
	    $("#rcvSdt").on("focusout", function(){
	    	var rcvSdt	= $("#rcvSdt").val();
	    	rcvSdt		= rcvSdt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	var rcvSdt	= this.value;
	    	rcvSdt		= rcvSdt.replaceAll("_", "").replaceAll(".", "");
	    	
	    	if ( $.trim(rcvSdt) == "" ) return;
	    	
	    	if ( !isValidDate(rcvSdt) ) {
	    		alert("유효하지 않는 날짜 입니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    	
	    	if ( $.trim(rcvSdt) == "" || $.trim(rcvSdt) == "" ) return;
	    	if( getDayInterval(rcvSdt, rcvSdt) < 0 ) {
	    		alert("시작일자가 종료일자 보다 큽니다.");
	    		this.value	= "";
	    		$(this).focus();
	    	}
	    });
	},
	// 목록 페이지로 이동
	fnList : function(pageNo) {
		var frm = document.formInit;
		
		$("#form1 currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshList.do";
		
		frm.submit();	
	},
	// 저장
	fnSave : function() { 
		
		var data = {};
		if ( !recuOtshHandle.validate(data) ) {
			return false;
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("저장하시겠습니까?") ) return false;
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/otsh/otshSave.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					/*
					 * 검색조건 초기화 리스트 페이지 이동
					 */
					recuOtshHandle.fnInitList();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
		
	},
	// 삭제
	fnDelete : function(otshEvntNo) {
		var data = {};
		
		data.otshEvntNo	= otshEvntNo;
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/otsh/otshDelete.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					recuOtshHandle.fnInitList();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 교외행사 저장시 입력값 체크
	validate : function(data) {
		
		$("#form1").validInit({onsubmit : false, onfocusout : false});
		
		$("#form1").validAddRules(option);		
		if($("#form1").validate().form() == false) {
	    	return false;
		}
		
		data.cmd			= $("#cmd").val();
		
		/*
		 * 교외행사
		 */
		if ( data.cmd == "U" ) {
			data.otshEvntNo			= $("#otshEvntNo").val();
			//data.otshEvntStcd		= $("#otshEvntStcd").val();		// 교외행사상태코드(001:신청, 002:승인, 003:미승인)
		} else if ( data.cmd == "I" ) {
			data.otshEvntStcd		= "001";						// 교외행사상태코드(001:신청, 002:승인, 003:미승인)
			data.inqT				= 0;							// 조회수
		}
		data.titNm					= $("#titNm").val();											// 제목
		data.otshEvntDvCd			= $('input:radio[name="otshEvntDvCd"]:checked').val();			// 교외행사구분코드
		data.rcvSdt					= $("#rcvSdt").val();											// 접수시작일자
		data.rcvClseDt				= $("#rcvClseDt").val();										// 접수종료일자
		
		data.rcvSdt					= data.rcvSdt.replaceAll('.','').replaceAll('-','');			// 접수시작일자
		data.rcvClseDt				= data.rcvClseDt.replaceAll('.','').replaceAll('-','');			// 접수종료일자			
		
		// 상세내용
		oEditors.getById["otshEvntCn"].exec("UPDATE_CONTENTS_FIELD", []);
		if(document.getElementById("otshEvntCn").value.trim() == "<br>" || document.getElementById("otshEvntCn").value.trim() == "<p><br></p>"){
			alert("상세내용은 필수 입력입니다.");
		    oEditors.getById["otshEvntCn"].exec("FOCUS");
		    return false;
	    }
		
		data.otshEvntCn	= document.getElementById("otshEvntCn").value.trim();
		
		return true;
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshList.do";
		
		frm.submit();
	}
	
}

//교외행사 상세
var recuOtshView = { 
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuOtshView.addEvent();
	},
	// 이벤트 추가
	addEvent : function() {
	},
	// 교외행사상태 처리
	fnOtshEvntStcd : function(otshEvntStcd) {
		var data = {};
		
		var confirmMsg	= "";
		if ( otshEvntStcd == "002" ) {
			confirmMsg = "승인";
		} else if ( otshEvntStcd == "003" ) {
			confirmMsg = "미승인";
		}
		
		data.cmd			= "U";
		data.otshEvntNo		= $("#otshEvntNo").val();
		data.otshEvntStcd	= otshEvntStcd;
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm(confirmMsg+"하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/otsh/otshSave.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					/*
					 * 리스트 페이지
					 */
					var pageNo	= nvl($("#form1 #currentPage").val(), 1);
					recuOtshView.fnList(pageNo);
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 신규/수정 페이지로 이동
	fnEdit : function(cmd) {
		var frm = document.form1;
		
		$("#form1 #cmd").val(cmd);

		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshHandle.do";
		
		frm.submit();
	},
	// 삭제
	fnDelete : function() {
		var data = {};
		
		data.recuInfoNo	= $("#recuInfoNo").val();
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/otsh/otshDelete.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					/*
					 * 리스트 페이지 검색 조건들 초기화
					 */
					recuOtshView.fnInitList();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 교외행사 조회 페이지로 이동
	fnList : function(pageNo) {
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshList.do";
		
		frm.submit();
	},
	// 상세페이지 reload
	fnReload : function() {
		var frm = document.form1;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshView.do";
		
		frm.submit();
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/otsh/otshList.do";
		
		frm.submit();
	}
	
}