package kr.swdl.query;

public interface FruitProductQuery {
	// 1. 조합원 입고 요청
	public String ADD_FRUIT_PRODUCT = 
			"INSERT INTO fruit_product (fruit_no, name, price, weight, quantity, status, request_date, storage_date, fruit_category_no, member_id)\r\n"
			+ "VALUES ('fd' || LPAD(seq_fruit_no.NEXTVAL, 4, '0'), ?, ?, ?, ? , '입고요청', SYSDATE, ?, ?, ?)";
	
	// 2. 관리자 기본 전체 입고요청목록 조회
	public String GET_FRUIT_PRODUCT_REQUESTS = 
			"SELECT\r\n"
			+ "fp.fruit_no AS 상품번호,\r\n"
			+ "fp.name AS 상품명, \r\n"
			+ "fp.quantity AS 입고수량,\r\n"
			+ "fp.price AS 단가,\r\n"
			+ "fp.quantity * fp.price AS 총판매예상금액,\r\n"
			+ "m.member_name AS 조합원명,\r\n"
			+ "m.name AS 이름,\r\n"
			+ "fp.request_date AS 요청일자,\r\n"
			+ "fp.fruit_product_date AS 처리일자,\r\n"
			+ "fp.status AS 요청상태\r\n"
			+ "FROM fruit_product fp\r\n"
			+ "JOIN member m\r\n"
			+ "ON fp.member_id = m.member_id\r\n"
			+ "ORDER BY fp.request_date DESC";
	
	// 3. 관리자 기간 전체 입고요청목록 조회
	public String GET_FRUIT_PRODUCT_REQUESTS_PERIOD = 
			"SELECT\r\n"
			+ "fp.fruit_no AS 상품번호,\r\n"
			+ "fp.name AS 상품명, \r\n"
			+ "fp.quantity AS 입고수량,\r\n"
			+ "fp.price AS 단가,\r\n"
			+ "fp.quantity * fp.price AS 총판매예상금액,\r\n"
			+ "m.member_name AS 조합원명,\r\n"
			+ "m.name AS 이름,\r\n"
			+ "fp.request_date AS 요청일자,\r\n"
			+ "fp.fruit_product_date AS 처리일자,\r\n"
			+ "fp.status AS 요청상태\r\n"
			+ "FROM fruit_product fp\r\n"
			+ "JOIN member m\r\n"
			+ "ON fp.member_id = m.member_id\r\n"
			+ "WHERE \r\n"
			+ "        fp.request_date >= TO_DATE(?, 'YYYY-MM-DD')\r\n"
			+ "        AND fp.request_date < TO_DATE(?,'YYYY-MM-DD')+1\r\n"
			+ "ORDER BY fp.request_date DESC";
	
	// 4. 조합원 기본 전체 입고요청목록 조회
	public String GET_MY_PENDING_FRUIT_PRODUCTS =
			"SELECT\r\n"
			+ "fruit_no AS 상품번호,\r\n"
			+ "name AS 상품명, \r\n"
			+ "quantity AS 입고수량,\r\n"
			+ "price AS 단가,\r\n"
			+ "quantity * price AS 총판매예상금액,\r\n"
			+ "request_date AS 요청일자,\r\n"
			+ "fruit_product_date AS 처리일자,\r\n"
			+ "status AS 요청상태\r\n"
			+ "FROM fruit_product\r\n"
			+ "WHERE member_id = ?\r\n"
			+ "ORDER BY request_date DESC";
	
	// 5. 조합원 기간 전체 입고요청목록 조회
	public String GET_MY_PENDING_FRUIT_PRODUCTS_PERIOD =
			"SELECT\r\n"
			+ "fruit_no AS 상품번호,\r\n"
			+ "name AS 상품명, \r\n"
			+ "quantity AS 입고수량,\r\n"
			+ "price AS 단가,\r\n"
			+ "quantity * price AS 총판매예상금액,\r\n"
			+ "request_date AS 요청일자,\r\n"
			+ "fruit_product_date AS 처리일자,\r\n"
			+ "status AS 요청상태\r\n"
			+ "FROM fruit_product\r\n"
			+ "WHERE member_id = ?\r\n"
			+ "        AND request_date >= TO_DATE(?, 'YYYY-MM-DD')\r\n"
			+ "        AND request_date < TO_DATE(?,'YYYY-MM-DD')+1\r\n"
			+ "ORDER BY request_date DESC";
	
