package com.example.spring11.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emp {
	@Id
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;
	
	//부서번호는 하나만 있어도 되기 때문에 주석 처리한다.
	//private Integer deptno;
	
	//emp 객체 하나는 사원 한명의 정보, dept 객체 하나는 부서 하나의 정보, 
	//emp 객체 안에 있는 dept 객체에 emp 객체가 가지고 있는 해당 사원의 부서 정보를 가지게 하고 싶다.
	//어떻게 하면 Emp에 dept 정보를 담을 수 있을까?
	@ManyToOne
	@JoinColumn(name="deptno", referencedColumnName = "deptno")
	private Dept dept;//name="deptno는 Emp 테이블의 칼럼명을 결정한다.
	//referencedColumnName="deptno" Dept 테이블의 어떤 칼럼을 참조할지 결정한다.(생략 시 자동으로 @Id 칼럼 참조)
	//즉, 이것이 JPA에서 테이블을 조인하는 방법이다.
}
