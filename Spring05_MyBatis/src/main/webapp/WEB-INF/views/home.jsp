<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스(jsp 페이지)</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/member/list">회원 목록 보기</a></li>
		</ul>
		
	</div>
</body>
</html>