//var oEditors = [];
var option = {};
option = {
		"validList" : [
		      {"objId" : "dispNm", 			"label" : "전시카테고리명", 		"rule" : { "required":true,   "maxlength":200												}		}
			, {"objId" : "shopDescCont", 	"label" : "매장 설명 내용", 		"rule" : { "required":false,  "maxlength":4000												}		}
			, {"objId" : "dispPrioRnk", 	"label" : "전시우선순위", 		"rule" : { "required":true,   "number":true,		"min":1,  "max":999,	"maxlength":3	}		}
			, {"objId" : "tmplNo", 			"label" : "전시 템플릿", 		"rule" : { "required":false																	}		}
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
		
		// 전시몰 change
		$("#searchDpmlNo").change();
		
		var searchDpmlNo	= $("#form1 #searchDpmlNo option:selected").val();		// 전시몰번호
		var searchShopTpCd	= $("#form1 #searchShopTpCd option:selected").val();	// 매장유형
		$("#dpmlNo").val(searchDpmlNo);												// 전시몰번호
		$("#shopTpCd").val(searchShopTpCd);											// 매장유형
		
		// 매장 타이틀 노출유형이 이미지가 아니면 매장 타이틀 이미지 파일업로드를 disabled 
		if($("input[name*='dispTitExpMethCd']:checked").val() == '02'){
			$("#fileupload0").prop("disabled",true);
		};
		
		// 매장 메뉴 노출유형이 이미지가 아니면 매장 메뉴 이미지 파일업로드를 disabled 
		if($("input[name*='gnbExpMethCd']:checked").val() == '02'){
			$("#fileupload1").prop("disabled",true);
		};
		
		// 출력유형 change
		$("#form1 #prtTpCd").change();
		
		$("#form1 #dispPrioRnk").inputmask("999", {"placeholder": ""});										// 
	},
	// 이벤트 추가
	addEvent : function() {
		// 전시몰 change 시
		$("#searchDpmlNo").change(function(){
			var text = $("#searchDpmlNo option:selected").text();
			$("#spDpmlNm").html(text);
			
			displayTreeList.fnCleanTree();
		});
		
		// 매장유형 change 시
		$("#searchShopTpCd").change(function(){
			displayTreeList.fnCleanTree();
		});
		
		// 전시여부 change 시
		$("#searchDispYn").change(function(){
			displayTreeList.fnCleanTree();
		});
		
		// 메뉴사용여부 change 시
		$("#searchMenuUseYn").change(function(){
			displayTreeList.fnCleanTree();
		});
		
		// 사용여부 change 시
		$("#searchUseYn").change(function(){
			displayTreeList.fnCleanTree();
		});
		
		// 매장 타이틀 노출유형 click 시
		$("input[name*='dispTitExpMethCd']").click(function(){
			
			if($(this).is(':checked')&& $(this).val() == '01') {
				$("#fileupload1").prop("disabled",false);
			}else
				$("#fileupload1").prop("disabled",true);
		});
		
		// 매장 메뉴 노출유형 click 시
		$("input[name*='gnbExpMethCd']").click(function(){
			if($(this).is(':checked')&& $(this).val() == '01') {
				$("#fileupload2").prop("disabled",false);
			}else
				$("#fileupload2").prop("disabled",true);
		});
		
		// 출력유형 change 시
		$("#prtTpCd").change(function(){
			var prtTpCd = $("#prtTpCd").val();
			$("tr[name*='printType']").each(function(i){
				/*if($(this).css('display') == '') 
					$(this).css('display', 'none');	*/	
				$(this).hide();
				
				if ( prtTpCd == '01' ) {
					option.validList[3].rule.required = true;	// 전시 템플릿
				} else {
					option.validList[3].rule.required = false;	// 전시 템플릿
				}
			});		
			//$("#printType"+prtTpCd).css('display', '');
			$("#printType"+prtTpCd).show();
		});
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
		
		var searchDpmlNo	= data.searchDpmlNo;
		var searchShopTpCd	= data.searchShopTpCd;
		
		$("#dpmlNo").val(searchDpmlNo);
		$("#shopTpCd").val(searchShopTpCd);
		
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
					// 하위에 전시매장이 존재하는 경우
					if(this.childCount > 0) {
						tree.insertNewChild(id, this.dispNo, fnRecoveHtml(nvl(this.dispNm, "")), null, 0, 0, 0, "CHILD");
						//tree.insertNewChild(id, "", "", null, 0, 0, 0);
					}else{
						tree.insertNewChild(id, this.dispNo, fnRecoveHtml(nvl(this.dispNm, "")), null, 0, 0, 0);
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
		
		displayTreeList.fnFormInit();
		
		var level = tree.getLevel(id);
		
		if ( Number(level) == 1 ) {
			$("#form1 #treeId").val("");
			$("#form1 #treeDepth").val("");
		} else {
			$("#form1 #treeId").val(id);
			$("#form1 #treeDepth").val(Number(level)-1);
		}
		
		
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
		
		var dispNo		    	= null;		// 전시번호     
		var uprDispNo			= null;     // 상위전시번호   
		var dpmlNo		    	= "";       // 전시몰번호    
		var dispNm		    	= "";       // 전시명      
		var dispGnbNm           = "";       // 전시 GNB명  
		var gnbExpMethCd        = "";       // 전시 GNB노출방
		var dispTitNm           = "";       // 전시 제목명   
		var dispTitExpMethCd    = "";       // 전시제목노출방식 
		var shopTpCd            = "";       // 매장유형코드   
		var shopDescCont        = "";       // 매장설명내용   
		var dpthNo          	= "";       // 깊이번호     
		var dispPrioRnk     	= null;     // 전시우선순위   
		var useYn           	= "";       // 사용여부     
		var dispYn              = "";       // 전시여부     
		var tlwtLfYn            = "";       // 최하위리프여부  
		var prtTpCd             = "";       // 출력유형코드   
		var lstSortCd           = "";       // 리스트정렬코드  
		var movFrmeCd           = "";       // 이동프레임코드  
		var lnkUrlAddr          = "";       // 연결 url주소 
		var lnkSpdpHhNo         = null;     // 연결 기획전 전시
		var dispLrgNo           = null;     // 전시대번호    
		var dispMidNo           = null;     // 전시중번호    
		var dispSmlNo           = null;     // 전시소번호    
		var dispThnNo           = null;     // 전시세번호    
		var menuUseYn           = "";       // 메뉴사용여부   
		var tmplNo              = null;     // 템플릿번호    
		var tmplNm              = "";     	// 템플릿명
		var pppSn               = "";       // 팝업일련번호   
		                
		if ( id != "treeRoot" ) {
			$("#form1 #treeId").val(id);
			data.dispNo = id;
			
			var param	= JSON.stringify(data); 
			
			$.ajax({
				async : false,
				type: 'POST',
				url: CTX_PATH + "/mgnt/display/selectDisplayInfo.json",
				data: param,
				contentType: 'application/json',
				dataType:"json",
				success : function (data) {
					var info			= data.info;
					var titleImgList	= data.titleImgList;
					var gnbImgList		= data.gnbImgList;
					var headerImgList	= data.headerImgList;
					
					/*console.log("info=["+JSON.stringify(info)+"]")
					console.log("titleImgList=["+JSON.stringify(titleImgList)+"]")
					console.log("gnbImgList=["+JSON.stringify(gnbImgList)+"]")*/
					
					$("#apndImgUl1").html("");
					$("#apndImgUl2").html("");
					$("#apndImgUl3").html("");
					
					if ( info != undefined ) {
						dispNo 		    	= nvl(info.dispNo, null);						// 전시번호      
						uprDispNo			= nvl(info.uprDispNo, null);                    // 상위전시번호    
						dpmlNo				= nvl(info.dpmlNo, null);                       // 전시몰번호     
						dispNm 		    	= fnRecoveHtml(nvl(info.dispNm, ""));           // 전시명       
						dispGnbNm       	= fnRecoveHtml(nvl(info.dispGnbNm, ""));        // 전시 GNB명   
						gnbExpMethCd    	= nvl(info.gnbExpMethCd, "");                   // 전시 GNB노출방식
						dispTitNm       	= fnRecoveHtml(nvl(info.dispTitNm, ""));        // 전시 제목명    
						dispTitExpMethCd	= nvl(info.dispTitExpMethCd, "");               // 전시제목노출방식  
						shopTpCd        	= nvl(info.shopTpCd, "");                       // 매장유형코드    
						shopDescCont        = fnRecoveHtml(nvl(info.shopDescCont, ""));     // 매장설명내용    
						dpthNo              = nvl(info.dpthNo, "");                         // 깊이번호      
						dispPrioRnk         = nvl(info.dispPrioRnk, null);                  // 전시우선순위    
						useYn               = nvl(info.useYn, "");                          // 사용여부      
						dispYn              = nvl(info.dispYn, "");                         // 전시여부      
						tlwtLfYn            = nvl(info.tlwtLfYn, "");                       // 최하위리프여부   
						prtTpCd             = nvl(info.prtTpCd, "");                        // 출력유형코드    
						lstSortCd           = nvl(info.lstSortCd, "");                      // 리스트정렬코드   
						movFrmeCd           = nvl(info.movFrmeCd, "");                      // 이동프레임코드   
						lnkUrlAddr          = nvl(info.lnkUrlAddr, "");                     // 연결 url주소  
						lnkSpdpHhNo         = nvl(info.lnkSpdpHhNo, null);                  // 연결 기획전 전시번
						dispLrgNo           = nvl(info.dispLrgNo, null);                    // 전시대번호     
						dispMidNo           = nvl(info.dispMidNo, null);                    // 전시중번호     
						dispSmlNo           = nvl(info.dispSmlNo, null);                    // 전시소번호     
						dispThnNo           = nvl(info.dispThnNo, null);                    // 전시세번호     
						menuUseYn           = nvl(info.menuUseYn, "");                      // 메뉴사용여부    
						tmplNo              = nvl(info.tmplNo, "");                         // 템플릿번호     
						pppSn               = nvl(info.pppSn, "");                          // 팝업일련번호    
						
					}
					
					$("#form1 #treeId").val(dispNo);
					$("#form1 #treeDepth").val(dpthNo);
					
					$("#form1 #dispNo").val(dispNo);
					$("#form1 #uprDispNo").val(uprDispNo);
					$("#form1 #dpmlNo").val(dpmlNo);
					$("#form1 #shopTpCd").val(shopTpCd);
					$("#form1 #dispLrgNo").val(dispLrgNo);
					$("#form1 #dispMidNo").val(dispMidNo);
					$("#form1 #dispSmlNo").val(dispSmlNo);
					$("#form1 #dispThnNo").val(dispThnNo);
					
					$("#form1 #dispNm").val(dispNm);
					$('input:radio[name=dispTitExpMethCd]:input[value=' + dispTitExpMethCd + ']').attr("checked", true);
					$('input:radio[name=gnbExpMethCd]:input[value=' + gnbExpMethCd + ']').attr("checked", true);
					$("#form1 #shopDescCont").val(shopDescCont);
					$("#form1 #dpthNo").val(dpthNo);
					$('input:radio[name=tlwtLfYn]:input[value=' + tlwtLfYn + ']').attr("checked", true);
					$("#form1 #dispPrioRnk").val(dispPrioRnk);
					$('input:radio[name=dispYn]:input[value=' + dispYn + ']').attr("checked", true);
					$('input:radio[name=useYn]:input[value=' + useYn + ']').attr("checked", true);
					$('input:radio[name=menuUseYn]:input[value=' + menuUseYn + ']').attr("checked", true);
					$("#form1 #prtTpCd").val(prtTpCd);
					$("#form1 #tmplNo").val(tmplNo);
					$("#form1 #tmplNm").val(tmplNm);
					$("#form1 #lnkUrlAddr").val(lnkUrlAddr);
					
					if ( prtTpCd == '04' ) {
						$("#form1 #lnkSpdpHhNo40").val(lnkSpdpHhNo);
					} else if ( prtTpCd == '05' ) {
						$("#form1 #lnkSpdpHhNo50").val(lnkSpdpHhNo);
					}
					
					// 출력유형 change
					$("#form1 #prtTpCd").change();
					
					$("#form1 #lstSortCd").val(lstSortCd);
					
					if ( titleImgList != undefined ) {
						fnFileEdit(1, JSON.stringify(titleImgList), "", "Y");
					}
					if ( gnbImgList != undefined ) {
						fnFileEdit(2, JSON.stringify(gnbImgList), "", "Y");
					}
					if ( headerImgList != undefined ) {
						fnFileEdit(3, JSON.stringify(headerImgList), "", "Y");
					}
					
					$("#form1 #cmd").val("U");	// 수정
				}, 
				error: function(data, textStatus, errorThrown) {
					//$("#form1 #cmd").val("");
					fnAjaxError(data);
				}
			});
		} else {
			dpmlNo 				= $("#searchDpmlNo option:selected").val();		// 전시몰 번호
			shopTpCd 			= $("#searchShopTpCd option:selected").val();	// 매장유형
			dispTitExpMethCd	= "01";
			gnbExpMethCd		= "01";
			tlwtLfYn			= "N";
			dispYn				= "N";
			useYn				= "N";
			menuUseYn			= "N";
			prtTpCd				= "01";
			lstSortCd			= "01";
			
			dpthNo				= "";
			
			$("#form1 #treeId").val(id);
			$("#form1 #treeDepth").val(dpthNo);
			
			$("#form1 #dispNo").val(dispNo);
			$("#form1 #uprDispNo").val(uprDispNo);
			$("#form1 #dpmlNo").val(dpmlNo);
			$("#form1 #shopTpCd").val(shopTpCd);
			$("#form1 #dispLrgNo").val(dispLrgNo);
			$("#form1 #dispMidNo").val(dispMidNo);
			$("#form1 #dispSmlNo").val(dispSmlNo);
			$("#form1 #dispThnNo").val(dispThnNo);
			
			$("#form1 #dispNm").val(dispNm);
			$('input:radio[name=dispTitExpMethCd]:input[value='+dispTitExpMethCd+']').attr("checked", true);
			$('input:radio[name=gnbExpMethCd]:input[value='+gnbExpMethCd+']').attr("checked", true);
			$("#form1 #shopDescCont").val(shopDescCont);
			$("#form1 #dpthNo").val(dpthNo);
			$('input:radio[name=tlwtLfYn]:input[value='+tlwtLfYn+']').attr("checked", true);
			$("#form1 #dispPrioRnk").val(dispPrioRnk);
			$('input:radio[name=dispYn]:input[value='+dispYn+']').attr("checked", true);
			$('input:radio[name=useYn]:input[value='+useYn+']').attr("checked", true);
			$('input:radio[name=menuUseYn]:input[value='+menuUseYn+']').attr("checked", true);
			$("#form1 #prtTpCd").val(prtTpCd);
			$("#form1 #tmplNo").val(tmplNo);
			$("#form1 #tmplNm").val(tmplNm);
			$("#form1 #lnkUrlAddr").val(lnkUrlAddr);
			$("#form1 #lnkSpdpHhNo40").val(lnkSpdpHhNo);
			$("#form1 #lnkSpdpHhNo50").val(lnkSpdpHhNo);
			$("#form1 #lstSortCd").val(lstSortCd);
			
			$("#apndImgUl1").html("");
			$("#apndImgUl2").html("");
			$("#apndImgUl3").html("");
			
			// 출력유형 change
			$("#form1 #prtTpCd").change();
			
			$("#form1 #cmd").val("I");	// 등록
		}
	},
	// Tree 초기화
	fnCleanTree : function() {
		var val = $("#displayTreeForm").serialize();
		var html = "<ul><li id=\"treeRoot\"><span id=\"spDpmlNm\"></span><ul><li></li></ul></li></ul>";	
		tree.destructor();
		$("#treeboxbox_tree").html(html);
		tree = dhtmlXTreeFromHTML("treeboxbox_tree");
		tree.setOnClickHandler(displayTreeList.fnClickHandler);
		tree.setOnOpenHandler(displayTreeList.fnOpenHandler);
		
		var text = $("#searchDpmlNo option:selected").text();
		$("#spDpmlNm").html(text);
		
		// 등록 form 초기화
		displayTreeList.fnFormInit();
	},
	// 신규 버튼 click
	fnAdd : function() {
		var dispNo		    	= null;		// 전시번호     
		var uprDispNo			= null;     // 상위전시번호   
		var dpmlNo		    	= "";       // 전시몰번호    
		var dispNm		    	= "";       // 전시명      
		var dispGnbNm           = "";       // 전시 GNB명  
		var gnbExpMethCd        = "";       // 전시 GNB노출방
		var dispTitNm           = "";       // 전시 제목명   
		var dispTitExpMethCd    = "";       // 전시제목노출방식 
		var shopTpCd            = "";       // 매장유형코드   
		var shopDescCont        = "";       // 매장설명내용   
		var dpthNo          	= "";       // 깊이번호     
		var dispPrioRnk     	= null;     // 전시우선순위   
		var useYn           	= "";       // 사용여부     
		var dispYn              = "";       // 전시여부     
		var tlwtLfYn            = "";       // 최하위리프여부  
		var prtTpCd             = "";       // 출력유형코드   
		var lstSortCd           = "";       // 리스트정렬코드  
		var movFrmeCd           = "";       // 이동프레임코드  
		var lnkUrlAddr          = "";       // 연결 url주소 
		var lnkSpdpHhNo         = null;     // 연결 기획전 전시
		var dispLrgNo           = null;     // 전시대번호    
		var dispMidNo           = null;     // 전시중번호    
		var dispSmlNo           = null;     // 전시소번호    
		var dispThnNo           = null;     // 전시세번호    
		var menuUseYn           = "";       // 메뉴사용여부   
		var tmplNo              = null;     // 템플릿번호    
		var tmplNm              = "";     	// 템플릿명
		var pppSn               = "";       // 팝업일련번호 
		
		uprDispNo			= $("#form1 #treeId").val();
		dpmlNo 				= $("#searchDpmlNo option:selected").val();		// 전시몰 번호
		shopTpCd 			= $("#searchShopTpCd option:selected").val();	// 매장유형
		dpthNo				= Number(nvl($("#form1 #treeDepth").val(), 0)) + 1;
		dispTitExpMethCd	= "01";
		gnbExpMethCd		= "01";
		tlwtLfYn			= "N";
		dispYn				= "N";
		useYn				= "N";
		menuUseYn			= "N";
		prtTpCd				= "01";
		lstSortCd			= "01";
		
		$("#form1 #dispNo").val("");
		$("#form1 #uprDispNo").val(uprDispNo);
		$("#form1 #dpmlNo").val(dpmlNo);
		$("#form1 #shopTpCd").val(shopTpCd);
		$("#form1 #dispLrgNo").val(dispLrgNo);
		$("#form1 #dispMidNo").val(dispMidNo);
		$("#form1 #dispSmlNo").val(dispSmlNo);
		$("#form1 #dispThnNo").val(dispThnNo);
		
		$("#form1 #dispNm").val(dispNm);
		$('input:radio[name=dispTitExpMethCd]:input[value='+dispTitExpMethCd+']').attr("checked", true);
		$('input:radio[name=gnbExpMethCd]:input[value='+gnbExpMethCd+']').attr("checked", true);
		$("#form1 #shopDescCont").val(shopDescCont);
		$("#form1 #dpthNo").val(dpthNo);
		$('input:radio[name=tlwtLfYn]:input[value='+tlwtLfYn+']').attr("checked", true);
		$("#form1 #dispPrioRnk").val(dispPrioRnk);
		$('input:radio[name=dispYn]:input[value='+dispYn+']').attr("checked", true);
		$('input:radio[name=useYn]:input[value='+useYn+']').attr("checked", true);
		$('input:radio[name=menuUseYn]:input[value='+menuUseYn+']').attr("checked", true);
		$("#form1 #prtTpCd").val(prtTpCd);
		$("#form1 #tmplNo").val(tmplNo);
		$("#form1 #tmplNm").val(tmplNm);
		$("#form1 #lnkUrlAddr").val(lnkUrlAddr);
		$("#form1 #lnkSpdpHhNo40").val(lnkSpdpHhNo);
		$("#form1 #lnkSpdpHhNo50").val(lnkSpdpHhNo);
		$("#form1 #lstSortCd").val(lstSortCd);
		
		$("#apndImgUl1").html("");
		$("#apndImgUl2").html("");
		$("#apndImgUl3").html("");
		
		// 출력유형 change
		$("#form1 #prtTpCd").change();
		
		$("#form1 #cmd").val("I");
	},
	// 저장 버튼 click
	fnSave : function() {
		var data = {};
		if ( !displayTreeList.validate(data) ) {
			return false;
		}
		
		var cmd		= data.cmd;
		var dispNo	= data.dispNo;
		var dispNm	= data.dispNm;
		
		var treeId	= nvl($("#form1 #treeId").val(), "treeRoot");
		alert("treeId=["+treeId+"]");
		var param	= JSON.stringify(data); 
			
		if ( !confirm("저장하시겠습니까?") ) return false;
			
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/display/displaySave.json",
			data: param,
			contentType: 'application/json',
			dataType:"json",
			success : function (data) {
				var resultMsg		= data.resultMsg;
				var completeYn		= data.completeYn;
				
				dispNo				= data.dispNo;
				
				alert(resultMsg);
				if ( completeYn == "Y" ) {
					if ( cmd == "U" ) {
						tree.setItemText(dispNo, dispNm);
					} else {
						tree.insertNewChild(treeId, dispNo, fnRecoveHtml(nvl(dispNm, "")), null, 0, 0, 0);
						tree.selectItem(dispNo);
						
						displayTreeList.fnClickHandler(dispNo);
					}
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				fnAjaxError(data);
			}
		});
	},
	// 품목정보 저장시 입력값 체크
	validate : function(data) {
		$("#form1").validInit({onsubmit : false, onfocusout : false});
		
		$("#form1").validAddRules(option);		
		if($("#form1").validate().form() == false) {
	    	return false;
		}
		
		data.cmd				= $("#form1 #cmd").val();									// 등록/수정 구분
		data.dpmlNo				= $.trim($("#form1 #dpmlNo").val());						// 전시몰번호
		data.shopTpCd			= $.trim($("#form1 #shopTpCd").val());						// 매장유형
		data.dispNo				= nvl($.trim($("#form1 #dispNo").val()), null);				// 전시번호
		/*if ( dispNo != "" ) {
			data.dispNo	= dispNo;
		}*/
		data.uprDispNo			= nvl($("#form1 #uprDispNo").val(), null);					// 상위전시번호
		data.dispNm				= $.trim($("#form1 #dispNm").val());						// 전시카테고리명
		data.dispGnbNm			= data.dispNm;												// 전시 GNB명
		data.dispTitNm			= data.dispTitNm;											// 전시 제목명
		data.dispTitExpMethCd	= $("input:radio[name=dispTitExpMethCd]:checked").val();	// 매장 타이틀 노출유형
		data.gnbExpMethCd		= $("input:radio[name=gnbExpMethCd]:checked").val();		// 매장 메뉴 노출유형
		data.shopDescCont		= $.trim($("#shopDescCont").val());							// 매장 설명 내용
		data.dpthNo				= nvl($.trim($("#dpthNo").val()), 1);						// 카테고리 깊이
		data.tlwtLfYn			= $("input:radio[name=tlwtLfYn]:checked").val();			// Leaf 카테고리 여부
		data.dispPrioRnk		= $.trim($("#dispPrioRnk").val());							// 전시우선순위	
		data.dispYn				= $("input:radio[name=dispYn]:checked").val();				// 전시여부
		data.useYn				= $("input:radio[name=useYn]:checked").val();				// 사용여부
		data.menuUseYn			= $("input:radio[name=menuUseYn]:checked").val();			// 메뉴사용 여부
		data.prtTpCd			= $("#form1 #prtTpCd option:selected").val();				// 출력유형
		
		/*
		 * 출력유형(prtTpCd) == "01"
		 */
		var tmplNo				= $.trim($("#tmplNo").val());								// 전시 템플릿 번호
		var tmplNm				= $.trim($("#tmplNm").val());								// 전시 템플릿 명
		/*
		 * 출력유형(prtTpCd) == "02"
		 */
		var lnkUrlAddr			= $.trim($("#lnkUrlAddr").val());							// 대상 URL
		/*
		 * 출력유형(prtTpCd) == "04"
		 */
		var lnkSpdpHhNo40		= nvl($.trim($("#lnkSpdpHhNo40").val()), "");				// 카테고리매장번호
		/*
		 * 출력유형(prtTpCd) > 그 외
		 */
		var lnkSpdpHhNo50		= nvl($.trim($("#lnkSpdpHhNo50").val()), null);						// 기획전매장번호
		
		if ( data.prtTpCd == "01" ) {
			data.tmplNo = tmplNo;
		} else if ( data.prtTpCd == "02" ) {
			data.lnkUrlAddr = lnkUrlAddr;
		} else if ( data.prtTpCd == "04" ) {
			data.lnkSpdpHhNo = lnkSpdpHhNo40;
		} else {
			data.lnkSpdpHhNo = lnkSpdpHhNo50;
		}
		
		data.lstSortCd			= $("#form1 #lstSortCd option:selected").val();				// 리스트소팅값
		
		data.dispLrgNo			= nvl($.trim($("#form1 #dispLrgNo").val()), null);			// 전시대번호
		data.dispMidNo			= nvl($.trim($("#form1 #dispMidNo").val()), null);			// 전시중번호
		data.dispSmlNo			= nvl($.trim($("#form1 #dispSmlNo").val()), null);			// 전시소번호
		data.dispThnNo			= nvl($.trim($("#form1 #dispThnNo").val()), null);			// 전시세번호
		
		var addFileList;
		
		// 매장 타이틀 이미지 첨부파일 정보
		if ( data.dispTitExpMethCd == "01" ) {
			if ( $('input[name="addFileList1"]').length > 0 ) {
				addFileList = new Array();
				$('input[name="addFileList1"]').each(function() {
					addFileList.push(this.value);
				});
				data.addTitleImgList	= addFileList;
			} else {
				alert("매장 타이틀 이미지 첨부파일를 선택해주세요.");
				return false;
			}
		}
		
		// 매장 메뉴  이미지 첨부파일 정보
		if ( data.gnbExpMethCd == "01" ) {
			if ( $('input[name="addFileList2"]').length > 0 ) {
				addFileList = new Array();
				$('input[name="addFileList2"]').each(function() {
					addFileList.push(this.value);
				});
				data.addGnbImgList	= addFileList;
			} else {
				alert("매장 메뉴 이미지 첨부파일를 선택해주세요.");
				return false;
			}
		}
		
		// 매장 부가  이미지 첨부파일 정보
		if ( $('input[name="addFileList3"]').length > 0 ) {
			addFileList = new Array();
			$('input[name="addFileList3"]').each(function() {
				addFileList.push(this.value);
			});
			data.addHeaderImgList	= addFileList;
		}
		
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
	},
	fnFormInit : function() {
		var dispNo		    	= null;		// 전시번호     
		var uprDispNo			= null;     // 상위전시번호   
		var dpmlNo		    	= "";       // 전시몰번호    
		var dispNm		    	= "";       // 전시명      
		var dispGnbNm           = "";       // 전시 GNB명  
		var gnbExpMethCd        = "";       // 전시 GNB노출방
		var dispTitNm           = "";       // 전시 제목명   
		var dispTitExpMethCd    = "";       // 전시제목노출방식 
		var shopTpCd            = "";       // 매장유형코드   
		var shopDescCont        = "";       // 매장설명내용   
		var dpthNo          	= "";       // 깊이번호     
		var dispPrioRnk     	= null;     // 전시우선순위   
		var useYn           	= "";       // 사용여부     
		var dispYn              = "";       // 전시여부     
		var tlwtLfYn            = "";       // 최하위리프여부  
		var prtTpCd             = "";       // 출력유형코드   
		var lstSortCd           = "";       // 리스트정렬코드  
		var movFrmeCd           = "";       // 이동프레임코드  
		var lnkUrlAddr          = "";       // 연결 url주소 
		var lnkSpdpHhNo         = null;     // 연결 기획전 전시
		var dispLrgNo           = null;     // 전시대번호    
		var dispMidNo           = null;     // 전시중번호    
		var dispSmlNo           = null;     // 전시소번호    
		var dispThnNo           = null;     // 전시세번호    
		var menuUseYn           = "";       // 메뉴사용여부   
		var tmplNo              = null;     // 템플릿번호    
		var tmplNm              = "";     	// 템플릿명
		var pppSn               = "";       // 팝업일련번호 
		
		dpmlNo 				= $("#searchDpmlNo option:selected").val();		// 전시몰 번호
		shopTpCd 			= $("#searchShopTpCd option:selected").val();	// 매장유형
		dpthNo				= "";
		dispTitExpMethCd	= "01";
		gnbExpMethCd		= "01";
		tlwtLfYn			= "N";
		dispYn				= "N";
		useYn				= "N";
		menuUseYn			= "N";
		prtTpCd				= "01";
		lstSortCd			= "01";
		
		$("#form1 #dispNo").val(dispNo);
		$("#form1 #uprDispNo").val(uprDispNo);
		$("#form1 #dpmlNo").val(dpmlNo);
		$("#form1 #shopTpCd").val(shopTpCd);
		$("#form1 #dispLrgNo").val(dispLrgNo);
		$("#form1 #dispMidNo").val(dispMidNo);
		$("#form1 #dispSmlNo").val(dispSmlNo);
		$("#form1 #dispThnNo").val(dispThnNo);
		
		$("#form1 #dispNm").val(dispNm);
		$('input:radio[name=dispTitExpMethCd]:input[value='+dispTitExpMethCd+']').attr("checked", true);
		$('input:radio[name=gnbExpMethCd]:input[value='+gnbExpMethCd+']').attr("checked", true);
		$("#form1 #shopDescCont").val(shopDescCont);
		$("#form1 #dpthNo").val(dpthNo);
		$('input:radio[name=tlwtLfYn]:input[value='+tlwtLfYn+']').attr("checked", true);
		$("#form1 #dispPrioRnk").val(dispPrioRnk);
		$('input:radio[name=dispYn]:input[value='+dispYn+']').attr("checked", true);
		$('input:radio[name=useYn]:input[value='+useYn+']').attr("checked", true);
		$('input:radio[name=menuUseYn]:input[value='+menuUseYn+']').attr("checked", true);
		$("#form1 #prtTpCd").val(prtTpCd);
		$("#form1 #tmplNo").val(tmplNo);
		$("#form1 #tmplNm").val(tmplNm);
		$("#form1 #lnkUrlAddr").val(lnkUrlAddr);
		$("#form1 #lnkSpdpHhNo40").val(lnkSpdpHhNo);
		$("#form1 #lnkSpdpHhNo50").val(lnkSpdpHhNo);
		$("#form1 #lstSortCd").val(lstSortCd);
		
		$("#apndImgUl1").html("");
		$("#apndImgUl2").html("");
		$("#apndImgUl3").html("");
		
		// 출력유형 change
		$("#form1 #prtTpCd").change();
		
		$("#form1 #cmd").val("I");
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