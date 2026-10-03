package kr.swdl.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

	private Connection conn;

	public SaleDAO(Connection conn) {
		this.conn = conn;
	}



	/**
	 * 조합원이 본인의 판매기록의 전체를 조회
	 */
	public List<SaleVO>  findAllSalesByMember(String memberId) {

		List<SaleVO> list = new ArrayList<SaleVO>();

		String sql = "SELECT fp.fruit_no, s.sale_no, fp.name, s.quantity, fp.price, s.quantity * fp.price AS total_price, s.sales_date "
				+ "FROM fruit_product fp JOIN sales s ON fp.fruit_no = s.fruit_no "
				+ "WHERE fp.member_id = ? ORDER BY s.sales_date";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs =  pstmt.executeQuery();
			while (rs.next()) {
				list.add(new SaleVO(
						rs.getString("fruit_no"), 
						rs.getString("sale_no"),
						rs.getString("name"),
						rs.getInt("quantity"), 
						rs.getInt("price"), 
						rs.getInt("total_price"),
						rs.getString("sales_date")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 조합원이 본인의 판매기록의 기간을 설정해 조회
	 */
	public List<SaleVO>  findSalesPeriodMember(String memberId, String startDay, String endDay) {

		List<SaleVO> list = new ArrayList<SaleVO>();


		String sql = "SELECT "
				+ "fp.fruit_no, "		// 과일 상품 일련번호
				+ "s.sale_no, "			// 판매 일련 번호
				+ "fp.name, "		 	// 과일 상품명
				+ "s.quantity, "		// 판매 수량
				+ "fp.price, "			// 판매 가격
				+ "s.quantity * fp.price AS total_price, " // 판매 총 가격
				+ "s.sales_date "		// 판매 일자
				+ "FROM fruit_product fp "
				+ "JOIN sales s "
				+ "    ON fp.fruit_no = s.fruit_no "
				+ "WHERE fp.member_id = ? "
				+ "AND s.sales_date >= ? "
				+ "AND s.sales_date <= ? "
				+ "ORDER BY s.sales_date";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, startDay);
			pstmt.setString(3, endDay);

			ResultSet rs =  pstmt.executeQuery();
			while (rs.next()) {
				list.add(new SaleVO(
						rs.getString("fruit_no"), 
						rs.getString("sale_no"),
						rs.getString("name"),
						rs.getInt("quantity"), 
						rs.getInt("price"), 
						rs.getInt("total_price"),
						rs.getString("sales_date")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


	/**
	 * 관지자가 전체 판매기록을 조회
	 */
	public List<SaleVO> findSalesPeriodAdmin() {
		List<SaleVO> list = new ArrayList<SaleVO>();

		String sql = "SELECT "
				+ "fp.fruit_no, "
				+ "s.sale_no, "
				+ "fp.name , "
				+ "m.member_name, "
				+ "m.name, "
				+ "s.quantity, "
				+ "fp.price, "
				+ "s.quantity * fp.price AS total_price, "
				+ "s.sales_date "
				+ "FROM fruit_product fp "
				+ "JOIN sales s "
				+ "    ON fp.fruit_no = s.fruit_no "
				+ "JOIN member m "
				+ "    ON fp.member_id = m.member_id "
				+ "ORDER BY s.sales_date";

		try {
			Statement stmt = conn.createStatement();	
			ResultSet rs =  stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(new SaleVO(
						rs.getString("fruit_no"), 
						rs.getString("sale_no"),
						rs.getString("name"),
						rs.getString("member_name"),
						rs.getString("name"),
						rs.getInt("quantity"), 
						rs.getInt("price"), 
						rs.getInt("total_price"),
						rs.getString("sales_date")));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;	
	}

	/**
	 * 관지자가 기간별 판매기록을 조회
	 */
	public List<SaleVO> findSalesByPeriod(String startDay, String endDay) {
		List<SaleVO> list = new ArrayList<SaleVO>();

		String sql = "SELECT "
				+ "fp.fruit_no, "
				+ "s.sale_no, "
				+ "fp.name , "
				+ "m.member_name, "
				+ "m.name, "
				+ "s.quantity, "
				+ "fp.price, "
				+ "s.quantity * fp.price AS total_price, "
				+ "s.sales_date "
				+ "FROM fruit_product fp "
				+ "JOIN sales s "
				+ "    ON fp.fruit_no = s.fruit_no "
				+ "JOIN member m "
				+ "    ON fp.member_id = m.member_id "
				+ "WHERE s.sales_date >= ? "
				+ "AND s.sales_date <= ? "
				+ "ORDER BY s.sales_date";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);

			ResultSet rs =  pstmt.executeQuery();
			while (rs.next()) {
				list.add(new SaleVO(
						rs.getString("fruit_no"), 
						rs.getString("sale_no"),
						rs.getString("name"),
						rs.getString("memberName"),
						rs.getString("name"),
						rs.getInt("quantity"), 
						rs.getInt("price"), 
						rs.getInt("total_price"),
						rs.getString("sales_date")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;	
	}

	/**
	 * 관리자 현재 보이는 판매 기록 각 총액의 총 합 (보이는 것 기준)
	 */
	public int getSalesTotalPrice() {
		int totalPrice = 0;
		String sql = "SELECT SUM(s.quantity * fp.price) AS all_total_price FROM fruit_product fp JOIN sales s ON fp.fruit_no = s.fruit_no";

		try {
			Statement stmt = conn.createStatement();	
			ResultSet rs =  stmt.executeQuery(sql);
			rs.next();
			totalPrice = rs.getInt("all_total_price");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalPrice;	
	}

	/**
	 * 관리자 기간내 판매 기록 금액 총합
	 */
	public int getSalesTotalPriceByPeriod(String startDay, String endDay) {
		int totalPrice = 0;
		String sql = "SELECT SUM(s.quantity * fp.price) AS all_total_price "
				+ "FROM fruit_product fp "
				+ "JOIN sales s ON fp.fruit_no = s.fruit_no "
				+ "WHERE s.sales_date >= ? AND s.sales_date <= ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);			
			ResultSet rs =  pstmt .executeQuery();
			rs.next();
			totalPrice = rs.getInt("all_total_price");
			rs.close();
			pstmt .close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalPrice;	
	}

	/**
	 * 조합원 판매기록 금액 총합
	 */
	public int getSalesTotalPriceByMemberId(String memberId) {
		int totalPrice = 0;
		String sql = "SELECT SUM(s.quantity * fp.price) AS all_total_price "
				+ "FROM fruit_product fp "
				+ "JOIN sales s ON fp.fruit_no = s.fruit_no "
				+ "WHERE fp.member_id = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, memberId);	
			ResultSet rs =  pstmt .executeQuery();
			rs.next();
			totalPrice = rs.getInt("all_total_price");
			rs.close();
			pstmt .close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalPrice;	
	}

	public int getSalesTotalPriceByMemberIdByPeriod(String memberId, String startDay, String endDay) {
		int totalPrice = 0;
		String sql = "SELECT SUM(s.quantity * fp.price) AS all_total_price "
				+ "FROM fruit_product fp JOIN sales s ON fp.fruit_no = s.fruit_no "
				+ "WHERE fp.member_id = ? "
				+ "AND s.sales_date >= ? "
				+ "AND s.sales_date <= ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, memberId);	
			pstmt.setString(2, startDay);	
			pstmt.setString(3, endDay);	
			ResultSet rs =  pstmt .executeQuery();
			rs.next();
			totalPrice = rs.getInt("all_total_price");
			rs.close();
			pstmt .close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalPrice;	
	}
}
