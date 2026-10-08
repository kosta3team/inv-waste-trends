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
				
		// 2. 관리자 입고요청목록 조회
		case "getAdminFruitProductRequestLists":
			a = new GetAdminFruitProductRequestLists();
			break;
			
		// 3. 조합원 입고요청목록 조회
		case "getMyFruitProductRequestLists":
			a = new GetMyFruitProductRequestLists();
			break;

		// 4. 관리자 입고요청 상세 - 모달
		case "getDetailFruitProduct":
			a = new GetDetailFruitProduct();
			break;
			
		// 5. 조합원 입고요청 상세 - 모달
		case "getDetailMyFruitProduct":
			a = new GetDetailMyFruitProduct();
			break;
		
		// 6. 관리자 입고요청 승인 처리
		case "approveFruitProduct":
			a = new ApproveFruitProduct();
			break;
			
		// 7. 관리자 입고요청 거절 처리
		case "rejectFruitProduct":
			a = new RejectFruitProduct();
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
