<%@page import="test.util.DbcpBean"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% //dbcpbean 객체 이용해서 conneciton 객체 얻어오기
		Connection conn = new DbcpBean().getConn();    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/connection/test.jsp</title>
</head>
<body>
	<div class="container">
	<h1>connection test</h1> <!--이 앞의 문자열은 무조건 출력-->
	
	<% if(conn!=null){ //conn이 null 이 아니면 여기에 있는 문자열이 클라이언트에게 출력%>
		<p>Connection 객체를 성공적으로 얻어왔습니다</p>	
	
	<%}else{ //conn이 null이면 여기에 있는 문자열이 출력%>
		<p>Connection 객체를 얻어오지 못했습니다</p>
	
	<%}	%>
	
	</div>	
	
</body>
</html>
<% 
	if(conn!=null)conn.close(); // close를 호출하면 Connection이 Pool에 반환됨
%>