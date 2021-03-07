package a03;

/**
 * @authors Riley Westergard + James Smith
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BinarySearchDeluxe {

    /**
     * @param <Key>
     * @param a
     * @param key
     * @param comparator
     * @return The index of the first key in a[] that equals the search key, or -1
     * if no such key exists in the array.
     */

    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null) {
            throw new java.lang.NullPointerException("null arguments not allowed");

        }

        int left = 0;
        int right = a.length - 1;
        if (comparator.compare(a[0], key) == 0) { return 0; }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (comparator.compare(key, a[mid]) < 0) {
                right = mid - 1;

            } else if (comparator.compare(key, a[mid]) > 0) {
                left = mid + 1;

            } else if (comparator.compare(a[mid - 1], a[mid]) == 0) {
                right = mid - 1;

            } else {
                return mid;

            }

        }
        return -1;

    }

    /**
     * @param <Key>
     * @param a
     * @param key
     * @param comparator
     * @return The index of the last key in a[] that equals the search key, or -1 if
     * no such key exists in the array.
     */

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null)
            throw new java.lang.NullPointerException("null arguments not allowed");
        int left = 0;
        int right = a.length - 1;
        if (comparator.compare(a[right], key) == 0) return right;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (comparator.compare(key, a[mid]) < 0) {
                right = mid - 1;

            } else if (comparator.compare(key, a[mid]) > 0) {
                left = mid + 1;

            } else if(comparator.compare(a[mid +1],a[mid])==0) {
                left =mid +1;

            } else {
                return mid;

            }
        }
        return -1;

    }

    /**
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