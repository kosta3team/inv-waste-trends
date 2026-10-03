<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>최강 ERP 회원가입</title>

<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">



</head>

<body class="bg-light">

	<div class="container">
		<div class="row justify-content-center align-items-center min-vh-100">
			<div class="col-12 col-md-10 col-lg-8">

				<div class="card border-0 shadow-sm">
					<div class="card-body p-4 p-md-5">



						<h2 class="text-center fw-bold mb-2">회원가입</h2>
						<p class="text-center text-muted mb-4">새로운 계정을 만들어보세요.</p>

						<!-- 개인 / 사업자 탭 -->
						<div class="d-flex mb-4 bg-light rounded-4 p-1 shadow-sm">

							<button type="button" id="personalBtn"
								class="btn btn-light flex-fill rounded-4 border fw-bold py-3">
								개인</button>

							<button type="button" id="businessBtn"
								class="btn flex-fill text-secondary fw-bold py-3">사업자</button>

						</div>

						<form>

							<!-- 아이디 -->
							<div class="mb-3">
								<label for="id" class="form-label">아이디 *</label>

								<div class="input-group">
									<input type="text" class="form-control" id="id"
										placeholder="아이디를 입력하세요" required>

									<button type="button" class="btn btn-outline-secondary">
										중복확인</button>
								</div>
							</div>

							<!-- 비밀번호 -->
							<div class="mb-3">
								<label for="password" class="form-label">비밀번호 *</label> <input
									type="password" class="form-control" id="password"
									placeholder="영문, 숫자, 특수문자 중 2가지 이상 조합하여 8자 이상 16자 이하" required
									minlength="8" maxlength="16">
							</div>
							<div id="passwordMessage" class="form-text"></div>

							<!-- 비밀번호 확인 -->
							<div class="mb-3">
								<label for="passwordConfirm" class="form-label"> 비밀번호 확인
									* </label> <input type="password" class="form-control"
									id="passwordConfirm" placeholder="비밀번호를 다시 입력하세요" required>
							</div>

							<div id="passwordConfirmMessage" class="form-text"></div>


							<!-- 조합원명 (사업자만 표시) -->
							<div class="mb-3" id="cooperativeArea" style="display: none;">
								<label for="cooperativeName" class="form-label"> 조합원명 *
								</label> <input type="text" class="form-control" id="cooperativeName"
									placeholder="조합원명을 입력하세요">
							</div>


							<!-- 이름 (개인)-->
							<div class="mb-3">
								<label for="name" id="nameLabel" class="form-label"> 이름
									*</label> <input type="text" class="form-control" id="name"
									placeholder="이름을 입력하세요" required>
							</div>


							<!-- 전화번호 -->
							<div class="mb-3">
								<label for="phone" class="form-label">전화번호 *</label>

								<div class="input-group mb-2">
									<input type="tel" class="form-control" id="phone"
										placeholder="010-1234-5678">

									<button type="button" class="btn btn-outline-primary">
										인증요청</button>
								</div>

								<input type="text" class="form-control" placeholder="인증번호 입력">
							</div>

							<!-- 생년월일 -->
							<div class="mb-3">
								<label for="birth" class="form-label">생년월일 *</label> <input
									type="date" class="form-control" id="birth">
							</div>

							<!-- 주소 -->
							<div class="mb-3">
								<label class="form-label">주소 *</label>

								<div class="input-group mb-2">
									<input type="text" class="form-control" id="address"
										placeholder="주소" readonly>

									<button type="button" id="searchAddressBtn"
										class="btn btn-outline-secondary">주소검색</button>
								</div>

								<input type="text" class="form-control" id="detailAddress"
									placeholder="상세주소 입력">
							</div>


							<!-- 이메일 -->
							<div class="mb-3">
								<label for="email" class="form-label">이메일 *</label>

								<div class="input-group mb-2">
									<input type="email" class="form-control" id="email"
										placeholder="example@email.com" required>

									<button type="button" class="btn btn-outline-primary">
										인증요청</button>
								</div>

								<input type="text" class="form-control" placeholder="인증번호 입력">
							</div>


							<!-- 조합원 증명서 첨부 -->
							<div class="mb-3">
								<label for="file" class="form-label">조합원 증명서 *</label> <input
									type="file" class="form-control" id="file">
							</div>

							<!-- 사업자등록증 (사업자만 표시) -->
							<div class="mb-3" id="businessLicenseArea" style="display: none;">
								<label for="businessLicense" class="form-label"> 사업자 등록증
									* </label> <input type="file" class="form-control" id="businessLicense">
							</div>

							<!-- 약관 -->
							<div class="form-check mb-4">
								<input class="form-check-input" type="checkbox" id="agree"
									required> <label class="form-check-label" for="agree">
									이용약관 및 개인정보 처리방침에 동의합니다. </label>
							</div>

							<!-- 회원가입 -->
							<button type="submit" class="btn btn-primary w-100 py-2">
								회원가입</button>

						</form>

						<div class="text-center mt-4">
							<span class="text-muted">이미 계정이 있으신가요?</span> <a href="/login"
								class="text-decoration-none"> 로그인 </a>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>

	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${pageContext.request.contextPath}/js/signup.js"></script>

	<%@ include file="footer.jsp"%>

</body>
</html>