var metricTableData;

$(document).ready(function(){
	showAbaciiFromContract();
	validateMetricTableOnClick();
	
});


//initTest();
//function initTest(){
//	$('#metricTableValidationButton').addClass("btn-success");
//	$('#metricTableValidationButton').removeAttr("disabled")
//	metricTableData=[];
//	$('#metricTableIdInput').val(27);
//	callAjax('GET', showMetricTableQuickView,'/devis/creation/api/parameters/27');
//}


emptyMetricTableData();

function emptyMetricTableData(){
	$("#metricTableData").empty();
}

selectContract();

function selectContract(){
	$('#selectContract').on("change", function(e){
		
		showAbaciiFromContract()
	});
}

function showAbaciiFromContract(){
	emptyMetricTableData();
	var selectedContract = $('#selectContract option:selected').val();
	
	if(selectedContract != ""){
		
		callAjax('GET', showMetricTableList,'/devis/creation/api/abaci/contract/'+selectedContract);	
	}
}


function showMetricTableList(data){
	$("#metricsTableList").empty();
	data.forEach(function(e){
		if(!e.archived){
			$("#metricsTableList").append('<a href="#" class="list-group-item list-group-item-action" data-name="'+e.name+'"data-id="'+e.id+'"">'+e.name+'</a>');
		}
	});
	if($(data).length ==1){
		var abacusFind=$(data).get(0);
		abacusSelected(abacusFind.id,abacusFind.name);
	}

	listenMetricTableClick();
}
	
function showMetricTableQuickView(data){
	data.forEach(function(i){
		var trElt =$("<tr></tr>");
		trElt.append("<td>"+blankIfNull(i.metric.type)+"</td>");	
		trElt.append("<td>"+blankIfNull(i.metric.typology)+"</td>");	
		trElt.append("<td>"+blankIfNull(i.metric.task)+"</td>");	
		trElt.append("<td>"+blankIfNull(i.metric.interventionLevel)+"</td>");	
		trElt.append("<td>"+blankIfNull(i.metric.complexity)+"</td>");	
		trElt.append("<td>"+blankIfNull(i.unitCharge)+"</td>");	
		$('#metricTableData').append(trElt);
		metricTableData.push(i);
	})				
}

//Activation du vouton de choix de l'abaque
function metricTableValidationButton_On() {
	$('#metricTableValidationButton').removeClass('btn-outline-secondary');
	$('#metricTableValidationButton').addClass('btn-outline-success');
	$('#metricTableValidationButton').attr("disabled", false);
}

// Vérifie si un abaque est sélectionné
function selectedAbacus(){
	if ( $('#metricTableData').length > 0 ) {
		metricTableValidationButton_On();
	}
}

function listenMetricTableClick() {
	
	$('#metricsTableList a').on("click", function(e){
		
		abacusSelected($(e.target).data("id"), $(e.target).data("name"));
	});
}

function abacusSelected(id, name){
	metricTableData=[];
	$('#metricTableValidationButton').addClass("btn-success");
	$('#metricTableValidationButton').removeAttr("disabled");
	$('#metricTablStatus').text(name);
	$('#metricTableData').empty();
	var metricTableId = id;
	$('#metricTableIdInput').val(metricTableId);

	callAjax('GET', showMetricTableQuickView,'/devis/creation/api/parameters/'+metricTableId);
	
	selectedAbacus();
};



function validateMetricTableOnClick(){

	$('#metricTableValidationButton').on("click", function(){
		
		/*
		$('#unitWorkData').empty();
		unitWorkLineNber = 0;
		addEmptyUnitWorkLine();
		showPart2();
		*/
	});
}