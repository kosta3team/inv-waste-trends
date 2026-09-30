package kr.swdl.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class WasteTemperatureCompare implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        
        // 1. 파라미터 수집 (화면의 상세 필터 조건)
        String startYear = request.getParameter("startYear");
        String endYear = request.getParameter("endYear");
        
        // 2. 초기 접근 시 파라미터가 없을 경우를 대비한 기본값 세팅
        if (startYear == null || startYear.isEmpty()) {
            startYear = "2025";
        }
        if (endYear == null || endYear.isEmpty()) {
            endYear = "2026";
        }

        // 3. 비즈니스 로직 처리 영역
        // Model 2 구조이므로 여기서 직접 DB 연동 로직을 처리하거나 Service/DAO를 호출합니다.
        // 예: 
        // WasteDAO dao = WasteDAO.getInstance();
        // List<WasteTempVO> list = dao.getWasteByTemperature(startYear, endYear);
        // request.setAttribute("wasteTempList", list);

        // 4. JSP에 다시 전달할 상태값 세팅 (사용자가 선택한 콤보박스 값 유지)
        request.setAttribute("startYear", startYear);
        request.setAttribute("endYear", endYear);

        // 5. 프론트 컨트롤러(서블릿)에 포워딩할 JSP 뷰 파일명 반환
        return "view/wasteTemperatureCompare.jsp";
    }
}