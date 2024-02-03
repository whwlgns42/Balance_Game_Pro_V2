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

	$(document).keydown(function(e) {
		key = (e) ? e.keyCode : e.keyCode;
		
		var t = document.activeElement;
		if (key == 8 || key == 116 || key == 17 || key == 82) {
			
			if (key == 8) {
				if (t.tagName != "INPUT") {
					if (e) {
						e.preventDefault();
					} else {
						e.keyCode = 0;
						e.returnValue = false;
					}
				}
			} else {
				alert('세로고침 불가');
				if (e) {
					e.preventDefault();
				} else { e.keyCode = 0; e.returnValue = false; }
			}
		}
	});
});
