$(".commentDelete").on("click", function() {
    console.log("[댓글삭제성공]");
    var cId = $(this).closest("tr").find(".cId").val();
    $.ajax({
        type: "POST",
        url: "deleteCommentAsync",
        data: { 'cId': cId },
        dataType: 'text',
        success: function(data) {
            console.log(data);
            if (data == '0') {
                alert('삭제에 실패했습니다');
            } else {
                Swal.fire({
                    title: "성공",
                    text: "삭제했습니다",
                    icon: "success"
                }).then((result) => {
                    if (result.isConfirmed) {
                        // 확인 버튼이 눌렸을 때 실행할 코드
                        location.reload();
                    }
                });
            }
        },
        error: function(error) {
            console.log("error" + error);
        }
    });
});