document.addEventListener('DOMContentLoaded', function () {
    const modalElement = document.getElementById('disposeRequestModal');
    if (!modalElement) return;

    // 모달이 열릴 때 실행되는 이벤트
    modalElement.addEventListener('show.bs.modal', function (event) {
        // 모달을 띄운 버튼 요소 가져오기
        const button = event.relatedTarget;
        if (!button) return;

        // 과일PK 값 추출
        const fruitNo = button.getAttribute('data-fruit-no');

        // XHR 요청 실행 (필요한 경우 cmd 파라미터나 경로를 조합)
        //getWasteRequestQuantity(fruitNo, modalElement);
        //getWasteCategoryList(modalElement);        
        getWasteRequestData(fruitNo, modalElement);
    });
});

/**
 * XMLHttpRequest를 이용한 비동기 폐기가능 수량 얻기 
 */
function getWasteRequestQuantity(fruitNo, modalElement) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
			const maxQuantityInput = modalElement.querySelector('#disposeQuantity')
			if (maxQuantityInput) {
				const maxQuantity = Number(xhr.responseText.trim());
                maxQuantityInput.setAttribute('max', maxQuantity);
                maxQuantityInput.placeholder = `최대 ${maxQuantity}개 가능`;
            }
        }
    };
 	const url = "controller?cmd=getWasteRequestQuantity&fruitNo=" + fruitNo;
    xhr.open("get", url, true);
    xhr.send();
}


function getWasteCategoryList(modalElement) {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const reasonSelect = modalElement.querySelector('#disposeReason');
            if (!reasonSelect) return;

            // 1. 서버에서 넘어온 텍스트 (예: "[WasteCategoryVO [wasteCategoryNo=wc0001, wasteCategoryReason=날씨], ...]")
            const rawText = xhr.responseText;

            // 2. 기존 option 초기화 ('선택' 옵션만 남기기)
            reasonSelect.innerHTML = '<option value="">선택</option>';

            // 3. 정규식을 이용하여 wasteCategoryNo와 wasteCategoryReason 값 추출
            // 패턴 설명: wasteCategoryNo=값, wasteCategoryReason=값
            const regex = /wasteCategoryNo=([^,]+),\s*wasteCategoryReason=([^\]]+)/g;
            let match;

            while ((match = regex.exec(rawText)) !== null) {
                const categoryNo = match[1].trim();     // 예: wc0001
                const categoryReason = match[2].trim(); // 예: 날씨

                // 4. option 태그 생성 및 추가
                const option = document.createElement('option');
                option.value = categoryNo;           // value에 PK(_NO) 설정
                option.textContent = categoryReason; // 화면에 노출될 텍스트

                reasonSelect.appendChild(option);
            }
        }
    };

 	const url = "controller?cmd=getWasteCategoryList";
    xhr.open("get", url, true);
    xhr.send();
}

function getWasteRequestData(fruitNo, modalElement) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
		
			var data = xhr.responseText.trim();
			const parts = data.split(',');
			
			const maxQuantityInput = modalElement.querySelector('#disposeQuantity')
			if (maxQuantityInput) {
				const maxQuantity = parts[0].split('=')[1];
                maxQuantityInput.setAttribute('max', maxQuantity);
                maxQuantityInput.placeholder = `최대 ${maxQuantity}개 가능`;
            }
            
            const reasonSelect = modalElement.querySelector('#disposeReason');
             if (reasonSelect) {			
					reasonSelect.innerHTML = '<option value="">선택</option>';
					for (let i = 1; i < parts.length; i+=2) {
						const option = document.createElement('option');
            	    	option.value = parts[i].split('=')[1];           // value에 PK(_NO) 설정
                		option.textContent = parts[i+1].split('=')[1]; // 화면에 노출될 텍스트
    		            reasonSelect.appendChild(option);
					}
			 }
        }
	}
 	const url = "controller?cmd=getWasteRequestData&fruitNo=" + fruitNo;
    xhr.open("get", url, true);
    xhr.send();
}