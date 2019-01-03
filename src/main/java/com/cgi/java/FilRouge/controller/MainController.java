package com.cgi.java.FilRouge.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi.java.FilRouge.model.Abacus;
import com.cgi.java.FilRouge.model.Agency;
import com.cgi.java.FilRouge.model.Contract;
import com.cgi.java.FilRouge.model.Parameter;
import com.cgi.java.FilRouge.model.Module;
import com.cgi.java.FilRouge.model.Metric;
import com.cgi.java.FilRouge.model.PhaseRtu;

import com.cgi.java.FilRouge.model.Privilege;
import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.model.QuoteTemplate;
import com.cgi.java.FilRouge.model.Role;
import com.cgi.java.FilRouge.model.Team;
import com.cgi.java.FilRouge.model.TeamWorker;
import com.cgi.java.FilRouge.model.Territory;
import com.cgi.java.FilRouge.model.User;
import com.cgi.java.FilRouge.repository.AbacusRepo;
import com.cgi.java.FilRouge.repository.AgencyRepo;
import com.cgi.java.FilRouge.repository.ContractRepo;
import com.cgi.java.FilRouge.repository.ParameterRepo;
import com.cgi.java.FilRouge.repository.ModuleRepo;
import com.cgi.java.FilRouge.repository.MetricRepo;
import com.cgi.java.FilRouge.repository.PhaseRtuRepo;
import com.cgi.java.FilRouge.repository.PrivilegeRepo;
import com.cgi.java.FilRouge.repository.ProjectRepo;
import com.cgi.java.FilRouge.repository.QuoteTemplateRepo;
import com.cgi.java.FilRouge.repository.RoleRepo;
import com.cgi.java.FilRouge.repository.TeamRepo;
import com.cgi.java.FilRouge.repository.TeamWorkerRepo;
import com.cgi.java.FilRouge.repository.TerritoryRepo;
import com.cgi.java.FilRouge.repository.UserRepo;
import com.cgi.java.FilRouge.service.ProjectSequenceServiceImpl;
import com.cgi.java.FilRouge.service.ProjectServiceImpl;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;


