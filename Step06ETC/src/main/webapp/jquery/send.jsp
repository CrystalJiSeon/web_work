<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=request.getParameter("msg");
	System.out.println("msg:"+msg);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery/send.jsp</title>
</head>
<body>
	<p>보내준 메시지를 확인했습니다.</p>
</body>
</html>