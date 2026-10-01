<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>최강ERP - 특정 양력 월 비교</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wasteTrends.css">
</head>
<body class="bg-light">
    <%@ include file="adminHeader.jsp" %>
    
    <div class="d-flex">
        <!-- aside -->
        <%@ include file="sidebar.jsp" %>
        
        <main class="main flex-grow-1 container-fluid p-4">

            <div class="mb-4">
                <h3 class="fw-bold">
                    <i class="bi bi-sun-fill text-danger me-2"></i>폐기량 동향 
                    <span class="fs-5 text-muted ms-2">(특정 양력 월 비교)</span>
                </h3>             
            </div>
            
            <!-- 1. 요약 카드 영역 (가장 상단에 배치) -->
            <div class="row g-4 mb-4">
                <div class="col-12 col-sm-6 col-xl-3">
                    <div class="card dashboard-card shadow-sm border-0">
                        <div class="card-body">
                            <div class="d-flex justify-content-between">
                                <div>
                                    <h6 class="text-muted fw-bold">작년 총 폐기량</h6>
                                    <h3 class="fw-bold">${empty lastYearTotal ? '50': lastYearTotal}만 톤</h3>
                                </div>
                                <div class="dashboard-icon bg-primary-subtle text-primary"><i class="bi bi-trash"></i></div>
                            </div>
                            <small class="text-muted"> (25.01.01 ~ 25.08.31) </small>
                        </div>
                    </div>
                </div>
                
                <div class="col-12 col-sm-6 col-xl-3">
                    <div class="card dashboard-card shadow-sm border-0">
                        <div class="card-body">
                            <div class="d-flex justify-content-between">
                                <div>
                                    <h6 class="text-muted fw-bold">올해 총 폐기량</h6>
                                    <h3 class="fw-bold">${empty thisYearTotal ? '46' : thisYearTotal}만 톤</h3>
                                </div>
                                <div class="dashboard-icon bg-success-subtle text-success"><i class="bi bi-graph-down"></i></div>
                            </div>
                            <small class="text-muted"> (26.01.01 ~ 26.08.31) </small>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-sm-6 col-xl-3">
                    <div class="card dashboard-card shadow-sm border-0">
                        <div class="card-body">
                            <div class="d-flex justify-content-between">
                                <div>
                                    <h6 class="text-muted fw-bold">작년 특정월 폐기량</h6>
                                    <h3 class="fw-bold">${empty lastMonthTotal ? '5' : lastMonthTotal}만 톤</h3>
                                </div>
                                <div class="dashboard-icon bg-danger-subtle text-danger"><i class="bi bi-exclamation-triangle"></i></div>
                            </div>
                            <small class="text-muted"> (25.09.01 ~ 25.09.30) </small>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-sm-6 col-xl-3">
                    <div class="card dashboard-card shadow-sm border-0">
                        <div class="card-body">
                            <div class="d-flex justify-content-between">
                                <div>
                                    <h6 class="text-muted fw-bold">올해 특정월 폐기량</h6>
                                    <h3 class="fw-bold">${empty thisMonthTotal ? '4.8' : thisMonthTotal}만 톤</h3>
                                </div>
                                <div class="dashboard-icon bg-warning-subtle text-warning"><i class="bi bi-shop"></i></div>
                            </div>
                            <small class="text-muted"> (26.09.01 ~ 26.09.23) </small>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 2. 필터 + 차트 영역 (하나의 카드로 통합) -->
            <div class="card shadow-sm border-0">
                <!-- 상단: 폼 필터 영역 -->
                <div class="card-header bg-white py-3 border-bottom">
                    <form action="${pageContext.request.contextPath }/controller" method="GET" id="wasteFilterForm" class="row g-3 align-items-center m-0">
                        <input type="hidden" name="cmd" value="wasteTrends">
                        
                        <div class="col-auto d-flex align-items-center">
                            <label class="form-label mb-0 fw-bold me-2">비교연도</label>
                            <select class="form-select form-select-sm w-auto" id="startYear" name="startYear">
                                <option value="2024" ${startYear == '2024' ? 'selected' : ''}>2024</option>
                                <option value="2023" ${startYear == '2023' ? 'selected' : ''}>2023</option>
                            </select>
                            <span class="mx-2 fw-bold">-</span>
                            <select class="form-select form-select-sm w-auto" id="endYear" name="endYear">
                                <option value="2025" ${endYear == '2025' ? 'selected' : ''}>2025</option>
                                <option value="2026" ${endYear == '2026' ? 'selected' : ''}>2026</option>
                            </select>
                        </div>
                        
                        <div class="col-auto d-flex align-items-center ms-3">
                            <label class="form-label mb-0 fw-bold me-2">월</label>
                            <select class="form-select form-select-sm w-auto" id="month" name="month">
                                <option value="ALL" ${selectedMonth == 'ALL' ? 'selected' : '' }>전체</option>
                                <option value="01" ${selectedMonth == '01' ? 'selected' : '' }>1월</option>
                                <option value="02" ${selectedMonth == '02' ? 'selected' : '' }>2월</option>
                                <option value="03" ${selectedMonth == '03' ? 'selected' : '' }>3월</option>
                                <option value="04" ${selectedMonth == '04' ? 'selected' : '' }>4월</option>
                                <option value="05" ${selectedMonth == '05' ? 'selected' : '' }>5월</option>
                                <option value="06" ${selectedMonth == '06' ? 'selected' : '' }>6월</option>
                                <option value="07" ${selectedMonth == '07' ? 'selected' : '' }>7월</option>
                                <option value="08" ${selectedMonth == '08' ? 'selected' : '' }>8월</option>
                                <option value="09" ${selectedMonth == '09' ? 'selected' : '' }>9월</option>
                                <option value="10" ${selectedMonth == '10' ? 'selected' : '' }>10월</option>
                                <option value="11" ${selectedMonth == '11' ? 'selected' : '' }>11월</option>
                                <option value="12" ${selectedMonth == '12' ? 'selected' : '' }>12월</option>
                            </select>
                        </div>
                        
                        <div class="col-auto d-flex align-items-center ms-3">
                            <label class="form-label mb-0 fw-bold me-2">지역</label>
                            <select class="form-select form-select-sm w-auto" id="region" name="region">
                                <option value="ALL" ${selectedRegion == 'ALL' ? 'selected' : '' }>전국</option>
                                <option value="SEOUL" ${selectedRegion == 'SEOUL' ? 'selected' : ''}>서울</option>
                                <option value="GYEONGGI" ${selectedRegion == 'GYEONGGI' ? 'selected' : '' }>경기</option>
                                <option value="GANGWON" ${selectedRegion == 'GANGWON' ? 'selected' : '' }>강원</option>
                                <option value="CHUNGBUK" ${selectedRegion == 'CHUNGBUK' ? 'selected' : '' }>충북</option>
                                <option value="CHUNGNAM" ${selectedRegion == 'CHUNGNAM' ? 'selected' : '' }>충남</option>
                                <option value="GYEONGBUK" ${selectedRegion == 'GYEONGBUK' ? 'selected' : '' }>경북</option>
                                <option value="GYEONGNAM" ${selectedRegion == 'GYEONGNAM' ? 'selected' : '' }>경남</option>
                                <option value="JEONBUK" ${selectedRegion == 'JEONBUK' ? 'selected' : '' }>전북</option>
                                <option value="JEONNAM" ${selectedRegion == 'JEONNAM' ? 'selected' : '' }>전남</option>
                                <option value="JEJU" ${selectedRegion == 'JEJU' ? 'selected' : '' }>제주</option>
                            </select>
                        </div>
                        
                        <div class="col-auto ms-2">
                            <button type="submit" class="btn btn-sm btn-primary px-4">조회</button>
                        </div>
                    </form>
                </div>
                
                <!-- 하단: 차트 렌더링 영역 -->
                <div class="card-body">
                    <div class="chart-area" style="height: 350px">
                        <canvas id="barChart"></canvas>
                    </div>
                </div>
            </div>
            
        </main>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const sYear = '${empty startYear ? "2024" : startYear}';
        const eYear = '${empty endYear ? "2025" : endYear}';
        const sMonth = '${selectedMonth}';
        
        const label1 = (sMonth === 'ALL' || sMonth === '') ? sYear + '년' : sYear + '년 ' + sMonth + '월';
        const label2 = (sMonth === 'ALL' || sMonth === '') ? eYear + '년' : eYear + '년 ' + sMonth + '월';

        const barCtx = document.getElementById('barChart').getContext('2d');
        new Chart(barCtx, {
            type: 'bar',
            data: {
                labels: [label1, label2],
                datasets: [
                    {
                        label: '총 폐기량 (만 톤)',
                        data: ${empty chartDataTotal ? '[50,46]' : chartDataTotal},
                        backgroundColor: 'rgba(54,162,235,0.7)',
                        borderWidth: 0
                    },
                    {
                        label: '특정월 폐기량 (만 톤)',
                        data: ${empty chartDataMonth ? '[5, 4.8]' : chartDataMonth},
                        backgroundColor: 'rgba(255, 99, 132, 0.7)',
                        borderWidth: 0
                    }
                ]
            },
            options: {
                responsive: true, 
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>