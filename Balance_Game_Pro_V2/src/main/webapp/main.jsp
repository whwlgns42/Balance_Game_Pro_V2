<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
	Hyperspace by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Hyperspace by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">
	<%
	String loginData = (String) session.getAttribute("loginId");
	%>
	<!-- Sidebar -->
	<section id="sidebar">
		<div class="inner">
			<nav>
				<ul>
					<c:if test="${empty loginId }">
						<li><a href="#">문제 목록</a></li>
						<li><a href="loginPage.do">문제 출제</a></li>
						<li><a href="loginPage.do">찜목록</a></li>
						<li><a href="loginPage.do">후원 랭킹</a></li>
					</c:if>
					<c:if test="${not empty loginId }">
						<li><a href="#intro">문제 목록</a></li>
						<li><a href="makeTitlePage.do">문제 출제</a></li>
						<li><a href="#two">찜목록</a></li>
						<li><a href="sponsorPage.do">후원 랭킹</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</section>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Intro -->
		<section id="intro" class="wrapper style1 fullscreen fade-up">
			<div class="inner">
				<h1>밸런스 게임</h1>


				<c:if test="${empty loginId }">
					<ul class="actions">
						<li><a href="gamePage.do" class="button scrolly">게임하기</a></li>
					</ul>
					<ul class="actions">
						<li><a href="loginPage.do" class="button scrolly">로그인</a></li>
					</ul>
					<ul class="actions">
						<li><a href="joinPage.do" class="button scrolly">회원가입</a></li>
					</ul>
				</c:if>
				<c:if test="${not empty loginId }">
					<ul class="actions">
						<li><a href="gamePage.do" class="button scrolly">게임하기</a></li>
					</ul>
					<ul class="actions">
						<li><a href="logout.do" class="button scrolly">로그아웃</a></li>
					</ul>
					<ul class="actions">
						<li><a href="pwCheckPage.do" class="button scrolly">마이페이지</a></li>
					</ul>
				</c:if>

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