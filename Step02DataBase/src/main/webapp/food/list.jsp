<%@page import="java.util.List"%>
<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//FoodDao 객체의 getList로 목록 가져오기
	FoodDao dao =new FoodDao();
	List<FoodDto> list= dao.getList();
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<a href="insertform.jsp">목록에 추가</a>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>종류</th>
					<th>이름</th>
					<th>가격</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<% for(FoodDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getType() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getPrice() %></td>
					<td><a href="updateform.jsp?num=<%=tmp.getNum()%>">수정</a></td>
					<td><a href="delete.jsp?num=<%= tmp.getNum()%>">삭제</a></td>
					
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<script>
	function deleteConfirm(num){
		const isDelete = confirm("삭제할까요?");
		location.href="delete.jsp?num="+num;
	}
	</script>
</body>
</html>