package com.example.spring13;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.spring13.util.JwtUtil;

import jakarta.annotation.PostConstruct;
//custom.properties 파일의 위치를 알려서 로딩하기
@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Spring13JwtApplication {

	@Autowired JwtUtil util;
	
	@PostConstruct
	public void generateToken(){
		//토큰에 담을 추가 정보(디코딩하면 알아낼 수 있음)
		Map<String, Object> claims = Map.of("role","USER", "email", "aaa@naver.com");
		//userName을 전달해서 토큰을 발급 받기
		String token=util.generateToken("nameplace", claims);
		// 토큰을 콘솔창에 출력해보기
		System.out.println(token);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Spring13JwtApplication.class, args);
	}

}
