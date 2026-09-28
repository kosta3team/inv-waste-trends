<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>최강 ERP</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="css/header.css">

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-white border-bottom px-4">

			<div class="container-fluid">
				<a class="navbar-brand fw-bold me-4" href="index.jsp"> <i
					class="bi bi-house-door-fill me-2"></i> 최강 ERP
				</a>
				<ul class="navbar-nav flex-grow-1 mb-2 mb-lg-0">

					<li class="nav-item flex-fill text-center"><a class="nav-link"
						href="controller?cmd=wasteTrends"> 폐기량 동향 </a></li>

					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						onclick="return false"> 재고 목록 조회 </a>
						<ul class="dropdown-menu">

							<li><a class="dropdown-item"
								href="controller?cmd=inventoryList">재고 조회</a></li>

						</ul></li>
					<li class="nav-item dropdown flex-fill text-center"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						onclick="return false"> 회원 가입 요청 조회 </a>

						<ul class="dropdown-menu">

							<li><a class="dropdown-item" href="#"> </a></li>

						</ul></li>



				</ul>
				<div
					class="header-right d-flex flex-column align-items-center gap-1">
					<div class="dropdown">
						<button class="dropdown-toggle border-0 bg-transparent">
							<i class="bi bi-person-circle me-1"></i> 박관리자
						</button>

						<ul class="dropdown-menu dropdown-menu-end">
							<li><a class="dropdown-item" href="#">프로필</a></li>
							<li><a class="dropdown-item" href="#">설정</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">로그아웃</a></li>
						</ul>
					</div>

					<div class="current-date">
						현재날짜 <span id="currentDate">2026-09-23</span>
					</div>
				</div>
			</div>

		</nav>
	</header>
</body>
</html>
