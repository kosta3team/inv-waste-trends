package kr.swdl.servlet;

public class ActionFactory {
	public static Action getAction(String cmd) {
		if(cmd ==null) cmd="";
		Action a = null;
		
		switch(cmd) {
		case "adminWasteRequestList":
			a=new AdminWasteRequestListAction();
			break;
		case "adminGetWasteRequestList":
			a=new AdminGetWasteRequestListAction ();
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
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
