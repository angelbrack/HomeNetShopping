/**
 * @author Zag (zag@uzen.net)
 *
 * 달력 팝업 관련
 *
 */
  
var dateDelimiter	= ".";
var calendar 		= null;

/**
 * 달력 선택 팝업을 띄운다
 *
 * @param target 값을 돌려 받을 input text, 팝업을 띄울때 target의 좌측 하단에 맞추기때문에 반드시 not null 이어야한다
 * @param dateString 팝업의 설정 날짜
 * @param callback 날짜를 선택한 뒤 특별한 처리를 하고자 할 때 callback을 설정하면 호출된다 func(target, dateString, year, month, day) 형태로 인자를 받을 수 있다
 *
 */
function openCalendar(target, dateString, callback, option) {
	if (!option){
		option = {
				beforeYears: 5
		};
	}
	if(target) {
		if (calendar != null) {
			calendar.hide();
			calendar = null;
		}
		else {
			calendar = new Calendar(target, dateString, callback, option);
			calendar.show();
		}	
	} else {
		alert('target이 지정되지 않았습니다!\n input text를 첫번째 인자로 넣어주세요.');
	}

}

/**
 * 달력 객체 생성자
 */
function Calendar(target, dateString, callback, option) {
	
	this.init(target, dateString, callback, option);
	
}

/**
 * 달력 객체
 */
Calendar.prototype = {
	
	/**
	 * 초기화
	 */
	init: function(target, dateString, callback, option) {
		
		this.target = target;		
		this.calendarOption = option;
		
		
		this.selectedDate = stringToDate(dateString);
		
		if(!this.selectedDate)
			this.selectedDate = stringToDate(target.value);
		if(!this.selectedDate)
			this.selectedDate = new Date();
		this.today = new Date();
		
		/* Ceres-1127 : [BO] 판매기간 개별 수정시 종료일자 수정 개선 요청 
		 * 현재일자와 selectedDate의 년도가 10년이상 차이가 날 경우,
		 * 현재일자의 날짜를 calendar의 날짜로 바꿔주는 기능을 넣어주자.
		 */
		var targetYear = this.selectedDate.getFullYear();
		var todayYear = this.today.getFullYear();
		
		if(Math.abs(targetYear-todayYear)>10)
		{
//			alert("10년이상 차이나는 경우임");
			this.selectedDate = this.today;
//			alert("날짜 보정처리 완료 : "+this.selectedDate);
		}
		
		this.callback = callback;
		
		/* Constants */
		this.WIDTH = 205;
		this.HEIGHT_BASE = 47;
		this.HEIGHT_DELTA = 25;
	},
	
	/**
	 * 달력 팝업 띄우기
	 */
	show: function() {
		this.popup =  $(this.makeBodyHTML());
		this.popup.css("position", "absolute");
		this.popup.appendTo("body");
		this.locatePopup();
	},
	
	/**
	 * 달력 팝업의 BODY의 innerHTML 생성하기
	 */
	makeBodyHTML: function() {
		
		var year = this.selectedDate.getFullYear();
		var month = this.selectedDate.getMonth() + 1; // Date.getMonth()는 0 ~ 11 까지의 값을 리턴하기 때문에 ...
		
		//alert("Y:"+year+" M:"+month);
		var yearAsString = "" + year;
		var monthAsString = ((month < 10) ? "0" : "") + month;
		var beforeYear = 5;
		if (this.calendarOption && this.calendarOption.beforeYears){
			beforeYear = this.calendarOption.beforeYears;
		}
		/* 처음에 나오는  iframe 은 정보를 출력하기 위한것이 아니고, active-x(grid, flash player) 위에 달력을 표시하기 위한 BG임. */
//		html ="<iframe frameborder='0' tabindex='-1' src='javascript:false' style='display:block;position:absolute;z-index:-1;  width:213px; height:176px '/> \
		html ="<iframe frameborder='0' tabindex='-1' src='' style='display:block;position:absolute;z-index:-1;  width:222px; height:186px '/>\
			    <div class='layer' style='position:absoulte; display:inherit; width:215px; height:176px;'>\
				<div class='layer_lb'>\
			        <div class='layer_rt'>\
			            <div class='layer_rb'>\
			                <div class='header'>\
			                    <span><img src='/image/btn_layer_close.gif' alt='창닫기'  onclick='calendar.hide(); calendar=null;'/></span>\
			                </div>\
			                <div class='contents pd0'>\
			                    <div class='tCenter'>\
									<img src='/image/btn_cap_pre.gif' alt='' class='alg_md' onclick='calendar.goPrevMonth()'/>\
			                        <select style='width:60px;' id='calendarYearSelect' onChange='calendar.updateCalendar();'>";  
		for ( var i = beforeYear ; i >0; i--  ){
			                        html += "<option>" + (year - i) +"</option>";
		}
									html += "<option selected>" + yearAsString +"</option>";
		for ( var i = 1; i <= beforeYear; i++  ){
									html += "<option>" + (year + i) +"</option>";
		}
			                        html += "\
			                        </select>\
			                        <select style='width:37px;' id='calendarMonthSelect'  onChange='calendar.updateCalendar();'>";
        for ( var i = 1; i <= 12; i++) {
        	if ( i == month ){
        		html+="<option selected>" + monthAsString + "</option>";
        	}
        	else {
        		if ( i < 10 ) {
        			html+="<option>0" + i + "</option>" ;
        		}
        		else {
        			html+="<option>" + i + "</option>" ;
        		}
        	}
        }			                          
			    html+="                    </select>\
									<img src='/image/btn_cap_next.gif' alt='' class='alg_md' onclick='calendar.goNextMonth()' />\
			                    </div>\
			                    <div class='mt5'></div>\
			                    <!-- Grid Data Area -->\
								<div class='grid_calendar w160'>\
									<table class='calendar'>\
										<colgroup>\
										<col span='6' />\
										</colgroup>\
										<tr>\
											<th class='red' style='color:red;'>일</th>\
											<th>월</th>\
											<th>화</th>\
											<th>수</th>\
											<th>목</th>\
											<th>금</th>\
											<th class='blue'>토</th>\
										</tr>";
		
		var firstDate = getFirstDateOfMonth(this.selectedDate);
		var lastDate = getLastDateOfMonth(this.selectedDate);
		
		var firstDatePos = firstDate.getDay();
		var lastDatePos = firstDatePos + lastDate.getDate();
		
		cellCount = lastDatePos + (6 - lastDate.getDay());
		
		this.rowCount = cellCount / 7; // 달력의 줄수
		
		var realDate;
		
		for(var d = 0; d < cellCount; ++d) {
		
			realDate = d - firstDatePos + 1;
		
			if(d % 7 == 0) {
				html += "<tr>";
			}
			
			
			if(d < firstDatePos || lastDatePos <= d) {
				html += "<td>&nbsp;</td>";
			} else {
				if (d % 7 == 0){
					html += "<td class='red' "; 
				}
				else if (d % 7 ==  6){
					html += "<td class='blue' "; 
				}
				else {
					html += "<td ";
						
				}
				html += "onclick='calendar.selectDate(" + realDate + ")'><div class='note_s'><i>"+realDate+"</i></div></td>"; 
				
			}
			html += "</td>";
			
			if(d % 7 == 6) {
				html += "</tr>";
			}
		}

		html += "";
			"</table></div></div></div></div></div></div>";

		return html;
	},
	
	/**
	 * 날짜 선택 이벤트 핸들러
	 */
	selectDate: function(date) {
		
		
		/* 전체기간이 있을 경우에 체크를 빼고 enable 시킴 */
		if (typeof $('input:checkbox[name=checkTerm]').val()!= 'undefined') {
	
			if($('input:checkbox[name=checkTerm]').val()!=""){
				$('input:checkbox[name=checkTerm]').prop("checked", false);
				$('#startDate').prop("disabled", false);
				$('#endDate').prop("disabled", false);			
				
			}
		}
		
		
		var y = this.selectedDate.getFullYear();
		var m = (((this.selectedDate.getMonth() + 1) < 10) ? "0" : "") + (this.selectedDate.getMonth() + 1);
		var d = ((date < 10) ? "0" : "") + date;
		
		var dateString = y + dateDelimiter + m + dateDelimiter + d;
		
		if(this.target) {
			$("#"+this.target).val(dateString);
			//this.target.value = dateString;
		}
		
		if(this.callback) {
			this.callback(this.target, dateString, y, m, d);
		}
		
		this.hide();
		
		calendar = null;
	},
	
	/**
	 * 달력 숨기기
	 */
	hide: function() {
		this.popup.hide();
		this.popup.remove();
		// ${"#layer").hide();
		//${"#layer").remove();
	},
	
	/**
	 * 다음 달 보기
	 */
	goNextMonth: function() {
		if(!this.popup) {
			return;
		}
		this.selectedDate = getNextMonth(this.selectedDate);

		this.hide();
		this.show();
	},
	
	/**
	 * 지난 달 보기
	 */
	goPrevMonth: function() {
		if(!this.popup) {
			return;
		}
		this.selectedDate = getPrevMonth(this.selectedDate);
		
		this.hide();
		this.show();
	}, 
	
	updateCalendar: function  () {
		if(!this.popup) {
			return;
		}
		

		var year = $("#calendarYearSelect").val();
		var month = $("#calendarMonthSelect").val();
		var dateString = year+dateDelimiter+month+".01";
		this.selectedDate = stringToDate(dateString);
		this.hide();
		this.show();
	},
	
	locatePopup :function (){
		var pos = $("#"+this.target).offset();
		this.popup.css({
			top : pos.top+$("#"+this.target).height()-80,
			left: pos.left,
			zIndex: 9999999
		});
	}

	
}


