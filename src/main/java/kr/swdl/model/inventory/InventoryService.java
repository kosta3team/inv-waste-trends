package kr.swdl.model.inventory;

import java.sql.SQLException;
import java.util.List;

import kr.swdl.model.DBCP;

public class InventoryService {
	// 과일상품일련번호 상세조회
		public InventoryVO getFruitProductDetail(String fruitNo) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getFruitProductDetail(fruitNo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	 
						// 판매중인재고 선택조회
		public List<InventoryVO> getNormalProducts() {
			try {
				return new InventoryDAO(DBCP.getConnection()).getNormalProducts();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		// 폐기재고 선택조회
		public List<InventoryVO> getWastedProducts() {
			try {
				return new InventoryDAO(DBCP.getConnection()).getWastedProducts();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		// 협동 조합원명으로 조회
		public List<InventoryVO> getInventoryByMemberName(String coopName) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getInventoryByMemberName(coopName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		// 상품명을 조회
		public List<InventoryVO> getInventoryByName(String productName) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getInventoryByName(productName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		// 전체 재고 목록을 조회
		public List<InventoryVO> getInventory(int page) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getInventory();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
	 
		
	 
		// 판매중인재고 조회 (회원)
		public List<InventoryVO> getNormalProducts(String memberId) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getNormalProducts(memberId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		// 폐기재고 조회 (회원)
		public List<InventoryVO> getWastedProducts(String memberId) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getWastedProducts(memberId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		// 상품명 조회 (회원)
		public List<InventoryVO> getInventoryByName(String memberId, String productName) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getInventoryByName(memberId, productName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		// 전체 재고 목록 조회 (회원)
		public List<InventoryVO> getInventory(String memberId, int page) {
			try {
				return new InventoryDAO(DBCP.getConnection()).getInventory(memberId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

}
