<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 목록 조회</title>

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
			<h3 class="fw-bold mb-1">재고 목록 조회</h3>
		</div>


		<!-- 검색 영역 -->
		<div class="d-flex justify-content-center align-items-center">

			<div class="search-area">

				<div class="search-box">

					<div class="input-group">

						<!-- 검색 조건 -->
						<select class="form-select" style="max-width: 130px;">
							<option value="product">상품명</option>
						</select>

						<!-- 검색어 -->
						<input type="text" class="form-control" placeholder="검색어 입력">

						<!-- 검색 버튼 -->
						<button class="btn btn-dark">
							<i class="bi bi-search"></i>
						</button>

					</div>

				</div>


				<!-- 재고 상태 -->
				<div class="stock-status fw-bold">

					<label class="text-danger"> <input type="checkbox"
						class="form-check-input me-1" id="disposed"> 폐기재고
					</label> <label class="text-primary"> <input type="checkbox"
						class="form-check-input me-1" id="selling"> 판매중인재고
					</label>

				</div>

			</div>

		</div>


		<!-- 재고 목록 나중에 db에서 불러오는 값 넣을겁니다~ -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>

					<tr>
						<th>순번</th>
						<th>재고일련번호</th>
						<th>상품명</th>
						<th>재고수량(box)</th>
						<th>단가(1box)</th>
						<th>보관기간</th>
						<th>폐기일자</th>
						<th>폐기사유</th>
					</tr>

				</thead>

				<tbody>

					<tr>
						<td>1</td>
						<td>SH260923001</td>
						<td>설향딸기</td>
						<td>25</td>
						<td>32,000원</td>
						<td>2026-09-30</td>
						<td>-</td>
						<td>-</td>
					</tr>


					<tr>
						<td>2</td>
						<td>AP260923003</td>
						<td>사과</td>
						<td>30</td>
						<td>24,000원</td>
						<td>2026-10-15</td>
						<td>-</td>
						<td>-</td>
					</tr>



					<tr>
						<td>3</td>
						<td>PE260923006</td>
						<td>복숭아</td>
						<td>16</td>
						<td>30,000원</td>
						<td>2026-10-03</td>
						<td>-</td>
						<td>-</td>
					</tr>

					<tr>
						<td>4</td>
						<td>YD260923010</td>
						<td>영동포도</td>
						<td>6</td>
						<td>28,000원</td>
						<td>2026-09-20</td>
						<td>2026-09-21</td>
						<td>미판매</td>
					</tr>

				</tbody>

			</table>

		</div>

		<!-- 페이지네이션 -->
		<div class="pagination-area">

			<nav>
				<ul class="pagination">

					<li class="page-item disabled"><a class="page-link" href="#">
							<i class="bi bi-chevron-left"></i>
					</a></li>

					<li class="page-item active"><a class="page-link" href="#">1</a>
					</li>

					<li class="page-item"><a class="page-link" href="#">2</a></li>

					<li class="page-item"><a class="page-link" href="#">3</a></li>

					<li class="page-item"><a class="page-link" href="#">4</a></li>

					<li class="page-item"><a class="page-link" href="#">5</a></li>

					<li class="page-item"><a class="page-link" href="#"> <i
							class="bi bi-chevron-right"></i>
					</a></li>
				</ul>
			</nav>
		</div>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>