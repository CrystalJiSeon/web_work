<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지입니다</h1>
		<p>context path : <strong>${pageContext.request.contextPath}</strong></p>
		
		<h3>공지사항</h3>
		<ul>
			<c:forEach var="tmp" items="${notice }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
		
		<ul>
			<li><a href="${pageContext.request.contextPath}/fortune">오늘의 운세</a></li>
			<li><a href="${pageContext.request.contextPath}/fortune2">오늘의 운세2</a></li>
			<li><a href="${pageContext.request.contextPath}/member">회원 1명의 정보(Dto 테스트)</a></li>
		</ul>
		
	</div>
</body>
</html>