package test.filter;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import test.user.dto.SessionDto;


@WebFilter({"/member-only/*", "/staff/*", "/admin/*","/user/protected/*", "/post/protected/*"})
public class LoginFilter implements Filter{
	
	//@WebFilter()에 명시한 패턴의 요청을 하면 아래의 메소드가 호출된다
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		//매개변수에 전달된 객체를 이용해서 부모타입을 자식타입으로 캐스팅하여 객체의 참조값을 얻어낸다.
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //session 영역에서 로그인된 정보를 얻어내기 위한 객체
        HttpSession session = req.getSession();
        //세션 영역에 sessionDto 라는 키값으로 저장된 세션 값이 있으면 얻어내서 원래 type으로 캐스팅
        SessionDto dto = (SessionDto) session.getAttribute("sessionDto");
        //만일 로그인하지 않았다면
        if (dto == null) { 
        	//로그인 페이지로 리다이렉트 시키는 메소드를 호출해서 리다이렉트 시킴
            redirectToLogin(req, res);
            //메소드를 여기서 끝내기
            return;
        }   
        // Role-based authorization : role을 확인해서 /admin/*, /staff/* 요청도 필터링 해주는 역할
        String role = dto.getRole();
        String requestURI = req.getRequestURI();
       
        if (requestURI.startsWith(req.getContextPath() + "/admin") && !"ADMIN".equals(role)) {
        	//금지된 요청이라고 응답한다(관리자만 쓰는거니까 에러페이지를 굳이 쓸 필요는 없음)
        	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
            return;
        }

        if (requestURI.startsWith(req.getContextPath() + "/staff") && !"STAFF".equals(role) && !"ADMIN".equals(role)) {
            //금지된 요청이라고 응답한다(직원이 쓸 수 있는거)
        	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
            return;
        }

        // 여기까지 실행의 흐름이 넘어오면 요청의 흐름을 계속 이어간다(필터를 하지 않겠다는 것)Allow access for USER, STAFF, ADMIN
        chain.doFilter(request, response);
    }

	
	//리다이렉트로 응답하는 메소드(요청을 새로운 경로로 다시 하라는 응답)
    private void redirectToLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //원래 요청 url을 읽어온다
    	String url = req.getRequestURI();
    	//혹시 뒤에 query parameter가 있다면 걔 역시 읽어온다. ?num=xxx&count=ccc
        String query = req.getQueryString();
        String encodedUrl = query == null ? URLEncoder.encode(url, "UTF-8") : URLEncoder.encode(url + "?" + query, "UTF-8");
        //로그인 페이지로 리다이렉트 이동하면서 원래 가려던 목적지 정보도 같이 넘겨줌.
        res.sendRedirect(req.getContextPath() + "/user/login-form.jsp?url=" + encodedUrl);
    }

}
