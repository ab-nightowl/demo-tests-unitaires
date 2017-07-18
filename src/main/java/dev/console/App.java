package dev.console;

import dev.exception.CalculException;
import dev.service.CalculService;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	private Scanner scanner;
	private CalculService calculatrice;

	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}

	protected void afficherTitre() {
		LOG.info("**** Application Calculatrice ****");
	}

	public void demarrer() {

		afficherTitre();

	}

	protected void evaluer(String expression) {
		LOG.info("Evaluation de l'expression " + expression);

		try {
			calculatrice.additionner(expression);
		} catch (CalculException e) {
			LOG.error("L’expression " + expression + " est invalide.");
		}
	}
	
}
