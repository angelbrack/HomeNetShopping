<%@page import="org.apache.commons.lang.time.StopWatch"%>
<%@page import="coperframe.common.util.StaticApplicationUtil"%>
<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@page import="java.net.URLDecoder"%>
<%@page import="coperframe.common.*"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.apache.commons.dbcp.BasicDataSource"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<html><head><title>DB TEST</title></head><body onload="form1.sql.focus();">
<%
Connection con=null;
Statement st=null;
ResultSet rs=null;
String sql=null;
String sqltp=null;
String db=null;
String dbtp=null;
ResultSetMetaData meta = null;
String msg="";
int rcnt = 0;
int mxCol = 0;
try {
		
	sql = request.getParameter("quy");
	if(sql != null){
		sql = URLDecoder.decode(sql,"utf-8") ;
	}
	
	sqltp = request.getParameter("sqltp");
	dbtp = request.getParameter("dbtp");
	db = request.getParameter("db");
	
	if("B".equals(dbtp)){
		BasicDataSource dbcp = (BasicDataSource)StaticApplicationUtil.getBean(db);
		con = dbcp.getConnection();
	} else if("D".equals(dbtp)){
		InitialContext initCtx = new InitialContext();
		DataSource ds = (DataSource) initCtx.lookup(db);
		con=ds.getConnection();
	} else if("C".equals(dbtp)){
		String[] args = db.split(",");
		
		if(args != null && args.length == 4){
			Class.forName(args[0]);
			con=DriverManager.getConnection(args[1], args[2], args[3]);
		} else {
			msg = "URL Sample : driver,url,id,password <br />";
		}
	} else {
		//defult setting
		db = "DataSource";
		dbtp = "B";
	}
%>
<%=msg %>
<script type="text/javascript">

var selectWarns = ["limit","rownum","top"];

function OnSubmitsql(obj){
	var v = form1.sql.value;
	
	if( !v){
		alert("Did not written query");
		return false;
	}
	
	//query 검사.
	if(form1.sqltp.value == "S"){
		var founds = false;
		for(var i=0;i<selectWarns.length;i++){
			
			if(v.indexOf(selectWarns[i]) != -1){
				founds = true;
				break;
			}
		}
		
		var warnMsg = "Do you execute this sql statement without limit?";
		warnMsg += "\nWarning!, long time no see this page";
		warnMsg += "\n(use : oracle=rownum or mysql=limit or mssql=top)";
		
		if(founds == false && confirm(warnMsg) == false){
			return false;
		}
	}
	
	form1.quy.value = encodeURIComponent(form1.sql.value);
	return true;
}

function OnKeyup(obj){
	if(event.keyCode == 13){
		if(event.ctrlKey == true){
			if(OnSubmitsql(form1)){
				form1.submit();
			}
		} else if(event.shiftKey == true){
			obj.sql.value='';
		}
	}
}

function OnKeydown(){
	if(event.ctrlKey == true && event.altKey == true){
		var t = form1.sqltp.value;
		form1.sqltp.value = t=='S'?'U':'S';
	}
}

</script>
<b>Ctrl+Enter:</b> sql submit, <b>Shift+Enter:</b> sql clear, <b>Ctrl+Alt:</b> toggle query mode
<form name="form1" method="post"  onsubmit="return OnSubmitsql(this);" 
	onkeyup="OnKeyup(this)" onkeydown="OnKeydown(this)" enctype="application/x-www-form-urlencoded">
<select name="dbtp">
	<option value="D" <%="D".equals(dbtp)?"selected":"" %>>DataSource</option>
	<option value="C" <%="C".equals(dbtp)?"selected":"" %>>URL</option>
	<option value="B" <%="B".equals(dbtp)?"selected":"" %>>DBCP</option>
</select>
<input type="text" name="db" size="80"  value="<%=db!=null?db:""%>" /> <br />
<input type="hidden" name="quy"  value="" />
<textarea  name="sql" rows="8" cols="100"><%=sql!=null?sql:"" %></textarea>
<br />
<select name="sqltp">
	<option value="S" <%="S".equals(sqltp)?"selected":"" %>>SELECT</option>
	<option value="U" <%="U".equals(sqltp)?"selected":"" %>>UPDATE</option>
</select>
<input type="submit"></input>

</form>
<hr></hr>
<%	
	//갱신요청.
	if(sql != null){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		st = con.createStatement();
		
		if("U".equals(sqltp)){
			rcnt = st.executeUpdate(sql);
			
			stopWatch.stop();
			
			out.print("DB elapsed time : "+stopWatch.toString());
			out.print("result :"+rcnt);
			con.commit();
		} else {
			rs = st.executeQuery(sql);
			
			stopWatch.split();
			out.print("DB elapsed time : "+stopWatch.toString());

			meta = rs.getMetaData();
			mxCol = meta.getColumnCount();
			
			out.print("<table border=1><tr><th>no</th>");
			for(int i=0;i<mxCol;i++){
				out.print("<th>");
				out.print(meta.getColumnName(i+1));
				out.print("</th>");
			}
			
			out.print("</tr>");
			while(rs.next()) {
				out.print("<tr><td>"+rs.getRow()+"</td>");
				
				for(int i=0;i<mxCol;i++){
					out.print("<td>");
					out.print(rs.getString(i+1));
					out.print("</td>");
				}
				out.print("<tr>");
			}
			out.print("</table>");
			
			stopWatch.stop();
			out.print("UI elapsed time : "+stopWatch.toString());
		}
		
	}//endif
}catch(Exception e) {
	out.print("Error!\n");
	out.println(e);
} finally{
	if(rs!=null)rs.close();
	if(st!=null)st.close();
	if(con!=null)con.close();
}
%>

</body>
</html>
