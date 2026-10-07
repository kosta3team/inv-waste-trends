package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.member.MemberService;

public class SignupRejectAction implements Action {

	@Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException {

        String memberId = request.getParameter("memberId");

        System.out.println("삭제 요청 memberId : " + memberId);

        MemberService service = new MemberService();

        boolean result = service.rejectSignup(memberId);

        System.out.println("회원 삭제 결과 : " + result);

        SignupListAction action = new SignupListAction();

        return action.execute(request);
    }

}
