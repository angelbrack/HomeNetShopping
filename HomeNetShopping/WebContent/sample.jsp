<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<iframe id="pIframe" src="http://localhost:8080/sample/sampleView" style="width: 800px; height: 300px;" scrolling="no"></iframe>

<script>
window.addEventListener("message", receiveMessage, false);
function receiveMessage(e) {
	
	var data	= e.data;				// 전송되어진 메세지 객체
	var origin	= e.origin;				// 전송하는 메세지 주쇼(YT POC 주쇼)
	
	var height	= data.height;			// irame 높이

  	console.log("data=["+data+"]");
	console.log("origin=["+origin+"]");
	console.log("height=["+height+"]");
	
	if ( origin == "http://localhost:8080") {
		// 아이프레임 리사이즈.
		document.getElementById("pIframe").style.height = height+"px";
	} else {
		alert("허용가능한 도메인이 아닙니다.");
	}
}
</script>
</body>
</html>