package dev.service;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

/**
 * Test unitaire de la classe dev.service.CalculService.
 */

public class CalculServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	@Test
	public void testAdditionner() throws CalculException {
		LOG.info("Etant donn�, une instance de la classe CalculService");
		CalculService calcul = new CalculService();
		
		LOG.info("Lorsque j'�value l'addition de l'expression 1+3+4");
		int somme = calcul.additionner("1+3+4");

		LOG.info("Alors j'obtiens le r�sultat 8");
		Assert.assertEquals(8, somme);
	}
	
	@Test (expected = CalculException.class)
	public void testCalculException() throws CalculException {
		LOG.info("Etant donné une instance de la classe CalculService");
		CalculService calcul = new CalculService();
		
		LOG.info("Lorsque j'évalue l'addition d'une l'expression vide");
		calcul.additionner("");
		
	}

}
