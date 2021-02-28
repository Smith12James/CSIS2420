/**
 * CSIS 2420 - A01
 * 1/30/2020
 * 
 * Performs a series of computational experiments on a percolation system 
 * such as mean, Standard Deviation, Low Confidence, and High confidence
 * Creates data type PercolationStats
 * 
 * @author Hanna Whitney & James Smith collab
 *
 */
package a01;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	int frequency;
	double[] thresholds;
	double thresh;
	Percolation percolation;
	
	/**
	 * Performs T independent experiments on an N by N grid
	 * 
	 * @param N - Grid parameters set by user
	 * @param T - Frequency of tests set by user
	 * 
	 */
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
		
		frequency = T;
		thresholds= new double[T];
		
		for (int i = 0; i < frequency; i++) {
			percolation = new Percolation(N);
			int open = 0;
			
			while (!percolation.percolates()) {
				int randi = StdRandom.uniform(1, N - 1);
	            int randj = StdRandom.uniform(1, N - 1);
	            
	            if (!percolation.isOpen(randi, randj)) {
					percolation.open(randi, randj);
					open++;
				}
			}
			thresh = (double) open / (N * N);
			thresholds[i] = thresh;
		}
		
		
	}
	
	public static void main(String[] args) {
		
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);

        String confidence = ps.confidenceLow() + ", "
                + ps.confidenceHigh();
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + confidence);
        
    }
	
	/**
	 * Sample mean of percolation threshold
	 * 
	 * @return Mean of threshold
	 */
	public double mean() {
	
		return StdStats.mean(thresholds);
	
	}
	
	
	
	/**
	 * Sample standard deviation of percolation threshold
	 * 
	 * @return Standard deviation of threshold
	 */
	public double stddev() {
		return StdStats.stddev(thresholds);
	}
	
	/**
	 * Low endpoint of 95% confidence interval
	 * 
	 * @return Low endpoint
	 */
	public double confidenceLow() {
		return mean() - ((1.96*stddev())/Math.sqrt(frequency));
	}
	
	/**
	 * High endpoint of 95% confidence interval
	 * 
	 * @return High endpoint
	 */
	public double confidenceHigh() {
		return mean() + ((1.96*stddev())/Math.sqrt(frequency));
	}

}