<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//이 영역은 servlet의 service 메소드 안쪽 영역과 동일하다고 생각하면 된다.
	String fortuneToday="서쪽엔 마녀가 살고 있습니다";
	//요청 파라미터 추출
	String msg=request.getParameter("msg");
	//콘솔에 출력
	System.out.println(msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send.jsp</title>
</head>
<body>
	<h1>나는 jsp 페이지</h1>
	<p>오늘의 운세 : <strong>  <%  out.print(fortuneToday); %> </strong></p>
	<p>오늘의 운세 : <strong>  <%=fortuneToday%> </strong></p> 
	<% for(int i=0; i<3; i++) { %>
	<p><%= i %></p>
	<% } %>
</body>
</html>