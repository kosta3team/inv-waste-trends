<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ko">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>회원가입 요청 상세조회 (개인)</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/inventoryList.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/signupDetail.css">

</head>

<body>

	<%@ include file="adminHeader.jsp"%>

	<main class="signupDetailPersonal-page">

		<!-- 제목 -->
		<div class="mb-4">
			<h3 class="fw-bold mb-1">회원가입 요청 상세조회(개인)</h3>
		</div>

		<div class="signupDetail-table-area">

			<table class="table table-bordered align-middle">

				<tbody>

					<tr>
						<th width="20%" class="table-light">요청일자</th>
						<td>${member.requestDate}</td>
						<th class="table-light">이름</th>
						<td>${member.name}</td>
					</tr>

					<tr>


						<th class="table-light">생년월일</th>
						<td>${member.birth}</td>
						<th class="table-light">전화번호</th>
						<td>${member.phone}</td>
					</tr>

					<tr>


						<th class="table-light">이메일</th>
						<td>${member.email}</td>
						<th class="table-light">주소</th>
						<td colspan="3">${member.address}</td>
					</tr>


					<tr>
						<th class="table-light">첨부파일</th>
						<td colspan="3">

							<div class="d-flex align-items-center gap-2">

								<i class="bi bi-file-earmark-pdf"></i> <a href="#"
									class="text-decoration-none"> ${member.memberFile} </a>

								<button type="button" class="btn btn-sm btn-outline-secondary">
									다운로드</button>

							</div>

						</td>
					</tr>


				</tbody>

			</table>

			<div class="d-flex justify-content-center gap-3 mt-4">

				<form
					action="${pageContext.request.contextPath}/controller?cmd=signupApprove"
					method="post">
					<input type="hidden" name="memberId" value="${member.memberId}">
					<button type="submit" class="btn btn-approve px-5">승인</button>
				</form>

				<form
					action="${pageContext.request.contextPath}/controller?cmd=signupReject"
					method="post">
					
					<input type="hidden" name="memberId" value="${member.memberId}">
					<button type="submit" class="btn btn-reject px-5">거절</button>
				</form>

			</div>

		</div>

	</main>

	<%@ include file="footer.jsp"%>

</body>
</html>