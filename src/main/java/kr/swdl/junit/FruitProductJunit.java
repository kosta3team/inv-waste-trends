package kr.swdl.junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.swdl.dao.FruitProductDAO;
import kr.swdl.util.DBUtil;
import kr.swdl.vo.FruitProductVO;

public class FruitProductJunit {

	private FruitProductDAO dao = new FruitProductDAO();
	
	// 테스트 시작전 오토커밋 false
	@Before
	public void setUp() throws SQLException {
		DBUtil.beginTestTransaction();
	}
	
	// 테스트 완료후 
	@After
	public void testShutDown() {
		DBUtil.rollbackTestTransaction();
	}
	
	// 본격 테스트 구문
	@Test
	public void testAllDaoMethod() {
		// 1. 조합원 입고요청 등록
		FruitProductVO vo = new FruitProductVO();
		vo.setName("사키딸기");
		vo.setPrice(30000);
		vo.setWeight(3.0);
		vo.setQuantity(30);
		vo.setStorageDate(Date.valueOf("2026-10-01"));
		vo.setFruitCategoryNo("fc0001");
		vo.setMemberId("member001");
		
		boolean isAdded = dao.addFruitProduct(vo);
		assertTrue("1. 입고요청 등록 성공여부", isAdded);
		
		// 1에서 넣은 자료 조회되는지 확인
		List<FruitProductVO> adminList = dao.getFruitProductRequests();
		assertFalse("2. 목록이 비어있으면 안됨.", adminList.isEmpty());
		
		// 1에서 넣은 데이터 꺼내기
		FruitProductVO insertedItem = adminList.get(0);
		assertEquals("사키딸기", insertedItem.getName());
		String testFruitNo = insertedItem.getFruitNo();
		System.out.println("테스트 중 입시 생성된 번호: "+ testFruitNo + ", 상품명: "+ insertedItem.getName());
		
		// 기간조회 및 대기목록 조회
		assertFalse(dao.getFruitProductRequestsPeriod("2026-01-01", "2026-12-31").isEmpty());
		assertFalse(dao.getMyPendingFruitProducts("member001").isEmpty());
		assertFalse(dao.getMyPendingFruitProductsPeriod("member001", "2026-01-01", "2026-12-31").isEmpty());
		assertFalse(dao.getPendingFruitProducts().isEmpty());
		assertFalse(dao.getPendingFruitProductsPeriod("2026-01-01", "2026-12-31").isEmpty());
		
		// 상세 조회
		FruitProductVO detail = dao.getFruitProduct(testFruitNo);
		assertNotNull("8. 상세조회 객체가 존재해야 함", detail);
		assertEquals("사키딸기", detail.getName());
		
		// 승인 및 거절 처리
		assertTrue("9. 거절 처리 성공 여부", dao.rejectFruitProduct("admin001", testFruitNo));
		assertTrue("10. 승인 처리 성공 여부", dao.approveFruitProduct("admin001", testFruitNo));
	}
	
	
}
