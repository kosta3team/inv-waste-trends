<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>회원가입 요청 목록 조회</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet" href="css/inventoryList.css">

</head>

<body>

	<%@ include file="adminHeader.jsp"%>

	<main class="inventory-page">

		<!-- 제목 -->
		<div class="mb-4">
			<h3 class="fw-bold mb-1">회원가입 요청 목록 조회</h3>
		</div>


		<!-- 회원가입 요청 목록 -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>
					<tr>
						<th>요청 일련번호</th>
						<th>요청일자</th>
						<th>조합원명</th>
						<th>이름</th>
						<th>요청상태</th>
					</tr>
				</thead>

				<tbody>

					<!-- 회원가입 요청 목록 출력 -->
					<c:forEach var="member" items="${memberList}" varStatus="status">

						<tr
							onclick="goDetail('${member.memberId}', '${member.isCompany}')"
							style="cursor: pointer;">

							<td>${status.count}</td>
							<td>${member.requestDate}</td>
							<td>${member.memberName}</td>
							<td>${member.name}</td>
							<td>${member.status}</td>
						</tr>

					</c:forEach>


					<!-- 조회 결과가 없는 경우 -->
					<c:if test="${empty memberList}">

						<tr>
							<td colspan="5" class="text-center">회원가입 요청이 없습니다.</td>
						</tr>

					</c:if>

				</tbody>

			</table>

		</div>

	</main>


	<!-- 페이지네이션 -->
	<div class="pagination-area">

		<nav>

			<ul class="pagination justify-content-center">

				<!-- 이전 -->
				<li class="page-item disabled"><a class="page-link" href="#">
						<i class="bi bi-chevron-left"></i>
				</a></li>


				<!-- 1페이지 -->
				<li class="page-item active"><a class="page-link" href="#">1</a>
				</li>


				<!-- 2페이지 -->
				<li class="page-item"><a class="page-link" href="#">2</a></li>


				<!-- 3페이지 -->
				<li class="page-item"><a class="page-link" href="#">3</a></li>


				<!-- 4페이지 -->
				<li class="page-item"><a class="page-link" href="#">4</a></li>


				<!-- 5페이지 -->
				<li class="page-item"><a class="page-link" href="#">5</a></li>


				<!-- 다음 -->
				<li class="page-item"><a class="page-link" href="#"> <i
						class="bi bi-chevron-right"></i>
				</a></li>

			</ul>

		</nav>

	</div>
	<script src="${pageContext.request.contextPath}/js/signup.js"></script>


	<%@ include file="footer.jsp"%>


</body>

</html>
