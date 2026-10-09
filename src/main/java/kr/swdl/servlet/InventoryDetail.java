package kr.swdl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.swdl.model.inventory.InventoryService;
import kr.swdl.model.inventory.InventoryVO;
import kr.swdl.model.sales.SaleService;
import kr.swdl.model.sales.SaleVO;

public class InventoryDetail  implements Action  {


	@Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException {

        String fruitNo = request.getParameter("fruitNo");

        InventoryService service = new InventoryService();

        // 상세정보 조회
		InventoryVO inventory = new InventoryService().getFruitProductDetail(fruitNo);
		
  
        int saleQuantity = 0;
        List<SaleVO> saleList = new SaleService().findSalesPeriodAdmin();
        if (saleList != null) {
            for (SaleVO sale : saleList) {
                if (fruitNo != null && fruitNo.equals(sale.getFruitNo())) {
                    saleQuantity += sale.getQuantity();
                }
            }
        }


        request.setAttribute("inventory", inventory);
		request.setAttribute("fruitNo", fruitNo);
        request.setAttribute("saleQuantity", saleQuantity);



        return "view/inventoryDetailModal.jsp";
    }

}
