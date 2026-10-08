<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>과일상품정보 목록 조회</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="css/inventoryList.css">

</head>

<body>
	<input type="hidden" id="contextPath"
		value="${pageContext.request.contextPath}">

	<%@ include file="memberHeader.jsp"%>

	<main class="inventory-page">

		<!-- 제목 -->

		<div class="mb-4">
			<h3 class="fw-bold mb-1">과일상품정보 목록 조회</h3>
		</div>

		<!-- 검색 영역 -->

		<div class="d-flex justify-content-center align-items-center">
			<div class="search-area">
				<div class="search-box">
					<form action="${pageContext.request.contextPath}/controller"
						method="get">
						<input type="hidden" name="cmd" value="memberInventoryList">
						<div class="input-group">

							<!-- 검색 조건 -->
							<select name="searchType" class="form-select"
								style="max-width: 130px;">
								<option value="product">상품명</option>
							</select>

							<!-- 검색어 -->
							<input type="text" name="keyword" class="form-control"
								placeholder="검색어 입력" value="${param.keyword}">

							<!-- 검색 버튼 -->
							<button type="submit" class="btn btn-dark">
								<i class="bi bi-search"></i>
							</button>
						</div>
					</form>
				</div>

				<!-- 재고 상태 -->

				<div class="stock-status fw-bold">
					<label class="text-danger"> <input type="checkbox"
						class="form-check-input me-1 stockType" name="stockType"
						value="waste" id="disposed"
						<c:if test="${param.stockType eq 'waste'}">checked</c:if>>
						폐기재고
					</label> <label class="text-primary"> <input type="checkbox"
						class="form-check-input me-1 stockType" name="stockType"
						value="normal" id="selling"
						<c:if test="${param.stockType eq 'normal'}">checked</c:if>>
						판매중인재고
					</label>
				</div>
			</div>
		</div>
		
		<div class="inventory-table-area">
			<table class="table table-hover inventory-table">
				<thead>
					<tr>
						<th>순번</th>
						<th>과일상품일련번호</th>
						<th>상품명</th>
						<th>재고수량(box)</th>
						<th>단가(1box)</th>
						<th>보관일자</th>
						<th>폐기일자</th>
						<th>폐기사유</th>
						<th>상태</th>
					</tr>
				</thead>

				<tbody>

					<c:if test="${empty inventoryList}">
						<tr>
							<td colspan="9" class="text-center">조회된 재고가 없습니다.</td>
						</tr>

					</c:if>
					<c:forEach var="inventory" items="${inventoryList}"
						varStatus="status">
						<tr style="cursor: pointer;"
							onclick="showInventoryDetail('${inventory.fruitNo}')">
							<td>${status.count}</td>
							<td>${inventory.fruitNo}</td>
							<td>${inventory.productName}</td>
							<td>${inventory.remainQuantity}</td>
							<td><fmt:formatNumber value="${inventory.price}"
									pattern="#,###" /> 원</td>

							<td>${fn:substring(inventory.storageDate, 0, 10)}</td>
							<td><c:choose>
									<c:when test="${empty inventory.wasteDate}">-</c:when>
									<c:otherwise>${fn:substring(inventory.wasteDate, 0, 10)}</c:otherwise>
								</c:choose></td>

							<td><c:choose>
									<c:when test="${empty inventory.wasteCategoryReason}">-</c:when>
									<c:otherwise>${inventory.wasteCategoryReason}</c:otherwise>
								</c:choose></td>

							<td><c:choose>
									<c:when test="${inventory.status eq '폐기'}">
										<span class="badge bg-danger">${inventory.status}</span>
									</c:when>
									<c:otherwise>
										<span class="badge bg-success">${inventory.status}</span>
									</c:otherwise>
								</c:choose></td>

						</tr>
					</c:forEach>
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

	<%@ include file="footer.jsp"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/modal.js"></script>
	<script src="${pageContext.request.contextPath}/js/memberInventory.js"></script>

</body>

</html>