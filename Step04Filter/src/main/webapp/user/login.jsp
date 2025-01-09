<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼에서 전송되는 아이디, 비밀번호를 읽어온다.
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	//DB에 사용자 정보를 읽어와서 맞는 정보인지 비교한다
	boolean isValid=false;
	if(pwd.equals("1234")){
		//세션에 "id"라는 키값으로 로그인도니 아이디를 저장한다
		session.setAttribute("id", id);
		isValid=true;
		
	}
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%if(isValid){ %>
		<p>
			<strong><%=id %></strong>님, 로그인되었습니다.
			<a href="${pageContext.request.contextPath}/">인덱스로</a>
		</p>
		<%}else{ %>
		<p>
			아이디 혹은 비밀번호가 틀립니다.
			<a href="loginform.jsp">다시 로그인하러 가기</a>
		</p>
		<%} %>
	</div>
</body>
</html>