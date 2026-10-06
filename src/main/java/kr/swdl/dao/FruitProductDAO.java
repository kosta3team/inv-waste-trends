package kr.swdl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.swdl.query.FruitProductQuery;
import kr.swdl.util.DBUtil;
import kr.swdl.vo.AdminVO;
import kr.swdl.vo.FruitCategoryVO;
import kr.swdl.vo.FruitProductVO;
import kr.swdl.vo.MemberVO;

public class FruitProductDAO {
	
	// 1. 조합원 입고 요청
	public boolean addFruitProduct(FruitProductVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.ADD_FRUIT_PRODUCT);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setDouble(3, vo.getWeight());
			pstmt.setInt(4, vo.getQuantity());
//			pstmt.setDate(5, vo.getStorageDate());
			pstmt.setString(5, vo.getFruitCategoryNo());			
			pstmt.setString(6, vo.getMemberId());
			
			if (pstmt.executeUpdate() == 1) {
				result = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		} return result;
	}
	
	// 2. 관리자 기본 전체 입고요청목록 조회
	public List<FruitProductVO> getFruitProductRequests(){
		List<FruitProductVO> lists = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.GET_FRUIT_PRODUCT_REQUESTS);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FruitProductVO vo = new FruitProductVO();
				vo.setFruitNo(rs.getString("상품번호"));
				vo.setName(rs.getString("상품명"));
				vo.setQuantity(rs.getInt("입고수량"));
				vo.setPrice(rs.getInt("단가"));
				vo.setTotalPrice(rs.getInt("총판매예상금액"));
				
				MemberVO mv = new MemberVO();
				mv.setMemberName(rs.getString("조합원명"));
				mv.setName(rs.getString("이름"));
				vo.setMember(mv);
				
				vo.setRequestDate(rs.getDate("요청일자"));
				vo.setFruitProductDate(rs.getDate("처리일자"));
				vo.setStatus(rs.getString("요청상태"));
				
				lists.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		} return lists;
	}
	
	// 3. 관리자 기간 전체 입고요청목록 조회
	public List<FruitProductVO> getFruitProductRequestsPeriod(String startDate, String endDate){
		List<FruitProductVO> lists = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.GET_FRUIT_PRODUCT_REQUESTS_PERIOD);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FruitProductVO vo = new FruitProductVO();
				vo.setFruitNo(rs.getString("상품번호"));
				vo.setName(rs.getString("상품명"));
				vo.setQuantity(rs.getInt("입고수량"));
				vo.setPrice(rs.getInt("단가"));
				vo.setTotalPrice(rs.getInt("총판매예상금액"));
				
				MemberVO mv = new MemberVO();
				mv.setMemberName(rs.getString("조합원명"));
				mv.setName(rs.getString("이름"));
				vo.setMember(mv);
				
				vo.setRequestDate(rs.getDate("요청일자"));
				vo.setFruitProductDate(rs.getDate("처리일자"));
				vo.setStatus(rs.getString("요청상태"));
				
				lists.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		} return lists;
	}
	
	// 4. 조합원 기본 전체 입고요청목록 조회
	public List<FruitProductVO> getMyPendingFruitProducts (String memberId){
		List<FruitProductVO> lists = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.GET_MY_PENDING_FRUIT_PRODUCTS);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FruitProductVO vo = new FruitProductVO();
				
				vo.setFruitNo(rs.getString("상품번호"));
				vo.setName(rs.getString("상품명"));
				vo.setQuantity(rs.getInt("입고수량"));
				vo.setPrice(rs.getInt("단가"));
				vo.setTotalPrice(rs.getInt("총판매예상금액"));
				vo.setRequestDate(rs.getDate("요청일자"));
				vo.setFruitProductDate(rs.getDate("처리일자"));
				vo.setStatus(rs.getString("요청상태"));
				
				lists.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		} return lists;
	}
	
	// 5. 조합원 기간 전체 입고요청목록 조회
	public List<FruitProductVO> getMyPendingFruitProductsPeriod (String memberId, String startDate, String endDate){
		List<FruitProductVO> lists = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.GET_MY_PENDING_FRUIT_PRODUCTS_PERIOD);
			pstmt.setString(1, memberId);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FruitProductVO vo = new FruitProductVO();
				
				vo.setFruitNo(rs.getString("상품번호"));
				vo.setName(rs.getString("상품명"));
				vo.setQuantity(rs.getInt("입고수량"));
				vo.setPrice(rs.getInt("단가"));
				vo.setTotalPrice(rs.getInt("총판매예상금액"));
				vo.setRequestDate(rs.getDate("요청일자"));
				vo.setFruitProductDate(rs.getDate("처리일자"));
				vo.setStatus(rs.getString("요청상태"));
				
				lists.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		} return lists;
	}
	
	// 6. 관리자 기본 입고요청상태만 목록 조회
	public List<FruitProductVO> getPendingFruitProducts(){
		List<FruitProductVO> lists = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.GET_PENDING_FRUIT_PRODUCTS);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FruitProductVO vo = new FruitProductVO();
								
				vo.setFruitNo(rs.getString("상품번호"));
				vo.setName(rs.getString("상품명"));
				vo.setQuantity(rs.getInt("입고수량"));
				vo.setPrice(rs.getInt("단가"));
				vo.setTotalPrice(rs.getInt("총판매예상금액"));
				
				MemberVO mv = new MemberVO();
				mv.setMemberName(rs.getString("조합원명"));
				mv.setName(rs.getString("이름"));
				vo.setMember(mv);
				
				vo.setRequestDate(rs.getDate("요청일자"));
				vo.setFruitProductDate(rs.getDate("처리일자"));
				vo.setStatus(rs.getString("요청상태"));
				
				lists.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		} return lists;
	}
	
	// 7. 관리자 기간 입고요청상태만 목록 조회
	public List<FruitProductVO> getPendingFruitProductsPeriod(String startDate, String endDate){
		List<FruitProductVO> lists = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.GET_PENDING_FRUIT_PRODUCTS_PERIOD);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FruitProductVO vo = new FruitProductVO();
				
				vo.setFruitNo(rs.getString("상품번호"));
				vo.setName(rs.getString("상품명"));
				vo.setQuantity(rs.getInt("입고수량"));
				vo.setPrice(rs.getInt("단가"));
				vo.setTotalPrice(rs.getInt("총판매예상금액"));
				
				MemberVO mv = new MemberVO();
				mv.setMemberName(rs.getString("조합원명"));
				mv.setName(rs.getString("이름"));
				vo.setMember(mv);
				
				vo.setRequestDate(rs.getDate("요청일자"));
				vo.setFruitProductDate(rs.getDate("처리일자"));
				vo.setStatus(rs.getString("요청상태"));
				
				lists.add(vo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, pstmt, rs);
		} return lists;
	}
	
	// 8. 공통 입고 요청한 상품정보 상세조회
	public FruitProductVO getFruitProduct(String fruitNo) {
		FruitProductVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.GET_FRUIT_PRODUCT);
			pstmt.setString(1, fruitNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				vo = new FruitProductVO();
				vo.setRequestDate(rs.getDate("요청일자"));
				
				FruitCategoryVO fc = new FruitCategoryVO();
				fc.setItemCode(rs.getString("품목코드"));
				fc.setItemName(rs.getString("품목"));
				fc.setKindName(rs.getString("품종"));
				vo.setName(rs.getString("상품명"));
				fc.setOrigin(rs.getString("원산지"));
				vo.setFruitCategory(fc);
				
				vo.setPrice(rs.getInt("단가"));
				vo.setQuantity(rs.getInt("입고수량"));
				vo.setWeight(rs.getDouble("중량"));
				vo.setTotalPrice(rs.getInt("총판매예상금액"));
				
				MemberVO mv = new MemberVO();
				mv.setMemberName(rs.getString("조합원명"));
				mv.setName(rs.getString("이름"));
				mv.setAddress(rs.getString("주소"));
				mv.setPhone(rs.getString("전화번호"));
				mv.setEmail(rs.getString("이메일"));
				vo.setMember(mv);
				
				AdminVO av = new AdminVO();
				av.setName(rs.getString("입고처리자"));
				vo.setAdmin(av);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		} return vo; 
	}
	
	// 9. 관리자 입고 요청을 거절
	public boolean rejectFruitProduct (String adminId, String fruitNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.REJECT_FRUIT_PRODUCT);
			pstmt.setString(1, adminId);
			pstmt.setString(2, fruitNo);
			
			if (pstmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		} return result;
	}
	// 10. 관리자 입고 요청을 승인
	public boolean approveFruitProduct (String adminId, String fruitNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(FruitProductQuery.APPROVE_FRUIT_PRODUCT);
			pstmt.setString(1, adminId);
			pstmt.setString(2, fruitNo);
			
			if (pstmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		} return result;
		
	}


}

