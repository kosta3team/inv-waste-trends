package kr.swdl.model.waste;

import java.util.Objects;

public class WasteCategoryVO {
	private String wasteCategoryNo;
	private String wasteCategoryReason;

	public WasteCategoryVO(String wasteCategoryNo, String wasteCategoryReason) {
		super();
		setWasteCategoryNo(wasteCategoryNo);
		setWasteCategoryReason(wasteCategoryReason);
	}

	public String getWasteCategoryNo() {
		return wasteCategoryNo;
	}
	public void setWasteCategoryNo(String wasteCategoryNo) {
		this.wasteCategoryNo = wasteCategoryNo;
	}
	public String getWasteCategoryReason() {
		return wasteCategoryReason;
	}
	public void setWasteCategoryReason(String wasteCategoryReason) {
		this.wasteCategoryReason = wasteCategoryReason;
	}
	
	@Override
	public String toString() {
		return "WasteCategoryVO [wasteCategoryNo=" + wasteCategoryNo + ", wasteCategoryReason=" + wasteCategoryReason
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(wasteCategoryNo, wasteCategoryReason);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WasteCategoryVO other = (WasteCategoryVO) obj;
		return Objects.equals(wasteCategoryNo, other.wasteCategoryNo)
				&& Objects.equals(wasteCategoryReason, other.wasteCategoryReason);
	}


}
