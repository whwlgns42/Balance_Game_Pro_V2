<%@page import="model.question.QuestionDTO"%>
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
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
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

#save {
	width: 40px;
	height: 40px;
}

#title {
	
}

#title h1 {
	display: inline-block;
}

#answer_A, #answer_B {
	border: 50px;
	border-color: black;
	border-radius: 0;
	color: blue;
	cursor: auto;
	display: inline-block;
	font-size: 50px;
	font-weight: 200px;
	width: 200px;
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

#answer_A :hover {
	background-color: #6DD66D;
}

#answer_B :hover {
	background-color: #FFB900;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$(".save").on("click", function() {
			console.log("[성공]");
			var loginId='<%=session.getAttribute("loginId")%>';
			'<%
					QuestionDTO data =(QuestionDTO) request.getAttribute("data");
				%>';
				var qId='<%=data.getqId()%>';
			
			if ( loginId== "null") {
				console.log("[로그]로그인 x");
				alert('로그인이 필요합니다');
				//location.href='loginPage.do';
			} 
			else if(loginId != "null"){

				console.log("[로그] 로그인 o");
				//요소 값 가져오기
				//https://luahius.tistory.com/158
				$.ajax({
					type : "POST",
					url : "SaveAsync.do",
					data : {
						'loginId' :loginId,'qId' : qId},
					dataType : 'text',
					success : function(data) {
						console.log(data);
						if(data=="실패"){
							//console.log("실패");
						}else{
							$(".save").attr("src", "images/"+data);
						}
						
						//document.getElementById(".save").src="images/찜o.png";
					},
					error : function(error) {
						console.log('에러발생');
						console.log('에러의 종류:' + error);
					}

				});
			} 
		});

	});
</script>
</head>
<body class="is-preload">
	<%
	String loginData = (String) session.getAttribute("loginId");
	QuestionDTO qDTO = (QuestionDTO) request.getAttribute("data");
	%>
	<!-- Header -->
	<header id="header">
		<a href="main.do" class="title">자비스</a>
		<nav>
			<%
			if (loginData == null) {
			%>
			<ul>
				<li><a href="loginPage.do">로그인</a></li>
				<li><a href="joinPage.do" class="active">회원가입</a></li>
			</ul>
			<%
			} else {
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
		<h3 class="major">밸런스 게임</h3>
		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<div id="title">
					<h1><%=qDTO.getTitle()%></h1>
					 <%
					//System.out.println(qDTO.isSave());
					System.out.println("Game : " + qDTO.getSave());
					if (qDTO.getSave() <= 0) {
						%>
					<img class="save" src="images/찜x.png" alt="찜이 안되어 있습니다">
					<%
					} else {
					%>
					<img class="save" src="images/찜o.png" alt="찜이 되어 있습니다">
					<%
					}
					%> 
					<!-- <img id="save" src="images/찜x.png" alt="찜이 되어 있습니다"> -->
				</div>
				<!-- <span class="image "><img src="images/pic09.jpg" alt="" /></span> -->
				<!-- <button class="like-button" onclick="toggleLike(2)">찜하기</button> -->



				<div class="inner">
					<button id="answer_A" onclick="">
						<%=qDTO.getAnswer_A()%>
						
					</button>
					<button id="answer_B" onclick="">
					<%=qDTO.getAnswer_B()%>
						
					</button>

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