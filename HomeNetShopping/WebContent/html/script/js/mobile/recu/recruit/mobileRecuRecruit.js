// 채용정보 리스트
var recuRecruitList = {
	// 전역변수
	GV : {
		
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitList.addEvent();
		
		/*// 경력사항
		var checkCarrFlag = false;
		$("input:checkbox[name=searchRecuCarrTgtCdList]:checked").each(function(){
			if ( this.value == '003' ) {
				checkCarrFlag = true; 
				return;
			}
		});
		
		// 경력 초기화
		if ( !checkCarrFlag ) {
			$("#searchCarrCd").prop("disabled", true);
			$('#searchCarrCd option').eq(0).prop('selected',true);
		}*/
	},
	// 이벤트 추가
	addEvent : function() {
		/*// 
		$("input:checkbox[name=searchRecuCarrTgtCdList]").change(function() {
			if (this.value == '003') {
				if ( this.checked ) {
					$("#searchCarrCd").prop("disabled", false);
				} else {
					$("#searchCarrCd").prop("disabled", true);
					$('#searchCarrCd option').eq(0).prop('selected',true);
				}
			}
		});*/
	},
	// 검색
	fnSearch : function() {
		recuRecruitList.fnListPage(1);
	},
	// 상세 검색조건 펼치기
	fnSearchDetailOpen : function() {
		
	},
	// Order 검색
	fnOrderSearch : function(orderCol, orderSort) {
		$("#form1 #orderCol").val(orderCol);
		$("#form1 #orderSort").val(orderSort);
		
		recuRecruitList.fnListPage(1);
	},
	// Tab 검색
	fnTab : function(searchEtrFomCd) {
		var frmInit = document.formInit;
		
		$("#formInit #searchEtrFomCd").val(searchEtrFomCd);
		$("#formInit #currentPage").val(1);
	
		frmInit.method = "post";
		frmInit.target = "_self";
		frmInit.action = CTX_PATH + "/mobile/recu/recruit/recruitList.do";
		frmInit.submit();
		
	},
	// 페이징에서 호출
	fnListPage : function(pageNo) {
		var frm	= document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 나이스DNB 기업싞용정보 팝업
	fnCompany : function(etrNo) {
		// 나이스DNB 기업싞용정보 연게 API 를 받아야함.
	},
	// 채용정보 상세 페이지로 이동
	fnView : function(recuInfoNo) {
		var frm = document.form1;
		
		$("#form1 #recuInfoNo").val(recuInfoNo);
		
		frm.method	= "post";
		frm.target	= "_self";
		frm.action	= CTX_PATH + "/mobile/recu/recruit/recruitView.do";
		frm.submit();
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		$("#formTab #searchTab").val("");
		$("#formInit #currentPage").val(1);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 폼 초기화
	formInit : function() {
		var frm = document.form1;
		
		for (var i=0; i<frm.elements.length; i++) {
			var inputType 	= frm.elements[i].getAttribute("type");
			var inputId 	= frm.elements[i].getAttribute("id");
			var val			= frm.elements[i].getAttribute("value");
			//console.log("inputId["+i+"]["+inputType+"]["+inputId+"]=["+val+"]")
			
			if ( (inputType == 'hidden' || inputType == 'text') ) {
				frm.elements[i].setAttribute("value", "");
			}
		}
	},
	// 상세 검색조건 열기
	fnOpen : function() {
		if($('.search_detail').css("display") == "block"){
			$("#form1 #openYn").val("N");
		}else{
			$("#form1 #openYn").val("Y");
		}
		
	}
}

//채용정보 상세
var recuRecruitView = { 
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitView.addEvent();
		
		// 기업채용정보 조회
		//recuRecruitEtrList.fnRecruitEtrList();
	},
	// 이벤트 추가
	addEvent : function() {
	},
	// 나이스DNB 기업싞용정보 팝업
	fnCompany : function(etrNo) {
		// 나이스DNB 기업싞용정보 연게 API 를 받아야함.
	},
	// 스크랩
	fnScrap : function() {
		var data = {};
		
		data.scrpDvCd		= "001";
		data.scrpCnnctNo	= $("#recuInfoNo").val();	// 스크랩연계번호
		
		if ( !confirm("스크랩하시겠습니까?") ) return false; 
		
		fnScrap(data, "recuRecruitView.fnScrapCallback");
	},
	fnScrapCallback : function(completeYn, resultMsg) {
		console.log("callBack=["+completeYn+"]["+resultMsg+"]");
	},
	// 채용정보 조회 페이지로 이동
	fnList : function(pageNo, sUrl) {
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		if ( sUrl == '' ) {
			frm.action = CTX_PATH + "/mobile/recu/recruit/recruitList.do";
		} else {
			frm.action = CTX_PATH + sUrl;
		}
		
		frm.submit();
	},
	// 상세페이지 reload
	fnReload : function() {
		var frm = document.form1;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/recruit/recruitView.do";
		
		frm.submit();
	},
	// 지원하기
	fnRecuApply  : function(submMethCd) {
		
		if ( submMethCd == '001' ) {
			// 바로제출
			var frm	= document.form1
			var VIEW_APPLY = window.open('', 'VIEW_APPLY' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
			VIEW_APPLY.focus();   
			
			frm.action = CTX_PATH+"/user/recu/recruit/recruitApplyHandlePop.do";
			frm.method = "POST";
			frm.target = "VIEW_APPLY";
			alert(frm.action)
			frm.submit();
		} else {
			// 온라인입사지원서 제출 - 공통으로 이민섭이 개발함.
			alert("온라인입사지원서 제출");
		}
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mobile/recu/recruit/recruitList.do";
		
		frm.submit();
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
	}
}