/**
 * 문자열을 날짜로 변환한다
 * 문자열 형식은 "2008/05/21", "2008-05-21", "2008.05.21"만 지원한다
 */
function stringToDate(str) {
	if(str) {
		var matches = str.match(/(\d{4})[-./](\d{1,2})[-./](\d{1,2})/);
		if(matches != null) {
			return createDate(parseInt(matches[1], 10), parseInt(matches[2], 10), parseInt(matches[3], 10));
		}
	}
	return null;
}

/**
 *
 */
function createDate(year, month, date) {
	return doCreateDate(year, month - 1, date);
}

function doCreateDate(year, month, date) {
	return new Date(year, month, date, 0, 0, 0, 0);
}

/**
 *
 */
function copyDate(date) {
	return doCreateDate(date.getFullYear(), date.getMonth(), date.getDate());
}

/**
 * 해당 월의 첫날 날짜 구하기
 */
function getFirstDateOfMonth(date) {
	return doCreateDate(date.getFullYear(), date.getMonth(), 1);
}

/**
 * 해당 월의 마직막 날짜 구하기
 */
function getLastDateOfMonth(date) {
	return doCreateDate(date.getFullYear(), date.getMonth() + 1, 0);
}

/**
 * 한달 전 날짜 구하기
 */
function getPrevMonth(date) {
	
	// 지난 달 마지막 날짜를 구한다
	var newDate = doCreateDate(date.getFullYear(), date.getMonth(), 0);
	
	// 1. 현재 '일'이 지난 달 마지막 '일'보다 작으면, '월'만 감소시키고
	// 2. 아니면 지난 달 마지막 날짜를 리턴한다
	return (date.getDate() < newDate.getDate()) ? doCreateDate(date.getFullYear(), date.getMonth() - 1, date.getDate()) : newDate;
}

