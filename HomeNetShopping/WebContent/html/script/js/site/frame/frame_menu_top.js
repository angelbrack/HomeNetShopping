var _FRM;
var _PATH_TOP;
var _PATH_LEFT;
var _CURRENT_MENU_NO;
var _PATH_LOGOUT;


function doChangeMenu(currentMenuNo, menuCnt) {
	_CURRENT_MENU_NO = currentMenuNo;
	$("#currentMenuNo").val(_CURRENT_MENU_NO);
	parent.document.getElementById("mainFrame").cols = "237,*" ;
	parent.document.getElementById("leftFrame").src = _PATH_LEFT+"?currentMenuNo="+currentMenuNo;
	
	

}		

function getFrame(id) {
	var frame = top.frames[id];
    return frame;
}

function getCurrentMenuNo() {
	return _CURRENT_MENU_NO;
}

function logout(){
	_FRM.action = _PATH_LOGOUT;
	_FRM.target = "_parent";
	_FRM.submit();
}

$(document).ready(function() {
	_FRM = document.forms[0];
});