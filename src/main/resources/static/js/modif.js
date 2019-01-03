
//region Event Listeners Globaux


//$('.ss-total').remove();


var deletedPhases=[];
var deletedModules=[];

$(document).ready(function(){
	initPhaseAutocomplete();
	addEventsOnPhasesAndModules();
	addEventsToPhasesList(listElements(".phaseLine"));
	addEventsToModulesList(listElements(".moduleLine"));

		
});

function addEventsOnPhasesAndModules(){
	preventEnterKey($('#abacusForm input'));
	preventIfNotNumeric();
	//numeric
}

function addEventsToModulesList(list){
	$(list).each(function(e){
		var idModule= $(this).attr('id');
		deleteModule(idModule);
		onCalcModify();

	})
}

function addEventsToPhasesList(list){
	$(list).each(function(e){
		var idLine= $(this).attr('id');
		deletePhase(idLine);
		eventWitoutRatioKeyUp(idLine);
		eventOnGlobalKeyUp(idLine);
		eventOnRTUKeyUp(idLine);
	})
}


var initPhaseAutocomplete = function(){
	$( ".phaseNameInput" ).autocomplete({
		delay: 800,
		minLength : 3,
		source: function( request, response ) {
			// Fetch data
			callAjax("GET", 
				function(data){
					
					// Résultats commmençant par la recherche
					var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( request.term ), "i" );
					var dataFirst = $.grep( data, function( item ){
			        	return matcher.test( item );
			        });
					
					// Résultats ne commmençant pas par la recherche
					var dataSecond = $.grep( data, function( item ){
			        	return !(matcher.test( item ));
			        });
					
					// Affichage de tous les résultats avec un maximum
					var dataSorted = $.merge( $.merge( [], dataFirst ), dataSecond );
					var maxLengthResults = 5;
					if (dataSorted.length > maxLengthResults){
						dataSorted = dataSorted.slice(0, maxLengthResults)
					}
					response(dataSorted);
				},
				"/api/phase/"+request.term
			);
		}
//	,
//		select: function (event, ui) {
//			// Set selection
//			$('.phaseNameInput').val(ui.item.label); // display the selected text
//			//$('#selectuser_id').val(ui.item.value); // save selected id to input
//			return false;
//		}
		});
	}



function findDistincts(moduleLineId){
	callAjax("GET", function(e){
		$(e.type).each(function(e){
			$('#'+moduleLineId+' .typeInput select').append('<option value="'+this+'">'+ this+'</option>');
		});
		$(e.typology).each(function(e){
			$('#'+moduleLineId+' .typologyInput select').append('<option value="'+this+'">'+ this+'</option>');
		});
		$(e.task).each(function(e){
			$('#'+moduleLineId+' .taskInput select').append('<option value="'+this+'">'+ this+'</option>');
		});
		$(e.complexity).each(function(e){
			$('#'+moduleLineId+' .complexityInput select').append('<option value="'+this+'">'+ this+'</option>');
		});
		$(e.interventionLvl).each(function(e){
			$('#'+moduleLineId+' .interLvlInput select').append('<option value="'+this+'">'+ this+'</option>');
		});

	}, "/devis/api/modulesfields/"+$('#abacusId').val());
}

function findTheMetric(moduleLineId){
	$('#'+moduleLineId+' .moduleSelect').on("change", function(e){
		$('#'+moduleLineId+' .unitChargesInput input').val('');
		callAjax("GET", function(e){
			if(e!=null){
				$('#'+moduleLineId+' .idMetricInput').val(e.id);
				findTheParameter(moduleLineId, e.id);
			}

		}, "/devis/api/metric/"+$('#abacusId').val()+"/"+$('#'+moduleLineId+' .complexityInput select').val()+"/"+$('#'+moduleLineId+' .interLvlInput select').val()+"/"+$('#'+moduleLineId+' .taskInput select').val()+"/"+$('#'+moduleLineId+' .typeInput select').val()+"/"+$('#'+moduleLineId+' .typologyInput select').val());
	})
}

function findTheParameter(moduleLineId, idMetric){
	callAjax("GET", function(i){
		$('#'+moduleLineId+' .idParameterInput').val(i.id);
		$('#'+moduleLineId+' .unitChargesInput input').val(i.unitCharge);
			fullShowAndCalculate();
		},"/devis/api/parameter/"+$('#abacusId').val()+"/"+idMetric);
}
	
