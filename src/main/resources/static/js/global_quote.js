
var phaseLineIncr =0;
var moduleLineIncr =0;
var moduleLineNber=0;
var abacusData=[];


$('#part1').on("click", showPart1);
$('#part2').on("click", showPart2);

$(document).ready(function(){
	showCorrectDiv();
	showPhaseLinesChargesValue();
	fullShowAndCalculate();
	giveModuleLineId();
	givePhaseLineId();

	
	if ( $('.moduleLine').length > 0 ) {
		step3_On();
	}
	
});



function showCorrectDiv(){
	switch($('#idDivView').val()){
		case '1' : 
			showPart1();
			break;
		case '2' :
			showPart2();
			break;
		case '3' :
			showPart3();
			break;
		default:
			showPart2();		
	}
}

function stepSelected(){
	$(" .caption").css('font-weight', 'normal');
	$("#step" + $('#idDivView').val() + " .caption").css('font-weight', 'bold');

}

function showPart1(){
	$('#metric').removeClass("d-none");
	$('#productionWorkLoad').addClass("d-none");
	$('#global').addClass("d-none");
	$('#idDivView').val('1');
	stepSelected();	
}

function showPart2(){
	$('#metric').addClass("d-none");
	$('#productionWorkLoad').removeClass("d-none");
	$('#global').addClass("d-none");
	$('#idDivView').val('2');
	stepSelected();
	
	if ( $('.moduleLine').length > 0 ) {
		moduleBtn_On();
	}
	
}

function showPart3(){
	$('#metric').addClass("d-none");
	$('#productionWorkLoad').addClass("d-none");
	$('#global').removeClass("d-none");
	$('#idDivView').val('3');
	stepSelected();	
	
	if ( $('.phaseLine').length > 1 ) {
		GWLValidationButton_On();
		sendBtn_On();
	}
	
}

//Style du bouton Valider Chiffrage réalisation
function moduleBtn_On() {
	$('#PWLValidationButton').removeClass('btn-outline-secondary');
	$('#PWLValidationButton').addClass('btn-outline-success');
	$('#PWLValidationButton').removeAttr('disabled');
//	$('#PWLValidationButton').addClass('pointer');
}

//Style du bouton Valider Chiffrage global
function GWLValidationButton_On() {
	$('#GWLValidationButton').removeClass('btn-outline-secondary');
	$('#GWLValidationButton').addClass('btn-outline-success');
	$('#GWLValidationButton').removeAttr('disabled');
//	$('#GWLValidationButton').addClass('pointer');
}
//Style du bouton Envoyer en validation
function sendBtn_On() {
	$('#sendBtn').removeClass('btn-outline-secondary');
	$('#sendBtn').addClass('btn-outline-success');
	$('#sendBtn').removeAttr('disabled');
}


function listElements(classElt){ 
	var list =[];
	$(classElt).each(function(e){
		list.push($(this));
	})
	return list;
}



function giveModuleLineId(){
	$('.moduleLine').each(function(e){
		$(this).attr("id",incrementModuleLineId() )
	});
}

function showPhaseLinesChargesValue(){
	$('.phaseLine').each(function(e){
		 $('#'+$(this).attr('id')+' .chargesValue').text(calculChargesOnRtu($(this).attr('id')));
	})
}


function incrementModuleLineId(){
	moduleLineIncr ++;
	var moduleLine = "module"+moduleLineIncr;
	return moduleLine;
}

function incrementPhaseLineId(){
	phaseLineIncr ++;
	var GWLLine = "phase"+phaseLineIncr;
	return GWLLine;
}

function givePhaseLineId(){
	$('.phaseLine').each(function(e){
		$(this).attr("id",incrementPhaseLineId() )
	});
}



//CALCULS ET AFFICHAGE RESULTATS

function fullShowAndCalculate(){
	var totalModules = calculTotalModules();
	var totalRTUOnRTU = calculTotalRTUOnRTU(totalModules);
	var totalWithoutRatio =calculTotalWithoutRatio();
	var totalOnGlobal = calculTotalOnGlobal(totalRTUOnRTU,totalWithoutRatio);
	var total = calculTotalGWL(totalRTUOnRTU, totalOnGlobal, totalWithoutRatio);
	showTotalModules(totalModules);
	showTotalRTUOnRTU(totalRTUOnRTU);
	showTotalWithoutRatio(totalWithoutRatio);
	showTotalOnGlobal(totalOnGlobal);
	showTotalGWL(total);
}

