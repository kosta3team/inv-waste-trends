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

	// 1. 조합원 입고 요청
	String ADD_FRUIT_PRODUCT = "INSERT INTO fruit_product (fruit_no, name, price, weight, quantity, status, request_date, fruit_category_no, member_id) "
			+ "VALUES ('fd' || LPAD(seq_fruit_no.NEXTVAL, 4, '0'), ?, ?, ?, ? , '입고요청', SYSDATE, ?, ?)";
	
	// 2. 관리자 기본 전체 입고요청목록 조회
	String GET_FRUIT_PRODUCT_REQUESTS = 
			"SELECT "
			+ "fp.fruit_no AS 상품번호, "
			+ "fp.name AS 상품명,  "
			+ "fp.quantity AS 입고수량, "
			+ "fp.price AS 단가, "
			+ "fp.quantity * fp.price AS 총판매예상금액, "
			+ "m.member_name AS 조합원명, "
			+ "m.name AS 이름, "
			+ "fp.request_date AS 요청일자, "
			+ "fp.fruit_product_date AS 처리일자, "
			+ "fp.status AS 요청상태 "
			+ "FROM fruit_product fp "
			+ "JOIN member m "
			+ "ON fp.member_id = m.member_id "
			+ "ORDER BY fp.request_date DESC";
	
	// 3. 관리자 기간 전체 입고요청목록 조회
	String GET_FRUIT_PRODUCT_REQUESTS_PERIOD = 
			"SELECT "
			+ "fp.fruit_no AS 상품번호, "
			+ "fp.name AS 상품명,  "
			+ "fp.quantity AS 입고수량, "
			+ "fp.price AS 단가, "
			+ "fp.quantity * fp.price AS 총판매예상금액, "
			+ "m.member_name AS 조합원명, "
			+ "m.name AS 이름, "
			+ "fp.request_date AS 요청일자, "
			+ "fp.fruit_product_date AS 처리일자, "
			+ "fp.status AS 요청상태 "
			+ "FROM fruit_product fp "
			+ "JOIN member m "
			+ "ON fp.member_id = m.member_id "
			+ "WHERE  "
			+ "        fp.request_date >= TO_DATE(?, 'YYYY-MM-DD') "
			+ "        AND fp.request_date < TO_DATE(?,'YYYY-MM-DD')+1 "
			+ "ORDER BY fp.request_date DESC";
	
	// 4. 관리자 기본 입고요청상태만 목록 조회
	String GET_PENDING_FRUIT_PRODUCTS =
			"SELECT "
			+ "fp.fruit_no AS 상품번호, "
			+ "fp.name AS 상품명,  "
			+ "fp.quantity AS 입고수량, "
			+ "fp.price AS 단가, "
			+ "fp.quantity * fp.price AS 총판매예상금액, "
			+ "m.member_name AS 조합원명, "
			+ "m.name AS 이름, "
			+ "fp.request_date AS 요청일자, "
			+ "fp.fruit_product_date AS 처리일자, "
			+ "fp.status AS 요청상태 "
			+ "FROM fruit_product fp "
			+ "JOIN member m "
			+ "ON fp.member_id = m.member_id "
			+ "WHERE fp.status = '입고요청' "
			+ "ORDER BY fp.request_date DESC";
	
	// 5. 관리자 기간 입고요청상태만 목록 조회
	String GET_PENDING_FRUIT_PRODUCTS_PERIOD =
			"SELECT "
			+ "fp.fruit_no AS 상품번호, "
			+ "fp.name AS 상품명,  "
			+ "fp.quantity AS 입고수량, "
			+ "fp.price AS 단가, "
			+ "fp.quantity * fp.price AS 총판매예상금액, "
			+ "m.member_name AS 조합원명, "
			+ "m.name AS 이름, "
			+ "fp.request_date AS 요청일자, "
			+ "fp.fruit_product_date AS 처리일자, "
			+ "fp.status AS 요청상태 "
			+ "FROM fruit_product fp "
			+ "JOIN member m "
			+ "ON fp.member_id = m.member_id "
			+ "WHERE  "
			+ "fp.status = '입고요청' "
			+ "AND fp.request_date >= TO_DATE(?, 'YYYY-MM-DD') "
			+ "        AND fp.request_date < TO_DATE(?,'YYYY-MM-DD')+1 "
			+ "ORDER BY fp.request_date DESC";
	
	// 6. 조합원 기본 전체 입고요청목록 조회
	String GET_MY_FRUIT_PRODUCT_REQUESTS =
			"SELECT "
			+ "fruit_no AS 상품번호, "
			+ "name AS 상품명,  "
			+ "quantity AS 입고수량, "
			+ "price AS 단가, "
			+ "quantity * price AS 총판매예상금액, "
			+ "request_date AS 요청일자, "
			+ "fruit_product_date AS 처리일자, "
			+ "status AS 요청상태 "
			+ "FROM fruit_product "
			+ "WHERE member_id = ? "
			+ "ORDER BY request_date DESC";
	
	// 7. 조합원 기간 전체 입고요청목록 조회
	String GET_MY_FRUIT_PRODUCT_REQUESTS_PERIOD =
			"SELECT "
			+ "fruit_no AS 상품번호, "
			+ "name AS 상품명,  "
			+ "quantity AS 입고수량, "
			+ "price AS 단가, "
			+ "quantity * price AS 총판매예상금액, "
			+ "request_date AS 요청일자, "
			+ "fruit_product_date AS 처리일자, "
			+ "status AS 요청상태 "
			+ "FROM fruit_product "
			+ "WHERE member_id = ? "
			+ "        AND request_date >= TO_DATE(?, 'YYYY-MM-DD') "
			+ "        AND request_date < TO_DATE(?,'YYYY-MM-DD')+1 "
			+ "ORDER BY request_date DESC";

	// 8. 조합원 기본 입고요청상태만 목록 조회
	String GET_MY_PENDING_FRUIT_PRODUCTS =
			"SELECT "
			+ "fruit_no AS 상품번호, "
			+ "name AS 상품명,  "
			+ "quantity AS 입고수량, "
			+ "price AS 단가, "
			+ "quantity * price AS 총판매예상금액, "
			+ "request_date AS 요청일자, "
			+ "fruit_product_date AS 처리일자, "
			+ "status AS 요청상태 "
			+ "FROM fruit_product "
			+ "WHERE member_id = ? "
			+ "AND status = '입고요청' "
			+ "ORDER BY request_date DESC";

	// 9. 조합원 기간 입고 요청만 기본 목록 조회
	String GET_MY_PENDING_FRUIT_PRODUCTS_PERIOD =
			"SELECT "
			+ "fruit_no AS 상품번호, "
			+ "name AS 상품명,  "
			+ "quantity AS 입고수량, "
			+ "price AS 단가, "
			+ "quantity * price AS 총판매예상금액, "
			+ "request_date AS 요청일자, "
			+ "fruit_product_date AS 처리일자, "
			+ "status AS 요청상태 "
			+ "FROM fruit_product "
			+ "WHERE member_id = ? "
			+ "AND status = '입고요청' "
			+ "        AND request_date >= TO_DATE(?, 'YYYY-MM-DD') "
			+ "        AND request_date < TO_DATE(?,'YYYY-MM-DD')+1 "
			+ "ORDER BY request_date DESC";
	
	// 10. 관리자 입고 요청한 상품정보 상세조회
	String GET_FRUIT_PRODUCT = 
			"SELECT "
			+ "fp.request_date AS 요청일자, "
			+ "        fc.item_code AS 품목코드, "
			+ "        fc.item_name AS 품목, "
			+ "        fc.kind_name AS 품종, "
			+ "        fp.name AS 상품명, "
			+ "        fc.origin AS 원산지, "
			+ "        fp.price AS 단가, "
			+ "        fp.quantity AS 입고수량, "
			+ "        fp.weight AS 중량, "
			+ "        fp.quantity * fp.price AS 총판매예상금액, "
			+ "        m.member_name AS 조합원명, "
			+ "        m.name AS 이름, "
			+ "        m.address || ' ' || m.detail_address AS 주소, "
			+ "        m.phone AS 전화번호, "
			+ "        m.email AS 이메일, "
			+ "        a.name AS 입고처리자 "
			+ "        fp.status AS 요청상태"
			+ "FROM fruit_product fp "			
			+ "LEFT JOIN fruit_category fc "
			+ "ON fp.fruit_category_no = fc.fruit_category_no "
			+ "LEFT JOIN admin a "
			+ "ON fp.admin_id = a.admin_id "
			+ "JOIN member m "
			+ "ON fp.member_id = m.member_id "
			+ "WHERE fp.fruit_no = ?";
	
	// 11. 조합원 입고 요청한 상품정보 상세조회
	String GET_MY_FRUIT_PRODUCT =
			"SELECT "
			+ "fp.request_date AS 요청일자, "
			+ "        fc.item_code AS 품목코드, "
			+ "        fc.item_name AS 품목, "
			+ "        fc.kind_name AS 품종, "
			+ "        fp.name AS 상품명, "
			+ "        fc.origin AS 원산지, "
			+ "        fp.price AS 단가, "
			+ "        fp.quantity AS 입고수량, "
			+ "        fp.weight AS 중량, "
			+ "        fp.quantity * fp.price AS 총판매예상금액, "
			+ "        m.member_name AS 조합원명, "
			+ "        m.name AS 이름, "
			+ "        m.address || ' ' || m.detail_address AS 주소, "
			+ "        m.phone AS 전화번호, "
			+ "        m.email AS 이메일, "
			+ "        a.name AS 입고처리자 "
			+ "        fp.status AS 요청상태"
			+ "FROM fruit_product fp "
			+ "LEFT JOIN fruit_category fc "
			+ "ON fp.fruit_category_no = fc.fruit_category_no "
			+ "LEFT JOIN admin a "
			+ "ON fp.admin_id = a.admin_id "
			+ "JOIN member m "
			+ "ON fp.member_id = m.member_id "
			+ "WHERE fp.fruit_no = ? "
			+ "AND fp.member_id = ?";
	
	// 12. 관리자 입고 요청을 거절
	String REJECT_FRUIT_PRODUCT = "UPDATE fruit_product SET status='거절', admin_id = ?, fruit_product_date = SYSDATE WHERE  fruit_no = ?";
	
	// 13. 관리자 입고 요청을 승인
	String APPROVE_FRUIT_PRODUCT = "UPDATE fruit_product SET status='정상', admin_id = ?, fruit_product_date = SYSDATE WHERE  fruit_no = ?";

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
	// // ==================== Waste ====================
		public static final String ADD_WASTE_REQ =  "INSERT INTO waste (waste_no, waste_date, waste_req_date, reason_detail, quantity, fruit_no, member_id, admin_id, waste_category_no) "
					+ "VALUES ('wa' || LPAD(seq_waste_no.NEXTVAL, 4, '0'), NULL, SYSDATE, ?, ?, ?, ?, NULL, ?)";
			
			public static final String SET_FRUIT_STATUS_TO_WASTE_REQ = "UPDATE fruit_product fp SET status = '폐기 요청' WHERE fp.fruit_no = ? "
					+ "AND (fp.quantity "
					+ "- NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
					+ "- NVL((SELECT SUM(w.quantity) FROM waste w WHERE w.fruit_no = fp.fruit_no), 0)) <= ?";
			
			public static final String GET_MEMBER_WASTES = "SELECT  waste_no, waste_req_date, waste_date "
					+ "FROM waste "
					+ "WHERE member_id = ?"
					+ "ORDER BY waste_req_date DESC";
			
			public static final String GET_MEMBER_WASTES_BY_PERIOD = "SELECT waste_no, waste_req_date, waste_req_date, waste_date "
					+ "FROM waste WHERE member_id = ? "
					+ "AND waste_req_date IS NOT NULL "
					+ "AND waste_date IS NULL"
					+ "ORDER BY waste_req_date DESC";
			
			public static final String GET_ADMIN_WASTES = "SELECT w.waste_no, w.waste_req_date, w.waste_date, m.member_name, m.name "
					+ "FROM waste w, member m "
					+ "WHERE w.member_id = m.member_id "
					+ "ORDER BY w.waste_req_date DESC";

			public static final String GET_ADMIN_WASTES_ONLY_REQUEST = "SELECT w.waste_no, w.waste_req_date, w.waste_date, m.member_name, m.name "
					+ "FROM waste w, member m "
					+ "WHERE w.member_id = m.member_id "
					+ "AND  w.waste_req_date IS NOT NULL "
					+ "AND w.waste_date IS NULL "
					+ "ORDER BY w.waste_req_date DESC";	
			
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
					+ "a.name, "
					+ "fp.fruit_no "
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




	// ==================== WASTE ====================
	String ADD_WASTE = "INSERT INTO waste (waste_no, waste_date, waste_req_date, reason_detail, quantity, fruit_no, member_id, admin_id, waste_category_no) "
			+ "VALUES ('wa' || LPAD(seq_waste_no.NEXTVAL, 4, '0'), NULL, 'sysdate', ?, ?, ?, ?, NULL, ?)";

	String REQUEST_WASTE = "UPDATE fruit_product fp SET status = '폐기 요청' WHERE fp.fruit_no = ? "
			+ "AND (fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
			+ "- NVL((SELECT SUM(w.quantity) FROM waste w WHERE w.fruit_no = fp.fruit_no), 0)) <= ?";

	String GET_MY_WASTE_REQUESTS = "SELECT fp.request_date AS 폐기요청일자, fp.name AS 이름, fp.status AS 폐기요청상태 "
			+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id "
			+ "WHERE m.member_id = ? AND fp.status IN ('폐기요청', '폐기') ORDER BY fp.request_date DESC";

	String GET_WASTE_REQUESTS = "SELECT fp.request_date AS 폐기요청일자, m.member_name AS 조합원명, fp.name AS 이름, fp.status AS 폐기요청상태 "
			+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id "
			+ "WHERE fp.status IN ('폐기요청', '폐기') ORDER BY fp.request_date DESC";

	String GET_PENDING_WASTE_REQUESTS = "SELECT fp.request_date AS 폐기요청일자, m.member_name AS 조합원명, fp.name AS 이름, fp.status AS 폐기요청상태 "
			+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id "
			+ "WHERE fp.status '폐기요청' ORDER BY fp.request_date DESC";

	String GET_WASTE = "SELECT w.waste_req_date, wc.waste_category_reason, w.reason_detail, w.quantity, fc.item_code, fc.item_name, "
			+ "fc.kind_name, fc.origin, fp.name, fp.price, fp.weight, m.member_name, m.name, a.name "
			+ "FROM fruit_category fc, fruit_product fp, member m, admin a, waste w, waste_category wc "
			+ "WHERE w.fruit_no = fp.fruit_no AND w.member_id = m.member_id AND w.waste_category_no = wc.waste_category_no "
			+ "AND fp.fruit_category_no = fc.fruit_category_no AND fp.admin_id = a.admin_id AND w.waste_no = ?";

	String APPROVE_WASTE = "UPDATE waste SET waste_date = 'sysdate', admin_id = ? WHERE fruit_no = ? AND waste_date IS NULL";

	String COMPLETE_WASTE = "UPDATE fruit_product SET status = '폐기' WHERE fruit_no = ?";

	// ==================== INVENTORY ====================

	String GET_REMAIN_QUANTITY = "SELECT fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
			+ "- NVL((SELECT SUM(w.quantity) FROM waste w WHERE w.fruit_no = fp.fruit_no), 0) AS remain_quantity "
			+ "FROM fruit_product fp WHERE fp.fruit_no = ?";

	String GET_INVENTORY = "SELECT fp.fruit_no, fp.name, " + "fp.quantity "
			+ "- NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
			+ "- NVL((SELECT SUM(w2.quantity) FROM waste w2 WHERE w2.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
			+ "fp.price, " + "m.member_name, " + "fp.storage_date, " + "w.waste_date, " + "wc.waste_category_reason, "
			+ "fp.status " + "FROM FRUIT_PRODUCT fp " + "JOIN MEMBER m ON fp.member_id = m.member_id "
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
			+ "fp.price, " + "m.member_name, " + "fp.storage_date, " + "w.waste_date, " + "wc.waste_category_reason, "
			+ "fp.status " + "FROM FRUIT_PRODUCT fp " + "JOIN MEMBER m ON fp.member_id = m.member_id "
			+ "LEFT JOIN WASTE w ON fp.fruit_no = w.fruit_no "
			+ "LEFT JOIN WASTE_CATEGORY wc ON w.waste_category_no = wc.waste_category_no "
			+ "WHERE fp.status IN ('정상', '폐기') " + "AND m.member_name LIKE '%' || ? || '%'";

	String GET_WASTED_PRODUCTS = "SELECT fp.fruit_no, fp.name, "
			+ "fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
			+ "- NVL((SELECT SUM(w2.quantity) FROM waste w2 WHERE w2.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
			+ "fp.price, " + "m.member_name, " + "fp.storage_date, " + "w.waste_date, " + "wc.waste_category_reason, "
			+ "fp.status " + "FROM FRUIT_PRODUCT fp " + "JOIN MEMBER m ON fp.member_id = m.member_id "
			+ "LEFT JOIN WASTE w ON fp.fruit_no = w.fruit_no "
			+ "LEFT JOIN WASTE_CATEGORY wc ON w.waste_category_no = wc.waste_category_no " + "WHERE fp.status = '폐기'";

	String GET_NORMAL_PRODUCTS = "SELECT fp.fruit_no, fp.name, fp.quantity - NVL((SELECT SUM(s.quantity) FROM sales s WHERE s.fruit_no = fp.fruit_no), 0) "
			+ "- NVL((SELECT SUM(w2.quantity) FROM waste w2 WHERE w2.fruit_no = fp.fruit_no), 0) AS remain_quantity, "
			+ "fp.price, m.member_name, fp.storage_date, w.waste_date, wc.waste_category_reason, fp.status "
			+ "FROM fruit_product fp JOIN member m ON fp.member_id = m.member_id LEFT JOIN waste w ON fp.fruit_no = w.fruit_no "
			+ "LEFT JOIN waste_category wc ON w.waste_category_no = wc.waste_category_no WHERE fp.status = '정상'";

	String GET_FRUIT_PRODUCT_DETAIL = "SELECT"
			+ "    fc.fruit_category_no, fc.item_code, fc.kind_name, fc.origin, fc.item_name, fp.name, fp.price, fp.weight, fp.received_date, fp.quantity,"
			+ "    fp.quantity" + "    - NVL((" + "        SELECT SUM(s.quantity)" + "        FROM sales s"
			+ "        WHERE s.fruit_no = fp.fruit_no" + "    ), 0)" + "    - NVL((" + "        SELECT SUM(w.quantity)"
			+ "        FROM waste w" + "        WHERE w.fruit_no = fp.fruit_no" + "    ), 0) AS remain_quantity,"
			+ "    m.member_name," + "    m.name AS member_name_detail," + "    a.name AS admin_name,"
			+ "    fp.storage_date," + "    w.quantity AS waste_quantity," + "    w.waste_req_date,"
			+ "    w.waste_date," + "    w.reason_detail," + "    ww.daily_rainfall," + "    ww.max_temp,"
			+ "    ww.avg_temp," + "    ww.min_temp," + "    wm.name AS waste_member_name,   "
			+ "    wa.name AS waste_admin_name     " + "FROM fruit_product fp" + "JOIN fruit_category fc"
			+ "    ON fc.fruit_category_no = fp.fruit_category_no" + "JOIN member m"
			+ "    ON fp.member_id = m.member_id" + "LEFT JOIN admin a" + "    ON fp.admin_id = a.admin_id"
			+ "LEFT JOIN waste w" + "    ON fp.fruit_no = w.fruit_no" + "LEFT JOIN waste_weather ww"
			+ "    ON w.waste_no = ww.waste_no" + "LEFT JOIN member wm" + "    ON w.member_id = wm.member_id"
			+ "LEFT JOIN admin wa" + "    ON w.admin_id = wa.admin_id  " + "WHERE fp.fruit_no = ?";

	// ============= waste category =============
	String GET_WASTE_CATEGORY = "SELECT waste_category_reason "
			+ "FROM waste_category "
			+ "WHERE waste_category_no = ?";
	
	String GET_WASTE_CATEGORY_LIST = "SELECT waste_category_no, waste_category_reason "
			+ "FROM waste_category";
}