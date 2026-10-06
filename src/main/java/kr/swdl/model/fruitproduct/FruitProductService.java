package kr.swdl.model.fruitproduct;


import java.util.List;

public class FruitProductService {
	
	private FruitProductDAO dao = new FruitProductDAO();
	
	// 1. 조합원 입고 요청
	public boolean addFruitProduct(FruitProductVO vo) {
		boolean result = false;
		
		if (dao.addFruitProduct(vo)) {
			result = true;
		}
		return result;
	}
	
	// 2. 관리자 기본 전체 입고요청목록 조회
	public List<FruitProductVO> getFruitProductRequests(){
		return dao.getFruitProductRequests();
	}
	
	// 3. 관리자 기간 전체 입고요청목록 조회
	public List<FruitProductVO> getFruitProductRequestsPeriod(String startDate, String endDate){
		return dao.getFruitProductRequestsPeriod(startDate, endDate);
	}
	
	// 4. 조합원 기본 전체 입고요청목록 조회
	public List<FruitProductVO> getMyPendingFruitProducts (String memberId){
		return dao.getMyPendingFruitProducts(memberId);
	}
	
	// 5. 조합원 기간 전체 입고요청목록 조회
	public List<FruitProductVO> getMyPendingFruitProductsPeriod (String memberId, String startDate, String endDate){
		return dao.getMyPendingFruitProductsPeriod(memberId, startDate, endDate);
	}
	
	// 6. 관리자 기본 입고요청상태만 목록 조회
	public List<FruitProductVO> getPendingFruitProducts(){
		return dao.getPendingFruitProducts();
	}
	
	// 7. 관리자 기간 입고요청상태만 목록 조회
	public List<FruitProductVO> getPendingFruitProductsPeriod(String startDate, String endDate){
		return dao.getPendingFruitProductsPeriod(startDate, endDate);
	}
	
	// 8. 공통 입고 요청한 상품정보 상세조회
	public FruitProductVO getFruitProduct(String fruitNo) {
		return dao.getFruitProduct(fruitNo);
	}
	
	// 9. 관리자 입고 요청을 거절
	public boolean rejectFruitProduct (String adminId, String fruitNo) {
		return dao.rejectFruitProduct(adminId, fruitNo);
	}
	
	// 10. 관리자 입고 요청을 승인
	public boolean approveFruitProduct (String adminId, String fruitNo) {
		return dao.approveFruitProduct(adminId, fruitNo);
	}
}
