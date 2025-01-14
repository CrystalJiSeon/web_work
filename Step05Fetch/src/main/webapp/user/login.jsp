<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /user/login.jsp--%>

<%
	//요청 파라미터 추출
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	//아이디 비밀번호가 유요한지 여부
	boolean isValid =false;
	//아이디는 admin 비밀번호는 1234가 유효한 정보라고 가정하자(나중에는 DB에서 뒤져서 가져오는걸로 만든다)
	if(id.equals("admin")&&pwd.equals("1234")) {
		isValid=true;
	}
%>

{"isSuccess": <%=isValid %>}