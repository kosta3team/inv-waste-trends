package kr.swdl.model.sales;

public class SaleVO {
	private String fruitNo;
	private String saleNo;
	private String productName;
	private String memberName;
	private String name;
	private int quantity;
	private int price;
	private int totalPrice;
	private String saleDate;
	
	
	
	
	public SaleVO(String fruitNo, String saleNo, String productName, String memberName, String name, int quantity,
			int price, int totalPrice, String saleDate) {
		super();
	
		setFruitNo(fruitNo);
		setSaleNo(saleNo);
		setProductName(productName);
		setMemberName(memberName);
		setName(name);
		setQunatity(quantity);
		setPrice(price);
		setTotalPrice(totalPrice);
		setSaleDate(saleDate);
	}
	
	public SaleVO(String fruitNo, String saleNo, String name, int quantity,
			int price, int totalPrice, String saleDate) {
		this(fruitNo, saleNo, name, null, null, quantity, price, totalPrice, saleDate);
	}
	
	
	
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}




	public String getFruitNo() {
		return fruitNo;
	}
	public void setFruitNo(String fruitNo) {
		this.fruitNo = fruitNo;
	}
	public String getSaleNo() {
		return saleNo;
	}
	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQunatity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	@Override
	public String toString() {
		return "SaleVO [fruitNo=" + fruitNo + ", saleNo=" + saleNo + ", productName=" + productName + ", memberName="
				+ memberName + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", totalPrice="
				+ totalPrice + ", saleDate=" + saleDate + "]";
	}
}
