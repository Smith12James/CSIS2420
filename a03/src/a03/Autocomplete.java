package a03;

/**
 * @authors James Smith and Riley Westergard
 */

import java.util.Arrays;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public final class Autocomplete {
    private final int n;
    private final Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if(terms == null) { throw new NullPointerException(); }

        this.n = terms.length;
        this.terms = new Term[n];

        System.arraycopy(terms, 0, this.terms, 0, n);

        Quick.sort(this.terms);

    }

    // Return all terms that start with the given prefix, in descending order of weight.
    // if no matches return an array of size 0
    public Term[] allMatches(String prefix) {
        Term[] temp1;
        if(prefix == null) throw new java.lang.NullPointerException();

        int first1 = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        if(first1 == - 1) { return new Term[0]; }

        int last1 = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

        if((last1 - first1) + 1 == 2) {
            temp1 = new Term[1];
        } else {
            temp1 = new Term[1 + last1 - first1];
        }

        for(int i = 0; i<temp1.length; i++){
            temp1[i] = terms[first1++];
        }
        Arrays.sort(temp1, Term.byReverseWeightOrder());

        return temp1;
    }

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if(prefix.equals(null)) { throw new NullPointerException(); }

        Term searchTerm = new Term(prefix, 0);
        int first1 = BinarySearchDeluxe.firstIndexOf(terms, searchTerm,Term.byPrefixOrder(prefix.length()));
        int last1 = BinarySearchDeluxe.lastIndexOf(terms, searchTerm,Term.byPrefixOrder(prefix.length()));
        int found =  1 + (last1 - first1);

        if(first1 < 0) { return 0; }
        else if(found == 2) { return 1; }
        else if(found <= 0) { return 0; }
        else if(last1 == first1) { return 1; }
        else { return found; }

    }

    public static void main(String[] args) {



    }

}