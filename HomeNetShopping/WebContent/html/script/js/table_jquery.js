/*
	getAddTrHtml() : 추가할 ROW HTML 코드 반환 함수, table_jquery.js를 호출하는 페이지 쪽에서 구현
*/

function addTableRow(id) {
	
	var addHtml = getAddTrHtml();
	
	jQuery('#' + id + ' > tbody').append(addHtml);
	
}

function delTableRow(id) {

	var rowCount = getRowCount(id) - 1;

	if (rowCount < 1) {	
		
		alert('더 이상 삭제 할 수 없습니다.');
		return false;
		
	} else {
		
		var tgEl = jQuery('#' + id + ' > tbody > tr');
    	jQuery(tgEl[rowCount]).remove();
    		
	}
    		
}

function getRowCount(id) {
	return jQuery('#' + id + ' > tbody > tr').length;
}
