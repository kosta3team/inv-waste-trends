package kr.swdl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCP {
	private static DBCP dbcp;

	private DBCP() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("oracle driver check"); // 혹시나 오류가 날 수 있을경우
		}
		System.out.println("1. driver loading ok");
	}

	public static Connection getConnection() throws SQLException { // 왜 넘기는지 지금은 ;;
		if (dbcp == null)
			dbcp = new DBCP();
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		return DriverManager.getConnection(url, "hr", "hr");
	}
}
