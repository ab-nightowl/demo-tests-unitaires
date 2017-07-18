package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

import java.util.Optional;
import java.util.stream.*;

public class CalculService {

	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);

	public int additionner(String expression) throws CalculException {
		LOG.debug("Evaluation de l'expression " + expression);

		String expressionNotNull;
		try {
			expressionNotNull = Optional.ofNullable(expression)
										.orElseThrow(() -> new CalculException("L'expression saisie est vide"));

			Stream<String> numbers = Stream.of(expressionNotNull.split("\\+"));
	
			return numbers.collect(Collectors.summingInt(Integer::valueOf));
		} catch (NumberFormatException e) {
			LOG.error("Erreur générée par une expression invalide", e);
			throw new CalculException(e);
		}
	}
}