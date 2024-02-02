/**
 * 
 */
function blankSpace() {
    var flag = true;
    // 모든 input 요소의 value 확인하기
    $('input[type="text"]').each(function() {
        var content = $(this).val().trim(); // trim() 함수를 호출합니다.
        //console.log("Input Value: " + content);
        $(this).val(content);
        if (content === '') { // 입력 값이 공백이면
            flag = false;
            //return false; // 반복문을 종료합니다.
        }
        
    });
    return flag;
}