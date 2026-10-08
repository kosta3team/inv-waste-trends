package kr.swdl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteRequestListVO;
import kr.swdl.model.waste.WasteService;

public class AdminGetWasteRequestListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String isOnlyRequest = request.getParameter("IsOnlyRequest");
		List<WasteRequestListVO> list = null;
		if (isOnlyRequest.equals("true")) {
			list = new WasteService().getWasteListOnlyRequest();
		} else {
			list = new WasteService().getWasteList();
		}
		System.out.println(list);
		request.setAttribute("list", list);	
		return "view/adminGetWasteList.jsp";
	}

}
