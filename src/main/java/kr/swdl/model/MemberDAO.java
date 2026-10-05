package kr.swdl.model;

import java.sql.Connection;

public class MemberDAO {
	private Connection conn;

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}

	public MemberVO getMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