function addEmptymoduleLine(){
	
	var moduleLineId = incrementModuleLineId()

	var line =$('<tr id="'+moduleLineId +'" class="moduleLine"></tr>');
	line.append('<td class="d-none"><input class="moduleIdInput"/></td>');
	line.append('<td class="moduleNameInput "><input class="moduleInput" type="text" placeholder="Ajouter un module"/></td>');
	line.append('<td class="typeInput "><select class="moduleInput moduleSelect"><option value=null>--</option></select></td>');
	line.append('<td class="typologyInput "><select class= "moduleInput moduleSelect"><option value=null>--</option></select></td>');
	line.append('<td class="taskInput "><select class= "moduleInput moduleSelect"><option value=null>--</option></select ></td>');
	line.append('<td class="complexityInput "><select class= "moduleInput moduleSelect"><option value=null>--</option></select></td>');
	line.append('<td class="interLvlInput "><select class= "moduleInput moduleSelect"><option value=null>--</option></select></td>');
	line.append('<td class="unitChargesInput "><input disabled class="calc moduleInput" size="6" type="text" /></td>');
	line.append('<td class="d-none"><input class="idMetricInput" /></td>');
	line.append('<td class="d-none"><input class="idParameterInput" /></td>');
	line.append('<td class="revisedChargesInput "><input class="calc numeric moduleInput" size="6" type="text" placeholder="" /></td>');
	line.append('<td class="deleteModule "><button  title="Supprimer le module" class="btn btn-md btn-danger btn-lg btn-rond"><i class="far fa-times-circle"></i></button></td>');
	$('#productionWorkLoadData').append(line);
	verifyAddModule();
	
	addEventsOnPhasesAndModules();
	$('#productionWorkLoadData').append(line);
	findDistincts(moduleLineId);
	addEventsToModulesList(listElements(".moduleLine"));
	findTheMetric(moduleLineId);

}

function deleteModule(idModule){
	$('#'+idModule+' .deleteModule button').on("click", function(e){
		
		var moduleId= $('#'+idModule+' .moduleIdInput').val();
		if(moduleId != ""){
			deletedModules.push(parseInt(moduleId));
		}
		$(e.target).parents('tr').remove();	
		fullShowAndCalculate();
		verifyDeleteModule();
	})	
}

function onCalcModify(){
	$('.calc').on("keyup", function(e){
		fullShowAndCalculate();
	});
}

function verifyDeleteModule(){
	if ( $('.moduleLine').length == 0 ) {
		ModuleBtn_Off();		
		step3_Off();
	}
}

function verifyAddModule(){
	if ( $('.moduleLine').length > 0 ) {
		moduleBtn_On();
	}
}


function ModuleBtn_Off() {
	$('#PWLValidationButton').removeClass('btn-outline-success');
	$('#PWLValidationButton').addClass('btn-outline-secondary');
	$('#PWLValidationButton').attr("disabled", true);
//	$('#PWLValidationButton').removeClass('pointer');
}


function GWLValidationButton_Off() {
	$('#GWLValidationButton').removeClass('btn-outline-success');
	$('#GWLValidationButton').addClass('btn-outline-secondary');
	$('#GWLValidationButton').attr("disabled", true);

//	$('#GWLValidationButton').removeClass('pointer');
}


function sendBtn_Off() {
	$('#sendBtn').removeClass('btn-outline-success');
	$('#sendBtn').addClass('btn-outline-secondary');
	$('#sendBtn').attr("disabled", true);
}

// Vérification à l'ajout et suppression des phases
function verifyaddPhase(){
	if ( $('.phaseLine').length > 1 ) {
		GWLValidationButton_On();
	}
}

function verifyDeletePhase(){
	if ( $('.phaseLine').length < 2 ) {
		GWLValidationButton_Off();
		sendBtn_Off();
	}
}

$('#addModuleBtn').on("click", addEmptymoduleLine);

// Bouton Valider le Chiffrage Réalisation
$('#PWLValidationButton').on("click", function(e){
	step3_On();
	showPart3();
});

//Bouton Valider le Chiffrage Global
$('#GWLValidationButton').on("click", function(e){
	sendBtn_On();
});

//Bouton Valider le Chiffrage Global
$('#sendBtn').on("click", function(e){
	e.preventDefault();
	alert('Le devis a bien été envoyé en validation');
});
	
$('#addPhaseBtn').on("click", addEmptyPhaseLine);




