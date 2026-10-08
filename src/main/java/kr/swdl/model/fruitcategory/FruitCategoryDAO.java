package kr.swdl.model.fruitcategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.swdl.model.DBCPTest;

public class FruitCategoryDAO {
	// 전체 카테고리 목록 메서드
	public List<FruitCategoryVO> getAllCategories(){
		List<FruitCategoryVO> lists = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBCPTest.getConnection();
			// 전체를 가져오는거라 하드코딩함
			String sql = "SELECT fruit_category_no, item_code, item_name, kind_name, origin, storage_date FROM fruit_category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FruitCategoryVO vo = new FruitCategoryVO();
				vo.setFruitCategoryNo(rs.getString("fruit_category_no"));
				vo.setItemCode(rs.getInt("item_code"));
				vo.setItemName(rs.getString("item_name"));
				vo.setKindName(rs.getString("kind_name"));
				vo.setOrigin(rs.getString("origin"));
				vo.setStorageDate(rs.getInt("storage_date"));
				lists.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPTest.close(conn, pstmt, rs);
		} return lists;
	}
}
