<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 폼 전송되는 수정할 글의 글번호, 작성자, 내용, 비밀번호를 모두 가져와서
	int num=Integer.parseInt(request.getParameter("num"));
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	//2. DB에 저장
	GuestDto dto =new GuestDto();
	dto.setNum(num);
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setPwd(pwd);
	
	//GuestDao 객체의 참조값 얻어오기
	GuestDao dao=GuestDao.getInstance();
	boolean isSuccess=dao.update(dto);

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p>
				글을 수정했습니다.
				<a href="list.jsp">목록보기</a>
			</p>
		<%}else{ %>
			<p>
				수정 실패!
				<a href="insertform.jsp">다시 작성</a>
			</p>
		<%} %>
	</div>
</body>
</html>