<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<html>
<head>
<title>밸런스게임</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">
	<!-- Sidebar -->
	<section id="sidebar" style="margin-top: -120px;">
		<div class="inner" style="height: 300px; ">
		<a style="text-decoration: none; border: none;" href="main.do"><img style="margin-right: 200px;  "  src="images/logo.png" height="150" width="150"></a>
			<nav>
				<ul style="margin-top: 145px;">
					<c:if test="${empty loginId }">
						<li><a href="titleListPage.do">문제 목록</a></li>
						<li><a href="loginPage.do">문제 출제</a></li>
						<li><a href="loginPage.do">찜목록</a></li>
						<li><a href="loginPage.do">후원 랭킹</a></li>
					</c:if>
					<c:if test="${not empty loginId }">
						<li><a href="titleListPage.do">문제 목록</a></li>
						<li><a href="makeTitlePage.do">문제 출제</a></li>
						<li><a href="wishListPage.do">찜목록</a></li>
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