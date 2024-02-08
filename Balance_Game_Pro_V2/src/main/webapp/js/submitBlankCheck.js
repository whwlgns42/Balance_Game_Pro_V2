$(document).ready(function() {
	console.log('성공');
	$('input[type="text"]').attr('maxlength', 50);
	$('textarea').attr('maxlength', 50);
	$('#insertForm').submit(function(event) {

		// 입력 필드의 값이 공백인지 확인합니다.
		if (!blankSpace()) {
			event.preventDefault(); // 폼 제출을 막습니다.
			alert('입력 필드에 값을 입력하세요 또는 기호 입력은 불가합니다'); // 사용자에게 알립니다.
		}
	});

});