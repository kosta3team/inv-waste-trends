package kr.swdl.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontContollerServlet
 */
@WebServlet("/controller")
public class FrontControllerServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		try {
			Action a= ActionFactory.getAction(cmd);
			String url = a.execute(request);
			
			if (url != null) {
				request.getRequestDispatcher("/"+url).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
