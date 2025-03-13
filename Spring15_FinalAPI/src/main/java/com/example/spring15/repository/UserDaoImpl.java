package com.example.spring15.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring15.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired private SqlSession session;
	
	@Override
	public UserDto getData(long num) {
		return session.selectOne("user.getByNum", num);
	}// 네임스페이스 : user, SQL 아이디 : getByNum, 파라미터타입: long, resultType: UserDto

	@Override
	public UserDto getData(String userName) {
		return session.selectOne("user.getByUserName", userName);
	}

	@Override
	public int insert(UserDto dto) {
		return session.insert("user.insert", dto);
	
	}

	@Override
	public int updatePassword(UserDto dto) {
		return session.update("user.updatePassword",dto);
	}

	@Override
	public int update(UserDto dto) {
		return session.update("user.update", dto);
	}

}
