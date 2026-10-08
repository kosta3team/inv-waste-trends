package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteService;

public class AdminApproveWasteAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		request.setAttribute("result", new WasteService().updateWasteDate("admin001", request.getParameter("wasteNo"))); 
		return "view/adminApproveWaste.jsp";
	}

}
