--가입된 회원정보를 저장할 테이블
CREATE TABLE users(
	num NUMBER PRIMARY KEY,
	userName VARCHAR2(100) UNIQUE,
	password VARCHAR2(100) NOT NULL,
	email VARCHAR2(100) UNIQUE,
	profileImage VARCHAR2(100),
	role VARCHAR2(10) DEFAULT 'USER' CHECK (role IN ('USER', 'STAFF', 'ADMIN')),
	createdAt DATE DEFAULT SYSDATE,
	updatedAt DATE DEFAULT SYSDATE
);


--회원 번호를 얻어낼 시퀀스 
CREATE SEQUENCE users_seq;


--글 목록을 저장할 테이블
CREATE TABLE posts(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	createdAt DATE DEFAULT SYSDATE,
	updatedAt DATE DEFAULT SYSDATE
);
--글 번호를 얻어낼 시퀀스
CREATE SEQUENCE posts_seq;

-- 어떤 세션에서 몇번글을 읽었는지 정보를 저장할 테이블
CREATE TABLE readed_data(
	num NUMBER REFERENCES posts(num),
	session_id VARCHAR2(50)
);


create table files(
num number primary key,
uploader varchar2(100) not null,
title varchar2(100) not null,
orgFileName varchar2(100) not null,
savefilename varchar2(100) not null,
filesize number not null,
uploadedat date)
  
create sequence files_seq;

Sequence created.

CREATE TABLE downloaded_data(
	num NUMBER REFERENCES files(num),
	session_Id VARCHAR2(50)
);

