package ceMerge;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class MergeComparison {
	
	public static void main(String[] args) {
		MergeComparison mc = new MergeComparison();

		float time;
		float endTime;
		int size = 1024;
		
		StdOut.print("\t   n\t   Merge: \n------------------------------------\n");

		for (int i = 0; i < 9; i++) {
			time = System.nanoTime();
			Merge.sort(mc.getNumbers(size));
			endTime = (System.nanoTime() - time) / 1_000_000_000;
			StdOut.printf("\t %d\t%7.3fs\n", size, endTime);
			size = size * 2;
		}
		
		StdOut.println();
		StdOut.print("\t   n\t  MergeSlow: \n------------------------------------\n");


		size = 1024;
		for (int i = 0; i < 9; i++) {
			time = System.nanoTime();
			MergeSlow.sort(mc.getNumbers(size));
			endTime = (System.nanoTime() - time) / 1_000_000_000;
			StdOut.printf("\t %d\t%7.3fs\n", size, endTime);
			size = size * 2;
		}
		
	}
	
	public Comparable[] getNumbers(int size) {
		Comparable[] bigNumbers = new Comparable[size];
		for (int i = 0; i < bigNumbers.length; i++) {
			bigNumbers[i] = StdRandom.uniform(99999, 999999);
		}	
		return bigNumbers;
	}
}