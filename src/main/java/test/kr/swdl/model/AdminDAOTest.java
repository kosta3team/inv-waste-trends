package test.kr.swdl.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.swdl.model.AdminDAO;
import kr.swdl.model.DBCP;
import kr.swdl.model.MemberDAO;

public class AdminDAOTest {

	private static Connection conn;
	@BeforeClass
	public static void 클래스_사전동작() throws SQLException {
		conn=DBCP.getConnection();
		System.out.println("연결완료");
	}
	@Before
	public void 단위테스트_사전동작() {
		System.out.println("단위테스트_사전동작");
	}

	
	
	@Test
	public void 회원가입_요청_거절() throws SQLException{
		System.out.println("회원가입_요청_거절 테스트");
		System.out.println(new AdminDAO(conn).rejectSignup("member009"));
	}
	
	
	
	@Test
	public void 회원가입_요청_승인() throws SQLException{
		System.out.println("회원가입_요청_승인 테스트");
		System.out.println(new AdminDAO(conn).approveSignup("member001"));
	}
	
	
	
	
	@Test
	public void 회원가입_요청_조회_사업자() throws SQLException{
		System.out.println("회원가입_요청_조회_사업자 테스트");
		System.out.println(new AdminDAO(conn).getbusinessSignup("member001"));
	
	}
	
	
	@Test
	public void 회원가입_요청_조회_개인() throws SQLException{
		System.out.println("회원가입_요청_조회_개인 테스트");
		System.out.println(new AdminDAO(conn).getSignup("member001"));
		
	}


	@Test
	public void 회원가입_요청_목록_조회() throws SQLException {
		System.out.println((new AdminDAO(conn).getSignups()));
	}

	@Test
	public void 관리자_로그인() throws SQLException {
		System.out.println((new AdminDAO(conn).loginAdmin("admin001","admin1234" )));;
	}

}
