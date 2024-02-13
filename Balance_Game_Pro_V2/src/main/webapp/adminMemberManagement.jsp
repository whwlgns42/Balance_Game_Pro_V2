<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList,model.member.MemberDTO"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="crown"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/favicon.png">
<title>관리자 유저관리 페이지</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="adminLte/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet" href="adminLte/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="adminLte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="adminLte/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="adminLte/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet" href="adminLte/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet" href="adminLte/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet" href="adminLte/plugins/summernote/summernote-bs4.min.css">
<style>
.message {
	border-top: 1px solid #ccc;
	padding: 10px;
	margin-top: 5px;
	background-color: #e6e6e6;
}

#chat-container {
	width: 400px;
	height: 600px;
	display: flex;
	flex-direction: column;
	border: 1px solid #ccc;
}

#chat-messages {
	flex: 1;
	overflow-y: auto;
	padding: 10px;
	display: flex;
	flex-direction: column-reverse;
}

#user-input {
	display: flex;
	padding: 10px;
}

#user-input input {
	flex: 1;
	padding: 10px;
	outline: none;
}

#user-input button {
	border: none;
	background-color: #1e88e5;
	color: white;
	padding: 10px 15px;
	cursor: pointer;
}
</style>
<style>
/* 스타일링을 위한 CSS */
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<div class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake" src="images/logo.png" alt="AdminLTELogo" height="60" width="60">
		</div>

		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu" href="adminPage.do" role="button"> <img src="images/123.png" alt="Menu" width="30" height="30">
				</a></li>
				<li class="nav-item d-none d-sm-inline-block nav-link">유저관리 페이지</li>
				<li class="nav-item d-none d-sm-inline-block"><a href="logout.do" class="nav-link">로그아웃</a></li>
			</ul>
			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">

				<!-- Messages Dropdown Menu -->
				<li class="nav-item dropdown">
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<div class="dropdown-divider"></div>
						<div class="dropdown-divider"></div>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
					</div>
				</li>
				<!-- Notifications Dropdown Menu -->
				<li class="nav-item dropdown"></li>

			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="adminPage.do" class="brand-link"> <img src="images/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> <span class="brand-text font-weight-light">관리자</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->

				<!-- SidebarSearch Form -->
				<div class="form-inline"></div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
						<li class="nav-item menu-open"><a href="#" class="nav-link active"> <i class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									관리 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="adminPage.do" class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>메인</p>
								</a></li>
								<li class="nav-item"><a href="adminMemberManagementPage.do" class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>유저관리</p>
								</a></li>
								<li class="nav-item"><a href="adminTitleManagementPage.do" class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>문제관리</p>
								</a></li>
								<li class="nav-item"><a href="adminSponsor.do" class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>후원관리</p>
								</a></li>
							</ul></li>



					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">


			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- /.row -->
					<!-- Main row -->
					<div class="row">
						<div class="col-12">
							<div class="card" style="margin-top: 20px;">
								<div class="card-header">
									<h3 class="card-title">유저목록</h3>
									<div class="card-tools">
										<div class="input-group input-group-sm" style="width: 150px;">
											<input type="text" id="searchLoginId" name="table_search" class="form-control float-right" placeholder="검색">
											<div class="input-group-append">
												<button type="submit" class="btn btn-default">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</div>
									</div>
								</div>

								<div class="card-body table-responsive p-0">
									<table class="table table-hover text-nowrap">
										<thead>
											<tr>
												<th>ID</th>
												<th>주소</th>
												<th>이메일</th>
												<th>나이</th>
												<th>성별</th>
												<th>후원금액</th>
												<th>후원랭킹</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${empty member}">
												<tr>
													<td colspan="1">회원 정보가 없습니다.</td>
												</tr>

											</c:if>
										</tbody>
										<tbody id="suggestionTableBody">
											<c:forEach var="data" items="${member}">
												<tr onclick="location.href = 'adminMemberDetailPage.do?loginId=${data.loginId}'">
													<td id="loginIdData">
														<crown:crown ranking="${data.ranking}" />
														${data.loginId}
													</td>
													<td id="addressData">${data.address}</td>
													<td id="eamilData">${data.email}</td>
													<td id="ageData">${data.age}</td>
													<td id="genderData">${data.gender}</td>
													<td id="totalData">
														<fmt:formatNumber value="${data.total}" currencyCode="KRW" />
													</td>
													<td id="rankingData">${data.ranking}</td>
												</tr>

											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>



						</div>

						<!-- right col (We are only adding the ID to make the widgets sortable)-->

						<!-- right col -->
					</div>
					<!-- /.row (main row) -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<strong>자비스(주) &copy; 1234-5678 <a href="index.html">페이지1이동</a>.
			</strong> All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 3.2.0
			</div>
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var checkbox = document.getElementById('exampleCheck1');
			checkbox.addEventListener('change', function() {
				if (this.checked) {
					this.value = '1'; // 체크되었을 때 1로 변경
				} else {
					this.value = '0'; // 체크 해제되었을 때 0으로 변경
				}
			});
		});
	</script>
	<!-- jQuery -->
	<script src="adminLte/plugins/jquery/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="adminLte/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button)
	</script>
	<!-- Bootstrap 4 -->
	<script src="adminLte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- ChartJS -->
	<script src="adminLte/plugins/chart.js/Chart.min.js"></script>
	<!-- Sparkline -->
	<script src="adminLte/plugins/sparklines/sparkline.js"></script>
	<!-- JQVMap -->
	<script src="adminLte/plugins/jqvmap/jquery.vmap.min.js"></script>
	<script src="adminLte/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<!-- jQuery Knob Chart -->
	<script src="adminLte/plugins/jquery-knob/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<script src="adminLte/plugins/moment/moment.min.js"></script>
	<script src="adminLte/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script src="adminLte/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Summernote -->
	<script src="adminLte/plugins/summernote/summernote-bs4.min.js"></script>
	<!-- overlayScrollbars -->
	<script src="adminLte/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script src="adminLte/dist/js/adminlte.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="adminLte/dist/js/pages/dashboard.js"></script>
	<!-- 인공지능 -->
	<script src="adminLte/dist/js/ai.js"></script>
	<script>
		$(document)
				.ready(
						function() {

							$("#searchLoginId").keyup(function() {
								searchLoginId();
							});

							function updateTableRow(result) {
								var tableBody = $("#suggestionTableBody");

								if (!result || result.length === 0) {
									// 데이터가 없을 경우 메시지 출력
									tableBody
											.html('<tr><td colspan="2">데이터가 없습니다.</td></tr>');
									return;
								}
								// 데이터가 있을 경우 테이블 업데이트
								var html = "";
								console.log(typeof result + "   데이터 타입");
								for (var i = 0; i < result.length; i++) {
								   
								    var loginId = result[i].loginId;
								    var address = result[i].address;
								    console.log(address + "<<< 주소 ");
								    
								    var email = result[i].email;  // 변수명이 eamil이 아닌 email이어야 합니다.
								    var age = result[i].age;
								    var gender = result[i].gender;
								    var total = result[i].total;
								    var ranking = result[i].ranking;

								    html += '<tr data-loginId="' + loginId + '" onclick="location.href=\'adminMemberDetailPage.do?loginId=' + loginId + '\'">';
								    html += '<td id="loginIdData">' + loginId + '</td>';
								    html += '<td id="address">' + address + '</td>';
								    html += '<td id="email">' + email + '</td>';  // 변수명을 올바르게 수정
								    html += '<td id="age">' + age + '</td>';
								    html += '<td id="gender">' + gender + '</td>';
								    html += '<td id="total">' + total + '</td>';
								    html += '<td id="ranking">' + ranking + '</td>';
								    html += '</tr>';
								}

								tableBody.html(html);

							}
							function searchLoginId() {
								var searchLoginIdData = $("#searchLoginId")
										.val();

								$.ajax({
									url : "adminMemberManageSearchAsync",
									type : "POST",
									data : {
										searchData : searchLoginIdData
									// 체크박스 상태를 전달
									},
									dataType : 'json',
									success : function(result) {
										console.log(result) + "  <<비동기 결과 ";
										updateTableRow(result);
									},
									error : function(request, status, error) {
										// 서버 요청 실패시 에러 메시지 표시
										updateTableRow();
										console.error('서버 요청 실패!!!', error);
									}
								});
							}
						});
	</script>
</body>
</html>