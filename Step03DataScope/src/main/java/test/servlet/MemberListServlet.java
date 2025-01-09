package test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.member.dto.MemberDto;
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//DB에서 읽어온 회원 목록이라 가정하자
	MemberDto mem1=new MemberDto(1,"이름1", "주소1");
	MemberDto mem2=new MemberDto(2, "이름2", "주소2");
	MemberDto mem3=new MemberDto(3, "이름3", "주소3");
	List<MemberDto> list = new ArrayList<>();
	list.add(mem1);
	list.add(mem2);
	list.add(mem3);
	
	//webapp/member/list.jsp 페이지에서 회원 목록을 table 요소를 이용해서 출력하도록 해보세요
	request.setAttribute("memberlist", list);
	RequestDispatcher rd= request.getRequestDispatcher("/member/list.jsp");
	rd.forward(request, response);
	}
}
