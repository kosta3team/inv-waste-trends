<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>입고요청 목록 조회(조합원)</title>

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
			<h3 class="fw-bold mb-1">입고요청 목록 조회(조합원)</h3>
		</div>


		<!-- 검색 영역 -->
		<div class="input-group justify-content-center align-items-center">

			<!-- 검색 조건 -->
			<!-- 검색 조건 -->
			<label class="form-label mb-0 fw-bold">요청기간</label>
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

		</div><hr>
		<div style="text-align: right";>
			<input type="checkbox">대기만보기
		</div>

		<!-- 재고 목록 나중에 db에서 불러오는 값 넣을겁니다~ -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>

					<tr>
						<th>순번</th>
						<th>입고요청일련번호</th>
						<th>상품명</th>
						<th>입고수량(box)</th>
						<th>단가(1box)</th>
						<th>총판매예상금액</th>
						<th>요청일자</th>
						<th>처리일자</th>
						<th>요청상태</th>		
					</tr>

				</thead>

				<tbody>

					<tr>
						<td>1</td>
						<td>REQ001</td>
						<td>맛있는 딸기</td>
						<td>50</td>
						<td>30,000원</td>
						<td>1,500,000원</td>
						<td>2026-09-21</td>
						<td>2026-09-23</td>
						<td>승인</td>
					</tr>

					<tr>
						<td>2</td>
						<td>REQ002</td>
						<td>복숭아</td>
						<td>30</td>
						<td>20,000원</td>
						<td>600,000원</td>
						<td>2026-09-10</td>
						<td>2026-09-10</td>
						<td>거절</td>
					</tr>

					<tr>
						<td>3</td>
						<td>REQ003</td>
						<td>메론</td>
						<td>25</td>
						<td>10,000원</td>
						<td>250,000원</td>
						<td>2026-09-22</td>
						<td></td>
						<td>대기</td>
					</tr>
				

				</tbody>

			</table>

		</div>

	</main>
	<!-- 페이지네이션 -->
	<div class="pagination-area" >

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