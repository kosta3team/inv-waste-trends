package kr.swdl.model.waste;

import java.sql.SQLException;
import java.util.List;

import kr.swdl.model.DBCP;

public class WasteCategoryService {

	public List<WasteCategoryVO> getWateCategoryList() {
		try {
			return new WasteCategoryDAO(DBCP.getConnection()).getWasteCategoryList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
