$(".save").on("click", function() {
	console.log("[성공]");
	var loginId = document.getElementById('loginId').value;
	var qId = document.getElementById('qId').value;
	var saveId = $(this).attr('id');
	console.log(loginId);
	if (loginId == "") {
		console.log("[로그]로그인 x");
		alert('로그인이 필요합니다');
		//location.href='loginPage.do';
	} else {

		console.log("[로그] 로그인 o");
		//요소 값 가져오기
		//https://luahius.tistory.com/158
		$.ajax({
			type: "POST",
			url: "saveAsync.do",
			data: {
				'loginId': loginId,
				'qId': qId
			},
			dataType: 'text',
			success: function(data) {
				console.log(data);
				if (data == "실패") {
					console.log("실패");
				} else {
					console.log($("#"+saveId).attr("src", "images/" + data) + "<<<<<")
					$("#"+saveId).attr("src", "images/" + data);
				}

				//document.getElementById(".save").src="images/찜o.png";
			},
			error: function(error) {
				console.log('에러발생');
				console.log('에러의 종류:' + error);
			}

		});
	}
});
