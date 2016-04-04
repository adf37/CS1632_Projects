import java.awt.*;
import javax.swing.*;
import java.util.*;


public class MainPanel extends JPanel {

    // Current configuration
    public Cell[][] _cells;

    // Backup configuration
    public Cell[][] _backupCells;

    public int _size = 0;

    private int _maxCount = 10000;

    public int _r = 1000;
    public int count = 0;
    
    public boolean _running = false;

    public int getCellsSize() {
	return _size;
    }

    public void setCells(Cell[][] cells) {
	_cells = cells;
    }
    
    public Cell[][] getCells() {
	return _cells;
    }

	//Uses up most CPU time. This call takes in an integer value and loops throught a for loop of _r many times which the value
    //is set at 1000. A string is padded with 1000 '0's' and the integer value passed in is converted to the string value
    //of that number and added to the end of this string of 0's. Then Integer.parseInt is applied to this string which just
    //is the value of the integer that was originally passed in and that number is returned. Therefore, this method is unneeded.
    public int convertToInt(int x) {
		int c = 0;
		String padding = "0";
		while (c < _r) {
			String l = new String("0");
			padding += l;
			c++;
		}
		
		String n = padding + String.valueOf(x);
		int q = Integer.parseInt(n);
		return q;
    }
    
    public int getNumNeighbors(int x, int y) {
	int size = _size;
	int leftX = (x - 1) % size;
	int rightX = (x + 1) % size;
	int upY = (y - 1) % size;
	int downY = (y + 1) % size;

	if (leftX == -1) { leftX = size - 1; }
	if (rightX == -1) { rightX = size - 1; }
	if (upY == -1) { upY = size - 1; }
	if (downY == -1) { downY = size - 1; }
		
	int numNeighbors = 0;

	if (_cells[leftX][upY].getAlive())    { numNeighbors++; }
	if (_cells[leftX][downY].getAlive())  { numNeighbors++; }
	if (_cells[leftX][y].getAlive())      { numNeighbors++; }
	if (_cells[rightX][upY].getAlive())   { numNeighbors++; }
	if (_cells[rightX][downY].getAlive()) { numNeighbors++; }
	if (_cells[rightX][y].getAlive())     { numNeighbors++; }
	if (_cells[x][upY].getAlive())        { numNeighbors++; }
	if (_cells[x][downY].getAlive())      { numNeighbors++; }
	    
	return convertToInt(numNeighbors); //no need for the call, just return the numNeighbors value.
	//return numNeighbors;

    }
    
    public int getNumNeighborsModified(int x, int y){
    	int size = _size;
    	int leftX = (x - 1) % size;
    	int rightX = (x + 1) % size;
    	int upY = (y - 1) % size;
    	int downY = (y + 1) % size;

    	if (leftX == -1) { leftX = size - 1; }
    	if (rightX == -1) { rightX = size - 1; }
    	if (upY == -1) { upY = size - 1; }
    	if (downY == -1) { downY = size - 1; }
    		
    	int numNeighbors = 0;

    	if (_cells[leftX][upY].getAlive())    { numNeighbors++; }
    	if (_cells[leftX][downY].getAlive())  { numNeighbors++; }
    	if (_cells[leftX][y].getAlive())      { numNeighbors++; }
    	if (_cells[rightX][upY].getAlive())   { numNeighbors++; }
    	if (_cells[rightX][downY].getAlive()) { numNeighbors++; }
    	if (_cells[rightX][y].getAlive())     { numNeighbors++; }
    	if (_cells[x][upY].getAlive())        { numNeighbors++; }
    	if (_cells[x][downY].getAlive())      { numNeighbors++; }
    	    
    	//return convertToInt(numNeighbors); //no need for the call, just return the numNeighbors value.
    	return numNeighbors;
    }

    public boolean iterateCell(int x, int y) {
	boolean toReturn = false;
	boolean alive = _cells[x][y].getAlive();
	int numNeighbors = getNumNeighbors(x, y);
	if (alive) {
	    if (numNeighbors < 2 || numNeighbors > 3) {
		toReturn = false;
	    } else {
		toReturn = true;
	    }
	} else {
	    if (numNeighbors == 3) {
		toReturn = true;
	    } else {
		toReturn = false;
	    }
	}
	return toReturn;

    }
    
