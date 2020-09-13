package com.project.kabaddi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new team.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

	/** The team name. */
	private String teamName;

	/** The home city. */
	private String homeCity;

	/** The venue. */
	private String venue;

}
