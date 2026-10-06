package kr.swdl.model;

import java.sql.SQLException;

public class MemberService {
	public String login(String memberId, String pw) {
		try {
			return new MemberDAO(DBCP.getConnection())
					.loginMember(memberId, pw);
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}
}
