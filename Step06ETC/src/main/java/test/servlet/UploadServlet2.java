package test.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet("/file/upload2")
@MultipartConfig(
		fileSizeThreshold = 1024*1024*5,//메모리 임계값 (메모리를 효율적으로 쓰기 위한 값, 이 값을 넘어가면 파일로 전환함)
		maxFileSize=1024*1024*50, //최대 파일 사이즈 1킬로바이트(=1024바이트)*1킬로바이트*50 =50메가 바이트
		maxRequestSize=1024*1024*60//최대 요청 사이즈 
		)
public class UploadServlet2 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//업로드될 실제 경로 얻어내기
		String uploadPath=getServletContext().getRealPath("/upload");
		File uploadDir=new File(uploadPath);
		//만일 upload 폴더가 존재하지 않으면
		if(!uploadDir.exists()) {
			uploadDir.mkdir();//실제로 폴더 만들기
		}
		
		String title=req.getParameter("title");
		
		//파일명이 겹치지 않게 저장하기 위한 랜덤한 문자열을 얻어내기
		String uid=UUID.randomUUID().toString();
		System.out.println(uid);
		String origFileName=null;
		String saveFileName=null;
		
		//파일 데이터 처리는 좀 다르다
		Part filePart = req.getPart("myImg");
		if(filePart !=null) {
			//파일 이름 얻어내기
			origFileName=filePart.getSubmittedFileName();
			//저장될 파일의 이름 구성하기
			saveFileName=uid+origFileName;
			//저장할 파일의 경로 구성
			String filePath=uploadPath+File.separator+ saveFileName;
			//파일 저장
			InputStream is=filePart.getInputStream();
			Files.copy(is, Paths.get(filePath));
		}
		
		//파일의 크기 얻어내기(큰 정수이기 때문에 long type 사용)
		long fileSize=filePart.getSize();
		
		//응답에 필요한 데이터를 request 영역에 담기
		req.setAttribute("title", title);
		req.setAttribute("origFileName", origFileName);
		req.setAttribute("saveFileName",saveFileName );
		req.setAttribute("fileSize", fileSize);
		req.setAttribute("uploadPath", uploadPath);
		//나중에 이걸 리눅스에 올릴 때 uploadPath 확인할 필요가 있어서
		
		//jsp 페이지로 응답을 위임하기(리다이렉트는 컨텍스트 경로가 필요하지만 포워드 이동은 필요 없다)
		RequestDispatcher rd=req.getRequestDispatcher("/file/upload2.jsp");
		rd.forward(req, resp);
	}
	
}
