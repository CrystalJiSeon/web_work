<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//세션 스코프에 "nick"이라는 키 값으로 저장된 값 삭제하기
	session.removeAttribute("nick");
	//세션 스코프에 저장된 모든 내용을 초기화
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		로그아웃되었습니다.
		<a href="${pageContext.request.contextPath}/index.jsp">인덱스로 돌아가기</a>
	
	</p>
	
</body>
</html>