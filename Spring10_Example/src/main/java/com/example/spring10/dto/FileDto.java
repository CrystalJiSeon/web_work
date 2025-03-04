package com.example.spring10.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {
	private long num;
	private String uploader;
	private String title;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	private String uploadedAt;
	private long downloadCount;
	
	//파일 업로드 폼에 있는  input type="file" 의 name 속성의 value 와 필드명이 일치해야한다.
	// <input type="file" name="myFile">
	private MultipartFile myFile;
	
	//페이징 처리에 필요한 필드
	private int startRowNum;
	private int endRowNum;
	private String condition;//검색 조건
	private String keyword;//검색 키워드 : writer 또는 title, 또는 title+content
	private long prevNum;//이전글 글번호
	private long nextNum;//다음글 글번호
	//FileListDto 를 따로 만들지 않고 여기다가 만들었음
	private List<FileDto> list;
	private int pageNum;
	private int startPageNum;
	private int endPageNum;
	private int totalRow;
	private int totalPageCount;
	private String findQuery;
}
