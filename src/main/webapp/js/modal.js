// 현재 날짜 표시
const today = new Date();

const year = today.getFullYear();
const month = String(today.getMonth() + 1).padStart(2, '0');
const day = String(today.getDate()).padStart(2, '0');

document.getElementById("currentDate").textContent = `${year}-${month}-${day}`;

// 재고 상세정보 모달 채우기
// tr의 data-* 속성값이 모달 내용 (추후 DB 연동 시 data-* 값만 실제 데이터로 교체하기)
function showInventoryDetail(row) {
	const d = row.dataset;

	document.getElementById("inventoryDetailModalTitle").textContent =
		`재고일련번호(${d.serial}) 상세정보`;
	document.getElementById("inventoryDetailStorageDeadline").textContent =
		`보관 일자 : ${d.storageDeadline}`;

	document.getElementById("detailItemCode").textContent = d.itemCode;
	document.getElementById("detailCategory").textContent = d.category;
	document.getElementById("detailVariety").textContent = d.variety;
	document.getElementById("detailOrigin").textContent = d.origin;
	document.getElementById("detailProductName").textContent = d.productName;
	document.getElementById("detailUnitPrice").textContent = d.unitPrice;
	document.getElementById("detailWeight").textContent = d.weight;
	document.getElementById("detailQuantity").textContent = d.quantity;
	document.getElementById("detailRemainQuantity").textContent = d.remainQuantity;
	

	document.getElementById("detailCoopName").textContent = d.coopName;
	document.getElementById("detailRequester").textContent = d.requester;
	document.getElementById("detailRegistrant").textContent = d.registrant;
	document.getElementById("detailReceivedDate").textContent = d.receivedDate;

	document.getElementById("detailDisposeRegistrant").textContent = d.disposeRegistrant;
	document.getElementById("detailDisposeRequester").textContent = d.disposeRequester;
	document.getElementById("detailRequestDate").textContent = d.requestDate;
	document.getElementById("detailDisposeDate").textContent = d.disposeDate;
	document.getElementById("detailDisposeReason").textContent = d.disposeReason;
	document.getElementById("detailPrecipitation").textContent = d.precipitation;
	document.getElementById("detailHighestTemp").textContent = d.highestTemp;
	document.getElementById("detailAverageTemp").textContent = d.averageTemp;
	document.getElementById("detailLowestTemp").textContent = d.lowestTemp;





	const modal = new bootstrap.Modal(document.getElementById("inventoryDetailModal"));
	modal.show();
}
