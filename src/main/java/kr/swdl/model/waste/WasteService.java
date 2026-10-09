package kr.swdl.model.waste;

import java.sql.SQLException;
import java.util.List;
import kr.swdl.model.DBCP;


public class WasteService {


	public int getRemainQuantity(String fruitNo) {
		try {
			return new WasteDAO(DBCP.getConnection()).getRemainQuantity(fruitNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean addWaste(String reasonDeatil, int quantity, String fruitNo, String memberId, String wasteCategoryNo) {
		try {
			return new WasteDAO(DBCP.getConnection()).addWaste(reasonDeatil, quantity, fruitNo, memberId, wasteCategoryNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	public boolean updateWasteRequestStatus(String fruitNo, int wasteRequestQuantity) {
		try {
			return new WasteDAO(DBCP.getConnection()).updateWasteRequestStatus(fruitNo, wasteRequestQuantity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public List<WasteRequestListVO> getMemberWasteList(String memberId) {
		try {
			return new WasteDAO(DBCP.getConnection()).getMemberWasteList(memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<WasteRequestListVO> getMemberWasteListOnlyRequest(String memberId) {
		try {
			return new WasteDAO(DBCP.getConnection()).getMemberWasteListOnlyRequest(memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<WasteRequestListVO> getWasteList() {
		try {
			return new WasteDAO(DBCP.getConnection()).getWasteList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<WasteRequestListVO> getWasteListOnlyRequest() {
		try {
			return new WasteDAO(DBCP.getConnection()).getWasteListOnlyRequest();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public WasteDetailVO getWasteDetail(String wasteNo) {
		try {
			return new WasteDAO(DBCP.getConnection()).getWasteDetail(wasteNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateWasteDate(String adminId, String wasteNo) {
		try {
			return new WasteDAO(DBCP.getConnection()).updateWasteDate(adminId, wasteNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateWasteStatus(String fruitNo) {
		try {
			return new WasteDAO(DBCP.getConnection()).updateWasteStatus(fruitNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public WasteVO getWaste(String wasteNo) {
		try {
			return new WasteDAO(DBCP.getConnection()).getWaste(wasteNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public WasteRequestDataDTO getWasteRequestData(String fruitNo) {
		try {
			return new WasteRequestDataDTO(
					new WasteDAO(DBCP.getConnection()).getRemainQuantity(fruitNo),
					new WasteCategoryDAO(DBCP.getConnection()).getWasteCategoryList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
