//var oEditors = [];
var option = {};
option = {
		"validList" : [
		      {"objId" : "brndNm", 			"label" : "품목군명", 			"rule" : { "required":true,   "maxlength":200}		}
			, {"objId" : "brndKorNm", 		"label" : "품목군한글명", 		"rule" : { "required":false,  "maxlength":200}		}
			, {"objId" : "brndEngNm", 		"label" : "품목군영문명", 		"rule" : { "required":false,  "maxlength":200}		}
			, {"objId" : "brndDescCont", 	"label" : "품목군상세설명", 		"rule" : { "required":false,  "maxlength":4000}		}
			
			
		]
	};

// 품목군 Tree 정보
var articleTreeList = {
	// 전역변수
	GV : {
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		articleTreeList.addEvent();
		
		// 품목군 초기 Tree 조회
		articleTreeList.initTreeSearch();
		
	},
	// 이벤트 추가
	addEvent : function() {
		
	},
	// 품목군 초기 Tree 조회
	initTreeSearch : function() {
		// 품목군 Tree 조회
		var data	= {};
		
		//fnTreeSearch();
	},
	// 검색
	fnTreeSearch : function(data) {
		var param	= JSON.stringify(data); 
		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/article/selectGoodsArtcCdTreeList.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var list		= data.list;
				console.log("list=["+JSON.stringify(list)+"]");
				
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 
	fnOpenHandler : function(id, mode) {
		console.log("id=["+id+"]");
		console.log("mode=["+mode+"]");
		/*if(mode <= 0) {
			// tree를 open한 경우 하위 매장리스트조회
			$("#displayTreeForm").find("#displayNo").val(id);
			if(id == $("#displayTreeForm").find("#displayMallNo").val()) {
				$("#displayTreeForm").find("#displayNo").val("");
			}
			var val = $("#displayTreeForm").serialize();
			
			ajaxCall("post", "/display/lower.display.shop.tree.list.ajax.lecs", val, "json", callback = function(data){
				if(data.message != 'O') {
					alert(data.message);
				}else {
					
					tree.deleteChildItems(id);
					$(data.data).each(function(idx){
						
						// 하위에 매장이 존재하는 경우
						if(this.childDisplayShopCount > 0) {
							tree.insertNewChild(id,this.displayNo,this.displayName, null,0,0,0,"CHILD");
							tree.insertNewChild(this.displayNo,"","", null,0,0,0);
						}else{
							tree.insertNewChild(id,this.displayNo,this.displayName, null,0,0,0);
						}
						tree.closeItem(this.displayNo);
					});
					$("#displayTreeForm").find("#displayNo").val(id);
					return true;
				}
			});
		}else{
			return true;
		}*/
	},
	//
	fnCleanTree : function() {
		
		var val = $("#displayTreeForm").serialize();
		var html = "<ul><li id=\"treeRoot\">Root<ul><li></li></ul></li></ul>";	
		tree.destructor();
		$("#treeboxbox_tree").html(html);
		tree = dhtmlXTreeFromHTML("treeboxbox_tree");
		tree.setOnClickHandler(articleTreeList.fnClickHandler);
		tree.setOnOpenHandler(articleTreeList.fnOpenHandler);
	},
	// 페이징에서 호출
	fnListPage : function(pageNo) {
		var frm	= document.form1;
		
		$("#form1 #currentPage").val(pageNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/article/initMgntArticle.do";
		
		frm.submit();
	},
	// 페이지당 레코드 갯수로 호출
	fnListPerLine : function(recordCountPerPage) {
		var frm	= document.form1
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/article/initMgntArticle.do";
		
		frm.submit();
	},
	// 신규/수정 페이지로 이동
	fnEdit : function(cmd, brndNo) {
		
		var frm = document.form1
		
		$("#form1 #cmd").val(cmd);
		$("#form1 #brndNo").val(brndNo);
		
		frm.method = "post";
		frm.target = "_self";
		frm.action = CTX_PATH + "/mgnt/article/articleHandle.do";
		
		frm.submit();
		
	}
	
}

// 품목군 정보 등록/수정
var articleHandle = {
	// 전역변수
	GV : {	 
	},
	// 초기화
	init : function () {
		// 이벤트 추가
		articleHandle.addEvent();
		
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
		frm.action = CTX_PATH + "/mgnt/article/initMgntArticle.do";
		
		frm.submit();
	},
	// 저장
	fnSave : function() { 
		
		var data = {};
		if ( !articleHandle.validate(data) ) {
			return false;
		}
		
		var param	= JSON.stringify(data); 
		console.log(param);
   		
   		if ( !confirm("저장하시겠습니까?") ) return false;
   		
   		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/article/articleSave.json",
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
					articleHandle.fnInitList();
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
			url: CTX_PATH + "/mgnt/article/articleDelete.json",
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
			data.brndNo	= $("#form1 #brndNo").val();			   	// 품목군번호 
		}
		data.brndNm			= $("#form1 #brndNm").val();           	// 품목군명 	
		data.brndKorNm		= $("#form1 #brndKorNm").val();        	// 품목군한글명 
		data.brndEngNm		= $("#form1 #brndEngNm").val();        	// 품목군영문명 
		data.brndDescCont	= $("#form1 #brndDescCont").val();     	// 품목군상세설명
		
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
		frm.action = CTX_PATH + "/mgnt/article/initMgntArticle.do";
		
		frm.submit();
	}
	
}