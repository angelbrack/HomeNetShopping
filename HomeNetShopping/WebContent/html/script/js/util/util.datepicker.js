/* Util - DatePicker */
var _DATEPICKER_BTN_PATH = _CONTEXT_ROOT + "/html/images/ko/back/btn_cal.gif";

function UtilDatePicker() {}
// Default
UtilDatePicker.prototype.init = function(datePickerId, _LOCALE_TYPE) {
	$(function() {
		$.datepicker.setDefaults($.datepicker.regional[_LOCALE_TYPE]);
		$( "#" + datePickerId ).datepicker({
			showOn: "button"
			, dateFormat : 'yy-mm-dd'
			, showAnim : 'slide'
			, buttonImage: _DATEPICKER_BTN_PATH
			, buttonImageOnly: true
			, numberOfMonths: 1
			, showButtonPanel: true
			, yearRange:'2005:2020'
		});
	});
};