/**
 * 한달 후 날짜 구하기
 */
function getNextMonth(date) {

	// 다음 달 마지막 날짜를 구한다
	var newDate = doCreateDate(date.getFullYear(), date.getMonth() + 2, 0);
	
	// 1. 현재 '일'이 다음 달 마지막 '일'보다 작으면, '월'만 증가시키고
	// 2. 아니면 다음 달 마지막 날짜를 리턴한다
	return (date.getDate() < newDate.getDate()) ? doCreateDate(date.getFullYear(), date.getMonth() + 1, date.getDate()) : newDate;
}

/*********************************************/
//2008/06/04 charles추가
/**
 * 유효한(존재하는) 월(月)인지 체크
 */
function isValidMonth(mm) {
    var m = parseInt(mm,10);
    return (m >= 1 && m <= 12);
}

/**
 * 유효한(존재하는) 일(日)인지 체크
 */
function isValidDay(yyyy, mm, dd) {
    var m = parseInt(mm,10) - 1;
    var d = parseInt(dd,10);

    var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
    if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
        end[1] = 29;
    }

    return (d >= 1 && d <= end[m]);
}

/**
 * 유효한(존재하는) 시(時)인지 체크
 */
function isValidHour(hh) {
    var h = parseInt(hh,10);
    return (h >= 1 && h <= 24);
}

/**
 * 유효한(존재하는) 분(分)인지 체크
 */
function isValidMin(mi) {
    var m = parseInt(mi,10);
    return (m >= 1 && m <= 60);
}

/**
 * Time 형식인지 체크(느슨한 체크)
 */
function isValidTimeFormat(time) {
    return (!isNaN(time) && time.length == 12);
}

/**
 * 유효하는(존재하는) Time 인지 체크

 * ex) var time = form.time.value; //'200102310000'
 *     if (!isValidTime(time)) {
 *         alert("올바른 날짜가 아닙니다.");
 *     }
 */
function isValidTime(time) {
    var year  = time.substring(0,4);
    var month = time.substring(4,6);
    var day   = time.substring(6,8);
    var hour  = time.substring(8,10);
    var min   = time.substring(10,12);

    if (parseInt(year,10) >= 1900  && isValidMonth(month) &&
        isValidDay(year,month,day) && isValidHour(hour)   &&
        isValidMin(min)) {
        return true;
    }
    return false;
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toTimeObject(time) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);
    var hour  = time.substr(8,2);
    var min   = time.substr(10,2);

    return new Date(year,month,day,hour,min);
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toDateObject(time) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);

    return new Date(year,month,day);
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toFormatString(time, dele) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2); // 1월=0,12월=11
    var day   = time.substr(6,2);

    return ("" + year + dele + month + dele + day)
}


/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeString(date, secondYn) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();
    var hour  = date.getHours();
    var min   = date.getMinutes();
    var second = date.getSeconds(); 
    

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
    if (("" + hour).length  == 1) { hour  = "0" + hour;  }
    if (("" + min).length   == 1) { min   = "0" + min;   }
    if (("" + second).length   == 1) { second   = "0" + second;   }

    if ( secondYn == 'Y' ) {
    	return ("" + year + month + day + hour + min + second)
    } else {
    	return ("" + year + month + day + hour + min)
    }
}


/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toDateString(date, dele) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }

    return ("" + year + dele + month + dele + day)
}

/**
 * Time이 현재시각 이후(미래)인지 체크
 */
function isFutureTime(time) {
    return (toTimeObject(time) > new Date());
}

/**
 * Time이 현재시각 이전(과거)인지 체크
 */
function isPastTime(time) {
    return (toTimeObject(time) < new Date());
}

/**
 * 주어진 Time 과 y년 m월 d일 차이나는 Time을 리턴

 * ex) var time = form.time.value; //'20000101'
 *     alert(shiftTime(time,0,0,-100));
 *     => 2000/01/01 00:00 으로부터 100일 전 Time
 */
function shiftDate(time,y,m,d, dele) { //moveTime(time,y,m,d)
    var date = toDateObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return toDateString(date, dele);
}

/**
 * 주어진 Time 과 y년 m월 d일 h시 차이나는 Time을 리턴

 * ex) var time = form.time.value; //'20000101000'
 *     alert(shiftTime(time,0,0,-100,0));
 *     => 2000/01/01 00:00 으로부터 100일 전 Time
 */
function shiftTime(time,y,m,d,h) { //moveTime(time,y,m,d,h)
    var date = toTimeObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date, 'N');
}

/**
 * 두 Time이 몇 개월 차이나는지 구함

 * time1이 time2보다 크면(미래면) minus(-)
 */
function getMonthInterval(time1,time2) { //measureMonthInterval(time1,time2)
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);

    var years  = date2.getFullYear() - date1.getFullYear();
    var months = date2.getMonth() - date1.getMonth();
    var days   = date2.getDate() - date1.getDate();

    return (years * 12 + months + (days >= 0 ? 0 : -1) );
}

/**
 * 두 Time이 며칠 차이나는지 구함
 * time1이 time2보다 크면(미래면) minus(-)
 */
function getDayInterval(time1,time2) {
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);
    var day   = 1000 * 3600 * 24; //24시간

    return parseInt((date2 - date1) / day, 10);
}

/**
 * 두 Time이 몇 시간 차이나는지 구함

 * time1이 time2보다 크면(미래면) minus(-)
 */
function getHourInterval(time1,time2) {
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);
    var hour  = 1000 * 3600; //1시간

    return parseInt((date2 - date1) / hour, 10);
}

/**
 * 현재 시각을 Date 형식으로 리턴

 */
