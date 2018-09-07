var frm;

function onListPage(pageNo) {
	frm = document.forms[0];
	
	frm.currentPage.value = pageNo;
	frm.target = "_self";
	frm.action = CTX_PATH + "/mgnt/mbr/general/genEnterpriseList.do";
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
	
	if($("#searchWord").val().length > 100) {
		alert("검색어는 100자를 초과할 수 없습니다.");
		return;
	}
	frm.currentPage.value = 1;
    frm.target = "_self";
    frm.action = CTX_PATH + "/mgnt/mbr/general/genEnterpriseList.do";
    frm.submit();
    return;
}

function onViewPage(userNo, etrNo) {
    frm = document.forms[0];
    
    if (userNo != '') {
        $("#userNo").val(userNo);
    }
    $("#etrNo").val(etrNo);
        
    frm.target = "_self";
    frm.action = CTX_PATH + "/mgnt/mbr/general/genEnterpriseView.do";
    frm.submit();

}


function onPasswordInit() {
	if($("#emailAddr").val() == "") {
		alert("이메일 주소가 없습니다.");
		return false;
	}
	if(confirm("비밀번호 초기화하시겠습니까?")) {
		
		$.ajax({
			async : false,
			type: 'POST',
			url: CTX_PATH + "/mgnt/mbr/general/genEnterprisePassInitsaveData.ajax",
			data : $("#form1").serialize(),
			dataType:"json",
			success : function (data) {
				alert("초기화되었습니다.");
			}, 
			error: function(xhr, textStatus, errorThrown) {
				fnAjaxError("에러가 발생하였습니다.");
			}
		});
	}
	
}
