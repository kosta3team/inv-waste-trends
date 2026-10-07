package kr.swdl.servlet.fruitproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.fruitcategory.FruitCategoryDAO;
import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.model.member.MemberVO;
import kr.swdl.servlet.Action;

public class AddFruitProduct implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		// 로그인 연동 전까지 사용될 가짜 더미 데이터 영역 시작
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginUser") == null) {
			MemberVO devUser = new MemberVO();
			devUser.setMemberId("member001");
			devUser.setMemberName("종현과수원");
			devUser.setName("김종현");
			devUser.setPhone("010-1111-1111");
			devUser.setEmail("member001@gmail.com");
			devUser.setAddress("경기도 포천시");
			
			session.setAttribute("loginUser", devUser);
		}
		// 로그인 연동 전까지 사용될 가짜 더미 데이터 영역 끝
		
		
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