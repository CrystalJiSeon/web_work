package com.example.spring01.service;

import org.springframework.stereotype.Component;

@Component // spring 이 component scan을 통해 해당 클래스로 객체를 생성해서 관리하도록 어노테이션을 붙임. 즉, 이 어노테이션을 붙임으로써 얘도 Spring Bean이 된다. 
public class DrillImpl implements Drill {
	@Override
	public void hole() {
		System.out.println("(드드드드듣ㄱ)");
	}
}
