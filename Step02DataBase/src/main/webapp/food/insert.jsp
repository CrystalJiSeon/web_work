<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼으로 전송되는 종류, 이름, 가격 추출
	String type=request.getParameter("type");
	String name=request.getParameter("name");
	int price=Integer.parseInt(request.getParameter("price"));
	//Dto 객체에 담기
	FoodDto dto=new FoodDto();
	dto.setType(type);
	dto.setName(name);
	dto.setPrice(price);
	//DB에 저장하기
	FoodDao dao= new FoodDao();
	boolean isSuccess= dao.insert(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){%>
			<p><strong><%=name %></strong> 맛집 정보를 저장했습니다.</p>
			<a href="list.jsp">목록보기</a>
		<%}else{%>
			<p>데이터 저장 실패</p>
			<a href="insertform.jsp">다시 작성</a>
		<%}%>
	</div>
</body>
</html>