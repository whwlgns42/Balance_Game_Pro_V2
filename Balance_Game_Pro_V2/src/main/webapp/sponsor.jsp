<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>후원하기</title>
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
				<c:if test="${not empty loginId }">
					<li><a href="loginPage.do" class="active">로그아웃</a></li>
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
				<h1 class="major">후원하기</h1>
				<!-- <span class="image fit"><img src="images/pic04.jpg" alt="" /></span> -->
				<h2>오늘의 후원 랭킹</h2>
				<table>
					<tr>
						<td>
							<c:forEach var="data" items="${datas}" varStatus="loop">
								<c:choose>
									<c:when test="${loop.index + 1 == 1}">
										<img alt="${loop.index + 1}위" src="images/${loop.index + 1}위.gif" width="25" height="25">
									</c:when>
									<c:when test="${loop.index + 1 == 2}">
										<img alt="${loop.index + 1}위" src="images/${loop.index + 1}위.gif" width="25" height="25">
									</c:when>
									<c:when test="${loop.index + 1 == 3}">
										<img alt="${loop.index + 1}위" src="images/${loop.index + 1}위.gif" width="25" height="25">
									</c:when>
									<c:otherwise>
            							${loop.index + 1}위
        							</c:otherwise>
								</c:choose>
   										 ${data.loginId}님이
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
	<common:footer></common:footer>
	<!-- Scripts -->
	<script src="assets/js/wpd"></script>
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
        $("#sponsorButton").click(async function () {
            const { value: formValues } = await Swal.fire({
                title: "후원금액",
                html: `
                    <input id="swal-input1" class="swal2-input" oninput="this.value = this.value.replace(/[^0-9]/g, '');">원
                `,
                focusConfirm: false,
                preConfirm: () => {
                    amount = $("#swal-input1").val();
                    return amount;
                }
            });

            if (formValues) {
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
                            loginId: loginId,
                            buyer_postcode: '123-456',
                        }, function (rsp) {
                            if (rsp.success) {
                                console.log(loginId);
                                console.log(rsp.imp_uid);
                                console.log(amount);

                                j.ajax({
                                    url: "sponsor",
                                    type: 'POST',
                                    dataType: 'text',
                                    data: {
                                        imp_uid: rsp.imp_uid,
                                        loginId: loginId,
                                        amount: amount
                                    }
                                }).done(function (data) {
                                    if (everythings_fine) {
                                        msg = '후원해주셔서 감사합니다.';
                                        redirectToSuccessPage(msg);
                                    } else {
                                        redirectToFailPage(msg);
                                    }
                                }).fail(function (jqXHR, textStatus, errorThrown) {
                                    console.error("AJAX request failed: ", textStatus, errorThrown);
                                });
                            } else {
                                msg = '결제에 실패하였습니다.';
                                redirectToFailPage(msg);
                            }
                        });

                        // 성공 페이지로 이동하는 함수
                        function redirectToSuccessPage(msg) {
                            location.href = 'alert.do?msg=' + msg + '&status=success&redirect=main.do';
                        }

                        // 실패 페이지로 이동하는 함수
                        function redirectToFailPage(msg) {
                            location.href = 'alert.do?msg=' + msg + '&status=fail&redirect=main.do';
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