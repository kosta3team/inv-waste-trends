// ========================================
// 현재 차트
// ========================================

let wasteChart = null;


// ========================================
// 메뉴 클릭
// ========================================

document.querySelectorAll(".waste-menu").forEach(function(menu) {

	menu.addEventListener("click", function(e) {

		e.preventDefault();

		const type = this.dataset.type;

		createControl(type);

		// 처음에는 그래프 삭제
		if (wasteChart !== null) {
			wasteChart.destroy();
			wasteChart = null;
		}

	});

});

// ========================================
// 차트 데이터 요청
// ========================================

function loadWasteChart(type, optionValue) {

	let body = "cmd=wasteChart&type=" + encodeURIComponent(type);


	if (type === "solar" || type === "lunar") {

		body += "&month=" + encodeURIComponent(optionValue);

	}


	if (type === "year") {

        body += "&year=" + encodeURIComponent(optionValue);

    }


	fetch("controller", {

		method: "POST",

		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},

		body: body

	})
		.then(function(response) {

			if (!response.ok) {
				throw new Error("서버 응답 오류 : " + response.status);
			}

			return response.json();

		})
		.then(function(result) {

			console.log("받은 데이터 :", result);

			drawChart(result);

		})
		.catch(function(error) {

			console.error("AJAX 오류 :", error);

		});

}


// ========================================
// SELECT 생성
// ========================================

function createControl(type) {

	const control = document.getElementById("chartControl");

	control.innerHTML = "";


	// ================================
	// 양력 월
	// ================================

	if (type === "solar") {

		control.innerHTML = `
            <label for="solarMonth">양력 월</label>

            <select id="solarMonth">
                <option value="" selected>월 선택</option>
                <option value="1">1월</option>
                <option value="2">2월</option>
                <option value="3">3월</option>
                <option value="4">4월</option>
                <option value="5">5월</option>
                <option value="6">6월</option>
                <option value="7">7월</option>
                <option value="8">8월</option>
                <option value="9">9월</option>
                <option value="10">10월</option>
                <option value="11">11월</option>
                <option value="12">12월</option>
            </select>
        `;


		document.getElementById("solarMonth")
			.addEventListener("change", function() {

				if (this.value === "") {
					return;
				}

				loadWasteChart("solar", this.value);

			});

	}


	// ================================
	// 음력 월
	// ================================

	else if (type === "lunar") {

		control.innerHTML = `
            <label for="lunarMonth">음력 월</label>

            <select id="lunarMonth">
                <option value="" selected>월 선택</option>
                <option value="1">1월</option>
                <option value="2">2월</option>
                <option value="3">3월</option>
                <option value="4">4월</option>
                <option value="5">5월</option>
                <option value="6">6월</option>
                <option value="7">7월</option>
                <option value="8">8월</option>
                <option value="9">9월</option>
                <option value="10">10월</option>
                <option value="11">11월</option>
                <option value="12">12월</option>
            </select>
        `;


		document.getElementById("lunarMonth")
			.addEventListener("change", function() {

				if (this.value === "") {
					return;
				}

				loadWasteChart("lunar", this.value);

			});

	}


	// ================================
	// 연중 폐기량
	// ================================

	else if (type === "year") {

		control.innerHTML = `
        <label for="yearSelect">연도</label>

        <select id="yearSelect">
            <option value="" selected>연도 선택</option>
            <option value="2024">2024년</option>
            <option value="2025">2025년</option>
            <option value="2026">2026년</option>
        </select>
    `;

		document.getElementById("yearSelect")
			.addEventListener("change", function() {

				console.log("선택한 연도 :", this.value);

				if (this.value === "") {
					return;
				}

				loadWasteChart("year", this.value);

			});
	}


	// ================================
	// 연도별 총량 비교
	// ================================

	else if (type === "compare") {

	control.innerHTML = `
            <span>작년 / 재작년 현재월까지 비교</span>
        `;

	loadWasteChart("compare");

}

}

// ========================================
// 차트 그리기
// ========================================

function drawChart(result) {

	const canvas = document.getElementById("wasteChart");


	// 기존 차트 제거
	if (wasteChart !== null) {

		wasteChart.destroy();

	}


	// ====================================
	// 특정 양력 월 비교
	// ====================================

	if (result.type === "solar") {

		wasteChart = new Chart(canvas, {

			type: "bar",

			data: {

				labels: result.data.map(function(item) {
					return item.year;
				}),

				datasets: [{

					label: result.month + "월 양력 폐기량",

					data: result.data.map(function(item) {
						return item.amount;
					}),

					borderWidth: 1

				}]

			},

			options: {

				responsive: true,

				maintainAspectRatio: false,

				scales: {

					y: {
						beginAtZero: true,
						title: {
							display: true,
							text: "폐기량"
						}
					},

					x: {
						title: {
							display: true,
							text: "연도"
						}
					}

				}

			}

		});

	}


	// ====================================
	// 특정 음력 월 비교
	// ====================================

	else if (result.type === "lunar") {

		wasteChart = new Chart(canvas, {

			type: "bar",

			data: {

				labels: result.data.map(function(item) {
					return item.year;
				}),

				datasets: [{

					label: "음력 " + result.month + "월 폐기량",

					data: result.data.map(function(item) {
						return item.amount;
					}),

					borderWidth: 1

				}]

			},

			options: {

				responsive: true,

				maintainAspectRatio: false,

				scales: {

					y: {
						beginAtZero: true,
						title: {
							display: true,
							text: "폐기량"
						}
					},

					x: {
						title: {
							display: true,
							text: "연도"
						}
					}

				}

			}

		});

	}


	// ====================================
	// 연중 폐기량
	// ====================================

	else if (result.type === "year") {

		wasteChart = new Chart(canvas, {

			type: "line",

			data: {

				labels: result.data.map(function(item) {
					return item.month;
				}),

				datasets: [{

					label: result.year + "년 월별 폐기량",

					data: result.data.map(function(item) {
						return item.amount;
					}),

					borderWidth: 2,

					tension: 0.3,

					fill: false

				}]

			},

			options: {

				responsive: true,

				maintainAspectRatio: false,

				scales: {

					y: {
						beginAtZero: true,
						title: {
							display: true,
							text: "폐기량"
						}
					},

					x: {
						title: {
							display: true,
							text: "월"
						}
					}

				}

			}

		});

	}


	// ====================================
	// 연도별 총량 비교
	// ====================================

	else if (result.type === "compare") {

		wasteChart = new Chart(canvas, {

			type: "line",

			data: {

				labels: result.data.map(function(item) {
					return item.month;
				}),

				datasets: [

					{

						label: "작년",

						data: result.data.map(function(item) {
							return item.lastYear;
						}),

						borderWidth: 2,

						tension: 0.3,

						fill: false

					},

					{

						label: "재작년",

						data: result.data.map(function(item) {
							return item.twoYearsAgo;
						}),

						borderWidth: 2,

						tension: 0.3,

						fill: false

					}

				]

			},

			options: {

				responsive: true,

				maintainAspectRatio: false,

				scales: {

					y: {
						beginAtZero: true,
						title: {
							display: true,
							text: "폐기량"
						}
					},

					x: {
						title: {
							display: true,
							text: "월"
						}
					}

				}

			}

		});

	}

}