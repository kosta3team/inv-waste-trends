package test.kr.swdl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import kr.swdl.model.DBCP;
import kr.swdl.model.inventory.InventoryDAO;
import kr.swdl.model.inventory.InventoryVO;


public class InventoryDAOTest {

	private static Connection conn;

	@BeforeClass
	public static void 클래스_사전동작() throws SQLException {

		conn = DBCP.getConnection();

		System.out.println("클래스 사전동작 - InventoryDAO 테스트");
	}




//	// 과일상품일련번호 상세조회
//	@Test
//	public void 과일상품일련번호_상세조회_관리자_조합원() throws SQLException {
//
//		InventoryDAO dao = new InventoryDAO(conn);
//
//		InventoryVO inventory  = dao.getFruitProductDetail("fc0001");
//
//        assertNotNull(inventory);
//
//
//
//			System.out.println(
//					inventory.getFruitCategoryNo() + " / "
//							+ inventory.getItemCode() + " / "
//							+ inventory.getKindName() + " / "
//							+ inventory.getOrigin() + " / "
//							+ inventory.getItemName() + " / "
//							+ inventory.getProductName() + " / "
//							+ inventory.getPrice() + " / "
//							+ inventory.getWeight() + " / "
//							+ inventory.getInventoryDate() + " / "
//							+ inventory.getInventoryQuantity() + " / "
//							+ inventory.getRemainQuantity() + " / "
//							+ inventory.getCoopName() + " / "
//							+ inventory.getInventoryMemberName() + " / "
//							+ inventory.getInventoryAdminName() + " / "
//							+ inventory.getStorageDate() + " / "
//							+ inventory.getWasteQuantity() + " / "
//							+ inventory.getWasteReqDate() + " / "
//							+ inventory.getWasteDate() + " / "
//							+ inventory.getWasteReasonDetail() + " / "
//							+ inventory.getDailyRainfall() + " / "
//							+ inventory.getMaxTemp() + " / "
//							+ inventory.getAvgTemp() + " / "
//							+ inventory.getMinTemp() + " / "
//							+ inventory.getWasteMemberName() + " / "
//							+ inventory.getWasteAdminName()
//
//
//					);
//		}
//	
//
//
//

//	@Test
//	public void 판매중재고_선택조회_관리자() throws SQLException {
//
//		InventoryDAO dao = new InventoryDAO(conn);
//
//		List<InventoryVO> list = dao.getNormalProducts();
//
//		assertNotNull(list);
//		assertTrue(list.size() > 0);
//
//		for (InventoryVO inventory : list) {
//
//			System.out.println(
//					inventory.getFruitNo() + " / "
//							+ inventory.getProductName() + " / "
//							+ inventory.getRemainQuantity() + " / "
//							+ inventory.getPrice() + " / "
//							+ inventory.getCoopName() + " / "
//							+ inventory.getStorageDate() + " / "
//							+ inventory.getWasteDate() + " / "
//							+ inventory.getWasteCategoryReason() + " / "
//							+ inventory.getStatus()
//
//
//					);
//		}
//	}






	//	@Test
	//	public void 폐기재고_선택조회_관리자() throws SQLException {
	//
	//		InventoryDAO dao = new InventoryDAO(conn);
	//
	//		List<InventoryVO> list = dao.getWastedProducts();
	//
	//		assertNotNull(list);
	//		assertTrue(list.size() > 0);
	//
	//		for (InventoryVO inventory : list) {
	//
	//			System.out.println(
	//					inventory.getFruitNo() + " / "
	//							+ inventory.getProductName() + " / "
	//							+ inventory.getRemainQuantity() + " / "
	//							+ inventory.getPrice() + " / "
	//							+ inventory.getCoopName() + " / "
	//							+ inventory.getStorageDate() + " / "
	//							+ inventory.getWasteDate() + " / "
	//							+ inventory.getWasteCategoryReason() + " / "
	//							+ inventory.getStatus()
	//
	//
	//					);
	//		}
	//	}


	//	@Test
	//	public void 협동조합원명을_조회_관리자() throws SQLException {
	//
	//		InventoryDAO dao = new InventoryDAO(conn);
	//
	//		List<InventoryVO> list = dao.getInventoryByMemberName("종현");
	//
	//		assertNotNull(list);
	//		assertTrue(list.size() > 0);
	//
	//		for (InventoryVO inventory : list) {
	//
	//			System.out.println(
	//					inventory.getFruitNo() + " / "
	//							+ inventory.getProductName() + " / "
	//							+ inventory.getRemainQuantity() + " / "
	//							+ inventory.getPrice() + " / "
	//							+ inventory.getCoopName() + " / "
	//							+ inventory.getStorageDate() + " / "
	//							+ inventory.getWasteDate() + " / "
	//							+ inventory.getWasteCategoryReason() + " / "
	//							+ inventory.getStatus()
	//
	//
	//					);
	//		}
	//	}
	//	
	//	


	//	@Test
	//	public void 상품명을_조회_관리자() throws SQLException {
	//
	//		InventoryDAO dao = new InventoryDAO(conn);
	//
	//		List<InventoryVO> list = dao.getInventoryByName("");
	//
	//		assertNotNull(list);
	//		assertTrue(list.size() > 0);
	//
	//		for (InventoryVO inventory : list) {
	//
	//			System.out.println(
	//					inventory.getFruitNo() + " / "
	//							+ inventory.getProductName() + " / "
	//							+ inventory.getRemainQuantity() + " / "
	//							+ inventory.getPrice() + " / "
	//							+ inventory.getCoopName() + " / "
	//							+ inventory.getStorageDate() + " / "
	//							+ inventory.getWasteDate() + " / "
	//							+ inventory.getWasteCategoryReason() + " / "
	//							+ inventory.getStatus()
	//
	//
	//					);
	//		}
	//	}
	//	
	//	
	//	
	//	@Test
	//	public void 상품명을_조회() throws SQLException {
	//
	//		InventoryDAO dao = new InventoryDAO(conn);
	//
	//		List<InventoryVO> list = dao.getInventoryByName("");
	//
	//		assertNotNull(list);
	//		assertTrue(list.size() > 0);
	//
	//		for (InventoryVO inventory : list) {
	//
	//			System.out.println(
	//					inventory.getFruitNo() + " / "
	//							+ inventory.getProductName() + " / "
	//							+ inventory.getRemainQuantity() + " / "
	//							+ inventory.getPrice() + " / "
	//							+ inventory.getCoopName() + " / "
	//							+ inventory.getStorageDate() + " / "
	//							+ inventory.getWasteDate() + " / "
	//							+ inventory.getWasteCategoryReason() + " / "
	//							+ inventory.getStatus()
	//
	//
	//					);
	//		}
	//	}
	//	
	//		
	//	
	//	@Test
	//	public void 전체_재고목록을_조회() throws SQLException {
	//
	//		InventoryDAO dao = new InventoryDAO(conn);
	//
	//		List<InventoryVO> list = dao.getInventory();
	//
	//		assertNotNull(list);
	//		assertTrue(list.size() > 0);
	//
	//		for (InventoryVO inventory : list) {
	//
	//			System.out.println(
	//					inventory.getFruitNo() + " / "
	//							+ inventory.getProductName() + " / "
	//							+ inventory.getRemainQuantity() + " / "
	//							+ inventory.getPrice() + " / "
	//							+ inventory.getCoopName() + " / "
	//							+ inventory.getStorageDate() + " / "
	//							+ inventory.getWasteDate() + " / "
	//							+ inventory.getWasteCategoryReason() + " / "
	//							+ inventory.getStatus()
	//
	//
	//					);
	//		}
	//	}
}
