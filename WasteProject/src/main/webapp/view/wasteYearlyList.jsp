<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최강 ERP - 연중 폐기량 조회</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/wasteTrends.css">
</head>
<body class="bg-light">
    <%@ include file="adminHeader.jsp" %>
    <div class="d-flex">
        <%@ include file="sidebar.jsp" %>
        <main class="main flex-grow-1 container-fluid p-4">
            <div class="mb-4">
                <h3 class="fw-bold">연중 폐기량 조회</h3>
                <p class="text-muted mb-0">선택한 연도의 월별 폐기량 누적 데이터를 조회합니다.</p>
            </div>
            
            <!-- 통합된 Card 영역 (상단 필터, 하단 그래프) -->
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <!-- 상단: 조회 필터 영역 -->
                    <div class="filter-section border-bottom pb-3 mb-4">
                        <form id="searchForm" class="row g-3 align-items-end">
                            <input type="hidden" name="cmd" value="wasteYearlyList">
                            <div class="col-auto">
                                <label class="form-label mb-1 fw-bold">조회 연도</label>
                                <select class="form-select" name="year" id="yearSelect">
                                    <option value="2026">2026년</option>
                                    <option value="2025">2025년</option>
                                    <option value="2024">2024년</option>
                                </select>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary px-4"><i class="bi bi-search"></i> 조회</button>
                            </div>
                        </form>
                    </div>
                    
                    <!-- 하단: 선 그래프 영역 -->
                    <div class="chart-section">
                        <h5 class="mb-3 fw-bold text-center" id="chartTitle">2026년 연중 폐기량 추이</h5>
                        <div class="chart-area" style="height: 400px;">
                            <canvas id="lineChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <%@ include file="footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        // 샘플 데이터 (2024, 2025, 2026)
        const dbData = {
            '2026': [25, 32, 45, 58, 40, 35, 30, 42, 65, 50, 48, 62],
            '2025': [20, 28, 38, 48, 35, 30, 25, 35, 55, 45, 40, 52],
            '2024': [15, 22, 30, 40, 28, 24, 18, 28, 45, 38, 32, 45]
        };

        // URL 파라미터에서 선택된 연도 가져오기 (기본값 2026)
        const urlParams = new URLSearchParams(window.location.search);
        const selectedYear = urlParams.get('year') || '2026';
        document.getElementById('yearSelect').value = selectedYear;
        document.getElementById('chartTitle').innerText = selectedYear + '년 연중 폐기량 추이';

        const lineCtx = document.getElementById('lineChart').getContext('2d');
        new Chart(lineCtx, {
            type: 'line',
            data: {
                labels: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
                datasets: [{
                    label: selectedYear + '년 발생량 (톤)',
                    data: dbData[selectedYear],
                    borderColor: 'rgba(54, 162, 235, 1)',
                    backgroundColor: 'rgba(54, 162, 235, 0.1)',
                    borderWidth: 2,
                    tension: 0.3,
                    fill: true,
                    pointBackgroundColor: 'rgba(54, 162, 235, 1)'
                }]
            },
            options: { maintainAspectRatio: false, responsive: true }
        });
    </script>
</body>
</html>