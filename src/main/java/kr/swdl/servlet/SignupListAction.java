package kr.swdl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.member.MemberService;
import kr.swdl.model.member.MemberVO;

public class SignupListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		MemberService service = new MemberService();

        List<MemberVO> memberList = service.getSignups();

        request.setAttribute("memberList", memberList);

		return "view/memberRegistRequestList.jsp";
	}

}