function getCurrentDate(dele) {
    return toFormatString(toTimeString(new Date(), 'N'), dateDelimiter);
}

/**
 * 현재 시각을 Time 형식으로 리턴

 */
function getCurrentTime() {
    return toTimeString(new Date(), 'N');
}

/**
 * 현재 시간 초까지 Time 형식으로 리턴

 */
function getCurrentTimeSecond() {
    return toTimeString(new Date(), 'Y');
}

/**
 * 현재 시각과 y년 m월 d일 h시 차이나는 Time을 리턴
 */
function getRelativeTime(y,m,d,h) {
/*
    var date = new Date();

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date);
*/
    return shiftTime(getCurrentTime(),y,m,d,h);
}

/**
 * 현재 年을 YYYY형식으로 리턴
 */
function getYear() {
/*
    var now = new Date();
    return now.getFullYear();
*/
    return getCurrentTime().substr(0,4);
}

/**
 * 현재 月을 MM형식으로 리턴
 */
function getMonth() {
/*
    var now = new Date();

    var month = now.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    if (("" + month).length == 1) { month = "0" + month; }

    return month;
*/
    return getCurrentTime().substr(4,2);
}

/**
 * 현재 日을 DD형식으로 리턴

 */
function getDay() {
/*
    var now = new Date();

    var day = now.getDate();
    if (("" + day).length == 1) { day = "0" + day; }

    return day;
*/
    return getCurrentTime().substr(6,2);
}

/**
 * 현재 時를 HH형식으로 리턴
 */
function getHour() {
/*
    var now = new Date();

    var hour = now.getHours();
    if (("" + hour).length == 1) { hour = "0" + hour; }

    return hour;
*/
    return getCurrentTime().substr(8,2);
}

/**
 * 오늘이 무슨 요일이야?

 * ex) alert('오늘은 ' + getDayOfWeek() + '요일입니다.');
 * 특정 날짜의 요일을 구하려면? => 여러분이 직접 만들어 보세요.
 */
function getDayOfWeek() {
    var now = new Date();

    var day = now.getDay(); //일요일=0,월요일=1,...,토요일=6
    var week = new Array('일','월','화','수','목','금','토');

    return week[day];
}


/*
  윤달 포함 달별 일수 Return
*/
function daysPerMonth()
{
    var DOMonth  = new Array("31","28","31","30","31","30","31","31","30","31","30","31");
    var IDOMonth = new Array("31","29","31","30","31","30","31","31","30","31","30","31");

	if(arguments[1] == 0) arguments[1] = 12;

    if ( (arguments[0]%4) == 0 )
    {
        if ( (arguments[0]%100) == 0 && (arguments[0]%400) != 0 )
            return DOMonth[arguments[1]-1];
        return IDOMonth[arguments[1]-1];
    }
    else
        return DOMonth[arguments[1]-1];
}

/*
	화면의 오늘, 어제, 이번주, 지난주, 이번달, 지난달 기간 구하기 
*/
function setDuration(obj, start, end) {

	var flag = obj.value;

	// 기간선택이면...
	if(flag == "CM00801") {
		start.value = "";
		end.value = "";
	}
	// 오늘이면...
	if(flag == "CM00802") {
		start.value = getCurrentDate(dateDelimiter);
		end.value = getCurrentDate(dateDelimiter);
	}
	// 어제이면...
	if(flag == "CM00803") {
		var tmpDate = getCurrentTime();
		start.value = shiftDate(tmpDate, 0, 0, -1, dateDelimiter);
		end.value = shiftDate(tmpDate, 0, 0, -1, dateDelimiter);
	}
	// 최근한주이면...
	if(flag == "CM00804") {
		start.value = shiftDate(getCurrentTime(), 0, 0, -7, dateDelimiter);
		end.value = getCurrentDate(dateDelimiter);
	}
	// 이번주이면...
	if(flag == "CM00805") {
	    var now = new Date();
	    var day = now.getDay(); //일요일=0,월요일=1,화요일=2,수요일=3,목요일=4,금요일=5,토요일=6
	    
		start.value = shiftDate(getCurrentTime(), 0, 0, -1 * day, dateDelimiter);
		end.value = shiftDate(getCurrentTime(), 0, 0, 6-day, dateDelimiter);
	}
	// 지난주이면...
	if(flag == "CM00806") {
	    var now = new Date();
	    var day = now.getDay() + 7; //일요일=0,월요일=1,화요일=2,수요일=3,목요일=4,금요일=5,토요일=6
	    
		start.value = shiftDate(getCurrentTime(), 0, 0, -1 * day, dateDelimiter);
		end.value = shiftDate(getCurrentTime(), 0, 0, 6-day, dateDelimiter);
	}
	// 최근한달이면...
	//if(flag == "") {
	//	start.value = shiftDate(getCurrentTime(), 0, -1, 0, "/");
	//	end.value = getCurrentDate("/");
	//}
	// 이번달이면...
	if(flag == "CM00807") {
	    var now = new Date();
	    var day = now.getDate();
		var last_day = daysPerMonth(now.getFullYear(), now.getMonth()+1);
		
		start.value = shiftDate(getCurrentTime(), 0, 0, -1 * (day - 1), dateDelimiter);
		end.value = shiftDate(getCurrentTime(), 0, 0, last_day-day, dateDelimiter);
	}
	// 지난달이면...
	if(flag == "CM00808") {
	    var now = new Date();
	    var day = now.getDate();
		var last_day = daysPerMonth(now.getFullYear(), now.getMonth());
		
		start.value = shiftDate(getCurrentTime(), 0, -1, -1 * (day - 1), dateDelimiter);
		end.value = shiftDate(getCurrentTime(), 0, -1, last_day-day, dateDelimiter);
	}
	/*******************************************************/
	//charles추가끝
}

