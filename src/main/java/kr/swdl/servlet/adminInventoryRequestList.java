package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class adminInventoryRequestList implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "view/adminInventoryRequestList.jsp";
	}

}
