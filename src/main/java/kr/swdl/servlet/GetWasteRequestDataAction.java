package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteService;

public class GetWasteRequestDataAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		request.setAttribute("wasteRequestData", new WasteService().getWasteRequestData(request.getParameter("fruitNo")));
		return "view/getWasteRequestData.jsp";
	}

}
