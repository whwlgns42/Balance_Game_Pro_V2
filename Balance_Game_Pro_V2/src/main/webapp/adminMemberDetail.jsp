<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList,model.member.MemberDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>유저관리 상세 페이지</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
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
			<img class="animation__shake" src="dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
		</div>

		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu" href="#" role="button"> <img src="images/123.jpg" alt="Menu" width="30" height="30">
				</a></li>
				<li class>유저관리 페이지</li>
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
			<a href="index3.html" class="brand-link"> <img src="images/logo.jpg" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> <span class="brand-text font-weight-light">관리자</span>
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
								<li class="nav-item"><a href="./index.html" class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>메인</p>
								</a></li>
								<li class="nav-item"><a href="index2.html" class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>유저관리</p>
								</a></li>
								<li class="nav-item"><a href="index3.html" class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>문제관리</p>
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
					<div class="col-md-9">
						<div class="card card-primary card-outline">
							<div class="card-header">
								<h3 class="card-title">유저 정보</h3>
							</div>

							<%-- 							<%
							MemberDTO member = (MemberDTO) request.getAttribute("member");

							if (member != null) {
							%>
							<table border="1">
								<tr>
									<th>ID</th>
									<td><%=member.getmId()%></td>
								</tr>
								<tr>
									<th>Login ID</th>
									<td><%=member.getLoginId()%></td>
								</tr>
								<!-- 다른 회원 정보도 필요에 따라 추가 -->
							</table>
							<%
							} else {
							%>
							<p>회원 정보가 없습니다.</p>
							<%
							}
							%> --%>
							<%
							MemberDTO member = (MemberDTO) request.getAttribute("member");

							if (member != null) {
							%>
							<form action="updateMember.do" method="post">
								<table border="1">
									<tr>
										<th style="width: 78px;">ID</th>
										<td><%=member.getmId()%></td>
									</tr>
									<tr>
										<th>Login ID</th>
										<td><%=member.getLoginId()%></td>
									</tr>


								</table>

								<div class="card-body">
									<div class="form-group">
										<label for="name">이름:</label>
										<input class="form-control" type="text" name="name" value="<%=member.getName()%>">
									</div>
									<div class="form-group">
										<label for="email">이메일:</label>
										<input class="form-control" type="text" name="email" value="<%=member.getEmail()%>">
									</div>
									<div class="form-group">
										<label for="address">주소:</label>
										<input class="form-control" type="text" name="address" value="<%=member.getAddress()%>">
									</div>
									<div class="form-group">
										<label for="age">나이:</label>
										<input class="form-control" type="text" name="age" value="<%=member.getAge()%>">
									</div>




									<!-- 다른 필드에 대한 입력 폼도 필요에 따라 추가 -->

									<input type="hidden" name="memberId" value="<%=member.getmId()%>">
								</div>
							</form>
							<%
							} else {
							%>
							<p>회원 정보가 없습니다.</p>
							<%
							}
							%>
							<!-- <div class="card-body">
								<div class="form-group">
									<input class="form-control" placeholder="이름:">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="주소:">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="등등:">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Subject:">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Subject:">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Subject:">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Subject:">
								</div>

								<div class="form-group">
									<div class="btn btn-default btn-file">
										<i class="fas fa-paperclip"></i> 회원 삭제
										<input type="file" name="attachment">
									</div>
								</div>
							</div> -->

							<div class="card-footer">
								<div class="float-left">
									<button type="button" class="btn btn-block btn-danger">회원삭제</button>

								</div>
								<div class="float-right">
									<button type="button" class="btn btn-block btn-primary">회원정보수정</button>

								</div>
							</div>

						</div>

					</div>
					<!-- /.row (main row) -->
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">댓글목록</h3>
						</div>

						<div class="card-body">
							<div id="jsGrid1" class="jsgrid" style="position: relative; height: 100%; width: 100%;">
								<div class="jsgrid-grid-header jsgrid-header-scrollbar">
									<table class="jsgrid-table">
										<tr class="jsgrid-header-row">
											<th class="jsgrid-header-cell jsgrid-header-sortable" style="width: 150px;">Name</th>
											<th class="jsgrid-header-cell jsgrid-align-right jsgrid-header-sortable" style="width: 50px;">Age</th>
											<th class="jsgrid-header-cell jsgrid-header-sortable" style="width: 200px;">Address</th>
											<th class="jsgrid-header-cell jsgrid-align-center jsgrid-header-sortable" style="width: 100px;">Country</th>
											<th class="jsgrid-header-cell jsgrid-align-center jsgrid-header-sortable" style="width: 100px;">Is Married</th>
										</tr>
										<tr class="jsgrid-filter-row" style="display: none;">
											<td class="jsgrid-cell" style="width: 150px;">
												<input type="text">
											</td>
											<td class="jsgrid-cell jsgrid-align-right" style="width: 50px;">
												<input type="number">
											</td>
											<td class="jsgrid-cell" style="width: 200px;">
												<input type="text">
											</td>
											<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
												<select>
													<option value="0"></option>
													<option value="1">United States</option>
													<option value="2">Canada</option>
													<option value="3">United Kingdom</option>
													<option value="4">France</option>
													<option value="5">Brazil</option>
													<option value="6">China</option>
													<option value="7">Russia</option>
												</select>
											</td>
											<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
												<input type="checkbox" readonly="">
											</td>
										</tr>
										<tr class="jsgrid-insert-row" style="display: none;">
											<td class="jsgrid-cell" style="width: 150px;">
												<input type="text">
											</td>
											<td class="jsgrid-cell jsgrid-align-right" style="width: 50px;">
												<input type="number">
											</td>
											<td class="jsgrid-cell" style="width: 200px;">
												<input type="text">
											</td>
											<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
												<select>
													<option value="0"></option>
													<option value="1">United States</option>
													<option value="2">Canada</option>
													<option value="3">United Kingdom</option>
													<option value="4">France</option>
													<option value="5">Brazil</option>
													<option value="6">China</option>
													<option value="7">Russia</option>
												</select>
											</td>
											<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
												<input type="checkbox">
											</td>
										</tr>
									</table>
								</div>
								<div class="jsgrid-grid-body" style="height: 300px;">
									<table class="jsgrid-table">
										<tbody>
											<tr class="jsgrid-row">
												<td class="jsgrid-cell" style="width: 150px;">Otto Clay</td>
												<td class="jsgrid-cell jsgrid-align-right" style="width: 50px;">61</td>
												<td class="jsgrid-cell" style="width: 200px;">Ap #897-1459 Quam Avenue</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">China</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
													<input type="checkbox" disabled="">
												</td>
											</tr>
											<tr class="jsgrid-alt-row">
												<td class="jsgrid-cell" style="width: 150px;">Connor Johnston</td>
												<td class="jsgrid-cell jsgrid-align-right" style="width: 50px;">73</td>
												<td class="jsgrid-cell" style="width: 200px;">Ap #370-4647 Dis Av.</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">Russia</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
													<input type="checkbox" disabled="">
												</td>
											</tr>
											<tr class="jsgrid-row">
												<td class="jsgrid-cell" style="width: 150px;">Lacey Hess</td>
												<td class="jsgrid-cell jsgrid-align-right" style="width: 50px;">29</td>
												<td class="jsgrid-cell" style="width: 200px;">Ap #365-8835 Integer St.</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">Russia</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
													<input type="checkbox" disabled="">
												</td>
											</tr>
											<tr class="jsgrid-alt-row">
												<td class="jsgrid-cell" style="width: 150px;">Timothy Henson</td>
												<td class="jsgrid-cell jsgrid-align-right" style="width: 50px;">78</td>
												<td class="jsgrid-cell" style="width: 200px;">911-5143 Luctus Ave</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">United States</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
													<input type="checkbox" disabled="">
												</td>
											</tr>
											<tr class="jsgrid-row">
												<td class="jsgrid-cell" style="width: 150px;">Ramona Benton</td>
												<td class="jsgrid-cell jsgrid-align-right" style="width: 50px;">43</td>
												<td class="jsgrid-cell" style="width: 200px;">Ap #614-689 Vehicula Street</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">Brazil</td>
												<td class="jsgrid-cell jsgrid-align-center" style="width: 100px;">
													<input type="checkbox" disabled="">
												</td>
											</tr>

										</tbody>
									</table>
								</div>

							</div>
							<div class="jsgrid-load-shader" style="display: none; position: absolute; inset: 0px; z-index: 1000;"></div>
							<div class="jsgrid-load-panel" style="display: none; position: absolute; top: 50%; left: 50%; z-index: 1000;">Please, wait...</div>
						</div>
					</div>

				</div>
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

	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="adminLte/dist/js/pages/dashboard.js"></script>
	<!-- 인공지능 -->
	<script src="adminLte/dist/js/ai.js"></script>
</body>
</html>
