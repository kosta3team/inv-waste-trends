<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<form action="${pageContext.request.contextPath}/controller"
				method="get" class="d-flex align-items-center gap-2">

				<input type="hidden" name="cmd" value="memberSaleInventoryList"> <label
					class="form-label mb-0 fw-bold"> 판매기간 : </label>

				<!-- 시작일 -->
				<div class="d-flex align-items-center gap-1">

					<select class="form-select" id="startYear" name="startYear"
						style="width: 100px;">

						<option value="2026" selected>2026</option>
						<option value="2025">2025</option>

					</select> <select class="form-select" id="startMonth" name="startMonth">

						<option value="08" selected>08</option>
						<option value="07">07</option>

					</select> <select class="form-select" id="startDate" name="startDate">

						<option value="10" selected>10</option>
						<option value="9">9</option>

					</select>

				</div>

				<span>~</span>

				<!-- 종료일 -->
				<div class="d-flex align-items-center gap-1">

					<select class="form-select" id="endYear" name="endYear"
						style="width: 100px;">

						<option value="2026" selected>2026</option>
						<option value="2025">2025</option>

					</select> <select class="form-select" id="endMonth" name="endMonth">

						<option value="08" selected>08</option>
						<option value="09">09</option>

					</select> <select class="form-select" id="endDate" name="endDate">

						<option value="15" selected>15</option>
						<option value="14">14</option>

					</select>

				</div>

				<!-- 검색 버튼 -->
				<button type="submit" class="btn btn-dark">
					<i class="bi bi-search"></i>
				</button>

			</form>

		</div>


		<!-- 재고 목록 나중에 db에서 불러오는 값 넣을겁니다~ -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>

					<tr>
						<th>순번</th>
						<th>상품명</th>
						<th>판매수량(box)</th>
						<th>단가(1box)</th>
						<th>판매금액</th>
						<th>판매일자</th>
					</tr>

				</thead>

				<tbody>

					<c:forEach var="sale" items="${saleList}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${sale.productName}</td>
							<td>${sale.quantity}</td>
							<td><fmt:formatNumber value="${sale.price}" pattern="#,###원"/></td>
							<td><fmt:formatNumber value="${sale.totalPrice}" pattern="#,###원"/></td>
							<td>${sale.saleDate}</td>

						</tr>
					</c:forEach>

					
					
					<tr>
						<td colspan="4"></td>
						<td>총판매금액 : <fmt:formatNumber value="${sumTotalPrice}" pattern="#,###원"/></td>
						<td></td>

					</tr>


				</tbody>

			</table>

		</div>

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
	</main>
	<%@ include file="inventoryDetailModal.jsp" %>
	<%@ include file="footer.jsp"%>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/modal.js"></script>

</body>
</html>