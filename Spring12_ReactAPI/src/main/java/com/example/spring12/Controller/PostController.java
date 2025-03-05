package com.example.spring12.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

@RestController//@ResponseBody가 기본인 Controller
public class PostController {
	
	@Autowired private PostRepository repo;
	
	//@ResponseBody : RestController 에서는 @ResponseBody가 기본이다.
	//보통 API 서버에는 클라이언트가 json 문자열을 전송하는데, 이 json 문자열에서 데이터를 추출하기 위해서는 @RequestBody 어노테이션이 필요하다.
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto){
		//방금 저장된 정보가 들어있는 Saved Entity가 리턴됨
		Post post = repo.save(Post.toEntity(dto));
		
		//Entity를 dto로 변경해서 리턴하기 
		return PostDto.toDto(post);
	}
	
	//글 목록 요청 처리
	@GetMapping("/posts")
	public List<PostDto> list(){
		//Entity List 를 dto List로 변경해서 리턴해준다.
		return repo.findAll().stream().map(PostDto::toDto).toList();

	}
}
