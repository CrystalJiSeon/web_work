package com.example.spring10.service;



import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;

public interface FileService {
	public FileDto toDto(String title, MultipartFile myFile);
	public int uploadFile(FileDto dto);
	public int deleteFile(long num);
	public int editFile(String title, MultipartFile myFile, long num);
	public FileDto getData(long num);
	public FileDto getFileList(FileDto search, int pageNum);
	public ResponseEntity<InputStreamResource> downloadFile(FileDto dto);
	public void manageDownloadCount(long fileNum);
}
