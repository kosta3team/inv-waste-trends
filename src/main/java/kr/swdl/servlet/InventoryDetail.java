package kr.swdl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.inventory.InventoryService;
import kr.swdl.model.inventory.InventoryVO;

public class InventoryDetail  implements Action  {


	@Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException {

        String fruitNo = request.getParameter("fruitNo");

        InventoryService service = new InventoryService();

        // 상세정보 조회
		InventoryVO inventory = new InventoryService().getFruitProductDetail(fruitNo);

        request.setAttribute("inventory", inventory);
		request.setAttribute("fruitNo", fruitNo);


        return "view/inventoryDetailModal.jsp";
    }

}
