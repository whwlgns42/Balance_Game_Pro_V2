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
.button-container {
	text-align: left;
	margin-top: 10px;
}

.button-container button {
	margin-left: 5px;
}

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

.stats_graph_box {
	position: relative;
	padding-bottom: 20px;
}

.stats_graph_box .graph {
	position: relative;
	height: 46px;
	background: #f0f0f0;
	border-radius: 4px;
	font-size: 0;
	white-space: nowrap;
	overflow: hidden;
}

.stats_graph_box .graph:after {
	content: '';
	display: block;
	clear: both;
}

.stats_graph_box .graph .bar {
	position: relative;
	display: inline-block;
	height: 46px;
	background: #ddd;
	border-radius: 4px;
	overflow: hidden;
}

.stats_graph_box .graph .bar+.bar {
	margin-left: -5px;
	padding-right: 5px;
	box-sizing: content-box;
	border-radius: 0 4px 4px 0;
}

.stats_graph_box .graph .bar.green {
	z-index: 1;
	background: #14d57e;
}

.stats_graph_box .graph .bar.pink {
	background: #e750b0;
}

.stats_graph_box .graph .bar.clear {
	background: transparent;
}

.stats_graph_box .graph .desc {
	position: absolute;
	top: 50%;
	left: 50%;
	margin: 0;
	text-align: center;
	font-size: 12px;
	line-height: 16px;
	color: #fff;
	transform: translate(-50%, -50%);
}

.stats_graph_box .graph .desc em {
	font-style: normal;
	font-size: 16px;
	font-weight: 700;
}

.stats_graph_box .graph .desc dd {
	margin: 0;
}

.stats_graph_box .fixed_data {
	position: absolute;
	top: 52px;
	width: 120px;
	margin: 0;
	text-align: center;
	font-size: 10px;
	color: #000;
}

.stats_graph_box .fixed_data:before {
	content: '';
	position: absolute;
	top: -53px;
	left: 50%;
	width: 1px;
	height: 50px;
	border-left: 1px dashed #bababa;
}

.stats_graph_box .fixed_data .item {
	float: left;
}

.stats_graph_box .fixed_data .item+.item dt {
	padding-left: 8px;
}

.stats_graph_box .fixed_data .item dt:before {
	content: '/';
	position: absolute;
	top: 0;
	left: 2px;
}

.stats_graph_box .fixed_data .item dd:before {
	content: '/';
	position: absolute;
	top: 2px;
	left: -1px;
}

.stats_graph_box .fixed_data dt {
	position: relative;
}

.stats_graph_box .fixed_data dd {
	margin: 0;
	position: relative;
}

.stats_graph_box .fixed_data em {
	font-style: normal;
	font-size: 13px;
	font-weight: 700;
}
/* 찜 이미지 */
.major img {
	max-width: 50px; /* 이미지의 최대 너비 */
	max-height: 50px; /* 이미지의 최대 높이 */
	margin-left: 10px; /* 오른쪽 이동 */
	margin-top: 10px; /* 밑으로 이동 */
	width: auto;
	/* 너비를 자동으로 조정하여 비율을 유지합니다. */
	height: auto;
	width: auto; /* 높이를 자동으로 조정하여 비율을 유지합니다. */
}
</style>
<title>문제 상세 페이지</title>
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
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major" style="margin-bottom: 20px;">
					문제 상세 페이지
					<input id="qId" type="hidden" value="${qData.qId}" />
					<c:if test="${qData.save > 0}">
						<img class="save" id="${qData.qId}" alt="찜이 되어있습니다 " src="images/찜o.png">
					</c:if>
					<c:if test="${qData.save <= 0}">
						<img class="save" id="${qData.qId}" alt="찜이 안되어있습니다 " src="images/찜x.png">
					</c:if>
				</h1>
				<div class="card-body table-responsive p-0">
					<table class="table table-hover text-nowrap" style="margin-bottom: 10px;">
						<thead>
							<tr>
								<th>Title</th>
								<th>Reason</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty qData}">
									<tr>
										<td colspan="4">출제된 문제가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td>${qData.title}</td>
										<td>${qData.explanation}</td>
									</tr>
								</c:otherwise>
							</c:choose>

						</tbody>
					</table>
					<table class="table table-hover text-nowrap" style="margin-bottom: 10px;">
						<thead>
							<tr>
								<th>Answer_A</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty qData}">
									<tr>
										<td colspan="4">출제된 문제가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td>${qData.answer_A}</td>
									</tr>
								</c:otherwise>
							</c:choose>

						</tbody>
					</table>

					<table class="table table-hover text-nowrap" style="margin-bottom: 10px;">
						<thead>
							<tr>
								<th>Answer_B</th>
							</tr>
						</thead>

						<tbody>
							<c:choose>
								<c:when test="${empty qData}">
									<tr>
										<td colspan="4">출제된 문제가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td>${qData.answer_B}</td>
									</tr>
								</c:otherwise>
							</c:choose>

						</tbody>
					</table>

					<div class="stats_graph_box">
						<div class="graph">
							<div id="barA" class="bar green">
								<dl class="desc">
									<dt>Answer_A</dt>
									<dd>
										<em id="percentA">0%</em>
									</dd>
								</dl>
							</div>
							<div id="barB" class="bar pink">
								<dl class="desc">
									<dt>Answer_B</dt>
									<dd>
										<em id="percentB">0%</em>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<table class="table table-hover text-nowrap" style="margin-bottom: 10px;">
						<thead>
							<tr>
								<th>Answer_A</th>
								<th>Answer_B</th>
							</tr>
						</thead>
						<tbody>



							<tr>
								<td>${qData.answerCntA}</td>
								<td>${qData.answerCntB}</td>
							</tr>



						</tbody>
					</table>
					<table class="table table-hover text-nowrap">
						<thead>
							<tr>
								<th>번호</th>
								<th>아이디</th>
								<th>댓글내용</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty cDatas}">
								<tr>
									<td colspan="1">작성된 댓글이 없습니다.</td>
								</tr>
							</c:if>

							<c:forEach var="data" items="${cDatas}" varStatus="loop">
								<tr>
									<td>${loop.index + 1}</td>
									<td>${data.loginId}</td>
									<td>${data.content}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="stats_graph_box"></div>

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
	<script src="js/save.js"></script>
	<script>
		// Assume qData.answerCntA and qData.answerCntB are available from the server
		const countA = parseInt("${qData.answerCntA}");
		const countB = parseInt("${qData.answerCntB}");

		// Calculate percentages
		const total = countA + countB;
		const percentA = (countA / total) * 100;
		const percentB = (countB / total) * 100;

		// Set widths based on percentages
		document.getElementById('barA').style.width = percentA + '%';
		document.getElementById('barB').style.width = percentB + '%';

		// Update percentages
		document.getElementById('percentA').innerText = percentA.toFixed(2)
				+ '%';
		document.getElementById('percentB').innerText = percentB.toFixed(2)
				+ '%';
	</script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</body>
</html>