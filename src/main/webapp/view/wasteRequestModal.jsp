<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="disposeRequestModal" tabindex="-1" aria-labelledby="disposeRequestModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">

			<!-- 헤더 -->
			<div class="modal-header">
				<h5 class="modal-title" id="disposeRequestModalTitle">폐기 요청</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>

			<!-- 바디 -->
			<div class="modal-body">
				<!-- 카테고리 표시 영역 -->
				<div class="mb-3">
					<label class="form-label fw-bold">카테고리:</label>
					<span id="modalCategoryArea" class="text-muted">-</span>
				</div>

				<!-- 폐기 사유 -->
				<div class="mb-3">
					<label for="disposeReason" class="form-label">폐기 사유</label> 
					<select class="form-select" id="disposeReason">
						<option value="">선택</option>
						<option value="PRODUCT_DAMAGE">상품 훼손</option>
						<option value="QUALITY_DETERIORATION">품질 저하</option>
						<option value="PESTICIDE_DETECTION">농약 검출</option>
						<option value="EXPIRATION">보관기한 초과</option>
						<option value="OTHER">기타</option>
					</select>
				</div>

				<!-- 폐기 수량 -->
				<div class="mb-3">
					<label for="disposeQuantity" class="form-label">수량(box)</label> 
					<input type="number" class="form-control" id="disposeQuantity" min="1" placeholder="폐기할 수량을 입력하세요">
				</div>

				<!-- 폐기 상세 사유 -->
				<div class="mb-3">
					<label for="fruitCondition" class="form-label">폐기 상세 사유</label><br>
					<textarea class="form-control" id="fruitCondition" rows="3" placeholder="과일 상태 정보를 입력하세요"></textarea>
				</div>
			</div>

			<!-- 푸터 -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="disposeRequestBtn">요청</button>
			</div>
		</div>
	</div>
</div>

<script>
	let fruitId = "";

	// 모달이 열릴 때 호출
	const disposeRequestModal = document.querySelector('#disposeRequestModal');
	disposeRequestModal.addEventListener('show.bs.modal', function(event) {
		const button = event.relatedTarget;

		// 호출한 버튼의 data 속성 읽기
		fruitId = button.getAttribute('data-fruit-no') || "";
		const categoryName = button.getAttribute('data-category-name') || "-"; // 필요시 카테고리명 전달받음

		// 모달 내 텍스트 및 입력값 초기화
		document.querySelector('#modalCategoryArea').textContent = categoryName;
		document.querySelector('#disposeReason').value = "";
		document.querySelector('#disposeQuantity').value = "";
		document.querySelector('#fruitCondition').value = "";
	});

	// 요청 버튼 클릭 이벤트
	function requestButtonClickEvent() {
		const disposeReason = document.querySelector('#disposeReason').value;
		const disposeQuantity = document.querySelector('#disposeQuantity').value;
		const fruitCondition = document.querySelector('#fruitCondition').value;

		// 간단한 유효성 검사
		if (!disposeReason) {
			alert("폐기 사유를 선택하세요.");
			return;
		}
		if (!disposeQuantity || disposeQuantity <= 0) {
			alert("올바른 수량을 입력하세요.");
			return;
		}

		// AJAX 객체 생성
		const xhr = new XMLHttpRequest();
		
		// URL 인코딩 처리 (한글 입력 대비)
		const params = "cmd=requestWaste"
			+ "&fruitId=" + fruitId
			+ "&disposeReason=" + disposeReason
			+ "&disposeQuantity=" + disposeQuantity
			+ "&fruitCondition=" + encodeURIComponent(fruitCondition);

		const url = "controller?" + params;

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					alert("폐기 요청이 정상적으로 처리되었습니다.");
					// 성공 시 리다이렉트 또는 페이지 새로고침
					location.href = "controller?cmd=memberInventoryList";
				} else {
					alert("처리 중 오류가 발생했습니다.");
				}
			}
		};

		xhr.open("GET", url, true);
		xhr.send();
	}

	const requestButton = document.querySelector('#disposeRequestBtn');
	if (requestButton) {
		requestButton.addEventListener('click', requestButtonClickEvent);
	}
</script>