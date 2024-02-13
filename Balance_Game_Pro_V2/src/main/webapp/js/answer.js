
function totalAnswer(qId, loginId, answerValue) {
    $.ajax({
        type: "POST",
        url: "answerAsync",
        data: {
            'qId': qId,
            'loginId': loginId,
            'answer': answerValue
        },
        dataType: 'json',
        success: function(data) {
            console.log(data.answerCntA);
            console.log(data.answerCntB);
            var total = data.answerCntA + data.answerCntB;
            console.log(total);
            $("#answer_A").text(Math.round(((data.answerCntA * 1.0) / total) * 100) + "%");
            $("#answer_B").text(Math.round(((data.answerCntB * 1.0) / total) * 100) + "%");
        },
        error: function(error) {
            console.log('에러발생');
            console.log('에러의 종류:' + error);
        }
    });
}