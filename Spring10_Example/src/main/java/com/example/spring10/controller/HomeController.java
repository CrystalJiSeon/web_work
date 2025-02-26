package com.example.spring10.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		//공지사항 내용
		List<String> noticeList=List.of("Spring Boot 프로젝트 시작!", "1단계 : 기본 세팅(시큐리티 포함)", "2단계 : 공지사항 출력하기 ",
				"3단계 : 스프링 Security", "4단계 : 회원가입과 로그인, 개인정보보기", "5단계 : 게시글 목록과 게시글 작성", "6단계 : 댓글 기능 추가" );
		// templates/home.html로 전달하기 위해 응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("noticeList", noticeList);
		
		
		return "home";
	}
	

}