    /**
     * Uses the same functionality as the original iterateCell method, however, to get the numNeighbors the call is 
     * made to the getNumNeighborsModified method rather than the getNumNeighbors method
     */
    
    public boolean iterateCellModified(int x, int y){
    	boolean toReturn = false;
    	boolean alive = _cells[x][y].getAlive();
    	int numNeighbors = getNumNeighborsModified(x, y);
    	if (alive) {
    	    if (numNeighbors < 2 || numNeighbors > 3) {
    		toReturn = false;
    	    } else {
    		toReturn = true;
    	    }
    	} else {
    	    if (numNeighbors == 3) {
    		toReturn = true;
    	    } else {
    		toReturn = false;
    	    }
    	}
    	return toReturn;
    }

    public void displayIteration(boolean[][] nextIter) {
	//System.out.println("\tDisplaying...");
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size;  k++) {
		_cells[j][k].setAlive(nextIter[j][k]);
	    }
	}
	setVisible(true);
    }

    /**
     * For each of the cells, calculate what their
     * state will be for the next iteration.
     */
    
    public void calculateNextIteration() {
	//System.out.println("\tCalculating..");
	boolean[][] nextIter = new boolean[_size][_size];
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		nextIter[j][k] = iterateCell(j, k);
	    }
	}

	displayIteration(nextIter);
    }

    /**
     * For each of the cells, calculate what their state will be for the next iteration using the modified
     * iterateCell method
     */
    
    public void calculateNextIterationModified(){
    	//System.out.println("\tCalculating..");
    	boolean[][] nextIter = new boolean[_size][_size];
    	for (int j = 0; j < _size; j++) {
    	    for (int k = 0; k < _size; k++) {
    		nextIter[j][k] = iterateCellModified(j, k);
    	    }
    	}

    	displayIteration(nextIter);
    }
    
    /**
     * Make a copy of the current cells and put
     * the copy in the backup cells.
     */
    
    public void backup() {
	_backupCells = new Cell[_size][_size];
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		_backupCells[j][k] = new Cell();
		_backupCells[j][k].setAlive(_cells[j][k].getAlive());
	    }
	}
    }
    
    /**
     * Makes a copy of the current cells and puts the copy in the backup cells. The difference between backup() and 
     * backupModified() is that in the below method a new two dimensional cell array specified by the inputted size
     * from the initial set up of the game is created and the built in Arrays.copyOf method is used to copy the contents
     * of the current cells to those of the backup cells and avoids doing a nested for loop call on each iteration. 
     */
    public void backupModified(){
    	_backupCells = new Cell[_size][_size];
    	_backupCells = Arrays.copyOf(_cells, _cells.length);
    }

    /**
     * This is for debug use.  It will display
     * the state of cells in a convenient format.
     * First it will display backup cells and then
     * the current cells.  Backup cells are what
     * you revert to when you press Undo.
     */
    
    public void debugPrint() {
	System.out.println("Backup cells");

	try {
	    for (int j = 0; j < _size; j++) {
		for (int k = 0; k < _size; k++) {

		    if (_backupCells[j][k].getAlive()) {
			System.out.print("X");
		    } else {
			System.out.print(".");
		    }
		}
		System.out.println("");
	    }

	    System.out.println("Current cells:");

	    for (int j = 0; j < _size; j++) {
		for (int k = 0; k < _size; k++) {

		    if (_cells[j][k].getAlive()) {
			System.out.print("X");
		    } else {
			System.out.print(".");
		    }
		}
		System.out.println("");
	    }
	} catch (Exception ex) {
	    System.out.println("Nothin' yet");
	}
					   
	
    }

    /**
     * Convert the Main Panel into a String
     * which can be written to a file.
     */
    
    public String toString() {

	// Loop through all of the cells, and
	// if they are alive, add an "X" to
	// the String, if dead, a ".".

	String toWrite = "";
	
	for (int j = 0; j < _size; j++) {
	    for(int k = 0; k < _size; k++) {
		if (_cells[j][k].getAlive()) {
		    toWrite += _cells[j][k].toString();
		} else {
		    toWrite += _cells[j][k].toString();
		}
		    
	    }
	    toWrite += "\n";
	}
	return toWrite;
    }

    /**
     * Run one iteration of the Game of Life
     */
    
    public void run() {
	backup();
	calculateNextIteration();
    }
    
    
    /**
     * Run the system continuously.
     */

    /**
     * This is the modified runContinuous method with the middle section of the method commented out and a new call to 
     * the modified backup method. The middle section was commented out to speed up performance because a try catch exception
     * caused the thread to sleep by 20 ms on each iteration and the _r variable was modified within a for loop that iterated
     * 10,000 times just to set back _r to its original value before iterating. This was an unneeded call and hampered performance.
     */
	 //Another CPU intensive method, for loop iteration is unneeded. _r is 1000 before and after the iterations
    public void runContinuous() {
	_running = true;
	//keep the count of how many iterations are completed and limiting to 1000 total iterations. We do this to properly time modifications.
	while (_running) {
	    //System.out.println("Running...");
	    /*int origR = _r;
	    try {
		Thread.sleep(20);
	    } catch (InterruptedException iex) { }
	    for (int j=0; j < _maxCount; j++) {
	    	_r += (j % _size) % _maxCount;
		_r += _maxCount;
	    }
	    _r = origR;*/
	    //backup();
	    backupModified();
	    calculateNextIterationModified();
	}
	//System.exit(0); //exit the game after we have reached 1000 iterations.
    }

    /**
     * This is the runContinuous method initially created from the original files without any modifications. It will be
     * used to display the results of pinning tests that show that the below method and the modified runContinuous method
     * above still produce the same result.
     */
    public void runContinuousFormer(){
    	_running = true;
    	while (_running) {
    	    //System.out.println("Running...");
    	    int origR = _r;
    	    try {
    		Thread.sleep(20);
    	    } catch (InterruptedException iex) { }
    	    for (int j=0; j < _maxCount; j++) {
    	    	_r += (j % _size) % _maxCount;
    		_r += _maxCount;
    	    }
    	    _r = origR;
    	    backup();
    	    calculateNextIteration();
    	}
    	//System.exit(0); //exit the game after we have reached 1000 iterations.
    }
    /**
     * Stop a continuously running system.
     */
    
    public void stop() {
	_running = false;
    }
   

    /**
     * Convert the array of Cell objects into an 
     * array of booleans.
     */
    
    public boolean[][] convertToBoolean(Cell[][] cells) {

	// 2-D array to return.  Remember everything
	// is false by default for boolean arrays!
	
	boolean[][] toReturn = new boolean[_size][_size];

	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		if (cells[j][k].getAlive()) {
		    toReturn[j][k] = true;
		} else {
		    // Nothing to do!  Already
		    // set to false by default.
		    // toReturn[j][k] = false;
		}
	    }
	}
	return toReturn;
	
    }

    /**
     * Revert back to the previous iteration,
     * which we have saved in _backupCells.
     */
    
    public void undo() {
	displayIteration(convertToBoolean(_backupCells));
    }

    /**
     * Loop through the entire array and reset
     * each of the Cells in the MainPanel.
     */
    
    public void clear() {
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		_cells[j][k].reset();
	    }
	}
	// Need to call setVisible() since
	// we did not do a displayIteration()
	// call.
	setVisible(true);
    }

    /**
     * Load in a previously saved Game of Life
     * configuration.
     */
    
    public void load(ArrayList<String> lines) {
	boolean[][] loaded = new boolean[_size][_size];

	
	for (int j = 0; j < _size; j++) {
	    String l = lines.get(j);
	    for (int k = 0; k < _size; k++) {

		// Reset the "been alive" count
		_cells[j][k].resetBeenAlive();

		// For each line, get each character.
		// If it's a '.', the cell stays
		// dead.  Otherwise, the cell is alive.
		// We could specifically check for
		// an 'X' for alive and throw an
		// error if we get an unexpected char.
		if (l.charAt(k) == '.') {		    
		    _cells[j][k].setAlive(false);
		    loaded[j][k] = false;
		} else {
		    _cells[j][k].setAlive(true);
		    loaded[j][k] = true;
		}
	    }
	}

	// Now that we have set the Cells to what
	// we expect, display the iteration.
	displayIteration(loaded);
	// debugPrint();
	
    }
    

    public MainPanel(int size) {
	super();
	_size = size;
	setLayout(new GridLayout(size, size));
	_cells = new Cell[size][size];
	for (int j = 0; j < size; j++) {
	    for (int k = 0; k < size; k++) {
		_cells[j][k] = new Cell();
		this.add(_cells[j][k]);
		_cells[j][k].setAlive(false);
	    }
	}

    }
	
}
