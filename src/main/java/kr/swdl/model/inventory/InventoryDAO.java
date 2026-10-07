package kr.swdl.model.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.swdl.model.Query;

public class InventoryDAO {

	private Connection conn;

	public InventoryDAO(Connection conn) {
		this.conn = conn;
	}

	 // 과일상품일련번호 상세조회
	public InventoryVO getFruitProductDetail(String fruitNo) {
		InventoryVO inventoryVO = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_FRUIT_PRODUCT_DETAIL);

			pstmt.setString(1, fruitNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				inventoryVO = new InventoryVO(
						rs.getString("fruit_category_no"), 
						rs.getString("item_code"), 
						rs.getString("kind_name"),
						rs.getString("origin"), 
						rs.getString("item_name"), 
						rs.getString("name"), 
						rs.getInt("price"),
						rs.getDouble("weight"),  
						rs.getString("received_date"), 
						rs.getInt("quantity"),  
						rs.getInt("remain_quantity"),  
						rs.getString("member_name"),
						rs.getString("member_name_detail"), 
						rs.getString("admin_name"), 
						rs.getString("storage_date"), 
						rs.getInt("waste_quantity"),
						rs.getString("waste_req_date"),
						rs.getString("waste_date"), 
						rs.getString("reason_detail"), 
						rs.getDouble("daily_rainfall"), 
						rs.getDouble("max_temp"), 
						rs.getDouble("avg_temp"), 
						rs.getDouble("min_temp"),  
						rs.getString("waste_member_name"),
						rs.getString("waste_admin_name")
						);
			}

			rs.close();
			pstmt.close();




		} catch (SQLException e) {
			e.printStackTrace();
		}



		return inventoryVO ;



	}





	// 판매중인재고 선택조회
	public List<InventoryVO> getNormalProducts(){
		List<InventoryVO> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_NORMAL_PRODUCTS);


			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(new InventoryVO(
						rs.getString("fruit_no"),
						rs.getString("name"),
						rs.getInt("remain_quantity"),
						rs.getInt("price"),
						rs.getString("member_name"),
						rs.getString("storage_date"),
						rs.getString("waste_date"),
						rs.getString("waste_category_reason"),
						rs.getString("status")
						));
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}


		return list;


	}




	// 폐기재고 선택조회
	public List<InventoryVO> getWastedProducts(){
		List<InventoryVO> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_WASTED_PRODUCTS);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(new InventoryVO(
						rs.getString("fruit_no"),
						rs.getString("name"),
						rs.getInt("remain_quantity"),
						rs.getInt("price"),
						rs.getString("member_name"),
						rs.getString("storage_date"),
						rs.getString("waste_date"),
						rs.getString("waste_category_reason"),
						rs.getString("status")
						));
			}

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return list;


	}





	// 협동 조합원명으로 조회
	public List<InventoryVO> getInventoryByMemberName(String coopName){

		List<InventoryVO> list = new ArrayList<>();

		try {

			PreparedStatement pstmt =
					conn.prepareStatement(Query.GET_INVENTORY_BY_MEMBER);

			pstmt.setString(1, coopName);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(new InventoryVO(
						rs.getString("fruit_no"),
						rs.getString("name"),
						rs.getInt("remain_quantity"),
						rs.getInt("price"),
						rs.getString("member_name"),
						rs.getString("storage_date"),
						rs.getString("waste_date"),
						rs.getString("waste_category_reason"),
						rs.getString("status")
						));
			}

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}







	// 상품명을 조회(관리자)
	public List<InventoryVO> getInventoryByNameAdmin(String productName){
		List<InventoryVO> list = new ArrayList<>();

		try {

			PreparedStatement pstmt =
					conn.prepareStatement(Query.GET_INVENTORY_BY_NAME);

			pstmt.setString(1, productName);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(new InventoryVO(
						rs.getString("fruit_no"),
						rs.getString("name"),
						rs.getInt("remain_quantity"),
						rs.getInt("price"),
						rs.getString("member_name"),
						rs.getString("storage_date"),
						rs.getString("waste_date"),
						rs.getString("waste_category_reason"),
						rs.getString("status")
						));
			}

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}




	// 상품명을 조회
	public List<InventoryVO> getInventoryByName(String productName) {

		List<InventoryVO> list = new ArrayList<>();

		try {

			PreparedStatement pstmt =
					conn.prepareStatement(Query.GET_INVENTORY_BY_NAME);

			pstmt.setString(1, productName);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(new InventoryVO(
						rs.getString("fruit_no"),
						rs.getString("name"),
						rs.getInt("remain_quantity"),
						rs.getInt("price"),
						rs.getString("member_name"),
						rs.getString("storage_date"),
						rs.getString("waste_date"),
						rs.getString("waste_category_reason"),
						rs.getString("status")
						));
			}

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}




	// 전체 재고 목록을 조회
	public List<InventoryVO> getInventory(){
		List<InventoryVO> list = new ArrayList<InventoryVO>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_INVENTORY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(new InventoryVO(
						rs.getString("fruit_no"),
						rs.getString("name"),
						rs.getInt("remain_quantity"),
						rs.getInt("price"),
						rs.getString("member_name"),
						rs.getString("storage_date"),
						rs.getString("waste_date"),
						rs.getString("waste_category_reason"),
						rs.getString("status")
						));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}



		return list;


	}

}
