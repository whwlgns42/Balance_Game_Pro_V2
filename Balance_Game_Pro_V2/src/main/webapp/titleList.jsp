<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>

<!DOCTYPE HTML>
<!--
   Hyperspace by HTML5 UP
   html5up.net | @ajlkn
   Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<style>
.button-container {
	text-align: right;
	margin-top: 10px;
}
</style>
<style>
.button-container {
	text-align: left;
	margin-top: 10px;
}

.button-container button {
	margin-left: 5px;
}
</style>
<style>
.button-container1 {
	text-align: right;
	margin-top: 10px;
}

.button-container1 button {
	margin-left: 5px;
}

.special-button {
	text-align: right;
	margin-top: 1px;
}

.special-button button {
	margin-left: 5px;
}

.special-button2 {
	text-align: right;
	margin-top: 1px;
}

.special-button2 button {
	margin-left: 5px;
}
</style>
<title>문제 목록</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>

<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<common:logo></common:logo>
		<nav>
			<ul>
				<c:if test="${not empty loginId }">
					<li><a href="logout.do" class="active">로그아웃</a></li>
					<li><a href="pwCheckPage.do" class="active">마이페이지</a></li>
				</c:if>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major" style="margin-bottom: 20px;">문제 목록</h1>
				<div class="card-body table-responsive p-0">
					<table class="table table-hover text-nowrap">
						<thead>
							<tr>
								<th>NO</th>
								<th>Title</th>
								<!-- <th>Reason</th> -->
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(qDatas) <= 0 }">
								<tr>
									<td colspan="1">출제된 문제가 없습니다.</td>
								</tr>
							</c:if>
							<c:forEach var="data" items="${qDatas}" varStatus="loop">
							
								<tr
									onclick="location.href = 'titleDetailPage.do?qid=${data.qId}&writer=${loginId}'">
									<td>${loop.index + 1}</td>
									<!-- loop.index는 0부터 시작하므로 +1을 해서 순번을 출력합니다. -->
									<td>${data.title}</td>
									<!-- <td>${data.explanation}</td> -->
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
	</div>

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