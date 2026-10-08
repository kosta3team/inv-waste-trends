<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>그린 매니저</title>

<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/images/favicon.png">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">


</head>

<body>

	<header>

		<nav class="navbar navbar-expand-lg bg-white border-bottom px-4">

			<div class="container-fluid">

				<a class="navbar-brand me-4 py-0"
					href="${pageContext.request.contextPath}/controller?cmd=memberMain">
					<img src="${pageContext.request.contextPath}/images/logo.png"
					alt="그린매니저 ERP" class="navbar-logo">

				</a>


				<ul class="navbar-nav flex-grow-1 mb-2 mb-lg-0">

					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 입고확인 </a>

						<ul class="dropdown-menu start-50 translate-middle-x text-center">
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/view/memberInventoryRequestList.jsp">
									입고 내역 </a></li>
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/view/fruitproduct/addFruitProduct.jsp">
									입고 요청 </a></li>
						</ul></li>

					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 재고확인 </a>

						<ul class="dropdown-menu start-50 translate-middle-x text-center">
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/controller?cmd=memberInventoryList">
									재고 확인 </a></li>
						</ul></li>

					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 판매기록확인 </a>

						<ul class="dropdown-menu start-50 translate-middle-x text-center">
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/controller?cmd=menuMemberSaleInventoryList">
									판매 기록 </a></li>
						</ul></li>


				</ul>

				<div
					class="header-right d-flex flex-column align-items-center gap-1">

					<div class="dropdown">

						<button class="dropdown-toggle border-0 bg-transparent">

							<i class="bi bi-person-circle me-1"></i>
							${sessionScope.loginName}님

						</button>

						<a href="${pageContext.request.contextPath}/controller?cmd=logout"
							class="text-danger text-decoration-none"> <i
							class="bi bi-box-arrow-right me-1"></i> 로그아웃
						</a>

					</div>


					<div class="current-date">
						현재날짜 <span id="currentDate"></span>
					</div>

				</div>

			</div>

		</nav>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/header.js"></script>

	</header>

</body>
</html>