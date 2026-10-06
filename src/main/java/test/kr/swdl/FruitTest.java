package test.kr.swdl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import kr.swdl.model.DBCP;
import kr.swdl.model.FruitDAO;
import kr.swdl.model.FruitDetailVO;
import kr.swdl.model.FruitRequestVO;

public class FruitTest {
		private static Connection conn;

		// 단위 테스트 전 사전 동작
		@BeforeClass
		public static void 클래스_사전동작() throws SQLException {
			conn = DBCP.getConnection();
		}
		
//		@Test
//		public void 입고요청() {
//
//		    FruitDAO dao = new FruitDAO(conn);
//
//		    boolean result = dao.addFruitProduct(
//		    	    "전주 참외",
//		    	    34000,
//		    	    5,
//		    	    30,
//		    	    "fc0003",
//		    	    null,
//		    	    "admin003",
//		    	    "member005",
//		    	    null,
//		    	    null
//		    	);
//
//		    assertTrue(result);
//		}
//		@Test
//		public void 입고요청_조회() throws SQLException {
//
//		    FruitDAO dao = new FruitDAO(conn);
//
//		    List<FruitRequestVO> list = dao.getStockInsAdmin("2026-01-01", "2027-01-01");
//
//		    for (FruitRequestVO vo : list) {
//		        System.out.println(vo);
//		    }
//
//		    assertFalse(list.isEmpty());
//		}
//		
//		@Test
//		public void 자신의_입고요청_조회() throws SQLException {
//
//		    FruitDAO dao = new FruitDAO(conn);
//
//		    List<FruitRequestVO> list = dao.getStockIns("member002","2026-01-01", "2027-01-01");
//
//		    for (FruitRequestVO vo : list) {
//		        System.out.println(vo);
//		    }
//
//		    assertFalse(list.isEmpty());
//		}
		@Test
		public void 입고_상품_상세조회() throws SQLException {
			FruitDAO dao = new FruitDAO(conn);
			List<FruitDetailVO> list = dao.getStockInDetail("fd0023");
			for (FruitDetailVO vo : list) {
		        System.out.println(vo);
		    }

		    assertFalse(list.isEmpty());
		}
		
}
