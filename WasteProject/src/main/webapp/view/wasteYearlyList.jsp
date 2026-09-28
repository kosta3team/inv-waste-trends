<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>

<%@ include file="adminHeader.jsp" %>
    
    <div class="d-flex">
    	<!-- aside -->
        <%@ include file="sidebar.jsp" %>

        <main class="main flex-grow-1 container-fluid p-4">
            <div class="mb-4">
                <h3 class="fw-bold">연중 폐기량 조회</h3>
                <p class="text-muted mb-0">선택한 연도의 월별 폐기량 누적 데이터를 조회합니다.</p>
            </div>
            
            <!-- 상단 검색 필터 -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body bg-light">
                    <form action="${pageContext.request.contextPath }/controller" method="GET" class="row g-3 align-items-center">
                        <input type="hidden" name="cmd" value="wasteYearlyList">
                        
                        <div class="col-auto">
                            <label class="form-label mb-0 fw-bold">조회 연도</label>
                            <select class="form-select" name="year">
                                <option value="2026" ${selectedYear == '2026' ? 'selected' : ''}>2026년</option>
                                <option value="2025" ${selectedYear == '2025' ? 'selected' : ''}>2025년</option>
                                <option value="2024" ${selectedYear == '2024' ? 'selected' : ''}>2024년</option>
                            </select>
                        </div>
                        
                        <div class="col-auto ms-auto mt-4">
                            <button type="submit" class="btn btn-primary px-4"><i class="bi bi-search"></i> 조회</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <!-- 선 그래프 영역 -->
            <div class="card shadow-sm mb-4">
            	<div class="card-header bg-white py-3">
            		<h5 class="mb-0 fw-bold">${empty selectedYear ? '2026' : selectedYear }년 연중 폐기량 추이 (선 그래프)</h5>
            	</div>
            	<div class="card-body">
            		<div class="chart-area" style="height: 300px;">
            			<canvas id="lineChart"></canvas>
            		</div>
            	</div>
            </div>

            <!-- 데이터 테이블 영역 -->
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold">${selectedYear}년 월별 상세 데이터</h5>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover table-striped align-middle mb-0">
                            <thead class="table-light">
                                <tr>
                                    <th scope="col" class="text-center">조회 연도</th>
                                    <th scope="col" class="text-center">해당 월</th>
                                    <th scope="col" class="text-end pe-5">발생 총량 (톤)</th>
                                    <th scope="col" class="text-center">상태</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- 서블릿에서 넘어온 yearlyList를 반복 출력 -->
                                <c:choose>
                                    <c:when test="${not empty yearlyList}">
                                        <c:forEach var="item" items="${yearlyList}">
                                            <tr>
                                                <td class="text-center fw-semibold">${selectedYear}</td>
                                                <td class="text-center">${item.month}</td>
                                                <td class="text-end pe-5">${item.amount}</td>
                                                <td class="text-center">
                                                    <span class="badge ${item.status == '주의' ? 'bg-danger' : 'bg-success'}">
                                                        ${item.status}
                                                    </span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="4" class="text-center py-4 text-muted">해당 연도의 데이터가 존재하지 않습니다.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <%@ include file="footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Chart.js 스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
    	const lineCtx = document.getElementById('lineChart').getContext('2d');
    	new Chart(lineCtx, {
    		type: 'line',
    		data: {
    			labels: ${empty chartLabels ? '["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"]' : chartLabels},
    			datasets: [{
    				label: '월별 발생량 (톤)',
    				data: ${empty chartValues ? '[12,19,15,25,22,30,28,35,22,18,15,26]' : chartValues},
    				borderColor: 'rgba(75, 192, 192, 1)',
    				backgroundColor: 'rgba(75, 192, 192, 0.2)',
    				borderWidth: 2,
    				tension: 0.3,
    				fill: true
    			}]
    		},
    		options: {responsive: true, main }
    	})
    </script>
</body>
</html>