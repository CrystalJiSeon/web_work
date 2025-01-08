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
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/food/list.jsp">맛집 목록</a></li>
				<li class="breadcrumb-item">맛집 추가</li>			
			</ol>
		</nav>
		<h1>맛집 목록에 추가하기 양식</h1>
		<form action="${pageContext.request.contextPath}/food/insert.jsp">
		<div>
			<label class="form-label" for="type">종류 구분</label>
			<select class="form=control" name="type" id="type">
				<option value="한식">한식</option>
				<option value="중식">중식</option>
				<option value="양식">양식</option>
				<option value="일식">일식</option>
				<option value="기타">기타</option>
			</select>
		</div>
		<div>
			<label for="name">이름</label>
			<input type="text" name="name" id="name"/>
		</div>
		<div>
			<label for="price">가격</label>
			<input type="text" name="price" id="price"/>
		</div>
		<button type="submit">저장</button>
		</form>
		
	</div>
</body>
</html>