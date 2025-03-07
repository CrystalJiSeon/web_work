package com.example.spring10.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.FileDto;


@Repository
public class FileDaoImpl implements FileDao {

	@Autowired private SqlSession session;
	
	@Override
	public int insert(FileDto dto) {
		//파일의 번호는 files_seq라는 이름의 시퀀스로 넣기
		return session.insert("files.insert", dto);
	}

	@Override
	public int update(FileDto dto) {
	
		return session.insert("files.update", dto);
	}

	@Override
	public int delete(long num) {
		return session.delete("files.delete", num);
	}

	@Override
	public FileDto getData(long num) {
		return session.selectOne("files.getData", num);
	}

	@Override
	public List<FileDto> getList(FileDto dto) {
		return session.selectList("files.getList", dto);
	}

	@Override
	public int getSequence(FileDto dto) {
		return 0;
	}

	@Override
	public int getCount(FileDto dto) {
		return session.selectOne("files.getCount", dto);
	}

	@Override
	public long addDownloadCount(long num) {
		return session.insert("files.addDownloadCount", num);
	}

	@Override
	public long getDownloadCount(long num) {
		return session.selectOne("files.countDownload", num);
	
	}

	public int insertDownloadCount(long num, long count) {
		Map<String, Object> map = Map.of("num", num, "count", count);
		return session.update("files.insertDownloadCount", map);
	}
	
}
