package com.example.spring12.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring12.entity.Post;
//JpaRepository<Entity클래스타입, id역할을 하는 칼럼의 타입>
//이렇게 인터페이스를 설정하는 것만으로 
//PostRepository의 구현 클래스가 자동으로 만들어지고 
//해당 클래스로 생성된 객체가 bean으로 관리된다. => Dao 역할
public interface PostRepository extends JpaRepository<Post, Long> {

}
