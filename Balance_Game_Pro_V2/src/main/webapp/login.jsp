
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	#loginBtn {
		margin-left: 110px;
	}
</style>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&amp;display=fallback">

<link rel="stylesheet" href="adminLte/plugins/fontawesome-free/css/all.min.css">

<link rel="stylesheet" href="adminLte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">

<link rel="stylesheet" href="adminLte/dist/css/adminlte.min.css?v=3.2.0">
<style>
	@font-face {
		font-family: 'SegoeUI_online_security';
		src: url(chrome-extension://llbcnfanfmjhpedaedhbcnpgeepdnnok/segoe-ui.woff);
	}
	@font-face {
		font-family: 'SegoeUI_bold_online_security';
		src: url(chrome-extension://llbcnfanfmjhpedaedhbcnpgeepdnnok/segoe-ui-bold.woff);
	}
	@font-face {
		font-family: 'SegoeUI_online_security';
		src: url(chrome-extension://llbcnfanfmjhpedaedhbcnpgeepdnnok/segoe-ui.woff);
	}
	@font-face {
		font-family: 'SegoeUI_bold_online_security';
		src: url(chrome-extension://llbcnfanfmjhpedaedhbcnpgeepdnnok/segoe-ui-bold.woff);
	}
	.av-extension {
		--dark-blue-background: #183360;
		--active-blue-font-color: #183360;
		--modal-header-darkblue-background: #05153f;
		--grey-font-color: #93a0b5;
		--background-color: #f1f4f8;
		--foreground-color: #ffffff;
		--tertiary-color: #05153f;
		--primary-font-color: #183360;
		--green-font-color: #04d289;
		--red-font-color: #ff3b30;
		--purple-font-color: #6726ff;
		--orange-color: #ff8f11;
		--default-font-size: 18px;
		--modal-header-background: #f2f2f2;
		--hover-orange-color: #d97a0e
	}
	.av-extension h1 {
		font-family: 'Segoe UI Bold';
		font-size: 50px;
		font-weight: 700;
		line-height: 66.5px
	}
	.av-extension h2 {
		font-size: 30px;
		padding: 0px;
		margin: 5px 0px;
		margin-top: 0px
	}
	.av-extension h3 {
		font-size: 20px;
		font-weight: normal;
		white-space: pre-line
	}
	.av-extension p {
		padding: 0px;
		margin: 5px 0px
	}
	.av-extension a {
		text-decoration: none
	}
	.av-extension .flex {
		display: flex
	}
	.av-extension .grid {
		display: grid
	}
	.av-extension .fwrap {
		flex-wrap: wrap
	}
	.av-extension .ait {
		align-items: top
	}
	.av-extension .aic {
		align-items: center
	}
	.av-extension .jcl {
		justify-content: left
	}
	.av-extension .jcc {
		justify-content: center
	}
	.av-extension .jcr {
		justify-content: right
	}
	.av-extension .tac {
		text-align: center
	}
	.av-extension .w100 {
		width: 100%
	}
	.av-extension .sb {
		font-weight: 600
	}
	.av-extension .borderButtonColor {
		color: var(--orange-color);
		border: 3px solid var(--orange-color)
	}
	.av-extension .paddinglr {
		padding: 100px 50px
	}
	.av-extension .redColor {
		color: var(--red-font-color);
		fill: var(--red-font-color)
	}
	.av-extension .greenColor {
		color: var(--green-font-color);
		fill: var(--green-font-color)
	}
	.av-extension .purpleColor {
		color: var(--purple-font-color)
	}
	.av-extension .orangeColor {
		color: var(--orange-color)
	}
	.av-extension .content {
		color: var(--primary-font-color);
		margin: auto;
		max-width: 85%;
		padding-top: 30px;
		padding-bottom: 20px
	}
	.av-extension .sectionContent {
		margin-top: 80px;
		margin-bottom: 40px;
		font-size: 22px;
		color: var(--primary-font-color)
	}
	.av-extension .btnAction {
		min-width: 170px;
		padding: 10px 25px;
		color: var(--orange-color);
		border: 2.5px solid var(--orange-color);
		border-radius: 39px;
		font-weight: 600;
		background-color: transparent
	}
	.av-extension .btnAction:hover {
		background-color: var(--orange-color);
		color: white
	}
	.av-extension .btnDwm {
		background: linear-gradient(#fff, #fff) padding-box, linear-gradient(to right, #8526ff, #2a26ff) border-box;
		border: 2.5px solid transparent;
		color: #7644ff
	}
	.av-extension .btnDwm:hover {
		background: linear-gradient(to right, #8526ff, #2a26ff) padding-box, linear-gradient(to right, #8526ff, #2a26ff) border-box;
		border: 2.5px solid transparent
	}
	.av-extension .btnBuy {
		display: flex;
		align-items: center;
		justify-content: center;
		gap: 10px;
		min-width: 170px;
		padding: 15px 40px;
		color: #fff;
		border-radius: 39px;
		font-weight: 600;
		background-color: var(--tertiary-color);
		border: none;
		cursor: pointer
	}
	.av-extension .btnBuy:hover {
		background-color: #0f3cb0
	}
	.av-extension .btnBuy:active {
		background-color: #0f3391
	}
	.av-extension .btnBuyOrange {
		display: flex;
		align-items: center;
		justify-content: center;
		gap: 10px;
		min-width: 170px;
		padding: 15px 40px;
		color: #fff;
		border-radius: 39px;
		font-weight: 600;
		background-color: var(--orange-color);
		border: none;
		cursor: pointer
	}
	.av-extension .btnBuyOrange:hover {
		background-color: #ffa846
	}
	.av-extension .btnBuyOrange:active {
		background-color: #d97a0e
	}
	.av-extension .sectionTitle {
		font-weight: bold;
		font-size: 20px;
		margin-bottom: 25px
	}
	.av-extension .sectionTitle img {
		margin-left: 5px;
		margin-right: 13px
	}
	.av-extension .fullHeadContent {
		height: 400px;
		background: var(--tertiary-color);
		box-shadow: -3px 0px 3px rgba(0, 0, 0, 0.1);
		border-radius: 0px 0px 22px 22px;
		color: var(--foreground-color)
	}
	.av-extension .fullHeadContent img {
		width: 442px
	}
	.av-extension .fullHeadContent p {
		margin: 10px
	}
	.av-extension .spinner {
		width: 120px;
		height: 120px
	}
	@media screen and (min-width: 1500px) {
		.av-extension .content {
			max-width: 70%
		}
	}
	@
	keyframes spin {
	0% {
		transform: rotate(0deg)
	} 100% {
		transform:rotate(360deg)
	  }
	}
	.checkboxContainer {
		display: block;
		position: relative;
		padding-left: 35px;
		margin-bottom: 12px;
		user-select: none
	}
	.checkboxContainer input {
		position: absolute;
		opacity: 0;
		height: 0;
		width: 0
	}
	.checkboxContainer .checkmark {
		position: absolute;
		top: 0;
		left: 0;
		height: 18px;
		width: 18px;
		border: 1px solid #cad0dd;
		border-radius: 100%
	}
	.checkboxContainer .checkmark.greenColor {
		border: 2.5px solid #cad0dd;
		border-radius: 8px
	}
	.checkboxContainer:hover input ~ .checkmark {
		background-color: #cad0dd
	}
	.checkboxContainer input:checked ~ .checkmark {
		background-color: var(--primary-font-color)
	}
	.checkboxContainer input:checked ~ .purpleColor {
		background-color: var(--purple-font-color)
	}
	.checkboxContainer input:checked ~ .greenColor {
		background-color: var(--green-font-color);
		border: 2px solid var(--green-font-color);
		border-radius: 8px
	}
	.checkmark:after {
		content: '';
		position: absolute;
		display: none
	}
	.checkboxContainer input:checked ~ .checkmark:after {
		display: block
	}
	.checkboxContainer .checkmark:after {
		left: 6px;
		top: 3px;
		width: 3px;
		height: 7px;
		border: solid white;
		border-width: 0 3px 3px 0;
		transform: rotate(45deg)
	}
	.checkboxContainer .uncheckAll:after {
		left: 7.5px;
		top: 4px;
		width: 0px;
		height: 9px;
		border-width: 0 3px 0 0;
		transform: rotate(90deg)
	}
	.sectionSelectRadio {
		display: none
	}
	.sectionSelectRadio+label {
		padding: 7px 2px;
		font-size: 20px;
		font-weight: 700;
		margin-right: 50px;
		color: var(--primary-font-color);
		border: 0px;
		background-color: transparent
	}
	.sectionSelectRadio:checked+label {
		border-bottom: 4px solid var(--purple-font-color)
	}
</style>
</head>
<body style="background: #312450;">
	<div style="height: 819px; display: flex; justify-content: center; align-items: center;">
		<div class="login-box">
			<div class="login-logo">
				<b>로그인</b>
			</div>
			<!-- /.login-logo -->
			<div class="card">
				<div class="card-body login-card-body">
					<form action="login.do" method="POST">
						<div class="input-group mb-3">
							<input type="text" class="form-control" name="loginId" id="loginId" placeholder="ID">

							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>
						<div class="input-group mb-3">
							<input type="password" class="form-control" name="mPw" id="mPw" placeholder="Password">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<input class="btn btn-primary btn-block" value="회원가입" onclick="location.href='joinPage.do'">
							</div>
							<div class="col-4" id="loginBtn">
								<input class="btn btn-primary btn-block" type="submit" value="로그인">
							</div>
						</div>
						<!-- /.col -->
					</form>
				</div>
			</div>
			<!-- /.login-card-body -->

		</div>
	</div>

	<script src="adminLte/plugins/jquery/jquery.min.js"></script>

	<script src="adminLte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script src="adminLte/dist/js/adminlte.min.js?v=3.2.0"></script>

</body>

</html>