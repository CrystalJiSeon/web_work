<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	//navbar.jsp 페이지가 어떤 페이지에 include 되었는지 정보 읽어오기
	String currentPage=request.getParameter("current"); // "index" or "member" or "food" 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-primary" data-bs-theme="dark">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">Home</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" <%=currentPage.equals("member") ? "active":"" %> href="${pageContext.request.contextPath }/member/list.jsp">Member</a>
					</li>
					<li class="nav-item"><a class="nav-link" <%=currentPage.equals("food") ? "active":"" %> href="${pageContext.request.contextPath }/food/list.jsp">Food</a>
					</li>
					<li>
						<a class="nav-link" <%=currentPage.equals("guest") ? "active":"" %> href="${pageContext.request.contextPath}/guest/list.jsp">방명록</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>