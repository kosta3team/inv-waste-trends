package kr.swdl.servlet.fruitproduct;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.servlet.Action;

public class GetMyFruitProductRequestLists implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		// 1. 현재 로그인한 조합원의 아이디 가져오기
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		// 2. 화면에서 넘어온 검색 파라미터 받기
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String onlyPending = request.getParameter("onlyPending");
		
		boolean hasDate = (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty());
		boolean isPendingOnly = "Y".equals(onlyPending);
		
		FruitProductService service = new FruitProductService();
		List<FruitProductVO> requestList = null; 
		
		// 3. 4가지 업무가 경우에 따라 필터링
		// 1) 입고요청만 + 기간설정
		if (isPendingOnly && hasDate) { 
			requestList = service.getMyPendingFruitProductsPeriod(memberId, startDate, endDate);
		// 2) 입고요청만 + 기간전체	
		} else if (isPendingOnly && !hasDate) {
			requestList = service.getMyPendingFruitProducts(memberId);
		// 3) 입고조건x + 기간설정
		} else if (!isPendingOnly && hasDate) {
			requestList = service.getMyFruitProductRequestsPeriod(memberId, startDate, endDate);
		// 4) 전체 조회 - 최초접속시
		} else {
			requestList = service.getMyFruitProductRequests(memberId);
		}
		
		// 4. 페이징 처리구간
		int currentPage = 1;
		String pageParam = request.getParameter("page");
		if (pageParam != null && !pageParam.isEmpty()) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		int pageSize = 15;
		int totalCount = requestList.size();
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
		
		int fromIndex = (currentPage - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, totalCount);
		
		List<FruitProductVO> pagedList = requestList.subList(fromIndex, toIndex);
		
		// 5. 조회된 데이터 기반으로 jsp로 포워딩
		request.setAttribute("requestList", pagedList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);
		
		return "view/fruitproduct/getMyFruitProductRequestLists.jsp";
	}

}
