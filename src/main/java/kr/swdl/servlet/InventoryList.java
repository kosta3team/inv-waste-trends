package kr.swdl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.inventory.InventoryService;
import kr.swdl.model.inventory.InventoryVO;

public class InventoryList implements Action {

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

		} else if (keyword != null && !keyword.trim().isEmpty()) {

			keyword = keyword.trim();

			if ("coop".equals(searchType)) {
				// 협동조합원명 
				inventoryList = service.getInventoryByMemberName(keyword);
			} else {
				// 상품명 
				inventoryList = service.getInventoryByName(keyword);
			}

		} else {

			inventoryList = service.getInventory(1);

		}

		request.setAttribute("inventoryList", inventoryList);

		return "view/inventoryList.jsp";
	}

}