/*
	기간 조회
*/
function setSearchDate(term) {

	var startDate;
	var dateNew = new Date();
	var endDate;
	if(term == "0day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, 0, dateDelimiter);
	}else if(term == "1day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, -1, dateDelimiter);
	}else if(term == "3day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, -3, dateDelimiter);
	}else if(term == "5day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, -5, dateDelimiter);
	}else if(term == "7day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, -7, dateDelimiter);
	}else if(term == "15day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, -15, dateDelimiter);
	}else if(term == "1month"){
		startDate = shiftDate(getCurrentTime(), 0, -1, 0, dateDelimiter);
	}else if(term == "2month") {
		startDate = shiftDate(getCurrentTime(), 0, -2, 0, dateDelimiter);
	}else if(term == "3month"){
		startDate = shiftDate(getCurrentTime(), 0, -3, 0, dateDelimiter);
	}else if(term == "6month"){
		startDate = shiftDate(getCurrentTime(), 0, -6, 0, dateDelimiter);	
	}else if(term == "9month"){
		startDate = shiftDate(getCurrentTime(), 0, -9, 0, dateDelimiter);
	}else if(term == "12month"){
		startDate = shiftDate(getCurrentTime(), 0, -12, 0, dateDelimiter);		
	}else if(term == "thisMonth"){  // 이번달일 경우 이정현 추가
		startDate = shiftDate(getCurrentTime(), 0, 0, -1 * (dateNew.getDate() - 1), dateDelimiter);
	}

	if(term == "thisMonth"){
		var last_day = daysPerMonth(dateNew.getFullYear(), dateNew.getMonth()+1);
		endDate = shiftDate(getCurrentTime(), 0, 0, last_day-dateNew.getDate(), dateDelimiter);
	}else{
		endDate = toFormatString(getCurrentTime(), dateDelimiter);
	}


	if(term == ""){
		endDate == "";
	}else{
		endDate = toFormatString(getCurrentTime(), dateDelimiter);
	}

	$("#searchStartDate").val(startDate);
	$("#searchEndDate").val(endDate);
}
/**
 * 콤보박스 선택 값에 따라 일자 설정
 */
function searchDateChange(){
	
	var term = $('#searchCheckOptDate').val();
	if( term == '' ){
		$('#searchStartDate').val("");
		$('#searchEndDate').val("");
	}
	else {
		setSearchDate(term);
	}
}

jQuery(function($){
//	$.datepicker.regional['ko'] = {
//		closeText: '닫기',
//		prevText: '이전달',
//		nextText: '다음달',
//		currentText: '오늘',
//		monthNames: ['1월','2월','3월','4월','5월','6월',
//		'7월','8월','9월','10월','11월','12월'],
//		monthNamesShort: ['1월','2월','3월','4월','5월','6월',
//		'7월','8월','9월','10월','11월','12월'],
//		dayNames: ['일','월','화','수','목','금','토'],
//		dayNamesShort: ['일','월','화','수','목','금','토'],
//		dayNamesMin: ['일','월','화','수','목','금','토'],
//		dateFormat: 'yy-mm-dd', firstDay: 0,
//		isRTL: false};
//	$.datepicker.setDefaults($.datepicker.regional['ko']);
});

$(function() {
	marker = $('<span><img id="hdStartDate" style="vertical-align:middle;" src="/image/icon_calendar.gif"/></span>').insertBefore("#hdStartDate")
	marker.click(function(e){
		openCalendar("startDate", $("#startDate").val(),null);
	});
	
	marker = $('<span><img id="hdEndDate" style="vertical-align:middle;" src="/image/icon_calendar.gif"/></span>').insertBefore("#hdEndDate")
	marker.click(function(e){
		openCalendar("endDate", $("#endDate").val(),null);
	});
//	$("#hdStartDate").datepicker({ 
//        buttonImage: '../image/icon_calendar.gif', 
//        buttonImageOnly: true,
//        changeMonth: true,
//		changeYear: true,
//		dateFormat: 'yy-mm-dd',
//		showOn: 'button',
//		buttonText: "달력",
//		constranInput: true,
//		duration: 100,
//		onSelect:function(date) { $("#startDate").val(date); }
//     });
//	
//	$("#hdEndDate").datepicker({ 
//        buttonImage: '../image/icon_calendar.gif', 
//        buttonImageOnly: true,
//        changeMonth: true,
//		changeYear: true,
//		dateFormat: 'yy-mm-dd',
//		showOn: 'button',
//		buttonText: "달력",
//		constranInput: true,
//		duration: 100,
//		onSelect:function(date) { $("#endDate").val(date); }
//     });
	
});

$(function() {
	marker = $('<span><img id="hdStartDate2" style="vertical-align:middle;" src="/image/icon_calendar.gif"/></span>').insertBefore("#hdStartDate2")
	marker.click(function(e){
		openCalendar("startDate2", $("#startDate2").val(),null);
	});
	
	marker = $('<span><img id="hdEndDate2" style="vertical-align:middle;" src="/image/icon_calendar.gif"/></span>').insertBefore("#hdEndDate2")
	marker.click(function(e){
		openCalendar("endDate2", $("#endDate2").val(),null);
	});
});

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
* @author Zag (zag@uzen.net)
*
* 달력 팝업 관련
*
*/

var calendar_two = null;

