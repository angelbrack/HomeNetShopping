jQuery(function($){ 
	$.datepicker.regional['ko'] = {
		closeText: '\ub2eb\uae30',
		prevText: '\uc774\uc804\ub2ec',
		nextText: '\ub2e4\uc74c\ub2ec',
		currentText: '\uc624\ub298',
		monthNames: ['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
		monthNamesShort: ['1\uc6d4','2\uc6d4','3\uc6d4','4\uc6d4','5\uc6d4','6\uc6d4','7\uc6d4','8\uc6d4','9\uc6d4','10\uc6d4','11\uc6d4','12\uc6d4'],
		dayNames: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesShort: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesMin: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		yearRange: '2001:2020',
		changeYear: true,
		showMonthAfterYear: false,
		yearSuffix: '\ub144'};
	
	$.datepicker.regional['en'] = {
		closeText: 'CLOSE',
		prevText: '\u4e0a\u4e00\u4e2a\u6708',
		nextText: '\u4e0b\u4e00\u4e2a\u6708',
		currentText: 'today',
		monthNames: ['JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC'],
		monthNamesShort: ['JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC'],
		dayNames: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesShort: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		dayNamesMin: ['\uc77c','\uc6d4','\ud654','\uc218','\ubaa9','\uae08','\ud1a0'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		yearRange: '2001:2020',
		changeYear: true,
		showMonthAfterYear: false,
		yearSuffix: 'year'};
	
	
	$.datepicker.regional['zh'] = {
		closeText: '\u5173\u95ed',
		prevText: '\uc774\uc804\ub2ec',
		nextText: '\ub2e4\uc74c\ub2ec',
		currentText: 'today',
		monthNames: ['\u4e00\u6708','\u4e8c\u6708','\u4e09\u6708','\u56db\u6708','\u4e94\u6708','\u516d\u6708','\u4e03\u6708','\u516b\u6708','\u4e5d\u6708','\u5341\u6708','\u5341\u4e00\u6708','\u5341\u4e8c\u6708'],
		monthNamesShort: ['\u4e00\u6708','\u4e8c\u6708','\u4e09\u6708','\u56db\u6708','\u4e94\u6708','\u516d\u6708','\u4e03\u6708','\u516b\u6708','\u4e5d\u6708','\u5341\u6708','\u5341\u4e00\u6708','\u5341\u4e8c\u6708'],
      	dayNames: ['\u661f\u671f\u5929','\u661f\u671f\u4e00','\u661f\u671f\u4e8c','\u661f\u671f\u4e09','\u661f\u671f\u56db','\u661f\u671f\u4e94','\u661f\u671f\uf9d1'],
      	dayNamesShort: ['\u65e5','\u6708','\u706b','\u6c34','\u6728','\uf90a','\u571f'],
      	dayNamesMin: ['\u65e5','\u4e00','\u4e8c','\u4e09','\u56db','\u4e94','\uf9d1'],
      	weekHeader: 'Wk',
      	dateFormat: 'yy-mm-dd',
      	firstDay: 0,
      	isRTL: false,
      	yearRange: '2010:2020',
      	changeYear: true,
      	showMonthAfterYear: false,
      	yearSuffix: '\u5e74\u5ea6'};
});