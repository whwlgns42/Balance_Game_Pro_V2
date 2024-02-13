<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="term"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="is-preload">

	<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
   // 주소검색을 수행할 팝업 페이지를 호출합니다.
   // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
   var pop = window.open("addressPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
   // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadAddrPart1,addrDetail){
      // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
      
      var address = roadAddrPart1 + " " + addrDetail;
      
      document.joinForm.address.value = address;
      
      /** 2017년 2월 추가제공 **/
   
      /** 2017년 3월 추가제공 **/
      
}

</script>
	<%-- Sidebar--%>
	<section id="sidebar">
		<div class="inner">
			<a style="text-decoration: none; border: none;" href="main.do"><img style="margin-right: 200px; margin-top: -20px;" src="images/logo.png" height="150" width="150"></a>
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
				<form action="join.do" method="POST" name="joinForm" onsubmit="return validateForm()">
					<div class="split style1">

						<section>
							<div class="row gtr-uniform">
								<div class="col-12">
									<label for="name">이름</label>
									<input type="text" name="name" id="name" placeholder="Name" value="테스트" />
								</div>

								<div class="col-12-xsmall">
									<label for="loginId">아이디</label>
									<input type="text" class="loginId" name="loginId" id="loginIdCheck" placeholder="ID" value="asdff" />
									<div>
										<font id="id_feedback" size="2"></font>
									</div>
								</div>


								<div class="col-12">
									<label for="mPw">비밀번호</label>
									<input type="password" name="mPw" id="mPw" placeholder="password" value="123qwe123!@#" />
								</div>

								<div class="col-12">
									<label for="mpwCh">비밀번호확인</label>
									<input type="password" name="mpwCh" id="mpwCh" placeholder="PasswordCk" value="123qwe123!@#" />
								</div>

								<div class="col-12">
									<label for="age">나이</label>
									<input type="text" name="age" id="age" placeholder="나이를 입력하세요" oninput="this.value=this.value.replace(/[^0-9]/g,'');" value="12" />
								</div>

								<div class="col-12">
									<label for="email">이메일</label>
									<input type="email" name="email" id="email" placeholder="Email" value="asgsd@naver.com" />
								</div>

								<div class="col-12">
									<label for="address">주소</label>
									<input type="text" name="address" id="address" readonly />
								</div>

								<div class="col-12">
									<input type="button" onClick="goPopup();" value="주소찾기" />
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
									<input type="text" name="cellPhone" id="cellPhone" placeholder="Your PhoneNumber" oninput="this.value=this.value.replace(/[^0-9]/g,'');" />
									<button style="margin-top: 22px;" type="button" onclick="sendAuthNum()">인증번호발송</button>
								</div>

								<div class="col-12">
									<label for="authCheck">인증번호</label>
									<input type="text" name="authNum" id="authNum" oninput="this.value=this.value.replace(/[^0-9]/g,'');" />
									<button style="margin-top: 22px;" type="button" onclick="authNumCheck()">확인</button>
								</div>

								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="가입하기" /></li>
										<li><input type="reset" value="리셋" /></li>
									</ul>
								</div>
							</div>
						</section>
						<section>
							            
							<ul class="alt">
								<!-- 회원가입 이용약관 -->
								<common:terms></common:terms>
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
	<common:footer></common:footer>

	<!-- Scripts -->

	<script>
	
	// 사용자가 제출한 폼을 유효성 검사하는 함수
	function validateForm() {
	    // 입력된 값 가져오기
	    let loginId = $('#loginId').val(); // 아이디
	    let password = $('#mPw').val(); // 비밀번호
	    let passwordCheck = $('#mpwCh').val(); // 비밀번호 확인
	    let name = $('#name').val(); // 이름
	    let email = $('#email').val(); // 이메일
	    let address = $('#address').val(); // 주소
	    let age = $('#age').val(); // 나이
	    let genderMale = $('#male').prop('checked'); // 남성 체크 여부
	    let genderFemale = $('#female').prop('checked'); // 여성 체크 여부
	    let agreement = document.getElementById("agreement"); // 이용약관 동의 체크박스
	    let profile = document.getElementById("profile"); // 개인정보 수집 동의 체크박스
	    let cellPhone = $("#cellPhone").val(); // 핸드폰 번호
	    let authNumCk = $("#authNum").val(); // 입력된 인증번호

	    // 이름 유효성 검사
	    let namePattern = /^[가-힣]{2,4}$/; // 이름은 한글로 2글자 이상 4글자 이하
	    if (!namePattern.test(name)) {
	        showError("이름", "한글로 2글자 이상 4글자 이하로 입력해주세요.");
	        return false;
	    }

	    // 아이디 유효성 검사
	    let loginIdPattern = /^[a-zA-Z0-9]{5,10}$/; // 아이디는 5글자 이상
	    if (!loginIdPattern.test(loginId)) {
	        showError("아이디", "영어로 5글자 이상 10글자 이하로 입력해주세요.");
	        return false;
	    }

	    // 비밀번호 유효성 검사
	    let passwordPattern = /^(?=.*[!@#$%^&*(),.?":{}|<>])(?=.*[a-zA-Z]).{6,15}$/; // 특수문자와 영어를 포함하여 6자 이상 15자 이하
	    if (!passwordPattern.test(password)) {
	        showError("비밀번호", "특수문자와 영어를 포함하여 6자 이상 15자 이하로 입력해주세요.");
	        return false;
	    }

	    // 비밀번호 일치 확인
	    if (password != passwordCheck) {
	        showError("비밀번호", "비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
	        return false;
	    }

	    // 나이 유효성 검사
	    if (age < 1 || age > 99 && age != null) {
	        showError("나이", "나이는 1부터 99까지의 숫자로 입력해주세요.");
	        return false;
	    }

	    // 이메일 유효성 검사
	    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/; // 이메일 형식
	    if (!emailPattern.test(email)) {
	        showError("이메일", "이메일 형식이 맞지 않습니다.");
	        return false;
	    }

	    // 주소 입력 확인
	    if (!address) {
	        showError("주소", "주소를 입력해주세요.");
	        return false;
	    }

	    // 성별 선택 확인
	    if (!genderMale && !genderFemale) {
	        showError("성별", "성별을 선택해주세요.");
	        return false;
	    }

	    // 핸드폰 번호 유효성 검사
	    let phonePattern = /^01[016789]\d{8}$/; // 숫자로만 이루어진 11자리 번호
	    if (!phonePattern.test(cellPhone)) {
	        showError("핸드폰번호", "숫자만 입력 가능합니다.");
	        return false;
	    }

	    // 이용약관 동의 확인
	    if (!agreement.checked) {
	        showError("약관 동의", "이용약관에 동의해야 회원가입이 가능합니다.");
	        return false;
	    }

	    // 개인정보 수집 동의 확인
	    if (!profile.checked) {
	        showError("개인정보 수집 동의", "개인정보 수집에 동의해야 회원가입이 가능합니다.");
	        return false;
	    }

	    // 핸드폰 번호 입력 확인
	    if (!cellPhone) {
	        showError("휴대폰 번호", "휴대폰 번호를 입력해주세요");
	        return false;
	    }

	    // 인증번호 확인
	    if (authStatus <= 0 ) { // authStatus: 인증 성공 여부
	        showError("인증확인", "인증번호가 일치하지 않습니다. 다시 확인해주세요");
	        return false;
	    }

	    // 인증번호 미 입력시 예외처리
	    if (!authNumCk) {
	        showError("인증확인", "인증번호를 입력해주세요");
	        return false;
	    }        

	    // 아이디 중복체크 확인여부 
	    if (useIdCheck == 0) {
	        showError("중복", "중복된 아이디 입니다");
	        return false;
	    }

	    return true; // 모든 유효성 검사 통과
	}

	// 경고 메세지 출력 함수
	function showError(title, text) {
	    Swal.fire({
	        title: title,
	        text: text,
	        icon: "warning"
	    });
	}
	
	let authAttempts = 0; // 인증 시도 횟수
 	let cooldownTime = 180; // 3분 (초 단위)
	let cooldownTimer; // 타이머 변수
	// 성공 메세지 출력 함수
	function showSuccess(title, text) {
	    Swal.fire({
	        title: title,
	        text: text,
	        icon: "success"
	    });
	}

	// 아이디 중복 체크 이벤트 리스너
	$('#loginIdCheck').keyup(function () {
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
	            success: function (result) {
	                let feedback = $("#id_feedback");
	                if (result == 1) {
	                    useIdCheck = 0;
	                    showIdFeedback('이미 사용중인 아이디입니다.', '#dc3545');
	                } else {
	                    useIdCheck = 1;
	                    showIdFeedback('사용할 수 있는 아이디입니다.', '#2fb380');
	                }
	            },
	            error: function (request, status, error) {
	                showIdFeedback('서버 요청 실패', '#dc3545');
	            }
	        });
	    } else {
	        showIdFeedback('아이디 5글자 이상으로 입력해주세요.', '#dc3545');
	    }
	}

	// 아이디 중복 체크 알림 메세지 함수
	function showIdFeedback(message, color) {
	    let feedback = $("#id_feedback");
	    feedback.html(message);
	    feedback.css('color', color);
	    feedback.css('font-size', '20px');
	}

	// 인증번호 발송 함수
	function sendAuthNum() {
	    let cellPhone = $("#cellPhone").val();
	    let phonePattern = /^01[016789]\d{8}$/;

	    if (!cellPhone || !phonePattern.test($("#cellPhone").val())) {
	        showError("인증확인", "휴대폰번호를 제대로 입력해주세요");
	        return false;
	    } else {
	        if (authAttempts >= 3) {
	            showError("인증확인", "인증번호 발송이 3회 이상 시도되었습니다. 3분 후에 다시 시도해주세요.");
	            return false;
	        }
	        $.ajax({
	            type: "POST",
	            url: "authServlet",
	            data: {'cellPhone': cellPhone},
	            dataType: "text",
	            success: function (data) {
	                authNum = data;
	                showSuccess("발송완료", "인증번호를 발송했습니다.");
	                authAttempts++; // 인증문자 발송 횟수 증가
	                
	                // 3분 타이머 설정
	                cooldownTimer = setInterval(function () {
	                    cooldownTime--;
	                    if (cooldownTime <= 0) {
	                        clearInterval(cooldownTimer);
	                        cooldownTime = 180; // 타이머 초기화
	                        authAttempts = 0; // 시도 횟수 초기화
	                    }
	                }, 1000);
	            },
	            error: function (error) {
	                console.log(error);
	            }
	        });
	    }
	}

	// 인증번호 확인 함수
	function authNumCheck() {
	    let authNumCk = $("#authNum").val();
	    if (authNum == authNumCk) {
	        showSuccess("인증확인", "인증번호가 확인되었습니다.");
	        authStatus = 1; // 인증번호 확인 성공시 회원가입 가능 상태값 부여
	    } else {
	        authStatus = 0; // 인증번호 확인 실패시 회원가입 불가능 상태값 부여
	        showError("인증확인", "인증번호가 일치하지 않습니다. 다시 확인해주세요");
	    }
	}

</script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</body>
</html>