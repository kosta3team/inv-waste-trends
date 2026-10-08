package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.swdl.model.member.MemberService;
import kr.swdl.model.member.MemberVO;

public class LoginAction implements Action {
	
    @Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        MemberService service = new MemberService();

        if ("member".equals(userType)) {

            MemberVO member = service.loginMember(userId, userPw);

            if (member != null) {
                if ("승인".equals(member.getStatus())) {

                    String loginName = member.getMemberName();

                    HttpSession session = request.getSession();

                    session.setAttribute("loginName", loginName);
                    session.setAttribute("userType", "member");
                    session.setAttribute("memberId", userId);

                    return "controller?cmd=memberMain";
                }

                if ("대기".equals(member.getStatus())) {
                    request.setAttribute("errorMessage",
                            "관리자 승인 후 로그인할 수 있습니다.");

                    return "view/login.jsp";
                }
            }
        }

        if ("admin".equals(userType)) {

            String adminName = service.loginAdmin(userId, userPw);

            if (adminName != null && !adminName.isEmpty()) {

                HttpSession session = request.getSession();

                session.setAttribute("loginName", adminName);
                session.setAttribute("userType", "admin");
                session.setAttribute("adminId", userId);

                return "view/wasteTrends.jsp";
            }
        }

        request.setAttribute("errorMessage",
                "아이디 또는 비밀번호가 올바르지 않습니다.");

        return "view/login.jsp";
    }
}