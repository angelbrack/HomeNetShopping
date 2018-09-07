$(document).ready(function(){
	/** 과정분류 기본설정 **/
	$('select > option[class^="type"]').wrap('<span style="display:none"></span>');
	$('select > option[class^="mlcl"]').wrap('<span style="display:none"></span>');
	$('select > option[class^="cl"]').wrap('<span style="display:none"></span>');
	
	/** start 셀렉트박스 변화 **/
	$("#searchCrsHlclNo").change(function() {
		$('#searchCrsMlclNo > option:first').prop('selected',true);
		$('#searchCrsClNo > option:first').prop('selected',true);
		$('#searchCrsMlclNo > option[class^="mlcl"]').wrap('<span style="display:none"></span>');
		$('#searchCrsClNo > option[class^="cl"]').wrap('<span style="display:none"></span>');
		var $mlcl = ".mlcl"+$(this).val();
		$($mlcl).unwrap();
	});
	
	$("#searchCrsMlclNo").change(function() {
		$('#searchCrsClNo > option:first').prop('selected',true);
		$('#searchCrsClNo > option[class^="cl"]').wrap('<span style="display:none"></span>');
		var $cl = ".cl"+$(this).val();
		$($cl).unwrap();
	});
});