<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>


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

.table img {
	max-width: 30px; /* 이미지의 최대 너비 */
	max-height: 30px; /* 이미지의 최대 높이 */
	width: auto; /* 너비를 자동으로 조정하여 비율을 유지합니다. */
	height: auto; /* 높이를 자동으로 조정하여 비율을 유지합니다. */
}
/* 문제제목, 찜이미지 커서 모양 */
.qTitle, .save { 
cursor : pointer;
}

</style>
<title>문제 목록</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="assets/css/main.css" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">
	<input type="hidden" id=loginId value="${loginId}" />
	<input type="hidden" id=page value="titlePage" />
	<!-- Header -->
	<header id="header">
		<common:logo></common:logo>
		<nav>
			<ul>
				<c:if test="${not empty loginId }">
					<li><a href="logout.do" class="active">로그아웃</a></li>
					<li><a href="pwCheckPage.do" class="active">마이페이지</a></li>
				</c:if>
				<c:if test="${empty loginId }">
					<li><a href="loginPage.do" class="active">로그인</a></li>
					<li><a href="joinPage.do" class="active">회원가입</a></li>
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
								<th>찜</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(qDatas) <= 0 }">
								<tr>
									<td colspan="1">출제된 문제가 없습니다.</td>
								</tr>
							</c:if>
							<c:forEach var="data" items="${qDatas}" varStatus="loop">
								<tr>
									<td onClick="location.href='titleDetailPage.do?qid=${data.qId}&writer=${loginId}'">${loop.index + 1}</td>
									<!-- loop.index는 0부터 시작하므로 +1을 해서 순번을 출력합니다. -->
									<td class="qTitle" onClick="location.href='titleDetailPage.do?qid=${data.qId}&writer=${loginId}'">${data.title}</td>
									<td>
										<input id="qId" type="hidden" value="${data.qId}" />
										<c:if test="${data.save > 0}">
											<img class="save" id="${data.qId}" alt="찜이 되어있습니다 " src="images/찜o.png">
										</c:if>
										<c:if test="${data.save <= 0}">
											<img class="save" id="${data.qId}" alt="찜이 안되어있습니다 " src="images/찜x.png">
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
	<script src="js/save.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</body>
</html>