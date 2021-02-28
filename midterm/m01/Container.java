package m01;

import java.util.Comparator;

/** 
 * Represents a freight container that is defined by its length, 
 * width, and country of origin.
 * 
 * @author StarterCode + James Smith
 *
 */
public class Container implements Comparable<Container>{
	
	private String country;  // country of origin
	private int id;          // identification number
	private int length;      // in feet
	private double weight;   // in tons
	
	public Container(int id, int length, double weight, String country ) {
		this.id = id;
		this.length = length;
		this.weight = weight;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}	

	public int getLength() {
		return length;
	}

	public double getWeight() {
		return weight;
	}

	public String getCountry() {
		return country;
	}

	@Override public String toString() {
		return String.format("%4d: %2dft %ft %s", id, length, weight, country);
	}

	@Override public int compareTo(Container o) {

		return this.getId() - o.getId();
		
	}
	
	/*
	 * IdComparator compares the id and orders them from smallest to largest
	 */
	
	static class IdComparator implements Comparator<Container> {

		@Override public int compare(Container o1, Container o2) {

			Integer id1 = o1.getId();
			Integer id2 = o2.getId();
			
			return id1.compareTo(id2);
			
		}
		
	}
	
	/*
	 * ReverseIdComparator compares the id and orders them from largest to smallest
	 */
	
	static class ReverseIdComparator implements Comparator<Container> {

		@Override public int compare(Container o1, Container o2) {

			Integer id1 = o1.getId();
			Integer id2 = o2.getId();
			
			return id2.compareTo(id1);
			
		}
		
	}
	
}
