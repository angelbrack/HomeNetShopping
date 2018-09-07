
/*********************************************************
 * checkbox 전체 선택/해지
 *
 * 전체 선택 checkbox name, id 값은 _checkCheckbox
 * 선택 checkbox name, id 값은 _selectCheckbox
 *
 ******************************************************** */
function fnSelectedAllCheckbox(_checkCheckbox, _selectCheckbox) {
	$("input[name="+_selectCheckbox+"]")
		.attr("checked", $("input[name="+_checkCheckbox+"]").is(":checked"));
}

/**
 * 쿠키 정보 조회
 * @param name : 쿠키명
 * @returns
 */
function getCookie(name) {
	var dc = document.cookie;
	var prefix = name + "=";
	var begin = dc.indexOf("; " + prefix);
	if (begin == -1) {
		begin = dc.indexOf(prefix);
		if (begin != 0) {
			return null;
		}
	} else {
		begin += 2;
	}
	var end = document.cookie.indexOf(";", begin);
	if (end == -1){
		end = dc.length;
	}
	return unescape(dc.substring(begin + prefix.length, end));
}

/*********************************************************
 * URL에 파라미터 정보를 추가하여 반환
 * @param theURL : URL
 * @param nm	 : 파라미터명
 * @param val	 : 파라미터값
 * @returns
 ******************************************************** */
function addParam(theURL, nm, val) {
	var ap = 0;
	var param = (nm+"="+escape(val));
    if((ap = theURL.indexOf("?")) != -1){
        theURL += ("&"+param);
    } else {
    	theURL += ("?"+param);
    }
	return theURL;
}
/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
var _URL_PAGINATION = "";
function fnPage(pageNo) {

	var _FRM = document.forms[0];
	_FRM.pageIndex.value = pageNo;
	_FRM.target = "_self";
    _FRM.action = _URL_PAGINATION;
    _FRM.submit();

}
/**
 * 쿠키 값 설정
 * @param nm	: 쿠키명
 * @param val	: 쿠키값
 * @param maxAge	: 유효기간
 * @param path	: 유효범위
 * @param domin	: 도메인
 */
function setCookie(nm, val, maxAge,path,domin){
	var cok = nm+"="+escape(val);
	if(maxAge) {
		var dt = new Date();
		dt.setTime(dt.getTime()+maxAge);
		cok += "; expires="+dt.toDateString();
	}
	if(path) {
		cok += "; path="+path;
	}
	if(domin) {
		cok += "; domain="+domin;
	}
	document.cookie = (cok+";");
}

/**
 * 팝업창을 띄운다.
 * @param theURL : URL
 * @param winNm  : 윈도우명
 * @param opt    : 옵션설정값
 */
function popupWindow(theURL, winNm, opt){

	var dt = new Date();
	var s = dt.getTime();
	if(theURL == ''){
		theURL = '/getAboutBlank.do';
	}else if(theURL == null){
		theURL = '/getAboutBlank.do';
	}

	theURL = addParam(theURL, "_TOK", s);
	setCookie("THIS_POPUP", s, 1000*10, "/");
	window.open(theURL, winNm, opt);
}

/**
 * 팝업 리사이징
 *
 * (ex.) window.onload = function(){popupAutoResize(500,'');}
*/
function setWindowResize(width, height) {
	var thisX = width == '' || width == null ? parseInt(document.body.scrollWidth): width;
	var thisY = height == '' || height == null ? parseInt(document.body.scrollHeight): height;
	var maxThisX = screen.width - 50;
	var maxThisY = screen.height - 50;
	var marginY = 0;
	// alert(thisX + "===" + thisY);
	// alert!("임시 브라우저 확인 : " + navigator.userAgent);
	// 브라우저별 높이 조절. (표준 창 하에서 조절해 주십시오.)
	if (navigator.userAgent.indexOf("MSIE 6") > 0)
		marginY = 45; // IE 6.x
	else if (navigator.userAgent.indexOf("MSIE 7") > 0)
		marginY = 75; // IE 7.x
	else if (navigator.userAgent.indexOf("MSIE 9") > 0)
		marginY = 90; // IE 9.x
	else if (navigator.userAgent.indexOf("Firefox") > 0)
		marginY = 50; // FF
	else if (navigator.userAgent.indexOf("Opera") > 0)
		marginY = 30; // Opera
	else if (navigator.userAgent.indexOf("Chrome") > 0)
		marginY = 70; // Chrome
	else if (navigator.userAgent.indexOf("Netscape") > 0)
		marginY = -2; // Netscape

	if (thisX > maxThisX) {
		window.document.body.scroll = "yes";
		thisX = maxThisX;
	}
	if (thisY > maxThisY - marginY) {
		window.document.body.scroll = "yes";
		thisX += 19;
		thisY = maxThisY - marginY;
	}
	window.resizeTo(thisX, thisY + marginY);

	/*
	 * 팝업 위치 정중으로 이동 var windowX = (screen.width - (thisX+10))/2; var windowY =
	 * (screen.height - (thisY+marginY))/2 - 20; window.moveTo(windowX,windowY);
	 */
}

