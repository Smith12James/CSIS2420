package books;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BookApp {

	public static void main(String[] args) {

		File filePath = new File("src/books/books.csv");
		String file = "src/books/books.csv";
		ArrayList<String> bookList = new ArrayList<String>();
		bookList = bookScanner(filePath, bookList);
		String[] toolArr = bookList.toArray(new String[0]);
		String[] titles = new String[bookList.size()];
		String[] authors = new String[bookList.size()];
		String[] tempYears = new String[bookList.size()];
		Integer[] years = new Integer[bookList.size()];
		int listLen = toolArr.length; 
		Book book[] = new Book[listLen];
		
		for (int i = 0; i < listLen; i++) {
			titles[i] = subStrTitle(toolArr[i]);
			authors[i] = subStrAuthor(toolArr[i]);
			tempYears[i] = subStrYear(toolArr[i]);	
		}
		
		for(int u = 0; u < listLen; u++) {
			try {
				if(authors[u] == null) {
					authors[u] = "";
				} else {
					authors[u] = subStrAuthor(toolArr[u]);
				}
			} catch(NumberFormatException ex) {
			}
		}
		
		for(int j = 0; j < listLen; j++) {
			try {
				if(tempYears[j] == null) {
					years[j] = 0;
				} else {
					years[j] = Integer.parseInt(tempYears[j]);
				}
			} catch(NullPointerException e) {
				years[j] = 0;
			} catch(NumberFormatException e) {
				years[j] = 0;
			}
		}

		for(int k = 0; k < listLen; k++) {
			book[k] = new Book(titles[k],authors[k], years[k]);
		}
		
		book[0].getList(file);
		System.out.println("");
		System.out.println("Reverse Order:");
		
		Arrays.sort(book, Collections.reverseOrder());
		for (int i = 0; i < book.length; i++) {
			System.out.println(book[i].toString());		
		}

	}
	
	private static String subStrTitle(String title) {
		try {
			String[] results = title.split(",");
			return results[0];
		} catch (Exception e) {}
		
		return null;
	}

	private static String subStrAuthor(String author) {
		try {
			String[] results = author.split(",");
			return results[1];
		} catch (Exception e) {}
		
		return null;
		}

	private static String subStrYear(String year) {
		try {
			String[] results = year.split(",");
			return results[2];
		} catch (Exception e) {}
			return null;
		}	

	private static ArrayList bookScanner(File file, ArrayList list) {

		Scanner sc;
		try {
			sc = new Scanner(file);
			String bookLine = "";
			
			while (sc.hasNextLine()) {
				
				bookLine = sc.nextLine();
				list.add(bookLine);
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}