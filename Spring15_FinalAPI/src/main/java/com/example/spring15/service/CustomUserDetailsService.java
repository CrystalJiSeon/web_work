package com.example.spring15.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring15.dto.UserDto;
import com.example.spring15.repository.UserDao;


//Spring Security가 로그인 처리시 호출하는 메소드를 가지고 있는 서비스 클래스를 정의함

@Service //bean으로 만들기 위한 어노테이션
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired private UserDao dao;
	
	//Username을 전달하면 해당 user의 자세한 정보를 리턴하는 메소드
	//원래대로라면 DB에서 가져와야 하지만 우리는 샘플 데이터로 해본다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//원래는 DB에서 dao를 이용해 username에 해당하는 사용자 정보(UserDto)를 얻어와야 한다.
		UserDto dto=dao.getData(username);
		//만일 존재하지 않는 사용자라면 
		if(dto==null) {
			throw new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.");
		}
		
		
		//권한 목록을 List 에 담아서  (지금은 1개 이지만)
		List<GrantedAuthority> authList=new ArrayList<>();
		//DB에 ROLE_ 형태로 작성하지 않았으니까 Authority를 넣을 땐 앞에 ROLE_ 붙이기
		authList.add(new SimpleGrantedAuthority("ROLE_"+dto.getRole()));
		
		//UserDetails 객체를 생성해서 
		UserDetails ud=new User(dto.getUserName(), dto.getPassword(), authList);
		//리턴해준다.
		return ud;
	}

}
