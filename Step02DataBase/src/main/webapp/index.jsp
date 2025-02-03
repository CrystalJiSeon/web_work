<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<jsp:include page="/include/resource.jsp">
	<jsp:param value="index" name="current"/>
</jsp:include>
</head>
<body class="d-flex flex-column min-vh-100">
	<div class="main flex-grow-1">
		<%-- 아래 명령을 통해 navbar.jsp 페이지가 응답하도록 할 수 있다 --%>
		<jsp:include page="/include/navbar.jsp">
			<jsp:param value="index" name="current"></jsp:param>
		</jsp:include>
		<div class="container">
			<h1 >Home</h1>
			<ul>
				<%--<li><a href="connection/test.jsp" >Connection 테스트</a></li>--%>
				<li><a href="member/list.jsp">Member List</a></li>
				<li><a href="food/list.jsp">Food List</a></li>
				<li><a href="guest/list.jsp">Visitor's Log</a></li>
			</ul>
		</div>
	</div>
	<%-- footer.jsp include --%>
	<jsp:include page="/include/footer.jsp"/>
<%-- 파라미터 전달할 게 없으면 바로 닫아버리면 된다 --%>
</body>
</html>