	// 6. 관리자 기본 입고요청상태만 목록 조회
	public String GET_PENDING_FRUIT_PRODUCTS =
			"SELECT\r\n"
			+ "fp.fruit_no AS 상품번호,\r\n"
			+ "fp.name AS 상품명, \r\n"
			+ "fp.quantity AS 입고수량,\r\n"
			+ "fp.price AS 단가,\r\n"
			+ "fp.quantity * fp.price AS 총판매예상금액,\r\n"
			+ "m.member_name AS 조합원명,\r\n"
			+ "m.name AS 이름,\r\n"
			+ "fp.request_date AS 요청일자,\r\n"
			+ "fp.fruit_product_date AS 처리일자,\r\n"
			+ "fp.status AS 요청상태\r\n"
			+ "FROM fruit_product fp\r\n"
			+ "JOIN member m\r\n"
			+ "ON fp.member_id = m.member_id\r\n"
			+ "WHERE fp.status = '입고요청'\r\n"
			+ "ORDER BY fp.request_date DESC";
	
	// 7. 관리자 기간 입고요청상태만 목록 조회
	public String GET_PENDING_FRUIT_PRODUCTS_PERIOD =
			"SELECT\r\n"
			+ "fp.fruit_no AS 상품번호,\r\n"
			+ "fp.name AS 상품명, \r\n"
			+ "fp.quantity AS 입고수량,\r\n"
			+ "fp.price AS 단가,\r\n"
			+ "fp.quantity * fp.price AS 총판매예상금액,\r\n"
			+ "m.member_name AS 조합원명,\r\n"
			+ "m.name AS 이름,\r\n"
			+ "fp.request_date AS 요청일자,\r\n"
			+ "fp.fruit_product_date AS 처리일자,\r\n"
			+ "fp.status AS 요청상태\r\n"
			+ "FROM fruit_product fp\r\n"
			+ "JOIN member m\r\n"
			+ "ON fp.member_id = m.member_id\r\n"
			+ "WHERE \r\n"
			+ "fp.status = '입고요청'\r\n"
			+ "AND fp.request_date >= TO_DATE(?, 'YYYY-MM-DD')\r\n"
			+ "        AND fp.request_date < TO_DATE(?,'YYYY-MM-DD')+1\r\n"
			+ "ORDER BY fp.request_date DESC";
	
	// 8. 공통 입고 요청한 상품정보 상세조회
	public String GET_FRUIT_PRODUCT =
			"SELECT\r\n"
			+ "fp.request_date AS 요청일자,\r\n"
			+ "        fc.item_code AS 품목코드,\r\n"
			+ "        fc.item_name AS 품목,\r\n"
			+ "        fc.kind_name AS 품종,\r\n"
			+ "        fp.name AS 상품명,\r\n"
			+ "        fc.origin AS 원산지,\r\n"
			+ "        fp.price AS 단가,\r\n"
			+ "        fp.quantity AS 입고수량,\r\n"
			+ "        fp.weight AS 중량,\r\n"
			+ "        fp.quantity * fp.price AS 총판매예상금액,\r\n"
			+ "        m.member_name AS 조합원명,\r\n"
			+ "        m.name AS 이름,\r\n"
			+ "        m.address || ' ' || m.detail_address AS 주소,\r\n"
			+ "        m.phone AS 전화번호,\r\n"
			+ "        m.email AS 이메일,\r\n"
			+ "        a.name AS 입고처리자\r\n"
			+ "FROM fruit_product fp\r\n"
			+ "JOIN fruit_category fc\r\n"
			+ "ON fp.fruit_category_no = fc.fruit_category_no\r\n"
			+ "LEFT JOIN admin a\r\n"
			+ "ON fp.admin_id = a.admin_id\r\n"
			+ "JOIN member m\r\n"
			+ "ON fp.member_id = m.member_id\r\n"
			+ "WHERE fruit_no = ?";
	
	// 9. 관리자 입고 요청을 거절
	public String REJECT_FRUIT_PRODUCT = "UPDATE fruit_product SET status='거절', admin_id = ?, fruit_product_date = SYSDATE WHERE  fruit_no = ?";
	
	// 10. 관리자 입고 요청을 승인
	public String APPROVE_FRUIT_PRODUCT = "UPDATE fruit_product SET status='정상', admin_id = ?, fruit_product_date = SYSDATE WHERE  fruit_no = ?";
}
