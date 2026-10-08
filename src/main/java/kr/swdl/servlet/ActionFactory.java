package kr.swdl.servlet;

import kr.swdl.servlet.fruitproduct.*;

public class ActionFactory {
	public static Action getAction(String cmd) {
		if(cmd ==null) cmd="";
		Action a = null;
		switch(cmd) {
		case "inventoryDetail":
		    a = new InventoryDetail();
		    break;
		case "adminWasteRequestList":
			a=new AdminWasteRequestListAction();
			break;
		case "adminGetWasteRequestDetail":
			a=new AdminGetWasteRequestDetailAction();
			break;
		case "adminApproveWaste":
			a=new AdminApproveWasteAction();
			break;
		case "getWasteRequestQuantity":
			a = new GetWasteRequestQuantityAction();
			break;
		case "getWasteCategoryList":
			a = new GetWasteCategoryListAction();
			break;
		case "requestWaste":
			a = new RequestWasteAction();
			break;
		case "wasteLunarTrends":
			a = new WasteLunarTrends();
			break;
		case "wasteYearlyList":
			a = new WasteYearlyList();
			break;
		case "wasteYearlyCompare" :
			a = new WasteYearlyCompare();
			break;
		case "wasteTemperatureCompare" :
			a = new WasteTemperatureCompare();
			break;
			//
		case "memberInventoryList":
			a = new MemberInventoryList();
			break;
			//
			
			
		case "idCheck":
			a = new IdCheckAction();
			break;
		case "signupMemberUI":
			a = new SignupMemberUIAction();
			break;
		case "signup":
			a = new SignupAction();
			break;
		case "signupListUI":
			a= new SignupListAction();
			break;
		case "signupDetailPersonalUI":
			a = new SignupDetailPersonalUIAction();
			break;
		case "signupDetailCoopUI":
			a = new SignupDetailCoopUIAction();
			break;
		case "signupApprove":
		    a = new SignupApproveAction();
		    break;
		case "signupReject":
		    a = new SignupRejectAction();
		    break;
		case "wasteTrends":
			a=new WasteTrendsUIAction();
			break;
		case "inventoryListUI":
			a=new InventoryList();
			break;
		case "adminInventoryRequestListUI":
			a=new AdminInventoryRequestListUIAction();
			break;
		case "saleInventoryList":
			a=new SaleInventoryList();
			break;
		case "menuSaleInventoryList":
			a=new MenuSaleInventoryList();
			break;
		case "memberSaleInventoryList":
			a=new MemberSaleInventoryList();
			break;
		case "menuMemberSaleInventoryList":
			a=new MenuMemberSaleInventoryList();
			break;
		case "memberMain":
		    a = new MemberMainUIAction();
		    break;
		case "loginUI":
			a = new LoginUIAction();
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
			
		// 4. 관리자 기본 입고요청상태만 목록 조회
		case "getPendingFruitProducts":
			a = new GetPendingFruitProducts();
			break;
			
		// 5. 관리자 기간 입고요청상태만 목록 조회
		case "getPendingFruitProductsPeriod":
			a = new GetPendingFruitProductsPeriod();
			break;

		// 6. 조합원 기본 전체 입고요청목록 조회
		case "getMyFruitProductRequests":
			a = new GetMyFruitProductRequests();
			break;

		// 7. 조합원 기간 전체 입고요청목록 조회
		case "getMyFruitProductRequestsPeriod":
			a = new GetMyFruitProductRequestsPeriod();
			break;

		// 8. 조합원 기본 입고요청상태만 목록 조회
		case "getMyPendingFruitProducts":
			a = new GetMyFruitProductRequests();
			break;
			
		// 9. 조합원 기간 입고 요청만 기본 목록 조회
		case "getMyPendingFruitProductsPeriod":
			a = new GetMyPendingFruitProductsPeriod();
			break;
				
		// 10. 관리자 입고 요청한 상품정보 상세조회
		case "getFruitProduct":
			a = new GetFruitProduct();
			break;

		//11. 조합원 입고 요청한 상품정보 상세조회
		case "getMyFruitProduct":
			a = new GetMyFruitProduct();
			break;
			
		// 12. 관리자 입고 요청을 거절
		case "rejectFruitProduct":
			a = new RejectFruitProduct();
			break;
				
		// 13. 관리자 입고 요청을 승인
		case "approveFruitProduct":
			a = new ApproveFruitProduct();
			break;
		//	입고요청 업무 - 다니엘 종료
		case "logout":
			a=new LogoutAction();
			break;
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
