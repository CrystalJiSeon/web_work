package com.example.spring10.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.dto.CommentListRequest;
import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.exception.DeniedException;
import com.example.spring10.repository.CommentDao;
import com.example.spring10.repository.PostDao;

@Service
public class PostServiceImpl implements PostService{
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	
	@Autowired private PostDao postDao;
	@Autowired private CommentDao commentDao;

	//pageNum과 검색 조건, 키워드가 담겨 있을 수 있는 PostDto를 전달하면 해당 글 목록을 리턴하는 메소드
	@Override
	public PostListDto getPosts(int pageNum, PostDto search) {
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=postDao.getCount(search);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		//startRownum과 endrownum을 PostDto 객체에 담아서 
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		//글 목록 얻어오기
		List<PostDto> list= postDao.getList(search);
		
		String findQuery="";
		if(search.getKeyword() !=null) {
			findQuery="&keyword="+search.getKeyword()+"&condition="+search.getCondition();
		}
		
		PostListDto dto=PostListDto.builder()
						.list(list)
						.startPageNum(startPageNum)
						.endPageNum(endPageNum)
						.totalPageCount(totalPageCount)
						.pageNum(pageNum)
						.totalRow(totalRow)
						.findQuery(findQuery)
						.condition(search.getCondition())
						.keyword(search.getKeyword())
						.build();
		
		return dto;
		
	}


	@Override
	public long createPost(PostDto dto) {
		//글 작성자 = 로그인된 유저
		String writer= SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		//저장할 글 번호를 미리 얻어옴
		long num = postDao.getSequence();
		//dto 에 글번호를 넣은 다음 DB에 저장
		dto.setNum(num);
		postDao.insert(dto);
		//글번호를 리턴해줌
		return num;
	}


	@Override
	public PostDto getByNum(long num) {
		return postDao.getData(num);
		
	}


	@Override
	public PostDto getDetail(PostDto dto) {
		return postDao.getDetail(dto);
	}


	@Override
	public void updatePost(PostDto dto) {
		postDao.update(dto);
		
	}


	@Override
	public void delete(long num) {
		/*
		 * //글 작성자와 로그인된 userName과 다르면 Exception을 발생시키고 ExceptionController에서 처리하게 한다.
		 * String writer = postDao.getData(num).getWriter(); String userName =
		 * SecurityContextHolder.getContext().getAuthentication().getName();
		 * if(!writer.equals(userName)) { throw new
		 * DeniedException("요청이 거부되었습니다! 다른 사람의 글을 삭제할 수 없습니다!"); }
		 */
		//post의 num을 참조하는 자식 레코드를 미리 삭제
		postDao.deleteReaded(num);
		//글 삭제
		postDao.delete(num);
		
	}


	@Override
	public void manageViewCount(long num, String sessionId) {
		//이미 읽었는지 여부를 얻어낸다 
		boolean isReaded=postDao.isReaded(num, sessionId);
		if(!isReaded){
			//글 조회수도 1 증가 시킨다
			postDao.addViewCount(num);
			//이미 읽었다고 표시한다. 
			postDao.insertReaded(num, sessionId);
		}
	}


	@Override
	public void createComment(CommentDto dto) {
		//로그인한 유저 = 작성자
		String writer= SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		//글 번호 가져와서 Comment의 글번호에 저장
		long num = commentDao.getSequence();
		long parentNum=dto.getParentNum();
		if(parentNum==0) {
			//댓글의 글번호가 parentNum이 됨
			parentNum=num;
		}
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setParentNum(parentNum);
		//dao를 이용해서 DB에 저장
		commentDao.insert(dto);

	}


	@Override
	public void updateComment(CommentDto dto) {
		commentDao.update(dto);
		
	}


	@Override
	public void deleteComment(long num) {
		commentDao.delete(num);
		
	}


	@Override
	public Map<String, Object> getComments(CommentListRequest clr) {
		CommentDto dto=new CommentDto();
		dto.setPostNum(clr.getPostNum());
		/*
		[ 댓글 페이징 처리에 관련된 로직 ]
		*/
		//한 페이지에 댓글을 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=10;
		int pageNum=clr.getPageNum();
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//계산된 값을 dto 에 담는다
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//전체 댓글의 갯수
		int totalRow=commentDao.getCount(clr.getPostNum());
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		//pageNum 에 해당하는 댓글 목록을 얻어낸다.
		List<CommentDto> list=commentDao.getList(dto);
		//Gson 객체에 전달할 Map 객체를 구성한다.
		Map<String, Object> map=new HashMap<>();
		map.put("list", list);
		map.put("totalPageCount", totalPageCount);
		return map;
	}


	/*@Override
	public List<CommentDto> getCommentList(CommentDto postcomment) {
		//댓글 목록 얻어오기
		List<CommentDto> list= commentDao.getList(postcomment);
		
		PostListDto dto=PostListDto.builder()
						.list(list)
						.startPageNum(startPageNum)
						.endPageNum(endPageNum)
						.totalPageCount(totalPageCount)
						.pageNum(pageNum)
						.totalRow(totalRow)
						.findQuery(findQuery)
						.condition(search.getCondition())
						.keyword(search.getKeyword())
						.build();
		
		return dto;
		
	}*/
}
