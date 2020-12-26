<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String url = application.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>모든 Probono Project list 화면</title>
</head>
<body>
	<br>
	<br>
	<br>
	<center>
		<h3>재능 기부 프로젝트 list</h3>
		<hr>
		<p>
		<table border="1">
			<tr>
				<th>프로젝트 id</th>
				<th>프로젝트명</th>
				<th>재능기부자 id</th>
				<th>재능 수혜자 id</th>
				<th>재능기부 내용</th>
			</tr>
			<c:forEach items="${requestScope.probonoProjectAll}" var="pp">
				<tr>
					<td>${pp.probonoProjectId}</td>
					<td>${pp.probonoProjectName}</td>
					<td><button onclick="location.href='probono?command=activist&activistId=${pp.activistId.activistId}'">${pp.activistId.activistId}</button></td>
					<td><button onclick="location.href='probono?command=recipient&recipientId=${pp.recipientId.recipientId}'">${pp.recipientId.recipientId}</button></td>
					<td>${pp.projectContent}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<br> <font color="blue">재능 기부자 id 및 재능 수혜자 id를 클릭하면 상세 정보
			확인이 가능합니다</font> <a href="index.html">메인 화면 이동</a>
	</center>
</body>
</html>