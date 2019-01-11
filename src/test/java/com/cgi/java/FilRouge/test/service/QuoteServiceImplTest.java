package com.cgi.java.FilRouge.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cgi.java.FilRouge.app.Application;
import com.cgi.java.FilRouge.enums.EnumPhaseType;
import com.cgi.java.FilRouge.model.Phase;
import com.cgi.java.FilRouge.model.Quote;
import com.cgi.java.FilRouge.service.QuoteServiceImpl;
import com.cgi.java.FilRouge.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceImplTest {

	QuoteServiceImpl quoteServiceImpl= new QuoteServiceImpl();
	
	@Spy
	List<Phase> phases = new ArrayList<Phase>();
	
	@Spy
	Phase phase1 = new Phase();
	@Spy
	Phase phase2 = new Phase();
	@Spy
	Phase phase3 = new Phase();
	
	@Spy
	Quote quote =new Quote();
	
	@Test
	public void testCalculSansRatio() {
		phase1.setPhaseType(EnumPhaseType.UOS);
		phase1.setValue(10);
		phase2.setPhaseType(EnumPhaseType.WITHOUTRATIO);
		phase2.setValue(5.50);
		phase3.setPhaseType(EnumPhaseType.RTU);
		phase3.setValue(2);
		phases.add(phase1);
		phases.add(phase2);
		phases.add(phase3);
		quote.setRealPhases(phases);
		assertThat(quoteServiceImpl.calculSansRatio(quote)).isEqualTo(15.50);
		assertThat(quoteServiceImpl.calculSansRatio(quote)).isNotEqualTo(17.50);
		phase1.setValue(-10);
		phase2.setValue(-5.50);
		assertThat(quoteServiceImpl.calculSansRatio(quote)).isEqualTo(-15.50);
	}

	@Test
	public void testCalculRatioSurGlobal() {
		phase1.setPhaseType(EnumPhaseType.UOS);
		phase1.setValue(10);
		phase2.setPhaseType(EnumPhaseType.ONGLOBAL);
		phase2.setValue(5.50);
		phase3.setPhaseType(EnumPhaseType.ONGLOBAL);
		phase3.setValue(2);
		phases.add(phase1);
		phases.add(phase2);
		phases.add(phase3);
		quote.setRealPhases(phases);
		assertThat(quoteServiceImpl.calculRatioSurGlobal(quote, 60, 40)).isEqualTo(7.50);
		assertThat(quoteServiceImpl.calculRatioSurGlobal(quote, 60, 60)).isEqualTo(7.50);
	}
 
	@Test
	public void testCalculChargeTotale() {
		assertThat(quoteServiceImpl.calculChargeTotale(100, 50, 30)).isEqualTo(180);
		assertThat(quoteServiceImpl.calculChargeTotale(-100, -50, -30)).isEqualTo(-180);
		assertThat(quoteServiceImpl.calculChargeTotale(100.20, 50.30, 30.50)).isEqualTo(181);
		assertThat(quoteServiceImpl.calculChargeTotale(100.20, 50.30, 30.49)).isNotEqualTo(180.9);
		assertThat(quoteServiceImpl.calculChargeTotale(100.20, 50.30, 30.49)).isEqualTo(181);
	}

	@Test
	public void testCalculChargePourcentageRtu() {
		assertThat(quoteServiceImpl.calculChargePourcentageRtu(11, 100)).isEqualTo(11);
		assertThat(quoteServiceImpl.calculChargePourcentageRtu(-11, 100)).isEqualTo(-11);
		assertThat(quoteServiceImpl.calculChargePourcentageRtu(11.255, 100.00)).isEqualTo(11.25);
		assertThat(quoteServiceImpl.calculChargePourcentageRtu(11, 100)).isNotEqualTo(111);
	}

	@Test
	public void testFindBySearchQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAndMakeAsDto() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindParametersFromIdQuote() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenerateUniqueCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitQuote() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTotalCalculsFromQuote() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculAvecRatioRtuEtRtu() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testCalculSommePourcentageGlobal() {
		phase1.setPhaseType(EnumPhaseType.ONGLOBAL);
		phase1.setValue(23);
		phases.add(phase1);
		assertThat(quoteServiceImpl.calculSommePourcentageGlobal(phases)).isEqualTo(23.0);
	}

	@Test
	public void testCalculArrondiChargeTotale() {
		
		assertThat(quoteServiceImpl.calculArrondiChargeTotale(11.255555555)).isEqualTo(11.25);
		assertThat(quoteServiceImpl.calculArrondiChargeTotale(11.255555555)).isNotEqualTo(11);
		assertThat(quoteServiceImpl.calculArrondiChargeTotale(-0.00)).isEqualTo(0);
		assertThat(quoteServiceImpl.calculArrondiChargeTotale(11.255555555)).isNotEqualTo(-11);
		
	}
	

}
