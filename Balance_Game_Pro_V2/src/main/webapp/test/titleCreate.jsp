<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Hyperspace by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>문제출제</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<a href="index.html" class="title">Hyperspace</a>
				<nav>
					<ul>
						<li><a href="index.html">로그아웃</a></li>
						<li><a href="elements.html">마이페이지</a></li>
					</ul>
				</nav>
			</header>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<section id="main" class="wrapper">
						<div class="inner">
							<h1 class="major">문제 출제</h1>
	<form action="makeTitle.do" method="POST" onsubmit="return validation()">
    <label for="title">문제 입력:</label>
    <input type="text" id="title" name="title" placeholder="문제 주제를 입력해주세요" >
    <label for="answer_A">선택지A 입력:</label>
    <input type="text" id="answer_A" name="answer_A" placeholder="답변A를 입력해주세요" >
    <label for="answer_B">선택지B 입력:</label>
    <input type="text" id="answer_B" name="answer_B" placeholder="답변B를 입력해주세요" >
    <label for="explanation">출제 이유:</label>
    <input type="text" id="explanation" name="explanation" placeholder="출제 이유를 입력해주세요" >
    <input type="submit" value="전송">
    
</form>
						</div>
					</section>

			</div>

		<!-- Footer -->
			<footer id="footer" class="wrapper alt">
				<div class="inner">
					<ul class="menu">
						<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
					</ul>
				</div>
			</footer>
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
		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
	
	
</html>