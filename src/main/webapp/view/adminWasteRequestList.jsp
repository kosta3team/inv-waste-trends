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
							<td>
							<td>
    							<button class="btn btn-outline-secondary btn-detail" 
            					data-bs-toggle="modal"
            					data-bs-target="#warehouseDetailModal"
					            data-waste-no="${wasteRequest.wasteNo}">상세조회</button>
							</td>
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

	<!-- 모달 -->
	<div class="modal fade" id="warehouseDetailModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title mb-0">폐기 요청 승인 상세</h5>
					<div class="ms-auto d-flex align-items-center gap-3">
						<h5 class="mb-0">
							요청일자 : <span id="modalReqDate"></span>
						</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
				</div>
				<div class="modal-body">
					<h6>
						재고 일련번호 : <span id="modalStockNo"></span>
					</h6>
					<table class="table table-bordered align-middle fixed-table">
						<tbody>
							<tr>
								<th class="table-light">품목코드</th>
								<td id="modalItemCode"></td>
								<th class="table-light">품목</th>
								<td id="modalItemName"></td>
								<th class="table-light">품종</th>
								<td id="modalVariety"></td>
							</tr>
							<tr>
								<th class="table-light">상품명</th>
								<td colspan="2" id="modalProductName"></td>
								<th class="table-light">원산지</th>
								<td colspan="2" id="modalOrigin"></td>
							</tr>
							<tr>
								<th class="table-light">조합원명</th>
								<td colspan="2" id="modalMemberName"></td>
								<th class="table-light">이름</th>
								<td colspan="2" id="modalName"></td>
							</tr>
						</tbody>
					</table>

					<h6 class="fw-bold mt-4">폐기 사유</h6>
					<table class="table table-bordered align-middle">
						<tbody>
							<tr>
								<th class="table-light">사유 구분</th>
								<td id="modalReasonCategory"></td>
							</tr>
							<tr>
								<th class="table-light">상세 내용</th>
								<td id="modalReasonDetail"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-approve px-4" id="btnApprove">승인</button>
					<button type="button" class="btn btn-reject px-4" id="btnReject">거절</button>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="footer.jsp"%>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
		rel="stylesheet">

	<script>
    document.addEventListener("DOMContentLoaded", function () {
        const modal = document.querySelector('#warehouseDetailModal');
        var currentWasteNo = null; // 승인/거절 버튼 등에 사용할 wasteNo 저장용
        var xhr = new XMLHttpRequest();

        // 모달 내 데이터 응답 처리
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {   
            	const response = xhr.responseText.trim();

                // key=value 문자열에서 값만 뽑아오는 함수
                function getValue(key) {
                    // key= 뒤에 오며, 쉼표(,)나 닫는 대괄호(]) 전까지의 문자열을 추출
                    const regex = new RegExp(key + '=([^,\\]]+)');
                    const match = response.match(regex);
                    return match ? match[1].trim() : '';
                }

                // VO 필드명에 맞춰 모달에 데이터 바인딩
                document.getElementById('modalReqDate').textContent = getValue('wasteReqDate');
                document.getElementById('modalStockNo').textContent = getValue('fruitPorductNo');
                document.getElementById('modalItemCode').textContent = getValue('itemCode');
                document.getElementById('modalItemName').textContent = getValue('itemName');
                document.getElementById('modalVariety').textContent = getValue('kindName');          // VO의 kindName
                document.getElementById('modalProductName').textContent = getValue('fruitProductName'); // VO의 fruitProductName
                document.getElementById('modalOrigin').textContent = getValue('origin');
                document.getElementById('modalMemberName').textContent = getValue('memberGroupName'); // VO의 memberGroupName
                document.getElementById('modalName').textContent = getValue('memberName');            // VO의 memberName
                document.getElementById('modalReasonCategory').textContent = getValue('wasteCategoryReason'); // VO의 wasteCategoryReason
                document.getElementById('modalReasonDetail').textContent = getValue('reasonDetail');

                // 승인/거절 버튼 dataset에 wasteNo 저장
                document.getElementById('btnApprove').dataset.wasteNo = currentWasteNo;
                document.getElementById('btnReject').dataset.wasteNo = currentWasteNo;
            }
        };

        // 모달이 열릴 때 버튼의 data-waste-no 값을 읽어서 XHR 요청 전송
        modal.addEventListener('show.bs.modal', function (event) {
            // 모달을 트리거한 버튼 요소
            const button = event.relatedTarget;
            
            // data-waste-no 속성 값 가져오기
            const wasteNo = button.getAttribute('data-waste-no');
            currentWasteNo = wasteNo;

            // XHR 요청 보내기
            const url = "controller?cmd=adminGetWasteRequestDetail&wasteNo=" + wasteNo;
            xhr.open("GET", url, true);
            xhr.send();
        });
    });
	</script>


	<script type="text/javascript"> // 필터 비동기 요청 
		const onlyWait = document.querySelector("#OnlyWait");
		const wasteRequestList = document.querySelector("#wasteRequestList");
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				wasteRequestList.innerHTML = xhr.responseText;
			}
		}
		filterEvent = function() {
			const url = "controller?cmd=adminGetWasteRequestList&IsOnlyRequest="
					+ onlyWait.checked;
			xhr.open("get", url, true);
			xhr.send();
		}

		onlyWait.addEventListener("change", filterEvent);
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"> </script>

</body>
</html>