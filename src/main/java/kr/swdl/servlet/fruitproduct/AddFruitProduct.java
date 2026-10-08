package kr.swdl.servlet.fruitproduct;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.MemberDAO;
import kr.swdl.model.fruitcategory.FruitCategoryDAO;
import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.model.member.MemberVO;
import kr.swdl.servlet.Action;

public class AddFruitProduct implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		
		// 1. LoginAction에서 저장한 로그인 유저의 ID
		String memberId = (String) session.getAttribute("memberId");
		
		// 2. ID를 기반으로 MemberDAO를 통해서 주소, 연락처등 상세정보 가져옴.
		if (memberId != null) {
			Connection conn = null;
			MemberDAO memberDao = new MemberDAO(conn);
//			MemberVO actualUser
		}
		
		String actionType = request.getParameter("actionType");
		FruitCategoryDAO categoryDao = new FruitCategoryDAO();		
		
		// 1. 처음 화면 진입 시
		if (actionType == null || !actionType.equals("submit")) {
			// ★ JSTL 에러 방지를 위해, 여기서 반드시 데이터를 퍼서 보내줘야 합니다.
			request.setAttribute("categoryList", categoryDao.getAllCategories());
			return "view/fruitproduct/addFruitProduct.jsp";
		}
		
		// 2. 폼 제출 시
		FruitProductVO vo = new FruitProductVO();
		
		vo.setName(request.getParameter("itemName"));
		String priceStr = request.getParameter("unitPrice");
		String weightStr = request.getParameter("weight");
		String qtyStr = request.getParameter("qty");
		
		int price = (priceStr == null || priceStr.trim().isEmpty()) ? 0 : Integer.parseInt(priceStr.trim());
		double weight = (weightStr == null || weightStr.trim().isEmpty()) ? 0.0 : Double.parseDouble(weightStr.trim());
		int qty = (qtyStr == null || qtyStr.trim().isEmpty()) ? 0 : Integer.parseInt(qtyStr.trim());
		
		vo.setPrice(price);
		vo.setWeight(weight);
		vo.setQuantity(qty);
		
		// 폼의 히든 인풋에서 올라온 값 세팅
		vo.setFruitCategoryNo(request.getParameter("fruitCategoryNo"));
		vo.setMemberId(request.getParameter("memberId"));
		
		FruitProductService service = new FruitProductService();
		request.setAttribute("result", service.addFruitProduct(vo));
		
		// ★ 화면 다시 그릴 때 JSTL 에러 방지용 리스트 다시 퍼오기
		request.setAttribute("categoryList", categoryDao.getAllCategories());
		
		return "view/fruitproduct/addFruitProduct.jsp";
	}
}