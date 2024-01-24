<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="is-preload">
	<script type="text/javascript">
	var authNumResult;
	
	console.log("로그1");
	
	console.log(authNumResult);
	
	function authCheck(){
	console.log("로그2");
		var cellPhone = $("#cellPhone").val();
		$.ajax({
			type: "POST",
			url: "authServlet",
			data: {'cellPhone' : cellPhone},
			dataType: "text",
			success: function(data){
				authNumResult = data;
				console.log(authNumResult);
			},
			error: function(error){
				console.log(error);
				document.getElementById("subBtn").disabled = true;
			}
			
		});
		
	}
	console.log("로그3");
</script>
	<script type="text/javascript">

	function authNumCheck(){
		var authNum = $("#authNum").val();
		console.log("["+authNumResult+"]");
		console.log("["+authNum+"]");
		if(authNumResult == authNum){
			console.log("로그4");
			alert('성공');
		}else{
			alert('실패');
		}
	}


</script>

	<%-- Sidebar--%>
	<section id="sidebar">
		<div class="inner">
			<nav>
				<ul>
					<li><a href="#intro">회원가입</a></li>
				</ul>
			</nav>
		</div>
	</section>

	<div id="wrapper">
		<%-- Intro --%>
		<section id="intro" class="wrapper style1 fullscreen fade-up">
			<!-- 회원가입 폼 -->
			<div class="inner">
				<form action="join.do" method="POST" onsubmit="return validateForm()">
					<div class="split style1">

						<section>
							<div class="row gtr-uniform">
								<div class="col-12">
									<label for="name">이름</label>
									<input type="text" name="name" id="name" placeholder="Name" />
								</div>

								<div class="col-12-xsmall">
									<label for="loginId">아이디</label>
									<input type="text" class="loginId" name="loginId" id="loginIdCheck" placeholder="ID" />
									<div>
										<font id="id_feedback" size="2"></font>
									</div>
								</div>


								<div class="col-12">
									<label for="mPw">비밀번호</label>
									<input type="password" name="mPw" id="mPw" placeholder="password" />
								</div>

								<div class="col-12">
									<label for="mpwCh">비밀번호확인</label>
									<input type="password" name="mpwCh" id="mpwCh" placeholder="PasswordCk" />
									<!-- <div id="ok"></div> -->
								</div>

								<div class="col-12">
									<label for="age">나이</label>
									<input type="text" name="age" id="age" placeholder="나이를 입력하세요" oninput="validateAge(this)" />
									<!-- <div id="ok"></div> -->
								</div>

								<div class="col-12">
									<label for="email">이메일</label>
									<input type="email" name="email" id="email" placeholder="Email" />
								</div>

								<div class="col-12">
									<label for="address">주소</label>
									<input type="text" name="address" id="address" placeholder="서울시 땡땡구 땡땡동 132-1번지" />
								</div>

								<div class="col-4 col-12-small">
									<input type="radio" id="male" name="gender" value="M" checked="">
									<label for="male">남</label>
								</div>

								<div class="col-4 col-12-small">
									<input type="radio" id="female" name="gender" value="F">
									<label for="female">여</label>
								</div>

								<div class="col-12">
									<label for="cellPhone">핸드폰번호</label>
									<input type="text" name="cellPhone" id="cellPhone" placeholder="Your PhoneNumber" />
									<button type="button" onclick="authCheck()">인증번호발송</button>
								</div>

								<div class="col-12">
									<label for="authCheck">인증번호</label>
									<input type="text" name="authNum" id="authNum" />
									<button type="button" onclick="authNumCheck()">확인</button>
								</div>

								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="Send Message" class="primary" id="subBtn" /></li>
										<li><input type="reset" value="Reset" /></li>
									</ul>
								</div>
							</div>
						</section>
						<section>
							            
							<ul class="alt">

								<li><textarea name="" class="" style="resize: none;" readonly>여러분을 환영합니다.
네이버 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와 이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
									<ul class="alt">
										<li>
											<div class="col-6 col-12-small">
												<input type="checkbox" id="agreement" name="agreement">
												<label for="agreement">이용약관 동의(필수)</label>
											</div>
										</li>
									</ul></li>

								<li><textarea name="" class="" style="resize: none;" readonly>여러분을 환영합니다.
네이버 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와 이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
									<ul class="alt">
										<li>
											<div class="col-6 col-12-small">
												<input type="checkbox" id="profile" name="profile">
												<label for="profile">개인정보 수집 및 이용에 대한 안내(필수)</label>
											</div>
										</li>
									</ul></li>

								<li><textarea name="" class="" style="resize: none;" readonly>여러분을 환영합니다.
