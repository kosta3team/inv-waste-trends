package kr.swdl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.sales.SaleService;
import kr.swdl.model.sales.SaleVO;

public class MenuMemberSaleInventoryList implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {

		//String memberId = request.getParameter("memberId");//화면에서 memberId를 세션으로 들고있어야 가져올수 있음
		String memberId = "member001";
		System.out.println(memberId+"!!!");//Null값이라서 출력안됨
		
		//3. 서비스 생성
		SaleService ss = new SaleService();

		//4. 화면에서 입력받은 날짜는 서비스 파라미터로 전달
		List<SaleVO> saleList = ss.findAllSalesByMember(memberId);
		System.out.println("조회 건수 : " + saleList.size());
		int sumTotalPrice = ss.getSalesTotalPriceByMemberId(memberId);
		System.out.println(sumTotalPrice);

		//5. Service 에서 조회한 결과 JSP로 전달
		request.setAttribute("saleList", saleList);
		request.setAttribute("sumTotalPrice", sumTotalPrice);

		// TODO Auto-generated method stub
		return "view/memberSaleInventoryList.jsp";
	}

}
