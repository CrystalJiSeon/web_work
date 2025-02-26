package com.example.spring10.repository;

import com.example.spring10.dto.UserDto;

public interface UserDao {
	//메소드는 같은 이름으로 overloading 할 수 있다
	public UserDto getData(long num);
	public UserDto getData(String userName);
	public int insert(UserDto dto);
	public int updatePassword(UserDto dto);
	public int update(UserDto dto);
	
}
