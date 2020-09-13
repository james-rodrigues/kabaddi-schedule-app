package com.project.kabaddi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.project.kabaddi.constants.Constants;
import com.project.kabaddi.model.Match;
import com.project.kabaddi.model.Team;

import lombok.Data;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class ScheduleService {

	/** The start date. */
	private LocalDate startDate;

	/**
	 * Instantiates a new schedule service.
	 *
	 * @param startDate the start date
	 */
	public ScheduleService(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Generate schedule.
	 *
	 * @param teams the teams
	 * @return the list
	 */
	public List<Match> generateSchedule(List<Team> teams) {
		List<Team> homeList = new ArrayList<>(teams);
		List<Team> awayList = new ArrayList<>(teams);
		List<Match> matches = new ArrayList<>();

		// Creating Round Robin List
		for (Team homeTeam : homeList) {
			for (Team awayTeam : awayList) {
				if (!homeTeam.getTeamName().equals(awayTeam.getTeamName())) {
					Match match = new Match();
					match.setAwayTeamName(awayTeam.getTeamName());
					match.setHomeTeamName(homeTeam.getTeamName());
					match.setMatchCity(homeTeam.getHomeCity());
					match.setMatchVenue(homeTeam.getVenue());
					matches.add(match);
				}
			}
		}

		while (Constants.LISTCOUNT.test(matches)) {
			List<Match> pendingMatchList = matches.stream().filter(Constants.MATCHDATENULL)
					.collect(Collectors.toList());

			for (Match match : pendingMatchList) {
				// To check today's match list
				List<Match> todayMatchList = matches.stream()
						.filter(currentDayMatch -> currentDayMatch.getMatchDate() != null
								&& currentDayMatch.getMatchDate().isEqual(startDate))
						.collect(Collectors.toList());

				// To check if team hasn't played yesterday
				List<Match> yestDayMatchList = matches.stream()
						.filter(previousDayMatch -> previousDayMatch.getMatchDate() != null
								&& previousDayMatch.getMatchDate().isEqual(startDate.minusDays(1)))
						.collect(Collectors.toList());

				/**
				 * Conditions for 1. Max 2 matches per day. 2. No team should play on
				 * consecutive days
				 */
				if (todayMatchList.size() < 2
						&& todayMatchList.stream().filter(currentMatch -> currentMatch.isTeamMatch(match)).count() == 0
						&& yestDayMatchList.stream().filter(prevMatch -> prevMatch.isTeamMatch(match)).count() == 0) {
					match.setMatchDate(startDate);
					// schedule.add(match);
				}

			}

			startDate = startDate.plusDays(1);
		}

		// schedule.sort(Comparator.comparing(Match::getMatchDate));
		matches.sort(Comparator.comparing(Match::getMatchDate));
		return matches;
	}

}
