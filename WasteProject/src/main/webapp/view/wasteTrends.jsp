<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>최강ERP</title>


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/wasteTrends.css">
</head>


<body>
	<%@ include file="adminHeader.jsp" %>
	
	<div class="d-flex">
		<!-- aside -->
		<%@ include file="sidebar.jsp" %>
		
		<main class="main flex-grow-1 container-fluid p-4">

			<div class="mb-4">
				<h3 class="fw-bold">폐기물 동향</h3>
				<p class="text-muted mb-0">조합 및 조합원 현황</p>
			</div>
			
			<!-- 필터 영역 시작 -->
			<div class="card mb-4 shadow-sm">
				<div class="card-body bg-light">
					<form action="${pageContext.request.contextPath }/controller" method="GET" id="wasteFilterForm" class="row g-3 align-items-center">
					
						<input type="hidden" name="cmd" value="wasteTrends">
						<div class="col-auto">
							<label class="form-label mb-0 fw-bold">비교년도</label>
							<div class="d-flex align-items-center">
								<select class="form-select" id="startYear" name="startYear">
									<option value="2024">2024</option>
									<option value="2023">2023</option>
								</select>
								<span class="mx-2">-</span>
								<select class="form-select" id="endYear" name="endYear">
									<option value="2025" selected>2025</option>
									<option value="2026">2026</option>
								</select>
							</div>
						</div>
						
						<div class="col-auto ms-3">
							<label class="form-label mb-0 fw-bold">월</label>
							<select class="form-select" id="month" name="month">
								<option value="ALL" ${selectedMonth == 'ALL' ? 'selected' : '' }>전체</option>
								<option value="09" ${selectedMonth == '09' ? 'selected' : '' }>9월</option>
								<option value="10월" ${selectedMonth == '10' ? 'selected' : '' }>10월</option>
							</select>
						</div>
						
						<div class="col-auto ms-3">
							<label class="form-label mb-0 fw-bold">지역</label>
							<select class="form-select" id="region" name="region">
								<option value="ALL" ${selectedRegion == 'ALL' ? 'selected' : '' }>전국</option>
								<option value="SEOUL" ${selectedRegion == 'SEOUL' ? 'selected' : ''}>서울</option>
								<option value="GYONGGI" ${selectedRegion == 'GYEONGGI' ? 'selected' : '' }>경기</option>
							</select>
						</div>
						
						<div class="col-auto ms-auto mt-4">
							<button type="submit" class="btn btn-primary px-4">조회</button>
						</div>
					</form>
				</div>
			</div>
			<!-- 필터 영역 끝 -->

			<!-- 카드 -->
			<div class="row g-4 mb-4">


				<div class="col-12 col-sm-6 col-xl-3">

					<div class="card dashboard-card shadow-sm">

						<div class="card-body">

							<div class="d-flex justify-content-between">

								<div>
									<h6 class="text-muted">작년 총 폐기량</h6>
									<h3 class="fw-bold">${empty lastYearTotal ? '50': lastYearTotal}만 톤</h3>
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
									<h3 class="fw-bold">${empty thisYearTotal ? '46' : thisYearTotal}만 톤</h3>
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
									<h6 class="text-muted">작년 특정월 폐기량</h6>
									<h3 class="fw-bold">${empty lastMonthTotal ? '5' : lastMonthTotal}만 톤</h3>
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
									<h6 class="text-muted">올해 특정월 폐기량</h6>
									<h3 class="fw-bold">${empty thisMonthTotal ? '4.8' : thisMonthTotal}만 톤</h3>
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



			<!-- 그래프 시작 -->
			<div class="row g-4 mb-4">
				<div class="col-12">
					<div class="card dashboard-card shadow-sm">
						<div class="card-header bg-white">
							<h5 class="mb-0">폐기량 추이 (막대그래프)</h5>
						</div>
						<div class="card-body">
							<div class="chart-area" style="height: 300px">
								<canvas id="barChart"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 그래프 마무리 -->
			
		</main>

	</div>

	<%@ include file="footer.jsp" %>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>	

	<!-- Chart.js 라이브러리 추가 -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
		const barCtx = document.getElementById('barChart').getContext('2d');
		new Chart(barCtx, {
			type: 'bar',
			data: {
				labels: ['${empty startYear ? "2024" : startYear}년', '${empty endYear ? "2025" : endYear}년'],
				datasets: [
					{
						label: '총 폐기량 (만 톤)',
						data: ${empty chartDataTotal ? '[50,46]' : chartDataTotal},
						backgroundColor: 'rgba(54,162,235,0.6)',
						borderWidth: 1
					},
					{
						label: '특정월 폐기량 (만 톤)',
						data: ${empty chartDataMonth ? '[5, 4.8]' : chartDataMonth},
						backgroundColor: 'rgba(255, 99, 132, 0.6)',
						borderWidth: 1
					}
				]
			},
			options: {responsive: true, maintainAspectRatio: false}
		});
	</script>
</body>

</html>
