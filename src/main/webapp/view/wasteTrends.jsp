<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

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
<link rel="stylesheet" href="css/wasteTrends.css">
</head>


<body>
	<%@ include file="adminHeader.jsp"%>

	<div class="d-flex">
		<aside class="sidebar">

			<div class="logo">

				<i class="bi bi-bar-chart-line me-2"></i> <span>폐기량 분석</span>

			</div>


			<ul class="nav flex-column mt-3">

				<li class="nav-item"><a class="nav-link waste-menu" href="#"
					data-type="solar"> <i class="bi bi-calendar3"></i> <span>특정
							양력 월 비교</span>
				</a></li>

				<li class="nav-item"><a class="nav-link waste-menu" href="#"
					data-type="lunar"> <i class="bi bi-calendar-event"></i> <span>특정
							음력 월 비교</span>
				</a></li>

				<li class="nav-item"><a class="nav-link waste-menu" href="#"
					data-type="year"> <i class="bi bi-trash"></i> <span>연중
							폐기량 조회</span>
				</a></li>

				<li class="nav-item"><a class="nav-link waste-menu" href="#"
					data-type="compare"> <i class="bi bi-bar-chart"></i> <span>연도별
							총량 비교</span>
				</a></li>

			</ul>

		</aside>

		<main class="main flex-grow-1 container-fluid p-4">

			<div class="mb-4">

				<h3 class="fw-bold">폐기물 동향</h3>

				<p class="text-muted mb-0">본사 및 가맹점 현황</p>

			</div>


			<!-- 카드 -->
			<div class="row g-4 mb-4">


				<div class="col-12 col-sm-6 col-xl-3">

					<div class="card dashboard-card shadow-sm">

						<div class="card-body">

							<div class="d-flex justify-content-between">

								<div>

									<h6 class="text-muted">작년 총 폐기량</h6>

									<h3 class="fw-bold">50만 톤</h3>

								</div>

								<div class="dashboard-icon bg-primary-subtle text-primary">

									<i class="bi bi-trash"></i>

								</div>

							</div>

							<small class="text-muted"> (25.01.01 ~ 25.08.31) </small>

						</div>

					</div>

				</div>


				<div class="col-12 col-sm-6 col-xl-3">

					<div class="card dashboard-card shadow-sm">

						<div class="card-body">

							<div class="d-flex justify-content-between">

								<div>

									<h6 class="text-muted">올해 총 폐기량</h6>

									<h3 class="fw-bold">46만 톤</h3>

								</div>

								<div class="dashboard-icon bg-success-subtle text-success">

									<i class="bi bi-graph-down"></i>

								</div>

							</div>

							<small class="text-muted"> (26.01.01 ~ 26.08.31) </small>

						</div>

					</div>

				</div>


				<div class="col-12 col-sm-6 col-xl-3">

					<div class="card dashboard-card shadow-sm">

						<div class="card-body">

							<div class="d-flex justify-content-between">

								<div>

									<h6 class="text-muted">작년 9월 폐기량</h6>

									<h3 class="fw-bold">5만 톤</h3>

								</div>

								<div class="dashboard-icon bg-danger-subtle text-danger">

									<i class="bi bi-exclamation-triangle"></i>

								</div>

							</div>

							<small class="text-muted"> (25.09.01 ~ 25.09.30) </small>

						</div>

					</div>

				</div>


				<div class="col-12 col-sm-6 col-xl-3">

					<div class="card dashboard-card shadow-sm">

						<div class="card-body">

							<div class="d-flex justify-content-between">

								<div>

									<h6 class="text-muted">올해 9월 폐기량</h6>

									<h3 class="fw-bold">4.8만 톤</h3>

								</div>

								<div class="dashboard-icon bg-warning-subtle text-warning">

									<i class="bi bi-shop"></i>

								</div>

							</div>

							<small class="text-muted"> (26.09.01 ~ 26.09.23) </small>

						</div>

					</div>

				</div>

			</div>



			<!-- 그래프 -->
			<div class="row g-4 mb-4">

				<div class="col-12">

					<div class="card dashboard-card shadow-sm">

						<div class="card-header bg-white">

							<h5 class="mb-0">폐기량 추이</h5>
						</div>

						<div class="card-body">

							<div class="chart-control" id="chartControl"></div>
							<div class="chart-area" id="chartArea">
								<canvas id="wasteChart"></canvas>
							</div>

						</div>

					</div>

				</div>

			</div>
		</main>

	</div>

	<%@ include file="footer.jsp"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
		
	</script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="js/wasteGraph.js"></script>
</body>

</html>
