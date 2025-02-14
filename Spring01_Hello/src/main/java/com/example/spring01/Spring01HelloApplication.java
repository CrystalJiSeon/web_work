package com.example.spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.spring01.service.HomeService;

import jakarta.annotation.PostConstruct;
/*
 * 	@SpringBootApplication 어노테이션이 많은 기능을 하는데, 그 중 하나는 이 클래스(Spring01HelloApplication)클래스로 객체를 생성해서 관리를 해주는 것.
 *  이 아래의 클래스로도 생성자 선언 없이 객체가 생성되어 있다는 것.
 */
@SpringBootApplication
public class Spring01HelloApplication {

	
	//Spring01HelloApplication 객체 안에서 필요한 객체가 있다면 필드로 선언해두고, 스프링빈컨테이너로부터 @Autowired 어노테이션을 붙여놓은 필드에 해당 객체가 주입(DI)된다.
	@Autowired
	HomeService service;
	
	//메소드에 붙는 어노테이션 PostConstruct 가 붙으면, 실행했을 때 필드에 객체가 @Autowired로 자동 주입이 되고, PostConstruct 붙은 메소드가 실행된다.
	@PostConstruct
	public void exec() {
		System.out.println("생성 후 exec()메소드 호출됨");
		service.clean("나");
		service.wash("우리들");
		service.hole("벽");
		
	}
	
	public static void main(String[] args) {
		//run()메소드가 리턴하는 객체(이 객체를 Spring Bean Container라고 생각하면 된다)
		ApplicationContext context = SpringApplication.run(Spring01HelloApplication.class, args);
		
		
		//"이름1"의 집을 청소해야 한다면 어떻게 해야 할까? 일단은 HomeServiceImpl 객체가 필요한데, 늘 하던대로 
		//HomeServiceImpl service1= new HomeServiceImpl();
		//service1.clean("이름1");
		
		//@Component를 함으로써 Spring이 생성하고 관리하는 객체 중 HomeService 타입(인터페이스 타입)의 객체를 getBean()하고 변수에 담는다
	    HomeService service2 = context.getBean(HomeService.class);
	    service2.clean("이름2");
		
	}

}
