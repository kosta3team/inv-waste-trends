<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>폐기 요청 목록 승인 상세</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/warehousinngCoop.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wasteDetail.css">

</head>

<body>

	<div class="container mt-5">

		<h3 class="mb-4">폐기 요청 목록</h3>

		<table class="table table-bordered">
			<tr>
				<th>요청번호</th>
				<th>품목</th>
				<th>조합원</th>
				<th>상세</th>
			</tr>
			<tr>
				<td>REQ001</td>
				<td>설향딸기</td>
				<td>노종과수원</td>
				<td>
					<button class="btn btn-outline-secondary" data-bs-toggle="modal"
						data-bs-target="#warehouseDetailModal">상세조회</button>
				</td>
			</tr>
		</table>

	</div>

	<!-- 모달 -->
	<div class="modal fade" id="warehouseDetailModal" tabindex="-1"
		aria-hidden="true">

		<div class="modal-dialog modal-xl modal-dialog-scrollable">

			<div class="modal-content">

				<div class="modal-header">

					<h5 class="modal-title mb-0">폐기 요청 승인 상세</h5>

					<div class="ms-auto d-flex align-items-center gap-3">

						<h5 class="mb-0">요청일자 : 2026-09-22</h5>

						<button type="button" class="btn-close" data-bs-dismiss="modal">
						</button>

					</div>

				</div>

				<div class="modal-body">

					<h6>재고 일련번호 : ST001</h6>


					<table class="table table-bordered align-middle fixed-table">
						<tbody>
							<tr>
								<th class="table-light">품목코드</th>
								<td>001</td>

								<th class="table-light">품목</th>
								<td>딸기</td>

								<th class="table-light">품종</th>
								<td>설향</td>
							</tr>

							<tr>
								<th class="table-light">상품명</th>
								<td colspan="2">설향딸기</td>
								
								<th class="table-light">원산지</th>
								<td colspan="2">전북 남원</td>
							</tr>

							<tr>
								<th class="table-light">조합원명</th>
								<td colspan="2">종현과수원</td>

								<th class="table-light">이름</th>
								<td colspan="2">노종현</td>

							</tr>
						</tbody>
					</table>

					<h6 class="fw-bold mt-4">폐기 사유</h6>

					<table class="table table-bordered align-middle">
						<tbody>

							<tr>
								<th class="table-light">사유 구분</th>
								<td>제품 하자</td>
							</tr>

							<tr>
								<th class="table-light">상세 내용</th>
								<td>농약 과다 검출로 인해 판매가 불가능하여 폐기를 요청합니다.</td>
							</tr>

						</tbody>
					</table>


					

				</div>

				<div class="modal-footer">

					<button type="button" class="btn btn-approve px-4">승인</button>
					<button type="button" class="btn btn-reject px-4">거절</button>

				</div>

			</div>

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>