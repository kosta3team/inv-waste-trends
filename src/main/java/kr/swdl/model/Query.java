package kr.swdl.model;

public interface Query {
//회원가입
	String GET_MEMBER_ID = "SELECT  member_id" 
			+" FROM member" 
			+" WHERE member_id = ?"; 
	
   String ADD_MEMBER = "INSERT INTO" 
		+ " member(member_id, pw, name, birth, phone,email, member_file, is_company, zip_code, address, detail_address, status, request_date)" 
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, 'F', ?, ?, ?, '대기', SYSDATE )";

   String ADD_COMPANY_MEMBER = "INSERT INTO member (member_id, pw, name, birth, phone, email, member_file, company_file, is_company, member_name, zip_code, address, detail_address, status, request_date)"
   		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'T', ?, ?, ?, ?, '대기', SYSDATE)";

   String GET_MEMBERS = "SELECT request_date, status, member_name, name FROM MEMBER";
   
   String GET_MEMBER = "SELECT request_date, name, birth, phone, email, address || ' ' ||  detail_address as 주소, member_file FROM member WHERE member_id = ?";
   
   String GET_COMPANY_MEMBER = "SELECT request_date, name, member_name, birth, phone, email, address || ' ' ||  detail_address as 주소, member_file, company_file FROM member WHERE member_id = ?";
   
   String APPROVE_MEMBER = "UPDATE member SET status = '승인', member_date = SYSDATE WHERE member_id = ?";
   
   String DELETE_MEMBER = "DELETE FROM member WHERE member_id = ?";

//로그인
   String MEMBER_LOGIN = "SELECT name FROM member WHERE member_id=? AND pw=?";
   
   String ADMIN_LOGIN = "SELECT name FROM admin WHERE admin_id=? AND pw=?";
}