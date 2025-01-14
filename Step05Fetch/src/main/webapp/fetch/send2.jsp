<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	//Get 방식으로 요청 파라미터 읽어오기
	String msg=request.getParameter("msg");
	System.out.println("msg:"+msg);

%>
{
	"isSuccess":true,
	"message":"메시지 수신 확인"
}