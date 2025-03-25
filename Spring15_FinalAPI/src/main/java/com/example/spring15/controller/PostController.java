package com.example.spring15.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring15.dto.CommentDto;
import com.example.spring15.dto.CommentListRequest;
import com.example.spring15.dto.PostDto;
import com.example.spring15.dto.PostListDto;
import com.example.spring15.service.PostService;

@RestController
public class PostController {

	@Autowired private PostService service;

	
	@PatchMapping("/posts/{num}/comments/{commentNum}")
	public Map<String, Boolean> updateComment(@RequestBody CommentDto dto){
		//dto에는 댓글의 글번호와 댓글의 내용이 들어있다.
		service.updateComment(dto);
		return Map.of("isSuccess", true);
	}
//	@PostMapping("/post/update-comment") @ResponseBody
//	public Map<String, Boolean> updateComment(CommentDto dto){
//		service.updateComment(dto);
//		return Map.of("isSuccess", true);
//	}
//	
	@DeleteMapping("/posts/{num}/comments/{commentNum}")
	public Map<String, Boolean> deleteComment(@PathVariable("commentNum") long num){
		service.deleteComment(num);
		//@ResponseBody 어노테이션을 붙여놓고 아래의 데이터를 리턴하면
		//{"isSuccess":true}형식의 json 문자열이 응답된다
		return Map.of("isSuccess", true);
	}
	
	
	
	@GetMapping("/posts/{num}/comments")
	public Map<String, Object> commentList(@PathVariable("num") long num, int pageNum){
		//CommentListRequest에 필요한 정보를 담고
		CommentListRequest clr=new CommentListRequest();
		clr.setPostNum(num);
		clr.setPageNum(pageNum);
		//서비스를 이용해서 댓글 목록 정보를 얻어내서 응답한다.
		return service.getComments(clr); // list 라는 키값으로 List를 담고, totalPageCount라는 키값으로 pageNum을 담아옴. {"list":[{},{},{}], "totalPageCount":19} 형태
		
	}
	 
	
	//댓글 저장 요청 처리
	@PostMapping("/posts/{num}/comments")
	public CommentDto saveComment(@PathVariable(value="num") long num, @RequestBody CommentDto dto) {
		//dto에 원글 번호 담기
		dto.setPostNum(num);
		//서비스를 이용해서 댓글 저장
		service.createComment(dto);
		return dto;
	}
	
	
	//글 삭제 요청 처리
	@DeleteMapping("/posts/{num}")
	public PostDto delete(@PathVariable(value="num") long num) {
		PostDto dto=service.getByNum(num);
		service.delete(num);
		return dto;//삭제한 글 정보를 응답으로 리턴하고 싶은 경우
	}
	
	
	//글 수정 반영 요청 처리
	@PatchMapping("/posts/{num}")
	public PostDto update(@PathVariable(name="num") long num, @RequestBody PostDto dto) {
		//글번호를 dto에 담는다
		dto.setNum(num);
		//서비스를 이용해서 수정 반영
		service.updatePost(dto);
	
		return dto;
	}
	
//	//글 수정 폼 요청처리
//	@GetMapping("/post/edit")
//	public String edit(long num, Model model) {
//		//수정할 글 정보를 얻어와서 Model 객체에 담는다.
//		PostDto dto= service.getByNum(num);
//		model.addAttribute("dto", dto);
//		return "post/edit";
//	}
//	

	
	@GetMapping("/posts/{num}")
	public PostDto view(@PathVariable(name="num") long num, PostDto dto) {
		//글번호는 경로 변수에, 검색 키워드가 있다면 해당 정보는 PostDto에 담겨있다
		//글번호를 dto에 담는다
		dto.setNum(num);
		//자세한 글 정보를 얻어와서
		PostDto resultDto = service.getDetail(dto);
		//응답한다.
		return resultDto;
	}
	
	
	//dto에 글번호만 있는 경우도 있고, 검색과 관련된 정보도 같이 있을 수 있고,
	@PostMapping("/posts")
	public Map<String, Object> save(@RequestBody PostDto dto) {
		long num=service.createPost(dto);
		return Map.of("num",num);
	
	}
	

	@GetMapping("/posts")
	public PostListDto list(@RequestParam(defaultValue="1") int pageNum, PostDto search) {
		//글목록과 검색 키워드 관련 정보가 들어 있는 PostListDto
		PostListDto dto=service.getPosts(pageNum, search);
		System.out.println(dto);
		// json 문자열이 응답되도록 dto를 리턴한다
		return dto;
	}
}
