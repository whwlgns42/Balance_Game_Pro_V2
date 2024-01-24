<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.save.*, java.util.ArrayList"%>

<!DOCTYPE HTML>
<!--
	Hyperspace by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>마이 페이지</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<a href="main.do" class="title">밸런스게임</a>
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
				<h1 class="major" style="margin-bottom: 20px;">마이 페이지</h1>
				<div style="margin-bottom: 30px;">
					<button style="font-size: 10px; margin-right: 20px;" onclick="location.href='makeTitlePage.do';">문제출제하기</button>
					<button style="font-size: 10px;" onclick="location.href='wishListPage.do';">찜목록</button>

				</div>
				<form action="mypageUpdate.do" method="POST">
					아이디
					<input readonly type="text" value="${myPageData.loginId}">
					<br> 이름
					<input type="text" name="name" value="${myPageData.name}">
					<br> 성별
					<input type="text" value="${myPageData.gender}">
					<br> 이메일
					<input type="email" name="email" value="${myPageData.email}">
					<br> 주소
					<input readonly type="text" value="${myPageData.address}">
					<br>
					<input type="submit" value="변경">
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