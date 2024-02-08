

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

/*	$(document).keydown(function(e) {
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
	});*/
	
	
	
$(document).keydown(function(event) {
    var key = event.keyCode || event.which;
    var targetTagName = event.target.tagName.toLowerCase();

    // Backspace 키 누르면서 입력창이 아닌 경우에는 뒤로가기를 막습니다.
    if (key === 8 && targetTagName !== "input" && targetTagName !== "textarea") {
        event.preventDefault();
    }

    // F5 키와 Ctrl+R 키를 누르면 새로고침을 막습니다.
    if (key === 116 || (event.ctrlKey && key === 82)) {
        event.preventDefault();
       	Swal.fire({
			title: "새로고침",
			text: "새로고침이 금지되어 있습니다.",
			icon: "info"
		});
        
        //alert("새로고침이 금지되어 있습니다.");
    }
});
	
});
