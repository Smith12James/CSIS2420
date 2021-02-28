package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * CSIS 2420 - a02
 * @author James Smith + Riley Westergard
 */

public class Deque<Item> implements Iterable<Item> {
	
	/*
	 * this class is designed to create a deque using a linked list implementation
	 * where you can add items at the beginning of the deque or at the end and also
	 * remove items at the beginning or end.
	 */

    // global variables for the Deque.
    DoubleNode start;
    DoubleNode end;
    int size;

    /**
     * With this constructor I create the start and end of the Deque and inialize
     * the current size to 0.
     * 
     */
    public Deque() {
        this.start = new DoubleNode();
        this.end = new DoubleNode();
        this.size = 0;

    }

    /**
     * This inner-class is used to create the Node objects that are used to store
     * Items in the Deque; it has a last and next variable that is used to traverse
     * over the linked list as well as a conent variable used to reference the new
     * item added to the Deque.
     * 
     */
    private class DoubleNode {
		public DoubleNode last;
        public DoubleNode next;
        public Item content;
        
//        private DoubleNode getNext() {
//        	return next;
//        }
//        
//        private DoubleNode getLast() {
//        	return last;
//        }
//        
//        private Item getItem() {
//        	return content;
//        }

    }

    /**
     * This method is used to dynamically check if the current instance of the Deque
     * is empty; (size == 0).
     * 
     * @return boolen; true if size is < 1 OR false otherwise.
     */
    public boolean isEmpty() {

        return this.size == 0;
    }

    /**
     * This method is used to dynmaically check the current size of the Deque.
     * 
     * @return an int equal to the number of current DoubleNode objects in the
     *         Deque.
     */
    public int size() {

        return this.size;
    }

    /**
     * This method creates a new DoubleNode iteam and inserts it to the front of the
     * Deque, then the size of the current Deque is increased.
     * 
     * @param item the new data that is to be added to the Deque.
     */
    public void addFirst(Item item) {
        DoubleNode newNode = new DoubleNode();
        newNode.content = item;

        if(item == null) {
        	throw new NullPointerException();
        } else if (isEmpty()) {
        	start = newNode;
        	end = newNode;
        } else {
        	DoubleNode oldStart = start;
        	start = newNode;
        	start.next = oldStart;
        	oldStart.last = start;
        }
        this.size++;
    }

    /**
     * This method creates a new DoubleNode iteam and inserts it to the end of the
     * Deque, then the size of the current Deque is increased.
     * 
     * @param item the new data that is to be added to the Deque.
     */
    public void addLast(Item item) {
        DoubleNode newNode = new DoubleNode();
        newNode.content = item;

        if(item == null) {
        	throw new NullPointerException();
        } else if (this.size() == 0) {
        	end = newNode;
            start = newNode;

        } else {
        	DoubleNode oldLast = end;
        	end = newNode;
        	end.last = oldLast;
        	oldLast.next = end;
        	
        }
        this.size++;
    }

    /**
     * This method removes the first DoubleNode object in the Deque and returns the
     * conent that was stored in that DoubleNode as an Item, then the current size
     * is decreased.
     * 
     * @return an Item that is the conent that was stored in the first DoubleNode.
     */
    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {

            Item firstItem = start.content;
            start = start.next;
            this.size--;
            return firstItem;
        }
    }

    /**
     * This method removes the last DoubleNode object in the Deque and returns the
     * conent that was stored in that DoubleNode as an Item, then the current size
     * is decreased.
     * 
     * @return an Item that is the content that was stored in the first DoubleNode.
     */
    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {

            Item lastItem = end.content;
            end = end.last;
            this.size--;
            return lastItem;
        }
    }

    /**
     * 
     */
    
    @Override
    public Iterator<Item> iterator() {
        
    	return new itemIterator<Item>(this);

    }
    
    private class itemIterator<Item> implements Iterator<Item>{
    	
    	Deque<Item>.DoubleNode pointer;
    	
    	itemIterator(Deque<Item> Object) {
    		pointer = Object.start;
    	}
    	
    	public boolean hasNext() {
    		return pointer != null;
    	}
    	
    	public void remove() {
    		throw new UnsupportedOperationException();
    	}
    	
    	public Item next() {
    		
    		Item data = pointer.content;
    		if(data == null) {
    			throw new NoSuchElementException();
    		}
			if(hasNext()) {
				
				pointer = pointer.next;
				return data;
				
			} 
			return data;
    		
    	}
    	
    }

    /**
     * 
     * 
     * @param args
     */
    public static void main(String[] args) {
    	
    	Deque<Object> d = new Deque<Object>();
    	
    	d.addLast("item1");
    	d.addLast(2);
    	d.addLast("item3");
    	d.addLast("4");
    	
    	d.addFirst("itemF");
    	d.addLast(10);
    	d.addLast("two");
    	d.addFirst("one");
//    	Object rl = d.removeLast();
//    	
//    	System.out.println(rl);
//    	System.out.println(d.removeLast());
//    	System.out.println(d.removeLast());
    	
    	System.out.println();
    	
    	for(Object i: d) {
    		System.out.println(i);
    	}

    }
}