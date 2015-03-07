/**
 * main.js
 */

console.log("main.js");
function signIn(){
	var regForm=$('#loginForm');
	
	if($('#username').val()!=""){
		if($('#password').val()!=""){
			var jsonObj=regForm.serializeObject();
			$.ajax({
				type:"POST",
				url:"account/validate",
				data:{
					validateLogin:JSON.stringify(jsonObj),
				},
				success:function(response){
					$('#username-error').slideUp(100);
					$('input#username').css("border","");
					$('#error').hide();
					var obj = $.parseJSON(response);
					if(obj.status==="AccountExits"){
						console.log("success: redirected to welcome controller");
						window.location.href = obj.targetUrl;
					}else{
						console.log("success: invalid username");
						$('#error').html("Invalid username password").show();
					}
				},
				error:function(){
					console.log("Error: Check backend");
					$('#error').html("Invalid username password").show();
				},
//				dataType:JSON
			});
		}else{
			if($('#username').val()!=""){
				$('#username-error').toggle();
				$('input#username').css("border","");
			}
			$('#password-error').html("Required").show();
			$('input#password').css("border","1px solid red");
		}
	}else{
		if($('#password').val()!=""){
			$('#password-error').toggle();
			$('input#password').css("border","");
		}
		$('#username-error').html("Required").show();
		$('input#username').css("border","1px solid red");
	}
};


function addAccount(){
	console.log("add Account");
	$('#adminPanel').slideUp(500);
	$('.error').hide();
	$('#addAccountForm')[0].reset();
	
	$('#addAccount').toggle();
	var addAccountForm= $('#addAccountForm');
	$('#saveButton').click(function(){
		
		var jsonObj=addAccountForm.serializeObject();
		$.ajax({
			type:"POST",
			url:"account",
			data:{
				incomingData:JSON.stringify(jsonObj),
			},
			success:function(response){
				console.log("success");
				$('#message').html("Account Added").show();
				$('#addAccount').fadeOut(2000,function(){
					$('#adminPanel').fadeToggle();
					$('#message').hide();
				});
			},
			error:function(){
				console.log("Error in addAccount");
				$('#message').html("Error").fade(500);
				$('#adminPanel').slideDown(1000);
				$('#addAccount').toggle();
			},
		});
	});
};

function logout(){
	console.log("Logout");
//	console.log(window.location);
	$.ajax({
		url:"logout",
		type:"GET",
		success:function(response){
			if(response)
			window.location.href=window.location.origin+"/BankRestAPI";
		}
	});
	

};
function resetAdminPanel(){
	console.log("resetAdminPanel");
	$('#adminPanel').fadeIn(500,function(){
		window.location.href=window.location.origin+"/BankRestAPI/welcome";
	});
	
	$('#addAccountForm')[0].reset();
	$('#addAccount').hide();
	
	$('#deleteAccountForm')[0].reset();
	$('#deleteAccount').hide();
	
	$('#changePasswordForm')[0].reset();
	$('#changePassword').hide();
	
	$('#getInfoForm')[0].reset();
	$('#getInfo').hide();
	
	$('#displayUser').hide();
	//window.location.href=window.location.origin+"/BankRestAPI/welcome";

};

function deleteAccount(){
	console.log("delete Account");
	$('#adminPanel').slideUp(500);
	$('.error').hide();
	$('#deleteAccountForm')[0].reset();
	$('#deleteAccount').hide();
	$('#deleteAccount').toggle();
	$('#deleteButton').click(function(){
		var deluser= $('#deleteusername').val();
		console.log(typeof deluser);
		$('.error').hide();
		if(deluser !=""){
			console.log("****************"+deluser);
			$.ajax({
				type:"PUT",
				url:"account/deactivate/"+deluser,
				dataType: 'string',
				success:function(response){
					var res=$.parseJSON(response);
					if(res.status==="Account Deleted"){
						$('#deactivatemessage').html("Account Detactivated").show();
						$('#deleteAccount').fadeOut(2000,function(){
							$('#adminPanel').fadeToggle();
							$('#deactivatemessage').hide();
						});
					}else if(res.status==="Account not found"){
						$('#deactivatemessage').html("Account Not found").show();
						$('#deactivatemessage').fadeOut(1500);
					}
				},
				error:function(){
					console.log("error");
				},
				dataType: "text",
			});
		}else{
			$('#error-delete').html('Username required').show();
		}
	});
};

function changePassword(){
	console.log("Change password");
	$('#adminPanel').slideUp(500);
	$('.error').hide();
	$('#changePasswordForm')[0].reset();
	
	$('#changePassword').toggle();
	$('#changeButton').click(function(){

		var changeuser= $('#changePasswordForm').serializeObject();
		console.log(JSON.stringify(changeuser));
		console.log(changeuser.username);
		$('.error').hide();
		if(changeuser !=""){
			$.ajax({
				type:"POST",
				url:"account/changePassword",
				data:{
					name:changeuser.username
				},
				dataType:"json",
				contentType:"application/json",
				success:function(response){
					var res=$.parseJSON(response);
					if(res.status==="Account deleted"){

					}else if(res.status==="Account not found"){

					}
				},
				error:function(){
					console.log("error");
				},
				dataType: "text",
			});
		}else{
			$('#error-delete').html('Username required').show();
		}
	});
};

function getInfo(){
	console.log("Get Account Info");
	$('#adminPanel').slideUp(500);
	$('.error').hide();
	$('#tableBody').html("");
	$('#getInfoForm')[0].reset();
	
	$('#getInfo').toggle();
	$('#getButton').click(function(){
		var user= $('#getusername').val();
		$('.error').hide();
		if(user !=""){
			$.ajax({
				type:"GET",
				url:"account",
				data:{
					username:user,
				},
				dataType:"json",
				contentType:"application/json",
				success:function(xhr,response){
					console.log("succes: "+xhr);
					console.log(response);
					var res=$.parseJSON(xhr);
					console.log(res);
					if(res.status==="Active" || res.status==="D-Active"){
						$('#idAccountsList').html('<tr>'+
								'<td>'+res.id+'</td>'+
								'<td>'+res.name+'</td>'+
								'<td>'+res.accountType+'</td>'+
								'<td>'+res.amount+'</td>'+
								'<td>'+res.username+'</td>'+
								'<td>'+res.status+'</td>'+
								'</tr>'
								);
			        $('#displayUser').show();
					}else if(res.status==="Account not found"){
						$('#getInfoMessage').html("Account Not found").show();
						$('#getInfoMessage').fadeOut(2000);
					}
				},
				error:function(){
					console.log("error");
				},
				dataType: "text",
			});
		}else{
			$('#error-delete').html('Username required').show();
		}
	});
};
function getAll(){
	console.log("Get All");
	$('#idAccountsList').html('');
		$.ajax({
		type:"GET",
		url:"account/getall",
		dataType:"json",
		contentType:"application/json",
		success:function(xhr,response){
			console.log(response);
			console.log(xhr);
			$.each(xhr, function(idx, obj) {
				$('#idAccountsList').append('<tr>'+
						'<td>'+obj.id+'</td>'+
						'<td>'+obj.name+'</td>'+
						'<td>'+obj.accountType+'</td>'+
						'<td>'+obj.amount+'</td>'+
						'<td>'+obj.username+'</td>'+
						'<td>'+obj.status+'</td>'+
						'</tr>');
		});
	        $('#displayUser').show();
		},
		error:function(){
			console.log("error");
		},
	});
};