
function blankSpace() {
    var flag = true;
    // 모든 input 요소의 value 확인하기
    $('input[type="text"]').each(function() {
        var content = $(this).val().trim(); // trim() 함수를 호출합니다.
        // < 나 > 가 하나라도 입력되었는지 확인
        if (content.includes('<') || content.includes('>')) {
			console.log('< 입력');
            flag = false;     
        }

        $(this).val(content);
        if (content === '') { // 입력 값이 공백이면
            flag = false;
        }

    });
    return flag;
}