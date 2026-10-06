package kr.swdl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCP {

	private static DBCP dbcp;

	private DBCP() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		System.out.println("1 driver loading ok");
	}
	
	
	public static Connection getConnection() throws SQLException {
		
		System.out.println();
		
		if (null == dbcp)
			dbcp = new DBCP();
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // -> throws
		return DriverManager.getConnection(url, "hr", "hr");
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		// 먼저 뒤의 인자부터
		try {
			if (pstmt != null) pstmt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (conn != null) conn.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} close(conn, pstmt);
	}
}

