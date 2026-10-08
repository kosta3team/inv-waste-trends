// 현재 날짜 표시
const today = new Date();

const year = today.getFullYear();
const month = String(today.getMonth() + 1).padStart(2, '0');
const day = String(today.getDate()).padStart(2, '0');

document.getElementById("currentDate").textContent = `${year}-${month}-${day}`;

// 재고 상세정보 모달 채우기
// tr의 data-* 속성값이 모달 내용 (추후 DB 연동 시 data-* 값만 실제 데이터로 교체하기)
function showInventoryDetail(fruitNo) {

    const contextPath =
        document.getElementById('contextPath').value;

    const url =
        contextPath
        + '/controller?cmd=inventoryDetail'
        + '&fruitNo='
        + encodeURIComponent(fruitNo);

    console.log("상세조회 URL:", url);

    fetch(url)
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    '상세정보 조회 실패: ' + response.status
                );
            }

            return response.text();
        })
        .then(html => {

            document.body.insertAdjacentHTML(
                'beforeend',
                html
            );

            const modalElement =
                document.getElementById(
                    'inventoryDetailModal'
                );

            const modal =
                new bootstrap.Modal(modalElement);

            modal.show();

            modalElement.addEventListener(
                'hidden.bs.modal',
                function () {
                    modalElement.remove();
                },
                { once: true }
            );
        })
        .catch(error => {

            console.error(error);

            alert(
                '재고 상세정보를 불러오지 못했습니다.'
            );
        });
}
