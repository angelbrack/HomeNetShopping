//var oEditors = [];
var option = {};
option = {
		"validList" : [
		      {"objId" : "dispNm", 			"label" : "전시카테고리명", 		"rule" : { "required":true,   "maxlength":200												}		}
			, {"objId" : "shopDescCont", 	"label" : "매장 설명 내용", 		"rule" : { "required":false,  "maxlength":4000												}		}
			, {"objId" : "dispPrioRnk", 	"label" : "전시우선순위", 		"rule" : { "required":true,   "number":true,		"min":1,  "max":999,	"maxlength":3	}		}
		]
	};

// 전시매장 정보 리스트
var displayList = {
	// 전역변수
	GV : {
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		displayList.addEvent();
		
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
		frm.action = CTX_PATH + "/mgnt/display/initMgntDisplay.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/display/initMgntDisplay.do";
		
		frm.submit();
	},
	// 신규/수정 페이지로 이동
	fnEdit : function(cmd, dispNo) {
		
		var VIEW_DISP = window.open('', 'VIEW_DISP' , 'width=1000, height=800, resizable=yes, scrollbars=yes, left=200, top=100');
		VIEW_DISP.focus();   
		
		var frm = document.form1
		
		$("#form1 #cmd").val(cmd);
		$("#form1 #dispNo").val(dispNo);
		
		frm.method = "post";
		frm.target = "VIEW_DISP";
		frm.action = CTX_PATH + "/mgnt/display/displayHandlePopup.do";
		
		frm.submit();
		
	}
	
}

