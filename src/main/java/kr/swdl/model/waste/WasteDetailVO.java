package kr.swdl.model.waste;

import kr.swdl.util.DateFormatter;

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
	private String fruitPorductNo;
	
	public WasteDetailVO(String wasteReqDate, String wasteDate, String wasteCategoryReason, String reasonDetail,
			int quantity, int itemCode, String itemName, String kindName, String origin, String fruitProductName,
			int price, float weight, String memberGroupName, String memberName, String adminName, String fruitProductNo) {
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
		setFruitPorductNo(fruitProductNo);
	}
	
	public String getWasteReqDate() {
		return wasteReqDate;
	}
	public void setWasteReqDate(String wasteReqDate) {
		this.wasteReqDate = DateFormatter.toYearMonthDay(wasteReqDate);
	}
	public String getWasteDate() {
		return wasteDate;
	}
	public void setWasteDate(String wasteDate) {
		this.wasteDate = DateFormatter.toYearMonthDay(wasteDate);
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
	public int getItemCode() {
		return itemCode;
	}
	public String getFruitPorductNo() {
		return fruitPorductNo;
	}

	public void setFruitPorductNo(String fruitPorductNo) {
		this.fruitPorductNo = fruitPorductNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WasteDetailVO [wasteReqDate=");
		builder.append(wasteReqDate);
		builder.append(", wasteDate=");
		builder.append(wasteDate);
		builder.append(", wasteCategoryReason=");
		builder.append(wasteCategoryReason);
		builder.append(", reasonDetail=");
		builder.append(reasonDetail);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", itemCode=");
		builder.append(itemCode);
		builder.append(", itemName=");
		builder.append(itemName);
		builder.append(", kindName=");
		builder.append(kindName);
		builder.append(", origin=");
		builder.append(origin);
		builder.append(", fruitProductName=");
		builder.append(fruitProductName);
		builder.append(", price=");
		builder.append(price);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", memberGroupName=");
		builder.append(memberGroupName);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", adminName=");
		builder.append(adminName);
		builder.append(", fruitPorductNo=");
		builder.append(fruitPorductNo);
		builder.append("]");
		return builder.toString();
	}

	
}
