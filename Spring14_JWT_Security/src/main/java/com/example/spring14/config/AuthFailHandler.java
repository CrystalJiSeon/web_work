package com.example.spring14.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//로그인 실패 시 동작하는 핸들러
@Component
public class AuthFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//발생한 예외의 종류(타입)를 확인해서 에러 메시지를 설정한다
		//발생한 예외의 종류는 모두 AuthenticationException의 자식 타입들이다.
		String errorMessage = "";
        if (exception instanceof BadCredentialsException) {
        	errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
        	errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
        } else if (exception instanceof DisabledException) {
        	errorMessage = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof CredentialsExpiredException) {
        	errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        }
        //에러 메시지를 request에 담고
        request.setAttribute("errorMessage", errorMessage);
        //로그인 폼으로 forward 이동해서 응답한다. 근데 이 요청은 POST 방식 /user/login에 대한 응답.
        //따라서 forward 이동될 /user/loginform은 get/post 방식을 모두 처리할 수 있어야 한다.
        request.getRequestDispatcher("/user/loginform").forward(request, response);
	}
	

}
