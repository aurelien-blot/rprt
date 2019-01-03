$(document).ready(function(){
	verifyUsernameOnKeyUp();
	verifyUsernameOnClick();
	$('.deleteUserBtn').each(function(e){

		$(this).on("click", function(i){
			i.preventDefault();
			alertDeleteUser($(this).attr("data-id"));
		})
	})
})

var usernameAlreadyExist = false;

function verifyUsernameOnKeyUp(){
	$('#usernameValue').on('keyup', function(e){
		callAjax("GET", 
				function(data){
					if(!data){
						usernameExist();
					}
					else{
						usernameIsNew();
					}
				},
				"/user/api/verify/username/"+$('#usernameValue').val());	
		})
}

function verifyUsernameOnClick(){
	$('#submitBtn').on('click', function(e){
		if(usernameAlreadyExist){
			e.preventDefault();
		}
	});
}

function usernameExist(){
	var message = "Ce nom d'utilisateur est déjà utilisé."
		$('#usernameValue').addClass("is-invalid");
		$('#usenameFeedBack').text(message);
		usernameAlreadyExist=true;
}

function usernameIsNew(){
	$('#usernameValue').removeClass("is-invalid");
	$('#usenameFeedBack').text();
	usernameAlreadyExist=false;
}

$('#initPwdBtn').on("click", function(e){
	
	callAjax("GET", function(i){

		if(i=="ok"){
			$('#initPwdBtn').hide();
			$('#genericPwdTd').removeClass("d-none");
			confirmResetPwd();
		}
		else{
			
		}
		
	}, "/user/api/resetpwd/"+$('#idValue').val());
});

function confirmResetPwd(){
	var dialog = $('<p>Le mot de passe a été réinitialisé.</p>').dialog({
		closeText: "hide",
        buttons: {
            "Ok":  function() {
                dialog.dialog('close');
            }
        }
    });
}

function alertDeleteUser(id){
	var dialog = $('<p>Êtes-vous sûr de supprimer cet utilisateur ?</p>').dialog({
		closeText: "hide",
        buttons: {
            "Supprimer": function() {
            	document.location.href="/administration/users/delete/"+id;
            },
            "Annuler":  function() {
                dialog.dialog('close');
            }
        }
    });
}