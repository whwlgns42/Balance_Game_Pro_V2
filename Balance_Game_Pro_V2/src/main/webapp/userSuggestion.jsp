<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>건의사항</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="adminLte/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="adminLte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="adminLte/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="adminLte/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="adminLte/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="adminLte/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet"
	href="adminLte/plugins/summernote/summernote-bs4.min.css">
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

.ck-editor__editable {
	height: 400px;
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">


			<!-- Main content -->
			<section class="content">
				<section class="content">
					<div class="row">
						<div class="col-md-9">
							<div class="card card-primary card-outline">
								<div class="card-header">
									<h3 class="card-title">건의하기</h3>
								</div>

								<div class="card-body">
									<form action="UserSuggestionAction.do" method="post">
										<!-- process.jsp는 입력된 내용을 처리하는 JSP 파일을 가정하였습니다. -->
										<div class="form-group">
											<input type="text" class="form-control" name="loginId"
												value="${loginId}" readonly>
										</div>
										<div class="form-group">
											<input type="text" class="form-control" name="title"
												placeholder="제목">
										</div>
										<div class="form-group">
											<textarea name="suggestion" id="editor">내용</textarea>
											<!-- CKEditor를 사용하여 내용을 입력 -->
										</div>
										<div class="card-footer">
											<div class="float-right">
												<button type="submit" class="btn btn-primary">
													<i class="far fa-envelope"></i> Send
												</button>
											</div>
										</div>
									</form>
								</div>

							</div>

						</div>
					</div>
		</div>
	</div>
	</section>

	<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->


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
	<script
		src="adminLte/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Summernote -->
	<script src="adminLte/plugins/summernote/summernote-bs4.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="adminLte/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script src="adminLte/dist/js/adminlte.js"></script>

	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="adminLte/dist/js/pages/dashboard.js"></script>
	<!-- 인공지능 -->
	<script src="adminLte/dist/js/ai.js"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
	<script>
		ClassicEditor.create(document.querySelector('#editor'), {
			language : "ko"
		});
	</script>
	<script>
	function submitForm() {
	    var editorData = CKEditor.instances.editor.getData();
	    document.getElementById('editor').value = editorData; // CKEditor에서의 데이터를 textarea에 설정

	    // 폼 제출
	    document.getElementById('myForm').submit();

	    // 폼 제출 후 창 닫기
	    window.close();
	}
	</script>
</body>
</html>
