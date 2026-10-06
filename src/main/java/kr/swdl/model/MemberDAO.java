package kr.swdl.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	private Connection conn;
	//원하는대로 추가 - overloading ==> 메모리에서 처리
	public MemberDAO(Connection conn) {  //service
		this.conn=conn;
	}
	public String loginMember(String memeberId, String pw) {
		String name=null;
		try {
			PreparedStatement pstmt=conn.prepareStatement(
					Query.MEMBER_LOGIN);
			pstmt.setString(1, memeberId);
			pstmt.setString(2, pw);			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				name=rs.getString(1);
				System.out.println(name);
			}
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return name;
	}
	
	public String checkId(String memberId) {
		
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(Query.GET_MEMBER_ID);
			pstmt.setString(1, memberId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				memberId= rs.getString(1);
				System.out.println(memberId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return memberId;
	}

	public boolean signUp(String memberId, String pw, String name, String birth, String phone, String email, String member_file, String zip_code, String address, String detail_address) {
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
			pstmt.setString(8, zip_code);
			pstmt.setString(9, address);
			pstmt.setString(10, detail_address);	
			
			result=(pstmt.executeUpdate()==1);
			pstmt.close();
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean signUpBusiness(String memberId, String pw, String name, String birth, String phone, String email, String member_file, String company_file, String member_name, String zip_code, String address, String detail_address) {
		boolean result = false;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(Query.ADD_COMPANY_MEMBER);
			pstmt.setString(1, memberId);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, phone);
			pstmt.setString(6, email);
			pstmt.setString(7, member_file);
			pstmt.setString(8, company_file);
			pstmt.setString(9, member_name);
			pstmt.setString(10, zip_code);
			pstmt.setString(11, address);
			pstmt.setString(12, detail_address);	
			
			result=(pstmt.executeUpdate()==1);
			pstmt.close();
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
