<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="class">
		<c:choose>
			<c:when test="${empty sessionScope.sessionDto}">
				<p>
					<a href="${pageContext.request.contextPath}/user/login-form.jsp">로그인</a>
					<a href="${pageContext.request.contextPath}/user/signup-form.jsp">회원가입</a>				
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href="${pageContext.request.contextPath}/user/protected/info.jsp"><strong>${sessionDto.userName}</strong>님 로그인 중...</a>
					<a href="${pageContext.request.contextPath}/user/logout.jsp">로그아웃</a>
				</p>
			</c:otherwise>
		</c:choose>
	
		<h1>인덱스 페이지입니다.</h1>	
		<ul>
			<li><a href="${pageContext.request.contextPath}/jstl/hello.jsp">jstl 테스트</a></li>
			<li><a href="${pageContext.request.contextPath}/user/signup-form.jsp">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath}/user/login-form.jsp">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/member-only/play.jsp">놀러가기</a></li>
			<li><a href="${pageContext.request.contextPath}/post/list.jsp">글 목록 보기</a></li>
		</ul>
	</div>
</body>
</html>