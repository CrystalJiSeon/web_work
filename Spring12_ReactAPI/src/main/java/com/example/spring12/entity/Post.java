package com.example.spring12.entity;

import com.example.spring12.dto.PostDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//http://localhost:9000/h2-console로 접속하면 만들어진 DB 테이블을 확인할 수 있다

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	
	//JpaRepository의 .save()메소드는 insert와 update가 겸용이다.
	//id가 null이 아니면 insert 가 아닌 update를 시도하기 때문이다.
	//id가 null이면 insert를 시도한다.
	//dto를 엔티티로 변환해서 리턴하는 static 메소드 만들기
	public static Post toEntity(PostDto dto) {
		return Post.builder().id(dto.getId()==0? null: dto.getId())
				.title(dto.getTitle())
				.author(dto.getAuthor()).build();
	}
}
