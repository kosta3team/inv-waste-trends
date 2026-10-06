<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최강 ERP - 연도별 총량 비교</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wasteTrends.css">
</head>
<body class="bg-light">
    <%@ include file="adminHeader.jsp" %>
    <div class="d-flex">
        <%@ include file="sidebar.jsp" %>
        <main class="main flex-grow-1 container-fluid p-4">
            <div class="mb-4">
                <h3 class="fw-bold">연도별 총량 비교</h3>
                <p class="text-muted mb-0">여러 연도의 월별 폐기량 데이터를 한눈에 비교합니다.</p>
            </div>
            
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <!-- 상단: 다중 연도 선택 필터 -->
                    <div class="filter-section border-bottom pb-3 mb-4">
                        <form id="compareForm" class="row g-3 align-items-center">
                            <div class="col-auto">
                                <label class="form-label mb-0 fw-bold me-3">비교 연도 선택</label>
                            </div>
                            <div class="col-auto">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input year-checkbox" type="checkbox" value="2026" checked>
                                    <label class="form-check-label">2026년</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input year-checkbox" type="checkbox" value="2025" checked>
                                    <label class="form-check-label">2025년</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input year-checkbox" type="checkbox" value="2024">
                                    <label class="form-check-label">2024년</label>
                                </div>
                            </div>
                            <div class="col-auto ms-3">
                                <button type="button" class="btn btn-primary px-4" onclick="updateChart()"><i class="bi bi-bar-chart-line"></i> 비교 조회</button>
                            </div>
                        </form>
                    </div>
                    
                    <!-- 하단: 다중 선 그래프 영역 -->
                    <div class="chart-section">
                        <div class="chart-area" style="height: 400px;">
                            <canvas id="compareLineChart"></canvas>
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
        const dbData = {
            '2026': { data: [25, 32, 45, 58, 40, 35, 30, 42, 65, 50, 48, 62], color: '#FF6384' },
            '2025': { data: [20, 28, 38, 48, 35, 30, 25, 35, 55, 45, 40, 52], color: '#36A2EB' },
            '2024': { data: [15, 22, 30, 40, 28, 24, 18, 28, 45, 38, 32, 45], color: '#FFCE56' }
        };

        let compareChart;

        function updateChart() {
            const checkboxes = document.querySelectorAll('.year-checkbox:checked');
            const datasets = [];

            checkboxes.forEach(cb => {
                const year = cb.value;
                datasets.push({
                    label: year + '년 (톤)',
                    data: dbData[year].data,
                    borderColor: dbData[year].color,
                    backgroundColor: 'transparent',
                    borderWidth: 2,
                    tension: 0.3,
                    pointBackgroundColor: dbData[year].color
                });
            });

            if(compareChart) compareChart.destroy();

            const ctx = document.getElementById('compareLineChart').getContext('2d');
            compareChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
                    datasets: datasets
                },
                options: { maintainAspectRatio: false, responsive: true }
            });
        }
        
        // 초기 로드 시 차트 렌더링
        updateChart();
    </script>
</body>
</html>