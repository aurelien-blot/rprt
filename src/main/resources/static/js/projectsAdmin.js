// APPELE SUR PROJECTADMIN.html et pas sur PROJECTSADMIN.html

$(document).ready(function(){
	contractSelectChangeEvent();
	if($('#contractSelect :selected').val()!=""){
		callAjax('GET', loadTeamsByContract,"/devis/creation/api/teams/byContract/"+$('#contractSelect :selected').val());
	}
	validBtnEvent();

});

function contractSelectChangeEvent(){
	$('#contractSelect').on("change", function(e){
		$('.teamCheckboxes input').prop("checked", false);
		$('.teamCheckboxes').addClass('d-none');
		callAjax('GET', loadTeamsByContract,'/devis/creation/api/teams/byContract/'+$(this).val());
	});
}

function loadTeamsByContract(data){
	$(data).each(function(e){
		var id = this.id;
		
		$('.teamCheckboxes input').each(function(i){
			
			if(id == $(this).val()){
				$(this).parent().removeClass("d-none");

			}
		})
	})
}

function validBtnEvent(){
	$('#saveBtn').on("click", function(e){
		if($('.teamCheckboxes :checked').length ==0){
			e.preventDefault();
			teamsErrorAnimation()
		}
	});
};


function teamsErrorAnimation(){
	$("#teamsTh").animate({zoom: '150%', color:'red'},  "slow").animate({zoom: '100%', color:'black'},3000);
}
