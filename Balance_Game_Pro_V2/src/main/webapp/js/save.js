$(".save").on("click", function() {
	console.log("[성공]");
	var loginId = document.getElementById('loginId').value;
	var qId = document.getElementById('qId').value;
	var saveId = $(this).prop('id');
	var page = document.getElementById('page').value;
	console.log(loginId);
	console.log(saveId);
	if (loginId == "") {
		console.log("[로그]로그인 x");
		Swal.fire({
			title: "로그인 필요",
			text: "로그인 후 사용가능합니다.",
			icon: "info"
		});
	} else {
		console.log("[로그] 로그인 o");
		//요소 값 가져오기
		//https://luahius.tistory.com/158
		$.ajax({
			type: "POST",
			url: "saveAsync",
			data: {
				'loginId': loginId,
				'qId': saveId
			},
			dataType: 'text',
			success: function(data) {
				console.log(data);
				if (data == "실패") {
					console.log("실패");
				} else {
					console.log($("#" + saveId).attr("src", "images/" + data) + "<<<<<")
					$("#" + saveId).attr("src", "images/" + data);
					if (data == "찜x.png" && page == "wishPage") {
						location.reload();
					}
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
