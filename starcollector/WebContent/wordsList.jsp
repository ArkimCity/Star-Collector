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
<body style="height: 100%;">

	<div>
		<form action="wordsList.jsp" method="post">
	    	<input type="submit" class="w3-large w3-animate-top" value="FIND OUT"></input>
	    	<input type="text" name="inputnumber" value="10"/><br>
    	</form>
	</div>
	
	<div class="w3-twothird">
      <div class="w3-container w3-card w3-white">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Education</h2>
        
        <c:forEach items="${requestScope.words}" var="word">
			<div class="w3-container">
	          	<h5 class="w3-opacity"><b>W3Schools.com</b></h5>
	          	<h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>${word}</h6>
	          	<p>Web Development! All I need to know in one place</p>
	          	<hr>
        	</div>
		</c:forEach>
        
        <div class="w3-container">
          <h5 class="w3-opacity"><b>London Business School</b></h5>
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>2013 - 2015</h6>
          <p>Master Degree</p>
          <hr>
        </div>
        
        <div class="w3-container">
          <h5 class="w3-opacity"><b>School of Coding</b></h5>
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>2010 - 2013</h6>
          <p>Bachelor Degree</p><br>
        </div>
        
      </div>
	</div>
	<div style="height: auto; width: 70%; float: left;">
		<hr>
			<c:forEach items="${requestScope.words}" var="word">
				<h2 >${word}</h2> <a href="index.html"> 저장</a><br>
			</c:forEach>
		<br><br>
		<a href="index.html">메인 화면 이동</a>
		<br>
	</div>
	
	<div style="height: 100%; width: 30%; float: right; background-image: url(http://jaeung.kim/wp-content/uploads/2020/10/KakaoTalk_20201025_184143003.jpg);">
		${sessionScope.id}
		logined id
	</div>
	
</body>

</html>