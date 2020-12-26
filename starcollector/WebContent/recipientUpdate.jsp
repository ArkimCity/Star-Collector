<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<br>
	<br>
	<center>
		<h3>재능 수혜자 정보 수정하기 - 수혜 분야 수정</h3>
		<hr>
		<p>
		<form action="probono?command=recipientUpdate" method="post">
			<table border="1">
				<thead>
					<tr>
						<th>수혜자 id</th>
						<th>수혜자명</th>
						<th>비밀번호</th>
						<th>희망분야</th>
					</tr>
					<tr>
						<td><input type="text" name="recipientId"
							value="${recipient.recipientId}" readonly></td>
						<td>${recipient.name}</td>
						<td>${recipient.password}</td>
						<td><input type="text" name="receiveContent"
							value="${recipient.receiveContent}"></td>
					</tr>
					<tr>
						<td colspan="4"><input type="submit" value="수정">
							&nbsp;&nbsp;&nbsp; <input type="reset" value="취소"></td>
					</tr>
			</table>
		</form>
		<a href="index.html">메인 화면 이동</a>
	</center>
</body>
</html>