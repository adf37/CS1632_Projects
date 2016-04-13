import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class GamePredictor {

	//public static ArrayList<Team> allTeams = new ArrayList<Team>();
	public static ArrayList<Integer> allRPI = new ArrayList<Integer>();
	public static ArrayList<Integer> allBPI = new ArrayList<Integer>();
	public static ArrayList<Integer> allO = new ArrayList<Integer>();
	public static ArrayList<Integer> allD = new ArrayList<Integer>();
	public static ArrayList<Integer> allLuck = new ArrayList<Integer>();
	public static ArrayList<Team> southRegion = new ArrayList<Team>();
	public static ArrayList<Team> eastRegion = new ArrayList<Team>();
	public static ArrayList<Team> westRegion = new ArrayList<Team>();
	public static ArrayList<Team> midwestRegion = new ArrayList<Team>();
	public static HashMap<String, Team> allTeams = new HashMap<String, Team>();

	public static void main(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("You did not input the correct argument. Please enter in either tournament, region, or game/matchup");
		}
		String argument = args[0];
		initialize(argument);
	}

	/**
	 * Responsible for the initial set up of the game. The user, when the
	 * program is initially run, will enter an argument to indicate what part of
	 * the tournament they would like to simulate: the entire tournament, an
	 * entire region, or an individual matchup (which can be between any two
	 * teams). Upon the correct input that part of the game predictor will run.
	 * If the user fails to input an argument they will be informed and the
	 * system will exit with error code 1.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void initialize(String args) {
		fillRegions();
		setWeights();
		Scanner read = new Scanner(System.in);
		if (args.toLowerCase().equals("tournament")) {
			runTournament();
		} else if (args.toLowerCase().equals("region")) {
			System.out.println("Which region would you like to simulate? East, West, Midwest, or South?");
			String region = read.nextLine();
			while (!region.toLowerCase().equals("west") && !region.toLowerCase().equals("east") && !region.toLowerCase().equals("midwest") && !region.toLowerCase().equals("south")){
				System.out.println("Please enter a valid region: East, West, Midwest, South");
				region = read.nextLine();
			}
			runRegion(region);
		} else if (args.toLowerCase().equals("game") || args.toLowerCase().equals("matchup")) {
			System.out.println("What is the first team you would like to have in the matchup?");
			String team1 = read.nextLine();
			team1 = team1.toLowerCase();
			while (!allTeams.containsKey(team1)){
				System.out.println("Please enter a valid team");
				team1 = read.nextLine();
				team1 = team1.toLowerCase();
			}
			System.out.println("What is the second team you would like to have in the matchup?");
			String team2 = read.nextLine();
			team2 = team2.toLowerCase();
			while (!allTeams.containsKey(team2)){
				System.out.println("Please enter a valid team");
				team2 = read.nextLine();
				team2 = team2.toLowerCase();
			}
			Team t1 = allTeams.get(team1);
			Team t2 = allTeams.get(team2);
			System.out.println("The winner is " + runMatchup(t1, t2));
		}
		else
		{
			throw new IllegalArgumentException("Command could not be recognized!");
			//System.out.println("Your command was not recognized.\nPlease enter in either: tournament, region, or game/matchup");
			//read.close();
			//System.exit(1);
		}
		read.close();
	}

	/**
	 * Fills in the entire bracket with the teams and their respective advanced
	 * metrics for set up. Each team will be added in the correct order
	 * according to their seed and region.
	 */
	public static void fillRegions() {
		String filepath = new File("").getAbsolutePath();
		String csvFile = filepath + "\\MarchMadness2016.csv";
		BufferedReader br = null;
		String line = "";
		boolean firstLine = true;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
					continue;
				}
				String[] current = line.split(",");
				initializeTeam(current);
				allRPI.add(Integer.parseInt(current[2]));
				allBPI.add(Integer.parseInt(current[3]));
				allO.add(Integer.parseInt(current[4]));
				allD.add(Integer.parseInt(current[5]));
				allLuck.add(Integer.parseInt(current[6]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	//Initializes each team with their respective parameter values, puts them into the overall teams hashmap and calls the initializeRegion
	//method to add each team to the region's respective arrayList.
	private static void initializeTeam(String[] current) {
		Team t = new Team(current[0]);
		t.setRegion(current[1]);
		t.setRpi(Integer.parseInt(current[2]));
		t.setBpi(Integer.parseInt(current[3]));
		t.setAdjO(Integer.parseInt(current[4]));
		t.setAdjD(Integer.parseInt(current[5]));
		t.setLuck(Integer.parseInt(current[6]));
		allTeams.put(current[0].toLowerCase(), t);
		initializeRegion(t, current[1]);
	}

	//Adds each team to their respective region
	private static void initializeRegion(Team t, String region) {
		if (region.equals("East"))
			eastRegion.add(t);
		else if (region.equals("South"))
			southRegion.add(t);
		else if (region.equals("West"))
			westRegion.add(t);
		else
			midwestRegion.add(t);
	}

	/**
	 * Normalized distribution of the advanced metric rankings will be conducted
	 * to get a number between 0 and 1 for all the teams.These weights are then set within each team
	 * to be used for their respective head to head matchups.
	 */
	public static void setWeights() {
		// get max and mins of each variable for the distributions
		int maxR = Collections.max(allRPI);
		int minR = Collections.min(allRPI);
		int maxB = Collections.max(allBPI);
		int minB = Collections.min(allBPI);
		int maxO = Collections.max(allO);
		int minO = Collections.min(allO);
		int maxD = Collections.max(allD);
		int minD = Collections.min(allD);
		int maxL = Collections.max(allLuck);
		int minL = Collections.min(allLuck);
		// normalized distribution between 0 and 1 = (xi-max)/(min-max)
		Iterator<Entry<String, Team>> it = allTeams.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Team t = (Team)pair.getValue();
			double dist1 = ((double) (t.rpi - maxR)) / ((double) (minR - maxR));
			t.setrpiWeight(dist1);
			double dist2 = ((double) (t.bpi - maxB)) / ((double) (minB - maxB));
			t.setbpiWeight(dist2);
			double dist3 = ((double) (t.adjO - maxO)) / ((double) (minO - maxO));
			t.setadjOWeight(dist3);
			double dist4 = ((double) (t.adjD - maxD)) / ((double) (minD - maxD));
			t.setadjDWeight(dist4);
			double dist5 = ((double) (t.luck - maxL)) / ((double) (minL - maxL));
			t.setLuckWeight(dist5);
			allTeams.put((String)pair.getKey(), t);
		}
	}

	/**
	 * Runs an individual matchup between two teams that can be from any of the
	 * regions. Returns the team name of the winner of the head to head matchup.
	 * @param team1
	 * @param team2
	 * @retun String
	 */
	public static String runMatchup(Team team1, Team team2) {
		double result1;
		double result2;
		double rpi1 = (Math.random() * ((team1.rpiW+.1) - (team1.rpiW-.2)+1)+(team1.rpiW-.2))*.95;
		double rpi2 = (Math.random() * ((team2.rpiW+.1) - (team2.rpiW-.2)+1)+(team2.rpiW-.2))*.95;
		double bpi1 = (Math.random() * ((team1.bpiW+.1) - (team1.bpiW-.2)+1)+(team1.bpiW-.2))*1.05;
		double bpi2 = (Math.random() * ((team2.bpiW+.1) - (team2.bpiW-.2)+1)+(team2.bpiW-.2))*1.05;
		double o1 = (Math.random() * ((team1.adjOW+.1) - (team1.adjOW-.2)+1)+(team1.adjOW-.2))*1.15;
		double o2 = (Math.random() * ((team2.adjOW+.1) - (team2.adjOW-.2)+1)+(team2.adjOW-.2))*1.15;
		double d1 = (Math.random() * ((team1.adjDW+.1) - (team1.adjDW-.2)+1)+(team1.adjDW-.2))*1.25;
		double d2 = (Math.random() * ((team2.adjDW+.1) - (team2.adjDW-.2)+1)+(team2.adjDW-.2))*1.25;
		result1 = (rpi1 + bpi1 + o1 + d1)/4 + (team1.luckW/15);
		result2 = (rpi2 + bpi2 + o2 + d2)/4 + (team2.luckW/15);
		if (result1 >= result2)
			return team1.name;
		else
			return team2.name;
	}

	/**
	 * Initializes the simulation of the regional matchup with the given region specified by the user earlier. A winner of the region is returned
	 * by the called simulateRegion method and is printed out.
	 * @param region
	 */
	public static void runRegion(String region) {
		Team winner;
		if (region.equals("east")){
			winner = simulateRegion(eastRegion);
		}
		else if(region.equals("west")){
			winner = simulateRegion(westRegion);
		}
		else if (region.equals("midwest")){
			winner = simulateRegion(midwestRegion);
		}
		else{
			winner = simulateRegion(southRegion);
		}
		System.out.println("The winner for region " + region + " is " + winner.name);
	}
	
	/**
	 * Runs a simulation of an entire region that is entered. Each region is made up of 16 teams that compete head to head to make the 
	 * final four. Each matchup is simulated 250 times and the team with the most wins moves on to each subsequent round. The winner
	 * of the region is returned.
	 * @param region
	 * @return Team
	 */
	public static Team simulateRegion(ArrayList<Team> region){
		ArrayList<Team> winners = new ArrayList<Team>();
		for (int i=0; i<region.size(); i+=2){
			int count1 = 0; int count2 = 0;
			for (int j=0; j<250; j++){
				String winner = runMatchup(region.get(i), region.get(i+1));
				if (winner.equals(region.get(i).name)){
					count1++;
				}else{
					count2++;
				}
			}
			System.out.println("Team 1 : " + region.get(i).name + " wins: " + count1);
			System.out.println("Team 2 : " + region.get(i+1).name + " wins: " + count2);
			if (count1 >= count2)
				winners.add(region.get(i));
			else
				winners.add(region.get(i+1));
		}
		
		//Run after the initial opening round to determine the winners of the round of 32, sweet sixteen, and elite 8 within the region.
		while (winners.size() != 1){
			System.out.println("\nRound of " + winners.size());
			for (int z=0; z<winners.size(); z++){
				int count1=0; int count2=0;
				for (int i=0; i<250; i++){
					String winner = runMatchup(winners.get(z), winners.get(z+1));
					if (winner.equals(winners.get(z).name))
						count1++;
					else
						count2++;
				}
				System.out.println("Team 1: " + winners.get(z).name + " wins: " + count1);
				System.out.println("Team 2: " + winners.get(z+1).name + " wins: " + count2);
				System.out.println();
				if (count1 >= count2)
					winners.remove(z+1);
				else
					winners.remove(z);
			}
		}
		return (winners.get(0));
	}

	/**
	 * A simulation of the entire tournament is run with each region being simulated individually. The winners of each region make up
	 * the final four teams that compete for the championship. The south region plays the west and east plays the midwest region. The winners
	 * of each final four matchup meet up for the championship. Each final four matchup and the championship are simulated 250 times
	 * in head to head scenarios. The team with the most wins is the winner of the individual matchup.
	 */
	public static void runTournament() {
		Team final1 = simulateRegion(southRegion);
		Team final2 = simulateRegion(westRegion);
		Team final3 = simulateRegion(eastRegion);
		Team final4 = simulateRegion(midwestRegion);
		Team champ1; Team champ2;
		int count1 = 0; int count2 = 0;
		
		//-------- Final four matchups ----------------------------------------------------
		System.out.println("The final four teams are: " + final1.name + ", " + final2.name + ", " + final3.name + ", " + final4.name);
		for (int i=0; i<100; i++){
			String winner1 = runMatchup(final1, final2);
			if (winner1.equals(final1.name))
				count1++;
			else
				count2++;
		}
		if (count1 >= count2)
			champ1 = final1;
		else
			champ1 = final2;
		
		count1 = 0; count2 = 0;
		for (int i=0; i<250; i++){
			String winner2 = runMatchup(final3, final4);
			if (winner2.equals(final3.name))
				count1++;
			else
				count2++;
		}
		if (count1 >= count2)
			champ2 = final3;
		else
			champ2 = final4;
		
		System.out.println("Finalists are: " + champ1.name + " and " + champ2.name);
		
		//---------- Championship matchup --------------------------------------------------
		count1 = 0; count2 = 0;
		for (int i=0; i<250; i++){
			String winner = runMatchup(champ1, champ2);
			if (winner.equals(champ1.name))
				count1++;
			else
				count2++;
		}
		if (count1>= count2)
			System.out.println("The winner is " + champ1.name);
		else
			System.out.println("The winner is " + champ2.name);
	}

}
