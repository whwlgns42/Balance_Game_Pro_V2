<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>2차인증</title>
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
		<section style="align-content: center;" id="intro" class="wrapper style1 fullscreen fade-up" >
			<div class="inner">
				<h1>본인 확인</h1>
				<form action="pwCheck.do" method="POST">
					<div>
						<label for="password">password:</label>
					</div>
					<div style="display: flex; justify-content: center;">
						<input type="password" id="mPw" name="mPw" style="margin-right: 30px;">
						<button type="submit">확인</button>
					</div>
				</form>
			</div>
		</section>

	</div>

	<!-- Footer -->
	<footer id="footer" class="wrapper style1-alt">
		<div class="inner">
			<ul class="menu">
				<li>&copy; Untitled. All rights reserved.</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
	</footer>

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