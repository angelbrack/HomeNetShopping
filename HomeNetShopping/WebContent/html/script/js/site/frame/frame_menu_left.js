var _FRM;
var _PATH_LEFT;
var _PATH_MAIN;

function doDraw() {	
	var topFrm = getFrame("gnb");
	var topMenuNo = null;

	if (topFrm && topFrm.getCurrentMenuNo) {
		topMenuNo = topFrm.getCurrentMenuNo();
	}
	
	if(topMenuNo) {
		// left reflesh
		$("#hgrkMenuNo").val(topMenuNo);
		
		_FRM.target = "_self";
		_FRM.action = _PATH_LEFT;
		_FRM.submit();
		
		// body reflesh
		var bodyFrm = getFrame("mainFrame");
		if (bodyFrm) {
			//bodyFrm.location = _PATH_MAIN;
		}			
	}
}

function getFrame(id) {
	var frame = top.frames[id];
    return frame;
}

$(document).ready(function() {
	_FRM = document.forms[0];
});

// 하위메뉴 없을 시 비노출 (클릭 시 동일)
$(function(){
	var $listFirst = $("#lnb .list ul:first");
	
	listReset();
	
	$listFirst.prev("strong").find("a").click(function(){
		listReset();
		if($(this).parent("strong").next("ul").find("li").size() == 0){
			$(this).parent("strong").next("ul").hide();
		}
		
	});
	
	$("#lnb .list").find("strong a").click(function(){
		if($(this).parent("strong").next("ul").find("li").size() == 0){
			$(this).parent("strong").next("ul").hide();
		}
	});
	
	function listReset(){
		if($listFirst.find("li").size() == 0){
			$listFirst.hide();
		}
	}
	
	
});