var frm = null;

$(document).ready(function(){
	var tree = new UtilTree("treeDiv",url1, url2, "");
	tree.initDnd();
	
	$("#menuNm").prop('disabled', true);
	$("#sortOr").prop('disabled', true);
	
});

function callbackCreateItem(result)
{
	doSelectMenu(result.menuInfo.level
				, result.menuInfo.menuNo
				, result.menuInfo.menuNm
				, result.menuInfo.useYn
				, result.menuInfo.sortOr
				, result.menuInfo.optrPgmNo
				, result.menuInfo.optrPgmNm
	);
	document.form.target = "_self";
	document.form.action = url3;
	document.form.submit();
}
	
function fnSearchProgram() {
	try {
		
	var url = url4;
	var opt = "width=1100, height=750, status=yes,scrollbars=yes,resizable=yes";
	window.open(url, "DIST_EXAM_VIEW", opt)
	} catch(e) {
		alert(e);
	}
}
function callbackDeleteItem(result)
{
	document.form.target = "_self";
	document.form.action = url3;
	document.form.submit();
}

function fnSettingProgram(progNo, progNm) {
	$("#optrPgmNo").val(progNo);
	$("#optrPgmNm").val(progNm);
}

function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
} 

function doSelectMenu(_level, _menuNo, _menuNm, _useYn, _sortOr, _optrPgmNo, _optrPgmNm) {
	if (_level > 0) {
		$("#optrPgmTr").show();
	} else {
		$("#optrPgmTr").hide();
	}
	
	if (_level > 0) {
		$("#menuNo").val(_menuNo);
		$("#menuNm").val(_menuNm);
		$(":radio[name='useYn']").each(function() {
			if ($(this).val() == _useYn) {
				$(this).attr('checked', true);
				return;
			}
		});
		$("#sortOr").val(_sortOr);
		$("#optrPgmNm").prop('readonly', true);
		$("#optrPgmNo").val(_optrPgmNo);
		$("#optrPgmNm").val(_optrPgmNm);
		$("#menuNm").removeAttr("disabled").focus();
		$("#optrPgmNm").removeAttr("disabled");
		$("#useYn").removeAttr("disabled");
		$(':radio[name="useYn"]').attr('disabled', false);
		$("#sortOr").removeAttr("disabled");
	}
}

function doUpdate() {
	var menuNo = $("#menuNo").val();
	var menuNm = $("#menuNm").val();
	var sortOr = $("#sortOr").val();

	if (menuNo == null || menuNo == "") {
		alert(message9);
		return false;
	}
	
	if (menuNm == null || menuNm == "") {
		alert(message5); 
		return false;
	}
	
	if(checkLength(menuNm) > 100) { 
		alert(message6); 
		return false;
	}
	
	if (sortOr == null || sortOr == "") {
		alert(message5); 
		return false;
	}
	
	if(isNaN(sortOr) == true) {
		alert(message10); 
		return false;
	}
	
	if(checkLength(sortOr) > 5) { 
		alert(message7); 
		return false;
	}
	
	document.form.target = "_self";
	document.form.action = url5;
	document.form.submit();
	return true;
}

function doCancel() {
	$("#menuNo").val("");
	$("#menuNm").val("");
	$("#menuNm").attr('disabled', true);
	$("#optrPgmNo").val("");
	$("#optrPgmNm").val("");
	$("#optrPgmTr").hide();
	
	$(":radio[name='useYn']").each(function() {
		$(this).attr('checked', false);
	});
	$(':radio[name="useYn"]').attr('disabled', true);
	
	$("#sortOr").val("");
	$("#sortOr").attr('disabled', true);
}

function doSubmit() {
	$("#form").submit();
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