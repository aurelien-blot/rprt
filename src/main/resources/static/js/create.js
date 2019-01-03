$(document).ready(function(){
	initQuoteButtonOff();
	selectContractEvent();
	selectProjectEvent();
	selectTeamsEvent();
	$('#newProjectModal').modal({backdrop: 'static', keyboard: false})  
	showModal('#newProjectModal');
})

function showModal(idModal){
	$(window).on('load',function(){
        $(idModal).modal('show');
    });
}

function selectContractEvent(){
	$("#selectContractNewQuote").change(function() {
		var selectedContract = $('#selectContractNewQuote option:selected').val();
		if(selectedContract != ""){
			callAjax('GET', showProjectsList, "/devis/creation/api/projects/byContract/"+selectedContract);
			callAjax('GET', showTeamsList, "/devis/creation/api/teams/byContract/"+selectedContract);
			
		}
		
	});
}

//Affichage de la liste des équipes du contrat en filtrant sur le projet

function selectProjectEvent(){
	$("#selectProjectNewQuote").on("change", function(e){

		var selectedProject = $('#selectProjectNewQuote option:selected').val();
		callAjax('GET', showTeamsList, "/devis/creation/api/teams/byProject/"+$(e.target).val());
		$('#teamSelectDiv').removeClass("d-none");
	});
}

function selectTeamsEvent(){
	$("#selectTeamNewQuote").on("change", function(e){
		initQuoteButtonOn();
	});
}

//Affichage de la liste des projets
function showProjectsList(data){
	$('#selectProjectNewQuote').empty();
	$('#selectProjectNewQuote').append('<option value="" hidden>Choisissez un projet</option>');
	
	//$(data).each(function(e){
		data.forEach(function(e){
		$('#selectProjectNewQuote').append('<option value="'+ e.id +'">'+ e.name +'</option>');
	});
}

//Affichage de la liste des équipes
function showTeamsList(data){
	$('#selectTeamNewQuote').empty();
	$("#selectTeamNewQuote").animate({height: "110px"})
	$('#selectTeamNewQuote').attr('multiple', true);

	$('#selectTeamNewQuote').append('<option  value="" select disabled>Choisissez une ou plusieurs équipes (CTRL + clic)</option>');
	
	//$(data).each(function(e){
		data.forEach(function(e){
		$('#selectTeamNewQuote').append('<option value="'+ e.id +'">'+ e.name +'</option>');
	});
}


//Activation du bouton initialisation
function initQuoteButtonOn(){
	$('#initQuoteButton').removeAttr("disabled");
	$('#initQuoteButton').removeClass('btn-outline-secondary');
	$('#initQuoteButton').addClass('btn-outline-success');
}

// Désactivation du bouton d'initialistion
function initQuoteButtonOff(){
	$('#initQuoteButton').attr("disabled", true);
	$('#initQuoteButton').removeClass('btn-outline-success');
	$('#initQuoteButton').addClass('btn-outline-secondary');
}