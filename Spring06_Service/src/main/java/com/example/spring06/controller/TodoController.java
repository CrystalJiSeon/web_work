package com.example.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring06.dto.TodoDto;
import com.example.spring06.repository.TodoDao;

@Controller
public class TodoController {
	@Autowired
	private TodoDao dao;
	
	@GetMapping("/todo/list")
	public String list(Model model) {
		List<TodoDto> list = dao.getList();
		model.addAttribute("list", list);
		return "todo/list";
	}
	
	@GetMapping("/todo/new")
	public String newform() {
		
		return "todo/new";
	}
	
	@PostMapping("/todo/insert")
	public String insert(TodoDto dto) {
		dao.insert(dto);
		return "redirect:list";
	}
	
	@GetMapping("/todo/edit")
	public String edit(int id, Model model) {
		TodoDto dto= dao.getData(id);
		model.addAttribute("dto", dto);
		return "todo/edit";
		
	}
	
	@PostMapping("/todo/update")
	public String update(TodoDto dto) {
		dao.update(dto);
		return "redirect:list";
	}
	
	@GetMapping("todo/delete")
	public String delete(int id) {
		dao.delete(id);
		return "redirect:list";
	}
}
