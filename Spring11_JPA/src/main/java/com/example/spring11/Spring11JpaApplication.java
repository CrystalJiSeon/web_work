package com.example.spring11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.spring11.dto.EmpDeptDto;
import com.example.spring11.dto.GalleryDto;
import com.example.spring11.entity.Gallery;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.EmpRepository;
import com.example.spring11.repository.GalleryRepository;
import com.example.spring11.repository.MemberRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@SpringBootApplication
public class Spring11JpaApplication {

	@Autowired	MemberRepository memberRepo;
	@Autowired GalleryRepository galleryRepo;
	@Autowired EmpRepository empRepo;
	//JpaRepository를 사용하지 않고 직접 DB를 사용하기 위한 객체
	@Autowired EntityManagerFactory emf;

	@PostConstruct
	public void initEmpDept() {
		/*
		 *  emp, dept 셈플 데이터를 JPQL 을 이용해서 넣어주기 
		 */
		
		//EntityManager 객체 얻어내서 
		EntityManager em=emf.createEntityManager();
		//하나의 트랜젝션을 시작한다 
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			List<String> queries = new ArrayList<>();

			queries.add("INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');");
			queries.add("INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');");
			queries.add("INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');");
			queries.add("INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7369,'SMITH','CLERK',7902,parsedatetime('17-12-1980','dd-MM-yyyy'),800,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7499,'ALLEN','SALESMAN',7698,parsedatetime('20-02-1981','dd-MM-yyyy'),1600,300,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7521,'WARD','SALESMAN',7698,parsedatetime('22-02-1981','dd-MM-yyyy'),1250,500,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7566,'JONES','MANAGER',7839,parsedatetime('02-04-1981','dd-MM-yyyy'),2975,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7654,'MARTIN','SALESMAN',7698,parsedatetime('28-09-1981','dd-MM-yyyy'),1250,1400,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7698,'BLAKE','MANAGER',7839,parsedatetime('01-05-1981','dd-MM-yyyy'),2850,NULL,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7782,'CLARK','MANAGER',7839,parsedatetime('09-06-1981','dd-MM-yyyy'),2450,NULL,10);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7788,'SCOTT','ANALYST',7566,parsedatetime('13-07-1987','dd-MM-yyyy'),3000,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7839,'KING','PRESIDENT',NULL,parsedatetime('17-11-1981','dd-MM-yyyy'),5000,NULL,10);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7844,'TURNER','SALESMAN',7698,parsedatetime('08-09-1981','dd-MM-yyyy'),1500,0,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7876,'ADAMS','CLERK',7788,parsedatetime('13-07-1987','dd-MM-yyyy'),1100,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7900,'JAMES','CLERK',7698,parsedatetime('03-12-1981','dd-MM-yyyy'),950,NULL,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7902,'FORD','ANALYST',7566,parsedatetime('03-12-1981','dd-MM-yyyy'),3000,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7934,'MILLER','CLERK',7782,parsedatetime('23-01-1982','dd-MM-yyyy'),1300,NULL,10);");

			//반복문 돌면서 실행할 쿼리를 얻어내서 
			for (String query : queries) {
				//직접 실행한다 
			    em.createNativeQuery(query).executeUpdate();
			}

			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		empRepo.findAll().stream().map(EmpDeptDto::toDto).forEach(System.out::println);
	}
	
	@PostConstruct public void galleryTest() {
		Gallery g1= Gallery.builder().title("그림제목").uploader("올린사람").build();
		galleryRepo.save(g1);
	
		List<GalleryDto> list = galleryRepo.findAll().stream().map(GalleryDto::toDto).toList();
		list.stream().forEach(item->{
			//galleryDto에는 @Data 어노테이션이 붙어 있어서 객체에 저장된 내용을 확인할 수 있다.
			System.out.println(item);
		});
		System.out.println("--------------------------------------------");
		list.stream().forEach(System.out::println);//위의 내용을 이 한줄로 줄일 수 있다.
		
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			Gallery g4= Gallery.builder().uploader("올린사람 4").title("제목4").build();
			em.persist(g4);//EntityManager 객체를 이용해서 Gallery 객체를 영속화(영구적으로 저장, DB 저장) 하겠다.
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	@PostConstruct public void memberTest() { //DB에 저장할 MemberEntity 객체를 생성해서
	Member m1=Member.builder().name("이름1").addr("주소1").build();
	Member m2=Member.builder().name("이름2").addr("주소2").build(); 
	Member m3=Member.builder().name("이름3").addr("주소3").build(); //DB에 저장하기
	memberRepo.save(m1); 
	memberRepo.save(m2); 
	memberRepo.save(m3); 
	}
	

	public static void main(String[] args) {
		SpringApplication.run(Spring11JpaApplication.class, args);
	}

	// 서버가 준비되었을 때 실행할 메소드 설정
	@EventListener(ApplicationReadyEvent.class)
	public void openChrome() {
		String url = "http://localhost:9000/spring11/";
		// 운영체제의 이름을 소문자로
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder builder = null;
		try {
			if (os.contains("win")) {
				builder = new ProcessBuilder("cmd.exe", "/c", "start chrome " + url);
			} else if (os.contains("mac")) {
				builder = new ProcessBuilder("/user/bin/open", "-a", "Google Chrome", url);
			} else {
				System.out.println("지원하지 않는 운영체제입니다.");
				return;
			}
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
