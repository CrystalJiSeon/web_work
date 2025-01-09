<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//폼으로 전송되는 내용 추출하기
	String nick = request.getParameter("nick");
	session.setAttribute("nick", nick); // request.setAttribute로 응답한 내용은 1회성으로 페이지가 이동하면 사라진다. 
	//이걸 다회성으로 저장할 방법이 session.setAttribute();(얘는 웹브라우저 세션을 닫기 전/삭제하기 전까지 데이터가 살아 있음)
	//session 영역에 "nick"이라는 키값으로 저장하기
	session.setMaxInactiveInterval(180000);
	//세션의 유지 시간을 초단위로 전달해서 설정할 수 있다(기본값은 30분=1800초, 30이라 쓰면 30초임)
	//3 응답한다

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p><strong><%=nick %>></strong>이라는 닉네임을 기억하겠습니다</p>
	<p> 30분 동안 아무런 요청을 하지 않거나 웹 브라우저를 닫으면 자동 삭제됩니다.</p>
	<a href="../index.jsp">인덱스로 이동하기</a>

	
</body>
</html>