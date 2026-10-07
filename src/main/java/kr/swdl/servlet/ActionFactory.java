package kr.swdl.servlet;

public class ActionFactory {
	public static Action getAction(String cmd) {
		if(cmd ==null) cmd="";
		Action a = null;
		
		switch(cmd) {
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
		case "logout":
			a=new LogoutAction();
			break;
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
