package kr.swdl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.model.inventory.InventoryVO;
import kr.swdl.model.sales.SaleService;
import kr.swdl.model.sales.SaleVO;
import kr.swdl.model.waste.WasteRequestListVO;

public class MemberMainUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();

        String memberId = (String) session.getAttribute("memberId");
        String coopName = (String) session.getAttribute("coopName");
         //조합원 입고요청목록
      	//FruitProductService fps = new FruitProductService();
      	
      	//List<FruitProductVO> FruitProductLists = fps.getMyPendingFruitProducts(memberId);
      	
      	//request.setAttribute("FruitProductlists", FruitProductLists);
		
      	//조합원 재고현환
      	//InventoryService is = new InventoryService();
      	
      	//List<InventoryVO> InventoryLists = is.getInventoryByMemberName(coopName);
      	
      	//request.setAttribute("InventoryLists", InventoryLists);
      	
      	//조합원 판매현황
      	SaleService ss = new SaleService();
      
      	List<SaleVO> SaleLists = ss.findAllSalesByMember(memberId);
      	
      	request.setAttribute("SaleLists", SaleLists);
      	
      	//조합원 폐기요청현황
      	//WasteService ws = new WasteServie();
      	   	
      	//List<WasteRequestListVO> WasteLists = ws.getMemberWasteList(memberId);
		
      	//request.setAttribute("WasteLists", WasteLists);
      	
		
		return "view/memberMain.jsp";
	}

}
