package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface Action {
	//공통
	String execute(HttpServletRequest request)throws ServletException, IOException;
}
