<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>

<title>Bank</title>
<script src='<spring:url value="/resources/js/lib/jquery.js"></spring:url>'></script>
</head>
<body>
	<div class="col-sm-offset-3">
		<h2>Lexnimble Bank</h2>
	</div><br>
	<form class="form-horizontal" id="loginForm">
		<div class="form-group">
			<label for="loginForm" class="col-sm-3 control-label">Username</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="username"
					placeholder="username" name="username">
					<span id="username-error" style="color:red;"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="loginForm" class="col-sm-3 control-label">Password</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="password"
					placeholder="Password" name="password">
					<span id="password-error" style="color:red;"></span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-4">
				<button type="button" class="btn btn-default" id="signin" onclick="loginForm()">Sign in</button>
			</div>
		</div>
	</form>
	<div class="form-group">
			<div class="col-sm-offset-3 col-sm-4">
				<div id="error" style="font-size: 20px; background-color: #d9534f; text-align: center; color: white" ></div>
			</div>
		</div>

	<%-- <script src='<spring:url value="/resources/js/lib/bootstrap.min.css"></spring:url>'></script> --%>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script src='<spring:url value="/resources/js/main.js"></spring:url>'></script>
	<script src='<spring:url value="/resources/js/lib/serialize-object.js"></spring:url>'></script>
</body>
</html>
