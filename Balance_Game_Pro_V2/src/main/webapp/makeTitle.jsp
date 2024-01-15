<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>문제 출제 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	문제 출제 페이지
	<form action="makeTitle.do" method="POST" onsubmit="return validation()">
		
		주제 : <input type="text" id="title" name="title" placeholder="문제 주제를 입력해주세요" > <br>
		답A : <input type="text" id="answer_A" name="answer_A" placeholder="답변A를 입력해주세요" > <br>
		답B : <input type="text" id="answer_B" name="answer_B" placeholder="답변B를 입력해주세요" > <br>
		출제 사유 : <input type="text" id="explanation" name="explanation" placeholder="출제 이유를 입력해주세요" > <br>
		<input type="submit" placeholder="출제하기" required> <br>
	</form>
	
	<script>
	function validation() {
		
		let title = $("#title").val();
		let answer_A = $("#answer_A").val();
		let answer_B = $("#answer_B").val();
		let explanation = $("#explanation").val();
		
	
		
		if(!title) {
			 Swal.fire({
          	  title: "제목",
          	  text: "문제 제목을 입력해주세요.",
          	  icon: "warning"
          	});
          return false;
		}
		
		if(!answer_A) {
			 Swal.fire({
          	  title: "답변A",
          	  text: "답변A를 입력해주세요.",
          	  icon: "warning"
          	});
          return false;
		}
		
		if(!answer_B) {
			 Swal.fire({
          	  title: "답변B",
          	  text: "답변B를 입력해주세요.",
          	  icon: "warning"
          	});
          return false;
		}
		
		if(!explanation) {
			 Swal.fire({
         	  title: "문제설명",
         	  text: "문제에 대한 설명을 입력해주세요.",
         	  icon: "warning"
         	});
         return false;
		}
		return true;
	}
	
	</script>
</body>
</html>