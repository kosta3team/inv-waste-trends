<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">


<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/index.css">

</head>

<body>

	<%@ include file="memberHeader.jsp"%>

	<div class="container-fluid p-0 main-container">

		<div class="row g-0">

			<div
				class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

				<h3 class="mb-3 fw-bold">입고요청</h3>

				<div class="table-container rounded">

					<table class="table table-hover table-light inventory-table">

						<thead>
							<tr>
								<th>상품명</th>
								<th>수량(box)</th>
								<th>상태</th>
							</tr>
						</thead>

						<tbody>
						<c:forEach var="product" items="${FruitProductlists}" varStatus="status">
							<tr>
								<td>${product.name}</td>
								<td>${product.quantity}</td>
								<td>${product.status}</td>
							</tr>
						</c:forEach>
							

						</tbody>

					</table>

				</div>

			</div>


			<div
				class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

				<h3 class="mb-3 fw-bold">재고현황</h3>

				<div class="table-container rounded">

					<table class="table table-hover table-light inventory-table">

						<thead>
							<tr>
								<th>상품명</th>
								<th>수량(box)</th>
							</tr>
						</thead>

						<tbody>
						<c:forEach var="Inventory" items="${InventoryLists}" varStatus="status">
							<tr>
								<td>${Inventory.productName}</td>
								<td>${Inventory.remainQuantity}</td>
							</tr>
						</c:forEach>
							
						</tbody>

					</table>

				</div>

			</div>

		</div>


		<div class="row g-0" style="height: 50%;">

			<div
				class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

				<h3 class="mb-3 fw-bold">판매현황</h3>

				<div class="table-container rounded">

					<table class="table table-hover table-light inventory-table">

						<thead>
							<tr>
								<th>상품명</th>
								<th>수량(box)</th>
								<th>단가</th>
								<th>금액</th>
							</tr>
						</thead>

						<tbody>
						<c:forEach var="Sale" items="${SaleLists}" varStatus="status">
							<tr>
								<td>${Sale.productName}</td>
								<td>${Sale.quantity}</td>
								<td><fmt:formatNumber value="${Sale.price}" pattern="#,###원"/></td>
							<td><fmt:formatNumber value="${Sale.totalPrice}" pattern="#,###원"/></td>
							</tr>
						</c:forEach>
							
						</tbody>

					</table>

				</div>

			</div>

			<div
				class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

				<h3 class="mb-3 fw-bold">폐기 요청 현황</h3>

				<div class="table-container rounded">

					<table class="table table-hover table-light inventory-table">

						<thead>
							<tr>
								<th>상품명</th>
								<th>수량(box)</th>
								<th>폐기사유</th>
								<th>상태</th>
							</tr>
						</thead>

						<tbody>
						<c:forEach var="Waste" items="${WasteLists}" varStatus="status">
							<tr>
								<td>${Waste.wasteNo}</td>
								<td>${Waste.wasteReqDate}</td>
								<td>${Waste.wasteDate}</td>
								<td><span class="badge bg-danger"> 폐기요청 </span></td>
							</tr>
						</c:forEach>
							
						</tbody>

					</table>

				</div>

			</div>

		</div>

	</div>

	<%@ include file="footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
		
	</script>

	<script src="https://cdn.jsdelivr.net/npm/chart.js">
		
	</script>

</body>

</html>