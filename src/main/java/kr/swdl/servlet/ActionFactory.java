package kr.swdl.servlet;

import kr.swdl.servlet.fruitproduct.*;

public class ActionFactory {
	public static Action getAction(String cmd) {
		if(cmd ==null) cmd="";
		Action a = null;
		
		switch(cmd) {
		case "wasteTrends":
			a=new WasteTrends();
			break;
		case "inventoryList":
			a=new InventoryList();
			break;
		case "memberInventoryList":
			a=new MemberInventoryList();
			break;
		case "login":
			a=new LoginAction();
			break;
		//	입고요청 업무 - 다니엘 시작
		
		// 1. 조합원 입고 요청
		case "addFruitProduct":
			a = new AddFruitProduct();
			break;
			
		// 2. 관리자 기본 전체 입고요청목록 조회
		case "getFruitProductRequests":
			a = new GetFruitProductRequests();
			break;
				
		// 3. 관리자 기간 전체 입고요청목록 조회
		case "getFruitProductRequestsPeriod":
			a = new GetFruitProductRequestsPeriod();
			break;
				
		// 4. 조합원 기본 전체 입고요청목록 조회
		case "getMyPendingFruitProducts":
			a = new GetMyPendingFruitProducts();
			break;
				
		// 5. 조합원 기간 전체 입고요청목록 조회
		case "getMyPendingFruitProductsPeriod":
			a = new GetMyPendingFruitProductsPeriod();
			break;
			
		// 6. 관리자 기본 입고요청상태만 목록 조회
		case "getPendingFruitProducts":
			a = new GetPendingFruitProducts();
			break;
			
		// 7. 관리자 기간 입고요청상태만 목록 조회
		case "getPendingFruitProductsPeriod":
			a = new GetPendingFruitProductsPeriod();
			break;

		// 8. 공통 입고 요청한 상품정보 상세조회
		case "getFruitProduct":
			a = new GetFruitProduct();
			break;

		// 9. 관리자 입고 요청을 거절
		case "rejectFruitProduct":
			a = new RejectFruitProduct();
			break;
				
		// 10. 관리자 입고 요청을 승인
		case "approveFruitProduct":
			a = new ApproveFruitProduct();
			break;
		//	입고요청 업무 - 다니엘 종료
	
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
