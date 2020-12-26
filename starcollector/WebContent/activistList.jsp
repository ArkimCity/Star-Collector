﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String url = application.getContextPath() + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>모든 Activist list 화면</title>
</head>
<body>
	<br>
	<br>
	<br>
	<center>
		<h3>재능 기부자 list</h3>
		<hr>
		<p>
		<table border="1">
			<thead>
				<tr>
					<th>기부자 id</th>
					<th>기부자 이름</th>
					<th>주요 분야</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.activistAll}" var="activist">
				<tr>
					<td><button onclick="location.href='probono?command=activist&activistId=${activist.activistId}'">${activist.activistId}</button></td>
					<td>${activist.name}</td>
					<td>${activist.major}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<br> <font color="blue">재능 기부자 id를 클릭하면 상세 정보 확인이 가능합니다</font> <a href="index.html">메인 화면 이동</a>
	</center>
</body>
</html>