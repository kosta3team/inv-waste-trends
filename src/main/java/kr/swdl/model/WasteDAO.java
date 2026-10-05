package kr.swdl.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WasteDAO {

	private Connection conn;

	public WasteDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean addWaste(String reasonDeatil, int quantity, String fruitNo, String memberId, String wasteCategoryNo) {

		boolean result = false;

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.ADD_WASTE_REQ);
			pstmt.setString(1, reasonDeatil);
			pstmt.setInt(2, quantity);
			pstmt.setString(3, fruitNo);
			pstmt.setString(4, memberId);
			pstmt.setString(5, wasteCategoryNo);
			result = (1 == pstmt.executeUpdate());
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;		
	}

	/**
	 * 폐기를 등록할 때, 폐기 수량이 과일의 남읜 수량과 동일하면 
	 * 과일정보의 상태르 (폐기요청(재고소진))으로 변경한다.
	 * 여기에 작성하지만, 이는 과일정보 쪽으로 이동되어야 한다.
	 */
	public boolean updateWasteRequestStatus(String fruitNo, int wasteRequestQuantity) {

		boolean result = false;


		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.SET_FRUIT_STATUS_TO_WASTE_REQ);
			pstmt.setString(1, fruitNo);
			pstmt.setInt(2, wasteRequestQuantity);
			result = (1 == pstmt.executeUpdate());
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;	
	}



	public List<WasteRequestListVO> getMemberWasteList(String memberId) {

		List<WasteRequestListVO> list = new ArrayList();

	

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER_WASTES);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new WasteRequestListVO(
						rs.getString("waste_no"),
						rs.getString("waste_req_date"),
						rs.getString("waste_date")));
			}	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return list;
	}


	public List<WasteRequestListVO> getMemberWasteListOnlyRequest(String memberId) {

		List<WasteRequestListVO> list = new ArrayList();

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER_WASTES_BY_PERIOD);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new WasteRequestListVO(
						rs.getString("waste_no"),
						rs.getString("waste_req_date"),
						rs.getString("waste_date")));
			}	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return list;
	}


	public List<WasteRequestListVO> getWasteList() {

		List<WasteRequestListVO> list = new ArrayList();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Query.GET_ADMIN_WASTES);
			while (rs.next()) {
				list.add(new WasteRequestListVO(
						rs.getString("waste_no"),
						rs.getString("waste_req_date"),
						rs.getString("waste_date"),
						rs.getString("member_name"),
						rs.getString("name")));
			}	
			rs.close();
			stmt .close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return list;
	}

	public List<WasteRequestListVO> getWasteListOnlyRequest() {
		List<WasteRequestListVO> list = new ArrayList();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Query.GET_ADMIN_WASTES_ONLY_REQUEST);
			while (rs.next()) {
				list.add(new WasteRequestListVO(
						rs.getString("waste_no"),
						rs.getString("waste_req_date"),
						rs.getString("waste_date"),
						rs.getString("member_name"),
						rs.getString("name")));
			}	
			rs.close();
			stmt .close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


	public WasteDetailVO getWasteDetail(String wasteNo) {
		WasteDetailVO vo = null;

		

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_WASTE_DETAIL);
			pstmt.setString(1, wasteNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next(); 
			vo = new WasteDetailVO(
					rs.getString(1), 
					rs.getString(2), 
					rs.getString(3), 
					rs.getString(4), 
					rs.getInt(5), 
					rs.getInt(6), 
					rs.getString(7), 
					rs.getString(8),
					rs.getString(9), 
					rs.getString(10),
					rs.getInt(11), 
					rs.getFloat(12), 
					rs.getString(13), 
					rs.getString(14), 
					rs.getString(15)
					);	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}




	public boolean updateWasteDate(String adminId, String wasteNo) {
		boolean result = false;

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.SET_WASTE_REQUEST_APPROVE);
			pstmt.setString(1, adminId);
			pstmt.setString(2, wasteNo);
			result = (1 == pstmt.executeUpdate()); 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 이거도 과일정보 쪽으로 이동이 필요해보임
	 */
	public boolean updateWasteStatus(String fruitNo) {
		boolean result = false;

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.SET_FRUIT_STATUS_TO_WASTE);
			pstmt.setString(1, fruitNo);
			result = (1 == pstmt.executeUpdate()); 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public WasteVO getWaste(String wasteNo) {
		WasteVO vo = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_WASTE_BY_WASTE_NO);
			pstmt.setString(1, wasteNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next(); 
			vo = new WasteVO(
					rs.getString("waste_no"),
					rs.getString("waste_date"),
					rs.getString("waste_req_date"),
					rs.getString("reason_detail"),
					rs.getInt("quantity"),
					rs.getString("fruit_no"),
					rs.getString("member_id"),
					rs.getString("admin_id"),
					rs.getString("waste_category_no"));	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

}
