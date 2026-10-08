package kr.swdl.servlet.fruitproduct;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.DBCP;
import kr.swdl.model.DBCPTest;
import kr.swdl.model.MemberDAO;
import kr.swdl.model.fruitcategory.FruitCategoryDAO;
import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.model.member.MemberVO;
import kr.swdl.servlet.Action;

public class AddFruitProduct implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		
		// 1. LoginAction에서 저장한 로그인 유저의 ID
		String memberId = (String) session.getAttribute("memberId");
		
		// 2. ID를 기반으로 MemberDAO 없이 주소, 연락처등 상세정보 가져옴.
		if (memberId != null) {
			System.out.println("입고요청 진입: 로그인유저 "+memberId);
			MemberVO actualUser = new MemberVO();
			actualUser.setMemberId(memberId);
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBCP.getConnection();
//				String sql = "name, member_name, phone, email, address, detail_address FROM member WHERE member_id = ?";
				// MemberDAO에서 사용된 인자가 한글이라 불가피하게 *을 함. -> 추후 MemberDAO생성이 되면 그에 따라 조정예정
				String sql = "SELECT * FROM member WHERE member_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					System.out.println("DB조회 성공 데이터를 VO에 담음.");
					actualUser.setMemberName(rs.getString("name"));
					actualUser.setName(rs.getString("member_name"));
					actualUser.setPhone(rs.getString("phone"));
					actualUser.setEmail(rs.getString("email"));
					actualUser.setAddress(rs.getString("address"));
					actualUser.setDetailAddress(rs.getString("detail_address"));
				} else {
					System.out.println("DB에 해당 ID 정보가 없습니다.");
				}
			} catch (Exception e) {
				System.out.println("DB조회중 에러발생");
				e.printStackTrace();
			} finally {
				DBCPTest.close(conn, pstmt, rs);
			}
			request.setAttribute("loginUser", actualUser);
		}
		
		String actionType = request.getParameter("actionType");
		FruitCategoryDAO categoryDao = new FruitCategoryDAO();		
		
		// 3. 처음 화면 진입 시
		if (actionType == null || !actionType.equals("submit")) {
			// ★ JSTL 에러 방지를 위해, 여기서 반드시 데이터를 퍼서 보내줘야 합니다.
			request.setAttribute("categoryList", categoryDao.getAllCategories());
			return "view/fruitproduct/addFruitProduct.jsp";
		}
		
		// 4. 폼 제출 시
		FruitProductVO vo = new FruitProductVO();
		
		vo.setName(request.getParameter("itemName"));
		String priceStr = request.getParameter("unitPrice");
		String weightStr = request.getParameter("weight");
		String qtyStr = request.getParameter("qty");
		
		int price = (priceStr == null || priceStr.trim().isEmpty()) ? 0 : Integer.parseInt(priceStr.trim());
		double weight = (weightStr == null || weightStr.trim().isEmpty()) ? 0.0 : Double.parseDouble(weightStr.trim());
		int qty = (qtyStr == null || qtyStr.trim().isEmpty()) ? 0 : Integer.parseInt(qtyStr.trim());
		
		vo.setPrice(price);
		vo.setWeight(weight);
		vo.setQuantity(qty);
		
		// 폼의 히든 인풋에서 올라온 값 세팅
		vo.setFruitCategoryNo(request.getParameter("fruitCategoryNo"));
		vo.setMemberId(request.getParameter("memberId"));
		
		FruitProductService service = new FruitProductService();
		request.setAttribute("result", service.addFruitProduct(vo));
		
		// 화면 다시 로드시 JSTL 에러 방지용 리스트 다시 퍼오기
		request.setAttribute("categoryList", categoryDao.getAllCategories());
		
		return "view/fruitproduct/addFruitProduct.jsp";
	}
}