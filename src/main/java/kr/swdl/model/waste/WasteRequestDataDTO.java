package kr.swdl.model.waste;

import java.util.List;

public class WasteRequestDataDTO {
	private int remainQuantity;
	private List<WasteCategoryVO> wasteCategoryList;
	
	
	public WasteRequestDataDTO(int remainQuantity, List<WasteCategoryVO> wasteCategoryList) {
		super();
		setRemainQuantity(remainQuantity);
		setWasteCategoryList(wasteCategoryList);
	}

	public void setRemainQuantity(int remainQuantity) {
		this.remainQuantity = remainQuantity;
	}
	public List<WasteCategoryVO> getWasteCategoryList() {
		return wasteCategoryList;
	}
	public void setWasteCategoryList(List<WasteCategoryVO> wasteCategoryList) {
		this.wasteCategoryList = wasteCategoryList;
	}
	public int getRemainQuantity() {
		return remainQuantity;
	}

	@Override
	public String toString() {
		//return "remainQuantity=" + remainQuantity + ", wasteCategoryList=" + wasteCategoryList;
		
		StringBuilder str = new StringBuilder();
		str.append("remainQuantity=" + remainQuantity);
		for (WasteCategoryVO vo : wasteCategoryList) {
			str.append(", ");
			str.append("wasteCategoryNo=" + vo.getWasteCategoryNo() + ", wasteCategoryReason=" + vo.getWasteCategoryReason());
		}
		
		return str.toString();
	}

	
}
