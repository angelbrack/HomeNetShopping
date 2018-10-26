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
var displayTreeList = {
	// 전역변수
	GV : {
	},
	// 초기화
	init : function () {
		
		// 이벤트 추가
		displayTreeList.addEvent();
		
	},
	// 이벤트 추가
	addEvent : function() {
		
	},
	// 전시매장 초기 Tree 조회
	initTreeSearch : function() {
		// 전시매장 Tree 조회
		var data	= {};
		
		displayTreeList.fnCleanTree();
		
		displayTreeList.fnTreeSearch(data, "treeRoot");
	},
	// Tree 검색
	fnTreeSearch : function(data, id) {
		var param	= JSON.stringify(data); 
		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/display/selectDisplayTreeList.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var list		= data.list;
				
				tree.deleteChildItems(id);
				
				$(list).each(function(idx){
					/*console.log("this.dispNo["+idx+"]=["+this.dispNo+"]");
					console.log("this.dispNm["+idx+"]=["+this.dispNm+"]");
					console.log("this.childCount["+idx+"]=["+this.childCount+"]");*/
					// 하위에 전시매장이 존재하는 경우
					if(this.childCount > 0) {
						tree.insertNewChild(id, this.dispNo, this.dispNm, null, 0, 0, 0, "CHILD");
						//tree.insertNewChild(id, "", "", null, 0, 0, 0);
					}else{
						tree.insertNewChild(id, this.dispNo, this.dispNm, null, 0, 0, 0);
					}
					tree.closeItem(this.dispNo);
				});
				//$("#displayTreeForm").find("#displayNo").val(id);
				return true;
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// Tree 에서 하위를 open 했을때
	fnOpenHandler : function(id, mode) {
		var data	= {};
		
		data.searchDpmlNo 		= $("#searchDpmlNo option:selected").val();		// 전시몰 번호
		data.searchShopTpCd 	= $("#searchShopTpCd option:selected").val();	// 매장유형
		data.searchDispYn 		= $("#searchDispYn option:selected").val();		// 전시여부
		data.searchMenuUseYn 	= $("#searchMenuUseYn option:selected").val();	// 메뉴사용여부
		data.searchUseYn 		= $("#searchUseYn option:selected").val();		// 사용여부
		
		if(mode <= 0) {
			// tree를 open한 경우 하위 폼목리스트 조회
			
			if ( id != "treeRoot" ) {
				$("#form1").find("#dispNo").val(id);
				data.dispNo = id;
			}
			
			displayTreeList.fnTreeSearch(data, id);
			
		}else{
			return true;
		}
	},
	// Tree 에서 품목명을 선택했을때
	fnClickHandler : function(id) {
		var data = {};
		
		var dispNo		    = "";
		var dispNm		    = "";
		var artcFullNm	    = "";
		var ecdispNo	    = "";
		var ecdispNm	    = "";
		var uprdispNo	    = "";
		var uprdispNm	    = "";
		var artcDpthNo	    = "1";
		var onlBrchInvRt	= "";
		var maxLmtQty		= "";
		var goodsMrgnRt		= "";
		
		if ( id != "treeRoot" ) {
			$("#form1 #treeId").val(id);
			data.dispNo = id;
			
			var param	= JSON.stringify(data); 
			
			$.ajax({
				async : false,
				type: 'POST',
				url: CTX_PATH + "/mgnt/article/selectGoodsdispNoInfo.json",
				data: param,
				contentType: 'application/json',
				dataType:"json",
				success : function (data) {
					var info		= data.info;
					
					if ( info != undefined ) {
						dispNo 		    = nvl(info.dispNo, "");
						dispNm 		    = fnRecoveHtml(nvl(info.dispNm, ""));
						artcFullNm 	    = fnRecoveHtml(nvl(info.artcFullNm, ""));
						ecdispNo 	    = nvl(info.ecdispNo, "");
						ecdispNm 	    = fnRecoveHtml(nvl(info.ecdispNm, ""));
						uprdispNo 	    = nvl(info.uprdispNo, "");
						uprdispNm 	    = fnRecoveHtml(nvl(info.uprdispNm, ""));
						artcDpthNo	    = nvl(info.artcDpthNo, "");
						onlBrchInvRt	= nvl(info.onlBrchInvRt, "");
						maxLmtQty		= nvl(info.maxLmtQty, "");
						goodsMrgnRt		= nvl(info.goodsMrgnRt, "");
					}
					
					$("#form1 #dispNo").val(dispNo);
					$("#form1 #dispNm").val(dispNm);
					$("#form1 #artcFullNm").val(artcFullNm);
					$("#form1 #ecdispNo").val(ecdispNo);
					$("#form1 #ecdispNm").val(ecdispNm);
					$("#form1 #uprdispNo").val(uprdispNo);
					$("#form1 #uprdispNm").val(uprdispNm);
					$("#form1 #artcDpthNo").val(artcDpthNo);
					$("#form1 #onlBrchInvRt").val(onlBrchInvRt);
					$("#form1 #maxLmtQty").val(maxLmtQty);
					$("#form1 #goodsMrgnRt").val(goodsMrgnRt);
					
					$("#form1 #dispNo").prop("disabled", true);
					$("#form1 #dispNm").prop("disabled", true);
					
					$("#form1 #cmd").val("U");	// 수정
				}, 
				error: function(data, textStatus, errorThrown) {
					//$("#form1 #cmd").val("");
					fnAjaxError(data);
				}
			});
		} else {
			$("#form1 #treeId").val("");
			
			$("#form1 #dispNo").val(dispNo);
			$("#form1 #dispNm").val(dispNm);
			$("#form1 #artcFullNm").val(artcFullNm);
			$("#form1 #ecdispNo").val(ecdispNo);
			$("#form1 #ecdispNm").val(ecdispNm);
			$("#form1 #uprdispNo").val(uprdispNo);
			$("#form1 #uprdispNm").val(uprdispNm);
			$("#form1 #artcDpthNo").val(artcDpthNo);
			$("#form1 #onlBrchInvRt").val(onlBrchInvRt);
			$("#form1 #maxLmtQty").val(maxLmtQty);
			$("#form1 #goodsMrgnRt").val(goodsMrgnRt);
			
			$("#form1 #dispNo").prop("disabled", false);
			$("#form1 #dispNm").prop("disabled", false);
			
			$("#form1 #cmd").val("I");	// 등록
		}
	},
	// Tree 초기화
	fnCleanTree : function() {
		
		var val = $("#displayTreeForm").serialize();
		var html = "<ul><li id=\"treeRoot\">품목<ul><li></li></ul></li></ul>";	
		tree.destructor();
		$("#treeboxbox_tree").html(html);
		tree = dhtmlXTreeFromHTML("treeboxbox_tree");
		tree.setOnClickHandler(displayTreeList.fnClickHandler);
		tree.setOnOpenHandler(displayTreeList.fnOpenHandler);
	},
	// 신규 버튼 click
	fnAdd : function() {
		var treeId			= "";
		var dispNo		    = "";
		var dispNm		    = "";
		var artcFullNm	    = "";
		var ecdispNo	    = "";
		var ecdispNm	    = "";
		var uprdispNo	    = "";
		var uprdispNm	    = "";
		var artcDpthNo	    = 0;
		var onlBrchInvRt	= "";
		var maxLmtQty		= "";
		var goodsMrgnRt		= "";
		
		treeId		= nvl($("#form1 #treeId").val(), "");
		dispNo		= nvl($("#form1 #dispNo").val(), "");
		dispNm		= nvl($("#form1 #dispNm").val(), "");
		artcDpthNo	= nvl($("#form1 #artcDpthNo").val(), 1);
		
		if ( treeId == "" ) {
			dispNo	= "";
			dispNm	= "";
		}
		
		if ( dispNo != "" && treeId != "" ) {
			artcDpthNo = Number(artcDpthNo) + 1;
		}
		
		$("#form1 #dispNo").val("");
		$("#form1 #dispNm").val("");
		$("#form1 #artcFullNm").val(artcFullNm);
		$("#form1 #ecdispNo").val(ecdispNo);
		$("#form1 #ecdispNm").val(ecdispNm);
		$("#form1 #uprdispNo").val(dispNo);
		$("#form1 #uprdispNm").val(dispNm);
		$("#form1 #artcDpthNo").val(artcDpthNo);
		$("#form1 #onlBrchInvRt").val(onlBrchInvRt);
		$("#form1 #maxLmtQty").val(maxLmtQty);
		$("#form1 #goodsMrgnRt").val(goodsMrgnRt);
		
		$("#form1 #dispNo").prop("disabled", false);
		$("#form1 #dispNm").prop("disabled", false);
		
		$("#form1 #cmd").val("I");
	},
	// 저장 버튼 click
	fnSave : function() {
		var data = {};
		if ( !displayTreeList.validate(data) ) {
			return false;
		}
		
		var param	= JSON.stringify(data); 
			
		if ( !confirm("저장하시겠습니까?") ) return false;
			
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/article/articleSave.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 품목정보 저장시 입력값 체크
	validate : function(data) {
		$("#ecdispNo").val("01");	// 테스트를 위해서
		
		$("#form1").validInit({onsubmit : false, onfocusout : false});
		
		$("#form1").validAddRules(option);		
		if($("#form1").validate().form() == false) {
	    	return false;
		}
		
		data.cmd				= $("#cmd").val();							// 등록/수정 구분
		data.dispNo				= $.trim($("#dispNo").val());				// 품목코드
		data.dispNm				= $.trim($("#dispNm").val());				// 품목명
		data.artcFullNm			= $.trim($("#artcFullNm").val());			// 품목FULL명
		data.uprdispNo			= $.trim($("#uprdispNo").val());			// 상위 품목코드  
		data.uprdispNm			= $.trim($("#uprdispNm").val());			// 상위 품목명  
		
		data.ecdispNo			= $.trim($("#ecdispNo").val());				// 전자상거래품목코드
		data.ecdispNm			= $.trim($("#ecdispNm").val());				// 전자상거래품목명
		data.artcDpthNo			= $.trim($("#artcDpthNo").val());			// 품목깊이번호
		var onlBrchInvRt		= $.trim($("#onlBrchInvRt").val());			// 재고율
		var maxLmtQty			= $.trim($("#maxLmtQty").val());			// 최대구매수량
		var goodsMrgnRt			= $.trim($("#goodsMrgnRt").val());			// 상품마진율
		
		if ( onlBrchInvRt != "" ) {
			data.onlBrchInvRt	= onlBrchInvRt;
		}
		if ( maxLmtQty != "" ) {
			data.maxLmtQty	= maxLmtQty;
		}
		if ( goodsMrgnRt != "" ) {
			data.goodsMrgnRt	= goodsMrgnRt;
		}
		
		/*// 첨부파일 정보
		if ( $('input[name="addFileList"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList"]').each(function() {
				addFileList.push(this.value);
			});
			data.addFileList	= addFileList;
		} else {
			alert("배너 이미지 첨부파일를 선택해주세요.");
			return false;
		}*/
		
		return true;
	},
	// 삭제
	fnDelete : function() {
		var data = {};
		
		if ( nvl($("#form1 #brndNo").val(), "") != "" ) {
			data.brndNo	= $("#form1 #brndNo").val();
		}
		
		var param	= JSON.stringify(data); 
   		
   		if ( !confirm("삭제하시겠습니까?") ) return false; 
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/article/articleDelete.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
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
		var tmplNo					= $("#form1 #tmplNo").val();				// 템플릿번호					
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
			if ( tmplNo == "" ) {
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
		
		if ( tmplNo != "" ) {
			data.tmplNo = tmplNo;
		}
		
		data.shopTpCd	= "01";	// 01 카테고리매장, 02 비정형매장
		
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