/**
 * 
 */
console.log("[생성]");
$("#commentDelete").on("click",function(){
	console.log("[댓글삭제성공]");
	var cId = document.getElementById('cId').value;
	$.ajax({
		type: "POST",
		url: "DeleteCommentAsync.do",
		data: {'cId': cId},
		dataType: 'text',
		success: function(data){
			console.log(data);
			if(data == '0'){
				alert('삭제에 실패했습니다');
			}else{
				alert('삭제했습니다');
				location.reload();
			}
		},
		error(error){
			console.log("error"+error);
		}
	});
});