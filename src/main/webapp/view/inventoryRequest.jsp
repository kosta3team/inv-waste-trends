<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고요청</title>
<!-- 부트스트랩 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 외부 분리된 CSS 호출 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/request.css">
</head>
<body>
    <jsp:include page="memberHeader.jsp"/>
    
    <div class="container mt-5 mb-5" style="max-width: 900px;">
    	<h2 class="mb-4 pb-2 border-bottom">입고 요청</h2>
    	    	
    	<form action="FrontControllerServlet" method="post" id="inventoryForm" onsubmit="return removeCommasBeforeSubmit();">
    		<input type="hidden" name="cmd" value="inventoryRequestProcess">
    		
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
    						포천과수원
    					</div>
    					
    					<label class="col-sm-2 col-form-label fw-bold text-end">이름</label>
    					<div class="col-sm-4">
    						노종현
    					</div>
    				</div>
    				
    				<!-- 추후에 로그인한 인원의 주소값으로 가져올 예정 -->
    				<div class="row align-items-center mb-3">
    					<label class="col-sm-2 col-form-label fw-bold">주소</label>
    					<div class="col-sm-10">
    						경기도 포천시 초가팔리 348-26
    					</div>
    				</div>
    				
    				<div class="row align-items-center mb-3">
    					<label class="col-sm-2 col-form-label fw-bold">휴대폰번호</label>
    					<div class="col-sm-10">
    						010-1111-2222
    					</div>
    				</div>
    				
    				<div class="row align-items-center">
    					<label class="col-sm-2 col-form-label fw-bold">이메일</label>
    					<div class="col-sm-10">
    						nojongfruit@gmail.com
    					</div>
    				</div>
    			</div>
    		</div>
    		<!-- 조합원 종료 -->
    		
    		<div class="d-flex justify-content-center gap-2 mt-4">
    			<button type="button" class="btn btn-primary px-4" onclick="return false;">등록</button>
    			<button type="button" class="btn btn-secondary px-4" onclick="location.href='${pageContext.request.contextPath}/controller';">취소</button>
    		</div>
    	</form>
    </div>
    
    <!-- 스크립트 영역 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script>
	    const kamisDB = [
			// 1. 사과
			{code: '1', name: '사과', variety: '후지(부사)', origin: '경기'},
			{code: '2', name: '사과', variety: '후지(부사)', origin: '강원'},
			{code: '3', name: '사과', variety: '후지(부사)', origin: '충북'},
			{code: '4', name: '사과', variety: '후지(부사)', origin: '충남'},
			{code: '5', name: '사과', variety: '후지(부사)', origin: '경북'},
			{code: '6', name: '사과', variety: '후지(부사)', origin: '경남'},
			{code: '7', name: '사과', variety: '후지(부사)', origin: '전북'},
			{code: '8', name: '사과', variety: '홍로', origin: '강원'},
			{code: '9', name: '사과', variety: '홍로', origin: '충북'},
			{code: '10', name: '사과', variety: '홍로', origin: '경북'},
			{code: '11', name: '사과', variety: '홍로', origin: '전북'},
			{code: '12', name: '사과', variety: '쓰가루(아오리)', origin: '경북'},
			{code: '13', name: '사과', variety: '쓰가루(아오리)', origin: '경남'},
			{code: '14', name: '사과', variety: '감홍', origin: '강원'},
			{code: '15', name: '사과', variety: '감홍', origin: '경북'},
	
			// 2. 배
			{code: '16', name: '배', variety: '신고', origin: '서울'},
			{code: '17', name: '배', variety: '신고', origin: '경기'},
			{code: '18', name: '배', variety: '신고', origin: '충남'},
			{code: '19', name: '배', variety: '신고', origin: '경북'},
			{code: '20', name: '배', variety: '신고', origin: '전남'},
			{code: '21', name: '배', variety: '원황', origin: '경기'},
			{code: '22', name: '배', variety: '원황', origin: '충남'},
			{code: '23', name: '배', variety: '원황', origin: '전남'},
			{code: '24', name: '배', variety: '화산', origin: '충남'},
			{code: '25', name: '배', variety: '화산', origin: '전남'},
	
			// 3. 복숭아
			{code: '26', name: '복숭아', variety: '백도', origin: '경기'},
			{code: '27', name: '복숭아', variety: '백도', origin: '강원'},
			{code: '28', name: '복숭아', variety: '백도', origin: '충북'},
			{code: '29', name: '복숭아', variety: '백도', origin: '경북'},
			{code: '30', name: '복숭아', variety: '백도', origin: '전북'},
			{code: '31', name: '복숭아', variety: '황도', origin: '경기'},
			{code: '32', name: '복숭아', variety: '황도', origin: '충북'},
			{code: '33', name: '복숭아', variety: '황도', origin: '경북'},
			{code: '34', name: '복숭아', variety: '천중도', origin: '충북'},
			{code: '35', name: '복숭아', variety: '천중도', origin: '경북'},
	
			// 4. 포도
			{code: '36', name: '포도', variety: '샤인머스캣', origin: '경기'},
			{code: '37', name: '포도', variety: '샤인머스캣', origin: '충북'},
			{code: '38', name: '포도', variety: '샤인머스캣', origin: '충남'},
			{code: '39', name: '포도', variety: '샤인머스캣', origin: '경북'},
			{code: '40', name: '포도', variety: '캠벨얼리', origin: '경기'},
			{code: '41', name: '포도', variety: '캠벨얼리', origin: '충북'},
			{code: '42', name: '포도', variety: '캠벨얼리', origin: '경북'},
			{code: '43', name: '포도', variety: '거봉', origin: '경기'},
			{code: '44', name: '포도', variety: '거봉', origin: '충남'},
			{code: '45', name: '포도', variety: '거봉', origin: '경북'},
	
			// 5. 감귤
			{code: '46', name: '감귤', variety: '노지감귤', origin: '제주'},
			{code: '47', name: '감귤', variety: '시설감귤', origin: '제주'},
			{code: '48', name: '감귤', variety: '한라봉', origin: '전남'},
			{code: '49', name: '감귤', variety: '한라봉', origin: '제주'},
			{code: '50', name: '감귤', variety: '천혜향', origin: '전남'},
			{code: '51', name: '감귤', variety: '천혜향', origin: '제주'},
			{code: '52', name: '감귤', variety: '레드향', origin: '제주'},
	
			// 6. 단감
			{code: '53', name: '단감', variety: '부유', origin: '경북'},
			{code: '54', name: '단감', variety: '부유', origin: '경남'},
			{code: '55', name: '단감', variety: '부유', origin: '전남'},
			{code: '56', name: '단감', variety: '태추', origin: '경남'},
			{code: '57', name: '단감', variety: '태추', origin: '전남'},
	
			// 7. 참다래(키위)
			{code: '58', name: '참다래', variety: '헤이워드(그린)', origin: '경남'},
			{code: '59', name: '참다래', variety: '헤이워드(그린)', origin: '전남'},
			{code: '60', name: '참다래', variety: '헤이워드(그린)', origin: '제주'},
			{code: '61', name: '참다래', variety: '해금(골드)', origin: '전남'},
			{code: '62', name: '참다래', variety: '해금(골드)', origin: '제주'},
	
			// 8. 딸기
			{code: '63', name: '딸기', variety: '설향', origin: '서울'},
			{code: '64', name: '딸기', variety: '설향', origin: '경기'},
			{code: '65', name: '딸기', variety: '설향', origin: '충남'},
			{code: '66', name: '딸기', variety: '설향', origin: '경남'},
			{code: '67', name: '딸기', variety: '설향', origin: '전북'},
			{code: '68', name: '딸기', variety: '설향', origin: '전남'},
			{code: '69', name: '딸기', variety: '매향', origin: '충남'},
			{code: '70', name: '딸기', variety: '매향', origin: '경남'},
			{code: '71', name: '딸기', variety: '죽향', origin: '전남'},
			{code: '72', name: '딸기', variety: '킹스베리', origin: '충남'},
	
			// 9. 방울토마토
			{code: '73', name: '방울토마토', variety: '원형방울토마토', origin: '경기'},
			{code: '74', name: '방울토마토', variety: '원형방울토마토', origin: '충남'},
			{code: '75', name: '방울토마토', variety: '원형방울토마토', origin: '전남'},
			{code: '76', name: '방울토마토', variety: '대추방울토마토', origin: '강원'},
			{code: '77', name: '방울토마토', variety: '대추방울토마토', origin: '충남'},
			{code: '78', name: '방울토마토', variety: '대추방울토마토', origin: '경남'},
			{code: '79', name: '방울토마토', variety: '대추방울토마토', origin: '전북'},
	
			// 10. 수박 & 참외
			{code: '80', name: '수박', variety: '흑미수박', origin: '충북'},
			{code: '81', name: '수박', variety: '흑미수박', origin: '전북'},
			{code: '82', name: '수박', variety: '복수박', origin: '충남'},
			{code: '83', name: '수박', variety: '복수박', origin: '경남'},
			{code: '84', name: '참외', variety: '금싸라기', origin: '경북'}
		];
    	
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
    		let originSelect = document.getElementById('itemOrigin');
    		originSelect.innerHTML = '<option value="">품종을 먼저 선택하세요.</option>';
    		originSelect.disabled = true;
    		
    		if (clearCodeInput){
    			document.getElementById('itemCode').value = '';	
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
    	
    	function handleVarietyChange(){
    		let itemName = document.getElementById('itemName').value.trim();
    		let selectedVariety = document.getElementById('itemVariety').value;
    		
    		// 품종 선택 취소시 비활성화
    		if (!selectedVariety){
    			resetOriginAndCode(true);
    			return;
    		}
    		
    		populateOriginDropdown(itemName, selectedVariety);
    		document.getElementById('itemCode').value= '';
    	}
    	
    	function populateOriginDropdown (itemName, varietyName){
    		let originSelect = document.getElementById('itemOrigin');
    		originSelect.innerHTML = '<option value="">원산지를 선택하세요</option>';
    		
    		let matchedOrigins = kamisDB.filter(
   				item => item.name === itemName && item.variety === varietyName
			);
    		
    		matchedOrigins.forEach(item => {
    			let option = document.createElement('option');
    			option.value =  item.origin;
    			option.text = item.origin;
    			option.dataset.code = item.code; // 최종 단계인 원산지 옵션에 품목 코드를 매핑
    			originSelect.appendChild(option);
    		});
    		
    		originSelect.disabled = false;    		
    	}
    	
    	// 원산지 선택시 최종 품목 코드 자동 세팅
    	function applyOriginCode(){
    		let originSelect = document.getElementById('itemOrigin');
    		let selectedOption = originSelect.options[originSelect.selectedIndex];
    		
    		if (selectedOption && selectedOption.value !== ""){
    			document.getElementById('itemCode').value = selectedOption.dataset.code;
    		} else {
    			document.getElementById('itemCode').value = '';
    		}
    	}
    	// 품목코드 직접 입력시 나머지 자동완성
    	function applyByCode(inputElement){
    		// 숫자 이외는 입력방지
    		let rawValue = inputElement.value.replace(/[^0-9]/g, '');
    		
    		if (!rawValue){
    			inputElement.value = '';
    			document.getElementById('itemName').value = '';
    			resetVarietyOriginAndCode(false);
    			return;
    		}
    		
    		// '001'이나 '0011'같은 형식을 입력해도 1, 11같이 변환 (10진수변환)
    		let normalizedCode = String(parseInt(rawValue,10));
    		inputElement.value = normalizedCode;
    		
    		let matchedItem = kamisDB.find(item => item.code === normalizedCode);
    		
    		if (matchedItem){
    			// 품목 명 세팅
    			document.getElementById('itemName').value = matchedItem.name;
    			
    			// 품종 목록 생성 및 선택
    			populateVarietyDropdown(matchedItem.name);
    			document.getElementById('itemVariety').value = matchedItem.variety;
    			
    			// 원산지 목록 생성 및 선택
    			populateOriginDropdown(matchedItem.name, matchedItem.variety);
    			document.getElementById('itemOrigin').value = matchedItem.origin;
    			    			
    			// 자동완성 창 닫기    			    			
    			document.getElementById('autocompleteResults').style.display = 'none';
    		} else{
    			document.getElementById('itemName').value = '';
    			resetVarietyOriginAndCode(false);
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
            let unitPrice = document.getElementById('unitPrice');
            let totalPrice = document.getElementById('totalPrice');
            
            unitPrice.value = unitPrice.value.replace(/,/g, '');
            totalPrice.value = totalPrice.value.replace(/,/g, '');
            return true;
        }
    </script>
    <%@ include file="footer.jsp"%>
</body>
</html>