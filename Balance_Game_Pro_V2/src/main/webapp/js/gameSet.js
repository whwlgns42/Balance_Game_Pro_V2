/**
 * 
 */





$(document).ready(function() {
	if (document.getElementById('qId').value == '') {
		console.log('[질문 pk x]');
		location.href = 'main.do';
	}

	$('input[type="text"]').attr('maxlength', 50);
	$("#comment").hide();

	$("#next").on("click", function() {

		//localStorage.setItem("list",${list});
		location.href = 'gamePage.do';

	});
});