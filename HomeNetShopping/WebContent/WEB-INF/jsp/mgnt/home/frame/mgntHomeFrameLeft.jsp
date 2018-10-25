<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/mgnt/common/include/incHeader.jspf" %>

<script type="text/javascript" src="<ctag:conf key="JS.PATH" />site/frame/frame_menu_top.js"></script>
<link href="/html/script/css/common_styles.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">
    _PATH_LEFT = "<c:url value='/mgnt/home/menuLeftAction.do' />";
    _PATH_MAIN = "<c:url value='/mgnt/home/menuBodyAction.do' />";
    var prevObj;
    var menuSelYn = "<c:out value='${param.menuSelYn}' />";
    
    $(document).ready(function() {
    	fnLeftDraw();

		
	});
    
    function fnLeftDraw() {
    	var currMenuNo = "<c:out value='${param.currentMenuNo}' />" ;
        $.ajax(
            {
                async : false,
                type : 'POST',
                url : '<c:url value="/mgnt/home/menuLeftAction.ajax" />?currentMenuNo='+currMenuNo,             
                dataType : "json",
                success : function(data) {
                	var row = data.row;
                    var cnt = 0;
                    
                    if ( row != null ) {
                    	cnt = data.row.length;
                    }
                    
                    var leftMenuDiv = "";
                    for(var i = 0; i < cnt; i++) {
                        try {
                            if(data.row[i].lvl == "1") {
                                document.getElementById("top_menu_nm").innerHTML = data.row[i].menuNm;
                                continue;
                            }
                            if(data.row[i].lvl == "2") {
                                var plag = 0;
                                
                                if((i+1) < cnt) {
                                    if(data.row[i + 1].lvl == "2") {
                                        plag = 1;
                                    }
                                }
                                var progrmUrl = data.row[i].optrPgmUrlV;
                                var fnCn = "" ;
                                if(plag == 0) {
                                	if(progrmUrl != null && progrmUrl != "" ) {
                                		leftMenuDiv += "<li class=\"leftMenu\"><a href=\"#\" onclick=\"fnLeftClass(this);fnMenuMove('" + data.row[i].menuNm.replace(/ /gi, '&nbsp;') + "','" + progrmUrl + "');\"><div class=\"menu-title-bar\"><div class=\"menu-title\">" + data.row[i].menuNm + "</div><div class=\"menu-list-icon\"></div></div></a><ul class=\"sub-menu\">";
                                    }else{
                                    	leftMenuDiv += "<li class=\"leftMenu\"><a href=\"#\" onclick=\"fnLeftClass(this);\"><div class=\"menu-title-bar\"><div class=\"menu-title\">" + data.row[i].menuNm + "</div><div class=\"menu-list-icon\"></div></div></a><ul class=\"sub-menu\">";
                                    }
                                } else {
                                	if(progrmUrl != null && progrmUrl != "" ) {
                                		leftMenuDiv += "<li class=\"leftMenu\"><a href=\"#\" onclick=\"fnLeftClass(this);fnMenuMove('" + data.row[i].menuNm.replace(/ /gi, '&nbsp;') + "','" + progrmUrl + "');\"><div class=\"menu-title-bar\"><div class=\"menu-title\">" + data.row[i].menuNm + "</div><div class=\"menu-list-icon\"></div></div></a></li>";
                                	}else{
                                		leftMenuDiv += "<li class=\"leftMenu\"><a href=\"#\" onclick=\"fnLeftClass(this);\"><div class=\"menu-title-bar\"><div class=\"menu-title\">" + data.row[i].menuNm + "</div><div class=\"menu-list-icon\"></div></div></a></li>";
                                	}
                                    
                                }
                            } else {
                                var progrmUrl = data.row[i].optrPgmUrlV;

                                leftMenuDiv += "<li class=\"subMenu\"><a href=\"#\" onclick=\"fnMenuMove('" + data.row[i].menuNm.replace(/ /gi, '&nbsp;') + "','" + progrmUrl + "')\"><div class=\"menu-title-bar\"><span>•</span> " + data.row[i].menuNm + "</div></a></li>";                          
                                if((i + 1) == cnt || data.row[i + 1].lvl == "2") {
                                    leftMenuDiv += "</ul></li>";
                                }
                            }
                        } catch(e) {
                            leftMenuDiv = leftMenuDiv;
                        }
                    }
                    
                    
                    document.getElementById("ekpLeftMenu").innerHTML = leftMenuDiv;
                },
                error : function(data, textStatus, errorThrown) {
                    alert("서버에 통신 중에 에러가 발생하였습니다.");
                    return false;
                }
            });
    }
    function fnLeftClass(obj) {
        if($(obj).parent().hasClass('leftMenu')) {
            if($(obj).parent().hasClass('active')) {
                $(obj).parent().removeClass('active');
            } else {
                $('.leftMenu').removeClass('active');
                $(obj).parent().addClass('active');
            }
        }
    }
    function fnMenuMove(menuNm, url) {
    	parent.document.getElementById("bodyFrame").src = url ;
    }
</script>

<body>

<div id="con_left" class="mt29">

	<div class="left_list">
		<ul id="leftMenu">
			<li class="tit" id="top_menu_nm"></li>
	        <div class="menu-list menu-show">
	            <ul class="main-menu" id="ekpLeftMenu">
	            </ul>
	        </div>
			
			
			<%-- 
			<c:forEach items="${list}" var="item" begin="1" varStatus="status">
			</c:forEach>
			
			
			
			<c:set var="ulYn" value="N" />
			
            <c:forEach items="${list}" var="item" begin="1" varStatus="status">
            	<c:if test="${item.lastYn eq 'N' and item.lvl eq '1' and ulYn eq 'Y'}">
            			</ul>
            		</li>
            		<c:set var="ulYn" value="N" />
            	</c:if>
            	<li id="leftMenu_<c:out value="${item.menuNo}" />">
            		<a href="<c:out value="${item.optrPgmUrlV}" />" target="bodyFrame" class="off"><c:out value="${item.menuNm}" /></a>
            	<c:choose>
	            	<c:when test="${item.lastYn eq 'Y'and item.lvl eq '1' and ulYn eq 'N'}">
	            		<ul>
	            		<c:set var="ulYn" value="Y" />
	            	</c:when>
	            	<c:otherwise>
		            	</li>
	            	</c:otherwise>
            	</c:choose>
            </c:forEach>
            
            <li id="leftMenu_99"><a href="<c:url value="/mgnt/brd/notice/notice.do" />" target="bodyFrame" class="off">Notice</a></li>
            <li id="leftMenu_99"><a href="<c:url value="/mgnt/brd/faq/faq.do" />" target="bodyFrame" class="off">FAQ</a></li>
            <li id="leftMenu_99"><a href="<c:url value="/mgnt/brd/qa/qa.do" />" target="bodyFrame" class="off">QNA</a></li>
            <li id="leftMenu_99"><a href="<c:url value="/mgnt/recu/recruit/recruitList.do" />" target="bodyFrame" class="off">채용정보관리</a></li>
            --%>
		</ul>
	</div>
	
</div>

</body>
</html>
