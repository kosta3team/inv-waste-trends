/**
 * 
 */
function handleItemNameInput(){
	let inputName = document.getElementById('itemName').value.trim();
	let resultBox = document.getElementById('autocompleteResults');
	
	if (!inputName){
		resultBox.style.display = 'none';
		resetVarietyOriginAndCode(true);
		return;
	}
	
	let uniqueNames = [...new Set(kamisDB.map(item => item.name))];
	let matchedNames = uniqueNames.filter(name => name.includes(inputName));
	
	if (matchedNames.length > 0){
		resultBox.innerHTML = ''; 
		
		matchedNames.forEach(name => {
			let btn = document.createElement('button');
			btn.type = 'button';
			btn.className = 'list-group-item list-group-item-action py-2';
			
			let regex = new RegExp(`(${inputName})`, "gi");
			btn.innerHTML = name.replace(regex, "<strong>$1</strong>");
			
			btn.onclick = function(){
				selectItemName(name);
			};
			resultBox.appendChild(btn);
		});
		resultBox.style.display = 'block'; 
	} else{
		resultBox.style.display = 'none'; 
		resetVarietyOriginAndCode(true);
	}
}


// 품목명 선택
function selectItemName(selectedName){
	document.getElementById('itemName').value = selectedName;
	document.getElementById('autocompleteResults').style.display = 'none';
	populateVarietyDropdown(selectedName);
	resetOriginAndCode(true);
}

// clearCodeInput 파라미터를 통해 직접 입력중일때 코드창 보호
function resetVarietyOriginAndCode(clearCodeInput = true){
	let varietySelect = document.getElementById('itemVariety');
	varietySelect.innerHTML = '<option value="">품목을 먼저 검색하세요</option>';
	varietySelect.disabled = true;
	
	resetOriginAndCode(clearCodeInput);
}

function resetOriginAndCode(clearCodeInput = true){
	
	if (clearCodeInput){
		document.getElementById('itemCode').value = '';	
		document.getElementById('fruitCategoryNo').value = '';
	}
}

document.addEventListener('click', function(e){
	let resultBox = document.getElementById('autocompleteResults');
	let itemNameInput = document.getElementById('itemName');
	if (e.target !== itemNameInput && !resultBox.contains(e.target)){
		resultBox.style.display = 'none';
	}
});

// 품종 드롭다운 옵션 함수
function populateVarietyDropdown(itemName){
	let varietySelect = document.getElementById('itemVariety');
	varietySelect.innerHTML = '<option value="">품종을 선택하세요</option>';
	
	let matchedItems = kamisDB.filter(item => item.name === itemName);
	let uniqueVarieties = [...new Set(matchedItems.map(item => item.variety))];
	
	uniqueVarieties.forEach(variety => {
		let option = document.createElement('option');
		option.value = variety;
		option.text = variety;    			
		varietySelect.appendChild(option);
	});
	
	varietySelect.disabled = false;
}

// 품종 드롭다운
function handleVarietyChange(){
	let itemName = document.getElementById('itemName').value.trim();
	let selectedVariety = document.getElementById('itemVariety').value;
	
	// 품종 선택 취소시 비활성화
	if (!selectedVariety){
		document.getElementById('fruitCategoryNo').value = '';
		return;
	}
	
	// 품목명과 품종명이 일치하는 데이터
	let matchedItem = kamisDB.find(item => item.name === itemName && item.variety === selectedVariety);
	
	if (matchedItem){
		document.getElementById('itemCode').value = matchedItem.code;
		document.getElementById('fruitCategoryNo').value = matchedItem.categoryNo;
	}
	// 원산지 관련은 일단 필요없음

}


// 품목코드 직접 입력시 나머지 자동완성
function applyByCode(){
	// 숫자 이외는 입력방지
	let codeInput = document.getElementById('itemCode'); // 직접 input 태그를 잡는다.
	let rawValue = codeInput.value.replace(/[^0-9]/g, ''); // 숫자만 남기기
	
	if (!rawValue){
		inputElement.value = '';
		document.getElementById('itemName').value = '';
		document.getElementById('fruitCategoryNo').value = '';
		resetVarietyOriginAndCode(false);
		return;
	}
	
	// '001'이나 '0011'같은 형식을 입력해도 1, 11같이 변환 (10진수변환)
	let normalizedCode = String(parseInt(rawValue,10));
	codeInput.value = normalizedCode;
	
	let matchedItem = kamisDB.find(item => item.code === normalizedCode);
	
	if (matchedItem){
		// 품목 명 세팅
		document.getElementById('itemName').value = matchedItem.name;
		
		// 품종 목록 생성 및 선택
		populateVarietyDropdown(matchedItem.name);
		document.getElementById('itemVariety').value = matchedItem.variety;
		    			
		 // CategoryNo 히든 인풋에 삽입
		 document.getElementById('fruitCategoryNo').value = matchedItem.categoryNo;
		 
		// 자동완성 창 닫기    			    			
		document.getElementById('autocompleteResults').style.display = 'none';
	} else{
		document.getElementById('itemName').value = '';
		document.getElementById('fruitCategoryNo').value = '';
		resetVarietyOriginAndCode(false);
	}
}

// 품종 드롭다운 선택 완료 처리 (JSP와 함수 이름 매칭: applyVarietyCode)
function applyVarietyCode() {
    let itemName = document.getElementById('itemName').value.trim();
    let selectedVariety = document.getElementById('itemVariety').value;
    
    // 품종 선택 취소시 비활성화
    if (!selectedVariety) {
        document.getElementById('fruitCategoryNo').value = '';
        return;
    }
    
    // 품목명과 품종명이 일치하는 데이터 찾기
    let matchedItem = kamisDB.find(item => item.name === itemName && item.variety === selectedVariety);
    
    if (matchedItem) {
        document.getElementById('itemCode').value = matchedItem.code;
        document.getElementById('fruitCategoryNo').value = matchedItem.categoryNo;
    }
}

function formatAndCalculate(input){
	let value = input.value.replace(/[^0-9]/g, '');
	input.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	calculateTotal();
}

function calculateTotal(){
	let qty = document.getElementById('qty').value;
	let priceStr = document.getElementById('unitPrice').value.replace(/,/g, '');
	
	let price = parseInt(priceStr) || 0;
	let quantity = parseInt(qty) || 0;
	let total = price * quantity;
	
	document.getElementById('totalPrice').value = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function removeCommasBeforeSubmit() {
    // 1. 진짜 식별자가 비어있는지 확인 (잘못된 입력 방지)
    let categoryNo = document.getElementById('fruitCategoryNo').value;
    if (!categoryNo || categoryNo.trim() === '') {
        alert('정확한 품목과 품종을 선택해주세요.');
        return false; // 폼 제출(서버 전송)을 강제로 중단합니다.
    }

    // 2. 단가, 총금액 콤마 제거
    let unitPrice = document.getElementById('unitPrice');
    let totalPrice = document.getElementById('totalPrice');
    
    if (unitPrice.value) {
        unitPrice.value = unitPrice.value.replace(/,/g, '');
    }
    if (totalPrice.value) {
        totalPrice.value = totalPrice.value.replace(/,/g, '');
    }
    
    return true; // 에러가 없으면 정상 제출
}