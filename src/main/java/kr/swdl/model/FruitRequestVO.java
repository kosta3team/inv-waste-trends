package kr.swdl.model;


public class FruitRequestVO {
	private String fruitNo;
    private String name;               // 상품명
    private int quantity;              // 입고수량
    private int price;                 // 단가
    private long expectedAmount;       // 판매예상금액 (quantity * price)
    private long totalExpectedAmount;  // 총판매예상금액 (SUM OVER, 조회된 전체 행 합계)
    private String memberName;
    private String mName;
    private String requestDate;        // 요청일자 (yyyy-MM-dd)
    private String fruitProductDate;   // 처리일자 (NULL 허용)
    private String status;             // 요청상태
    
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getExpectedAmount() {
		return expectedAmount;
	}
	public void setExpectedAmount(long expectedAmount) {
		this.expectedAmount = expectedAmount;
	}
	public long getTotalExpectedAmount() {
		return totalExpectedAmount;
	}
	public void setTotalExpectedAmount(long totalExpectedAmount) {
		this.totalExpectedAmount = totalExpectedAmount;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "FruitRequestVO [fruitNo=" + fruitNo + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", expectedAmount=" + expectedAmount + ", totalExpectedAmount=" + totalExpectedAmount
				+ ", memberName=" + memberName + ", mName=" + mName + ", requestDate=" + requestDate
				+ ", fruitProductDate=" + fruitProductDate + ", status=" + status + "]";
	}

    
    
}