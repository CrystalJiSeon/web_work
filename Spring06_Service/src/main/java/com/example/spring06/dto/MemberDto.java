package com.example.spring06.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

//type에 별칭을 부여하고 Mapper.xml 문서에서 별칭을 이용해서
//parameterType과 resultType을 설정할 수 있다.
@Alias("memberDto")
@Setter
@Getter
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
