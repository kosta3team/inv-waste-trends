<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 요청 상세조회(사업자)</title>

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

	<main class="signupDetail-page">

		<!-- 제목 -->
		<div class="mb-4">
			<h3 class="fw-bold mb-1">회원가입 요청 상세조회(사업자)</h3>
		</div>

		<div class="signupDetail-table-area">
			<table class="table table-bordered align-middle">
				<tbody>

					<tr>

						<th width="20%" class="table-light">요청일자</th>
						<td>2026-09-20</td>
					</tr>

					<tr>

						<th class="table-light">조합원명</th>
						<td>노종과수원</td>

						<th class="table-light">대표자 이름</th>
						<td>노종현</td>

					</tr>

					<tr>
						<th class="table-light">대표 전화번호</th>
						<td>010-1233-6029</td>

						<th class="table-light">대표 이메일</th>
						<td>dkdkdlw@gmail.com</td>
					</tr>

					<tr>
						<th class="table-light">주소</th>
						<td colspan="3">서울시 관악구 봉천동 55-11</td>
					</tr>

					<tr>
						<th class="table-light">첨부파일</th>
						<td colspan="3">

							<div class="d-flex align-items-center gap-2">

								<i class="bi bi-file-earmark-pdf"></i> <a href="#"
									class="text-decoration-none"> 사업자등록증.pdf </a> <i
									class="bi bi-file-earmark-pdf"></i> <a href="#"
									class="text-decoration-none"> 조합원증명서.pdf </a>

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