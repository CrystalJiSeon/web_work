package com.example.spring08.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessengerAspect {
	//메소드의 return 타입은 String 이고, com.example.spring08.util 패키지에 속해있는 모든 클래스 중에서 get 으로 시작하는 메소드
	//그리고 메소드의 매개변수는 비어 있는 메소드
	@Around("execution(String com.example.spring08.util.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable{
		//aspect 가 적용된 메소드를 실행하고 해당 메소드가 리턴하는 값을 변수에 담기
		Object obj= joinPoint.proceed(); // getMessage() 메소드가 리턴한 값
		//원래 타입으로 캐스팅
		String returnValue=(String)obj;
		System.out.println("aspect가 적용된 메소드가 리턴한 문자열   " +returnValue);
		//리턴값이 있는 메소드에 aspect 를 적용하면 반드 해당 데이터를 리턴해야 한다.
		//return obj; //getMessage()메소드가 리턴한 값을 리턴
		return "공부라니! 불금이다 놀자! 야호!";
	}
	
	
	//..은 매개변수의 모양을 상관하지 않겠다는 얘기(타입 같은걸 고려하지 않겠다)
	@Around("execution(void send*(..))")
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args= joinPoint.getArgs();
		for(Object tmp:args) {
			//찾는 타입을 확인한다.
			if(tmp instanceof String) {
				//찾았다면 원래 타입으로 Casting 한다.
				String msg=(String)tmp;
				System.out.println("aspect 에서 읽어낸 내용:" +msg);
				if(msg.contains("욕설")) {
					System.out.println("욕설 금지");
					return;//메소드를 여기서 끝내기
				}
			}
		}
		//이 메소드를 호출하는 시점에 실제로 aspect 가 적용된 메소드가 수행된다(이걸 호출하지 않으면 aspect 적용 메소드가 수행되지 않음)
		joinPoint.proceed();
		
	}
}
