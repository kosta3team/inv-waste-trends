package kr.swdl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.inventory.InventoryService;
import kr.swdl.model.inventory.InventoryVO;

public class MemberInventoryList implements Action {

	@Override 
	public String execute(HttpServletRequest request) throws ServletException, IOException { 

		String memberId = (String) request.getSession().getAttribute("memberId");

		InventoryService service = new InventoryService();

		String stockType = request.getParameter("stockType");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");

		List<InventoryVO> inventoryList;

		if ("waste".equals(stockType)) {
			inventoryList = service.getWastedProducts(memberId);

		} else if ("normal".equals(stockType)) {
			inventoryList = service.getNormalProducts(memberId);

		} else if ("product".equals(searchType)
				&& keyword != null
				&& !keyword.trim().isEmpty()) {
			inventoryList = service.getInventoryByName(memberId, keyword.trim());

		} else {
			inventoryList = service.getInventory(memberId, 1);
		}

		request.setAttribute("inventoryList", inventoryList);

		return "view/memberInventoryList.jsp";
	}
}
