package com.example.spring11.entity;

import com.example.spring11.dto.MemberDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="MEMBER_INFO")//MEMBER_INFO라는 이름의 테이블
public class Member {
	//@Entity 어노테이션으로 테이블이 만들어지고 아래에 나열한 필드명으로 테이블의 칼럼명이 결정된다.
	@Id//primary key 값으로 사용할 필드에 붙인다
	@GeneratedValue(strategy=GenerationType.AUTO)//시퀀스가 자동으로 만들어지고 사용되게 해준다
	private Integer num;//@Id 부터 여기 num;까지가 한줄
	private String name;
	private String addr;
	
	//dto 를 entity로 변환하는 static 메소드
	public static Member toEntity(MemberDto dto) {
		return Member.builder().num(dto.getNum())
					.name(dto.getName()).addr(dto.getAddr()).build();
	}
}
