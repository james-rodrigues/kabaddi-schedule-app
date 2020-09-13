package com.project.kabaddi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.kabaddi.model.Match;
import com.project.kabaddi.model.Team;
import com.project.kabaddi.service.ScheduleService;

/**
 * The Class SchedulerTest.
 */
public class SchedulerTest {

	/**
	 * Creates the four teams.
	 *
	 * @return the list
	 */
	public List<Team> createFourTeams() {
		List<Team> teams = new ArrayList<>();
		teams.add(new Team("Bengal Warriors", "Kolkata", "Netaji Indoor Stadium"));
		teams.add(new Team("Bengaluru Bulls", "Bengaluru", "Mankapur Indoor Stadium"));
		teams.add(new Team("Dabang Delhi", "Delhi", "Thyagaraj Sports Complex"));
		teams.add(new Team("Gujarat Fortune Giants", "Ahmedabad", "The Arena"));
		return teams;
	}

	/**
	 * Creates the twelve teams.
	 *
	 * @return the list
	 */
	public List<Team> createTwelveTeams() {
		List<Team> teams = new ArrayList<>();
		teams.add(new Team("Bengal Warriors", "Kolkata", "Netaji Indoor Stadium"));
		teams.add(new Team("Bengaluru Bulls", "Bengaluru", "Mankapur Indoor Stadium"));
		teams.add(new Team("Dabang Delhi", "Delhi", "Thyagaraj Sports Complex"));
		teams.add(new Team("Gujarat Fortune Giants", "Ahmedabad", "The Arena"));
		teams.add(new Team("Haryana Steelers", "Sonipat", "Motilal Nehru School of Sports"));
		teams.add(new Team("Jaipur Pink Panthers", "Jaipur", "Sawai Mansingh Stadium"));
		teams.add(new Team("Patna Pirates", "Patna", "Patliputra Sports Complex"));
		teams.add(new Team("Puneri Paltan", "Pune", "Shree Shiv Chhatrapati Sports Complex"));
		teams.add(new Team("Tamil Thalaivas", "Chennai", "Jawaharlal Nehru Stadium"));
		teams.add(new Team("Telugu Titans", "Hyderabad", "Gachibowli Indoor Stadium"));
		teams.add(new Team("U Mumba", "Mumbai", "Sardar Vallabhbhai Patel Indoor Stadium"));
		teams.add(new Team("UP Yoddha", "Lucknow", "Babu Banarasi Das Indoor Stadium"));
		return teams;
	}

	/**
	 * Test for noof matches for four teams.
	 */
	@Test
	void testForNoofMatchesForFourTeams() {
		ScheduleService service = new ScheduleService(LocalDate.of(2020, Month.SEPTEMBER, 13));
		List<Match> matches = service.generateSchedule(createFourTeams());
		assertEquals(12, matches.size());
	}

	/**
	 * Test for noof matches for pro kabaddi.
	 */
	@Test
	void testForNoofMatchesForProKabaddi() {
		ScheduleService service = new ScheduleService(LocalDate.of(2020, Month.SEPTEMBER, 01));
		List<Match> matches = service.generateSchedule(createTwelveTeams());
		for(Match match: matches) {
			System.out.println(match);
		}
		assertEquals(132, matches.size());
	}

}
