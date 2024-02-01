/**
 * 
 */
function blankSpace() {
    var flag = true;
	var inputElements = document.querySelectorAll('input[type="text"]');
    // 모든 input 요소의 value 출력하기
    inputElements.forEach(function(inputElement) {
        var content = inputElement.value;
        console.log("Input Value: " + content);
        if (!content.trim()) { // 입력 값이 공백이면
            flag = false;
        }
        inputElement.value = '';
    });

    return flag;
}