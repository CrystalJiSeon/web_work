<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/member/list.jsp">회원 목록</a></li>
				<li class="breadcrumb-item">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-add" viewBox="0 0 16 16">
  					<path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
  					<path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
				</svg>
				회원 추가
				</li>			
			</ol>
		</nav>
		<h1>회원추가폼</h1>
		<form action="${pageContext.request.contextPath}/member/insert.jsp" method="post">
		<div class="mb-3">
			<label class="form-label" for="name">이름</label>
			<input class="form-control" type="text" name = "name" id="name" placeholder="이름 입력..."/>
		</div>
		<div class="mb-3">
			<label class="form-label" for="addr">주소</label>
			<input class="form-control" type="text" name="addr" id="addr" placeholder="주소 입력..."/>
		</div>
		<button class="btn btn-success" type = "submit">저장</button>
		</form>
	
	</div>
</body>
</html>