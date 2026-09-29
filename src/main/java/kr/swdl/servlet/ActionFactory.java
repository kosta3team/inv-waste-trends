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
		case "saleInventoryList":
			a=new saleInventoryList();
			break;
		case "memberRegistRequestList":
			a=new memberRegistRequestList();
			break;
		case "memberInventoryRequestList":
			a=new memberInventoryRequestList();
			break;
		case "adminInventoryRequestList":
			a=new adminInventoryRequestList();
			break;
		case "inventoryDisposalRequest":
			a=new inventoryDisposalRequest();
			break;
		case "DisposalRequestList":
			a=new disposalRequestList();
			break;
		default :
			a=new MainUIAction();
		}
		return a;
	}
}
