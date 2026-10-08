package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteDetailVO;
import kr.swdl.model.waste.WasteService;

public class AdminGetWasteRequestDetailAction implements Action {
	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		request.setAttribute("wasteDtail", new WasteService().getWasteDetail(request.getParameter("wasteNo")));
		return "view/adminGetWasteRequestDetail.jsp";
	}
}
