<%@page import="java.util.ArrayList"%>
<%@page import="api.WordApi"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String url = application.getContextPath() + "/";
	ArrayList<String> words = WordApi.crawler(request.getParameter("inputnumber"));
	request.setAttribute("words", words);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>World of Words</title>
</head>
<body>
	<div>
		<form action="wordsList.jsp" method="post">
	    	<input type="submit" class="w3-large w3-animate-top" value="FIND OUT"></input>
	    	<input type="text" name="inputnumber" value="10"/><br>
    	</form>
	</div>
	<br>
	<br>
	<br>
	<center>
		<h3>word list</h3>
		<hr>
			<c:forEach items="${requestScope.words}" var="word">
				<h1 class="w3-middle w3-animate-top">${word}</h1>
			</c:forEach>
		<br>
		<br>
		<br>
		<a href="index.html">메인 화면 이동</a>
	</center>
</body>
</html>