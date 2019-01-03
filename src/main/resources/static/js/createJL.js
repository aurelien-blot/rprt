// Changement de contrat
$("#selectContractNewQuote").change(function() {
	initQuoteButtonOff();
	selectContract();
});

// Affichage de des Projets et Equipes en fonction du contrat
function selectContract(){
	var selectedContract = $('#selectContractNewQuote option:selected').val();

	if(selectedContract != ""){
		callAjax('GET', showProjectsList, "/devis/creation/api/projects/byContract/"+selectedContract);
		callAjax('GET', showTeamsList, "/devis/creation/api/teams/byContract/"+selectedContract);
	}
	alertSelectContractBeforeOff();
	alertfilterByContractOn();
}

// Affichage de la liste des équipes du contrat en filtrant sur le projet
$("#selectProjectNewQuote").on("change", function(e){
	var selectedTeam = $('#selectTeamNewQuote option:selected').val();
	var selectedProject = $('#selectProjectNewQuote option:selected').val();
	
	if(typeof selectedTeam === "undefined"){
		callAjax('GET', showTeamsList, "/devis/creation/api/teams/byProject/"+$(e.target).val());
		alertFilterByProjectOn();
	}
	verifyInit();
});

// Affichage de la liste des projets du contrat en filtrant sur une ou plusieurs équipes
$("#selectTeamNewQuote").on("change", function(e){
	var selectedTeam = $('#selectTeamNewQuote option:selected').val();
	var selectedProject = $('#selectProjectNewQuote option:selected').val();
	
	if(selectedProject == ""){
		callAjax('GET', showProjectsList, "/devis/creation/api/projects/byTeam/"+$(e.target).val());		
		alertFilterByTeamOn();
	}
	verifyInit();
});

// Affichage de la liste des projets
function showProjectsList(data){
	$('#selectProjectNewQuote').empty();
	$('#selectProjectNewQuote').append('<option value="" hidden>Choisissez un projet</option>');
	
	//$(data).each(function(e){
		data.forEach(function(e){
		$('#selectProjectNewQuote').append('<option value="'+ e.id +'">'+ e.name +'</option>');
	});
}

// Affichage de la liste des équipes
function showTeamsList(data){
	$('#selectTeamNewQuote').empty();
	$("#selectTeamNewQuote").animate({height: "110px"})
	$('#selectTeamNewQuote').attr('multiple', true);

	$('#selectTeamNewQuote').append('<option value="" select disabled>Choisissez une ou plusieurs équipes (CTRL + clic)</option>');
	
	//$(data).each(function(e){
		data.forEach(function(e){
		$('#selectTeamNewQuote').append('<option value="'+ e.id +'">'+ e.name +'</option>');
	});
}

// Reset des champs projet et équipe du formulaire
$('.resetTeamAndContract').on("click", function(e){
	var indexSelectedContract = $("#selectContractNewQuote").prop('selectedIndex');
	$('#selectContractNewQuote').prop('selectedIndex', indexSelectedContract);
	selectContract();
});


//////////// BOUTON D'INITIALISATION  ////////////

// vérification sur les champs input
$('#quoteName, #descName').on("keyup", function(e){
	verifyInit();
});

// vérification sur le champ template
$("#selectTemplateNewQuote").on("change", function(e){
	verifyInit();
});

// vérification sur les champs select
function verifyInit(){
	var quoteNameInput = $('#quoteName').val();
	var descNameInput = $('#descName').val();
	var selectedContract = $('#selectContractNewQuote option:selected').val();
	var selectedTemplate = $('#selectTemplateNewQuote option:selected').val();
	var selectedTeam = $('#selectTeamNewQuote option:selected').val();
	var selectedProject = $('#selectProjectNewQuote option:selected').val();

	if (quoteNameInput != "" && descNameInput != "" && selectedContract != "" && selectedTemplate != "" && selectedProject != "" && typeof selectedTeam !== "undefined") {
		initQuoteButtonOn();
	}
	else {
		initQuoteButtonOff();
	}
}

// Activation du bouton initialisation
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


//////////////// MESSAGES D'ALERTE ////////////////

// Alerte sélection du contrat en premier
$('#selectTeamNewQuote, #selectProjectNewQuote').on("click", function(e){
	var selectedContact = $('#selectContractNewQuote option:selected').val();
	
	if(selectedContact == ""){
		alertSelectContractBeforeOn();
	}
	
});

// Alerte select contrat ON
function alertSelectContractBeforeOn() {
	$('#alertSelectContractBefore').removeClass("d-none");
}

//Alerte select contrat OFF
function alertSelectContractBeforeOff() {
	$('#alertSelectContractBefore').addClass("d-none");
}

//Alerte filterByContract ON
function alertfilterByContractOn() {
	alertfilterByTeamOff();
	alertfilterByProjectOff();
	$('#filterByContract').removeClass("d-none");
}

//Alerte filterByContract OFF
function alertfilterByContractOff() {
	$('#filterByContract').addClass("d-none");
}

//Alerte filterByTeam ON
function alertFilterByTeamOn() {
	alertfilterByContractOff();
	$('#filterByTeam').removeClass("d-none");
}

//Alerte filterByTeam OFF
function alertfilterByTeamOff() {
	$('#filterByTeam').addClass("d-none");
}

//Alerte filterByProject ON
function alertFilterByProjectOn() {
	alertfilterByContractOff();
	$('#filterByProject').removeClass("d-none");
}

//Alerte filterByProject OFF
function alertfilterByProjectOff() {
	$('#filterByProject').addClass("d-none");
}