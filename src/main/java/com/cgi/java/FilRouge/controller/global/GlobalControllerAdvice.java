package com.cgi.java.FilRouge.controller.global;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.cgi.java.FilRouge.helper.Menu;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.service.PeopleServiceImpl;
import com.cgi.java.FilRouge.service.UserServiceImpl;

import com.cgi.java.FilRouge.helper.Menu;



@ControllerAdvice
public class GlobalControllerAdvice {
	
	@Autowired
	UserServiceImpl userService;
	
	@ModelAttribute
	public void headerLoading(HttpServletRequest request, Model model, Authentication auth) {
		
		if( auth != null ) {
			User user = userService.findByUsername(auth.getName());
			if( user == null) {
				return;
			}
			// Sous menu de Mon compte
			Menu sstab1User = new Menu("Paramètres", "/mon-compte");
			Menu sstab2User = new Menu("Déconnexion", "/logout");
			
			List<Menu> ssmenuUser = new ArrayList<>();
			
			ssmenuUser.add(sstab1User);
			ssmenuUser.add(sstab2User);
			
			//Sous-menu Admin
			Menu sstab1Admin = new Menu("Utilisateurs", "/administration/users");
			Menu sstab2Admin = new Menu("Projets", "/administration/projects");
			Menu sstab3Admin = new Menu("Equipes", "/administration/teams");
			Menu sstab4Admin = new Menu("Abaques", "/administration/abacii");
			List<Menu> ssmenuAdmin = new ArrayList<>();
			ssmenuAdmin.add(sstab1Admin);
			ssmenuAdmin.add(sstab2Admin);
			ssmenuAdmin.add(sstab3Admin);
			ssmenuAdmin.add(sstab4Admin);
			
			// Menu principal
			Menu tab1 = new Menu("Home", "/home");
			Menu tab2 = new Menu("Recherche avancée", "/devis/recherche");
			Menu tab3 = new Menu("Création de devis", "/devis/creation");
			Menu tab4 = new Menu("Administration", "/administration", ssmenuAdmin);
			Menu tab5 = new Menu(user.getFirstname() + " " + user.getLastname() , "/mon-compte", ssmenuUser);
			
			List<Menu> menu = new ArrayList<>();
					
			menu.add(tab1);
			menu.add(tab2);
			menu.add(tab3);
			menu.add(tab4);
			menu.add(tab5);
			
			model.addAttribute("menu", menu);
			

			String url = request.getRequestURI();
			
			if(url.equals("/")) {
				tab1.setActive(true);
			}
			else
			{
				for(Menu tab:menu) {
					
					String pattern = "^"+tab.getUrl();
					
					Pattern r = Pattern.compile(pattern);
					
					Matcher m = r.matcher(url);
					if(m.find()) {
						tab.setActive(true);
						break;
					}
				}
			}
			
		}
	}
}