package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오늘의 운세를 얻어오는 비즈니스 로직을 수행(DB에서 읽어왔다고 가정)
		String fortune="동쪽으로 가면 귀인을 만나요";
		//여기서 html 형식으로 오늘의 운세를 응답하려면 머리가 아프다 
		//-> 해결 방법: webapp/에 어딘가에 있는 jsp 페이지에게 대신 응답하라고 응답을 위임할 수 있다!!!!!!
		//			대신 응답에 필요한 데이터(운세)sms request scope에 담아서 전달해 주어야 한다
		// 하나의 요청이 왔을 때 서블릿이 복잡한거 다 처리하게하고 jsp로 협동
		//request scope이란 것은 request 라는 객체에 데이터를 담는 메소드를 이용하는 것임(jsp로 응답을 위임 시킴) <-나중에 많이 쓰이는 구조

		//오늘의 운세를 request scope에 담는다.setAttribute key값과 value의 쌍을 담는데, value의 데이터 타입이 object라 아무거나 다 담을 수 있다.
		request.setAttribute("fortuneToday", fortune);
		
		//응답은 jsp 페이지에 위임한다(forward 이동)
		//.getRequestDispatcher)"응답을위임할jsp페이지의위치")
		RequestDispatcher rd=request.getRequestDispatcher("/test/fortune.jsp");
		rd.forward(request, response);
	}
}
