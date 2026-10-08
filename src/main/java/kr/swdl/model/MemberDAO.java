package kr.swdl.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.swdl.model.member.MemberVO;

public class MemberDAO {

    private Connection conn;

    public MemberDAO(Connection conn) {
        this.conn = conn;
    }

    public MemberVO loginMember(String memberId, String pw) {

	    MemberVO member = null;

	    try {

	        PreparedStatement pstmt =
	                conn.prepareStatement(Query.MEMBER_LOGIN);

	        pstmt.setString(1, memberId);
	        pstmt.setString(2, pw);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {

	            member = new MemberVO();

	            member.setName(rs.getString("name"));
	            member.setMemberName(rs.getString("member_name"));
	            member.setIsCompany(rs.getString("is_company"));
	            member.setStatus(rs.getString("status"));
	        }

	        rs.close();
	        pstmt.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return member;
	}
	
	public boolean checkMemberId(String memberId) {

	    try {

	        PreparedStatement pstmt =
	                conn.prepareStatement(Query.GET_MEMBER_ID);

	        pstmt.setString(1, memberId);

	        ResultSet rs = pstmt.executeQuery();

	        boolean result = rs.next();

	        rs.close();
	        pstmt.close();

	        return result;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}

	public boolean signUp(String memberId, String pw, String name, String birth, String phone, String email, String member_file, String zip_code, String address, String detail_address, String isCompany) {
		boolean result = false;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(Query.ADD_MEMBER);
			pstmt.setString(1, memberId);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, phone);
			pstmt.setString(6, email);
			pstmt.setString(7, member_file);
			pstmt.setString(8, isCompany);
			pstmt.setString(9, zip_code);
			pstmt.setString(10, address);
			pstmt.setString(11, detail_address);
			
			result=(pstmt.executeUpdate()==1);
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
                		rs.getString("member_id"),
                        rs.getString("name"),
                        rs.getString("member_name"),
                        rs.getString("status"),
                        rs.getDate("request_date"),
                        rs.getString("is_company")
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
                        rs.getDate("birth"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("member_file"),
                        null,
                        rs.getString("주소"),
                        rs.getDate("request_date")
                );
                member.setMemberId(memberId);
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
                        rs.getDate("birth"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("member_file"),
                        rs.getString("company_file"),
                        rs.getString("member_name"),
                        rs.getString("주소"),
                        null,
                        rs.getDate("request_date")
                );
                member.setMemberId(memberId);
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