package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class wasteLunarTrends implements Action{
	@Override
	public String execute(HttpServletRequest request)throws ServletException, IOException{
		return "view/wasteLunarTrends.jsp";
	}
}
