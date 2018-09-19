//var oEditors = [];
var option = {};
option = {
		"validList" : [
		      {"objId" : "brndNm", 			"label" : "브랜드명", 			"rule" : { "required":true,   "maxlength":200}		}
			, {"objId" : "brndKorNm", 		"label" : "브랜드한글명", 		"rule" : { "required":false,  "maxlength":200}		}
			, {"objId" : "brndEngNm", 		"label" : "브랜드영문명", 		"rule" : { "required":false,  "maxlength":200}		}
			, {"objId" : "brndDescCont", 	"label" : "브랜드상세설명", 		"rule" : { "required":false,  "maxlength":4000}		}
			
			
		]
	};

// 브랜드 정보 리스트
var brandList = {
	// 전역변수
	GV : {
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		brandList.addEvent();
		
	},
	// 이벤트 추가
	addEvent : function() {
		
	},
	// 검색
	fnSearch : function() {
		recuRecruitList.fnListPage(1);
	},
	// 페이징에서 호출
	fnListPage : function(pageNo) {
		var frm	= document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/brand/initMgntBrand.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/brand/initMgntBrand.do";
		
		frm.submit();
	},
	// 신규/수정 페이지로 이동
	fnEdit : function(cmd, brndNo) {
		
		var frm = document.form1
		
		$("#form1 #cmd").val(cmd);
		$("#form1 #brndNo").val(brndNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/brand/brandHandle.do";
		
		frm.submit();
		
	}
	
}

// 브랜드 정보 등록/수정
var brandHandle = {
	// 전역변수
	GV : {	 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		brandHandle.addEvent();
		
	},
	// 이벤트 추가
	addEvent : function() {
		
	},
	// 목록 페이지로 이동
	fnList : function(pageNo) {
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/brand/initMgntBrand.do";
		
		frm.submit();
	},
	// 저장
	fnSave : function() { 
		
		var data = {};
		if ( !brandHandle.validate(data) ) {
			return false;
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("저장하시겠습니까?") ) return false;
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/brand/brandSave.json",
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
					/*
					 * 검색조건 초기화 리스트 페이지 이동
					 */
					brandHandle.fnInitList();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
		
	},
	// 삭제
	fnDelete : function() {
		var data = {};
		
		if ( nvl($("#form1 #brndNo").val(), "") != "" ) {
			data.brndNo	= $("#form1 #brndNo").val();
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/brand/brandDelete.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					recuRecruitList.fnInitList(1);
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
		
		var brndBaseVO;
		var addFileList;
		var addFileInfo;
		
		var etrNo;
		
		data.cmd			= $("#form1 #cmd").val();				// 처리구분(I:등록, U:수정)
		
		if ( nvl($("#form1 #brndNo").val(), "") != "" ) {
			data.brndNo	= $("#form1 #brndNo").val();			   	// 브랜드번호 
		}
		data.brndNm			= $("#form1 #brndNm").val();           	// 브랜드명 	
		data.brndKorNm		= $("#form1 #brndKorNm").val();        	// 브랜드한글명 
		data.brndEngNm		= $("#form1 #brndEngNm").val();        	// 브랜드영문명 
		data.brndDescCont	= $("#form1 #brndDescCont").val();     	// 브랜드상세설명
		
		if ( $('input[name="addFileList"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList"]').each(function() {
				addFileList.push(this.value);
			});
			data.addFileList	= addFileList;
		}
		
		return true;
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		$("#formInit #currentPage").val(1);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/brand/initMgntBrand.do";
		
		frm.submit();
	}
	
}