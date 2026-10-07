package kr.swdl.model;

public interface Query {


	// ==================== MEMBER ====================

		String GET_MEMBER_ID = "SELECT member_id FROM member WHERE member_id = ?";

		String ADD_MEMBER = "INSERT INTO member(member_id, pw, name, birth, phone, email, member_file, is_company, zip_code, address, detail_address, status, request_date) "
				  + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '대기', SYSDATE)";

		String ADD_COMPANY_MEMBER = "INSERT INTO member(member_id, pw, name, birth, phone, email, member_file, company_file, is_company, member_name, zip_code, address, detail_address, status, request_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'T', ?, ?, ?, ?, '대기', SYSDATE)";

		String GET_MEMBERS = "SELECT member_id, request_date, status, member_name, name, is_company FROM member";

		String GET_MEMBER = "SELECT request_date, name, birth, phone, email, address || ' ' || detail_address AS 주소, member_file FROM member WHERE member_id = ?";

		String GET_COMPANY_MEMBER = "SELECT request_date, name, member_name, birth, phone, email, address || ' ' || detail_address AS 주소, member_file, company_file FROM member WHERE member_id = ?";

		String APPROVE_MEMBER = "UPDATE member SET status = '승인', member_date = SYSDATE WHERE member_id = ?";

		String DELETE_MEMBER = "DELETE FROM member WHERE member_id = ?";
		
		String MEMBER_LOGIN = "SELECT name, member_name, is_company, status FROM member WHERE member_id=? AND pw=?";

		String ADMIN_LOGIN = "SELECT name FROM admin WHERE admin_id=? AND pw=?";

		// ==================== FRUIT PRODUCT ====================

		String ADD_FRUIT_PRODUCT =  "INSERT INTO fruit_product (fruit_no, name, price, weight, quantity, status, request_date, fruit_category_no, member_id)\r\n"
				+ "VALUES ('fd' || LPAD(seq_fruit_no.NEXTVAL, 4, '0'), ?, ?, ?, ? , '입고요청', SYSDATE, ?, ?)";
		
		String GET_FRUIT_PRODUCT_REQUESTS = "SELECT fp.name AS 상품명, fp.quantity AS 입고수량, fp.price AS 단가, "
				+ "fp.quantity * fp.price AS 판매예상금액, SUM(fp.quantity * fp.price) OVER() AS 총판매예상금액, "
				+ "fp.request_date AS 요청일자, fp.fruit_product_date AS 처리일자, fp.status AS 요청상태 "
				+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id "
				+ "WHERE fp.request_date >= TO_DATE(?, 'YYYY-MM-DD') AND fp.request_date <  TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND fp.status = '입고대기' ORDER BY fp.request_date";

		String GET_MY_PENDING_FRUIT_PRODUCTS = "SELECT fp.fruit_no, fp.name, fp.quantity, fp.price, fp.quantity * fp.price AS 판매예상금, "
				+ "SUM(fp.quantity * fp.price) OVER() AS 총판매예상금, m.member_name, m.name AS 회원명, fp.request_date, fp.fruit_product_date AS 처리일자, fp.status "
				+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id "
				+ "WHERE m.member_id = ? AND fp.request_date >= TO_DATE(?, 'YYYY-MM-DD') AND fp.request_date <= TO_DATE(?, 'YYYY-MM-DD') AND fp.status = '입고대기'";

		String GET_PENDING_FRUIT_PRODUCTS = "SELECT fp.fruit_no, fp.name, fp.quantity, fp.price, fp.quantity * fp.price AS 판매예상금, "
				+ "SUM(fp.quantity * fp.price) OVER() AS 총판매예상금, m.member_name, m.name AS 회원명, fp.request_date, fp.fruit_product_date, fp.status "
				+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id "
				+ "WHERE fp.request_date >= TO_DATE(?, 'YYYY-MM-DD') AND fp.request_date <= TO_DATE(?, 'YYYY-MM-DD') AND fp.status = '입고대기'";

		String GET_FRUIT_PRODUCT = "SELECT fc.item_code, fc.item_name, fc.kind_name, fp.name, fc.origin, fp.price, fp.quantity, fp.weight, fp.price * fp.quantity AS total_price, "
				+ "m.name, m.member_name, m.address || ' ' || m.detail_address AS address, m.phone, m.email, a.name "
				+ "FROM fruit_product fp, admin a, member m, fruit_category fc "
				+ "WHERE fp.fruit_category_no = fc.fruit_category_no AND fp.admin_id = a.admin_id AND fp.member_id = m.member_id AND fp.fruit_no = ?";

		String REJECT_FRUIT_PRODUCT = "UPDATE fruit_product SET status='거절' WHERE fruit_no = ?";

		String APPROVE_FRUIT_PRODUCT = "UPDATE fruit_product fp SET fp.status = '정상', "
				+ "fp.admin_id = 'admin001', fp.received_date = SYSDATE, fp.storage_date = SYSDATE + "
				+ "(SELECT storage_date FROM fruit_category WHERE fruit_category_no = fp.fruit_category_no) "
				+ "WHERE fruit_no = ?;";

		
		// ==================== INVENTORY ====================

		String GET_REMAIN_QUANTITY = "SELECT fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
				+ "- NVL((SELECT SUM(w.quantity) FROM waste w WHERE w.fruit_no = fp.fruit_no), 0) AS remain_quantity "
				+ "FROM fruit_product fp WHERE fp.fruit_no = '?'";

		String GET_INVENTORY = "SELECT fp.fruit_no, fp.name, "
				+ "fp.quantity "
				+ "- NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
				+ "- NVL((SELECT SUM(w2.quantity) FROM waste w2 WHERE w2.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
				+ "fp.price, "
				+ "m.member_name, "
				+ "fp.storage_date, "
				+ "w.waste_date, "
				+ "wc.waste_category_reason, "
				+ "fp.status "
				+ "FROM FRUIT_PRODUCT fp "
				+ "JOIN MEMBER m ON fp.member_id = m.member_id "
				+ "LEFT JOIN WASTE w ON fp.fruit_no = w.fruit_no "
				+ "LEFT JOIN WASTE_CATEGORY wc ON w.waste_category_no = wc.waste_category_no "
				+ "WHERE fp.status IN ('정상', '폐기')";

		String GET_INVENTORY_BY_NAME = "SELECT fp.fruit_no, fp.name, fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
				+ "- NVL((SELECT SUM(w.quantity) FROM waste w WHERE w.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
				+ "fp.price, m.member_name, fp.storage_date, w.waste_date, wc.waste_category_reason, fp.status "
				+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id LEFT OUTER JOIN waste w ON fp.fruit_no = w.fruit_no "
				+ "LEFT OUTER JOIN waste_category wc ON w.waste_category_no = wc.waste_category_no "
				+ "WHERE fp.name LIKE '%' || ? || '%' AND fp.status IN ('정상', '폐기')";

		String GET_INVENTORY_BY_MEMBER = "SELECT fp.fruit_no, fp.name, "
				+ "fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
				+ "- NVL((SELECT SUM(w2.quantity) FROM waste w2 WHERE w2.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
				+ "fp.price, "
				+ "m.member_name, "
				+ "fp.storage_date, "
				+ "w.waste_date, "
				+ "wc.waste_category_reason, "
				+ "fp.status "
				+ "FROM FRUIT_PRODUCT fp "
				+ "JOIN MEMBER m ON fp.member_id = m.member_id "
				+ "LEFT JOIN WASTE w ON fp.fruit_no = w.fruit_no "
				+ "LEFT JOIN WASTE_CATEGORY wc ON w.waste_category_no = wc.waste_category_no "
				+ "WHERE fp.status IN ('정상', '폐기') "
				+ "AND m.member_name LIKE '%' || ? || '%'";

		String GET_WASTED_PRODUCTS =  "SELECT fp.fruit_no, fp.name, "
				+ "fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
				+ "- NVL((SELECT SUM(w2.quantity) FROM waste w2 WHERE w2.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
				+ "fp.price, "
				+ "m.member_name, "
				+ "fp.storage_date, "
				+ "w.waste_date, "
				+ "wc.waste_category_reason, "
				+ "fp.status "
				+ "FROM FRUIT_PRODUCT fp "
				+ "JOIN MEMBER m ON fp.member_id = m.member_id "
				+ "LEFT JOIN WASTE w ON fp.fruit_no = w.fruit_no "
				+ "LEFT JOIN WASTE_CATEGORY wc ON w.waste_category_no = wc.waste_category_no "
				+ "WHERE fp.status = '폐기'";

		String GET_NORMAL_PRODUCTS = "SELECT fp.fruit_no, fp.name, fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
				+ "- NVL((SELECT SUM(w2.quantity) FROM waste w2 WHERE w2.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
				+ "fp.price, m.member_name, fp.storage_date, w.waste_date, wc.waste_category_reason, fp.status "
				+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id LEFT JOIN waste w ON fp.fruit_no = w.fruit_no "
				+ "LEFT JOIN waste_category wc ON w.waste_category_no = wc.waste_category_no WHERE fp.status = '정상'";

		
	
	// ==================== Sales ==================== 
	public static final String GET_MEMBER_SALES = "SELECT fp.fruit_no, s.sale_no, fp.name, s.quantity, fp.price, s.quantity * fp.price AS total_price, s.sales_date "
			+ "FROM fruit_product fp JOIN sales s ON fp.fruit_no = s.fruit_no "
			+ "WHERE fp.member_id = ? ORDER BY s.sales_date";
	
	public static final String GET_MEMBER_SALES_BY_PERIOD =  "SELECT "
			+ "fp.fruit_no, "		// 과일 상품 일련번호
			+ "s.sale_no, "			// 판매 일련 번호
			+ "fp.name, "		 	// 과일 상품명
			+ "s.quantity, "		// 판매 수량
			+ "fp.price, "			// 판매 가격
			+ "s.quantity * fp.price AS total_price, " // 판매 총 가격
			+ "s.sales_date "		// 판매 일자
			+ "FROM fruit_product fp "
			+ "JOIN sales s "
			+ "    ON fp.fruit_no = s.fruit_no "
			+ "WHERE fp.member_id = ? "
			+ "AND s.sales_date >= ? "
			+ "AND s.sales_date <= ? "
			+ "ORDER BY s.sales_date";
	
	public static final String GET_ADMIN_SALES = "SELECT "
			+ "fp.fruit_no, "
			+ "s.sale_no, "
			+ "fp.name , "
			+ "m.member_name, "
			+ "m.name, "
			+ "s.quantity, "
			+ "fp.price, "
			+ "s.quantity * fp.price AS total_price, "
			+ "s.sales_date "
			+ "FROM fruit_product fp "
			+ "JOIN sales s "
			+ "    ON fp.fruit_no = s.fruit_no "
			+ "JOIN member m "
			+ "    ON fp.member_id = m.member_id "
			+ "ORDER BY s.sales_date";
	
	
	public static final String GET_ADMIN_SALES_BY_PERIOD = "SELECT "
			+ "fp.fruit_no, "
			+ "s.sale_no, "
			+ "fp.name , "
			+ "m.member_name, "
			+ "m.name, "
			+ "s.quantity, "
			+ "fp.price, "
			+ "s.quantity * fp.price AS total_price, "
			+ "s.sales_date "
			+ "FROM fruit_product fp "
			+ "JOIN sales s "
			+ "    ON fp.fruit_no = s.fruit_no "
			+ "JOIN member m "
			+ "    ON fp.member_id = m.member_id "
			+ "WHERE s.sales_date >= ? "
			+ "AND s.sales_date <= ? "
			+ "ORDER BY s.sales_date";
	
	public static final String GET_ADMIN_SALES_TOTAL_PRICE = "SELECT SUM(s.quantity * fp.price) AS all_total_price FROM fruit_product fp JOIN sales s ON fp.fruit_no = s.fruit_no";
	
	public static final String GET_ADMIN_SALES_TOTAL_PRICE_BY_PERIOD = "SELECT SUM(s.quantity * fp.price) AS all_total_price "
			+ "FROM fruit_product fp "
			+ "JOIN sales s ON fp.fruit_no = s.fruit_no "
			+ "WHERE s.sales_date >= ? AND s.sales_date <= ?";
	
	public static final String GET_MEMBER_SALES_TOTAL_PRICE = "SELECT SUM(s.quantity * fp.price) AS all_total_price "
			+ "FROM fruit_product fp "
			+ "JOIN sales s ON fp.fruit_no = s.fruit_no "
			+ "WHERE fp.member_id = ?";

	public static final String GET_MEMBER_SALES_TOTAL_PRICE_BY_PERIOD = "SELECT SUM(s.quantity * fp.price) AS all_total_price "
			+ "FROM fruit_product fp JOIN sales s ON fp.fruit_no = s.fruit_no "
			+ "WHERE fp.member_id = ? "
			+ "AND s.sales_date >= ? "
			+ "AND s.sales_date <= ?";
	
	// ==================== Waste ====================
	public static final String ADD_WASTE_REQ =  "INSERT INTO waste (waste_no, waste_date, waste_req_date, reason_detail, quantity, fruit_no, member_id, admin_id, waste_category_no) "
			+ "VALUES ('wa' || LPAD(seq_waste_no.NEXTVAL, 4, '0'), NULL, SYSDATE, ?, ?, ?, ?, NULL, ?)";
	
	public static final String SET_FRUIT_STATUS_TO_WASTE_REQ = "UPDATE fruit_product fp SET status = '폐기 요청' WHERE fp.fruit_no = ? "
			+ "AND (fp.quantity "
			+ "- NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
			+ "- NVL((SELECT SUM(w.quantity) FROM waste w WHERE w.fruit_no = fp.fruit_no), 0)) <= ?";
	
	public static final String GET_MEMBER_WASTES = "SELECT  waste_no, waste_req_date, waste_date "
			+ "FROM waste "
			+ "WHERE member_id = ?";
	
	public static final String GET_MEMBER_WASTES_BY_PERIOD = "SELECT waste_no, waste_req_date, waste_req_date, waste_date "
			+ "FROM waste WHERE member_id = ? "
			+ "AND waste_req_date IS NOT NULL "
			+ "AND waste_date IS NULL";
	
	public static final String GET_ADMIN_WASTES = "SELECT w.waste_no, w.waste_req_date, w.waste_date, m.member_name, m.name "
			+ "FROM waste w, member m "
			+ "WHERE w.member_id = m.member_id";

	public static final String GET_ADMIN_WASTES_ONLY_REQUEST = "SELECT w.waste_no, w.waste_req_date, w.waste_date, m.member_name, m.name "
			+ "FROM waste w, member m "
			+ "WHERE w.member_id = m.member_id "
			+ "AND  w.waste_req_date IS NOT NULL "
			+ "AND w.waste_date IS NULL";	
	
	public static final String GET_WASTE_DETAIL = "SELECT "
			+ "w.waste_req_date, "
			+ "w.waste_date, "
			+ "wc.waste_category_reason, "
			+ "w.reason_detail, "
			+ "w.quantity, "
			+ "fc.item_code, "
			+ "fc.item_name, "
			+ "fc.kind_name, "
			+ "fc.origin, "
			+ "fp.name, "
			+ "fp.price, "
			+ "fp.weight, "
			+ "m.member_name, "
			+ "m.name, "
			+ "a.name "
			+ "FROM "
			+ "fruit_category fc, "
			+ "fruit_product fp, "
			+ "member m, "
			+ "admin a, "
			+ "waste w, "
			+ "waste_category wc "
			+ "WHERE "
			+ "w.fruit_no = fp.fruit_no AND "
			+ "w.member_id = m.member_id AND "
			+ "w.waste_category_no = wc.waste_category_no AND "
			+ "fp.fruit_category_no = fc.fruit_category_no AND "
			+ "fp.admin_id = a.admin_id AND "
			+ "w.waste_no = ?";
	
	public static final String SET_WASTE_REQUEST_APPROVE= "UPDATE waste SET "
			+ "waste_date = SYSDATE, "
			+ "admin_id = ? "
			+ "WHERE waste_no = ?";
	
	public static final String SET_FRUIT_STATUS_TO_WASTE = "UPDATE fruit_product SET status = '폐기' WHERE fruit_no =?";
	
	public static final String GET_WASTE_BY_WASTE_NO = "SELECT waste_no ,waste_date ,waste_req_date, reason_detail, quantity, fruit_no, member_id, admin_id, waste_category_no "
			+ "FROM waste "
			+ "WHERE waste_no = ?";

}

