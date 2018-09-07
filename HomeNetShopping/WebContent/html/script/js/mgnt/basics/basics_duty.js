$(document).ready(function(){
	var tree = new UtilTree("treeDiv",url1, url2, "");
	tree.initDnd();
	
	$("#jikchekNmRow").hide();
});

function callbackCreateItem(result){
	doSelectMenu(result.dutyInfo.level
				, result.dutyInfo.dutyCd
				, result.dutyInfo.dutyNm
				, result.dutyInfo.useYn
				, result.dutyInfo.sortOr
				, result.dutyInfo.jikchekNm
				, result.dutyInfo.dutyDivCd
	);
	$("#form").attr("action", url3);
	$("#form").submit();
}
	
function callbackDeleteItem(result){
	$("#form").attr("action", url3);
	$("#form").submit();
}

function checkDelete(orgData){
	var returnVal = true;
	
	orgData.rslt.obj.each(function () {
		$.ajax({
			async : false,
			type: 'POST',
			url: url5,
			data : { 
				"operation" : "remove_node", 
				"nodeNo" : this.id.replace("treeItem",""),
				"dnldClsNo" : this.id.replace("treeItem","")
			}, 
			dataType:"json",
			success : function (r) {
				if(!r.status) {
					if(r.completeYn == "N"){
						alert(r.resultMsg);
						returnVal = false;
					}
				}
			}, 
			error: function(data, textStatus, errorThrown) {
				alert(message4);
				returnVal = false;
			}
		});
	});
	
	return returnVal;
}

function onPress(event, type) {
	if(type == "numbers") {
		if(event.keyCode < 48 || event.keyCode > 57) return false;
	}
} 

function doSelectMenu(_level, _dutyCd, _dutyNm, _useYn, _sortOr, _jikchekNm, _dutyDivCd) {
	if (_level > 0) {
		$("#dutyCdSpan").html(_dutyCd);
		$("#dutyCd").val(_dutyCd);
		$(":radio[name='dutyDivCd']").each(function() {
			if ($(this).val() == _dutyDivCd) {
				$(this).attr('checked', true);
				return;
			}
		});
		$("#dutyNm").val(_dutyNm);
		$("#jikchekNm").val(_jikchekNm);
		$(":radio[name='useYn']").each(function() {
			if ($(this).val() == _useYn) {
				$(this).attr('checked', true);
				return;
			}
		});
		$("#sortOr").val(_sortOr);
	}
	
	if(_dutyDivCd == "2"){
		$("#jikchekNmRow").show();
	}else{
		$("#jikchekNmRow").hide();
	}
}

function doUpdate() {
	$("#form").validate({
		rules: {
			dutyNm : {
				required: true,
				maxlength : 100
			},
			jikchekNm : {
				maxlength : 100
			},
			sortOr : {
				required: true,
				number : true,
				maxlength : 5
			}
		}
	});

	$("#form").attr("action", url4);
	$("#form").submit();
}

function doCancel() {
	$("#dutyCd").val("");
	$("#dutyCdSpan").html("");
	$("#dutyNm").val("");
	$(":radio[name='dutyDivCd']").each(function() {
		$(this).attr('checked', false);
	});
	$("#jikchekNm").val("");
	
	$(":radio[name='useYn']").each(function() {
		$(this).attr('checked', false);
	});
	
	$("#sortOr").val("");
}

function doSubmit() {
	$("#form").submit();
}