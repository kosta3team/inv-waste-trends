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
    		<div class="card mb-5 shadow-sm" style="margin-bottom: 40px !important;">
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
    		{code: '001', name: '딸기', variety: '설향'},
    		{code: '002', name: '딸기', variety: '매향'},
    		{code: '003', name: '딸기', variety: '죽향'},
    		{code: '004', name: '사과', variety: '부사'},
    		{code: '005', name: '사과', variety: '홍로'},
    		{code: '006', name: '포도', variety: '샤인머스캣'}
    	];
    	
    	function handleItemNameInput(){
    		let inputName = document.getElementById('itemName').value.trim();
    		let resultBox = document.getElementById('autocompleteResults'); // 오타 수정됨
    		
    		if (!inputName){
    			resultBox.style.display = 'none';
    			resetVarietyAndCode(); // 오타 수정됨
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
    			resetVarietyAndCode();
    		}
    	}
    	
    	
    	function selectItemName(selectedName){
    		document.getElementById('itemName').value = selectedName;
    		document.getElementById('autocompleteResults').style.display = 'none';
    		populateVarietyDropdown(selectedName);
    	}
    	
    	function resetVarietyAndCode(){
    		let varietySelect = document.getElementById('itemVariety');
    		varietySelect.innerHTML = '<option value="">품목을 먼저 검색하세요</option>';
    		varietySelect.disabled = true;
    		document.getElementById('itemCode').value = '';
    	}
    	
    	document.addEventListener('click', function(e){
    		let resultBox = document.getElementById('autocompleteResults');
    		let itemNameInput = document.getElementById('itemName');
    		if (e.target !== itemNameInput && !resultBox.contains(e.target)){
    			resultBox.style.display = 'none';
    		}
    	});
    	
    	function populateVarietyDropdown(itemName){
    		let varietySelect = document.getElementById('itemVariety');
    		varietySelect.innerHTML = '<option value="">품종을 선택하세요</option>'; // 오타 수정됨
    		
    		let varieties = kamisDB.filter(item => item.name === itemName);
    		
    		varieties.forEach(item => {
    			let option = document.createElement('option');
    			option.value = item.variety;
    			option.text = item.variety;
    			option.dataset.code = item.code;
    			varietySelect.appendChild(option);
    		});
    		
    		varietySelect.disabled = false;
    		document.getElementById('itemCode').value = '';
    	}
    	
    	function applyVarietyCode(){
    		let varietySelect = document.getElementById('itemVariety');
    		let selectedOption = varietySelect.options[varietySelect.selectedIndex];
    		
    		if (selectedOption.value !== ""){
    			document.getElementById('itemCode').value = selectedOption.dataset.code;
    		} else {
    			document.getElementById('itemCode').value = '';
    		}
    	}
    	
    	function applyByCode(codeValue){
    		if (!codeValue) return;
    		
    		let formattedCode = codeValue.padStart(3, '0');
    		let matchedItem = kamisDB.find(item => item.code === formattedCode);
    		
    		if (matchedItem){
    			document.getElementById('itemCode').value = matchedItem.code;
    			document.getElementById('itemName').value = matchedItem.name;
    			
    			populateVarietyDropdown(matchedItem.name);
    			document.getElementById('itemVariety').value = matchedItem.variety;
    			document.getElementById('autocompleteResults').style.display = 'none';
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

    	function formatPhoneNumber(input){
    		let val = input.value.replace(/[^0-9]/g, '');
    		let res = '';
    		
    		if (val.length < 4){
    			res = val;
    		} else if (val.length < 7){
    			res = val.substr(0,3) + '-' + val.substr(3);
    		} else if (val.length < 11){
    			res = val.substr(0,3) + '-' + val.substr(3,3) + '-'+ val.substr(6);
    		} else{
    			res = val.substr(0,3) + '-' + val.substr(3,4) + '-' + val.substr(7);
    		} input.value = res;
    	}
    	
    	function execDaumPostcode(){
    		new daum.Postcode({
    			oncomplete: function(data){
    				var addr = data.roadAddress;
    				var extraAddr = '';
    				
    				if (data.bname != '' && /[동|로|기]$/g.test(data.bname)){
    					extraAddr += data.bname;
    				}
    				if (data.buildingName !== '' && data.apartment ==='Y'){
    					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
    				}
    				if (extraAddr !== ''){
    					extraAddr = ' (' + extraAddr + ')'; // 오타 수정됨 (!== -> =)
    				}
    				
                    document.getElementById("address").value = data.zonecode + " " + addr + extraAddr;
    			}
    		}).open();
    	}
    </script>
    <%@ include file="footer.jsp"%>
</body>
</html>