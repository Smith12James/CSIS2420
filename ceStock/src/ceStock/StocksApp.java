package ceStock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import edu.princeton.cs.algs4.*;
import java.io.IOException;

public class StocksApp {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private static ST<Date, Double> readCSV(String fileName, int keyField, int valField) throws IOException {

        // symbol table
        ST<Date, Double> st = new ST<Date, Double>();

        // read in the data from csv file
        In in = new In(fileName);

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String dateInString = tokens[keyField];
            try {
                java.util.Date key = formatter.parse(dateInString);
                Double val = Double.parseDouble(tokens[valField]);
                st.put(key, val);
            } catch (Exception e) {
                continue;
            }

        }
        return st;


    }

    public static void main(String[] args) throws IOException {

        String fileName = ("src/ceStock/AMZN.csv");
        ST<Date, Double> st = readCSV(fileName, 0, 4);

        StdOut.println("Recordes Read\t: " + st.size());
        StdOut.printf("A) Oldest closing price:  $%.2f.%n" , st.get(st.min()));
        StdOut.printf("B) Newest closing price:  $%.2f.%n" , st.get(st.max()));
        if (st.get(st.max()) - st.get(st.min()) > 0){
            StdOut.printf("C) The closing price increased by $%.2f.%n" , (st.get(st.max()) - st.get(st.min())));
        }

        StdOut.println();

        try {
            java.util.Date date1 = formatter.parse("2018-08-31");
            StdOut.printf("D) Closing price from 08/31/18: $%.2f.%n" , st.get(date1));
            java.util.Date date2 = formatter.parse("2020-03-10");
            StdOut.printf("E) Closing price from 03/10/20: $%.2f.%n" , st.get(date2));
            StdOut.printf("F) The closing price decreased by $%.2f.%n" , (st.get(date1) - st.get(date2)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StdOut.println();
        try {
            java.util.Date date3 = formatter.parse("2019-01-01");
            StdOut.printf("G) First available closing price in 2019: " + formatter.format(st.ceiling(date3)) + " $%.2f.%n" , st.get(st.ceiling(date3)));
            java.util.Date date4 = formatter.parse("2020-01-01");
            StdOut.printf("H) Last available closing price in 2020: " + formatter.format(st.floor(date4)) + " $%.2f.%n" , st.get(st.floor(date4)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}