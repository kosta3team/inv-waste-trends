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
		InventoryService service = new InventoryService();

		String stockType = request.getParameter("stockType");

		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");

		List<InventoryVO> inventoryList;

		if ("waste".equals(stockType)) {

		    inventoryList = service.getWastedProducts();

		} else if ("normal".equals(stockType)) {

		    inventoryList = service.getNormalProducts();

		} else if ("product".equals(searchType)
		        && keyword != null
		        && !keyword.trim().isEmpty()) {

		    inventoryList = service.getInventoryByName(keyword);

		} else {

		    inventoryList = service.getInventory(1);

		}

		request.setAttribute("inventoryList", inventoryList);

		return "view/memberInventoryList.jsp";
	}
}
