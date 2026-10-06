package kr.swdl.test;

import static org.junit.Assert.*;

//import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.swdl.model.fruitproduct.*;
import kr.swdl.model.DBCPTest;

public class FruitProductJunitTest {
	
	private kr.swdl.model.fruitproduct.FruitProductDAO dao = new FruitProductDAO();
	
	@Before
	public void setUp() throws SQLException {
		DBCPTest.beginTestTransaction();
	}
	
	@After
	public void testShutDown() {
		DBCPTest.rollbackTestTransaction();
	}
	
	@Test
	public void testAllDaoMethod() {
		// 1. 조합원 입고 요청
		FruitProductVO vo = new FruitProductVO();
		vo.setName("꼬깔콘");
		vo.setPrice(30000);
		vo.setWeight(5.0);
		vo.setQuantity(50);
//		vo.setStorageDate(Date.valueOf("2026-10-01"));
		vo.setFruitCategoryNo("fc0001");
		vo.setMemberId("member001");
		
		assertTrue("1. 조합원 입고요청 성공여부", dao.addFruitProduct(vo));
		
		// 2. 관리자 기본 전체 입고요청목록 조회
		List<FruitProductVO> adminLists = dao.getFruitProductRequests();
		assertTrue("2. 관리자 기본 전체 입고요청목록 조회", adminLists.size() >0);
		
		// 3. 관리자 기간 전체 입고요청목록 조회
		List<FruitProductVO> adminListsPeriod = dao.getFruitProductRequestsPeriod("2026-01-01", "2026-12-31");
		assertTrue("3. 관리자 기간 전체 입고요청목록 조회", adminListsPeriod.size() > 0);
		
		// 4. 조합원 기본 전체 입고요청목록 조회
		List<FruitProductVO> memberLists = dao.getMyPendingFruitProducts("member001");
		assertTrue("4. 조합원 기본 전체 입고요청목록 조회", memberLists.size() > 0);
		
		// 5. 조합원 기간 전체 입고요청목록 조회
		List<FruitProductVO> memberListsPeriod = dao.getMyPendingFruitProductsPeriod("member001", "2026-01-01", "2026-12-31");
		assertTrue("5. 조합원 기간 전체 입고요청목록 조회", memberListsPeriod.size() > 0);
		
		// 6. 관리자 기본 입고요청상태만 목록 조회
		List<FruitProductVO> adminListsReq = dao.getPendingFruitProducts();
		assertTrue("6. 관리자 기본 입고요청상태만 목록 조회", adminListsReq.size() > 0);
		
		// 7. 관리자 기간 입고요청상태만 목록 조회
		List<FruitProductVO> adminListsReqPeriod = dao.getMyPendingFruitProductsPeriod("member001", "2026-01-01", "2026-12-31");
		assertTrue("7. 관리자 기간 입고요청상태만 목록 조회", adminListsReqPeriod.size() > 0);
		
		// 8. 공통 입고 요청한 상품정보 상세조회
		// 8-1 테스트 더미용 상품번호
		String testFruitNo = adminLists.get(0).getFruitNo();		
		// 8-2 기본 코드
		FruitProductVO detail = dao.getFruitProduct(testFruitNo);
		assertTrue("8. 공통 입고 요청한 상품정보 상세조회", detail != null);
		
		// 9. 관리자 입고 요청을 거절
		assertTrue("9. 관리자 입고 요청을 거절", dao.rejectFruitProduct("admin001", testFruitNo));
		
		// 10. 관리자 입고 요청을 승인
		assertTrue("10. 관리자 입고 요청을 승인", dao.approveFruitProduct("admin001", testFruitNo));
	}

}
