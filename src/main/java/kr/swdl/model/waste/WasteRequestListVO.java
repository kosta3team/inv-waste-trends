package kr.swdl.model.waste;

import kr.swdl.util.DateFormatter;

public class WasteRequestListVO {
	private String wasteNo;
	private String wasteReqDate;
	private String wasteDate;
	private String memberName;
	private String name;
	private String status;
	
	
	
	
	public WasteRequestListVO(String wasteNo, String wasteReqDate, String wasteDate, String memberName, String name) {
		super();
		setWasteNo(wasteNo);
		setWasteReqDate(wasteReqDate);
		setWasteDate(wasteDate);
		setMemberName(memberName);
		setName(name);
		setStatus();
	}
	
	public WasteRequestListVO(String wasteNo, String wasteReqDate, String wasteDate) {
		this(wasteNo, wasteReqDate, wasteDate, null, null);
	}
	
	public String getWasteNo() {
		return wasteNo;
	}
	public void setWasteNo(String wasteNo) {
		this.wasteNo = wasteNo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus() {
		String st = "승인";
		if (null == getWasteDate())
			st = "대기";
		
		this.status = st;
	}

	@Override
	public String toString() {
		return "WasteRequestListVO [wasteNo=" + wasteNo + ", wasteReqDate=" + wasteReqDate + ", wasteDate=" + wasteDate
				+ ", memberName=" + memberName + ", name=" + name + ", status=" + status + "]";
	}

	
}
