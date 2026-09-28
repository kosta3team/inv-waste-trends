package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class inventoryList implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		return "view/inventoryList.jsp";
	}

}
