package com.example.spring10.service;

import java.util.List;

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
	public List<FileDto> getList(FileDto dto);
	public ResponseEntity<InputStreamResource> downloadFile(FileDto dto);
}
