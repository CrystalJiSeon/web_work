package com.example.spring12.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.service.PostService;

@RequestMapping("/v2")
@RestController//@ResponseBody가 기본인 Controller
public class PostController2 {
	
	@Autowired PostService service;
	
	//@ResponseBody : RestController 에서는 @ResponseBody가 기본이다.
	//보통 API 서버에는 클라이언트가 json 문자열을 전송하는데, 이 json 문자열에서 데이터를 추출하기 위해서는 @RequestBody 어노테이션이 필요하다.
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto){
		return service.save(dto);
	}
	
	//글 목록 요청 처리
	@GetMapping("/posts")
	public List<PostDto> list(){
		
		return service.findAll();

	}
	
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") long id) {
		return delete(id);
	}
	
	@PutMapping("/posts/{id}")
	public PostDto updateAll(@PathVariable("id") long id, @RequestBody PostDto dto) {		
		return service.updateAll(dto);
	}
	
	@PatchMapping("/posts/{id}")
	public PostDto update(@PathVariable("id") long id, @RequestBody PostDto dto) {
		return service.update(dto);
	}
	
	@GetMapping("/posts/{id}")
	public PostDto findPost(@PathVariable("id") long id) {
		
		return service.find(id);
	}
}
