package com.project.kabaddi.driver;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.kabaddi.constants.Constants;
import com.project.kabaddi.model.Match;
import com.project.kabaddi.model.Team;
import com.project.kabaddi.service.ScheduleService;

/**
 * The Class ScheduleCreator.
 */
public class ScheduleCreator {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		LocalDate startDate = null;
		List<Team> teams = new ArrayList<>();
		Scanner scan = new Scanner(System.in);

		System.out.println("***************************************************************");
		System.out.println("*********************PRO KABADDI TOURNAMENT********************");
		System.out.println("***************************************************************");

		System.out.println("Enter the number of teams :");
		String n = scan.next();

		if (n != null && !"".equals(n) && !"\\s".equals(n) && n.matches(Constants.DIGIT_REGEX)) {
			int noOfTeams = Integer.parseInt(n);

			teams = createTeams(scan, noOfTeams);

			startDate = getStartDate(startDate, scan);

			System.out.println("Generating Schedule");

			ScheduleService service = new ScheduleService(startDate.plusDays(1));
			List<Match> matchList = service.generateSchedule(teams);

			System.out.println("***************************************************************");
			System.out.println("**********************TOURNAMENT SCHEDULE**********************");
			System.out.println("***************************************************************");

			printSchedule(matchList);

		} else {
			scan.close();
			System.err.println("Invalid Input for no of Teams!!!! Please provide valid input");
		}

		scan.close();

	}

	/**
	 * Prints the schedule.
	 *
	 * @param matchList the match list
	 */
	private static void printSchedule(List<Match> matchList) {
		int matchNumber = 1;
		for (Match match : matchList) {
			System.out.println("########## Match " + matchNumber + " ##########");

			System.out.println("Match Date : " + match.getMatchDate());
			System.out.println(match.getHomeTeamName() + " v/s " + match.getAwayTeamName());
			System.out.println("Venue : " + match.getMatchVenue() + " in " + match.getMatchCity() + "\n");

			matchNumber++;
		}
	}

	/**
	 * Gets the start date.
	 *
	 * @param startDate the start date
	 * @param scan      the scan
	 * @return the start date
	 */
	private static LocalDate getStartDate(LocalDate startDate, Scanner scan) {
		System.out.println("***************************************************************");
		System.out.println("Does the tournament have a Start Date ? Enter Y for YES and N for No");
		String dateDecision = scan.next();

		if (Constants.YES.equals(dateDecision)) {
			System.out.println("Please enter the start Date in yyyy-mm-dd:");
			try {
				startDate = LocalDate.parse(scan.next());
			} catch (DateTimeException ex) {
				System.err.println("Please enter date in proper format");
				scan.close();
				System.exit(0);
			}
		} else {
			System.out.println("Assuming Tomorrow Date as StartDate !!!");
			startDate = LocalDate.now().plusDays(1);
		}
		return startDate;
	}

	/**
	 * Creates the teams.
	 *
	 * @param scan      the scan
	 * @param noOfTeams the no of teams
	 * @return the list
	 */
	private static List<Team> createTeams(Scanner scan, int noOfTeams) {
		List<Team> teams = new ArrayList<>();
		if (noOfTeams > 1) {
			for (int i = 0; i < noOfTeams; i++) {
				System.out.println("***************************************************************");
				System.out.println("*********************ENTER TEAM " + (i + 1) + "********************");
				System.out.println("***************************************************************");

				Team team = new Team();
				System.out.println("Enter Team Name :");
				team.setTeamName(scan.next());

				System.out.println("Enter Home City :");
				team.setHomeCity(scan.next());

				System.out.println("Enter Venue Location :");
				team.setVenue(scan.next());

				teams.add(team);
			}
		} else {
			System.err.println("Number of Teams should be greater than 1 for a tounament to commence !!!");
			scan.close();
			System.exit(0);
		}
		return teams;
	}

}
