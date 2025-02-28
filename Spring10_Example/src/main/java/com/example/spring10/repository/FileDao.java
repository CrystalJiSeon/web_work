package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.FileDto;

public interface FileDao {
	public int insert(FileDto dto);		
	public int update(FileDto dto);
	public int delete(long num);
	public FileDto getData(long num);
	public List<FileDto> getList(FileDto dto);
	public int getSequence(FileDto dto);
	public int getCount(FileDto dto);
}
