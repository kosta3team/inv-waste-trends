package kr.swdl.servlet;

public class ActionFactory {
	public static Action getAction(String cmd) {
		if(cmd ==null) cmd="";
		Action a = null;
		
		switch(cmd) {
		
		case "inventoryList":
			a=new InventoryList();
			break;
		case "memberInventoryList":
			a=new MemberInventoryList();
			break;		
		case "wasteTrends":
			a=new WasteTrends();
			break;
		case "wasteYearlyList":
			a = new WasteYearlyList();
			break;
		case "wasteLunarTrends":
			a = new WasteLunarTrends();
			break;
		case "wasteYearlyCompare":
			a = new WasteYearlyCompare();
			break;
		case "login":
			a = new LoginAction();
			break;
		case "inventoryRequest":
			a = new InventoryRequestAction();
			break;
		case "wasteTemperatureCompare":
			a = new WasteTemperatureCompare();
			break;
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
