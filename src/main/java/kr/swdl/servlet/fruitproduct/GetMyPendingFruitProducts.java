package kr.swdl.servlet.fruitproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.servlet.Action;

public class GetMyPendingFruitProducts implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "view/fruitproduct/getMyPendingFruitProducts.jsp";
	}

}
