<%@page import="java.util.ArrayList"%>
<%@page import="api.WordApi"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String url = application.getContextPath() + "/";
	ArrayList<String> words = WordApi.crawler(10);
	request.setAttribute("words", words);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>World of Words</title>
</head>
<body>
	<br>
	<br>
	<br>
	<center>
		<h3>word list</h3>
		<hr>
		<p>
		<table border="1">
			<thead>
				<tr>
					<th>�ܾ�</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.words}" var="word">
				<tr>
					<td>${word}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<br> <font color="blue">��� ����� id�� Ŭ���ϸ� �� ���� Ȯ���� �����մϴ�</font> <a href="index.html">���� ȭ�� �̵�</a>
	</center>
</body>
</html>