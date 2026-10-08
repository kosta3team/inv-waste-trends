package kr.swdl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "hr"; // daniel로 로컬에서 테스트 했었음
	private static final String PASSWORD = "hr"; // 1234로 로컬에서 테스트 했었음
	
	// Junit 용 테스트 커넥션
	private static Connection testConn = null;
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("oracle 드라이버 로딩 실패: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Junit 오토커밋 false 모드
	public static void beginTestTransaction() throws SQLException {
		testConn = DriverManager.getConnection(URL, USER, PASSWORD);
		testConn.setAutoCommit(false);
	}
	
	// Junit 끝날때 작업했던 데이터 모두 롤백
	public static void rollbackTestTransaction() {
		if (testConn != null) {
			try {
				testConn.rollback();
				testConn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				testConn = null;
			}
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
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