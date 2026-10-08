package kr.swdl.model.waste;

public class WasteDetailVO {

	
	private String wasteReqDate;
	private String wasteDate;
	private String wasteCategoryReason;
	private String reasonDetail;
	private int quantity;
	private int itemCode;
	private String itemName;
	private String kindName;
	private String origin;
	private String fruitProductName;
	private int price;
	private float weight;
	private String memberGroupName;
	private String memberName;
	private String adminName;
	
	public WasteDetailVO(String wasteReqDate, String wasteDate, String wasteCategoryReason, String reasonDetail,
			int quantity, int itemCode, String itemName, String kindName, String origin, String fruitProductName,
			int price, float weight, String memberGroupName, String memberName, String adminName) {
		super();
		
		setWasteReqDate(wasteReqDate);
		setWasteDate(wasteDate);
		setWasteCategoryReason(wasteCategoryReason);
		setReasonDetail(reasonDetail);
		setQuantity(quantity);
		setItemCode(itemCode);
		setItemName(itemName);
		setKindName(kindName);
		setOrigin(origin);
		setFruitProductName(fruitProductName);
		setPrice(price);
		setWeight(weight);
		setMemberGroupName(memberGroupName);
		setMemberName(memberName);
		setAdminName(adminName);
	}
	
	public String getWasteReqDate() {
		return wasteReqDate;
	}
	public void setWasteReqDate(String wasteReqDate) {
		this.wasteReqDate = wasteReqDate;
	}
	public String getWasteDate() {
		return wasteDate;
	}
	public void setWasteDate(String wasteDate) {
		this.wasteDate = wasteDate;
	}
	public String getWasteCategoryReason() {
		return wasteCategoryReason;
	}
	public void setWasteCategoryReason(String wasteCategoryReason) {
		this.wasteCategoryReason = wasteCategoryReason;
	}
	public String getReasonDetail() {
		return reasonDetail;
	}
	public void setReasonDetail(String reasonDetail) {
		this.reasonDetail = reasonDetail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getItem_code() {
		return itemCode;
	}
	public void setItemCode(int item_code) {
		this.itemCode = item_code;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String item_name) {
		this.itemName = item_name;
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
	public String getFruitProductName() {
		return fruitProductName;
	}
	public void setFruitProductName(String fruitProductName) {
		this.fruitProductName = fruitProductName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "WasteDetailVO [wasteReqDate=" + wasteReqDate + ", wasteDate=" + wasteDate + ", wasteCategoryReason="
				+ wasteCategoryReason + ", reasonDetail=" + reasonDetail + ", quantity=" + quantity + ", itemCode="
				+ itemCode + ", itemName=" + itemName + ", kindName=" + kindName + ", origin=" + origin
				+ ", fruitProductName=" + fruitProductName + ", price=" + price + ", weight=" + weight
				+ ", memberGroupName=" + memberGroupName + ", memberName=" + memberName + ", adminName=" + adminName
				+ "]";
	}
}
