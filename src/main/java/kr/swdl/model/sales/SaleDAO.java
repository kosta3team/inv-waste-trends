package kr.swdl.model.sales;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.swdl.model.Query;

public class SaleDAO {

	private Connection conn;

	public SaleDAO(Connection conn) {
		this.conn = conn;
	}

	private static final DateTimeFormatter DATE_FORMATTER =
	        DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	
	private String formatSaleDate(ResultSet rs) throws SQLException {
	    Date sqlDate = rs.getDate("sales_date");

	    return sqlDate.toLocalDate().format(DATE_FORMATTER);
	}
	/**
	 * 조합원이 본인의 판매기록의 전체를 조회
	 */
	public List<SaleVO>  findAllSalesByMember(String memberId) {

		List<SaleVO> list = new ArrayList<SaleVO>();

	
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER_SALES);
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
						formatSaleDate(rs)));
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


	
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER_SALES_BY_PERIOD);
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
						formatSaleDate(rs)));
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

	

		try {
			Statement stmt = conn.createStatement();	
			ResultSet rs =  stmt.executeQuery(Query.GET_ADMIN_SALES);
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
						formatSaleDate(rs)));
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
		List<SaleVO> saleList = new ArrayList<SaleVO>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_ADMIN_SALES_BY_PERIOD);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);

			ResultSet rs =  pstmt.executeQuery();
			while (rs.next()) {
				saleList.add(new SaleVO(
						rs.getString("fruit_no"), 
						rs.getString("sale_no"),
						rs.getString("name"),
						rs.getString("member_name"),
						rs.getString("name"),
						rs.getInt("quantity"), 
						rs.getInt("price"), 
						rs.getInt("total_price"),
						formatSaleDate(rs)));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return saleList;	
	}

	/**
	 * 관리자 현재 보이는 판매 기록 각 총액의 총 합 (보이는 것 기준)
	 */
	public int getSalesTotalPrice() {
		int totalPrice = 0;
		
		try {
			Statement stmt = conn.createStatement();	
			ResultSet rs =  stmt.executeQuery(Query.GET_ADMIN_SALES_TOTAL_PRICE);
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
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_ADMIN_SALES_TOTAL_PRICE_BY_PERIOD);	
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
	
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER_SALES_TOTAL_PRICE);	
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

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER_SALES_TOTAL_PRICE_BY_PERIOD);	
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
