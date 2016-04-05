import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Test;

public class TestProfiler {

	/**
	 * This method tests the first refractored method which is the getNumNeighbors(int x, int y) method that before called
	 * convertToInt(int x) unnecessarily resulting in unneeded CPU usage. The refractored method simply returns the calculated
	 * numNeighbors value instead of making this call. In this test we are looking to make sure that running both the modified 
	 * and former methods should result in the same value being returned when all cells are alive.
	 */
	@Test
	public void testNumNeighborsAllAlive() {
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				test._cells[i][j].setAlive(true);
				test2._cells[i][j].setAlive(true);
			}
		}
		int val = test.getNumNeighbors(3, 10);
		int val2 = test.getNumNeighborsModified(3, 10);
		assertEquals(val, val2);
	}
	
	/**
	 * This method tests the first refractored method which is the getNumNeighbors(int x, int y) method that before called
	 * convertToInt(int x) unnecessarily resulting in unneeded CPU usage. The refractored method simply returns the calculated
	 * numNeighbors value instead of making this call. In this test we are looking to make sure that running both the modified 
	 * and former methods should result in the same value being returned when all cells are dead.
	 */
	@Test
	public void testNumNeighborsAllDead(){
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				test._cells[i][j].setAlive(false);
				test2._cells[i][j].setAlive(false);
			}
		}
		test.setCells(test._cells);
		test2.setCells(test2._cells);
		assertEquals(test.getNumNeighbors(2, 11), test2.getNumNeighborsModified(2, 11));
	}
	
	/**
	 * This method tests the first refractored method which is the getNumNeighbors(int x, int y) method that before called
	 * convertToInt(int x) unnecessarily resulting in unneeded CPU usage. The refractored method simply returns the calculated
	 * numNeighbors value instead of making this call. In this test we are looking to make sure that running both the modified 
	 * and former methods should result in the same value being returned when all cells are dead except one.
	 */
	@Test
	public void testNumNeighborsAllDeadExceptOne(){
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				if (i == 0 && j==0){
					test._cells[i][j].setAlive(true);
					test2._cells[i][j].setAlive(true);
				}
				else{
					test._cells[i][j].setAlive(false);
					test2._cells[i][j].setAlive(false);
				}
			}
		}
		test.setCells(test._cells);
		test2.setCells(test2._cells);
		assertEquals(test.getNumNeighbors(2, 1), test2.getNumNeighborsModified(2, 1));
	}
	
	/**
	 * This method tests the first refractored method which is the getNumNeighbors(int x, int y) method that before called
	 * convertToInt(int x) unnecessarily resulting in unneeded CPU usage. The refractored method simply returns the calculated
	 * numNeighbors value instead of making this call. In this test we are looking to make sure that running both the modified 
	 * and former methods should result in the same value being returned when all cells are alive except for one.
	 */
	@Test
	public void testNumNeighborsAllAliveExceptOne(){
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				if (i == 0 && j==0){
					test._cells[i][j].setAlive(false);
					test2._cells[i][j].setAlive(false);
				}
				else{
					test._cells[i][j].setAlive(true);
					test2._cells[i][j].setAlive(true);
				}
			}
		}
		test.setCells(test._cells);
		test2.setCells(test2._cells);
		assertEquals(test.getNumNeighbors(2, 1), test2.getNumNeighborsModified(2, 1));
	}

	/**
	 * This method tests the first refractored method which is the getNumNeighbors(int x, int y) method that before called
	 * convertToInt(int x) unnecessarily resulting in unneeded CPU usage. The refractored method simply returns the calculated
	 * numNeighbors value instead of making this call. In this test we are looking to make sure that running both the modified 
	 * and former methods should result in the same value being returned when some cells are alive and some are dead.
	 */
	@Test
	public void testNumNeighborsMixedAlive(){
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				if (i % test._size == 0 && j % test._size ==0){
					test._cells[i][j].setAlive(true);
					test2._cells[i][j].setAlive(true);
				}
				else{
					test._cells[i][j].setAlive(false);
					test2._cells[i][j].setAlive(false);
				}
			}
		}
		test.setCells(test._cells);
		test2.setCells(test2._cells);
		assertEquals(test.getNumNeighbors(10, 6), test2.getNumNeighborsModified(10, 6));
	}
	
	/**
	 * This is the third refractored method that added a modification to the previous backup method. In the modified backup method
	 * the current cells are copied to the backup cells array with the use of Arrays.copyOf method instead of a nested for loop
	 * that iterates over each individual cell and copying the contents to the that indice in the backup cell array. This method tests
	 * that the backup cells in both the modified and former method call contain the same values when cells are a mix of alive and dead.
	 */
	@Test
	public void testBackupMixedCells(){
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				if (i % test._size == 0 && j % test._size ==0){
					test._cells[i][j].setAlive(true);
					test2._cells[i][j].setAlive(true);
				}
				else{
					test._cells[i][j].setAlive(false);
					test2._cells[i][j].setAlive(false);
				}
			}
		}
		test.setCells(test._cells);
		test2.setCells(test2._cells);
		test.backup();
		test2.backupModified();
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				if (test._backupCells[i][j].getAlive() != test2._backupCells[i][j].getAlive()){
					fail("Indices are not equal");
				}
			}
		}
		assertTrue(true);
	}
	
	/**
	 * This is the third refractored method that added a modification to the previous backup method. In the modified backup method
	 * the current cells are copied to the backup cells array with the use of Arrays.copyOf method instead of a nested for loop
	 * that iterates over each individual cell and copying the contents to the that indice in the backup cell array. This method tests
	 * that the backup cells in both the modified and former method call contain the same values when all the cells are dead.
	 */
	@Test
	public void testBackupAllDead(){
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				test._cells[i][j].setAlive(false);
				test2._cells[i][j].setAlive(false);
			}
		}
		test.setCells(test._cells);
		test2.setCells(test2._cells);
		test.backup();
		test2.backupModified();
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				if (test._backupCells[i][j].getAlive() != test2._backupCells[i][j].getAlive()){
					fail("Indices are not equal");
				}
			}
		}
		assertTrue(true);
	}
	
	/**
	 * This is the third refractored method that added a modification to the previous backup method. In the modified backup method
	 * the current cells are copied to the backup cells array with the use of Arrays.copyOf method instead of a nested for loop
	 * that iterates over each individual cell and copying the contents to the that indice in the backup cell array. This method tests
	 * that the backup cells in both the modified and former method call contain the same values when all cells are alive.
	 */
	@Test
	public void testBackupAllAlive(){
		MainPanel test = new MainPanel(15);
		MainPanel test2 = new MainPanel(15);
		
		test._cells = new Cell[test._size][test._size];
		test2._cells = new Cell[test2._size][test2._size];
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				test._cells[i][j] = new Cell();
				test2._cells[i][j] = new Cell();
				test._cells[i][j].setAlive(true);
				test2._cells[i][j].setAlive(true);
			}
		}
		test.setCells(test._cells);
		test2.setCells(test2._cells);
		test.backup();
		test2.backupModified();
		for (int i=0; i<test._size; i++){
			for (int j=0; j<test._size; j++){
				if (test._backupCells[i][j].getAlive() != test2._backupCells[i][j].getAlive()){
					fail("Indices are not equal");
				}
			}
		}
		assertTrue(true);
	}
	
	/**
	 * This method tests that the refractoring of the runContinuous method is unaffected in its functionality by my refractoring. A thread
	 * that runs the game with the run continuous method will test that no errors will occur while running the profiler for a short time.
	 */
	@Test
	public void testRunContinuousInitial(){
		MainPanel test = new MainPanel(15);
		
		Thread t = new Thread(new runProfiler(test));
		t.start();
		try{
			Thread.sleep(10);
		}catch(InterruptedException ex){}
		assertTrue(test._running);
		test.stop();
		t.stop();
	}
	
	/**
	 * This method tests that the refractoring of the runContinuous method is unaffected in its functionality by my refractoring. A thread
	 * that runs the game with the run continuous method will test that no errors will occur while running the profiler for a short time and 
	 * then stopping the thread's execution.
	 */
	@Test
	public void testRunContinuousStop(){
		MainPanel test = new MainPanel(15);
		Thread t = new Thread(new runProfiler(test));
		t.start();
		try{
			Thread.sleep(10);
		}catch(InterruptedException ex){}
		test.stop();
		t.stop();
		assertFalse(test._running);
	}
	
	/**
	 * This method tests that the refractoring of the runContinuous method is unaffected in its functionality by my refractoring. A thread
	 * that runs the game with the run continuous method will test that no errors will occur while running the profiler for a longer period
	 * of time or fifty iterations.
	 */
	@Test
	public void testRunContinuousLong(){
		MainPanel test = new MainPanel(15);
		Thread t = new Thread(new runProfiler(test));
		t.start();
		for (int i=0; i<50; i++){
			try{
				Thread.sleep(100);
			}catch(InterruptedException ex){}
			assertTrue(test._running);
		}
		test.stop();
		t.stop();
		assertFalse(test._running);
	}
	
	/**
	 * Used to run the threads of Conway's Game of Life to test the run continuous method refractoring.
	 */
	class runProfiler implements Runnable{
		
		MainPanel panel;
		public runProfiler(MainPanel m){
			panel = m;
		}

		public void run() {
			panel.runContinuous();
		}
	}
}
