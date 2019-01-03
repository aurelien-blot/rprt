/**
 * 
 */
$("#content").addClass("d-none");

/*	Sélection de contrat, affiche les projets associés au contrat	*/
$("#step1 #selectContractStep1").on("change", function(e){
	$.ajax({
		url : "/devis/creation/api/metricTables/"+contractId
	});
});



$("#step1 form").on("submit", function(e){
	e.preventDefault();
	console.log("submitted");
	var quoteName = $("#step1 form #devisName").val();
	var projectId = $("#step1 form #selectProject").val();
	
	$("#content").removeClass("d-none");
	$("#step1").addClass("d-none");
	
	
});

