package kr.swdl.model;

public interface Query {

	//SALE 
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
	
	// WASTE
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
