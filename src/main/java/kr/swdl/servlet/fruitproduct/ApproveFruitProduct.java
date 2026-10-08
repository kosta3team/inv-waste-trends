package kr.swdl.servlet.fruitproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.servlet.Action;

public class ApproveFruitProduct implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        // 1. 모달 폼에서 넘겨준 파라미터(fruitNo) 받기
        String fruitNo = request.getParameter("fruitNo");
        
        // 2. 세션에서 현재 로그인한 관리자의 아이디(adminId) 꺼내기
        // (※ 주의: 관리자 로그인 시 세션에 저장하는 키값이 "adminId"가 맞는지 확인 필요)
        HttpSession session = request.getSession();
        String adminId = (String) session.getAttribute("adminId");
        
        // 3. Service 호출하여 DB 승인 업데이트 진행
        FruitProductService service = new FruitProductService();
        service.approveFruitProduct(adminId, fruitNo);
        
        // 4. 업데이트가 완료되면, 다시 '관리자 입고요청 목록 조회' 컨트롤러로 이동 (새로고침 효과)
        // jsp로 바로 가지 않고 컨트롤러를 태워서 가야 목록 데이터가 최신 상태로 갱신됨
        return "controller?cmd=getAdminFruitProductRequestLists";
    }
}