package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



//요청 경로 작성 시 주의 점
//1. context path는 생략한다.
//2. 반드시 /로 시작한다.

@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//어떤 로직에 의해 오늘의 운세를 얻어 왔다고 하자
		String fortuneToday="동쪽으로 가면 귀인을 만나요";
		
		
		//응답 인코딩 설정
		response.setCharacterEncoding("utf-8");
		//응답 컨텐트 설정
		response.setContentType("text/html; charset=utf-8");
		//html 형식의 utf-8을 응답해줄게, 라고 알려주는 정석적인 형식
		
		
		//요청한 클라이언트에게 문자열을 출력할 수 있는 객체
		PrintWriter pw=response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>오늘의 운세:  <strong> "+fortuneToday+"</strong></p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
	}
}
