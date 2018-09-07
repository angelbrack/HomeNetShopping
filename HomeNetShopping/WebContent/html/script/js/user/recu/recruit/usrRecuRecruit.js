//var oEditors = [];

var _RESM_POP;

// 채용정보 리스트
var recuRecruitList = {
	// 전역변수
	GV : {
		
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitList.addEvent();
		
		// 경력사항
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
		}
	},
	// 이벤트 추가
	addEvent : function() {
		// 
		$("input:checkbox[name=searchRecuCarrTgtCdList]").change(function() {
			if (this.value == '003') {
				if ( this.checked ) {
					$("#searchCarrCd").prop("disabled", false);
				} else {
					$("#searchCarrCd").prop("disabled", true);
					$('#searchCarrCd option').eq(0).prop('selected',true);
				}
			}
		});
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
		frmInit.action = CTX_PATH + "/user/recu/recruit/recruitList.do";
		frmInit.submit();
		
	},
	// 페이징에서 호출
	fnListPage : function(pageNo) {
		var frm	= document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/user/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/user/recu/recruit/recruitList.do";
		
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
		frm.action	= CTX_PATH + "/user/recu/recruit/recruitView.do";
		frm.submit();
	},
	// 신규/수정 페이지로 이동
	fnEdit : function(cmd, recuInfoNo) {
		
		var frm = document.form1
		
		$("#form1 #cmd").val(cmd);
		$("#form1 #recuInfoNo").val(recuInfoNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/user/recu/recruit/recruitHandle.do";
		
		frm.submit();
		
	},
	// 삭제
	fnDelete : function(recuInfoNo, etrNo) {
		
		recuRecruitList.formInit();
		
		var data = {};
		
		data.recuInfoNo	= recuInfoNo;
		data.etrNo		= etrNo;
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/user/recu/recruit/recruitDelete.json",
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
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		$("#formTab #searchTab").val("");
		$("#formInit #currentPage").val(1);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/user/recu/recruit/recruitList.do";
		
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
		recuRecruitEtrList.fnRecruitEtrList();
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
	fnList : function(pageNo) {
		var frm = document.form1;
		
		var sUrl		= "";
		var inflowPath	= $("#form1 #inflowPath").val();
		if ( inflowPath != "" ) {
			sUrl = CTX_PATH + inflowPath;
		} else {
			sUrl = CTX_PATH + "/user/recu/recruit/recruitList.do";
		}
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = sUrl;
		
		frm.submit();
	},
	// 상세페이지 reload
	fnReload : function() {
		var frm = document.form1;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/user/recu/recruit/recruitView.do";
		
		frm.submit();
	},
	// 지원하기
	fnRecuApply : function(submMethCd) {
		
		if ( submMethCd == '001' ) {
			// 바로제출
			var frm	= document.form1
			var VIEW_APPLY = window.open('', 'VIEW_APPLY' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
			VIEW_APPLY.focus();   
			
			frm.action = CTX_PATH+"/user/recu/recruit/recruitApplyHandlePop.do";
			frm.method = "POST";
			frm.target = "VIEW_APPLY";
			
			frm.submit();
		} else {
			// 온라인입사지원서 제출 - 공통으로 이민섭이 개발함.
			//이력서 선택 팝업
			_RESM_POP = window.open(CTX_PATH+"/user/common/popup/resmList.do?callBackFun=fnResmSet", '_RESM_POP' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
			_RESM_POP.focus();  
		}
	},
	// 채용전달
	fnSendRecruit : function() {
		// 바로제출
		var frm	= document.form1
		var VIEW_SEND = window.open('', 'VIEW_SEND' , 'width=800, height=600, resizable=yes, scrollbars=yes, left=200, top=100');
		VIEW_SEND.focus();   
		
		frm.action = CTX_PATH+"/user/recu/recruit/recruitSendHandlePop.do";
		frm.method = "POST";
		frm.target = "VIEW_SEND";
		
		frm.submit();
	}, 
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/user/recu/recruit/recruitList.do";
		
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

//기업채용정보 조회 - 기업별
var recuRecruitEtrList = { 
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitEtrList.addEvent();

	},
	// 이벤트 추가
	addEvent : function() {
	 
	},
	// 기업채용정보 조회
	fnRecruitEtrList : function() {
		var frm 	= $("#form2");
		var param	= {};
		
		param.searchEtrNo 			= $("#form2 #etrNo").val();
		param.currentPage 			= $("#form2 #currentPage").val();
		param.recordCountPerPage	= $("#form2 #recordCountPerPage").val();
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/user/recu/recruit/recruitEtrList.json",
			data: param,
			//contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				//list목록
				$("#itemsList").setTemplateElement("listTmp", [], {filter_data: false}).processTemplate(data);		//화면이 이동하는경우
				
				//페이징
				PageUtil("pager", data.paginationInfo, "recuRecruitEtrList.fnListPage");							//화면이 이동하는경우
				
				var recuEtrTotalCount = 0;
				if (data.paginationInfo.totalRecordCount != undefined) {
					recuEtrTotalCount = data.paginationInfo.totalRecordCount;
					$("#recuEtrTotalCount").html("총 "+comma(recuEtrTotalCount)+"명");
				} else {
					$("#recuEtrTotalCount").html("총 "+recuEtrTotalCount+"명");
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 검색
	fnSearch : function() {
		$("#form2 #currentPage").val("1");
		// 채용신청정보 조회
		recuRecruitEtrList.fnRecruitEtrList();
	},
	// 페이징 검색
	fnListPage  : function(pageNo) {
		$("#currentPage").val(pageNo);
		// 채용신청정보 조회
		recuRecruitEtrList.fnRecruitEtrList();
	},
	// 채용정보 상세 페이지로 이동
	fnView : function(recuInfoNo) {
		var frm = document.form1;
		
		$("#form1 #recuInfoNo").val(recuInfoNo);
		
		frm.method	= "post";
		frm.target	= "_self";
		frm.action	= CTX_PATH + "/user/recu/recruit/recruitView.do";
		frm.submit();
	},
	
}

// 채용 지원하기 Popup
var recuRecruitApplyHandle = { 
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitApplyHandle.addEvent();
	},
	// 이벤트 추가
	addEvent : function() {
	 
	},
	// 채용 지원하기
	fnSaveRecruitApply : function() {
		
		var data = {};
		var addFileList;
		var addFileInfo;
		
		data.cmd		= "I";
		data.recuInfoNo	= $("#recuInfoNo").val();
		
		// 첨부파일
		data.apndFileNo			= $("#apndFileNo").val();									// 수정시 첨부파일 번호
		if ( $('input[name="addFileList"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList"]').each(function() {
				addFileList.push(this.value);
			});
			data.addFileList	= addFileList;
		} else {
			alert("첨부파일를 선택해주세요.");
			return;
		}
		
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
		if ( !confirm("지원하시겠습니까?") ) return false; 
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/user/recu/recruit/recruitApplySave.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					recuRecruitView.fnReload();
					self.close();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
		
	}
		
}

//채용 전달 Popup
var recuRecruitSendHandle = { 
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitSendHandle.addEvent();
	},
	// 이벤트 추가
	addEvent : function() {
	 
	},
	// 채용 전달하기
	fnSaveRecruitSend : function() {
		
		var data = {};
		var mailVO	= new Object();
		
		var mailTo				= $.trim($("#mailTo").val());
		var mailSubject			= $.trim($("#mailSubject").val());
		var mailSmyContent		= $.trim($("#mailSmyContent").val());
		
		if ( mailTo == "" ) {
			alert("메일주소를 입력하세요.");
			$("#mailTo").focus();
			return false;
		}
		
		/*if ( !validateEmail(mailTo) ) {
			alert("메일주소가 올바르지 않습니다.");
			$("#mailTo").focus();
			return false;
		}*/
		if ( !validateMultipleEmails(mailTo) ) {
			alert("메일주소가 올바르지 않습니다.");
			$("#mailTo").focus();
			return false;
		}
		
		if ( mailSubject == "" ) {
			alert("메일제목를 입력하세요.");
			$("#mailSubject").focus();
			return false;
		}
		
		if ( mailSmyContent == "" ) {
			alert("전하고 싶은 말를 입력하세요.");
			$("#mailSmyContent").focus();
			return false;
		}
		
		mailVO.mailTo			= mailTo;
		mailVO.mailSubject		= mailSubject;
		mailVO.mailSmyContent	= mailSmyContent;
		
		data.recuInfoNo	= $("#recuInfoNo").val();
		data.mailVO		= mailVO;
		 
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
		if ( !confirm("지원하시겠습니까?") ) return false; 
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/user/recu/recruit/recruitSendSave.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					self.close();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
		
	}
		
}
