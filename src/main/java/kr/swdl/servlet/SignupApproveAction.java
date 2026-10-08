package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.member.MemberService;

public class SignupApproveAction implements Action {

	@Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException {

		String memberId = request.getParameter("memberId");

	    System.out.println("승인 요청 memberId : " + memberId);

	    MemberService service = new MemberService();

	    boolean result = service.approveSignup(memberId);

	    SignupListAction action = new SignupListAction();

	    return action.execute(request);
    }

}
