<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
		<a href="main.do" class="title">밸런스게임</a>
		<nav>
			<ul>
				<li><a href="loginPage.do" class="active">login</a></li>
				<li><a href="elements.html" class="active">joinMembership</a></li>
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
					<h2>sponsor rank</h2>
					<table>
						<tr>
							<td>
								 <c:forEach var="data" items="${datas}" varStatus="loop">
                                    ${loop.index + 1}. ${data.loginId}님이
                                    <fmt:formatNumber value="${data.total}" currencyCode="KRW" /> 원 후원하셨습니다.<br>
                                </c:forEach>
							</td>
						</tr>
					</table>
				<div class="button-container">
					<button id="sponsorButton">후원하기</button>
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
	
	<!-- 후원하기 카카오페이  -->
	<script>
	
	var amount;
	var loginId = "${loginId}";

	 $(document).ready(function () {
	    $("#sponsorButton").click(async function () {  // async 키워드 추가
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
	
	
    </script>
	<!-- 후원하기 카카오페이  -->
</body>
</html>