<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	//Get 방식으로 요청 파라미터 읽어오기
	String msg=request.getParameter("msg");
	System.out.println("msg:"+msg);



%>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	메시지 문자 확인	
</body>
</html>