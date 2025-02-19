package com.example.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.Param;

import com.example.spring05.dto.MemberDto;
import com.example.spring05.repository.MemberDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class MemberController {
	@Autowired
	private MemberDao dao;
	// 매개변수에 MemberDto 타입을 선언하면 form 전송되는 파라미터가 자동 추출되어서 MemberDto 객체에 담긴채로 참조값이 전달된다.
	// 단,form에서 전송되는 파라미터가 Dto의 필드명과 동일해야 한다. 
	
	@GetMapping("/member/delete")
	public String delete(int num) {
		dao.delete(num);
		return "redirect:/member/list";
	}
	
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/updateform")
	public String updateForm(@RequestParam("num")int num ,Model model) {
		MemberDto dto=dao.getData(num);
		model.addAttribute("dto", dto);
		return "member/updateform";
	}
	
	@PostMapping("/member/insert")
	public String insert(MemberDto dto) {
		dao.insert(dto);
		return "member/insert";
	}
	
	@GetMapping("/member/new")
	public String newForm() {
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model, HttpSession session) {
		
		//주입받은 MemberDao 객체를 이용해서 회원 목록을 얻음
		List<MemberDto> list=dao.getList();
		//Model 객체에 담는다
		model.addAttribute("list",list);
		
		//타임리프의 view 페이지에서 회원 목록을 응답함
		return "member/list";
	}
	
}
