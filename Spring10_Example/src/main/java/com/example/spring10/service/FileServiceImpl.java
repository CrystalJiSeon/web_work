package com.example.spring10.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;
import com.example.spring10.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired FileDao fileDao;
	@Value("${file.location}") private String fileLocation;
	
	@Override
	public FileDto toDto(String title, MultipartFile myFile) {
		String loggedon=SecurityContextHolder.getContext().getAuthentication().getName();
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일을 선택하지 않았거나 파일이 업로드되지 않았습니다");
		}
		String orgFileName=myFile.getOriginalFilename();
		long fileSize= myFile.getSize();
		String saveFileName=UUID.randomUUID().toString();
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			File saveFile=new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		FileDto dto=FileDto.builder().num(0).uploader(loggedon).title(title)
				.orgFileName(orgFileName).saveFileName(saveFileName).fileSize(fileSize).build();

		return dto;
	}
	

	@Override
	public int uploadFile(FileDto dto) {
		return fileDao.insert(dto);
	}

	@Override
	public int deleteFile(long num) {
		return fileDao.delete(num);
	}

	@Override
	public int editFile(String title, MultipartFile myFile, long num) {
		String loggedon=SecurityContextHolder.getContext().getAuthentication().getName();
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일을 선택하지 않았거나 파일이 업로드되지 않았습니다");
		}
		String orgFileName=myFile.getOriginalFilename();
		long fileSize= myFile.getSize();
		String saveFileName=UUID.randomUUID().toString();
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			File saveFile=new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		FileDto dto=FileDto.builder().num(num).uploader(loggedon).title(title)
				.orgFileName(orgFileName).saveFileName(saveFileName).fileSize(fileSize).build();

		return fileDao.update(dto);
		
	}

	@Override
	public FileDto getData(long num) {
		return fileDao.getData(num);
	}
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	@Override
	public FileDto getFileList(FileDto search, int pageNum) {
		//보여줄 페이지의 시작 ROWNUM
				int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
				//보여줄 페이지의 끝 ROWNUM
				int endRowNum=pageNum*PAGE_ROW_COUNT;
				
				//하단 시작 페이지 번호 
				int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
				//하단 끝 페이지 번호
				int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
				//전체 글의 갯수
				int totalRow=fileDao.getCount(search);
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
				List<FileDto> list= fileDao.getList(search);
				
				String findQuery="";
				if(search.getKeyword() !=null) {
					findQuery="&keyword="+search.getKeyword()+"&condition="+search.getCondition();
				}
				
				FileDto dto=FileDto.builder()
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
	public ResponseEntity<InputStreamResource> downloadFile(FileDto dto) {
		try {
			String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
			encodedName=encodedName.replaceAll("\\+"," ");
			//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers=new HttpHeaders();
			//파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
			//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			//파일의 크기 정보도 담아준다.
			headers.setContentLength(dto.getFileSize());
			//읽어들일 파일의 경로 구성
			String filePath=fileLocation + File.separator + dto.getSaveFileName();
			
			//파일에서 읽어들일 스트림 객체
			InputStream is =new FileInputStream(filePath);
			//InputStreamResource 객체의 참조값 얻어내기
			InputStreamResource isr=new InputStreamResource(is);
			//ResponseEntity 객체를 구성해서
			ResponseEntity<InputStreamResource> resEntity=ResponseEntity.ok().headers(headers).body(isr);
			//리턴해주면 파일이 다운로드 된다.
			return resEntity;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("파일 다운로드 중 오류가 발생했습니다.");
			
		}
		
		
	}



}
