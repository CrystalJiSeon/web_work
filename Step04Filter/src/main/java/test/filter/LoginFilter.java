package test.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebFilter({"/test/protected/*", "/xxxx/protected/*"}) //자바에서 {}안은 배열이라 여러 경로를 쓸 수 있다.
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpSession session=request.getSession();
		//세션영역에 아이디라는 키값으로 저장된 값이 있는지 확인하기(로그인된 사용자인지 확인)
		String id=(String)session.getAttribute("id");
		//만일 로그인 하지 않았다면
		if(id==null) {
			//로그인 페이지로 리다이렉트 이동시킴
			String cPath=request.getContextPath();
			HttpServletResponse response=(HttpServletResponse)resp;
			response.sendRedirect(cPath+"/user/loginform.jsp");
		}else {
			//로그인 한 사용자라면 관여하지 않고 요청의 흐름을 이어간다
			chain.doFilter(req, resp);
		}
	}
}
