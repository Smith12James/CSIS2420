package ceMail;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

public class DemoHeap {

    public static void main(String args[]) {

        MaxPQ<Mail> max = new MaxPQ<Mail>();

        for(int i = 0; i < 25; i++) {
            max.insert(new Mail());

        }

        int i = 1;
        for(Mail m : max) {
            StdOut.println(i + ",\t" + m);
            i++;
        }

    }

}
