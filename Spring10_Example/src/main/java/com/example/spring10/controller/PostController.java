package com.example.spring10.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.dto.CommentListRequest;
import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

	@Autowired private PostService service;
	
	@PostMapping("/post/update-comment") @ResponseBody
	public Map<String, Boolean> updateComment(CommentDto dto){
		service.updateComment(dto);
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/post/delete-comment")	@ResponseBody //이렇게 작성해도 문제 없어
	public Map<String, Boolean> deleteComment(long num){
		service.deleteComment(num);
		//@ResponseBody 어노테이션을 붙여놓고 아래의 데이터를 리턴하면
		//{"isSuccess":true}형식의 json 문자열이 응답된다
		return Map.of("isSuccess", true);
	}
	
	
	
	@GetMapping("/post/comment-list")
	@ResponseBody//Gson 기능이 내장되어서 Map을 응답하는 곳의 body에 넣어주면 알아서 json으로 응답함
	public Map<String, Object> commentList(CommentListRequest clr){
		//CommentListRequest 객체에는 댓글의 list만이 아니라 댓글의 pageNum과 원글의 글번호 postNum이 들어있다.
		return service.getComments(clr);
		
	}
	 
	
	//댓글 저장 요청 처리
	@PostMapping("/post/save-comment")
	@ResponseBody//dto 에 저장된 내용을 json으로 응답하기 위한 어노테이션
	public CommentDto saveComment(CommentDto dto) {
		service.createComment(dto);
		return dto;
	}
	
	
	//글 삭제 요청 처리
	@GetMapping("/post/delete")
	public String delete(long num) {
		service.delete(num);
		return "post/delete";
	}
	
	
	//글 수정 반영 요청 처리
	@PostMapping("post/update")
	public String update(PostDto dto, RedirectAttributes ra) {
		service.updatePost(dto);
		//RedirectAttributes 객체에 FlashAttribute 로 담은 내용은
		//redirect 이동된 요청을 처리하는 곳의 Model 객체에 자동으로 담긴다.
		ra.addFlashAttribute("updateMessage",dto.getNum()+"번 글을 수정했습니다.");
		//수정 반영 후 글 자세히 보기로 이동
		return "redirect:/post/view?num="+dto.getNum();
	}
	
	//글 수정 폼 요청처리
	@GetMapping("/post/edit")
	public String edit(long num, Model model) {
		//수정할 글 정보를 얻어와서 Model 객체에 담는다.
		PostDto dto= service.getByNum(num);
		model.addAttribute("dto", dto);
		return "post/edit";
	}
	

	
	@GetMapping("/post/view")
	public String view(PostDto dto, CommentDto cdto, Model model, HttpSession session) {
		PostDto resultDto=service.getDetail(dto);
		model.addAttribute("postDto", resultDto);
		model.addAttribute("commentDto",cdto);
		
		if(model.getAttribute("saveMessage")==null) {
			//조회수 관련 처리를 한다.
			String sessionId=session.getId();
			service.manageViewCount(dto.getNum(), sessionId);
			
		}
		
		return "post/view";
	}
	
	
	@GetMapping("/post/new")
	public String newForm() {
		return "post/new";
	}
	//dto에 글번호만 있는 경우도 있고, 검색과 관련된 정보도 같이 있을 수 있고,
	@PostMapping("/post/save")
	public String save(PostDto dto, RedirectAttributes ra) {
		long num=service.createPost(dto);
		
		ra.addFlashAttribute("saveSuccess", "글을 성공적으로 저장했습니다");
		//글 자세히 보기로 리다이렉트
		return "redirect:/post/view?num="+num;
	}
	
	//@RequestParam 은 pageNum이 파라미터로 넘어오지 않을 때의 default 값을 1로 설정하기 위해서 작성한 내용이고,
	//검색한 키워드도 파라미터로 넘어오게 되면 PostDto에는 condition과 keyword가 null이 아니다.
	//검색한 키워드가 넘어오지 않으면 PostDto의 condition과 keyword가 null이다.
	@GetMapping("/post/list")
	public String list(@RequestParam(defaultValue="1") int pageNum, PostDto search, Model model) {
		PostListDto dto=service.getPosts(pageNum, search);
		model.addAttribute("dto", dto);
		System.out.println(dto);
		return "post/list";
	}
}
