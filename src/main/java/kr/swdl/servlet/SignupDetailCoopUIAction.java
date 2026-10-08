package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.member.MemberService;
import kr.swdl.model.member.MemberVO;

public class SignupDetailCoopUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");

        MemberService service = new MemberService();

        MemberVO member = service.getBusinessSignup(memberId);

        request.setAttribute("member", member);
		return "view/signupDetailCoop.jsp";
	}

}
