<%@page import="api.WordApi"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String url = application.getContextPath() + "/";
	ArrayList<String> words = new ArrayList<String>();
	if (request.getParameter("inputnumber")==null){
		words = WordApi.crawler("10");
	}else{
		words = WordApi.crawler(request.getParameter("inputnumber"));
	}
	request.setAttribute("words", words);
%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third" style="height: 100%;">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-container">
        <br>
        <%
        if (session.getAttribute("id")==null){
        	out.print("pleas <a href=\"login-page.jsp\">Log In</a> to find out your words");
        } else {
        	out.print("Welcome! " + session.getAttribute("id"));
        }
        %>
          <hr>
          <form action="wordList.jsp" method="post">
	    	<input type="submit" class="w3-large w3-animate-top" value="FIND OUT"></input>
	    	<input type="text" name="inputnumber" value="10"/>words<br>
    	</form>
    	<hr>
    	also find out our <a href="wordCommunity.jsp">community</a>! <br><br>
        </div>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
      	 <c:forEach items="${requestScope.words}" var="word">
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16">
        <i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>
        	${word}</h2>
			<div class="w3-container">
	          	<h5 class="w3-opacity"><b></b></h5>
	          	<p>뜻이 삽입될 예정입니다</p>
	          	<hr>
        	</div>
      </div>
		</c:forEach>
    <!-- End Right Column -->
    </div>
  <!-- End Grid -->
  </div>
  <!-- End Page Container -->
  <center>
  <a href="index.html">메인 화면 이동</a>
  </center>
</div>
<footer class="w3-container w3-teal w3-center w3-margin-top">
  <p>Find me on social media.</p>
  <i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-instagram w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

</body>
</html>
