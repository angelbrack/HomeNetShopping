/*
	팝업 윈도우를 다루는 기능 모음.
	url.js 와 cookie.js에 의존성 있음.
*/
function popupCall(fun, winNm, opt){
	var win = popupWindow("", winNm, opt);
	win.document.open();
	win.document.write("<html><body onload='opener."+fun+"()'></body></html>");
	win.document.close();
}

function popupWindow(theURL, winNm, opt){
	if(theURL){
		var dt = new Date();
		var s = dt.getTime();
		theURL = addParam(theURL, "_TOK", s);
		setCookie("THIS_POPUP", s, 1000*10, "/");
	}
	
	return window.open(theURL, winNm, opt); 
}

function popupWindowCheck(theURL, winNm, opt){
	var dt = new Date();
	var s = dt.getTime();
	theURL = addParam(theURL, "_TOK", s);
	setCookie("THIS_POPUP", s, 1000*10, "/");
	var viewerWindow = window.open(theURL, winNm, opt); 
	return viewerWindow;
}

function showModal(url, parm, w, h){
	var dt = new Date();
	var s = dt.getTime();
	url = addParam(url, "_TOK", s);
	setCookie("THIS_POPUP", s, 1000*10, "/");
	
	var opt = "dialogWidth="+w+"px; dialogHeight="+h+"px; ";
	opt += "center: Yes; help: No; edge: Raised; ";
	opt += "resizable: yes; status: yes; scrollerable: yes; ";
	return window.showModalDialog(url, parm, opt);
}

function downPage(url){
	window.open("/download.jsp?durl="+escape(url));
}

function downWindow(url){
	var win = window.open(url);
	win.onload = new function(){setInterval(new function(){self.close();},2000)}
}
