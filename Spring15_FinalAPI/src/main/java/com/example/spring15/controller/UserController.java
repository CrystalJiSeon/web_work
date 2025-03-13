package com.example.spring15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring15.dto.UserDto;
import com.example.spring15.util.JwtUtil;

@RestController
public class UserController {
	@Autowired JwtUtil jwtUtil;
	@Autowired AuthenticationManager authManager;
	
	
	@PostMapping("/auth") 
	public ResponseEntity<String> auth(@RequestBody UserDto dto) throws Exception{
		System.out.println("hi");
		Authentication authentication=null;
		try {
			UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
			//인증 매니저객체를 이용해서 인증을 진행한다
			authentication= authManager.authenticate(authToken);
			
		}catch(Exception e){
			//예외가 발생하면 인증실패(아이디 혹은 비밀번호 틀림 등등...)
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패!!");
		}
		
		// Authentication 객체에는 인증된 사용자 정보가 들어 있다.
		// userName, Role, 등의 정보
		// 현재는 role을 하나만 부여하기 때문에 0번방에 있는 데이터만 불러오면 된다.
		GrantedAuthority authority = authentication.getAuthorities().stream().toList().get(0);
		// ROLE_XXX형식
		String role = authority.getAuthority();
		// "role"이라는 키값으로 Map 에 담기
		Map<String, Object> claims = Map.of("role", role);
		
		//예외가 발생하지 않고 여기까지 실행된다면 인증을 통과한 것이다. 토큰을 발급해서 응답한다.
		String token = jwtUtil.generateToken(dto.getUserName(), claims);
		//발급받은 토큰 문자열을 ReponseEntity에 담아서 리턴한다.
		return ResponseEntity.ok("Bearer "+token);
	}
}
