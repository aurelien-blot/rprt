//var artifactId = "";
var artifactId = "/chiffragecgi";

function generateUrl(url){
	return artifactId+url;
}

function findDistinct(startTab, dataModel){
	var endTab=[];
	startTab.forEach(function(e){
		if (!endTab.includes(e[dataModel])){
			endTab.push(e[dataModel]);
		}
	})
	return endTab;
}

function numericKeyUpOnly(event, toDo ){
	if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )){
		event.preventDefault();
	}
	else{
		toDo();
	}
}

function preventIfNotNumeric(){
	
	$('.numeric').on("keyup", function(event){

		 $(this).val($(this).val().replace(/[^0-9\.\,]/gi,'').replace(/,/gi,'.'));
		 
		 if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
		 // Transforme le "." en "0."
		if($(this).val()=="."){
			$(this).val("0.");
		}
	})
	
}


//Désactive la touche entrée sur les formulaires
function preventEnterKey(element){
	element.keydown(function(e) {    
		if(e.keyCode == 13) { // KeyCode de la touche entrée
			e.preventDefault();
				// console.log('touche entrée');
		}
	});
}

function arrondiAuDemi(total){
	
	var chargeTotaleArrondie = 0.0;
	
	var cutTot = total * 100.0;
	var reste = cutTot % 100.0;

	
	if(reste <= 50 && reste > 0) {
		
		chargeTotaleArrondie = Math.trunc(total) + 0.5;
		 
	}else if(reste > 50) {
		
		chargeTotaleArrondie = Math.trunc(total) + 1;
	}else {
	
		chargeTotaleArrondie = Math.trunc(total);
	}
	
	//retourne la charge totale arrondie au demi près
	return(chargeTotaleArrondie);
}

function blankIfNull(element){
	if(element == null ){
		return '';
	}
	return element;
}

function blankIfNullOrZero(element){
	if(element == null || element == 0 || element =='0'){
		return '';
	}
	return element;
}


function sortByKeyDesc(array, key) {
    return array.sort(function (a, b) {
        var x = a[key]; var y = b[key];
        return ((x > y) ? -1 : ((x < y) ? 1 : 0));
    });
}
function sortByKeyAsc(array, key) {
    return array.sort(function (a, b) {
        var x = a[key]; var y = b[key];
        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
}