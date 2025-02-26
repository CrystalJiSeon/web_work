package com.example.spring10.service;

import com.example.spring10.dto.UserDto;


public interface UserSerivce {
		public UserDto getByNum(long num);
		public UserDto getByUserName(String userName);
		public void createUser(UserDto dto);
		public void updateUserInfo(UserDto dto);
		public void changePassword(UserDto dto);

}
