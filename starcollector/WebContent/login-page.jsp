<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="login-page.css">
</head>
<body>
	<main id="main-holder">
	<%
	if (request.getAttribute("errorMsg")!=null){
		out.print(request.getAttribute("errorMsg"));
	}
	%>
	
	<h1 id="login-header">Login</h1>
	<form id="login-form" action="loginservice" method="post">
		<input id="username-field" type="text" name="id" placeholder="Username"><br>
		<input id="password-field" type="password" name="pw" placeholder="Password"><br>
		<input id="login-form-submit" type="submit" value="login">
	</form>
	</main>
</body>
</html>