package kr.swdl.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import kr.swdl.model.DBCP;
import kr.swdl.model.member.MemberDAO;

public class MemberDAOTest {

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
	public void 조합원정보_이름_가져오기() throws SQLException {
		assertEquals(new MemberDAO(conn).loginMember("member001","member001!" ), "김종현");
	}

	
	@Test
	public void 아이디_중복_조회하기() throws SQLException {
		//assertEquals(new MemberDAO(conn).checkId("member001"), "member001");
	}


	@Test 
	public void 개인_회원가입_요청하기() throws SQLException {
		//new MemberDAO(conn).signUp("member008", "member008!", "김종직", "2009-08-22", "010-9999-0001", "jkimck@gmail.com", "https://example.com/member/member008.jpg", "11118", "경기도 삼천시", "고장면 농촌길 23");
	}


	@Test 
	public void 사업자_회원가입_요청하기() throws SQLException {
		//new MemberDAO(conn).signUpBusiness("member009", "member009!", "이재마", "2009-08-21", "010-9999-1111", "ejma@gmail.com", "https://example.com/member/member009.jpg", "https://example.com/company/company009.pdf", "푸른빛농원","11119", "경기도 삼천시", "고장면 농촌길 23");
	}

}

