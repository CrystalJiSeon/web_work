package com.example.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.FileDto;
import com.example.spring10.service.FileService;

@Controller
public class FileController {
	
	@Autowired private FileService service;
	
	@GetMapping("/file/list")
	public String listFile(Model model, FileDto search,@RequestParam(defaultValue="1") int pageNum) {	
		FileDto dto = service.getFileList(search, pageNum);
		model.addAttribute("dto", dto);
		return "file/list";
	}
	
	@GetMapping("/file/new")
	public String newFile() {
		return "file/new";
	}
	@PostMapping("/file/upload")
	public String uploadFile(String title, MultipartFile myFile, RedirectAttributes ra) {
		FileDto dto= service.toDto(title, myFile);
		service.uploadFile(dto);
		boolean uploadSuccess=false;
		System.out.println(dto);
		if(dto!=null) {
			ra.addFlashAttribute("uploadSuccess", "파일이 성공적으로 업로드되었습니다.");
		}
		System.out.println(uploadSuccess);
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/delete")
	public String deleteFile(long num, RedirectAttributes ra) {
		service.deleteFile(num);
		ra.addFlashAttribute("deleteSuccess", "파일이 성공적으로 삭제되었습니다.");
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/edit")
	public String updateFileForm(long num, Model model) {
		model.addAttribute("num", num);
		return "file/edit";
	}
	
	@PostMapping("/file/update")
	public String updateFile(String title, MultipartFile myFile, long num, Model model, RedirectAttributes ra) {
		service.editFile(title, myFile, num);
		ra.addFlashAttribute("editSuccess", "파일이 성공적으로 수정되었습니다.");
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> downloadFile(long num) {
		FileDto dto= service.getData(num);
		System.out.println(dto);
		
		return service.downloadFile(dto);
	}
}
