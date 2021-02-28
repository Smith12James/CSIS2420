package a03;

/**
 * @authors Riley Westergard + James Smith
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BinarySearchDeluxe {

    /**
     *
     * @param <Key>
     * @param a
     * @param key
     * @param comparator
     * @return The index of the first key in a[] that equals the search key, or -1
     *         if no such key exists in the array.
     */

    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a.equals(null) || key.equals(null) || comparator.equals(null)) {

            throw new NullPointerException();
        }

        else {

            int left = 0;
            int right = a.length - 1;
            int firstIndex = -1;
            while (left <= right) {

                int mid = (left + right) / 2;
                if (comparator.compare(a[mid], key) > 0) {

                    right = mid - 1;
                } else if (comparator.compare(a[mid], key) < 0) {

                    left = mid + 1;
                }

                else {

                    firstIndex = mid;
                    right = mid - 1;
                }
            }
            return firstIndex;
        }

    }

    /**
     *
     *
     * @param <Key>
     * @param a
     * @param key
     * @param comparator
     * @return The index of the last key in a[] that equals the search key, or -1 if
     *         no such key exists in the array.
     */

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a.equals(null) || key.equals(null) || comparator.equals(null)) {

            throw new NullPointerException();
        }

        else {

            int left = 0;
            int right = a.length - 1;
            int lastIndex = -1;
            while (left <= right) {

                int mid = (left + right) / 2;
                if (comparator.compare(a[mid], key) > 0) {

                    right = mid - 1;
                } else if (comparator.compare(a[mid], key) < 0) {

                    left = mid + 1;
                }

                else {

                    lastIndex = mid;
                    left = mid + 1;
                }
            }
            return lastIndex;
        }
    }

    /*
     * Main is used for testing purposes
     */

    public static void main(String[] args) {

        String[] testingStrings = new String[25];
        for (int i = 0; i < testingStrings.length; i++) {

            if (i % 2 != 0) {
                testingStrings[i] = "Finny";
            } else {
                testingStrings[i] = "this is the first test!";
            }
        }

        Arrays.sort(testingStrings, Collections.reverseOrder());

        System.out.println("This is the result of String Arrays\n-----------------------------------");

        System.out.println("First Occurrance of 'Finny' in Array at index: "
                + firstIndexOf(testingStrings, "Finny", Collections.reverseOrder()));

        System.out.println("Last Occurrance of 'Finny' in Array at index: "
                + lastIndexOf(testingStrings, "Finny", Collections.reverseOrder()));

    }
}