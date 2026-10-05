package kr.swdl.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class AdminDAO {
	private Connection conn;

	public AdminDAO(Connection conn) {
		this.conn=conn;
	}

	public String loginAdmin(String adminId, String pw) {
		String name = null;
		try {
			PreparedStatement pstmt=conn.prepareStatement(
					Query.ADMIN_LOGIN);
			pstmt.setString(1, adminId);
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
	
	
	public List<AdminVO> getSignups(){
		List<AdminVO> list = new ArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBERS);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				list.add(new AdminVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	public List<AdminVO> getSignup(String memberId){
		List<AdminVO> list = new ArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_MEMBER);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				list.add(new AdminVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
    public List<AdminVO> getbusinessSignup(String memberId){
    	List<AdminVO> list = new ArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.GET_COMPANY_MEMBER);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				list.add(new AdminVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
    }	
		
    public boolean approveSignup(String memberId) {
    	boolean result = false;
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(Query.APPROVE_MEMBER);
    		pstmt.setString(1,  memberId);

    		result=(pstmt.executeUpdate()==1);
    		pstmt.close();

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    	return result;

    }
    
    public boolean rejectSignup(String memberId) {
    	boolean result = false;
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(Query.DELETE_MEMBER);
    		pstmt.setString(1,  memberId);

    		result=(pstmt.executeUpdate()==1);
    		pstmt.close();

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    	return result;

    }
}
