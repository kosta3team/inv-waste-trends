package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteService;

public class RequestWasteAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		String fruitNo = request.getParameter("fruitId");
		String wasteCategoryNo = request.getParameter("disposeReason");
		int quantity = Integer.parseInt(request.getParameter("disposeQuantity"));
		String detailReason = request.getParameter("fruitCondition");
		String memberName = request.getParameter("memberName");
		
		System.out.println(fruitNo);
		System.out.println(wasteCategoryNo);
		System.out.println(quantity);
		System.out.println(detailReason);
		System.out.println(memberName+"--");
		request.setAttribute("result", new WasteService().addWaste(detailReason, quantity, fruitNo, wasteCategoryNo));
		return "view/requestWasteResult.jsp";
	}

}
