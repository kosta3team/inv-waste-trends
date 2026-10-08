package kr.swdl.model.fruitcategory;

public class FruitCategoryVO {
	private String fruitCategoryNo;
	private int itemCode;
	private String itemName;
	private String kindName;
	private String origin;
	private int storageDate; 
	
	// 기본 생성자
	public FruitCategoryVO() {}

	// getter and setter
	public String getFruitCategoryNo() {
		return fruitCategoryNo;
	}

	public void setFruitCategoryNo(String fruitCategoryNo) {
		this.fruitCategoryNo = fruitCategoryNo;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(int storageDate) {
		this.storageDate = storageDate;
	}
	
	
}
