package kr.swdl.servlet.fruitproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.servlet.Action;

public class RejectFruitProduct implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        // 1. 모달 폼에서 넘겨준 파라미터(fruitNo) 받기
        String fruitNo = request.getParameter("fruitNo");
        
        // 2. 세션에서 현재 로그인한 관리자의 아이디(adminId) 꺼내기
        HttpSession session = request.getSession();
        String adminId = (String) session.getAttribute("adminId");
        
        // 3. Service 호출하여 DB 거절 업데이트 진행
        FruitProductService service = new FruitProductService();
        service.rejectFruitProduct(adminId, fruitNo);
        
        // 4. 업데이트가 완료되면, 다시 '관리자 입고요청 목록 조회' 컨트롤러로 이동
        return "controller?cmd=getAdminFruitProductRequestLists";
    }
}