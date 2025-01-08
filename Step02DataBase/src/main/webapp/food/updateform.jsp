<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//수정할 음식 번호 읽어오기
	int num= Integer.parseInt(request.getParameter("num"));
	//수정할 음식의 정보를 DB에서 읽어오기
	FoodDto dto=new FoodDao().getData(num);
	//아래의 내용으로 응답된다
%>  
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
				<li class="breadcrumb-item">맛집 정보 수정</li>			
			</ol>
		</nav>
		<h1>맛집정보 수정 폼</h1>
		<p><%=true? "양식" :"" %></p>
		<form action="update.jsp" method="post">
			<div>
				<!-- 화면에 보이지는 않지만 폼 제출할 때 같이 전송되는 값 -->
				<input type="hidden" name="num" id="num" value="<%=dto.getNum()%>"/>
				
				<%--<label for="num">번호</label>
				<input type="text" name="num" id="num" value="<%=dto.getNum()%>" readonly/>--%>
			</div>
			<div>
				<label for="type">종류 구분</label>
				<select name="type" id="type" value="<%=dto.getType() %>">
					<option value="한식" <%=dto.getType().equals("한식")? "selected":"" %>>한식</option>
					<option value="중식" <%=dto.getType().equals("중식")? "selected":"" %>>중식</option>
					<option value="양식" <%=dto.getType().equals("양식")? "selected":"" %>>양식</option>
					<option value="일식" <%=dto.getType().equals("일식")? "selected":"" %>>일식</option>
					<option value="기타" <%=dto.getType().equals("기타")? "selected":"" %>>기타</option>
				</select>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="<%=dto.getName() %>"/>
			</div>
			<div>
				<label for="price">가격</label>
				<input type="number" name="price" id="price" max="100000000" min="100" step="100" value="<%=dto.getPrice() %>"/>
			</div>
			<button class="btn btn-success" type="submit">저장</button>
			<button class="btn btn-danger"type="reset">취소</button>  <!-- 작성하던 내용을 원래 상태로 되돌림 -->
		</form>
	</div>
</body>
</html>