/**
 * 라디오, 체크박스의 체크된 컨트롤 수 얻기
 * @param objName : 오브젝트 이름
 * @returns : 체크상태의 컨트롤 수
 */
// 라디오, 체크박스의 체크된 컨트롤 수 얻기
function getCheckedCount(objName)
{
	var i;
	var obj = new Array();
	var objCnt;
	var checkedCnt = 0;

	obj = document.getElementsByName(objName);
	objCnt = obj.length;

	for (i = 0; i < objCnt; i++)
	{
		if (obj[i].checked == true)
		{
			checkedCnt = checkedCnt + 1;
		}
	}

	return checkedCnt;
}
/**
 * 검색어 텍스트 select, focus 처리
 * @param searchWordName : 오브젝트 ID
 * @returns :
 */
function fnSetSearchWordFocus(searchWordName)
{
	var objSearchWord	= document.getElementById(searchWordName);
	objSearchWord.focus();
	objSearchWord.select();
}

/**
 * 자바스크립트에서 replaceAll효과를 갖는다
 * ex) string.replaceAll(대상문자열, 치환문자)
 * @param
 * @returns :
 */
String.prototype.replaceAll = function (str1,str2){
	var r = this;var a = r.split(str1);for(var i=0;i<a.length;i++){r = r.replace(str1,str2);}return r;
};

/**
 * RichEdit2의 컨텐츠 영역과 대상 textarea와 데이터를 동기화 후 HTML을 제외한 text가 비어있는지를 반환한다. (true, false)
 * !text가 비어있을 경우 edit영역으로 포커스를 이동
 * if(syncContent(document.form.bdltCn)){alert("내용을 입력해주세요.")};
 * @param
 * @returns :
 */
function syncContent(obj){
	var sync = obj.getContent();
	obj.value = obj.value.replaceAll('<p> </p>','<p>&nbsp;</p>');
	if(sync.isEmptyTxt)obj.focusContent();
	return sync.isEmptyTxt;
}

/**
 * Ajax
 * @param url,콜백함수명
 * @param returnFunction
 * 데이터만 return 해주다
 */
function lmsAjax(url, returnFunction){

	jQuery.ajax({
		type: 'post'
		, url: _CONTEXT_ROOT + url
		, contentType: 'application/x-wwwform-urlencoded; charset=utf-8'
		, dataType: 'json'
		, success: function(data) {
			if (data != null) {
				eval(returnFunction + '(data)');
			} else {
				alert('NULL');
				return false;
			}
		}
		, error: function(data, textStatus, errorThrown) {
			alert('ERROR');
		}
	});
}

function getByteLen(str)
{
	return (str.length + (escape(str) + "%u").match(/%u/g).length -1);
}



function fnLTrim( value ){

	if(value==null || value=="") return "";

	var length = value.length;

	var i;
	for(i=0;i<length;i++){
		if(value.charAt(i) != " ") break;
	}
	return value.substring(i);
}


/**
 * 오른쪽 공백을 없애 준다
 * @param value   : 값
 * @return 오른쪽 공백을 없앤 값 , value가 null 이거나 "" 이면 ""을 return
 */

function fnRTrim( value ){
	if(value==null || value=="") return "";

	var length = value.length;

	var i;
	for(i=length-1;i>=0;i--){
		if(value.charAt(i) != " ") break;
	}
	return value.substring(0,i+1);
}


