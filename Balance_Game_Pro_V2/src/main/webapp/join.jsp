<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <form action="join.do" method="POST" onsubmit="return validateForm()">
        아이디 <input type="text" name="loginId" id="loginIdCheck" maxlength="10"> <br>
        <div>
            <font id="id_feedback" size="2"></font>
        </div>
        비번 <input type="password" name="mPw" id="password"  maxlength="15"> <br>
        비번확인 <input type="password" name="mpwCh" id="passwordCh"  maxlength="15"> <br>
        이름 <input type="text" name="name" id="name" maxlength="3"> <br>
        이메일 <input type="email" name="email" id="email"> <br>
        주소 <input type="text" name="address" id="address"> 예) 서울시 땡땡구 땡땡동 132-1번지, 서울시 땡땡구 가나길 2<br> 
         <input type="radio" id="male" name="gender" value="M">
  		<label for="male">남</label><br>
  		<input type="radio" id="female" name="gender" value="F">
  		<label for="female">여</label><br>
        나이 <input type="number" name="age" id="age"> <br>
        <input type="submit" value="회원가입">
    </form>

    <script type="text/javascript">
        function validateForm() {
            let loginId = $('#loginIdCheck').val();
            let password = $('#password').val();
            let passwordCheck = $('#passwordCh').val();
            let name = $('#name').val();
            let email = $('#email').val();
            let address = $('#address').val();
            let age = $('#age').val();
            let genderMale = $('#male').prop('checked');
            let genderFemale = $('#female').prop('checked');

            // 아이디는 5글자 이상
            let loginIdPattern = /^[a-zA-Z0-9]{5,10}$/;
            if (!loginIdPattern.test(loginId)) {
                Swal.fire({
                	  title: "아이디",
                	  text: "영어로 5글자 이상 10글자 이하로 입력해주세요.",
                	  icon: "warning"
                	});
                return false;
            }

            // 비밀번호는 특수문자, 영어 포함
            let passwordPattern = /^(?=.*[!@#$%^&*(),.?":{}|<>])(?=.*[a-zA-Z]).{6,15}$/;
            if (!passwordPattern.test(password)) {
                Swal.fire({
                  title: "비밀번호",
              	  text: "특수문자와 영어를 포함하여 6자 이상 15자이하로 입력해주세요.",
              	  icon: "warning"
              	});
                return false;
            }
            if (password != passwordCheck) {
                Swal.fire({
                  title: "비밀번호",
              	  text: "비밀번호가 일치하지 않습니다 다시 확인해 주세요.",
              	  icon: "warning"
              	});
                return false;
            }

            // 이름은 한글로 2글자 이상 4글자 이하
            let namePattern = /^[가-힣]{2,4}$/;
            if (!namePattern.test(name)) {
                Swal.fire({
                	  title: "이름",
                	  text: "한글로 2글자 이상 4글자 이하로 입력해주세요.",
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

            // 나이는 1부터 99까지
            if (age < 1 || age > 99) {
                Swal.fire({
                  title: "나이",
              	  text: "나이는 1부터 99까지의 숫자로 입력해주세요.",
              	  icon: "warning"
              	});
                return false;
            }

            // 모든 조건을 통과하면 회원가입 진행
            return true;
        }

        $('#loginIdCheck').keyup(function() {
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
                            $("#id_feedback").attr('color', '#dc3545');
                        } else {
                            $("#id_feedback").html('사용할 수 있는 아이디입니다.');
                            $("#id_feedback").attr('color', '#2fb380');
                        }
                    },
                    error: function(request, status, error, result) {
                        console.log(status);
                        console.log(result)
                        $("#id_feedback").html('서버요청 실패');
                        $("#id_feedback").attr('color', '#2fb380');
                    }
                });
            } else {
                $("#id_feedback").html('아이디 5글자 이상으로 가입해주세요.');
                $("#id_feedback").attr('color', '#2fb380');
            }
        })
    </script>
