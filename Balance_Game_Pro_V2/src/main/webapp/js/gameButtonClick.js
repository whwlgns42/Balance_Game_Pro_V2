

 $(".answer").on("click", function() {
	
	var qId = document.getElementById('qId').value;
	var loginId = document.getElementById('loginId').value;
	var answerValue = $(this).prop('value');
	//로그인 아이디,질문 pk 로그
	/*console.log($(this).prop("value"));
	console.log(loginId);
	console.log(qId);*/
	
	totalAnswer(qId, loginId, answerValue);

	$(".answer").css("height", "100px");
	$(".answer").css("line-height", "100px");
	$(".answer").css("font-size", "30px");
	$(".answer").css("transition", "1000ms");
	$(".answer").attr("disabled", true);

	$("#title h1").css("font-size", "30px");
	$("#title h1").css("transition", "1000ms");

	$(".save").css("width", "30px");
	$(".save").css("height", "30px");
	$(".save").css("transition", "1000ms");


	commentAll(qId);
	

	$("#comment").show();
});