/**
* 달력 선택 팝업을 띄운다
*
* @param target 값을 돌려 받을 input text, 팝업을 띄울때 target의 좌측 하단에 맞추기때문에 반드시 not null 이어야한다
* @param dateString 팝업의 설정 날짜
* @param callback 날짜를 선택한 뒤 특별한 처리를 하고자 할 때 callback을 설정하면 호출된다 func(target, dateString, year, month, day) 형태로 인자를 받을 수 있다
*
*/
function openCalendar_two(target, dateString, callback, option) {
	if (!option){
		option = {
				beforeYears: 5
		};
	}
	if(target) {
		if (calendar_two != null) {
			calendar_two.hide();
			calendar_two = null;
		} else {
			calendar_two = new Calendar_two(target, dateString, callback, option);
			calendar_two.show();
		}	
	} else {
		alert('target이 지정되지 않았습니다!\n input text를 첫번째 인자로 넣어주세요.');
	}

}

/**
* 달력 객체 생성자
*/
function Calendar_two(target, dateString, callback, option) {

	this.init(target, dateString, callback, option);

}

/**
* 달력 객체
*/
Calendar_two.prototype = {

/**
* 초기화
*/
init: function(target, dateString, callback, option) {

	this.target = target;		
	this.calendarOption = option;
	this.startSelecDate = "";
	this.endSelecDate = "";
	
	this.selectedDate = stringToDate(dateString);
	this.selectedDate2 = stringToDate(dateString);
	
	if(!this.selectedDate)
		this.selectedDate = stringToDate(target.value);
	if(!this.selectedDate)
		this.selectedDate = new Date();
	if(!this.selectedDate2)
		this.selectedDate2 = stringToDate(target.value);
	if(!this.selectedDate2)
		this.selectedDate2 = new Date();
	this.today = new Date();
	
	/* Ceres-1127 : [BO] 판매기간 개별 수정시 종료일자 수정 개선 요청 
	* 현재일자와 selectedDate의 년도가 10년이상 차이가 날 경우,
	* 현재일자의 날짜를 calendar의 날짜로 바꿔주는 기능을 넣어주자.
	*/
	var targetYear = this.selectedDate.getFullYear();
	var e_targetYear = this.selectedDate2.getFullYear();
	var todayYear = this.today.getFullYear();
	
	if(Math.abs(targetYear-todayYear)>10)
	{
		//alert("10년이상 차이나는 경우임");
		this.selectedDate = this.today;
		//alert("날짜 보정처리 완료 : "+this.selectedDate);
	}else if(Math.abs(e_targetYear-todayYear)>10){
		this.selectedDate2 = this.today;
	}
	
	this.callback = callback;
	
	/* Constants */
	this.WIDTH = 205;
	this.HEIGHT_BASE = 47;
	this.HEIGHT_DELTA = 25;
},

/**
* 달력 팝업 띄우기
*/
show: function() {
	this.popup =  $(this.makeBodyHTML());
	this.popup.css("position", "absolute");
	this.popup.appendTo("body");
	this.locatePopup();

},

/**
* 달력 팝업의 BODY의 innerHTML 생성하기
*/
makeBodyHTML: function() {

	var year = this.selectedDate.getFullYear();
	var month = this.selectedDate.getMonth() + 1; // Date.getMonth()는 0 ~ 11 까지의 값을 리턴하기 때문에 ...
	//alert("Y:"+year+" M:"+month);
	var yearAsString = "" + year;
	var monthAsString = ((month < 10) ? "0" : "") + month;
	
	var e_year = this.selectedDate2.getFullYear();
	var e_month = this.selectedDate2.getMonth() + 1; // Date.getMonth()는 0 ~ 11 까지의 값을 리턴하기 때문에 ...
	//alert("Y:"+year+" M:"+month);
	var e_yearAsString = "" + e_year;
	var e_monthAsString = ((e_month < 10) ? "0" : "") + e_month;
	
	var beforeYear = 5;
	if (this.calendarOption && this.calendarOption.beforeYears){
		beforeYear = this.calendarOption.beforeYears;
	}

	var startDT = this.startSelecDate;
	var endDT = this.endSelecDate;
	
	/* 처음에 나오는  iframe 은 정보를 출력하기 위한것이 아니고, active-x(grid, flash player) 위에 달력을 표시하기 위한 BG임. */
	//html ="<iframe frameborder='0' tabindex='-1' src='javascript:false' style='display:block;position:absolute;z-index:-1;  width:213px; height:176px '/> \
	html ="<iframe frameborder='0' tabindex='-1' src='' style='display:block;position:absolute;z-index:-1;  width:385px;height:250px;'/>\
			<div class='layer' style='position:absoulte; display:inherit;width:375px;'>\
				<div class='layer_lb'>\
					<div class='layer_rt'>\
						<div class='layer_rb'>\
							<div class='header'>\
								<span><img src='/image/btn_layer_close.gif' alt='창닫기'  onclick='calendar_two.hide(); calendar=null;'/></span>\
							</div>\
							<div>\
								<table>\
									<colgroup>\
										<col width='180' />\
										<col width='1%' />\
										<col width='180' />\
										<col width='1%' />\
									</colgroup>\
									<tr>\
										<td>\
											<div class='tCenter'>[시작일시]</div>\
											<div>\
												<div class='tCenter mt3'>\
													<img src='/image/btn_cap_pre.gif' alt='' class='alg_md' onclick='calendar_two.goPrevMonth(1)'/>\
													<select style='width:50px;' id='s_calendarYearSelect' onChange='calendar_two.updateCalendar_two(1);'>";  
											for ( var i = beforeYear ; i >0; i--  ){
														html += "<option>" + (year - i) +"</option>";
											}
														html += "<option selected>" + yearAsString +"</option>";
											for ( var i = 1; i <= beforeYear; i++  ){
														html += "<option>" + (year + i) +"</option>";
											}
														html += "\
													</select>\
													<select style='width:37px;' id='s_calendarMonthSelect'  onChange='calendar_two.updateCalendar_two(1);'>";
											for ( var i = 1; i <= 12; i++) {
												if ( i == month ){
														html+="<option selected>" + monthAsString + "</option>";
												} else {
													if ( i < 10 ) {
														html+="<option>0" + i + "</option>" ;
													} else {
														html+="<option>" + i + "</option>" ;
													}
												}
											}
					html+="                    	</select>\
													<img src='/image/btn_cap_next.gif' alt='' class='alg_md' onclick='calendar_two.goNextMonth(1)' />\
												</div>\
												<div class='mt5'></div>\
												<div class='grid_calendar w160'>\
													<table class='calendar'>\
														<colgroup>\
															<col span='6' />\
														</colgroup>\
															<tr>\
																<th class='red' style='color:red;'>일</th>\
																<th>월</th>\
																<th>화</th>\
																<th>수</th>\
																<th>목</th>\
																<th>금</th>\
																<th class='blue'>토</th>\
															</tr>";
												
												var firstDate = getFirstDateOfMonth(this.selectedDate);
												var lastDate = getLastDateOfMonth(this.selectedDate);
												
												var firstDatePos = firstDate.getDay();
												var lastDatePos = firstDatePos + lastDate.getDate();
												
												cellCount = 42;
												this.rowCount = cellCount / 7; // 달력의 줄수
												
												var realDate;
												
												for(var d = 0; d < cellCount; ++d) {
												
													realDate = d - firstDatePos + 1;
													
													if(d % 7 == 0) {
														html += "<tr>";
													}
													
													if(d < firstDatePos || lastDatePos <= d) {
														html += "<td>&nbsp;</td>";
													} else {
														if (d % 7 == 0){
															html += "<td class='red' "; 
														} else if (d % 7 ==  6){
															html += "<td class='blue' "; 
														} else {
															html += "<td ";
														}
														html += "onclick='calendar_two.selectDate2(1," + realDate + ")'><div class='note_s'><i>"+realDate+"</i></div></td>"; 
													}
														html += "</td>";
													
													if(d % 7 == 6) {
														html += "</tr>";
													}
												}
												
									html += "</table>\
												</div>\
												<div class='tCenter mt5'>\
													<input type='text' id='start_dt' class='input tLeft' style='width:63px;' value='"+startDT+"' />\
												</div>\
											</div>\
										</td>\
										<td><div class=''></div></td>\
										<td>\
											<div class='tCenter'>[종료일시]</div>\
												<div>\
												<div class='tCenter mt3'>\
													<img src='/image/btn_cap_pre.gif' alt='' class='alg_md' onclick='calendar_two.goPrevMonth()'/>\
													<select style='width:50px;' id='e_calendarYearSelect' onChange='calendar_two.updateCalendar_two(2);'>";  
												for ( var i = beforeYear ; i >0; i--  ){
													html += "<option>" + (e_year - i) +"</option>";
												}
													html += "<option selected>" + e_yearAsString +"</option>";
												for ( var i = 1; i <= beforeYear; i++  ){
													html += "<option>" + (e_year + i) +"</option>";
												}
													html += "\
													</select>\
													<select style='width:37px;' id='e_calendarMonthSelect'  onChange='calendar_two.updateCalendar_two();'>";
												for ( var i = 1; i <= 12; i++) {
													if ( i == e_month ){
														html+="<option selected>" + e_monthAsString + "</option>";
													} else {
														if ( i < 10 ) {
															html+="<option>0" + i + "</option>" ;
														} else {
															html+="<option>" + i + "</option>" ;
														}
													}
												}
					html+="                    	</select>\
													<img src='/image/btn_cap_next.gif' alt='' class='alg_md' onclick='calendar_two.goNextMonth()' />\
												</div>\
												<div class='mt5'></div>\
												<div class='grid_calendar w160'>\
													<table class='calendar'>\
														<colgroup>\
															<col span='6' />\
														</colgroup>\
															<tr>\
																<th class='red' style='color:red;'>일</th>\
																<th>월</th>\
																<th>화</th>\
																<th>수</th>\
																<th>목</th>\
																<th>금</th>\
																<th class='blue'>토</th>\
															</tr>";
												
												var e_firstDate = getFirstDateOfMonth(this.selectedDate2);
												var e_lastDate = getLastDateOfMonth(this.selectedDate2);
												
												var e_firstDatePos = e_firstDate.getDay();
												var e_lastDatePos = e_firstDatePos + e_lastDate.getDate();
												
												e_cellCount = 42;
												
												this.rowCount = e_cellCount / 7; // 달력의 줄수
												
												var e_realDate;
												
												for(var d = 0; d < e_cellCount; ++d) {
												
													e_realDate = d - e_firstDatePos + 1;
													
													if(d % 7 == 0) {
													html += "<tr>";
													}
													
													if(d < e_firstDatePos || e_lastDatePos <= d) {
														html += "<td>&nbsp;</td>";
													} else {
														if (d % 7 == 0){
															html += "<td class='red' "; 
														} else if (d % 7 ==  6){
															html += "<td class='blue' "; 
														} else {
															html += "<td ";
														}
													html += "onclick='calendar_two.selectDate2(2,"+ e_realDate + ")'><div class='note_s'><i>"+e_realDate+"</i></div></td>"; 
													
													}
													
													html += "</td>";
													
													if(d % 7 == 6) {
														html += "</tr>";
													}
												}
												
												html += "</table>\
												</div>\
												<div class='tCenter mt5'>\
													<input type='text' name='end_dt' id='end_dt' class='input tLeft' style='width:63px;' value='"+endDT+"' />\
												</div>\
											</div>\
										</td>\
										<td>&nbsp;</td>\
									</tr>";
												
									html +=	 "\
									</table>\
									<div class='btn_area pdb10'>\
										<div class='btn_pop mt5'>\
										<button class='blue2' onclick='calendar_two.setDate();'>확인</button>\
										<button class='gray2 bbtn' onclick='calendar_two.hide(); calendar=null;'>취소</button>\
									</div>\
								</div>\
							</div></div></div></div></div>";
	
	return html;
},

/**
* 선택한 날짜 폼데이터에 세팅
*/
setDate: function(date) {

	/* 전체기간이 있을 경우에 체크를 빼고 enable 시킴 */
	if (typeof $('input:checkbox[name=checkTerm]').val()!= 'undefined') {
	
		if($('input:checkbox[name=checkTerm]').val()!=""){
			$('input:checkbox[name=checkTerm]').prop("checked", false);
			$('#startDate').prop("disabled", false);
			$('#endDate').prop("disabled", false);			
		}
	}
	
	var s_date = this.startSelecDate;
	var e_date = this.endSelecDate;

//	var y = this.selectedDate.getFullYear();
//	var m = (((this.selectedDate.getMonth() + 1) < 10) ? "0" : "") + (this.selectedDate.getMonth() + 1);
//	var d = ((date < 10) ? "0" : "") + date;
//	
//	var dateString = y + dateDelimiter + m + dateDelimiter + d;
//	
	if(this.target) {
		if(s_date != null && s_date != ""){
			$("#startDate").val(s_date);
		}
		if(e_date != null && e_date != ""){
			$("#endDate").val(e_date);
		}
		//this.target.value = dateString;
	}
//	
//	if(this.callback) {
//		this.callback(this.target, dateString, y, m, d);
//	}
//	
	this.hide();
	
	calendar = null;
},

/**
* 날짜 선택 이벤트 핸들러
*/
selectDate2: function(type, date) {

	if(type == 1){
		var y = this.selectedDate.getFullYear();
		var m = (((this.selectedDate.getMonth() + 1) < 10) ? "0" : "") + (this.selectedDate.getMonth() + 1);
		var d = ((date < 10) ? "0" : "") + date;
		
		var dateString = y + dateDelimiter + m + dateDelimiter + d;
		
		$('#start_dt').val(dateString);
		this.startSelecDate = dateString;
	}else if(type == 2){
		var y = this.selectedDate2.getFullYear();
		var m = (((this.selectedDate2.getMonth() + 1) < 10) ? "0" : "") + (this.selectedDate2.getMonth() + 1);
		var d = ((date < 10) ? "0" : "") + date;
		
		var dateString = y + dateDelimiter + m + dateDelimiter + d;
		
		$('#end_dt').val(dateString);
		this.endSelecDate = dateString;
	}else{
		$('#start_dt').val("");
		$('#end_dt').val("");
	}
},

/**
* 달력 숨기기
*/
hide: function() {
	this.popup.hide();
	this.popup.remove();
	// ${"#layer").hide();
	//${"#layer").remove();
},

/**
* 다음 달 보기
*/
goNextMonth: function(type) {
	if(!this.popup) {
	return;
	}

	if(type == 1){
		this.selectedDate = getNextMonth(this.selectedDate);
	}else{
		this.selectedDate2 = getNextMonth(this.selectedDate2);
	}
	
	this.hide();
	this.show();
},

/**
* 지난 달 보기
*/
goPrevMonth: function(type) {
	if(!this.popup) {
		return;
	}
	
	if(type == 1){
		this.selectedDate = getPrevMonth(this.selectedDate);
	}else{
		this.selectedDate2 = getPrevMonth(this.selectedDate2);
	}
	
	this.hide();
	this.show();
}, 

updateCalendar_two: function  (type) {
	if(!this.popup) {
		return;
	}
	
	var year;
	var month;
	var dateString;
	
	if(type == 1){
		year = $("#s_calendarYearSelect").val();
		month = $("#s_calendarMonthSelect").val();
		dateString = year+dateDelimiter+month+".01";
		this.selectedDate = stringToDate(dateString);
	}else{
		year = $("#e_calendarYearSelect").val();
		month = $("#e_calendarMonthSelect").val();
		dateString = year+dateDelimiter+month+".01";
		this.selectedDate2 = stringToDate(dateString);
	}
	
	this.hide();
	this.show();
},

locatePopup :function (){
	var pos = $("#"+this.target).offset();
	this.popup.css({
	top : pos.top+$("#"+this.target).height()-80,
	left: pos.left,
	zIndex: 9999999
	});
}


}

