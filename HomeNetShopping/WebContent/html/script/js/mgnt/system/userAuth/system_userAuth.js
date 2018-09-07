var message1 = "";

var url1 = "";
var url2 = "";
var url3 = "";

var frm;

function onListPage(pageNo) {
	frm = document.forms[0];
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();	
}

function onListPerLine(pagePerLine) {
	frm = document.forms[0];
	
	frm.currentPage.value = "1";
	frm.recordCountPerPage.value = pagePerLine;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();
}

function onSearch() {
	frm = document.forms[0];
	
	var chktext = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+\u253c<>@\#$%&\'\"\\\(\=]/gi;
	
	if(chktext.test($("#searchWord").val())) {
		alert(message1);
		return;
	}
	frm.currentPage.value = 1;
    frm.target = "_self";
    frm.action = url1;
    frm.submit();
    return;
}

function onEdit(userNo) {
	frm = document.forms[0];
	
    if (userNo != '') {
        $("#searchUserNo").val(userNo);
    }
    
	/*jPrompt('','', '\uac1c\uc778\uc815\ubcf4\uc870\ud68c \uc0ac\uc720\uc785\ub825', function(text) {
	     if (text == null || text.length < 1) {
	    	 jAlert('\uac1c\uc778\uc815\ubcf4\uc870\ud68c \uc0ac\uc720\ub97c \uc785\ub825\ud558\uc138\uc694.', '');
	    	 return false;
	     } else {
	    	 $("#indvInfoInqRsnCn").val(text);
	    	 frm.target = "_self";
	    	 frm.action = url2;
	    	 frm.submit();
	     }
	});*/
    
    frm.target = "_self";
	frm.action = url2;
	frm.submit();

}

function onList() {
	frm = document.forms[0];
	frm.target = "_self";
	frm.action = url1;
	frm.submit();
}

function onUpdate() {
	frm = document.forms[0];

	frm.target = "_self";
	frm.action = url3;

	return true;
}

function onSubmit() {
	jQuery("#form").submit();
}

function onSaveData() {
	frm = document.forms[0];
	
	frm.target = "_self";
	frm.action = url3;
	frm.submit();
}
 
