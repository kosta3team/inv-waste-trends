package kr.swdl.model.member;

import java.sql.SQLException;
import java.util.List;

import kr.swdl.model.DBCP;

public class MemberService {

	public boolean signUp(String memberId, String pw, String name, String birth, String phone, String email,
			String member_file, String zip_code, String address, String detail_address, String isCompany) {

		try {
			return new MemberDAO(DBCP.getConnection()).signUp(memberId, pw, name, birth, phone, email, member_file,
					zip_code, address, detail_address, isCompany);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public MemberVO loginMember(String memberId, String pw) {
		try {
			return new MemberDAO(DBCP.getConnection()).loginMember(memberId, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkMemberId(String memberId) {

		try {

			return new MemberDAO(DBCP.getConnection()).checkMemberId(memberId);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return false;
	}

	public List<MemberVO> getSignups() {

		try {
			return new MemberDAO(DBCP.getConnection()).getSignups();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public MemberVO getSignup(String memberId) {
		try {

			return new MemberDAO(DBCP.getConnection()).getSignup(memberId);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
	}

	public MemberVO getBusinessSignup(String memberId) {
		try {

			return new MemberDAO(DBCP.getConnection()).getBusinessSignup(memberId);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
	}

	public String loginAdmin(String userId, String userPw) {
		try {

			return new MemberDAO(DBCP.getConnection()).loginAdmin(userId, userPw);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
	}
	
	public boolean approveSignup(String memberId) {

	    try {

	        return new MemberDAO(DBCP.getConnection()).approveSignup(memberId);

	    } catch (SQLException e) {

	        e.printStackTrace();

	    }

	    return false;
	}


	public boolean rejectSignup(String memberId) {

	    try {

	        return new MemberDAO(DBCP.getConnection()).rejectSignup(memberId);

	    } catch (SQLException e) {

	        e.printStackTrace();

	    }

	    return false;
	}
}