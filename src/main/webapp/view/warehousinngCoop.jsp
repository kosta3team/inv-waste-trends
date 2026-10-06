
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>입고 요청 목록 상세 조회(조합원)</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/warehousinngCoop.css">

</head>

<body>

	<div class="container mt-5">

		<h3 class="mb-4">입고 요청 목록(조합원)</h3>

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

					<h5 class="modal-title mb-0">입고 요청 목록 상세 조회(조합원)</h5>

					<div class="ms-auto d-flex align-items-center gap-3">

						<h5 class="mb-0">요청일자 : 2026-09-22</h5>

						<button type="button" class="btn-close" data-bs-dismiss="modal">
						</button>

					</div>

				</div>

				<div class="modal-body">

					<h6>상품정보</h6>

					<table class="table table-bordered align-middle">
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
								<td>설향딸기</td>
								
								<th class="table-light">원산지</th>
								<td>강원도 철원</td>

								<th class="table-light">단가(1Box)</th>
								<td>30,000원</td>

								
							</tr>

							<tr>
								<th class="table-light">입고수량(Box)</th>
								<td>25</td>

								<th class="table-light">중량(1Box)</th>
								<td>3kg</td>

								<th class="table-light">총 판매 예상 금액</th>
								<td>750,000원</td>
							</tr>
						</tbody>
					</table>

					<h6>조합원 정보</h6>

					<table class="table table-bordered align-middle">
						<tbody>

							<tr>
								<th class="table-light">조합원명</th>
								<td colspan="3">노종과수원</td>
							</tr>

							<tr>
								<th class="table-light">이름</th>
								<td colspan="3">노종현</td>
							</tr>

							<tr>
								<th class="table-light">주소</th>
								<td colspan="3">강원도 철원시 123-456</td>
							</tr>

							<tr>
								<th class="table-light">전화번호</th>
								<td colspan="3">010-3849-4839</td>
							</tr>

							<tr>
								<th class="table-light">이메일</th>
								<td colspan="3">sdkkj29@gmail.com</td>
							</tr>

							

						</tbody>
					</table>

				


					<h6>입고 처리자 : 박재현 관리자</h6>

				</div>


			</div>

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>