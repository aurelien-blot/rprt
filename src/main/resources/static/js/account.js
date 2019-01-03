$(document).ready(function(){
	validationClickEvent();
	validationKeyupEvent();
	$("#succesDiv").hide();
});


function validationClickEvent(){
	
	$('#sendNewPwdBtn').on("click", function(e){
		e.preventDefault();
		cleanValidations();
		if(emptiesPassword().length!=0){	
			emptyValidation();
			
		}
		else if(compareOldAndNewPassword()){
			sameOldPasswordValidation();
		}
		else if(!comparePasswords()){	
			samePasswordValidation();
			
		}
		else{
			successAnimation();
			setTimeout(function(){
				console.log('test1')
				$("form").submit();
			}, 3600);
		}		
	});
}

function validationKeyupEvent(){
	
	$('input').on("keyup", function(e){
		cleanValidations();
		if(emptiesPassword().length!=0){	
			emptyValidation();
			e.preventDefault();
		}
	})
	$('#newPwd1 , #newPwd2').on("keyup", function(e){
		if(!comparePasswords()){	
			samePasswordValidation();
		}
		
	})
	$('#newPwd1').on("keyup", function(e){
		if(compareOldAndNewPassword()){
			sameOldPasswordValidation();
		}
		
	})
}


function comparePasswords(){
	if($('#newPwd1').val()===$('#newPwd2').val()){
		return true;
	}
	else{
		return false;
	}
}

function emptiesPassword(){
	
	var errors=[];
	
	if($('#newPwd1').val()== ""){
		errors.push($('#newPwd1'));
	}
	if($('#newPwd2').val() ==""){
		errors.push($('#newPwd2'));
	}
	if ($('#previousPwd').val() ==""){
		errors.push($('#previousPwd'));
	}
	return errors;
}
//previousPwdFeedBack
function emptyValidation(){
	$(emptiesPassword()).each(function(i){
		var errorDiv= this.next('.invalid-feedback');
		errorDiv.text("Le champ ci-dessus est vide.");
		$(this).addClass("is-invalid");
	});
	
}

function compareOldAndNewPassword(){
	if($('#newPwd1').val()==$('#previousPwd').val()){
		return true;
	}
	else{
		return false;
	}
}
function samePasswordValidation(){
	var message = "Les 2 mots de passe ne correspondent pas."
	$('#newPwd1 , #newPwd2').addClass("is-invalid");
	$('#newPwd1FeedBack , #newPwd2FeedBack').text(message);

}

function sameOldPasswordValidation(){
	var message = "Le nouveau mot de passe est identique à l'ancien.";
	$('#newPwd1').addClass("is-invalid");
	$('#newPwd1FeedBack').text(message);

}

function cleanValidations(){
	$('input').removeClass("is-invalid");
	$('.invalid-feedback').text('');
}
function successAnimation(){
	$("#succesDiv").hide();
	$("#succesDiv").removeClass("d-none");
	$("#succesDiv").fadeIn(400).delay(3000).fadeOut(400);
}