package com.example.spring10.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.PostDto;
import com.example.spring10.exception.DeniedException;
import com.example.spring10.repository.CommentDao;
import com.example.spring10.repository.FileDao;
import com.example.spring10.repository.PostDao;

@Component //bean으로 만들기 위한 어노테이션
@Aspect //aspect 역할을 하기 위한 어노테이션
public class AuthAspect {
	
	@Autowired private PostDao postDao;
	@Autowired private CommentDao commentDao;
	@Autowired private FileDao fileDao;
	//메소드의 리턴 타입은 모두 가능하고, 아래 패키지 속에 속해 있는 모든 클래스 중에서 delete로 이름이 시작하는 모든 메소드의 모든 .. 매개변수
	@Around("execution(* com.example.spring10.service.*.delete*(..))|| execution(* com.example.spring10.service.*.update*(..))") //어떤 값을 들고 있는지 모르니까
	public Object checkWriter(ProceedingJoinPoint joinPoint) throws Throwable {
		//aop가 적용된 메소드명 얻어내기
		String methodName=joinPoint.getSignature().getName();
		System.out.println(methodName+"메소드에 aop가 적용됨");
		long fnum=0;
		//수정, 삭제 작업을 할 글번호
		long pnum=0;
		//수정, 삭제 작업을 할 댓글 번호
		long cnum=0;
		//작성자
		String writer=null;
		//현재 유저
		

		//매개변수에 전달된 데이터를 Object[]로 얻어내기
		Object[] args= joinPoint.getArgs();
		//반복문 돌면서 원하는 type을 찾는다
		for(Object tmp:args) {
			if(tmp instanceof Long) {
				if(methodName.contains("Post")) {
					pnum=(long)tmp;
					writer=postDao.getData(pnum).getWriter();
				}else if(methodName.contains("Comment")) {
					cnum=(long)tmp;
					writer=commentDao.getData(cnum).getWriter();
				}else if(methodName.contains("File")) {
					fnum=(long)tmp;
					writer=fileDao.getData(fnum).getUploader();
				}
			}else if(tmp instanceof PostDto) {
				pnum=((PostDto)tmp).getNum();
				writer=postDao.getData(pnum).getWriter();
			}else if(tmp instanceof CommentDto) {
				cnum=((CommentDto)tmp).getNum();
				writer=commentDao.getData(cnum).getWriter();
			}else if(tmp instanceof FileDto) {
				fnum=((FileDto)tmp).getNum();
				writer=fileDao.getData(fnum).getUploader();
			}
		}
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new DeniedException("요청이 거부되었습니다.");
		}	
		
		
		/*처음에 if 문 안에 넣었던 부분
		 * //글번호 long num=(long)tmp; //글 작성자와 로그인된 userName과 다르면 Exception을 발생시키고
		 * ExceptionController에서 처리하게 한다. String writer =
		 * postDao.getData(num).getWriter(); String userName =
		 * SecurityContextHolder.getContext().getAuthentication().getName();
		 * if(!writer.equals(userName)) { throw new
		 * DeniedException("요청이 거부되었습니다! 다른 사람의 글을 삭제할 수 없습니다!"); }
		 */
		
		//aop가 적용된 메소드를 실행한다
		Object obj=joinPoint.proceed();
		
		return obj;
	}
}
