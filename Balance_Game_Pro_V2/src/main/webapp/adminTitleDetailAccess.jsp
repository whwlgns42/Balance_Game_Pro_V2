<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/favicon.png">
<title>관리자 문제 승인 상세 페이지</title>

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
    <li class="nav-item d-none d-sm-inline-block nav-link">문제승인 페이지</li>
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
								<li class="nav-item"><a href="adminMemberManagementPage.do" class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>유저관리</p>
								</a></li>
								<li class="nav-item"><a href="adminTitleManagementPage.do" class="nav-link active"> <i class="far fa-circle nav-icon"></i>
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
    <section class="content">
     <div class="row">
      <div class="col-md-6">
       <div class="card card-primary">
        <div class="card-header">
         <h3 class="card-title">문제</h3>
         <div class="card-tools">
          <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
           <i class="fas fa-minus"></i>
          </button>
         </div>
        </div>
        <form action="adminTitleAccess.do" method="POST" id="insertForm">
        <div class="card-body">
         <div class="form-group">
          <label for="inputName">제목</label> <input type="text" id="inputName" class="form-control" value="${qDTO.title}" name="title">
         </div>
         <div class="form-group">
          <label for="inputDescription">선택지A</label>
          <textarea id="inputDescription" class="form-control" rows="2" name="answer_A">${qDTO.answer_A}</textarea>
         </div>
         <div class="form-group">
          <label for="inputDescription">선택지B</label>
          <textarea id="inputDescription" class="form-control" rows="2" name="answer_B">${qDTO.answer_B }</textarea>
         </div>
         <div class="form-group">
          <label for="inputDescription">출제 이유</label>
          <textarea id="inputDescription" class="form-control" rows="3" name="Explanation">${qDTO.explanation }</textarea>
         </div>
         <div class="form-check">
           <input type="hidden" name="category" id="YN" value="1">
            <input type="checkbox" class="form-check-input" id="adultCheck">
           <label class="form-check-label" for="adultCheck">19 문제</label>
          </div>
          <input type="hidden" name="qid" value="${qDTO.qId}" />
          <button type="submit" class="btn btn-block btn-success" style="margin-bottom: 10px; margin-top: 10px;">문제 승인</button>
         </form>
         <form action="adminTitleRefuse.do" method="POST">
          <input type="hidden" name="qid" value="${qDTO.qId}" />
          <button type="submit" class="btn btn-block btn-danger">문제 거절</button>
         </form>

        </div>

       </div>

      </div>

     </div>
     <div class="row"></div>
    </section>

    <!-- /.container-fluid -->
   </section>
   <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
   <strong>자비스(주) &copy; 1234-5678
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
 
  <script src="js/blankSpace.js"></script>
 <!-- 공백을 막아주는 js -->
 <script src="js/submitBlankCheck.js"></script>
<script src = "js/categoryCheck.js"></script>
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
</body>
</html>