function addEmptyPhaseLine(){

	var GWLLine = incrementPhaseLineId()
	var line =$('<tr id="'+GWLLine +'"class="phaseLine"></tr>');
	line.append('<td class="d-none"><input class="phaseIdInput"/></td>');
	line.append('<td><input class="phaseNameInput" type="text" placeholder="Ajouter une phase"/></td>');
	line.append('<td class="typeRatioInput"><select class="selectPhaseType"><option value="ONRTU">Sur RTU</option><option value="WITHOUTRATIO">Sans ratio / Sur devis</option><option value="UOS">Unité d\'oeuvre spécifique</option><option value="ONGLOBAL">Sur global</option></select></td>');
	line.append('<td class="chargesWithoutratio"><input class="chargesWithoutratioValue numeric" size="2" type="text"/></td>');
	line.append('<td class="onRTU"><input class="onRTUValue numeric" size="2" type="text" /> %</td>');
	line.append('<td class="charges"><span  class="chargesValue">0</span></td>');
	line.append('<td class="onGlobal"><input class="onGlobalValue numeric" size="2" type="text"/> %</td>');
	line.append('<td class="deletePhase"><button  title="Supprimer la phase" type="button" class="btn btn-md btn-danger btn-lg btn-rond"><i class="far fa-times-circle"></i></button></td>');

	$('#GWLData').append(line);
	adaptPhaseLine(line, GWLLine);
	addEventsOnPhasesAndModules();
	$('#'+GWLLine+' .selectPhaseType').on("change",function(){
		adaptPhaseLine(line, GWLLine);
		cleanPhaseLine(GWLLine);
		fullShowAndCalculate();
	});
	
	addEventsToPhasesList(listElements(".phaseLine"));
	
	initPhaseAutocomplete();
	
	verifyaddPhase();

}

function deletePhase(GWLLine){
	$('#'+GWLLine+' .deletePhase button').on("click", function(e){
		var phaseId= $('#'+GWLLine+' .phaseIdInput').val();
		if(phaseId != "" && phaseId != null){
			deletedPhases.push(parseInt(phaseId));
		}
		
		removeNoteLine(GWLLine);
		$(e.target).parents('tr').remove();
		
		fullShowAndCalculate();
		
		verifyDeletePhase();
	})
}

function eventWitoutRatioKeyUp(GWLLine){
	$('#'+GWLLine+' .chargesWithoutratioValue').on("keyup", function(e){
		fullShowAndCalculate();

	});
}

function eventOnGlobalKeyUp(GWLLine){
	$('#'+GWLLine+' .onGlobalValue').on("keyup", function(e){
		fullShowAndCalculate();
	});
}

function eventOnRTUKeyUp(GWLLine){
	$('#'+GWLLine+' .onRTUValue').on("keyup", function(e){
		$('#'+GWLLine+' .chargesValue').text(calculChargesOnRtu(GWLLine));
		fullShowAndCalculate();

	});
}

function adaptPhaseLine(line, phaseLineId){
	var optionSelected = $(line).find(' .selectPhaseType option:selected').val();
	removeNoteLine(phaseLineId);
		$(line).removeAttr("th:disabled");
	switch(optionSelected){
		case "ONRTU":		
			$('#'+phaseLineId+' .chargesWithoutratio input').attr("disabled", true);
			$('#'+phaseLineId+' .onGlobal input').attr("disabled", true);
			$('#'+phaseLineId+' .onRTU input').attr("disabled", false);
			break;
		case "WITHOUTRATIO":
			$('#'+phaseLineId+' .chargesWithoutratio input').attr("disabled", false);
			$('#'+phaseLineId+' .onGlobal input').attr("disabled", true);
			$('#'+phaseLineId+' .onRTU input').attr("disabled", true);
			addNoteLine(phaseLineId);
			
			break;
		case "UOS":
			$('#'+phaseLineId+' .chargesWithoutratio input').attr("disabled", false);
			$('#'+phaseLineId+' .onGlobal input').attr("disabled", true);
			$('#'+phaseLineId+' .onRTU input').attr("disabled", true);
			addNoteLine(phaseLineId);
			break;
		case "ONGLOBAL":
			$('#'+phaseLineId+' .chargesWithoutratio input').attr("disabled", true);
			$('#'+phaseLineId+' .onGlobal input').attr("disabled", false);
			$('#'+phaseLineId+' .onRTU input').attr("disabled", true);
			break;
		default:
			$('#'+phaseLineId+' .chargesWithoutratio input').attr("disabled", true);
			$('#'+phaseLineId+' .onGlobal input').attr("disabled", true);
			$('#'+phaseLineId+' .onRTU input').attr("disabled", true);
			
	}
}

function cleanPhaseLine(phaseLineId){
	$('#'+phaseLineId+' .onRTUValue').val('');
	$('#'+phaseLineId+' .chargesValue').text("");
	$('#'+phaseLineId+' .onRTUValue').val('');
	$('#'+phaseLineId+' .chargesWithoutratioValue').val('');
}



// END CALCULS ET AFFICHAGE


function addNoteLine(phaseLineId){
	var idNoteLine = "note"+phaseLineId;
	var noteLine = '<tr id="'+idNoteLine+'" class="noteLine"><td colspan="7" class=" col-lg-12 text-center"><input class="justifValue col-lg-10" type ="textarea" placeholder="Merci de justifier la charge imputée." /></td></tr>';
	$('#'+phaseLineId).after(noteLine);
}
function removeNoteLine(phaseLineId){
	if($('#'+phaseLineId).next().hasClass('noteLine')){
		
		$('#'+phaseLineId).next().remove();
	}
}



