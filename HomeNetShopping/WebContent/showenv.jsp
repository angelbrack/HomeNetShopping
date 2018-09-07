<%@page import="java.util.Date"%>
<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<html>
<HEAD><TITLE>Show status</TITLE></HEAD>
<BODY>

<h1>My ip</h1>
<HR />
<pre>
You are IP : <%=request.getRemoteAddr() %><br/>
server IP : <%=InetAddress.getLocalHost().getHostAddress() %><br/>
hostname : <%=InetAddress.getLocalHost().getHostName() %><br/>
</pre>
<br/>

<h1>My session</h1>
<HR />
<pre>
<%
	out.println("Session ID : "+session.getId() + "<br>");
    out.println("Session Creation Time : "+new Date(session.getCreationTime()) + "<br>");
    out.println("Session Last Access Time : "+new Date(session.getLastAccessedTime()) + "<br>");
	out.println("Session TimeOut : "+session.getMaxInactiveInterval() + "<br>");
    out.println("Session is new? "+session.isNew() + "<br>");
%>
</pre>
<br/>

<h1>WAS  env</h1>
<HR />
<pre>
<%
java.util.Properties prop = System.getProperties();
java.util.Enumeration key = prop.keys();
Object obj = null;
while(key.hasMoreElements()){
	obj = key.nextElement();
	out.print(obj +" = ");
	out.println(prop.get(obj));
	out.println("<br />");
}
%>
</pre>
</BODY>
</html>