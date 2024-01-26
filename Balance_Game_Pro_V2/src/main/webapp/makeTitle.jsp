<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html>

<head>
<style>
input {
	margin: inherit;
}
</style>
<meta charset="utf-8" />
<title>문제 출제 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body class="is-preload">

	<!-- 헤더 -->
	<header id="header">
	<!-- 로고 -->
		<common:logo></common:logo>
		<nav>
			<ul>
				<li><a href="logout.do" class="active">로그아웃</a></li>
				<li><a href="pwCheckPage.do" class="active">마이페이지</a></li>
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
					<input type="text" id="title" name="title" placeholder="문제 주제를 입력해주세요">
					<label for="answer_A">선택지A 입력:</label>
					<input type="text" id="answer_A" name="answer_A" placeholder="답변A를 입력해주세요">
					<label for="answer_B">선택지B 입력:</label>
					<input type="text" id="answer_B" name="answer_B" placeholder="답변B를 입력해주세요">
					<label for="explanation">출제 이유:</label>
					<input type="text" id="explanation" name="explanation" placeholder="출제 이유를 입력해주세요">
					<input type="submit" value="문제생성">

				</form>
			</div>
		</section>
	</div>

	<!-- Footer -->
	<footer id="footer" class="wrapper alt">
		<div class="inner">
			<ul class="menu">
				<li>&copy; Untitled. All rights reserved.</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
	</footer>
	<script>
		function validation() {

			let title = $("#title").val();
			let answer_A = $("#answer_A").val();
			let answer_B = $("#answer_B").val();
			let explanation = $("#explanation").val();

			if (!title) {
				Swal.fire({
					title : "제목",
					text : "문제 제목을 입력해주세요.",
					icon : "warning"
				});
				return false;
			}

			if (!answer_A) {
				Swal.fire({
					title : "답변A",
					text : "답변A를 입력해주세요.",
					icon : "warning"
				});
				return false;
			}

			if (!answer_B) {
				Swal.fire({
					title : "답변B",
					text : "답변B를 입력해주세요.",
					icon : "warning"
				});
				return false;
			}

			if (!explanation) {
				Swal.fire({
					title : "문제설명",
					text : "문제에 대한 설명을 입력해주세요.",
					icon : "warning"
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