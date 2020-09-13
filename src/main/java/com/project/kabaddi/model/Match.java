package com.project.kabaddi.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * Instantiates a new match.
 */
@Data
public class Match {

	/** The home team name. */
	private String homeTeamName;

	/** The away team name. */
	private String awayTeamName;

	/** The match date. */
	private LocalDate matchDate;

	/** The venue. */
	private String matchVenue;

	/** The city. */
	private String matchCity;

	public boolean isTeamMatch(Match other) {
		return (other != null && other instanceof Match) && (this.homeTeamName.equals(other.homeTeamName)
				|| this.homeTeamName.equals(other.awayTeamName) || this.awayTeamName.equals(other.awayTeamName)
				|| this.awayTeamName.equals(other.homeTeamName));
	}

}
