package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action{
	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String userType = request.getParameter("userType");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		// 처음 링크를 클릭해 로그인 화면으로 진입시
		if (userId == null) {
			return "/view/login.jsp";
		}
		// DB 회원 검증 로직
		boolean isValid = false;
		
		// 임시 테스트용 조건
		if ("abc".equals(userId) && "123".equals(userPw)) {
			isValid = true;
		}
		
		if (isValid) {
			// 인증성공
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", userId);
			session.setAttribute("userRole", userType);
			
			// 로그인 성공 후 이동 할 페이지 경로
			return "inventoryList";
		} else {
			// 인증 실패
			request.setAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/view/login.jsp";
		}
			
	}
}
