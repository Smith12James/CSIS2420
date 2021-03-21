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
		
		System.out.print(solution(10));

//		File filePath = new File("src/books/books.csv");
//		String file = "src/books/books.csv";
//		ArrayList<String> bookList = new ArrayList<String>();
//		bookList = bookScanner(filePath, bookList);
//		String[] toolArray = bookList.toArray(new String[0]);
//		String[] titles = new String[bookList.size()];
//		String[] authors = new String[bookList.size()];
//		String[] tempYears = new String[bookList.size()];
//		Integer[] years = new Integer[bookList.size()];
//		int listLength = toolArray.length; 
//		Book book[] = new Book[listLength];
//		
//		for (int i = 0; i < listLength; i++) {
//			titles[i] = subStringTitleTest(toolArray[i]);
//			authors[i] = subStringAuthorTest(toolArray[i]);
//			tempYears[i] = subStringYearTest(toolArray[i]);	
//		}
//		
//		for(int u = 0; u < listLength; u++) {
//			try {
//				if(authors[u] == null) {
//					authors[u] = "";
//				} else {
//					authors[u] = subStringAuthorTest(toolArray[u]);
//				}
//			} catch(NumberFormatException ex) {
//			}
//		}
//		
//		for(int j = 0; j < listLength; j++) {
//			try {
//				if(tempYears[j] == null) {
//					years[j] = 0;
//				} else {
//					years[j] = Integer.parseInt(tempYears[j]);
//				}
//			} catch(NullPointerException e) {
//				years[j] = 0;
//			} catch(NumberFormatException e) {
//				years[j] = 0;
//			}
//		}
//
//		for(int k = 0; k < listLength; k++) {
//			book[k] = new Book(titles[k],authors[k], years[k]);
//		}
//		
//		book[0].getList(file);
//		System.out.println("");
//		System.out.println("Reverse Order:");
//		
//		Arrays.sort(book, Collections.reverseOrder());
//		for (int i = 0; i < book.length; i++) {
//			System.out.println(book[i].toString());		
//		}

	}
	
	public static int solution(int number) {
	    
		if(number <= 0) {
			return 0;
		} else if(number <= 4) {
			return 3;
		} else if(number <= 5) {
		    return 8;
		}
		
		int sumThree = 3;
		int sumFive = 5;
		    
		int temp = number / 5;
		int arrFive[] = new int[temp];
		temp = number / 3;
		int arrThree[] = new int[temp];
		    
		for(int i = 0; i < arrThree.length; i++) {
		      
		  arrThree[i] = 3 * (i + 1);
		  sumThree = sumThree * (i + 1);
		      
		}
		    
		for(int i = 0; i < arrFive.length; i++) {
		      
		  arrFive[i] = 5 * (i + 1);
		  sumFive = sumFive * (i + 1);
		      
		}
		      
		int totalArr[] = new int[arrThree.length + arrFive.length];

		int sum = 3;
		int total = 0;
		    
		for(int i = 0; i < totalArr.length; i++) {
		  total = total + totalArr[i];
		}
		return total;
		    
	}
	
	private static String subStringTitleTest(String title) {
		try {
			String[] results = title.split(",");
			return results[0];
		} catch (Exception e) {}
		
		return null;
	}

	private static String subStringAuthorTest(String author) {
		try {
			String[] results = author.split(",");
			return results[1];
		} catch (Exception e) {}
		
		return null;
		}

	private static String subStringYearTest(String year) {
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