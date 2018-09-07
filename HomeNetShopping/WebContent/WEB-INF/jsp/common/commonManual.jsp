<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>
<script type="text/javascript">
$().ready(function() {
	if("<c:out value='${fn:length(fileList)}'/>" != 0 ) {
		<c:forEach var='fileList' items='${fileList}' varStatus='status'>
			var fileListHtml = "";	
			var fileListName = "<c:out value='${fileList.atchFileNm}'/>";
			var realFileName= "<c:out value='${fileList.atchFileSaveNm}'/>";
			
			<c:if test="${status.index ne 0}">
			fileListHtml += "	<br />";
			</c:if>
			fileListHtml += " <img width=\"100%\" height=\"100%\" src=\"/upload?pathkey=MANUAL.IMAGE&getthumb="+realFileName+"&realFileName="+encodeURI(encodeURIComponent(fileListName))+" alt=\""+encodeURI(encodeURIComponent(fileListName))+"\" />";
			
			$("#uploaded-files").append(fileListHtml);
		</c:forEach>
	}else {
		var fileListHtml = "";
		fileListHtml = "<img width=\"100%\" height=\"100%\" src=\"<ctag:conf key='THEME.PATH' />portal/images/con_noimgage.jpg\" />";
		$("#uploaded-files").append(fileListHtml);
	}
});
</script>
<body>

<div id="wrap_pop">
<form name="form1" id="form1" method="post" action="">
	
	<div class="pop_con_top">
		<div class="pop_con_top_tit">
			[<c:out value="${info.optrPgmNm}"/>] 메뉴얼
		</div>
		<div class="pop_con_top_close">
			<a href="#none" onclick="self.close();" class="btn_close_01"></a>
		</div>
	</div>

	<div id="pop_con">
		<div class="pop_con_c">

			<!--내용-->
			<div class="con_search mt24">
				<div id="uploaded-files"></div>
			</div>

			<div class="con_btn mt30">
				<a href="#none" onclick="self.close();" class="btn_list1"><spring:message code="BTN.CLOSE" /></a>
			</div>
			<!--내용-->

		</div>
	</div>
</form>
</div>

</body>
</html>
