import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Test;

public class TestProfiler {

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
