package a03;

/**
 * @authors James Smith + Riley Westergard
 */

import java.util.Comparator;

public class Term implements Comparable<Term> {

    public String query;
    public double weight;

    /**
     * This constructor inilaizes the values of the global variables.
     *
     * @param query  A string that is the name of the Term (will be compared to the
     *               users' input)
     * @param weight A double that is used to order the given Terms whose queries
     *               match that of users' input
     */
    public Term(String query, double weight) {

        if (query.equals(null)) {

            throw new NullPointerException();
        } else if (weight < 0) {

            throw new IllegalArgumentException();
        } else {
            this.query = query;
            this.weight = weight;
        }
    }

    /**
     * This method is used to create a Comparator that sorts the Terms in desencing
     * order of their weight values (largest ---> smallest)
     *
     * @return A new comparator object with an implemented compare() method
     *         returning values that are the result of the Double.compare() method
     *         acting on the objects' weight values.
     */
    public static Comparator<Term> byReverseWeightOrder() {

        return new Comparator<Term>() {

            @Override
            public int compare(Term o1, Term o2) {

                return Double.compare(o2.weight, o1.weight);

            }
        };
    }

    /**
     * This method is used to create a Comparator that sorts the Terms in the
     * assending order (Alphabetically) of their query values for a given prefix of
     * lenghth (r)
     *
     * @param r This int is the length of the prefix used to order the Terms
     * @return A new comparator object with an implemented compare() method
     *         returning values that are the result of the String.compareTo() method
     *         acting on the objects' query (prefix of length r) values.
     */
    public static Comparator<Term> byPrefixOrder(int r) {

        if (r < 0) {
            throw new IllegalArgumentException();
        }

        return new Comparator<Term>() {

            @Override
            public int compare(Term o1, Term o2) {

                return o1.query.substring(0, r).compareTo(o2.query.substring(0, r));
            }
        };
    }

    /**
     * This method orders the Terms in assending order (Alphabetically) of their
     * query values as a whole.
     *
     * @param that A Term object which is compared to the Term object that calls
     *             this method
     * @return An int that is the result of the compareTo() method which is called
     *         by this object's query value acting on the provided Term's query
     *         value.
     *
     */
    public int compareTo(Term that) {

        return this.query.compareTo(that.query);
    }

    /**
     * Writes weight and query to a string for clean output
     */
    @Override
    public String toString() {

        return this.weight + "\t" + this.query;
    }
}