package kr.swdl.servlet.fruitproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.servlet.Action;

public class RejectFruitProduct implements Action {

    @Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException {

        // 1. 요청한 상품번호
        String fruitNo = request.getParameter("fruitNo");

        // 2. 로그인한 관리자 정보
        HttpSession session = request.getSession();
        String adminId = (String) session.getAttribute("adminId");

        // 3. 거절 처리
        FruitProductService service = new FruitProductService();
        boolean result = service.rejectFruitProduct(adminId, fruitNo);

        // 4. 처리 결과 확인
        if (result) {
            System.out.println("입고 요청 거절 성공");
        } else {
            System.out.println("입고 요청 거절 실패");
        }

        // 5. 처리 후 관리자 입고요청 목록으로 이동
        return "controller?cmd=getAdminFruitProductRequestLists";
    }
}