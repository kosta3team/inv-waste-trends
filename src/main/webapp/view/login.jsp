<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>최강ERP - 로그인</title>
    
    <!-- 공유해주신 부트스트랩 및 CSS 적용 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/wasteTrends.css">
</head>
<body>
	<!-- 필요할경우 비로그인 header 생성후 연결영역 -->
	
	<!-- 로그인 폼 컨테이너 시작 -->
	<div class="container mt-5" style="max-width: 450px;">
		<h2 class="text-center mb-4 fw-bold">로그인</h2>
		
		<form action="${pageContext.request.contextPath }/controller" method="post" class="border p-4 rounded bg-white shadow-sm">
			<!-- ActionFactory에서 인식하도록 cmd 전송 / 화면에서는 숨기고 데이터 전송 -->
			<input type="hidden" name="cmd" value="login">
			
			<!-- 분류 선택영역 시작 -->
			<div class="mb-4 d-flex justify-content-center gap-4">
				<div class="form-check">
					<label class="form-check-label" for="typeMember">조합원</label>
					<input class="form-check-input" type="radio" name="userType" id="typeMember" value="member" checked>
				</div>
				
				<div class="form-check">
					<label class="form-check-label" for="typeAdmin">관리자</label>
					<input class="form-check-input" type="radio" name="userType" id="typeAdmin" value="admin">
				</div>
			</div>
			<!-- 분류 선택영역 끝 -->
			
			<!-- ID, PW 입력 영역 시작 -->
			<div class="mb-3">
				<label for="userId" class="form-label text-secondary">ID</label>
				<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력해주세요." required>
			</div>
			<div class="mb-3">
				<label for="userPw" class="form-label text-secondary">PW</label>
				<input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호를 입력해주세요." required>
			</div>
			<!-- ID, PW 입력 영역 끝 -->
			
			<c:if test="${not empty errorMessage }">
				<div class="mb-3 text-danger small">
					<i class="bi bi-exclamation-circle"></i> ${errorMessage }
				</div>
			</c:if>
			
			<button type="submit" class="btn btn-primary w-100 py-2 my-3 fw-bold">로그인</button>
			
			<div class="text-center mt-3">
				<a href="${pageContext.request.contextPath }/controller?cmd=join" class="text-decoration-none text-muted small">회원가입</a>
			</div>
		</form>
	</div>
	<!-- 로그인 폼 컨테이너 끝 -->
	
	<!-- 문의처 -->
	<div class="text-center pb-4 pt-3">
		<span class="text-secondary small">문의: </span>
		<a href="tel:010-1111-2222" class="text-decoration-none text-secondary fw-bold small">010-1111-2222</a>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>