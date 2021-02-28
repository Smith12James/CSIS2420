package ceLinearBinary;

public class Search {
	
	public static int linear(int[] numbers, int key) {
		
		int index = 0;
		
		/*
		 * 
		 */ 
		
		for(int i = 0; i < numbers.length; i++) {
			
			if(numbers[i] == key) {
				
				index = i;
				break;
				
			}
			
		}
		
		return index; // return index location
		
	}
	
	public static int binary(int[] numbers, int key) {
		
		int index = 0;
		
		/*
		 * 
		 */
		
		
		
		return index;
		
	}

}
