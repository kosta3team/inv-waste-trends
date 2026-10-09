package kr.swdl.servlet.fruitproduct;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.swdl.model.DBCPTest;
import kr.swdl.model.fruitcategory.FruitCategoryDAO;
import kr.swdl.model.fruitcategory.FruitCategoryVO;
import kr.swdl.model.fruitproduct.FruitProductService;
import kr.swdl.model.fruitproduct.FruitProductVO;
import kr.swdl.model.member.MemberVO;
import kr.swdl.servlet.Action;

public class AddFruitProduct implements Action {

    @Override
    public String execute(HttpServletRequest request)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // =========================================================
        // 1. 로그인한 조합원 ID
        // =========================================================

        String memberId = (String) session.getAttribute("memberId");

        System.out.println("=================================");
        System.out.println("[AddFruitProduct] memberId = " + memberId);


        // =========================================================
        // 2. 조합원 정보 조회
        // =========================================================

        MemberVO actualUser = null;

        if (memberId != null) {

            actualUser = new MemberVO();
            actualUser.setMemberId(memberId);

            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {

                conn = DBCPTest.getConnection();

                String sql =
                    "SELECT name, member_name, phone, email, address, detail_address " +
                    "FROM member " +
                    "WHERE member_id = ?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, memberId);

                rs = pstmt.executeQuery();

                if (rs.next()) {

                    actualUser.setName(rs.getString("name"));
                    actualUser.setMemberName(rs.getString("member_name"));
                    actualUser.setPhone(rs.getString("phone"));
                    actualUser.setEmail(rs.getString("email"));
                    actualUser.setAddress(rs.getString("address"));
                    actualUser.setDetailAddress(rs.getString("detail_address"));

                    System.out.println("[AddFruitProduct] 회원정보 조회 성공");

                } else {

                    System.out.println(
                        "[AddFruitProduct] 회원정보 없음 - memberId = "
                        + memberId
                    );
                }

            } catch (Exception e) {

                System.out.println("[AddFruitProduct] 회원정보 조회 오류");
                e.printStackTrace();

            } finally {

                DBCPTest.close(conn, pstmt, rs);
            }

            request.setAttribute("loginUser", actualUser);

            System.out.println(
                "[AddFruitProduct] loginUser.memberId = "
                + actualUser.getMemberId()
            );

            System.out.println(
                "[AddFruitProduct] loginUser.memberName = "
                + actualUser.getMemberName()
            );

            System.out.println(
                "[AddFruitProduct] loginUser.name = "
                + actualUser.getName()
            );

            System.out.println(
                "[AddFruitProduct] loginUser.phone = "
                + actualUser.getPhone()
            );

            System.out.println(
                "[AddFruitProduct] loginUser.email = "
                + actualUser.getEmail()
            );

            System.out.println(
                "[AddFruitProduct] loginUser.address = "
                + actualUser.getAddress()
            );

            System.out.println(
                "[AddFruitProduct] loginUser.detailAddress = "
                + actualUser.getDetailAddress()
            );

        } else {

            System.out.println("[AddFruitProduct] memberId가 null입니다.");
        }


        // =========================================================
        // 3. 상품 카테고리 조회
        // =========================================================

        FruitCategoryDAO categoryDao = new FruitCategoryDAO();

        List<FruitCategoryVO> categoryList =
                categoryDao.getAllCategories();

        System.out.println(
            "[AddFruitProduct] categoryList size = "
            + categoryList.size()
        );

        request.setAttribute("categoryList", categoryList);


        // =========================================================
        // 4. 최초 화면 진입
        // =========================================================

        String actionType = request.getParameter("actionType");

        if (actionType == null || !actionType.equals("submit")) {

            return "view/fruitproduct/addFruitProduct.jsp";
        }


        // =========================================================
        // 5. 등록 처리
        // =========================================================

        FruitProductVO vo = new FruitProductVO();

        vo.setName(request.getParameter("itemName"));

        String priceStr = request.getParameter("unitPrice");
        String weightStr = request.getParameter("weight");
        String qtyStr = request.getParameter("qty");

        int price =
            (priceStr == null || priceStr.trim().isEmpty())
            ? 0
            : Integer.parseInt(priceStr.trim());

        double weight =
            (weightStr == null || weightStr.trim().isEmpty())
            ? 0.0
            : Double.parseDouble(weightStr.trim());

        int qty =
            (qtyStr == null || qtyStr.trim().isEmpty())
            ? 0
            : Integer.parseInt(qtyStr.trim());

        vo.setPrice(price);
        vo.setWeight(weight);
        vo.setQuantity(qty);

        vo.setFruitCategoryNo(
            request.getParameter("fruitCategoryNo")
        );

        // hidden input의 memberId 대신 세션의 memberId 사용
        vo.setMemberId(memberId);


        // =========================================================
        // 6. DB 등록
        // =========================================================

        FruitProductService service = new FruitProductService();

        boolean result = service.addFruitProduct(vo);

        request.setAttribute("result", result);


        // =========================================================
        // 7. 등록 후 화면
        // =========================================================

        return "view/fruitproduct/addFruitProduct.jsp";
    }
}