</body>
</html>
 --%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%--  <jsp:useBean class="idCk.IdCkBean" id="ck" />
    <jsp:setProperty property="ck" name="id"/> --%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta charset="utf-8" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
   <link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="is-preload">

   <script>
      function myPw() {

         var div = document.getElementById('ok');
         var value = joinForm.mpwCk.value;
         if (value == joinForm.mpw.value) {
            div.textContent = '비밀번호가 일치합니다';//텍스트 노드만들기

         } else {
            div.textContent = '비밀번호가 다릅니다';
         }

         if (value == "") {
            div.textContent = '';
         }

      }
      window.onload = function() {

         document.joinForm.onsubmit = function() {

            var regId = /^[a-zA-Z0-9_]{4,}$/;
            var regIdPw = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
            var regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
            var agreement = document.getElementById("agreement");
            var profile = document.getElementById("profile");

            if (!regId.test(mid.value)) { // 아이디 영어 대소문자 확인
               alert('잘못된 아이디 형식입니다, 4~12자 영문 대소문자와 숫자만 입력해주세요!')
               mid.focus();
               return false;
            }
            if (!regIdPw.test(mpw.value)) { // 비밀번호 영어 대소문자 확인
               alert('비밀번호는 영문자, 숫자, 특수 문자를 모두 포함하며, 최소 8자 이상으로 구성되어야 합니다.')
               mpw.focus();
               return false;
            }
            
            if (!agreement.checked) {
               // 약관 동의 체크 여부 확인
               Swal.fire({
                  title : "약관동의",
                  text : "약관동의를 체크해주세요",
                  icon : "error"
               });
               return false;
            }

            if (!profile.checked) {
               // 약관 동의 체크 여부 확인
               Swal.fire({
                  title : "약관동의",
                  text : "약관동의를 체크해주세요",
                  icon : "error"
               });
               return false;
            }
            
            if (joinForm.mpwCk.value) {
                     if (joinForm.mpwCk.value == joinForm.mpw.value) {
                        var div = document.getElementById('ok');
                        div.textContent = '비밀번호가 일치합니다';
                        return true;
                     }

                     var div = document.getElementById('ok');
                     //var txt = document.createTextNode('비밀번호가 다릅니다');//텍스트 노드만들기
                     div.textContent = '비밀번호가 다릅니다';

                     joinForm.mpwCk.focus();
                     return false;
                  } 

         }
      }
   </script>
   <%-- Sidebar--%>
   <section id="sidebar">
      <div class="inner">
         <nav>
            <ul>
               <li><a href="#intro">시작</a></li>
               <li><a href="#one">로그인</a></li>
               <li><a href="#two">회원가입</a></li>
               <li><a href="#three">Get in touch</a></li>
            </ul>
         </nav>
      </div>
   </section>

   <div id="wrapper">
      <%-- Intro --%>
      <section id="intro" class="wrapper style1 fullscreen fade-up">
         <div class="inner">
            <h1>연습</h1>
            <p>
               Just another fine responsive site template designed by <a
                  href="http://html5up.net">HTML5 UP</a><br /> and released for
               free under the <a href="http://html5up.net/license">Creative
                  Commons</a>.
            </p>
            <ul class="actions">
               <li><a href="#one" class="button scrolly">로그인</a></li>
            </ul>
         </div>
      </section>

      <!-- One -->
      <section id="one" class="wrapper style2 fullscreen fade-up">
         <div class="inner">
            <div>로그인</div>
            <form action="">
               아이디 : <input type="text"> 비밀번호 : <input type="password">
               <br> <input type="submit" value="로그인">
            </form>


         </div>


      </section>
      <!-- Two -->
      <section id="two" class="wrapper style3 fullscreen fade-up">

         <div class="inner">
            <form name="joinForm" id="joinForm" method="post">
               <div class="split style1">

                  <section>
                     <div class="row gtr-uniform">
                        <div class="col-12">
                           <label for="name">Name</label> <input type="text" name="name"
                              id="name" placeholder="Name" required="required" />
                        </div>

                        <div class="col-12-xsmall">
                           <label for="id">ID</label> <input type="text" name="mid"
                              id="mid" placeholder="ID" required="required" />

                        </div>
                        <%-- <div class="col-12-xsmall">
                                 <button type="button" onclick="<% ck.idCk(); %>" name="checkId"></button>
                                    <h2><% if(ck.isCheckId()){out.println("사용가능한 ID 입니다");}else {out.println("중복된 ID 입니다"); } %> </h2>
                                    </div> --%>



                        <div class="col-12">
                           <label for="name">Password</label> <input type="password"
                              name="mpw" id="mpw" placeholder="password" required="required"
                              oninput="myPw()" />
                        </div>

                        <div class="col-12" id="mpwCk">
                           <label for="PasswordCk">PasswordCk</label> <input
                              type="password" name="mpwCk" id="mpwCk"
                              placeholder="PasswordCk" oninput="myPw()" required />
                           <div id="ok"></div>
                        </div>

                        <div class="col-12">
                           <label for="email">Email</label> <input type="email"
                              name="email" id="email" placeholder="Email"
                              required="required" />
                        </div>


                        <div class="col-12">
                           <ul class="actions">
                              <li><input type="submit" value="Send Message"
                                 class="primary" /></li>
                              <li><input type="reset" value="Reset" /></li>
                           </ul>
                        </div>
                     </div>


                  </section>
                  <section>
                                 
                     <ul class="alt">
                        <!-- <li >
                           <ul class="alt">
                              <li>이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택), 프로모션 안내 메일 수신(선택)에
                                 모두 동의합니다.</li>
                              <li class="checkAllBtn">
                              <div class="col-6 col-12-small">
                                    <input type="checkbox" id="demo-copy" name="demo-copy">
                                    <label for="demo-copy">Email me a copy</label>
                                 </div>
                                 </li>
                           </ul>
                        </li> -->

                        <li><textarea name="" id="">여러분을 환영합니다.
