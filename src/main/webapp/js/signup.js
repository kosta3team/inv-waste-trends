const personalBtn = document.getElementById("personalBtn");
const businessBtn = document.getElementById("businessBtn");
const nameLabel = document.getElementById("nameLabel");
const nameInput = document.getElementById("name");
const cooperativeArea = document.getElementById("cooperativeArea");
const phoneInput = document.getElementById("phone");
const passwordInput = document.getElementById("password");
const passwordMessage = document.getElementById("passwordMessage");
const passwordConfirmInput = document.getElementById("passwordConfirm");

const passwordConfirmMessage = document.getElementById("passwordConfirmMessage");

// 개인 선택
personalBtn.addEventListener("click", function() {

    nameLabel.innerText = "이름 *";
    nameInput.placeholder = "이름을 입력하세요";

    cooperativeArea.style.display = "none";

    personalBtn.classList.add("btn-light", "border");

    businessBtn.classList.remove("btn-light", "border");
    businessBtn.classList.add("text-secondary");

});

// 사업자 선택
businessBtn.addEventListener("click", function() {

    nameLabel.innerText = "대표자 이름 *";
    nameInput.placeholder = "대표자 이름을 입력하세요";

    cooperativeArea.style.display = "block";

    businessBtn.classList.add("btn-light", "border");

    personalBtn.classList.remove("btn-light", "border");
    personalBtn.classList.add("text-secondary");

});

// 주소검색 버튼 클릭 이벤트 등록
document
    .getElementById("searchAddressBtn")
    .addEventListener("click", searchAddress);

function searchAddress() {

    new daum.Postcode({
        oncomplete: function(data) {

            document.getElementById("address").value =
                data.roadAddress || data.jibunAddress;

            document.getElementById("detailAddress").focus();

        }
    }).open();
}


// 010-1234-5678 형식 적용
phoneInput.addEventListener("input", function () {

    let value = this.value.replace(/[^0-9]/g, "");
    value = value.substring(0, 11); // 최대 11자리

    if (value.length > 7) {
        value =
            value.replace(/(\d{3})(\d{4})(\d+)/, "$1-$2-$3");
    } else if (value.length > 3) {
        value =
            value.replace(/(\d{3})(\d+)/, "$1-$2");
    }

    this.value = value;
});


// 비밀번호 검증 
passwordInput.addEventListener("input", function () {

    const password = this.value;

    const hasEnglish = /[a-zA-Z]/.test(password);
    const hasNumber = /[0-9]/.test(password);
    const hasSpecial = /[!@#$%^&*(),.?":{}|<>]/.test(password);

    let count = 0;

    if (hasEnglish) count++;
    if (hasNumber) count++;
    if (hasSpecial) count++;

    if (password.length < 8 || password.length > 16) {

        passwordMessage.textContent =
            "8자 이상 16자 이하로 입력해주세요.";

        passwordMessage.className = "text-danger";

    } else if (count < 2) {

        passwordMessage.textContent =
            "영문, 숫자, 특수문자 중 2가지 이상을 조합해주세요.";

        passwordMessage.className = "text-danger";

    } else {

        passwordMessage.textContent =
            "사용 가능한 비밀번호입니다.";

        passwordMessage.className = "text-success";
    }
});


// 비밀번호 확인
function checkPasswordMatch() {

    const password = passwordInput.value;
    const passwordConfirm = passwordConfirmInput.value;

    if (passwordConfirm === "") {
        passwordConfirmMessage.textContent = "";
        return;
    }

    if (password === passwordConfirm) {

        passwordConfirmMessage.textContent =
            "비밀번호가 일치합니다.";

        passwordConfirmMessage.className =
            "form-text text-success";

    } else {

        passwordConfirmMessage.textContent =
            "비밀번호가 일치하지 않습니다.";

        passwordConfirmMessage.className =
            "form-text text-danger";
    }
}

// 비밀번호 입력 시 확인
passwordInput.addEventListener(
    "input",
    checkPasswordMatch
);

// 비밀번호 확인 입력 시 확인
passwordConfirmInput.addEventListener(
    "input",
    checkPasswordMatch
);
