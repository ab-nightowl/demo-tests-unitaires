package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class AppTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private App app;
	private CalculService calculService;

	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(System.in);
		this.calculService = mock(CalculService.class);
		this.app = new App(sc, calculService);
	}

	@Test
	public void testAfficherTitre() throws Exception {
		LOG.info("Lorsque la méthode afficherTitre est invoquée");
		this.app.afficherTitre();

		LOG.info("Alors dans la console, s'affiche '**** Application Calculatrice ****'");
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}

	@Test
	public void testEvaluer() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(35);
	
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
	
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
	
		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}
	
	@Test
	public void testExpressionInvalide() throws Exception {
		LOG.info("Etant donné un service CalculService qui lance l'exception CalculException à l'évaluation de l'expression a+b");
		String expression = "a+b";
		when(calculService.additionner(expression)).thenThrow(CalculException.class);
		
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		
		LOG.info("Alors dans la console, s'affiche 'L'expression a+b est invalide.'");
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("L’expression " + expression + " est invalide.");
	}
	
}