$(function() {
	marker = $('<span><img id="hdStartDate_two" style="vertical-align:middle;" src="/image/icon_calendar.gif"/></span>').insertBefore("#hdStartDate_two")
	marker.click(function(e){
		openCalendar_two("startDate", $("#startDate").val(),null);
	});
	
	marker = $('<span><img id="hdEndDate_two" style="vertical-align:middle;" src="/image/icon_calendar.gif"/></span>').insertBefore("#hdEndDate_two")
	marker.click(function(e){
		openCalendar_two("endDate", $("#endDate").val(),null);
	});
//	$("#hdStartDate").datepicker({ 
//        buttonImage: '../image/icon_calendar.gif', 
//        buttonImageOnly: true,
//        changeMonth: true,
//		changeYear: true,
//		dateFormat: 'yy-mm-dd',
//		showOn: 'button',
//		buttonText: "달력",
//		constranInput: true,
//		duration: 100,
//		onSelect:function(date) { $("#startDate").val(date); }
//     });
//	
//	$("#hdEndDate").datepicker({ 
//        buttonImage: '../image/icon_calendar.gif', 
//        buttonImageOnly: true,
//        changeMonth: true,
//		changeYear: true,
//		dateFormat: 'yy-mm-dd',
//		showOn: 'button',
//		buttonText: "달력",
//		constranInput: true,
//		duration: 100,
//		onSelect:function(date) { $("#endDate").val(date); }
//     });
	
});