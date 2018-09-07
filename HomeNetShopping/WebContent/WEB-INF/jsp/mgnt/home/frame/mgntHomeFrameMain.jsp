<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>

<frameset rows="109,*" frameborder="no" border="0">
<frame id="topFrame" src="/mgnt/home/menuTopAction.do" name="topFrame" scrolling="no" noresize="noresize" marginwidth="0" marginwidth="0" marginheight="0" />
	<frameset id="mainFrame" cols="20,*" frameborder="no" border="0">
		<frame id="leftFrame" src="/mgnt/home/menuLeftAction.do" name="leftFrame" scrolling="no" noresize="noresize" marginwidth="0" marginheight="0" />
		<frame id="bodyFrame" src="/mgnt/home/menuBodyAction.do" name="bodyFrame" scrolling="auto" marginwidth="0" marginwidth="0" marginheight="0" />
	</frameset>

<noframes>
   <body>
       <p>이 페이지를 보려면, 프레임을 볼 수 있는 브라우저가 필요합니다.</p>
   </body>
</noframes>

</frameset>

</html>
