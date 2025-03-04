package com.example.spring11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired private MemberRepository repo;
	
	@Override
	public List<MemberDto> selectAll() {
		//member entity의 목록
		List<Member> entityList=repo.findAll(); //엔티티의 리스트가 나옴, 이걸 Dto의 리스트로 바꿔줘야 함(아래의 stream()이용 코드)
		//내림차순으로 정렬하고 싶으면 Repository에서 추가한 메소드를 활용한다.
		//List<Member> entityList=repo.findAllByOrderByNumDesc();
		//우리는 중수니까 
		List<MemberDto> list = entityList.stream().map(item->MemberDto.toDto(item)).toList();
		//더 간단히 쓰면 --->>>>>	List<MemberDto> list = entityList.stream().map(MemberDto::toDto).toList();
		/*
		 * 이렇게 작성해도 되는데 이건 하수
		 * //MemberDto 의 목록으로 만들어서 리턴해야 함. 
		 * List<MemberDto> list =new ArrayList<>();
		 * //반복문 돌면서 Member 객체를 순서대로 참조 for(Member tmp:entityList) {
		 * list.add(MemberDto.toDto(tmp)); }
		 */
		
		return list;
	}

	@Override
	public void saveMember(MemberDto dto) {
		//dto 를 entity로 변경해서 저장한다
		repo.save(Member.toEntity(dto));
	}

	@Override
	public MemberDto editMember(int num) {
		Member member=repo.findById(num).get();
		return MemberDto.toDto(member);
	}
	
	public void updateMember(MemberDto dto) {
		repo.save(Member.toEntity(dto));//MemberDto를 Entity로 변경해서 save()
		//save는 insert, update 겸용
	}

	@Override
	public void deleteMember(int num) {
		repo.deleteById(num);
	}

}
