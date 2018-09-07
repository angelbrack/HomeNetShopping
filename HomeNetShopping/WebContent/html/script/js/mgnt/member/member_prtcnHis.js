var url1 = "";	// list
var url2 = "";	// handle
var url3 = "";	// excel list
var url4 = "";	// save
var url5 = "";	// search1
var url8 = "";	// search1

var message1 = ""; //수정하시겠습니까?
var message2 = ""; //삭제하시겠습니까?

function onListPage(pageNo) {  
	$("#currentPage").val(pageNo);
	$("#form1").attr("action", url1);
	$("#form1").attr("target", "_self");
	$("#form1").submit();
}

function onSearch() {  
	$("#currentPage").val(1);
	$("#form1").attr("action", url1);
	$("#form1").attr("target", "_self");
	$("#form1").submit();
}

function onListPerLine(pagePerLine) {
	$("#currentPage").val(1);
	$("#recordCountPerPage").val(pagePerLine);
	$("#form1").attr("action", url1);
	$("#form1").attr("target", "_self");
	$("#form1").submit();
}

function onEditPage(userNo) {
	$("#userNo").val(userNo);
	$("#form1").attr("action", url2);
	$("#form1").attr("target", "_self");
	$("#form1").submit();
}

function onRegistPage(){
	var opt = "width=1100, height=700, status=yes, scrollbars=yes, resizable=yes";
	win = window.open("", "_popup", opt);
	win.focus();

	$("#recordCountPerPage").val(10);
	$("#form1").attr("target", "_popup");
	$("#form1").attr("action", url3);
	$("#form1").submit();
} 

function onDeleteData(){
	if($("input[name='arrCheck']:checked").length == 0){
		alert(message1);
		return;
	}
	
	if(confirm(message2)){
		$("#form1").attr("action", url5);
		$("#form1").submit();
	}
}

function onSaveData(){
	if($("input[name='arrCheck']:checked").length == 0){
		alert(message1);
		return;
	}
	
	if(confirm(message3)){
		$("#form1").attr("action", url4);
		$("#form1").submit();
	}
}

function onCheckAll(obj){
	if($(obj).attr("checked")){
		$("input[name='arrCheck']").attr("checked", true);
	}else{
		$("input[name='arrCheck']").attr("checked", false);
	}
}

function onReturnPage(){
	$("#form1").attr("target", "_self");
	$("#form1").attr("action", url2);
	$("#form1").submit();
}

function onListPopupPage(pageNo) {  
	$("#currentPage").val(pageNo);
	$("#form1").attr("action", url3);
	$("#form1").attr("target", "_self");
	$("#form1").submit();
}

function onSearchPopup() {  
	$("#currentPage").val(1);
	$("#form1").attr("action", url3);
	$("#form1").attr("target", "_self");
	$("#form1").submit();
}

function onListPopupPerLine(pagePerLine) {
	$("#currentPage").val(1);
	$("#recordCountPerPage").val(pagePerLine);
	$("#form1").attr("action", url3);
	$("#form1").attr("target", "_self");
	$("#form1").submit();
}

function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
} 

/* 소속기관 팝업호출 */
function onBlngSearchPopup(callBackFun) {
	try {
		var url = url8;
		url += "?callBackFun="+callBackFun;
		var opt = "width=700, height=700, status=yes,scrollbars=yes,resizable=yes";
		var win = window.open(url, "MEMBER_BLNG_VIEW", opt);
		win.focus();
	} catch(e) {
		alert(e);
	}
}

/* search 소속기관 리턴함수 */
function onSearchSetBlng(blngNm, blngCd) {
	$("#searchBlngNm").val(blngNm);
	$("#searchBlngCd").val(blngCd);
}