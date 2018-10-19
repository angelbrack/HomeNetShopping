//var oEditors = [];
var option = {};
option = {
		"validList" : [
		      {"objId" : "artcCd", 			"label" : "품목코드", 			"rule" : { "required":true,   "maxlength":10}		}
			, {"objId" : "artcNm", 			"label" : "품목명", 			"rule" : { "required":true,  "maxlength":100}		}
			, {"objId" : "artcFullNm", 		"label" : "품목FULL명", 		"rule" : { "required":true,  "maxlength":300}		}
			, {"objId" : "ecArtcCd", 		"label" : "전자상거래품목", 		"rule" : { "required":true,  "maxlength":10}		}
			
			
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
		//articleTreeList.initTreeSearch();
		
	},
	// 이벤트 추가
	addEvent : function() {
		
	},
	// 품목군 초기 Tree 조회
	initTreeSearch : function() {
		// 품목군 Tree 조회
		var data	= {};
		
		articleTreeList.fnCleanTree();
		
		articleTreeList.fnTreeSearch(data, "treeRoot");
	},
	// Tree 검색
	fnTreeSearch : function(data, id) {
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
				
				tree.deleteChildItems(id);
				
				$(list).each(function(idx){
					/*console.log("this.artcCd["+idx+"]=["+this.artcCd+"]");
					console.log("this.artcNm["+idx+"]=["+this.artcNm+"]");
					console.log("this.childCount["+idx+"]=["+this.childCount+"]");*/
					// 하위에 품목군이 존재하는 경우
					if(this.childCount > 0) {
						tree.insertNewChild(id, this.artcCd, this.artcNm, null, 0, 0, 0, "CHILD");
						//tree.insertNewChild(id, "", "", null, 0, 0, 0);
					}else{
						tree.insertNewChild(id, this.artcCd, this.artcNm, null, 0, 0, 0);
					}
					tree.closeItem(this.artcCd);
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
		
		if(mode <= 0) {
			// tree를 open한 경우 하위 폼목리스트 조회
			
			if ( id != "treeRoot" ) {
				$("#form1").find("#artcCd").val(id);
				data.artcCd = id;
			}
			
			articleTreeList.fnTreeSearch(data, id);
			
		}else{
			return true;
		}
	},
	// Tree 에서 품목명을 선택했을때
	fnClickHandler : function(id) {
		var data = {};
		
		var artcCd		    = "";
		var artcNm		    = "";
		var artcFullNm	    = "";
		var ecArtcCd	    = "";
		var ecArtcNm	    = "";
		var uprArtcCd	    = "";
		var uprArtcNm	    = "";
		var artcDpthNo	    = "1";
		var onlBrchInvRt	= "";
		var maxLmtQty		= "";
		var goodsMrgnRt		= "";
		
		if ( id != "treeRoot" ) {
			$("#form1 #treeId").val(id);
			data.artcCd = id;
			
			var param	= JSON.stringify(data); 
			
			$.ajax({
				async : false,
				type: 'POST',
				url: CTX_PATH + "/mgnt/article/selectGoodsArtcCdInfo.json",
				data: param,
				contentType: 'application/json',
				dataType:"json",
				success : function (data) {
					var info		= data.info;
					
					if ( info != undefined ) {
						artcCd 		    = nvl(info.artcCd, "");
						artcNm 		    = fnRecoveHtml(nvl(info.artcNm, ""));
						artcFullNm 	    = fnRecoveHtml(nvl(info.artcFullNm, ""));
						ecArtcCd 	    = nvl(info.ecArtcCd, "");
						ecArtcNm 	    = fnRecoveHtml(nvl(info.ecArtcNm, ""));
						uprArtcCd 	    = nvl(info.uprArtcCd, "");
						uprArtcNm 	    = fnRecoveHtml(nvl(info.uprArtcNm, ""));
						artcDpthNo	    = nvl(info.artcDpthNo, "");
						onlBrchInvRt	= nvl(info.onlBrchInvRt, "");
						maxLmtQty		= nvl(info.maxLmtQty, "");
						goodsMrgnRt		= nvl(info.goodsMrgnRt, "");
					}
					
					$("#form1 #artcCd").val(artcCd);
					$("#form1 #artcNm").val(artcNm);
					$("#form1 #artcFullNm").val(artcFullNm);
					$("#form1 #ecArtcCd").val(ecArtcCd);
					$("#form1 #ecArtcNm").val(ecArtcNm);
					$("#form1 #uprArtcCd").val(uprArtcCd);
					$("#form1 #uprArtcNm").val(uprArtcNm);
					$("#form1 #artcDpthNo").val(artcDpthNo);
					$("#form1 #onlBrchInvRt").val(onlBrchInvRt);
					$("#form1 #maxLmtQty").val(maxLmtQty);
					$("#form1 #goodsMrgnRt").val(goodsMrgnRt);
					
					$("#form1 #artcCd").prop("disabled", true);
					$("#form1 #artcNm").prop("disabled", true);
					
					$("#form1 #cmd").val("U");	// 수정
				}, 
				error: function(data, textStatus, errorThrown) {
					//$("#form1 #cmd").val("");
					fnAjaxError(data);
				}
			});
		} else {
			$("#form1 #treeId").val("");
			
			$("#form1 #artcCd").val(artcCd);
			$("#form1 #artcNm").val(artcNm);
			$("#form1 #artcFullNm").val(artcFullNm);
			$("#form1 #ecArtcCd").val(ecArtcCd);
			$("#form1 #ecArtcNm").val(ecArtcNm);
			$("#form1 #uprArtcCd").val(uprArtcCd);
			$("#form1 #uprArtcNm").val(uprArtcNm);
			$("#form1 #artcDpthNo").val(artcDpthNo);
			$("#form1 #onlBrchInvRt").val(onlBrchInvRt);
			$("#form1 #maxLmtQty").val(maxLmtQty);
			$("#form1 #goodsMrgnRt").val(goodsMrgnRt);
			
			$("#form1 #artcCd").prop("disabled", false);
			$("#form1 #artcNm").prop("disabled", false);
			
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
		tree.setOnClickHandler(articleTreeList.fnClickHandler);
		tree.setOnOpenHandler(articleTreeList.fnOpenHandler);
	},
	// 신규 버튼 click
	fnAdd : function() {
		var treeId			= "";
		var artcCd		    = "";
		var artcNm		    = "";
		var artcFullNm	    = "";
		var ecArtcCd	    = "";
		var ecArtcNm	    = "";
		var uprArtcCd	    = "";
		var uprArtcNm	    = "";
		var artcDpthNo	    = 0;
		var onlBrchInvRt	= "";
		var maxLmtQty		= "";
		var goodsMrgnRt		= "";
		
		treeId		= nvl($("#form1 #treeId").val(), "");
		artcCd		= nvl($("#form1 #artcCd").val(), "");
		artcNm		= nvl($("#form1 #artcNm").val(), "");
		artcDpthNo	= nvl($("#form1 #artcDpthNo").val(), 1);
		
		if ( treeId == "" ) {
			artcCd	= "";
			artcNm	= "";
		}
		
		if ( artcCd != "" && treeId != "" ) {
			artcDpthNo = Number(artcDpthNo) + 1;
		}
		
		$("#form1 #artcCd").val("");
		$("#form1 #artcNm").val("");
		$("#form1 #artcFullNm").val(artcFullNm);
		$("#form1 #ecArtcCd").val(ecArtcCd);
		$("#form1 #ecArtcNm").val(ecArtcNm);
		$("#form1 #uprArtcCd").val(artcCd);
		$("#form1 #uprArtcNm").val(artcNm);
		$("#form1 #artcDpthNo").val(artcDpthNo);
		$("#form1 #onlBrchInvRt").val(onlBrchInvRt);
		$("#form1 #maxLmtQty").val(maxLmtQty);
		$("#form1 #goodsMrgnRt").val(goodsMrgnRt);
		
		$("#form1 #artcCd").prop("disabled", false);
		$("#form1 #artcNm").prop("disabled", false);
		
		$("#form1 #cmd").val("I");
	},
	// 저장 버튼 click
	fnSave : function() {
		var data = {};
		if ( !articleTreeList.validate(data) ) {
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
		$("#ecArtcCd").val("01");	// 테스트를 위해서
		
		$("#form1").validInit({onsubmit : false, onfocusout : false});
		
		$("#form1").validAddRules(option);		
		if($("#form1").validate().form() == false) {
	    	return false;
		}
		
		data.cmd				= $("#cmd").val();							// 등록/수정 구분
		data.artcCd				= $.trim($("#artcCd").val());				// 품목코드
		data.artcNm				= $.trim($("#artcNm").val());				// 품목명
		data.artcFullNm			= $.trim($("#artcFullNm").val());			// 품목FULL명
		data.uprArtcCd			= $.trim($("#uprArtcCd").val());			// 상위 품목코드  
		data.uprArtcNm			= $.trim($("#uprArtcNm").val());			// 상위 품목명  
		
		data.ecArtcCd			= $.trim($("#ecArtcCd").val());				// 전자상거래품목코드
		data.ecArtcNm			= $.trim($("#ecArtcNm").val());				// 전자상거래품목명
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