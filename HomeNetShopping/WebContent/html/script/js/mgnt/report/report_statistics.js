var frm;

var url1 = "";
var url2 = "";
var url3 = "";
var url4 = "";
var url5 = "";
var url6 = "";

function onInit() {
	if(!frm) {
		frm = document.form1;
	}
}

function onListPage(pageNo) { 
	frm = document.form1;
	frm.currentPage.value = pageNo;
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onSearch(parm) {
	frm = document.form1;
	if (parm == "1") {
		frm.action = url1;
	} else if (parm == "2") {
		frm.action = url4;
	}
	frm.target = "_self";
	frm.submit();
}

function onExcelPage(parm) {
	frm = document.form1;
	if (parm == "1") {
		frm.action = url3;
	} else if (parm == "2") {
		frm.action = url6;
	}
	frm.target = "_self";
	frm.submit();
}

function onViewPage(blngNm,blngCd,searchYear) {
	frm = document.form1;
	frm.searchBlngCd.value = blngCd;
	frm.searchBlngNm.value = blngNm;
	frm.searchYear.value = searchYear;
	frm.action = url4;
	frm.target = "_self";
	frm.submit();
}

function onListPerLine(pagePerLine) {
	frm = document.form1;
	frm.currentPage.value = 1;
	frm.recordCountPerPage.value = pagePerLine;
	frm.action = url4;
	frm.target = "_self";
	frm.submit();
}
