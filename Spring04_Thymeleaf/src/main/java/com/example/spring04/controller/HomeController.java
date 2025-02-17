package com.example.spring04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")//최상위 경로 요청에 대해서
	public String home(Model model) {
		//오늘의 인물
		String personToday="나";
		//응답에 필요한 데이터를 Model 객체에 담는다
		model.addAttribute("personToday", personToday);
		
		/*
		 *  아무런 설정 없이 여기에서 "home"이라는 문자열을 리턴하면 "/templates/"+home+".html"로 연결되어서 
		 *  결국 /templates/home.html view 페이지로 응답하겠다는 의미가 된다.
		 *  
		 *  /static/ 폴더의 html 페이지는 내용 그대로 응답되지만, 
		 *  /templates/ 폴더의 html 페이지는 view engine(Thymeleaf)가 해석을 하고, 해석된 결과가 응답된다.
		 *  이것은 우리가 그동안 jsp와 서블릿을 사용하던 것과 같은 기능을 해준다.
		 *  해석이 필요한 내용은 request나 model에 담아서 전달하면 jsp에서는 ${}로 추출했었는데,
		 *  타임리프에서는 어떻게 되느냐 : [[${}]] 더블 대괄호, 안에 el
		 *  
		 */
		
		
		return "home";
	}
}
