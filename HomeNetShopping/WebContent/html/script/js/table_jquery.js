/*
	getAddTrHtml() : �߰��� ROW HTML �ڵ� ��ȯ �Լ�, table_jquery.js�� ȣ���ϴ� ������ �ʿ��� ����
*/

function addTableRow(id) {
	
	var addHtml = getAddTrHtml();
	
	jQuery('#' + id + ' > tbody').append(addHtml);
	
}

function delTableRow(id) {

	var rowCount = getRowCount(id) - 1;

	if (rowCount < 1) {	
		
		alert('�� �̻� ���� �� �� �����ϴ�.');
		return false;
		
	} else {
		
		var tgEl = jQuery('#' + id + ' > tbody > tr');
    	jQuery(tgEl[rowCount]).remove();
    		
	}
    		
}

function getRowCount(id) {
	return jQuery('#' + id + ' > tbody > tr').length;
}
