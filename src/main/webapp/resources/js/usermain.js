/**
 * 
 */

console.log("usermain.js");

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

function deposit(){
	$('#idDisplayTransaction').slideUp();
	$('#idDepositFormDisplay').slideToggle();
	$('#idDepositAmount').focus();
	$('#idDisplayDespositMessage').hide();
	$('#idDepositForm')[0].reset();
	$('.error').hide();
	
	$('#idDepositButton').click(function(){
		
		if( $('#idDepositAmount').val()!=""  ){
			
			if($("input[name=type]:checked").val()!=undefined){
				
				console.log($("input[name=type]:checked").val());
				
				var depositform=$('#idDepositForm').serializeObject();
				
				console.log(JSON.stringify(depositform));
				
				var amount=Number($('#idDepositAmount').val());
				if(typeof amount==="number" && amount>0){
					$.ajax({
						url:"account/deposit",
						type:"POST",
						data:{
							amount:depositform.amount,
							txType:depositform.type
						},
						//dataType:"json",
						success:function(xhr,response){
							console.log("success:  " +response);
							$('#idDisplayDespositMessage').html('');
							if($("input[name=type]:checked").val()=="Deposit"){
								$('#idDisplayDespositMessage').html("<div class=\"alert alert-success\" role=\"alert\" style=\"margin-bottom: 0px;width:300px; font-Size:20px;text-align: center;\">Desposited</div>").show();
							}else{
								$('#idDisplayDespositMessage').html("<div class=\"alert alert-success\" role=\"alert\" style=\"margin-bottom: 0px;width:300px; font-Size:20px;text-align: center;\">Withdrawn</div>").show();
							}
							$('.error').hide();
						},
						error:function(){

						}


					});
				}else{
					$('#idDepositError').html("Minimum $1").show();
				}
			}else{
					$('#txTypeError').html("Please select the type of TX").show();
			}
		}else{
			$('#idDepositError').html("Required").show();
		}
	});
}

function displayTx(){
	$('#txTable').empty();
	$('#idDepositFormDisplay').slideUp();
	$('#idDisplayTransaction').slideToggle();
	$.ajax({
		url:"account/getAllTx",
		type:"GET",
		dataType:"json",
		contentType:"application/json",
		success:function(xhr,response){
			$.each(xhr,function(idx,obj){
				console.log(idx+"::"+obj)
				$('#txTable').append('<tr>'+
						'<td>'+(idx+1)+'</td>'+
						'<td colspan="2">'+obj.date+'</td>'+
						'<td>'+obj.type+'</td>'+
						'<td>'+obj.amount+'</td>'+
						'<td>'+obj.balance+'</td>'+
						'</tr>');
				
			});
			console.log(response);
		},error:function(xhr,response){
			console.log(xhr);
			console.log(response);
		}
	});
}