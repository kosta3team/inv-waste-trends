package kr.swdl.model.waste;

import java.util.Objects;

public class WasteVO {
	private String wasteNo;
	private String wasteDate;
	private String wasteReqDate;
	private String reasonDetail;
	private int quantity;
	private String fruitNo;
	private String memberId;
	private String adminId;
	private String wasteCategoryNo;
	public WasteVO(String wasteNo, String wasteDate, String wasteReqDate, String reasonDetail, int quantity,
			String fruitNo, String memberId, String adminId, String wasteCategoryNo) {
		super();
		setWasteNo(wasteNo);
		setWasteDate(wasteDate);
		setWasteReqDate(wasteReqDate);
		setReasonDetail(reasonDetail);
		setQuantity(quantity);
		setFruitNo(fruitNo);
		setMemberId(memberId);
		setAdminId(adminId);
		setWasteCategoryNo(wasteCategoryNo);
	}
	
	public String getWasteNo() {
		return wasteNo;
	}
	public void setWasteNo(String wasteNo) {
		this.wasteNo = wasteNo;
	}
	public String getWasteDate() {
		return wasteDate;
	}
	public void setWasteDate(String wasteDate) {
		this.wasteDate = wasteDate;
	}
	public String getWasteReqDate() {
		return wasteReqDate;
	}
	public void setWasteReqDate(String wasteReqDate) {
		this.wasteReqDate = wasteReqDate;
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
	public String getFruitNo() {
		return fruitNo;
	}
	public void setFruitNo(String fruitNo) {
		this.fruitNo = fruitNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getWasteCategoryNo() {
		return wasteCategoryNo;
	}
	public void setWasteCategoryNo(String wasteCategoryNo) {
		this.wasteCategoryNo = wasteCategoryNo;
	}

	@Override
	public String toString() {
		return "WasteVO [wasteNo=" + wasteNo + ", wasteDate=" + wasteDate + ", wasteReqDate=" + wasteReqDate
				+ ", reasonDetail=" + reasonDetail + ", quantity=" + quantity + ", fruitNo=" + fruitNo + ", memberId="
				+ memberId + ", adminId=" + adminId + ", wasteCategoryNo=" + wasteCategoryNo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminId, fruitNo, memberId, quantity, reasonDetail, wasteCategoryNo, wasteDate, wasteNo,
				wasteReqDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WasteVO other = (WasteVO) obj;
		return Objects.equals(adminId, other.adminId) && Objects.equals(fruitNo, other.fruitNo)
				&& Objects.equals(memberId, other.memberId) && quantity == other.quantity
				&& Objects.equals(reasonDetail, other.reasonDetail)
				&& Objects.equals(wasteCategoryNo, other.wasteCategoryNo) && Objects.equals(wasteDate, other.wasteDate)
				&& Objects.equals(wasteNo, other.wasteNo) && Objects.equals(wasteReqDate, other.wasteReqDate);
	}
	
	

}
