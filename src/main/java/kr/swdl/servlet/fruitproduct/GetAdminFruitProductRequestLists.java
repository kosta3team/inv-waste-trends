package kr.swdl.servlet.fruitproduct;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.servlet.Action;

public class GetAdminFruitProductRequestLists implements Action {
    
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        
        // 1. 화면에서 넘어온 검색 파라미터 받기
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String onlyPending = request.getParameter("onlyPending");
        
        // 2. 조건 확인 변수
        boolean hasDate = (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty());
        boolean isPendingOnly = "Y".equals(onlyPending);
        
        // DAO 대신 Service 객체 생성
        FruitProductService service = new FruitProductService();
        List<FruitProductVO> requestList = null;
        
        // 3. 4가지 경우의 수에 따라 Service 분기 호출
        if (isPendingOnly && hasDate) {
            requestList = service.getPendingFruitProductsPeriod(startDate, endDate);
        } else if (isPendingOnly && !hasDate) {
            requestList = service.getPendingFruitProducts();
        } else if (!isPendingOnly && hasDate) {
            requestList = service.getFruitProductRequestsPeriod(startDate, endDate);
        } else {
            requestList = service.getFruitProductRequests();
        }
        
        // 페이징 구간 시작
        int currentPage = 1; // 기본페이지
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
        	currentPage = Integer.parseInt(pageParam);
        }
        
        int pageSize = 15; // 한페이지당 보여줄 갯수
        int totalCount = requestList.size();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize); // 총 페이지
        
        // 리스트를 자를 시작점과 끝점
        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalCount);
        
        // 15개씩 잘라낸 새 리스트
        List<FruitProductVO> pagedList = requestList.subList(fromIndex, toIndex);
        
        // 페이징처리 구간 끝
        
        // 4. 조회된 리스트를 request에 담아서 jsp로 포워딩
        request.setAttribute("requestList", pagedList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        
        return "view/fruitproduct/adminInventoryRequestList.jsp";
    }
}