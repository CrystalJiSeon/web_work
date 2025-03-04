package com.example.spring11.service;

import java.util.List;

import com.example.spring11.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> selectAll();
	public void saveMember(MemberDto dto);
	public MemberDto editMember(int num);
	public void deleteMember(int num);
	public void updateMember(MemberDto dto);
}