// 전시매장 정보 등록/수정
var displayHandle = {
	// 전역변수
	GV : {	 
	},
	// 초기화
	init : function () {
		
		$("#dispPrioRnk").inputmask("999", {"placeholder": ""});							// 전시우선순위
		
		var divCnt = $("#searchArticle").find("div").length;
		if($("#searchArticle").find("div").length > 2) {
			$("#searchArticle").find("[name=removeTargetBtn]").show();
		}
		$("#searchArticle").find("div").eq(divCnt-2).find("[name=addTargetBtn]").hide();
		
		// 이벤트 추가
		displayHandle.addEvent();
		
	},
	// 이벤트 추가
	addEvent : function() {
		if($("input[name*='tlwtLfYn']:checked").val() == 'N'){
			$("#searchArticle").hide();
		};
		
		$("input[name*='tlwtLfYn']").click(function(){
			
			var param = "dispNo="+$("#form1").find("#dispNo").val();
			
			if($(this).is(':checked')&& $(this).val() == 'Y') {
				var url = "/display/display.check.leaf.exist.ajax.lecs";
				
				ajaxCall("post", url, param, "text", callback = function(data){
					var leafYn = data.split(",")[0];
					var message = data.split(",")[1];
					
					if(leafYn == "Y") {
						alert(message);
						$("input[name*='tlwtLfYn']").each(function() {
							if($(this).val() == 'N')
								$(this).prop("checked",true);
					
						});
						$("#lstSortCd").prop("disabled",true);
						$("#searchArticle").hide();
					}else{
					
						$("#lstSortCd").prop("disabled",false);
						$("#searchArticle").show();
					}
				});
			}else {			
				var url = "/display/display.check.goods.ajax.lecs";
				   
				ajaxCall("post", url, param, "text", callback = function(data){
					var checkYn = data.split(",")[0];
					var message = data.split(",")[1];
					// 연결 상품이 존재하는 경우 리프 해제 전에 알럿 띄우기
					if(checkYn == "Y") {
						alert(message);
						$("input[name*='tlwtLfYn']").each(function() {
							if($(this).val() == 'Y')
								$(this).prop("checked",true);
							
						});
					}else{
					
						$("#lstSortCd").prop("disabled",true);
						$("#searchArticle").hide();
					}
				});
			}
			return;
		});
		
		//최초 접근시 매장 타이틀 노출유형이 텍스트인경우 이미지 추가 버튼 비활성화 처리
		if($("input[name*='dispTitExpMethCd']:checked").val() == '02'){
			$("#fileupload0").prop("disabled",true);
		};
		
		$("input[name*='dispTitExpMethCd']").click(function(){
			if($(this).is(':checked')&& $(this).val() == '01') {
				$("#fileupload0").prop("disabled",false);
			}else
				$("#fileupload0").prop("disabled",true);
		});
		
		//최초 접근시 매장 메뉴 노출유형이 텍스트인경우 이미지 추가 버튼 비활성화 처리
		if($("input[name*='gnbExpMethCd']:checked").val() == '02'){
			$("#fileupload1").prop("disabled",true);
		};
		
		$("input[name*='gnbExpMethCd']").click(function(){
			if($(this).is(':checked')&& $(this).val() == '01') {
				$("#fileupload1").prop("disabled",false);
			} else
				$("#fileupload1").prop("disabled",true);
		});
	},
	// 목록 페이지로 이동
	fnList : function(pageNo) {
		var frm = document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/display/initMgntDisplay.do";
		
		frm.submit();
	},
	// 저장
	fnSave : function() { 
		
		var data = {};
		if ( !displayHandle.validate(data) ) {
			return false;
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("저장하시겠습니까?") ) return false;
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/display/displaySave.json",
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
					displayHandle.fnInitList();
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
		
		if ( nvl($("#form1 #dispNo").val(), "") != "" ) {
			data.dispNo	= $("#form1 #dispNo").val();
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/display/displayDelete.json",
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
		
		var addFileList;
		
		data.cmd					= $("#form1 #cmd").val();				// 처리구분(I:등록, U:수정)
		
		if ( nvl($("#form1 #dispNo").val(), "") != "" ) {
			data.dispNo	= $("#form1 #dispNo").val();			   			// 전시매장번호 
		}
		data.dispNm					= $("#form1 #dispNm").val();			// 전시카테고리명 
		data.dispTitExpMethCd		= getCheckedValue("dispTitExpMethCd");  // 매장 타이틀 노출유형
		data.gnbExpMethCd			= getCheckedValue("gnbExpMethCd");      // 매장 메뉴 노출유형
		
		// 매장 타이틀 이미지
		if ( $('input[name="addFileList0"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList0"]').each(function() {
				addFileList.push(this.value);
			});
			data.addTitleImgList	= addFileList;
		}
		
		// 매장 메뉴 이미지
		if ( $('input[name="addFileList1"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList1"]').each(function() {
				addFileList.push(this.value);
			});
			data.addGnbImgList	= addFileList;
		}
		
		data.shopDescCont			= $("#form1 #shopDescCont").val();			// 매장 설명 내용
		data.tlwtLfYn				= getCheckedValue("tlwtLfYn");  			// Leaf 카테고리 여부
		data.dispPrioRnk			= $("#form1 #dispPrioRnk").val();			// 전시우선순위
		data.dispYn					= getCheckedValue("dispYn");  				// 전시여부 
		data.useYn					= getCheckedValue("useYn");  				// 사용여부 
		data.menuUseYn				= getCheckedValue("menuUseYn");  			// 메뉴사용 여부
		data.prtTpCd				= getSelectedValue("prtTpCd");  			// 출력유형
		data.tmplNo					= $("#form1 #tmplNo").val();				// 템플릿번호
		data.lnkUrlAddr				= $("#form1 #lnkUrlAddr").val();			// 대상 URL
		
		var lnkSpdpHhNo40			= $("#form1 #lnkSpdpHhNo40").val();			// 카테고리매장번호
		var lnkSpdpHhNo50			= $("#form1 #lnkSpdpHhNo50").val();			// 기획전매장번호
		
		data.lstSortCd				= getSelectedValue("lstSortCd");  			// 리스트소팅값
		
		// 매장 부가 이미지
		if ( $('input[name="addFileList2"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList2"]').each(function() {
				addFileList.push(this.value);
			});
			data.addHeaderImgList	= addFileList;
		}
		
		/*if ( data.dispTitExpMethCd == "01" ) {
			if ( data.addTitleImgList == undefined || data.addTitleImgList.length == 0 ) {
				alert("매장타이틀 노출유형이 이미지입니다.\n이미지를 업로드해주세요.");
				return false;
			}
		}*/
		
		if ( data.prtTpCd == "01" ) {
			if ( data.tmplNo == "" ) {
				alert("전시 템플릿을 선택해주세요.");
				return false;
			}
		} else if ( data.prtTpCd == "02" ) {
			if ( data.lnkUrlAddr == "" ) {
				alert("대상  URL을 입력해주세요.");
				return false;
			}
		} else if ( data.prtTpCd == "04" ) {
			if ( lnkSpdpHhNo40 == "" ) {
				alert("전시 카테고리번호를 입력해주세요.");
				return false;
			}
			
			data.lnkSpdpHhNo = lnkSpdpHhNo40;
		} else if ( data.prtTpCd == "05" ) {
			if ( lnkSpdpHhNo50 == "" ) {
				alert("기획전번호을 입력해주세요.");
				return false;
			}
			
			data.lnkSpdpHhNo = lnkSpdpHhNo50;
		} else if ( data.prtTpCd == "06" ) {
			data.lnkSpdpHhNo = "";
		}
		
		if ( data.gnbExpMethCd == "01" ) {
			if ( data.addGnbImgList == undefined || data.addGnbImgList.length == 0 ) {
				alert("매장타이틀 노출유형이 이미지입니다.\n이미지를 업로드해주세요.");
				return false;
			}
		}
		
		if ( data.tlwtLfYn == "Y" ) {
			
			var checkLeaf = false;
			var index = 0;
			$("input:[name='mdGoodsGroupNo']").each(function(){
				if(index >= 0 && $(this).val() !='' && !checkLeaf) { 
					checkLeaf = true;
					index++;
	 			}
			});
			
		}
		
		if ( data.tlwtLfYn == "Y" ) {
			
			var index = 1;
			$("input:[name='articleCode1']").each(function(){
				if($(this).val() =='') {
					$("#articleCode"+index).remove();
				}
				index++;
			});
			
		}
		
		return true;
	},
	// 폼 초기화 리스트 페이지 이동
	fnInitList  : function() {
		var frm	= document.formInit;
		
		$("#formInit #currentPage").val(1);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/display/initMgntDisplay.do";
		
		frm.submit();
	},
	fnClose  : function() {
		self.close();
	}
	
}