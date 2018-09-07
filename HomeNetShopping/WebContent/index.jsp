<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="prjframework.common.util.WebUtil"%>
<%--
	String fileName = "고려대관리자 메뉴구조도 - 복사본.xlsx";
	out.println("fileName_1=["+WebUtil.encodeURIComponent(fileName)+"]");
--%>

<%-- <jsp:forward page="/portal/home/mainIntroAction.do" />--%>

<a href="/mgnt/" target="_self">관리자</a> <br/> <br/>


<a href="/user/index/home/homeMain.do" target="_self">사용자메인</a> <br/><br/>

<a href="/mobile/index/home/homeMain.do" target="_self">모바일메인</a> <br/><br/>


<a href="/user/login/login/initLogin.do" target="_self">사용자로그인</a><br/><br/>


<a href="/design/index.jsp" target="_self">사용자디자인</a> <br/><br/>

<a href="/design/mobile/www/apps/main/default.jsp" target="_self">모바일디자인</a> <br/><br/>


SSO연계 <br/><br/>

 <br/>