package kr.swdl.query;

public interface FruitProductQuery {
	// 1. 조합원 입고 요청
	public String ADD_FRUIT_PRODUCT = 
			"INSERT INTO fruit_product (fruit_no, name, price, weight, quantity, status, request_date, storage_date, fruit_category_no, member_id) "
			+ "VALUES ('fd' || LPAD(seq_fruit_no.NEXTVAL, 4, '0'), ?, ?, ?, ? , '입고요청', SYSDATE, ?, ?, ?)";
	
	// 2. 관리자 기본 전체 입고요청목록 조회
	public String GET_FRUIT_PRODUCT_REQUESTS = 
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
	public String GET_FRUIT_PRODUCT_REQUESTS_PERIOD = 
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
	
	// 4. 조합원 기본 전체 입고요청목록 조회
	public String GET_MY_PENDING_FRUIT_PRODUCTS =
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
	
	// 5. 조합원 기간 전체 입고요청목록 조회
	public String GET_MY_PENDING_FRUIT_PRODUCTS_PERIOD =
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
	
	// 6. 관리자 기본 입고요청상태만 목록 조회
	public String GET_PENDING_FRUIT_PRODUCTS =
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
	
	// 7. 관리자 기간 입고요청상태만 목록 조회
	public String GET_PENDING_FRUIT_PRODUCTS_PERIOD =
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
	
	// 8. 공통 입고 요청한 상품정보 상세조회
	public String GET_FRUIT_PRODUCT =
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
			+ "FROM fruit_product fp "
			+ "JOIN fruit_category fc "
			+ "ON fp.fruit_category_no = fc.fruit_category_no "
			+ "LEFT JOIN admin a "
			+ "ON fp.admin_id = a.admin_id "
			+ "JOIN member m "
			+ "ON fp.member_id = m.member_id "
			+ "WHERE fruit_no = ?";
	
	// 9. 관리자 입고 요청을 거절
	public String REJECT_FRUIT_PRODUCT = "UPDATE fruit_product SET status='거절', admin_id = ?, fruit_product_date = SYSDATE WHERE  fruit_no = ?";
	
	// 10. 관리자 입고 요청을 승인
	public String APPROVE_FRUIT_PRODUCT = "UPDATE fruit_product SET status='정상', admin_id = ?, fruit_product_date = SYSDATE WHERE  fruit_no = ?";
}
