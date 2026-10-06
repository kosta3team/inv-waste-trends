<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>폐기 요청 목록 조회</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/inventoryList.css">

</head>
<body>
	<%@ include file="adminHeader.jsp"%>
	<main class="inventory-page">

		<!-- 제목 -->
		<div class="mb-4">
			<h3 class="fw-bold mb-1">폐기 요청 목록 조회</h3>
		</div>


		<!-- 검색 영역 -->
		<div style="text-align: right";>
			<input type="checkbox" id="OnlyWait">대기만보기
		</div>



		<!-- 재고 목록 나중에 db에서 불러오는 값 넣을겁니다~ -->
		<div class="inventory-table-area">

			<table class="table table-hover inventory-table">

				<thead>

					<tr>
						<th>순번</th>
						<th>폐기요청일련번호</th>
						<th>폐기요청일자</th>
						<th>폐기처리일자</th>
						<th>조합원명</th>
						<th>이름</th>
						<th>폐기요청상태</th>
					</tr>

				</thead>





				<tbody id="wasteRequestList">
					<c:forEach items="${WasteRequestList}" var="wasteRequest"
						varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${wasteRequest.wasteNo}</td>
							<td>${wasteRequest.wasteReqDate}</td>
							<td>${wasteRequest.wasteDate}</td>
							<td>${wasteRequest.memberName}</td>
							<td>${wasteRequest.name}</td>
							<td>${wasteRequest.status}</td>
						</tr>
					</c:forEach>

				</tbody>


			</table>

		</div>

	</main>

	<!-- 페이지네이션 -->
	<div class="pagination-area">

		<nav>
			<ul class="pagination justify-content-center">

				<li class="page-item disabled"><a class="page-link" href="#">
						<i class="bi bi-chevron-left"></i>
				</a></li>

				<li class="page-item active"><a class="page-link" href="#">1</a>
				</li>

				<li class="page-item"><a class="page-link" href="#">2</a></li>

				<li class="page-item"><a class="page-link" href="#">3</a></li>

				<li class="page-item"><a class="page-link" href="#">4</a></li>

				<li class="page-item"><a class="page-link" href="#">5</a></li>

				<li class="page-item"><a class="page-link" href="#"> <i
						class="bi bi-chevron-right"></i>
				</a></li>
			</ul>
		</nav>
	</div>


	<%@ include file="footer.jsp"%>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
		rel="stylesheet">



	<script type="text/javascript">
		
		 const onlyWait = document.querySelector("#OnlyWait");
		 const wasteRequestList = document.querySelector("#wasteRequestList");

		 
	        var xhr = new XMLHttpRequest();
	        
		 
	        xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						
						wasteRequestList.innerHTML = xhr.responseText;
						
					}
	            }
	            
	            filterEvent = function() {
	            	const url = "controller?cmd=adminGetWasteRequestList&IsOnlyRequest=" + onlyWait.checked;
	    			xhr.open('get', url, true);
	    			xhr.send();
	            }
	        
	        
		 
		  onlyWait.addEventListener("change", filterEvent);
		        
			
		
		</script>
</body>
</html>