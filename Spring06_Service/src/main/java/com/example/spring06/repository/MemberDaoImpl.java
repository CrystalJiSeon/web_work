package com.example.spring06.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring06.dto.MemberDto;

//dao를 bean으로 만들기 위한 어노테이션
@Repository
public class MemberDaoImpl implements MemberDao{
	//MyBatis 기반으로 DB 관련 작업을 하기 위한 핵심 의존 객체를 DI받는다.
	@Autowired
	private SqlSession session;
	
	@Override
	public List<MemberDto> getList() {
		
		//SqlSession 객체를 이용해서 회원 목록을 얻어옴
		List<MemberDto> list= session.selectList("member.getList");
		return list;
	}
	
	@Override
	public MemberDto getData(int num) {
		MemberDto dto=session.selectOne("member.getData", num);
		return dto;
	}

	@Override
	public void insert(MemberDto dto) {
		//Mapper 의 namespace:member, sql의 id:insert, parameterType :MemberDto
		session.insert("member.insert", dto);
	}

	@Override
	public int update(MemberDto dto) {
		//session.update("member.update", dto);
		//수정 반영하고 수정반영된 row의 갯수를 리턴(성공하면 1, 실패하면 0)
		return session.update("member.update", dto);
		
	}

	@Override
	public int delete(int num) {
		//삭제하고 삭제된 row의 갯수를 리턴(성공하면 1, 실패하면 0)
		return session.delete("member.delete", num);
		
	}


}
