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
		<h1>로그인된 회원 전용 공간</h1>
		<p>
			<strong>${sessionDto.userName }</strong>님의 로그인 페이지 입니다.
			<a href="${pageContext.request.contextPath}/">인덱스로 돌아가기</a>
		</p>
	
	</div>
</body>
</html>