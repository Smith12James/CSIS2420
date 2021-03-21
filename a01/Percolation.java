/**
 * CSIS 2420 - A01
 * 
 * Models a percolation system
 * Creates data type Percolation
 * 
 * @author Hanna Whitney & James Smith collab
 *
 */

package a01;

/**
 * @author James Smith
 * @author Hanna Whitney
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean grid[][]; // 2D array used to create a grid where values are open or closed (true or false)
	private int gridIndex[][];
	private boolean open[][];
	private int gridLength; // length of grid
	private int top; // top of grid
	private int openSites;
	private int bottom;
	private int n = 0;
	private WeightedQuickUnionUF qUF;
	private WeightedQuickUnionUF uf;

	public Percolation(int N) {
		
		if (N <= 0) throw new IllegalArgumentException();
		
		this.grid = new boolean [N][N];
		this.gridIndex = new int [N][N];
		this.open = new boolean[N][N];
		this.gridLength = N;
		this.top = N * N;
		this.bottom = N * N + 1;
		qUF = new WeightedQuickUnionUF(N * N + 2);
		uf = new WeightedQuickUnionUF(N * N + 2);
		
		for(int i = 0; i < N; i++) {
			
			if(i != N * (N -1) + i) {
				
				qUF.union(top, i);
				qUF.union(bottom, N * (N - 1) + i);
				
			}
			
		}

		// create N-by-N grid, with all sites blocked
		// multi-dimensional array, N x N
		// for loop to assign incrementing numbers to array

	}
	
	public static void main(String[] args) {
		
		PercolationStats ps = new PercolationStats(200,100);
		System.out.println(ps.mean());
		System.out.println(ps.stddev());
		System.out.println(ps.confidenceLow());
		System.out.println(ps.confidenceHigh());
//		System.out.println();
//		
//		for(int i = 0; i < ps.thresholds.length; i++) {
//			
//			System.out.println(ps.thresholds[i]);
//			
//		}
		
	}

	public void open(int i, int j) {
		
		if (i < 0 || i >= this.gridLength) {
			
			throw new IndexOutOfBoundsException("row index " + i + " must be between 0 and " + (this.gridLength-1));
			
		} else if(j < 0 || j >= this.gridLength) {
			
			throw new IndexOutOfBoundsException("row index " + j + " must be between 0 and " + (this.gridLength-1));
			
		} if(!isOpen(i,j)) {
		
			this.grid[i][j] = true;
			this.open[i][j] = true;

			this.openSites++;

	        if (i == 0) {
//	            checkAdjacent(i, j);
	            grid[i][j] = true;
	            open[i][j] = true;
	        }

	        // filling in
	        if (i + 1 < this.gridLength  && isFull(i + 1, j));
	        if (j + 1 < this.gridLength  && isFull(i, j + 1));
	        if (j - 1 >= 0 && isFull(i, j - 1));
	        if (i - 1 >= 0 && isFull(i - 1, j));

	        // merging
	        if (i + 1 < this.gridLength  && isOpen(i + 1, j)) qUF.union(i * this.gridLength + j, (i + 1) * this.gridLength + j);
	        if (j + 1 < this.gridLength  && isOpen(i, j + 1)) qUF.union(i * this.gridLength + j, i * this.gridLength + j + 1);
	        if (j - 1 >= 0 && isOpen(i, j - 1)) qUF.union(i * this.gridLength + j, i * this.gridLength + j - 1);
	        if (i - 1 >= 0 && isOpen(i - 1, j)) qUF.union(i * this.gridLength + j, (i - 1) * this.gridLength + j);
	        if (this.gridLength == 1)  { 
	        	
	        	// for 1x1 case
	        	
	            qUF.union(bottom, 0);
	            qUF.union(top, 0);
	            
	        }
		
		}

		// open site (row i, column j) if it is not open already
		// call method isOpen to check if the site is already open.
		// use union connect to connect coordinates to first opening

	}
	
	int getOpenSites() {
		return this.openSites;
	}

	public boolean isOpen(int i, int j) {
		
		if (i < 0 || i >= this.gridLength) throw new IndexOutOfBoundsException("Illegal value for row: must be between 0 and N - 1");
        if (j < 0 || j >= this.gridLength) throw new IndexOutOfBoundsException("Illegal value for col: must be between 0 and N - 1");
        return open[i][j];

		// is site (row i, column j) open?
		// use union find to check if site is connected to top openings.

	}

	public boolean isFull(int i, int j) {
		
		if (i < 0 || i >= this.gridLength) throw new IndexOutOfBoundsException("Illegal value for row: must be between 0 and N - 1");
        if (j < 0 || j >= this.gridLength) throw new IndexOutOfBoundsException("Illegal value for col: must be between 0 and N - 1");
        return qUF.connected(getIndex(0,0), getIndex(i, j));
	}
		
		// is site (row i, column j) full?
		// use union find to verify site is not connected

	public boolean percolates() {
		
		return qUF.connected(top, bottom);
        
    }

	// does the system percolate true or false?
	// use algorithm to check for connections,
	// check if row numbers are the same and next to each other
	// or if the column is the same and next to each other

	
	
	private int getIndex(int i, int j) {
		
		return this.gridLength * i + j;
		
	}
	
}