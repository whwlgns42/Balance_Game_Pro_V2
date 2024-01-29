<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
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
</style>
<title>찜목록</title>
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
		<a href="main.do" class="title">자비스</a>
		<nav>
			<ul>
				<c:if test="${not empty loginId }">
					<li><a href="logout.do" class="active">로그아웃</a></li>
					<li><a href="pwCheckPage.do" class="active">마이페이지</a></li>
				</c:if>
				<c:if test="${empty loginId }">
					<li><a href="loginPage.do" class="active">로그인</a></li>
					<li><a href="pwCheckPage.do" class="active">회원가입</a></li>
				</c:if>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major" style="margin-bottom: 20px;">찜목록</h1>
				<div class="button-container">
				<div class="card-body table-responsive p-0">
					<table class="table table-hover text-nowrap">
						<thead>
							<tr>
								<th>NO</th>
								<th>Title</th>
								<th>Writer</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(sdatas) <= 0 }">
								<tr>
									<td colspan="1">찜목록이 없습니다.</td>
								</tr>
							</c:if>
							<c:forEach var="data" items="${sdatas}" varStatus="loop">
								<tr
									onclick="location.href = 'wishListDetailPage.do?sid=${data.sId}'">
									<td>${loop.index + 1}</td>
									<!-- loop.index는 0부터 시작하므로 +1을 해서 순번을 출력합니다. -->
									<td>${data.saveTitle}</td>
									<td>${data.saveWriter}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%-- 
				
					<c:if test="${empty sdatas}">
						찜목록이 없습니다.
					</c:if>
					<c:forEach var="data" items="${sdatas}">
						<table>
							<thead>
								<tr>
									<th>NO</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<a href="wishListDetailPage.do?sid=${data.sId}">${data.sId}</a>
									</td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<td>${data.saveTitle}</td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<td>${data.saveWriter}</td>
								</tr>
								<br>
							</tbody>
						</table>
					</c:forEach> --%>
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