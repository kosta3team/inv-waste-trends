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
}
