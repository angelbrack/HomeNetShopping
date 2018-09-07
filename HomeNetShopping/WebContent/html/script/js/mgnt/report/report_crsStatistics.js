var frm;

var url1 = "";
var url2 = "";
var url3 = "";

var message1 = "";

function onInit() {
	if(!frm) {
		frm = document.form1;
	}
}

function onListPage() { 
	frm = document.form1;
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onSearch() {
	frm = document.form1;
	if ($('#searchBlngCd').val() == "") {
		alert(message1);
		$('#searchBlngCd').focus();
		return false;
	}
	frm.action = url1;
	frm.target = "_self";
	frm.submit();
}

function onViewPage(crsId, crsNm) {
	frm = document.form1;
	frm.crsId.value = crsId;
	frm.crsNm.value = crsNm;
	frm.action = url2;
	frm.target = "_self";
	frm.submit();
}
