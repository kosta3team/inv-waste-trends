package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.member.MemberService;

public class SignupAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// JSP에서 전달받은 회원가입 정보
        String memberId = request.getParameter("memberId");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String member_file = request.getParameter("member_file");
        String zip_code = request.getParameter("zip_code");
        String address = request.getParameter("address");
        String detail_address = request.getParameter("detail_address");

        // 개인 : F / 사업자 : T
        String isCompany = request.getParameter("isCompany");

        // Service 호출
        MemberService service = new MemberService();

        boolean result = service.signUp(
                memberId,
                pw,
                name,
                birth,
                phone,
                email,
                member_file,
                zip_code,
                address,
                detail_address,
                isCompany
        );

        // 회원가입 성공
        if (result) {
        	request.setAttribute("signupSuccess", true);
            return "view/login.jsp";
        }

        // 회원가입 실패
        return "view/signupMember.jsp";
    }

}
