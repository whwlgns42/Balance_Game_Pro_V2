<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Hyperspace by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Generic - Hyperspace by HTML5 UP</title>
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
		<a href="index.html" class="title">Hyperspace</a>
		<nav>
			<ul>
				<li><a href="index.html">Home</a></li>
				<li><a href="generic.html" class="active">Generic</a></li>
				<li><a href="elements.html">Elements</a></li>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">결과</h1>
				<span class="image fit"><img src="images/pic07.jpg" alt="" /></span>
			</div>
		</section>

	</div>

	<div class="card-footer">
		<form onsubmit="addComment(event)">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="댓글을 입력하세요" required>
			</div>
			<button type="submit" class="btn btn-primary">작성</button>
		</form>
		<div class="comments mt-3"></div>
	</div>
	<div class="table-wrapper">
		<table class="alt">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>작성자</td>
					<td>댓글</td>
					<td>작성일자</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>댓글</td>
					<td>작성일자</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>댓글</td>
					<td>작성일자</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>댓글</td>
					<td>작성일자</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>댓글</td>
					<td>작성일자</td>
				</tr>
			</tbody>

		</table>
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