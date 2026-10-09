package kr.swdl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteRequestListVO;
import kr.swdl.model.waste.WasteService;

public class AdminWasteRequestListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		List<WasteRequestListVO> list = null;
		String url = null;
		String isOnlyRequest = request.getParameter("IsOnlyRequest");
		if (null == isOnlyRequest) { 
			list = new WasteService().getWasteList();
			url = "view/adminWasteRequestList.jsp";
		} else {
			if (isOnlyRequest.equals("true")) {
				list = new WasteService().getWasteListOnlyRequest();
			} else {
				list = new WasteService().getWasteList();
			}
			url = "view/adminGetWasteList.jsp";
		}
		request.setAttribute("WasteRequestList",  list);	
		return url;
	}

}
