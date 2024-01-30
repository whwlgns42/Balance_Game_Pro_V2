/**
 * 
 */


var isRun = false;
$("#write").on("click", function() {
	var qId = document.getElementById('qId').value;
	var loginId = document.getElementById('loginId').value;
	console.log("댓글 입력");
	var content = $('#inputContent').val();
	//$('#inputContent').val('');
	if (isRun == true) {
		return;
	}
	isRun = true;




	$('#apple').html('<input type="text" placeholder="댓글을 입력하세요" id="inputContent">'); ////

	console.log('확인1: ' + content);
	if (content) {
		$.ajax({
			type: "POST",
			url: "commentWriteAsync.do",
			data: {
				'qId': qId,
				'loginId': loginId,
				'comment': content

			},
			dataType: 'json',
			success: function(data) {

				var elem = "";

				elem += "<tr> <td>";
				if (data.grade == 1) {
					elem += "<img src= 'images/blackStone.png' alt='등급1에 주는 블랙스톤' width='25' height ='25' />";
				} else if (data.grade == 2) {
					elem += "<img src= 'images/silverStone.png' alt='등급1에 주는 블랙스톤' width='25' height ='25' />";
				} else if (data.grade == 3) {
					elem += "<img src='images/goldStone.png' alt='등급3에 주는 골드스톤' width='25' height ='25' />";
				} else if (data.grade == 4) {
					elem += "<img src='images/blueStone.png' alt='등급4에 주는 플래티넘스톤' width='25' height ='25' />";
				}
				elem += data.memberName + "( " + data.loginId + " )</td>";

				elem += "<td>" + data.content + "</td>";
				elem += "</tr>";
				console.log('확인2: ' + data.name);
				if ($("#noComment").length > 0) {
					$("#noComment").text("");
				}
				$("table tbody").append(elem);

				isRun = false;
				//document.getElementById(".save").src="images/찜o.png";
			},
			error: function(error) {

				console.log('에러발생');
				console.log('에러의 종류:' + error);
			}

		});

	}

});

$("#inputContent").on("keydown", function(e) {
	console.log(e.code);
	if (e.code == 'Enter' || e.code == 'NumpadEnter') {
		console.log("엔터침" + e.code);
		$("#write").click();
	}
});