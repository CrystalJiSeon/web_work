package com.example.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*
 * 클라이언트의 요청을 처리할 컨트롤러를 정의하고 bean으로 만들기
 */
@Controller
public class HelloController {
	
	@ResponseBody
	@GetMapping("/fortune")
	public String fortune() {
		return "열심히 하면 행운이 찾아올 것입니다";
	}
	
	
	@ResponseBody
	@GetMapping("/hello")// 클라이언트가 GET 방식"/hello" 경로로 요청하면 이 메소드가 실행됨
	public String hello() {
		
		//String type을 리턴하면서 메소드에 @ResponseBody 어노테이션을 붙여 놓으면 
		//여기서 리턴한 문자열이 클라이언트에게 그대로 출력됨
		return "만나서 반가워";
		//여기서 jsp 위치를 알려주면 jsp에 응답이 위임(forward)되어서 응답이 위임됨
		
	}
}
