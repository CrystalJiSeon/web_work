package com.example.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 * 스프링 프레임워크가 HomeServiceImpl클래스가 존재하는 패키지(com.example.spring01.service)를
 * component scan 을 하게 되면 이 클래스로 객체를 생성해서 해당 객체의 참조값을 spring bean container에서 관리하게 된다.
 *  - 그렇다면 도대체 어디를 scan 하게 되나?
 *  @SpringBootApplication어노테이션이 붙어있는 클래스가 존재하는 패키지 혹은 하위 패키지를 모두 scan해서 main() 메소드가 존재하는지를 찾는다. 
 */
@Component
public class HomeServiceImpl implements HomeService{//인터페이스를 구현해서 만든 클래스
	
	
	//의존 객체 주입받기(DI). 의존 객체가 무조건 주입되는건 아니고 bean들끼리만 주입이된다.
	@Autowired Drill drill; //한줄로 써도 된다.
	
	
	//생성자
	public HomeServiceImpl() {
		System.out.println("HomeServiceImpl 객체가 생성됨");
	}
	
	@Override
	public void clean(String name) {
		System.out.println(name+"의 집을 청소해요!");
		
	}
	@Override
	public void wash(String name) {
		System.out.println(name+"의 빨래를 돌려요!");
		
	}
	@Override
	public void hole(String name) {
		System.out.println(name+"을/를 드릴로 뚫어요!");
		drill.hole();
		
	}
}
