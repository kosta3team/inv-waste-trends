package kr.swdl.model.waste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.swdl.model.Query;

public class WasteCategoryDAO {
	private Connection conn;

	public WasteCategoryDAO(Connection conn) {
		this.conn = conn;
	}

	public WasteCategoryVO getWasteCategory(String wasteCategoryNo) {
		WasteCategoryVO vo = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_WASTE_CATEGORY);
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
	
	public List<WasteCategoryVO> getWasteCategoryList() {
		List<WasteCategoryVO> list = new ArrayList();


		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Query.GET_WASTE_CATEGORY_LIST);
			while(rs.next()) {
				list.add(new WasteCategoryVO(rs.getString(1), rs.getString(2)));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
