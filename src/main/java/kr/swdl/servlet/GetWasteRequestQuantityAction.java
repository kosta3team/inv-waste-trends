package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteService;

public class GetWasteRequestQuantityAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		System.out.println("GetWasteRequestQuantityAction");
		request.setAttribute("remainQuantity", new WasteService().getRemainQuantity(request.getParameter("fruitNo")));
		return "view/getWasteRequestQuantity.jsp";
	}
}

