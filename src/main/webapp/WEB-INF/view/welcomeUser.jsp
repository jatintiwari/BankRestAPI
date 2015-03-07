<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>


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
				Lex Nimble Bank<input
					class="btn btn-primary btn-lg col-sm-offset-2" type="button"
					value="Log Out" onClick="logout()" id="logout">
			</div>
		</div>
	</div>
	<br>
	<div id="userPanel" style="display:">
		<div class="row">
			<div class=" col-md-3 col-sm-offset-3" style="">
				<a href="#" onclick="deposit()" class="thumbnail"
					style="font-size: 40px; text-decoration: none; border: 0px; width: 400px; padding-left: 4px; margin-bottom: 0px;">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"
					style="font-size: 30px;"></span> Deposit / Withdraw
				</a>
				<div id="idDepositFormDisplay"
					style="display: none; none; padding-left: 40px;">
					<form class="form-horizontal " id="idDepositForm"
						style="margin-bottom: 0px;">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">$</div>
								<input type="number" class="form-control input-lg"
									id="idDepositAmount" placeholder="Amount" min="1" name="amount">
							</div>
							<span style="color: red" class="error" id="idDepositError"></span>
							<div class="form-group" style="padding-left: 17px;">
								<label class="radio-inline"> <input type="radio"
									name="type" id="txTypeBtn" value="Withdraw" class="required">
									Withdraw
								</label> <label class="radio-inline"> <input type="radio"
									name="type" id="txTypeBtn" value="Deposit"> Deposit
								</label><br> <span style="color: red" class="error"
									id="txTypeError"></span>
							</div>
							<button type="button" class="btn btn-primary"
								id="idDepositButton">Transfer cash</button>
						</div>
					</form>
					<div id="idDisplayDespositMessage" style="display: none"></div>
				</div>
				<a href="#" onclick="displayTx()" class="thumbnail"
					style="font-size: 40px; text-decoration: none; border: 0px; width: 428px;">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"
					style="font-size: 30px; display: inline;"></span> Display
					Transactions
				</a>
			</div>
		</div>
		<div id="idDisplayTransaction" style="display: none">
					<div class=" col-sm-6 col-sm-offset-2">
						<table class="table ">
							<thead id="txTableHead">
								<tr>
								<th>S.No</th>
									<th colspan="2">Date</th>
									<th>Type</th>
									<th>Amount</th>
									<th>Current Balance</th>
								</tr>
							</thead>
							<tbody id="txTable">

							</tbody>
						</table>
					</div>
				</div>
	</div>








	<!-- Scripts in the end of the page -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script
		src='<spring:url value="/resources/js/usermain.js"></spring:url>'></script>
	<script
		src='<spring:url value="/resources/js/lib/serialize-object.js"></spring:url>'></script>
</body>
</html>