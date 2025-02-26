package com.example.spring10.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor// 모든 필드값을 전달받는 생성자(Builder를 사용하려면 필요함
@NoArgsConstructor// 빈생성자
@Data// setter, getter, (toString)
public class UserDto {
	private long num;
	private String userName;
	private String password;
	private String newPassword;
	private String email;
	private String role;
	private String profileImage;
	//프로필 파일 업로드 처리를 위한 필드
	private MultipartFile profileFile; 
	
	private String createdAt;
	private String updatedAt;
}
