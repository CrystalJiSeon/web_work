package com.example.spring10.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("postDto") //mapper xml에서 PostDto 타입을 편하게 선언하기 위해서
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
	private long num;
	private String writer;
	private String title;
	private String content;
	private int viewCount;
	private String createdAt;
	private String updatedAt;
	//페이징 처리할 때 필요한 필드
	private int startRowNum;
	private int endRowNum;
	private String condition;//검색 조건
	private String keyword;//검색 키워드 : writer 또는 title, 또는 title+content
	private long prevNum;//이전글 글번호
	private long nextNum;//다음글 글번호
}
