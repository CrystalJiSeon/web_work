package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.dto.MemberDto;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberDto> list = new ArrayList<>();
		list.add(new MemberDto(1,"이름1","주소1"));
		list.add(new MemberDto(2,"이름2","주소2"));
		list.add(new MemberDto(3,"이름3","주소3"));
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<table>");
		pw.println("<tr><th>번호</th><th>이름</th><th>주소</th></tr>");
		for (MemberDto tmp:list) {
			pw.println("<tr><td>"+tmp.getNum()+"</td><td>"+tmp.getName()+"</td><td>"+tmp.getAddr()+"</td></tr>");
		}
		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