// ENVOI FORMULAIRE

function addParamsPost(json, name, string){
	var result = json+"&"+name+"="+string;
	return result
}

var chiffrageGlobal = [];
var chiffrageRea = [];

function updateModules(){
	
	$('.moduleLine').each(function(e){
		if($(this).find('.idParameterInput').val()!=''){
			var newModule = {id:null, name:null, revisedCharge:null, parameterId:null};
			newModule.id=parseInt($(this).find('.moduleIdInput').val());
			newModule.name = ($(this).find('.moduleNameInput input').val());
			newModule.parameterId =parseInt($(this).find('.idParameterInput').val());
			newModule.revisedCharge = parseFloat($(this).find('.revisedChargesInput input').val());
			chiffrageRea.push(newModule);
		}
	});
}
function updatePhases(){
	//var i =0;
 $('.phaseLine').each(function(e){
	 
	 if($(this).find('.phaseNameInput').val()!=''){
		 var newPhase = {id:null, name:null, phaseType:null, value: null, justification:null};
		 
		 if($(this).find('.phaseIdInput').val()==''){
			 newPhase.id=null;
		 }
		 else{
			 newPhase.id=($(this).find('.phaseIdInput').val());
		 }
		
		 
		 newPhase.name = ($(this).find('.phaseNameInput').val());
		 newPhase.phaseType = ($(this).find('.selectPhaseType').val());
		 
		 switch($(this).find('.selectPhaseType').val()){
		 	case "RTU":
		 		 newPhase.value = ($(this).find('.chargesValue').val());
		 		 break;
		 	case "ONRTU":
		 		 newPhase.value = ($(this).find('.onRTUValue').val());
		 		 break;
		 	case "WITHOUTRATIO":
		 		 newPhase.value = ($(this).find('.chargesWithoutratioValue').val());
		 		 newPhase.justification=($(this).next().find('.justifValue').val());
		 		 break;
		 	case "UOS":
		 		 newPhase.value = ($(this).find('.chargesWithoutratioValue').val());
		 		newPhase.justification=($(this).next().find('.justifValue').val());
		 		 break;
		 	case "ONGLOBAL":
		 		 newPhase.value = ($(this).find('.onGlobalValue').val());
		 		 break;
	 		 default:
	 			newPhase.value = null;
	 			
		 }
	 
		 chiffrageGlobal.push(newPhase);
	 }
	 

	 
	 //i++;
	 
 })

};


function saveQuote(){

	updateModules();
	updatePhases();
	var formJson = $('form').serialize();
	var formModif = addParamsPost(formJson, "phases", JSON.stringify(chiffrageGlobal));
	formModif= addParamsPost(formModif, "modules", JSON.stringify(chiffrageRea));
	formModif = addParamsPost(formModif, "deletedPhases", JSON.stringify(deletedPhases));
	formModif = addParamsPost(formModif, "deletedModules", JSON.stringify(deletedModules));
	formModif =addParamsPost(formModif, "idDivView", parseInt($('#idDivView').val()));
	console.log(formModif);
	
	callAjax("POST", function(e){
		chiffrageGlobal = [];	//	on vide le tableau après l'avoir utilisé.
		chiffrageRea=[];
		if(e=="ok"){
			document.location.href=generateUrl("/devis/modif/"+$('#quoteId').val()+"/"+$('#idDivView').val());
		}
		
	}, "/devis/save/", formModif);
}

$('.saveBtns').on("click", function(e){
	e.preventDefault();
	saveQuote();
	
});


//Permet de sauvegarder un devis sur la page modif en faisant Ctrl + S ou Command + S sous Mac
document.addEventListener("keydown", function(e) {
	  if ((window.navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey)  && e.keyCode == 83) {
	    e.preventDefault();
	    
	    saveBtn.click();
	    
	  }
}, false);

////// TITRES DU DEVIS ////////

////Modifier le titre
$( "#BlocTitle2" ).hover(function () {
	$('.modifyTitle').removeClass("d-none");
	$('#quoteTitle').css('border', '1px solid rgba(0,0,0,.125)');
}, function () {
	$('.modifyTitle').addClass("d-none");
	$('#quoteTitle').css('border', '1px solid #FFF');
});

////Modifier la Description
$("#BlocTitle3").hover(function () {
	$('.modifyDescription').removeClass("d-none");
	$('#quoteDescription').css('border', '1px solid rgba(0,0,0,.125)');
}, function () {
	$('.modifyDescription').addClass("d-none");
	$('#quoteDescription').css('border', '1px solid #FFF');
});