<%@page import="kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext"%>
<%@page import="model.question.QuestionDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>게임 페이지</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
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

.major {
	margin-top: 50px;
	margin-left: 50px;
}

.save {
	width: 50px;
	height: 50px;
}

#title {
	
}

#title h1 {
	display: inline-block;
}

#answer_A, #answer_B {
	/* 	padding-left:20px;
	padding-right:20px; */
	border: 50px;
	border-color: black;
	border-radius: 0;
	color: blue;
	cursor: auto;
	display: inline-block;
	font-size: 30px;
	font-weight: 100px;
	/* width: 200px; */
	min-width: 200px;
	height: 200px;
	letter-spacing: normal;
	line-height: normal;
	outline: 100px;
	padding: 0;
	position: static;
	text-align: center;
	text-decoration: none;
	text-transform: none;
	white-space: normal;
}

#answer_A:hover {
	background-color: #6DD66D;
}

#answer_B:hover {
	background-color: #FFB900;
}

ul.actions #input {
	width: 70%;
}

ul.actions {
	margin: 0 auto;
}

#comment {
	width: 60%;
	margin: 0 auto;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="js/gameSet.js"></script>
</head>
<body class="is-preload">
	<input id="qId" type="hidden" value="${data.qId}">
	<input id="loginId" type="hidden" value="${loginId}">
	<input id="page" type="hidden" value="gamePage"/>
	<%-- 	<%
	String loginData = (String) session.getAttribute("loginId");
	QuestionDTO qDTO = (QuestionDTO) request.getAttribute("data");
	%> --%>
	<!-- Header -->
	<header id="header">
		<common:logo></common:logo>
		<nav>
			<c:if test="${loginId ==null}">
				<ul>
					<li><a href="loginPage.do" class="active">로그인</a></li>
					<li><a href="joinPage.do" class="active">회원가입</a></li>
				</ul>
			</c:if>
			<c:if test="${loginId !=null}">
				<ul>
					<li><a href="logout.do" class="active">로그아웃</a></li>
					<li><a href="pwCheckPage.do" class="active">마이페이지</a></li>
				</ul>
			</c:if>

		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">
		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<div id="title">
					<h1>${data.title}</h1>

					<c:if test="${data.save <= 0}">
						<img class="save" id="${data.qId}" src="images/찜x.png" alt="찜이 안되어 있습니다">
					</c:if>
					<c:if test="${data.save > 0}">
						<img class="save" id="${data.qId}" src="images/찜o.png" alt="찜이 되어 있습니다">
					</c:if>
					<!-- <img id="save" src="images/찜x.png" alt="찜이 되어 있습니다"> -->
				</div>
				<!-- <span class="image "><img src="images/pic09.jpg" alt="" /></span> -->
				<!-- <button class="like-button" onclick="toggleLike(2)">찜하기</button> -->



				<div class="inner">
					<button class="answer" id="answer_A" type="button" value="A">${data.getAnswer_A()}</button>
					<button class="answer" id="answer_B" type="button" value="B">${data.getAnswer_B()}</button>

				</div>
			</div>

		</section>

	</div>

	<div id="wrapper">
		<div class="inner" id="comment">

			<!-- 		<button id="prev">이전</button> -->

			<button id="next">다음</button>



			<c:if test="${loginId !=null}">


				<ul class="actions">
					<li id="input"><div id='apple'>
							<input type="text" placeholder="댓글을 입력하세요" id="inputContent">
						</div></li>
					<li><button class="button" id="write">작성</button></li>
				</ul>

			</c:if>

			<div class="table-wrapper" id="table">
				<table>
					<thead>
						<tr>
							<th>Name(LoginId)</th>
							<th>CONTENT</th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
				<div id='noComment'>출력할 댓글이 없습니다</div>
			</div>
		</div>
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

	<script src="js/commentAll.js"></script>
	<script src="js/commentInsert.js"></script>
	<script src="js/gameButtonClick.js"></script>
	<script src="js/save.js"></script>
	<script src="js/answer.js"></script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>