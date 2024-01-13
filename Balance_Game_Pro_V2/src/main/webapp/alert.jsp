<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	<script>
	Swal.fire({
	    text: "${msg}",
	    icon: "success", // 성공버튼 누르면
	  }).then(() => { // 메인 페이지로 이동
	    location.href = "main.do";
	  });
	</script>

</body>
</html>