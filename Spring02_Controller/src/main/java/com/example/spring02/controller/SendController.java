package com.example.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

/*
 * 컨트롤러 메소드 안에서 HttpServletRequest, HttpServletResponse,
 * HttpSession 등 객체가 필요하면 매개변수에 선언하면 된다.
 * 선언만 하면 Spring 프레임워크가 알아서 해당 객체의 참조값을 매개변수에 전달해줌
 */
@Controller
public class SendController {
	

	@ResponseBody
	@PostMapping("/send")
	public String send(HttpServletRequest request) {
		// 요청 파라미터 추출
		String msg = request.getParameter("msg");
		// 콘솔창에 출력하기
		System.out.println(msg);
		return "/send okay!";
	}
	
	//전송되는 파라미터명과 동일하게 매개 변수를 선언하면 자동으로 추출되어서 매개 변수에 전달된다.
	//<input type="text" name="msg"/>
	@ResponseBody
	@PostMapping("/send2")
	public String send2(String msg) {
		System.out.println(msg);
		return "/send2 okay!";
	}
	
}
