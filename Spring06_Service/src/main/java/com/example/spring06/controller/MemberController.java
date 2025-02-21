package com.example.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.Param;

import com.example.spring06.dto.MemberDto;
import com.example.spring06.repository.MemberDao;
import com.example.spring06.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class MemberController {
	
	//서비스 객체는 Controller에서 활용하는 유틸리티라고 생각하면 된다
	@Autowired
	private MemberService service;
	//private MemberDao dao; 이 줄은 서비스에 넘겨주기 때문에 없앤다.
	// 매개변수에 MemberDto 타입을 선언하면 form 전송되는 파라미터가 자동 추출되어서 MemberDto 객체에 담긴채로 참조값이 전달된다.
	// 단,form에서 전송되는 파라미터가 Dto의 필드명과 동일해야 한다. 
	
	@GetMapping("/member/delete")
	public String delete(int num) {
		service.deleteById(num);
		return "redirect:/member/list";
	}
	
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		service.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/updateform")
	public String updateForm(@RequestParam("num")int num ,Model model) {
		//서비스를 이용해서 회원 정보 얻어오기
		MemberDto dto=service.findById(num);
		model.addAttribute("dto", dto);
		return "member/updateform";
	}
	
	@PostMapping("/member/insert")
	public String insert(MemberDto dto) {
		service.save(dto);
		return "member/insert";
	}
	
	@GetMapping("/member/new")
	public String newForm() {
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model, HttpSession session) {
		
		//서비스를 이용해서 회원 목록 얻어오기
		List<MemberDto> list=service.findAll();
		//Model 객체에 담는다
		model.addAttribute("list",list);
		
		//타임리프의 view 페이지에서 회원 목록을 응답함
		return "member/list";
	}
	
}