@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }
    
    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
     
    @Autowired AgencyRepo agR;
	@Autowired TerritoryRepo teR;
	@Autowired ContractRepo coR;
	@Autowired ProjectRepo prR;
	@Autowired TeamRepo tmR;
	@Autowired TeamWorkerRepo twR;
	
	@Autowired PrivilegeRepo pvR;
	@Autowired RoleRepo roR;
	@Autowired UserRepo usR;
	@Autowired AbacusRepo abR;
	@Autowired MetricRepo pmR;
	@Autowired ParameterRepo mtR;

	@Autowired QuoteTemplateRepo qtR;
	@Autowired PhaseRtuRepo phR;
	@Autowired ModuleRepo moR;
	
	@Autowired QuoteServiceImpl qsi;
	@Autowired ProjectSequenceServiceImpl pssi;
	@Autowired ProjectServiceImpl psi;
	
	
	@GetMapping("/test")
	public String init() {
		Agency ag1 = new Agency("Agence 1");
		Agency ag2 = new Agency("Agence 2");
		agR.save(ag1);
		agR.save(ag2);
		
		Territory te1 = new Territory("Territoire client 1", ag1);
		Territory te2 = new Territory("Territoire client 2", ag2);
		teR.save(te1);
		teR.save(te2);
		
		Contract co1 = new Contract("Contrat 1", "Jean-Paul", te1);
		Contract co2 = new Contract("Contrat 2", "Didier", te2);
		Contract co3 = new Contract("Contrat 3", "Norbert", te2);
		coR.save(co1);
		coR.save(co2);
		coR.save(co3);
		
		Project pr1 = new Project("PROJ1", "Projet 1");
		Project pr2 = new Project("PROJ2", "Projet 2");
		Project pr3 = new Project("PROJ3", "Projet 3");
		Project pr4 = new Project("PROJ4", "Projet 4");
		Project pr5 = new Project("PROJ5", "Projet 5");
		pr1 = prR.save(pr1);
		pr2 = prR.save(pr2);
		pr3 = prR.save(pr3);
		pr4 = prR.save(pr4);
		pr5 = prR.save(pr5);
		
		Team tm1 = new Team("Equipe 1", co1);
		Team tm2 = new Team("Equipe 2", co1);
		Team tm3 = new Team("Equipe 3", co1);
		Team tm4 = new Team("Equipe 4", co2);
		Team tm5 = new Team("Equipe 5", co2);
		Team tm6 = new Team("Equipe 6", co2);
		Team tm7 = new Team("Equipe 7", co3);
		Team tm8 = new Team("Equipe 8", co3);
		Team tm9 = new Team("Equipe 9", co3);
		tm1.getProjects().add(pr1);
		tm2.getProjects().add(pr2);
		tm3.getProjects().add(pr2);
		tm4.getProjects().add(pr3);
		tm5.getProjects().add(pr3);
		tm6.getProjects().add(pr4);
		tm7.getProjects().add(pr5);
		tm8.getProjects().add(pr5);
		tm9.getProjects().add(pr5);
		
		tmR.save(tm1);
		tmR.save(tm2);
		tmR.save(tm3);
		tmR.save(tm4);
		tmR.save(tm5);
		tmR.save(tm6);
		tmR.save(tm7);
		tmR.save(tm8);
		tmR.save(tm9);

		ArrayList<Team> teams11 = new ArrayList<>(); teams11.add(tm1);
		TeamWorker tw1 = new TeamWorker("Jean", "Vallée", new Date(), "TW0001", teams11, "chef"); 
		ArrayList<Team> teams22 = new ArrayList<>(); teams22.add(tm2); teams22.add(tm3);
		TeamWorker tw2 = new TeamWorker("Philippe", "Leclerc", new Date(), "TW0002", teams22, "responsable"); 
		ArrayList<Team> teams33 = new ArrayList<>(); teams33.add(tm1); teams33.add(tm2); teams33.add(tm3);
		TeamWorker tw3 = new TeamWorker("Michel", "Legrand", new Date(), "TW0003", teams33, "ouvrier"); 
		twR.save(tw1);
		twR.save(tw2);
		twR.save(tw3);
		
		
		
		Privilege pv1 = new Privilege("ROLE_DEVIS_VIEW", "Visualisation de devis");
		Privilege pv2 = new Privilege("ROLE_DEVIS_CREATE", "Creation de devis");
		Privilege pv3 = new Privilege("ROLE_DEVIS_EDIT", "Edition de devis");
		Privilege pv4 = new Privilege("ROLE_DEVIS_VALIDATE", "Validation de devis");
		Privilege pv5 = new Privilege("ROLE_ADMIN_VIEW", "Visibilité de la section admin");
		pvR.save(pv1);
		pvR.save(pv2);
		pvR.save(pv3);
		pvR.save(pv4);
		pvR.save(pv5);
		
		ArrayList<Privilege> roles1 = new ArrayList<>(); roles1.add(pv1);
		Role ro1 = new Role("Role simple", roles1);
		ArrayList<Privilege> roles2 = new ArrayList<>(); roles2.add(pv1); roles2.add(pv2); roles2.add(pv3);
		Role ro2 = new Role("Role Creation", roles2);
		ArrayList<Privilege> roles3 = new ArrayList<>(); roles3.add(pv1); roles3.add(pv4); roles3.add(pv5);
		Role ro3 = new Role("Role Validation", roles3);
		roR.save(ro1);
		roR.save(ro2);
		roR.save(ro3);
		
		ArrayList<Team> teams1 = new ArrayList<>(); teams1.add(tm1);
		User u1 = new User("Jean", "Visionne", new Date(), "USR001", "view", "password", ro1, teams1);
		ArrayList<Team> teams2 = new ArrayList<>(); teams2.add(tm2); teams2.add(tm3);
		User u2 = new User("Jean", "Créé", new Date(), "USR002", "create", "password", ro2, teams2);
		ArrayList<Team> teams3 = new ArrayList<>(); teams3.add(tm1); teams3.add(tm3);
		User u3 = new User("Jean", "Valide", new Date(), "USR003", "validate", "password", ro3, teams3);
		
		u1.setPassword(bCryptPasswordEncoder.encode(u1.getPassword()));
		u2.setPassword(bCryptPasswordEncoder.encode(u2.getPassword()));
		u3.setPassword(bCryptPasswordEncoder.encode(u3.getPassword()));
		
		usR.save(u1);
		usR.save(u2);
		usR.save(u3);
		
		
		Abacus ab1 = new Abacus("Abaque 1", new Date(), false, co1);
		Abacus ab2 = new Abacus("Abaque 2", new Date(), false, co1);
		Abacus ab3 = new Abacus("Abaque 3", new Date(), false, co2);
		Abacus ab4 = new Abacus("Abaque 4", new Date(), false, co2);
		Abacus ab5 = new Abacus("Abaque 5", new Date(), false, co3);
		Abacus ab6 = new Abacus("Abaque 6", new Date(), false, co3);
		ab1.setFinished(true);
		ab2.setFinished(true);
		ab3.setFinished(true);
		ab4.setFinished(true);
		ab5.setFinished(true);
		ab6.setFinished(true);
		
		abR.save(ab1);
		abR.save(ab2);
		abR.save(ab3);
		abR.save(ab4);
		abR.save(ab5);
		abR.save(ab6);

		
		Metric me01 = new Metric("prog", "tp-saisie", "modification", "TC", "S");
		Metric me02 = new Metric("fmt", "tp-typo2", "creation", "M", "M");
		Metric me03 = new Metric("copy", "tp-test", "modification", "C", "S");
		Metric me11 = new Metric("prog", "tp-saisie2", "modification", "TC", "S");
		Metric me12 = new Metric("fmt", "tp-typo3", "creation", "M", "M");
		Metric me13 = new Metric("copy", "tp-test4", "modification", "C", "S");
		Metric me21 = new Metric("prog", "tp-saisie3", "modification", "TC", "S");
		Metric me22 = new Metric("fmt", "tp-typo4", "creation", "M", "M");
		Metric me23 = new Metric("copy", "tp-test8", "modification", "C", "S");
		pmR.save(me01); pmR.save(me02); pmR.save(me03);
		pmR.save(me11); pmR.save(me12); pmR.save(me13);
		pmR.save(me21); pmR.save(me22); pmR.save(me23);
		
		
		Parameter met01 = new Parameter(ab1, me01, 8.5d);
		Parameter met02 = new Parameter(ab1, me02, 5.6d);
		Parameter met03 = new Parameter(ab1, me03, 12.4d);
		Parameter met11 = new Parameter(ab2, me11, 6.4d);
		Parameter met12 = new Parameter(ab2, me12, 2.3d);
		Parameter met13 = new Parameter(ab2, me13, 19.8d);
		Parameter met21 = new Parameter(ab3, me21, 8.8d);
		Parameter met22 = new Parameter(ab3, me22, 4.4d);
		Parameter met23 = new Parameter(ab3, me23, 9.7d);
		mtR.save(met01); mtR.save(met02); mtR.save(met03);
		mtR.save(met11); mtR.save(met12); mtR.save(met13);
		mtR.save(met21); mtR.save(met22); mtR.save(met23);
		
		
		Metric Dpm01 = new Metric("prog", "tp-saisie", "modification", "TC", "S");
		Metric Dpm02 = new Metric("fmt", "tp-typo2", "creation", "M", "M");
		Metric Dpm03 = new Metric("copy", "tp-test", "modification", "C", "S");
		Metric Dpm11 = new Metric("prog", "tp-saisie2", "modification", "TC", "S");
		Metric Dpm12 = new Metric("fmt", "tp-typo3", "creation", "M", "M");
		Metric Dpm13 = new Metric("copy", "tp-test2", "modification", "C", "S");
		Metric Dpm21 = new Metric("prog", "tp-saisie3", "modification", "TC", "S");
		Metric Dpm22 = new Metric("fmt", "tp-typo4", "creation", "M", "M");
		Metric Dpm23 = new Metric("copy", "tp-test8", "modification", "C", "S");
		pmR.save(Dpm01); pmR.save(Dpm02); pmR.save(Dpm03);
		pmR.save(Dpm11); pmR.save(Dpm12); pmR.save(Dpm13);
		pmR.save(Dpm21); pmR.save(Dpm22); pmR.save(Dpm23);
		
		Parameter Dmet01 = new Parameter(ab4, Dpm01, 2.01d);
		Parameter Dmet02 = new Parameter(ab4, Dpm02, 5.64d);
		Parameter Dmet03 = new Parameter(ab4, Dpm03, 4.1d);
		mtR.save(Dmet01); mtR.save(Dmet02); mtR.save(Dmet03);
		
		Parameter Dmet24 = new Parameter(ab5, Dpm01, 2.01d);
		Parameter Dmet25 = new Parameter(ab5, Dpm02, 5.64d);
		Parameter Dmet26 = new Parameter(ab5, Dpm03, 4.1d);
		mtR.save(Dmet24); mtR.save(Dmet25); mtR.save(Dmet26);
		
		Parameter Dmet29 = new Parameter(ab6, Dpm01, 2.01d);
		Parameter Dmet30 = new Parameter(ab6, Dpm02, 5.64d);
		Parameter Dmet31 = new Parameter(ab6, Dpm03, 4.1d);
		mtR.save(Dmet29); mtR.save(Dmet30); mtR.save(Dmet31);
		
		/*
		GlobalWorkload cv1 = new GlobalWorkload();
		GlobalWorkload cv2 = new GlobalWorkload();
		GlobalWorkload cv3 = new GlobalWorkload();
		cvR.save(cv1);
		cvR.save(cv2);
		cvR.save(cv3);
		
		QuoteTemplate qt1 = new QuoteTemplate("Template1", cv1);
		QuoteTemplate qt2 = new QuoteTemplate("Template2", cv2);
		QuoteTemplate qt3 = new QuoteTemplate("Template3", cv3);
		qtR.save(qt1);
		qtR.save(qt2);
		qtR.save(qt3);
		
	//	PhaseWithRatio pw1 = new PhaseWithRatio("Phase avec ratio 1", cv1, 0.2);
	//	PhaseWithRatio pw2 = new PhaseWithRatio("Phase avec ratio 2", cv2, 0.4);
	//	PhaseWithRatio pw3 = new PhaseWithRatio("Phase avec ratio 3", cv3, 0.8);
	//	pwiR.save(pw1);
	//	pwiR.save(pw2);
	//	pwiR.save(pw3);
		
		PhaseType ptr1 = new PhaseType("RTU");
		PhaseType ptr2 = new PhaseType("Ratio sur RTU");
		PhaseType ptr3 = new PhaseType("Phase sans ratio");
		PhaseType ptr4 = new PhaseType("UOS");
		PhaseType ptr5 = new PhaseType("Ratio sur global");
		
		ptR.save(ptr1);
		ptR.save(ptr2);
		ptR.save(ptr3);
		ptR.save(ptr4);
		ptR.save(ptr5);
		
		PhaseWithoutRatio pwo1 = new PhaseWithoutRatio("Phase sans ratio 1", cv1, "J'ai oubli� mon chien � l'a�roport",ptr3);
		PhaseWithoutRatio pwo2 = new PhaseWithoutRatio("Phase sans ratio 2", cv2, ptr3);
		PhaseWithoutRatio pwo3 = new PhaseWithoutRatio("Phase sans ratio 3", cv3, "La phase ne tient pas compte de l'orbite de la lune",ptr3);
		pwoR.save(pwo1);
		pwoR.save(pwo2);
		pwoR.save(pwo3);
		
		ProductionWorkload uw1 = new ProductionWorkload();
		ProductionWorkload uw2 = new ProductionWorkload();
		ProductionWorkload uw3 = new ProductionWorkload();
		unR.save(uw1);
		unR.save(uw2);
		unR.save(uw3);
		
		PhaseRtu pt1 = new PhaseRtu("PhaseRTU1", cv1, uw1, ptr1);
		PhaseRtu pt2 = new PhaseRtu("PhaseRTU2", cv2, uw2, ptr1);
		PhaseRtu pt3 = new PhaseRtu("PhaseRTU3", cv3, uw3, ptr1);
		phR.save(pt1);
		phR.save(pt2);
		phR.save(pt3);
		
		
		
		
		Module m01 = new Module("MME434", uw1, met01, 0);
		Module m02 = new Module("KKD235", uw1, met02, 0);
		Module m03 = new Module("HRJAZDFC", uw1, met03, 0);
		Module m11 = new Module("JJKTTR", uw1, met11, 0);
		Module m12 = new Module("APAMER", uw1, met12, 0);
		Module m13 = new Module("DOUDOUX", uw1, met13, 0);
		Module m21 = new Module("NCNV451", uw1, met21, 0);
		Module m22 = new Module("LLDLFMC", uw1, met22, 0);
		Module m23 = new Module("BAMBAM10", uw1, met23, 0);
		moR.save(m01); moR.save(m02); moR.save(m03);
		moR.save(m11); moR.save(m12); moR.save(m13);
		moR.save(m21); moR.save(m22); moR.save(m23);
		
		*/
	
		
		
		/*
		GlobalWorkload Dcv1 = new GlobalWorkload();
		GlobalWorkload Dcv2 = new GlobalWorkload();
		GlobalWorkload Dcv3 = new GlobalWorkload();
		cvR.save(Dcv1);
		cvR.save(Dcv2);
		cvR.save(Dcv3);
		
		
		
		
		//PhaseWithRatio Dpw1 = new PhaseWithRatio("Phase blah avec ratio 1", Dcv1, 0.2);
		//PhaseWithRatio Dpw2 = new PhaseWithRatio("Phase blahblah avec ratio 2", Dcv2, 0.4);
		//PhaseWithRatio Dpw3 = new PhaseWithRatio("Phase blah avec ratio 3", Dcv3, 0.8);
		//pwiR.save(Dpw1);
		//pwiR.save(Dpw2);
		//pwiR.save(Dpw3);
		
		PhaseWithoutRatio Dpwo1 = new PhaseWithoutRatio("Phase sans ratio 1", Dcv1, "J'ai oublié mon chien à l'aéroport",ptr3);
		PhaseWithoutRatio Dpwo2 = new PhaseWithoutRatio("Phase sans ratio 2", Dcv2,ptr3);
		PhaseWithoutRatio Dpwo3 = new PhaseWithoutRatio("Phase sans ratio 3", Dcv3, "La phase ne tient pas compte de l'orbite de la lune",ptr3);
		pwoR.save(Dpwo1);
		pwoR.save(Dpwo2);
		pwoR.save(Dpwo3);
		
		ProductionWorkload Duw1 = new ProductionWorkload();
		ProductionWorkload Duw2 = new ProductionWorkload();
		ProductionWorkload Duw3 = new ProductionWorkload();
		unR.save(Duw1);
		unR.save(Duw2);
		unR.save(Duw3);
		
		PhaseRtu Dpt1 = new PhaseRtu("PhaseRTU1", Dcv1, Duw1,ptr1);
		PhaseRtu Dpt2 = new PhaseRtu("PhaseRTU2", Dcv2, Duw2,ptr1);
		PhaseRtu Dpt3 = new PhaseRtu("PhaseRTU3", Dcv3, Duw3,ptr1);
		phR.save(Dpt1);
		phR.save(Dpt2);
		phR.save(Dpt3);
		
		*/
		
		
		/*
		
		Module Dm01 = new Module("MME434", Duw1, Dmet01, 0);
		Module Dm02 = new Module("KKD235", Duw1, Dmet02, 0);
		Module Dm03 = new Module("HRJAZDFC", Duw1, Dmet03, 0);
		Module Dm11 = new Module("JJKTTR", Duw1, Dmet11, 0);
		Module Dm12 = new Module("APAMER", Duw1, Dmet12, 0);
		Module Dm13 = new Module("DOUDOUX", Duw1, Dmet13, 0);
		Module Dm21 = new Module("NCNV451", Duw1, Dmet21, 0);
		Module Dm22 = new Module("LLDLFMC", Duw1, Dmet22, 0);
		Module Dm23 = new Module("BAMBAM10", Duw1, Dmet23, 0);
		moR.save(Dm01); moR.save(Dm02); moR.save(Dm03);
		moR.save(Dm11); moR.save(Dm12); moR.save(Dm13);
		moR.save(Dm21); moR.save(Dm22); moR.save(Dm23);
		
		*/
		pssi.getNextSequence(pr1);
		pssi.getNextSequence(pr2);
		
		prR.flush();
		
		pr1 = prR.findById(pr1.getId()).get();
		pr2 = prR.findById(pr2.getId()).get();
		
/*
		Quote Dqt1 = new Quote("Devis 1", "BLABAL", ss1, pr1, Dab1, Dcv1, u1, tm1);
		Quote Dqt2 = new Quote("Devis 2","TEXT", ss2, pr2, Dab2, Dcv2, u2, tm2);
		Quote Dqt3 = new Quote("Devis 3","TEXT", ss2, pr1, Dab3, Dcv3, u3, tm1);
		
		Dqt1.setCode(qsi.generateUniqueCode(Dqt1));
		Dqt2.setCode(qsi.generateUniqueCode(Dqt2));
		Dqt3.setCode(qsi.generateUniqueCode(Dqt3));
		
		qtR.save(Dqt1);
		qtR.save(Dqt2);
		qtR.save(Dqt3);
		*/
		
		QuoteTemplate qt1 = new QuoteTemplate("template 1");
		QuoteTemplate qt2 = new QuoteTemplate("template 2");
		QuoteTemplate qt3 = new QuoteTemplate("template 3");
		qtR.save(qt1);
		qtR.save(qt2);
		qtR.save(qt3);
		
		
		return "test";
	}
	
	@GetMapping("/testabacus")
	public String createAbacus() {
		
		Agency ag2 = new Agency("Agence 2");
		agR.save(ag2);		
		Territory te1 = new Territory("Territoire client 1", ag2);
		teR.save(te1);
		Contract co1 = new Contract("Contrat 1", "Jean-Paul", te1);
		coR.save(co1);
		Project pr2 = new Project("GDDEL", "Projet Test");

		prR.save(pr2);
		Team tm1 = new Team("Equipe 1", co1);
		tm1.getProjects().add(pr2);
		
		tmR.save(tm1);	
		Abacus Dab1 = new Abacus("FEM_IRSI_V1170", new Date(), false, co1);
		abR.save(Dab1);
		
		
		Metric me01 = new Metric("prog", "tp-saisie", "modification", "S", "R");
		Metric me02 = new Metric("prog", "tp-saisie", "modification", "S", "TS");
		Metric me03 = new Metric("prog", "tp-saisie", "modification", "S", "S");
		Metric me04 = new Metric("prog", "tp-saisie", "modification", "S", "M");
		Metric me05 = new Metric("prog", "tp-saisie", "modification", "S", "L");
		Metric me06 = new Metric("prog", "tp-saisie", "modification", "S", "TL");
		pmR.save(me01);
		pmR.save(me02);
		pmR.save(me03);
		pmR.save(me04);
		pmR.save(me05);
		pmR.save(me06);
		Parameter met01 = new Parameter(Dab1, me01, 0.1d);
		Parameter met02 = new Parameter(Dab1, me02, 0.2d);
		Parameter met03 = new Parameter(Dab1, me03, 0.3d);
		Parameter met04 = new Parameter(Dab1, me04, 0.5d);
		Parameter met05 = new Parameter(Dab1, me05, 0.8d);
		Parameter met06 = new Parameter(Dab1, me06, 1.7d);
		mtR.save(met01);
		mtR.save(met02);
		mtR.save(met03);
		mtR.save(met04);
		mtR.save(met05);
		mtR.save(met06);
		
		
		Metric me01b = new Metric("prog", "tp-saisie", "modification", "M", "R");
		Metric me02b = new Metric("prog", "tp-saisie", "modification", "M", "TS");
		Metric me03b = new Metric("prog", "tp-saisie", "modification", "M", "S");
		Metric me04b = new Metric("prog", "tp-saisie", "modification", "M", "M");
		Metric me05b = new Metric("prog", "tp-saisie", "modification", "M", "L");
		Metric me06b = new Metric("prog", "tp-saisie", "modification", "M", "TL");
		pmR.save(me01b);
		pmR.save(me02b);
		pmR.save(me03b);
		pmR.save(me04b);
		pmR.save(me05b);
		pmR.save(me06b);
		Parameter met07 = new Parameter(Dab1, me01b, 0.1d);
		Parameter met08 = new Parameter(Dab1, me02b, 0.3d);
		Parameter met09 = new Parameter(Dab1, me03b, 0.5d);
		Parameter met10 = new Parameter(Dab1, me04b, 0.9d);
		Parameter met11 = new Parameter(Dab1, me05b, 1.4d);
		Parameter met12 = new Parameter(Dab1, me06b, 2.7d);
		mtR.save(met07);
		mtR.save(met08);
		mtR.save(met09);
		mtR.save(met10);
		mtR.save(met11);
		mtR.save(met12);
		
		
		/*
		
		
		Metric me01c = new Metric("prog", "tp-saisie", "modification", "C", "R");
		Metric me02c = new Metric("prog", "tp-saisie", "modification", "C", "TS");
		Metric me03c = new Metric("prog", "tp-saisie", "modification", "C", "S");
		Metric me04c = new Metric("prog", "tp-saisie", "modification", "C", "M");
		Metric me05c = new Metric("prog", "tp-saisie", "modification", "C", "L");
		Metric me06c = new Metric("prog", "tp-saisie", "modification", "C", "TL");
		pmR.save(me01c);
		pmR.save(me02c);
		pmR.save(me03c);
		pmR.save(me04c);
		pmR.save(me05c);
		pmR.save(me06c);
		
		Metric me01d = new Metric("prog", "tp-saisie", "modification", "TC", "R");
		Metric me02d = new Metric("prog", "tp-saisie", "modification", "TC", "TS");
		Metric me03d = new Metric("prog", "tp-saisie", "modification", "TC", "S");
		Metric me04d = new Metric("prog", "tp-saisie", "modification", "TC", "M");
		Metric me05d = new Metric("prog", "tp-saisie", "modification", "TC", "L");
		Metric me06d= new Metric("prog", "tp-saisie", "modification", "TC", "TL");
		pmR.save(me01d);
		pmR.save(me02d);
		pmR.save(me03d);
		pmR.save(me04d);
		pmR.save(me05d);
		pmR.save(me06d);
		
		Metric me11 = new Metric("prog", "tp-consultation", "modification", "S", "R");
		Metric me12 = new Metric("prog", "tp-consultation", "modification", "S", "TS");
		Metric me13 = new Metric("prog", "tp-consultation", "modification", "S", "S");
		Metric me14 = new Metric("prog", "tp-consultation", "modification", "S", "M");
		Metric me15 = new Metric("prog", "tp-consultation", "modification", "S", "L");
		Metric me16 = new Metric("prog", "tp-consultation", "modification", "S", "TL");
		pmR.save(me11);
		pmR.save(me12);
		pmR.save(me13);
		pmR.save(me14);
		pmR.save(me15);
		pmR.save(me16);
		
		
		Metric me11b = new Metric("prog", "tp-consultation", "modification", "M", "R");
		Metric me12b = new Metric("prog", "tp-consultation", "modification", "M", "TS");
		Metric me13b = new Metric("prog", "tp-consultation", "modification", "M", "S");
		Metric me14b = new Metric("prog", "tp-consultation", "modification", "M", "M");
		Metric me15b = new Metric("prog", "tp-consultation", "modification", "M", "L");
		Metric me16b = new Metric("prog", "tp-consultation", "modification", "M", "TL");
		pmR.save(me11b);
		pmR.save(me12b);
		pmR.save(me13b);
		pmR.save(me14b);
		pmR.save(me15b);
		pmR.save(me16b);
		
		Metric me11c = new Metric("prog", "tp-consultation", "modification", "C", "R");
		Metric me12c = new Metric("prog", "tp-consultation", "modification", "C", "TS");
		Metric me13c = new Metric("prog", "tp-consultation", "modification", "C", "S");
		Metric me14c = new Metric("prog", "tp-consultation", "modification", "C", "M");
		Metric me15c = new Metric("prog", "tp-consultation", "modification", "C", "L");
		Metric me16c = new Metric("prog", "tp-consultation", "modification", "C", "TL");
		pmR.save(me11c);
		pmR.save(me12c);
		pmR.save(me13c);
		pmR.save(me14c);
		pmR.save(me15c);
		pmR.save(me16c);
		
		Metric me11d = new Metric("prog", "tp-consultation", "modification", "TC", "R");
		Metric me12d = new Metric("prog", "tp-consultation", "modification", "TC", "TS");
		Metric me13d = new Metric("prog", "tp-consultation", "modification", "TC", "S");
		Metric me14d = new Metric("prog", "tp-consultation", "modification", "TC", "M");
		Metric me15d = new Metric("prog", "tp-consultation", "modification", "TC", "L");
		Metric me16d= new Metric("prog", "tp-consultation", "modification", "TC", "TL");
		pmR.save(me11d);
		pmR.save(me12d);
		pmR.save(me13d);
		pmR.save(me14d);
		pmR.save(me15d);
		pmR.save(me16d);
		
		
		
		
		
		Parameter met13 = new Parameter(Dab1, me01, 8.5d);
		Parameter met14 = new Parameter(Dab1, me01, 8.5d);
		Parameter met15 = new Parameter(Dab1, me01, 8.5d);
		Parameter met16 = new Parameter(Dab1, me01, 8.5d);
		Parameter met17 = new Parameter(Dab1, me01, 8.5d);
		Parameter met18 = new Parameter(Dab1, me01, 8.5d);
		Parameter met19 = new Parameter(Dab1, me01, 8.5d);
		Parameter met20 = new Parameter(Dab1, me01, 8.5d);
		Parameter met21 = new Parameter(Dab1, me01, 8.5d);
		Parameter met22 = new Parameter(Dab1, me01, 8.5d);
		Parameter met23 = new Parameter(Dab1, me01, 8.5d);
		Parameter met24 = new Parameter(Dab1, me01, 8.5d);
		Parameter met25 = new Parameter(Dab1, me01, 8.5d);
		
		*/

		
		return "test";
	}
	
}
