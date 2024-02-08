console.log("[로그] rangeSlider.js");
$(document).ready(function() {
    $("#btn").click(function() {
        var inputVal = document.querySelector('#range_1');
        const minAmount = parseFloat(inputVal.value.split(";")[0]);
        const maxAmount = parseFloat(inputVal.value.split(";")[1]);

        // 세션 스토리지에서 데이터 가져오기
        const userInfoObj = JSON.parse(sessionStorage.getItem('userInfoObj'));

        if(userInfoObj) {
            // 필터링 함수 호출
            const filteredData = filterByAmount(minAmount, maxAmount, userInfoObj);
            var i = 1;
            let elem = "<tr>";
            filteredData.forEach(supporter => {
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

            $("table tbody").html(elem);
        } else {
            console.log("세션 스토리지에서 데이터를 가져올 수 없습니다.");
        }
    });
});

// 필터링 함수
function filterByAmount(minAmount, maxAmount, supportList) {
    console.log("filter 로그: " + supportList.datas[0].name);
    if(supportList.id == '후원순'){
        return supportList.datas.filter(supporter => supporter.total >= minAmount && supporter.total <= maxAmount);
    } else if(supportList.id == '최신순'){
        return supportList.datas.filter(supporter => supporter.amount >= minAmount && supporter.amount <= maxAmount);
    }
}

