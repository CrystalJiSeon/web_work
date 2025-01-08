<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<%-- 아래 명령을 통해 resource.jsp 페이지가 응답하도록 할 수 있다. --%>
<jsp:include page="/include/resource.jsp">
	<jsp:param value="index" name="current"/>
</jsp:include>
</head>
<body>
	<%-- 아래 명령을 통해 navbar.jsp 페이지가 응답하도록 할 수 있다 --%>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="index" name="current"></jsp:param>
	</jsp:include>
	<div class="container">
		<h1 >인덱스 페이지</h1>
		<ul>
			<%--<li><a href="connection/test.jsp" >Connection 테스트</a></li>--%>
			<li><a href="member/list.jsp">회원목록보기</a></li>
			<li><a href="food/list.jsp">맛집목록보기</a></li>
			<li><a href="guest/list.jsp">방명록</a></li>
		</ul>
	</div>
</body>
</html>