/**
 * 왼쪽/오른쪽 공백을 없애 준다
 * @param value   : 값
 * @return 공백을 없앤 값 , value가 null 이거나 "" 이면 ""을 return
 */

function fnTrim( value ){
	return fnRTrim(fnLTrim(value));
}

/*********************************************************
 * checkbox 전체 선택/해지
 *
 * 전체 선택 checkbox name, id 값은 _checkCheckbox
 * 선택 checkbox name, id 값은 _selectCheckbox
 * Disabled 된 checkbox 는 제외
 *
 ******************************************************** */
function fnCheckboxNotDisabled(_checkCheckbox, _selectCheckbox) {
	if($("input:checkbox[name="+_checkCheckbox+"]").is(":checked")){
		$("input:checkbox[name="+_selectCheckbox+"]").each(function(index){
			if(!$(this).attr("disabled")){
				$(this).attr("checked", "checked");
			}
		});
	}
	else{
		$("input:checkbox[name="+_selectCheckbox+"]").removeAttr("checked");
	}
}

/*********************************************************
 * 체크박스 목록에서 하나 이상 체크 여부
 *
 * @param objName : 오브젝트 이름
 * @returns : 하나 이상 체크시 true , 아닐경우 false
 * fnIsChecked('checkValue')
 *
 ******************************************************** */
function fnIsChecked(objName) {
	var obj = document.getElementsByName(objName);
	for (var i = 0; i < obj.length; i++)
		if (obj[i].checked == true)
			return true;
	return false;
}

/**
 * 객체 길이 제한 체크 및 substring
 * 1. 객체의 값 제한을 체크함.
 * 2. 초과하는 글자는 뒤에서부터 잘라냄.
 * 3. 초과시 알림 메시지를 호출하고 false 를 반환함.
 * 4. 정상시 true 반환함.
 * true : 정상 , false : 초과
 * @param
 */
function limitCheck(obj,limit,title) {
	var str = obj.value;
	if (str == null || str.length == 0) {
		return true;
	}

	if(stringByteSize(str) > limit){
		alert(title+fnMessageArguments(LBL_LIMIT_CHECK_ERROR,limit));
		obj.value = substr_utf8_bytes(str,0,limit);
		obj.focus();
		return false;
	}
	return true;
}

/**
 * Byte 길이만큼 글자수 자르기
 * @param str
 * @param startInBytes
 * @param lengthInBytes
 * @returns {String}
 */
function substr_utf8_bytes(str, startInBytes, lengthInBytes) {

    var resultStr = '';
    var startInChars = 0;

    for (var bytePos = 0; bytePos < startInBytes; startInChars++) {
        ch = str.charCodeAt(startInChars);
        bytePos += (ch < 256) ? 1 : encode_utf8(str[startInChars]).length;
    }
    end = startInChars + lengthInBytes - 1;

    for (var n = startInChars; startInChars <= end; n++) {
        ch = str.charCodeAt(n);
        end -= (ch < 256) ? 1 : encode_utf8(str[n]).length;

        resultStr += str[n];
    }

    return resultStr;
}

function encode_utf8( str ){
  return unescape( encodeURIComponent( str ) );
}

/**
 * UTF-8 문자열의 Byte 크기 구하기
 * @param ch
 * @returns {Number}
 */
function charByteSize(ch) {
	if (ch == null || ch.length == 0) {
		return 0;
	}
	var charCode = ch.charCodeAt(0);
	if (charCode <= 0x00007F) {
		return 1;
	} else if (charCode <= 0x0007FF) {
		return 2;
	} else if (charCode <= 0x00FFFF) {
		return 3;
	} else {
		return 4;
	}
}

/**
 * 문자열을 UTF-8로 변환했을 경우 차지하게 되는 byte 수를 리턴한다.
 * @param str
 * @returns {Number}
 */
function stringByteSize(str) {
	if (str == null || str.length == 0) {
		return 0;
	}
	var size = 0;
	for (var i = 0; i < str.length; i++) {
		size += charByteSize(str.charAt(i));
	}
	return size;
}


/**
 * 빈값 체크
 * @param str
 * @returns {Boolean}
 */
