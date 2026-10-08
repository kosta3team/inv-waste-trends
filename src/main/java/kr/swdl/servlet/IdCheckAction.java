package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.member.MemberService;

public class IdCheckAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
	        throws ServletException, IOException {

	    String memberId = request.getParameter("memberId");

	    MemberService service = new MemberService();

	    boolean result = service.checkMemberId(memberId);

	    if (result) {
	        request.setAttribute("message",
	                "이미 사용 중인 아이디입니다.");
	    } else {
	        request.setAttribute("message",
	                "사용 가능한 아이디입니다.");
	    }

	    return "view/idCheck.jsp";
	}
}
