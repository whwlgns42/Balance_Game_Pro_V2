<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
        비번 <input type="password" name="mPw" id="password"> <br>
        비번확인 <input type="password" name="mpwCh" id="passwordCh"> <br>
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
            let passwordPattern = /^(?=.*[!@#$%^&*(),.?":{}|<>])(?=.*[a-zA-Z]).{6,}$/;
            if (!passwordPattern.test(password)) {
                Swal.fire({
                  title: "비밀번호",
              	  text: "특수문자와 영어를 포함하여 6자 이상으로 입력해주세요.",
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
                    url: "idCheckAsyncs",
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
