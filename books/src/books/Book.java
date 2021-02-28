package books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Book implements Comparable<Book>{
	
	private String title;
	private String author;
	private int year;

	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}	
	
	@Override
	public String toString() {
		return this.title + " by " + this.author + " (" + this.year + ")";
	}
	
	public static List<Book> getList(String file) {
		File filePath = new File(file);
		ArrayList<String> bookList = new ArrayList<String>();
		bookList = bookScanner(filePath, bookList);
		String[] toolArr = bookList.toArray(new String[0]);
		String[] title = new String[bookList.size()];
		String[] authors = new String[bookList.size()];
		String[] tempYears = new String[bookList.size()];
		Integer[] years = new Integer[bookList.size()];

		int listLength = toolArr.length; 

		Book book[] = new Book[listLength];

		for (int i = 0; i < listLength; i++) {
			title[i] = subStrTitle(toolArr[i]);
			authors[i] = subStrAuthor(toolArr[i]);
			tempYears[i] = subStrYear(toolArr[i]);	
			}

		for(int u = 0; u < listLength; u++) {
			try {
				if(authors[u] == null) {
					authors[u] = "";
				}
				else {
					authors[u] = subStrAuthor(toolArr[u]);
				}
			} catch(NumberFormatException ex) {
				System.err.println("Problem reading in " + toolArr[u]);
			}
		}
		
		for(int j = 0; j < listLength; j++) {
			try {
				if(tempYears[j] == null) {
				years[j] = 0;
				} else {
					years[j] = Integer.parseInt(tempYears[j]);
				}
			}			
			catch(NullPointerException e) {
				System.err.println("Problem reading in " + toolArr[j]);
				years[j] = 0;
			} catch(NumberFormatException e) {
				System.err.println("Problem reading in " + toolArr[j]);
				years[j] = 0;
			}
		}
		
		for(int k = 0; k < listLength; k++) {
			book[k] = new Book(title[k],authors[k], years[k]);
		}
		
		
		Arrays.sort(book);
		System.out.println("Number of books read in: " + book.length);
		System.out.println("");
		System.out.println("Sorted Book List:");
		for (int i = 0; i < book.length; i++) {
			System.out.println(book[i].toString());
		}
		return null;
	}

	private static String subStrTitle(String title) {
		try {
			String[] results = title.split(",");
			return results[0];
		} catch (Exception e) {
			System.err.println("Problem reading in " + title);
		}
			return null;
		}
	
	private static String subStrAuthor(String author) {
		try {
			String[] results = author.split(",");
			return results[1];
		} catch (Exception e) {
		System.err.println("Problem reading in " + author);
		}
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

	@Override
	public int compareTo(Book other) {
	    return this.getTitle().compareTo(other.getTitle());
	}
	
}