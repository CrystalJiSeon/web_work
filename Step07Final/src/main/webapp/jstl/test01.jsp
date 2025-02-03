<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	//테스트를 위해 샘플 데이터를 request scope에 담는다
	List<String> names=new ArrayList<String>();
	names.add("친구1");
	names.add("친구2");
	names.add("친구3");
	//"list" 라는 키값으로 request scope에 ArrayList 객체 담아두기
	request.setAttribute("list",names);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>친구목록</h1>
	<%
		//request 영역에 "list"라는 키값으로 담긴 친구 목록을 얻어와서 원래 type으로 casting
		List<String> list=(List<String>)request.getAttribute("list");
	%>
	<ul>
		<%for(String tmp:list){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
	
	<h1>친구목록2</h1>
	<ul>
		<c:forEach var="tmp" items="${requestScope.list}">
			<li>${tmp}</li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 인덱스 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list}" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.index}</strong></li>
		</c:forEach>
	</ul>
	
	
	<h1>친구 목록 순서 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list}" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.count}</strong></li>
		</c:forEach>
	</ul>
	
	
	<h1>친구 목록 첫번째 순서인지 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list}" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.first}</strong></li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 마지막 순서인지 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list}" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.last}</strong></li>
		</c:forEach>
	</ul>
</body>
</html>