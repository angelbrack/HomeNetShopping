<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctag" uri="/WEB-INF/tld/ctag.tld" %>
<html>
<head>
<script type="text/javascript" src="<ctag:conf key="JS.PATH" />jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<ctag:conf key="JS.PATH" />jquery/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxtree.css">
<script src="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxcommon.js"></script>
<script src="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxtree.js"></script>
<script src="<ctag:conf key="JS.PATH" />dhtmlxtree/dhtmlxtree_start.js"></script>
</head>
<body>

<div id="treeboxbox_tree" setImagePath="/html/script/css/images/dhtmlxtree/" xclass="dhtmlxTree"
	style="width: 250px; height: 218px; background-color: #f5f5f5; border: 1px solid Silver;">
	<ul>
		<li id="treeRoot">Root
		<ul>
			<li id="C1">Child1
			<ul>
				<li id="C1-1">Child 1-1</li>
			</ul>
			</li>
			<li id="C2">Child2</li>
			<li id="C3"><b>Bold</b> <i>Italic</i></li>
		</ul>
		</li>
	</ul>
</div>
<script>
	var tree = dhtmlXTreeFromHTML("treeboxbox_tree"); // for script conversion
	tree.setOnClickHandler(function(id) {
		alert("Click:"+id);
	});	
</script>


</body>
</html>