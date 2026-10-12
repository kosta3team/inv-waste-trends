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
	public void 조합원_폐기요청_성공() throws SQLException {
		assertTrue(new WasteService().addWaste("관리자의 관리부재로 처리한다.", 5, "fd0001", "member001", "wc0002"));
	}
	
	@Test
	public void 조합원_폐기요청_실패() throws SQLException {
		WasteService service = new WasteService();
		assertFalse(service.addWaste("관리자의 관리부재로 처리한다.", 5, null, "member001", "wc0002"));
		assertFalse(service.addWaste("관리자의 관리부재로 처리한다.", 5, "fd0001", null, "wc0002"));
		assertFalse(service.addWaste("관리자의 관리부재로 처리한다.", 5, "fd0001", "member001", null));	
	}
	
	@Test
	public void 과일상태_폐기요청으로변경_성공() throws SQLException {
		assertTrue(new WasteService().updateWasteRequestStatus("fd0001", 30));
	}
	
	@Test
	public void 과일상태_폐기요청으로변경_실패() throws SQLException {
		assertFalse(new WasteService().updateWasteRequestStatus("fd111", 30));
	}
	
	
	@Test
	public void 조합원_폐기요청목록_전체_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteService().getMemberWasteList("member001");
		for (WasteRequestListVO vo : list) {
			System.out.println(vo);
		}
		assertTrue(list.size() > 0);
	}
	
	
	@Test  
	public void 요청_전체_요청대기_조회() throws SQLException  {
		
		WasteService service = new WasteService();
		
		int preAddAllSize = service .getMemberWasteList("member001").size();
		int preOnlyRequestSize = service .getMemberWasteListOnlyRequest("member001").size();
			
		// 추가 실패시 테스트 실패 
		assertTrue(new WasteService().addWaste("관리자의 관리부재로 처리한다.", 5, "fd0001", "member001", "wc0002"));
		// 추가 성공을 가정하고, 이전과 이후의 사이즈가 동일한지 확인 (DB실제 반영 여부 확인) 
		assertEquals(preAddAllSize + 1, service .getMemberWasteList("member001").size());
		assertEquals(preOnlyRequestSize + 1, service .getMemberWasteListOnlyRequest("member001").size());
	}
	
	
	
	@Test
	public void 조합원_폐기요청목록_폐기요청대기상태_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteService().getMemberWasteListOnlyRequest("member001");
		for (WasteRequestListVO vo : list) {
			System.out.println(vo);
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void 관리자_폐기요청목록_폐기요청_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteService().getWasteList();
		for (WasteRequestListVO vo : list) {
			System.out.println(vo);
		}
		assertTrue(list.size() > 0);
	}

	@Test
	public void 관리자_폐기요청목록_폐기요청대기상태_조회() throws SQLException {
		List<WasteRequestListVO> list = new WasteService().getWasteListOnlyRequest();
		for (WasteRequestListVO vo : list) {
			System.out.println(vo);
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void 관리자_특정_폐기요청_상세_조회_성공() throws SQLException {
		assertNotNull(new WasteService().getWasteDetail("wa0001"));
	}
	
	@Test
	public void 관리자_특정_폐기요청_상세_조회_실패() throws SQLException {
		assertNull(new WasteService().getWasteDetail("wa1212"));
	}
	
	@Test
	public void 관리자_폐기요청_승인_성공() throws SQLException {
		assertEquals(new WasteService().updateWasteDate("admin001", "wa0001"), true);
	}
	
	@Test
	public void 관리자_폐기요청_승인_실패() throws SQLException {
		WasteService service = new WasteService();
		// 폐기 정보가 잘못된 경우
		assertFalse(service.updateWasteDate("admin001", "wa1111"));
		// 관리자 정보가 잘못된 경우 
		assertFalse(service.updateWasteDate("admin1111", "wa0001"));	
		// 빈값이 들어간 경우 
		assertFalse(service.updateWasteDate("admin001", null));	
		
		//여기 주의해서 확인해보세요.
		//폐기 테이블에 관리자는 널을 허용합니다. 왜냐하면 폐기승인은 관리자가 하기때문입니다.
		//(처음에 요청했을때는 널) 
		//그렇기에 아래 업데이트문은 널을 넣어도 처리날짜가 찍히고, 문제가 없었음
		//즉, 처리날짜는 존재하는데 승인한 관리자가 없는 경우가 생기게됨 
		//이를 TDD를 통해서 검출할 수 있었습니다.
		assertFalse(service.updateWasteDate(null, "wa0001"));	
	}
	
	@Test
	public void 관리자_과일상품_폐기로변경_성공() throws SQLException {
		assertTrue(new WasteService().updateWasteStatus("fd0001"));
	}
	
	@Test
	public void 관리자_과일상품_폐기로변경_실패() throws SQLException {
		assertFalse(new WasteService().updateWasteStatus("fd01111"));
	}
	
	@Test 
	public void 특정_폐기정보_조회() throws SQLException {
		WasteVO v1 = new WasteService().getWaste("wa0001");
		// 얻어온 정보가 널인지 확인한다.
		assertNotEquals(v1, null);
		// 실제 일치하는 VO를 만들어 내부 내용이 동일한지 확인한다.
		WasteVO v2 =  new WasteVO("wa0001", "2026-10-11 00:00:00", "2026-08-21 00:00:00", "보관 중 부분 무름", 10, "fd0001", "member001", "admin001", "wc0005");
		assertEquals(v1, v2); 
	}
	
	@Test
	public void 과일_폐기요청_상세_페이지_데이터_조회_성공() {
		assertNotEquals(new WasteService().getWasteRequestData("fd0001"), null);
	}
	
	@Test
	public void 과일_폐기요청_상세_페이지_데이터_조회_실패() {
		// 없는 데이터 요청시
		assertEquals(new WasteService().getWasteRequestData("fd1111"), new WasteService().getWasteRequestData("fd2222"));
	}
}
