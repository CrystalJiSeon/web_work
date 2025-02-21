package com.example.spring06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring06.dto.MemberDto;
import com.example.spring06.repository.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	
	@Override
	public List<MemberDto> findAll() {
		//회원 목록을 얻어와서 리턴
		List<MemberDto> list=dao.getList();
		return list;
	}

	@Override
	public MemberDto findById(int num) {
		//번호를 이용해서 회원 1명의 정보를 얻어와서 리턴
		MemberDto dto=dao.getData(num);
		return dto;
		
	}

	@Override
	public void save(MemberDto dto) {
	
			dao.insert(dto);
		
	}

	@Override
	public void update(MemberDto dto) {
		
		int rowCount=dao.update(dto);//dao 에서 SQLException이 발생하는 경우
		if(rowCount==0) {
			throw new RuntimeException("수정할 회원 정보가 없습니다");
		}
	}

	@Override
	public void deleteById(int num) {

		int rowCount=dao.delete(num);
		if(rowCount==0) {
			throw new RuntimeException("삭제할 회원이 존재하지 않습니다");
		}
		
	}

}
