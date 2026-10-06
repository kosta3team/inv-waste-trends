package test.kr.swdl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import kr.swdl.model.DBCP;
import kr.swdl.model.MemberDAO;
import kr.swdl.model.MemberVO;

public class MemberDAOTest {

    private static Connection conn;

    @BeforeClass
    public static void 클래스_사전동작() throws SQLException {

        conn = DBCP.getConnection();

        System.out.println("클래스 사전동작 - MemberDAO 테스트");
    }

//    @Test
//    public void 회원ID_조회() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//        assertEquals("member001", dao.checkId("member001"));
//        System.out.println("id 체크 완료");
//    }

//    @Test
//    public void 일반회원_가입() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//
//        boolean result = dao.signUp(
//                "member009",
//                "1234",
//                "테스트회원",
//                "19990101",
//                "010-1111-1111",
//                "test@test.com",
//                "member.jpg",
//                "12345",
//                "서울시 강남구",
//                "테스트주소"
//        );
//
//        assertTrue(result);
//    }
//
//    @Test
//    public void 법인회원_가입() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//
//        boolean result = dao.signUpBusiness(
//                "member010",
//                "1234",
//                "기업회원",
//                "19900101",
//                "010-2222-2222",
//                "company@test.com",
//                "member.jpg",
//                "company.jpg",
//                "테스트회사",
//                "12345",
//                "서울시 강남구",
//                "기업주소"
//        );
//
//        assertTrue(result);
//    }

//    @Test
//    public void 회원_전체조회() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//
//        List<MemberVO> list = dao.getSignups();
//
//        assertNotNull(list);
//        assertTrue(list.size() > 0);
//
//        for (MemberVO member : list) {
//
//            System.out.println(
//                    member.getName() + " / "
//                    + member.getMemberName() + " / "
//                    + member.getStatus() + " / "
//                    + member.getRequestDate()
//            );
//        }
//    }
//
//    @Test
//    public void 일반회원_상세조회() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//
//        MemberVO member = dao.getSignup("member009");
//
//        assertNotNull(member);
//
//        System.out.println(
//                member.getName() + " / "
//                + member.getBirth() + " / "
//                + member.getPhone() + " / "
//                + member.getEmail() + " / "
//                + member.getAddress() + " / "
//                + member.getMemeberFile() + " / "
//                + member.getRequestDate()
//        );
//    }

//    @Test
//    public void 법인회원_상세조회() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//
//        MemberVO member = dao.getBusinessSignup("member010");
//
//        assertNotNull(member);
//
//        System.out.println(
//                member.getName() + " / "
//                + member.getMemberName() + " / "
//                + member.getBirth() + " / "
//                + member.getPhone() + " / "
//                + member.getEmail() + " / "
//                + member.getAddress() + " / "
//                + member.getMemeberFile() + " / "
//                + member.getCompanyFile() + " / "
//                + member.getRequestDate()
//        );
//    }
//
//    @Test
//    public void 회원_승인() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//
//        boolean result = dao.approveSignup("member007");
//
//        assertTrue(result);
//    }
//
//    @Test
//    public void 회원_삭제() throws SQLException {
//
//        MemberDAO dao = new MemberDAO(conn);
//
//        boolean result = dao.rejectSignup("member010");
//
//        assertTrue(result);
//    }
    
    @Test
    public void 개인_로그인() {
    	MemberDAO dao = new MemberDAO(conn);
    	String login_name = dao.loginMember("member009","1234");
    	System.out.println(login_name);
    }
     
    @Test
    public void 관리자_로그인() {
    	MemberDAO dao = new MemberDAO(conn);
    	String login_name = dao.loginAdmin("admin002","admin1234");
    	System.out.println(login_name);
    }
    
}