var parametersArray = [];

$(document).ready(function(){
	
	archiveBtnClickEvent();
	modifBtnClickEvent();
	addMetricElementBtnEvent();
	addMetricsBtnEvent();
	saveAbacusBtnEvent();
	resetBtnEvent();
	sortBtnEvent();
	selectAbacusOriginEvent();
	validateAbacusBtnEvent();
	loadParametersArray($('#idValue').val());
	
	//test();
});



function test(){
	$('#abacusName').val('test6');
	$($('#abacusContract option').get(2)).attr("selected", true);
	$($('.metricElement [data-elt="type"] input').get(0)).attr('checked', true);
	//$($('.metricElement [data-elt="type"] input').get(1)).attr('checked', true);
	//$($('.metricElement [data-elt="type"] input').get(2)).attr('checked', true);
	$($('.metricElement [data-elt="typology"] input').get(0)).attr('checked', true);
	$($('.metricElement [data-elt="typology"] input').get(1)).attr('checked', true);
	$($('.metricElement [data-elt="typology"] input').get(2)).attr('checked', true);
	$($('.metricElement [data-elt="task"] input').get(0)).attr('checked', true);
	//$($('.metricElement [data-elt="task"] input').get(1)).attr('checked', true);
}

function selectAbacusOriginEvent(){
	$('#abacusOrigin').on("change", function(e){
		cleanAbacusTable();
		
		var selectedAbacus = $('#abacusOrigin option:selected').val();
		
		if(selectedAbacus != "new"){
			
			loadParametersArray(selectedAbacus);
		}
		
	});
}


function loadParametersArray(abacusId){

	if(abacusId!=null && abacusId!="" && abacusId!=0 ){
		callAjax('GET', showAbacusFromModel,'/administration/abacii/loadtemplate/'+abacusId);	
	}
	
}


function showAbacusFromModel(data){
	parametersArray=[];
	$(data).each(function(e){
		
		addParameterInArray({type: data[e].type, typology:data[e].typology, task:data[e].task, interventionLevel:data[e].interventionLevel, complexity: data[e].complexity, unitCharge:data[e].unitCharge});
	});

	sortParametersArray();
	showParametersArray();
}

function archiveBtnClickEvent(){
	$('.archiveBtn').each(function(e){
		$(this).on("click", function(i){
			i.preventDefault();
			alertArchiveAbacus($(this).attr("data-id"));
		})
	})
}

function modifBtnClickEvent(){
	$('.modifBtn').each(function(e){
		$(this).on("click", function(i){
			i.preventDefault();
			document.location.href="/administration/abacii/create/"+$(this).attr("data-id");
		})
	})
}

function alertArchiveAbacus(id){
	var dialog = $('<p>Êtes-vous sûr de vouloir archiver cet abaque ?</p>').dialog({
		closeText: "hide",
        buttons: {
            "Archiver": function() {
            	document.location.href="/administration/abacii/archive/"+id;
            },
            "Annuler":  function() {
                dialog.dialog('close');
            }
        }
    });
}

function addMetricElementBtnEvent(){
	$('.metricElement .addMetricEltBtn').on("click", function(){
		
		var value = $(this).parent().find('.newMetric').val();
		var checkboxes = $(this).parent().find('.checkboxes');
		if(value!=''){	
			checkboxes.append('<div class="checkbox"><input type="checkbox" name="'+checkboxes.data('elt')+'" value="'+value+'" /><label for="'+checkboxes.data('elt')+'">'+value+'</label></div>');
			$(this).parent().find('.newMetric').val('');
		}
	})
}

function loadDeleteBtnEvent(){

	$('.deleteMetricBtn').each(function(){
		$(this).on("click", function(e){
			parametersArray.splice($(this).parent().attr("data-id"), 1)
			showParametersArray();
		});
	})
	
		
}

function unitChargeCompleteEvent(){
	$('.unitChargeValue').each(function(){
		$(this).on("keyup", function(e){
			var index = $(this).parent().parent().attr("data-id");
			parametersArray[index].unitCharge = $(this).val();
		});
	})
}


function sortBtnEvent(){
	$('#sortBtn').on("click", function(e){
		sortParametersArray();
		showParametersArray();

	});
}

