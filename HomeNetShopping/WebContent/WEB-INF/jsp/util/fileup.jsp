<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<html>
<head>
	<title>File Upload</title>
	<script src="/theme/00001/ko/portal/js/jquery-1.10.2.min.js"></script>
	<script src="/html/script/js/jquery/jquery.uploadify-3.1.min.js"></script>
</head>
<body>
<%

%>
<script type="text/javascript">

$(document).ready(function(){
		
		$('#uploadFile').uploadify({
			'swf': '/theme/00001/ko/support/uploadify/uploadify.swf',
			'formData':{'filepath':''},
			'uploader': '/theme/00001/ko/support/uploadify/uploadify.jsp',
			// Put your options here
			'auto' : false,
	        onUploadStart : function(){
	        	$("#uploadFile").uploadify("settings", "formData", {
	        		"filepath":$("#filepath").val()
	        		});
	   		},
		});//end uploadify
		
		$('#submit').click(function(){
			$('#uploadFile').uploadify('upload');
		});
});
</script>
<form>
<input type="text" id="filepath" name="filepath" value=""></input>
<input type="file" id="uploadFile">
<button type="button" id="submit">¾÷·Îµå</button>

</form>
<hr></hr>

</body>
</html>