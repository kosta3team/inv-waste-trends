package kr.swdl.servlet;

public class ActionFactory {
	public static Action getAction(String cmd) {
		if(cmd ==null) cmd="";
		Action a = null;
		
		switch(cmd) {
		case "wasteTrends":
			a=new wasteTrends();
			break;
		case "inventoryList":
			a=new inventoryList();
			break;
		case "memberInventoryList":
			a=new memberInventoryList();
			break;
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
