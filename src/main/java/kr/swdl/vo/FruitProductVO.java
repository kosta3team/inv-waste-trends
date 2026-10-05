package kr.swdl.vo;

import java.sql.Date;

public class FruitProductVO {
	// 기본테이블 인자
	private String fruitNo;
	private String name;
	private int price;
	private double weight;
	private int quantity;
	private String status;
	private Date requestDate;
	private Date receivedDate;
	private Date fruitProductDate;
//	private int storageDate; // 수정사항 반영시
	private Date storageDate; // ERD 그대로 갈 시
	
	// FK테이블 인자
	private String fruitCategoryNo;
	private String adminId;
	private String memberId;
	
	// FK 및 계산 인자
	private int totalPrice;
	private MemberVO member;
	private AdminVO admin;
	private FruitCategoryVO fruitCategory;
	
	
	// 기본 생성자
	public FruitProductVO() {}
	
	// getter setter
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getFruitProductDate() {
		return fruitProductDate;
	}

	public void setFruitProductDate(Date fruitProductDate) {
		this.fruitProductDate = fruitProductDate;
	}

	public Date getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}

	public String getFruitCategoryNo() {
		return fruitCategoryNo;
	}

	public void setFruitCategoryNo(String fruitCategoryNo) {
		this.fruitCategoryNo = fruitCategoryNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
	
	// 추가 게터세터

	public AdminVO getAdmin() {
		return admin;
	}

	public void setAdmin(AdminVO admin) {
		this.admin = admin;
	}

	public FruitCategoryVO getFruitCategory() {
		return fruitCategory;
	}

	public void setFruitCategory(FruitCategoryVO fruitCategory) {
		this.fruitCategory = fruitCategory;
	}
	

	
	
}
