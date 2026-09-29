<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="inventoryRequestModal" tabindex="-1"
	aria-labelledby="inventoryRequestModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">

			<div class="modal-header">
				<div>
					<h5 class="modal-title mb-1" id="inventoryRequestModalTitle">재고일련번호() 상세정보</h5>
					<p class="text-muted mb-0 small" id="inventoryRequestStorageDeadline">보관일자 : </p>
				</div>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>

			<div class="modal-body">

				<!-- 상품 정보 -->
				<h6 class="fw-bold section-title">상품 정보</h6>
				<table class="table table-bordered text-center align-middle mb-4">
					<thead class="table-light">
						<tr>
							<th>품목코드</th>
							<th>품목</th>
							<th>품종</th>
							<th>주소</th>
							<th>상품명</th>
							<th>단가(1box)</th>
							<th>중량(1box)</th>
							<th>입고수량(1box)</th>
							<th>재고수량(1box)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="detailItemCode"></td>
							<td id="detailCategory"></td>
							<td id="detailVariety"></td>
							<td id="detailOrigin"></td>
							<td id="detailProductName" class="fw-semibold"></td>
							<td id="detailUnitPrice"></td>
							<td id="detailWeight"></td>
							<td id="detailQuantity"></td>
							<td id="detailRemainQuantity"></td>
						</tr>
					</tbody>
				</table>

				<!-- 조합원 정보 -->
				<h6 class="fw-bold section-title">조합원 정보</h6>
				<table class="table table-bordered align-middle mb-4">
					<tbody>
						<tr>
							<th class="table-light" style="width: 15%;">협동조합원명</th>
							<td id="detailCoopName" style="width: 35%;"></td>
							<th class="table-light" style="width: 15%;">입고요청자</th>
							<td id="detailRequester" style="width: 35%;"></td>
						</tr>
						<tr>
							<th class="table-light">입고등록자</th>
							<td id="detailRegistrant" colspan="3"></td>
						</tr>
						<tr>
							<th class="table-light">입고일자</th>
							<td id="detailReceivedDate" colspan="3"></td>
						</tr>
					</tbody>
				</table>


			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			</div>

		</div>
	</div>
</div>
