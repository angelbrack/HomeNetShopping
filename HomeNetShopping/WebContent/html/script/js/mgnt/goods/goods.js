var option = {};
option = {
		"validList" : [
		      {"objId" : "brndNm", 			"label" : "상품명", 			"rule" : { "required":true,   "maxlength":200}		}
			, {"objId" : "brndKorNm", 		"label" : "상품한글명", 		"rule" : { "required":false,  "maxlength":200}		}
			, {"objId" : "brndEngNm", 		"label" : "상품영문명", 		"rule" : { "required":false,  "maxlength":200}		}
			, {"objId" : "brndDescCont", 	"label" : "상품상세설명", 		"rule" : { "required":false,  "maxlength":4000}		}
			
			
		]
	};

// 상품 리스트 정보 
var goodsList = {
	// 전역변수
	GV : {
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		this.addEvent();
		
		// // 검색 기간 체크 옵션 change
		$("#searchCheckOptDate").change();
		
		// 시작일자
		$("#searchStartDate").datepicker();
		$('#searchStartDate').datepicker("option", "onClose", function ( selectedDate ) {
		    $("#searchEndDate").datepicker( "option", "minDate", selectedDate );
		});
		// 종료일자
		$("#searchEndDate").datepicker();
		$('#searchEndDate').datepicker("option", "minDate", $("#searchStartDate").val());
	    $('#searchEndDate').datepicker("option", "onClose", function ( selectedDate ) {
	        $("#searchStartDate").datepicker( "option", "maxDate", selectedDate );
	    });
	    
	    $("#searchStartDate").inputmask('9999.99.99');									// 시작일자
	    $("#searchEndDate").inputmask('9999.99.99');									// 종료일자
	},
	// 이벤트 추가
	addEvent : function() {
		// 검색 기간 체크 옵션 change 시
		$("#searchCheckOptDate").change(function(){
			searchDateChange();
		});
	},
	// 검색
	fnSearch : function() {
		this.fnListPage(1);
	},
	// 페이징에서 호출
	fnListPage : function(pageNo) {
		var frm	= document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/goods/initMgntGoods.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/goods/initMgntGoods.do";
		
		frm.submit();
	},
	// 등록/수정 페이지로 이동
	fnEdit : function(cmd, brndNo) {
		
		var frm = document.form1
		
		$("#form1 #cmd").val(cmd);
		$("#form1 #brndNo").val(brndNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/goods/goodsHandle.do";
		
		frm.submit();
	}
}

//상품 등록/수정 정보
var goodsHandle = {
	// 전역변수
	GV : {
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		this.addEvent();
		
	},
	// 이벤트 추가
	addEvent : function() {
		
	}
	
}