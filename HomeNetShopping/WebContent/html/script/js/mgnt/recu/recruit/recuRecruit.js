//var oEditors = [];
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
	fnTab : function() {
		var frm = document.formInit;
		
		var searchTab = $("input:radio[name=searchTabRadio]:checked").val();

		
		$("#formInit #searchTab").val(searchTab);
		$("#formInit #currentPage").val(1);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 페이징에서 호출
	fnListPage : function(pageNo) {
		var frm	= document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 채용정보 상세 페이지로 이동
	fnView : function(recuInfoNo) {
		var frm = document.form1;
		
		$("#form1 #recuInfoNo").val(recuInfoNo);
		
		frm.method	= "post";
		frm.target	= "_self";
		frm.action	= CTX_PATH + "/mgnt/recu/recruit/recruitView.do";
		frm.submit();
	},
	// 신규/수정 페이지로 이동
	fnEdit : function(cmd, recuInfoNo) {
		
		var frm = document.form1
		
		$("#form1 #cmd").val(cmd);
		$("#form1 #recuInfoNo").val(recuInfoNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitHandle.do";
		
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
			url: CTX_PATH + "/mgnt/recu/recruit/recruitDelete.json",
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
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
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
		
		// 신청서 현황 조회
		recuRecruitAplcList.fnRecruitAplcList();
	},
	// 이벤트 추가
	addEvent : function() {
	},
	// 채용승인구분 처리
	fnRecuAprvDc : function(recuAprvDc) {
		var data = {};
		
		var confirmMsg	= "";
		if ( recuAprvDc == "002" ) {
			confirmMsg = "승인";
		} else if ( recuAprvDc == "003" ) {
			confirmMsg = "마감";
		} else if ( recuAprvDc == "004" ) {
			confirmMsg = "미승인";
		} else if ( recuAprvDc == "005" ) {
			confirmMsg = "확정발표";
		}
		
		data.recuInfoNo	= $("#recuInfoNo").val();
		data.etrNo		= $("#etrNo").val();
		data.recuAprvDc	= recuAprvDc;
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm(confirmMsg+"하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/recruit/recruitAprv.json",
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
					recuRecruitView.fnList(pageNo);
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
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitHandle.do";
		
		frm.submit();
	},
	// 삭제
	fnDelete : function() {
		var data = {};
		
		data.recuInfoNo	= $("#recuInfoNo").val();
		data.etrNo		= $("#etrNo").val();
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/recruit/recruitDelete.json",
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
					recuRecruitView.fnInitList();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 채용정보 조회 페이지로 이동
	fnList : function(pageNo) {
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 상세페이지 reload
	fnReload : function() {
		var frm = document.form1;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitView.do";
		
		frm.submit();
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
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

//채용신청정보 조회
var recuRecruitAplcList = { 
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitAplcList.addEvent();

	},
	// 이벤트 추가
	addEvent : function() {
	 
	},
	// 채용신청정보 조회
	fnRecruitAplcList : function() {
		var frm 	= $("#form2");
		var param	= frm.serialize();
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/recruit/recruitAplcList.json",
			data: param,
			//contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				//list목록
				$("#itemsList").setTemplateElement("listTmp", [], {filter_data: false}).processTemplate(data);		//화면이 이동하는경우
				
				//페이징
				//PageUtil("pager", data.paginationInfo, "fnListPage", "M");		//화면을 더보기하는경우
				PageUtil("pager", data.paginationInfo, "recuRecruitAplcList.fnListPage");							//화면이 이동하는경우
				
				var recuAplcTotalCount = 0;
				if (data.paginationInfo.totalRecordCount != undefined) {
					recuAplcTotalCount = data.paginationInfo.totalRecordCount;
					$("#recuAplcTotalCount").html("총 "+comma(recuAplcTotalCount)+"명");
				} else {
					$("#recuAplcTotalCount").html("총 "+recuAplcTotalCount+"명");
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
		recuRecruitAplcList.fnRecruitAplcList();
	},
	// 페이징 검색
	fnListPage  : function(pageNo) {
		$("#currentPage").val(pageNo);
		// 채용신청정보 조회
		recuRecruitAplcList.fnRecruitAplcList();
	},
	// 전체선택
	fnAllChecked : function() {
		$("input:checkbox[id=chkall]").prop("checked", true);
		fnAllCheck($("input:checkbox[id=chkall]"), 'chk');
	},
	// 엑셀다운로드
	fnExcelDown : function() {
		var frm 	= $("#form2");
		var param	= frm.serialize();
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/recruit/recruitAplcExcelList.json",
			data: param,
			//contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var downUrl = "/upload?pathkey=COMMON.EXCEL.DOWN&getfile="+data.fileName+"&realFileName="+data.fileName;
				$("#fileHiddenFrame").attr("src", downUrl);
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 	블랙리스트
	fnSpclManagePop : function() {
		
		var idx			= 0;
		var userNo		= "";
		
		if ( $("input:checkbox[name=chk]:checked").length == 0 ) {
			alert("한 건이상 선택해주세요.");
			return false;
		}
		
		// 블랙리스트 폼 생성
		var $form = fnAddForm('formSpcl');
		$form.empty();
		
		$("input:checkbox[name=chk]:checked").each(function(){
			userNo 		= $(this).attr("userNo");
			
			fnAddInput($form, "userNoList_"+idx, "userNoList", userNo);
			
			idx++;
		});
		
		/*
		 * 페널티구분코드
		 */
		fnAddInput($form, "pntDvCd", "pntDvCd", "01");
		
		// 블랙리스트 팝업
		fnSendSpclManagePop($form);
	},
	// 신청서출력
	fnPrint : function() {
		
	},
	// 삭제
	fnDelete : function() {
		
		var param		;
		var data	= {};
		var nWrk310VOList;
		var nWrk310VOInfo;
		
		if ( $("input:checkbox[name=chk]:checked").length == 0 ) {
			alert("한 건이상 선택해주세요.");
			return false;
		}
		
		var confirmMsg = "삭제 처리하시겠습니까";
		
		if ( !confirm(confirmMsg) ) return false;
		
		nWrk310VOList = new Array();
		$("input:checkbox[name=chk]:checked").each(function(){
			recuAplcNo 		= $(this).attr("recuAplcNo");
			recuInfoNo		= $(this).attr("recuInfoNo");
			apndFileNo		= $(this).attr("apndFileNo");
			
			if ( apndFileNo == 'null' ) apndFileNo = "";
			
			nWrk310VOInfo = new Object();
			nWrk310VOInfo.recuAplcNo 	= recuAplcNo	; // 채용신청번호
			nWrk310VOInfo.recuInfoNo 	= recuInfoNo	; // 채용정보번호
			nWrk310VOInfo.apndFileNo 	= apndFileNo	; // 첨부파일번호
			
			nWrk310VOList.push(nWrk310VOInfo);
		});
		data.nWrk310VOList = nWrk310VOList;
		
		param = JSON.stringify(data);
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/recruit/recruitAplcDelete.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					//recuRecruitAplcList.formInit();
					$("#form2 #currentPage").val("1");
					
					recuRecruitView.fnReload();
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
		
	},
	// 채용신청상태 변경
	fnRecuAplcStcdChange : function(recuAplcStcd) {
		var param		;
		var data	= {};
		var nWrk310VOList;
		var nWrk310VOInfo;
		
		var confirmMsg = "";
		if ( recuAplcStcd == "002" ) confirmMsg = "취소";
		else if ( recuAplcStcd == "003" ) confirmMsg = "합격";
		else if ( recuAplcStcd == "004" ) confirmMsg = "불합격";
		
		confirmMsg += " 처리하시겠습니까?";
		
		if ( $("input:checkbox[name=chk]:checked").length == 0 ) {
			alert("한 건이상 선택해주세요.");
			return false;
		}
		
		if ( !confirm(confirmMsg) ) return false;
		
		nWrk310VOList = new Array();
		$("input:checkbox[name=chk]:checked").each(function(){
			recuAplcNo 		= $(this).attr("recuAplcNo");
			recuInfoNo		= $(this).attr("recuInfoNo");
			
			nWrk310VOInfo = new Object();
			nWrk310VOInfo.recuAplcNo 	= recuAplcNo	; // 채용신청번호
			nWrk310VOInfo.recuInfoNo 	= recuInfoNo	; // 채용정보번호
			nWrk310VOInfo.recuAplcStcd 	= recuAplcStcd	; // 채용신청상태코드
			
			nWrk310VOList.push(nWrk310VOInfo);
		});
		data.nWrk310VOList = nWrk310VOList;
		
		param = JSON.stringify(data);
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/recruit/recruitAplcStcd.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					recuRecruitView.fnReload();
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 폼 초기화
	formInit : function() {
		var frm = document.form2;
		
		for (var i=0; i<frm.elements.length; i++) {
			var inputType = frm.elements[i].getAttribute("type");
			var inputId = frm.elements[i].getAttribute("id");
			
			if ( (inputType == 'hidden' || inputType == 'text')
					&& ( inputId != 'recuInfoNo' || inputId != 'etrNo' || inputId != 'searchDelYn' ) ) {
				frm.elements[i].setAttribute("value", "");
			}
		}
	}

}

//채용정보 등록/수정
var recuRecruitHandle = {
	// 전역변수
	GV : {
		 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		recuRecruitHandle.addEvent();
		
		var cmd = $("#form1 #cmd").val();
		if ( cmd == 'I' ) {
			recuRecruitHandle.fnDefaultForm();
		}
		
		// 기업정보 직접입력 여부 셋팅
		recuRecruitHandle.fnDirectInput();
		
		// 접수마감
		var rcvClseCd = $("input:radio[name=rcvClseCd]:checked").val();
		recuRecruitHandle.fnChkRcvClseDt(rcvClseCd);
		
		// 경력사항
		var recuCarrTgtCd = $("input:radio[name=recuCarrTgtCd]:checked").val();
		recuRecruitHandle.fnSetCareer(recuCarrTgtCd);
	},
	// 이벤트 추가
	addEvent : function() {
		// 접수마감일자
		$("#rcvClseDt").datepicker();
		
		$("#bizJangNo").inputmask("999-99-99999", {"placeholder": ""}); 
		$("#hmpgUrl").inputmask({"alias":"url", "placeholder": ""});
		$("#rcvClseDt").inputmask("9999.99.99", {"placeholder": ""}); 
		
		$("input:radio[name=rcvClseCd]").change(function(){
			// 접수마감 
			recuRecruitHandle.fnChkRcvClseDt(this.value);
		});
		
		$("input:radio[name=recuCarrTgtCd]").change(function(){
			// 경력사항 
			recuRecruitHandle.fnSetCareer(this.value);
		});
	},
	// 목록 페이지로 이동
	fnList : function(pageNo) {
		var frm = document.formInit;
		
		$("#form1 currentPage").value(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
		frm.submit();	
	},
	// 저장
	fnSave : function() { 
		
		var data = {};
		if ( !recuRecruitHandle.validate(data) ) {
			return false;
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("저장하시겠습니까?") ) return false;
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/recu/recruit/recruitSave.json",
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
					recuRecruitHandle.fnInitList();
				}
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
		
	},
	// 삭제
	fnDelete : function() {
		
	},
	// 채용정보 저장시 입력값 체크
	validate : function(data) {
		
		var directYn = $("input:checkbox[name=directYn]").is(":checked");
		
		if ( directYn == false ) {
			if ( $.trim($("#etrNm").val()) == "" ) {
				alert("기업명 필수 입력값입니다.");
				return false;
			}
			
			if ( $.trim($("#bizJangNo").val()) == "" ) {
				alert("사업자번호 필수 입력값입니다.");
				return false;
			}
			
			if ( $.trim($("input:radio[name=etrFomCd]:checked").val()) == "" ) {
				alert("사업자번호 필수 입력값입니다.");
				return false;
			}
			
			if ( $.trim($("input:radio[name=zonCd]:checked").val()) == "" ) {
				alert("사업자번호 필수 입력값입니다.");
				return false;
			}
			
		}
		
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
		//if ( etrNo != undefined && etrNo != "" ) {
		if ( directYn == false ) {
			nWrk110VO.etrNo		= etrNo;
		}
		
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
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/recu/recruit/recruitList.do";
		
		frm.submit();
	},
	// 기업정보 검색
	fnSearchCompany : function() {
		// 기업정보 폼 생성
		var $form = fnAddForm('formComp');
		$form.empty();
		
		//fnAddInput($form, "pntDvCd", "pntDvCd", "01");
		
		fnCompanyPop($form);
	},
	fnSelCompany : function(companyInfo) {
		var etrNo 			= companyInfo.etrNo 		;		
		var etrNm 	        = companyInfo.etrNm 		;
		var bizJangNo	    = companyInfo.bizJangNo	    ;
		var etrFomCd 	    = companyInfo.etrFomCd 	    ;
		var etrFomCdNm      = companyInfo.etrFomCdNm	;
		var zonCd 	        = companyInfo.zonCd 		;
		var zonCdNm 	    = companyInfo.zonCdNm 	    ;
		var hmpgUrl         = companyInfo.hmpgUrl		;
		
		$("#etrNo").val(etrNo);
		$("#etrNm").val(etrNm);
		$("#bizJangNo").val(bizJangNo);
		
		$("input:radio[name=etrFomCd]").each(function() {
			if (this.value == etrFomCd) {
				this.checked = true;
				return;
			}
		});
		
		$("input:radio[name=zonCd]").each(function() {
			if (this.value == zonCd) {
				this.checked = true;
				return;
			}
		});
		
		$("#hmpgUrl").val(hmpgUrl);
	},
	// 기업정보 직접입력 여부 셋팅
	fnDirectInput : function() {
		var directYn = $("input:checkbox[name=directYn]").is(":checked");
		
		if ( directYn == true ) {
			$("#etrNm").prop("disabled", false);
			$("#btnSearchCompany").hide();
			$("#bizJangNo").prop("disabled", false);
			$("#bizJangNo").prop("disabled", false);
			$("input:radio[name=etrFomCd]").prop("disabled", false);
			$("input:radio[name=zonCd]").prop("disabled", false);
			$("#hmpgUrl").prop("disabled", false);
		} else {
			$("#etrNm").prop("disabled", true);
			$("#btnSearchCompany").show();
			$("#bizJangNo").prop("disabled", true);
			$("#bizJangNo").prop("disabled", true);
			$("input:radio[name=etrFomCd]").prop("disabled", true);
			$("input:radio[name=zonCd]").prop("disabled", true);
			$("#hmpgUrl").prop("disabled", true);
		}
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
		$("input:checkbox[name=directYn]").eq(0).prop("checked", true);			// 직접입력
		
		$("input:radio[name=tgtDc]").eq(0).prop("checked", true);				// 대상구분
		$("input:radio[name=submMethCd]").eq(0).prop("checked", true);			// 제출방법
		$("input:radio[name=rcvClseCd]").eq(0).prop("checked", true);			// 접수마감
		$("input:radio[name=recuRcmdCd]").eq(1).prop("checked", true);			// 추천
		$("input:radio[name=recuTpCd]").eq(0).prop("checked", true);			// 채용유형
		$("input:radio[name=recuCarrTgtCd]").eq(0).prop("checked", true);		// 경력사항 
	}
	
	
}


var spclManageCallback = function() {
	$("#form2 #currentPage").val("1");
	$("#form2 #searchAplcKey").val("");
	$("#form2 #searchAplcWord").val("");
	
	// 채용신청정보 조회
	recuRecruitAplcList.fnRecruitAplcList();
}

var selCompanyCallback = function(companyInfo) {
	recuRecruitHandle.fnSelCompany(companyInfo);
}
