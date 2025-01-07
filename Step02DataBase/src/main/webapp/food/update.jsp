<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%


//1. 폼으로 전송되는 번호, 이름과 주소 추출
	int num=Integer.parseInt(request.getParameter("num"));
	String type=request.getParameter("type");
	String name=request.getParameter("name");
	int price=Integer.parseInt(request.getParameter("price"));
//2. MemberDto 객체에 담기
	FoodDto dto = new FoodDto(num, type, name, price);

//2. DB에 수정하기
	boolean isSuccess=new FoodDao().update(dto);
//3. 응답하기
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		<%if(isSuccess){%>
			//알림창을 띄우고
			alert("정보를 수정했습니다");
			//list.jsp 페이지로 이동
			location.href="list.jsp"
		<%}else{%>
			//알림창을 띄우고
			alert("정보 수정 실패");
			//updateform.jsp 페이지로 이동하면서 num이라는 파라미터명으로 수정할 회원의 번호를 가지고 간다
			location.href="updateform.jsp?num=<%=num%>";
		<%}%>
		
	</script>
</body>
</html>