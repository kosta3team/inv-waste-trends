<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>판매 목록 조회</title>

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
			<h3 class="fw-bold mb-1">판매 기록 조회</h3>
		</div>


		<!-- 검색 영역 -->
		<div class="input-group justify-content-center align-items-center">

			<!-- 검색 조건 -->
			<label class="form-label mb-0 fw-bold">기간 : </label>
			<div class="d-flex align-items-center">
				<select class="form-select" id="start" name="startYear" style="width: 100px";>
					<option >2026</option>
					<option >2025</option>
				</select> 
				<select class="form-select"
					id="month" name="startmonth">
					<option  selected>09</option>
					<option >08</option>
				</select>
				<select class="form-select"
					id="date" name="startdate">
					<option  selected>22</option>
					<option >23</option>
				</select>
				~
				<select class="form-select" id="end" name="endYear" style="width: 100px";>
					<option >2026</option>
					<option >2025</option>
				</select> 
				<select class="form-select"
					id="month" name="endmonth">
					<option selected>10</option>
					<option >09</option>
				</select>
				<select class="form-select"
					id="date" name="enddate">
					<option  selected>15</option>
					<option >14</option>
				</select>
			</div>
			<!-- 검색 버튼 -->
			<button class="btn btn-dark">
				<i class="bi bi-search"></i>
			</button>

		</div>



		<!-- 재고 목록 나중에 db에서 불러오는 값 넣을겁니다~ -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>

					<tr>
						<th>순번</th>
						<th>과일상품일련번호</th>
						<th>판매일련번호</th>
						<th>협동조합원명</th>
						<th>상품명</th>
						<th>판매수량(box)</th>
						<th>단가(1box)</th>
						<th>판매금액</th>
						<th>판매일자</th>
					</tr>

				</thead>

				<tbody>

					<tr>
						<td>1</td>
						<td>AP09123</td>
						<td>20260922-002</td>
						<td>진농원</td>
						<td>아오리사과</td>
						<td>10</td>
						<td>16,000원</td>
						<td>160,000원</td>
						<td>2026-09-22</td>
					</tr>


					<tr>
						<td>2</td>
						<td>WA09472</td>
						<td>20260922-001</td>
						<td>진과수원</td>
						<td>꿀수박</td>
						<td>6</td>
						<td>32,000원</td>
						<td>192,000원</td>
						<td>2026-10-15</td>
					</tr>



					<tr>
						<td>3</td>
						<td>PE07423</td>
						<td>20260921-001</td>
						<td>종현농원</td>
						<td>복숭아</td>
						<td>16</td>
						<td>16,000원</td>
						<td>265,000원</td>
						<td>2026-10-03</td>
					</tr>
					
					<tr>
						<td><td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>총판매금액 : 2,226,000원</td>
						<td></td>
					
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
	<%@ include file="inventoryDetailModal.jsp" %>
	<%@ include file="footer.jsp"%>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/modal.js"></script>

</body>
</html>