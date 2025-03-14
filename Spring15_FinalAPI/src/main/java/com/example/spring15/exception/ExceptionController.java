package com.example.spring15.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//예외 컨트롤러는 @ControllerAdvice 어노테이션을 붙여서 bean을 만든다
@ControllerAdvice
public class ExceptionController {
	
	//아래 메소드 호출 시점 : "/user/update-password" 요청을 처리하는 중에 기존 비밀번호가 일치하지 않으면 
	//UserService 객체에서 PasswordException이 발생하고
	//해당 예외가 발생하면 이 메소드가 호출되면서 결과적으로 비밀번호 수정 폼으로 다시 리다이렉트 됨.
	@ExceptionHandler(PasswordException.class)
	public ResponseEntity<String> password(PasswordException pe) {
		//기존 비밀번호가 잘못 입력된 요청이라고 응답한다.
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pe.getMessage());
	}
	//거부된 요청일 때 실행되는 메소드
	@ExceptionHandler(DeniedException.class)
	public String denied(DeniedException de, Model model) {
		//예외 객체를 template 페이지에 전달하기
		model.addAttribute("exception",de);
		return "error/denied";
	}
	
}
