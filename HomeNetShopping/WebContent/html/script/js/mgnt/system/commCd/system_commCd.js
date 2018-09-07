var message1 = ""; //필수 입력값입니다.
var message2 = ""; //3자 이상 입력할 수 없습니다.
var message3 = ""; //숫자만 입력가능합니다.
var message4 = ""; //10자 이상 입력할 수 없습니다.
var message5 = ""; //100자 이상 입력할 수 없습니다.
var message6 = ""; //500자 이상 입력할 수 없습니다.
var message7 = ""; //3자 이상 입력할 수 없습니다.
var message8 = ""; //삭제하시겠습니까?
var message9 = ""; //2000자 이상 입력할 수 없습니다.
var message10 = ""; //50자 이상 입력할 수 없습니다.
var message11 = "";

var url1 = "";
var url2 = "";
var url3 = "";
var url4 = "";

var frm;


function onListPage(pageNo) {
	var frm = document.form1;
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();	
}

function onSearch(pageNo) {
	var frm = document.form1;
	
	var chktext = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
	
	if(chktext.test($("#searchWord").val())) {
//		alert(message11);
//		return;
	}
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();	
}


function onListPerLine(pagePerLine) {
	var frm = document.form1;
	
	frm.currentPage.value = "1";
	frm.recordCountPerPage.value = pagePerLine;
	frm.target = "_self";
	frm.action = url1;
	frm.submit();
}

function onListPage2() {
	var frm = document.form1;
	
	frm.target = "_self";
	frm.action = url1;
	frm.submit();	
}


function onEditPage(grpCdId, cdId) {
	var frm = document.form1;
	
	frm.grpCdId.value = grpCdId;
	frm.cdId.value = cdId;
	frm.cmd.value = "U";
	
	frm.target = "_self";
	frm.action = url2;
	frm.submit();	
}

function onNewPage() {
	var frm = document.form1;
	
	frm.target = "_self";
	frm.action = url2;
	frm.submit();	
}

function onCheckEnter(event) {
	if (event.keyCode==13) {
		onListPage('1');
	}
}		


function onSave() {
	var frm1 = document.form1;
	var result = onCheckValid(frm1.cmd.value);
	
	if(!result) {
		return;
	}
	
	frm1.target = "_self";
	frm1.action = url3;
	frm1.submit();	
}

function onCheckValid(cmd) {
	
	if(cmd == "I") {
		if($("#grpCdId").val() == "") { 
			alert(message1); 
			$("#grpCdId").focus(); 
			return false;
		}
		
		/*if(isNaN($("#cdId").val()) == true) {
			alert(message3); 
			$("#cdId").focus(); 
			return false;
		}*/

		if(checkLength($("#grpCdId").val()) > 50) { 
			alert(message2); 
			$("#grpCdId").focus(); 
			return false;
		}
		
		if($("#cdId").val() == "") { 
			alert(message1); 
			$("#cdId").focus(); 
			return false;
		}
		
		if(checkLength($("#cdId").val()) > 3) { 
			alert(message4); 
			$("#cdId").focus(); 
			return false;
		}
	}
	
	if($("#cdNm").val() == "") { 
		alert(message1); 
		$("#cdNm").focus(); 
		return false;
	}
	
	if(checkLength($("#cdNm").val()) > 100) { 
		alert(message5); 
		$("#cdNm").focus(); 
		return false;
	}
	

	if(checkLength($("#cdCn").val()) > 1000) { 
		alert(message9); 
		$("#cdCn").focus(); 
		return false;
	}
	
	if($("#sortOr").val() == "") { 
		alert(message1); 
		$("#sortOr").focus(); 
		return false;
	}
	
	if(isNaN($("#sortOr").val()) == true) {
		alert(message3); 
		$("#sortOr").focus(); 
		return false;
	}

	if(checkLength($("#sortOr").val()) > 3) { 
		alert(message2); 
		$("#sortOr").focus(); 
		return false;
	}
	
	if(checkLength($("#refeV1").val()) > 50) { 
		alert(message10); 
		$("#refeV1").focus(); 
		return false;
	}
	
	if(checkLength($("#refeV2").val()) > 50) { 
		alert(message10); 
		$("#refeV2").focus(); 
		return false;
	}
	
	if(checkLength($("#refeV3").val()) > 50) { 
		alert(message10); 
		$("#refeV3").focus(); 
		return false;
	}
	
	if(checkLength($("#refeV4").val()) > 50) { 
		alert(message10); 
		$("#refeV4").focus(); 
		return false;
	}
	
	if(checkLength($("#refeV5").val()) > 50) { 
		alert(message10); 
		$("#refeV5").focus(); 
		return false;
	}
	
	return true;
	
}	

function onDelete() {
	var frm1 = document.form1;
	
	var result = confirm(message8);

	if(result) {
		frm1.target = "_self";
		frm1.action = url4;
		frm1.submit();
	}
}	


function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
} 

function checkLength(str) {
   var stringLength = str.length;
   var stringByteLength = 0;
   
   for(var i=0; i<stringLength; i++) {
       if(escape(str.charAt(i)).length >= 4) {
           stringByteLength += 3;
       } else if(escape(str.charAt(i)) == "%A7") {
           stringByteLength += 3;
   	   } else {
           if(escape(str.charAt(i)) != "%0D") {
               stringByteLength++;
   	   	   }    
   	   }    
   }
   return stringByteLength;
}