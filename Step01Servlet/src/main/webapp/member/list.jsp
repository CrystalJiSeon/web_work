<%@page import="java.io.PrintWriter"%>
<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
List<MemberDto> list = new ArrayList<>(); 
list.add(new MemberDto(1, "이름1", "주소1"));
list.add(new MemberDto(2, "이름2", "주소2"));
list.add(new MemberDto(3, "이름3", "주소3"));
response.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8");
PrintWriter pw = response.getWriter();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>친구목록</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<% 
				for (MemberDto tmp:list){ %>
			<tr>
				<td><%=tmp.getNum()%></td>
				<td><%=tmp.getName()%></td>
				<td><%=tmp.getAddr()%></td> 
			</tr>
			<%  }%>
		</tbody>
	
	</table>
</body>
</html>