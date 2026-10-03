package kr.swdl.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import kr.swdl.model.DBCP;
import kr.swdl.model.SaleDAO;
import kr.swdl.model.SaleVO;

public class SaleDAOTest {

	private static Connection conn;

	// 단위 테스트 전 사전 동작
	@BeforeClass
	public static void 클래스_사전동작() throws SQLException {
		conn = DBCP.getConnection();
	}

	@Test
	public void 특정조합원_전체_판매기록_조회() throws SQLException {
		//assertEquals(new SaleDAO(conn).findAllSalesByMember("member001"), )
		List<SaleVO> list = new SaleDAO(conn).findAllSalesByMember("member001");
		for (SaleVO VO : list) {
			System.out.println(VO);
		}
	}


	@Test
	public void 특정조합원_기간내_판매기록_조회() throws SQLException {
		List<SaleVO> list = new SaleDAO(conn).findSalesPeriodMember("member001", "2026-08-01", "2026-08-01");
		for (SaleVO VO : list) {
			System.out.println(VO);
		}
	}
	
	@Test
	public void 관리자_전체_판매기록_조회() throws SQLException {
		List<SaleVO> list  = new SaleDAO(conn).findSalesPeriodAdmin();
		for (SaleVO VO : list) {
			System.out.println(VO);
		}
	}
	
	
	@Test
	public void 관리자_기간내_판매기록_조회() throws SQLException {
		List<SaleVO> list  = new SaleDAO(conn).findSalesByPeriod("2026-08-01", "2026-08-01");
		for (SaleVO VO : list) {
			System.out.println(VO);
		}
	}
	
	
	@Test
	public void 관리자_판매기록_금액_총합() throws SQLException {
		assertEquals(new SaleDAO(conn).getSalesTotalPrice(), 26630000);
	}
	
	@Test
	public void 관리자_기간내_판매기록_금액_총합() throws SQLException {
		assertEquals(new SaleDAO(conn).getSalesTotalPriceByPeriod("2026-08-01", "2026-08-07"), 5460000);
	}
	
	@Test
	public void 조합원_판매기록_금액_총합() throws SQLException {
		assertEquals(new SaleDAO(conn).getSalesTotalPriceByMemberId("member001"), 6950000);
	}
	

	@Test
	public void 조합원_기간내_판매기록_금액_총합() throws SQLException {
		assertEquals(new SaleDAO(conn).getSalesTotalPriceByMemberIdByPeriod("member001", "2026-08-01", "2026-08-04"), 450000);
	}
}