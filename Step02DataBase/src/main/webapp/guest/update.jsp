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
	
	//GuestDao 객체의 참조값 얻어오기
	GuestDao dao=GuestDao.getInstance();
	//DB에 저장된 비밀번호를 가져와서
	String savedPwd=dao.getData(num).getPwd();
	
	//지역변수 안에서만 있으면 사용할 수 없으니까 미리 선언하기
	boolean isSuccess=false;
	
	//만일 비밀번호가 일치한다면
	if(pwd.equals(savedPwd)){
		GuestDto dto =new GuestDto();
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setPwd(pwd);
		isSuccess= dao.update(dto);		
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