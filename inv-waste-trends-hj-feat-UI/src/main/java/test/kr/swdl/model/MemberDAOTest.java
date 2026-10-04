package test.kr.swdl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import kr.swdl.model.DBCP;
import kr.swdl.model.MemberDAO;

class MemberDAOTest {

	private static Connection conn;
	@BeforeClass
	public static void 클래스_사전동작() throws SQLException {
		conn=DBCP.getConnection();
		System.out.println("클래스_사전동작-select 공통 코드");
	}
	@Before
	public void 단위테스트_사전동작() {
		System.out.println("단위테스트_사전동작");
	}
	@Test
	public void 고객정보_이름_가져오기() throws SQLException {
		assertEquals(new MemberDAO(conn).loginMember("member001", "member001!"), "김종현");
	}
	
	
	
	

}
