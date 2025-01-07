<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	//폼으로 전송되는 번호 추출
	int num=Integer.parseInt(request.getParameter("num"));
	//DB에서 삭제
	FoodDao dao= new FoodDao();
	boolean isSuccess= dao.delete(num);
	//3. redirect 방식의 응답
	//특정 경로로 요청을 다시 하라는 리다이렉트 응답
	//list.jsp=>delete.jsp=>list.jsp 이동이기 때문에 새로고침하는 느낌을 줄 수 있음
	
	//context path는 HttpServletRequest 객체를 이용해서 얻어낸다.
	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/food/list.jsp");
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
		<%if(isSuccess){ %>
			<p>
				<strong><%=num %></strong> 맛집의 정보를 삭제 했습니다.
				<a href="list.jsp">확인</a>
			</p>
		<%}else{ %>
			<p>
				삭제 실패!
				<a href="list.jsp">확인</a>
			</p>
		<%} %>
	</div>
</body>
</html>