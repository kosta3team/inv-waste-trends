package kr.swdl.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	private Connection conn;

    public MemberDAO(Connection conn) {
        this.conn = conn;
    }

    // 회원 ID 조회
    public String checkId(String memberId) {
        String result = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER_ID);
            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getString(1);
            }

            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 개인회원 가입
    public boolean signUp(String memberId, String pw, String name,
            String birth, String phone, String email, String memberFile,
            String zipCode, String address, String detailAddress) {

        boolean result = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement(Query.ADD_MEMBER);

            pstmt.setString(1, memberId);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, birth);
            pstmt.setString(5, phone);
            pstmt.setString(6, email);
            pstmt.setString(7, memberFile);
            pstmt.setString(8, zipCode);
            pstmt.setString(9, address);
            pstmt.setString(10, detailAddress);

            result = pstmt.executeUpdate() == 1;

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 법인회원 가입
    public boolean signUpBusiness(String memberId, String pw, String name,
            String birth, String phone, String email, String memberFile,
            String companyFile, String memberName, String zipCode,
            String address, String detailAddress) {

        boolean result = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement(Query.ADD_COMPANY_MEMBER);

            pstmt.setString(1, memberId);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, birth);
            pstmt.setString(5, phone);
            pstmt.setString(6, email);
            pstmt.setString(7, memberFile);
            pstmt.setString(8, companyFile);
            pstmt.setString(9, memberName);
            pstmt.setString(10, zipCode);
            pstmt.setString(11, address);
            pstmt.setString(12, detailAddress);

            result = pstmt.executeUpdate() == 1;

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 회원 전체 조회
    public List<MemberVO> getSignups() {

        List<MemberVO> list = new ArrayList<MemberVO>();

        try {

            PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBERS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                list.add(new MemberVO(
                        rs.getString("name"),
                        rs.getString("member_name"),
                        rs.getString("status"),
                        rs.getString("request_date")
                ));
            }

            rs.close();
            pstmt.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    // 개인회원 상세 조회
    public MemberVO getSignup(String memberId) {

        MemberVO member = null;

        try {

            PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER);

            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                member = new MemberVO(
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("member_file"),
                        null,
                        rs.getString("주소"),
                        rs.getString("request_date")
                );
            }

            rs.close();
            pstmt.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return member;
    }

    // 법인회원 상세 조회
    public MemberVO getBusinessSignup(String memberId) {

        MemberVO member = null;

        try {

            PreparedStatement pstmt = conn.prepareStatement(Query.GET_COMPANY_MEMBER);

            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                member = new MemberVO(
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("member_file"),
                        rs.getString("company_file"),
                        rs.getString("member_name"),
                        rs.getString("주소"),
                        null,
                        rs.getString("request_date")
                );
            }

            rs.close();
            pstmt.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return member;
    }

    // 회원 승인 -> 날짜update 
    public boolean approveSignup(String memberId) {
        boolean result = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement(Query.APPROVE_MEMBER);

            pstmt.setString(1, memberId);

            result = pstmt.executeUpdate() == 1;

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 회원 삭제
    public boolean rejectSignup(String memberId) {
        boolean result = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement(Query.DELETE_MEMBER);

            pstmt.setString(1, memberId);

            result = pstmt.executeUpdate() == 1;

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

	public String loginMember(String memberId, String pw) {
		String name="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.MEMBER_LOGIN);
			pstmt.setString(1, memberId);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				name=rs.getString("name");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}

	public String loginAdmin(String memberId, String pw) {
		String name="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.ADMIN_LOGIN);
			pstmt.setString(1, memberId);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name=rs.getNString("name");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

}
