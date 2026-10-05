package kr.swdl.model;

public class WasteDetailDTO {

	private WasteVO wasteVO;
	private FruitProductVO fruitVO;
	private MemberVO memberVO;
	private AdminVO adminVO;
	private WasteCategoryVO wasteCategoryVO;
	private FruitCategoryVO fruitCategoryVO;
	

	
	public WasteDetailDTO(WasteVO wasteVO, FruitProductVO fruitVO, MemberVO memberVO, AdminVO adminVO,
			WasteCategoryVO wasteCategoryVO, FruitCategoryVO fruitCategoryVO) {
		super();
		setWasteVO(wasteVO);
		setFruitVO(fruitVO);
		setMemberVO(memberVO);
		setAdminVO(adminVO);
		setWasteCategoryVO(wasteCategoryVO);
		setFruitCategoryVO(fruitCategoryVO);
	}
	
	public WasteVO getWasteVO() {
		return wasteVO;
	}
	public void setWasteVO(WasteVO wasteVO) {
		this.wasteVO = wasteVO;
	}
	public FruitProductVO getFruitVO() {
		return fruitVO;
	}
	public void setFruitVO(FruitProductVO fruitVO) {
		this.fruitVO = fruitVO;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public AdminVO getAdminVO() {
		return adminVO;
	}
	public void setAdminVO(AdminVO adminVO) {
		this.adminVO = adminVO;
	}
	public WasteCategoryVO getWasteCategoryVO() {
		return wasteCategoryVO;
	}
	public void setWasteCategoryVO(WasteCategoryVO wasteCategoryVO) {
		this.wasteCategoryVO = wasteCategoryVO;
	}
	public FruitCategoryVO getFruitCategoryVO() {
		return fruitCategoryVO;
	}
	public void setFruitCategoryVO(FruitCategoryVO fruitCategoryVO) {
		this.fruitCategoryVO = fruitCategoryVO;
	}
	
	
	
	
	
}
