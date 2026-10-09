package kr.swdl.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.swdl.model.DBCP;
import kr.swdl.model.waste.WasteDAO;
import kr.swdl.model.waste.WasteDetailVO;
import kr.swdl.model.waste.WasteRequestListVO;
import kr.swdl.model.waste.WasteService;
import kr.swdl.model.waste.WasteVO;

public class WasteServiceTest {

	private static Connection conn;
	
	@BeforeClass
	public static void 클래스_사전동작() throws SQLException {
		conn = DBCP.getConnection();
	}
	
	@Before
	public void 단위_테스트_시작() throws Exception {
		conn = DBCP.getConnection();
		conn.setAutoCommit(false); // 자동 확정 해제
	}

	@After
	public void 단위_테스트_종료() throws Exception {
		conn.rollback(); // 실행한 테스트 취소 
		conn.setAutoCommit(true); // 오토커밋 원상 복구
	}

	@Test
	public void 조합원_폐기요청() throws SQLException {
		assertTrue(new WasteDAO(conn).addWaste("관리자의 관리부재로 처리한다.", 5, "fd0001", "member001", "wc0002"));
	}
	
	@Test
	public void 과일상태_폐기요청으로변경() throws SQLException {
		assertEquals(new WasteDAO(conn).updateWasteRequestStatus("fd0001", 30), true);
	}
	
	@Test
	public void 조합원_폐기요청목록_전체_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteDAO(conn).getMemberWasteList("member001");
		for (WasteRequestListVO vo : list) {
			//System.out.println(vo);
		}
		assertTrue(list.size() == 4);
	}
	
	@Test
	public void 조합원_폐기요청목록_폐기요청대기상태_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteDAO(conn).getMemberWasteListOnlyRequest("member001");
		for (WasteRequestListVO vo : list) {
			//System.out.println(vo);
		}
		assertTrue(list.size() == 1);
	}
	
	@Test
	public void 관리자_폐기요청목록_폐기요청_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteDAO(conn).getWasteList();
		for (WasteRequestListVO vo : list) {
			//System.out.println(vo);
		}
		assertTrue(list.size() == 17);
	}
	

	@Test
	public void 관리자_폐기요청목록_폐기요청대기상태_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteDAO(conn).getWasteListOnlyRequest();
		for (WasteRequestListVO vo : list) {
			//System.out.println(vo);
		}
		assertTrue(list.size() == 1);
	}
	
	@Test
	public void 관리자_특정_폐기요청_상세_조회() throws SQLException {
		WasteDetailVO vo = new WasteDAO(conn).getWasteDetail("wa0001");
		System.out.println(vo);
	}

	
	@Test
	public void 관리자_폐기요청_승인() throws SQLException {
		assertEquals(new WasteDAO(conn).updateWasteDate("admin001", "wa0001"), true);
	}
	
	@Test
	public void 관리자_과일상품_폐기로변경() throws SQLException {
		assertEquals(new WasteDAO(conn).updateWasteStatus("fd0024"), true);
	}
	
	@Test 
	public void 특정_폐기정보_조회() throws SQLException {
		WasteVO v1 = new WasteDAO(conn).getWaste("wa0001");
		WasteVO v2 =  new WasteVO("wa0001", null, "2026-08-21 00:00:00", "보관 중 부분 무름", 10, "fd0001", "member001", null, "wc0005");
		assertEquals(v1, v2); 
	}
	
	@Test
	public void 과일_폐기요청_상세_페이지_데이터_조회() {
		new WasteService().getWasteRequestData("fd0001");
	}
	
}
