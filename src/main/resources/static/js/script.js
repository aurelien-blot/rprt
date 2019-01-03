// Affiche le titre dans le nav lors du scroll vers le bas et l'enlève lors du scroll vers le haut
$(document).scroll(function() {    
	var scroll = $(window).scrollTop();
	if (scroll >= 100) {
		$('.navbar-brand').show(500);
	} else {
		$('.navbar-brand').hide(500);
	}
});

//NAVIGATION



//Barre de recherche

function showSearchResults(data) {


	if (data[0].length != 0 || data[1].length != 0){
		$(".search-title").append('Résultats pour la recherche "' + $("#search").val() + '" :');
	}
	else {
		$(".search-title").append('Aucun résultat pour la recherche "' + $("#search").val() + '" :');
	}
	
	if (data[0].length != 0){
		$("#search-quotes-results").append('<h6 class="card-subtitle mb-2 subtitle search-results">Devis</h6>');
		data[0].forEach(function(quote){
			$("#search-quotes-results").append('<p class="card-text search-results">' + quote.id + ' - ' + quote.name + ' - ' +
					'<a href="#" class="card-link search-results">Lien</a></p>');
		})
	}
	
	
	if (data[1].length != 0){
		$("#search-quotes-results").append('<h6 class="card-subtitle mb-2 subtitle search-results">Projets</h6>');
		data[1].forEach(function(project){
			$("#search-projects-results").append('<p class="card-text search-results">' + project.id + ' - ' + project.name + ' - ' +
					'<a href="#" class="card-link search-results">Lien</a></p>');
		})
	}
	
	$("#results").css("min-height", '');
	
}



$('#search')
.keyup(
	function(){
		if ($("#search").val().length==0){
			// masqué si 0 char
			$("#search-block").fadeOut();
		}
		else if ($("#search").val().length>0 && $("#search").val().length<3){
			// affichage si +0 char
    		$("#search-block").fadeIn();
    		$(".search-title").empty();
    		$(".search-title").append("Veuillez saisir au moins 3 caractères");
    		$("#search-quotes-results").empty();
    		$("#search-projects-results").empty();
    		$("#results").css('min-height', '');
		}
		else if ($("#search").val().length>2){
			// affichage si +2 char
			$("#search-block").fadeIn();
			$(".search-title").empty();
			$(".search-title").append("Recherche en cours...");
			$(".search-results").css('color', '#dae0e5');
			$.doTimeout('search');
			$.doTimeout('search', 500, function(elem){
	    		$(".search-title").empty();
	    		$("#search-quotes-results").empty();
	    		$("#search-projects-results").empty();
	    		$(".search-results").css('');
	    		callAjax('GET', showSearchResults,'/devis/api/recherche/',$(".form-search").serialize());
			}, this);
			
			// récupération de la hauteur de #search-*-results pour garder la hauteur min si autre recherche
			var resultHeight = document.getElementById("results").offsetHeight;
			$("#results").css('min-height', resultHeight + 'px');
		
		}
    }
);


//effacement du champ si clic en dehors du #search-block
$(document.body).click(function(e) {
  if( !$(e.target).is($('#search-block, #search')) && !$.contains($('#search-block')[0],e.target) && $("#search").val().length>0) {
	  $('#search-block').fadeOut();
	  $("#search").val('');
  }
});