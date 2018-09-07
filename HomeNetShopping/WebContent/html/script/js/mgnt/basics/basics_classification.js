var frm = null;

$(document).ready(function(){
	var tree = new UtilTree("treeDiv",url1, url2, "");
	tree.initDnd();
});

function callbackCreateItem(result){
	doSelectMenu(result.info.level
				, result.info.crsClNo
				, result.info.crsClNm
				, result.info.useYn
				, result.info.sortOr
				, result.info.crsClEngNm
				, result.info.crsTypeNm
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
					if(r.completeYn == "E1"){
						alert(message9);
						returnVal = false;
					}else if(r.completeYn == "E2"){
						alert(message10);
						returnVal = false;
					}else if(r.completeYn == "E3"){
						alert(message11);
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

function doSelectMenu(_level, _crsClNo, _crsClNm, _useYn, _sortOr, _crsClEngNm, _crsTypeNm, _etc) {
	if (_level > 0) {
		$("#crsTypeNm").text(_crsTypeNm);
		$("#crsClNo").val(_crsClNo);
		$("#crsClNm").val(_crsClNm);
		$("#crsClEngNm").val(_crsClEngNm);
		$(":radio[name='useYn']").each(function() {
			if ($(this).val() == _useYn) {
				$(this).attr('checked', true);
				return;
			}
		});
		$("#sortOr").val(_sortOr);
		$("#etc").val(_etc);
	}
}

function doUpdate() {
	$("#form").validate({
		rules: {
			crsClNm : {
				required: true,
				maxlength : 100
			},
			crsClEngNm : {
				maxlength : 100
			},
			sortOr : {
				required: true,
				number : true,
				maxlength : 5
			},
			etc : {
				maxlength : 100
			}
		}
	});

	$("#form").attr("action", url4);
	$("#form").submit();
}

function doCancel() {
	$("#crsClNo").val("");
	$("#crsClNm").val("");
	$("#crsClEngNm").val("");
	
	$(":radio[name='useYn']").each(function() {
		$(this).attr('checked', false);
	});
	
	$("#sortOr").val("");
}

function doSubmit() {
	$("#form").submit();
}