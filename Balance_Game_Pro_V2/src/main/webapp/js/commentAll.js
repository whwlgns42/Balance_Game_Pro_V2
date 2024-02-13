
function commentAll(qId) {



	$.ajax({
		type: "POST",
		url: "commentAsync",
		data: {
			'qId': qId
		},
		dataType: 'json',
		success: function(data) {

			var elem = "";
			$.each(data, function(index, data) {
				elem += "<tr> <td>";

				if (data.grade == 1) {
					elem += "<img src='images/blackStone.png' alt='등급1에 주는 블랙스톤' width='25' height ='25' />";
				} else if (data.grade == 2) {
					elem += "<img src='images/silverStone.png' alt='등급2에 주는 실버스톤' width='25' height ='25' />";
				} else if (data.grade == 3) {
					elem += "<img src='images/goldStone.png' alt='등급3에 주는 골드스톤' width='25' height ='25' />";
				} else if (data.grade == 4) {
					elem += "<img src='images/blueStone.png' alt='등급4에 주는 플래티넘스톤' width='25' height ='25' />";
				}
				elem += data.memberName + "</td>";

				elem += "<td>" + data.content + "</td>";
				elem += "</tr>";
				console.log(data.name);
			});
			if ($("#noComment").length > 0) {
				$("#noComment").text("");
			}
			$("table tbody").append(elem);
			//document.getElementById(".save").src="images/찜o.png";
		},
		error: function(error) {

			console.log('에러발생');
			console.log('에러의 종류:' + error);
		}

	});


}