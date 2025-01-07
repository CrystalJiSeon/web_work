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
</head>
<body>
<div class="container">
		<h1>맛집정보 수정 폼</h1>
		<form action="update.jsp" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" name="num" id="num" value="<%=dto.getNum()%>" readonly/>
			</div>
			<div>
				<label for="type">종류 구분</label>
				<select name="type" id="type" value="<%=dto.getType() %>">
					<option value="한식">한식</option>
					<option value="중식">중식</option>
					<option value="양식">양식</option>
					<option value="일식">일식</option>
					<option value="기타">기타</option>
				</select>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="<%=dto.getName() %>"/>
			</div>
			<div>
				<label for="price">가격</label>
				<input type="text" name="price" id="price" value="<%=dto.getPrice() %>"/>
			</div>
			<button type="submit">저장</button>
			<button type="reset">취소</button>  <!-- 작성하던 내용을 원래 상태로 되돌림 -->
		</form>
	</div>
</body>
</html>