function isEmpty(str){
	if( str == "undefined")
		return true;
	if( str == null)
		return true;
	str = $.trim(str);
	if( str.length == 0 )
		return true;
	return false;
}

/**
 * 빈값 체크
 * @param str
 * @returns {Boolean}
 */
function isNotEmpty(str){
	return (isEmpty(str)) ? false : true ;
}

/**
 * 빈값 체크
 * @param str
 * @returns {Boolean}
 */
function fixNull(str){
	if(isEmpty(str)){
		str = "";
	}
	return str;
}

/**
 * 시작일 / 종료일 비교 리턴.
 * 1. 시작일과 종료일이 동일한경우도 제외함.
 * @param sDt,eDt
 * @returns {Boolean}
 */
function isDateCompare(sDt, eDt){
	return isDateCompare(sDt, eDt, true);
}

/**
 * 시작일 / 종료일 비교 리턴.
 * 1. true : 시작일과 종료일이 동일한 경우 false;
 * @param sDt,eDt
 * @returns {Boolean}
 */
function isDateCompare(sDt, eDt, blEquals){
	var sttDt = Number(sDt.replace(/[^0-9]/g,''));
	var edDt = Number(eDt.replace(/[^0-9]/g,''));

	if(blEquals){
		if(sttDt >= edDt)
			return false;
		else
			return true;
	}
	else{
		if(sttDt > edDt)
			return false;
		else
			return true;
	}
}

/**
 * spring:message
 * @param msg
 * @param args
 * @returns
 */
function fnMessageArguments(msg, args){
    if(args) {
        if (typeof args == "object" && args.length) {
            for (var i = 0; i < args.length; i++) {
                var pattern = new RegExp("\\{" + i + "\\}", "g");
                msg = msg.replace(pattern, args[i]);
            }
        } else {
        	msg = msg.replace(/\{0\}/g, args);
        }
    }
    return msg;
}

/**
 * 숫자만 반환
 * @param obj
 * @returns
 */
function fnNumber(str){
	return Number(str.replace(/[^0-9]/g,''));
}

/**
 * 숫자만 반환
 * @param obj
 * @returns
 */
function fnNumberHtml(str){
	return Number(str.replace(/[^0-9]/g,''));
}

/**
 * 배열
 * @returns
 */
function ArrayList()
{
  this.array = new Array();
  this.add = function(obj){
    this.array[this.array.length] = obj;
  };
  this.length = function (){
    return this.array.length;
  };
  this.get = function (index){
    return this.array[index];
  };
  this.addAll = function (obj)
  {
    if (obj instanceof Array){
      for (var i=0;i<obj.length;i++)
      {
        this.add(obj[i]);
      }
    } else if (obj instanceof ArrayList){
      for (var i=0;i<obj.length();i++)
      {
        this.add(obj.get(i));
      }
    }
  };
  this.remove = function(index){
	  return (index<0 || index > this.array.length) ? this.array : this.array.slice(0, idx).concat(this.array.slice(idx+1, this.array.length));
  };
  this.clear = function(){
	  this.array = new Array();
  };
}

/**
 * Javascript Map 이용
 * @returns {Map}
 */
Map = function(){
	 this.map = new Object();
};

Map.prototype = {
    put : function(key, value){
        this.map[key] = value;
    },
    get : function(key){
    	if(this.size() == 0)
    		return '';
        return this.map[key];
    },
    containsKey : function(key){
     return key in this.map;
    },
    containsValue : function(value){
     for(var prop in this.map){
      if(this.map[prop] == value) return true;
     }
     return false;
    },
    isEmpty : function(){
     return (this.size() == 0);
    },
    clear : function(){
     for(var prop in this.map){
      delete this.map[prop];
     }
    },
    remove : function(key){
     delete this.map[key];
    },
    keys : function(){
        var keys = new Array();
        for(var prop in this.map){
            keys.push(prop);
        }
        return keys;
    },
    values : function(){
     var values = new Array();
        for(var prop in this.map){
         values.push(this.map[prop]);
        }
        return values;
    },
    size : function(){
      var count = 0;
      for (var prop in this.map) {
        count++;
      }
      return count;
    }
};