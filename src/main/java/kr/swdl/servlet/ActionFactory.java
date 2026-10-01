package kr.swdl.servlet;

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
		default :
			a= new MainUIAction();
		}
		return a;
	}
}
