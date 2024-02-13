console.log("[로그] sponsorListRank.js");
$(document).ready(function() {
	$("#btnRank").click(function() {
		console.log("[로그] sponsorListRank.js 진입");
		$.ajax({
			type: "POST", //데이터 요청타입
			url: "adminSupportManageRankAsync", //비동기를 처리할 해당 주소
			dataType: "json", //데이터를 받을 타입
			success: function(datas) { //데이터 받기 성공
				if (datas == "실패") {
					console.log("실패")
				} else {
					// 세션 스토리지 이용
					const userInfoObj = {
						id: '후원순',
						datas: datas
					};
					sessionStorage.setItem('userInfoObj', JSON.stringify(userInfoObj));
					var i = 1;
					var elem = "<tr>";
					datas.forEach(supporter => {
						elem += "<td>" + i + "</td>";
						elem += "<td>" + supporter.loginId + "(" + supporter.name + ")" + "</td>";
						elem += "<td>" + supporter.total + "</td>";
						elem += "<td>" + supporter.date + "</td>";
						elem += "</tr>";
						i++;
					});
					$("table tbody").html(elem);
				}
			},
			error: function(error) { // 데이터 받기 실패
				console.log("error" + error);
			}
		});
	});
});
