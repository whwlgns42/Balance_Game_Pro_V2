<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<style>
input {
	margin: inherit;
}
</style>
<meta charset="utf-8" />
<link rel="icon" href="images/favicon.png">
<title>My Wish Game</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
				<h1 class="major">${qDTO.title}</h1>
				<h3>작성자: ${qDTO.loginId}</h3>
				<table>
					<tr>
						<td>답변A: ${qDTO.answer_A}</td>
						<td>답변B: ${qDTO.answer_B}</td>
					</tr>
				</table>
				<ui>
				<li>설명: ${qDTO.explanation}</li>
				</ui>
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