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
		request.setAttribute("WasteRequestList",  new WasteService().getWasteList());	
		return "view/adminWasteRequestList.jsp";
	}

}
