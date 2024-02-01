<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="images/favicon.png">
<title>에러 페이지</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
body {
	margin: 0;
	padding: 0;
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

img {
	width: 50%;
	height: 50%;
}
</style>
</head>
<body>
	<img src="images/error404.jpg">
</body>
<script>




let timerInterval;
Swal.fire({
  title: "어???",
  html: "잠시만 기다려주세요...",
  timer: 2000,
  timerProgressBar: true,
  didOpen: () => {
    Swal.showLoading();
    const timer = Swal.getPopup().querySelector("b");
    timerInterval = setInterval(() => {
      timer.textContent = `${Swal.getTimerLeft()}`;
    }, 100);
  },
  willClose: () => {
    clearInterval(timerInterval);
  }
}).then((result) => {
  if (result.dismiss === Swal.DismissReason.timer) {
	  Swal.fire("메인페이지로 이동합니다.!").then((result) => {
		 location.href= 'main.do';
		});
  }
});  
</script>
</html>
