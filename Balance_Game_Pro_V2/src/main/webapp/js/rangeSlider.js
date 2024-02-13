console.log("[로그] rangeSlider.js");

$(document).ready(function() {
    // 버튼 클릭 시 실행되는 함수
    $("#btn").click(function() {
        // 입력값 가져오기
        var inputVal = document.querySelector('#range_1'); 
        // 입력값을 ';'로 분리하여 최소 및 최대 금액 추출
        const minAmount = parseFloat(inputVal.value.split(";")[0]);
        const maxAmount = parseFloat(inputVal.value.split(";")[1]);

        // 세션 스토리지에서 데이터 가져오기
        const userInfoObj = JSON.parse(sessionStorage.getItem('userInfoObj'));

        if(userInfoObj) {
            // 필터링 함수 호출하여 데이터 필터링
            const filteredData = filterByAmount(minAmount, maxAmount, userInfoObj);
            var i = 1;
            let elem = "<tr>";
            filteredData.forEach(supporter => {
                // 필터링된 데이터를 HTML 테이블에 추가
                elem += "<td>" + i + "</td>";
                elem += "<td>" + supporter.loginId + "(" + supporter.name + ")" + "</td>";
                if(userInfoObj.id == '최신순') {
                    elem += "<td>" + supporter.amount + "</td>";
                } else if(userInfoObj.id == '후원순') {
                    elem += "<td>" + supporter.total + "</td>";
                }
                elem += "<td>" + supporter.date + "</td>";
                elem += "</tr>";
                i++;
            });

            // HTML 테이블 내용 업데이트
            $("table tbody").html(elem);
        } else {
            console.log("세션 스토리지에서 데이터를 가져올 수 없습니다.");
        }
    });
});

// 금액으로 데이터 필터링하는 함수
function filterByAmount(minAmount, maxAmount, supportList) {
    console.log("filter 로그: " + supportList.datas[0].name);
    if(supportList.id == '후원순'){
        // 후원순일 때는 총 후원 금액으로 필터링
        return supportList.datas.filter(supporter => supporter.total >= minAmount && supporter.total <= maxAmount);
    } else if(supportList.id == '최신순'){
        // 최신순일 때는 최신 후원 금액으로 필터링
        return supportList.datas.filter(supporter => supporter.amount >= minAmount && supporter.amount <= maxAmount);
    }
}
