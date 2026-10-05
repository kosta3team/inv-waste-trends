package kr.swdl.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import kr.swdl.model.DBCP;
import kr.swdl.model.WasteCategoryDAO;
import kr.swdl.model.WasteCategoryVO;

public class WasteCategoryDAOTest {

	private static Connection conn;
	
	@BeforeClass
	public static void 클래스_사전동작() throws SQLException {
		conn = DBCP.getConnection();
	}
	
	@Test
	public void 특정_폐기카테고리_조회() {
		
		assertEquals(
		new WasteCategoryDAO(conn).getWasteCategory("wc0001"),
		new WasteCategoryVO("wc0001", "날씨"));
	}

}
