/*
	쿠키를 다르는 기능 모음.
*/
function getCookie(name) {
  var dc = document.cookie;
  var prefix = name + "=";
  var begin = dc.indexOf("; " + prefix);
  if (begin == -1) {
    begin = dc.indexOf(prefix);
    if (begin != 0) return null;
  } else {
    begin += 2;
  }
  var end = document.cookie.indexOf(";", begin);
  if (end == -1){
    end = dc.length;
  }
  return unescape(dc.substring(begin + prefix.length, end));
}

function setCookie(nm, val, maxAge,path,domin){
	var cok = nm+"="+escape(val);
	if(maxAge) {
		var dt = new Date();
		dt.setTime(dt.getTime()+maxAge);
		cok += "; expires="+dt.toGMTString();
	}
	if(path) {
		cok += "; path="+path;
	}
	if(domin) {
		cok += "; domain="+domin;
	}
	document.cookie = (cok+";");
}

function deleteCookie(name, path, domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") +
    "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}