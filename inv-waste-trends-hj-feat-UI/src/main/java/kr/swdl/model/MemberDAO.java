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
	
	public Object checkId() {
	
		return null;
	}

	public Object signUp() {
		
		return null;
	}

	public Object signUpBusiness(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
