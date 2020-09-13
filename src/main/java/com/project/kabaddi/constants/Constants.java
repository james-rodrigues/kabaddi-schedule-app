package com.project.kabaddi.constants;

import java.util.List;
import java.util.function.Predicate;

import com.project.kabaddi.model.Match;

/**
 * The Interface Constants.
 */
public interface Constants {

	/** The yes. */
	String YES = "Y";

	/** The no. */
	String NO = "N";

	/** The digit regex. */
	String DIGIT_REGEX = "\\d+";

	/** The matchdatenull. */
	Predicate<Match> MATCHDATENULL = match -> match.getMatchDate() == null;

	/** The listcount. */
	Predicate<List<Match>> LISTCOUNT = matches -> matches.stream().filter(MATCHDATENULL).count() != 0;

}
