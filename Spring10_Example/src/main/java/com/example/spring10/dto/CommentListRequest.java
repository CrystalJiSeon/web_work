package com.example.spring10.dto;

import lombok.Data;

//댓글 목록 요청시 전달되는 파라미터를 담을 객체를 만들기 위해

@Data//setter getter만 잘 만들어지게 이 어노테이션만 붙인다
public class CommentListRequest {
	private int pageNum;
	private long postNum;
	
}
