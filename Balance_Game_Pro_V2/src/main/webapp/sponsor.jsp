<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
   Hyperspace by HTML5 UP
   html5up.net | @ajlkn
   Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<style>
.button-container {
	text-align: right;
	margin-top: 10px;
}
</style>
<title>Generic - Hyperspace by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body class="is-preload">
	<c:if test="${empty loginId}">
		<c:redirect url="alert.do?status=fail&msg=로그인후 이용가능합니다.&redirect=loginPage.do"></c:redirect>
	</c:if>
	<!-- Header -->
	<header id="header">
		<a href="index.html" class="title">Hyperspace</a>
		<nav>
			<ul>
				<li><a href="index.html">login</a></li>
				<li><a href="elements.html">joinMembership</a></li>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">sponsor</h1>
				<!-- <span class="image fit"><img src="images/pic04.jpg" alt="" /></span> -->
				<body>
					<h2>sponsor rank</h2>
					<table>
						<tr>
							<td>
								1. chanwoo<br>2. chanwoo<br>3. chanwoo<br>4. chanwoo<br>5. chanwoo<br>6. chanwoo
							</td>


						</tr>
					</table>
				</body>
				<body>
					<div class="button-container">
						<button id="sponsorButton">후원하기</button>
					</div>

					<div class="button-container">
						<button id="sponsorButton2">후원하기2</button>
					</div>
				</body>
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
	<%
	//    String loginId = (String)session.getAttribute("loginId");
	/* String loginId = (String)session.getAttribute("loginId"); */
	/* String email = "asdf@naver.com";
	String phone = "010-1234-1234";
	String address = "서울"; */
	//int amount = 0;
	%>

	<script>
	
	
	var amount;
	var loginId = "${loginId}";

	 $(document).ready(function () {
	    $("#sponsorButton2").click(async function () {  // async 키워드 추가
	        const { value: formValues } = await Swal.fire({
	            title: "후원금액",
	            html: `
	            	<input id="swal-input1" class="swal2-input" oninput="this.value = this.value.replace(/[^0-9]/g, '');">원
	            `,
	            focusConfirm: false,
	            preConfirm: () => {
	                amount = $("#swal-input1").val();
	            	return  amount;
	            }
	        });
	        if (formValues) {
	            /*  Swal.fire(amount + "원을\n후원하셨습니다.");  */
	        	Swal.fire({
	        		  title: "후원",
	        		  text: "[" + amount + "] 원을 후원 하시겠습니까?",
	        		  icon: "warning",
	        		  showCancelButton: true,
	        		  confirmButtonColor: "#3085d6",
	        		  cancelButtonColor: "#d33",
	        		  confirmButtonText: "후원하기",
	        		  cancelButtonText: "후원취소"
	            }).then((result) => {
	                if (result.isConfirmed) {
	                    // 카카오페이 시작
	                    var j = jQuery.noConflict();
	                            var IMP = window.IMP;
	                            IMP.init('imp77111714');

	                            var msg;
	                            var everythings_fine = true;

	                            IMP.request_pay({
	                                pg: 'kakaopay',
	                                pay_method: 'card',
	                                merchant_uid: 'merchant_' + new Date().getTime(),
	                                name: '후원하기',
	                                amount: amount,
	                            <%--  buyer_email: '<%=email%>', --%>
	                                loginId: loginId,
	                                <%-- buyer_tel: '<%=phone%>',
	                                buyer_addr: '<%=address%>', --%>
	                                buyer_postcode: '123-456',
	                            }, function (rsp) {
	                                if (rsp.success) {
	                                    console.log(loginId);
	                                    console.log(rsp.imp_uid);
	                                    console.log(amount);

	                                    j.ajax({
	                                        url: "sponsor",
	                                        type: 'POST',
	                                        dataType: 'json',
	                                        data: {
	                                            imp_uid: rsp.imp_uid,
	                                            loginId: loginId,
	                                            amount: amount
	                                        }
	                                    }).done(function (data) {
	                                        if (everythings_fine) {
	                                            msg = '후원해주셔서 감사합니다.'
	                                            /* msg = '후원해주셔서 감사합니다.\n후원 금액 : ' + rsp.paid_amount + '원';
	                                            msg += '\n고유ID : ' + rsp.imp_uid;
	                                            msg += '\n상점 거래ID : ' + rsp.merchant_uid; 
	                                            msg += '\n후원 금액 : ' + rsp.paid_amount + '원'; */

	                                            // 성공시 이동할 페이지
	                                             redirectToSuccessPage(msg);
	                                        } else {
	                                            // 결제가 되지 않았습니다.
	                                            // 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	                                            redirectToFailPage(msg);
	                                        }
	                                    });
	                                } else {
	                                    msg = '결제에 실패하였습니다.';
	                                   // msg += '에러내용 : ' + rsp.error_msg;
	                                    // 실패시 이동할 페이지
	                                    redirectToFailPage(msg);
	                                }
	                            });

	                        // 성공 페이지로 이동하는 함수
	                        function redirectToSuccessPage(msg) {
	                            location.href = 'alert.do?msg=' + msg +'&status=success&redirect=main.do'; // 성공시 메인화면
	                        }

	                        // 실패 페이지로 이동하는 함수
	                        function redirectToFailPage(msg) {
	                        	 location.href = 'alert.do?msg=' + msg +'&status=fail&redirect=main.do'; // 실패시 메인화면
	                        }
	                }
	            });
	        }
	    });
	});
	
	
	<%-- var j = jQuery.noConflict();	
	j(document).ready(function () {
	    j("#sponsorButton").click(function () {
	        var IMP = window.IMP;
	        IMP.init('imp77111714');

	        var msg;
	        var everythings_fine = true; // everythings_fine 변수 명시적으로 정의

	        IMP.request_pay({
	            pg: 'kakaopay',
	            pay_method: 'card',
	            merchant_uid: 'merchant_' + new Date().getTime(),
	            name: '후원하기',
	            amount: amount,
	            buyer_email: '<%=email%>',
	            loginId: '<%=loginId%>',
	            buyer_tel: '<%=phone%>',
	            buyer_addr: '<%=address%>',
	            buyer_postcode: '123-456',
	            // m_redirect_url: 'http://www.naver.com'
	        }, function (rsp) {
	            if (rsp.success) {
	                // 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
	                console.log('<%=loginId%>');
	                console.log(rsp.imp_uid);
	                console.log(amount);
	                
	                j.ajax({
	                    url: "sponsor", // 후원 금액 저장할 경로
	                    type: 'POST',
	                    dataType: 'json',
	                    data: {
	                        imp_uid: rsp.imp_uid,
	                        loginId: '<%=loginId%>',
	                        amount: amount
	                        
	                        
	                        // 기타 필요한 데이터가 있으면 추가 전달
	                    }
	                }).done(function (data) {
	                    // 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
	                    if (everythings_fine) {
	                        msg = '후원해주셔서 감사합니다.';
	                        msg += '\n고유ID : ' + rsp.imp_uid;
	                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	                        msg += '\n후원 금액 : ' + rsp.paid_amount + '원';
	                        

	                        alert(msg);
	                        
	                        // 성공시 이동할 페이지
	                       // redirectToSuccessPage(msg);
	                    } else {
	                        // 아직 제대로 결제가 되지 않았습니다.
	                        // 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	                        redirectToFailPage(msg);
	                    }
	                });
	            } else {
	                msg = '결제에 실패하였습니다.';
	                msg += '에러내용 : ' + rsp.error_msg;
	                // 실패시 이동할 페이지
	                redirectToFailPage(msg);
	            }
	        });
	    });

	    // 성공 페이지로 이동하는 함수
	    function redirectToSuccessPage(msg) {
	        location.href = '<%=request.getContextPath()%>/success.jsp?msg=' + msg;
	    }

	    // 실패 페이지로 이동하는 함수
	    function redirectToFailPage(msg) {
	        location.href = '<%=request.getContextPath()%>/fail.jsp?msg=' + msg;
	    }
	}); --%>



	
   
    </script>
</body>
</html>