<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>최강 ERP - 기온별 폐기량 비교</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wasteTrends.css">
</head>
<body>
    <%@ include file="adminHeader.jsp" %>
    <div class="d-flex">
        <%@ include file="sidebar.jsp" %>
        <main class="main flex-grow-1 container-fluid p-4">
            <div class="mb-4">
                <h3 class="fw-bold">기온별 폐기량 비교</h3>
                <p class="text-muted mb-0">온도 변화에 따른 폐기량 추이를 연도별로 비교 분석합니다.</p>
            </div>
            
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <div class="filter-section border-bottom pb-3 mb-4 bg-light rounded p-3">
                        <form id="tempForm" class="row g-3 align-items-end">
                            <div class="col-md-2">
                                <label class="form-label fw-bold mb-1">기준</label>
                                <select class="form-select">
                                    <option value="avg">평균 기온</option>
                                    <option value="max">최고 기온</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label class="form-label fw-bold mb-1">지역</label>
                                <select class="form-select">
                                    <option value="all">전체</option>
                                    <option value="seoul">서울</option>
                                    <option value="gyeonggi">경기</option>
                                    <option value="gangwon">강원</option>
                                    <option value="chungbuk">충북</option>
                                    <option value="chungnam">충남</option>
                                    <option value="gyeongbuk">경북</option>
                                    <option value="gyeongnam">경남</option>
                                    <option value="jeonbuk">전북</option>
                                    <option value="jeonnam">전남</option>
                                    <option value="jeju">제주</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label class="form-label fw-bold mb-1">온도 범위 (℃)</label>
                                <div class="input-group">
                                    <!-- 기본값을 -10 ~ 40도로 세팅 -->
                                    <input type="number" class="form-control text-center" value="-10" id="minTemp">
                                    <span class="input-group-text">~</span>
                                    <input type="number" class="form-control text-center" value="40" id="maxTemp">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label fw-bold mb-1">년도 범위</label>
                                <div class="input-group">
                                    <select class="form-select text-center" id="startYear">
                                        <option value="2025" selected>2025</option>
                                        <option value="2026">2026</option>
                                    </select>
                                    <span class="input-group-text">~</span>
                                    <select class="form-select text-center" id="endYear">
                                        <option value="2025">2025</option>
                                        <option value="2026" selected>2026</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-1 text-end">
                                <button type="button" class="btn btn-primary w-100" onclick="updateTempChart()">조회</button>
                            </div>
                        </form>
                    </div>
                    
                    <div class="chart-section">
                        <div class="chart-area" style="height: 400px;">
                            <canvas id="tempLineChart"></canvas>
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
        // -10도부터 40도까지 총 51개의 온도별 폐기량 더미 데이터
        // 저온에서는 평이하다가 18~34도 구간에서 피크를 찍는 곡선 형태
        const tempDbData = {
            '2026': [
                15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, // -10 ~ 0도
                21, 22, 23, 24, 25, 26, 28, 30, 32, 35,     // 1 ~ 10도
                38, 40, 43, 46, 50, 53, 57, 60, 63, 65,     // 11 ~ 20도
                68, 70, 72, 75, 76, 78, 79, 80, 78, 75,     // 21 ~ 30도 (여름 피크)
                72, 68, 65, 60, 55, 50, 45, 40, 35, 30      // 31 ~ 40도 (폭염 시 감소)
            ],
            '2025': [
                12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, // -10 ~ 0도
                18, 19, 20, 21, 22, 23, 25, 27, 29, 32,     // 1 ~ 10도
                35, 37, 40, 43, 47, 50, 54, 57, 60, 62,     // 11 ~ 20도
                65, 67, 69, 72, 73, 75, 76, 77, 75, 72,     // 21 ~ 30도
                69, 65, 62, 57, 52, 47, 42, 37, 32, 27      // 31 ~ 40도
            ]
        };
        
        let tempChart;

        function updateTempChart() {
            const startYear = parseInt(document.getElementById('startYear').value);
            const endYear = parseInt(document.getElementById('endYear').value);
            
            // 사용자가 입력한 온도 범위 가져오기
            let minT = parseInt(document.getElementById('minTemp').value);
            let maxT = parseInt(document.getElementById('maxTemp').value);
            
            // 입력값이 -10과 40을 벗어나지 않도록 방어 코드
            minT = Math.max(-10, Math.min(minT, 40));
            maxT = Math.max(minT, Math.min(maxT, 40));

            // 동적으로 X축 라벨(온도) 생성
            const dynamicLabels = [];
            for (let t = minT; t <= maxT; t++) {
                dynamicLabels.push(t + '℃');
            }
            
            const datasets = [];
            const colors = { '2026': '#4BC0C0', '2025': '#9966FF' };

            for(let y = startYear; y <= endYear; y++) {
                if(tempDbData[y.toString()]) {
                    // 데이터 인덱스 매핑: -10도는 0번 인덱스, 0도는 10번 인덱스
                    const startIndex = minT + 10;
                    const endIndex = maxT + 10;
                    
                    // 사용자가 선택한 구간만큼 배열을 잘라냄(slice)
                    const slicedData = tempDbData[y.toString()].slice(startIndex, endIndex + 1);

                    datasets.push({
                        label: y + '년 (톤)',
                        data: slicedData,
                        borderColor: colors[y.toString()],
                        backgroundColor: 'transparent',
                        borderWidth: 2,
                        tension: 0.4
                    });
                }
            }

            if(tempChart) tempChart.destroy();

            const ctx = document.getElementById('tempLineChart').getContext('2d');
            tempChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: dynamicLabels,
                    datasets: datasets
                },
                options: { 
                    maintainAspectRatio: false, 
                    responsive: true,
                    scales: {
                        y: { title: { display: true, text: '폐기량 (톤)' } },
                        x: { title: { display: true, text: '평균 기온 (℃)' } }
                    }
                }
            });
        }
        
        // 초기 로딩 시 차트 렌더링
        updateTempChart();
    </script>
</body>
</html>