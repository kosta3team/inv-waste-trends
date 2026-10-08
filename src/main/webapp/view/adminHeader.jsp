<%
    if (session.getAttribute("adminId") == null) {
        response.sendRedirect(request.getContextPath() + "/controller?cmd=loginUI");
        return;
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>그린 매니저</title>
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath }/images/favicon.png">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-white border-bottom px-4">

			<div class="container-fluid">
				<a class="navbar-brand me-4 py-0"
					href="${pageContext.request.contextPath }/controller?cmd=wasteTrends">
					<img src="${pageContext.request.contextPath }/images/logo.png"
					alt="그린매니저 ERP" class="navbar-logo">
				</a>

				<ul class="navbar-nav flex-grow-1 mb-2 mb-lg-0">
					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle"
						href="${pageContext.request.contextPath }/controller?cmd=wasteTrends"
						role="button">폐기량 동향</a></li>
					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 요청 조회 </a>
						<ul class="dropdown-menu start-50 translate-middle-x text-center">
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/controller?cmd=signupListUI">
									회원가입 요청 조회 </a></li>
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/controller?cmd=getAdminFruitProductRequestLists">
									입고 요청 조회 </a></li>
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/controller?cmd=adminWasteRequestList">
									폐기 요청 조회 </a></li>
						</ul></li>	
					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle"
						href="${pageContext.request.contextPath}/controller?cmd=adminInventoryRequestListUI"
						role="button">
							입고확인 </a></li>
					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle"
						href="${pageContext.request.contextPath}/controller?cmd=inventoryListUI"
						role="button">과일상품 목록 조회</a></li>

					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle"
						href="${pageContext.request.contextPath}/controller?cmd=menuSaleInventoryList"
						role="button">판매 조회</a></li>
				</ul>
				<div
					class="header-right d-flex flex-column align-items-center gap-1">

					<div class="d-flex align-items-center gap-3">

						<span> <i class="bi bi-person-circle me-1"></i>
							${sessionScope.loginName} 관리자
						</span> <a
							href="${pageContext.request.contextPath}/controller?cmd=logout"
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