package test.food.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.food.dto.FoodDto;
import test.util.DbcpBean;

public class FoodDao {

	public boolean update(FoodDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 미완성의 sql 문
			String sql = """
					update food
					set type=?, name=?, price=?
					where num=?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getNum());
			// sql 문 실행하고 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public FoodDto getData(int num) {
		FoodDto dto= null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = """
					select num, type, name, price
					from food
					where num=?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩할게 있으면 여기서 하기
			pstmt.setInt(1, num);
			//sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto=new FoodDto();
				dto.setNum(num);
				dto.setType(rs.getString("type"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dto;
	}
	
	
	public boolean insert(FoodDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 미완성의 sql 문
			String sql = """
						insert into food
						(num, type, name, price)
						values (food_seq.nextval, ?,?,?)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			// sql 문 실행하고 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 미완성의 sql 문
			String sql = """
					delete from food
					where num=?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setInt(1, num);
			// sql 문 실행하고 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}	
	
	public List<FoodDto> getList(){
		List<FoodDto> list = new ArrayList<FoodDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = """
						select num, type, name, price
						from food
						order by num asc
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩할게 있으면 여기서 하기
			
			//sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodDto dto=new FoodDto();
				dto.setNum(rs.getInt("num"));
				dto.setType(rs.getString("type"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
}
