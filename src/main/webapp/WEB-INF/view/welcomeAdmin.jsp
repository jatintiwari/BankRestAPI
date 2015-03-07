<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Lex Nimble Bank</title>
<script
	src='<spring:url value="/resources/js/lib/jquery.js"></spring:url>'></script>
</head>
<body>
	<div class="row">
		<div class="col-sm-12"
			style="background-color: rgb(98, 158, 191); color: white; font-size: 50px; height: 75px">
			<div class="col-sm-offset-3" style="display: inline;">
			<a href="#" onclick="resetAdminPanel()" 
										style="text-decoration: none;color:white">Administrator</a>
			<input class="btn btn-primary btn-lg col-sm-offset-2" type="button" value="Log Out" onClick="logout()" id="logout">
			</div>
		</div>
	</div>
	<br>
	<div id="adminPanel" style="display: block;">
		<div class="row">
			<div class="col-xs-2 col-md-6 col-sm-offset-2" style="">
				<a href="#" onclick="addAccount()" class="thumbnail" style="font-size: 40px; text-decoration: none;border: 0px;width: 308px;padding-left: 4px;"> <span class="glyphicon glyphicon-plus" aria-hidden="true" style="font-size: 30px;"></span> Add Account
				</a>
				<a href="#" onclick="deleteAccount()" class="thumbnail" style="font-size: 40px; text-decoration: none;border: 0px;width: 328px;"> <span class="glyphicon glyphicon-remove" aria-hidden="true" style="font-size: 30px"></span> Delete Account
				</a> <a href="#" onclick="changePassword()" class="thumbnail" style="font-size: 40px; text-decoration: none; border: 0px;width: 378px;">
					<span class="glyphicon glyphicon-wrench" aria-hidden="true" style="font-size: 30px; display: inline;"></span> Change Password
				</a>
				<a href="#" onclick="getInfo()" class="thumbnail" style="font-size: 40px; text-decoration: none;  border: 0px;width: 338px;">
					<span class="glyphicon glyphicon-search" aria-hidden="true" style="font-size: 30px; display: inline;"></span> Search Account
				</a>
			</div>
		</div>
	</div>

	<!-- Add an account -->

	<div id="addAccount" style="display: none;">
		<form class="form-horizontal" id="addAccountForm">
			<div class="form-group">
				<div class="col-md-offset-3">
					<p id="moduleName"
						style="color: rgb(98, 158, 191); font-size: 30px">Create
						Account</p>
				</div>
			</div>
			<div class="form-group">
				<label for="addAccountForm" class="col-sm-3 control-label">Name</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " id="name"
						placeholder="Full Name" name="name"> <span class="error"
						id="error-name" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="addAccountForm" class="col-sm-3 control-label">Username</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " id="username"
						placeholder="Username" name="username"> <span
						class="error" id="error-username" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="addAccountForm" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " id="password"
						placeholder="Password" name="password"> <span
						class="error" id="error-password" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="addAccountForm" class="col-sm-3 control-label">Account
					Type</label>
				<div class="col-sm-4">
					<input type="radio" name="accountType" id="accountType"
						value="saving"> Savings <input type="radio"
						name="accountType" id="accountType" value="current">
					Current <br> <span class="error" id="error-accountType"
						style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="addAccountForm" class="col-sm-3 control-label">Amount</label>
				<div class="col-sm-2">
					<input type="number" class="form-control " id="amount"
						placeholder="Amount to deposit" name="amount"> <span
						class="error" id="error-Amount" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<input class="btn btn-default col-sm-offset-3" type="button" value="Save" id="saveButton">
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-4">
					<div id="message" 
						style="font-size: 20px; background-color: #d9534f; text-align: center; color: white"></div>
				</div>
			</div>
		</form>
	</div>
		<!-- delete account -->
	<div id="deleteAccount" style="display: none;">
		<form class="form-horizontal" id="deleteAccountForm">
		<div class="form-group">
				<div class="col-md-offset-3">
					<p id="moduleName"
						style="color: rgb(98, 158, 191); font-size: 30px">Delete
						Account</p>
				</div>
			</div>
			<div class="form-group">
				<label for="deleteAccountForm" class="col-sm-3 control-label">Username</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " id="deleteusername"
						placeholder="username" name="username"> <span class="error"
						id="error-delete" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<input class="btn btn-default col-sm-offset-3" type="button" value="Delete" id="deleteButton">
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-4">
					<div id="deactivatemessage" 
						style="font-size: 20px; background-color: #d9534f; text-align: center; color: white"></div>
				</div>
			</div>
		</form>
	</div>
		<!-- change password -->
		<div id="changePassword" style="display: none;">
		<form class="form-horizontal" id="changePasswordForm">
		<div class="form-group">
				<div class="col-md-offset-3">
					<p id="moduleName"
						style="color: rgb(98, 158, 191); font-size: 30px">Change
						Password</p>
				</div>
			</div>
			<div class="form-group">
				<label for="changePassword" class="col-sm-3 control-label">Username</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " id="changeusername"
						placeholder="username" name="username"> <span
						class="error" id="error-change" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="changePassword" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " id="changepassword"
						placeholder="Password" name="password"> <span
						class="error" id="error-change" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<input class="btn btn-default col-sm-offset-3" type="button"
					value="Change Password" id="changeButton">
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-4">
					<div id="changepassmessage" 
						style="font-size: 20px; background-color: #d9534f; text-align: center; color: white"></div>
				</div>
			</div>
			
		</form>
		
	</div>
		<!-- getInfo -->
	<div id="getInfo" style="display: none;">
		<form class="form-horizontal" id="getInfoForm">
			<div class="form-group">
			<div class="form-group">
				<div class="col-md-offset-3">
					<p id="moduleName"
						style="color: rgb(98, 158, 191); font-size: 30px">Get Account Information</p>
				</div>
			</div>
				<label for="getInfoForm" class="col-sm-3 control-label">Username</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " id="getusername"
						placeholder="username" name="username"> <span
						class="error" id="error-get" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group">
				<input class="btn btn-default col-sm-offset-3" type="button"
					value="Get Info" id="getButton">
					<input class="btn btn-default col-sm-offset-1" type="button"
					value="Get All" id="getAllButton" onclick="getAll()">
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-4">
					<div id="getInfoMessage" 
						style="font-size: 20px; background-color: #d9534f; text-align: center; color: white"></div>
				</div>
			</div>
		</form>
		<br>
		<br>
	</div>
		<!-- display content -->
	<div id="displayUser" style="display: none;">
	<div class="col-sm-offset-2 col-sm-6">
		<table class="table table-condensed" id="tableid">
			<thead>
				<tr>
					<th>Account Id</th>
					<th>Name</th>
					<th>AccountType</th>
					<th>Amount</th>
					<th>Username</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody id="idAccountsList">
			</tbody>
		</table>
	</div>
</div>

<!-- Scripts in the end of the page -->
	<link rel="stylesheet"
				href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<script src='<spring:url value="/resources/js/main.js"></spring:url>'></script>
			<script
				src='<spring:url value="/resources/js/lib/serialize-object.js"></spring:url>'></script>
</body>
</html>