네이버 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와 이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
									<ul class="alt">
										<li><div class="col-6 col-12-small">
												<input type="checkbox" id="demo3" name="demo3">
												<label for="demo3">위치정보 이용약관 동의(선택)</label>
											</div></li>
									</ul></li>

								<li>
									<ul class="alt">

										<li><div class="col-6 col-12-small">
												<input type="checkbox" id="demo4" name="demo4">
												<label for="demo4">이벤트 등 프로모션 알림 메일 수신(선택)</label>
											</div></li>
									</ul>

								</li>

							</ul>

						</section>


					</div>
				</form>
			</div>
			<!-- 회원가입 폼 -->
		</section>
	</div>

	<!-- Footer -->
	<footer id="footer" class="wrapper style1-alt">
		<div class="inner">
			<ul class="menu">
				<li>&copy; Untitled. All rights reserved.</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
	</footer>
	<!-- Scripts -->

	<script>
		
		function validateForm() {
			let loginId = $('#loginId').val();
			let password = $('#mPw').val();
			let passwordCheck = $('#mpwCh').val();
			let name = $('#name').val();
			let email = $('#email').val();
			let address = $('#address').val();
			let age = $('#age').val();
			let genderMale = $('#male').prop('checked');
			let genderFemale = $('#female').prop('checked');
			let agreement = document.getElementById("agreement");
			let profile = document.getElementById("profile");

			// 이름은 한글로 2글자 이상 4글자 이하
			let namePattern = /^[가-힣]{2,4}$/;
			if (!namePattern.test(name)) {
				Swal.fire({
					title : "이름",
					text : "한글로 2글자 이상 4글자 이하로 입력해주세요.",
					icon : "warning"
				});
				return false;
			}
			
			// 아이디는 5글자 이상
			let loginIdPattern = /^[a-zA-Z0-9]{5,10}$/;
			if (!loginIdPattern.test(loginId)) {
				Swal.fire({
					title : "아이디",
					text : "영어로 5글자 이상 10글자 이하로 입력해주세요.",
					icon : "warning"
				});
				return false;
			}
			// 비밀번호는 특수문자, 영어 포함
			let passwordPattern = /^(?=.*[!@#$%^&*(),.?":{}|<>])(?=.*[a-zA-Z]).{6,15}$/;
			if (!passwordPattern.test(password)) {
				Swal.fire({
					title : "비밀번호",
					text : "특수문자와 영어를 포함하여 6자 이상 15자이하로 입력해주세요.",
					icon : "warning"
				});
				return false;
			}

			if (password != passwordCheck) {
				Swal.fire({
					title : "비밀번호",
					text : "비밀번호가 일치하지 않습니다 다시 확인해 주세요.",
					icon : "warning"
				});
				return false;
			}

			// 나이는 1부터 99까지
            if (age < 1 || age > 99 && age != null) {
                Swal.fire({
                  title: "나이",
              	  text: "나이는 1부터 99까지의 숫자로 입력해주세요.",
              	  icon: "warning"
              	});
                return false;
            }
			
			 // 이메일 형식
            let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            if (!emailPattern.test(email)) {
                Swal.fire({
                  title: "이메일",
              	  text: "이메일 형식이 맞지 않습니다.",
              	  icon: "warning"
              	});
                return false;
            }
            
            // 주소는 특정 형식을 갖추어야 함 (예: 서울시 동구 방어동 123-1번지 또는 서울시 동구 방어동 내진길 5길)
            let addressPattern = /^[가-힣\s]+\s?[가-힣\s\d-]+(\s\d+(-\d+)?)?(번지)?(\s[가-힣\s]+(\s[가-힣\s\d-]+(\s\d+(-\d+)?)?(길)?)?)?$/;
            if (!addressPattern.test(address)) {
                Swal.fire({
                	  title: "주소",
                	  text: "주소 형식을 지켜서 입력해주세요.",
                	  icon: "warning"
                	});
                return false;
            }
            
            // 성별 선택 여부 확인
            if (!genderMale && !genderFemale) {
                Swal.fire({
                	  title: "성별",
                	  text: "성별을 선택해주세요.",
                	  icon: "warning"
                	});
                return false;
            }
            
             // 약관 동의 체크 여부 확인
    	    if (!$("#agreement").prop('checked')) {
    	        Swal.fire({
    	            title: "약관 동의",
    	            text: "이용약관에 동의해야 회원가입이 가능합니다.",
    	            icon: "warning"
    	        });
    	        return false;
    	    }

    	    // 개인정보 수집 동의 체크 여부 확인
    	    if (!$("#profile").prop('checked')) {
    	        Swal.fire({
    	            title: "개인정보 수집 동의",
    	            text: "개인정보 수집에 동의해야 회원가입이 가능합니다.",
    	            icon: "warning"
    	        });
    	        return false;
    	    } 
    	    
            // 모든 조건을 통과하면 회원가입 진행
            return true;
		 }

		
		
		// 아이디 입력란에 keyup 이벤트 리스너 등록
		$('#loginIdCheck').keyup(function() {
		    checkDuplicateId();
		});

		// 아이디 중복 확인 함수
		function checkDuplicateId() {
		    let loginId = $('#loginIdCheck').val();
		    if (loginId.length >= 5) {
		        $.ajax({
		            url: "idCheckAsync",
		            type: "POST",
		            data: {
		                loginId: loginId
		            },
		            dataType: 'json',
		            success: function(result) {
		                if (result == 1) {
		                    $("#id_feedback").html('이미 사용중인 아이디입니다.');
		                    $("#id_feedback").css('color', '#dc3545');
		                } else {
		                    $("#id_feedback").html('사용할 수 있는 아이디입니다.');
		                    $("#id_feedback").css('color', '#2fb380');
		                }
		            },
		            error: function(request, status, error) {
		                console.log(status);
		                console.log(error);
		                $("#id_feedback").html('서버 요청 실패');
		                $("#id_feedback").css('color', '#dc3545');
		            }
		        });
		    } else {
		        $("#id_feedback").html('아이디 5글자 이상으로 입력해주세요.');
		        $("#id_feedback").css('color', '#dc3545');
		    }
		}
		 // 나이를 입력할때 숫자만 입력 가능하도록 정규표현식 사용
		function validateAge(input) {
	        input.value = input.value.replace(/[^0-9]/g, '');
	    }
	</script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>
