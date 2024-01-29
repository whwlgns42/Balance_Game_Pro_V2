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

<script type="text/javascript">
	$(document).ready(function() {
		$("#comment").hide();
		//if(${data.qId}=)
		//질문 pk없을시 메인으로
		var qId = ${data.qId};
		var loginId = "${loginId}";
		
		
		$(".save").on("click", function() {
			console.log("[성공]");
			
			
			console.log(loginId);
			if (loginId == "") {
				console.log("[로그]로그인 x");
				alert('로그인이 필요합니다');
				//location.href='loginPage.do';
			} else {

				console.log("[로그] 로그인 o");
				//요소 값 가져오기
				//https://luahius.tistory.com/158
				$.ajax({
					type : "POST",
					url : "saveAsync.do",
					data : {
						'loginId' : loginId,
						'qId' : qId
					},
					dataType : 'text',
					success : function(data) {
						console.log(data);
						if (data == "실패") {
							console.log("실패");
						} else {
							$(".save").attr("src", "images/" + data);
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

		$(".answer").on("click", function() {
			console.log($(this).prop("value"));
			$.ajax({
				type : "POST",
				url : "answerAsync.do",
				data : {
					'qId' : qId,
					'loginId' : loginId,
					'answer' : $(this).prop('value')
				},
				dataType : 'json',
				success : function(data) {
					console.log(data.answerCntA);
					console.log(data.answerCntB);
					var total=data.answerCntA+data.answerCntB;
					console.log(total);
					$("#answer_A").text(Math.round(((data.answerCntA*1.0)/total)*100)+"%");									
					$("#answer_B").text(Math.round(((data.answerCntB*1.0)/total)*100)+"%");									
				},
				error : function(error) {
					
					console.log('에러발생');
					console.log('에러의 종류:' + error);
				}

			});

			
			$(".answer").css("height", "100px");
			$(".answer").css("line-height", "100px");
			$(".answer").css("font-size", "30px");
			$(".answer").css("transition", "1000ms");
			$(".answer").attr("disabled",true);
			
			$("#title h1").css("font-size", "30px");
			$("#title h1").css("transition", "1000ms");

			$(".save").css("width", "30px");
			$(".save").css("height", "30px");
			$(".save").css("transition", "1000ms");

			
			
			$.ajax({
				type : "POST",
				url : "commentAsync.do",
				data : {
					'qId' : qId
				},
				dataType : 'json',
				success : function(data) {
					
					var elem = "";
					$.each(data, function(index,data) {
 						elem +="<tr> <td>";
						if(typeof data.loginId  != "undefined"){
							if(data.grade == 1){
								elem +="<img src='images/blackStone.png' alt='등급1에 주는 블랙스톤' width='25' height ='25' />";
							}else if(data.grade ==2){
								elem +="<img src='images/silverStone.png' alt='등급2에 주는 실버스톤' width='25' height ='25' />";
							}else if(data.grade == 3){
								elem +="<img src='images/goldStone.png' alt='등급3에 주는 골드스톤' width='25' height ='25' />";
							}else if(data.grade ==4){
								elem +="<img src='images/blueStone.png' alt='등급4에 주는 플래티넘스톤' width='25' height ='25' />";
							}
 							elem += data.memberName+"( "+data.loginId+" )</td>";
						}else{
							elem +="<td>탈퇴한 사용자</td>"
						}
 						elem +="<td>"+data.content+"</td>";
						elem +="</tr>"; 
						console.log(data.name);
					});
					$("table tbody").append(elem);
					//document.getElementById(".save").src="images/찜o.png";
				},
				error : function(error) {
					
					console.log('에러발생');
					console.log('에러의 종류:' + error);
				}

			});
			
			
			$("#comment").show();
		});
		var isRun = false;
		$("#write").on("click", function() {
			console.log("댓글 입력");
			var content= $('#inputContent').val();
			//$('#inputContent').val('');
			 if(isRun == true) {
			        return;
			    }
			 isRun = true;
			
		
			
			
			$('#apple').html('<input type="text" placeholder="댓글을 입력하세요" id="inputContent">'); ////
		
			console.log('확인1: '+content);
			if(content){
			$.ajax({
				type : "POST",
				url : "commentWriteAsync.do",
				data : {
					'qId' : qId,
					'loginId' : loginId,
					'comment' : content
					
				},
				dataType : 'json',
				success : function(data) {								

					var elem = "";
					
 					elem +="<tr> <td>";
					if(data.grade==1){
						elem += "<img src= 'images/blackStone.png' alt='등급1에 주는 블랙스톤' width='25' height ='25' />";
					}else if(data.grade==2){
						elem += "<img src= 'images/silverStone.png' alt='등급1에 주는 블랙스톤' width='25' height ='25' />";
					}else if(data.grade == 3){
						elem +="<img src='images/goldStone.png' alt='등급3에 주는 골드스톤' width='25' height ='25' />";
					}else if(data.grade ==4){
						elem +="<img src='images/blueStone.png' alt='등급4에 주는 플래티넘스톤' width='25' height ='25' />";
					}
 					elem += data.memberName+"( "+data.loginId+" )</td>";
						
 					elem +="<td>"+data.content+"</td>";
					elem +="</tr>"; 
					console.log('확인2: '+data.name);
					if($("#noComment").length>0){	
						$("#noComment").text("");
					}
					$("table tbody").append(elem);
					
					 isRun  = false;
					//document.getElementById(".save").src="images/찜o.png";
				},
				error : function(error) {

					console.log('에러발생');
					console.log('에러의 종류:' + error);
				}

			});
			
			}
			
		});
		
		$("#inputContent").on("keydown", function(e) {
			console.log(e.code);
			if(e.code=='Enter' || e.code=='NumpadEnter'){
				console.log("엔터침"+e.code);
				$("#write").click();
			}
		});
		
		console.log(${list});
		$("#next").on("click", function() {
			
			//localStorage.setItem("list",${list});
			location.href='gamePage.do';
			
		});
		
	});
</script>
</head>
<body class="is-preload">
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
		<h3 class="major">밸런스 게임</h3>
		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<div id="title">
					<h1>${data.title}</h1>

					<c:if test="${data.save <= 0}">
						<img class="save" src="images/찜x.png" alt="찜이 안되어 있습니다">
					</c:if>
					<c:if test="${data.save > 0}">
						<img class="save" src="images/찜o.png" alt="찜이 되어 있습니다">
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
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>