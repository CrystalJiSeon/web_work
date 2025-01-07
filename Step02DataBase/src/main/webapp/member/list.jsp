<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    //memberdao 객체를 이용해서 회원 목록 얻어오기
    MemberDao dao=new MemberDao();
    List<MemberDto> list = dao.getList();
   	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<a href="insertform.jsp">회원추가</a>
		<!-- href="/Step02DataBase/member/insertform.jsp"라고 해도 되고 -->
		<!-- href="${pageContext.request.contextPath}/member/insertform.jsp" 라고 해도 됨, 그리고 나중에 실제 개발을 하게 되면 이걸 쓰게 됨-->
		<h1>회원 목록</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<% 
				for (MemberDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getAddr() %></td>
					<td><a href="updateform.jsp?num=<%=tmp.getNum()%>">수정</a></td>
					<td><a href="delete.jsp?num=<%=tmp.getNum()%>">삭제</a></td>
				</tr>
				<% }%>
			</tbody>
		
		</table>
	</div>
</body>
</html>