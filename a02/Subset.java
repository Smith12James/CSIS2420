package a02;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * CSIS 2420 - a02
 * @author James Smith + Riley Westergard
 */

public class Subset {
	
	/*
	 * this class is designed to take user input and ask how long a list should be,
	 * take user input on what items to add to the list, add items to the list,
	 * and finally take user input on how many items to return and return x amount 
	 * of random items using Randomized Queue
	 */
	
	public static void main(String[] args) {
		
		Deque<Object> d = new Deque<Object>();
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		StdOut.print("How many items will be in the list? ");
		int listLen = StdIn.readInt();
		
		String[] input = new String[listLen];
		
		for(int i = 0; i < input.length; i++) {
			StdOut.print("Type item " + (i + 1) + " ");
			String strInput = StdIn.readString();
			input[i] = strInput;
		}
		
		for(String i : input) {
			rq.enqueue(i);
		}
		
		StdOut.print("How many items would you like to print? ");
		int k = StdIn.readInt();
		if(k > input.length) {
			throw new NoSuchElementException();
		} else {
			for(int i = 0; i < k; i++) {
				String sample = rq.sample();
				StdOut.println(sample);
			}
		}
		
	}

}