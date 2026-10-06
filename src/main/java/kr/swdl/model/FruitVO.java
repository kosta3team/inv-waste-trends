package kr.swdl.model;

public class FruitVO {
	private String fruitNo;
	private String name;
	private int price;
	private double weight; // NUMBER(3,1)
	private int quantity;
	private String status;
	private String requestDate;
	private String fruitProductDate; // 오타 수정
	private String receivedDate;
	private String storageDate;
	private String fruitCategoryNo; // 소문자 시작
	private String adminNo;
	private String memberId; // 소문자 시작

	public FruitVO() {
	}

	public FruitVO(String name, int price, double weight, int quantity, String fruitProductDate, String receivedDate,
			String storageDate, String fruitCategoryNo, String adminNo, String memberId) {
		setName(name);
		setPrice(price);
		setWeight(weight);
		setQuantity(quantity);
		setFruitProductDate(fruitProductDate);
		setReceivedDate(receivedDate);
		setStorageDate(storageDate);
		setFruitCategoryNo(fruitCategoryNo);
		setAdminNo(adminNo);
		setMemberId(memberId);
	}

	public String getFruitNo() {
		return fruitNo;
	}

	public void setFruitNo(String fruitNo) {
		this.fruitNo = fruitNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getFruitProductDate() {
		return fruitProductDate;
	}

	public void setFruitProductDate(String fruitProductDate) {
		this.fruitProductDate = fruitProductDate;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}

	public String getFruitCategoryNo() {
		return fruitCategoryNo;
	}

	public void setFruitCategoryNo(String fruitCategoryNo) {
		this.fruitCategoryNo = fruitCategoryNo;
	}

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}