<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>입고요청 목록 조회(관리자)</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/inventoryList.css">

</head>
<body>
	<%@ include file="../adminHeader.jsp"%>
	<main class="inventory-page">

		<!-- 제목 -->
		<div class="mb-4">
			<h3 class="fw-bold mb-1">입고요청 목록 조회(관리자)</h3>
		</div>

		<!-- form 영역 시작 -->
		<form action="${pageContext.request.contextPath }/controller" method="get">
			<input type="hidden" name="cmd" value="getAdminFruitProductRequestLists">
			
			<!-- 검색 영역 -->
			<div class="input-group justify-content-center align-items-center">
				<label class="form-label mb-0 fw-bold">요청기간</label>
				<div class="d-flex align-items-center">
					<input type="date" class="form-control" name="startDate" value="${param.startDate }">
					<span class="mx-2">~</span>
					<input type="date" class="form-control" name="endDate" value="${param.endDate }">
				</div>
				
				
				<!-- 검색버튼 -->
				<button type="submit" class="btn btn-dark ms-2">
					<i class="bi bi-search"></i>
				</button>
				
				<!-- 전체보기 버튼 -->
				<a href="${pageContext.request.contextPath }/controller?cmd=getAdminFruitProductRequestLists" class="btn btn-outline-secondary ms-2">전체보기</a>
			</div>	
			<hr>
			
			<div style="text-align: right">
				<!-- 체크박스에 이름표 부착 및 상태유지 -->
				<label for="onlyPending">입고요청만 보기</label>
				<input type="checkbox" name="onlyPending" value="Y" id="onlyPending" ${param.onlyPending == 'Y' ? 'checked' : '' } onchange="this.form.submit()">
			</div>
			
		</form>
		<!-- form 영역 끝 -->

		<!-- 입고요청 목록 시작 -->
		<div class="inventory-table-area mt-3">
			<table class="table table-hover inventory-table">
				<thead>
					<tr>
						<th>순번</th>					
						<th>상품명</th>
						<th>입고수량(box)</th>
						<th>단가(1box)</th>
						<th>총판매예상금액</th>		
						<th>조합원명</th>
						<th>이름</th>				
						<th>요청일자</th>
						<th>처리일자</th>
						<th>요청상태</th>	
					</tr>					
				</thead>
				<tbody>
					<!-- JSTL 반복문 -->
					<c:choose>
						<c:when test="${empty requestList }">
							<!-- 실데이터(컬럼수)가 현재 td로 11개라서 colspan11 -->
							<tr>
								<td colspan="10" class="text-center py-4">조회된 입고 요청 내역이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="req" items="${requestList}" varStatus="status">
								<tr style="cursor: pointer;" onclick="openDetailModalAdmin('${req.fruitNo}')">
									<!-- 페이지 이동시 매 페이지의 순번이 1~15가 아니라, 누적으로 1~15, 16~30으로 -->
									<td>${(currentPage - 1) * 15 + status.count }</td>									
									<td>${req.name }</td>
									<td>${req.quantity }</td>
									<td>${req.price }원</td>
									<td>${req.totalPrice }원</td>	
									<td>${req.member.memberName }</td>
									<td>${req.member.name }</td>								
									<td>${req.requestDate }</td>
									<td>${req.fruitProductDate }</td>
									<td>${req.status }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!-- 입고요청 목록 끝 -->
		

	<!-- 페이지네이션 -->
	<div class="pagination-area mt-4">
		<nav>
			<ul class="pagination justify-content-center">
				<!-- 이전 버튼 -->
 				<li class="page-item ${currentPage == 1 ? 'disabled' : '' }">
 					<a class="page-link" href="${pageContext.request.contextPath }/controller?cmd=getAdminFruitProductRequestLists&page=${currentPage - 1}&startDate=${param.startDate}&endDate=${param.endDate}&onlyPending=${param.onlyPending}">
						<i class="bi bi-chevron-left"></i>
					</a>
				</li>

				<!-- 페이지 번호 동적 생성 -->
				<c:forEach begin="1" end="${totalPages == 0 ? 1 : totalPages }" var="i">
					<li class="page-item ${currentPage == i ? 'active' : '' }">
						<a class="page-link" href="${pageContext.request.contextPath }/controller?cmd=getAdminFruitProductRequestLists&page=${i}&startDate=${param.startDate}&endDate=${param.endDate}&onlyPending=${param.onlyPending}">
							${i }
						</a>
					</li>
				</c:forEach>
				

				<!-- 다음 버튼 -->
				<li class="page-item ${currentPage == totalPages || totalPages == 0 ? 'disabled' : ''}">
					<a class="page-link" href="${pageContext.request.contextPath }/controller?cmd=getAdminFruitProductRequestLists&page=${currentPage + 1}&startDate=${param.startDate}&endDate=${param.endDate}&onlyPending=${param.onlyPending}">
					<i class="bi bi-chevron-right"></i>
				</a></li>
			</ul>
		</nav>
	</div>
	<!-- 페이지네이션 끝 -->
	
	<!-- 모달영역 시작-->
	<div class="modal fade" id="requestDetailModal" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-scrollable">
			<div class="modal-content" id="modalContentArea">
				<div class="modal-body text-center p-5">
					<div class="spinner-border text-primary" role="status">
						<span class="visually-hidden">Loading...</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달영역 끝-->


	<%@ include file="../footer.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		const contextPath = '${pageContext.request.contextPath}';
	</script>
	<script src="${pageContext.request.contextPath }/js/requestDetailModal.js"></script>

</body>
</html>