네이버 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와 이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
                           <ul class="alt">
                              <li>
                                 <div class="col-6 col-12-small">
                                    <input type="checkbox" id="agreement" name="agreement"> <label
                                       for="agreement">이용약관 동의(필수)</label>
                                 </div>
                              </li>
                           </ul></li>

                        <li><textarea name="" id="">여러분을 환영합니다.
네이버 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와 이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
                           <ul class="alt">
                              <li>
                                 <div class="col-6 col-12-small">
                                    <input type="checkbox" id="profile" name="profile"> <label
                                       for="profile">개인정보 수집 및 이용에 대한 안내(필수)</label>
                                 </div>
                              </li>
                           </ul></li>

                        <li><textarea name="" id="">여러분을 환영합니다.
네이버 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와 이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
                           <ul class="alt">
                              <li><div class="col-6 col-12-small">
                                    <input type="checkbox" id="demo3" name="demo3"> <label
                                       for="demo3">위치정보 이용약관 동의(선택)</label>
                                 </div></li>
                           </ul></li>

                        <li>
                           <ul class="alt">

                              <li><div class="col-6 col-12-small">
                                    <input type="checkbox" id="demo4" name="demo4"> <label
                                       for="demo4">이벤트 등 프로모션 알림 메일 수신(선택)</label>
                                 </div></li>
                           </ul>

                        </li>

                     </ul>

                  </section>


               </div>
            </form>
         </div>



      </section>

      <!-- Three -->
      <section id="three" class="wrapper style1 fade-up"></section>

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
   <script src="assets/js/jquery.min.js"></script>
   <script src="assets/js/jquery.scrollex.min.js"></script>
   <script src="assets/js/jquery.scrolly.min.js"></script>
   <script src="assets/js/browser.min.js"></script>
   <script src="assets/js/breakpoints.min.js"></script>
   <script src="assets/js/util.js"></script>
   <script src="assets/js/main.js"></script>

</body>
</html>
 