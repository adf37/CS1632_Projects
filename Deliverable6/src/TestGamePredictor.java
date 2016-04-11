import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class TestGamePredictor {
	
	@Before
	public void setUpTeams(){
		GamePredictor.fillRegions();
		GamePredictor.setWeights();
	}
	
	/**
	 * After each test the global arraylists need to be cleared for each subsequent test.
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
	 * of the 4 regions.
	 */
	@Test
	public void testFillRegions() {
		assertEquals(64, GamePredictor.allTeams.size());
		assertEquals(16, GamePredictor.eastRegion.size());
		assertEquals(16, GamePredictor.westRegion.size());
		assertEquals(16, GamePredictor.midwestRegion.size());
		assertEquals(16, GamePredictor.southRegion.size());
	}
	
	
	@Test
	public void testSetWeights(){
		assertEquals(64, GamePredictor.allBPI.size());
		assertEquals(64, GamePredictor.allRPI.size());
		assertEquals(64, GamePredictor.allD.size());
		assertEquals(64, GamePredictor.allLuck.size());
		assertEquals(64, GamePredictor.allO.size());
		
	}
	
	@Test
	public void testGameMain() throws InterruptedException{
		String [] args = {"game"};
		String lineToRead = "Villanova" + System.getProperty("line.separator") + "Kansas" + System.getProperty("line.separator");
		ByteArrayInputStream in1 = new ByteArrayInputStream(lineToRead.getBytes());
		System.setIn(in1);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test
	public void testMatchupMain() throws InterruptedException{
		String [] args = {"matchup"};
		String lineToRead = "Oklahoma" + System.getProperty("line.separator") + "Oregon" + System.getProperty("line.separator");
		ByteArrayInputStream in1 = new ByteArrayInputStream(lineToRead.getBytes());
		System.setIn(in1);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test
	public void testMatchupMainFirstInvalid() throws InterruptedException{
		String [] args = {"matchup"};
		String line = "NotTeam" + System.getProperty("line.separator") + "Oregon" + System.getProperty("line.separator") + "North Carolina" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test
	public void testMatchupMainBothInvalid() throws InterruptedException{
		String [] args = {"matchup"};
		String line = "NotTeam" + System.getProperty("line.separator") + "Oregon" + System.getProperty("line.separator") + "something" + System.getProperty("line.separator") + "Indiana" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test
	public void testTournamentMain() throws InterruptedException{
		String [] args = {"tournament"};
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainEast() throws InterruptedException{
		String [] args = {"region"};
		String line = "east" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainWest() throws InterruptedException{
		String [] args = {"region"};
		String line = "west" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainMidwest() throws InterruptedException{
		String [] args = {"region"};
		String line = "midwest" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainUpperCase() throws InterruptedException{
		String [] args = {"REGION"};
		String line = "EAST" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainMixedCase() throws InterruptedException{
		String [] args = {"ReGioN"};
		String line = "MIDweSt" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainIncorrect() throws InterruptedException{
		String [] args = {"region"};
		String line = "something" + System.getProperty("line.separator") + "east" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainMultipleIncorrect() throws InterruptedException{
		String [] args = {"region"};
		String line = "something" + System.getProperty("line.separator") + "dark" + System.getProperty("line.separator") + "west" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test 
	public void testRegionMainSouth() throws InterruptedException{
		String [] args = {"region"};
		String line = "south" + System.getProperty("line.separator");
		ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
		System.setIn(in);
		GamePredictor.main(args);
		assertTrue(true);
	}
	
	@Test
	public void testOneonOne(){
		String team1 = "Villanova";
		String team2 = "Kansas";
		assertNotNull(GamePredictor.runMatchup(GamePredictor.allTeams.get(team1.toLowerCase()), GamePredictor.allTeams.get(team2.toLowerCase())));
	}
	
	@Test
	public void testOneonOne2(){
		String team1 = "oklahoma";
		String team2 = "pittsburgh";
		assertNotNull(GamePredictor.runMatchup(GamePredictor.allTeams.get(team1), GamePredictor.allTeams.get(team2)));
	}
	
	@Test
	public void testOneonOne3(){
		String team1 = "wisconsin";
		String team2 = "notre dame";
		assertNotNull(GamePredictor.runMatchup(GamePredictor.allTeams.get(team1), GamePredictor.allTeams.get(team2)));
	}
	
	@Test
	public void testEntireRegionEast(){
		ArrayList<Team> regionToTest = GamePredictor.eastRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	
	@Test
	public void testEntireRegionWest(){
		ArrayList<Team> regionToTest = GamePredictor.westRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	
	@Test
	public void testEntireRegionMidwest(){
		ArrayList<Team> regionToTest = GamePredictor.midwestRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	
	@Test
	public void testEntireRegionSouth(){
		ArrayList<Team> regionToTest = GamePredictor.southRegion;
		Team winner = GamePredictor.simulateRegion(regionToTest);
		assertNotNull(winner);
	}
	

}
