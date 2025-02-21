package com.example.spring06.repository;

import java.util.List;

import com.example.spring06.dto.TodoDto;

public interface TodoDao {
	public List<TodoDto> getList();
	public TodoDto getData(int id);
	public void insert(TodoDto dto);
	public void update(TodoDto dto);
	public void delete(int id);
	
}