function sortParametersArray(){
	sortArray3Args("type", "typology", "task");
	
}

function sortArray3Args(key, key2, key3) {
    return parametersArray.sort(function (a, b) {
        var x = a[key]; var y = b[key];
        if(x < y){
        	return -1;
        }
        if(x > y){
        	return 1
        }
        var z = a[key2]; var w = b[key2];
        if(z <w ){
        	return -1;
        }
        if(z > w){
        	return 1
        }
        var e = a[key3]; var u = b[key3];
        if(e < u ){
        	return -1;
        }
        if(e > u){
        	return 1
        }
        // TRIER PAR interventionLevel et complexity
        return 0;
    });
}

function resetBtnEvent(){
	$('#resetBtn').on("click", function(e){
		cleanAbacusTable();
	});
}

function cleanAbacusTable(){
	parametersArray = [];
	showParametersArray();
}


function addMetricsBtnEvent(){
	$('#addMetricBtn').on("click", function(e){
		if($('.metricElement :checked').length!= 0){
			var parametersArrayLength = $(parametersArray).length;
			fillParametersArrayWithNull();
			fillParametersArray(parametersArrayLength);
			showParametersArray();
			
		}
		$(':checked').prop('checked', false);
	});
}


function fillParametersArray(parametersArrayLength){
	
	
	var typeElement = 'type';
	var typologyElement =  'typology';
	var taskElement = 'task';
	var interventionLevelElement =  'interventionLevel';
	var complexityElement =  'complexity';
	
	var typeCount= checkedBoxesCount(checkedsElt(typeElement));
	var typologyCount= checkedBoxesCount(checkedsElt(typologyElement));
	var taskCount = checkedBoxesCount(checkedsElt(taskElement));
	var interventionLevelCount = checkedBoxesCount(checkedsElt(interventionLevelElement));
	var complexityCount = checkedBoxesCount(checkedsElt(complexityElement));
	
	fillColumnWithElement(typeElement,parametersArrayLength, calculateParametersCount()/typeCount);
	fillColumnWithElement(typologyElement,parametersArrayLength,calculateParametersCount()/typeCount/typologyCount);
	fillColumnWithElement(taskElement, parametersArrayLength, calculateParametersCount()/typeCount/typologyCount/taskCount);
	fillColumnWithElement(interventionLevelElement, parametersArrayLength, calculateParametersCount()/typeCount/typologyCount/taskCount/interventionLevelCount);
	fillColumnWithElement(complexityElement, parametersArrayLength, calculateParametersCount()/typeCount/typologyCount/taskCount/interventionLevelCount/complexityCount);
	
	
	
}

function checkedsElt(element){
	
	return $('.metricElement [data-elt="'+element+'"] :checked')
}

function fillColumnWithElement(element, parametersArrayLength, sequenceLength){
	
	var totalCount = calculateParametersCount();
	var nbSequences = totalCount/sequenceLength;
	var checkedCount =  checkedsElt(element).length;

	var u= parametersArrayLength;
	
	var repere =0;
	for(var e =0; e<nbSequences; e++){

		for(var i =0; i<sequenceLength; i++){

			$($(parametersArray).get(u)).attr(element, $($(checkedsElt(element)).get(repere)).val());
			u++;
		}
		repere++;
		if (repere>=checkedCount){
			repere=0;
		}

	}
}

function fillParametersArrayWithNull(){
	for(var i =0; i<calculateParametersCount(); i++){
		addParameterInArray({type: null, typology:null, task:null, interventionLevel:null, complexity: null, unitCharge:null});
	}	
}
function calculateParametersCount(){
	var typeElement = 'type';
	var typologyElement =  'typology';
	var taskElement = 'task';
	var interventionLevelElement =  'interventionLevel';
	var complexityElement =  'complexity';
	
	var typeCount= checkedBoxesCount(checkedsElt(typeElement));
	var typologyCount= checkedBoxesCount(checkedsElt(typologyElement));
	var taskCount = checkedBoxesCount(checkedsElt(taskElement));
	var interventionLevelCount = checkedBoxesCount(checkedsElt(interventionLevelElement));
	var complexityCount = checkedBoxesCount(checkedsElt(complexityElement));
	
	var count = typeCount*typologyCount*taskCount*interventionLevelCount*complexityCount;

	return count;
}

