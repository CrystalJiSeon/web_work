package com.example.spring07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring07.dto.FileDto;

@Controller
public class FileController {
	//custom.properties 파일이 로딩되기 위해서는 main 메소드가 있는 클래스에
	//Spring07FileUploadApplication에 @PropertiesSource 어노테이션 설정이 되어 있어야 함
	//custom.properties 파일에 있는 파일 업로드 경로를 읽어와서 필드에 담아둘 수 있다.
	@Value("${file.location}")
	private String fileLocation;
	
	
	//ResponseEntity<InputStreamResource>는 파일을 다운로드할 때 사용하는 리턴 타입이다.
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(String orgFileName, String saveFileName, long fileSize){
		//원래는 DB 에서 읽어와야 하지만 지금은 다운로드해줄 파일의 정보가 요청 파라미터로 전달된다.
		try {
			//다운로드 시켜줄 원본 파일명
			String encodedName=URLEncoder.encode(orgFileName, "utf-8");
			//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
			encodedName=encodedName.replaceAll("\\+"," ");
			//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers=new HttpHeaders();
			//파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
			//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			//파일의 크기 정보도 담아준다.
			headers.setContentLength(fileSize);
			//읽어들일 파일의 경로 구성
			String filePath=fileLocation + File.separator + saveFileName;
			
			//파일에서 읽어들일 스트림 객체
			InputStream is =new FileInputStream(filePath);
			//InputStreamResource 객체의 참조값 얻어내기
			InputStreamResource isr=new InputStreamResource(is);
			//ResponseEntity 객체를 구성해서
			ResponseEntity<InputStreamResource> resEntity=ResponseEntity.ok().headers(headers).body(isr);
			//리턴해주면 파일이 다운로드 된다.
			return resEntity;
			
		}catch(Exception e) {
			//예외 정보를 콘솔에 출력
			e.printStackTrace();
			//예외 발생시키기
			throw new RuntimeException("파일을 다운로드하는 중에 에러가 발생했습니다.");
		}
	}
	
	
	//<input type="file" name="myFile">에 대해서
	//MultipartFile myFile 로 해서 업로드된 파일의 정보를 자동으로 임시 폴더에 넣어넣고 그 파일의 정보를 담아서 얻어내야 한다.
	// 즉, 우리는 그 임시 폴더를 우리가 원하는 위치로 이동시켜야 하기도 하고, 파일의 정보도 얻을 수 있다.
	@PostMapping("/file/upload")
	public String upload(String title, MultipartFile myFile, Model model) {
		
		//null 체크
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않았습니다");
		}
		
		//원본 파일 이름
		String orgFileName= myFile.getOriginalFilename();
		//파일의 크기
		long fileSize = myFile.getSize();
		//저장할 파일의 이름을 Universal Unique한 문자열로 얻어내기
		String saveFileName= UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			//업로드된 파일을 저장할 파일 객체 생성
			File saveFile=new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//원래는 DB에 저장해야 하지만 테스트를 위해서 view 페이지에 전달해서 출력한다.
		model.addAttribute("title",title);
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("fileSize", fileSize);
		model.addAttribute("saveFileName", saveFileName);
		
		//System.out.println("title:"+title);
		//System.out.println("원본파일명"+myFile.getOriginalFilename());
		//System.out.println("파일의 크기:" +myFile.getSize());
		return "file/upload";
	}
	
	@PostMapping("/file/upload2")
	public String upload2(FileDto dto, Model model) {
		//FileDto 객체에서 MultipartFile 객체를 얻어낸다.
		MultipartFile myFile= dto.getMyFile(); 
		
		//null 체크
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않았습니다");
		}
		
		//원본 파일 이름
		String orgFileName= myFile.getOriginalFilename();
		//파일의 크기
		long fileSize = myFile.getSize();
		//저장할 파일의 이름을 Universal Unique한 문자열로 얻어내기
		String saveFileName= UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			//업로드된 파일을 저장할 파일 객체 생성
			File saveFile=new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//원래는 DB에 저장해야 하지만 테스트를 위해서 view 페이지에 전달해서 출력한다.
		model.addAttribute("title",dto.getTitle());
		model.addAttribute("orgFileName", dto.getOrgFileName());
		model.addAttribute("fileSize", dto.getFileSize());
		model.addAttribute("saveFileName", dto.getSaveFileName());
		
		//System.out.println("title:"+title);
		//System.out.println("원본파일명"+myFile.getOriginalFilename());
		//System.out.println("파일의 크기:" +myFile.getSize());
		return "file/upload";
	}
	
	
	@GetMapping("/file/new")
	public String newForm() {
		
		return "file/new";
	}
}
