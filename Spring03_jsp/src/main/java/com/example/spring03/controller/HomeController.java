package com.example.spring03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring03.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		//공지사항을 출력하는 코드
		List<String> notice = List.of("Spring Boot 시작입니다", "공지사항입니다", "오늘은 입학식이 있습니다.");
		
		//공지사항을 "notice라는 키값으로 담기
		model.addAttribute("notice", notice);
		
		//view 페이지의 위치(응답을 위임할 jsp 페이지의 위치)를 리턴해준다.
		//return "/WEB-INF/views/home.jsp";
		return "home";//접두어가 WEB-INF/views/이고 접미어가 .jsp인 상황
	}
	
	@GetMapping("/fortune")
	public String fortune(HttpServletRequest request) {
		//오늘의 운세를 얻어오는 비즈니스 로직 수행했다는 가정의 더미 데이터
		String fortuneToday="새로 시작하는 일이 잘 될거에요";
		
		//오늘의 운세를 request scope에 담고
		request.setAttribute("fortuneToday", fortuneToday);
		
		//view page로 forwarding 이동해서 응답하기
		//return "/WEB-INF/views/fortune.jsp";
		return "fortune";
		
	}
	
	@GetMapping("/fortune2")
	public String fortune2(Model model) {
		//오늘의 운세를 얻어오는 비즈니스 로직 수행했다는 가정의 더미 데이터
		String fortuneToday="오늘도 열심히 하면 좋은 결과가 있을 거에요";
		
		//Model 객체에 응답에 필요한 데이터를 담으면 자동으로 request 영역에 담긴다.
		model.addAttribute("fortuneToday", fortuneToday);
		
		//view page로 forwarding 이동해서 응답하기
		//return "/WEB-INF/views/fortune.jsp";
		return "fortune";
		
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		//롬복을 활용해서 MemberDto 객체를 만들고 값을 담은 다음
		MemberDto dto= MemberDto.builder().num(1).name("송하나").addr("부산").build();
		//Model 객체에 "dto"라는 키값으로 MemberDto 객체를 담고
		model.addAttribute("dto", dto);
		// /WEB-INF/vies/member/info.jsp 페이지로 forward 이동해서 응답하기
		return "member/info";
		
	}
}
