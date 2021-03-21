package m01;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * CSIS-2420 Midterm1
 * 
 * @author StarterCode + James Smith
 *
 */
public class Midterm1 {

	public static void main(String[] args) {
		Container[] containers = {
				new Container(1234, 20, 1.9, "China"),
				new Container(1235, 40, 3.97, "USA"),
				new Container(1236, 40, 4.22, "China"),
				new Container(1237, 20, 2.16, "Ghana"),
				new Container(1238, 20, 2.1, "USA"),
				new Container(1239, 40, 4.08, "Italy"),
				new Container(1240, 40, 3.81, "China"),
				new Container(1241, 40, 4.2, "USA"),
				new Container(1242, 20, 1.82, "Italy")
		};
		
		StdOut.println("Containers: ");
		StdOut.println("=========== ");
		for(Container c : containers) {
			StdOut.println(c);
		}	
		System.out.println();
		
		StdOut.println("= = = =    Part 1   = = = =\n");
		
		/*
		 * Smallest to largest based on ID
		 */
		
		StdOut.println("Containers by natural order:");
		StdOut.println("============================");
		
		Arrays.sort(containers, new Container.IdComparator());
		for(int i = 0; i < containers.length; i++) {
			
			System.out.println(containers[i].toString());
			
		}
		System.out.println();
		
		/*
		 * Largest to smallest based on ID
		 */
		
		StdOut.println("Containers in reverse order:");
		StdOut.println("============================");
		
		Arrays.sort(containers, new Container.ReverseIdComparator());
		for(int i = 0; i < containers.length; i++) {
			
			System.out.println(containers[i].toString());
			
		}
		System.out.println();
		
		StdOut.println("= = = =    Part 2    = = = =\n");
		
		StdOut.println("Foreign Containers:");
		StdOut.println("===================");
		
		/*
		 * This eliminates a row from the array from being printed based on whether
		 * the container is from a location outside of the US. Similar to sorting by
		 * ID where smallest is printed first and largest last, the only difference
		 * is we skip all entries from the US
		 */
		
		Arrays.sort(containers, new Container.IdComparator());
		for(int i = 0; i < containers.length; i++) {
			
			if(containers[i].getCountry() == "USA") {
				i++;
			}
			
			StdOut.println(containers[i].toString());
			
		}

	}

}