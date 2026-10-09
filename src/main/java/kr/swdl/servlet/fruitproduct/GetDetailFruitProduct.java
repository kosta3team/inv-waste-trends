package kr.swdl.servlet.fruitproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.servlet.Action;

public class GetDetailFruitProduct implements Action {
	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String fruitNo = request.getParameter("fruitNo");
		
		FruitProductService service = new FruitProductService();
		FruitProductVO reqDetail = service.getFruitProduct(fruitNo);
		
		request.setAttribute("reqDetail", reqDetail);
		
		return "view/fruitproduct/getDetailFruitProduct.jsp";
	}
}