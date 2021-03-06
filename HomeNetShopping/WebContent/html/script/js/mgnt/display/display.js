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
		
		if($("input[name*='tlwtLfYn']:checked").val() == 'N'){
			$("#searchArticle").hide();
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
		
		$("input[name*='tlwtLfYn']").change(function(){
			if ( this.value == "Y" ) {
				$("#searchArticle").show();
			} else if ( this.value == "N" ) {
				$("#searchArticle").hide();
			}
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
		
		var selectedItemId = tree.getSelectedItemId();
		tree._unselectItems();
		
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
					var info				= data.info;
					var titleImgList		= data.titleImgList;
					var gnbImgList			= data.gnbImgList;
					var headerImgList		= data.headerImgList;
					var displayArticleList 	= data.displayArticleList;
					
					/*console.log("info=["+JSON.stringify(info)+"]")
					console.log("titleImgList=["+JSON.stringify(titleImgList)+"]")
					console.log("gnbImgList=["+JSON.stringify(gnbImgList)+"]")
					console.log("displayArticleList=["+JSON.stringify(displayArticleList)+"]")*/
					
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
					
					console.log("dispLrgNo_click=["+dispLrgNo+"]");
					console.log("dispMidNo_click=["+dispMidNo+"]");
					console.log("dispSmlNo_click=["+dispSmlNo+"]");
					console.log("dispThnNo_click=["+dispThnNo+"]");
					
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
					
					if ( tlwtLfYn == "Y" ) {
						$("#searchArticle").show();
					} else {
						$("#searchArticle").hide();
					}
					
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
					
					// 전시매장 품목코드매핑 정보
					var artcCd		= "";
					var artcNm		= "";
					var targetHtml	= "";
					var idx			= 1;
					
					if ( tlwtLfYn == "Y" && displayArticleList != undefined ) {
						
						if ( displayArticleList.length > 0 ) {
							for ( var i=0; i<displayArticleList.length; i++ ) {
								artcCd = nvl(displayArticleList[i].artcCd, "");
								artcNm = nvl(displayArticleList[i].artcNm, "");
								
								targetHtml += '<div id="target'+idx+'">';						
								targetHtml += '	<input type="text" id="articleCode'+idx+'" name="articleCode1" value="'+artcCd+'" class="txtbox1"  style="width:80px;height:18px;" readOnly />';
								targetHtml += '	<input type="text" id="articleName'+idx+'" name="articleName1" value="'+artcNm+'" class="txtbox1"  style="width:250px;height:18px;" readOnly />';
								targetHtml += '	<span class="hahahoho">';
								targetHtml += '		<a href="javascript:searchDisplayArticle(\'displayShopForm\', '+idx+');" name="popupBtnArticle1" id="popupBtnArticle'+idx+'" class="btn_search4">검색</a>';
								targetHtml += '		<a href="javascript:addNoArticleType('+idx+');" id="addTargetBtn'+idx+'" name="addTargetBtn" class="btn_plus2">추가</a>';
								targetHtml += '		<a href="javascript:removeNoArticleType('+idx+');" id="removeTargetBtn'+idx+'" name="removeTargetBtn" style="display: none;" class="btn_minus2">삭제</a>';
								targetHtml += '	</span>';
								targetHtml += '</div>';
							 
							 	idx++;
							}
						} else {
							targetHtml += '<div id="target'+idx+'">';						
							targetHtml += '	<input type="text" id="articleCode'+idx+'" name="articleCode1" value="" class="txtbox1"  style="width:80px;height:18px;" readOnly />';
							targetHtml += '	<input type="text" id="articleName'+idx+'" name="articleName1" value="" class="txtbox1"  style="width:250px;height:18px;" readOnly />';
							targetHtml += '	<span class="hahahoho">';
							targetHtml += '		<a href="javascript:searchDisplayArticle(\'displayShopForm\', '+idx+');" name="popupBtnArticle1" id="popupBtnArticle1" class="btn_search4">검색</a>';
							targetHtml += '		<a href="javascript:addNoArticleType('+idx+');" id="addTargetBtn1" name="addTargetBtn" class="btn_plus2">추가</a>';
							targetHtml += '		<a href="javascript:removeNoArticleType('+idx+');" id="removeTargetBtn1" name="removeTargetBtn" style="display: none;" class="btn_minus2">삭제</a>';
							targetHtml += '	</span>';
							targetHtml += '</div>';
						}
					} else {
						targetHtml += '<div id="target'+idx+'">';						
						targetHtml += '	<input type="text" id="articleCode'+idx+'" name="articleCode1" value="" class="txtbox1"  style="width:80px;height:18px;" readOnly />';
						targetHtml += '	<input type="text" id="articleName'+idx+'" name="articleName1" value="" class="txtbox1"  style="width:250px;height:18px;" readOnly />';
						targetHtml += '	<span class="hahahoho">';
						targetHtml += '		<a href="javascript:searchDisplayArticle(\'displayShopForm\', '+idx+');" name="popupBtnArticle1" id="popupBtnArticle1" class="btn_search4">검색</a>';
						targetHtml += '		<a href="javascript:addNoArticleType('+idx+');" id="addTargetBtn1" name="addTargetBtn" class="btn_plus2">추가</a>';
						targetHtml += '		<a href="javascript:removeNoArticleType('+idx+');" id="removeTargetBtn1" name="removeTargetBtn" style="display: none;" class="btn_minus2">삭제</a>';
						targetHtml += '	</span>';
						targetHtml += '</div>';
					}
					
					$("#searchArticle").children().next().html(targetHtml);
					
					var divCnt = $("#searchArticle").find("div").length;
					var cnt = divCnt-1;
					for ( var i=divCnt; i>0; i-- ) {
						if ( i == divCnt ) {
							$("[name=addTargetBtn]").eq(cnt).show();
						} else {
							$("[name=addTargetBtn]").eq(cnt).hide();
						}
						
						
						if ( i == divCnt ) {
							$("[name=removeTargetBtn]").eq(cnt).hide();
						} else {
							$("[name=removeTargetBtn]").eq(cnt).show();
						}
						
						cnt--;
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
			
			// 품목군
			var targetHtml	= "";
			var idx			= 1;
			
			targetHtml += '<div id="target'+idx+'">';						
			targetHtml += '	<input type="text" id="articleCode'+idx+'" name="articleCode1" value="" class="txtbox1"  style="width:80px;height:18px;" readOnly />';
			targetHtml += '	<input type="text" id="articleName'+idx+'" name="articleName1" value="" class="txtbox1"  style="width:250px;height:18px;" readOnly />';
			targetHtml += '	<span class="hahahoho">';
			targetHtml += '		<a href="javascript:searchDisplayArticle(\'displayShopForm\', '+idx+');" name="popupBtnArticle1" id="popupBtnArticle1" class="btn_search4">검색</a>';
			targetHtml += '		<a href="javascript:addNoArticleType('+idx+');" id="addTargetBtn1" name="addTargetBtn" class="btn_plus2">추가</a>';
			targetHtml += '		<a href="javascript:removeNoArticleType('+idx+');" id="removeTargetBtn1" name="removeTargetBtn" style="display: none;" class="btn_minus2">삭제</a>';
			targetHtml += '	</span>';
			targetHtml += '</div>';
			
			$("#searchArticle").children().next().html(targetHtml);
			
			var divCnt = $("#searchArticle").find("div").length;
			var cnt = divCnt-1;
			for ( var i=divCnt; i>0; i-- ) {
				if ( i == divCnt ) {
					$("[name=addTargetBtn]").eq(cnt).show();
				} else {
					$("[name=addTargetBtn]").eq(cnt).hide();
				}
				
				
				if ( i == divCnt ) {
					$("[name=removeTargetBtn]").eq(cnt).hide();
				} else {
					$("[name=removeTargetBtn]").eq(cnt).show();
				}
				
				cnt--;
			}
			$("#searchArticle").hide();
			
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
		
		var dispLrgNo       = $("#dispLrgNo").val();     // 전시대번호    
		var dispMidNo       = $("#dispMidNo").val();     // 전시중번호    
		var dispSmlNo       = $("#dispSmlNo").val();     // 전시소번호    
		var dispThnNo       = $("#dispThnNo").val();     // 전시세번호    
		
		$("#form1 #dispNo").val("");
		$("#form1 #uprDispNo").val(uprDispNo);
		$("#form1 #dpmlNo").val(dpmlNo);
		$("#form1 #shopTpCd").val(shopTpCd);
		$("#form1 #dispLrgNo").val(dispLrgNo);
		$("#form1 #dispMidNo").val(dispMidNo);
		$("#form1 #dispSmlNo").val(dispSmlNo);
		$("#form1 #dispThnNo").val(dispThnNo);
		
		console.log("dispLrgNo_add=["+dispLrgNo+"]");
		console.log("dispMidNo_add=["+dispMidNo+"]");
		console.log("dispSmlNo_add=["+dispSmlNo+"]");
		console.log("dispThnNo_add=["+dispThnNo+"]");
		
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
		
		// 품목군
		var targetHtml	= "";
		var idx			= 1;
		
		targetHtml += '<div id="target'+idx+'">';						
		targetHtml += '	<input type="text" id="articleCode'+idx+'" name="articleCode1" value="" class="txtbox1"  style="width:80px;height:18px;" readOnly />';
		targetHtml += '	<input type="text" id="articleName'+idx+'" name="articleName1" value="" class="txtbox1"  style="width:250px;height:18px;" readOnly />';
		targetHtml += '	<span class="hahahoho">';
		targetHtml += '		<a href="javascript:searchDisplayArticle(\'displayShopForm\', '+idx+');" name="popupBtnArticle1" id="popupBtnArticle1" class="btn_search4">검색</a>';
		targetHtml += '		<a href="javascript:addNoArticleType('+idx+');" id="addTargetBtn1" name="addTargetBtn" class="btn_plus2">추가</a>';
		targetHtml += '		<a href="javascript:removeNoArticleType('+idx+');" id="removeTargetBtn1" name="removeTargetBtn" style="display: none;" class="btn_minus2">삭제</a>';
		targetHtml += '	</span>';
		targetHtml += '</div>';
		
		$("#searchArticle").children().next().html(targetHtml);
		
		var divCnt = $("#searchArticle").find("div").length;
		var cnt = divCnt-1;
		for ( var i=divCnt; i>0; i-- ) {
			if ( i == divCnt ) {
				$("[name=addTargetBtn]").eq(cnt).show();
			} else {
				$("[name=addTargetBtn]").eq(cnt).hide();
			}
			
			
			if ( i == divCnt ) {
				$("[name=removeTargetBtn]").eq(cnt).hide();
			} else {
				$("[name=removeTargetBtn]").eq(cnt).show();
			}
			
			cnt--;
		}
		$("#searchArticle").hide();
		
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
		//alert("treeId=["+treeId+"]");
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
		
		
		var articleList;
		var articleInfo;
		if ( $('input[name="articleCode1"]').length > 0 ) {
			articleList = new Array();
			$('input[name="articleCode1"]').each(function() {
				articleInfo = new Object();
				articleInfo.artcCd = nvl(this.value, "");
				
				if ( articleInfo.artcCd != "" ) {
					articleList.push(articleInfo);
				}
			});
			data.articleList	= articleList;
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
		
		/*dispLrgNo           = $("#form1 #dispLrgNo").val();     // 전시대번호    
		dispMidNo           = $("#form1 #dispMidNo").val();     // 전시중번호    
		dispSmlNo           = $("#form1 #dispSmlNo").val();     // 전시소번호    
		dispThnNo           = $("#form1 #dispThnNo").val();     // 전시세번호    
*/		
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