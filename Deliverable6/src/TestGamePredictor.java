import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class TestGamePredictor {
	
	/**
	 * Before each test the fillRegions method is run to fill each region with their respective teams and the overall hashmap containing
	 * all of the teams in the field. Then the weights are set running normalized distribution to get weighted values for the five different
	 * variables being taken into account for this program.
	 */
	@Before
	public void setUpTeams(){
		GamePredictor.fillRegions();
		GamePredictor.setWeights();
	}
	
	/**
	 * After each test the global arraylists and the hashmap need to be cleared for each subsequent test.
	 */
	@After
	public void clearRegions(){
		GamePredictor.allTeams.clear();
		GamePredictor.allBPI.clear();
		GamePredictor.allD.clear();
		GamePredictor.allO.clear();
		GamePredictor.allLuck.clear();
		GamePredictor.allRPI.clear();
		GamePredictor.eastRegion.clear();
		GamePredictor.midwestRegion.clear();
		GamePredictor.westRegion.clear();
		GamePredictor.southRegion.clear();
	}
	
	/**
	 * First we want to test that all teams and regions have been filled. The total tournament size is 64 teams with 16 making up each
	 * of the 4 regions. Within each set up the arraylists containing all of the teams different advanced metrics are also added to.
	 */
	@Test
	public void testFillRegions() {
		assertEquals(64, GamePredictor.allTeams.size());
		assertEquals(16, GamePredictor.eastRegion.size());
		assertEquals(16, GamePredictor.westRegion.size());
		assertEquals(16, GamePredictor.midwestRegion.size());
		assertEquals(16, GamePredictor.southRegion.size());
		assertEquals(64, GamePredictor.allBPI.size());
		assertEquals(64, GamePredictor.allRPI.size());
		assertEquals(64, GamePredictor.allD.size());
		assertEquals(64, GamePredictor.allLuck.size());
		assertEquals(64, GamePredictor.allO.size());
	}
	
	/**
	 * Tests that the setWeights method works. Each of the teams should have weights added to the five different advanced metrics to 
	 * be used in simulations.
	 */
	@Test
	public void testSetWeights(){
		Iterator it = GamePredictor.allTeams.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			Team t = (Team)pair.getValue();
			assertNotNull(t.adjDW);
			assertNotNull(t.adjOW);
			assertNotNull(t.bpiW);
			assertNotNull(t.luckW);
			assertNotNull(t.rpiW);
		}
		
	}
	
	/**
	 * Runs the game simulation through the main method. The argument 'game' is passed into the command line and the user will
	 * be prompted to input with the use of a scanner. Two valid teams are inputted as bytes and sets the System.in value to the 
	 * byte array and runs the simulation through the main method. If there are no errors then the test passes.
	 * @throws InterruptedException
	 */
	@Test
	public void testGameMain() throws InterruptedException{
		String [] args = {"game"};
		String lineToRead = "Villanova" + System.getProperty("line.separator") + "Kansas" + System.getProperty("line.separator");
		ByteArrayInputStream in1 = new ByteArrayInputStream(lineToRead.getBytes());
		System.setIn(in1);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the game simulation through the main method. The argument 'matchup' is passed into the command line and the user will
	 * be prompted to input with the use of a scanner. Two valid teams are inputed as bytes and sets the System.in value to the 
	 * byte array and runs the simulation through the main method. If there are no errors then the test passes.
	 * @throws InterruptedException
	 */
	@Test
	public void testMatchupMain() throws InterruptedException{
		String [] args = {"matchup"};
		String lineToRead = "Oklahoma" + System.getProperty("line.separator") + "Oregon" + System.getProperty("line.separator");
		ByteArrayInputStream in1 = new ByteArrayInputStream(lineToRead.getBytes());
		System.setIn(in1);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the game simulation through the main method. The argument 'matchup' is passed into the command line and the user will
	 * be prompted to input with the use of a scanner. One invalid team is inputed followed by one team that is valid are passed as 
	 * bytes followed by another valid team and is set the System.in value to the 
	 * byte array and runs the simulation through the main method. If there are no errors then the test passes.
	 * @throws InterruptedException
	 */
	@Test
	public void testMatchupMainFirstInvalid() throws InterruptedException{
		String [] args = {"matchup"};
		String line = "NotTeam" + System.getProperty("line.separator") + "Oregon" + System.getProperty("line.separator") + "North Carolina" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the game simulation through the main method. The argument 'matchup' is passed into the command line and the user will
	 * be prompted to input with the use of a scanner. One invalid team is inputed followed by one team that is valid are passed as 
	 * bytes followed by another invalid team followed by a valid team that is set as the System.in value to the 
	 * byte array and runs the simulation through the main method. If there are no errors then the test passes.
	 * @throws InterruptedException
	 */
	@Test
	public void testMatchupMainBothInvalid() throws InterruptedException{
		String [] args = {"matchup"};
		String line = "NotTeam" + System.getProperty("line.separator") + "Oregon" + System.getProperty("line.separator") + "something" + System.getProperty("line.separator") + "Indiana" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation of the enter tournament. 'Tournament' is passed as a command line argument to initialize the entire simulation
	 * of the tournament. If there are no errors the test passes. 
	 * @throws InterruptedException
	 */
	@Test
	public void testTournamentMain() throws InterruptedException{
		String [] args = {"tournament"};
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is the east region and is passed through as a byte array input
	 * stream and set as the scanner input within for the system.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainEast() throws InterruptedException{
		String [] args = {"region"};
		String line = "east" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is the west region and is passed through as a byte array input
	 * stream and set as the scanner input within for the system.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainWest() throws InterruptedException{
		String [] args = {"region"};
		String line = "west" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is the midwest region and is passed through as a byte array input
	 * stream and set as the scanner input within for the system.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainMidwest() throws InterruptedException{
		String [] args = {"region"};
		String line = "midwest" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is the east region and is passed through as a byte array input
	 * stream and set as the scanner input within for the system. This is a test to make sure that if the input is all uppercase that
	 * there will still be no errors.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainUpperCase() throws InterruptedException{
		String [] args = {"REGION"};
		String line = "EAST" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is the midwest region and is passed through as a byte array input
	 * stream and set as the scanner input within for the system. This test is to make sure that if the input is in a mix of uppercase
	 * and lowercase that this does not affect that result and no errors are thrown.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainMixedCase() throws InterruptedException{
		String [] args = {"ReGioN"};
		String line = "MIDweSt" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is an invalid region followed by the valid east region and is passed through as a byte array input
	 * stream and set as the scanner input within for the system. This tests that the program will not accept input that is not one of the four specified regions in the
	 * tournament.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainIncorrect() throws InterruptedException{
		String [] args = {"region"};
		String line = "something" + System.getProperty("line.separator") + "east" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is an invalid region followed by another invalid region and then followed by the valid west region 
	 * and is passed through as a byte array input stream and set as the scanner input within for the system. 
	 * This tests that the program will not accept multiple input that is not one of the four specified regions in the tournament.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainMultipleIncorrect() throws InterruptedException{
		String [] args = {"region"};
		String line = "something" + System.getProperty("line.separator") + "dark" + System.getProperty("line.separator") + "west" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Runs the simulation for entire region. The region that is run is the midwest region and is passed through as a byte array input
	 * stream and set as the scanner input within for the system.
	 * @throws InterruptedException
	 */
	@Test 
	public void testRegionMainSouth() throws InterruptedException{
		String [] args = {"region"};
		String line = "south" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	/**
	 * Two valid teams are passed as strings and the teams are retrieved from the allTeams hashmap and passed into the runMatchup(Team t1, Team t2)
	 * method. A single simulated matchup is run and the winning team is returned as the victor. 
	 */
	@Test
	public void testOneonOne(){
		String team1 = "Villanova";
		String team2 = "Kansas";
		assertNotNull(GamePredictor.runMatchup(GamePredictor.allTeams.get(team1.toLowerCase()), GamePredictor.allTeams.get(team2.toLowerCase())));
	}
	
	/**
	 * Two valid teams are passed as strings and the teams are retrieved from the allTeams hashmap and passed into the runMatchup(Team t1, Team t2)
	 * method. A single simulated matchup is run and the winning team is returned as the victor. 
	 */
	@Test
	public void testOneonOne2(){
		String team1 = "oklahoma";
		String team2 = "pittsburgh";
		assertNotNull(GamePredictor.runMatchup(GamePredictor.allTeams.get(team1), GamePredictor.allTeams.get(team2)));
	}
	
	/**
	 * Two valid teams are passed as strings and the teams are retrieved from the allTeams hashmap and passed into the runMatchup(Team t1, Team t2)
	 * method. A single simulated matchup is run and the winning team is returned as the victor. 
	 */
	@Test
	public void testOneonOne3(){
		String team1 = "wisconsin";
		String team2 = "notre dame";
		assertNotNull(GamePredictor.runMatchup(GamePredictor.allTeams.get(team1), GamePredictor.allTeams.get(team2)));
	}
	
	/**
	 * Runs a test of entire simulation of a valid region using the east region's arraylist of teams. The winner of that region
	 * is returned and checked to make sure it is not null.
	 */
	@Test
	public void testEntireRegionEast(){
		ArrayList<Team> regionToTest = GamePredictor.eastRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	
	/**
	 * Runs a test of entire simulation of a valid region using the west region's arraylist of teams. The winner of that region
	 * is returned and checked to make sure it is not null.
	 */
	@Test
	public void testEntireRegionWest(){
		ArrayList<Team> regionToTest = GamePredictor.westRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	
	/**
	 * Runs a test of entire simulation of a valid region using the midwest region's arraylist of teams. The winner of that region
	 * is returned and checked to make sure it is not null.
	 */
	@Test
	public void testEntireRegionMidwest(){
		ArrayList<Team> regionToTest = GamePredictor.midwestRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	
	/**
	 * Runs a test of entire simulation of a valid region using the south region's arraylist of teams. The winner of that region
	 * is returned and checked to make sure it is not null.
	 */
	@Test
	public void testEntireRegionSouth(){
		ArrayList<Team> regionToTest = GamePredictor.southRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	
	/**
	 * A test of an illegal argument that is passed into the initialize method is run. A try catch is initiated to check if 
	 * an IllegalArgumentException is thrown with the invalid input. If this is the case then the error message should be 
	 * 'Command could not be recognized!' and the System should exit with status 1.
	 */
	@Test
	public void testIllegalArgument(){
		String input = "something";
		try{
			GamePredictor.initialize(input);
		}catch (IllegalArgumentException ex){
			assertEquals(ex.getMessage(), "Command could not be recognized!");
		}
		input = " ";
		try{
			GamePredictor.initialize(input);
		}catch (IllegalArgumentException ex){
			assertEquals(ex.getMessage(), "Command could not be recognized!");
		}
	}
	

}
