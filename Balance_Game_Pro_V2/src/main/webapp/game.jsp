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
		<title>게임 페이지</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<style>
      #wrapper .inner {
      text-align: center;
    }

    #wrapper .inner button {
      margin: 5px;
    }
      #wrapper .inner .like-button {
      margin-top: 10px; /* 선택지와 찜하기 버튼 사이의 간격을 조절합니다. */
      color: #333; /* 초기에는 어두운 회색으로 표시 */
    }
  </style>
	</head>
	<body class="is-preload">
	<%String loginData = (String) session.getAttribute("loginId"); %>
		<!-- Header -->
			<header id="header">
				<a href="main.do" class="title">자비스</a>
				<nav>
				<%
				if(loginData == null) {
					%>
					<ul>
						<li><a href="loginPage.do">로그인</a></li>
						<li><a href="joinPage.do" class="active">회원가입</a></li>
					</ul>
					<% 
				}else {
					%>
					<ul>
						<li><a href="logout.do">로그아웃</a></li>
						<li><a href="pwCheckPage.do" class="active">마이페이지</a></li>
					</ul>
					<%
				}
				%>
					
				</nav>
			</header>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<section id="main" class="wrapper">
						<div class="inner">
							<h1 class="major">밸런스 게임</h1>
							<span class="image "><img src="images/pic09.jpg" alt="" /></span>
							 <button class="like-button" onclick="toggleLike(2)">찜하기</button>
						</div>
						<div class="inner">
          					<button onclick="selectOption(1)">평생 떡볶이 먹기</button>
          					<button onclick="selectOption(2)">평생 떡볶이 안먹기</button>
          					 <button class="like-button" onclick="toggleLike(2)">찜하기</button>
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