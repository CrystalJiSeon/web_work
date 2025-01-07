package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//3. 어떤 요청이 왔을 때 이 클래스로 생성된 객체로 응답을 할지 정해야 한다.
@WebServlet("/ping")
public class PingServlet extends HttpServlet{
	//이 클래스로 생성된 객체가 tomcat 서버에서 /ping 요청이 오면 직접 응답하도록 코딩해야 함...
	//이미 만들어진 서버의 부품으로 들어갈 클래스라 마음대로 만들면 안된다 -> 상속받거나 구현해서 만들어야 함
	//1. HttpServlet 추상 클래스를 상속 받음
	//2.부모의 service()메소드를 오버라이딩 한다
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이 안의 내용이 /ping에 대한 요청이 있을 때 실행된다. arg0은 요청에 관련된 정보를, arg1은 응답에 필요한 객체의 도구를 작성
		//톰캣서버가 객체의 참조값을 전달해주고, 이 객체를 활용한 응답을 작성하기
		
		//클라이언트에게 문자열을 출력할 수 있는 객체를 얻어낸다.
		PrintWriter pw=response.getWriter();
		//핑요청을 한다면 그게 클라이언트의 웹브라우저에 출력된다.
		pw.println("pong");
		pw.close();
		
	}
}
