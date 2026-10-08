<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-header">
    <h5 class="modal-title mb-0">입고 요청 목록 상세 조회(관리자)</h5>
    <div class="ms-auto d-flex align-items-center gap-3">
        <h5 class="mb-0">요청일자 : ${reqDetail.requestDate}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
    </div>
</div>

<div class="modal-body">
    <h6>상품정보</h6>
    <table class="table table-bordered align-middle mb-4">
        <tbody>
            <tr>
                <th class="table-light">품목코드</th><td>${reqDetail.fruitCategory.itemCode}</td>
                <th class="table-light">품목</th><td>${reqDetail.fruitCategory.itemName}</td>
                <th class="table-light">품종</th><td>${reqDetail.fruitCategory.kindName}</td>
            </tr>
            <tr>
                <th class="table-light">상품명</th><td>${reqDetail.name}</td>
                <th class="table-light">원산지</th><td>${reqDetail.fruitCategory.origin}</td>
                <th class="table-light">단가(1Box)</th><td>${reqDetail.price}원</td>
            </tr>
            <tr>
                <th class="table-light">입고수량(Box)</th><td>${reqDetail.quantity}</td>
                <th class="table-light">중량(1Box)</th><td>${reqDetail.weight}kg</td>
                <th class="table-light">총 판매 예상 금액</th><td class="text-primary fw-bold">${reqDetail.totalPrice}원</td>
            </tr>
        </tbody>
    </table>

    <h6>조합원 정보</h6>
    <table class="table table-bordered align-middle mb-4">
        <tbody>
            <tr><th class="table-light" style="width: 20%;">조합원명</th><td colspan="3">${reqDetail.member.memberName}</td></tr>
            <tr><th class="table-light">이름</th><td colspan="3">${reqDetail.member.name}</td></tr>
            <tr><th class="table-light">주소</th><td colspan="3">${reqDetail.member.address}</td></tr>
            <tr><th class="table-light">전화번호</th><td colspan="3">${reqDetail.member.phone}</td></tr>
            <tr><th class="table-light">이메일</th><td colspan="3">${reqDetail.member.email}</td></tr>
        </tbody>
    </table>
    
    <c:if test="${not empty reqDetail.admin.name}">
        <h6 class="text-end text-muted mt-3">입고 처리자 : ${reqDetail.admin.name} 관리자</h6>
    </c:if>
</div>

<div class="modal-footer">
    <!-- 상태가 '입고요청'일 때만 승인/거절 폼 노출 (선택사항) -->
    <c:if test="${reqDetail.status == '입고요청'}">
        
        <!-- 1. 승인 버튼 폼 -->
        <form action="${pageContext.request.contextPath}/controller" method="post" class="d-inline m-0 ms-2">
            <input type="hidden" name="cmd" value="approveFruitProduct">
            <input type="hidden" name="fruitNo" value="${param.fruitNo}">
            <button type="submit" class="btn btn-primary px-4">승인</button>
        </form>

        <!-- 2. 거절 버튼 폼 -->
        <form action="${pageContext.request.contextPath}/controller" method="post" class="d-inline m-0 ms-2">
            <input type="hidden" name="cmd" value="rejectFruitProduct">
            <input type="hidden" name="fruitNo" value="${param.fruitNo}">
            <button type="submit" class="btn btn-danger px-4">거절</button>
        </form>
        
    </c:if>

    <!-- 3. 모달 닫기 버튼 -->
    <button type="button" class="btn btn-secondary px-4 ms-2" data-bs-dismiss="modal">닫기</button>
</div>