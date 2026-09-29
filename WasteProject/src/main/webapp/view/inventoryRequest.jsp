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
        
        <form action="FrontControllerServlet" method="post" id="inventoryForm">
            <input type="hidden" name="cmd" value="inventoryRequestProcess">
            
            <div class="card mb-5 shadow-sm" style="margin-bottom: 40px !important;">
			    <div class="card-header bg-light d-flex justify-content-between align-items-center">
			        <h5 class="mb-0 fw-bold">상품 정보</h5>
			    </div>
			    <div class="card-body">
                    <div class="row align-items-center mb-3">
                        <label class="col-sm-2 col-form-label fw-bold">품목 선택</label>
                        
                        <!-- 1. 품목코드 (직접 입력 시 연동) -->
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="itemCode" name="itemCode" placeholder="품목코드 (예: 1)" oninput="applyByCode(this.value)" autocomplete="off">
                        </div>
                        
                        <!-- 2. 품목명 (커스텀 자동완성 적용) -->
                        <div class="col-sm-3 position-relative">
                            <input type="text" class="form-control" id="itemName" name="itemName" placeholder="품목명 입력 (예: 딸기)" oninput="handleItemNameInput()" autocomplete="off">
                            
                            <!-- 커스텀 드롭다운 목록이 렌더링될 레이어 -->
                            <div id="autocompleteResults" class="list-group position-absolute w-100 shadow-sm" style="display:none; z-index: 1050; max-height: 200px; overflow-y: auto; top: 100%;">
                            </div>
                        </div>
                        
                        <!-- 3. 품종 (품목명이 정확히 입력/선택되면 활성화됨) -->
                        <div class="col-sm-4">
                            <select class="form-select" id="itemVariety" name="itemVariety" onchange="applyVarietyCode()" disabled>
                                <option value="">품목을 먼저 검색하세요</option>
                            </select>
                        </div>
                    </div>
                    
                    <!-- 수량 및 중량 -->
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
					            <input type="text" class="form-control text-end" name="weight" id="weight" placeholder="0" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
					            <span class="input-group-text bg-white">kg</span>
					        </div>
					    </div>
					</div>
					
					<!-- 단가 및 총판매예상금액 -->
					<div class="row align-items-center">
			            <label class="col-sm-2 col-form-label fw-bold">단가(1box)</label>
			            <div class="col-sm-4">
			                <div class="input-group">
			                    <input type="text" class="form-control text-end" id="unitPrice" name="unitPrice" placeholder="0" oninput="formatAndCalculate(this)">
			                    <span class="input-group-text bg-white">원</span>
			                </div>
			            </div>
			            <label class="col-sm-2 col-form-label fw-bold text-end">총판매예상</label>
			            <div class="col-sm-4">
			                <div class="input-group">
			                    <input type="text" class="form-control fw-bold text-primary readonly-field text-end" id="totalPrice" name="totalPrice" placeholder="0" readonly>
			                    <!-- 2. bg-light를 bg-white로 변경하여 흰색으로 통일 -->
			                    <span class="input-group-text bg-white">원</span>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			
			<!-- 조합원 시작 -->
            <div class="card mb-4 shadow-sm">
                <div class="card-header bg-light">
                    <h5 class="mb-0 fw-bold">조합원 정보</h5>
                </div>
                <div class="card-body">
                    <div class="row align-items-center mb-3">
                        <label class="col-sm-2 col-form-label fw-bold">조합원명</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="companyName" placeholder="조합원(회사)명 입력">
                        </div>
                        <label class="col-sm-2 col-form-label fw-bold text-end">이름</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="memberName" placeholder="대표자/개인 이름 입력">
                        </div>
                    </div>
                    <div class="row align-items-center mb-3">
                        <label class="col-sm-2 col-form-label fw-bold">주소</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control cursor-pointer" id="address" name="address" placeholder="클릭하여 주소 검색" onclick="execDaumPostcode()" readonly>
                        </div>
                    </div>
                    <div class="row align-items-center mb-3">
                        <label class="col-sm-2 col-form-label fw-bold">휴대폰번호</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="phone" name="phone" placeholder="숫자만 입력하세요" oninput="formatPhoneNumber(this)" maxlength="13">
                        </div>
                    </div>
                    <div class="row align-items-center">
                        <label class="col-sm-2 col-form-label fw-bold">이메일</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" placeholder="이메일 입력">
                        </div>
                    </div>              
                </div>
            </div>
            
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
            { code: '001', name: '딸기', variety: '설향' },
            { code: '002', name: '딸기', variety: '매향' },
            { code: '003', name: '딸기', variety: '죽향' },
            { code: '004', name: '사과', variety: '부사' },
            { code: '005', name: '사과', variety: '홍로' },
            { code: '006', name: '포도', variety: '샤인머스캣' }
        ];

        // 1. 커스텀 자동완성 입력 감지 함수
        function handleItemNameInput() {
            let inputName = document.getElementById('itemName').value.trim();
            let resultBox = document.getElementById('autocompleteResults');
            
            if (!inputName) {
                resultBox.style.display = 'none';
                resetVarietyAndCode();
                return;
            }

            // 고유한 품목명만 추출 (딸기가 여러 개여도 중복 제거)
            let uniqueNames = [...new Set(kamisDB.map(item => item.name))];
            
            // LIKE 검색 필터링
            let matchedNames = uniqueNames.filter(name => name.includes(inputName));

            if (matchedNames.length > 0) {
                resultBox.innerHTML = ''; // 기존 목록 초기화
                
                matchedNames.forEach(name => {
                    let btn = document.createElement('button');
                    btn.type = 'button';
                    btn.className = 'list-group-item list-group-item-action py-2';
                    
                    // 입력한 키워드 부분을 굵게(bold) 처리
                    let regex = new RegExp(`(${inputName})`, "gi");
                    btn.innerHTML = name.replace(regex, "<strong>$1</strong>");
                    
                    // 목록 클릭 시 동작
                    btn.onclick = function() {
                        selectItemName(name);
                    };
                    resultBox.appendChild(btn);
                });
                resultBox.style.display = 'block'; // 드롭다운 표시
            } else {
                resultBox.style.display = 'none'; // 매칭 없으면 숨김
                resetVarietyAndCode();
            }
        }

        // 2. 자동완성 목록에서 항목을 선택했을 때
        function selectItemName(selectedName) {
            document.getElementById('itemName').value = selectedName;
            document.getElementById('autocompleteResults').style.display = 'none';
            populateVarietyDropdown(selectedName);
        }

        // 품종 및 코드 초기화 공통 함수
        function resetVarietyAndCode() {
            let varietySelect = document.getElementById('itemVariety');
            varietySelect.innerHTML = '<option value="">품목을 먼저 검색하세요</option>';
            varietySelect.disabled = true;
            document.getElementById('itemCode').value = '';
        }

        // 화면 밖 클릭 시 드롭다운 닫기 처리
        document.addEventListener('click', function(e) {
            let resultBox = document.getElementById('autocompleteResults');
            let itemNameInput = document.getElementById('itemName');
            if (e.target !== itemNameInput && !resultBox.contains(e.target)) {
                resultBox.style.display = 'none';
            }
        });

        // 3. 품종 드롭다운 생성
        function populateVarietyDropdown(itemName) {
            let varietySelect = document.getElementById('itemVariety');
            varietySelect.innerHTML = '<option value="">품종을 선택하세요</option>';
            
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

        // 4. 드롭다운에서 품종 선택 시 코드 기입
        function applyVarietyCode() {
            let varietySelect = document.getElementById('itemVariety');
            let selectedOption = varietySelect.options[varietySelect.selectedIndex];
            
            if (selectedOption.value !== "") {
                document.getElementById('itemCode').value = selectedOption.dataset.code;
            } else {
                document.getElementById('itemCode').value = '';
            }
        }

        // 5. 품목코드를 직접 타이핑 시 자동완성 연동
        function applyByCode(codeValue) {
            if(!codeValue) return;
            
            let formattedCode = codeValue.padStart(3, '0');
            let matchedItem = kamisDB.find(item => item.code === formattedCode);

            if (matchedItem) {
                document.getElementById('itemCode').value = matchedItem.code;
                document.getElementById('itemName').value = matchedItem.name;
                
                populateVarietyDropdown(matchedItem.name);
                document.getElementById('itemVariety').value = matchedItem.variety;
                document.getElementById('autocompleteResults').style.display = 'none';
            }
        }

        // 6. 금액 콤마 및 계산
        function formatAndCalculate(input) {
            let value = input.value.replace(/[^0-9]/g, '');
            input.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            calculateTotal();
        }

        function calculateTotal() {
            let qty = document.getElementById('qty').value;
            let priceStr = document.getElementById('unitPrice').value.replace(/,/g, '');
            
            let price = parseInt(priceStr) || 0;
            let quantity = parseInt(qty) || 0;
            let total = price * quantity;
            
            document.getElementById('totalPrice').value = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        }

        // 7. 휴대폰 번호 자동 포맷팅
        function formatPhoneNumber(input) {
            let val = input.value.replace(/[^0-9]/g, '');
            let res = '';
            
            if(val.length < 4) {
                res = val;
            } else if(val.length < 7) {
                res = val.substr(0, 3) + '-' + val.substr(3);
            } else if(val.length < 11) {
                res = val.substr(0, 3) + '-' + val.substr(3, 3) + '-' + val.substr(6);
            } else {
                res = val.substr(0, 3) + '-' + val.substr(3, 4) + '-' + val.substr(7);
            }
            input.value = res;
        }

        // 8. 다음 우편번호 API 연동
        function execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    var addr = data.roadAddress; 
                    var extraAddr = ''; 

                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    
                    document.getElementById("address").value = data.zonecode + " " + addr + extraAddr;
                }
            }).open();
        }
    </script>
</body>
</html>