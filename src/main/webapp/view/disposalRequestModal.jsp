<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- 폐기 목록 조회 페이지에서 body 가장 밑에 include 해줘야함 + 폐기요청 버튼시 연동되는 자바스크립트 코드도 필요함.-->

    <!-- 폐기 목록 상세 모달 -->
    <div class="modal fade" id="disposalRequestModal" tabindex="-1" aria-labelledby="disposeModalLabel" aria-hidden="true">
    	<div class="modal-dialog modal-dialog-centered modal-sm">
    		<div class="modal-content">
    			<div class="modal-header bg-light">
    				<h5 class="modal-title fw-bold" id="disposeModalLabel">폐기 요청</h5>
    				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    			</div>
    			
    			<div class="modal-body">
    				<form id="disposeForm" action="${pageContext.request.contextPath }/controller" method="post">
	    				<!-- 서버 전송을 위한 식별자  -->
	    				<input type="hidden" name="cmd" value="requestDispose">
	    				<input type="hidden" name="inventoryId" id="targetInventoryId" value="">
	    				
	    				<div class="mb-3">
	    					<label for="reasonSelect" class="form-label text-secondary mb-1">폐기 사유 선택</label>
	    					<select class="form-select" id="reasonSelect" name="reason">
	    						<option value="제품하자" selected>제품하자</option>
	    						<option value="유통기한경과">유통기한경과</option>
	    						<option value="파손">파손</option>
	    					</select>
	    				</div>
	    				
	    				<div class="mb-3">
	    					<label for="disposeQty" class="form-label text-secondary small mb-1">수량(box)</label>
	    					<input type="number" class="form-control" id="disposeQty" name="quantity" value="5" min="1">
	    				</div>
	    				
	    				<div class="mb-3">
	    					<input type="text" class="form-control" id="disposeDetail" name="detail" value="농약 검출" placeholder="상세 사유 입력">    					
	    				</div>
    				</form>
    			</div>
    			
    			<div class="modal-footer justify-content-center border-0 pt-0">
    				<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
    				<button type="button" class="btn btn-primary px-4" onclick="document.getElementById('disposeForm').submit();">요청</button>
    			</div>
    			
    		</div>
    	</div>
    </div>