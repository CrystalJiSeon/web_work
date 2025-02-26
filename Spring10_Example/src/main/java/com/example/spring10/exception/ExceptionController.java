package com.example.spring10.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//예외 컨트롤러는 @ControllerAdvice 어노테이션을 붙여서 bean을 만든다
@ControllerAdvice
public class ExceptionController {
	
	//아래 메소드 호출 시점 : "/user/update-password" 요청을 처리하는 중에 기존 비밀번호가 일치하지 않으면 
	//UserService 객체에서 PasswordException이 발생하고
	//해당 예외가 발생하면 이 메소드가 호출되면서 결과적으로 비밀번호 수정 폼으로 다시 리다이렉트 됨.
	@ExceptionHandler(PasswordException.class)
	public String password(PasswordException pe, RedirectAttributes ra) {
		//리다이렉트 이동된 페이지에서도 한 번 사용할 수 있는 RedirectAttributes
		//타임리프에서 ${exception}으로, 메시지면 ${exception.message}로 참조 가능
		ra.addFlashAttribute("exception",pe);
		//ra.addFlashAttribute("exception",pe.getMessage());라고 메시지 자체를 보낼수도 있음
		
		//  user/edit-password로 요청을 다시 하라고 리다이렉트 요청하기
		return "redirect:/user/edit-password";
	}
}
