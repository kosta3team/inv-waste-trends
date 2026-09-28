<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 요청 상세조회</title>

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

	<main class="signupDetail-page">

		<!-- 제목 -->
		<div class="mb-4">
			<h3 class="fw-bold mb-1">회원가입 요청 상세조회(개인)</h3>
		</div>

		<div class="signupDetail-table-area">
			<table class="table table-bordered align-middle">
				<tbody>

					<tr>
						<th width="20%" class="table-light">요청 일련번호</th>
						<td>A00014</td>

						<th width="20%" class="table-light">요청일자</th>
						<td>2026.09.21</td>
					</tr>

					<tr>
						<th class="table-light">이름</th>
						<td>박재현</td>

						<th class="table-light">생년월일</th>
						<td>2001.02.07</td>
					</tr>

					<tr>
						<th class="table-light">전화번호</th>
						<td>010-7413-5894</td>

						<th class="table-light">이메일</th>
						<td>rdkskj75@gmail.com</td>
					</tr>

					<tr>
						<th class="table-light">주소</th>
						<td colspan="3">전남 해남군 해남읍 해리 123-55</td>
					</tr>

					<tr>
						<th class="table-light">첨부파일</th>
						<td colspan="3">

							<div class="d-flex align-items-center gap-2">

								<i class="bi bi-file-earmark-pdf"></i> <a href="#"
									class="text-decoration-none"> 조합원증명서.pdf </a>

								<button type="button" class="btn btn-sm btn-outline-secondary">
									다운로드</button>

							</div>

						</td>
					</tr>

					<tr>
						<th class="table-light">거절사유</th>
						<td colspan="3"><textarea class="form-control" rows="5"
								placeholder="거절 시 사유를 입력하세요."></textarea></td>
					</tr>

				</tbody>
			</table>


			<div class="d-flex justify-content-center gap-3 mt-4">

				<button type="button" class="btn btn-success px-5">승인</button>

				<button type="button" class="btn btn-danger px-5">거절</button>


			</div>

		</div>



	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>