/**
 * 
 */





$(document).ready(function() {
	if (document.getElementById('qId').value == '') {
		console.log('[질문 pk x]');
		location.href = 'main.do';
	}


	$("#comment").hide();

	$("#next").on("click", function() {

		//localStorage.setItem("list",${list});
		location.href = 'gamePage.do';

	});
});