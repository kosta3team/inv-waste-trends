<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal fade" id="inventoryDetailModal" tabindex="-1"
	aria-labelledby="inventoryDetailModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">

			<div class="modal-header">
				<div>
					<h5 class="modal-title mb-1" id="inventoryDetailModalTitle">과일상품일련번호
						(${fruitNo}) 상세정보</h5>
					<p class="text-muted mb-0 small">보관 마감날짜 : ${empty inventory.storageDate ? '-' : fn:substring(inventory.storageDate, 0, 10)}</p>
				</div>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>

			<div class="modal-body">

				<c:choose>
					<c:when test="${empty inventory}">
						<p class="text-center my-4">상세정보를 찾을 수 없습니다.</p>
					</c:when>
					<c:otherwise>

						<!-- 상품 정보 -->
						<h6 class="fw-bold section-title">상품 정보</h6>
						<table class="table table-bordered text-center align-middle mb-4">
							<thead class="table-light">
								<tr>
									<th>품목코드</th>
									<th>품목</th>
									<th>품종</th>
									<th>원산지</th>
									<th>상품명</th>
									<th>단가(1box)</th>
									<th>중량(1box)</th>
									<th>입고수량(box)</th>
									<th>재고수량(box)</th>
									<th>판매수량(box)</th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${inventory.itemCode}</td>
									<td>${inventory.itemName}</td>
									<td>${inventory.kindName}</td>
									<td>${inventory.origin}</td>
									<td class="fw-semibold">${inventory.productName}</td>
									<td><fmt:formatNumber value="${inventory.price}"
											pattern="#,###" /> 원</td>
									<td>${inventory.weight}kg</td>
									<td>${inventory.inventoryQuantity}</td>
									<td>${inventory.remainQuantity}</td>
									<td>${saleQuantity}</td>

								</tr>
							</tbody>
						</table>

						<!-- 입고 정보 -->
						<h6 class="fw-bold section-title">입고 정보</h6>
						<table class="table table-bordered align-middle mb-4">
							<tbody>
								<tr>
									<th class="table-light" style="width: 15%;">협동조합원명</th>
									<td style="width: 35%;">${empty inventory.coopName ? '-' : inventory.coopName}</td>
									<th class="table-light" style="width: 15%;">입고요청자</th>
									<td style="width: 35%;">${empty inventory.inventoryMemberName ? '-' : inventory.inventoryMemberName}</td>
								</tr>
								<tr>
									<th class="table-light">입고등록자</th>
									<td colspan="3">${empty inventory.inventoryAdminName ? '-' : inventory.inventoryAdminName}</td>
								</tr>
								<tr>
									<th class="table-light">입고일자</th>
									<td colspan="3">${empty inventory.inventoryDate ? '-' : fn:substring(inventory.inventoryDate, 0, 10)}</td>
								</tr>
							</tbody>
						</table>

						<!-- 폐기 정보 -->
						<h6 class="fw-bold section-title text-danger">폐기 정보</h6>
						<table class="table table-bordered text-center align-middle mb-0">
							<thead class="table-light">
								<tr>
									<th>폐기등록자</th>
									<th>폐기요청자</th>
									<th>폐기수량(box)</th>
									<th>요청일자</th>
									<th>처리일자</th>
									<th>폐기사유</th>
									<th>일강수량</th>
									<th>최고기온</th>
									<th>평균기온</th>
									<th>최저기온</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty inventory.wasteReqDate}">
										<tr>
											<td colspan="10">폐기 정보가 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>${empty inventory.wasteAdminName ? '-' : inventory.wasteAdminName}</td>
											<td>${empty inventory.wasteMemberName ? '-' : inventory.wasteMemberName}</td>
											<td>${inventory.wasteQuantity}box</td>
											<td>${fn:substring(inventory.wasteReqDate, 0, 10)}</td>
											<td>${empty inventory.wasteDate ? '-' : fn:substring(inventory.wasteDate, 0, 10)}</td>
											<td>${empty inventory.wasteReasonDetail ? '-' : inventory.wasteReasonDetail}</td>
											<td>${inventory.dailyRainfall}mm</td>
											<td>${inventory.maxTemp}℃</td>
											<td>${inventory.avgTemp}℃</td>
											<td>${inventory.minTemp}℃</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>

					</c:otherwise>
				</c:choose>

			</div>

		</div>
	</div>
</div>