function calculTotalModules(){
	
	var total = parseFloat(0);
	
	for(var i =0; i<$('.unitChargesInput input').length;i++){
		
		var regularValue = $('.unitChargesInput input').eq(i).val();
		var revisedValue = $('.revisedChargesInput input').eq(i).val();
		
		
		if(regularValue == ""){
			regularValue = 0;
		}

		if(revisedValue == ""){
			revisedValue = 0;
		}
		var regularValueFloat = parseFloat(regularValue);
		var revisedValueFloat =parseFloat(revisedValue);
		
		if(revisedValueFloat == 0){
			total +=regularValueFloat;
		}
		else{
			total += revisedValueFloat;
		}
	}
	return total.toFixed(2); //  le toFixed(2) est obligatoire sinon ça bug : essayer 0.7 + 0.35 et ça donne un chiffre incompréhensible

}

function showTotalModules(total){
	$('#totalPWL').text(total);
	$('#rtuValue').text(total);
}

function calculTotalRTUOnRTU(totalRtu){
	
	var total = parseFloat(totalRtu);
	//test
	
	
	//endTest
	var lines = $('#GWLData .onRTUValue:not(#rtuRatio)');

	$(lines).each(function(e){
		value = $(this).val();
		if(value == ""){
			value = 0;
		}
		value = parseFloat(value);
		total += parseFloat(value/100*totalRtu);	
	})
	return total.toFixed(2);
}

function showTotalRTUOnRTU(totalRtuOnRtu){
	$('#totalGWLRtuOnRtu').text(totalRtuOnRtu);
}

function calculTotalOnGlobal(totalRtuOnRTU, totalWithoutRatio ){
	
	var percent = parseFloat(0);
	var subtotal =  parseFloat(totalRtuOnRTU) + parseFloat(totalWithoutRatio);
	var total= parseFloat(0);

	var lines = $('#GWLData .onGlobalValue');

	$(lines).each(function(e){
		value = $(this).val();
		if(value == ""){
			value = 0;
		}
		value = parseFloat(value);
		percent += value;
	})
	if(percent != 0){
		total = parseFloat((subtotal*percent)/100);
		return  total.toFixed(2);
	}
	return 0;
	
}

function showTotalOnGlobal(totalOnGlobal){
	$('#chargeOnGlobal').text(totalOnGlobal);

}




function calculTotalGWL(totalRTUOnRTU, totalOnGlobal, totalWithoutRatio){
	var total = parseFloat(0);
	total = (parseFloat(totalRTUOnRTU)+ parseFloat(totalOnGlobal)+ parseFloat(totalWithoutRatio));
	
	return arrondiAuDemi(total);
}

function showTotalGWL(total){	
	$('#totalGWL').text(total);
}

function calculTotalWithoutRatio(){
	
var total = parseFloat(0);
	
	var lines = $('#GWLData .chargesWithoutratioValue');

	$(lines).each(function(e){
		value = $(this).val();
		if(value == ""){
			value = 0;
		}
		value = parseFloat(value);
		total += value;
	})
	
	return total.toFixed(2);
}
function showTotalWithoutRatio(total){
	$('#totalWithoutRatio').text(total);
}

function calculChargesOnRtu(phaseLineId){
	var onRTUValue = $('#'+phaseLineId+' .onRTUValue');
	var charge = (parseFloat(calculTotalModules())*(parseFloat(onRTUValue.val())/100)).toFixed(2);
	if(onRTUValue.val() == ''){
		charge = parseFloat(0);
	}
	return charge;
}



function step3_On() {
	$('#part3').on("click", showPart3);
	$('#step3').addClass('active');
	$('#part3').removeClass('not-allowed');
	$('#part3').addClass('pointer');
}

function step3_Ok() {
	$('#step3').removeClass('active');
	$('#step3').addClass('complete');
}

function step3_Off() {
	$('#part3').off("click", showPart3);
	$('#step3').removeClass('active');
	$('#part3').removeClass('pointer');
	$('#part3').addClass('not-allowed');
}