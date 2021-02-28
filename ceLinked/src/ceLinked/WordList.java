package ceLinked;

/**
 * WordList is a singly-linked list of Strings. It is designed as a practice
 * opportunity to learn how to manipulate linked sturctures.
 * 
 * @author ..........
 */
public class WordList {
	private Node head; // first node of the list or null
	private Node tail; // last node of the list or null
	private int n; // number of words in the list

	/**
	 * Node of LinkedList that stores the item and a single reference to the next
	 * node.
	 */
	private class Node {
		private String item;
		private Node next;
	}

	/**
	 * Adds a node containing the new item at the end of the list.
	 * 
	 * @param newItem
	 */
	public void append(String newItem) {
		// create a new node based on the word provided by the user
		Node newNode = new Node();
		newNode.item = newItem;

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		n++;
	}

	/**
	 * Adds a node containing the new item at the front of the list.
	 * 
	 * @param newItem
	 */
	public void prepend(String newItem) {
		Node newFirst = new Node();
		newFirst.item = newItem;

		if (isEmpty()) {
			head = newFirst;
			tail = newFirst;
		} else {
			newFirst.next = head;
			head = newFirst;

		}
		n++;

	}

	/**
	 * Returns the index of the first occurrence of the specified item. If the
	 * specified item in not part of the list the method indexOf returns -1
	 * 
	 * @param item
	 * @return index of the first occurrence of the item; -1 if the word was not
	 *         found.
	 */
	public int indexOf(String item) {

		if (this.head.item == item) {
			return 0;
		} else if (this.tail.item == item) {
			return n;
		}

		Node current = this.head.next;
		for (int i = 0; i < n - 1; i++) {

			if (current.item == item) {
				return i + 1;
			}
			current = current.next;

		}
		return -1; // TODO 3
	}

	/**
	 * Checks whether the list contains the given item.
	 * 
	 * @param item
	 * @return true if the item is contained in the list; false otherwise.
	 */
	public boolean contains(String item) {

		if (this.head.item == item) {
			return true;
		} else if (this.tail.item == item) {
			return true;
		}

		Node current = this.head.next;
		for (int i = 0; i < n - 1; i++) {

			if (current.item == item) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Returns the number of elements in the list
	 * 
	 * @return the number of elements
	 */
	public int size() {
		return n;
	}

	/**
	 * Determines whether the list is empty or not.
	 * 
	 * @return true if there are no elements in the list.
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;

		while (current != null) {
			sb.append(current.item).append(" ");
			current = current.next;
		}

		return sb.toString();
	}

	/* * * * * * * * Test Client * * * * * * */
	public static void main(String[] args) {
		WordList list = new WordList();
		System.out.println("size: " + list.size());

		// Dynamically determine whether the list is empty. If so, print
		// 'The list is empty.' otherwise print 'The list is not empty.'
		String listStatis = (list.isEmpty()) ? "The list is empty." : "The list is not empty.";
		System.out.println(listStatis);
		System.out.println();

		list.append("ant");
		list.append("bat");
		list.append("cow");
		list.append("dog");
		
		list.prepend("ape");
		System.out.println("list: " + list);
		System.out.println("size: " + list.size());
		list.prepend("auk");
		System.out.println("list: " + list);
		System.out.println("size: " + list.size());
		
		System.out.print("cow");
		System.out.println((list.contains("cow")) ? " is included in the list" : " is not included in the list");
		System.out.print("yak");
		System.out.println((list.contains("yak")) ? " is included in the list" : " is not included in the list");

		System.out.println();
		
		System.out.print("Index of dog: ");
		System.out.println(list.indexOf("dog"));
		System.out.print("Index of auk: ");
		System.out.println(list.indexOf("auk"));
		System.out.print("Index of yak: ");
		System.out.println(list.indexOf("yak"));
		
		System.out.println();

		System.out.println("list: " + list);
		System.out.println("size: " + list.size());

	}

}