function checkedBoxesCount(checkedBoxes){
	if(checkedBoxes.length==0){
		return 1;
	}
	else{
		return checkedBoxes.length;
	}
	
}

function addParameterInArray(parameter){
	parametersArray.push(parameter);
}

function emptyParametersArray(){
	parametersArray=[];
}

function showParametersArray(){
	$('#abacusData').empty();
	$(parametersArray).each(function(e){
		generateParameterLine(e, this.type, this.typology, this.task, this.interventionLevel,  this.complexity, this.unitCharge);
	})
	loadDeleteBtnEvent();
	preventIfNotNumeric();
	unitChargeCompleteEvent();
}


function generateParameterLine(number, type, typology, task, interventionLevel, complexity, unitCharge){
	var line =$('<tr class="parameterLine" data-id="'+number+'"></tr>');
	//line.append('<td class="d-none"><input value="'+number+'"/></td>');
	line.append('<td>'+blankIfNull(type)+'</td>');
	line.append('<td>'+blankIfNull(typology)+'</td>');
	line.append('<td>'+blankIfNull(task)+'</td>');
	line.append('<td>'+blankIfNull(interventionLevel)+'</td>');
	line.append('<td>'+blankIfNull(complexity)+'</td>');
	line.append('<td><input class="unitChargeValue numeric" size=3 value="'+blankIfNullOrZero(unitCharge)+'"/></td>');
	line.append('<td  class="deleteMetricBtn"><button type="button" title="Supprimer la ligne" class="btn btn-md btn-danger btn-lg btn-rond"><i class="far fa-times-circle"></i></button></td>')
	$('#abacusData').append(line);
}

function hideFieldIfAlreadyBefore(field){
	
}


function saveAbacusBtnEvent(){
	$('#saveAbacusBtn').on("click", function(e){
		e.preventDefault();
		if(validateNameAndContractForm()){
			saveAbacus(redirectAbacusCreationPage,"/administration/abacii/save");
		}
		else{
			errorValidationMessage("Vous ne pouvez pas enregistrer l'abaque si son nom et son contrat sont vides !");
		}

	} )
}

function validateAbacusBtnEvent(){
	$('#validateAbacusBtn').on("click", function(e){

		e.preventDefault();
		if(validateNameAndContractForm() && validateUnitChargeForm()){
			alertFinalizeAbacus();
		}
		else{
			errorValidationMessage("Vous ne pouvez pas finaliser l'abaque si le nom de l'abaque, son contrat ou l'une des charges d'un paramètre est vide !");
			
		}
	});
}

function saveAbacus(callback, url){
	var formJson = $('form').serialize();
	var jsonArray = "parameters="+JSON.stringify(parametersArray);
	var result = formJson+"&"+jsonArray;
	callAjax("POST", function(e){
		console.log(e);
		var jsonResult = JSON.parse(e);
		if(jsonResult.message=="ok"){
			callback(jsonResult.id);
		}
		
	}, url, result);
}

function redirectAdminAbaciiPage(){
	document.location.href="/administration/abacii";
}

function redirectAbacusCreationPage(id){
	document.location.href="/administration/abacii/create/"+id;
}
function finalizeAbacus(){
		saveAbacus(redirectAdminAbaciiPage,"/administration/abacii/finalize")
}

function alertFinalizeAbacus(){
	var dialog = $('<p>Cette action est définitive, êtes vous sûr(e) de finaliser cet abaque ?</p>').dialog({
		closeText: "hide",
        buttons: {
            "Finaliser": function() {
            	finalizeAbacus();
            },
            "Annuler":  function() {
                dialog.dialog('close');
            }
        }
    });
}

function validateNameAndContractForm(){
	if($('#abacusName').val()=='' || $('#abacusContract').val()==''){
		return false
	}
	return true;
}

function validateUnitChargeForm(){
	var result = true;
	$('.unitChargeValue').each(function(e){
		if($(this).val()=="" ){
			result= false;

		}
				
	});
	return result;
}

function errorValidationMessage(text){
	var dialog = $('<p>'+text+'</p>').dialog({
        buttons: {
            "OK":  function() {
                dialog.dialog('close');
            }
        }
    });
}