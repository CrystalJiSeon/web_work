package com.example.spring11.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.spring11.entity.Gallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GalleryDto {
	private long id;
	private String uploader;
	private String title;
	private String createdAt;//Dto의 날짜는 보통 String type으로 선언하다.
	
	//Entity를 dto로 만들어서 리턴하는 static 메소드
	public static GalleryDto toDto(Gallery gallery) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy.MM.dd E a hh:mm:ss", Locale.KOREA);
		//한국 기준 sysdate 형식의 문자열을 얻어낼 수 있는 객체
		String result =sdf.format(gallery.getCreatedAt());
		//테스트 위해서 콘솔창에 찍어보기
		System.out.println(result);
		
		return GalleryDto.builder().id(gallery.getId()).uploader(gallery.getUploader())
				.title(gallery.getTitle()).build();
	}
}
