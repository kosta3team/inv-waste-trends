package kr.swdl.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.swdl.model.DBCP;
import kr.swdl.model.sales.SaleDAO;
import kr.swdl.model.sales.SaleService;
import kr.swdl.model.sales.SaleVO;

public class SaleInventoryList implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		//1.화면에서 입력한 값 가져오기
		String startYear = request.getParameter("startYear");
		String startMonth = request.getParameter("startMonth");
		String startDate = request.getParameter("startDate");

		String endYear = request.getParameter("endYear");
		String endMonth = request.getParameter("endMonth");
		String endDate = request.getParameter("endDate");

		String startday = startYear + "-" + startMonth + "-" + startDate;
		String endday = endYear + "-" + endMonth + "-" + endDate;
		
		//2. 입력받은 값을 request에 다시 저장
				request.setAttribute("startday", startday);
				request.setAttribute("endday", endday);
				
		//3. 서비스 생성
		SaleService ss = new SaleService();
		
		//4. 화면에서 입력받은 날짜는 서비스 파라미터로 전달
		List<SaleVO> saleList = ss.findSalesByPeriod(startday, endday);
		int sumTotalPrice = ss.getSalesTotalPriceByPeriod(startday, endday);
		System.out.println(sumTotalPrice);
		
		//5. Service 에서 조회한 결과 JSP로 전달
		request.setAttribute("saleList", saleList);
		request.setAttribute("sumTotalPrice", sumTotalPrice);
		
		return "view/saleInventoryList.jsp";
	}

}
