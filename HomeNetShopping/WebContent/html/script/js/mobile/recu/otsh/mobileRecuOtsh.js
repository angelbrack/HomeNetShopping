//교외행사 리스트
var recuOtshList = {
	// 전역변수
	GV : {
		
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuOtshList.addEvent();
	},
	// 이벤트 추가
	addEvent : function() {
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
		frm.action = CTX_PATH + "/mobile/recu/otsh/otshList.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/otsh/otshList.do";
		
		frm.submit();
	},
	// 교외행사 상세 페이지로 이동
	fnView : function(otshEvntNo) {
		var frm = document.form1;
		
		$("#form1 #otshEvntNo").val(otshEvntNo);
		
		frm.method	= "post";
		frm.target	= "_self";
		frm.action	= CTX_PATH + "/mobile/recu/otsh/otshView.do";
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
	// 스크랩
	fnScrap : function() {
		var data = {};
		
		data.scrpDvCd		= "004"; 					// 스크랩구분(004:교외행사)
		data.scrpCnnctNo	= $("#otshEvntNo").val();	// 스크랩연계번호
		
		if ( !confirm("스크랩하시겠습니까?") ) return false; 
		
		fnScrap(data, "recuOtshView.fnScrapCallback");
	},
	fnScrapCallback : function(completeYn, resultMsg) {
		console.log("callBack=["+completeYn+"]["+resultMsg+"]");
	},
	// 교외행사 조회 페이지로 이동
	fnList : function(pageNo) {
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/otsh/otshList.do";
		
		frm.submit();
	},
	// 상세페이지 reload
	fnReload : function() {
		var frm = document.form1;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/otsh/otshView.do";
		
		frm.submit();
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/otsh/otshList.do";
		
		frm.submit();
	}
	
}