package kr.swdl.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
		
		boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
		if (hasKeyword) {
			keyword = keyword.trim();
		}

		List<InventoryVO> inventoryList;

		if ("waste".equals(stockType) || "normal".equals(stockType)) {

			List<InventoryVO> stockList = "waste".equals(stockType)
					? service.getWastedProducts(memberId)
					: service.getNormalProducts(memberId);

			if (hasKeyword && stockList != null) {
				inventoryList = new ArrayList<>();
				for (InventoryVO inv : stockList) {
					String productName = inv.getProductName();
					if (productName != null && productName.contains(keyword)) {
						inventoryList.add(inv);
					}
				}
			} else {
				inventoryList = stockList;
			}

		} else if (hasKeyword) {

			inventoryList = service.getInventoryByName(memberId, keyword);

		} else {

			inventoryList = service.getInventory(memberId, 1);

		}

		request.setAttribute("inventoryList", inventoryList);

		return "view/memberInventoryList.jsp";
	}
}
