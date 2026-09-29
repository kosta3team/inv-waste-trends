<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>재고 폐기 요청</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="css/inventoryList.css">



</head>
<body>
<%@ include file="memberHeader.jsp"%>
<main class="inventory-page">

		<!-- 제목 -->
		<div class="mb-4">
			<h3 class="fw-bold mb-1">재고 폐기 요청</h3>
		</div>


		<!-- 검색 영역 -->
		<div class="d-flex justify-content-center align-items-center">

		
						<!-- 검색 조건 -->
						<select class="form-select" style="max-width: 130px;">
							<option value="product">상품명</option>
							<option value="coop">협동조합원명</option>
						</select>

						<!-- 검색어 -->
						<input type="text" class="form-control" placeholder="검색어 입력">

						<!-- 검색 버튼 -->
						<button class="btn btn-dark">
							<i class="bi bi-search"></i>
						</button>

		</div><hr>
		<input type="checkbox">판매중인재고<input type="checkbox">폐기요청된재고<input type="checkbox">폐기된재고


		<!-- 재고 목록 나중에 db에서 불러오는 값 넣을겁니다~ -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>

					<tr>
						<th>순번</th>
						<th>재고일련번호</th>
						<th>상품명</th>
						<th>재고수량</th>
						<th>단가</th>
						<th>보관일자</th>
						<th>폐기일자</th>
						<th>폐기사유</th>
						<th>폐기요청일자</th>
						<th>폐기상태</th>		
					</tr>

				</thead>

				<tbody>

					<tr>
						<td>1</td>
						<td>ST17802</td>
						<td>설향딸기</td>
						<td>16</td>
						<td>13,000원</td>
						<td>2026.09.22</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td><button type="button" class="btn btn-sm btn-danger" onclick="openDisposeModal('1042')">폐기요청</button></td>
					</tr>

					<tr>
						<td>2</td>
						<td>ST17803</td>
						<td>맛있는 딸기</td>
						<td>7</td>
						<td>12,000원</td>
						<td>2026.09.24</td>
						<td>-</td>
						<td>제품하자발견</td>
						<td>2026.09.22</td>
						<td>폐기대기</td>
					</tr>

					<tr>
						<td>3</td>
						<td>p2123</td>
						<td>복숭아</td>
						<td>3</td>
						<td>14,000원</td>
						<td>2026.09.27</td>
						<td>2026.09.22</td>
						<td>관리부재</td>
						<td>-</td>
						<td>폐기완료</td>
					</tr>
					
					<tr>
						<td>4</td>
						<td>B234</td>
						<td>바나나</td>
						<td>7</td>
						<td>12,000원</td>
						<td>2026.09.29</td>
						<td>2026.09.22</td>
						<td>제품하자</td>
						<td>2026.09.21</td>
						<td>폐기완료</td>
					</tr>
				

				</tbody>

			</table>

		</div>

	</main>

<%@ include file="disposalRequestModal.jsp" %>
<%@ include file="footer.jsp"%>

<script>
function openDisposeModal(inventoryId) {
	// 1. 넘겨받은 ID 값을 모달 안의 hidden input에 삽입 (서버 전송용)
    document.getElementById('targetInventoryId').value = inventoryId;
    
    // 2. 모달 HTML 요소를 가져와서 부트스트랩 모달 객체로 변환
    var modalElement = document.getElementById('disposalRequestModal');
    var modalInstance = bootstrap.Modal.getOrCreateInstance(modalElement);
    
    modalInstance.show();
}
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>