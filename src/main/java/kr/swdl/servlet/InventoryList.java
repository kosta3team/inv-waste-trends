package kr.swdl.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

		boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
		if (hasKeyword) {
			keyword = keyword.trim();
		}


		List<InventoryVO> inventoryList;

		if ("waste".equals(stockType) || "normal".equals(stockType)) {

			List<InventoryVO> stockList = "waste".equals(stockType)
					? service.getWastedProducts()
							: service.getNormalProducts();

			if (hasKeyword && stockList != null) {
				inventoryList = new ArrayList<>();
				for (InventoryVO inv : stockList) {
					String target = "coop".equals(searchType)
							? inv.getCoopName()      // 협동조합원명
									: inv.getProductName();  // 상품명
					if (target != null && target.contains(keyword)) {
						inventoryList.add(inv);
					}
				}
			} else {
				inventoryList = stockList;
			}

		} else if (hasKeyword) {

			if ("coop".equals(searchType)) {
				inventoryList = service.getInventoryByMemberName(keyword);
			} else {
				inventoryList = service.getInventoryByName(keyword);
			}

		} else {

			inventoryList = service.getInventory(1);

		}

		request.setAttribute("inventoryList", inventoryList);

		return "view/inventoryList.jsp";
	}
}