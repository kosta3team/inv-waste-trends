package kr.swdl.model.waste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WasteCategoryDAO {
	private Connection conn;

	public WasteCategoryDAO(Connection conn) {
		this.conn = conn;
	}

	public WasteCategoryVO getWasteCategory(String wasteCategoryNo) {
		WasteCategoryVO vo = null;

		String sql = "SELECT waste_category_reason "
				+ "FROM waste_category "
				+ "WHERE waste_category_no = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wasteCategoryNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next(); 
			vo = new WasteCategoryVO(wasteCategoryNo, rs.getString("waste_category_reason"));	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}
}
