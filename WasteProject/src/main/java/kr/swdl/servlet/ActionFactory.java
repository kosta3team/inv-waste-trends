package kr.swdl.servlet;

public class ActionFactory {
	public static Action getAction(String cmd) {
		if(cmd ==null) cmd="";
		Action a = null;
		
		switch(cmd) {
		
		case "inventoryList":
			a=new inventoryList();
			break;
		case "memberInventoryList":
			a=new memberInventoryList();
			break;		
		case "wasteTrends":
			a=new wasteTrends();
			break;
		case "wasteYearlyList":
			a = new wasteYearlyList();
			break;
		case "wasteLunarTrends":
			a = new wasteLunarTrends();
			break;
		case "wasteYearlyCompare":
			a = new wasteYearlyCompare();
			break;
				
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
