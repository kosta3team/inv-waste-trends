<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside class="sidebar">
	<div class="logo">
		<i class="bi bi-bar-chart-line me-2"></i><span>폐기량 분석</span>
	</div>
	
	<ul class="nav flex-column mt-3">
		<!-- 특정 양력 월 비교 (막대그래프) -->
		<li class="nav-item">
			<a class="nav-link ${empty param.cmd || param.cmd == 'wasteTrends' ? 'active' : '' }"
				href="${pageContext.request.contextPath }/controller?cmd=wasteTrends">
				<i class="bi bi-sun"></i><span>특정 양력 월 비교</span>
			</a>
		</li>
		
		<!-- 특정 음력 월 비교 (막대그래프) -->
		<li class="nav-item">
			<a class="nav-link ${param.cmd == 'wasteLunarTrends' ? 'active' : '' }"
				href="${pageContext.request.contextPath }/controller?cmd=wasteLunarTrends">
				<i class="bi bi-moon-stars"></i><span>특정 음력 월 비교</span>
			</a>
		</li>
		
		<!-- 연중 폐기량 조회 (선그래프) -->
		<li class="nav-item">
			<a class="nav-link ${param.cmd == 'wasteYearlyList' ? 'active' : ''}"
				href="${pageContext.request.contextPath }/controller?cmd=wasteYearlyList">
				<i class="bi bi-graph-up"></i><span>연중 폐기량 조회</span>
			</a>
		</li>
		
		<!-- 연도별 총량 비교 (선그래프 예정) -->
		<li class="nav-item">
			<a class="nav-link ${param.cmd == 'wasteYearlyCompare' ? 'active' : ''}"
				href="${pageContext.request.contextPath }/controller?cmd=wasteYearlyCompare">
				<i class="bi bi-layers"></i><span>연도별 총량 비교</span>
			</a>
		</li>
		
		<!--  기온별 폐기량 비교 (선그래프 예정) -->
		<li class="nav-item">
			<a class="nav-link ${param.cmd == 'wasteTemperatureCompare' ? 'active' : ''}"
				href="${pageContext.request.contextPath }/controller?cmd=wasteTemperatureCompare">
				<i class="bi bi-thermometer-half"></i><span>기온별 폐기량 비교</span>
			</a>
		</li>
	</ul>
</aside>