package kr.swdl.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FruitDAO {
	private Connection conn;

	public FruitDAO(Connection conn) {
		this.conn = conn;
	}
	
	// 과일상품 입고 요청
    public boolean addFruitProduct(String name, int price, int weight,
            int quantity, String fruitCategoryNo,String fruitProdectDate, String adminNo,
            String memberId, String receivedDate, String storageDate) {

        boolean result = false;

        try {

            PreparedStatement pstmt = conn.prepareStatement(Query.ADD_FRUIT_PRODUCT);

            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, weight);
            pstmt.setInt(4, quantity);
            pstmt.setString(5, fruitProdectDate);
            pstmt.setString(6, receivedDate);
            pstmt.setString(7, storageDate);
            pstmt.setString(8, fruitCategoryNo);
            pstmt.setString(9, adminNo);
            pstmt.setString(10, memberId);		

            result = pstmt.executeUpdate() == 1;

            pstmt.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return result;
    }
    
    public List<FruitRequestVO> getStockInsAdmin(String startDate, String endDate) throws SQLException {
        List<FruitRequestVO> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(Query.GET_FRUIT_PRODUCT_REQUESTS)) {
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FruitRequestVO fvo = new FruitRequestVO();
                    fvo.setName(rs.getString(1));
                    fvo.setQuantity(rs.getInt(2));
                    fvo.setPrice(rs.getInt(3));
                    fvo.setExpectedAmount(rs.getLong(4));
                    fvo.setTotalExpectedAmount(rs.getLong(5));
                    fvo.setRequestDate(rs.getString(6));
                    fvo.setFruitProductDate(rs.getString(7));
                    fvo.setStatus(rs.getString(8));
                    list.add(fvo);
                }
            }
        }
        return list;
    }
    public List<FruitRequestVO> getStockIns(String memberId, String startDate, String endDate) throws SQLException {
        List<FruitRequestVO> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(Query.GET_MY_PENDING_FRUIT_PRODUCTS)) {
        	pstmt.setString(1, memberId);
        	pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FruitRequestVO fvo = new FruitRequestVO();
                    fvo.setFruitNo(rs.getString(1));
                    fvo.setName(rs.getString(2));
                    fvo.setQuantity(rs.getInt(3));
                    fvo.setPrice(rs.getInt(4));
                    fvo.setExpectedAmount(rs.getInt(5));
                    fvo.setTotalExpectedAmount(rs.getInt(6));
                    fvo.setMemberName(rs.getString(7));
                    fvo.setmName(rs.getString(8));
                    fvo.setRequestDate(rs.getString(9));
                    fvo.setFruitProductDate(rs.getString(10));
                    fvo.setStatus(rs.getString(11));
                    
                    
                    list.add(fvo);
                }
            }
        }
        return list;
    }
    
    public List<FruitDetailVO> getStockInDetail(String fruitNo) throws SQLException {
    	List<FruitDetailVO> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(Query.GET_FRUIT_PRODUCT)) {
            pstmt.setString(1, fruitNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    FruitDetailVO fdvo = new FruitDetailVO();
                    fdvo.setItemCode(rs.getInt(1));
                    fdvo.setItemName(rs.getString(2));
                    fdvo.setKindName(rs.getString(3));
                    fdvo.setName(rs.getString(4));
                    fdvo.setOrigin(rs.getString(5));
                    fdvo.setPrice(rs.getInt(6));
                    fdvo.setQuantity(rs.getInt(7));
                    fdvo.setWeight(rs.getDouble(8));
                    fdvo.setTotalPrice(rs.getLong(9));
                    fdvo.setMemberRealName(rs.getString(10));
                    fdvo.setMemberName(rs.getString(11));
                    fdvo.setAddress(rs.getString(12));
                    fdvo.setPhone(rs.getString(13));
                    fdvo.setEmail(rs.getString(14));
                    fdvo.setAdminName(rs.getString(15));
                    list.add(fdvo);
                }
            }
        }
        return list;
    }
}
