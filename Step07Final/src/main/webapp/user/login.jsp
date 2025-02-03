<%@page import="java.net.URLEncoder"%>
<%@page import="test.user.dto.SessionDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 1. 폼 전송되는 userName, password 를 읽어와서
	String userName=request.getParameter("userName");
	String password=request.getParameter("password");
	//2. 아이디에 해당하는 회원정보를 userdao 객체를 이용해서 얻어와서
	UserDto dto = UserDao.getInstance().getData(userName);
	//3. 실제로 존재하는 아이디이고, 존재한다면 비밀번호도 일치하는지 비교해서
	boolean isLoginSuccess=false;
	if(dto!=null){
		//비밀 번호 확인 해서 
		if(dto.getPassword().equals(password)){//비밀번호까지 일치한다면 
			//로그인 처리를 한다(로그인된 user 정보를 session 영역에 담는다 * 이외에도 프라이머리키, userName, role 정도를 담아두는 편이다.)
			SessionDto sessionDto=new SessionDto();
			sessionDto.setNum(dto.getNum());
			sessionDto.setUserName(dto.getUserName());
			sessionDto.setRole(dto.getRole());
			//로그인 처리 해주기
			session.setAttribute("sessionDto", sessionDto);
			isLoginSuccess=true;
		}
	}
	//로그인 후 가야할 목적지 정보
	String url=request.getParameter("url");
	//로그인 실패를 대비해서 목적지 정보를 인코딩한 결과도 준비한다
	String encodedUrl=URLEncoder.encode(url, "UTF-8");
	
	//일치하면 로그인 처리 후 응답, 일치하지 않으면 일치하지 않는다고 응답

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%if(isLoginSuccess){%>
		<p><strong><%=dto.getUserName() %></strong>님 로그인 되었습니다.</p>
		<a href="<%=url%>">확인</a>
		<%}else{ %>
		<p>아이디 또는 비밀번호가 일치하지 않습니다.</p>
		<a href="${pageContext.request.contextPath}/user/login-form.jsp?url=<%=encodedUrl%>">다시 입력</a>
		<%} %>
	</div>
</body>
</html>