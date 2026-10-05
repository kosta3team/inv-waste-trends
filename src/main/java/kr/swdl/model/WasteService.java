package kr.swdl.model;

import java.sql.SQLException;

public class WasteService {
	
	// 용어를 DTO를 써야할지 VO를 써야할지.. 
	public WasteDetailDTO getWasteDetail(String wasteNo) {
		
		WasteDetailDTO dto = null;
		
		try {
			 WasteVO wasteVO = new WasteDAO(DBCP.getConnection()).getWaste(wasteNo);
			 FruitProductVO fruitVO = new FruitProductDAO(DBCP.getConnection()).getFruitProduct(wasteVO.getFruitNo());
			 MemberVO memberVO = new MemberDAO(DBCP.getConnection()).getMember(wasteVO.getMemberId());
			 WasteCategoryVO wasteCategoryVO = new WasteCategoryDAO(DBCP.getConnection()).getWasteCategory(wasteVO.getWasteCategoryNo());
			 FruitCategoryVO fruitCategoryVO = new FruitCategoryDAO(DBCP.getConnection()).getFruitCategory(fruitVO.getFruitCategoryNo());
			 AdminVO adminVO = new AdminDAO(DBCP.getConnection()).getAdmin(wasteVO.getAdminId());
			 
			 WasteDetailDTO wasteDetailDTO = new WasteDetailDTO(
					 wasteVO,
					 fruitVO,
					 memberVO,
					 adminVO,
					 wasteCategoryVO,
					 fruitCategoryVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;
	}
}
