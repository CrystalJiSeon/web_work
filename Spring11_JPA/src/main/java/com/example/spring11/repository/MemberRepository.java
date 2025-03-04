package com.example.spring11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring11.entity.Member;

//아래와 같이 선언만 해도 Repository가 자동으로 완성된다.
//extedns JpaRepositroy<Entity Type, Entity에서 아이디 역할을 하는 필드의Type>
public interface MemberRepository extends JpaRepository<Member, Integer> {
	//번호에 대해서 내림차순 정렬을 해서 select 하는 메소드를 추가하려면 이곳에 한다.
	public List<Member> findAllByOrderByNumDesc();
	/*
	 * findAllByOrderBy칼럼명Desc(); 
	 * findAllByOrderBy칼럼명Asc(); 
	 * 칼럼명은 Camel 케이스로 작성하기
	 */
	
	/*
	 * Java Persistence Query Language(JPQL) 
	 * -sql과 유사하지만 엔티티와 속성에 기반하여 작성되며, 데이터베이스 종속적이지 않음 
	 * -jpql만의 문법이 존재 
	 * -entity의 네임은 @Entity 어노테이션이 붙어있는 클래스에서 붙인 이름(name)
	 * -Entity의 별칭은 필수 
	 * -select 된 row의 정보를 Entity 혹은 Dto에 담을 수 있다.
	 * 
	 */
	
	@Query(value="SELECT m from MEMBER_INFO m Order by m.num desc")//JPA 패키지에 있는 어노테이션, 
	public List<Member> getList();//메소드 명은 마음대로 규칙없이 지을 수 있음

	
}
