/**
 * 
 */
function openDetailModalAdmin(fruitNo){
	// 관리자는 cmd=getFruitProduct
	const url = contextPath + '/controller?cmd=getDetailFruitProduct&fruitNo=' + fruitNo;
	
	fetch(url)
		.then(response => response.text())
		.then(html => {
			// 모달 겁데기 안에서 백엔드화면
			document.getElementById('modalContentArea').innerHTML = html;
			
			// 부트스트랩 모달
			const modalElement = document.getElementById('requestDetailModal');
			const modalInstance = bootstrap.Modal.getOrCreateInstance(modalElement);
			modalInstance.show();
		})
		.catch(error => {
			alert('상세 정보를 불러오는 데 실패했습니다.');
			console.error(error);
		});
}

function openDetailModalMember(fruitNo){
	// 조합원은 cmd=getMyFruitProduct
	const url = contextPath + '/controller?cmd=getDetailMyFruitProduct&fruitNo=' + fruitNo;
	
	fetch(url)
		.then(response => response.text())
		.then(html => {
			// 모달 겁데기 안에서 백엔드화면
			document.getElementById('modalContentArea').innerHTML = html;
			
			// 부트스트랩 모달
			const modalElement = document.getElementById('requestDetailModal');
			const modalInstance = bootstrap.Modal.getOrCreateInstance(modalElement);
			modalInstance.show();
		})
		.catch(error => {
			alert('상세 정보를 불러오는 데 실패했습니다.');
			console.error(error);
		});
}