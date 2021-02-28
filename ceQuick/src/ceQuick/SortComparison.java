package ceQuick;

import java.util.Random;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;

public class SortComparison {
    
    private static Integer[] getRandomNumberArray(int arraySize, int numberOfDigits) {
        if(numberOfDigits >= 10 || numberOfDigits <= 0) {
            throw new IllegalArgumentException("The number of digits needs to be a value between 1 and 10.");
        }
        Integer[] arr = new Integer[arraySize];
        for(int i = 0; i < arraySize; i++) {
        	int rand = (int) Math.pow(10,  numberOfDigits - 1);
        	rand = rand + new Random().nextInt(9 * rand);
            arr[i] = rand;
        }
        return arr;
    }

    public static void main(String[] args) {

        int n = 1000;
        System.out.println("\tn\t|    Selection\t|\t Quick\t|");
        System.out.println("----------------|---------------|---------------|");
        for(int i = 0; i <= 10; i++) {
        	Integer[] temp = getRandomNumberArray(n,7);
        	Integer[] tempc = new Integer[n];

        	for(int j = 0; j < temp.length; j++) {
                tempc[j] = temp[j];
            }
        	
            double startTime = System.nanoTime();
        	Selection.sort(temp);
            double sTime = System.nanoTime() - startTime;
            sTime = sTime / 1000000000;
        	
            startTime = System.nanoTime();
        	Quick.sort(tempc);
        	double qTime = System.nanoTime() - startTime;
        	qTime = qTime / 1000000000;

            System.out.printf("\t" + n + "\t|\t%.4f\t|\t%.4f\t|\n", sTime, qTime);
            
            n = n*2;
        }
    }

}
