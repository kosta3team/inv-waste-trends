package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.waste.WasteCategoryService;

public class GetWasteCategoryListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		request.setAttribute("list", new WasteCategoryService().getWateCategoryList());
		return "view/getWasteCategoryList.jsp";
	}

}
