<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조합원 입고요청</title>
<!-- 부트스트랩 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 외부 분리된 CSS 호출 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/request.css">
</head>
<body>
    <%@ include file="../memberHeader.jsp"%>
    
    <!-- 컨트롤러에서 넘겨준 result -->
    <c:if test="${result != null }">
    	<script>
    		alert('${result == true ? "입고 요청이 성공적으로 등록 되었습니다. " : "입고 요청 등록에 실패 했습니다."}')
    		// 등록 완료 후 입고요청목록으로 재 이동
    		location.href = '${pageContext.request.contextPath}/controller?cmd=addFruitProduct';
   		</script>
    </c:if>
    
    <div class="container mt-5 mb-5" style="max-width: 900px;">
    	<h2 class="mb-4 pb-2 border-bottom">입고 요청</h2>
    	    	
    	<form action="${pageContext.request.contextPath}/controller" method="post" id="inventoryForm" onsubmit="return removeCommasBeforeSubmit();">
    		<input type="hidden" name="cmd" value="addFruitProduct">    		    		
    		<input type="hidden" name="actionType" value="submit">
    		
    		<input type="hidden" name="memberId" value="${loginUser.memberId }">
    		
    		<!-- 실제 서버에 올라갈 식별자. -->
    		<input type="hidden" id="fruitCategoryNo" name="fruitCategoryNo">
    		
    		<!-- 품목 시작 -->
    		<div class="card mb-5 shadow-sm">
    			<div class="card-header bg-light d-flex justify-content-between align-items-center">
    				<h5 class="mb-0 fw-bold">상품 정보</h5>
    			</div>
    			
    			<div class="card-body">
    				<!-- 품목 검색 영역 시작 -->
    				<div class="row align-items-center mb-3">
    					<label class="col-sm-2 col-form-label fw-bold">품목 선택</label>
    					
    					<!-- 품목 코드 -->    					
    					<div class="col-sm-3">
    						<input type="text" class="form-control" id="itemCode" name="itemCode" placeholder="예: 1" oninput="applyByCode(this.value)" autocomplete="off">
    					</div>
    					
    					<!-- 품목명 -->
    					<div class="col-sm-3 position-relative">
    						<input type="text" class="form-control" id="itemName" name="itemName" placeholder="예: 딸기" oninput="handleItemNameInput()" autocomplete="off">
    						
    						<!-- 드롭다운 -->
    						<div id="autocompleteResults" class="list-group position-absolute w-100 shadow-sm" style="display:none; z-index: 1050; max-height: 200px; overflow-y: auto; top:100%;">
    						</div>
    					</div>
    					
    					<!-- 품종 -->
    					<div class="col-sm-4">
    						<select class="form-select" id="itemVariety" name="itemVariety" onchange="applyVarietyCode()" disabled>
    							<option value="">품목을 먼저 검색하세요.</option>
    						</select>
    					</div>
    				</div>
    				<!-- 품목 검색 영역 종료 -->
    				
    				<!-- 수량 및 중량 영역 시작 -->
    				<div class="row align-items-center mb-3">
    					<label class="col-sm-2 col-form-label fw-bold">입고수량</label>
    					<div class="col-sm-4">
    						<div class="input-group">
    							<input type="number" class="form-control text-end" id="qty" name="qty" placeholder="0" oninput="calculateTotal()">
    							<span class="input-group-text bg-white">Box</span>
    						</div>
    					</div>
    					
    					<label class="col-sm-2 col-form-label fw-bold text-end">중량(1Box)</label>
    					<div class="col-sm-4">
    						<div class="input-group">
    							<input type="text" class="form-control text-end" id="weight" name="weight" placeholder="0" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
    							<span class="input-group-text bg-white">kg</span>
    						</div>
    					</div>
    				</div>
    				<!-- 수량 및 중량 영역 끝 -->
    				
    				<!-- 단가 및 총판매 금액 영역 시작 -->
    				<div class="row align-items-center">
    					<label class="col-sm-2 col-form-label fw-bold">단가(1Box)</label>
    					<div class="col-sm-4">
    						<div class="input-group">
    							<input type="text" class="form-control text-end" id="unitPrice" name="unitPrice" placeholder="0" oninput="formatAndCalculate(this)">
    							<span class="input-group-text bg-white">원</span>
    						</div>
    					</div>
    					
    					<label class="col-sm-2 col-form-label fw-bold text-end">총 판매예상</label>
    					<div class="col-sm-4">
    						<div class="input-group">
    							<input type="text" class="form-control fw-bold text-primary readonly-field text-end" id="totalPrice" name="totalPrice" placeholder="0" readonly>
    							<span class="input-group-text bg-white">원</span>
    						</div>
    					</div>
    				</div>
    				<!-- 단가 및 총판매 금액 영역 끝 -->
    			</div>
    		</div>    	
    		<!-- 품목 종료 -->
    		
    		<!-- 조합원 시작 -->
    		<div class="card mb-4 shadow-sm">
    			<div class="card-header bg-light">
    				<h5 class="mb-0 fw-bold">조합원 정보</h5>
    			</div>
    			
    			<div class="card-body">
    				<div class="row align-items-center mb-3">
    					<label class="col-sm-2 col-form-label fw-bold">조합원명</label>
    					<div class="col-sm-4">
    						${loginUser.memberName }
    					</div>
    					
    					<label class="col-sm-2 col-form-label fw-bold text-end">이름</label>
    					<div class="col-sm-4">
    						${loginUser.name }
    					</div>
    				</div>
    				
    				<!-- 추후에 로그인한 인원의 주소값으로 가져올 예정 -->
    				<div class="row align-items-center mb-3">
    					<label class="col-sm-2 col-form-label fw-bold">주소</label>
    					<div class="col-sm-10">
    						${loginUser.address } ${loginUser.detailAddress }
    					</div>
    				</div>
    				
    				<div class="row align-items-center mb-3">
    					<label class="col-sm-2 col-form-label fw-bold">휴대폰번호</label>
    					<div class="col-sm-10">
    						${loginUser.phone }
    					</div>
    				</div>
    				
    				<div class="row align-items-center">
    					<label class="col-sm-2 col-form-label fw-bold">이메일</label>
    					<div class="col-sm-10">
    						${loginUser.email }
    					</div>
    				</div>
    			</div>
    		</div>
    		<!-- 조합원 종료 -->
    		
    		<div class="d-flex justify-content-center gap-2 mt-4">
    			<button type="submit" class="btn btn-primary px-4">등록</button>
    			<button type="button" class="btn btn-secondary px-4" onclick="location.href='${pageContext.request.contextPath}/controller';">취소</button>
    		</div>
    	</form>
    </div>
    
    <!-- 스크립트 영역 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    	const kamisDB = [
    		<c:if test="${not empty categoryList}">
	    		<c:forEach var="cat" items="${categoryList}" varStatus="status">
	    		{
	    			categoryNo: '${cat.fruitCategoryNo}',
	    			code: '${cat.itemCode}',
	    			name: '${cat.itemName}',
	    			variety: '${cat.kindName}'
	    		}${!status.last ? ',' : ''}
	    		</c:forEach>
	    	</c:if>
    	];
    </script>
    
    <script src="${pageContext.request.contextPath }/js/addFruitProduct.js"></script>
    
    <%@ include file="/view/footer.jsp"%>
</body>
</html>