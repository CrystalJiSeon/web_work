package com.example.spring12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring12.dto.PostDto;
import com.example.spring12.dto.PostPageResponse;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired PostRepository repo;
	
	@Override
	public PostDto save(PostDto dto) {
		//dto 를 Entity로 변경해서 저장하고 결과를 entity로 리턴받기
		Post post = repo.save(Post.toEntity(dto));
		//결과 entity를 dto로 변경해서 리턴하기
		return PostDto.toDto(post);
	}

	@Override
	public List<PostDto> findAll() {
		//entity의 list를 얻어내서 
		List<Post> list=repo.findAll();
		//dto의 list로 변경해서 리턴한다
		return list.stream().map(PostDto::toDto).toList();
	}

	@Override
	public PostDto delete(long id) {
		//삭제할 entity를 미리 얻어오기(없으면 예외가 발생하고, 있으면 Post가 리턴된다)
		//(Optional: 추가 동작을 할 여지가 있다.)
		//Post post=repo.findById(id).orElseThrow();
		//만일 원하는 예외를 발생시키고 싶다면 
		Post post = repo.findById(id)
					.orElseThrow(
							()->new RuntimeException("삭제하려는 글이 존재하지 않습니다.")
					);
		//삭제 작업하고
		repo.deleteById(id);
		//삭제된 엔티티를 dto로 변경해서 리턴
		return PostDto.toDto(post);
	}

	@Override
	public PostDto updateAll(PostDto dto) {
		//entity의 id를 제외한 모든 필드를 수정함
		Post post = repo.save(Post.toEntity(dto));
		//수정된 entity를 dto로 변경해서 리턴하기
		return PostDto.toDto(post);
	}

	
	//JPA에서, ※동일한 Transaction 내에서 특정 entity를 find 한 다음
	//해당 entity의 setter 메소드를 이용해서 특정 필드를 수정하면 
	//Transaction이 종료될 때 Entity가 변경되었는지를 확인해서
	//자기가 알아서 자동으로 DB에 변경된 내용만 수정 반영한다.
	//Entity가 변경되었는지 체크하는걸 DirtyCheck라고 함
	//변경한 필드만 업데이트하는 sql문을 알아서 작성해서 update 해줌
	@Transactional//update()메소드를 단일 transaction으로 묶어준다
	@Override
	public PostDto update(PostDto dto) {
		//post id 를 이용해서 수정할 Entity를 얻어오기
		Post post =repo.findById(dto.getId()).orElseThrow();
		if(dto.getTitle()!=null) {//Title이 null이 아닐 때
				post.setTitle(dto.getTitle());
		}
		//Optional.ofNullable(dto.getTitle()).ifPresent(post::setTitle);
		
		if(dto.getAuthor()!=null) {//Author가 null이 아닐 때
			post.setAuthor(dto.getAuthor());
		}
		//Optional.ofNullable(dto.getAuthor()).ifPresent(post::setAuthor);
		return PostDto.toDto(post);
	}

	@Override
	public PostDto find(long id) {
		//post를 찾고 없으면 예외 발생시키기
		Post post = repo.findById(id).orElseThrow();
		//찾은 Entity를 dto로 변경해서 리턴하기
		return PostDto.toDto(post);
	}
	
	final int PAGE_ROW_COUNT=10;//한 페이지에 몇 개 씩 목록을 표시할 것인지,
	final int PAGE_DISPLAY_COUNT=5;
	//pageable 객체의 참조값 얻어내는 예제
	@Override
	public PostPageResponse findPage(int pageNum) {
		//id 칼럼에 대해서 내림차순 정렬하라는 정보를 가진 Sort 객체 만들기
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		//pageNum 과 PAGE_ROW+COUNT 와 sort 객체를 전달해서 Pageable 객체를 얻어낸다
		Pageable pageable =PageRequest.of(pageNum-1,PAGE_ROW_COUNT, sort);
		//Pageable 객체를 전달해서 해당 페이지 정보를 얻어온 다음
		Page<Post> page = repo.findAll(pageable);//얘는 도메인이다
		List<PostDto> list= page.stream().map(PostDto::toDto).toList();
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 페이지의 갯수 구하기
		int totalPageCount=page.getTotalPages();
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		
		//위의 정보를 이용해서 PostPageResponse 객체에 담아서 리턴한다.
		return PostPageResponse.builder().list(list)
							.startPageNum(startPageNum)
							.endPageNum(endPageNum)
							.totalPageCount(totalPageCount)
							.pageNum(pageNum).build();
	}

}
