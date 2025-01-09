<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>login form</h1>
		<form action="login.jsp" method="post">
			<div>
				<label for="">id</label>
				<input type="text" name="id" id="id" />
			</div>
			<div>
				<label for="pwd">pw</label>
				<input type="password" name="pwd" id="pwd" />
			</div>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>