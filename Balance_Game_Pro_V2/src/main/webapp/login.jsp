<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>로그인</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">



	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Intro -->
		<section style="align-content: center;" id="intro" class="wrapper style1 fullscreen fade-up">
			<a style="text-decoration: none; border: none; margin-top: -150px;" href="main.do"><img style="margin-right: 200px; margin-top: -100px; margin-left: 50px;" src="images/logo.png" height="150" width="150"></a>
			<div style="margin-top: 100px;">
				<h1 style="text-align: center;">로그인</h1>
				
				<div style="display: flex; justify-content: center;">
					<form id="loginForm" action="login.do" method="POST">
						<div>
							<label for="loginId">아이디</label>
						</div>
						<div>
							<input type="text" id="loginId" name="loginId" style="margin-right: 30px; width: 600px; height: 50px;">
						</div>
						<label style="margin-top: 20px;" for="password">비밀번호</label>
						<input type="password" id="mPw" name="mPw" style="margin-right: 30px; width: 600px; height: 50px;">
					</form>
				</div>
				<div style="text-align: center; padding-right: 36px;">
					<button style="margin-top: 20px;  margin-right: 270px;" onclick="location.href = 'joinPage.do'">회원가입</button>
					<button style="margin-top: 20px;" type="submit" form="loginForm">로그인</button>

				</div>
			</div>
		</section>

	</div>

	<!-- Footer -->
	<!-- 	<footer id="footer" class="wrapper style1-alt">
		<div class="inner">
			<ul class="menu">
				<li>&copy; Untitled. All rights reserved.</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
	</footer> -->

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