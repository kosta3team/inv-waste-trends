<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>과일상품정보 폐기 요청 조회</title>

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
			<h3 class="fw-bold mb-1">과일상품정보 폐기 요청 조회</h3>
		</div>


		<!-- 검색 영역 -->
		<div class="d-flex justify-content-center align-items-center">

		
						<!-- 검색 조건 -->
						<select class="form-select" style="max-width: 130px;">
							<option value="product">상품명</option>
							<option value="coop">협동조합원명</option>
						</select>

						<!-- 검색어 -->
						<input type="text" class="form-control" placeholder="검색어 입력" style="max-width: 500px";>

						<!-- 검색 버튼 -->
						<button class="btn btn-dark">
							<i class="bi bi-search"></i>
						</button>

		</div><hr>
			<!-- 재고 목록 나중에 db에서 불러오는 값 넣을겁니다~ -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>

					<tr>
						<th>순번</th>
						<th>과일상품정보일련번호</th>
						<th>상품명</th>
						<th>재고수량(box)</th>
						<th>단가(1box)</th>
						<th>보관일자</th>
						<th>협동조합명</th>
						<th>이름</th>
						<th>폐기일자</th>
						<th>폐기사유</th>
						<th>폐기요청일자</th>
						<th>폐기상태</th>		
					</tr>

				</thead>

				<tbody>

					<tr>
						<td>1</td>
						<td>ST17803</td>
						<td>맛있는 딸기</td>
						<td>7</td>
						<td>12,000원</td>
						<td>2026-09-24</td>
						<td>아주농장</td>
						<td>김미원</td>
						<td>-</td>
						<td>제품하자발견</td>
						<td>2026-09-22</td>
						<td>폐기대기</td>
					</tr>

					<tr>
						<td>2</td>
						<td>p2123</td>
						<td>복숭아</td>
						<td>3</td>
						<td>14,000원</td>
						<td>2026-09-27</td>
						<td>황금농원</td>
						<td>박재현</td>
						<td>2026-09-22</td>
						<td>관리부재</td>
						<td>-</td>
						<td>폐기완료</td>
					</tr>
					
					<tr>
						<td>3</td>
						<td>B234</td>
						<td>바나나</td>
						<td>7</td>
						<td>12,000원</td>
						<td>2026-09-29</td>
						<td>진농원</td>
						<td>허진수</td>
						<td>2026-09-22</td>
						<td>제품하자</td>
						<td>2026-09-21</td>
						<td>폐기완료</td>
					</tr>
				

				</tbody>

			</table>

		</div>

	</main>
	<!-- 페이지네이션 -->
		<div class="pagination-area">

			<nav>
				<ul class="pagination justify-content-center">

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
<%@ include file="footer.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">



</body>
</html>